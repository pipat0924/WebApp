
package th.co.softpos.ws.mg.s002;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rqBody complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rqBody"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="MgTempImportDetailGiftvoucher" type="{http://s002.mg.ws.softpos.co.th/}mgTempImportDetailGiftvoucher"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rqBody", propOrder = {
    "mgTempImportDetailGiftvoucher"
})
public class RqBody {

    @XmlElement(name = "MgTempImportDetailGiftvoucher", required = true)
    protected MgTempImportDetailGiftvoucher mgTempImportDetailGiftvoucher;

    /**
     * Gets the value of the mgTempImportDetailGiftvoucher property.
     * 
     * @return
     *     possible object is
     *     {@link MgTempImportDetailGiftvoucher }
     *     
     */
    public MgTempImportDetailGiftvoucher getMgTempImportDetailGiftvoucher() {
        return mgTempImportDetailGiftvoucher;
    }

    /**
     * Sets the value of the mgTempImportDetailGiftvoucher property.
     * 
     * @param value
     *     allowed object is
     *     {@link MgTempImportDetailGiftvoucher }
     *     
     */
    public void setMgTempImportDetailGiftvoucher(MgTempImportDetailGiftvoucher value) {
        this.mgTempImportDetailGiftvoucher = value;
    }

}
