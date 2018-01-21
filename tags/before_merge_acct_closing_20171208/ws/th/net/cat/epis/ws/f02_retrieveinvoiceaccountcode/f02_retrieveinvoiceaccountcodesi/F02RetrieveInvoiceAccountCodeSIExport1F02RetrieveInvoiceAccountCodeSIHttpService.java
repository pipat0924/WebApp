package th.net.cat.epis.ws.f02_retrieveinvoiceaccountcode.f02_retrieveinvoiceaccountcodesi;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2017-03-24T17:17:12.470+07:00
 * Generated source version: 3.1.6
 * 
 */
@WebServiceClient(name = "F02_RetrieveInvoiceAccountCodeSIExport1_F02_RetrieveInvoiceAccountCodeSIHttpService", 
                  wsdlLocation = "http://10.32.23.134:7800/F02_RetrieveInvoiceAccountCodeSIExport1_F02_RetrieveInvoiceAccountCodeSIHttpService?wsdl",
                  targetNamespace = "http://F02_RetrieveInvoiceAccountCode/F02_RetrieveInvoiceAccountCodeSI") 
public class F02RetrieveInvoiceAccountCodeSIExport1F02RetrieveInvoiceAccountCodeSIHttpService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://F02_RetrieveInvoiceAccountCode/F02_RetrieveInvoiceAccountCodeSI", "F02_RetrieveInvoiceAccountCodeSIExport1_F02_RetrieveInvoiceAccountCodeSIHttpService");
    public final static QName F02RetrieveInvoiceAccountCodeSIExport1F02RetrieveInvoiceAccountCodeSIHttpPort = new QName("http://F02_RetrieveInvoiceAccountCode/F02_RetrieveInvoiceAccountCodeSI", "F02_RetrieveInvoiceAccountCodeSIExport1_F02_RetrieveInvoiceAccountCodeSIHttpPort");
    static {
        URL url = null;
        try {
            url = new URL("http://10.32.23.134:7800/F02_RetrieveInvoiceAccountCodeSIExport1_F02_RetrieveInvoiceAccountCodeSIHttpService?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(F02RetrieveInvoiceAccountCodeSIExport1F02RetrieveInvoiceAccountCodeSIHttpService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://10.32.23.134:7800/F02_RetrieveInvoiceAccountCodeSIExport1_F02_RetrieveInvoiceAccountCodeSIHttpService?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public F02RetrieveInvoiceAccountCodeSIExport1F02RetrieveInvoiceAccountCodeSIHttpService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public F02RetrieveInvoiceAccountCodeSIExport1F02RetrieveInvoiceAccountCodeSIHttpService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public F02RetrieveInvoiceAccountCodeSIExport1F02RetrieveInvoiceAccountCodeSIHttpService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public F02RetrieveInvoiceAccountCodeSIExport1F02RetrieveInvoiceAccountCodeSIHttpService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public F02RetrieveInvoiceAccountCodeSIExport1F02RetrieveInvoiceAccountCodeSIHttpService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public F02RetrieveInvoiceAccountCodeSIExport1F02RetrieveInvoiceAccountCodeSIHttpService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns F02RetrieveInvoiceAccountCodeSI
     */
    @WebEndpoint(name = "F02_RetrieveInvoiceAccountCodeSIExport1_F02_RetrieveInvoiceAccountCodeSIHttpPort")
    public F02RetrieveInvoiceAccountCodeSI getF02RetrieveInvoiceAccountCodeSIExport1F02RetrieveInvoiceAccountCodeSIHttpPort() {
        return super.getPort(F02RetrieveInvoiceAccountCodeSIExport1F02RetrieveInvoiceAccountCodeSIHttpPort, F02RetrieveInvoiceAccountCodeSI.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns F02RetrieveInvoiceAccountCodeSI
     */
    @WebEndpoint(name = "F02_RetrieveInvoiceAccountCodeSIExport1_F02_RetrieveInvoiceAccountCodeSIHttpPort")
    public F02RetrieveInvoiceAccountCodeSI getF02RetrieveInvoiceAccountCodeSIExport1F02RetrieveInvoiceAccountCodeSIHttpPort(WebServiceFeature... features) {
        return super.getPort(F02RetrieveInvoiceAccountCodeSIExport1F02RetrieveInvoiceAccountCodeSIHttpPort, F02RetrieveInvoiceAccountCodeSI.class, features);
    }

}
