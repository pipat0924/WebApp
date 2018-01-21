/*==============================================================*/
/* DBMS name:      EPISDB Version 1.0                           */
/*                          									*/
/*==============================================================*/

-- alter table MASBILLING_GROUP_IGNORE drop constraint MASBILLING_GROUP_IGNORE_PK;
-- alter table MASBILLING_GROUP_IGNORE drop constraint MASBILLING_GROUP_IGNORE_PK;

drop table MASBILLING_GROUP_IGNORE  cascade constraints PURGE;
drop table MASPACKAGE_PROMOTION  cascade constraints PURGE;
drop table INV_LOCK  cascade constraints PURGE;
drop table PROMOTION_BILLING_MAPPING  cascade constraints PURGE;
drop table PROMOTION_INSALE_MAPPING  cascade constraints PURGE;
drop table RECEIPT_PRINT_TYPE_MAPPING cascade constraints PURGE;
commit;

DROP SEQUENCE BILLING_GROUP_IGNORE_SEQ;
DROP SEQUENCE MASPACKAGE_PROMOTION_SEQ;
commit;

CREATE SEQUENCE BILLING_GROUP_IGNORE_SEQ MINVALUE 1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE MASPACKAGE_PROMOTION_SEQ MINVALUE 1 START WITH 1 INCREMENT BY 1;
commit;

/*==============================================================*/
/* Table: MASBILLING_GROUP_IGNORE                       	    */
/*==============================================================*/


CREATE TABLE MASBILLING_GROUP_IGNORE 
(
  ID NUMBER NOT NULL 
, BILLING_GROUP VARCHAR2(255 BYTE) NOT NULL 
, BILLING_GROUP_DESC VARCHAR2(500 BYTE)
 , FULL_NAME VARCHAR2(255 BYTE)
); 

CREATE UNIQUE INDEX MASBILLING_GROUP_IGNORE_PK ON MASBILLING_GROUP_IGNORE (ID ASC); 

ALTER TABLE MASBILLING_GROUP_IGNORE
ADD CONSTRAINT MASBILLING_GROUP_IGNORE_PK PRIMARY KEY 
(
  ID 
)
USING INDEX MASBILLING_GROUP_IGNORE_PK
ENABLE;

commit;

/*==============================================================*/
/* Table: CREATE TABLE INV_LOCK                        	    */
/*==============================================================*/

CREATE TABLE INV_LOCK 
(
  INVNO VARCHAR2(255 BYTE) NOT NULL 
, USER_CREATED VARCHAR2(20 BYTE) 
, CREATED_TIME TIMESTAMP(6) 
) ;

CREATE UNIQUE INDEX INV_LOCK_PK ON INV_LOCK (INVNO ASC) ;

ALTER TABLE INV_LOCK
ADD CONSTRAINT INV_LOCK_PK PRIMARY KEY 
(
  INVNO 
)
USING INDEX INV_LOCK_PK
ENABLE;

commit;

/*==============================================================*/
/* Table: MASPACKAGE_PROMOTION                       	    */
/*==============================================================*/

CREATE TABLE MASPACKAGE_PROMOTION 
(
  ID NUMBER NOT NULL 
, PACKAGE_ID VARCHAR2(255 BYTE) 
, PROMOTION VARCHAR2(500 BYTE) 
, CREATED_BY VARCHAR2(20 BYTE) 
, CREATED_DATE TIMESTAMP(6) 
);


CREATE UNIQUE INDEX MASPACKAGE_PROMOTION_PK ON MASPACKAGE_PROMOTION (ID ASC) ;

ALTER TABLE MASPACKAGE_PROMOTION
ADD CONSTRAINT MASPACKAGE_PROMOTION_PK PRIMARY KEY 
(
  ID 
)
USING INDEX MASPACKAGE_PROMOTION_PK
ENABLE;
commit;

/*==============================================================*/
/* Table: PROMOTION_BILLING_MAPPING                       	    */
/*==============================================================*/

CREATE TABLE PROMOTION_BILLING_MAPPING 
(
  ACCOUNT_NO VARCHAR2(20 BYTE) 
, PROMOTION_ID VARCHAR2(20 BYTE) 
, INVOICE_NO VARCHAR2(20 BYTE) 
, SERVICE_NO VARCHAR2(20 BYTE) 
) ;

/*==============================================================*/
/* Table: PROMOTION_INSALE_MAPPING                       	    */
/*==============================================================*/

CREATE TABLE PROMOTION_INSALE_MAPPING 
(
  ACCOUNT_NO VARCHAR2(20 BYTE) 
, PROMOTION_ID VARCHAR2(20 BYTE) 
, SERVICE_NO VARCHAR2(20 BYTE) 
);
commit;


/*==============================================================*/
/* Table: RECEIPT_PRINT_TYPE_MAPPING                       	    */
/*==============================================================*/


CREATE TABLE RECEIPT_PRINT_TYPE_MAPPING 
(
  RECEIPT_ID NUMBER NOT NULL ENABLE 
, PRINT_TYPE VARCHAR2(20) NOT NULL 
, CONSTRAINT RECEIPT_PRINT_TYPE_MAPPING_PK PRIMARY KEY 
  (
    RECEIPT_ID 
  , PRINT_TYPE 
  )
  ENABLE 
);

COMMENT ON COLUMN RECEIPT_PRINT_TYPE_MAPPING.PRINT_TYPE IS 'RECEIVE_WH , RECEIVE_ONLY , WH_ONLY';
commit;

/* ALTER CORRECEIPT */
ALTER TABLE CORRECEIPT 
ADD (BILLING_GROUP VARCHAR2(255) );

ALTER TABLE CORRECEIPT 
ADD (BILLING_GROUP_FULL VARCHAR2(255) );

ALTER TABLE CORRECEIPT 
ADD (LANGUAGE VARCHAR2(20) );

/* ALTER TRSREPRINT */
ALTER TABLE TRSREPRINT  
MODIFY (REASON VARCHAR2(255 BYTE) );

/* ALTER TRSMETHOD */
ALTER TABLE TRSMETHOD 
ADD (OFFSET_DOCUMENT_NO VARCHAR2(255) );

ALTER TABLE TRSMETHOD 
ADD (OFFSET_ACCOUNT_CODE VARCHAR2(255) );

ALTER TABLE TRSMETHOD 
ADD (OFFSET_ACCOUNT_NAME VARCHAR2(255) );

/*  INV_SUMMARY_SAP_IBACSS */
ALTER TABLE INV_SUMMARY_SAP_IBACSS
ADD (PRODUCT_NAME VARCHAR2(250) );

ALTER TABLE INV_SUMMARY_SAP_IBACSS
ADD (SUB_PRODUCT_NAME VARCHAR2(250) );

ALTER TABLE INV_SUMMARY_SAP_IBACSS
ADD (REV_TYPE_NAME VARCHAR2(250) );

/* INV_SUMMARY_SAP_OTBOSS */
ALTER TABLE INV_SUMMARY_SAP_OTBOSS
ADD (PRODUCT_NAME VARCHAR2(250) );

ALTER TABLE INV_SUMMARY_SAP_OTBOSS
ADD (SUB_PRODUCT_NAME VARCHAR2(250) );

ALTER TABLE INV_SUMMARY_SAP_OTBOSS
ADD (REV_TYPE_NAME VARCHAR2(250) );

/* INV_SUMMARY_SAP_TBOSS */
ALTER TABLE INV_SUMMARY_SAP_TBOSS
ADD (PRODUCT_NAME VARCHAR2(250) );

ALTER TABLE INV_SUMMARY_SAP_TBOSS
ADD (SUB_PRODUCT_NAME VARCHAR2(250) );

ALTER TABLE INV_SUMMARY_SAP_TBOSS
ADD (REV_TYPE_NAME VARCHAR2(250) );

/* ALTER TMPACCOUNT By Wipaporn 23-sep-2016 */
ALTER TABLE TMPACCOUNT
  MODIFY BILLGROUP VARCHAR2(260);
 
COMMENT ON COLUMN TRSREPRINT.CATEGORY IS '1=COPY,2=SUBSTITUE,3=REPRINT';

ALTER TABLE INV_SUMMARY_SAP_OTBOSS   MODIFY (SERVICE_PRIORITY NULL);

commit;

insert into PROMOTION_BILLING_MAPPING (ACCOUNT_NO,PROMOTION_ID,INVOICE_NO,SERVICE_NO) values ('800175483','222','200172751',null);
insert into arcenums values (172,'reason.service.category','rson0001','001',null,null,null,null,'สูญหาย',null,null,'1',null,'SYS',null,'1');
insert into arcenums values (173,'reason.service.category','rson0002','002',null,null,null,null,'ถูกทำลาย',null,null,'1',null,'SYS',null,'1');
insert into arcenums values (174,'reason.service.category','rson0003','003',null,null,null,null,'ชำรุดในสาระสำคัญ',null,null,'1',null,'SYS',null,'1');

commit;
