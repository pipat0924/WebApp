
package th.net.cat.epis.ws.m05_repeatorder;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the m05_repeatorder package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: m05_repeatorder
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RepeatOrderRequest }
     * 
     */
    public RepeatOrderRequest createRepeatOrderRequest() {
        return new RepeatOrderRequest();
    }

    /**
     * Create an instance of {@link OrderRepeat }
     * 
     */
    public OrderRepeat createOrderRepeat() {
        return new OrderRepeat();
    }

    /**
     * Create an instance of {@link RepeatOrderResponse }
     * 
     */
    public RepeatOrderResponse createRepeatOrderResponse() {
        return new RepeatOrderResponse();
    }

}
