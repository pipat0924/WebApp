
package th.net.cat.epis.ws.f01_retrieveinvoiceheader;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the f01_retrieveinvoiceheader package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: f01_retrieveinvoiceheader
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InvoiceHeaderBO }
     * 
     */
    public InvoiceHeaderBO createInvoiceHeaderBO() {
        return new InvoiceHeaderBO();
    }

    /**
     * Create an instance of {@link InvoiceVatDetailBO }
     * 
     */
    public InvoiceVatDetailBO createInvoiceVatDetailBO() {
        return new InvoiceVatDetailBO();
    }

    /**
     * Create an instance of {@link SubscriptionBO }
     * 
     */
    public SubscriptionBO createSubscriptionBO() {
        return new SubscriptionBO();
    }

    /**
     * Create an instance of {@link RetrieveInvoiceHeaderRequest }
     * 
     */
    public RetrieveInvoiceHeaderRequest createRetrieveInvoiceHeaderRequest() {
        return new RetrieveInvoiceHeaderRequest();
    }

    /**
     * Create an instance of {@link RetrieveInvoiceHeaderResponse }
     * 
     */
    public RetrieveInvoiceHeaderResponse createRetrieveInvoiceHeaderResponse() {
        return new RetrieveInvoiceHeaderResponse();
    }

}
