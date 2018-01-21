
package th.net.cat.epis.ws.f19_retrieveaccount;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the f19_retrieveaccount package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: f19_retrieveaccount
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RetrieveAccountRequest }
     * 
     */
    public RetrieveAccountRequest createRetrieveAccountRequest() {
        return new RetrieveAccountRequest();
    }

    /**
     * Create an instance of {@link AccountBO }
     * 
     */
    public AccountBO createAccountBO() {
        return new AccountBO();
    }

    /**
     * Create an instance of {@link RetrieveAccountResponse }
     * 
     */
    public RetrieveAccountResponse createRetrieveAccountResponse() {
        return new RetrieveAccountResponse();
    }

}
