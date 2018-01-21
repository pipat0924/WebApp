package th.net.cat.epis.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="MASPACKAGE_PROMOTION")
public class PackagePromotion {


	@Id
	@SequenceGenerator(name="MASPACKAGE_PROMOTION_SEQ", sequenceName="MASPACKAGE_PROMOTION_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MASPACKAGE_PROMOTION_SEQ")
	@Column(name="ID") private Long id;
	@Column(name="PACKAGE_ID") private String packageId;
	@Column(name="PROMOTION") private String promotion;
	@Column(name="CREATED_BY") private String createdBy;
	@Column(name="CREATED_DATE") private Date createdDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	public String getPromotion() {
		return promotion;
	}
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
}
