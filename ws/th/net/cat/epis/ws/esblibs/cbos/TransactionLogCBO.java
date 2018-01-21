
package th.net.cat.epis.ws.esblibs.cbos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TransactionLogCBO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransactionLogCBO"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TranID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="InterfaceNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SourceSystem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SourceIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SourceUsername" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DestinationSystem" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DestinationIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DestinationUsername" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SourceInterfaceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DestinationInterfaceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RequestDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="ResponseDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="InputData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="OutputData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="InputFilename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="OutputFilename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ESBReturnCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ESBReturnDetails" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DestinationReturnCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DestinationReturnDetails" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="LogStatus" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="TransactionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DataLog" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransactionLogCBO", propOrder = {
    "tranID",
    "interfaceNo",
    "sourceSystem",
    "sourceIP",
    "sourceUsername",
    "destinationSystem",
    "destinationIP",
    "destinationUsername",
    "sourceInterfaceType",
    "destinationInterfaceType",
    "requestDateTime",
    "responseDateTime",
    "inputData",
    "outputData",
    "inputFilename",
    "outputFilename",
    "esbReturnCode",
    "esbReturnDetails",
    "destinationReturnCode",
    "destinationReturnDetails",
    "logStatus",
    "transactionType",
    "dataLog"
})
public class TransactionLogCBO {

    @XmlElement(name = "TranID")
    protected String tranID;
    @XmlElement(name = "InterfaceNo")
    protected String interfaceNo;
    @XmlElement(name = "SourceSystem")
    protected String sourceSystem;
    @XmlElement(name = "SourceIP")
    protected String sourceIP;
    @XmlElement(name = "SourceUsername")
    protected String sourceUsername;
    @XmlElement(name = "DestinationSystem")
    protected String destinationSystem;
    @XmlElement(name = "DestinationIP")
    protected String destinationIP;
    @XmlElement(name = "DestinationUsername")
    protected String destinationUsername;
    @XmlElement(name = "SourceInterfaceType")
    protected String sourceInterfaceType;
    @XmlElement(name = "DestinationInterfaceType")
    protected String destinationInterfaceType;
    @XmlElement(name = "RequestDateTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar requestDateTime;
    @XmlElement(name = "ResponseDateTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar responseDateTime;
    @XmlElement(name = "InputData")
    protected String inputData;
    @XmlElement(name = "OutputData")
    protected String outputData;
    @XmlElement(name = "InputFilename")
    protected String inputFilename;
    @XmlElement(name = "OutputFilename")
    protected String outputFilename;
    @XmlElement(name = "ESBReturnCode")
    protected String esbReturnCode;
    @XmlElement(name = "ESBReturnDetails")
    protected String esbReturnDetails;
    @XmlElement(name = "DestinationReturnCode")
    protected String destinationReturnCode;
    @XmlElement(name = "DestinationReturnDetails")
    protected String destinationReturnDetails;
    @XmlElement(name = "LogStatus")
    protected Boolean logStatus;
    @XmlElement(name = "TransactionType")
    protected String transactionType;
    @XmlElement(name = "DataLog")
    protected Boolean dataLog;

    /**
     * Gets the value of the tranID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTranID() {
        return tranID;
    }

    /**
     * Sets the value of the tranID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTranID(String value) {
        this.tranID = value;
    }

    /**
     * Gets the value of the interfaceNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInterfaceNo() {
        return interfaceNo;
    }

    /**
     * Sets the value of the interfaceNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterfaceNo(String value) {
        this.interfaceNo = value;
    }

    /**
     * Gets the value of the sourceSystem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceSystem() {
        return sourceSystem;
    }

    /**
     * Sets the value of the sourceSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceSystem(String value) {
        this.sourceSystem = value;
    }

    /**
     * Gets the value of the sourceIP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceIP() {
        return sourceIP;
    }

    /**
     * Sets the value of the sourceIP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceIP(String value) {
        this.sourceIP = value;
    }

    /**
     * Gets the value of the sourceUsername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceUsername() {
        return sourceUsername;
    }

    /**
     * Sets the value of the sourceUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceUsername(String value) {
        this.sourceUsername = value;
    }

    /**
     * Gets the value of the destinationSystem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationSystem() {
        return destinationSystem;
    }

    /**
     * Sets the value of the destinationSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationSystem(String value) {
        this.destinationSystem = value;
    }

    /**
     * Gets the value of the destinationIP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationIP() {
        return destinationIP;
    }

    /**
     * Sets the value of the destinationIP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationIP(String value) {
        this.destinationIP = value;
    }

    /**
     * Gets the value of the destinationUsername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationUsername() {
        return destinationUsername;
    }

    /**
     * Sets the value of the destinationUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationUsername(String value) {
        this.destinationUsername = value;
    }

    /**
     * Gets the value of the sourceInterfaceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceInterfaceType() {
        return sourceInterfaceType;
    }

    /**
     * Sets the value of the sourceInterfaceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceInterfaceType(String value) {
        this.sourceInterfaceType = value;
    }

    /**
     * Gets the value of the destinationInterfaceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationInterfaceType() {
        return destinationInterfaceType;
    }

    /**
     * Sets the value of the destinationInterfaceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationInterfaceType(String value) {
        this.destinationInterfaceType = value;
    }

    /**
     * Gets the value of the requestDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRequestDateTime() {
        return requestDateTime;
    }

    /**
     * Sets the value of the requestDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRequestDateTime(XMLGregorianCalendar value) {
        this.requestDateTime = value;
    }

    /**
     * Gets the value of the responseDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getResponseDateTime() {
        return responseDateTime;
    }

    /**
     * Sets the value of the responseDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setResponseDateTime(XMLGregorianCalendar value) {
        this.responseDateTime = value;
    }

    /**
     * Gets the value of the inputData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInputData() {
        return inputData;
    }

    /**
     * Sets the value of the inputData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInputData(String value) {
        this.inputData = value;
    }

    /**
     * Gets the value of the outputData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputData() {
        return outputData;
    }

    /**
     * Sets the value of the outputData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputData(String value) {
        this.outputData = value;
    }

    /**
     * Gets the value of the inputFilename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInputFilename() {
        return inputFilename;
    }

    /**
     * Sets the value of the inputFilename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInputFilename(String value) {
        this.inputFilename = value;
    }

    /**
     * Gets the value of the outputFilename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputFilename() {
        return outputFilename;
    }

    /**
     * Sets the value of the outputFilename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputFilename(String value) {
        this.outputFilename = value;
    }

    /**
     * Gets the value of the esbReturnCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getESBReturnCode() {
        return esbReturnCode;
    }

    /**
     * Sets the value of the esbReturnCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setESBReturnCode(String value) {
        this.esbReturnCode = value;
    }

    /**
     * Gets the value of the esbReturnDetails property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getESBReturnDetails() {
        return esbReturnDetails;
    }

    /**
     * Sets the value of the esbReturnDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setESBReturnDetails(String value) {
        this.esbReturnDetails = value;
    }

    /**
     * Gets the value of the destinationReturnCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationReturnCode() {
        return destinationReturnCode;
    }

    /**
     * Sets the value of the destinationReturnCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationReturnCode(String value) {
        this.destinationReturnCode = value;
    }

    /**
     * Gets the value of the destinationReturnDetails property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinationReturnDetails() {
        return destinationReturnDetails;
    }

    /**
     * Sets the value of the destinationReturnDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinationReturnDetails(String value) {
        this.destinationReturnDetails = value;
    }

    /**
     * Gets the value of the logStatus property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLogStatus() {
        return logStatus;
    }

    /**
     * Sets the value of the logStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLogStatus(Boolean value) {
        this.logStatus = value;
    }

    /**
     * Gets the value of the transactionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Sets the value of the transactionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionType(String value) {
        this.transactionType = value;
    }

    /**
     * Gets the value of the dataLog property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDataLog() {
        return dataLog;
    }

    /**
     * Sets the value of the dataLog property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDataLog(Boolean value) {
        this.dataLog = value;
    }

}
