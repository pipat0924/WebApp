<%-- <%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="menu-side">
    <div>
        <div class="list-group">
            <a href="#" class="list-group-item menu-toggle menu-close" style="text-align: right"><span class="glyphicon glyphicon-step-backward"></span></a>
            <a href="Dashboard.jsp" class="list-group-item small"><span class="glyphicon glyphicon-star-empty"></span> แผงแสดงงาน (Dashboard)</a>
            <a href="#" class="list-group-item active" data-toggle="collapse" data-target="#list_payment" data-parent="#menu"><span class="glyphicon glyphicon-shopping-cart"></span> รับชำระเงิน<span class="glyphicon glyphicon-chevron-down pull-right"></span></a>
            <div id="list_payment" class="sublinks collapse">
                <a href="pay-service-charge.jsp?new" class="list-group-item small"><span class="glyphicon glyphicon-barcode"></span> รับชำระค่าใช้บริการ</a>
                <a href="epis_penalty_and_extend.jsp" class="list-group-item small"><span class="glyphicon glyphicon-barcode"></span> รับชำระค่าต่อ / ค่าปรับ</a>
                <!--<a href="pay-6-stap_1.jsp?new" class="list-group-item small"><span class="glyphicon glyphicon-edit"></span> รับชำระค่าบริการ (MNP)</a>-->
                <a href="pay-7-stap_1.jsp?new" class="list-group-item small"><span class="glyphicon glyphicon-tasks"></span> รับชำระค่าบริการ (Top UP)</a>
                <a href="pay-7-stap_1.jsp?new" class="list-group-item small"><span class="glyphicon glyphicon-tasks"></span> รับชำระค่าเติมเงินออนไลน์ (Top up)</a>
                <a href="pay-other_1.jsp?new" class="list-group-item small"><span class="glyphicon glyphicon-briefcase"></span> รับชำระค่าบริการอื่นๆ</a>
                <a href="pay-tboss-charge_1.jsp?new" class="list-group-item small"><span class="glyphicon glyphicon-magnet"></span> รับชำระหนี้สูญ (TBOSS)</a>
                <a href="pay-ot-tboss-charge_1.jsp?new" class="list-group-item small"><span class="glyphicon glyphicon-magnet"></span> รับชำระหนี้ นอก TBOSS</a>

                <a href="pay-manual-service-1.jsp?new" class="list-group-item small"><span class="glyphicon glyphicon-road"></span> รับชำระแบบ Manual</a>
            </div>
            <!--by NSD 10-01-2017-->
            <a href="#" class="list-group-item active" data-toggle="collapse" data-target="#list_payment_agent" data-parent="#menu"><span class="glyphicon glyphicon-shopping-cart"></span> รับชำระเงินจากตัวแทน<span class="glyphicon glyphicon-chevron-down pull-right"></span></a>
            <div id="list_payment_agent" class="sublinks collapse">
                <a href="pay-6-stap_1.jsp?new" class="list-group-item small"><span class="glyphicon glyphicon-edit"></span> รับชำระค่าบริการ (MNP)</a>
                <a href="pay-agent_1.jsp?new" class="list-group-item small"><span class="glyphicon glyphicon-road"></span> รับชำระตัวแทน (AGENT)</a>
            </div>
            <!--END NSD-->
            <a href="#" class="list-group-item active" data-toggle="collapse" data-target="#list_2" data-parent="#menu"><span class="glyphicon glyphicon-export"></span> ยกเลิกรับชำระเงิน<span class="glyphicon glyphicon-chevron-down pull-right"></span></a>
            <div id="list_2" class="sublinks collapse in">
                <a href="cancel-service-charge_1.jsp?new" class="list-group-item small"><span class="glyphicon glyphicon-remove-sign"></span> ยกเลิกรับชำระค่าบริการ</a>
                <a href="cancel-penaltyExtend_1.jsp?new" class="list-group-item small"><span class="glyphicon glyphicon-remove-sign"></span> ยกเลิกรับชำระค่าปรับ/ค่าต่อ</a>
                <a href="cancel-mnp-charge_1.jsp?new" class="list-group-item small"><span class="glyphicon glyphicon-warning-sign"></span> ยกเลิกรับชำระค่าบริการ (MNP)</a>
                <a href="cancel-TopUp.jsp?new" class="list-group-item small"><span class="glyphicon glyphicon-floppy-remove"></span> ยกเลิกรับชำระค่าเติมเงินออนไลน์ (Top UP)</a>
                <a href="cancel-other-charge_1.jsp?new" class="list-group-item small"><span class="glyphicon glyphicon-copyright-mark"></span> ยกเลิกชำระค่าบริการอื่นๆ</a>
                <a href="cancel-tboss-charge_1.jsp?new" class="list-group-item small"><span class="glyphicon glyphicon-bullhorn"></span> ยกเลิกชำระค่าหนี้สูญ (TBOSS)</a>
                <a href="cancel-ot-tboss-charge_1.jsp?new" class="list-group-item small"><span class="glyphicon glyphicon-bullhorn"></span> ยกเลิกชำระค่าหนี้ นอก TBOSS</a>
                <a href="cancel-agent-charge_1.jsp?new" class="list-group-item small"><span class="glyphicon glyphicon-adjust"></span> ยกเลิกรับชำระตัวแทน (AGENT)</a>
            </div>
            <a href="#" class="list-group-item active" data-toggle="collapse" data-target="#list_3" data-parent="#menu"><span class="glyphicon glyphicon-envelope"></span> บันทึกคำร้อง<span class="glyphicon glyphicon-chevron-down pull-right"></span></a>
            <div id="list_3" class="sublinks collapse in">
                <a href="adjust-payment_1.jsp" class="list-group-item small"><span class="glyphicon glyphicon-list-alt"></span> บันทึกคำร้องขอปรับลดหนี้</a>
                <a href="revert-payment_1.jsp" class="list-group-item small"><span class="glyphicon glyphicon-comment"></span> บันทึกคำร้องขอคืนเงิน</a>
            </div>
            <a href="#" class="list-group-item active" data-toggle="collapse" data-target="#list_4" data-parent="#menu"><span class="glyphicon glyphicon-magnet"></span> อนุมัติคำร้อง<span class="glyphicon glyphicon-chevron-down pull-right"></span></a>
            <div id="list_4" class="sublinks collapse in">
                <a href="approve-adjust-payment_1.jsp" class="list-group-item small"><span class="glyphicon glyphicon-check"></span> อนุมัติการร้องขอปรับลดหนี้</a>
                <a href="approve-revert-payment_1.jsp" class="list-group-item small"><span class="glyphicon glyphicon-star"></span> อนุมัติการร้องขอคืนเงิน</a>
            </div>
            <a href="#" class="list-group-item active" data-toggle="collapse" data-target="#list_5" data-parent="#menu"><span class="glyphicon glyphicon-calendar"></span> ประวัติการรับชำระเงิน<span class="glyphicon glyphicon-chevron-down pull-right"></span></a>
            <div id="list_5" class="sublinks collapse in">
                <a href="history-service_1.jsp" class="list-group-item small"><span class="glyphicon glyphicon-filter"></span> ประวัติการรับชำระค่าบริการ</a>
                <a href="history-penalty_extend_1.jsp" class="list-group-item small"><span class="glyphicon glyphicon-filter"></span> ประวัติการรับชำระค่าปรับค่าต่อ</a>
                <a href="history-mnp_1.jsp" class="list-group-item small"><span class="glyphicon glyphicon-globe"></span> ประวัติการรับชำระค่าบริการ (MNP)</a>
                <a href="history-topup_1.jsp" class="list-group-item small"><span class="glyphicon glyphicon-retweet"></span> ประวัติการรับชำระค่าเติมเงินออนไลน์ (Top UP)</a>
                <a href="history-other_1.jsp" class="list-group-item small"><span class="glyphicon glyphicon-bullhorn"></span> ประวัติการรับชำระค่าบริการอื่นๆ</a>
                <a href="history-tboss_1.jsp" class="list-group-item small"><span class="glyphicon glyphicon-bell"></span> ประวัติการรับชำระค่าหนี้สูญ (TBOSS)</a>
                <a href="#" class="list-group-item small"><span class="glyphicon glyphicon-bell"></span> ประวัติการรับชำระค่าหนี้สูญ (นอก TBOSS)</a>
                <a href="history_agent.jsp" class="list-group-item small"><span class="glyphicon glyphicon-th-large"></span> ประวัติการรับชำระตัวแทน (AGENT)</a> Agent ไม่มีประวัติการรับชำระ
                <a href="reprint-history.jsp" class="list-group-item small"><span class="glyphicon glyphicon-th-large"></span> ประวัติการพิมพ์ใบเสร็จรับเงินซ้ำ/สำเนา/ใบแทน</a>
            </div>
            <a href="#" class="list-group-item active" data-toggle="collapse" data-target="#list_6" data-parent="#menu"><span class="glyphicon glyphicon-bookmark"></span> พิมพ์ใบเสร็จรับเงิน<span class="glyphicon glyphicon-chevron-down pull-right"></span></a>
            <div id="list_6" class="sublinks collapse in">
                <a href="page-6-1.jsp" class="list-group-item small"><span class="glyphicon glyphicon-print"></span> พิมพ์ใบเสร็จรับเงินซ้ำ/สำเนา/ใบแทน</a>
            </div>
            <a href="#" class="list-group-item active" data-toggle="collapse" data-target="#list_7" data-parent="#menu"><span class="glyphicon glyphicon-inbox"></span> รายงานทั้งหมด Front Offlice <span class="glyphicon glyphicon-chevron-down pull-right"></span></a>
            <div id="list_7" class="sublinks collapse in">
                <a href="epis-daily-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานการรับชำระ</a>
                <a href="epis-daily-cheque-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานการรับชำระด้วยเช็ค</a>
                <a href="epis-exchange-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานกำไร/ขาดทุนจากการปริวรรตเงินตรา</a>
                <a href="epis-daily-tax-sale-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานภาษีขาย/ใบกำกับภาษีเต็มรูป</a>
                <a href="epis-daily-tax-abbreviate-sale-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานภาษีขาย/ใบกำกับภาษีแบบย่อ</a>
                <a href="epis-monthly-tax-abbreviate-sale-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานภาษีขาย/ใบกำกับภาษีแบบย่อ</a>
                <a href="epis-daily-new-receipt-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานภาษีขาย (การยกเลิกใบกำกับภาษีฉบับเดิมและออกฉบับใหม่)</a>
                <a href="epis-daily-receipt-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานภาษีขาย (การออกใบแทนใบกำกับภาษี)</a>
                <a href="epis-daily-deduction-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานภาษีหัก ณ ที่จ่าย</a>
                <a href="epis-monthly-deduction-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานภาษีหัก ณ ที่จ่าย รายเดือน</a>
                <a href="epis-daily-summary-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานสรุปของการรับชำระเงิน</a>
                <a href="close-endofday-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานปิดบัญชีการรับชำระเงิน</a>
                <a href="unclose-endofday-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานไม่ปิดบัญชี</a>
                <a href="offline-payment-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานรับชำระเงินขณะที่เครื่อง Offline</a>
                <a href="cancel-payment-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานการยกเลิกการรับชำระเงิน</a>
                <a href="egp-payment-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานรับชำระเงิน บช.1</a>
                <a href="egp-project-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานรายรับโครงการ/สัญญา บช.1</a>
                <!-- Uncompleate -->
                <a href="epis-daily-currencyForeign-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-star-empty"></span> รายงานการรับชำระเงินสกุลต่างประเทศ</a>
                <a href="0-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-star-empty"></span> รายงานสถิติการรับชำระเงิน</a>
                <a href="epis-daily-penaltyExtend-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-star-empty"></span> รายงานค่าต่อ/ค่าปรับ</a>
                <a href="epis-daily-tax-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> แก้ไขรายงานภาษีหัก ณ ที่จ่าย </a>
            </div>
            <a href="#" class="list-group-item active" data-toggle="collapse" data-target="#list_8" data-parent="#menu"><span class="glyphicon glyphicon-inbox"></span> รายงานทั้งหมด Back Office <span class="glyphicon glyphicon-chevron-down pull-right"></span></a>
            <div id="list_8" class="sublinks collapse in">
                <a href="epis-daily-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานการรับชำระ</a>
                <a href="epis-daily-tax-sale-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานภาษีขาย/ใบกำกับภาษีเต็มรูป</a>
                <a href="epis-daily-tax-abbreviate-sale-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานภาษีขาย/ใบกำกับภาษีแบบย่อ</a>
                <a href="epis-daily-deduction-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานภาษีหัก ณ ที่จ่าย</a>
                <a href="epis-daily-summary-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานสรุปของการรับชำระเงิน</a>
                <a href="cancel-payment-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานภาษีขายยกเลิกใบกำกับภาษี</a>
            </div>
            <a href="#" class="list-group-item active" data-toggle="collapse" data-target="#list_9" data-parent="#menu"><span class="glyphicon glyphicon-inbox"></span> รายงานทั้งหมด (AGENT) <span class="glyphicon glyphicon-chevron-down pull-right"></span></a>
            <div id="list_9" class="sublinks collapse">
                <a href="agent_daily_report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานการรับชำระ</a>
                <a href="agent-daily-tax-sale-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานภาษีขาย/ใบกำกับภาษีเต็มรูป</a>
                <a href="agent-daily-tax-abbreviate-sale-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานภาษีขาย/ใบกำกับภาษีแบบย่อ</a>
                <a href="agent-daily-deduction-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานภาษีหัก ณ ที่จ่าย</a>
                <a href="agent-daily-summary-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานสรุปของการรับชำระเงิน</a>
                <a href="cancel-pay-agent-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานการยกเลิกการรับชำระเงิน</a>
                <a href="close-endofday-report.jsp" class="list-group-item small"><span class="glyphicon glyphicon-film"></span> รายงานปิดบัญชีการรับชำระเงิน</a>

            </div>
            <a href="#" class="list-group-item active" data-toggle="collapse" data-target="#list_10" data-parent="#menu"><span class="glyphicon glyphicon-cog"></span> ตั้งค่า<span class="glyphicon glyphicon-chevron-down pull-right"></span></a>
            <div id="list_10" class="sublinks collapse">
                <a href="epis-master-ignore-billing-group.jsp" class="list-group-item small"><span class="glyphicon glyphicon-th-list"></span> ตั้งค่า Ignore Billing Group</a>
                <a href="package-promotion.jsp" class="list-group-item small"><span class="glyphicon glyphicon-th-list"></span> ตั้งค่า โปรโมชั่น</a>
                <a href="verify-config.jsp" class="list-group-item small"><span class="glyphicon glyphicon-th-list"></span> ตั้งค่าการระบุตัวตน</a>
                <a href="epis-master-billing-group-credit-limit.jsp" class="list-group-item small"><span class="glyphicon glyphicon-th-list"></span> ตั้งค่า Billing Group (Credit Limit)</a>
                <a href="epis-dw-dm.jsp" class="list-group-item small"><span class="glyphicon glyphicon-th-list"></span> Export DWDM</a>
            </div>
            <a href="#" class="list-group-item active" data-toggle="collapse" data-target="#list_11" data-parent="#menu"><span class="glyphicon glyphicon-qrcode"></span> ระบบอื่นๆ<span class="glyphicon glyphicon-chevron-down pull-right"></span></a>
            <div id="list_11" class="sublinks collapse">
            	<!-- UAT (Dev.) : https://pdomps01.cattelecom.com:444/ProcessPortal/login.jsp -->
				<!--   Prod.    : https://pomelb.cattelecom.com/ProcessPortal/login.jsp -->
                <a href="https://pdomps01.cattelecom.com:444/ProcessPortal/login.jsp" target="_blank" class="list-group-item small"><span class="glyphicon glyphicon-user"></span> UDB</a>
                <a class="list-group-item small"><span class="glyphicon glyphicon-gift"></span> INSALE</a>
            </div>
        </div>
    </div>
</nav>
 --%>
<%@ include file="/commons/includes.jsp" %> 
<nav class="menu-side">
    <div>
        <div class="list-group">
        
        

        <!-- <a href="Dashboard.jsp" class="list-group-item small"><span class="glyphicon glyphicon-star-empty"></span> แผงแสดงงาน (Dashboard)</a>

        <a href="#" class="list-group-item active" data-toggle="collapse" data-target="#list_payment" data-parent="#menu"><span class="glyphicon glyphicon-shopping-cart"></span> รับชำระเงิน<span class="glyphicon glyphicon-chevron-down pull-right"></span></a> -->
        
            <a href="#" class="list-group-item menu-toggle menu-close" style="text-align: right"><span class="glyphicon glyphicon-step-backward"></span></a>
           
            
            <c:set var="submenuFlag" value="0"/>
            <c:set var="datatarget" value=""/>
            <c:forEach var="menuUser" items="${sessionScope.menuMainData}">
           	 <c:choose>
       <%--      <c:choose>
  <c:when test="${condition1}">
    ...
  </c:when>
  <c:when test="${condition2}">
    ...
  </c:when> --%>
             
             	<c:when test="${not empty menuUser.url && menuUser.parrentId == 0 && menuUser.isEnabled ==1}">
	         		<a href="<c:out value="${menuUser.url}" ></c:out>" class="list-group-item small"><span class="glyphicon glyphicon-star-empty"></span> <c:out value="${menuUser.name}" ></c:out></a>
	         	</c:when>
	         	
				<c:when test="${empty menuUser.url  && menuUser.parrentId == 0 && menuUser.isEnabled ==1 }">
				
					<c:if test="${submenuFlag ==1}"><!-- submenu -->
		   	    		</div> 
		   	    		<c:set var="submenuFlag" value="0"/>
		   	    			
		   	    		 
		   	    	</c:if>
		   	    	<c:set var="datatarget" value="list${menuUser.id}"/>
		   	    	
		   	    	
	         		<a href="#" class="list-group-item active" data-toggle="collapse" data-target="<c:out value="#${datatarget}" ></c:out>" data-parent="#menu"><!-- <span class="glyphicon glyphicon-shopping-cart"></span> --><c:out value="${menuUser.name}" ></c:out><span class="glyphicon glyphicon-chevron-down pull-right"></span></a>
	         	</c:when>
	         	
		   	    <c:when test="${not empty menuUser.url && menuUser.parrentId != 0 && menuUser.isEnabled ==1}">
		   	    	<c:if test="${submenuFlag ==0}"><!-- submenu -->
		   	    		<div id="<c:out value="${datatarget}" ></c:out>" class="sublinks collapse"> 
		   	    		<c:set var="submenuFlag" value="1"/>
		   	    		
		   	    	</c:if>
		   	    
	         		<a href="<c:out value="${menuUser.url}" ></c:out>" class="list-group-item small"><!-- <span class="glyphicon glyphicon-barcode"> --></span> <c:out value="${menuUser.name}" ></c:out></a>
	         	
         		</c:when>
            
           	 </c:choose>
            </c:forEach> 
             	
            
        </div>
    </div>
</nav>

<script>
/* loadListPaymentAgent();
function loadListPaymentAgent() {
    $("#list_payment_agent").append('<a href="pay-6-stap_1.jsp?new" class="list-group-item small"><span class="glyphicon glyphicon-edit"></span> รับชำระค่าบริการ (MNP)</a>')
    $.get("../loadMenuListPaymentAgent.json", function (res) {
        console.log(res.data);
        var agents = res.data;
        for (var i = 0; i < agents.length; i++) {
            var params = "id="+agents[i].id;
            $("#list_payment_agent").append('<a href="pay-agent_1.jsp?'+params+'" class="list-group-item small"><span class="glyphicon glyphicon-road"></span> รับชำระ'+agents[i].name+'</a>')
        }
    });
} */
</script>