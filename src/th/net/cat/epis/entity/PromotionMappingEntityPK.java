package th.net.cat.epis.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by imake on 02/08/2017
 */
public class PromotionMappingEntityPK implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2354932830491003535L;
	private String taxId;
    private String packageId;

    @Column(name = "TAX_ID")
    @Id
    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    @Column(name = "PACKAGE_ID")
    @Id
    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PromotionMappingEntityPK that = (PromotionMappingEntityPK) o;

        if (taxId != null ? !taxId.equals(that.taxId) : that.taxId != null) return false;
        if (packageId != null ? !packageId.equals(that.packageId) : that.packageId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = taxId != null ? taxId.hashCode() : 0;
        result = 31 * result + (packageId != null ? packageId.hashCode() : 0);
        return result;
    }
}
