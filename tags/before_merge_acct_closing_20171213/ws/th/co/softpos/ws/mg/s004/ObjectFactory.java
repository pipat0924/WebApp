
package th.co.softpos.ws.mg.s004;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the th.co.softpos.ws.mg.s004 package. 
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

    private final static QName _Process_QNAME = new QName("http://s004.mg.ws.softpos.co.th/", "process");
    private final static QName _ProcessResponse_QNAME = new QName("http://s004.mg.ws.softpos.co.th/", "processResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: th.co.softpos.ws.mg.s004
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Process }
     * 
     */
    public Process createProcess() {
        return new Process();
    }

    /**
     * Create an instance of {@link ProcessResponse }
     * 
     */
    public ProcessResponse createProcessResponse() {
        return new ProcessResponse();
    }

    /**
     * Create an instance of {@link Request }
     * 
     */
    public Request createRequest() {
        return new Request();
    }

    /**
     * Create an instance of {@link RqHeader }
     * 
     */
    public RqHeader createRqHeader() {
        return new RqHeader();
    }

    /**
     * Create an instance of {@link RqBody }
     * 
     */
    public RqBody createRqBody() {
        return new RqBody();
    }

    /**
     * Create an instance of {@link PosFreeBillno }
     * 
     */
    public PosFreeBillno createPosFreeBillno() {
        return new PosFreeBillno();
    }

    /**
     * Create an instance of {@link Response }
     * 
     */
    public Response createResponse() {
        return new Response();
    }

    /**
     * Create an instance of {@link RsHeader }
     * 
     */
    public RsHeader createRsHeader() {
        return new RsHeader();
    }

    /**
     * Create an instance of {@link Error }
     * 
     */
    public Error createError() {
        return new Error();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Process }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://s004.mg.ws.softpos.co.th/", name = "process")
    public JAXBElement<Process> createProcess(Process value) {
        return new JAXBElement<Process>(_Process_QNAME, Process.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProcessResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://s004.mg.ws.softpos.co.th/", name = "processResponse")
    public JAXBElement<ProcessResponse> createProcessResponse(ProcessResponse value) {
        return new JAXBElement<ProcessResponse>(_ProcessResponse_QNAME, ProcessResponse.class, null, value);
    }

}
