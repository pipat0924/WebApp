package th.net.cat.epis.ws.f06_reversepayment.f06_reversepaymentsi;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2017-03-24T17:17:54.337+07:00
 * Generated source version: 3.1.6
 * 
 */
@WebService(targetNamespace = "http://F06_ReversePayment/F06_ReversePaymentSI", name = "F06_ReversePaymentSI")
@XmlSeeAlso({th.net.cat.epis.ws.f06_reversepayment.ObjectFactory.class, th.net.cat.epis.ws.esblibs.cbos.ObjectFactory.class, ObjectFactory.class})
public interface F06ReversePaymentSI {

    @WebResult(name = "reversePaymentResponse", targetNamespace = "")
    @RequestWrapper(localName = "reversePayment", targetNamespace = "http://F06_ReversePayment/F06_ReversePaymentSI", className = "th.net.cat.epis.ws.f06_reversepayment.f06_reversepaymentsi.ReversePayment")
    @WebMethod
    @ResponseWrapper(localName = "reversePaymentResponse", targetNamespace = "http://F06_ReversePayment/F06_ReversePaymentSI", className = "th.net.cat.epis.ws.f06_reversepayment.f06_reversepaymentsi.ReversePaymentResponse")
    public th.net.cat.epis.ws.f06_reversepayment.ReversePaymentResponse reversePayment(
            @WebParam(name = "reversePaymentRequest", targetNamespace = "")
                    th.net.cat.epis.ws.f06_reversepayment.ReversePaymentRequest reversePaymentRequest
    );
}