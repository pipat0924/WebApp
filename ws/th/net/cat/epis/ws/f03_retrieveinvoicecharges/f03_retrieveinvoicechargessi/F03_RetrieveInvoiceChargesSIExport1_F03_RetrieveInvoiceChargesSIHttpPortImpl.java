
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package th.net.cat.epis.ws.f03_retrieveinvoicecharges.f03_retrieveinvoicechargessi;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2017-03-24T17:17:23.339+07:00
 * Generated source version: 3.1.6
 * 
 */

@WebService(
                      serviceName = "F03_RetrieveInvoiceChargesSIExport1_F03_RetrieveInvoiceChargesSIHttpService",
                      portName = "F03_RetrieveInvoiceChargesSIExport1_F03_RetrieveInvoiceChargesSIHttpPort",
                      targetNamespace = "http://F03_RetrieveInvoiceCharges/F03_RetrieveInvoiceChargesSI",
                      wsdlLocation = "http://10.32.23.134:7800/F03_RetrieveInvoiceChargesSIExport1_F03_RetrieveInvoiceChargesSIHttpService?wsdl",
                      endpointInterface = "th.net.cat.epis.ws.f03_retrieveinvoicecharges.f03_retrieveinvoicechargessi.F03RetrieveInvoiceChargesSI")
                      
public class F03_RetrieveInvoiceChargesSIExport1_F03_RetrieveInvoiceChargesSIHttpPortImpl implements F03RetrieveInvoiceChargesSI {

    private static final Logger LOG = Logger.getLogger(F03_RetrieveInvoiceChargesSIExport1_F03_RetrieveInvoiceChargesSIHttpPortImpl.class.getName());

    /* (non-Javadoc)
     * @see f03_retrieveinvoicecharges.f03_retrieveinvoicechargessi.F03RetrieveInvoiceChargesSI#retrieveInvoiceChargeInfo(f03_retrieveinvoicecharges.RetrieveInvoiceChargeInfoRequest  retrieveInvoiceChargeInfoRequest )*
     */
    public th.net.cat.epis.ws.f03_retrieveinvoicecharges.RetrieveInvoiceChargeInfoResponse retrieveInvoiceChargeInfo(th.net.cat.epis.ws.f03_retrieveinvoicecharges.RetrieveInvoiceChargeInfoRequest retrieveInvoiceChargeInfoRequest) {
        LOG.info("Executing operation retrieveInvoiceChargeInfo");
        System.out.println(retrieveInvoiceChargeInfoRequest);
        try {
            th.net.cat.epis.ws.f03_retrieveinvoicecharges.RetrieveInvoiceChargeInfoResponse _return = null;
            return _return;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}