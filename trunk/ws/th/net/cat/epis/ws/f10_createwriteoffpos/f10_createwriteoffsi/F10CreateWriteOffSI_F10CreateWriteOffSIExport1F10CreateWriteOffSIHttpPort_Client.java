
package th.net.cat.epis.ws.f10_createwriteoffpos.f10_createwriteoffsi;

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
 * 2017-03-24T17:18:36.842+07:00
 * Generated source version: 3.1.6
 * 
 */
public final class F10CreateWriteOffSI_F10CreateWriteOffSIExport1F10CreateWriteOffSIHttpPort_Client {

    private static final QName SERVICE_NAME = new QName("http://F10_CreateWriteOffPOS/F10_CreateWriteOffSI", "F10_CreateWriteOffSIExport1_F10_CreateWriteOffSIHttpService");

    private F10CreateWriteOffSI_F10CreateWriteOffSIExport1F10CreateWriteOffSIHttpPort_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = F10CreateWriteOffSIExport1F10CreateWriteOffSIHttpService.WSDL_LOCATION;
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
      
        F10CreateWriteOffSIExport1F10CreateWriteOffSIHttpService ss = new F10CreateWriteOffSIExport1F10CreateWriteOffSIHttpService(wsdlURL, SERVICE_NAME);
        F10CreateWriteOffSI port = ss.getF10CreateWriteOffSIExport1F10CreateWriteOffSIHttpPort();  
        
        {
        System.out.println("Invoking createWriteOff...");
            th.net.cat.epis.ws.f10_createwriteoffpos.CreateWriteOffRequest _createWriteOff_createWriteOffRequest = null;
            th.net.cat.epis.ws.f10_createwriteoffpos.CreateWriteOffResponse _createWriteOff__return = port.createWriteOff(_createWriteOff_createWriteOffRequest);
        System.out.println("createWriteOff.result=" + _createWriteOff__return);


        }

        System.exit(0);
    }

}
