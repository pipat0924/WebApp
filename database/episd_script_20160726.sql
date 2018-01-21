/*==============================================================*/
/* DBMS name:      EPISDB Version 1.0                           */
/* Created on:     6/4/2559 0:19:05                             */
/*==============================================================*/

alter table ARCGRANT drop constraint FK_OPEGRA_OPERATIONID;
alter table ARCGRANT drop constraint FK_PRIGRA_PRINCIPALID;
alter table ARCUSERAUTHNTICN drop constraint fk_offuse_officerid;
alter table MASOFFICER drop constraint FK_SHOOFF_SHOPID;
alter table MASOFFICER drop constraint fk_prioff_principalid;
alter table CORREDUCEDEBT drop constraint FK_PAYRED_PAYMENTID;
alter table MASSHOP drop constraint FK_SHOGL_GLID;
alter table TMPACCOUNT drop constraint FK_PAYACC_PAYMENTID;
alter table TMPINVOICE drop constraint FK_ACCINV_ACCOUNTID;
alter table TMPINVOICE drop constraint FK_PAYINV_PAYMENTID;
alter table MASPOS drop constraint FK_SHOPOS_SHOPID;
alter table TMPINVOICESERVICE drop constraint fk_recinv_receiptid;
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
commit;

/*==============================================================*/
/* Table: ARCENUMS                                              */
/*==============================================================*/
create table ARCENUMS (
	ENUMID               INTEGER NOT NULL,
	CATEGORY             VARCHAR(40),
	ORDERNO              INTEGER not null,
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

INSERT INTO ARCENUMS VALUES(1, 'branch.central', 1, '00000', '1000', null, null, null, null, 'สำนักงานใหญ่', null, '', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(2, 'branch.central', 2, '00000', null, '1J60200', null, null, null, 'ฝ่ายขายและตลาดสื่อสารไร้สาย', null, 'ตร.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(3, 'branch.central', 3, '00000', '1011', '1J60203', null, null, null, 'สำนักงานใหญ่ ฝ่ายขายและตลาดสื่อสารไร้สาย (ส่วนขาย)', null, 'สต. (ขส)', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(4, 'branch.central', 4, '00000', '1012', '1J60205', null, null, null, 'สำนักงานใหญ่ ฝ่ายขายและตลาดสื่อสารไร้สาย (ส่วนบริหารคลังสินค้า)', null, 'สต. (คส)', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(5, 'branch.central', 5, '00000', null, '1J60100', null, null, null, 'ฝ่ายพัฒนาผลิตภัณฑ์โทรศัพท์', null, 'ทต.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(6, 'branch.central', 6, '00000', '1013', '1J60101', null, null, null, 'สำนักงานใหญ่ ฝ่ายพัฒนาผลิตภัณฑ์โทรศัพท์ (ส่วนการตลาดบริการโทรศัพท์)', null, 'ทต. (ตท)', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(7, 'branch.central', 7, '00000', '1014', '1J60105', null, null, null, 'สำนักงานใหญ่ ฝ่ายพัฒนาผลิตภัณฑ์โทรศัพท์ (ส่วนวิเคราะห์ข้อมูลลูกค้าและบริหารคลังสินค้า)', null, 'ทต. (ลท)', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(8, 'branch.central', 8, '00000', '1015', '1J60500', null, null, null, 'สำนักงานใหญ่ ฝ่ายเทคโนโลยีศูนย์ข้อมูล (ส่วนสนับสนุนการตลาด)', null, 'ศต.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(9, 'branch.central', 9, '00000', null, '1J00200', null, null, null, 'ฝ่ายบริหารลูกค้า', null, 'คต.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(10, 'business.place', 1, '01230', '1100', null, null, null, null, 'สำนักงานบริการลูกค้า กสท เขตกลาง', null, 'สข. (ก)', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(11, 'business.place', 2, '01130', '1101', '1J20220', null, null, null, 'สำนักงานบริการลูกค้า กสท พระนครศรีอยุธยา', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(12, 'business.place', 3, '01134', '1102', '1J20233', null, null, null, 'สำนักงานบริการลูกค้า กสท เพชรบูรณ์', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(13, 'business.place', 4, '01136', '1103', '1J20222', null, null, null, 'สำนักงานบริการลูกค้า กสท ลพบุรี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(14, 'business.place', 5, '01141', '1104', '1J20224', null, null, null, 'สำนักงานบริการลูกค้า กสท สิงห์บุรี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(15, 'business.place', 6, '01142', '1105', '1J20231', null, null, null, 'สำนักงานบริการลูกค้า กสท พิจิตร', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(16, 'business.place', 7, '01143', '1106', '1J20225', null, null, null, 'สำนักงานบริการลูกค้า กสท ชัยนาท', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(17, 'business.place', 8, '01145', '1107', '1J20230', null, null, null, 'สำนักงานบริการลูกค้า กสท กำแพงเพชร', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(18, 'business.place', 9, '01147', '1108', '1J20221', null, null, null, 'สำนักงานบริการลูกค้า กสท อ่างทอง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(19, 'business.place', 10, '01148', '1109', '1J20229', null, null, null, 'สำนักงานบริการลูกค้า กสท อุทัยธานี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(20, 'business.place', 11, '01149', '1110', '1J20235', null, null, null, 'สำนักงานบริการลูกค้า กสท หนองไผ่', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(21, 'business.place', 12, '01150', '1111', '1J20232', null, null, null, 'สำนักงานบริการลูกค้า กสท ตะพานหิน', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(22, 'business.place', 13, '01152', '1112', '1J20228', null, null, null, 'สำนักงานบริการลูกค้า กสท ตาคลี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(23, 'business.place', 14, '01221', '1113', '1J20227', null, null, null, 'สำนักงานบริการลูกค้า กสท นครสวรรค์', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(24, 'business.place', 15, '01222', '1114', '1J20226', null, null, null, 'สำนักงานบริการลูกค้า กสท สระบุรี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(25, 'business.place', 16, '01316', '1115', '1J20234', null, null, null, 'สำนักงานบริการลูกค้า กสท หล่มสัก', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(26, 'business.place', 17, '01338', '1116', '1J20223', null, null, null, 'สำนักงานบริการลูกค้า กสท ชัยบาดาล', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(27, 'business.place', 18, '01375', '1117', '1J20227', null, null, null, 'สำนักงานบริการลูกค้า กสท แคท ซีดีแอมเอ นครสวรรค์', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(28, 'business.place', 19, '01228', '1200', null, null, null, null, 'สำนักงานบริการลูกค้า กสท เขตตะวันออก', null, 'สข. (อ)', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(29, 'business.place', 20, '01189', '1201', '1J30220', null, null, null, 'สำนักงานบริการลูกค้า กสท ชลบุรี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(30, 'business.place', 21, '01190', '1202', '1J30228', null, null, null, 'สำนักงานบริการลูกค้า กสท ตราด', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(31, 'business.place', 22, '01191', '1203', '1J30224', null, null, null, 'สำนักงานบริการลูกค้า กสท ระยอง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(32, 'business.place', 23, '01192', '1204', '1J30230', null, null, null, 'สำนักงานบริการลูกค้า กสท ปราจีนบุรี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(33, 'business.place', 24, '01193', '1205', '1J30229', null, null, null, 'สำนักงานบริการลูกค้า กสท ฉะเชิงเทรา', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(34, 'business.place', 25, '01194', '1206', '1J30232', null, null, null, 'สำนักงานบริการลูกค้า กสท นครนายก', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(35, 'business.place', 26, '01195', '1207', '1J30221', null, null, null, 'สำนักงานบริการลูกค้า กสท สัตหีบ', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(36, 'business.place', 27, '01196', '1208', '1J30234', null, null, null, 'สำนักงานบริการลูกค้า กสท อรัญประเทศ', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(37, 'business.place', 28, '01197', '1209', '1J30231', null, null, null, 'สำนักงานบริการลูกค้า กสท กบินทร์บุรี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(38, 'business.place', 29, '01215', '1210', '1J30223', null, null, null, 'สำนักงานบริการลูกค้า กสท พัทยา', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(39, 'business.place', 30, '01232', '1211', '1J30227', null, null, null, 'สำนักงานบริการลูกค้า กสท จันทบุรี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(40, 'business.place', 31, '01237', '1212', '1J30222', null, null, null, 'สำนักงานบริการลูกค้า กสท แหลมฉบัง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(41, 'business.place', 32, '01246', '1213', '1J30226', null, null, null, 'สำนักงานบริการลูกค้า กสท มาบตาพุด', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(42, 'business.place', 33, '01278', '1214', '1J30233', null, null, null, 'สำนักงานบริการลูกค้า กสท สระแก้ว', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(43, 'business.place', 34, '01381', '1216', '1J30225', null, null, null, 'สำนักงานบริการลูกค้า กสท ปลวกแดง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(44, 'business.place', 35, '01231', '1300', null, null, null, null, 'สำนักงานบริการลูกค้า กสท เขตตะวันออกเฉลียงเหนือ', null, 'สข.  (อน)', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(45, 'business.place', 36, '01170', '1301', '1J30133', null, null, null, 'สำนักงานบริการลูกค้า กสท อุดรธานี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(46, 'business.place', 37, '01171', '1302', '1J30123', null, null, null, 'สำนักงานบริการลูกค้า กสท สุรินทร์', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(47, 'business.place', 38, '01172', '1303', '1J30140', null, null, null, 'สำนักงานบริการลูกค้า กสท สกลนคร', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(48, 'business.place', 39, '01173', '1304', '1J30125', null, null, null, 'สำนักงานบริการลูกค้า กสท อุบลราชธานี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(49, 'business.place', 40, '01174', '1305', '1J30142', null, null, null, 'สำนักงานบริการลูกค้า กสท มุกดาหาร', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(50, 'business.place', 41, '01175', '1306', '1J30124', null, null, null, 'สำนักงานบริการลูกค้า กสท ศรีสะเกษ', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(51, 'business.place', 42, '01176', '1307', '1J30138', null, null, null, 'สำนักงานบริการลูกค้า กสท ร้อยเอ็ด', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(52, 'business.place', 43, '01177', '1308', '1J30135', null, null, null, 'สำนักงานบริการลูกค้า กสท หนองคาย', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(53, 'business.place', 44, '01178', '1309', '1J30122', null, null, null, 'สำนักงานบริการลูกค้า กสท บุรีรัมย์', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(54, 'business.place', 45, '01179', '1310', '1J30134', null, null, null, 'สำนักงานบริการลูกค้า กสท เลย', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(55, 'business.place', 46, '01180', '1311', '1J30127', null, null, null, 'สำนักงานบริการลูกค้า กสท ชัยภูมิ', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(56, 'business.place', 47, '01181', '1312', '1J30137', null, null, null, 'สำนักงานบริการลูกค้า กสท มหาสารคาม', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(57, 'business.place', 48, '01182', '1313', '1J30139', null, null, null, 'สำนักงานบริการลูกค้า กสท กาฬสินธุ์', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(58, 'business.place', 49, '01183', '1314', '1J30126', null, null, null, 'สำนักงานบริการลูกค้า กสท ยโสธร', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(59, 'business.place', 50, '01184', '1315', '1J30141', null, null, null, 'สำนักงานบริการลูกค้า กสท นครพนม', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(60, 'business.place', 51, '01185', '1316', '1J30121', null, null, null, 'สำนักงานบริการลูกค้า กสท ปากช่อง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(61, 'business.place', 52, '01186', '1317', '1J30132', null, null, null, 'สำนักงานบริการลูกค้า กสท ชุมแพ', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(62, 'business.place', 53, '01187', '1318', '1J30128', null, null, null, 'สำนักงานบริการลูกค้า กสท อำนาจเจริญ', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(63, 'business.place', 54, '01188', '1319', '1J30131', null, null, null, 'สำนักงานบริการลูกค้า กสท บ้านไผ่', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(64, 'business.place', 55, '01223', '1320', '1J30120', null, null, null, 'สำนักงานบริการลูกค้า กสท นครราชสีมา', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(65, 'business.place', 56, '01224', '1321', '1J30130', null, null, null, 'สำนักงานบริการลูกค้า กสท ขอนแก่น', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(66, 'business.place', 57, '01276', '1322', '1J30136', null, null, null, 'สำนักงานบริการลูกค้า กสท บึงกาฬ', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(67, 'business.place', 58, '01323', '1323', '1J30129', null, null, null, 'สำนักงานบริการลูกค้า กสท หนองบัวลำภู', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(68, 'business.place', 59, '01360', '1324', '1J30135', null, null, null, 'สำนักงานบริการลูกค้า กสท หนองคาย', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(69, 'business.place', 60, '01361', '1325', '1J30130', null, null, null, 'สำนักงานบริการลูกค้า กสท ขอนแก่น', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(70, 'business.place', 61, '01373', '1327', '1J30130', null, null, null, 'สำนักงานบริการลูกค้า กสท แคท ซีดีเอ็มเอ ขอนแก่น', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(71, 'business.place', 62, '01383', '1328', '1J30133', null, null, null, 'สำนักงานบริการลูกค้า กสท อุดรธานี (เซ็นทรัล)', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(72, 'business.place', 63, '01390', '1329', '1J30125', null, null, null, 'สำนักงานบริการลูกค้า กสท อุบลราชธานี (เซ็นทรัล)', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(73, 'business.place', 64, '01227', '1400', null, null, null, null, 'สำนักงานบริการลูกค้า กสท เขตเหนือ', null, 'สข.  (น)', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(74, 'business.place', 65, '01154', '1401', '1J20129', null, null, null, 'สำนักงานบริการลูกค้า กสท เชียงราย', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(75, 'business.place', 66, '01155', '1402', '1J20126', null, null, null, 'สำนักงานบริการลูกค้า กสท แพร่', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(76, 'business.place', 67, '01156', '1403', '1J20127', null, null, null, 'สำนักงานบริการลูกค้า กสท น่าน', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(77, 'business.place', 68, '01157', '1404', '1J20136', null, null, null, 'สำนักงานบริการลูกค้า กสท สุโขทัย', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(78, 'business.place', 69, '01158', '1405', '1J20135', null, null, null, 'สำนักงานบริการลูกค้า กสท แม่สอด', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(79, 'business.place', 70, '01159', '1406', '1J20125', null, null, null, 'สำนักงานบริการลูกค้า กสท อุตรดิตถ์', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(80, 'business.place', 71, '01160', '1407', '1J20134', null, null, null, 'สำนักงานบริการลูกค้า กสท ตาก', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(81, 'business.place', 72, '01161', '1408', '1J20128', null, null, null, 'สำนักงานบริการลูกค้า กสท พะเยา', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(82, 'business.place', 73, '01162', '1409', '1J20122', null, null, null, 'สำนักงานบริการลูกค้า กสท ลำพูน', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(83, 'business.place', 74, '01162', '1410', '1J20137', null, null, null, 'สำนักงานบริการลูกค้า กสท สวรรคโลก', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(84, 'business.place', 75, '01164', '1411', '1J20130', null, null, null, 'สำนักงานบริการลูกค้า กสท แม่สาย', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(85, 'business.place', 76, '01166', '1412', '1J20121', null, null, null, 'สำนักงานบริการลูกค้า กสท ฝาง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(86, 'business.place', 77, '01167', '1413', '1J20132', null, null, null, 'สำนักงานบริการลูกค้า กสท แม่สะเรียง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(87, 'business.place', 78, '01168', '1414', '1J20124', null, null, null, 'สำนักงานบริการลูกค้า กสท เถิน', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(88, 'business.place', 79, '01169', '1415', '1J20131', null, null, null, 'สำนักงานบริการลูกค้า กสท แม่ฮ่องสอน', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(89, 'business.place', 80, '01216', '1416', '1J20120', null, null, null, 'สำนักงานบริการลูกค้า กสท เชียงใหม่', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(90, 'business.place', 81, '01217', '1417', '1J20123', null, null, null, 'สำนักงานบริการลูกค้า กสท ลำปาง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(91, 'business.place', 82, '01218', '1418', '1J20138', null, null, null, 'สำนักงานบริการลูกค้า กสท พิษณุโลก', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(92, 'business.place', 83, '01371', '1419', '1J20120', null, null, null, 'สำนักงานบริการลูกค้า กสท แคท ซีดีเอ็มเอ เชียงใหม่', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(93, 'business.place', 84, '01377', '1422', '1J20133', null, null, null, 'สำนักงานบริการลูกค้า กสท ปาย', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(94, 'business.place', 85, '01380', '1424', '1J20129', null, null, null, 'สำนักงานบริการลูกค้า กสท เชียงราย (เซ็นทรัล)', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(95, 'business.place', 86, '01382', '1425', '1J20138', null, null, null, 'สำนักงานบริการลูกค้า กสท พิษณุโลก (เซ็นทรัล)', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(96, 'business.place', 87, '01385', '1426', '1J20123', null, null, null, 'สำนักงานบริการลูกค้า กสท ลำปาง (เซ็นทรัล)', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(97, 'business.place', 88, '01387', '1427', '1J20120', null, null, null, 'สำนักงานบริการลูกค้า กสท เชียงใหม่ (เม-ญ่า เชียงใหม่)', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(98, 'business.place', 89, '01229', '1500', null, null, null, null, 'สำนักงานบริการลูกค้า กสท เขตใต้', null, 'สข.  (ต)', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(99, 'business.place', 90, '01198', '1501', '1J10120', null, null, null, 'สำนักงานบริการลูกค้า กสท นครศรีธรรมราช', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(100, 'business.place', 91, '01199', '1502', '1J10135', null, null, null, 'สำนักงานบริการลูกค้า กสท ยะลา', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(101, 'business.place', 92, '01200', '1503', '1J10122', null, null, null, 'สำนักงานบริการลูกค้า กสท กระบี่', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(102, 'business.place', 93, '01201', '1504', '1J10121', null, null, null, 'สำนักงานบริการลูกค้า กสท ทุ่งสง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(103, 'business.place', 94, '01202', '1505', '1J10137', null, null, null, 'สำนักงานบริการลูกค้า กสท นราธิวาส', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(104, 'business.place', 95, '01203', '1506', '1J10132', null, null, null, 'สำนักงานบริการลูกค้า กสท ตรัง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(105, 'business.place', 96, '01205', '1507', '1J10128', null, null, null, 'สำนักงานบริการลูกค้า กสท เกาะสมุย', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(106, 'business.place', 97, '01206', '1508', '1J10124', null, null, null, 'สำนักงานบริการลูกค้า กสท ตะกั่วป่า', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(107, 'business.place', 98, '01208', '1509', '1J10131', null, null, null, 'สำนักงานบริการลูกค้า กสท สตูล', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(108, 'business.place', 99, '01209', '1510', '1J10134', null, null, null, 'สำนักงานบริการลูกค้า กสท ปัตตานี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(109, 'business.place', 100, '01211', '1511', '1J10133', null, null, null, 'สำนักงานบริการลูกค้า กสท พัทลุง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(110, 'business.place', 101, '01213', '1512', '1J10136', null, null, null, 'สำนักงานบริการลูกค้า กสท เบตง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(111, 'business.place', 102, '01214', '1513', '1J10123', null, null, null, 'สำนักงานบริการลูกค้า กสท พังงา', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(112, 'business.place', 103, '01219', '1514', '1J10130', null, null, null, 'สำนักงานบริการลูกค้า กสท หาดใหญ่', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(113, 'business.place', 104, '01220', '1515', '1J10125', null, null, null, 'สำนักงานบริการลูกค้า กสท ภูเก็ต', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(114, 'business.place', 105, '01225', '1516', '1J10127', null, null, null, 'สำนักงานบริการลูกค้า กสท สุราษฎร์ธานี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(115, 'business.place', 106, '01252', '1517', '1J10126', null, null, null, 'สำนักงานบริการลูกค้า กสท ป่าตอง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(116, 'business.place', 107, '01328', '1518', '1J10129', null, null, null, 'สำนักงานบริการลูกค้า กสท สงขลา', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(117, 'business.place', 108, '01366', '1519', '1J10125', null, null, null, 'สำนักงานบริการลูกค้า กสท แคท ซีดีเอ็มเอ ภูเก็ต', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(118, 'business.place', 109, '01376', '1520', '1J10130', null, null, null, 'สำนักงานบริการลูกค้า กสท CAT Shop หาดใหญ่', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(119, 'business.place', 110, '01386', '1521', '1J10127', null, null, null, 'สำนักงานบริการลูกค้า กสท สุราษฎร์ธานี (เซ็นทรัล)', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(120, 'business.place', 111, '01264', '1600', null, null, null, null, 'สำนักงานบริการลูกค้า กสท เขตตะวันตก', null, 'สข.  (ตต)', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(121, 'business.place', 112, '01131', '1601', '1J10224', null, null, null, 'สำนักงานบริการลูกค้า กสท นครปฐม', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(122, 'business.place', 113, '01132', '1602', '1J10230', null, null, null, 'สำนักงานบริการลูกค้า กสท ประจวบคีรีขันธ์', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(123, 'business.place', 114, '01133', '1603', '1J10231', null, null, null, 'สำนักงานบริการลูกค้า กสท หัวหิน', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(124, 'business.place', 115, '01135', '1604', '1J10221', null, null, null, 'สำนักงานบริการลูกค้า กสท บ้านโป่ง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(125, 'business.place', 116, '01137', '1605', '1J10225', null, null, null, 'สำนักงานบริการลูกค้า กสท สมุทรสาคร', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(126, 'business.place', 117, '01138', '1606', '1J10223', null, null, null, 'สำนักงานบริการลูกค้า กสท สุพรรณบุรี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(127, 'business.place', 118, '01139', '1607', '1J10222', null, null, null, 'สำนักงานบริการลูกค้า กสท กาญจนบุรี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(128, 'business.place', 119, '01140', '1608', '1J10220', null, null, null, 'สำนักงานบริการลูกค้า กสท ราชบุรี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(129, 'business.place', 120, '01144', '1609', '1J10228', null, null, null, 'สำนักงานบริการลูกค้า กสท เพชรบุรี', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(130, 'business.place', 121, '01146', '1610', '1J10227', null, null, null, 'สำนักงานบริการลูกค้า กสท สมุทรสงคราม', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(131, 'business.place', 122, '01204', '1611', '1J10232', null, null, null, 'สำนักงานบริการลูกค้า กสท ระนอง', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(132, 'business.place', 123, '01207', '1612', '1J10233', null, null, null, 'สำนักงานบริการลูกค้า กสท ชุมพร', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(133, 'business.place', 124, '01317', '1613', '1J10226', null, null, null, 'สำนักงานบริการลูกค้า กสท กระทุ่มแบน', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(134, 'business.place', 125, '01318', '1614', '1J10229', null, null, null, 'สำนักงานบริการลูกค้า กสท ชะอำ', null, 'สค.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(135, 'business.place', 126, '00000', '1700', '1J60400', null, null, null, 'ฝ่ายพัฒนาธุรกิจการตลาดลูกค้ารายย่อย', null, 'ยต.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(136, 'business.place', 127, '01330', '1701', '1J60409', null, null, null, 'ศูนย์บริการลูกค้า บางรัก (อาคาร 16)', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(137, 'business.place', 128, '01333', '1702', '1J60410', null, null, null, 'ศูนย์บริการลูกค้า สามเสนใน', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(138, 'business.place', 129, '01334', '1703', '1J60411', null, null, null, 'ศูนย์บริการลูกค้า มีนบุรี', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(139, 'business.place', 130, '01335', '1704', '1J60414', null, null, null, 'ศูนย์บริการลูกค้า นนทบุรี', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(140, 'business.place', 131, '01336', '1705', '1J60409', null, null, null, 'ศูนย์บริการลูกค้า ราชดำเนิน', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(141, 'business.place', 132, '01337', '1706', '1J60412', null, null, null, 'ศูนย์บริการลูกค้า สมุทรปราการ', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(142, 'business.place', 133, '01343', '1707', '1J60413', null, null, null, 'ศูนย์บริการลูกค้า เซ็นทรัลพลาซ่า พระราม 2', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(143, 'business.place', 134, '01344', '1708', '1J60412', null, null, null, 'ศูนย์บริการลูกค้า สุขุมวิท', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(144, 'business.place', 135, '01345', '1709', '1J60411', null, null, null, 'ศูนย์บริการลูกค้า เอ็นมาร์คพลาซ่า', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(145, 'business.place', 136, '01346', '1710', '1J60411', null, null, null, 'ศูนย์บริการลูกค้า เดอะมอลล์รามคำแหง', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(146, 'business.place', 137, '01347', '1711', '1J60413', null, null, null, 'ศูนย์บริการลูกค้า เดอะมอลล์บางแค', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(147, 'business.place', 138, '01348', '1712', '1J60409', null, null, null, 'ศูนย์บริการลูกค้า เดอะมอลล์ท่าพระ', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(148, 'business.place', 139, '01350', '1713', '1J60414', null, null, null, 'ศูนย์บริการลูกค้า คลองหลวง', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(149, 'business.place', 140, '01351', '1714', '1J60409', null, null, null, 'ศูนย์บริการลูกค้า ยานนาวา', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(150, 'business.place', 141, '01370', '1715', '1J60415', null, null, null, 'CAT TELECOM INTERNATIONAL TRAN', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(151, 'business.place', 142, '01379', '1716', '1J60410', null, null, null, 'ศูนย์บริการลูกค้า จัตุรัสจามจุรี', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(152, 'business.place', 143, '00000', '1717', '1J60410', null, null, null, 'ศูนย์บริการลูกค้า แจ้งวัฒนะ', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(153, 'business.place', 144, '01349', '1719', '1J60409', null, null, null, 'ศูนย์บริการลูกค้า อาคาร CAT TOWER', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(154, 'business.place', 145, '01384', '1720', null, null, null, null, 'ศูนย์บริการลูกค้า อื้อจือเหลียง', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(155, 'business.place', 146, '01388', '1721', '1J60413', null, null, null, 'ศูนย์บริการลูกค้า ธนบุรี', null, 'ศบล.', null, 'SYS', null, 1);
INSERT INTO ARCENUMS VALUES(156, 'business.place', 147, '01389', '1722', '1J60412', null, null, null, 'ศูนย์บริการลูกค้า เมกา บางนา', null, 'ศบล.', null, 'SYS', null, 1);

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
   IP                   VARCHAR(20),
   MAC                  VARCHAR(100),
   ISPOSITIVE           INTEGER,
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
	GLID                 INTEGER,
	SHOPNO               VARCHAR(10),
	SHOPNAME             VARCHAR(1000),
	ISPOSITIVE           INTEGER,
	BUPLACE              VARCHAR(10),
	BUAREA               VARCHAR(10),
	COSTCENTER           VARCHAR(10),
	CASHINHAND           VARCHAR(10),
	UPDATEDTTM           TIMESTAMP,
	UPDATESYSTEM         CHAR(3),
	UPDATEUSER           VARCHAR(32),
	VERSIONSTAMP         INTEGER,
	constraint PK_MASSHOP primary key (SHOPID)
);
insert into MASSHOP values(1, null, '01230', 'สำนักงานบริการลูกค้า กสท เขตกลาง', 1, '01230', '1100', null, null, null, null, null, null);
insert into MASSHOP values(2, null, '01130', 'สำนักงานบริการลูกค้า กสท พระนครศรีอยุธยา', 1, '01130', '1101', '1J20220', null, null, null, null, null);
insert into MASSHOP values(3, null, '01134', 'สำนักงานบริการลูกค้า กสท เพชรบูรณ์', 1, '01134', '1102', '1J20233', null, null, null, null, null);
insert into MASSHOP values(4, null, '01136', 'สำนักงานบริการลูกค้า กสท ลพบุรี', 1, '01136', '1103', '1J20222', null, null, null, null, null);
insert into MASSHOP values(5, null, '01141', 'สำนักงานบริการลูกค้า กสท สิงห์บุรี', 1, '01141', '1104', '1J20224', null, null, null, null, null);
insert into MASSHOP values(6, null, '01142', 'สำนักงานบริการลูกค้า กสท พิจิตร', 1, '01142', '1105', '1J20231', null, null, null, null, null);
insert into MASSHOP values(7, null, '01143', 'สำนักงานบริการลูกค้า กสท ชัยนาท', 1, '01143', '1106', '1J20225', null, null, null, null, null);
insert into MASSHOP values(8, null, '01145', 'สำนักงานบริการลูกค้า กสท กำแพงเพชร', 1, '01145', '1107', '1J20230', null, null, null, null, null);
insert into MASSHOP values(9, null, '01147', 'สำนักงานบริการลูกค้า กสท อ่างทอง', 1, '01147', '1108', '1J20221', null, null, null, null, null);
insert into MASSHOP values(10, null, '01148', 'สำนักงานบริการลูกค้า กสท อุทัยธานี', 1, '01148', '1109', '1J20229', null, null, null, null, null);
insert into MASSHOP values(11, null, '01149', 'สำนักงานบริการลูกค้า กสท หนองไผ่', 1, '01149', '1110', '1J20235', null, null, null, null, null);
insert into MASSHOP values(12, null, '01150', 'สำนักงานบริการลูกค้า กสท ตะพานหิน', 1, '01150', '1111', '1J20232', null, null, null, null, null);
insert into MASSHOP values(13, null, '01152', 'สำนักงานบริการลูกค้า กสท ตาคลี', 1, '01152', '1112', '1J20228', null, null, null, null, null);
insert into MASSHOP values(14, null, '01221', 'สำนักงานบริการลูกค้า กสท นครสวรรค์', 1, '01221', '1113', '1J20227', null, null, null, null, null);
insert into MASSHOP values(15, null, '01222', 'สำนักงานบริการลูกค้า กสท สระบุรี', 1, '01222', '1114', '1J20226', null, null, null, null, null);
insert into MASSHOP values(16, null, '01316', 'สำนักงานบริการลูกค้า กสท หล่มสัก', 1, '01316', '1115', '1J20234', null, null, null, null, null);
insert into MASSHOP values(17, null, '01338', 'สำนักงานบริการลูกค้า กสท ชัยบาดาล', 1, '01338', '1116', '1J20223', null, null, null, null, null);
insert into MASSHOP values(18, null, '01375', 'สำนักงานบริการลูกค้า กสท แคท ซีดีแอมเอ นครสวรรค์', 1, '01375', '1117', '1J20227', null, null, null, null, null);
insert into MASSHOP values(19, null, '01228', 'สำนักงานบริการลูกค้า กสท เขตตะวันออก', 1, '01228', '1200', null, null, null, null, null, null);
insert into MASSHOP values(20, null, '01189', 'สำนักงานบริการลูกค้า กสท ชลบุรี', 1, '01189', '1201', '1J30220', null, null, null, null, null);
insert into MASSHOP values(21, null, '01190', 'สำนักงานบริการลูกค้า กสท ตราด', 1, '01190', '1202', '1J30228', null, null, null, null, null);
insert into MASSHOP values(22, null, '01191', 'สำนักงานบริการลูกค้า กสท ระยอง', 1, '01191', '1203', '1J30224', null, null, null, null, null);
insert into MASSHOP values(23, null, '01192', 'สำนักงานบริการลูกค้า กสท ปราจีนบุรี', 1, '01192', '1204', '1J30230', null, null, null, null, null);
insert into MASSHOP values(24, null, '01193', 'สำนักงานบริการลูกค้า กสท ฉะเชิงเทรา', 1, '01193', '1205', '1J30229', null, null, null, null, null);
insert into MASSHOP values(25, null, '01194', 'สำนักงานบริการลูกค้า กสท นครนายก', 1, '01194', '1206', '1J30232', null, null, null, null, null);
insert into MASSHOP values(26, null, '01195', 'สำนักงานบริการลูกค้า กสท สัตหีบ', 1, '01195', '1207', '1J30221', null, null, null, null, null);
insert into MASSHOP values(27, null, '01196', 'สำนักงานบริการลูกค้า กสท อรัญประเทศ', 1, '01196', '1208', '1J30234', null, null, null, null, null);
insert into MASSHOP values(28, null, '01197', 'สำนักงานบริการลูกค้า กสท กบินทร์บุรี', 1, '01197', '1209', '1J30231', null, null, null, null, null);
insert into MASSHOP values(29, null, '01215', 'สำนักงานบริการลูกค้า กสท พัทยา', 1, '01215', '1210', '1J30223', null, null, null, null, null);
insert into MASSHOP values(30, null, '01232', 'สำนักงานบริการลูกค้า กสท จันทบุรี', 1, '01232', '1211', '1J30227', null, null, null, null, null);
insert into MASSHOP values(31, null, '01237', 'สำนักงานบริการลูกค้า กสท แหลมฉบัง', 1, '01237', '1212', '1J30222', null, null, null, null, null);
insert into MASSHOP values(32, null, '01246', 'สำนักงานบริการลูกค้า กสท มาบตาพุด', 1, '01246', '1213', '1J30226', null, null, null, null, null);
insert into MASSHOP values(33, null, '01278', 'สำนักงานบริการลูกค้า กสท สระแก้ว', 1, '01278', '1214', '1J30233', null, null, null, null, null);
insert into MASSHOP values(34, null, '01381', 'สำนักงานบริการลูกค้า กสท ปลวกแดง', 1, '01381', '1216', '1J30225', null, null, null, null, null);
insert into MASSHOP values(35, null, '01231', 'สำนักงานบริการลูกค้า กสท เขตตะวันออกเฉลียงเหนือ', 1, '01231', '1300', null, null, null, null, null, null);
insert into MASSHOP values(36, null, '01170', 'สำนักงานบริการลูกค้า กสท อุดรธานี', 1, '01170', '1301', '1J30133', null, null, null, null, null);
insert into MASSHOP values(37, null, '01171', 'สำนักงานบริการลูกค้า กสท สุรินทร์', 1, '01171', '1302', '1J30123', null, null, null, null, null);
insert into MASSHOP values(38, null, '01172', 'สำนักงานบริการลูกค้า กสท สกลนคร', 1, '01172', '1303', '1J30140', null, null, null, null, null);
insert into MASSHOP values(39, null, '01173', 'สำนักงานบริการลูกค้า กสท อุบลราชธานี', 1, '01173', '1304', '1J30125', null, null, null, null, null);
insert into MASSHOP values(40, null, '01174', 'สำนักงานบริการลูกค้า กสท มุกดาหาร', 1, '01174', '1305', '1J30142', null, null, null, null, null);
insert into MASSHOP values(41, null, '01175', 'สำนักงานบริการลูกค้า กสท ศรีสะเกษ', 1, '01175', '1306', '1J30124', null, null, null, null, null);
insert into MASSHOP values(42, null, '01176', 'สำนักงานบริการลูกค้า กสท ร้อยเอ็ด', 1, '01176', '1307', '1J30138', null, null, null, null, null);
insert into MASSHOP values(43, null, '01177', 'สำนักงานบริการลูกค้า กสท หนองคาย', 1, '01177', '1308', '1J30135', null, null, null, null, null);
insert into MASSHOP values(44, null, '01178', 'สำนักงานบริการลูกค้า กสท บุรีรัมย์', 1, '01178', '1309', '1J30122', null, null, null, null, null);
insert into MASSHOP values(45, null, '01179', 'สำนักงานบริการลูกค้า กสท เลย', 1, '01179', '1310', '1J30134', null, null, null, null, null);
insert into MASSHOP values(46, null, '01180', 'สำนักงานบริการลูกค้า กสท ชัยภูมิ', 1, '01180', '1311', '1J30127', null, null, null, null, null);
insert into MASSHOP values(47, null, '01181', 'สำนักงานบริการลูกค้า กสท มหาสารคาม', 1, '01181', '1312', '1J30137', null, null, null, null, null);
insert into MASSHOP values(48, null, '01182', 'สำนักงานบริการลูกค้า กสท กาฬสินธุ์', 1, '01182', '1313', '1J30139', null, null, null, null, null);
insert into MASSHOP values(49, null, '01183', 'สำนักงานบริการลูกค้า กสท ยโสธร', 1, '01183', '1314', '1J30126', null, null, null, null, null);
insert into MASSHOP values(50, null, '01184', 'สำนักงานบริการลูกค้า กสท นครพนม', 1, '01184', '1315', '1J30141', null, null, null, null, null);
insert into MASSHOP values(51, null, '01185', 'สำนักงานบริการลูกค้า กสท ปากช่อง', 1, '01185', '1316', '1J30121', null, null, null, null, null);
insert into MASSHOP values(52, null, '01186', 'สำนักงานบริการลูกค้า กสท ชุมแพ', 1, '01186', '1317', '1J30132', null, null, null, null, null);
insert into MASSHOP values(53, null, '01187', 'สำนักงานบริการลูกค้า กสท อำนาจเจริญ', 1, '01187', '1318', '1J30128', null, null, null, null, null);
insert into MASSHOP values(54, null, '01188', 'สำนักงานบริการลูกค้า กสท บ้านไผ่', 1, '01188', '1319', '1J30131', null, null, null, null, null);
insert into MASSHOP values(55, null, '01223', 'สำนักงานบริการลูกค้า กสท นครราชสีมา', 1, '01223', '1320', '1J30120', null, null, null, null, null);
insert into MASSHOP values(56, null, '01224', 'สำนักงานบริการลูกค้า กสท ขอนแก่น', 1, '01224', '1321', '1J30130', null, null, null, null, null);
insert into MASSHOP values(57, null, '01276', 'สำนักงานบริการลูกค้า กสท บึงกาฬ', 1, '01276', '1322', '1J30136', null, null, null, null, null);
insert into MASSHOP values(58, null, '01323', 'สำนักงานบริการลูกค้า กสท หนองบัวลำภู', 1, '01323', '1323', '1J30129', null, null, null, null, null);
insert into MASSHOP values(59, null, '01360', 'สำนักงานบริการลูกค้า กสท หนองคาย', 1, '01360', '1324', '1J30135', null, null, null, null, null);
insert into MASSHOP values(60, null, '01361', 'สำนักงานบริการลูกค้า กสท ขอนแก่น', 1, '01361', '1325', '1J30130', null, null, null, null, null);
insert into MASSHOP values(61, null, '01373', 'สำนักงานบริการลูกค้า กสท แคท ซีดีเอ็มเอ ขอนแก่น', 1, '01373', '1327', '1J30130', null, null, null, null, null);
insert into MASSHOP values(62, null, '01383', 'สำนักงานบริการลูกค้า กสท อุดรธานี (เซ็นทรัล)', 1, '01383', '1328', '1J30133', null, null, null, null, null);
insert into MASSHOP values(63, null, '01390', 'สำนักงานบริการลูกค้า กสท อุบลราชธานี (เซ็นทรัล)', 1, '01390', '1329', '1J30125', null, null, null, null, null);
insert into MASSHOP values(64, null, '01227', 'สำนักงานบริการลูกค้า กสท เขตเหนือ', 1, '01227', '1400', null, null, null, null, null, null);
insert into MASSHOP values(65, null, '01154', 'สำนักงานบริการลูกค้า กสท เชียงราย', 1, '01154', '1401', '1J20129', null, null, null, null, null);
insert into MASSHOP values(66, null, '01155', 'สำนักงานบริการลูกค้า กสท แพร่', 1, '01155', '1402', '1J20126', null, null, null, null, null);
insert into MASSHOP values(67, null, '01156', 'สำนักงานบริการลูกค้า กสท น่าน', 1, '01156', '1403', '1J20127', null, null, null, null, null);
insert into MASSHOP values(68, null, '01157', 'สำนักงานบริการลูกค้า กสท สุโขทัย', 1, '01157', '1404', '1J20136', null, null, null, null, null);
insert into MASSHOP values(69, null, '01158', 'สำนักงานบริการลูกค้า กสท แม่สอด', 1, '01158', '1405', '1J20135', null, null, null, null, null);
insert into MASSHOP values(70, null, '01159', 'สำนักงานบริการลูกค้า กสท อุตรดิตถ์', 1, '01159', '1406', '1J20125', null, null, null, null, null);
insert into MASSHOP values(71, null, '01160', 'สำนักงานบริการลูกค้า กสท ตาก', 1, '01160', '1407', '1J20134', null, null, null, null, null);
insert into MASSHOP values(72, null, '01161', 'สำนักงานบริการลูกค้า กสท พะเยา', 1, '01161', '1408', '1J20128', null, null, null, null, null);
insert into MASSHOP values(73, null, '01162', 'สำนักงานบริการลูกค้า กสท ลำพูน', 1, '01162', '1409', '1J20122', null, null, null, null, null);
insert into MASSHOP values(74, null, '01163', 'สำนักงานบริการลูกค้า กสท สวรรคโลก', 1, '01163', '1410', '1J20137', null, null, null, null, null);
insert into MASSHOP values(75, null, '01164', 'สำนักงานบริการลูกค้า กสท แม่สาย', 1, '01164', '1411', '1J20130', null, null, null, null, null);
insert into MASSHOP values(76, null, '01166', 'สำนักงานบริการลูกค้า กสท ฝาง', 1, '01166', '1412', '1J20121', null, null, null, null, null);
insert into MASSHOP values(77, null, '01167', 'สำนักงานบริการลูกค้า กสท แม่สะเรียง', 1, '01167', '1413', '1J20132', null, null, null, null, null);
insert into MASSHOP values(78, null, '01168', 'สำนักงานบริการลูกค้า กสท เถิน', 1, '01168', '1414', '1J20124', null, null, null, null, null);
insert into MASSHOP values(79, null, '01169', 'สำนักงานบริการลูกค้า กสท แม่ฮ่องสอน', 1, '01169', '1415', '1J20131', null, null, null, null, null);
insert into MASSHOP values(80, null, '01216', 'สำนักงานบริการลูกค้า กสท เชียงใหม่', 1, '01216', '1416', '1J20120', null, null, null, null, null);
insert into MASSHOP values(81, null, '01217', 'สำนักงานบริการลูกค้า กสท ลำปาง', 1, '01217', '1417', '1J20123', null, null, null, null, null);
insert into MASSHOP values(82, null, '01218', 'สำนักงานบริการลูกค้า กสท พิษณุโลก', 1, '01218', '1418', '1J20138', null, null, null, null, null);
insert into MASSHOP values(83, null, '01371', 'สำนักงานบริการลูกค้า กสท แคท ซีดีเอ็มเอ เชียงใหม่', 1, '01371', '1419', '1J20120', null, null, null, null, null);
insert into MASSHOP values(84, null, '01377', 'สำนักงานบริการลูกค้า กสท ปาย', 1, '01377', '1422', '1J20133', null, null, null, null, null);
insert into MASSHOP values(85, null, '01380', 'สำนักงานบริการลูกค้า กสท เชียงราย (เซ็นทรัล)', 1, '01380', '1424', '1J20129', null, null, null, null, null);
insert into MASSHOP values(86, null, '01382', 'สำนักงานบริการลูกค้า กสท พิษณุโลก (เซ็นทรัล)', 1, '01382', '1425', '1J20138', null, null, null, null, null);
insert into MASSHOP values(87, null, '01385', 'สำนักงานบริการลูกค้า กสท ลำปาง (เซ็นทรัล)', 1, '01385', '1426', '1J20123', null, null, null, null, null);
insert into MASSHOP values(88, null, '01387', 'สำนักงานบริการลูกค้า กสท เชียงใหม่ (เม-ญ่า เชียงใหม่)', 1, '01387', '1427', '1J20120', null, null, null, null, null);
insert into MASSHOP values(89, null, '01229', 'สำนักงานบริการลูกค้า กสท เขตใต้', 1, '01229', '1500', null, null, null, null, null, null);
insert into MASSHOP values(90, null, '01198', 'สำนักงานบริการลูกค้า กสท นครศรีธรรมราช', 1, '01198', '1501', '1J10120', null, null, null, null, null);
insert into MASSHOP values(91, null, '01199', 'สำนักงานบริการลูกค้า กสท ยะลา', 1, '01199', '1502', '1J10135', null, null, null, null, null);
insert into MASSHOP values(92, null, '01200', 'สำนักงานบริการลูกค้า กสท กระบี่', 1, '01200', '1503', '1J10122', null, null, null, null, null);
insert into MASSHOP values(93, null, '01201', 'สำนักงานบริการลูกค้า กสท ทุ่งสง', 1, '01201', '1504', '1J10121', null, null, null, null, null);
insert into MASSHOP values(94, null, '01202', 'สำนักงานบริการลูกค้า กสท นราธิวาส', 1, '01202', '1505', '1J10137', null, null, null, null, null);
insert into MASSHOP values(95, null, '01203', 'สำนักงานบริการลูกค้า กสท ตรัง', 1, '01203', '1506', '1J10132', null, null, null, null, null);
insert into MASSHOP values(96, null, '01205', 'สำนักงานบริการลูกค้า กสท เกาะสมุย', 1, '01205', '1507', '1J10128', null, null, null, null, null);
insert into MASSHOP values(97, null, '01206', 'สำนักงานบริการลูกค้า กสท ตะกั่วป่า', 1, '01206', '1508', '1J10124', null, null, null, null, null);
insert into MASSHOP values(98, null, '01208', 'สำนักงานบริการลูกค้า กสท สตูล', 1, '01208', '1509', '1J10131', null, null, null, null, null);
insert into MASSHOP values(99, null, '01209', 'สำนักงานบริการลูกค้า กสท ปัตตานี', 1, '01209', '1510', '1J10134', null, null, null, null, null);
insert into MASSHOP values(100, null, '01211', 'สำนักงานบริการลูกค้า กสท พัทลุง', 1, '01211', '1511', '1J10133', null, null, null, null, null);
insert into MASSHOP values(101, null, '01213', 'สำนักงานบริการลูกค้า กสท เบตง', 1, '01213', '1512', '1J10136', null, null, null, null, null);
insert into MASSHOP values(102, null, '01214', 'สำนักงานบริการลูกค้า กสท พังงา', 1, '01214', '1513', '1J10123', null, null, null, null, null);
insert into MASSHOP values(103, null, '01219', 'สำนักงานบริการลูกค้า กสท หาดใหญ่', 1, '01219', '1514', '1J10130', null, null, null, null, null);
insert into MASSHOP values(104, null, '01220', 'สำนักงานบริการลูกค้า กสท ภูเก็ต', 1, '01220', '1515', '1J10125', null, null, null, null, null);
insert into MASSHOP values(105, null, '01225', 'สำนักงานบริการลูกค้า กสท สุราษฎร์ธานี', 1, '01225', '1516', '1J10127', null, null, null, null, null);
insert into MASSHOP values(106, null, '01252', 'สำนักงานบริการลูกค้า กสท ป่าตอง', 1, '01252', '1517', '1J10126', null, null, null, null, null);
insert into MASSHOP values(107, null, '01328', 'สำนักงานบริการลูกค้า กสท สงขลา', 1, '01328', '1518', '1J10129', null, null, null, null, null);
insert into MASSHOP values(108, null, '01366', 'สำนักงานบริการลูกค้า กสท แคท ซีดีเอ็มเอ ภูเก็ต', 1, '01366', '1519', '1J10125', null, null, null, null, null);
insert into MASSHOP values(109, null, '01376', 'สำนักงานบริการลูกค้า กสท CAT Shop หาดใหญ่', 1, '01376', '1520', '1J10130', null, null, null, null, null);
insert into MASSHOP values(110, null, '01386', 'สำนักงานบริการลูกค้า กสท สุราษฎร์ธานี (เซ็นทรัล)', 1, '01386', '1521', '1J10127', null, null, null, null, null);
insert into MASSHOP values(111, null, '01264', 'สำนักงานบริการลูกค้า กสท เขตตะวันตก', 1, '01264', '1600', null, null, null, null, null, null);
insert into MASSHOP values(112, null, '01131', 'สำนักงานบริการลูกค้า กสท นครปฐม', 1, '01131', '1601', '1J10224', null, null, null, null, null);
insert into MASSHOP values(113, null, '01132', 'สำนักงานบริการลูกค้า กสท ประจวบคีรีขันธ์', 1, '01132', '1602', '1J10230', null, null, null, null, null);
insert into MASSHOP values(114, null, '01133', 'สำนักงานบริการลูกค้า กสท หัวหิน', 1, '01133', '1603', '1J10231', null, null, null, null, null);
insert into MASSHOP values(115, null, '01135', 'สำนักงานบริการลูกค้า กสท บ้านโป่ง', 1, '01135', '1604', '1J10221', null, null, null, null, null);
insert into MASSHOP values(116, null, '01137', 'สำนักงานบริการลูกค้า กสท สมุทรสาคร', 1, '01137', '1605', '1J10225', null, null, null, null, null);
insert into MASSHOP values(117, null, '01138', 'สำนักงานบริการลูกค้า กสท สุพรรณบุรี', 1, '01138', '1606', '1J10223', null, null, null, null, null);
insert into MASSHOP values(118, null, '01139', 'สำนักงานบริการลูกค้า กสท กาญจนบุรี', 1, '01139', '1607', '1J10222', null, null, null, null, null);
insert into MASSHOP values(119, null, '01140', 'สำนักงานบริการลูกค้า กสท ราชบุรี', 1, '01140', '1608', '1J10220', null, null, null, null, null);
insert into MASSHOP values(120, null, '01144', 'สำนักงานบริการลูกค้า กสท เพชรบุรี', 1, '01144', '1609', '1J10228', null, null, null, null, null);
insert into MASSHOP values(121, null, '01146', 'สำนักงานบริการลูกค้า กสท สมุทรสงคราม', 1, '01146', '1610', '1J10227', null, null, null, null, null);
insert into MASSHOP values(122, null, '01204', 'สำนักงานบริการลูกค้า กสท ระนอง', 1, '01204', '1611', '1J10232', null, null, null, null, null);
insert into MASSHOP values(123, null, '01207', 'สำนักงานบริการลูกค้า กสท ชุมพร', 1, '01207', '1612', '1J10233', null, null, null, null, null);
insert into MASSHOP values(124, null, '01317', 'สำนักงานบริการลูกค้า กสท กระทุ่มแบน', 1, '01317', '1613', '1J10226', null, null, null, null, null);
insert into MASSHOP values(125, null, '01318', 'สำนักงานบริการลูกค้า กสท ชะอำ', 1, '01318', '1614', '1J10229', null, null, null, null, null);
insert into MASSHOP values(126, null, '00000', 'ฝ่ายพัฒนาธุรกิจการตลาดลูกค้ารายย่อย', 1, '00000', '1700', '1J60400', null, null, null, null, null);
insert into MASSHOP values(127, null, '01330', 'ศูนย์บริการลูกค้า บางรัก (อาคาร 16)', 1, '01330', '1701', '1J60409', null, null, null, null, null);
insert into MASSHOP values(128, null, '01333', 'ศูนย์บริการลูกค้า สามเสนใน', 1, '01333', '1702', '1J60410', null, null, null, null, null);
insert into MASSHOP values(129, null, '01334', 'ศูนย์บริการลูกค้า มีนบุรี', 1, '01334', '1703', '1J60411', null, null, null, null, null);
insert into MASSHOP values(130, null, '01335', 'ศูนย์บริการลูกค้า นนทบุรี', 1, '01335', '1704', '1J60414', null, null, null, null, null);
insert into MASSHOP values(131, null, '01336', 'ศูนย์บริการลูกค้า ราชดำเนิน', 1, '01336', '1705', '1J60409', null, null, null, null, null);
insert into MASSHOP values(132, null, '01337', 'ศูนย์บริการลูกค้า สมุทรปราการ', 1, '01337', '1706', '1J60412', null, null, null, null, null);
insert into MASSHOP values(133, null, '01343', 'ศูนย์บริการลูกค้า เซ็นทรัลพลาซ่า พระราม 2', 1, '01343', '1707', '1J60413', null, null, null, null, null);
insert into MASSHOP values(134, null, '01344', 'ศูนย์บริการลูกค้า สุขุมวิท', 1, '01344', '1708', '1J60412', null, null, null, null, null);
insert into MASSHOP values(135, null, '01345', 'ศูนย์บริการลูกค้า เอ็นมาร์คพลาซ่า', 1, '01345', '1709', '1J60411', null, null, null, null, null);
insert into MASSHOP values(136, null, '01346', 'ศูนย์บริการลูกค้า เดอะมอลล์รามคำแหง', 1, '01346', '1710', '1J60411', null, null, null, null, null);
insert into MASSHOP values(137, null, '01347', 'ศูนย์บริการลูกค้า เดอะมอลล์บางแค', 1, '01347', '1711', '1J60413', null, null, null, null, null);
insert into MASSHOP values(138, null, '01348', 'ศูนย์บริการลูกค้า เดอะมอลล์ท่าพระ', 1, '01348', '1712', '1J60409', null, null, null, null, null);
insert into MASSHOP values(139, null, '01350', 'ศูนย์บริการลูกค้า คลองหลวง', 1, '01350', '1713', '1J60414', null, null, null, null, null);
insert into MASSHOP values(140, null, '01351', 'ศูนย์บริการลูกค้า ยานนาวา', 1, '01351', '1714', '1J60409', null, null, null, null, null);
insert into MASSHOP values(141, null, '01370', 'CAT TELECOM INTERNATIONAL TRAN', 1, '01370', '1715', '1J60415', null, null, null, null, null);
insert into MASSHOP values(142, null, '01379', 'ศูนย์บริการลูกค้า จัตุรัสจามจุรี', 1, '01379', '1716', '1J60410', null, null, null, null, null);
insert into MASSHOP values(143, null, '00000', 'ศูนย์บริการลูกค้า แจ้งวัฒนะ', 1, '00000', '1717', '1J60410', null, null, null, null, null);
insert into MASSHOP values(144, null, '01349', 'ศูนย์บริการลูกค้า อาคาร CAT TOWER', 1, '01349', '1719', '1J60409', null, null, null, null, null);
insert into MASSHOP values(145, null, '01384', 'ศูนย์บริการลูกค้า อื้อจือเหลียง', 1, '01384', '1720', null, null, null, null, null, null);
insert into MASSHOP values(146, null, '01388', 'ศูนย์บริการลูกค้า ธนบุรี', 1, '01388', '1721', '1J60413', null, null, null, null, null);
insert into MASSHOP values(147, null, '01389', 'ศูนย์บริการลูกค้า เมกา บางนา', 1, '01389', '1722', '1J60412', null, null, null, null, null);

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

alter table MASSHOP
   add constraint fk_shogl_glid foreign key (GLID)
      references MASGL (GLID);

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
DELETE FROM ARCGRANT;
DELETE FROM ARCOPERATION;
DELETE FROM MASGL;
DELETE FROM MASPOS;
DELETE FROM COROFFICERPOS;
DELETE FROM ARCUSERAUTHNTICN;
DELETE FROM MASOFFICER;
DELETE FROM ARCPRINCIPAL;
insert into ARCPRINCIPAL values(1, 'ADMIN', 'desc', null, null, null, null);
insert into ARCPRINCIPAL values(2, 'BASIC', 'desc', null, null, null, null);
insert into ARCPRINCIPAL values(3, 'AGENT', 'desc', null, null, null, null);
insert into ARCPRINCIPAL values(4, 'CUSTOMERSERVICE', 'desc', null, null, null, null);
insert into ARCPRINCIPAL values(5, 'CALLCENTER', 'desc', null, null, null, null);
insert into ARCPRINCIPAL values(6, 'USERAGENT', 'desc', null, null, null, null);
insert into ARCPRINCIPAL values(7, 'GFMISAGENT', 'Allow GFMIS transactions', null, null, null, null);
insert into MASGL values(1, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
-- MASPOS(POSID, SHOPID, POSNO, POSNAME)
insert into MASPOS values(1, 143, '01', 'EPIS09-1', '10.36.10.44', '4C-34-88-D2-74-F9', 1, null, null, null, null);
insert into MASPOS values(2, 143, '02', 'EPIS09-2', '10.36.10.44', '4C-34-88-D2-74-F9', 1, null, null, null, null);
insert into MASPOS values(3, 1, '01', 'EPIS10-1', '10.36.10.44', '4C-34-88-D2-74-F9', 1, null, null, null, null);
insert into MASPOS values(4, 1, '02', 'EPIS10-2', '10.36.10.44', '4C-34-88-D2-74-F9', 1, null, null, null, null);
insert into MASPOS values(5, 3, '01', 'EPIS11-1', '10.36.10.44', '4C-34-88-D2-74-F9', 1, null, null, null, null);
insert into MASPOS values(6, 3, '02', 'EPIS11-2', '10.36.10.44', '4C-34-88-D2-74-F9', 1, null, null, null, null);
insert into MASPOS values(7, 133, '01', 'EPIS12-1', '10.36.10.44', '4C-34-88-D2-74-F9', 1, null, null, null, null);
insert into MASPOS values(8, 133, '02', 'EPIS12-2', '10.36.10.44', '4C-34-88-D2-74-F9', 1, null, null, null, null);
insert into MASPOS values(9, 1, '03', 'EPIS10-3', '10.36.10.45', '4C-34-88-D2-74-F9', 1, null, null, null, null);
insert into MASPOS values(10, 1, '04', 'EPIS10-4', '10.36.10.46', '4C-34-88-D2-74-F9', 1, null, null, null, null);
-- MASOFFICER(OFFICERID, PRINCIPALID, SESSIONID, USERNAME, OFFICERCODE)
insert into MASOFFICER values(1, 1, null, 'EPIS0', 'EPIS0', 'ผู้ดูแลระบบ', 'ควบคุมงานดี', null, 1, null, null, null, null);
insert into MASOFFICER values(2, 2, null, 'EPIS1', 'EPIS1', 'สมชาย', 'ใจดี', null, 1, null, null, null, null);
insert into MASOFFICER values(3, 2, null, 'EPIS2', 'EPIS2', 'สมศรี', 'ใจงาม', null, 1, null, null, null, null);
insert into MASOFFICER values(4, 3, null, 'EPIS3', 'EPIS3', 'บัวชม', 'ทะเลแดง', null, 1, null, null, null, null);
insert into MASOFFICER values(5, 3, null, 'EPIS4', 'EPIS4', 'อนันต์', 'ส่องแสง', null, 1, null, null, null, null);
insert into MASOFFICER values(6, 3, null, 'EPIS5', 'EPIS5', 'บุญมา', 'ทองดี', null, 1, null, null, null, null);
insert into MASOFFICER values(7, 3, null, 'EPIS6', 'EPIS6', 'สมบูรณ์', 'ศรีจันทร์', null, 1, null, null, null, null);
insert into MASOFFICER values(8, 3, null, 'EPIS7', 'EPIS7', 'จิตดี', 'ศรีดี', null, 1, null, null, null, null);
insert into MASOFFICER values(9, 3, null, 'EPIS8', 'EPIS8', 'สถาพร', 'สุกใส', null, 1, null, null, null, null);
insert into MASOFFICER values(10, 7, null, 'EPIS9', 'EPIS9', 'บัวชม', 'ทะเลแดง', null, 1, null, null, null, null);
insert into MASOFFICER values(11, 7, null, 'EPIS10', 'EPIS10', 'อนันต์', 'ส่องแสง', null, 1, null, null, null, null);
insert into COROFFICERPOS values(2, 1);
insert into COROFFICERPOS values(3, 2);
insert into COROFFICERPOS values(4, 3);
insert into COROFFICERPOS values(5, 4);
insert into COROFFICERPOS values(6, 5);
insert into COROFFICERPOS values(7, 6);
insert into COROFFICERPOS values(8, 7);
insert into COROFFICERPOS values(9, 8);
insert into COROFFICERPOS values(10, 9);
insert into COROFFICERPOS values(11, 10);
-- ARCUSERAUTHNTICN(USERAUTHNTICNID, OFFICERID)
insert into ARCUSERAUTHNTICN values(1, 1, '5f4dcc3b5aa765d61d8327deb882cf99', null, null, null, null);
insert into ARCUSERAUTHNTICN values(2, 2, '5f4dcc3b5aa765d61d8327deb882cf99', null, null, null, null);
insert into ARCUSERAUTHNTICN values(3, 3, '5f4dcc3b5aa765d61d8327deb882cf99', null, null, null, null);
insert into ARCUSERAUTHNTICN values(4, 4, '5f4dcc3b5aa765d61d8327deb882cf99', null, null, null, null);
insert into ARCUSERAUTHNTICN values(5, 5, '5f4dcc3b5aa765d61d8327deb882cf99', null, null, null, null);
insert into ARCUSERAUTHNTICN values(6, 6, '5f4dcc3b5aa765d61d8327deb882cf99', null, null, null, null);
insert into ARCUSERAUTHNTICN values(7, 7, '5f4dcc3b5aa765d61d8327deb882cf99', null, null, null, null);
insert into ARCUSERAUTHNTICN values(8, 8, '5f4dcc3b5aa765d61d8327deb882cf99', null, null, null, null);
insert into ARCUSERAUTHNTICN values(9, 9, '5f4dcc3b5aa765d61d8327deb882cf99', null, null, null, null);
insert into ARCUSERAUTHNTICN values(10, 10, '5f4dcc3b5aa765d61d8327deb882cf99', null, null, null, null);
insert into ARCUSERAUTHNTICN values(11, 11, '5f4dcc3b5aa765d61d8327deb882cf99', null, null, null, null);
insert into ARCOPERATION values(1, 'oper name', 'desc', null, null, null, null);
insert into ARCGRANT values(1, 1, 1, 1, null, null, null, null);
commit;