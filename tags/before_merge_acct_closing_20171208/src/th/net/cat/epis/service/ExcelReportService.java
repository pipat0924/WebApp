package th.net.cat.epis.service;

import static th.net.cat.epis.util.AppConstants.JETT_EXCEL_PATH;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.jett.transform.ExcelTransformer;
import th.net.cat.epis.to.report.DailyPaymentTO;
import th.net.cat.epis.util.AppConstants;

@Service
public class ExcelReportService {

	@Autowired
	ExcelTransformer transformer;

	public byte[] generateExcelReportPrintDailyPaymentReportTemplate(ServletContext context ,DailyPaymentTO dailyPaymentTO, List<DailyPaymentTO> list, String serviceType ) throws  IOException, InvalidFormatException  {
		byte[] b = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		Workbook workbook;
		InputStream is = null;
			String file;
		 if(AppConstants.PAYMENT_TYPE_AGENT.equalsIgnoreCase(serviceType)){
				file = context.getRealPath(JETT_EXCEL_PATH) + File.separatorChar + "printAgentDailyPaymentReportTemplate.xls";
		 }else if(AppConstants.PAYMENT_TYPE_MNP.equalsIgnoreCase(serviceType)){
				 file = context.getRealPath(JETT_EXCEL_PATH) + File.separatorChar + "printMNPDailyPaymentReportTemplate.xls";
		 }else{
				file = context.getRealPath(JETT_EXCEL_PATH) + File.separatorChar + "printDailyPaymentReportTemplate.xls";
		 	}
		System.out.println(file);

		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ExcelTransformer transformer = new ExcelTransformer();

		List<String> templateSheetNames = new ArrayList<String>();
		List<String> sheetNames = new ArrayList<String>();
		List<Map<String, Object>> beansList = new ArrayList<Map<String, Object>>();
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("dateTimeFromTo", dailyPaymentTO.getPaymentDate());
		beans.put("currentDate", dailyPaymentTO.getDocumentDate());
		beans.put("branchName", dailyPaymentTO.getBranchName());
		beans.put("branchNamePos", dailyPaymentTO.getBranchName() + "/" + dailyPaymentTO.getPos());
		beans.put("userNameFullName", dailyPaymentTO.getUserName());
		beans.put("payments", list);
		beansList.add(beans);
		sheetNames.add("InvReceiveStockByStock_Landscap");

		workbook = WorkbookFactory.create(is);
//		transformer.addCssText("Font: FreesiaUPC,Points: 12");
		transformer.transform(workbook, templateSheetNames, sheetNames, beansList);
		workbook.setActiveSheet(0);

		workbook.write(os);
		b = os.toByteArray();
		os.close();
		workbook.close();

		return b;

	}

	
	public byte[] generateExcelReportPrintCloseEndOfDay(ServletContext context ,DailyPaymentTO dailyPaymentTO, List<DailyPaymentTO> list) throws  IOException, InvalidFormatException  {
		byte[] b = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		Workbook workbook;
		InputStream is = null;
		String file = context.getRealPath(JETT_EXCEL_PATH) + File.separatorChar + "printCloseEndOfDayRepost.xls";
		System.out.println(file);

		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ExcelTransformer transformer = new ExcelTransformer();

		List<String> templateSheetNames = new ArrayList<String>();
		List<String> sheetNames = new ArrayList<String>();
		List<Map<String, Object>> beansList = new ArrayList<Map<String, Object>>();
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("dateTimeFromTo", dailyPaymentTO.getPaymentDate());
		beans.put("currentDate", dailyPaymentTO.getDocumentDate());
		beans.put("branchName", dailyPaymentTO.getBranchName());
		beans.put("shopNo", dailyPaymentTO.getShopNo());
		beans.put("branchNamePos", dailyPaymentTO.getBranchName() + "/" + dailyPaymentTO.getPos());
		beans.put("userNameFullName", dailyPaymentTO.getUserName());
		beans.put("payments", list);
		beansList.add(beans);
		sheetNames.add("InvReceiveStockByStock_Landscap");

		workbook = WorkbookFactory.create(is);
//		transformer.addCssText("Font: FreesiaUPC,Points: 12");
		transformer.transform(workbook, templateSheetNames, sheetNames, beansList);
		workbook.setActiveSheet(0);

		workbook.write(os);
		b = os.toByteArray();
		os.close();
		workbook.close();

		return b;

	}
	
	public byte[] generateExcelReportPrintUnCloseEndOfDay(ServletContext context ,DailyPaymentTO dailyPaymentTO, List<DailyPaymentTO> list) throws  IOException, InvalidFormatException  {
		byte[] b = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		Workbook workbook;
		InputStream is = null;
		String file = context.getRealPath(JETT_EXCEL_PATH) + File.separatorChar + "printUnCloseEndOfDayRepost.xls";
		System.out.println(file);

		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ExcelTransformer transformer = new ExcelTransformer();

		List<String> templateSheetNames = new ArrayList<String>();
		List<String> sheetNames = new ArrayList<String>();
		List<Map<String, Object>> beansList = new ArrayList<Map<String, Object>>();
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("dateTimeFromTo", dailyPaymentTO.getPaymentDate());
		beans.put("currentDate", dailyPaymentTO.getDocumentDate());
		beans.put("branchName", dailyPaymentTO.getBranchName());
		beans.put("shopNo", dailyPaymentTO.getShopNo());
		beans.put("branchNamePos", dailyPaymentTO.getBranchName() + "/" + dailyPaymentTO.getPos());
		beans.put("userNameFullName", dailyPaymentTO.getUserName());
		beans.put("payments", list);
		beansList.add(beans);
		sheetNames.add("InvReceiveStockByStock_Landscap");

		workbook = WorkbookFactory.create(is);
//		transformer.addCssText("Font: FreesiaUPC,Points: 12");
		transformer.transform(workbook, templateSheetNames, sheetNames, beansList);
		workbook.setActiveSheet(0);

		workbook.write(os);
		b = os.toByteArray();
		os.close();
		workbook.close();

		return b;

	}
	
	
	
	public byte[] generateExcelReportprintDiscountDailyCredit(ServletContext context ,DailyPaymentTO dailyPaymentTO, List<DailyPaymentTO> list) throws  IOException, InvalidFormatException  {
		byte[] b = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		Workbook workbook;
		InputStream is = null;
		String file = context.getRealPath(JETT_EXCEL_PATH) + File.separatorChar + "prinDiscountDailyCreditReport.xls";
		System.out.println(file);

		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ExcelTransformer transformer = new ExcelTransformer();

		List<String> templateSheetNames = new ArrayList<String>();
		List<String> sheetNames = new ArrayList<String>();
		List<Map<String, Object>> beansList = new ArrayList<Map<String, Object>>();
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("dateTimeFromTo", dailyPaymentTO.getPaymentDate());
		beans.put("currentDate", dailyPaymentTO.getDocumentDate());
		beans.put("branchName", dailyPaymentTO.getBranchName());
		beans.put("shopNo", dailyPaymentTO.getShopNo());
		beans.put("branchNamePos", dailyPaymentTO.getBranchName() + "/" + dailyPaymentTO.getPos());
		beans.put("userNameFullName", dailyPaymentTO.getUserName());
		beans.put("datasize", list.size());
		beans.put("payments", list);
		beansList.add(beans);
		sheetNames.add("InvReceiveStockByStock_Landscap");

		workbook = WorkbookFactory.create(is);
//		transformer.addCssText("Font: FreesiaUPC,Points: 12");
		transformer.transform(workbook, templateSheetNames, sheetNames, beansList);
		workbook.setActiveSheet(0);

		workbook.write(os);
		b = os.toByteArray();
		os.close();
		workbook.close();

		return b;

	}

	public byte[] generateExcelReportPrintChequePaymentReportTemplate(ServletContext context ,DailyPaymentTO dailyPaymentTO, List<DailyPaymentTO> list) throws  IOException, InvalidFormatException  {
		byte[] b = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		Workbook workbook;
		InputStream is = null;
		String file = context.getRealPath(JETT_EXCEL_PATH) + File.separatorChar + "printChequePaymentReportTemplate.xls";
		System.out.println(file);

		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ExcelTransformer transformer = new ExcelTransformer();

		List<String> templateSheetNames = new ArrayList<String>();
		List<String> sheetNames = new ArrayList<String>();
		List<Map<String, Object>> beansList = new ArrayList<Map<String, Object>>();
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("dateTimeFromTo", dailyPaymentTO.getPaymentDate());
		beans.put("documentDate", dailyPaymentTO.getDocumentDate());
		beans.put("branchName", dailyPaymentTO.getBranchName());
		beans.put("branchNamePos", dailyPaymentTO.getBranchName() + "/" + dailyPaymentTO.getPos());
		beans.put("userName", dailyPaymentTO.getUserName());
		beans.put("report", list);
		beansList.add(beans);
		sheetNames.add("รายงานการรับชำระเงินด้วยเช็ค");

		workbook = WorkbookFactory.create(is);
//		transformer.addCssText("Font: FreesiaUPC,Points: 12");
		transformer.transform(workbook, templateSheetNames, sheetNames, beansList);
		workbook.setActiveSheet(0);

		workbook.write(os);
		b = os.toByteArray();
		os.close();
		workbook.close();

		return b;

	}
	
	public byte[] generateExcelReportPrintSumCloseEndOfDayReportTemplate(ServletContext context ,DailyPaymentTO dailyPaymentTO, List<DailyPaymentTO> list) throws  IOException, InvalidFormatException  {
		byte[] b = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		Workbook workbook;
		InputStream is = null;
		String file = context.getRealPath(JETT_EXCEL_PATH) + File.separatorChar + "printSumCloseEndOfDayReportTemplate.xls";
		System.out.println(file);

		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ExcelTransformer transformer = new ExcelTransformer();

		List<String> templateSheetNames = new ArrayList<String>();
		List<String> sheetNames = new ArrayList<String>();
		List<Map<String, Object>> beansList = new ArrayList<Map<String, Object>>();
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("dateTimeFromTo", dailyPaymentTO.getPaymentDate());
		beans.put("documentDate", dailyPaymentTO.getDocumentDate());
		beans.put("branchName", dailyPaymentTO.getBranchName());
		beans.put("branchNamePos", dailyPaymentTO.getBranchName() + "/" + dailyPaymentTO.getPos());
		beans.put("userName", dailyPaymentTO.getUserName());
		beans.put("report", list);
		beansList.add(beans);
		sheetNames.add("CloseEndOfDay");

		workbook = WorkbookFactory.create(is);
//		transformer.addCssText("Font: FreesiaUPC,Points: 12");
		transformer.transform(workbook, templateSheetNames, sheetNames, beansList);
		workbook.setActiveSheet(0);

		workbook.write(os);
		b = os.toByteArray();
		os.close();
		workbook.close();

		return b;

	}
	public byte[] generateExcelReportPrintMonthlyDeductionTemplate(ServletContext context ,DailyPaymentTO dailyPaymentTO, List<DailyPaymentTO> list) throws  IOException, InvalidFormatException  {
		byte[] b = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		Workbook workbook;
		InputStream is = null;
		String file = context.getRealPath(JETT_EXCEL_PATH) + File.separatorChar + "printMonthlyDeductPaymentReport.xls";
		System.out.println(file);

		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ExcelTransformer transformer = new ExcelTransformer();

		List<String> templateSheetNames = new ArrayList<String>();
		List<String> sheetNames = new ArrayList<String>();
		List<Map<String, Object>> beansList = new ArrayList<Map<String, Object>>();
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("period", dailyPaymentTO.getPeriod());
		beans.put("documentDate", dailyPaymentTO.getDocumentDate());
		beans.put("branchName", dailyPaymentTO.getBranchName());
		beans.put("branchNamePos", dailyPaymentTO.getBranchName() + "/" + dailyPaymentTO.getPos());
		beans.put("userName", dailyPaymentTO.getUserName());
		beans.put("report", list);
		beansList.add(beans);
		sheetNames.add("MonthlyDeductPaymentReport");

		workbook = WorkbookFactory.create(is);
//		transformer.addCssText("Font: FreesiaUPC,Points: 12");
		transformer.transform(workbook, templateSheetNames, sheetNames, beansList);
		workbook.setActiveSheet(0);

		workbook.write(os);
		b = os.toByteArray();
		os.close();
		workbook.close();

		return b;

	}
	public byte[] generateExcelReportPrintCancelPaymentReportTemplate(ServletContext context ,DailyPaymentTO dailyPaymentTO, List<DailyPaymentTO> list, String serviceType ) throws  IOException, InvalidFormatException  {
		byte[] b = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		Workbook workbook;
		InputStream is = null;
			String file;
		 if(AppConstants.PAYMENT_TYPE_AGENT.equalsIgnoreCase(serviceType)){
				file = context.getRealPath(JETT_EXCEL_PATH) + File.separatorChar + "printAgentCancelPaymentReportTemplate.xls";
		 }else if(AppConstants.PAYMENT_TYPE_MNP.equalsIgnoreCase(serviceType)){
				 file = context.getRealPath(JETT_EXCEL_PATH) + File.separatorChar + "printMNPCancelPaymentReportTemplate.xls";
		 }else{
				file = context.getRealPath(JETT_EXCEL_PATH) + File.separatorChar + "printCancelPaymentReportTemplate.xls";
		 	}
		System.out.println(file);

		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ExcelTransformer transformer = new ExcelTransformer();

		List<String> templateSheetNames = new ArrayList<String>();
		List<String> sheetNames = new ArrayList<String>();
		List<Map<String, Object>> beansList = new ArrayList<Map<String, Object>>();
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("period", dailyPaymentTO.getPaymentDate());
		beans.put("documentDate", dailyPaymentTO.getDocumentDate());
		beans.put("branchName", dailyPaymentTO.getBranchName());
		beans.put("branchNamePos", dailyPaymentTO.getBranchName() + "/" + dailyPaymentTO.getPos());
		beans.put("userName", dailyPaymentTO.getUserName());
		beans.put("report", list);
		beansList.add(beans);
		beansList.add(beans);
		sheetNames.add("printCanclePaymentReport");

		workbook = WorkbookFactory.create(is);
		transformer.transform(workbook, templateSheetNames, sheetNames, beansList);
		workbook.setActiveSheet(0);

		workbook.write(os);
		b = os.toByteArray();
		os.close();
		workbook.close();

		return b;

	}
	public byte[] generateExcelReportPrintSummaryPaymentReportTemplate(ServletContext context ,DailyPaymentTO dailyPaymentTO, List<DailyPaymentTO> list, String serviceType ) throws  IOException, InvalidFormatException  {
		byte[] b = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		Workbook workbook;
		InputStream is = null;
			String file;
			file = context.getRealPath(JETT_EXCEL_PATH) + File.separatorChar + "printSummaryDailyPaymentReportTemplate.xls";

		/* if(AppConstants.PAYMENT_TYPE_AGENT.equalsIgnoreCase(serviceType)){
				file = context.getRealPath(JETT_EXCEL_PATH) + File.separatorChar + "printSummaryDailyPaymentReportTemplate.xls";
		 }else if(AppConstants.PAYMENT_TYPE_MNP.equalsIgnoreCase(serviceType)){
				 file = context.getRealPath(JETT_EXCEL_PATH) + File.separatorChar + "printSummaryDailyPaymentReportTemplate.xls";
		 }else{
				file = context.getRealPath(JETT_EXCEL_PATH) + File.separatorChar + "printSummaryDailyPaymentReportTemplate.xls";
		 	}*/
		System.out.println(file);

		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ExcelTransformer transformer = new ExcelTransformer();

		List<String> templateSheetNames = new ArrayList<String>();
		List<String> sheetNames = new ArrayList<String>();
		List<Map<String, Object>> beansList = new ArrayList<Map<String, Object>>();
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("period", dailyPaymentTO.getPaymentDate());
		beans.put("documentDate", dailyPaymentTO.getDocumentDate());
		beans.put("branchName", dailyPaymentTO.getBranchName());
		beans.put("branchNamePos", dailyPaymentTO.getBranchName() + "/" + dailyPaymentTO.getPos());
		beans.put("userName", dailyPaymentTO.getUserName());
		beans.put("report", list);
		beansList.add(beans);
		beansList.add(beans);
		sheetNames.add("printSummaryDailyPayment");

		workbook = WorkbookFactory.create(is);
		transformer.transform(workbook, templateSheetNames, sheetNames, beansList);
		workbook.setActiveSheet(0);

		workbook.write(os);
		b = os.toByteArray();
		os.close();
		workbook.close();

		return b;

	}
	
	//picht 10/10/2017
	public byte[] generateExcelReportEgpProjectReport(ServletContext context ,DailyPaymentTO dailyPaymentTO, List<DailyPaymentTO> list) throws  IOException, InvalidFormatException  {
		byte[] b = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		Workbook workbook;
		InputStream is = null;
		String file = context.getRealPath(JETT_EXCEL_PATH) + File.separatorChar + "printEgpProjectReportTemplate.xls";
		System.out.println(file);

		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ExcelTransformer transformer = new ExcelTransformer();

		System.out.println(list.size());
		List<String> templateSheetNames = new ArrayList<String>();
		List<String> sheetNames = new ArrayList<String>();
		List<Map<String, Object>> beansList = new ArrayList<Map<String, Object>>();
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("dateTimeFromTo", dailyPaymentTO.getPaymentDate());
		beans.put("documentDate", dailyPaymentTO.getDocumentDate());
		beans.put("branchName", dailyPaymentTO.getBranchName());
		beans.put("branchNamePos", dailyPaymentTO.getBranchName() + "/" + dailyPaymentTO.getPos());
		beans.put("userName", dailyPaymentTO.getUserName());
		beans.put("report", list);
		beansList.add(beans);
		sheetNames.add("printEgpProjectReportTemplate");

		workbook = WorkbookFactory.create(is);
		transformer.transform(workbook, templateSheetNames, sheetNames, beansList);
		workbook.setActiveSheet(0);

		workbook.write(os);
		b = os.toByteArray();
		os.close();
		workbook.close();

		return b;

	}
}
