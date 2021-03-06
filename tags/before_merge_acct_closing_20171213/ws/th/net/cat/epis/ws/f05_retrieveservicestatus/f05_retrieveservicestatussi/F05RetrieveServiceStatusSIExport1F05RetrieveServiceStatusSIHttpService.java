package th.net.cat.epis.ws.f05_retrieveservicestatus.f05_retrieveservicestatussi;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2017-03-24T17:17:45.206+07:00
 * Generated source version: 3.1.6
 * 
 */
@WebServiceClient(name = "F05_RetrieveServiceStatusSIExport1_F05_RetrieveServiceStatusSIHttpService", 
                  wsdlLocation = "http://10.32.23.134:7800/F05_RetrieveServiceStatusSIExport1_F05_RetrieveServiceStatusSIHttpService?wsdl",
                  targetNamespace = "http://F05_RetrieveServiceStatus/F05_RetrieveServiceStatusSI") 
public class F05RetrieveServiceStatusSIExport1F05RetrieveServiceStatusSIHttpService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://F05_RetrieveServiceStatus/F05_RetrieveServiceStatusSI", "F05_RetrieveServiceStatusSIExport1_F05_RetrieveServiceStatusSIHttpService");
    public final static QName F05RetrieveServiceStatusSIExport1F05RetrieveServiceStatusSIHttpPort = new QName("http://F05_RetrieveServiceStatus/F05_RetrieveServiceStatusSI", "F05_RetrieveServiceStatusSIExport1_F05_RetrieveServiceStatusSIHttpPort");
    static {
        URL url = null;
        try {
            url = new URL("http://10.32.23.134:7800/F05_RetrieveServiceStatusSIExport1_F05_RetrieveServiceStatusSIHttpService?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(F05RetrieveServiceStatusSIExport1F05RetrieveServiceStatusSIHttpService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://10.32.23.134:7800/F05_RetrieveServiceStatusSIExport1_F05_RetrieveServiceStatusSIHttpService?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public F05RetrieveServiceStatusSIExport1F05RetrieveServiceStatusSIHttpService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public F05RetrieveServiceStatusSIExport1F05RetrieveServiceStatusSIHttpService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public F05RetrieveServiceStatusSIExport1F05RetrieveServiceStatusSIHttpService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public F05RetrieveServiceStatusSIExport1F05RetrieveServiceStatusSIHttpService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public F05RetrieveServiceStatusSIExport1F05RetrieveServiceStatusSIHttpService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public F05RetrieveServiceStatusSIExport1F05RetrieveServiceStatusSIHttpService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns F05RetrieveServiceStatusSI
     */
    @WebEndpoint(name = "F05_RetrieveServiceStatusSIExport1_F05_RetrieveServiceStatusSIHttpPort")
    public F05RetrieveServiceStatusSI getF05RetrieveServiceStatusSIExport1F05RetrieveServiceStatusSIHttpPort() {
        return super.getPort(F05RetrieveServiceStatusSIExport1F05RetrieveServiceStatusSIHttpPort, F05RetrieveServiceStatusSI.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns F05RetrieveServiceStatusSI
     */
    @WebEndpoint(name = "F05_RetrieveServiceStatusSIExport1_F05_RetrieveServiceStatusSIHttpPort")
    public F05RetrieveServiceStatusSI getF05RetrieveServiceStatusSIExport1F05RetrieveServiceStatusSIHttpPort(WebServiceFeature... features) {
        return super.getPort(F05RetrieveServiceStatusSIExport1F05RetrieveServiceStatusSIHttpPort, F05RetrieveServiceStatusSI.class, features);
    }

}
