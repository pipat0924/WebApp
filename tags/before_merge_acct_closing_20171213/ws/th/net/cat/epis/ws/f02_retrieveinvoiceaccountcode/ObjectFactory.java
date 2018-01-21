
package th.net.cat.epis.ws.f02_retrieveinvoiceaccountcode;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the f02_retrieveinvoiceaccountcode package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: f02_retrieveinvoiceaccountcode
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InvoiceAccountCodeBO }
     * 
     */
    public InvoiceAccountCodeBO createInvoiceAccountCodeBO() {
        return new InvoiceAccountCodeBO();
    }

    /**
     * Create an instance of {@link RetrieveInvoiceAccountCodeResponse }
     * 
     */
    public RetrieveInvoiceAccountCodeResponse createRetrieveInvoiceAccountCodeResponse() {
        return new RetrieveInvoiceAccountCodeResponse();
    }

    /**
     * Create an instance of {@link RetrieveInvoiceAccountCodeRequest }
     * 
     */
    public RetrieveInvoiceAccountCodeRequest createRetrieveInvoiceAccountCodeRequest() {
        return new RetrieveInvoiceAccountCodeRequest();
    }

}
