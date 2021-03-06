package th.net.cat.epis.ws.f18_createrefund.f18_createrefundsi;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2017-03-24T17:19:39.544+07:00
 * Generated source version: 3.1.6
 * 
 */
@WebServiceClient(name = "F18_CreateRefundSIExport1_F18_CreateRefundSIHttpService", 
                  wsdlLocation = "http://10.32.23.134:7800/F18_CreateRefundSIExport1_F18_CreateRefundSIHttpService?wsdl",
                  targetNamespace = "http://F18_CreateRefund/F18_CreateRefundSI") 
public class F18CreateRefundSIExport1F18CreateRefundSIHttpService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://F18_CreateRefund/F18_CreateRefundSI", "F18_CreateRefundSIExport1_F18_CreateRefundSIHttpService");
    public final static QName F18CreateRefundSIExport1F18CreateRefundSIHttpPort = new QName("http://F18_CreateRefund/F18_CreateRefundSI", "F18_CreateRefundSIExport1_F18_CreateRefundSIHttpPort");
    static {
        URL url = null;
        try {
            url = new URL("http://10.32.23.134:7800/F18_CreateRefundSIExport1_F18_CreateRefundSIHttpService?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(F18CreateRefundSIExport1F18CreateRefundSIHttpService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://10.32.23.134:7800/F18_CreateRefundSIExport1_F18_CreateRefundSIHttpService?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public F18CreateRefundSIExport1F18CreateRefundSIHttpService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public F18CreateRefundSIExport1F18CreateRefundSIHttpService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public F18CreateRefundSIExport1F18CreateRefundSIHttpService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public F18CreateRefundSIExport1F18CreateRefundSIHttpService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public F18CreateRefundSIExport1F18CreateRefundSIHttpService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public F18CreateRefundSIExport1F18CreateRefundSIHttpService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns F18CreateRefundSI
     */
    @WebEndpoint(name = "F18_CreateRefundSIExport1_F18_CreateRefundSIHttpPort")
    public F18CreateRefundSI getF18CreateRefundSIExport1F18CreateRefundSIHttpPort() {
        return super.getPort(F18CreateRefundSIExport1F18CreateRefundSIHttpPort, F18CreateRefundSI.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns F18CreateRefundSI
     */
    @WebEndpoint(name = "F18_CreateRefundSIExport1_F18_CreateRefundSIHttpPort")
    public F18CreateRefundSI getF18CreateRefundSIExport1F18CreateRefundSIHttpPort(WebServiceFeature... features) {
        return super.getPort(F18CreateRefundSIExport1F18CreateRefundSIHttpPort, F18CreateRefundSI.class, features);
    }

}
