<%-- 
    Document   : Convert_json
    Created on : Feb 12, 2016, 2:11:44 PM
    Author     : softpos2013
--%>

<%@page import="th.co.softpos.cat.epis.bean.ESB_RetrieveInvoiceChargeInfo_Bean"%>
<%@page import="th.co.softpos.cat.epis.service.ESB_RetrieveInvoiceChargeInfo_Service"%>
<%@page import="th.co.softpos.cat.epis.bean.ESB_RetrieveInvoiceAccountCodeInfo_Bean"%>
<%@page import="th.co.softpos.cat.epis.service.ESB_RetrieveInvoiceAccountCodeInfo_Service"%>
<%@page import="th.co.softpos.cat.epis.bean.CRM_Customer_Billing_Bean"%>
<%@page import="th.co.softpos.cat.epis.control.CRM_Customer_Billing_Control"%>
<%@page import="th.co.softpos.cat.epis.bean.ESB_RetrieveInvoiceHeader_Bean"%>
<%@page import="th.co.softpos.cat.epis.service.ESB_RetrieveInvoiceHeader_Service"%>
<%@page import="th.co.softpos.cat.epis.service.ESB_RetrieveInvoiceHeader_Service"%>
<%@page import="sun.natee.project.util.ToJSON"%>
<%@page import="th.co.softpos.cat.epis.control.customer_epis_crm_control"%>
<%@page import="th.co.softpos.cat.epis.bean.customer_epis_crm_bean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String master = (String) request.getParameter("master");
    String get = (String) request.getParameter("get");
    String get2 = (String) request.getParameter("get2");
    String get3 = (String) request.getParameter("get3");
    String get4 = (String) request.getParameter("get4");
    String get5 = (String) request.getParameter("get5");
    String get6 = (String) request.getParameter("get6");
    String get7 = (String) request.getParameter("get7");
    String get8 = (String) request.getParameter("get8");
     if (master != null) {
//        ----------------index1-------------------
        if (master.equals("pop_crm")) {
            customer_epis_crm_control t = new customer_epis_crm_control();
            ArrayList<customer_epis_crm_bean> listCust = t.getdata3(get,get2,get3,get4,get5);

            response.setContentType("application/json");
            response.getWriter().print(ToJSON.getJSON(listCust));
        } else if (master.equals("pop_crm2")) {
            customer_epis_crm_control t = new customer_epis_crm_control();
            ArrayList<customer_epis_crm_bean> listCust = t.getdata4(get);

            response.setContentType("application/json");
            response.getWriter().print(ToJSON.getJSON(listCust));
        } else if (master.equals("esb_RetrieveInvoiceHeader_Service")) {
            ESB_RetrieveInvoiceHeader_Service t = new ESB_RetrieveInvoiceHeader_Service();
            ArrayList<ESB_RetrieveInvoiceHeader_Bean> listCust = t.createServiceSOAPRequest_Response(get);

            response.setContentType("application/json");
            response.getWriter().print(ToJSON.getJSON(listCust));
        } else if (master.equals("esb_RetrieveInvoiceAccountCodeInfo_Service")) {
            ESB_RetrieveInvoiceAccountCodeInfo_Service t = new ESB_RetrieveInvoiceAccountCodeInfo_Service();
            ArrayList<ESB_RetrieveInvoiceAccountCodeInfo_Bean> listCust = t.createServiceSOAPRequest_Response(get);

            response.setContentType("application/json");
            response.getWriter().print(ToJSON.getJSON(listCust));
        } else if (master.equals("esb_RetrieveInvoiceChargeInfo_Service")) {
            ESB_RetrieveInvoiceChargeInfo_Service t = new ESB_RetrieveInvoiceChargeInfo_Service();
            ArrayList<ESB_RetrieveInvoiceChargeInfo_Bean> listCust = t.createServiceSOAPRequest_Response(get);

            response.setContentType("application/json");
            response.getWriter().print(ToJSON.getJSON(listCust));
        } else if (master.equals("CRM_CALL_DATA")) {
            CRM_Customer_Billing_Control t = new CRM_Customer_Billing_Control();
            ArrayList<CRM_Customer_Billing_Bean> listCust = t.getdata5(get,get2,get3,get4,get5);

            response.setContentType("application/json");
            response.getWriter().print(ToJSON.getJSON(listCust));
        } 
     }
    %>
