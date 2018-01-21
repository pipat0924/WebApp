
package th.net.cat.epis.ws.f08_writeoffinquirypos;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the f08_writeoffinquirypos package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: f08_writeoffinquirypos
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RetrieveWriteOffInvoiceRequest }
     * 
     */
    public RetrieveWriteOffInvoiceRequest createRetrieveWriteOffInvoiceRequest() {
        return new RetrieveWriteOffInvoiceRequest();
    }

    /**
     * Create an instance of {@link RetrieveWriteOffInvoiceResponse }
     * 
     */
    public RetrieveWriteOffInvoiceResponse createRetrieveWriteOffInvoiceResponse() {
        return new RetrieveWriteOffInvoiceResponse();
    }

    /**
     * Create an instance of {@link WriteOffInvoiceBO }
     * 
     */
    public WriteOffInvoiceBO createWriteOffInvoiceBO() {
        return new WriteOffInvoiceBO();
    }

    /**
     * Create an instance of {@link SubscriptionBO }
     * 
     */
    public SubscriptionBO createSubscriptionBO() {
        return new SubscriptionBO();
    }

    /**
     * Create an instance of {@link InvoiceVatDetailBO }
     * 
     */
    public InvoiceVatDetailBO createInvoiceVatDetailBO() {
        return new InvoiceVatDetailBO();
    }

}