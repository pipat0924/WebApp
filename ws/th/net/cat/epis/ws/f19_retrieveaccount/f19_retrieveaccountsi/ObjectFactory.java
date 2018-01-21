
package th.net.cat.epis.ws.f19_retrieveaccount.f19_retrieveaccountsi;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the f19_retrieveaccount.f19_retrieveaccountsi package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: f19_retrieveaccount.f19_retrieveaccountsi
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RetrieveAccountInfo }
     * 
     */
    public RetrieveAccountInfo createRetrieveAccountInfo() {
        return new RetrieveAccountInfo();
    }

    /**
     * Create an instance of {@link RetrieveAccountInfoResponse }
     * 
     */
    public RetrieveAccountInfoResponse createRetrieveAccountInfoResponse() {
        return new RetrieveAccountInfoResponse();
    }

}
