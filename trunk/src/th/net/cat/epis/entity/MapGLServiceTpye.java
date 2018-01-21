package th.net.cat.epis.entity;


import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Administrator on 25/5/2560.
 */
@Entity
@Table(name = "MAP_GL_SERVICE_TYPE")
public class MapGLServiceTpye {
    @Id
    @SequenceGenerator(name="MAPGLSERVICETYPE_SEQ", sequenceName="MAPGLSERVICETYPE_SEQ", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MAPGLSERVICETYPE_SEQ")

    /*@Column(name = "MAPGLID") private Long id;*/
    @Column(name = "SERV_ID") private Long servId;
    @Column(name = "GL_CODE") private String glCode;
    @Column(name = "PRODUCT_CODE") private String productCode;
    @Column(name = "PRODUCT_NAME") private String productName;
    @Column(name = "SUB_PRODUCT_CODE") private String subProductCode;
    @Column(name = "SUB_PRODUCT_NAME") private String subProductName;
    @Column(name = "SERVICE_NAME") private String serviceName;
    @Column(name = "REVENUE_TYPE_CODE") private String revenueTypeCode;
    @Column(name = "REVENUE_TYPE_NAME") private String revenueTypeName;
    @Column(name = "SEGMENT_CODE") private String segmentCode;
    @Column(name = "SEGMENT_NAME") private String segmentName;
    @Column(name = "SOURCE") private String source;
    @Column(name = "SERVICE_CODE") private String serviceCode;
    @Column(name = "STATUS") private Long status;
    @Column(name = "CREATE_BY") private String createBy;
    @Column(name = "CREATE_DATE") private Date createDate;
    @Column(name = "UPDATE_BY") private String updateBy;
    @Column(name = "UPDATE_DATE") private Date updateDate;
    
    public Long getServId() {
		return servId;
	}
	public void setServId(Long servId) {
		this.servId = servId;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getGlCode() {
		return glCode;
	}
	public void setGlCode(String glCode) {
		this.glCode = glCode;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSubProductCode() {
		return subProductCode;
	}
	public void setSubProductCode(String subProductCode) {
		this.subProductCode = subProductCode;
	}
	public String getSubProductName() {
		return subProductName;
	}
	public void setSubProductName(String subProductName) {
		this.subProductName = subProductName;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getRevenueTypeCode() {
		return revenueTypeCode;
	}
	public void setRevenueTypeCode(String revenueTypeCode) {
		this.revenueTypeCode = revenueTypeCode;
	}
	public String getRevenueTypeName() {
		return revenueTypeName;
	}
	public void setRevenueTypeName(String revenueTypeName) {
		this.revenueTypeName = revenueTypeName;
	}
	public String getSegmentCode() {
		return segmentCode;
	}
	public void setSegmentCode(String segmentCode) {
		this.segmentCode = segmentCode;
	}
	public String getSegmentName() {
		return segmentName;
	}
	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}


}
