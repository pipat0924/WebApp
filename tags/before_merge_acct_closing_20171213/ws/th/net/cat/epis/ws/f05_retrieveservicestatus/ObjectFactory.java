
package th.net.cat.epis.ws.f05_retrieveservicestatus;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the f05_retrieveservicestatus package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: f05_retrieveservicestatus
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RetrieveServiceStatusRequest }
     * 
     */
    public RetrieveServiceStatusRequest createRetrieveServiceStatusRequest() {
        return new RetrieveServiceStatusRequest();
    }

    /**
     * Create an instance of {@link RetrieveServiceStatusResponse }
     * 
     */
    public RetrieveServiceStatusResponse createRetrieveServiceStatusResponse() {
        return new RetrieveServiceStatusResponse();
    }

    /**
     * Create an instance of {@link ServiceStatusBO }
     * 
     */
    public ServiceStatusBO createServiceStatusBO() {
        return new ServiceStatusBO();
    }

}
