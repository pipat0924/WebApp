
package th.net.cat.epis.ws.f05_retrieveservicestatus.f05_retrieveservicestatussi;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2017-02-21T17:09:13.716+07:00
 * Generated source version: 3.1.6
 * 
 */
 
public class F05RetrieveServiceStatusSI_F05RetrieveServiceStatusSIExport1F05RetrieveServiceStatusSIHttpPort_Server{

    protected F05RetrieveServiceStatusSI_F05RetrieveServiceStatusSIExport1F05RetrieveServiceStatusSIHttpPort_Server() throws Exception {
        System.out.println("Starting Server");
        Object implementor = new F05_RetrieveServiceStatusSIExport1_F05_RetrieveServiceStatusSIHttpPortImpl();
        String address = "http://10.32.23.134:7800/F05_RetrieveServiceStatusSIExport1_F05_RetrieveServiceStatusSIHttpService";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws Exception {
        new F05RetrieveServiceStatusSI_F05RetrieveServiceStatusSIExport1F05RetrieveServiceStatusSIHttpPort_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}