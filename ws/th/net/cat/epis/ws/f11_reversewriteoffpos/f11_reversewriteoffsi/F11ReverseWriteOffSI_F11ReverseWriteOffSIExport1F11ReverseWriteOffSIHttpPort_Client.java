
package th.net.cat.epis.ws.f11_reversewriteoffpos.f11_reversewriteoffsi;

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
 * 2017-03-24T17:18:48.154+07:00
 * Generated source version: 3.1.6
 * 
 */
public final class F11ReverseWriteOffSI_F11ReverseWriteOffSIExport1F11ReverseWriteOffSIHttpPort_Client {

    private static final QName SERVICE_NAME = new QName("http://F11_ReverseWriteOffPOS/F11_ReverseWriteOffSI", "F11_ReverseWriteOffSIExport1_F11_ReverseWriteOffSIHttpService");

    private F11ReverseWriteOffSI_F11ReverseWriteOffSIExport1F11ReverseWriteOffSIHttpPort_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = F11ReverseWriteOffSIExport1F11ReverseWriteOffSIHttpService.WSDL_LOCATION;
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
      
        F11ReverseWriteOffSIExport1F11ReverseWriteOffSIHttpService ss = new F11ReverseWriteOffSIExport1F11ReverseWriteOffSIHttpService(wsdlURL, SERVICE_NAME);
        F11ReverseWriteOffSI port = ss.getF11ReverseWriteOffSIExport1F11ReverseWriteOffSIHttpPort();  
        
        {
        System.out.println("Invoking reverseWriteOff...");
            th.net.cat.epis.ws.f11_reversewriteoffpos.ReverseWriteOffRequest _reverseWriteOff_reverseWriteOffRequest = null;
            th.net.cat.epis.ws.f11_reversewriteoffpos.ReverseWriteOffResponse _reverseWriteOff__return = port.reverseWriteOff(_reverseWriteOff_reverseWriteOffRequest);
        System.out.println("reverseWriteOff.result=" + _reverseWriteOff__return);


        }

        System.exit(0);
    }

}