
package th.co.softpos.ws.mg.s002;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2017-08-02T16:54:49.325+07:00
 * Generated source version: 3.1.6
 * 
 */
 
public class S002MGUpdGiftvoucher_S002MGUpdGiftvoucherPort_Server{

    protected S002MGUpdGiftvoucher_S002MGUpdGiftvoucherPort_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new S002MGUpdGiftvoucherPortImpl();
        String address = "http://10.44.1.4/InsalePosWS/services/S002MGUpdGiftvoucher";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new S002MGUpdGiftvoucher_S002MGUpdGiftvoucherPort_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}