package th.net.cat.epis.dto;

import java.util.ArrayList;
import java.util.List;

public class CancelPaymentOTTBossDTO {

	public static class Receipt {
		private Long id;
		private String no;
		private String reasonCode;
		private String reasonDesc;
		private String accountName;
		private String addrLine1;
		private String source;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getNo() {
			return no;
		}
		public void setNo(String no) {
			this.no = no;
		}
		public String getReasonCode() {
			return reasonCode;
		}
		public void setReasonCode(String reasonCode) {
			this.reasonCode = reasonCode;
		}
		public String getReasonDesc() {
			return reasonDesc;
		}
		public void setReasonDesc(String reasonDesc) {
			this.reasonDesc = reasonDesc;
		}
	
		public String getSource() {
			return source;
		}
		public void setSource(String source) {
			this.source = source;
		}
		public String getAccountName() { return accountName; }

		public void setAccountName(String accountName) { this.accountName = accountName; }

		public String getAddrLine1() { return addrLine1; }

		public void setAddrLine1(String addrLine1) { this.addrLine1 = addrLine1; }
	}

	private String reasonCode;
	private String reasonDesc;
	private List<Receipt> receipts = new ArrayList<Receipt>();
	private String userAuthen;
	private boolean flgNewReceipt;

	public String getReasonCode() {
		return reasonCode;
	}
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	public String getReasonDesc() {
		return reasonDesc;
	}
	public void setReasonDesc(String reasonDesc) {
		this.reasonDesc = reasonDesc;
	}
	public List<Receipt> getReceipts() {
		return receipts;
	}
	public void setReceipts(List<Receipt> receipts) {
		this.receipts = receipts;
	}

	public String getUserAuthen() {
		return userAuthen;
	}

	public void setUserAuthen(String userAuthen) {
		this.userAuthen = userAuthen;
	}

	
	public boolean isFlgNewReceipt() { return flgNewReceipt; }

	public void setFlgNewReceipt(boolean flgNewReceipt) { this.flgNewReceipt = flgNewReceipt; }
}