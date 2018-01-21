package th.net.cat.epis.dto;

import th.net.cat.epis.entity.EndDayClosing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nastanda on 5/11/2017 AD.
 */
public class EndDayClosingDTO {

    public static class Employee{
        private String empCode;
        private Long closingId;
        private String branchCode;
        private String posNo;
        private String closingEmpCode;
        private String receiptDate;
        private Boolean checked;
        private String shopClosingId;
        private String empClosingId;
        public String getEmpCode() {
            return empCode;
        }

        public void setEmpCode(String empCode) {
            this.empCode = empCode;
        }

        public Long getClosingId() {
            return closingId;
        }

        public void setClosingId(Long closingId) {
            this.closingId = closingId;
        }

        public String getBranchCode() {
            return branchCode;
        }

        public void setBranchCode(String branchCode) {
            this.branchCode = branchCode;
        }

        public String getPosNo() {
            return posNo;
        }

        public void setPosNo(String posNo) {
            this.posNo = posNo;
        }

        public String getClosingEmpCode() {
            return closingEmpCode;
        }

        public void setClosingEmpCode(String closingEmpCode) {
            this.closingEmpCode = closingEmpCode;
        }
        public String getReceiptDate() { return receiptDate; }

        public void setReceiptDate(String receiptDate) { this.receiptDate = receiptDate; }

        public Boolean getChecked() { return checked; }

        public void setChecked(Boolean checked) { this.checked = checked; }

        public String getShopClosingId() { return shopClosingId; }

        public void setShopClosingId(String shopClosingId) { this.shopClosingId = shopClosingId; }

        public String getEmpClosingId() { return empClosingId; }

        public void setEmpClosingId(String empClosingId) { this.empClosingId = empClosingId; }
    }
    private Long closingId;
    private List<Employee> employees = new ArrayList<Employee>();
    private List<EndDayClosing> endDayClosingList = new ArrayList<EndDayClosing>();
    private String searchDate;
    private String searchTransDate;
    private String posNo;
    private String empCode;//do payment transaction employee
    private String branchCode;
    private String closingEmpCode;// closing employee it can be super user
    private String backDateStatus;
    private String shopClosingId;
    private String empClosingId;
    private String userName;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Long getClosingId() {
        return closingId;
    }

    public void setClosingId(Long closingId) {
        this.closingId = closingId;
    }

    public List<EndDayClosing> getEndDayClosingList() {
        return endDayClosingList;
    }

    public void setEndDayClosingList(List<EndDayClosing> endDayClosingList) {
        this.endDayClosingList = endDayClosingList;
    }

    public String getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
    }
    public String getSearchTransDate() { return searchTransDate; }

    public void setSearchTransDate(String searchTransDate) { this.searchTransDate = searchTransDate; }


    public String getPosNo() {
        return posNo;
    }

    public void setPosNo(String posNo) {
        this.posNo = posNo;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getClosingEmpCode() {
        return closingEmpCode;
    }

    public void setClosingEmpCode(String closingEmpCode) {
        this.closingEmpCode = closingEmpCode;
    }

    public String getBackDateStatus() {
        return backDateStatus;
    }

    public void setBackDateStatus(String backDateStatus) {
        this.backDateStatus = backDateStatus;
    }
    public String getShopClosingId() { return shopClosingId; }

    public void setShopClosingId(String shopClosingId) {
        this.shopClosingId = shopClosingId;
    }

    public String getEmpClosingId() { return empClosingId; }

    public void setEmpClosingId(String empClosingId) { this.empClosingId = empClosingId; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

}
