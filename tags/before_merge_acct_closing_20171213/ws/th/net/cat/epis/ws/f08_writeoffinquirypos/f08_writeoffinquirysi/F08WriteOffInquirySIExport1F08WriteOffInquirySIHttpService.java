package th.net.cat.epis.ws.f08_writeoffinquirypos.f08_writeoffinquirysi;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2017-03-24T17:18:11.005+07:00
 * Generated source version: 3.1.6
 * 
 */
@WebServiceClient(name = "F08_WriteOffInquirySIExport1_F08_WriteOffInquirySIHttpService", 
                  wsdlLocation = "http://10.32.23.134:7800/F08_WriteOffInquirySIExport1_F08_WriteOffInquirySIHttpService?wsdl",
                  targetNamespace = "http://F08_WriteOffInquiryPOS/F08_WriteOffInquirySI") 
public class F08WriteOffInquirySIExport1F08WriteOffInquirySIHttpService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://F08_WriteOffInquiryPOS/F08_WriteOffInquirySI", "F08_WriteOffInquirySIExport1_F08_WriteOffInquirySIHttpService");
    public final static QName F08WriteOffInquirySIExport1F08WriteOffInquirySIHttpPort = new QName("http://F08_WriteOffInquiryPOS/F08_WriteOffInquirySI", "F08_WriteOffInquirySIExport1_F08_WriteOffInquirySIHttpPort");
    static {
        URL url = null;
        try {
            url = new URL("http://10.32.23.134:7800/F08_WriteOffInquirySIExport1_F08_WriteOffInquirySIHttpService?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(F08WriteOffInquirySIExport1F08WriteOffInquirySIHttpService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://10.32.23.134:7800/F08_WriteOffInquirySIExport1_F08_WriteOffInquirySIHttpService?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public F08WriteOffInquirySIExport1F08WriteOffInquirySIHttpService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public F08WriteOffInquirySIExport1F08WriteOffInquirySIHttpService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public F08WriteOffInquirySIExport1F08WriteOffInquirySIHttpService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public F08WriteOffInquirySIExport1F08WriteOffInquirySIHttpService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public F08WriteOffInquirySIExport1F08WriteOffInquirySIHttpService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public F08WriteOffInquirySIExport1F08WriteOffInquirySIHttpService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns F08WriteOffInquirySI
     */
    @WebEndpoint(name = "F08_WriteOffInquirySIExport1_F08_WriteOffInquirySIHttpPort")
    public F08WriteOffInquirySI getF08WriteOffInquirySIExport1F08WriteOffInquirySIHttpPort() {
        return super.getPort(F08WriteOffInquirySIExport1F08WriteOffInquirySIHttpPort, F08WriteOffInquirySI.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns F08WriteOffInquirySI
     */
    @WebEndpoint(name = "F08_WriteOffInquirySIExport1_F08_WriteOffInquirySIHttpPort")
    public F08WriteOffInquirySI getF08WriteOffInquirySIExport1F08WriteOffInquirySIHttpPort(WebServiceFeature... features) {
        return super.getPort(F08WriteOffInquirySIExport1F08WriteOffInquirySIHttpPort, F08WriteOffInquirySI.class, features);
    }

}
