
package th.net.cat.epis.ws.m02_findorderdetail;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the m02_findorderdetail package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: m02_findorderdetail
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindOrderDetailRequest }
     * 
     */
    public FindOrderDetailRequest createFindOrderDetailRequest() {
        return new FindOrderDetailRequest();
    }

    /**
     * Create an instance of {@link OrderDetail }
     * 
     */
    public OrderDetail createOrderDetail() {
        return new OrderDetail();
    }

    /**
     * Create an instance of {@link FindOrderDetailResponse }
     * 
     */
    public FindOrderDetailResponse createFindOrderDetailResponse() {
        return new FindOrderDetailResponse();
    }

}
