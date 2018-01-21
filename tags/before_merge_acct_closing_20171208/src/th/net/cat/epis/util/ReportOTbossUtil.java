package th.net.cat.epis.util;

import java.util.ArrayList;
import java.util.List;

public class ReportOTbossUtil {
	
	private String  customer_type_name  ;
	private String  customer_name;
	
	
	public String getCustomer_type_name() {
		return customer_type_name;
	}
	public void setCustomer_type_name(String customer_type_name) {
		this.customer_type_name = customer_type_name;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	
	public static List<ReportOTbossUtil> dataReportOTbossUtil(){
		List<ReportOTbossUtil> list = new ArrayList<ReportOTbossUtil>();
		ReportOTbossUtil data = new ReportOTbossUtil();
		
		
		
		data.setCustomer_name("INDIVIDUAL");
		data.setCustomer_type_name("บุคคลทั่วไป");
		list.add(data);
		
		data.setCustomer_name("ORGANIZATION");
		data.setCustomer_type_name("ธรกิจทั่วไป");
		list.add(data);
		
		data.setCustomer_name("STATEAGENCY");
		data.setCustomer_type_name("หน่วยงานรัฐ");
		list.add(data);
		
		System.out.println(list.size());
		return list;
	}
}
