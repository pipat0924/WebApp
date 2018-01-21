package th.net.cat.epis.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by nastanda on 5/9/2017 AD.
 */
@Entity
@Table(name="DAY_END_CLOSING")
public class EndDayClosing {
    @Id
    @SequenceGenerator(name="CLOSINGID_SEQ", sequenceName="CLOSINGID_SEQ", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLOSINGID_SEQ")
    @Column(name="CLOSING_ID") private Long id;

    @Column(name = "CLOSING_DATE") private Date closingDate;
    @Column(name = "BRANCH_CODE") private String branchCode;
    @Column(name = "MAC_NO") private String macNo;
    @Column(name = "EMP_CODE") private String empCode;
    @Column(name = "USERNAME") private String userName;
    @Column(name = "DOC_STATUS") private String docStatus;
    @Column(name = "TOTAL_EXC_AMOUNT") private BigDecimal totalExcAmount;
    @Column(name = "TOTAL_VAT_AMOUNT") private BigDecimal totalVatAmount;
    @Column(name = "TOTAL_CHAEGE") private BigDecimal totalCharge;
    @Column(name = "TOTAL_RECEIPT_COUNT") private Long totalReceiptCount;

    @Column(name = "TOTAL_CASH") private BigDecimal totalCash;
    @Column(name = "TOTAL_CASH_COUNT") private Long totalCashCount;
    @Column(name = "TOTAL_CHEQUE") private BigDecimal totalCheque;
    @Column(name = "TOTAL_CHEQUE_COUNT") private Long totalChequeCount;
    @Column(name = "TOTAL_CREDIT") private BigDecimal totalCredit;
    @Column(name = "TOTAL_CREDIT_COUNT") private Long totalCreditCount;
    @Column(name = "TOTAL_MONEY_ORDER") private BigDecimal totalMoneyOrder;
    @Column(name = "TOTAL_MONEY_ORD_CNT") private Long totalMoneyOrdCnt;
    @Column(name = "TOTAL_BILL_EXCHANGE") private BigDecimal totalBillExchange;
    @Column(name = "TOTAL_BILL_EXCH_CNT") private Long totalBillExchCnt;
    @Column(name = "TOTAL_MONEY_TRANSFER") private BigDecimal totalMoneyTransfer;
    @Column(name = "TOTAL_MONEY_TRNS_CNT") private Long totalMoneyTrnsCnt;
    @Column(name = "TOTAL_COUPON") private BigDecimal totalCoupon;
    @Column(name = "TOTAL_COUPON_COUNT") private Long totalCouponCount;
    @Column(name = "TOTAL_FOREIGN_TRANSFER") private BigDecimal totalForeignTransfer;
    @Column(name = "TOTAL_FOREIGN_TRNS_CNT") private Long totalForeignTrnsCnt;
    @Column(name = "TOTAL_OFFSET") private BigDecimal totalOffset;
    @Column(name = "TOTAL_OFFSET_COUNT") private Long totalOffsetCount;
    @Column(name = "TOTAL_OTHER") private BigDecimal totalOther;
    @Column(name = "TOTAL_OTHER_COUNT") private Long totalOtherCount;

    @Column(name = "TOTAL_WT_3TRED") private BigDecimal totalWt3tred;
    @Column(name = "TOTAL_WT_69BIS") private BigDecimal totalWt69bis;
    @Column(name = "TOTAL_WT_69TRE") private BigDecimal totalWt69tre;
    @Column(name = "TOTAL_RTTN_OUT") private BigDecimal totalRttnOut;
    @Column(name = "TOTAL_CMPST") private BigDecimal totalCmpst;
    @Column(name = "TOTAL_FEE_OUT") private BigDecimal totalFeeOut;
    @Column(name = "TOTAL_PTY_OUT") private BigDecimal totalPtyOut;
    @Column(name = "TOTAL_AFTS_DISC") private BigDecimal totalAftsDisc;

    @Column(name = "TOTAL_DISCOUNT") private BigDecimal totalDiscount;
    @Column(name = "TOTAL_CREDIT_AMT") private BigDecimal totalCreditAmt;
    @Column(name = "TOTAL_CREDIT_CNT") private Long totalCreditCnt;
    @Column(name = "TOTAL_CANCEL_AMT") private BigDecimal totalCancelAmt;
    @Column(name = "TOTAL_CANCEL_CNT") private Long totalCancelCnt;

    @Column(name = "RECEIPT_TYPE") private String receiptType;
    @Column(name = "SOURCE_TYPE") private String sourceType;

    @Column(name = "POS_NO") private String posNo;
    @Column(name = "EMP_NAME") private String empName;
    @Column(name = "SHOP_NO") private String shopNo;;
    @Column(name = "SHOP_NAME") private String shopName;

    @Column(name="CREATEDTTM") private java.sql.Date createDttm;
    @Column(name="CREATEUSER") private String createUser;
    @Column(name="UPDATEDTTM") private java.sql.Date updateDttm;
    @Column(name="UPDATEUSER") private String updateUser;

    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
    @JoinColumn(name="EMP_CLOSING_ID") private EmpClosing empClosing;

    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
    @JoinColumn(name="SHOP_CLOSING_ID") private ShopClosing shopClosing;
    // by nsd 28-05-2017
    @Column(name = "EXC_CANCEL_TOTAL_AMOUNT") private BigDecimal excCancelTotalAmount;
    @Column(name = "EXC_CANCEL_TOTAL_COUNT") private Long excCancelTotalCount;
    @Column(name = "RECEIVE_WH_FULL_AMOUNT") private BigDecimal receiveWhFullAmount;
    @Column(name = "RECEIVE_WH_FULL_COUNT") private Long receiveWhFullCount;
    @Column(name = "RECEIVE_WH_ABVR_AMOUNT") private BigDecimal receiveWhAbvrAmount;
    @Column(name = "RECEIVE_WH_ABVR_COUNT") private Long receiveWhAbvrCount;
    @Column(name = "RECEIVE_ONLY_AMOUNT") private BigDecimal receiveOnlyAmount;
    @Column(name = "RECEIVE_ONLY_COUNT") private Long receiveOnlyCount;

    @Column(name = "WH_ONLY_FULL_AMOUNT") private BigDecimal whOnlyFullAmount;//add 13-06-2017
    @Column(name = "WH_ONLY_FULL_COUNT") private Long whOnlyFullCount;
    @Column(name = "WH_ONLY_ABVR_AMOUNT") private BigDecimal whOnlyAbvrAmount;
    @Column(name = "WH_ONLY_ABVR_COUNT") private Long whOnlyAbvrCount;

    @Column(name = "CANCEL_RECEIVE_WH_FULL_AMOUNT") private BigDecimal cancelReceiveWhFullAmount;
    @Column(name = "CANCEL_RECEIVE_WH_FULL_COUNT") private Long cancelReceiveWhFullCount;
    @Column(name = "CANCEL_RECEIVE_WH_ABVR_AMOUNT") private BigDecimal cancelReceiveWhAbvrAmount;
    @Column(name = "CANCEL_RECEIVE_WH_ABVR_COUNT") private Long cancelReceiveWhAbvrCount;
    @Column(name = "CANCEL_RECEIVE_ONLY_AMOUNT") private BigDecimal cancelReceiveOnlyAmount;
    @Column(name = "CANCEL_RECEIVE_ONLY_COUNT") private Long cancelReceiveOnlyCount;

    @Column(name = "CANCEL_WH_ONLY_FULL_AMOUNT") private BigDecimal cancelWhOnlyFullAmount;//add 13-06-2017
    @Column(name = "CANCEL_WH_ONLY_FULL_COUNT") private Long cancelWhOnlyFullCount;
    @Column(name = "CANCEL_WH_ONLY_ABVR_AMOUNT") private BigDecimal cancelWhOnlyAbvrAmount;
    @Column(name = "CANCEL_WH_ONLY_ABVR_COUNT") private Long cancelWhOnlyAbvrCount;

    @Column(name = "TRANS_BACK_DATE_TOTAL_AMOUNT") private BigDecimal backDateTotalAmount;
    @Column(name = "TRANS_BACK_DATE_TOTAL_COUNT") private Long backDateTotalCount;
    //@Column(name = "BACK_DATE_TOTAL_AMOUNT") private BigDecimal backDateTotalAmount;
    //@Column(name = "BACK_DATE_TOTAL_COUNT") private Long backDateTotalCount;

    @Column(name = "BACKDATE_STATUS") private String backDateStatus;
    @Column(name = "RECEIPT_DATE") private java.sql.Date receiptDate;

    @Column(name = "TRANSFER_GFMIS_TOTAL_AMOUNT") private BigDecimal transGfmisTotalAmount;
    @Column(name = "TRANSFER_GFMIS_TOTAL_COUNT") private Long transGfmisTotalCount;
    @Column(name = "TRANS_FOREIGN_TOTAL_AMOUNT") private BigDecimal transForeignTotalAmount;
    @Column(name = "TRANS_FOREIGN_TH_TOTAL_AMOUNT") private BigDecimal transForeignThTotalAmount;
    @Column(name = "TRANS_FOREIGN_TOTAL_COUNT") private Long transForeignTotalCount;
    @Column(name = "FOREIGN_US_TOTAL_AMOUNT") private BigDecimal foreignUsTotalAmount;
    @Column(name = "FOREIGN_US_TO_TH_TOTAL_AMOUNT") private BigDecimal foreignUsToThTotalAmount;
    @Column(name = "FOREIGN_US_TOTAL_COUNT") private Long foreignUsTotalCount;
    @Column(name = "FOREIGN_EU_TOTAL_AMOUNT") private BigDecimal foreignEuTotalAmount;
    @Column(name = "FOREIGN_EU_TO_TH_TOTAL_AMOUNT") private BigDecimal foreignEuToThTotalAmount;
    @Column(name = "FOREIGN_EU_TOTAL_COUNT") private Long foreignEuTotalCount;

    @Column(name = "AGENT_TOTAL_AMOUNT") private BigDecimal agentTotalAmount;
    @Column(name = "AGENT_RECEIVE_FULL_AGENT_COUNT") private BigDecimal agentReceiveFullAgentCount;
    @Column(name = "AGENT_RECEIVE_ABVR_COUNT") private BigDecimal agentReceiveAbvrCount;

    @Transient
    private String closingDtStr;
    @Transient String closingTimeStr;
    @Transient private Long empClosingId1;
    @Transient private Long shopClosingId1;
    @Transient private String receiptDtStr;
    @Transient private String shopClosingDtStr;
    
    @Transient private BigDecimal totalSumByEmp;
    @Transient private BigDecimal total;

    @Transient private Long refClosingId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getMacNo() {
        return macNo;
    }

    public void setMacNo(String macNo) {
        this.macNo = macNo;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus;
    }

    public BigDecimal getTotalExcAmount() {
        return totalExcAmount;
    }

    public void setTotalExcAmount(BigDecimal totalExcAmount) {
        this.totalExcAmount = totalExcAmount;
    }

    public BigDecimal getTotalVatAmount() {
        return totalVatAmount;
    }

    public void setTotalVatAmount(BigDecimal totalVatAmount) {
        this.totalVatAmount = totalVatAmount;
    }

    public BigDecimal getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(BigDecimal totalCharge) {
        this.totalCharge = totalCharge;
    }

    public Long getTotalReceiptCount() {
        return totalReceiptCount;
    }

    public void setTotalReceiptCount(Long totalReceiptCount) {
        this.totalReceiptCount = totalReceiptCount;
    }

    public BigDecimal getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(BigDecimal totalCash) {
        this.totalCash = totalCash;
    }

    public Long getTotalCashCount() {
        return totalCashCount;
    }

    public void setTotalCashCount(Long totalCashCount) {
        this.totalCashCount = totalCashCount;
    }

    public BigDecimal getTotalCheque() {
        return totalCheque;
    }

    public void setTotalCheque(BigDecimal totalCheque) {
        this.totalCheque = totalCheque;
    }

    public Long getTotalChequeCount() {
        return totalChequeCount;
    }

    public void setTotalChequeCount(Long totalChequeCount) {
        this.totalChequeCount = totalChequeCount;
    }

    public BigDecimal getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(BigDecimal totalCredit) {
        this.totalCredit = totalCredit;
    }

    public Long getTotalCreditCount() {
        return totalCreditCount;
    }

    public void setTotalCreditCount(Long totalCreditCount) {
        this.totalCreditCount = totalCreditCount;
    }

    public BigDecimal getTotalMoneyOrder() {
        return totalMoneyOrder;
    }

    public void setTotalMoneyOrder(BigDecimal totalMoneyOrder) {
        this.totalMoneyOrder = totalMoneyOrder;
    }

    public Long getTotalMoneyOrdCnt() {
        return totalMoneyOrdCnt;
    }

    public void setTotalMoneyOrdCnt(Long totalMoneyOrdCnt) {
        this.totalMoneyOrdCnt = totalMoneyOrdCnt;
    }

    public BigDecimal getTotalBillExchange() {
        return totalBillExchange;
    }

    public void setTotalBillExchange(BigDecimal totalBillExchange) {
        this.totalBillExchange = totalBillExchange;
    }

    public Long getTotalBillExchCnt() {
        return totalBillExchCnt;
    }

    public void setTotalBillExchCnt(Long totalBillExchCnt) {
        this.totalBillExchCnt = totalBillExchCnt;
    }

    public BigDecimal getTotalMoneyTransfer() {
        return totalMoneyTransfer;
    }

    public void setTotalMoneyTransfer(BigDecimal totalMoneyTransfer) {
        this.totalMoneyTransfer = totalMoneyTransfer;
    }

    public Long getTotalMoneyTrnsCnt() {
        return totalMoneyTrnsCnt;
    }

    public void setTotalMoneyTrnsCnt(Long totalMoneyTrnsCnt) {
        this.totalMoneyTrnsCnt = totalMoneyTrnsCnt;
    }

    public BigDecimal getTotalCoupon() {
        return totalCoupon;
    }

    public void setTotalCoupon(BigDecimal totalCoupon) {
        this.totalCoupon = totalCoupon;
    }

    public Long getTotalCouponCount() {
        return totalCouponCount;
    }

    public void setTotalCouponCount(Long totalCouponCount) {
        this.totalCouponCount = totalCouponCount;
    }

    public BigDecimal getTotalForeignTransfer() {
        return totalForeignTransfer;
    }

    public void setTotalForeignTransfer(BigDecimal totalForeignTransfer) {
        this.totalForeignTransfer = totalForeignTransfer;
    }

    public Long getTotalForeignTrnsCnt() {
        return totalForeignTrnsCnt;
    }

    public void setTotalForeignTrnsCnt(Long totalForeignTrnsCnt) {
        this.totalForeignTrnsCnt = totalForeignTrnsCnt;
    }

    public BigDecimal getTotalOffset() {
        return totalOffset;
    }

    public void setTotalOffset(BigDecimal totalOffset) {
        this.totalOffset = totalOffset;
    }

    public Long getTotalOffsetCount() {
        return totalOffsetCount;
    }

    public void setTotalOffsetCount(Long totalOffsetCount) {
        this.totalOffsetCount = totalOffsetCount;
    }

    public BigDecimal getTotalOther() {
        return totalOther;
    }

    public void setTotalOther(BigDecimal totalOther) {
        this.totalOther = totalOther;
    }

    public Long getTotalOtherCount() {
        return totalOtherCount;
    }

    public void setTotalOtherCount(Long totalOtherCount) {
        this.totalOtherCount = totalOtherCount;
    }

    public BigDecimal getTotalWt3tred() {
        return totalWt3tred;
    }

    public void setTotalWt3tred(BigDecimal totalWt3tred) {
        this.totalWt3tred = totalWt3tred;
    }

    public BigDecimal getTotalWt69bis() {
        return totalWt69bis;
    }

    public void setTotalWt69bis(BigDecimal totalWt69bis) {
        this.totalWt69bis = totalWt69bis;
    }

    public BigDecimal getTotalWt69tre() {
        return totalWt69tre;
    }

    public void setTotalWt69tre(BigDecimal totalWt69tre) {
        this.totalWt69tre = totalWt69tre;
    }

    public BigDecimal getTotalRttnOut() {
        return totalRttnOut;
    }

    public void setTotalRttnOut(BigDecimal totalRttnOut) {
        this.totalRttnOut = totalRttnOut;
    }

    public BigDecimal getTotalCmpst() {
        return totalCmpst;
    }

    public void setTotalCmpst(BigDecimal totalCmpst) {
        this.totalCmpst = totalCmpst;
    }

    public BigDecimal getTotalFeeOut() {
        return totalFeeOut;
    }

    public void setTotalFeeOut(BigDecimal totalFeeOut) {
        this.totalFeeOut = totalFeeOut;
    }

    public BigDecimal getTotalPtyOut() {
        return totalPtyOut;
    }

    public void setTotalPtyOut(BigDecimal totalPtyOut) {
        this.totalPtyOut = totalPtyOut;
    }

    public BigDecimal getTotalAftsDisc() {
        return totalAftsDisc;
    }

    public void setTotalAftsDisc(BigDecimal totalAftsDisc) {
        this.totalAftsDisc = totalAftsDisc;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public BigDecimal getTotalCreditAmt() {
        return totalCreditAmt;
    }

    public void setTotalCreditAmt(BigDecimal totalCreditAmt) {
        this.totalCreditAmt = totalCreditAmt;
    }

    public Long getTotalCreditCnt() {
        return totalCreditCnt;
    }

    public void setTotalCreditCnt(Long totalCreditCnt) {
        this.totalCreditCnt = totalCreditCnt;
    }

    public BigDecimal getTotalCancelAmt() {
        return totalCancelAmt;
    }

    public void setTotalCancelAmt(BigDecimal totalCancelAmt) {
        this.totalCancelAmt = totalCancelAmt;
    }

    public Long getTotalCancelCnt() {
        return totalCancelCnt;
    }

    public void setTotalCancelCnt(Long totalCancelCnt) {
        this.totalCancelCnt = totalCancelCnt;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public java.sql.Date getCreateDttm() {
        return createDttm;
    }

    public void setCreateDttm(java.sql.Date createDttm) {
        this.createDttm = createDttm;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public java.sql.Date getUpdateDttm() {
        return updateDttm;
    }

    public void setUpdateDttm(java.sql.Date updateDttm) {
        this.updateDttm = updateDttm;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getPosNo() {
        return posNo;
    }

    public void setPosNo(String posNo) {
        this.posNo = posNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getClosingDtStr() {
        return closingDtStr;
    }

    public void setClosingDtStr(String closingDtStr) {
        this.closingDtStr = closingDtStr;
    }

    public String getClosingTimeStr() {
        return closingTimeStr;
    }

    public void setClosingTimeStr(String closingTimeStr) {
        this.closingTimeStr = closingTimeStr;
    }

    public EmpClosing getEmpClosing() {
        return empClosing;
    }

    public void setEmpClosing(EmpClosing empClosing) {
        this.empClosing = empClosing;
    }

    public ShopClosing getShopClosing() {
        return shopClosing;
    }

    public void setShopClosing(ShopClosing shopClosing) {
        this.shopClosing = shopClosing;
    }


    public BigDecimal getExcCancelTotalAmount() {
        return excCancelTotalAmount;
    }

    public void setExcCancelTotalAmount(BigDecimal excCancelTotalAmount) {
        this.excCancelTotalAmount = excCancelTotalAmount;
    }

    public Long getExcCancelTotalCount() {
        return excCancelTotalCount;
    }

    public void setExcCancelTotalCount(Long excCancelTotalCount) {
        this.excCancelTotalCount = excCancelTotalCount;
    }

    public BigDecimal getReceiveWhFullAmount() {
        return receiveWhFullAmount;
    }

    public void setReceiveWhFullAmount(BigDecimal receiveWhFullAmount) {
        this.receiveWhFullAmount = receiveWhFullAmount;
    }

    public Long getReceiveWhFullCount() {
        return receiveWhFullCount;
    }

    public void setReceiveWhFullCount(Long receiveWhFullCount) {
        this.receiveWhFullCount = receiveWhFullCount;
    }

    public BigDecimal getReceiveWhAbvrAmount() {
        return receiveWhAbvrAmount;
    }

    public void setReceiveWhAbvrAmount(BigDecimal receiveWhAbvrAmount) {
        this.receiveWhAbvrAmount = receiveWhAbvrAmount;
    }

    public Long getReceiveWhAbvrCount() {
        return receiveWhAbvrCount;
    }

    public void setReceiveWhAbvrCount(Long receiveWhAbvrCount) {
        this.receiveWhAbvrCount = receiveWhAbvrCount;
    }

    public BigDecimal getReceiveOnlyAmount() {
        return receiveOnlyAmount;
    }

    public void setReceiveOnlyAmount(BigDecimal receiveOnlyAmount) {
        this.receiveOnlyAmount = receiveOnlyAmount;
    }

    public Long getReceiveOnlyCount() {
        return receiveOnlyCount;
    }

    public void setReceiveOnlyCount(Long receiveOnlyCount) {
        this.receiveOnlyCount = receiveOnlyCount;
    }

    public BigDecimal getCancelReceiveWhFullAmount() {
        return cancelReceiveWhFullAmount;
    }

    public void setCancelReceiveWhFullAmount(BigDecimal cancelReceiveWhFullAmount) {
        this.cancelReceiveWhFullAmount = cancelReceiveWhFullAmount;
    }

    public Long getCancelReceiveWhFullCount() {
        return cancelReceiveWhFullCount;
    }

    public void setCancelReceiveWhFullCount(Long cancelReceiveWhFullCount) {
        this.cancelReceiveWhFullCount = cancelReceiveWhFullCount;
    }

    public BigDecimal getCancelReceiveWhAbvrAmount() {
        return cancelReceiveWhAbvrAmount;
    }

    public void setCancelReceiveWhAbvrAmount(BigDecimal cancelReceiveWhAbvrAmount) {
        this.cancelReceiveWhAbvrAmount = cancelReceiveWhAbvrAmount;
    }

    public Long getCancelReceiveWhAbvrCount() {
        return cancelReceiveWhAbvrCount;
    }

    public void setCancelReceiveWhAbvrCount(Long cancelReceiveWhAbvrCount) {
        this.cancelReceiveWhAbvrCount = cancelReceiveWhAbvrCount;
    }

    public BigDecimal getCancelReceiveOnlyAmount() {
        return cancelReceiveOnlyAmount;
    }

    public void setCancelReceiveOnlyAmount(BigDecimal cancelReceiveOnlyAmount) {
        this.cancelReceiveOnlyAmount = cancelReceiveOnlyAmount;
    }

    public Long getCancelReceiveOnlyCount() {
        return cancelReceiveOnlyCount;
    }

    public void setCancelReceiveOnlyCount(Long cancelReceiveOnlyCount) {
        this.cancelReceiveOnlyCount = cancelReceiveOnlyCount;
    }

    public BigDecimal getBackDateTotalAmount() {
        return backDateTotalAmount;
    }

    public void setBackDateTotalAmount(BigDecimal backDateTotalAmount) {
        this.backDateTotalAmount = backDateTotalAmount;
    }

    public Long getBackDateTotalCount() {
        return backDateTotalCount;
    }

    public void setBackDateTotalCount(Long backDateTotalCount) {
        this.backDateTotalCount = backDateTotalCount;
    }

    public Long getEmpClosingId1() {
        return empClosingId1;
    }

    public void setEmpClosingId1(Long empClosingId1) {
        this.empClosingId1 = empClosingId1;
    }

    public Long getShopClosingId1() {
        return shopClosingId1;
    }

    public void setShopClosingId1(Long shopClosingId1) {
        this.shopClosingId1 = shopClosingId1;
    }

    public String getBackDateStatus() {
        return backDateStatus;
    }

    public void setBackDateStatus(String backDateStatus) {
        this.backDateStatus = backDateStatus;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getReceiptDtStr() {
        return receiptDtStr;
    }

    public void setReceiptDtStr(String receiptDtStr) {
        this.receiptDtStr = receiptDtStr;
    }

    public BigDecimal getWhOnlyFullAmount() {
        return whOnlyFullAmount;
    }

    public void setWhOnlyFullAmount(BigDecimal whOnlyFullAmount) {
        this.whOnlyFullAmount = whOnlyFullAmount;
    }

    public Long getWhOnlyFullCount() {
        return whOnlyFullCount;
    }

    public void setWhOnlyFullCount(Long whOnlyFullCount) {
        this.whOnlyFullCount = whOnlyFullCount;
    }

    public BigDecimal getWhOnlyAbvrAmount() {
        return whOnlyAbvrAmount;
    }

    public void setWhOnlyAbvrAmount(BigDecimal whOnlyAbvrAmount) {
        this.whOnlyAbvrAmount = whOnlyAbvrAmount;
    }

    public Long getWhOnlyAbvrCount() {
        return whOnlyAbvrCount;
    }

    public void setWhOnlyAbvrCount(Long whOnlyAbvrCount) {
        this.whOnlyAbvrCount = whOnlyAbvrCount;
    }

    public BigDecimal getCancelWhOnlyFullAmount() {
        return cancelWhOnlyFullAmount;
    }

    public void setCancelWhOnlyFullAmount(BigDecimal cancelWhOnlyFullAmount) {
        this.cancelWhOnlyFullAmount = cancelWhOnlyFullAmount;
    }

    public Long getCancelWhOnlyFullCount() {
        return cancelWhOnlyFullCount;
    }

    public void setCancelWhOnlyFullCount(Long cancelWhOnlyFullCount) {
        this.cancelWhOnlyFullCount = cancelWhOnlyFullCount;
    }

    public BigDecimal getCancelWhOnlyAbvrAmount() {
        return cancelWhOnlyAbvrAmount;
    }

    public void setCancelWhOnlyAbvrAmount(BigDecimal cancelWhOnlyAbvrAmount) {
        this.cancelWhOnlyAbvrAmount = cancelWhOnlyAbvrAmount;
    }

    public Long getCancelWhOnlyAbvrCount() {
        return cancelWhOnlyAbvrCount;
    }

    public void setCancelWhOnlyAbvrCount(Long cancelWhOnlyAbvrCount) {
        this.cancelWhOnlyAbvrCount = cancelWhOnlyAbvrCount;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public BigDecimal getTransGfmisTotalAmount() {
		return transGfmisTotalAmount;
	}

	public void setTransGfmisTotalAmount(BigDecimal transGfmisTotalAmount) {
		this.transGfmisTotalAmount = transGfmisTotalAmount;
	}

	public Long getTransGfmisTotalCount() {
		return transGfmisTotalCount;
	}

	public void setTransGfmisTotalCount(Long transGfmisTotalCount) {
		this.transGfmisTotalCount = transGfmisTotalCount;
	}

	public BigDecimal getTransForeignTotalAmount() {
		return transForeignTotalAmount;
	}

	public void setTransForeignTotalAmount(BigDecimal transForeignTotalAmount) {
		this.transForeignTotalAmount = transForeignTotalAmount;
	}

	public BigDecimal getTransForeignThTotalAmount() {
		return transForeignThTotalAmount;
	}

	public void setTransForeignThTotalAmount(BigDecimal transForeignThTotalAmount) {
		this.transForeignThTotalAmount = transForeignThTotalAmount;
	}

	public Long getTransForeignTotalCount() {
		return transForeignTotalCount;
	}

	public void setTransForeignTotalCount(Long transForeignTotalCount) {
		this.transForeignTotalCount = transForeignTotalCount;
	}

	public BigDecimal getForeignUsTotalAmount() {
		return foreignUsTotalAmount;
	}

	public void setForeignUsTotalAmount(BigDecimal foreignUsTotalAmount) {
		this.foreignUsTotalAmount = foreignUsTotalAmount;
	}

	public BigDecimal getForeignUsToThTotalAmount() {
		return foreignUsToThTotalAmount;
	}

	public void setForeignUsToThTotalAmount(BigDecimal foreignUsToThTotalAmount) {
		this.foreignUsToThTotalAmount = foreignUsToThTotalAmount;
	}

	public Long getForeignUsTotalCount() {
		return foreignUsTotalCount;
	}

	public void setForeignUsTotalCount(Long foreignUsTotalCount) {
		this.foreignUsTotalCount = foreignUsTotalCount;
	}

	public BigDecimal getForeignEuTotalAmount() {
		return foreignEuTotalAmount;
	}

	public void setForeignEuTotalAmount(BigDecimal foreignEuTotalAmount) {
		this.foreignEuTotalAmount = foreignEuTotalAmount;
	}

	public BigDecimal getForeignEuToThTotalAmount() {
		return foreignEuToThTotalAmount;
	}

	public void setForeignEuToThTotalAmount(BigDecimal foreignEuToThTotalAmount) {
		this.foreignEuToThTotalAmount = foreignEuToThTotalAmount;
	}

	public Long getForeignEuTotalCount() {
		return foreignEuTotalCount;
	}

	public void setForeignEuTotalCount(Long foreignEuTotalCount) {
		this.foreignEuTotalCount = foreignEuTotalCount;
	}

	public BigDecimal getAgentTotalAmount() {
		return agentTotalAmount;
	}

	public void setAgentTotalAmount(BigDecimal agentTotalAmount) {
		this.agentTotalAmount = agentTotalAmount;
	}

	public BigDecimal getAgentReceiveFullAgentCount() {
		return agentReceiveFullAgentCount;
	}

	public void setAgentReceiveFullAgentCount(BigDecimal agentReceiveFullAgentCount) {
		this.agentReceiveFullAgentCount = agentReceiveFullAgentCount;
	}

	public BigDecimal getAgentReceiveAbvrCount() {
		return agentReceiveAbvrCount;
	}

	public void setAgentReceiveAbvrCount(BigDecimal agentReceiveAbvrCount) {
		this.agentReceiveAbvrCount = agentReceiveAbvrCount;
	}

	public String getShopClosingDtStr() {
		return shopClosingDtStr;
	}

	public void setShopClosingDtStr(String shopClosingDtStr) {
		this.shopClosingDtStr = shopClosingDtStr;
	}

	public BigDecimal getTotalSumByEmp() {
		return totalSumByEmp;
	}

	public void setTotalSumByEmp(BigDecimal totalSumByEmp) {
		this.totalSumByEmp = totalSumByEmp;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Long getRefClosingId() {
		return refClosingId;
	}

	public void setRefClosingId(Long refClosingId) {
		this.refClosingId = refClosingId;
	}
    
}
