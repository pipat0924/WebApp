package th.net.cat.epis.ws.esblibs.inf.esb.f14.binding;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

import th.net.cat.epis.ws.esblibs.inf.esb.f14.F14RetrievePaymentSI;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2559-04-21T09:46:17.818+07:00
 * Generated source version: 3.1.6
 * 
 */
@WebServiceClient(name = "F14_RetrievePaymentSIExport1_F14_RetrievePaymentSIHttpService", 
                  wsdlLocation = "http://10.32.23.134:7800/F14_RetrievePaymentSIExport1_F14_RetrievePaymentSIHttpService?wsdl",
                  targetNamespace = "http://ESBLibs/INF/ESB/F14/Binding") 
public class F14RetrievePaymentSIExport1F14RetrievePaymentSIHttpService extends Service {

    public final static URL WSDL_LOCATION;
    public final static URL WSDL_LIST_LOCATION;

    public final static QName SERVICE = new QName("http://ESBLibs/INF/ESB/F14/Binding", "F14_RetrievePaymentSIExport1_F14_RetrievePaymentSIHttpService");
    public final static QName F14RetrievePaymentSIExport1F14RetrievePaymentSIHttpPort = new QName("http://ESBLibs/INF/ESB/F14/Binding", "F14_RetrievePaymentSIExport1_F14_RetrievePaymentSIHttpPort");
    static {
        URL url = null;
        try {
            url = new URL("http://10.32.23.134:7800/F14_RetrievePaymentSIExport1_F14_RetrievePaymentSIHttpService?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(F14RetrievePaymentSIExport1F14RetrievePaymentSIHttpService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://10.32.23.134:7800/F14_RetrievePaymentSIExport1_F14_RetrievePaymentSIHttpService?wsdl");
        }
        WSDL_LOCATION = url;
    }
    static {
        URL url = null;
        try {
            url = new URL("http://10.32.23.134:7800/F14_RetrievePaymentListSIExport1_F14_RetrievePaymentListSIHttpService?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(F14RetrievePaymentSIExport1F14RetrievePaymentSIHttpService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://10.32.23.134:7800/F14_RetrievePaymentListSIExport1_F14_RetrievePaymentListSIHttpService?wsdl");
        }
        WSDL_LIST_LOCATION = url;
    }

    public F14RetrievePaymentSIExport1F14RetrievePaymentSIHttpService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public F14RetrievePaymentSIExport1F14RetrievePaymentSIHttpService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public F14RetrievePaymentSIExport1F14RetrievePaymentSIHttpService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public F14RetrievePaymentSIExport1F14RetrievePaymentSIHttpService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public F14RetrievePaymentSIExport1F14RetrievePaymentSIHttpService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public F14RetrievePaymentSIExport1F14RetrievePaymentSIHttpService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns F14RetrievePaymentSI
     */
    @WebEndpoint(name = "F14_RetrievePaymentSIExport1_F14_RetrievePaymentSIHttpPort")
    public F14RetrievePaymentSI getF14RetrievePaymentSIExport1F14RetrievePaymentSIHttpPort() {
        return super.getPort(F14RetrievePaymentSIExport1F14RetrievePaymentSIHttpPort, F14RetrievePaymentSI.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns F14RetrievePaymentSI
     */
    @WebEndpoint(name = "F14_RetrievePaymentSIExport1_F14_RetrievePaymentSIHttpPort")
    public F14RetrievePaymentSI getF14RetrievePaymentSIExport1F14RetrievePaymentSIHttpPort(WebServiceFeature... features) {
        return super.getPort(F14RetrievePaymentSIExport1F14RetrievePaymentSIHttpPort, F14RetrievePaymentSI.class, features);
    }

}
