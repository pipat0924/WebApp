<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>EPIS Back Office : Office Management UI.</title>
    <link href="../pages/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="../pages/resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css" />
    <link href="../pages/resources/custom.css" rel="stylesheet" type="text/css" />
    <script src="../pages/resources/jquery.min.js" type="text/javascript"></script>
    <script src="../pages/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="../pages/resources/bootstrap-table/src/bootstrap-table.js"></script>
    <script src="../pages/resources/custom.js" type="text/javascript"></script>
    <script>
        function runningFormatter(val, row, ind) { return ind + 1; }
        function runningMsgHide() { $('#alertSuccessAreaMsg').addClass('hide'); $('#alertDuplicateMsg').addClass('hide');$('#alertDangerAreaMsg').addClass('hide'); }
        function isPositiveFormatter(val) {var target; if("0" == val) {target = "ระงับใช้งาน";} else if("1" == val) { target = "เปิดใช้งาน";} return target; }
        function rowsFormatter(id) {return '<a id="shopEdit" style="cursor:pointer" onClick="findShopDetailById(\''+ id +'\')" class="btn-sm btn-info"><i class="glyphicon glyphicon-edit"></i> แก้ไข</a>'; }
        function findShopDetailById(id) {
    		$.get("../findShopDetailById.json", { "id": id }, function(res) {
    			$('#searchShopPanel').addClass('hide'); 
    			$('#displayShopPanel').addClass('hide');  
    			$('#createShopPanel').removeClass('hide');  
    			$('#resetCreate').addClass('disabled');
    			$("#id").val(res.id); 
    			$("#businessPlace").val(res.businessPlace); 
    			$("#buPlaceName").val(res.buPlaceName); 
    			$("#businessArea").val(res.businessArea); 
    			$("#buAreaName").val(res.buAreaName); 
    			$("#costCenter").val(res.costCenter); 
    			$("#isPositive").val(res.isPositive); 
    			$("#descAbvrTh").val(res.descAbvrTh); 
    			$("#costCenter").val(res.costCenter);
                 $("#costCenterName").val(res.costCenterName);
                 $("#building").val(res.building);
                 $("#floor").val(res.floor);
                 $("#room").val(res.room);
                 $("#address").val(res.address);
                 $("#street").val(res.street);
                 $("#subdistrict").val(res.subdistrict);
                 $("#district").val(res.district);
                 $("#province").val(res.province);
                 $("#zipCode").val(res.zipCode);
                 $("#phone").val(res.phone);
                 $("#fax").val(res.fax);
                 $("#email").val(res.email);
    		});
        }
    </script>
</head>
<body>
<header class="header_page"></header>
<section class="container-fluid menu">
    <div class="row">
        <div class="col-md-12 tab-modefile">
            <ol class="breadcrumb">
                <li><i>การจัดการทั่วไป</i></li>
                <li class="active"><i>ข้อมูลหน่วยงานรับชําระ</i></li>
            </ol>
        </div>
    </div>
    <div class="row">

        <div class="col-md-12 tab-modefile">
        			<div id="alertDuplicateMsg" class="hide alert alert-danger"><span class="glyphicon glyphicon-ok-sign"></span> รหัสBAซ้ำ</div>
            <div id="alertSuccessAreaMsg" class="hide alert alert-success"><span class="glyphicon glyphicon-ok-sign"></span> บันทึกเรียบร้อย</div>
            <div id="alertDangerAreaMsg" class="hide alert alert-danger"><span class="glyphicon glyphicon-remove-sign"></span> เกิดข้อผิดพลาด</div>
            <!-- Search -->
            <div id="searchShopPanel">
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-filter"></span> ค้นหาหน่วยงานรับชําระ</a></li>
                </ul>
                <div class="panel panel-default panal-radius">
                    <div class="panel-body">
                        <div class="tab-content">
                            <div role="tabpanel" class="tab-pane active" id="tab_1">
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <label class="control-label col-sm-2">รหัสพื้นที่ธุระกิจ (BP) :</label>
                                        <div class="col-sm-2"><input class="form-control" id="businessPlaceForSh" name="businessPlaceForSh"></div>
                                        <label class="control-label col-sm-2">รหัสหน่วยงาน/สาขา (BA) :</label>
                                        <div class="col-sm-2"><input class="form-control" id="businessAreaForSh" name="businessAreaForSh"></div>
                                        <div class="col-sm-3"></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-2">ชื่อหน่วยงาน/สาขา :</label>
                                        <div class="col-sm-6">
                                            <input class="form-control" id="nameForSh" name="nameForSh">
                                        </div>
                                        <div class="col-sm-4">
                                            <a id="searchShop" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูล</a>&nbsp;
                                            <a id="resetSearch" class="btn btn-danger"><span class="glyphicon glyphicon-minus-sign"></span> ยกเลิก</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <a id="createShop" class="btn btn-success"><span class="glyphicon glyphicon-file"></span> เพิ่มรายการ</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Display -->
            <div id="displayShopPanel" class="hide tab-content">
                <div role="tabpanel" class="tab-pane active" id="tab-2-1">
                    <table id="shopTable" data-toggle="table" data-toolbar="#shopToolbar" data-search="true" data-show-pagination-switch="true" data-show-refresh="true" data-show-toggle="true" data-show-columns="true" data-show-export="true" data-pagination="true" data-height="400" data-cache="false">
                        <thead>
                        <tr>
                            <th data-field="businessPlace" data-align="center">รหัสพื้นที่ธุระกิจ (BP)</th>
                            <th data-field="buPlaceName" data-align="center">ชื่อพื้นที่ธุระกิจ</th>
                            <th data-field="businessArea" data-align="center">รหัสหน่วยงาน/สาขา (BA)</th>
                            <th data-field="buAreaName" data-align="center">ชื่อหน่วยงาน/สาขา</th>
                            <th data-field="descAbvrTh" data-align="center">ชื่อหน่วยงาน/สาขา (ย่อ)</th>
                            <th data-field="costCenter" data-align="center">รหัสศูนย์ต้นทุน</th>
                            <th data-field="costCenterName" data-align="center">ชื่อศูนย์ต้นทุน</th>
                            <th data-field="building" data-align="center">อาคาร</th>
                            <th data-field="floor" data-align="center">ชั้น</th>
                            <th data-field="room" data-align="center">ห้อง</th>
                            <th data-field="address" data-align="center">ที่อยู่</th>
                            <th data-field="street" data-align="center">ถนน</th>
                            <th data-field="subdistrict" data-align="center">แขวง/ตำบล</th>
                            <th data-field="district" data-align="center">เขต/อำเภอ</th>
                            <th data-field="province" data-align="center">จังหวัด</th>
                            <th data-field="zipCode" data-align="center">รหัสไปรษณีย์</th>
                            <th data-field="phone" data-align="center">โทรศัพท์</th>
                            <th data-field="fax" data-align="center">โทรสาร</th>
                            <th data-field="email" data-align="center">Email</th>
                            <th data-field="isPositive" data-formatter="isPositiveFormatter" data-align="left">สถานะ</th>
                            <th data-field="id" data-formatter="rowsFormatter" data-align="center"></th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <!-- Create -->
            <div id="createShopPanel" class="hide">
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-list"></span> เพิ่มหน่วยงานรับชําระ</a></li>
                </ul>
                <div class="panel panel-default panal-radius">
                    <form id="createShopFm" method="post" class="form-horizontal" role="form">
                        <div class="panel-body">
                            <div class="tab-content">
                                <div role="tabpanel" class="tab-pane active" id="tab_1">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">รหัสพื้นที่ธุระกิจ (BP) :</label>
                                            <div class="col-sm-2"><input class="form-control" id="businessPlace" name="businessPlace"></div>
                                            <label class="control-label col-sm-2">ชื่อพื้นที่ธุระกิจ :</label>
                                            <div class="col-sm-2"><input class="form-control" id="buPlaceName" name="buPlaceName"></div>
                                            <div class="col-sm-3"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">รหัสหน่วยงาน/สาขา (BA) :</label>
                                            <div class="col-sm-2"><input class="form-control" id="businessArea" name="businessArea"></div>
                                            <label class="control-label col-sm-2">ชื่อหน่วยงาน/สาขา :</label>
                                            <div class="col-sm-2"><input class="form-control" id="buAreaName" name="buAreaName"></div>
                                            <div class="col-sm-3"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">รหัสศูนย์ต้นทุน :</label>
                                            <div class="col-sm-2"><input class="form-control" id="costCenter" name="costCenter"></div>
                                            <label class="control-label col-sm-2">ชื่อหน่วยงาน/สาขา (ย่อ) :</label>
                                            <div class="col-sm-2"><input class="form-control" id="descAbvrTh" name="descAbvrTh" ></div>
                                            <div class="col-sm-3"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">ชื่อศูนย์ต้นทุน :</label>
                                            <div class="col-sm-2"><input class="form-control" id="costCenterName" name="costCenterName"></div>
                                            <label class="control-label col-sm-2">อาคาร :</label>
                                            <div class="col-sm-2"><input class="form-control" id="building" name="building"></div>
                                            <div class="col-sm-3"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">ชั้น :</label>
                                            <div class="col-sm-2"><input class="form-control" id="floor" name="floor"></div>
                                            <label class="control-label col-sm-2">ห้อง :</label>
                                            <div class="col-sm-2"><input class="form-control" id="room" name="room"></div>
                                            <div class="col-sm-3"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">ที่อยู่ :</label>
                                            <div class="col-sm-2"><input class="form-control" id="address" name="address"></div>
                                            <label class="control-label col-sm-2">ถนน :</label>
                                            <div class="col-sm-2"><input class="form-control" id="street" name="street"></div>
                                            <div class="col-sm-3"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">แขวง/ตำบล :</label>
                                            <div class="col-sm-2"><input class="form-control" id="subdistrict" name="subdistrict"></div>
                                            <label class="control-label col-sm-2">เขต/อำเภอ :</label>
                                            <div class="col-sm-2"><input class="form-control" id="district" name="district"></div>
                                            <div class="col-sm-3"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">จังหวัด :</label>
                                            <div class="col-sm-2">
                                                <select class="form-control" id="province">
                                                </select>
                                            </div>
                                            <label class="control-label col-sm-2">รหัสไปรษณีย์ :</label>
                                            <div class="col-sm-2"><input class="form-control" id="zipCode" name="zipCode"></div>
                                            <div class="col-sm-3"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">โทรศัพท์ :</label>
                                            <div class="col-sm-2"><input class="form-control" id="phone" name="phone"></div>
                                            <label class="control-label col-sm-2">โทรสาร :</label>
                                            <div class="col-sm-2"><input class="form-control" id="fax" name="fax"></div>
                                            <div class="col-sm-3"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">Email :</label>
                                            <div class="col-sm-2"><input class="form-control" id="email" name="email"></div>
                                            <label class="control-label col-sm-2">สถานะการใช้งาน :</label>
                                            <div class="col-sm-2">
                                                <select class="form-control" id="isPositive" name="isPositive">
                                                    <option value="">- กรุณาเลือก -</option>
                                                    <option value="1">เปิดใช้งาน</option>
                                                    <option value="0">ระงับใช้งาน</option>
                                                </select>
                                            </div>
                                            <div class="col-sm-4">
                                                <a id="saveShop" class="btn btn-primary"><span class="glyphicon glyphicon-plus-sign"></span> บันทึกข้อมูล</a>&nbsp;
                                                <a id="resetCreate" class="btn btn-danger"><span class="glyphicon glyphicon-minus-sign"></span> ยกเลิก</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <a id="backToSearch" class="btn btn-success"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูล</a>
                                            </div>
                                            <input type="hidden" id="id" name="id" value="0"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    </div>
</section>
</body>
<script>
    var view = (function($){
        var self = this;
        function DropDown(el, url) { var obj = this, data = [{ key: "", value: "", text: "Please Select" }]; obj.el = el; obj.elem = $(el);
            obj.data = function(array) { if ($.type(array) == "array") { setup(data = array); return this } return data; };
            obj.init = function(url) { if (url) $.get(url, function(res) { setup(data = res.data);}); else setup(data); return this };
            obj.disable = function(flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this }
            obj.enable = function() { obj.disable(false); return this }
            obj.index = function() { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function(i, opt){ opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function(i, opt){ return opt.selected ? "selected" : "no-selected" })) }
            obj.selected = function(){ return data[obj.index()]; };
            obj.val = function() { return obj.elem.val(); };
            obj.key = function(){ if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
            function setup(array) { obj.elem.find("*").remove();
                $.each(array,function(i,o){
                    obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.key +'" value="'+ o.value +'">'+ (o.text || o.value) +'</option>')
                });
            }
            data = obj.elem.change(window[el.substring(1) +"ChangeEvent"]).find("option").map(function(i, opt){ return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });

        }
        self.province = new DropDown("#province").data([
                {key:"  0   ", value:"  "},
                {key:"	1	", value:"	กระบี่	"},
                {key:"	2	", value:"	กรุงเทพมหานคร	"},
                {key:"	3	", value:"	กาญจนบุรี	"},
                {key:"	4	", value:"	กาฬสินธุ์	"},
                {key:"	5	", value:"	กำแพงเพชร	"},
                {key:"	6	", value:"	ขอนแก่น	"},
                {key:"	7	", value:"	จันทบุรี	"},
                {key:"	8	", value:"	ฉะเชิงเทรา	"},
                {key:"	9	", value:"	ชลบุรี	"},
                {key:"	10	", value:"	ชัยนาท	"},
                {key:"	11	", value:"	ชัยภูมิ	"},
                {key:"	12	", value:"	ชุมพร	"},
                {key:"	13	", value:"	เชียงราย	"},
                {key:"	14	", value:"	เชียงใหม่	"},
                {key:"	15	", value:"	ตรัง	"},
                {key:"	16	", value:"	ตราด	"},
                {key:"	17	", value:"	ตาก	"},
                {key:"	18	", value:"	นครนายก	"},
                {key:"	19	", value:"	นครปฐม	"},
                {key:"	20	", value:"	นครพนม	"},
                {key:"	21	", value:"	นครราชสีมา	"},
                {key:"	22	", value:"	นครศรีธรรมราช	"},
                {key:"	23	", value:"	นครสวรรค์	"},
                {key:"	24	", value:"	นนทบุรี	"},
                {key:"	25	", value:"	นราธิวาส	"},
                {key:"	26	", value:"	น่าน	"},
                {key:"	27	", value:"	บุรีรัมย์	"},
                {key:"	28	", value:"	ปทุมธานี	"},
                {key:"	29	", value:"	ประจวบคีรีขันธ์	"},
                {key:"	30	", value:"	ปราจีนบุรี	"},
                {key:"	31	", value:"	ปัตตานี	"},
                {key:"	32	", value:"	พระนครศรีอยุธยา	"},
                {key:"	33	", value:"	พะเยา	"},
                {key:"	34	", value:"	พังงา	"},
                {key:"	35	", value:"	พัทลุง	"},
                {key:"	36	", value:"	พิจิตร	"},
                {key:"	37	", value:"	พิษณุโลก	"},
                {key:"	38	", value:"	เพชรบุรี	"},
                {key:"	39	", value:"	เพชรบูรณ์	"},
                {key:"	40	", value:"	แพร่	"},
                {key:"	41	", value:"	ภูเก็ต	"},
                {key:"	42	", value:"	มหาสารคาม	"},
                {key:"	43	", value:"	มุกดาหาร	"},
                {key:"	44	", value:"	แม่ฮ่องสอน	"},
                {key:"	45	", value:"	ยโสธร	"},
                {key:"	46	", value:"	ยะลา	"},
                {key:"	47	", value:"	ร้อยเอ็ด	"},
                {key:"	48	", value:"	ระนอง	"},
                {key:"	49	", value:"	ระยอง	"},
                {key:"	50	", value:"	ราชบุรี	"},
                {key:"	51	", value:"	ลพบุรี	"},
                {key:"	52	", value:"	เลย	"},
                {key:"	53	", value:"	ลำปาง	"},
                {key:"	54	", value:"	ลำพูน	"},
                {key:"	55	", value:"	ศีรสะเกษ	"},
                {key:"	56	", value:"	สกลนคร	"},
                {key:"	57	", value:"	สงขลา	"},
                {key:"	58	", value:"	สตูล	"},
                {key:"	59	", value:"	สมุทรปราการ	"},
                {key:"	60	", value:"	สมุทรสงคราม	"},
                {key:"	61	", value:"	สมุทรสาคร	"},
                {key:"	62	", value:"	สระแก้ว	"},
                {key:"	63	", value:"	สระบุรี	"},
                {key:"	64	", value:"	สิงห์บุรี	"},
                {key:"	65	", value:"	สุโขทัย	"},
                {key:"	66	", value:"	สุพรรณบุรี	"},
                {key:"	67	", value:"	สุราษฎร์ธานี	"},
                {key:"	68	", value:"	สุรินทร์	"},
                {key:"	69	", value:"	หนองคาย	"},
                {key:"	70	", value:"	หนองบัวลำภู	"},
                {key:"	71	", value:"	อ่างทอง	"},
                {key:"	72	", value:"	อำนาจเจริญ	"},
                {key:"	73	", value:"	อุดรธานี	"},
                {key:"	74	", value:"	อุตรดิตถ์	"},
                {key:"	75	", value:"	อุทัยธานี	"},
                {key:"	76	", value:"	อุบลราชธานี	"}
                ]);

        return this;
    })(jQuery);

    $(document).on("click", "a#searchShop", function() {
    	 runningMsgHide();
         if($('#businessPlaceForSh').val() == '' && $('#businessAreaForSh').val() == '' && $('#nameForSh').val() == '') {
             if($('#businessPlaceForSh').val() == '') { $('#businessPlaceForSh').attr("style", "color:#a94442"); $("#businessPlaceForSh").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
             if($('#businessAreaForSh').val() == '') { $('#businessAreaForSh').attr("style", "color:#a94442"); $("#businessAreaForSh").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
             if($('#nameForSh').val() == '') { $('#nameForSh').attr("style", "color:#a94442"); $("#nameForSh").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
             return false;
         } else {
             $('#displayShopPanel').removeClass('hide');  $('#createShopPanel').addClass('hide');
             $('#businessAreaForSh').removeAttr("style"); $('#businessAreaForSh').removeAttr("style"); 
             $('#nameForSh').removeAttr("style");
             var dataSend={
             businessPlace:$("#businessPlaceForSh").val()
			,businessArea:$("#businessAreaForSh").val()
			,name:$("#nameForSh").val()
             };
             $.ajax({
                 type: "POST",
                 url: "../findShopByBranchAttribute.json",
                 data: JSON.stringify(dataSend),
                 dataType: "json",
                 contentType: "application/json; charset=utf-8",
                 success:function(data){
                     $("#shopTable").bootstrapTable("load", data);
                 }
             });
         }
    }).on("click", "a#createShop", function() {
    	
        runningMsgHide();
        $('#searchShopPanel').addClass('hide'); $('#displayShopPanel').addClass('hide');  $('#createShopPanel').removeClass('hide');
        $('#businessPlaceForSh').removeAttr("style"); $('#businessAreaForSh').removeAttr("style"); $('#buAreaNameForSh').removeAttr("style");
        $("#id").val(''); $("#buPlace").val(''); $("#buArea").val(''); $("#name").val(''); $("#costCenter").val(''); $("#isPositive").val(''); $("#descAbvrTh").val(''); $("#description").val('');
    }).on("click", "a#backToSearch", function() {
        runningMsgHide();
        $('#searchShopPanel').removeClass('hide'); $('#displayShopPanel').addClass('hide');  $('#createShopPanel').addClass('hide');
        $('#businessPlaceForSh').removeAttr("style"); $('#businessAreaForSh').removeAttr("style"); $('#buAreaNameForSh').removeAttr("style");
    }).on("click", "a#resetSearch", function() {
        runningMsgHide();
        $('#businessPlaceForSh').removeAttr("style"); $('#businessAreaForSh').removeAttr("style"); $('#buAreaNameForSh').removeAttr("style");
        $('#businessPlaceForSh').val(""); $('#businessAreaForSh').val(""); $('#buAreaNameForSh').val("");
    }).on("click", "a#resetCreate", function() {
        runningMsgHide();
        $('#buPlace').removeAttr("style"); $('#buArea').removeAttr("style"); $('#name').removeAttr("style");
        $('#createShopFm')[0].reset();
    }).on("click", "a#saveShop", function() {
        runningMsgHide();
        //console.log("saveShop id : " + $("#id").val);
        if($('#businessPlace').val() == '' || $('#buPlaceName').val() == '' || $('#businessArea').val() == '' || $('#buAreaName').val() == '' || $('#costCenter').val() == '' || $('#costCenterName').val() == '' || $('#isPositive').val() == '') {
            if($('#businessPlace').val() == '') { $('#businessPlace').attr("style", "color:#a94442"); $("#businessPlace").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
            if($('#buPlaceName').val() == '') { $('#buPlaceName').attr("style", "color:#a94442"); $("#buPlaceName").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
            if($('#businessArea').val() == '') { $('#businessAreaode').attr("style", "color:#a94442"); $("#businessArea").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
            if($('#buAreaName').val() == '') { $('#buAreaName').attr("style", "color:#a94442"); $("#buAreaName").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
            if($('#costCenter').val() == '') { $('#costCenter').attr("style", "color:#a94442"); $("#costCenter").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
            if($('#costCenterName').val() == '') { $('#costCenterName').attr("style", "color:#a94442"); $("#costCenterName").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
            if($('#isPositive').val() == '') { $('#isPositive').attr("style", "color:#a94442"); $("#isPositive").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
            return false;
        } else {
            $('#businessPlace').removeAttr("style"); $('#buPlaceName').removeAttr("style"); $('#businessArea').removeAttr("style");
            $('#buAreaName').removeAttr("style"); $('#costCenter').removeAttr("style"); $('#costCenterName').removeAttr("style"); $('#isPositive').removeAttr("style");
           
            var dataSend={
                id:$("#id").val(),
                businessPlace:$("#businessPlace").val(),
                buPlaceName:$("#buPlaceName").val(),
                businessArea:$("#businessArea").val(),
                buAreaName:$("#buAreaName").val(),
                descAbvrTh:$("#descAbvrTh").val(),
                costCenter:$("#costCenter").val(),
                costCenterName:$("#costCenterName").val(),
                building:$("#building").val(),
                floor:$("#floor").val(),
                room:$("#room").val(),
                address:$("#address").val(),
                street:$("#street").val(),
                subdistrict:$("#subdistrict").val(),
                district:$("#district").val(),
                province:$("#province").val(),
                zipCode:$("#zipCode").val(),
                phone:$("#phone").val(),
                fax:$("#fax").val(),
                email:$("#email").val(),
                isPositive:$("#isPositive").val()
            };
            $.ajax({
                type: "POST",
                url: "../saveShopByBranchAttribute.json",
                data: JSON.stringify(dataSend),
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                success:function(data){
                	//console.log("success:function : "+data);
                	if(data != 0 ){
	                	$('#createShopFm')[0].reset();
	                    $('#alertSuccessAreaMsg').removeClass('hide');
	                    $('#alertDangerAreaMsg').addClass('hide');
	                    $('#alertDuplicateMsg').addClass('hide');
                	}else{
                		$('#alertDuplicateMsg').removeClass('hide');
                	}
                   
                },
                error: function(jqxhr) {
                	//console.log("jqxhr : "+jqxhr);
                    $('#alertDangerAreaMsg').removeClass('hide');
                    $('#alertSuccessAreaMsg').addClass('hide');
                }
            });
        }
    });
</script>
</html>
