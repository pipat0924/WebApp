
package th.net.cat.epis.ws.f09_writeoffpaymenthistoryinquirypos.f09_retrievewriteoffpaymenthistorysi;

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
 * 2559-04-21T09:42:31.150+07:00
 * Generated source version: 3.1.6
 * 
 */
public final class F09RWriteOffPayHistSI_F09RWriteOffPayHistSIExport1F09RWriteOffPayHistSIHttpPort_Client {

    private static final QName SERVICE_NAME = new QName("http://F09_WriteOffPaymentHistoryInquiryPOS/F09_RetrieveWriteOffPaymentHistorySI", "F09_RetrieveWriteOffPaymentHistorySIExport1_F09_RetrieveWriteOffPaymentHistorySIHttpService");

    private F09RWriteOffPayHistSI_F09RWriteOffPayHistSIExport1F09RWriteOffPayHistSIHttpPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = F09RetrieveWriteOffPaymentHistorySIExport1F09RetrieveWriteOffPaymentHistorySIHttpService.WSDL_LOCATION;
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
      
        F09RetrieveWriteOffPaymentHistorySIExport1F09RetrieveWriteOffPaymentHistorySIHttpService ss = new F09RetrieveWriteOffPaymentHistorySIExport1F09RetrieveWriteOffPaymentHistorySIHttpService(wsdlURL, SERVICE_NAME);
        F09RetrieveWriteOffPaymentHistorySI port = ss.getF09RetrieveWriteOffPaymentHistorySIExport1F09RetrieveWriteOffPaymentHistorySIHttpPort();  
        
        {
        System.out.println("Invoking retrieveWriteOffPaymentInfo...");
        th.net.cat.epis.ws.f09_writeoffpaymenthistoryinquirypos.RetrieveWriteOffPaymentRequest _retrieveWriteOffPaymentInfo_retrieveWriteOffPaymentRequest = null;
        th.net.cat.epis.ws.f09_writeoffpaymenthistoryinquirypos.RetrieveWriteOffPaymentResponse _retrieveWriteOffPaymentInfo__return = port.retrieveWriteOffPaymentInfo(_retrieveWriteOffPaymentInfo_retrieveWriteOffPaymentRequest);
        System.out.println("retrieveWriteOffPaymentInfo.result=" + _retrieveWriteOffPaymentInfo__return);


        }

        System.exit(0);
    }

}
