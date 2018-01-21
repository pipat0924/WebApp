package th.net.cat.epis.batch.task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.cxf.common.util.SortedArraySet;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.stereotype.Component;

import th.net.cat.epis.service.PaymentService;

@Component("checkAndCalculateReceiptStatus")
public class CheckAndCalculateReceiptStatus implements Tasklet {

	@Resource(name = "episJdbcTemplate")
	JdbcTemplate episJdbcTemplate;
	@Autowired
	PaymentService paymentService;
	@Value("${epis.task.active}")
	String taskActive;

	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		if (taskActive.equals("1")) {
			calculateCreateReceiptSuccess();
			calculateReverseReceiptSuccess();
		}
		return RepeatStatus.FINISHED;
	}

	private void calculateCreateReceiptSuccess() {
		Date currentDate = new Date();
		final List<Receipt> receipts = new LinkedList<Receipt>();
		Set<Receipt> checkReceipts = new SortedArraySet<Receipt>();

		episJdbcTemplate.query("SELECT DISTINCT receiptid " + ", receiptattr " + ", issuccess " + "FROM "
				+ "(SELECT rownum, rcp.receiptid AS receiptid, rcp.attributes AS receiptattr, txn.trackingid AS trackingid "
				+ ", CASE WHEN txn.trackingid IS NULL THEN 0 ELSE 1 END AS issuccess " + "FROM CORRECEIPT rcp "
				+ "LEFT JOIN TMPINVOICE inv ON inv.receiptid = rcp.receiptid "
				+ "LEFT JOIN TMPINVOICESERVICE svc ON svc.invoiceid = inv.invoiceid "
				+ "LEFT JOIN TRSPAYMENTREF txn ON txn.serviceid = svc.serviceid "
				+ "LEFT JOIN TMPACCOUNT cus ON cus.accountid = inv.accountid " + "WHERE rcp.attributes not like '%C%' "
				+ "ORDER BY receiptid asc ) result " + "GROUP BY receiptid, receiptattr, issuccess "
				+ "ORDER BY receiptid, receiptattr, issuccess asc ", new RowCountCallbackHandler() {
					@Override
					public void processRow(ResultSet resultSet, int rowNum) throws SQLException {
						Receipt rcp = new Receipt();
						rcp.setId(resultSet.getLong("receiptid"));
						rcp.setAttributes(resultSet.getString("receiptattr"));
						rcp.setSuccess(resultSet.getBoolean("issuccess"));
						receipts.add(rcp);
					}
				});

		// Fill Up Set
		for (Receipt rcp : receipts) {
			Receipt distinctReceipt = new Receipt(rcp.getId(), rcp.getAttributes(), true);
			checkReceipts.add(distinctReceipt);
		}

		for (Receipt distinctRcp : checkReceipts) {
			for (Receipt rcp : receipts) {
				if (distinctRcp.getId() == rcp.getId()) {
					distinctRcp.setSuccess(distinctRcp.isSuccess() & rcp.isSuccess());
				}
			}
		}
		String attributes;
		for (Receipt distinctRcp : checkReceipts) {
			if (distinctRcp.isSuccess()) {
				attributes = distinctRcp.getAttributes().replaceAll("C", "") + "C";
				episJdbcTemplate.update("UPDATE CORRECEIPT " + "SET attributes = ? " + ", updatedttm = ?"
						+ ", updatesystem = 'BAT'" + "WHERE receiptid = ?", attributes, currentDate,
						distinctRcp.getId());
				paymentService.saveLogCorReceiptByReceiptId(distinctRcp.getId(),"C");
			}
		}

	}

	private void calculateReverseReceiptSuccess() {
		Date currentDate = new Date();
		final List<Receipt> receipts = new LinkedList<Receipt>();
		Set<Receipt> checkReceipts = new SortedArraySet<Receipt>();

		episJdbcTemplate.query("SELECT DISTINCT receiptid " + ", receiptattr " + ", issuccess " + "FROM "
				+ "(SELECT rownum, rcp.receiptid AS receiptid, rcp.attributes AS receiptattr "
				+ ", CASE WHEN txn.attributes LIKE '%R%' THEN 1 ELSE 0 END AS issuccess " + "FROM CORRECEIPT rcp "
				+ "LEFT JOIN TMPINVOICE inv ON inv.receiptid = rcp.receiptid "
				+ "LEFT JOIN TMPINVOICESERVICE svc ON svc.invoiceid = inv.invoiceid "
				+ "LEFT JOIN TRSPAYMENTREF txn ON txn.serviceid = svc.serviceid "
				+ "LEFT JOIN TMPACCOUNT cus ON cus.accountid = inv.accountid " + "WHERE rcp.attributes like '%Z%' "
				+ "ORDER BY receiptid asc ) result " + "GROUP BY receiptid, receiptattr, issuccess "
				+ "ORDER BY receiptid, receiptattr, issuccess asc ", new RowCountCallbackHandler() {
					@Override
					public void processRow(ResultSet resultSet, int rowNum) throws SQLException {
						if (rowNum > 9999)
							return;
						Receipt rcp = new Receipt();
						rcp.setId(resultSet.getLong("receiptid"));
						rcp.setAttributes(resultSet.getString("receiptattr"));
						rcp.setSuccess(resultSet.getBoolean("issuccess"));
						receipts.add(rcp);
					}
				});

		// Fill Up Set
		for (Receipt rcp : receipts) {
			Receipt distinctReceipt = new Receipt(rcp.getId(), rcp.getAttributes(), true);
			checkReceipts.add(distinctReceipt);
		}

		for (Receipt distinctRcp : checkReceipts) {
			for (Receipt rcp : receipts) {
				if (distinctRcp.getId() == rcp.getId()) {
					distinctRcp.setSuccess(distinctRcp.isSuccess() & rcp.isSuccess());
				}
			}
		}
		String attributes;
		for (Receipt distinctRcp : checkReceipts) {
			if (distinctRcp.isSuccess()) {
				attributes = distinctRcp.getAttributes().replaceAll("Z", "") + "R";
				episJdbcTemplate.update("UPDATE CORRECEIPT " + "SET attributes = ? " + ", updatedttm = ?"
						+ ", updatesystem = 'BAT'" + "WHERE receiptid = ?", attributes, currentDate,
						distinctRcp.getId());
				paymentService.saveLogCorReceiptByReceiptId(distinctRcp.getId(),"R");
			}
		}
	}

	public static class Receipt implements Comparable<Receipt> {
		private long id;
		private String attributes;
		private boolean isSuccess;

		public Receipt() {
		};

		public Receipt(long id, String attributes, boolean isSuccess) {
			super();
			this.id = id;
			this.attributes = attributes;
			this.isSuccess = isSuccess;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getAttributes() {
			return attributes;
		}

		public void setAttributes(String attributes) {
			this.attributes = attributes;
		}

		public boolean isSuccess() {
			return isSuccess;
		}

		public void setSuccess(boolean isSuccess) {
			this.isSuccess = isSuccess;
		}

		@Override
		public int compareTo(Receipt o) {
			return (o.getId() == this.id) ? 0 : o.getId() < this.id ? -1 : 1;
		}
	}
}