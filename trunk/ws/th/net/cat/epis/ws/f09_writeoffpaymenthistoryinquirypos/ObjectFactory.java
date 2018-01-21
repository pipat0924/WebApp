
package th.net.cat.epis.ws.f09_writeoffpaymenthistoryinquirypos;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the f09_writeoffpaymenthistoryinquirypos package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: f09_writeoffpaymenthistoryinquirypos
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RetrieveWriteOffPaymentRequest }
     * 
     */
    public RetrieveWriteOffPaymentRequest createRetrieveWriteOffPaymentRequest() {
        return new RetrieveWriteOffPaymentRequest();
    }

    /**
     * Create an instance of {@link RetrieveWriteOffPaymentResponse }
     * 
     */
    public RetrieveWriteOffPaymentResponse createRetrieveWriteOffPaymentResponse() {
        return new RetrieveWriteOffPaymentResponse();
    }

    /**
     * Create an instance of {@link WriteOffPaymentBO }
     * 
     */
    public WriteOffPaymentBO createWriteOffPaymentBO() {
        return new WriteOffPaymentBO();
    }

}
