package th.net.cat.epis.to.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExportErpTO {
	private String BKPF_BLRES;
    private String BKPF_BLDAT;
    private String BKPF_BLART;
    private String BKPF_BUKRS;
    private String BKPF_BUDAT;
    private String BKPF_MONAT;
    private String BKPF_WAERS;
    private String BKPF_KURSF;
    private String BKPF_WWERT;
    private String BKPF_XBLNR;
    private String BKPF_BKTXT;
    private String BKPF_BRNCH;
    private String BKPF_GLVOR;
    private String BKPF_GJAHR;
    private String BKPF_USNAM;
    private String BKPF_AWTYP;
    private String BKPF_AWREF;
    private String BSEG_BUZEI;
    private String BSEG_SHKZG;
    private String BSEG_BSCHL;
    private String BSEG_KOART;
    private String BSEG_HKONT_KUNNR_LIFNR;
    private String BSEG_HKONT;
    private String BSEG_WRBTR;
    private String BSEG_DMBTR;
    private String BSEG_FWBAS;
    private String BSEG_HWBAS;
    private String BSEG_MWSKZ;
    private String BSEG_BUPLA;
    private String BSEG_GSBER;
    private String BSEG_VALUT;
    private String BSEG_ZTERM;
    private String BSEG_ZFBDT;
    private String BSEG_SKFBT;
    private String BSEG_KOSTL;
    private String BSEG_FKBER;
    private String BSEG_PROJK;
    private String BSEG_LSTAR;
    private String BSEG_SEGMENT;
    private String BSEG_ZZWWPRD;
    private String BSEG_ZZWWSUB;
    private String BSEG_ZZWWREV;
    private String BSEG_MATNR;
    private String BSEG_WERKS;
    private String BSEG_PRZNR;
    private String CE11000_WWCUS;
    private String BSEG_KIDNO;
    private String BSEG_ZOUNR;
    private String BSEG_SGTXT;
    private String BSEG_XREF1;
    private String BSEG_XREF2;
    private String BSEG_XREF3;
    private String BSEG_VBUND;
    private String BSEC_BUZEI;
    private String BSEC_ANRED;
    private String BSEC_NAME1;
    private String BSEC_NAME2;
    private String BSEC_NAME3;
    private String BSEC_NAME4;
    private String BSEC_STRAS;
    private String BSEC_ORT01;
    private String BSEC_PSTLZ;
    private String BSEC_LAND1;
    private String BSEC_STCD1;
    private String BSEC_STCD2;
    private String BSEC_EMPFG;
    private String BSEC_BANKL;
    private String BSEC_BANKN;
    private String BSEC_BANKS;
    private String WITH_ITEM_BUZEI;
    private String WITH_ITEM_WITHT;
    private String WITH_ITEM_WT_WITHCD;
    private String WITH_ITEM_WT_QSSHB;
    private String WITH_ITEM_WT_QSSHH;
    private String WITH_ITEM_WT_QBSHB;
    private String WITH_ITEM_WT_QBSHH;
    private String WITH_ITEM_WITHT84;
    private String WITH_ITEM_WT_WITHCD85;
    private String WITH_ITEM_WT_QSSHB86;
    private String WITH_ITEM_WT_QSSHH87;
    private String WITH_ITEM_WT_QBSHB88;
    private String WITH_ITEM_WT_QBSHH89;
    private String BSET_BUZEI;
    private String BSET_MWSKZ;
    private String BSET_HKONT;
    private String BSET_SHKZG;
    private String BSET_HWBAS;
    private String BSET_FWBAS;
    private String BSET_HWSTE;
    private String BSET_FWSTE;
    private String BSET_KTOSL;
    private String BSET_KSCHL;
    private String BSET_BUPLA;
    private String CE11000_WWCOC;
    private Date DATE;
     
    public ExportErpTO() {}
	
	public ExportErpTO(String BKPF_BLRES , String BKPF_BLDAT, String BKPF_BLART, String BKPF_BUKRS, String BKPF_BUDAT,
			String BKPF_MONAT, String BKPF_WAERS,String BKPF_KURSF,String BKPF_WWERT,String BKPF_XBLNR
			,String BKPF_BKTXT,String BKPF_BRNCH,String BKPF_GLVOR,String BKPF_GJAHR,String BKPF_USNAM
			,String BKPF_AWTYP,String BKPF_AWREF,String BSEG_BUZEI,String BSEG_SHKZG,String BSEG_BSCHL
			,String BSEG_KOART,String BSEG_HKONT_KUNNR_LIFNR,String BSEG_HKONT,String BSEG_WRBTR
			,String BSEG_DMBTR,String BSEG_FWBAS,String BSEG_HWBAS,String BSEG_MWSKZ,String BSEG_BUPLA
			,String BSEG_GSBER,String BSEG_VALUT,String BSEG_ZTERM,String BSEG_ZFBDT,String BSEG_SKFBT
			,String BSEG_KOSTL,String BSEG_FKBER,String BSEG_PROJK,String BSEG_LSTAR,String BSEG_SEGMENT
			,String BSEG_ZZWWPRD,String BSEG_ZZWWSUB,String BSEG_ZZWWREV,String BSEG_MATNR,String BSEG_WERKS
			,String BSEG_PRZNR,String CE11000_WWCUS,String BSEG_KIDNO,String BSEG_ZOUNR,String BSEG_SGTXT
			,String BSEG_XREF1,String BSEG_XREF2,String BSEG_XREF3,String BSEG_VBUND,String BSEC_BUZEI
			,String BSEC_ANRED,String BSEC_NAME1,String BSEC_NAME2,String BSEC_NAME3,String BSEC_NAME4
			,String BSEC_STRAS,String BSEC_ORT01,String BSEC_PSTLZ,String BSEC_LAND1,String BSEC_STCD1
			,String BSEC_STCD2,String BSEC_EMPFG,String BSEC_BANKL,String BSEC_BANKN,String BSEC_BANKS
			,String WITH_ITEM_BUZEI,String WITH_ITEM_WITHT,String WITH_ITEM_WT_WITHCD
			,String WITH_ITEM_WT_QSSHB,String WITH_ITEM_WT_QSSHH,String WITH_ITEM_WT_QBSHB
			,String WITH_ITEM_WT_QBSHH,String WITH_ITEM_WITHT84,String WITH_ITEM_WT_WITHCD85
			,String WITH_ITEM_WT_QSSHB86,String WITH_ITEM_WT_QSSHH87,String WITH_ITEM_WT_QBSHB88
			,String WITH_ITEM_WT_QBSHH89,String BSET_BUZEI,String BSET_MWSKZ,String BSET_HKONT,String BSET_SHKZG
			,String BSET_HWBAS,String BSET_FWBAS ,String BSET_HWSTE,String BSET_FWSTE,String BSET_KTOSL,String BSET_KSCHL
			,String BSET_BUPLA,Date DATE) {
			
		//Format Data FIXED Length ����Ѻ Export �� ��� IDOC
		this.BKPF_BLRES = parseText(BKPF_BLRES,3,false);
		this.BKPF_BLDAT = parseText(BKPF_BLDAT,8,true);
		this.BKPF_BLART = parseText(BKPF_BLART,2,true);
		this.BKPF_BUKRS = parseText(BKPF_BUKRS,4,true);
		this.BKPF_BUDAT = parseText(BKPF_BUDAT,8,true);
		this.BKPF_MONAT = parseText(BKPF_MONAT,2,true);
		this.BKPF_WAERS = parseText(BKPF_WAERS,5,true);
		this.BKPF_KURSF = parseText(BKPF_KURSF,11,false);
		this.BKPF_WWERT = parseText(BKPF_WWERT,8,true);
		this.BKPF_XBLNR = parseText(BKPF_XBLNR,16,true);
		this.BKPF_BKTXT = parseText(BKPF_BKTXT,25,true);
		this.BKPF_BRNCH = parseText(BKPF_BRNCH,4,true);
		this.BKPF_GLVOR = parseText(BKPF_GLVOR,4,true);
		this.BKPF_GJAHR = parseText(BKPF_GJAHR,4,true);
		this.BKPF_USNAM = parseText(BKPF_USNAM,12,true);
		this.BKPF_AWTYP = parseText(BKPF_AWTYP,5,true);
		this.BKPF_AWREF = parseText(BKPF_AWREF,10,true);
		this.BSEG_BUZEI = parseText(BSEG_BUZEI,3,true);
		this.BSEG_SHKZG = parseText(BSEG_SHKZG,1,true);
		this.BSEG_BSCHL = parseText(BSEG_BSCHL,2,true);
		this.BSEG_KOART = parseText(BSEG_KOART,1,true);
		this.BSEG_HKONT_KUNNR_LIFNR = parseText(BSEG_HKONT_KUNNR_LIFNR,10,true);
		this.BSEG_HKONT = parseText(BSEG_HKONT,10,true);
		this.BSEG_WRBTR = parseText(BSEG_WRBTR,13,false);
		this.BSEG_DMBTR = parseText(BSEG_DMBTR,13,false);
		this.BSEG_FWBAS = parseText(BSEG_FWBAS,13,false);
		this.BSEG_HWBAS = parseText(BSEG_HWBAS,13,false);
		this.BSEG_MWSKZ = parseText(BSEG_MWSKZ,2,true);
		this.BSEG_BUPLA = parseText(BSEG_BUPLA,4,true);
		this.BSEG_GSBER = parseText(BSEG_GSBER,4,true);
		this.BSEG_VALUT = parseText(BSEG_VALUT,8,true);
		this.BSEG_ZTERM = parseText(BSEG_ZTERM,4,true);
		this.BSEG_ZFBDT = parseText(BSEG_ZFBDT,8,true);
		this.BSEG_SKFBT = parseText(BSEG_SKFBT,13,false);
		this.BSEG_KOSTL = parseText(BSEG_KOSTL,10,true);
		this.BSEG_FKBER = parseText(BSEG_FKBER,16,true);
		this.BSEG_PROJK = parseText(BSEG_PROJK,24,true);
		this.BSEG_LSTAR = parseText(BSEG_LSTAR,6,true);
		this.BSEG_SEGMENT = parseText(BSEG_SEGMENT,10,true);
		this.BSEG_ZZWWPRD = parseText(BSEG_ZZWWPRD,18,true);
		this.BSEG_ZZWWSUB = parseText(BSEG_ZZWWSUB,2,true);
		this.BSEG_ZZWWREV = parseText(BSEG_ZZWWREV,10,true);
		this.BSEG_MATNR = parseText(BSEG_MATNR,18,true);
		this.BSEG_WERKS = parseText(BSEG_WERKS,4,true);
		this.BSEG_PRZNR = parseText(BSEG_PRZNR,12,true);
		this.CE11000_WWCUS = parseText(CE11000_WWCUS,10,true);
		this.BSEG_KIDNO = parseText(BSEG_KIDNO,30,true);
		this.BSEG_ZOUNR = parseText(BSEG_ZOUNR,18,true);
		this.BSEG_SGTXT = parseText(BSEG_SGTXT,50,true);
		this.BSEG_XREF1 = parseText(BSEG_XREF1,12,true);
		this.BSEG_XREF2 = parseText(BSEG_XREF2,12,true);
		this.BSEG_XREF3 = parseText(BSEG_XREF3,20,true);
		this.BSEG_VBUND = parseText(BSEG_VBUND,6,true);
		this.BSEC_BUZEI = parseText(BSEC_BUZEI,3,true);
		this.BSEC_ANRED = parseText(BSEC_ANRED,15,true);
		this.BSEC_NAME1 = parseText(BSEC_NAME1,35,true);
		this.BSEC_NAME2 = parseText(BSEC_NAME2,35,true);
		this.BSEC_NAME3 = parseText(BSEC_NAME3,35,true);
		this.BSEC_NAME4 = parseText(BSEC_NAME4,35,true);
		this.BSEC_STRAS = parseText(BSEC_STRAS,35,true);
		this.BSEC_ORT01 = parseText(BSEC_ORT01,35,true);
		this.BSEC_PSTLZ = parseText(BSEC_PSTLZ,10,true);
		this.BSEC_LAND1 = parseText(BSEC_LAND1,3,true);
		this.BSEC_STCD1 = parseText(BSEC_STCD1,16,true);
		this.BSEC_STCD2 = parseText(BSEC_STCD2,11,true);
		this.BSEC_EMPFG = parseText(BSEC_EMPFG,16,true);
		this.BSEC_BANKL = parseText(BSEC_BANKL,15,true);
		this.BSEC_BANKN = parseText(BSEC_BANKN,18,true);
		this.BSEC_BANKS = parseText(BSEC_BANKS,3,true);
		this.WITH_ITEM_BUZEI = parseText(WITH_ITEM_BUZEI,3,true);
		this.WITH_ITEM_WITHT = parseText(WITH_ITEM_WITHT,2,true);
		this.WITH_ITEM_WT_WITHCD = parseText(WITH_ITEM_WT_WITHCD,2,true);
		this.WITH_ITEM_WT_QSSHB = parseText(WITH_ITEM_WT_QSSHB,13,false);
		this.WITH_ITEM_WT_QSSHH = parseText(WITH_ITEM_WT_QSSHH,13,false);
		this.WITH_ITEM_WT_QBSHB = parseText(WITH_ITEM_WT_QBSHB,13,false);
		this.WITH_ITEM_WT_QBSHH = parseText(WITH_ITEM_WT_QBSHH,13,false);
		this.WITH_ITEM_WITHT84 = parseText(WITH_ITEM_WITHT84,2,true);
		this.WITH_ITEM_WT_WITHCD85 = parseText(WITH_ITEM_WT_WITHCD85,2,true);
		this.WITH_ITEM_WT_QSSHB86 = parseText(WITH_ITEM_WT_QSSHB86,13,false);
		this.WITH_ITEM_WT_QSSHH87 = parseText(WITH_ITEM_WT_QSSHH87,13,false);
		this.WITH_ITEM_WT_QBSHB88 = parseText(WITH_ITEM_WT_QBSHB88,13,false);
		this.WITH_ITEM_WT_QBSHH89 = parseText(WITH_ITEM_WT_QBSHH89,13,false);
		this.BSET_BUZEI = parseText(BSET_BUZEI,3,true);
		this.BSET_MWSKZ = parseText(BSET_MWSKZ,2,true);
		this.BSET_HKONT = parseText(BSET_HKONT,10,true);
		this.BSET_SHKZG = parseText(BSET_SHKZG,1,true);
		this.BSET_HWBAS = parseText(BSET_HWBAS,15,true);
		this.BSET_FWBAS = parseText(BSET_FWBAS,15,true);
		this.BSET_HWSTE = parseText(BSET_HWSTE,13,true);
		this.BSET_FWSTE = parseText(BSET_FWSTE,13,true);
		this.BSET_KTOSL = parseText(BSET_KTOSL,3,true);
		this.BSET_KSCHL = parseText(BSET_KSCHL,4,true);
		this.BSET_BUPLA = parseText(BSET_BUPLA,5,true); //+ Length 1  ��������鹨����֧ 1060 ����� format ��� IDOC
		
		// �����ҧ�� Length �ͧ ����ѡ�è��� 1060 �ʹի�� �ҧ ESB �С�Ҵ������� Length 1060 ��ҹ��
//		this.CE11000_WWCOC = parseText(CE11000_WWCOC,4,true); //���� Export �͡� Field �������١ Export 仴��� ��������� Template
		this.DATE = DATE;
		
	}

	
    public String getBKPF_BLRES() {
        return BKPF_BLRES;
    }

    public void setBKPF_BLRES(String BKPF_BLRES) {
        this.BKPF_BLRES = BKPF_BLRES;
    }

    public String getBKPF_BLDAT() {
        return BKPF_BLDAT;
    }

    public void setBKPF_BLDAT(String BKPF_BLDAT) {
        this.BKPF_BLDAT = BKPF_BLDAT;
    }

    public String getBKPF_BLART() {
        return BKPF_BLART;
    }

    public void setBKPF_BLART(String BKPF_BLART) {
        this.BKPF_BLART = BKPF_BLART;
    }

    public String getBKPF_BUKRS() {
        return BKPF_BUKRS;
    }

    public void setBKPF_BUKRS(String BKPF_BUKRS) {
        this.BKPF_BUKRS = BKPF_BUKRS;
    }

    public String getBKPF_BUDAT() {
        return BKPF_BUDAT;
    }

    public void setBKPF_BUDAT(String BKPF_BUDAT) {
        this.BKPF_BUDAT = BKPF_BUDAT;
    }

    public String getBKPF_MONAT() {
        return BKPF_MONAT;
    }

    public void setBKPF_MONAT(String BKPF_MONAT) {
        this.BKPF_MONAT = BKPF_MONAT;
    }

    public String getBKPF_WAERS() {
        return BKPF_WAERS;
    }

    public void setBKPF_WAERS(String BKPF_WAERS) {
        this.BKPF_WAERS = BKPF_WAERS;
    }

    public String getBKPF_KURSF() {
        return BKPF_KURSF;
    }

    public void setBKPF_KURSF(String BKPF_KURSF) {
        this.BKPF_KURSF = BKPF_KURSF;
    }

    public String getBKPF_WWERT() {
        return BKPF_WWERT;
    }

    public void setBKPF_WWERT(String BKPF_WWERT) {
        this.BKPF_WWERT = BKPF_WWERT;
    }

    public String getBKPF_XBLNR() {
        return BKPF_XBLNR;
    }

    public void setBKPF_XBLNR(String BKPF_XBLNR) {
        this.BKPF_XBLNR = BKPF_XBLNR;
    }

    public String getBKPF_BKTXT() {
        return BKPF_BKTXT;
    }

    public void setBKPF_BKTXT(String BKPF_BKTXT) {
        this.BKPF_BKTXT = BKPF_BKTXT;
    }

    public String getBKPF_BRNCH() {
        return BKPF_BRNCH;
    }

    public void setBKPF_BRNCH(String BKPF_BRNCH) {
        this.BKPF_BRNCH = BKPF_BRNCH;
    }

    public String getBKPF_GLVOR() {
        return BKPF_GLVOR;
    }

    public void setBKPF_GLVOR(String BKPF_GLVOR) {
        this.BKPF_GLVOR = BKPF_GLVOR;
    }

    public String getBKPF_GJAHR() {
        return BKPF_GJAHR;
    }

    public void setBKPF_GJAHR(String BKPF_GJAHR) {
        this.BKPF_GJAHR = BKPF_GJAHR;
    }

    public String getBKPF_USNAM() {
        return BKPF_USNAM;
    }

    public void setBKPF_USNAM(String BKPF_USNAM) {
        this.BKPF_USNAM = BKPF_USNAM;
    }

    public String getBKPF_AWTYP() {
        return BKPF_AWTYP;
    }

    public void setBKPF_AWTYP(String BKPF_AWTYP) {
        this.BKPF_AWTYP = BKPF_AWTYP;
    }

    public String getBKPF_AWREF() {
        return BKPF_AWREF;
    }

    public void setBKPF_AWREF(String BKPF_AWREF) {
        this.BKPF_AWREF = BKPF_AWREF;
    }

    public String getBSEG_BUZEI() {
        return BSEG_BUZEI;
    }

    public void setBSEG_BUZEI(String BSEG_BUZEI) {
        this.BSEG_BUZEI = BSEG_BUZEI;
    }

    public String getBSEG_SHKZG() {
        return BSEG_SHKZG;
    }

    public void setBSEG_SHKZG(String BSEG_SHKZG) {
        this.BSEG_SHKZG = BSEG_SHKZG;
    }

    public String getBSEG_BSCHL() {
        return BSEG_BSCHL;
    }

    public void setBSEG_BSCHL(String BSEG_BSCHL) {
        this.BSEG_BSCHL = BSEG_BSCHL;
    }

    public String getBSEG_KOART() {
        return BSEG_KOART;
    }

    public void setBSEG_KOART(String BSEG_KOART) {
        this.BSEG_KOART = BSEG_KOART;
    }

    public String getBSEG_HKONT_KUNNR_LIFNR() {
        return BSEG_HKONT_KUNNR_LIFNR;
    }

    public void setBSEG_HKONT_KUNNR_LIFNR(String BSEG_HKONT_KUNNR_LIFNR) {
        this.BSEG_HKONT_KUNNR_LIFNR = BSEG_HKONT_KUNNR_LIFNR;
    }

    public String getBSEG_HKONT() {
        return BSEG_HKONT;
    }

    public void setBSEG_HKONT(String BSEG_HKONT) {
        this.BSEG_HKONT = BSEG_HKONT;
    }

    public String getBSEG_WRBTR() {
        return BSEG_WRBTR;
    }

    public void setBSEG_WRBTR(String BSEG_WRBTR) {
        this.BSEG_WRBTR = BSEG_WRBTR;
    }

    public String getBSEG_DMBTR() {
        return BSEG_DMBTR;
    }

    public void setBSEG_DMBTR(String BSEG_DMBTR) {
        this.BSEG_DMBTR = BSEG_DMBTR;
    }

    public String getBSEG_FWBAS() {
        return BSEG_FWBAS;
    }

    public void setBSEG_FWBAS(String BSEG_FWBAS) {
        this.BSEG_FWBAS = BSEG_FWBAS;
    }

    public String getBSEG_HWBAS() {
        return BSEG_HWBAS;
    }

    public void setBSEG_HWBAS(String BSEG_HWBAS) {
        this.BSEG_HWBAS = BSEG_HWBAS;
    }

    public String getBSEG_MWSKZ() {
        return BSEG_MWSKZ;
    }

    public void setBSEG_MWSKZ(String BSEG_MWSKZ) {
        this.BSEG_MWSKZ = BSEG_MWSKZ;
    }

    public String getBSEG_BUPLA() {
        return BSEG_BUPLA;
    }

    public void setBSEG_BUPLA(String BSEG_BUPLA) {
        this.BSEG_BUPLA = BSEG_BUPLA;
    }

    public String getBSEG_GSBER() {
        return BSEG_GSBER;
    }

    public void setBSEG_GSBER(String BSEG_GSBER) {
        this.BSEG_GSBER = BSEG_GSBER;
    }

    public String getBSEG_VALUT() {
        return BSEG_VALUT;
    }

    public void setBSEG_VALUT(String BSEG_VALUT) {
        this.BSEG_VALUT = BSEG_VALUT;
    }

    public String getBSEG_ZTERM() {
        return BSEG_ZTERM;
    }

    public void setBSEG_ZTERM(String BSEG_ZTERM) {
        this.BSEG_ZTERM = BSEG_ZTERM;
    }

    public String getBSEG_ZFBDT() {
        return BSEG_ZFBDT;
    }

    public void setBSEG_ZFBDT(String BSEG_ZFBDT) {
        this.BSEG_ZFBDT = BSEG_ZFBDT;
    }

    public String getBSEG_SKFBT() {
        return BSEG_SKFBT;
    }

    public void setBSEG_SKFBT(String BSEG_SKFBT) {
        this.BSEG_SKFBT = BSEG_SKFBT;
    }

    public String getBSEG_KOSTL() {
        return BSEG_KOSTL;
    }

    public void setBSEG_KOSTL(String BSEG_KOSTL) {
        this.BSEG_KOSTL = BSEG_KOSTL;
    }

    public String getBSEG_FKBER() {
        return BSEG_FKBER;
    }

    public void setBSEG_FKBER(String BSEG_FKBER) {
        this.BSEG_FKBER = BSEG_FKBER;
    }

    public String getBSEG_PROJK() {
        return BSEG_PROJK;
    }

    public void setBSEG_PROJK(String BSEG_PROJK) {
        this.BSEG_PROJK = BSEG_PROJK;
    }

    public String getBSEG_LSTAR() {
        return BSEG_LSTAR;
    }

    public void setBSEG_LSTAR(String BSEG_LSTAR) {
        this.BSEG_LSTAR = BSEG_LSTAR;
    }

    public String getBSEG_SEGMENT() {
        return BSEG_SEGMENT;
    }

    public void setBSEG_SEGMENT(String BSEG_SEGMENT) {
        this.BSEG_SEGMENT = BSEG_SEGMENT;
    }

    public String getBSEG_ZZWWPRD() {
        return BSEG_ZZWWPRD;
    }

    public void setBSEG_ZZWWPRD(String BSEG_ZZWWPRD) {
        this.BSEG_ZZWWPRD = BSEG_ZZWWPRD;
    }

    public String getBSEG_ZZWWSUB() {
        return BSEG_ZZWWSUB;
    }

    public void setBSEG_ZZWWSUB(String BSEG_ZZWWSUB) {
        this.BSEG_ZZWWSUB = BSEG_ZZWWSUB;
    }

    public String getBSEG_ZZWWREV() {
        return BSEG_ZZWWREV;
    }

    public void setBSEG_ZZWWREV(String BSEG_ZZWWREV) {
        this.BSEG_ZZWWREV = BSEG_ZZWWREV;
    }

    public String getBSEG_MATNR() {
        return BSEG_MATNR;
    }

    public void setBSEG_MATNR(String BSEG_MATNR) {
        this.BSEG_MATNR = BSEG_MATNR;
    }

    public String getBSEG_WERKS() {
        return BSEG_WERKS;
    }

    public void setBSEG_WERKS(String BSEG_WERKS) {
        this.BSEG_WERKS = BSEG_WERKS;
    }

    public String getBSEG_PRZNR() {
        return BSEG_PRZNR;
    }

    public void setBSEG_PRZNR(String BSEG_PRZNR) {
        this.BSEG_PRZNR = BSEG_PRZNR;
    }

    public String getCE11000_WWCUS() {
        return CE11000_WWCUS;
    }

    public void setCE11000_WWCUS(String CE11000_WWCUS) {
        this.CE11000_WWCUS = CE11000_WWCUS;
    }

    public String getBSEG_KIDNO() {
        return BSEG_KIDNO;
    }

    public void setBSEG_KIDNO(String BSEG_KIDNO) {
        this.BSEG_KIDNO = BSEG_KIDNO;
    }

    public String getBSEG_ZOUNR() {
        return BSEG_ZOUNR;
    }

    public void setBSEG_ZOUNR(String BSEG_ZOUNR) {
        this.BSEG_ZOUNR = BSEG_ZOUNR;
    }

    public String getBSEG_SGTXT() {
        return BSEG_SGTXT;
    }

    public void setBSEG_SGTXT(String BSEG_SGTXT) {
        this.BSEG_SGTXT = BSEG_SGTXT;
    }

    public String getBSEG_XREF1() {
        return BSEG_XREF1;
    }

    public void setBSEG_XREF1(String BSEG_XREF1) {
        this.BSEG_XREF1 = BSEG_XREF1;
    }

    public String getBSEG_XREF2() {
        return BSEG_XREF2;
    }

    public void setBSEG_XREF2(String BSEG_XREF2) {
        this.BSEG_XREF2 = BSEG_XREF2;
    }

    public String getBSEG_XREF3() {
        return BSEG_XREF3;
    }

    public void setBSEG_XREF3(String BSEG_XREF3) {
        this.BSEG_XREF3 = BSEG_XREF3;
    }

    public String getBSEG_VBUND() {
        return BSEG_VBUND;
    }

    public void setBSEG_VBUND(String BSEG_VBUND) {
        this.BSEG_VBUND = BSEG_VBUND;
    }

    public String getBSEC_BUZEI() {
        return BSEC_BUZEI;
    }

    public void setBSEC_BUZEI(String BSEC_BUZEI) {
        this.BSEC_BUZEI = BSEC_BUZEI;
    }

    public String getBSEC_ANRED() {
        return BSEC_ANRED;
    }

    public void setBSEC_ANRED(String BSEC_ANRED) {
        this.BSEC_ANRED = BSEC_ANRED;
    }

    public String getBSEC_NAME1() {
        return BSEC_NAME1;
    }

    public void setBSEC_NAME1(String BSEC_NAME1) {
        this.BSEC_NAME1 = BSEC_NAME1;
    }

    public String getBSEC_NAME2() {
        return BSEC_NAME2;
    }

    public void setBSEC_NAME2(String BSEC_NAME2) {
        this.BSEC_NAME2 = BSEC_NAME2;
    }

    public String getBSEC_NAME3() {
        return BSEC_NAME3;
    }

    public void setBSEC_NAME3(String BSEC_NAME3) {
        this.BSEC_NAME3 = BSEC_NAME3;
    }

    public String getBSEC_NAME4() {
        return BSEC_NAME4;
    }

    public void setBSEC_NAME4(String BSEC_NAME4) {
        this.BSEC_NAME4 = BSEC_NAME4;
    }

    public String getBSEC_STRAS() {
        return BSEC_STRAS;
    }

    public void setBSEC_STRAS(String BSEC_STRAS) {
        this.BSEC_STRAS = BSEC_STRAS;
    }

    public String getBSEC_ORT01() {
        return BSEC_ORT01;
    }

    public void setBSEC_ORT01(String BSEC_ORT01) {
        this.BSEC_ORT01 = BSEC_ORT01;
    }

    public String getBSEC_PSTLZ() {
        return BSEC_PSTLZ;
    }

    public void setBSEC_PSTLZ(String BSEC_PSTLZ) {
        this.BSEC_PSTLZ = BSEC_PSTLZ;
    }

    public String getBSEC_LAND1() {
        return BSEC_LAND1;
    }

    public void setBSEC_LAND1(String BSEC_LAND1) {
        this.BSEC_LAND1 = BSEC_LAND1;
    }

    public String getBSEC_STCD1() {
        return BSEC_STCD1;
    }

    public void setBSEC_STCD1(String BSEC_STCD1) {
        this.BSEC_STCD1 = BSEC_STCD1;
    }

    public String getBSEC_STCD2() {
        return BSEC_STCD2;
    }

    public void setBSEC_STCD2(String BSEC_STCD2) {
        this.BSEC_STCD2 = BSEC_STCD2;
    }

    public String getBSEC_EMPFG() {
        return BSEC_EMPFG;
    }

    public void setBSEC_EMPFG(String BSEC_EMPFG) {
        this.BSEC_EMPFG = BSEC_EMPFG;
    }

    public String getBSEC_BANKL() {
        return BSEC_BANKL;
    }

    public void setBSEC_BANKL(String BSEC_BANKL) {
        this.BSEC_BANKL = BSEC_BANKL;
    }

    public String getBSEC_BANKN() {
        return BSEC_BANKN;
    }

    public void setBSEC_BANKN(String BSEC_BANKN) {
        this.BSEC_BANKN = BSEC_BANKN;
    }

    public String getBSEC_BANKS() {
        return BSEC_BANKS;
    }

    public void setBSEC_BANKS(String BSEC_BANKS) {
        this.BSEC_BANKS = BSEC_BANKS;
    }

    public String getWITH_ITEM_BUZEI() {
        return WITH_ITEM_BUZEI;
    }

    public void setWITH_ITEM_BUZEI(String WITH_ITEM_BUZEI) {
        this.WITH_ITEM_BUZEI = WITH_ITEM_BUZEI;
    }

    public String getWITH_ITEM_WITHT() {
        return WITH_ITEM_WITHT;
    }

    public void setWITH_ITEM_WITHT(String WITH_ITEM_WITHT) {
        this.WITH_ITEM_WITHT = WITH_ITEM_WITHT;
    }

    public String getWITH_ITEM_WT_WITHCD() {
        return WITH_ITEM_WT_WITHCD;
    }

    public void setWITH_ITEM_WT_WITHCD(String WITH_ITEM_WT_WITHCD) {
        this.WITH_ITEM_WT_WITHCD = WITH_ITEM_WT_WITHCD;
    }

    public String getWITH_ITEM_WT_QSSHB() {
        return WITH_ITEM_WT_QSSHB;
    }

    public void setWITH_ITEM_WT_QSSHB(String WITH_ITEM_WT_QSSHB) {
        this.WITH_ITEM_WT_QSSHB = WITH_ITEM_WT_QSSHB;
    }

    public String getWITH_ITEM_WT_QSSHH() {
        return WITH_ITEM_WT_QSSHH;
    }

    public void setWITH_ITEM_WT_QSSHH(String WITH_ITEM_WT_QSSHH) {
        this.WITH_ITEM_WT_QSSHH = WITH_ITEM_WT_QSSHH;
    }

    public String getWITH_ITEM_WT_QBSHB() {
        return WITH_ITEM_WT_QBSHB;
    }

    public void setWITH_ITEM_WT_QBSHB(String WITH_ITEM_WT_QBSHB) {
        this.WITH_ITEM_WT_QBSHB = WITH_ITEM_WT_QBSHB;
    }

    public String getWITH_ITEM_WT_QBSHH() {
        return WITH_ITEM_WT_QBSHH;
    }

    public void setWITH_ITEM_WT_QBSHH(String WITH_ITEM_WT_QBSHH) {
        this.WITH_ITEM_WT_QBSHH = WITH_ITEM_WT_QBSHH;
    }

    public String getWITH_ITEM_WITHT84() {
        return WITH_ITEM_WITHT84;
    }

    public void setWITH_ITEM_WITHT84(String WITH_ITEM_WITHT84) {
        this.WITH_ITEM_WITHT84 = WITH_ITEM_WITHT84;
    }

    public String getWITH_ITEM_WT_WITHCD85() {
        return WITH_ITEM_WT_WITHCD85;
    }

    public void setWITH_ITEM_WT_WITHCD85(String WITH_ITEM_WT_WITHCD85) {
        this.WITH_ITEM_WT_WITHCD85 = WITH_ITEM_WT_WITHCD85;
    }

    public String getWITH_ITEM_WT_QSSHB86() {
        return WITH_ITEM_WT_QSSHB86;
    }

    public void setWITH_ITEM_WT_QSSHB86(String WITH_ITEM_WT_QSSHB86) {
        this.WITH_ITEM_WT_QSSHB86 = WITH_ITEM_WT_QSSHB86;
    }

    public String getWITH_ITEM_WT_QSSHH87() {
        return WITH_ITEM_WT_QSSHH87;
    }

    public void setWITH_ITEM_WT_QSSHH87(String WITH_ITEM_WT_QSSHH87) {
        this.WITH_ITEM_WT_QSSHH87 = WITH_ITEM_WT_QSSHH87;
    }

    public String getWITH_ITEM_WT_QBSHB88() {
        return WITH_ITEM_WT_QBSHB88;
    }

    public void setWITH_ITEM_WT_QBSHB88(String WITH_ITEM_WT_QBSHB88) {
        this.WITH_ITEM_WT_QBSHB88 = WITH_ITEM_WT_QBSHB88;
    }

    public String getWITH_ITEM_WT_QBSHH89() {
        return WITH_ITEM_WT_QBSHH89;
    }

    public void setWITH_ITEM_WT_QBSHH89(String WITH_ITEM_WT_QBSHH89) {
        this.WITH_ITEM_WT_QBSHH89 = WITH_ITEM_WT_QBSHH89;
    }

    public String getBSET_BUZEI() {
        return BSET_BUZEI;
    }

    public void setBSET_BUZEI(String BSET_BUZEI) {
        this.BSET_BUZEI = BSET_BUZEI;
    }

    public String getBSET_MWSKZ() {
        return BSET_MWSKZ;
    }

    public void setBSET_MWSKZ(String BSET_MWSKZ) {
        this.BSET_MWSKZ = BSET_MWSKZ;
    }

    public String getBSET_HKONT() {
        return BSET_HKONT;
    }

    public void setBSET_HKONT(String BSET_HKONT) {
        this.BSET_HKONT = BSET_HKONT;
    }

    public String getBSET_SHKZG() {
        return BSET_SHKZG;
    }

    public void setBSET_SHKZG(String BSET_SHKZG) {
        this.BSET_SHKZG = BSET_SHKZG;
    }

    public String getBSET_HWBAS() {
        return BSET_HWBAS;
    }

    public void setBSET_HWBAS(String BSET_HWBAS) {
        this.BSET_HWBAS = BSET_HWBAS;
    }

    public String getBSET_FWBAS() {
        return BSET_FWBAS;
    }

    public void setBSET_FWBAS(String BSET_FWBAS) {
        this.BSET_FWBAS = BSET_FWBAS;
    }

    public String getBSET_HWSTE() {
        return BSET_HWSTE;
    }

    public void setBSET_HWSTE(String BSET_HWSTE) {
        this.BSET_HWSTE = BSET_HWSTE;
    }

    public String getBSET_FWSTE() {
        return BSET_FWSTE;
    }

    public void setBSET_FWSTE(String BSET_FWSTE) {
        this.BSET_FWSTE = BSET_FWSTE;
    }

    public String getBSET_KTOSL() {
        return BSET_KTOSL;
    }

    public void setBSET_KTOSL(String BSET_KTOSL) {
        this.BSET_KTOSL = BSET_KTOSL;
    }

    public String getBSET_KSCHL() {
        return BSET_KSCHL;
    }

    public void setBSET_KSCHL(String BSET_KSCHL) {
        this.BSET_KSCHL = BSET_KSCHL;
    }

    public String getBSET_BUPLA() {
        return BSET_BUPLA;
    }

    public void setBSET_BUPLA(String BSET_BUPLA) {
        this.BSET_BUPLA = BSET_BUPLA;
    }
    
    public String getCE11000_WWCOC(){
    	return CE11000_WWCOC;
    }
    
    public void setCE11000_WWCOC(String CE11000_WWCOC) {
        this.CE11000_WWCOC = CE11000_WWCOC;
    }
    
    public Date getDATE(){
    	return DATE;
    }
    
    public void setDATE(Date DATE){
    	this.DATE = DATE;
    }
    
  //Format 1
  	public String parseText(String data, int size, boolean isString) {
  		String str = "";

  		if (data == null) {
  			data = "";
  		}

  		data = data.trim();
  		int amt = size - data.length();
  		for (int i = 0; i < amt; i++) {
  			str += " ";
  		}
  		data += str;
  		return data;
  	}

  	//Format 2
  	public String parseTextZero(String data, int size, boolean isString) {
  		String str = "";

  		if (data == null) {
  			data = "";
  		}

  		data = data.trim();
  		int amt = size - data.length();

  		for (int i = 0; i < amt; i++) {
  			if (isString) {
  				str += " ";
  			} else {
  				str += "0";
  			}
  		}
  		str += data;

  		if (str.equals("0000000000") || str.equals("000000000000000000")
  				|| str.equals("000") || str.equals("00")) {
  			str = "";
  		}
  		return str;
  	}

  	//Format 3
  	public String parseIfZero(String data) {

  		if (data.equals("0.00") || data.equals(0) || data.equals("0")
  				|| data.equals("0.0")) {
  			data = null;
  		} else {
  			data = "'" + data + "'";
  		}

  		return data;
  	}

  	//Format 4
  	public String parseTextRight(String data, int size, boolean isString) {
  		String str = "";

  		if (data == null) {
  			data = "";
  		}
  		data = data.trim();
  		int amt = size - data.length();
  		for (int i = 0; i < amt; i++) {
  			str += " ";
  		}
  		data += str;
  		return data;
  	}
}
