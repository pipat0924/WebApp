
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package th.co.softpos.ws.mg.s002;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2017-08-02T16:54:49.306+07:00
 * Generated source version: 3.1.6
 * 
 */

@javax.jws.WebService(
                      serviceName = "S002MGUpdGiftvoucherService",
                      portName = "S002MGUpdGiftvoucherPort",
                      targetNamespace = "http://s002.mg.ws.softpos.co.th/",
                      wsdlLocation = "http://10.44.1.4/InsalePosWS/services/S002MGUpdGiftvoucher?wsdl",
                      endpointInterface = "th.co.softpos.ws.mg.s002.S002MGUpdGiftvoucher")
                      
public class S002MGUpdGiftvoucherPortImpl implements S002MGUpdGiftvoucher {

    private static final Logger LOG = Logger.getLogger(S002MGUpdGiftvoucherPortImpl.class.getName());

    /* (non-Javadoc)
     * @see th.co.softpos.ws.mg.s002.S002MGUpdGiftvoucher#process(th.co.softpos.ws.mg.s002.Request  rq )*
     */
    public th.co.softpos.ws.mg.s002.Response process(th.co.softpos.ws.mg.s002.Request rq) { 
        LOG.info("Executing operation process");
        System.out.println(rq);
        try {
            th.co.softpos.ws.mg.s002.Response _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
