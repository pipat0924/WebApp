
package th.net.cat.epis.ws.f13_retrievepaymenthistory.f13_retrievepaymenthistorysi;

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
 * 2017-03-31T11:15:16.963+07:00
 * Generated source version: 3.1.6
 * 
 */
public final class F13RetrievePaymentHistorySI_F13RetrievePaymentHistorySIExport1F13RetrievePaymentHistorySIHttpPort_Client {

    private static final QName SERVICE_NAME = new QName("http://F13_RetrievePaymentHistory/F13_RetrievePaymentHistorySI", "F13_RetrievePaymentHistorySIExport1_F13_RetrievePaymentHistorySIHttpService");

    private F13RetrievePaymentHistorySI_F13RetrievePaymentHistorySIExport1F13RetrievePaymentHistorySIHttpPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = F13RetrievePaymentHistorySIExport1F13RetrievePaymentHistorySIHttpService.WSDL_LOCATION;
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
      
        F13RetrievePaymentHistorySIExport1F13RetrievePaymentHistorySIHttpService ss = new F13RetrievePaymentHistorySIExport1F13RetrievePaymentHistorySIHttpService(wsdlURL, SERVICE_NAME);
        F13RetrievePaymentHistorySI port = ss.getF13RetrievePaymentHistorySIExport1F13RetrievePaymentHistorySIHttpPort();  
        
        {
        System.out.println("Invoking retrievePaymentHistory...");
            th.net.cat.epis.ws.f13_retrievepaymenthistory.RetrievePaymentHistoryRequest _retrievePaymentHistory_retrievePaymentHistoryRequest = null;
            th.net.cat.epis.ws.f13_retrievepaymenthistory.RetrievePaymentHistoryResponse _retrievePaymentHistory__return = port.retrievePaymentHistory(_retrievePaymentHistory_retrievePaymentHistoryRequest);
        System.out.println("retrievePaymentHistory.result=" + _retrievePaymentHistory__return);


        }

        System.exit(0);
    }

}
