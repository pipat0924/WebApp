package th.net.cat.epis.ws.f05_retrieveservicestatus.f05_retrieveservicestatussi;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2017-03-24T17:17:45.197+07:00
 * Generated source version: 3.1.6
 * 
 */
@WebService(targetNamespace = "http://F05_RetrieveServiceStatus/F05_RetrieveServiceStatusSI", name = "F05_RetrieveServiceStatusSI")
@XmlSeeAlso({ObjectFactory.class, th.net.cat.epis.ws.f05_retrieveservicestatus.ObjectFactory.class, th.net.cat.epis.ws.esblibs.cbos.ObjectFactory.class})
public interface F05RetrieveServiceStatusSI {

    @WebResult(name = "retrieveServiceStatusResponse", targetNamespace = "")
    @RequestWrapper(localName = "checkServiceStatus", targetNamespace = "http://F05_RetrieveServiceStatus/F05_RetrieveServiceStatusSI", className = "th.net.cat.epis.ws.f05_retrieveservicestatus.f05_retrieveservicestatussi.CheckServiceStatus")
    @WebMethod
    @ResponseWrapper(localName = "retrieveServiceStatusResponse", targetNamespace = "http://F05_RetrieveServiceStatus/F05_RetrieveServiceStatusSI", className = "th.net.cat.epis.ws.f05_retrieveservicestatus.f05_retrieveservicestatussi.RetrieveServiceStatusResponse")
    public th.net.cat.epis.ws.f05_retrieveservicestatus.RetrieveServiceStatusResponse checkServiceStatus(
            @WebParam(name = "retrieveServiceStatusRequest", targetNamespace = "")
                    th.net.cat.epis.ws.f05_retrieveservicestatus.RetrieveServiceStatusRequest retrieveServiceStatusRequest
    );
}