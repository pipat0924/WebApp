package th.co.softpos.ws.mg.s004;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2017-08-03T15:13:47.659+07:00
 * Generated source version: 3.1.6
 * 
 */
@WebService(targetNamespace = "http://s004.mg.ws.softpos.co.th/", name = "S004MGUpdFree")
@XmlSeeAlso({ObjectFactory.class})
public interface S004MGUpdFree {

    @WebResult(name = "rs", targetNamespace = "")
    @RequestWrapper(localName = "process", targetNamespace = "http://s004.mg.ws.softpos.co.th/", className = "th.co.softpos.ws.mg.s004.Process")
    @WebMethod
    @ResponseWrapper(localName = "processResponse", targetNamespace = "http://s004.mg.ws.softpos.co.th/", className = "th.co.softpos.ws.mg.s004.ProcessResponse")
    public th.co.softpos.ws.mg.s004.Response process(
        @WebParam(name = "rq", targetNamespace = "")
        th.co.softpos.ws.mg.s004.Request rq
    );
}
