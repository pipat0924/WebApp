
package th.co.softpos.ws.mg.s004;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2017-08-03T15:13:47.625+07:00
 * Generated source version: 3.1.6
 * 
 */
public final class S004MGUpdFree_S004MGUpdFreePort_Client {

    private static final QName SERVICE_NAME = new QName("http://s004.mg.ws.softpos.co.th/", "S004MGUpdFreeService");

    private S004MGUpdFree_S004MGUpdFreePort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = S004MGUpdFreeService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        S004MGUpdFreeService ss = new S004MGUpdFreeService(wsdlURL, SERVICE_NAME);
        S004MGUpdFree port = ss.getS004MGUpdFreePort();  
        
        {
        System.out.println("Invoking process...");
        th.co.softpos.ws.mg.s004.Request _process_rq = null;
        th.co.softpos.ws.mg.s004.Response _process__return = port.process(_process_rq);
        System.out.println("process.result=" + _process__return);


        }

        System.exit(0);
    }

}
