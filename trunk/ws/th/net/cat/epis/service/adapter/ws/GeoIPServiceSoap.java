package th.net.cat.epis.service.adapter.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2559-04-20T22:43:36.321+07:00
 * Generated source version: 3.1.6
 * 
 */
@WebService(targetNamespace = "http://www.webservicex.net/", name = "GeoIPServiceSoap")
@XmlSeeAlso({ObjectFactory.class})
public interface GeoIPServiceSoap {

    /**
     * GeoIPService - GetGeoIP enables you to easily look up countries by IP addresses
     */
    @WebMethod(operationName = "GetGeoIP", action = "http://www.webservicex.net/GetGeoIP")
    @RequestWrapper(localName = "GetGeoIP", targetNamespace = "http://www.webservicex.net/", className = "GetGeoIP")
    @ResponseWrapper(localName = "GetGeoIPResponse", targetNamespace = "http://www.webservicex.net/", className = "GetGeoIPResponse")
    @WebResult(name = "GetGeoIPResult", targetNamespace = "http://www.webservicex.net/")
    public GeoIP getGeoIP(
        @WebParam(name = "IPAddress", targetNamespace = "http://www.webservicex.net/")
        java.lang.String ipAddress
    );

    /**
     * GeoIPService - GetGeoIPContext enables you to easily look up countries by Context
     */
    @WebMethod(operationName = "GetGeoIPContext", action = "http://www.webservicex.net/GetGeoIPContext")
    @RequestWrapper(localName = "GetGeoIPContext", targetNamespace = "http://www.webservicex.net/", className = "GetGeoIPContext")
    @ResponseWrapper(localName = "GetGeoIPContextResponse", targetNamespace = "http://www.webservicex.net/", className = "GetGeoIPContextResponse")
    @WebResult(name = "GetGeoIPContextResult", targetNamespace = "http://www.webservicex.net/")
    public GeoIP getGeoIPContext();
}
