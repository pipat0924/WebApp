
package th.net.cat.epis.ws.f19_retrieveaccount.f19_retrieveaccountsi;

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
 * 2017-03-24T17:19:49.027+07:00
 * Generated source version: 3.1.6
 * 
 */
public final class F19RetrieveAccountSI_F19RetrieveAccountSIExport1F19RetrieveAccountSIHttpPort_Client {

    private static final QName SERVICE_NAME = new QName("http://F19_RetrieveAccount/F19_RetrieveAccountSI", "F19_RetrieveAccountSIExport1_F19_RetrieveAccountSIHttpService");

    private F19RetrieveAccountSI_F19RetrieveAccountSIExport1F19RetrieveAccountSIHttpPort_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = F19RetrieveAccountSIExport1F19RetrieveAccountSIHttpService.WSDL_LOCATION;
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
      
        F19RetrieveAccountSIExport1F19RetrieveAccountSIHttpService ss = new F19RetrieveAccountSIExport1F19RetrieveAccountSIHttpService(wsdlURL, SERVICE_NAME);
        F19RetrieveAccountSI port = ss.getF19RetrieveAccountSIExport1F19RetrieveAccountSIHttpPort();  
        
        {
        System.out.println("Invoking retrieveAccountInfo...");
            th.net.cat.epis.ws.f19_retrieveaccount.RetrieveAccountRequest _retrieveAccountInfo_retrieveAccountRequest = null;
            th.net.cat.epis.ws.f19_retrieveaccount.RetrieveAccountResponse _retrieveAccountInfo__return = port.retrieveAccountInfo(_retrieveAccountInfo_retrieveAccountRequest);
        System.out.println("retrieveAccountInfo.result=" + _retrieveAccountInfo__return);


        }

        System.exit(0);
    }

}