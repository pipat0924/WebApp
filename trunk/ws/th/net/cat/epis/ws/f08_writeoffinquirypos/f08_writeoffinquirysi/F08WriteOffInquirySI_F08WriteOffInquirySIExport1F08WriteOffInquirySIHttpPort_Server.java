
package th.net.cat.epis.ws.f08_writeoffinquirypos.f08_writeoffinquirysi;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2017-01-13T16:56:32.968+07:00
 * Generated source version: 3.1.6
 * 
 */
 
public class F08WriteOffInquirySI_F08WriteOffInquirySIExport1F08WriteOffInquirySIHttpPort_Server{

    protected F08WriteOffInquirySI_F08WriteOffInquirySIExport1F08WriteOffInquirySIHttpPort_Server() throws Exception {
        System.out.println("Starting Server");
        Object implementor = new F08_WriteOffInquirySIExport1_F08_WriteOffInquirySIHttpPortImpl();
        String address = "http://10.32.23.134:7800/F08_WriteOffInquirySIExport1_F08_WriteOffInquirySIHttpService";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws Exception {
        new F08WriteOffInquirySI_F08WriteOffInquirySIExport1F08WriteOffInquirySIHttpPort_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
