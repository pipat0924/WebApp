
package th.net.cat.epis.ws.esblibs.cbos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchCBO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SearchCBO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SearchField" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SearchCondition" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="10"/&gt;
 *               &lt;enumeration value="Equal"/&gt;
 *               &lt;enumeration value="Like"/&gt;
 *               &lt;enumeration value="Between"/&gt;
 *               &lt;enumeration value="GreaterThan"/&gt;
 *               &lt;enumeration value="LowerThan"/&gt;
 *               &lt;enumeration value="IsNull"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="SearchValue1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SearchValue2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SearchValueType1" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="10"/&gt;
 *               &lt;enumeration value="Short"/&gt;
 *               &lt;enumeration value="String"/&gt;
 *               &lt;enumeration value="Integer"/&gt;
 *               &lt;enumeration value="Boolean"/&gt;
 *               &lt;enumeration value="DateTime"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="SearchValueType2" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="10"/&gt;
 *               &lt;enumeration value="Short"/&gt;
 *               &lt;enumeration value="String"/&gt;
 *               &lt;enumeration value="Integer"/&gt;
 *               &lt;enumeration value="Boolean"/&gt;
 *               &lt;enumeration value="DateTime"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchCBO", propOrder = {
    "searchField",
    "searchCondition",
    "searchValue1",
    "searchValue2",
    "searchValueType1",
    "searchValueType2"
})
public class SearchCBO {

    @XmlElement(name = "SearchField")
    protected String searchField;
    @XmlElement(name = "SearchCondition")
    protected String searchCondition;
    @XmlElement(name = "SearchValue1")
    protected String searchValue1;
    @XmlElement(name = "SearchValue2")
    protected String searchValue2;
    @XmlElement(name = "SearchValueType1")
    protected String searchValueType1;
    @XmlElement(name = "SearchValueType2")
    protected String searchValueType2;

    /**
     * Gets the value of the searchField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchField() {
        return searchField;
    }

    /**
     * Sets the value of the searchField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchField(String value) {
        this.searchField = value;
    }

    /**
     * Gets the value of the searchCondition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchCondition() {
        return searchCondition;
    }

    /**
     * Sets the value of the searchCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchCondition(String value) {
        this.searchCondition = value;
    }

    /**
     * Gets the value of the searchValue1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchValue1() {
        return searchValue1;
    }

    /**
     * Sets the value of the searchValue1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchValue1(String value) {
        this.searchValue1 = value;
    }

    /**
     * Gets the value of the searchValue2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchValue2() {
        return searchValue2;
    }

    /**
     * Sets the value of the searchValue2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchValue2(String value) {
        this.searchValue2 = value;
    }

    /**
     * Gets the value of the searchValueType1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchValueType1() {
        return searchValueType1;
    }

    /**
     * Sets the value of the searchValueType1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchValueType1(String value) {
        this.searchValueType1 = value;
    }

    /**
     * Gets the value of the searchValueType2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchValueType2() {
        return searchValueType2;
    }

    /**
     * Sets the value of the searchValueType2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchValueType2(String value) {
        this.searchValueType2 = value;
    }

}
