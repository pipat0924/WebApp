package th.net.cat.epis.ws.f10_createwriteoffpos.f10_createwriteoffsi;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2017-03-24T17:18:36.895+07:00
 * Generated source version: 3.1.6
 * 
 */
@WebServiceClient(name = "F10_CreateWriteOffSIExport1_F10_CreateWriteOffSIHttpService", 
                  wsdlLocation = "http://10.32.23.134:7800/F10_CreateWriteOffSIExport1_F10_CreateWriteOffSIHttpService?wsdl",
                  targetNamespace = "http://F10_CreateWriteOffPOS/F10_CreateWriteOffSI") 
public class F10CreateWriteOffSIExport1F10CreateWriteOffSIHttpService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://F10_CreateWriteOffPOS/F10_CreateWriteOffSI", "F10_CreateWriteOffSIExport1_F10_CreateWriteOffSIHttpService");
    public final static QName F10CreateWriteOffSIExport1F10CreateWriteOffSIHttpPort = new QName("http://F10_CreateWriteOffPOS/F10_CreateWriteOffSI", "F10_CreateWriteOffSIExport1_F10_CreateWriteOffSIHttpPort");
    static {
        URL url = null;
        try {
            url = new URL("http://10.32.23.134:7800/F10_CreateWriteOffSIExport1_F10_CreateWriteOffSIHttpService?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(F10CreateWriteOffSIExport1F10CreateWriteOffSIHttpService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://10.32.23.134:7800/F10_CreateWriteOffSIExport1_F10_CreateWriteOffSIHttpService?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public F10CreateWriteOffSIExport1F10CreateWriteOffSIHttpService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public F10CreateWriteOffSIExport1F10CreateWriteOffSIHttpService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public F10CreateWriteOffSIExport1F10CreateWriteOffSIHttpService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public F10CreateWriteOffSIExport1F10CreateWriteOffSIHttpService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public F10CreateWriteOffSIExport1F10CreateWriteOffSIHttpService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public F10CreateWriteOffSIExport1F10CreateWriteOffSIHttpService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns F10CreateWriteOffSI
     */
    @WebEndpoint(name = "F10_CreateWriteOffSIExport1_F10_CreateWriteOffSIHttpPort")
    public F10CreateWriteOffSI getF10CreateWriteOffSIExport1F10CreateWriteOffSIHttpPort() {
        return super.getPort(F10CreateWriteOffSIExport1F10CreateWriteOffSIHttpPort, F10CreateWriteOffSI.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns F10CreateWriteOffSI
     */
    @WebEndpoint(name = "F10_CreateWriteOffSIExport1_F10_CreateWriteOffSIHttpPort")
    public F10CreateWriteOffSI getF10CreateWriteOffSIExport1F10CreateWriteOffSIHttpPort(WebServiceFeature... features) {
        return super.getPort(F10CreateWriteOffSIExport1F10CreateWriteOffSIHttpPort, F10CreateWriteOffSI.class, features);
    }

}
