
package th.net.cat.epis.ws.m04_reversereceipt.m04_reversereceiptsi;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2017-02-25T23:29:28.673+07:00
 * Generated source version: 3.1.6
 * 
 */
 
public class M04ReverseReceiptSI_M04ReverseReceiptSIExport1M04ReverseReceiptSIHttpPort_Server{

    protected M04ReverseReceiptSI_M04ReverseReceiptSIExport1M04ReverseReceiptSIHttpPort_Server() throws Exception {
        System.out.println("Starting Server");
        Object implementor = new M04_ReverseReceiptSIExport1_M04_ReverseReceiptSIHttpPortImpl();
        String address = "http://10.32.23.134:7800/M04_ReverseReceiptSIExport1_M04_ReverseReceiptSIHttpService";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws Exception {
        new M04ReverseReceiptSI_M04ReverseReceiptSIExport1M04ReverseReceiptSIHttpPort_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
