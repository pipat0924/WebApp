
package th.net.cat.epis.ws.esblibs.cbos;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DZipCodeCBO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DZipCodeCBO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ParamName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ParamId" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="ParamValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DZipCodeCBO", propOrder = {
    "paramName",
    "paramId",
    "paramValue"
})
public class DZipCodeCBO {

    @XmlElement(name = "ParamName")
    protected String paramName;
    @XmlElement(name = "ParamId")
    protected BigInteger paramId;
    @XmlElement(name = "ParamValue")
    protected String paramValue;

    /**
     * Gets the value of the paramName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * Sets the value of the paramName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParamName(String value) {
        this.paramName = value;
    }

    /**
     * Gets the value of the paramId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getParamId() {
        return paramId;
    }

    /**
     * Sets the value of the paramId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setParamId(BigInteger value) {
        this.paramId = value;
    }

    /**
     * Gets the value of the paramValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * Sets the value of the paramValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParamValue(String value) {
        this.paramValue = value;
    }

}
