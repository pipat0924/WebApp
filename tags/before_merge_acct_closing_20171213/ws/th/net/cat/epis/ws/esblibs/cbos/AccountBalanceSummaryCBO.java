
package th.net.cat.epis.ws.esblibs.cbos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccountBalanceSummaryCBO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountBalanceSummaryCBO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SumBalance" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;pattern value="[0-9]*(\.[0-9]*)?"/&gt;
 *               &lt;maxLength value="18"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="PastDueDaysGt120" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CurrencyCode" type="{http://www.w3.org/2001/XMLSchema}short" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountBalanceSummaryCBO", propOrder = {
    "sumBalance",
    "pastDueDaysGt120",
    "currencyCode"
})
public class AccountBalanceSummaryCBO {

    @XmlElement(name = "SumBalance")
    protected String sumBalance;
    @XmlElement(name = "PastDueDaysGt120")
    protected String pastDueDaysGt120;
    @XmlElement(name = "CurrencyCode")
    protected Short currencyCode;

    /**
     * Gets the value of the sumBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSumBalance() {
        return sumBalance;
    }

    /**
     * Sets the value of the sumBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSumBalance(String value) {
        this.sumBalance = value;
    }

    /**
     * Gets the value of the pastDueDaysGt120 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPastDueDaysGt120() {
        return pastDueDaysGt120;
    }

    /**
     * Sets the value of the pastDueDaysGt120 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPastDueDaysGt120(String value) {
        this.pastDueDaysGt120 = value;
    }

    /**
     * Gets the value of the currencyCode property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public Short getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the value of the currencyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setCurrencyCode(Short value) {
        this.currencyCode = value;
    }

}
