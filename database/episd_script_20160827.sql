/*==============================================================*/
/* DBMS name:      EPISDB Version 1.0                           */
/* Created on:     6/4/2559 0:19:05                             */
/*==============================================================*/

alter table ARCGRANT drop constraint FK_OPEGRA_OPERATIONID;
alter table ARCGRANT drop constraint FK_PRIGRA_PRINCIPALID;
alter table ARCUSERAUTHNTICN drop constraint fk_offuse_officerid;
alter table MASOFFICER drop constraint FK_SHOOFF_SHOPID;
alter table MASOFFICER drop constraint FK_PRIOFF_PRINCIPALID;
alter table CORREDUCEDEBT drop constraint FK_PAYRED_PAYMENTID;
alter table MASSHOP drop constraint FK_SHOGL_GLID;
alter table TMPACCOUNT drop constraint FK_PAYACC_PAYMENTID;
alter table TMPINVOICE drop constraint FK_ACCINV_ACCOUNTID;
alter table TMPINVOICE drop constraint FK_PAYINV_PAYMENTID;
alter table MASPOS drop constraint FK_SHOPOS_SHOPID;
alter table TMPINVOICESERVICE drop constraint FK_RECINV_RECEIPTID;
alter table TRSBILLOFEXCHANGEREF drop constraint FK_PAYBIL_PAYMENTREFID;
alter table TRSCHEQUEREF drop constraint FK_CHEPAY_PAYMENTREFID;
alter table TRSCREDITNOTEREF drop constraint FK_PAYNOT_PAYMENTREFID;
alter table TRSCREDITREF drop constraint FK_PAYCRE_PAYMENTREFID;
alter table TRSMETHOD drop constraint FK_PAYMET_PAYMENTID;
alter table TRSMONEYORDERREF drop constraint FK_PAYMON_PAYMENTREFID;
alter table TRSMONEYTRANSFERREF drop constraint FK_MONPAY_PAYMENTREFID;
alter table TRSOFFICERLOGIN drop constraint FK_OFFLOG_OFFICERID;
alter table TRSOTHERREF drop constraint FK_PAYOTH_PAYMENTREFID;
alter table TRSPAYMENTREF drop constraint FK_PAYPAY_PAYMENTID;
alter table TRSRECEIPTDETAIL drop constraint FK_RECREC_RECEIPTID;
alter table TRSREDUCEDEBTREF drop constraint FK_PAYRED_PAYMENTREFID;
alter table TRSREDUCEDEBTREF drop constraint FK_REDRED_REDUCEDEBTID;
alter table TRSREPRINT drop constraint FK_RECREP_RECEIPTID;
alter table TRSDEDUCTION drop constraint FK_PAYDED_PAYMENTID;
alter table ACCOUNTTBOSS drop constraint FK_ACCTTBOSS_CONTRNO;
alter table TRSACCOUNTTBOSS drop constraint FK_TRSACCTTBOSS_ACCOUNTTBOSSID;
alter table TRSACCOUNTTBOSS drop constraint FK_TRSACCTTBOSS_PAYMENTID;

drop table TRSERPPAYNOSERVICE;
drop table TRSERPPAYSERVICE;
drop table TRSBILLOFEXCHANGEREF;
drop table TRSCHEQUEREF;
drop table TRSCREDITNOTEREF;
drop table TRSCREDITREF;
drop table TRSDISCOUNTREF;
drop table TRSMETHOD;
drop table TRSMONEYORDERREF;
drop table TRSMONEYTRANSFERREF;
drop table TRSOFFICERLOGIN;
drop table TRSOTHERREF;
drop table TRSRECEIPTDETAIL;
drop table TRSREDUCEDEBTREF;
drop table TRSREPRINT;
drop table TRSDEDUCTION;
drop table CORREDUCEDEBT;
drop table CORREFUND;
drop table CORRECEIPT;
drop table CORRECEIPTDOCUMENT;
drop table TRSPAYMENTREF;
drop table CORPAYMENTMNP;
drop table CORPAYMENTTOPUP;
drop table CORPAYMENT;
drop table ARCENUMS;
drop table ARCGRANT;
drop table ARCMESSAGE;
drop table TRSPAYMENTSUMMARY;
drop table CORSESSION;
drop table COROFFICERPOS;
drop table MASOFFICER;
drop table MASPOS;
drop table MASSHOP;
drop table MASGL;
drop table TMPACCOUNT;
drop table TMPINVOICE;
drop table TMPINVOICESERVICE;
drop table ARCOPERATION;
drop table ARCUSERAUTHNTICN;
drop table ARCPRINCIPAL;
drop table INV_SUMMARY_SAP_IBACSS;
drop table INV_SUMMARY_SAP_TBOSS;
drop table INV_SUMMARY_SAP_OTBOSS;
DROP TABLE CONTRACTTBOSS;
DROP TABLE ACCOUNTTBOSS;
DROP TABLE TRSACCOUNTTBOSS;
DROP TABLE TAXTYPE;

DROP SEQUENCE shop_seq;
DROP SEQUENCE pos_seq;
DROP SEQUENCE officer_seq;
DROP SEQUENCE principal_seq;
DROP SEQUENCE authen_seq;
DROP SEQUENCE enum_seq;
DROP SEQUENCE payment_seq;
DROP SEQUENCE transaction_seq;
DROP SEQUENCE receipt_seq;
DROP SEQUENCE invoice_seq;
DROP SEQUENCE service_seq;
DROP SEQUENCE account_seq;
DROP SEQUENCE method_seq;
DROP SEQUENCE billxchg_seq;
DROP SEQUENCE other_seq;
DROP SEQUENCE withholding_seq;
DROP SEQUENCE reducedebt_seq;
DROP SEQUENCE mnytxnf_seq;
DROP SEQUENCE mnyorder_seq;
DROP SEQUENCE deduct_seq;
DROP SEQUENCE creditnote_seq;
DROP SEQUENCE creditcard_seq;
DROP SEQUENCE cheque_seq;
DROP SEQUENCE erp_payservice_seq;
DROP SEQUENCE erp_paynoservice_seq;
DROP SEQUENCE billinvoice_seq;
DROP SEQUENCE billinvoiceproduct_seq;
DROP SEQUENCE session_seq;
DROP SEQUENCE paymentsummary_seq;
DROP SEQUENCE receiptdocument_seq;
DROP SEQUENCE contracttboss_seq;
DROP SEQUENCE accounttboss_seq;
DROP SEQUENCE trsaccounttboss_seq;

CREATE SEQUENCE shop_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE pos_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE principal_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE officer_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE authen_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE enum_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE payment_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE transaction_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE receipt_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE invoice_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE service_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE account_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE method_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE billxchg_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE other_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE withholding_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE reducedebt_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE mnytxnf_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE mnyorder_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE deduct_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE creditnote_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE creditcard_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE cheque_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE erp_payservice_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE erp_paynoservice_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE billinvoice_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE billinvoiceproduct_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE session_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE paymentsummary_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE receiptdocument_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE contracttboss_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE accounttboss_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE trsaccounttboss_seq MINVALUE 1 START WITH 1 INCREMENT BY 1;
commit;

/*==============================================================*/
/* Table: ARCENUMS                                              */
/*==============================================================*/
create table ARCENUMS (
	ENUMID               INTEGER NOT NULL,
	CATEGORY             VARCHAR(40),
	MESSAGECODE          VARCHAR(40),
	MAPCODE1             VARCHAR(40),
	MAPCODE2             VARCHAR(40),
	MAPCODE3             VARCHAR(40),
	MAPCODE4             VARCHAR(40),
	DESCFULLEN           VARCHAR(1000),
	DESCFULLTH           VARCHAR(1000),
	DESCABVREN           VARCHAR(100),
	DESCABVRTH           VARCHAR(100),
	UPDATEDTTM           TIMESTAMP,
	UPDATESYSTEM         CHAR(3),
	UPDATEUSER           VARCHAR(32),
	VERSIONSTAMP         INTEGER,
	constraint PK_ARCENUMS primary key (ENUMID)
);

INSERT INTO ARCENUMS VALUES(1, 'branch.central', '00000', '1000', null, null, null, null, 'สำนักงานใหญ่', null, '', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(2, 'branch.central', '00000', null, '1J60200', null, null, null, 'ฝ่ายขายและตลาดสื่อสารไร้สาย', null, 'ตร.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(3, 'branch.central', '00000', '1011', '1J60203', null, null, null, 'สำนักงานใหญ่ ฝ่ายขายและตลาดสื่อสารไร้สาย (ส่วนขาย)', null, 'สต. (ขส)', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(4, 'branch.central', '00000', '1012', '1J60205', null, null, null, 'สำนักงานใหญ่ ฝ่ายขายและตลาดสื่อสารไร้สาย (ส่วนบริหารคลังสินค้า)', null, 'สต. (คส)', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(5, 'branch.central', '00000', null, '1J60100', null, null, null, 'ฝ่ายพัฒนาผลิตภัณฑ์โทรศัพท์', null, 'ทต.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(6, 'branch.central', '00000', '1013', '1J60101', null, null, null, 'สำนักงานใหญ่ ฝ่ายพัฒนาผลิตภัณฑ์โทรศัพท์ (ส่วนการตลาดบริการโทรศัพท์)', null, 'ทต. (ตท)', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(7, 'branch.central', '00000', '1014', '1J60105', null, null, null, 'สำนักงานใหญ่ ฝ่ายพัฒนาผลิตภัณฑ์โทรศัพท์ (ส่วนวิเคราะห์ข้อมูลลูกค้าและบริหารคลังสินค้า)', null, 'ทต. (ลท)', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(8, 'branch.central', '00000', '1015', '1J60500', null, null, null, 'สำนักงานใหญ่ ฝ่ายเทคโนโลยีศูนย์ข้อมูล (ส่วนสนับสนุนการตลาด)', null, 'ศต.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(9, 'branch.central', '00000', null, '1J00200', null, null, null, 'ฝ่ายบริหารลูกค้า', null, 'คต.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(10, 'business.place', '01230', '1100', null, null, null, null, 'สำนักงานบริการลูกค้า กสท เขตกลาง', null, 'สข. (ก)', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(11, 'business.place', '01130', '1101', '1J20220', null, null, null, 'สำนักงานบริการลูกค้า กสท พระนครศรีอยุธยา', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(12, 'business.place', '01134', '1102', '1J20233', null, null, null, 'สำนักงานบริการลูกค้า กสท เพชรบูรณ์', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(13, 'business.place', '01136', '1103', '1J20222', null, null, null, 'สำนักงานบริการลูกค้า กสท ลพบุรี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(14, 'business.place', '01141', '1104', '1J20224', null, null, null, 'สำนักงานบริการลูกค้า กสท สิงห์บุรี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(15, 'business.place', '01142', '1105', '1J20231', null, null, null, 'สำนักงานบริการลูกค้า กสท พิจิตร', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(16, 'business.place', '01143', '1106', '1J20225', null, null, null, 'สำนักงานบริการลูกค้า กสท ชัยนาท', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(17, 'business.place', '01145', '1107', '1J20230', null, null, null, 'สำนักงานบริการลูกค้า กสท กำแพงเพชร', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(18, 'business.place', '01147', '1108', '1J20221', null, null, null, 'สำนักงานบริการลูกค้า กสท อ่างทอง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(19, 'business.place', '01148', '1109', '1J20229', null, null, null, 'สำนักงานบริการลูกค้า กสท อุทัยธานี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(20, 'business.place', '01149', '1110', '1J20235', null, null, null, 'สำนักงานบริการลูกค้า กสท หนองไผ่', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(21, 'business.place', '01150', '1111', '1J20232', null, null, null, 'สำนักงานบริการลูกค้า กสท ตะพานหิน', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(22, 'business.place', '01152', '1112', '1J20228', null, null, null, 'สำนักงานบริการลูกค้า กสท ตาคลี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(23, 'business.place', '01221', '1113', '1J20227', null, null, null, 'สำนักงานบริการลูกค้า กสท นครสวรรค์', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(24, 'business.place', '01222', '1114', '1J20226', null, null, null, 'สำนักงานบริการลูกค้า กสท สระบุรี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(25, 'business.place', '01316', '1115', '1J20234', null, null, null, 'สำนักงานบริการลูกค้า กสท หล่มสัก', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(26, 'business.place', '01338', '1116', '1J20223', null, null, null, 'สำนักงานบริการลูกค้า กสท ชัยบาดาล', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(27, 'business.place', '01375', '1117', '1J20227', null, null, null, 'สำนักงานบริการลูกค้า กสท แคท ซีดีแอมเอ นครสวรรค์', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(28, 'business.place', '01228', '1200', null, null, null, null, 'สำนักงานบริการลูกค้า กสท เขตตะวันออก', null, 'สข. (อ)', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(29, 'business.place', '01189', '1201', '1J30220', null, null, null, 'สำนักงานบริการลูกค้า กสท ชลบุรี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(30, 'business.place', '01190', '1202', '1J30228', null, null, null, 'สำนักงานบริการลูกค้า กสท ตราด', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(31, 'business.place', '01191', '1203', '1J30224', null, null, null, 'สำนักงานบริการลูกค้า กสท ระยอง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(32, 'business.place', '01192', '1204', '1J30230', null, null, null, 'สำนักงานบริการลูกค้า กสท ปราจีนบุรี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(33, 'business.place', '01193', '1205', '1J30229', null, null, null, 'สำนักงานบริการลูกค้า กสท ฉะเชิงเทรา', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(34, 'business.place', '01194', '1206', '1J30232', null, null, null, 'สำนักงานบริการลูกค้า กสท นครนายก', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(35, 'business.place', '01195', '1207', '1J30221', null, null, null, 'สำนักงานบริการลูกค้า กสท สัตหีบ', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(36, 'business.place', '01196', '1208', '1J30234', null, null, null, 'สำนักงานบริการลูกค้า กสท อรัญประเทศ', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(37, 'business.place', '01197', '1209', '1J30231', null, null, null, 'สำนักงานบริการลูกค้า กสท กบินทร์บุรี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(38, 'business.place', '01215', '1210', '1J30223', null, null, null, 'สำนักงานบริการลูกค้า กสท พัทยา', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(39, 'business.place', '01232', '1211', '1J30227', null, null, null, 'สำนักงานบริการลูกค้า กสท จันทบุรี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(40, 'business.place', '01237', '1212', '1J30222', null, null, null, 'สำนักงานบริการลูกค้า กสท แหลมฉบัง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(41, 'business.place', '01246', '1213', '1J30226', null, null, null, 'สำนักงานบริการลูกค้า กสท มาบตาพุด', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(42, 'business.place', '01278', '1214', '1J30233', null, null, null, 'สำนักงานบริการลูกค้า กสท สระแก้ว', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(43, 'business.place', '01381', '1216', '1J30225', null, null, null, 'สำนักงานบริการลูกค้า กสท ปลวกแดง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(44, 'business.place', '01231', '1300', null, null, null, null, 'สำนักงานบริการลูกค้า กสท เขตตะวันออกเฉลียงเหนือ', null, 'สข.  (อน)', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(45, 'business.place', '01170', '1301', '1J30133', null, null, null, 'สำนักงานบริการลูกค้า กสท อุดรธานี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(46, 'business.place', '01171', '1302', '1J30123', null, null, null, 'สำนักงานบริการลูกค้า กสท สุรินทร์', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(47, 'business.place', '01172', '1303', '1J30140', null, null, null, 'สำนักงานบริการลูกค้า กสท สกลนคร', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(48, 'business.place', '01173', '1304', '1J30125', null, null, null, 'สำนักงานบริการลูกค้า กสท อุบลราชธานี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(49, 'business.place', '01174', '1305', '1J30142', null, null, null, 'สำนักงานบริการลูกค้า กสท มุกดาหาร', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(50, 'business.place', '01175', '1306', '1J30124', null, null, null, 'สำนักงานบริการลูกค้า กสท ศรีสะเกษ', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(51, 'business.place', '01176', '1307', '1J30138', null, null, null, 'สำนักงานบริการลูกค้า กสท ร้อยเอ็ด', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(52, 'business.place', '01177', '1308', '1J30135', null, null, null, 'สำนักงานบริการลูกค้า กสท หนองคาย', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(53, 'business.place', '01178', '1309', '1J30122', null, null, null, 'สำนักงานบริการลูกค้า กสท บุรีรัมย์', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(54, 'business.place', '01179', '1310', '1J30134', null, null, null, 'สำนักงานบริการลูกค้า กสท เลย', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(55, 'business.place', '01180', '1311', '1J30127', null, null, null, 'สำนักงานบริการลูกค้า กสท ชัยภูมิ', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(56, 'business.place', '01181', '1312', '1J30137', null, null, null, 'สำนักงานบริการลูกค้า กสท มหาสารคาม', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(57, 'business.place', '01182', '1313', '1J30139', null, null, null, 'สำนักงานบริการลูกค้า กสท กาฬสินธุ์', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(58, 'business.place', '01183', '1314', '1J30126', null, null, null, 'สำนักงานบริการลูกค้า กสท ยโสธร', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(59, 'business.place', '01184', '1315', '1J30141', null, null, null, 'สำนักงานบริการลูกค้า กสท นครพนม', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(60, 'business.place', '01185', '1316', '1J30121', null, null, null, 'สำนักงานบริการลูกค้า กสท ปากช่อง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(61, 'business.place', '01186', '1317', '1J30132', null, null, null, 'สำนักงานบริการลูกค้า กสท ชุมแพ', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(62, 'business.place', '01187', '1318', '1J30128', null, null, null, 'สำนักงานบริการลูกค้า กสท อำนาจเจริญ', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(63, 'business.place', '01188', '1319', '1J30131', null, null, null, 'สำนักงานบริการลูกค้า กสท บ้านไผ่', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(64, 'business.place', '01223', '1320', '1J30120', null, null, null, 'สำนักงานบริการลูกค้า กสท นครราชสีมา', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(65, 'business.place', '01224', '1321', '1J30130', null, null, null, 'สำนักงานบริการลูกค้า กสท ขอนแก่น', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(66, 'business.place', '01276', '1322', '1J30136', null, null, null, 'สำนักงานบริการลูกค้า กสท บึงกาฬ', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(67, 'business.place', '01323', '1323', '1J30129', null, null, null, 'สำนักงานบริการลูกค้า กสท หนองบัวลำภู', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(68, 'business.place', '01360', '1324', '1J30135', null, null, null, 'สำนักงานบริการลูกค้า กสท หนองคาย', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(69, 'business.place', '01361', '1325', '1J30130', null, null, null, 'สำนักงานบริการลูกค้า กสท ขอนแก่น', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(70, 'business.place', '01373', '1327', '1J30130', null, null, null, 'สำนักงานบริการลูกค้า กสท แคท ซีดีเอ็มเอ ขอนแก่น', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(71, 'business.place', '01383', '1328', '1J30133', null, null, null, 'สำนักงานบริการลูกค้า กสท อุดรธานี (เซ็นทรัล)', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(72, 'business.place', '01390', '1329', '1J30125', null, null, null, 'สำนักงานบริการลูกค้า กสท อุบลราชธานี (เซ็นทรัล)', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(73, 'business.place', '01227', '1400', null, null, null, null, 'สำนักงานบริการลูกค้า กสท เขตเหนือ', null, 'สข.  (น)', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(74, 'business.place', '01154', '1401', '1J20129', null, null, null, 'สำนักงานบริการลูกค้า กสท เชียงราย', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(75, 'business.place', '01155', '1402', '1J20126', null, null, null, 'สำนักงานบริการลูกค้า กสท แพร่', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(76, 'business.place', '01156', '1403', '1J20127', null, null, null, 'สำนักงานบริการลูกค้า กสท น่าน', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(77, 'business.place', '01157', '1404', '1J20136', null, null, null, 'สำนักงานบริการลูกค้า กสท สุโขทัย', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(78, 'business.place', '01158', '1405', '1J20135', null, null, null, 'สำนักงานบริการลูกค้า กสท แม่สอด', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(79, 'business.place', '01159', '1406', '1J20125', null, null, null, 'สำนักงานบริการลูกค้า กสท อุตรดิตถ์', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(80, 'business.place', '01160', '1407', '1J20134', null, null, null, 'สำนักงานบริการลูกค้า กสท ตาก', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(81, 'business.place', '01161', '1408', '1J20128', null, null, null, 'สำนักงานบริการลูกค้า กสท พะเยา', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(82, 'business.place', '01162', '1409', '1J20122', null, null, null, 'สำนักงานบริการลูกค้า กสท ลำพูน', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(83, 'business.place', '01162', '1410', '1J20137', null, null, null, 'สำนักงานบริการลูกค้า กสท สวรรคโลก', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(84, 'business.place', '01164', '1411', '1J20130', null, null, null, 'สำนักงานบริการลูกค้า กสท แม่สาย', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(85, 'business.place', '01166', '1412', '1J20121', null, null, null, 'สำนักงานบริการลูกค้า กสท ฝาง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(86, 'business.place', '01167', '1413', '1J20132', null, null, null, 'สำนักงานบริการลูกค้า กสท แม่สะเรียง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(87, 'business.place', '01168', '1414', '1J20124', null, null, null, 'สำนักงานบริการลูกค้า กสท เถิน', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(88, 'business.place', '01169', '1415', '1J20131', null, null, null, 'สำนักงานบริการลูกค้า กสท แม่ฮ่องสอน', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(89, 'business.place', '01216', '1416', '1J20120', null, null, null, 'สำนักงานบริการลูกค้า กสท เชียงใหม่', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(90, 'business.place', '01217', '1417', '1J20123', null, null, null, 'สำนักงานบริการลูกค้า กสท ลำปาง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(91, 'business.place', '01218', '1418', '1J20138', null, null, null, 'สำนักงานบริการลูกค้า กสท พิษณุโลก', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(92, 'business.place', '01371', '1419', '1J20120', null, null, null, 'สำนักงานบริการลูกค้า กสท แคท ซีดีเอ็มเอ เชียงใหม่', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(93, 'business.place', '01377', '1422', '1J20133', null, null, null, 'สำนักงานบริการลูกค้า กสท ปาย', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(94, 'business.place', '01380', '1424', '1J20129', null, null, null, 'สำนักงานบริการลูกค้า กสท เชียงราย (เซ็นทรัล)', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(95, 'business.place', '01382', '1425', '1J20138', null, null, null, 'สำนักงานบริการลูกค้า กสท พิษณุโลก (เซ็นทรัล)', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(96, 'business.place', '01385', '1426', '1J20123', null, null, null, 'สำนักงานบริการลูกค้า กสท ลำปาง (เซ็นทรัล)', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(97, 'business.place', '01387', '1427', '1J20120', null, null, null, 'สำนักงานบริการลูกค้า กสท เชียงใหม่ (เม-ญ่า เชียงใหม่)', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(98, 'business.place', '01229', '1500', null, null, null, null, 'สำนักงานบริการลูกค้า กสท เขตใต้', null, 'สข.  (ต)', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(99, 'business.place', '01198', '1501', '1J10120', null, null, null, 'สำนักงานบริการลูกค้า กสท นครศรีธรรมราช', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(100, 'business.place', '01199', '1502', '1J10135', null, null, null, 'สำนักงานบริการลูกค้า กสท ยะลา', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(101, 'business.place', '01200', '1503', '1J10122', null, null, null, 'สำนักงานบริการลูกค้า กสท กระบี่', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(102, 'business.place', '01201', '1504', '1J10121', null, null, null, 'สำนักงานบริการลูกค้า กสท ทุ่งสง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(103, 'business.place', '01202', '1505', '1J10137', null, null, null, 'สำนักงานบริการลูกค้า กสท นราธิวาส', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(104, 'business.place', '01203', '1506', '1J10132', null, null, null, 'สำนักงานบริการลูกค้า กสท ตรัง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(105, 'business.place', '01205', '1507', '1J10128', null, null, null, 'สำนักงานบริการลูกค้า กสท เกาะสมุย', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(106, 'business.place', '01206', '1508', '1J10124', null, null, null, 'สำนักงานบริการลูกค้า กสท ตะกั่วป่า', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(107, 'business.place', '01208', '1509', '1J10131', null, null, null, 'สำนักงานบริการลูกค้า กสท สตูล', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(108, 'business.place', '01209', '1510', '1J10134', null, null, null, 'สำนักงานบริการลูกค้า กสท ปัตตานี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(109, 'business.place', '01211', '1511', '1J10133', null, null, null, 'สำนักงานบริการลูกค้า กสท พัทลุง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(110, 'business.place', '01213', '1512', '1J10136', null, null, null, 'สำนักงานบริการลูกค้า กสท เบตง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(111, 'business.place', '01214', '1513', '1J10123', null, null, null, 'สำนักงานบริการลูกค้า กสท พังงา', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(112, 'business.place', '01219', '1514', '1J10130', null, null, null, 'สำนักงานบริการลูกค้า กสท หาดใหญ่', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(113, 'business.place', '01220', '1515', '1J10125', null, null, null, 'สำนักงานบริการลูกค้า กสท ภูเก็ต', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(114, 'business.place', '01225', '1516', '1J10127', null, null, null, 'สำนักงานบริการลูกค้า กสท สุราษฎร์ธานี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(115, 'business.place', '01252', '1517', '1J10126', null, null, null, 'สำนักงานบริการลูกค้า กสท ป่าตอง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(116, 'business.place', '01328', '1518', '1J10129', null, null, null, 'สำนักงานบริการลูกค้า กสท สงขลา', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(117, 'business.place', '01366', '1519', '1J10125', null, null, null, 'สำนักงานบริการลูกค้า กสท แคท ซีดีเอ็มเอ ภูเก็ต', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(118, 'business.place', '01376', '1520', '1J10130', null, null, null, 'สำนักงานบริการลูกค้า กสท CAT Shop หาดใหญ่', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(119, 'business.place', '01386', '1521', '1J10127', null, null, null, 'สำนักงานบริการลูกค้า กสท สุราษฎร์ธานี (เซ็นทรัล)', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(120, 'business.place', '01264', '1600', null, null, null, null, 'สำนักงานบริการลูกค้า กสท เขตตะวันตก', null, 'สข.  (ตต)', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(121, 'business.place', '01131', '1601', '1J10224', null, null, null, 'สำนักงานบริการลูกค้า กสท นครปฐม', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(122, 'business.place', '01132', '1602', '1J10230', null, null, null, 'สำนักงานบริการลูกค้า กสท ประจวบคีรีขันธ์', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(123, 'business.place', '01133', '1603', '1J10231', null, null, null, 'สำนักงานบริการลูกค้า กสท หัวหิน', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(124, 'business.place', '01135', '1604', '1J10221', null, null, null, 'สำนักงานบริการลูกค้า กสท บ้านโป่ง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(125, 'business.place', '01137', '1605', '1J10225', null, null, null, 'สำนักงานบริการลูกค้า กสท สมุทรสาคร', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(126, 'business.place', '01138', '1606', '1J10223', null, null, null, 'สำนักงานบริการลูกค้า กสท สุพรรณบุรี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(127, 'business.place', '01139', '1607', '1J10222', null, null, null, 'สำนักงานบริการลูกค้า กสท กาญจนบุรี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(128, 'business.place', '01140', '1608', '1J10220', null, null, null, 'สำนักงานบริการลูกค้า กสท ราชบุรี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(129, 'business.place', '01144', '1609', '1J10228', null, null, null, 'สำนักงานบริการลูกค้า กสท เพชรบุรี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(130, 'business.place', '01146', '1610', '1J10227', null, null, null, 'สำนักงานบริการลูกค้า กสท สมุทรสงคราม', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(131, 'business.place', '01204', '1611', '1J10232', null, null, null, 'สำนักงานบริการลูกค้า กสท ระนอง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(132, 'business.place', '01207', '1612', '1J10233', null, null, null, 'สำนักงานบริการลูกค้า กสท ชุมพร', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(133, 'business.place', '01317', '1613', '1J10226', null, null, null, 'สำนักงานบริการลูกค้า กสท กระทุ่มแบน', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(134, 'business.place', '01318', '1614', '1J10229', null, null, null, 'สำนักงานบริการลูกค้า กสท ชะอำ', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(135, 'business.place', '00000', '1700', '1J60400', null, null, null, 'ฝ่ายพัฒนาธุรกิจการตลาดลูกค้ารายย่อย', null, 'ยต.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(136, 'business.place', '01330', '1701', '1J60409', null, null, null, 'ศูนย์บริการลูกค้า บางรัก (อาคาร 16)', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(137, 'business.place', '01333', '1702', '1J60410', null, null, null, 'ศูนย์บริการลูกค้า สามเสนใน', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(138, 'business.place', '01334', '1703', '1J60411', null, null, null, 'ศูนย์บริการลูกค้า มีนบุรี', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(139, 'business.place', '01335', '1704', '1J60414', null, null, null, 'ศูนย์บริการลูกค้า นนทบุรี', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(140, 'business.place', '01336', '1705', '1J60409', null, null, null, 'ศูนย์บริการลูกค้า ราชดำเนิน', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(141, 'business.place', '01337', '1706', '1J60412', null, null, null, 'ศูนย์บริการลูกค้า สมุทรปราการ', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(142, 'business.place', '01343', '1707', '1J60413', null, null, null, 'ศูนย์บริการลูกค้า เซ็นทรัลพลาซ่า พระราม 2', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(143, 'business.place', '01344', '1708', '1J60412', null, null, null, 'ศูนย์บริการลูกค้า สุขุมวิท', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(144, 'business.place', '01345', '1709', '1J60411', null, null, null, 'ศูนย์บริการลูกค้า เอ็นมาร์คพลาซ่า', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(145, 'business.place', '01346', '1710', '1J60411', null, null, null, 'ศูนย์บริการลูกค้า เดอะมอลล์รามคำแหง', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(146, 'business.place', '01347', '1711', '1J60413', null, null, null, 'ศูนย์บริการลูกค้า เดอะมอลล์บางแค', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(147, 'business.place', '01348', '1712', '1J60409', null, null, null, 'ศูนย์บริการลูกค้า เดอะมอลล์ท่าพระ', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(148, 'business.place', '01350', '1713', '1J60414', null, null, null, 'ศูนย์บริการลูกค้า คลองหลวง', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(149, 'business.place', '01351', '1714', '1J60409', null, null, null, 'ศูนย์บริการลูกค้า ยานนาวา', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(150, 'business.place', '01370', '1715', '1J60415', null, null, null, 'CAT TELECOM INTERNATIONAL TRAN', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(151, 'business.place', '01379', '1716', '1J60410', null, null, null, 'ศูนย์บริการลูกค้า จัตุรัสจามจุรี', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(152, 'business.place', '00000', '1717', '1J60410', null, null, null, 'ศูนย์บริการลูกค้า แจ้งวัฒนะ', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(153, 'business.place', '01349', '1719', '1J60409', null, null, null, 'ศูนย์บริการลูกค้า อาคาร CAT TOWER', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(154, 'business.place', '01384', '1720', null, null, null, null, 'ศูนย์บริการลูกค้า อื้อจือเหลียง', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(155, 'business.place', '01388', '1721', '1J60413', null, null, null, 'ศูนย์บริการลูกค้า ธนบุรี', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(156, 'business.place', '01389', '1722', '1J60412', null, null, null, 'ศูนย์บริการลูกค้า เมกา บางนา', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(157, 'payothers.service.category', 'POSC0001', '001', null, null, null, null, 'ค่าถ่ายเอกสาร', null, null, null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(158, 'payothers.service.category', 'POSC0002', '002', null, null, null, null, 'ค่าเช่า', null, null, null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(159, 'payothers.service.category', 'POSC0003', '003', null, null, null, null, 'ค่าใช้', null, null, null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(160, 'payothers.service.category', 'POSC0004', '004', null, null, null, null, 'ค่าขายแบบ', null, null, null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(161, 'payothers.service.category', 'POSC0005', '005', null, null, null, null, 'รายได้อื่นๆ', null, null, null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(162, 'payothers.service.name', 'POSN0001', '41101037', '40101101', '101010017', '01', null, 'รายได้ค่าใช้บริการโทรศัพท์ระหว่างประเทศ-โทรสารสาธารณะ', null, null, null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(163, 'payothers.service.name', 'POSN0002', '41107033', '40101101', '101010001', '06', null, 'รายได้ค่าใช้บริการ ISDN รปท.-ประชุมทางจอภาพ', null, null, null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(164, 'payothers.service.name', 'POSN0003', '42101061', '40401101', '104049901', '01', null, 'รายได้ค่าติดตั้ง/ซ่อมเครื่องฯวิทยุฯความถี่สูง(HF)', null, null, null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(165, 'payothers.service.name', 'POSN0004', '42101101', '40401101', '104049901', '01', null, 'รายได้ค่าเช่าเครื่องและอุปกรณ์  -  HF', null, null, null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(166, 'payothers.service.name', 'POSN0005', '43111012', '40201101', '102020001', '02', null, 'รายได้ค่าใช้บริการพิมพ์เอกสาร', null, null, null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(167, 'payothers.service.name', 'POSN0006', '61019999', '40601101', '181050003', '12', null, 'รายได้บริการอื่น ๆ', null, null, null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(168, 'vat.type', 'VTTY0001', 'V072', '7.00', null, null, 'vat 7%', 'ภาษี 7%', '7%', '7%', null, 'SYS', null, 1);

/*==============================================================*/
/* Table: ARCGRANT                                              */
/*==============================================================*/
create table ARCGRANT 
(
   GRANTID              INTEGER              not null,
   OPERATIONID          INTEGER              not null,
   PRINCIPALID          INTEGER,
   ISPOSITIVE           INTEGER,
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_ARCGRANT primary key (GRANTID)
);

/*==============================================================*/
/* Table: ARCMESSAGE                                            */
/*==============================================================*/
create table ARCMESSAGE 
(
   MESSAGEID            INTEGER              not null,
   CODE                 VARCHAR(13),
   MESSAGE              VARCHAR(120),
   DESCRIPTION          VARCHAR(120),
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_ARCMESSAGE primary key (MESSAGEID)
);

/*==============================================================*/
/* Table: ARCOPERATION                                          */
/*==============================================================*/
create table ARCOPERATION 
(
   OPERATIONID          INTEGER              not null,
   NAME                 VARCHAR(64),
   DESCRIPTION          VARCHAR(200),
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_ARCOPERATION primary key (OPERATIONID)
);

/*==============================================================*/
/* Table: ARCPRINCIPAL                                          */
/*==============================================================*/
create table ARCPRINCIPAL 
(
   PRINCIPALID          INTEGER not null,
   NAME                 VARCHAR(64) not null,
   DESCRIPTION          VARCHAR(200),
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_ARCPRINCIPAL primary key (PRINCIPALID)
);

/*==============================================================*/
/* Table: ARCUSERAUTHNTICN                                      */
/*==============================================================*/
create table ARCUSERAUTHNTICN 
(
   USERAUTHNTICNID      INTEGER              not null,
   OFFICERID            INTEGER,
   PASSWORD             VARCHAR(64),
   UPDATEDTTM           DATE,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_ARCUSERAUTHNTICN primary key (USERAUTHNTICNID)
);

/*==============================================================*/
/* Table: CORPAYMENT                                            */
/*==============================================================*/
create table CORPAYMENT 
(
   PAYMENTID            INTEGER NOT NULL,
   OFFICERID            INTEGER,
   SHOPID               INTEGER,
   POSID                INTEGER,
   COLLECTIONID         INTEGER,
   PAYMENTDATE          DATE,
   PAYMENTTYPE          VARCHAR(200),
   PAYMENTCASE          VARCHAR(1000),
   AMOUNT               DECIMAL(14,2),
   DISCOUNT             DECIMAL(14,2),
   CHARGE               DECIMAL(14,2),
   VATRATE              DECIMAL(14,2),
   VAT                  DECIMAL(14,2),
   TOTALCHARGE          DECIMAL(14,2),
   DEDUCTION            DECIMAL(14,2),
   AFTERSALEDISCOUNT    DECIMAL(14,2),
   BALANCEDUE           DECIMAL(14,2),
   RECEIVED             DECIMAL(14,2),
   CHANGE               DECIMAL(14,2),
   ADVANCED             DECIMAL(14,2),
   REDUCED              INTEGER,
   ATTRIBUTES           VARCHAR(40),
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_CORPAYMENT primary key (PAYMENTID)
);

/*==============================================================*/
/* Table: CORPAYMENTMNP */
/*==============================================================*/
-- create table CORPAYMENTMNP(
-- 	PAYMENTID INTEGER not null,
-- 	OFFICERID INTEGER,
-- 	SHOPID INTEGER,
-- 	POSID INTEGER,
-- 	ORDERID VARCHAR(40),
-- 	SERVICEGROUP VARCHAR(40),
-- 	MDN VARCHAR(20),
-- 	ICCID VARCHAR(30),
-- 	PAYMENTDATE TIMESTAMP,
-- 	PAYMENTTYPE VARCHAR(200),
-- 	AMOUNT NUMBER(14,2),
-- 	DISCOUNT NUMBER(5,2),
-- 	VATAMOUNT NUMBER(14,2),
-- 	TOTALPAID NUMBER(14,2),
-- 	STATUS VARCHAR(13),
-- 	CANCELDATE TIMESTAMP,
-- 	CANCLEENUM VARCHAR(13),
-- 	CANCELEDBY VARCHAR(30),
-- 	APPROVEDBY VARCHAR(30),
-- 	UPDATEDTTM TIMESTAMP,
-- 	UPDATESYSTEM CHAR(3),
-- 	UPDATEUSER VARCHAR(32),
-- 	VERSIONSTAMP INTEGER,
-- 	constraint PK_CORPAYMENTMNP primary key (PAYMENTID)
-- );

/*==============================================================*/
/* Table: CORPAYMENTTOPUP */
/*==============================================================*/
-- create table CORPAYMENTTOPUP (
-- 	PAYMENTID INTEGER not null,
-- 	OFFICERID INTEGER,
-- 	SHOPID INTEGER,
-- 	POSID INTEGER,
-- 	SERVICECODE VARCHAR(40),
-- 	SERVICENAME VARCHAR(200),
-- 	PROMOTIONCODE VARCHAR(40),
-- 	PROMOTIONNAME VARCHAR(200),
-- 	SERVICENO VARCHAR(20),
-- 	PAYMENTDATE TIMESTAMP,
-- 	PAYMENTTYPE VARCHAR(200),
-- 	AMOUNT NUMBER(14,2),
-- 	DISCOUNT NUMBER(5,2),
-- 	VATAMOUNT NUMBER(14,2),
-- 	TOTALPAID NUMBER(14,2),
-- 	STATUS VARCHAR(13),
-- 	CANCELDATE TIMESTAMP,
-- 	CANCLEENUM VARCHAR(13),
-- 	CANCELEDBY VARCHAR(30),
-- 	APPROVEDBY VARCHAR(30),
-- 	UPDATEDTTM TIMESTAMP,
-- 	UPDATESYSTEM CHAR(3),
-- 	UPDATEUSER VARCHAR(32),
-- 	VERSIONSTAMP INTEGER,
-- 	constraint PK_CORPAYMENTTOPUP primary key (PAYMENTID)
-- );

/*==============================================================*/
/* Table: TRSPAYMENTREF                                         */
/*==============================================================*/
create table TRSPAYMENTREF 
(
   PAYMENTREFID         INTEGER              not null,
   PAYMENTID            INTEGER,
   SERVICEID            INTEGER,
   TRANSACTIONID        VARCHAR(15),
   TRACKINGID           VARCHAR(20),
   TRACKINGIDSERV       VARCHAR(20),
   TRACKINGCODE         VARCHAR(40),
   TRACKINGDETAILS      VARCHAR(2000),
   TRACKINGRETRY        INTEGER,
   PAYMENTDATE          DATE,
   PAYMENTTYPE          VARCHAR(100),
   PAYAMOUNT            DECIMAL(14,2),
   PAYMENTCASE          VARCHAR(13),
   STATUS               VARCHAR(13),
   CHEQUENO             VARCHAR(40),
   ACCOUNTNO            VARCHAR(40),
   APPROVEDBY           VARCHAR(30),
   ATTRIBUTES           VARCHAR(40),
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_TRSPAYMENTREF primary key (PAYMENTREFID)
);

/*==============================================================*/
/* Table: CORRECEIPT                                            */
/*==============================================================*/
create table CORRECEIPT 
(
   RECEIPTID            INTEGER NOT NULL,
   PAYMENTID            INTEGER,
   ACCOUNTID            INTEGER,
   RECEIPTNO            VARCHAR(40),
   RECEIPTNAME          VARCHAR(512),
   RECEIPTTYPE          VARCHAR(20),
   RECEIPTDTTM			TIMESTAMP,
   ACCOUNTNO            VARCHAR(35),
   ACCOUNTSUBNO         VARCHAR(40),
   ACCOUNTTYPE          VARCHAR(40),
   ACCOUNTNAME          VARCHAR(600),
   ACCOUNTBRANCH        VARCHAR(600),
   TAXID                VARCHAR(30),
   ADDRESSLINE1         VARCHAR(1024),
   ADDRESSLINE2         VARCHAR(1024),
   PAYMENTCASE          VARCHAR(200),
   BRANCHCODE           VARCHAR(20),
   BRANCHAREA           VARCHAR(20),
   BRANCHNAME           VARCHAR(600),
   AMOUNT               DECIMAL(14,2),
   DISCOUNT             DECIMAL(14,2),
   CHARGE               DECIMAL(14,2),
   VATRATE              DECIMAL(14,2),
   VAT                  DECIMAL(14,2),
   TOTALCHARGE          DECIMAL(14,2),
   DEDUCTION            DECIMAL(14,2),
   AFTERSALEDISCOUNT    DECIMAL(14,2),
   BALANCEDUE           DECIMAL(14,2),
   RECEIVED             DECIMAL(14,2),
   CHANGE               DECIMAL(14,2),
   ADVANCED             DECIMAL(14,2),
   REMARK               VARCHAR(200),
   PROMOTION            VARCHAR(200),
   REPRINT              INTEGER,
   CANCELREASON         VARCHAR(1000),
   CANCELDTTM           TIMESTAMP,
   CANCELEDBY           VARCHAR(40),
   ATTRIBUTES           VARCHAR(40),
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(40),
   VERSIONSTAMP         INTEGER,
   constraint PK_CORRECEIPT primary key (RECEIPTID)
);

/*==============================================================*/
/* Table: TMPINVOICE                                            */
/*==============================================================*/
create table TMPINVOICE 
(
   INVOICEID            INTEGER              not null,
   PAYMENTID            INTEGER,
   RECEIPTID            INTEGER,
   ACCOUNTID            INTEGER,
   INVOICENO            VARCHAR(30),
   PAYMENTTYPE          VARCHAR(40),
   KENAN                VARCHAR(100),
   CURRENCYCODE         VARCHAR(10),
   ISSUEDATE            DATE,
   DUEDATE              DATE,
   AMOUNT               DECIMAL(14,2),
   DISCOUNT             DECIMAL(14,2),
   CHARGE               DECIMAL(14,2),
   VATRATE              DECIMAL(14,2),
   VAT                  DECIMAL(14,2),
   TOTALCHARGE          DECIMAL(14,2),
   DEDUCTION            DECIMAL(14,2),
   BALANCEDUE           DECIMAL(14,2),
   AFTERSALEDISCOUNT   DECIMAL(14,2),
   RECEIVED             DECIMAL(14,2),
   CHANGE               DECIMAL(14,2),
   ADVANCED             DECIMAL(14,2),
   STATUS               VARCHAR(20),
   BILLCYCLE            VARCHAR(200),
   CANCELDTTM           TIMESTAMP,
   CANCELEDBY           VARCHAR(40),
   ATTRIBUTES           VARCHAR(40),
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_TMPINVOICE primary key (INVOICEID)
);

/*==============================================================*/
/* Table: TMPINVOICESERVICE                                     */
/*==============================================================*/
create table TMPINVOICESERVICE (
	SERVICEID            INTEGER not null,
	RECEIPTID            INTEGER,
	INVOICEID            INTEGER,
	ACCOUNTNO            VARCHAR(20),
	SERVICENO            VARCHAR(40),
	SERVICECODE          VARCHAR(40),
	SERVICENAME          VARCHAR(600),
	SERVICEGROUP         VARCHAR(40),
	PRODUCTCODE          VARCHAR(40),
	PRODUCTNAME          VARCHAR(120),
	PRODUCTSUBNO         VARCHAR(40),
	PRODUCTSUBNAME       VARCHAR(120),
	INCOMETYPE           VARCHAR(600),
	INCOMEUNIT           VARCHAR(100),
	INCOMEAMOUNT         DECIMAL(14,2),
	PROMOTIONCODE        VARCHAR(40),
	PROMOTIONNAME        VARCHAR(200),
	REFTRANSID           VARCHAR(100),
	ORDERID              VARCHAR(40),
	MDN                  VARCHAR(20),
	ICCID                VARCHAR(30),
	AMOUNT               DECIMAL(14,2),
	DISCOUNT             DECIMAL(14,2),
	CHARGE               DECIMAL(14,2),
	VATRATE              DECIMAL(14,2),
	VAT                  DECIMAL(14,2),
	TOTALCHARGE          DECIMAL(14,2),
	DEDUCTION            DECIMAL(14,2),
	ATTRIBUTES           VARCHAR(40),
	UPDATEDTTM           TIMESTAMP,
	UPDATESYSTEM         CHAR(3),
	UPDATEUSER           VARCHAR(40),
	VERSIONSTAMP         INTEGER,
	constraint PK_TMPINVOICESERVICE primary key (SERVICEID)
);

/*==============================================================*/
/* Table: CORREDUCEDEBT                                         */
/*==============================================================*/
create table CORREDUCEDEBT 
(
   REDUCEDEBTID         INTEGER              not null,
   COR_PAYMENTID        INTEGER,
   REQUESTNO            VARCHAR(30),
   APPROVENO            VARCHAR(30),
   REASON               VARCHAR(13),
   REQUESTAMOUNT        DECIMAL(14,2),
   APPROVEAMOUNT        DECIMAL(14,2),
   APPROVEDBY           VARCHAR(32),
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_CORREDUCEDEBT primary key (REDUCEDEBTID)
);

/*==============================================================*/
/* Table: CORREFUND                                             */
/*==============================================================*/
create table CORREFUND 
(
   REFUNDID             INTEGER              not null,
   PAYMENTID            INTEGER,
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_CORREFUND primary key (REFUNDID)
);

/*==============================================================*/
/* Table: MASGL                                                 */
/*==============================================================*/
create table MASGL 
(
   GLID                 INTEGER              not null,
   VATSALE              VARCHAR(10),
   DOCUMENTTYPE         VARCHAR(2),
   TRANSFERACCOUNT      VARCHAR(10),
   POSTKEYDEBIT         VARCHAR(2),
   POSTKEYCREDIT        VARCHAR(2),
   POSTKEYDEBITREV      VARCHAR(2),
   POSTKEYCREDITREV     VARCHAR(2),
   BUTRANSACTION        VARCHAR(4),
   DOCHEADTEXT          VARCHAR(25),
   REFERENCE            VARCHAR(16),
   COMPANYCODE          VARCHAR(4),
   DEPARTMENTCOMP       VARCHAR(10),
   DEPARTMENTCODE       VARCHAR(4),
   TEXT                 VARCHAR(50),
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_MASGL primary key (GLID)
);

/*==============================================================*/
/* Table: MASOFFICER                                            */
/*==============================================================*/
create table MASOFFICER 
(
   OFFICERID            INTEGER              not null,
   PRINCIPALID          INTEGER,
   SESSIONID            INTEGER,
   USERNAME             VARCHAR(40),
   OFFICERCODE          VARCHAR(50),
   OFFICERGIVENNAME     VARCHAR(200),
   OFFICERFAMILYNAME    VARCHAR(200),
   PERMISSION           VARCHAR(50),
   ISPOSITIVE           INTEGER,
   DESCRIPTION			VARCHAR(2000),
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_MASOFFICER primary key (OFFICERID)
);

CREATE TABLE COROFFICERPOS (
	OFFICERID INTEGER not null,
	POSID INTEGER not null
);
CREATE TABLE CORSESSION (
	SESSIONID           INTEGER NOT NULL,
	USERNAME            VARCHAR(40),
	FIRSTTIME           TIMESTAMP,
	LASTUPDATED         TIMESTAMP,
	constraint PK_CORSESSION primary key (SESSIONID)
);
CREATE TABLE TRSPAYMENTSUMMARY (
	SUMMARYID           INTEGER NOT NULL,
	SESSIONID           INTEGER,
	PAYTYPE             VARCHAR(40),
	PAYDESC             VARCHAR(200),
	BALANCE             DECIMAL(14,2),
	COUNTER             INTEGER,
	constraint PK_TRSPAYMENTSUMMARY primary key (SUMMARYID)
);
CREATE TABLE CORRECEIPTDOCUMENT (
	RECEIPTDOCUMENTID   INTEGER NOT NULL,
	RECEIPTDOCUMENTTYPE VARCHAR(10),
	BRANCHAREA          VARCHAR(10),
	DATETEXT            VARCHAR(20),
	DOCUMENTCOUNT       INTEGER,
	constraint PK_CORRECEIPTDOCUMENT primary key (RECEIPTDOCUMENTID)
);

/*==============================================================*/
/* Table: MASPOS                                                */
/*==============================================================*/
create table MASPOS 
(
   POSID                INTEGER              not null,
   SHOPID               INTEGER,
   POSNO                VARCHAR(20),
   POSNAME              VARCHAR(120),
   MAC                  VARCHAR(100),
   ISPOSITIVE           INTEGER,
   DESCRIPTION			VARCHAR(2000),
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_MASPOS primary key (POSID)
);

/*==============================================================*/
/* Table: MASSHOP                                               */
/*==============================================================*/
create table MASSHOP (
	SHOPID               INTEGER              not null,
	SHOPNO               VARCHAR(10),
	SHOPNAME             VARCHAR(1000),
	BUPLACE              VARCHAR(10),
	BUAREA               VARCHAR(10),
	COSTCENTER           VARCHAR(10),
	DESCABVRTH           VARCHAR(10),
	ISPOSITIVE           INTEGER,
	DESCRIPTION			 VARCHAR(1000), 
	UPDATEDTTM           TIMESTAMP,
	UPDATESYSTEM         CHAR(3),
	UPDATEUSER           VARCHAR(32),
	VERSIONSTAMP         INTEGER,
	constraint PK_MASSHOP primary key (SHOPID)
);
insert into MASSHOP values(1, '01230', 'สำนักงานบริการลูกค้า กสท เขตกลาง', '01230', '1100', null, null, 1, null, null, null, null, null);

/*==============================================================*/
/* Table: TMPACCOUNT                                            */
/*==============================================================*/
create table TMPACCOUNT 
(
   ACCOUNTID            INTEGER              not null,
   PAYMENTID            INTEGER,
   ACCOUNTNO            VARCHAR(30),
   ACCOUNTTYPE          VARCHAR(13),
   TAXID                VARCHAR(20),
   NAME                 VARCHAR(550),
   BRANCH               VARCHAR(120),
   STATUS               VARCHAR(13),
   CATEGORY             VARCHAR(13),
   COLLECTIONID         INTEGER,
   TELEPHONE            VARCHAR(20),
   OUTSTANDING          DECIMAL(14,2),
   ADVANCED             DECIMAL(14,2),
   RECEIPTADDRESS       VARCHAR(600),
   INVOICEADDRESS       VARCHAR(600),
   BILLINGSERVERID      VARCHAR(60),
   CURRENCYCODE         VARCHAR(10),
   VATRATE              VARCHAR(3),
   BILLGROUP            VARCHAR(13),
   REMARK               VARCHAR(600),
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_TMPACCOUNT primary key (ACCOUNTID)
);

/*==============================================================*/
/* Table: TRSBILLOFEXCHANGEREF                                  */
/*==============================================================*/
create table TRSBILLOFEXCHANGEREF 
(
   BILLOFEXCHANGEREFID  INTEGER              not null,
   PAYMENTREFID         INTEGER,
   BILLOFEXCHANGENO     VARCHAR(20),
   ISSUEDATE            DATE,
   AMOUNT               DECIMAL(14,2),
   ZIPCODE              VARCHAR(16),
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_TRSBILLOFEXCHANGEREF primary key (BILLOFEXCHANGEREFID)
);

/*==============================================================*/
/* Table: TRSCHEQUEREF                                          */
/*==============================================================*/
create table TRSCHEQUEREF 
(
   CHEQUEREFID          INTEGER              not null,
   PAYMENTREFID         INTEGER,
   CHEQUENO             VARCHAR(40),
   PUBLISHERID          VARCHAR(20),
   PUBLISHER            VARCHAR(300),
   BRANCH               VARCHAR(300),
   ISSUEDATE            DATE,
   AMOUNT               DECIMAL(14,2),
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_TRSCHEQUEREF primary key (CHEQUEREFID)
);

/*==============================================================*/
/* Table: TRSCREDITNOTEREF                                      */
/*==============================================================*/
create table TRSCREDITNOTEREF 
(
   CREDITNOTEREFID      INTEGER              not null,
   PAYMENTREFID         INTEGER,
   CREDITNOTENO         VARCHAR(20),
   ISSUEDATE            DATE,
   AMOUNT               DECIMAL(14,2),
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_TRSCREDITNOTEREF primary key (CREDITNOTEREFID)
);

/*==============================================================*/
/* Table: TRSCREDITREF                                          */
/*==============================================================*/
create table TRSCREDITREF 
(
   CREDITREFID          INTEGER              not null,
   PAYMENTREFID         INTEGER,
   CREDITNO             VARCHAR(40),
   PUBLISHERDEC         VARCHAR(20),
   CARDTYPE             VARCHAR(20),
   AMOUNT               DECIMAL(14,2),
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_TRSCREDITREF primary key (CREDITREFID)
);

/*==============================================================*/
/* Table: TRSDISCOUNTREF                                       */
/*==============================================================*/
create table TRSDISCOUNTREF 
(
   DISCOUNTREFID        INTEGER              not null,
   PAYMENTREFID         INTEGER,
   DISCOUNTTYPE         VARCHAR(20),
   FEEID                VARCHAR(40),
   AMOUNT               DECIMAL(14,2),
   INVOICENO            VARCHAR(10),
   PRODUCTNO            VARCHAR(10),
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_TRSDISCOUNTREF primary key (DISCOUNTREFID)
);

/*==============================================================*/
/* Table: TRSMETHOD                                             */
/*==============================================================*/
create table TRSMETHOD 
(
   METHODID             INTEGER              not null,
   PAYMENTID            INTEGER,
   CODE                 VARCHAR(13),
   NAME                 VARCHAR(200),
   CHEQUENO             VARCHAR(40),
   ACCOUNTNO            VARCHAR(40),
   AMOUNT               DECIMAL(14,2),
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_TRSMETHOD primary key (METHODID)
);

/*==============================================================*/
/* Table: TRSMONEYORDERREF                                      */
/*==============================================================*/
create table TRSMONEYORDERREF 
(
   MONEYORDERREFID      INTEGER              not null,
   PAYMENTREFID         INTEGER,
   MONEYORDERNO         VARCHAR(20),
   ISSUEDATE            DATE,
   AMOUNT               DECIMAL(14,2),
   ZIPCODE              VARCHAR(16),
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_TRSMONEYORDERREF primary key (MONEYORDERREFID)
);

/*==============================================================*/
/* Table: TRSMONEYTRANSFERREF                                   */
/*==============================================================*/
create table TRSMONEYTRANSFERREF 
(
   MONEYTRANSFERREFID   INTEGER              not null,
   PAYMENTREFID         INTEGER,
   MONEYTRANSFERNO      VARCHAR(20),
   ISSUEDATE            DATE,
   PUBLISHER            VARCHAR(10),
   PUBLISHERNAME        VARCHAR(120),
   BRANCH               VARCHAR(100),
   AMOUNT               DECIMAL(14,2),
   CODEBANKACCT         INTEGER,
   DECIMALBANKACCT      VARCHAR(300),
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_TRSMONEYTRANSFERREF primary key (MONEYTRANSFERREFID)
);

/*==============================================================*/
/* Table: TRSOFFICERLOGIN                                       */
/*==============================================================*/
-- create table TRSOFFICERLOGIN 
-- (
--    OFFICERLOGINID       INTEGER              not null,
--    OFFICERID            INTEGER,
--    SESSIONID            VARCHAR(40),
--    STARTTIME            TIMESTAMP,
--    ENDTIME              TIMESTAMP,
--    UPDATEDTTM           TIMESTAMP,
--    UPDATESYSTEM         CHAR(3),
--    UPDATEUSER           VARCHAR(32),
--    VERSIONSTAMP         INTEGER,
--    constraint PK_TRSOFFICERLOGIN primary key (OFFICERLOGINID)
-- );

/*==============================================================*/
/* Table: TRSOTHERREF                                           */
/*==============================================================*/
create table TRSOTHERREF 
(
   OTHERREFID           INT              not null,
   PAYMENTREFID         INTEGER,
   OTHERPAYTYPE         VARCHAR(40),
   OTHERNO              VARCHAR(20),
   ISSUEDATE            DATE,
   AMOUNT               DECIMAL(14,2),
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_TRSOTHERREF primary key (OTHERREFID)
);

/*==============================================================*/
/* Table: TRSRECEIPTDETAIL                                      */
/*==============================================================*/
create table TRSRECEIPTDETAIL 
(
   RECEIPTDETAILID      INTEGER              not null,
   RECEIPTID            INTEGER,
   RANKING              INTEGER,
   INVOICENO            DECIMAL(14,2),
   BILLCYCLE            DECIMAL(14,2),
   AMOUNT               DECIMAL(14,2),
   DISCOUNT             DECIMAL(5,2),
   PAYAMOUNT            DECIMAL(14,2),
   VATAMOUNT            DECIMAL(5,2),
   TOTALPAID            DECIMAL(14,2),
   PROMOTIION           VARCHAR(100),
   DESCRIPTION          VARCHAR(200),
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_TRSRECEIPTDETAIL primary key (RECEIPTDETAILID)
);

/*==============================================================*/
/* Table: TRSREDUCEDEBTREF                                      */
/*==============================================================*/
create table TRSREDUCEDEBTREF 
(
   REDUCEDEBTREFID      INTEGER              not null,
   REDUCEDEBTID         INTEGER,
   PAYMENTREFID         INTEGER,
   AMOUNT               DECIMAL(14,2),
   APPROVEAMOUNT        DECIMAL(14,2),
   STATUS               VARCHAR(13),
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_TRSREDUCEDEBTREF primary key (REDUCEDEBTREFID)
);

/*==============================================================*/
/* Table: TRSREPRINT                                            */
/*==============================================================*/
create table TRSREPRINT 
(
   REPRINTID            INTEGER              not null,
   RECEIPTID            INTEGER,
   CATEGORY             INTEGER,
   REASON               VARCHAR(13),
   APPROVEDBY           VARCHAR(32),
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_TRSREPRINT primary key (REPRINTID)
);

/*==============================================================*/
/* Table: TRSDEDUCTION                                              */
/*==============================================================*/
create table TRSDEDUCTION 
(
   DEDUCTIONID          INTEGER not null,
   PAYMENTID            INTEGER,
   DEDUCTIONNO          VARCHAR(40),
   DEDUCTIONTYPE        VARCHAR(10),
   AMOUNT               DECIMAL(14,2),
   PAYMENTDATE          DATE,
   UPDATEDTTM           TIMESTAMP,
   UPDATESYSTEM         CHAR(3),
   UPDATEUSER           VARCHAR(32),
   VERSIONSTAMP         INTEGER,
   constraint PK_TRSDEDUCTION primary key (DEDUCTIONID)
);

/*==============================================================*/
/* Table: TRSERPPAYSERVICE, TRSERPPAYNOSERVICE                  */
/*==============================================================*/
create table TRSERPPAYSERVICE (
	PAYSERVICEID        INTEGER not null,
	UPDATEDTTM          TIMESTAMP,
	ATTRIBUTES          VARCHAR(20),
	RECORDSEQ           CHAR(3),
	BKPF_BLDAT          CHAR(8),
	BKPF_BLART          CHAR(2),
	BKPF_BUKRS          CHAR(4),
	BKPF_BUDAT          CHAR(8),
	BKPF_MONAT          CHAR(2),
	BKPF_WAERS          CHAR(5),
	BKPF_KURSF          CHAR(11),
	BKPF_WWERT          CHAR(8),
	BKPF_XBLNR          CHAR(16),
	BKPF_BKTXT          CHAR(25),
	BKPF_BRNCH          CHAR(4),
	BKPF_GLVOR          CHAR(4),
	BKPF_GJAHR          CHAR(4),
	BKPF_USNAM          CHAR(12),
	BKPF_AWTYP          CHAR(5),
	BKPF_AWREF          CHAR(10),
	BSEG_BUZEI          CHAR(3),
	BSEG_SHKZG          CHAR(1),
	BSEG_BSCHL          CHAR(2),
	BSEG_KOART          CHAR(1),
	BSEG_HKONT_KUNNR_LIFNR CHAR(10),
	BSEG_HKONT          CHAR(10),
	BSEG_WRBTR          CHAR(13),
	BSEG_DMBTR          CHAR(13),
	BSEG_FWBAS          CHAR(13),
	BSEG_HWBAS          CHAR(13),
	BSEG_MWSKZ          CHAR(2),
	BSEG_BUPLA          CHAR(4),
	BSEG_GSBER          CHAR(4),
	BSEG_VALUT          CHAR(8),
	BSEG_ZTERM          CHAR(4),
	BSEG_ZFBDT          CHAR(8),
	BSEG_SKFBT          CHAR(13),
	BSEG_KOSTL          CHAR(10),
	BSEG_FKBER          CHAR(16),
	BSEG_PROJK          CHAR(24),
	BSEG_LSTAR          CHAR(6),
	BSEG_SEGMENT        CHAR(10),
	BSEG_ZZWWPRD        CHAR(18),
	BSEG_ZZWWSUB        CHAR(2),
	BSEG_ZZWWREV        CHAR(10),
	BSEG_MATNR          CHAR(18),
	BSEG_WERKS          CHAR(4),
	BSEG_PRZNR          CHAR(12),
	CE11000_WWCUS       CHAR(10),
	BSEG_KIDNO          CHAR(30),
	BSEG_ZUONR          CHAR(18),
	BSEG_SGTXT          CHAR(50),
	BSEG_XREF1          CHAR(12),
	BSEG_XREF2          CHAR(12),
	BSEG_XREF3          CHAR(20),
	BSEG_VBUND          CHAR(6),
	BSEC_BUZEI          CHAR(3),
	BSEC_ANRED          CHAR(15),
	BSEC_NAME1          CHAR(35),
	BSEC_NAME2          CHAR(35),
	BSEC_NAME3          CHAR(35),
	BSEC_NAME4          CHAR(35),
	BSEC_STRAS          CHAR(35),
	BSEC_ORT01          CHAR(35),
	BSEC_PSTLZ          CHAR(10),
	BSEC_LAND1          CHAR(3),
	BSEC_STCD1          CHAR(16),
	BSEC_STCD2          CHAR(11),
	BSEC_EMPFG          CHAR(16),
	BSEC_BANKL          CHAR(15),
	BSEC_BANKN          CHAR(18),
	BSEC_BANKS          CHAR(3),
	WITH_ITEM_BUZEI     CHAR(3),
	WITH_ITEM_WITHT1     CHAR(2),
	WITH_ITEM_WT_WITHCD1 CHAR(2),
	WITH_ITEM_WT_QSSHB1  CHAR(13),
	WITH_ITEM_WT_QSSHH1  CHAR(13),
	WITH_ITEM_WT_QBSHB1  CHAR(13),
	WITH_ITEM_WT_QBSHH1  CHAR(13),
	WITH_ITEM_WITHT2     CHAR(2),
	WITH_ITEM_WT_WITHCD2 CHAR(2),
	WITH_ITEM_WT_QSSHB2  CHAR(13),
	WITH_ITEM_WT_QSSHH2  CHAR(13),
	WITH_ITEM_WT_QBSHB2  CHAR(13),
	WITH_ITEM_WT_QBSHH2  CHAR(13),
	BSET_BUZEI           CHAR(3),
	BSET_MWSKZ           CHAR(2),
	BSET_HKONT           CHAR(10),
	BSET_SHKZG           CHAR(1),
	BSET_HWBAS           CHAR(15),
	BSET_FWBAS           CHAR(15),
	BSET_HWSTE           CHAR(15),
	BSET_FWSTE           CHAR(13),
	BSET_KTOSL           CHAR(3),
	BSET_KSCHL           CHAR(4),
	BSET_BUPLA           CHAR(4),
	CE11000_WWCOC        CHAR(4)
);
create table TRSERPPAYNOSERVICE (
	PAYNOSERVICEID      INTEGER not null,
	UPDATEDTTM          DATE,
	ATTRIBUTES          VARCHAR(20),
	RECORDSEQ           CHAR(3),
	BKPF_BLDAT          CHAR(8),
	BKPF_BLART          CHAR(2),
	BKPF_BUKRS          CHAR(4),
	BKPF_BUDAT          CHAR(8),
	BKPF_MONAT          CHAR(2),
	BKPF_WAERS          CHAR(5),
	BKPF_KURSF          CHAR(11),
	BKPF_WWERT          CHAR(8),
	BKPF_XBLNR          CHAR(16),
	BKPF_BKTXT          CHAR(25),
	BKPF_BRNCH          CHAR(4),
	BKPF_GLVOR          CHAR(4),
	BKPF_GJAHR          CHAR(4),
	BKPF_USNAM          CHAR(12),
	BKPF_AWTYP          CHAR(5),
	BKPF_AWREF          CHAR(10),
	BSEG_BUZEI          CHAR(3),
	BSEG_SHKZG          CHAR(1),
	BSEG_BSCHL          CHAR(2),
	BSEG_KOART          CHAR(1),
	BSEG_HKONT_KUNNR_LIFNR CHAR(10),
	BSEG_HKONT          CHAR(10),
	BSEG_WRBTR          CHAR(13),
	BSEG_DMBTR          CHAR(13),
	BSEG_FWBAS          CHAR(13),
	BSEG_HWBAS          CHAR(13),
	BSEG_MWSKZ          CHAR(2),
	BSEG_BUPLA          CHAR(4),
	BSEG_GSBER          CHAR(4),
	BSEG_VALUT          CHAR(8),
	BSEG_ZTERM          CHAR(4),
	BSEG_ZFBDT          CHAR(8),
	BSEG_SKFBT          CHAR(13),
	BSEG_KOSTL          CHAR(10),
	BSEG_FKBER          CHAR(16),
	BSEG_PROJK          CHAR(24),
	BSEG_LSTAR          CHAR(6),
	BSEG_SEGMENT        CHAR(10),
	BSEG_ZZWWPRD        CHAR(18),
	BSEG_ZZWWSUB        CHAR(2),
	BSEG_ZZWWREV        CHAR(10),
	BSEG_MATNR          CHAR(18),
	BSEG_WERKS          CHAR(4),
	BSEG_PRZNR          CHAR(12),
	CE11000_WWCUS       CHAR(10),
	BSEG_KIDNO          CHAR(30),
	BSEG_ZUONR          CHAR(18),
	BSEG_SGTXT          CHAR(50),
	BSEG_XREF1          CHAR(12),
	BSEG_XREF2          CHAR(12),
	BSEG_XREF3          CHAR(20),
	BSEG_VBUND          CHAR(6),
	BSEC_BUZEI          CHAR(3),
	BSEC_ANRED          CHAR(15),
	BSEC_NAME1          CHAR(35),
	BSEC_NAME2          CHAR(35),
	BSEC_NAME3          CHAR(35),
	BSEC_NAME4          CHAR(35),
	BSEC_STRAS          CHAR(35),
	BSEC_ORT01          CHAR(35),
	BSEC_PSTLZ          CHAR(10),
	BSEC_LAND1          CHAR(3),
	BSEC_STCD1          CHAR(16),
	BSEC_STCD2          CHAR(11),
	BSEC_EMPFG          CHAR(16),
	BSEC_BANKL          CHAR(15),
	BSEC_BANKN          CHAR(18),
	BSEC_BANKS          CHAR(3),
	WITH_ITEM_BUZEI     CHAR(3),
	WITH_ITEM_WITHT1     CHAR(2),
	WITH_ITEM_WT_WITHCD1 CHAR(2),
	WITH_ITEM_WT_QSSHB1  CHAR(13),
	WITH_ITEM_WT_QSSHH1  CHAR(13),
	WITH_ITEM_WT_QBSHB1  CHAR(13),
	WITH_ITEM_WT_QBSHH1  CHAR(13),
	WITH_ITEM_WITHT2     CHAR(2),
	WITH_ITEM_WT_WITHCD2 CHAR(2),
	WITH_ITEM_WT_QSSHB2  CHAR(13),
	WITH_ITEM_WT_QSSHH2  CHAR(13),
	WITH_ITEM_WT_QBSHB2  CHAR(13),
	WITH_ITEM_WT_QBSHH2  CHAR(13),
	BSET_BUZEI           CHAR(3),
	BSET_MWSKZ           CHAR(2),
	BSET_HKONT           CHAR(10),
	BSET_SHKZG           CHAR(1),
	BSET_HWBAS           CHAR(15),
	BSET_FWBAS           CHAR(15),
	BSET_HWSTE           CHAR(15),
	BSET_FWSTE           CHAR(13),
	BSET_KTOSL           CHAR(3),
	BSET_KSCHL           CHAR(4),
	BSET_BUPLA           CHAR(4),
	CE11000_WWCOC        CHAR(4)
);

alter table ARCGRANT
   add constraint fk_opegra_operationid foreign key (OPERATIONID)
      references ARCOPERATION (OPERATIONID);

alter table ARCGRANT
   add constraint fk_prigra_principalid foreign key (PRINCIPALID)
      references ARCPRINCIPAL (PRINCIPALID);

alter table ARCUSERAUTHNTICN
   add constraint fk_offuse_officerid foreign key (OFFICERID)
      references MASOFFICER (OFFICERID);

alter table CORREDUCEDEBT
   add constraint fk_payred_paymentid foreign key (COR_PAYMENTID)
      references CORPAYMENT (PAYMENTID);

alter table MASOFFICER
   add constraint fk_prioff_principalid foreign key (PRINCIPALID)
      references ARCPRINCIPAL (PRINCIPALID);
      
alter table MASPOS
   add constraint fk_shopos_shopid foreign key (SHOPID)
      references MASSHOP (SHOPID);

alter table TMPACCOUNT
   add constraint fk_payacc_paymentid foreign key (PAYMENTID)
      references CORPAYMENT (PAYMENTID);

alter table TMPINVOICE
   add constraint fk_accinv_accountid foreign key (ACCOUNTID)
      references TMPACCOUNT (ACCOUNTID);

alter table TMPINVOICE
   add constraint fk_payinv_paymentid foreign key (PAYMENTID)
      references CORPAYMENT (PAYMENTID);

alter table TMPINVOICESERVICE
   add constraint fk_recinv_receiptid foreign key (RECEIPTID)
      references CORRECEIPT (RECEIPTID);

alter table TRSBILLOFEXCHANGEREF
   add constraint fk_paybil_paymentrefid foreign key (PAYMENTREFID)
      references TRSPAYMENTREF (PAYMENTREFID);

alter table TRSCHEQUEREF
   add constraint fk_chepay_paymentrefid foreign key (PAYMENTREFID)
      references TRSPAYMENTREF (PAYMENTREFID);

alter table TRSCREDITNOTEREF
   add constraint fk_paynot_paymentrefid foreign key (PAYMENTREFID)
      references TRSPAYMENTREF (PAYMENTREFID);

alter table TRSCREDITREF
   add constraint fk_paycre_paymentrefid foreign key (PAYMENTREFID)
      references TRSPAYMENTREF (PAYMENTREFID);

alter table TRSDISCOUNTREF
   add constraint fk_paydis_paymentrefid foreign key (PAYMENTREFID)
      references TRSPAYMENTREF (PAYMENTREFID);

alter table TRSMETHOD
   add constraint fk_paymet_paymentid foreign key (PAYMENTID)
      references CORPAYMENT (PAYMENTID);

alter table TRSMONEYORDERREF
   add constraint fk_paymon_paymentrefid foreign key (PAYMENTREFID)
      references TRSPAYMENTREF (PAYMENTREFID);

alter table TRSMONEYTRANSFERREF
   add constraint fk_monpay_paymentrefid foreign key (PAYMENTREFID)
      references TRSPAYMENTREF (PAYMENTREFID);

-- alter table TRSOFFICERLOGIN
--    add constraint fk_offlog_officerid foreign key (OFFICERID)
--       references MASOFFICER (OFFICERID);

alter table TRSOTHERREF
   add constraint fk_payoth_paymentrefid foreign key (PAYMENTREFID)
      references TRSPAYMENTREF (PAYMENTREFID);

alter table TRSPAYMENTREF
   add constraint fk_paypay_paymentid foreign key (PAYMENTID)
      references CORPAYMENT (PAYMENTID);

alter table TRSRECEIPTDETAIL
   add constraint fk_recrec_receiptid foreign key (RECEIPTID)
      references CORRECEIPT (RECEIPTID);

alter table TRSREDUCEDEBTREF
   add constraint fk_payred_paymentrefid foreign key (PAYMENTREFID)
      references TRSPAYMENTREF (PAYMENTREFID);

alter table TRSREDUCEDEBTREF
   add constraint fk_redred_reducedebtid foreign key (REDUCEDEBTID)
      references CORREDUCEDEBT (REDUCEDEBTID);

alter table TRSREPRINT
   add constraint fk_recrep_receiptid foreign key (RECEIPTID)
      references CORRECEIPT (RECEIPTID);

alter table TRSDEDUCTION
   add constraint fk_payded_paymentid foreign key (PAYMENTID)
      references CORPAYMENT (PAYMENTID);


-------------------------------------------------------------------------------
-- select * from BATCH_JOB_INSTANCE;
-- select * from BATCH_JOB_EXECUTION;
-- select * from BATCH_JOB_EXECUTION_PARAMS;
-- select * from BATCH_JOB_EXECUTION_CONTEXT;
-- select * from BATCH_STEP_EXECUTION;
-- select * from BATCH_STEP_EXECUTION_CONTEXT;
DROP TABLE BATCH_STEP_EXECUTION_CONTEXT;
DROP TABLE BATCH_STEP_EXECUTION;
DROP TABLE BATCH_JOB_EXECUTION_CONTEXT;
DROP TABLE BATCH_JOB_EXECUTION_PARAMS;
DROP TABLE BATCH_JOB_EXECUTION;
DROP TABLE BATCH_JOB_INSTANCE;
DROP SEQUENCE BATCH_JOB_SEQ;
DROP SEQUENCE BATCH_JOB_EXECUTION_SEQ;
DROP SEQUENCE BATCH_STEP_EXECUTION_SEQ;
CREATE SEQUENCE BATCH_STEP_EXECUTION_SEQ MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE BATCH_JOB_EXECUTION_SEQ MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE BATCH_JOB_SEQ MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE TABLE BATCH_JOB_INSTANCE (
	JOB_INSTANCE_ID INTEGER PRIMARY KEY,
	VERSION INTEGER,
	JOB_NAME VARCHAR(100) NOT NULL,
	JOB_KEY VARCHAR(2500)
);
CREATE TABLE BATCH_JOB_EXECUTION (
	JOB_EXECUTION_ID INTEGER  PRIMARY KEY,
	VERSION INTEGER,
	JOB_INSTANCE_ID INTEGER NOT NULL,
	CREATE_TIME TIMESTAMP NOT NULL,
	START_TIME TIMESTAMP DEFAULT NULL,
	END_TIME TIMESTAMP DEFAULT NULL,
	STATUS VARCHAR(10),
	EXIT_CODE VARCHAR(20),
	EXIT_MESSAGE VARCHAR(2500),
	LAST_UPDATED TIMESTAMP,
	JOB_CONFIGURATION_LOCATION VARCHAR(2500) NULL,
	constraint JOB_INSTANCE_EXECUTION_FK foreign key (JOB_INSTANCE_ID)
	references BATCH_JOB_INSTANCE(JOB_INSTANCE_ID)
);
CREATE TABLE BATCH_JOB_EXECUTION_PARAMS (
	JOB_EXECUTION_ID INTEGER NOT NULL,
	TYPE_CD VARCHAR(6) NOT NULL,
	KEY_NAME VARCHAR(100) NOT NULL,
	STRING_VAL VARCHAR(250),
	DATE_VAL TIMESTAMP DEFAULT NULL,
	LONG_VAL INTEGER,
	DOUBLE_VAL DECIMAL(14,2),
	IDENTIFYING CHAR(1) NOT NULL,
	constraint JOB_EXEC_PARAMS_FK foreign key (JOB_EXECUTION_ID)
	references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
);
CREATE TABLE BATCH_JOB_EXECUTION_CONTEXT (
	JOB_EXECUTION_ID INTEGER PRIMARY KEY,
	SHORT_CONTEXT VARCHAR(2500) NOT NULL,
	SERIALIZED_CONTEXT CLOB,
	constraint JOB_EXEC_CTX_FK foreign key (JOB_EXECUTION_ID)
	references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
);
CREATE TABLE BATCH_STEP_EXECUTION (
	STEP_EXECUTION_ID INTEGER  PRIMARY KEY,
	VERSION INTEGER NOT NULL,
	STEP_NAME VARCHAR(100) NOT NULL,
	JOB_EXECUTION_ID INTEGER NOT NULL,
	START_TIME TIMESTAMP NOT NULL,
	END_TIME TIMESTAMP DEFAULT NULL,
	STATUS VARCHAR(10),
	COMMIT_COUNT INTEGER,
	READ_COUNT INTEGER,
	FILTER_COUNT INTEGER,
	WRITE_COUNT INTEGER,
	READ_SKIP_COUNT INTEGER,
	WRITE_SKIP_COUNT INTEGER,
	PROCESS_SKIP_COUNT INTEGER,
	ROLLBACK_COUNT INTEGER,
	EXIT_CODE VARCHAR(20),
	EXIT_MESSAGE VARCHAR(2500),
	LAST_UPDATED TIMESTAMP,
	constraint JOB_EXECUTION_STEP_FK foreign key (JOB_EXECUTION_ID)
	references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
);
CREATE TABLE BATCH_STEP_EXECUTION_CONTEXT (
	STEP_EXECUTION_ID INTEGER PRIMARY KEY,
	SHORT_CONTEXT VARCHAR(2500) NOT NULL,
	SERIALIZED_CONTEXT CLOB,
	constraint STEP_EXEC_CTX_FK foreign key (STEP_EXECUTION_ID)
	references BATCH_STEP_EXECUTION(STEP_EXECUTION_ID)
);

-------------------------------------------------------------------------------
DROP TABLE BILLINVOICE;
DROP TABLE BILLINVOICEPRODUCT;
CREATE TABLE BILLINVOICE (
	ID                        INTEGER,
	INVOICENO                 VARCHAR(30),
	TOTALCHARGE               NUMBER(14,2),
	constraint PK_BILLINVOICEREF primary key (ID)
);
CREATE TABLE BILLINVOICEPRODUCT (
	ID                        INTEGER,
	INVOICEID                 INTEGER,
	PRODUCTPRIORITY           INTEGER,
	PRODUCTCODE               VARCHAR(20),
	SUBPRODUCTCODE            VARCHAR(20),
	REVENUETYPE               VARCHAR(20),
	AMOUNT                    NUMBER(14,2),
	constraint PK_BILLINVOICEPRODUCTREF primary key (ID)
);

-------------------------------------------------------------------------------
CREATE TABLE INV_SUMMARY_SAP_IBACSS (
	ID                        INTEGER,
	CONTRNO                   VARCHAR2(15) NOT NULL,
	BI_REF                    NUMBER(15) NOT NULL,
	BILLGROUP                 VARCHAR2(25),
	ACCOUNT_CODE_OLD          VARCHAR2(40),
	ACCOUNT_CODE_NEW          VARCHAR2(20),
	SEGMENT_CODE              VARCHAR2(10),
	PRODUCT_CODE              VARCHAR2(15) NOT NULL,
	SUB_PRODUCT_CODE          VARCHAR2(15) NOT NULL,
	REVENUE_TYPE_CODE         VARCHAR2(10) NOT NULL,
	INVDATE                   VARCHAR2(8),
	DUEDATE                   VARCHAR2(8),
	PAYDATE                   VARCHAR2(8),
	PERIOD                    VARCHAR2(20),
	AMOUNT                    NUMBER(14,2) NOT NULL,
	VAT_AMOUNT                NUMBER(14,2) NOT NULL,
	TOTAL_AMOUNT              NUMBER(14,2) NOT NULL,
	TRADING_PART              VARCHAR2(4),
	TEXT                      VARCHAR2(50),
	REGION                    VARCHAR2(7),
	NEW_CODE                  VARCHAR2(20),
	CUSTOMER_GROUP            VARCHAR2(10),
	CCTR                      VARCHAR2(7),
	CURR                      VARCHAR2(10),
	TAX_CODE                  VARCHAR2(2),
	SUBSCR_NO                 NUMBER(10),
	LOAD_DATE                 VARCHAR2(8),
	BILL_ORDER_NUMBER         VARCHAR2(50),
	SERVICE_PRIORITY_PRODUCT  VARCHAR2(20),
	RENTAL_CHARGE             NUMBER(14,2),
	USESAGE_CHARGE            NUMBER(14,2),
	PROCESS_DATE              VARCHAR2(10),
	SERVICE_CODE              VARCHAR2(20),
	REGION_SAP                VARCHAR2(7),
	CCTR_SAP                  VARCHAR2(10),
	SERVICE_PRIORITY          VARCHAR2(20) NOT NULL,
	CATEGORY                  VARCHAR2(10),
	POSTING_DATE              VARCHAR2(8),
	CALLS                     NUMBER(10),
	RATED_UNITS               NUMBER(24,2),
	PRIMARY_UNITS             NUMBER(24,2),
	SECONDARY_UNITS           NUMBER(24,2),
	THIRD_UNITS               NUMBER(24,2),
	PROPERTY1                 VARCHAR2(144),
	PROPERTY2                 VARCHAR2(144),
	CARRIER_CODE              VARCHAR2(10),
	CARRIER_NAME              VARCHAR2(5),
	constraint PK_INV_SUMMARY_SAP_IBACSSREF primary key (ID)
);

CREATE TABLE INV_SUMMARY_SAP_OTBOSS (
	ID                        INTEGER,
	CONTRNO                   VARCHAR2(15) NOT NULL,
	BI_REF                    VARCHAR2(50) NOT NULL,
	BILLGROUP                 VARCHAR2(10),
	ACCOUNT_CODE_OLD          VARCHAR2(40),
	ACCOUNT_CODE_NEW          VARCHAR2(20),
	SEGMENT_CODE              VARCHAR2(10),
	PRODUCT_CODE              VARCHAR2(15) NOT NULL,
	SUB_PRODUCT_CODE          VARCHAR2(15) NOT NULL,
	REVENUE_TYPE_CODE         VARCHAR2(10) NOT NULL,
	INVDATE                   VARCHAR2(8),
	DUEDATE                   VARCHAR2(8),
	PAYDATE                   VARCHAR2(8),
	PERIOD                    VARCHAR2(17),
	AMOUNT                    NUMBER(14,2) NOT NULL,
	VAT_AMOUNT                NUMBER(14,2) NOT NULL,
	TOTAL_AMOUNT              NUMBER(14,2) NOT NULL,
	TRADING_PART              VARCHAR2(4),
	TEXT                      VARCHAR2(9),
	REGION                    VARCHAR2(7),
	NEW_CODE                  VARCHAR2(20),
	CUSTOMER_GROUP            VARCHAR2(10),
	CCTR                      VARCHAR2(7),
	CURR                      VARCHAR2(10),
	TAX_CODE                  VARCHAR2(2),
	SUBSCR_NO                 NUMBER(10),
	LOAD_DATE                 VARCHAR2(20),
	BILL_ORDER_NUMBER         VARCHAR2(50),
	SERVICE_PRIORITY_PRODUCT  VARCHAR2(20),
	RENTAL_CHARGE             NUMBER(14,2),
	USESAGE_CHARGE            NUMBER(14,2),
	PROCESS_DATE              VARCHAR2(8),
	SERVICE_CODE              VARCHAR2(20),
	REGION_SAP                VARCHAR2(7),
	CCTR_SAP                  VARCHAR2(7),
	SERVICE_PRIORITY          VARCHAR2(20) NOT NULL,
	CATEGORY                  VARCHAR2(10),
	POSTING_DATE              VARCHAR2(8),
	CREATED_DATE              VARCHAR2(20),
	CREATED_BY                VARCHAR2(50),
	PROPERTY1                 VARCHAR2(30),
	PROPERTY2                 VARCHAR2(30),
	CARRIER_CODE              VARCHAR2(10),
	CARRIER_NAME              VARCHAR2(5),
	CALLS                     NUMBER(10),
	RATED_UNITS               NUMBER(24,2),
	PRIMARY_UNITS             NUMBER(24,2),
	SECONDARY_UNITS           NUMBER(24,2),
	THIRD_UNITS               NUMBER(24,2),
	KEY_REF                   VARCHAR2(50),
	UPDATE_DATETIME           VARCHAR2(20),
	constraint PK_INV_SUMMARY_SAP_OTBOSSREF primary key (ID)
);

CREATE TABLE INV_SUMMARY_SAP_TBOSS (
	ID                        INTEGER,
	CONTRNO                   VARCHAR2(15) NOT NULL,
	BI_REF                    NUMBER(15) NOT NULL,
	BILLGROUP                 VARCHAR2(25),
	ACCOUNT_CODE_OLD          VARCHAR2(40),
	ACCOUNT_CODE_NEW          VARCHAR2(20),
	SEGMENT_CODE              VARCHAR2(10),
	PRODUCT_CODE              VARCHAR2(15) NOT NULL,
	SUB_PRODUCT_CODE          VARCHAR2(15) NOT NULL,
	REVENUE_TYPE_CODE         VARCHAR2(10) NOT NULL,
	INVDATE                   VARCHAR2(8),
	DUEDATE                   VARCHAR2(8),
	PAYDATE                   VARCHAR2(8),
	PERIOD                    VARCHAR2(20),
	AMOUNT                    NUMBER(14,2) NOT NULL,
	VAT_AMOUNT                NUMBER(14,2) NOT NULL,
	TOTAL_AMOUNT              NUMBER(14,2) NOT NULL,
	TRADING_PART              VARCHAR2(4),
	TEXT                      VARCHAR2(9),
	REGION                    VARCHAR2(7),
	NEW_CODE                  VARCHAR2(20),
	CUSTOMER_GROUP            VARCHAR2(10),
	CCTR                      VARCHAR2(7),
	CURR                      VARCHAR2(10),
	TAX_CODE                  VARCHAR2(2),
	SUBSCR_NO                 NUMBER(10),
	LOAD_DATE                 VARCHAR2(8),
	BILL_ORDER_NUMBER         VARCHAR2(50),
	SERVICE_PRIORITY_PRODUCT  VARCHAR2(20) NOT NULL,
	RENTAL_CHARGE             NUMBER(14,2),
	USESAGE_CHARGE            NUMBER(14,2),
	PROCESS_DATE              VARCHAR2(8),
	SERVICE_CODE              VARCHAR2(20),
	REGION_SAP                VARCHAR2(7),
	CCTR_SAP                  VARCHAR2(7),
	SERVICE_PRIORITY          VARCHAR2(20),
	CATEGORY                  VARCHAR2(10),
	POSTING_DATE              VARCHAR2(8),
	PROPERTY1                 VARCHAR2(30),
	PROPERTY2                 VARCHAR2(30),
	CARRIER_CODE              VARCHAR2(10),
	CARRIER_NAME              VARCHAR2(5),
	CALLS                     NUMBER(10),
	RATED_UNITS               NUMBER(24,2),
	PRIMARY_UNITS             NUMBER(24,2),
	SECONDARY_UNITS           NUMBER(24,2),
	THIRD_UNITS               NUMBER(24,2),
	constraint PK_INV_SUMMARY_SAP_TBOSSREF primary key (ID)
);
-------------------------------------------------------------------------------
/*==============================================================*/
/* TBOSS Related Tables                                         */
/*==============================================================*/
CREATE TABLE CONTRACTTBOSS (
	CONTRACTTBOSSID		INTEGER NOT NULL,
	CONTRNO       		VARCHAR2(10) NOT NULL UNIQUE,   -- เลขที่สัญญา
	FIRSTNAME   	 	VARCHAR2(50) NOT NULL,    
	LASTNAME     		VARCHAR2(50) NOT NULL,      
	ADDRESS_1   		VARCHAR2(50) NOT NULL,   
	ADDRESS_2   		VARCHAR2(50),   
	ADDRESS_3   		VARCHAR2(50),   
	ADDRESS_4   		VARCHAR2(50),   
	ADDRESS_5   		VARCHAR2(50),   
	POSTCODE     		VARCHAR2(5),      
	REGION_NAME         VARCHAR2(50),   -- get from REGION.region_name
	CUSTOMER_TYPE       VARCHAR2(12),   -- get from CATEGORY.category_name
	BILL_GROUP        	VARCHAR2(10),
	CREDIT_LIMIT       	NUMBER(12,2),
	DEPOSIT            	NUMBER(12,2),       
	DEBT_AMOUNT         NUMBER(12,2),   -- total from ACCOUNT.ar_am_debt
	INVOICE_AMOUNT      NUMBER(12,2),   -- total from ACCOUNT.ar_am_loc
	VAT_AMOUNT          NUMBER(10,2),   -- total from ACCOUNT.ar_am_tax
	VOL_DISCOUNT        NUMBER(10,2),   
	USAGE_PERIOD        VARCHAR2(21),
	UPDATEDTTM          TIMESTAMP(6),
	UPDATESYSTEM        CHAR(3),        
	UPDATEUSER          VARCHAR2(32),   
	VERSIONSTAMP        NUMBER(38),
	constraint PK_CONTRACTTBOSS primary key (CONTRACTTBOSSID)
);

INSERT INTO "EPIS"."CONTRACTTBOSS" (CONTRACTTBOSSID, CONTRNO, FIRSTNAME, LASTNAME, ADDRESS_1, ADDRESS_2, ADDRESS_3, ADDRESS_4, POSTCODE, CUSTOMER_TYPE, BILL_GROUP, CREDIT_LIMIT, DEPOSIT, DEBT_AMOUNT, INVOICE_AMOUNT, VAT_AMOUNT, UPDATEDTTM, UPDATESYSTEM, UPDATEUSER, VERSIONSTAMP) 
VALUES ('1', '261038', 'นาง เสาวนี', 'ปิ่นเพ็ชร', '319/12 หมู่ที่ ม.6', 'แฟลตปลาทอง อาคาร 127', 'ถ.รังสิต-ปทุมธานี', 'ปทุมธานี', '12000', 'INDIVIDUAL', 'TL', '200000.00', '10000.00', '214000.00', '200000.00', '14000.00', TO_TIMESTAMP('2016-08-22 04:52:54.328000000', 'YYYY-MM-DD HH24:MI:SS.FF'), 'SER', 'SER1', '1');


CREATE TABLE ACCOUNTTBOSS (
	ACCOUNTTBOSSID   	INTEGER,
	AR_REF           	NUMBER(9) NOT NULL ,    
	CONTRNO          	VARCHAR2(10) NOT NULL , 
	AR_INVDATE          TIMESTAMP(6) NOT NULL, -- วันที่จัดทำใบแจ้งค่าบริการ
	AR_DUEDATE          TIMESTAMP(6) NOT NULL, -- วันที่ครบกำหนดชำระเงิน
	AR_AM_LOC           NUMBER(12,2), -- ยอดเงินรวมตามใบแจ้งหนี้
	TAX_TYPE_CODE       VARCHAR2(4),  -- reference from TAX_TYPE.tax_type_code
	AR_AM_TAX           NUMBER(12,2), -- ภาษีมูลค่าเพิ่ม
	AR_TEXT1            VARCHAR2(30), 
	AR_TEXT2            VARCHAR2(30), 
	AR_DD_STATUS        VARCHAR2(1),  
	DESCRIPTION         VARCHAR2(61), 
	SUB_NO              VARCHAR2(16), 
	CHEQUE_FLAG         VARCHAR2(1),  
	PAY_ADVANCE         NUMBER(12,2), 
	PAY_AMOUNT          NUMBER(12,2), 
	AR_AM_DEBT          NUMBER(12,2) NOT NULL, 
	RECEIVE_DATETIME    TIMESTAMP(6), 
	SERVICE_CODE        VARCHAR2(12), 
	POS_NO              VARCHAR2(30), 
	RESERVE_TIME        TIMESTAMP(6),
	UPDATEDTTM          TIMESTAMP(6),
	UPDATESYSTEM        CHAR(3),        
	UPDATEUSER          VARCHAR2(32),   
	VERSIONSTAMP        NUMBER(38),
	constraint PK_ACCOUNTTBOSS primary key (ACCOUNTTBOSSID)
);

alter table ACCOUNTTBOSS
   add constraint FK_ACCTTBOSS_CONTRNO foreign key (CONTRNO)
      references CONTRACTTBOSS (CONTRNO);

INSERT INTO "EPIS"."ACCOUNTTBOSS" (ACCOUNTTBOSSID, AR_REF, CONTRNO, AR_INVDATE, AR_DUEDATE, AR_AM_LOC, TAX_TYPE_CODE, AR_AM_TAX, AR_TEXT1, AR_TEXT2, AR_DD_STATUS, DESCRIPTION, PAY_ADVANCE, PAY_AMOUNT, AR_AM_DEBT, RECEIVE_DATETIME, POS_NO, RESERVE_TIME, UPDATEDTTM) 
VALUES ('1', '318538', '261038', TO_TIMESTAMP('2001-01-10 09:19:38.134000000', 'YYYY-MM-DD HH24:MI:SS.FF'), TO_TIMESTAMP('2001-01-31 09:19:48.981000000', 'YYYY-MM-DD HH24:MI:SS.FF'), '4337.57', 'V072', '283.77', 'B0012-2:        :0105666', 'TP215524558: B T', '0', 'B0012-2:        :0105666      TP215524558: B T', '0', '0', '4337.57', TO_TIMESTAMP('2006-07-17 15:28:21.412000000', 'YYYY-MM-DD HH24:MI:SS.FF'), 'AM_ARDB', TO_TIMESTAMP('2006-07-17 15:28:21.412000000', 'YYYY-MM-DD HH24:MI:SS.FF'), TO_TIMESTAMP('2006-07-17 15:28:21.412000000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO "EPIS"."ACCOUNTTBOSS" (ACCOUNTTBOSSID, AR_REF, CONTRNO, AR_INVDATE, AR_DUEDATE, AR_AM_LOC, TAX_TYPE_CODE, AR_AM_TAX, AR_TEXT1, AR_TEXT2, AR_DD_STATUS, DESCRIPTION, PAY_ADVANCE, PAY_AMOUNT, AR_AM_DEBT, RECEIVE_DATETIME, POS_NO, RESERVE_TIME, UPDATEDTTM) 
VALUES ('2', '318539', '261038', TO_TIMESTAMP('2001-01-25 09:19:38.134000000', 'YYYY-MM-DD HH24:MI:SS.FF'), TO_TIMESTAMP('2001-02-14 00:00:00.981000000', 'YYYY-MM-DD HH24:MI:SS.FF'), '2757.18', 'V072', '180.38', 'B0101-1:        :0400742', 'TP215524558: B', '0', 'B0101-1:        :0400742      TP215524558: B', '0', '0', '2757.18', TO_TIMESTAMP('2006-07-17 15:44:21.412000000', 'YYYY-MM-DD HH24:MI:SS.FF'), 'AM_ARDB', TO_TIMESTAMP('2006-07-17 15:44:21.412000000', 'YYYY-MM-DD HH24:MI:SS.FF'), TO_TIMESTAMP('2006-07-17 15:44:21.412000000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO "EPIS"."ACCOUNTTBOSS" (ACCOUNTTBOSSID, AR_REF, CONTRNO, AR_INVDATE, AR_DUEDATE, AR_AM_LOC, TAX_TYPE_CODE, AR_AM_TAX, AR_TEXT1, AR_TEXT2, AR_DD_STATUS, DESCRIPTION, PAY_ADVANCE, PAY_AMOUNT, AR_AM_DEBT, RECEIVE_DATETIME, POS_NO, RESERVE_TIME, UPDATEDTTM) 
VALUES ('3', '318540', '261038', TO_TIMESTAMP('2001-02-13 09:19:38.134000000', 'YYYY-MM-DD HH24:MI:SS.FF'), TO_TIMESTAMP('2001-03-05 00:00:00.981000000', 'YYYY-MM-DD HH24:MI:SS.FF'), '1766.57', 'V072', '115.57', 'B0101-2:        :0578485', 'TP215524558: B', '0', 'B0101-2:        :0578485      TP215524558: B', '0', '0', '1766.57', TO_TIMESTAMP('2006-07-17 15:48:21.412000000', 'YYYY-MM-DD HH24:MI:SS.FF'), 'AM_ARDB', TO_TIMESTAMP('2006-07-17 15:48:21.412000000', 'YYYY-MM-DD HH24:MI:SS.FF'), TO_TIMESTAMP('2006-07-17 15:48:21.412000000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO "EPIS"."ACCOUNTTBOSS" (ACCOUNTTBOSSID, AR_REF, CONTRNO, AR_INVDATE, AR_DUEDATE, AR_AM_LOC, TAX_TYPE_CODE, AR_AM_TAX, AR_TEXT1, AR_TEXT2, AR_DD_STATUS, DESCRIPTION, PAY_ADVANCE, PAY_AMOUNT, AR_AM_DEBT, RECEIVE_DATETIME, POS_NO, RESERVE_TIME, UPDATEDTTM) 
VALUES ('4', '318541', '261038', TO_TIMESTAMP('2001-02-25 09:19:38.134000000', 'YYYY-MM-DD HH24:MI:SS.FF'), TO_TIMESTAMP('2001-03-17 00:00:00.981000000', 'YYYY-MM-DD HH24:MI:SS.FF'), '1263.46', 'V072', '82.66', 'B0102-1:        :0105531', 'TP215524558: B', '0', 'B0102-1:        :0105531      TP215524558: B', '0', '0', '1263.46', TO_TIMESTAMP('2006-08-22 09:18:21.412000000', 'YYYY-MM-DD HH24:MI:SS.FF'), 'AM_ARDB', TO_TIMESTAMP('2006-08-22 09:18:21.412000000', 'YYYY-MM-DD HH24:MI:SS.FF'), TO_TIMESTAMP('2006-08-22 09:18:21.412000000', 'YYYY-MM-DD HH24:MI:SS.FF'));

CREATE TABLE TRSACCOUNTTBOSS (
	TRSACCOUNTTBOSSID   INTEGER,
	ACCOUNTTBOSSID      INTEGER NOT NULL,
	PAYMENTID           INTEGER NOT NULL,
	PAYDATETIME         TIMESTAMP(6) NOT NULL, -- วันที่จ่าย
	AMOUNT              NUMBER(12,2) NOT NULL, -- ยอดเงินที่ชำระ
	TAX                 NUMBER(12,2) NOT NULL, -- ภาษีมูลค่าเพิ่ม
	BALANCEDUE          NUMBER(12,2) NOT NULL, -- ยอดคงค้าง
	RECEIVED            NUMBER(12,2) NOT NULL,
	UPDATEDTTM          TIMESTAMP(6),
	UPDATESYSTEM        CHAR(3),
	UPDATEUSER          VARCHAR2(32),   
	VERSIONSTAMP        NUMBER(38),
	constraint PK_TRSACCOUNTTBOSS primary key (TRSACCOUNTTBOSSID)
);

alter table TRSACCOUNTTBOSS
   add constraint FK_TRSACCTTBOSS_ACCOUNTTBOSSID foreign key (ACCOUNTTBOSSID)
      references ACCOUNTTBOSS (ACCOUNTTBOSSID);
alter table TRSACCOUNTTBOSS
   add constraint FK_TRSACCTTBOSS_PAYMENTID foreign key (PAYMENTID)
      references CORPAYMENT (PAYMENTID);

-------------------------------------------------------------------------------
DELETE FROM ARCGRANT;
DELETE FROM ARCOPERATION;
DELETE FROM MASGL;
DELETE FROM MASPOS;
DELETE FROM COROFFICERPOS;
DELETE FROM ARCUSERAUTHNTICN;
DELETE FROM MASOFFICER;
DELETE FROM ARCPRINCIPAL;
insert into ARCPRINCIPAL values(1, 'ADMIN', 'desc', null, null, null, null);
insert into ARCPRINCIPAL values(2, 'BASIC', 'Allow to approve transactions', null, null, null, null);
insert into ARCPRINCIPAL values(3, 'AGENT', 'Allow to do payment transactions', null, null, null, null);
insert into ARCPRINCIPAL values(4, 'CUSTOMERSERVICE', 'desc', null, null, null, null);
insert into ARCPRINCIPAL values(5, 'CALLCENTER', 'desc', null, null, null, null);
insert into ARCPRINCIPAL values(6, 'USERAGENT', 'desc', null, null, null, null);
insert into ARCPRINCIPAL values(7, 'GFMISAGENT', 'Allow GFMIS transactions', null, null, null, null);
insert into MASGL values(1, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
-- MASPOS(POSID, SHOPID, POSNO, POSNAME)
insert into MASPOS values(1, 1, '01', 'EPIS00-1', '4C-34-88-D2-74-F9', 1, null, null, null, null, null);
insert into MASPOS values(2, 1, '02', 'EPIS00-2', '4C-34-88-D2-74-F9', 1, null, null, null, null, null);

-- MASOFFICER(OFFICERID, PRINCIPALID, SESSIONID, USERNAME, OFFICERCODE)
insert into MASOFFICER values(1, 1, null, 'EPIS0', 'EPIS0', 'ผู้ดูแลระบบ', 'ควบคุมงานดี', null, 1, null, null, null, null, null);
insert into MASOFFICER values(2, 3, null, 'EPIS1', 'EPIS1', 'สมชาย', 'ใจดี', null, 1, null, null, null, null, null);
insert into MASOFFICER values(3, 2, null, 'EPIS2', 'EPIS2', 'สมศรี', 'ใจงาม', null, 1, null, null, null, null, null);
insert into MASOFFICER values(4, 7, null, 'EPIS7', 'EPIS7', 'สมชาย', 'ใจดี', null, 1, null, null, null, null, null);

insert into COROFFICERPOS values(1, 1);
insert into COROFFICERPOS values(1, 2);
insert into COROFFICERPOS values(2, 1);
insert into COROFFICERPOS values(2, 2);
insert into COROFFICERPOS values(4, 1);
insert into COROFFICERPOS values(4, 2);

-- ARCUSERAUTHNTICN(USERAUTHNTICNID, OFFICERID)
insert into ARCUSERAUTHNTICN values(1, 1, '5f4dcc3b5aa765d61d8327deb882cf99', null, null, null, null);
insert into ARCUSERAUTHNTICN values(2, 2, '5f4dcc3b5aa765d61d8327deb882cf99', null, null, null, null);
insert into ARCUSERAUTHNTICN values(3, 3, '5f4dcc3b5aa765d61d8327deb882cf99', null, null, null, null);
insert into ARCUSERAUTHNTICN values(4, 4, '5f4dcc3b5aa765d61d8327deb882cf99', null, null, null, null);

insert into ARCOPERATION values(1, 'oper name', 'desc', null, null, null, null);
insert into ARCGRANT values(1, 1, 1, 1, null, null, null, null);

ALTER SEQUENCE enum_seq INCREMENT BY 169;
ALTER SEQUENCE principal_seq INCREMENT BY 8;
ALTER SEQUENCE officer_seq INCREMENT BY 5;
ALTER SEQUENCE authen_seq INCREMENT BY 5;
ALTER SEQUENCE pos_seq INCREMENT BY 3;
ALTER SEQUENCE shop_seq INCREMENT BY 2;
ALTER SEQUENCE contracttboss_seq INCREMENT BY 2;
ALTER SEQUENCE accounttboss_seq INCREMENT BY 5;
commit;