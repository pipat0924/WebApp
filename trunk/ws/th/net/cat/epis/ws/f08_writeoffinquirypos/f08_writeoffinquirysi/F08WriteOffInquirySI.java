package th.net.cat.epis.ws.f08_writeoffinquirypos.f08_writeoffinquirysi;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2017-03-24T17:18:10.997+07:00
 * Generated source version: 3.1.6
 * 
 */
@WebService(targetNamespace = "http://F08_WriteOffInquiryPOS/F08_WriteOffInquirySI", name = "F08_WriteOffInquirySI")
@XmlSeeAlso({th.net.cat.epis.ws.f08_writeoffinquirypos.ObjectFactory.class, ObjectFactory.class, th.net.cat.epis.ws.esblibs.cbos.ObjectFactory.class})
public interface F08WriteOffInquirySI {

    @WebResult(name = "retrieveWriteOffInvoiceResponse", targetNamespace = "")
    @RequestWrapper(localName = "retrieveWriteOffInvoice", targetNamespace = "http://F08_WriteOffInquiryPOS/F08_WriteOffInquirySI", className = "th.net.cat.epis.ws.f08_writeoffinquirypos.f08_writeoffinquirysi.RetrieveWriteOffInvoice")
    @WebMethod
    @ResponseWrapper(localName = "retrieveWriteOffInvoiceResponse", targetNamespace = "http://F08_WriteOffInquiryPOS/F08_WriteOffInquirySI", className = "th.net.cat.epis.ws.f08_writeoffinquirypos.f08_writeoffinquirysi.RetrieveWriteOffInvoiceResponse")
    public th.net.cat.epis.ws.f08_writeoffinquirypos.RetrieveWriteOffInvoiceResponse retrieveWriteOffInvoice(
            @WebParam(name = "retrieveWriteOffInvoiceRequest", targetNamespace = "")
                    th.net.cat.epis.ws.f08_writeoffinquirypos.RetrieveWriteOffInvoiceRequest retrieveWriteOffInvoiceRequest
    );
}
