<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>EPIS Back Office : Master Data Management UI.</title>
    <link href="../pages/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="../pages/resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css" />
    <link href="../pages/resources/custom.css" rel="stylesheet" type="text/css" />
    <script src="../pages/resources/jquery.min.js" type="text/javascript"></script>
    <script src="../pages/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="../pages/resources/bootstrap-table/src/bootstrap-table.js"></script>
    <script src="../pages/resources/custom.js" type="text/javascript"></script>
    <script src="../pages/resources/common.js" type="text/javascript"></script>
</head>
<body>
<header class="header_page"></header>
<section class="container-fluid menu">
    <div class="row">
        <div class="col-md-12 tab-modefile">
            <ol class="breadcrumb">
                <li><i>การจัดการทั่วไป</i></li>
                <li class="active"><i>Master Data</i></li>
            </ol>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 tab-modefile">
            <div id="alertSuccessAreaMsg" class="hide alert alert-success"><span class="glyphicon glyphicon-ok-sign"></span> บันทึกเรียบร้อย</div>
				<div id="alertDangerAreaMsg" class="hide alert alert-danger"><span class="glyphicon glyphicon-remove-sign"></span> เกิดข้อผิดพลาด</div>
               <div id="alertDangerValidateMsg"></div>
            <!-- Search -->
            <div id="searchPanel">
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-filter"></span> ค้นหาข้อมูล</a></li>
                </ul>
                <div class="panel panel-default panal-radius">
                    <form id="searchForm" method="post" class="form-horizontal" role="form">
                        <div class="panel-body">
                            <div class="tab-content">
                                <div role="tabpanel" class="tab-pane active" id="tab_1">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">Group Code :</label>
                                            <div class="col-sm-2">
<!--                                                 <select class="form-control" id="groupKeyCrt"></select> -->
												<select id="dropdownGroupKey" name="dropdownGroupKey" class="form-control" >
												<option value="">-</option>
												
												 </select>
												
                                            </div>
                                            <label class="control-label col-sm-2">Code :</label>
                                            <div class="col-sm-2"><input class="form-control" id="keyCrt" name="keyCrt"></div>
                                            <div class="col-sm-4"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">Value :</label>
                                            <div class="col-sm-2"><input class="form-control" id="valueCrt" name="valueCrt"></div>
                                            <div class="col-sm-4"></div>
                                            <div class="col-sm-4">
                                                <a id="searchBtn" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูล</a>&nbsp;
                                                <a id="resetSearchBtn" class="btn btn-danger"><span class="glyphicon glyphicon-minus-sign"></span> ยกเลิก</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <a id="createBtn" class="btn btn-success"><span class="glyphicon glyphicon-file"></span> เพิ่มรายการ</a></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!-- Display -->
            <div id="searchResultPanel" class="hide tab-content">
                <div role="tabpanel" class="tab-pane active" id="tab-2-1">
                    <table id="searchResultTable" 
                    data-toggle="table"
			              data-single-select="true"
			              data-classes="table table-hover table-condensed "
			              data-search="true"
			              data-show-pagination-switch="false"
			              data-show-refresh="false"
			              data-show-toggle="false"
			              data-show-columns="false"
			              data-show-export="false"
			              data-show-footer="false"
			              data-pagination="true"
			              data-page-size="50"
			              data-height="300"> 
			          <thead>
                        <tr>
                            <th data-field="id" data-formatter="runningFormatter" data-align="center">#</th>
                            <th data-field="groupKey" data-align="center">Group Name</th>
                            <th data-field="key" data-align="left">Code</th>
                            <th data-field="value" data-align="left">Value</th>
                            <th data-field="property1" data-align="center">Property 1</th>
                            <th data-field="property2" data-align="center">Property 2</th>
                            <th data-field="property3" data-align="center">Property 3</th>
                            <th data-field="property4" data-align="center">Property 4</th>
                            <th data-field="property5" data-align="center">Property 5</th>
                            <th data-field="edit" data-align="center">แก้ไข</th>
                            <th data-field="del" data-align="center">ลบ</th>
                            
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <!-- Create -->
            <div id="createPanel" class="hide">
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-list"></span> เพิ่มข้อมูล</a></li>
                </ul>
                <div class="panel panel-default panal-radius">
                    <form id="createForm" method="post" class="form-horizontal" role="form">
                        <div class="panel-body">
                            <div class="tab-content">
                                <div role="tabpanel" class="tab-pane active" id="tab_1">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">Group Code :</label>
                                            <div class="col-sm-2">

                                                <select id="dropdownAddGroupKey" name="dropdownAddGroupKey" class="form-control" >
												<option value="">-</option>
												
												 </select>
                                            </div>
                                            <label class="control-label col-sm-2">Ordering :</label>
                                            <div class="col-sm-2"><input class="form-control" id="inputAddOrdered" name="inputAddOrdered"></div>
                                            <div class="col-sm-4"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-sm-2">Key :</label>
                                            <div class="col-sm-2"><input class="form-control" id="inputAddKey" name="inputAddKey"></div>
                                            <label class="control-label col-sm-2">Value :</label>
                                            <div class="col-sm-2"><input class="form-control" id="inputAddValue" name="inputAddValue"></div>
                                            <div class="col-sm-4"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">Property 1 :</label>
                                            <div class="col-sm-2"><input class="form-control" id="inputAddProperty1" name="inputAddProperty1"></div>
                                            <label class="control-label col-sm-2">Property 2 :</label>
                                            <div class="col-sm-2"><input class="form-control" id="inputAddProperty2" name="inputAddProperty2"></div>
                                            <div class="col-sm-4"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">Property 3 :</label>
                                            <div class="col-sm-2"><input class="form-control" id="inputAddProperty3" name="inputAddProperty3"></div>
                                            <label class="control-label col-sm-2">Property 4 :</label>
                                            <div class="col-sm-2"><input class="form-control" id="inputAddProperty4" name="inputAddProperty4"></div>
                                            <div class="col-sm-4"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">Property 5 :</label>
                                            <div class="col-sm-2"><input class="form-control" id="inputAddProperty5" name="inputAddProperty5"></div>
                                            <div class="col-sm-8">
                                                <a id="saveBtn" class="btn btn-primary"><span class="glyphicon glyphicon-plus-sign"></span> บันทึกข้อมูล</a>&nbsp;
                                                <a id="resetCreateBtn" class="btn btn-danger"><span class="glyphicon glyphicon-minus-sign"></span> ยกเลิก</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                
                                            </div>
                                            <input type="hidden" id="inputAddId" name="inputAddId" value=""/>
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
    var self = this, defaultErrorMessage = "An error occurred but there is no message response.";
    self.dropdownGroupKeyValue = {};
    self.session = function(key, val) { if (!val) { var val = window.sessionStorage.getItem(key); return val && (val.indexOf("{") > -1 || val.indexOf("[") > -1) ? JSON.parse(val) : val } window.sessionStorage.setItem(key, ($.type(val) != "object" && $.type(val) != "array" ? val : JSON.stringify(val)))  };
    self.sessionUnset = function(key) { window.sessionStorage.removeItem(key); };//add by kao
    self.utils = {
        guid: function(){ function r() { return Math.floor((Math.random() + 1) * 0x10000).toString(16).substring(1) } return r() + r() +"-"+ r() +"-"+ r() +"-"+ r() +"-"+ r() + r() + r() },
        numberFormat: function(num, dec) { return $.type(num) !== "number" ? "0.00" : num.toFixed(dec || 2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); },
        dateFormat: function() { var obj = arguments[0]; if ($.type(obj) == "string" && /[0-9]{2}.[0-9]{2}.[0-9]{4}/g) { return obj } else if ($.isNumeric(obj)) { var dt = new Date(obj), dd = dt.getDate().toString(), mm = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(); return (dd[1]?"":"0") + dd +"/"+ (mm[1]?"":"0") + mm +"/"+ yyyy } else if ($.type == "date") { return "" } return "" },
        dateTimeFormat: function() { var obj = arguments[0], dt = $.type(obj) != "date" ? ($.isNumeric(obj) ? new Date(obj) : new Date()) : new Date(), dd = dt.getDate().toString(), MM = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(), hh = dt.getHours().toString(), mm = dt.getMinutes().toString(), ss = dt.getSeconds().toString(); return (dd[1]?"":"0") + dd +"/"+ (MM[1]?"":"0") + MM +"/"+ yyyy +" "+ (hh[1]?"":"0") + hh +":"+ (mm[1]?"":"0") + mm +":"+ (ss[1]?"":"0") + ss },
        queryString: function() { var pos = location.href.indexOf("?"), obj = {}; if (pos < 0) return obj; var params = location.href.slice(pos + 1).split("&"); for (var i = 0, m = params.length; i < m; i++) { pos = params[i].indexOf("="); if (pos < 0) { obj[params[i]] = true; continue; } obj[params[i].substring(0, pos)] = params[i].substring(pos + 1) } return obj },
        window: function(windowName, url, side) { window.windowOpened = (window.windowOpened || {}); var screenProp = ""; if (side == "left") { screenProp = "left=0,top=0,width="+ (screen.width/2) +",height="+ (screen.height-100) } else if (side == "right") { screenProp = "left="+ (screen.width/2-40) +",top=0,width="+ (screen.width/2+40) +",height="+ (screen.height-100) } window.windowOpened[windowName] = window.open(url, windowName, "menubar=no,scrollbars=no,resizable=0,"+ screenProp, false); return window.windowOpened }
    };
    //// AUTOMATIC REGISTER FORMATTER FUNCTION ////
    window.get = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "GET", "data": params, "error": (msgDialog || { "valid": function(jqXHR, textStatus, errorThrow){ console.log(jqXHR); console.log("textStatus: "+ textStatus); console.log("errorThrow:"); console.log(errorThrow) } }).valid, "success": function(res){ var isNotJson = res.constructor == String; console.log(res); (preCheck || function(o){})(res); if (isNotJson) res = { "statusCode": "99", "errorList": ["No matching data found."] }; if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
    window.post = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "POST", "data": params, "error": (msgDialog || { "valid": function(jqXHR, textStatus, errorThrow){ console.log(jqXHR); console.log("textStatus: "+ textStatus); console.log("errorThrow:"); console.log(errorThrow) } }).valid, "success": function(res){ var isNotJson = res.constructor == String; console.log(res); (preCheck || function(o){})(res); if (isNotJson) res = { "statusCode": "99", "errorList": ["No matching data found."] }; if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
    window.add = function(num1, num2, dec) { if (!dec) dec = 2; return parseFloat((num1 + num2).toFixed(dec), 10); }; window.subtract = function(num1, num2, dec){ if (!dec) dec = 2; return parseFloat((num1 - num2).toFixed(dec), 10); }; window.multiply = function(num1, num2, dec){ if (!dec) dec = 2; return parseFloat((num1 * num2).toFixed(dec), 10); }; window.divide = function(num1, num2, dec){ if (!dec) dec = 2; return parseFloat((num1 / num2).toFixed(dec), 10); }
    window.find = function(array, propName, obj){ var tmp; for (var i = 0, m = array.length; i < m; i++) { if (array[i][propName] == obj[propName]) tmp = array[i]; } if (tmp == null) { array.push(tmp = obj); } return tmp; }
    window.toMap = function(array, propNames){ var map = {}, prop; function key(obj){ var p = []; for (var i = 0, m = propNames.length; i < m; i++) { p.push(obj[propNames[i]]) } return p.join() }; for (var i = 0, m = array.length; i < m; i++) { prop = key(array[i]); map[prop] = array[i] } return map }
    window.stripToNumber = function(str) { return $.type(str) === "string" && $.isNumeric(str = str.replace(/[^0-9\.]/g, "")) ? parseFloat(str, 10) : 0 }
    window.toDateString = function(ddMMyyyy){ return ddMMyyyy.replace(/(\d{2}).(\d{2}).(\d{4})/g, "$2/$1/$3") }
    window.runningFormatter = function(val, row, ind) { return ind + 1 }
    window.numberFormatter = function(val, row, ind) { return !$.isNumeric(stripToNumber(val)) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') }
    window.numberFormatterForVat = function(val, row, ind) {
        if(val == '0'){
            return "0.00";
        }else if(val == null){
            return "-";
        }
        else{
            return !$.isNumeric(stripToNumber(val)) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,')
        }
    }
    window.stringInputFormatter = function(val, row, ind) { var id = row.id; console.log(id); return '<input value="'+ $.trim(val) +'" maxLength="100" class="form-control" onkeyup="updateTableBillingList('+id+', this.value)">' }
    window.numberInputFormatter = function(val, row, ind) { return '<input value="'+ self.utils.numberFormat(parseFloat(val || "0.00", 10)) +'" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">' }
    window.moneyInputFormatter = function(val, row, ind) { var value = parseFloat(val || "0.00", 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,"); return '<input value="'+ value +'" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onkeyup="if (event.which == 13) this.blur()" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\'); o.select() })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); o.style.backgroundColor = o.value !== \''+ value +'\' ? \'yellow\' : \'\'; var table, elem = o; while (table == null) { elem = elem.parentNode; if (elem.tagName == \'TABLE\') table = elem; } (window[table.id +\'InputBlurEvent\'] || function(t){ console.log(t) })(table) })(this)">' }
    window.modifyButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link modList" onclick=\'(function(elem, row, ind){ var table = $("#"+ $(elem).closest("table")[0].id); window[table[0].id +"ModifyEvent"](table, row, ind) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-pencil"></span></a>' }
    window.removeButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link delList" onclick=\'(function(elem, row, ind){ $("#"+ $(elem).closest("table")[0].id).bootstrapTable("remove", { field: "index", values: [ind] }) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-trash"></span></a>' }
    window.LoadingPanel = function(id) { var obj = this, loadCnt = 0, setupFunc, loadFunc; function checkElemReady(){ if ((obj.elem = $("#"+ id).css("font", "")).length != 1) { alert("The element hasn't insert into HTML DOM."); throw "The element hasn't insert into HTML DOM."; } clearTimeout(setupFunc); clearInterval(loadFunc) }; return { "elem": null, "finish": function(html){ checkElemReady(); (this.elem ? this.elem : this.elem = $("#"+ id)).css("font", "").html(html); return this }, "toString": function(){ var elem; setupFunc = setTimeout(function(){ loadFunc = setInterval(function(){ (elem || (elem = document.getElementById(id))).innerHTML = "Loading"+ Array(++loadCnt).join(".."); if (loadCnt > 60) loadCnt = 2 }, 250) }, 1000); return "<div id='"+ id +"' style='font:BOLD 16pt Arial'></div>" } } }
    window.operateButtonFormatter = function(val, row, ind) {
        return "<a href='#' onclick='removeFromServiceList("+row.id+")'><span class='glyphicon glyphicon-trash' aria-hidden='true'></span></a>"
    }
    function AuthenticationDialog(succFunc, failFunc, url, title, usrLabel, pwdLabel, okBtn, cnBtn) { var obj = this, httpUrl = url, done = succFunc, fail = failFunc, cancel = function(){}, inputs; obj.el = "modal_authentication";
        var content = '<div class="row"><div class="col-md-12"><div class="col-md-offset-2 col-md-10"><div class="form-horizontal"><div class="form-group"><label class="col-sm-3 control-label">'+ (usrLabel || "User Name :") +'</label><div class="col-sm-6"><input type="text" class="form-control"></div></div><div class="form-group"><label class="col-sm-3 control-label">'+ (pwdLabel || "Password :") +'</label><div class="col-sm-6"><input type="password" class="form-control"></div></div></div></div></div></div>';
        var hdrCS = '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
        var hdrTT = '<h4 class="modal-title"><span class="glyphicon glyphicon-user"></span> '+ (title || "Authentication") +'</h4>';
        var divMG = '<div class="alert alert-danger hide"></div>';
        var btnOK = '<button type="button" class="btn btn-success"><span class="glyphicon glyphicon-ok-circle"></span> '+ (okBtn || "OK") +'</button>';
        var btnCN = '<button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> '+ (cnBtn || "Cancel") +'</button>';
        obj.elem = $(document.body).append('<div class="modal fade" role="dialog" data-backdrop="static" id="'+ obj.el +'"><div class="modal-dialog"><div class="modal-content"><div class="modal-header">'+ hdrCS + hdrTT +'</div><div class="modal-body">'+ divMG + content +'</div><div class="modal-footer">'+ btnOK + btnCN +'</div></div></div></div>').find("#"+ obj.el); inputs = obj.elem.find("input").keyup(function(e){ var key = (e.which || e.keyCode || e.charCode || 0); if (key != 13) return true; obj.elem.find("input, button").eq(e.target.type == "text" ? 2 : 3).focus(); return true }); buttons = obj.elem.find("button"); 
        buttons.eq(1).on({ click: function(){ $.post(httpUrl, { username: inputs.eq(0).val(), password: inputs.eq(1).val() }, function(res){ if (!res || res.statusCode != "0") { obj.elem.find(".alert").text("You don't have permission to access this feature. Please contact to Administration for more information.").removeClass("hide"); fail(res); return; } done(res); obj.hide() }) } }).end().filter(function(index){ return index == 0 || index == 2 }).on({ click: function(){ cancel() } })
        obj.url = function(url){ httpUrl = url; return this }; obj.show = function(){ obj.elem.modal("show"); obj.elem.find("div.alert").addClass("hide").text(""); inputs.val(""); return this }; obj.hide = function(){ obj.elem.modal("hide"); return this }
        obj.done = function(func) { done = func; return this }; obj.fail = function(func) { fail = func; return this }; obj.cancel = function(func) { cancel = func; return this }
    }
    function Button(el, url, inp, validate) { var obj = this, inputs = inp, val = (validate || function(){ return true }), badge, done = function(res) { console.log(res) }; obj.el = el; obj.elem = $(el);
        obj.inputs = function(object) { inputs = object; return this };
        obj.click = function(func) { this.elem.click(func) };
        obj.hide = function() { this.elem.addClass("hide"); return this }; obj.show = function() { this.elem.removeClass("hide"); return this };
        obj.hideLoad = function(){ obj.elem.button("reset"); return this }; obj.showLoad = function(){ obj.elem.button("loading"); return this };
       // obj.disable = function(flag) { if (flag == null || flag) this.elem.addClass("disabled"); else this.elem.removeClass("disabled"); return this };
        obj.disable = function(flag) { if (flag == null || flag) this.elem.addClass("disabled").attr("disabled", true); else this.elem.removeClass("disabled").attr("disabled", false); return this }; obj.enable = function() { obj.disable(false); return this };
        obj.enable = function() { obj.elem.removeClass("disabled").attr("disabled", false); return this };
        obj.validate = function(func) { val = func; return obj }
        obj.done = function(func) { done = func };
        obj.badge = function(val) { if (badge) badge.text(val) };
        function eventHandler (e) {
            if (!val(inputs)) return;
            if (!url) return; obj.elem.button("loading");
            var params = {}; $.each(inputs, function(k,v){
                if ($.type(v) === "object" && v.val) params[k] = v.val();
                else if ($.type(v) === "array")      params[k] = v.join("|");
                else if ($.type(v) === "function")   params[k] = v();
                else                                 params[k] = v;
            }); $.get(url, params, function(res){ obj.elem.button("reset"); done(res) });
        }
        if ((badge = this.elem.next()).length == 0) badge = null;
        obj.elem.click(eventHandler).click(window[el.substring(1) +"ClickEvent"]);
    }
    function DropDown(el, url) { var obj = this, data = [{ key: "", value: "", text: "Please Select" }],principalId = 0 ; obj.el = el; obj.elem = $(el);
        obj.setPrincipalId = function(principalId){ this.principalId =  principalId; };
    	obj.data = function(array) { if ($.type(array) == "array") { setup(data = array); return this } return data; };
        obj.init = function(url, param) {
            if (url) $.get(url, function(res)
            {if(param == "serviceType") console.log(res); setup(data = res.data, param);}); else setup(data, param); return this };
        obj.disable = function(flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this }
        obj.enable = function() { obj.elem.attr("disabled", false); return this }
        obj.index = function() { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function(i, opt){ opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function(i, opt){ return opt.selected ? "selected" : "no-selected" })) }
        obj.selected = function(){ return data[obj.index()]; }; 
        obj.val = function() { return obj.elem.val(); };
        
      //start add by kao 25600712
        obj.setSelected = function(index){ if(obj.length() > 0) obj.elem[0].selectedIndex = index  };
        obj.setSelectedByValue = function(){
        	if (arguments.length == 1) {
        		for(i=0; i<obj.length();i++){
          	      if(obj.elem[0].options[i].value == arguments[0])
          	            view.dropdownAddGroupKey.setSelected(i);
          		}	
        	}
        	
        };
        obj.length = function(){ return obj.elem[0].length; };
        obj.addByRow = function(key,val){ var option = document.createElement("option"); option.text = val;option.value = key; obj.elem[0].add(option);};
    	obj.clear = function(){  var lengthData = obj.elem[0].length; for(var i = 0;i<lengthData ;i++){ obj.removeByRow(0); } };
    	obj.removeByRow = function(index){ obj.elem[0].remove(index); };//add by kao 25600712
    	obj.json = function(){ var jsonArr = []; var localPrincipal = this.principalId; $.each( obj.elem[0].options, function( key, value ) { var jsonData ={};jsonData['principalId'] = localPrincipal; jsonData['id'] = value.value; jsonData['name'] = value.text;  jsonArr.push(jsonData);}); return jsonArr; };
    	obj.setupByObj = function(objData) {$.each(objData,function(key,value){obj.addByRow(key,value);});};
      //end add by kao 25600712
        obj.key = function(){ if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this };
       
	 
      function setup(array, param) {
            obj.elem.find("*").remove();
            $.each(array,function(i,o){
                if(o.category == 'payothers.service.unit'){
                    obj.elem.append('<option data-index="-1" data-key="-1" value="">ไม่เลือก</option>');
                    return false;
                }
                else{
                    obj.elem.append('<option data-index="-1" data-key="-1" value="-1">ทั้งหมด</option>');
                    return false;
                }
            });
            $.each(array,function(i,o){
                if(param == "serviceType"){
                    obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.revenueType +'" value="'+ o.description +'">'+ o.revenueType +' - '+ o.description +'</option>')
                }else if(param == "serviceName"){
                    obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.serviceCode +'" value="'+ o.serviceName +'">'+ o.serviceName +'</option>')
                }else if(o.category == 'payothers.service.category' || o.category == 'payothers.service.name' || o.category == 'payothers.service.unit') {
                    obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.code +'" value="'+ o.descFullTh +'">'+ o.descFullTh +'</option>')
                } else if(o.category == 'branch.central'|| o.category == 'business.place') {
                    obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.mapCode1 +'" value="'+ o.descFullTh +'">'+ o.descFullTh +'</option>')
                } else{
                    obj.elem.append('<option data-index="'+ i +'" data-key="'+ (o.key||o.id) +'" value="'+ (o.key||o.id) +'">'+ (o.text || o.value) +'</option>')
                }
            });
        }
        data = obj.elem.change(window[el.substring(1) +"ChangeEvent"]).find("option").map(function(i, opt){ return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
    }
    function CheckBox(el) { var obj = this; obj.el = el; obj.elem = $(el); obj.elem.click(window[obj.elem.attr("name") +"ClickEvent"])
        obj.disable = function() { obj.elem.attr("disabled", arguments.length == 0 || (arguments.length == 1 && (arguments[0] == "true" || arguments[0] == true))); return this; }
        obj.contains = function(val) { return $.inArray(val, obj.val()) > -1 };
        obj.val = function() { return obj.elem.filter(":checked").map(function(i,o){ return o.value }) };
        obj.unchecked = function(){ obj.elem.each(function(i,o){ o.checked = false }); return this; }
    }
    function Input(el, maxLen, propPos) { var obj = this; obj.el = el; obj.elem = $(el);
        obj.error = function(flag) { if (arguments.length == 0 || flag) obj.elem.parent().addClass("has-error"); else obj.elem.parent().removeClass("has-error"); return this; }
        obj.clear = function() { obj.val(""); return this }; obj.isEmpty = function() { return $.trim(obj.val()) === "" }; obj.isNumeric = function() { return $.isNumeric(obj.val()); }
        obj.nextFocus = function(nextElem) { if (nextElem && "Input|Button".indexOf(nextElem.constructor.name) > -1) { obj.elem.keyup(function(e){ var key = (e.which || e.keyCode || e.charCode || 0); if (key == 13) nextElem.elem.focus(); return true; }) } return this; };
        obj.click = function(func) { obj.elem.click(func); return this; }
        obj.readOnly = function(flag) { obj.elem.attr("readonly", (flag == null ? true : flag)); return this; }
        obj.disable = function(flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this; }
        obj.enable = function() { obj.disable(false); return this; }
        obj.val = function() { if (arguments.length == 1) { this.elem.val(arguments[0]); } return $.trim(this.elem.val()); }
        obj.get = function(propName) { if ($.type(propPos[propName]) !== "array" || propPos[propName].length !== 2) return ""; return obj.val().substring(propPos[propName][0], propPos[propName][1]); }
        obj.elem.blur(window[el.substring(1) +"BlurEvent"]); obj.elem.keyup(function(e){ var func = (window[el.substring(1) +"KeyUpEvent"] || function(){}); func((e.which || e.keyCode || e.charCode || 0), obj.elem) }); obj.elem.focus(function(){ this.select() }); if ($.isNumeric(maxLen)) { obj.elem.attr("maxLength", maxLen) }
    }
    function NumberInput(el, dec) { var obj = this, decimal = (dec == null ? 2 : dec); obj.el = el; obj.elem = $(el).addClass("text-right");
        obj.val = function() { if (arguments.length == 0) return parseFloat(strip(this.elem.val() || "0"), 10); this.elem.val(format(arguments[0])); return this; }
        obj.decimal = function(dec) { decimal = dec }; obj.format = format;
        obj.disable = function(){ obj.elem.attr("disabled", (arguments.length != 1 ? true : arguments[0])); return obj }; obj.enable = function(){ obj.disable(false); return obj }; obj.readOnly = function(flag) { obj.elem.attr("readonly", (flag == null ? true : flag)); return this; }
        obj.elem.keypress(function(e) { var key = (e.which || e.keyCode || e.charCode || 0); var ch = String.fromCharCode(key); return "0123456789.".indexOf(ch) > -1 });
        obj.elem.focus(function(){ this.value = strip(this.value); this.select() });
        obj.elem.blur(function(){ this.value = format(this.value) });
        function format(val) { var str = ($.isNumeric(val) ? parseFloat(val, 10) : obj.val()).toFixed(decimal == 0 ? 1 : decimal).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); return decimal == 0 ? str.substring(0, str.lastIndexOf(".")) : str };
        function strip(str) { return (str || "").replace(/[,]/g, "") }
        obj.elem.blur(window[el.substring(1) +"BlurEvent"]); obj.elem.keyup(function(e){ var key = (e.which || e.keyCode || e.charCode || 0), func = (window[el.substring(1) +"KeyUpEvent"] || function(){}); func(key, obj.elem); if (key == 13) this.blur() }); obj.elem.focus(function(){ this.select() })
        if (obj.elem.val() == "") obj.elem.val("0" + (decimal < 1 ? "" : "."+ Array(decimal + 1).join("0")));
    }
    function Message(el) { var obj = this, timeCnt = 0, loadFunc; obj.el = el; obj.elem = $(el);
        obj.hide = function() { obj.elem.addClass("hide"); return obj }; obj.show = function(flag) { if (flag == null || flag) obj.elem.removeClass("hide"); else obj.hide(); return obj };
        obj.clear = function() { obj.elem.find("*").remove(); obj.hide(); return obj };
        obj.message = function(arr, cls) { $.each(arr, function(i,o) { var m = o; if ($.type(o) === "object") { m = (o.desc || o.messageDesc) +" [code="+ (o.code || o.messageCode) +"]" }; obj.elem.append('<div class="'+ cls +'">'+ m +'<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button></div>') }); window.scrollTo(0, 0); return obj };
        obj.error = function(arr) { return obj.message(arr, "alert alert-danger") };
        obj.warn = function(arr) { return obj.message(arr, "alert alert-warning") };
        obj.success = function(arr) { return obj.message(arr, "alert alert-success") };
        obj.valid = function(jqXHR, textStatus, errorThrow){ var res = jqXHR; obj.stopLoad(); if (jqXHR && jqXHR.responseJSON) res = jqXHR.responseJSON; if (res) { var isNoData = false; if (res.statusCode && res.statusCode != "0") { obj.warn(res && $.type(res.warningList) === 'array' ? res.warningList : []).error(res && $.type(res.errorList) === 'array' ? res.errorList : ["An error occurred on the server side but there is no error message returned."]).show(); return false } if (res.data && res.statusCode == '0' && res.data.length < 1) isNoData = true; if ($.type(res._embedded) === 'object' && $.map(res._embedded,function(v,k){return v}).length < 1) isNoData = true; if (isNoData) { return false; } return true; } obj.error(["An error occurred on the server side but there is no error message returned."]).show(); return false; }
        obj.hideLoad = function(){ obj.stopLoad().clear(); return this; }; obj.stopLoad = function(){ clearInterval(loadFunc); return this; }
        obj.showLoad = function(msg){ obj.elem.append('<div id="'+ obj.el +'-loading" class="alert alert-warning">'+ bind(msg, 0) +'</div>'); timeCnt = 0; var elem = document.getElementById(obj.el +"-loading"); loadFunc = setInterval(function(){ elem.innerHTML = bind(msg, ++timeCnt) }, 1000); obj.show(); return this; }
        function bind(msg, timeCnt) { return msg.replace(/\{timeCnt\}/g, timeCnt); }
    }
    function Panel(el, hide) { var obj = this, dura = 500, counter = 0, cntFunc; obj.el = el; obj.elem = $(el); if ($.inArray(obj.elem[0].tagName.toLowerCase(), ["ol", "ul"]) > -1) { obj.elem.removeAttr("id").css("display", "").removeClass("hide").removeClass("hidden"); obj.elem = obj.elem.wrap('<div id="'+ el.substring(1) +'"></div>').parent(); } var children = obj.elem.children(), bodyEl = el.substring(1) +'-body', body = obj.elem.append('<div style="'+ (hide == null || hide ? "display: none" : "") +'" id="'+ bodyEl +'"></div>').find("#"+ bodyEl).append(children), progressType, progress = obj.elem.append('<div class="progress" style="display: none"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 0%"><span class="sr-only">0% Complete</span></div></div>').find(".progress");
        obj.center = function(){ obj.elem.removeClass(["text-left", "text-right"]).addClass("text-center"); return this; }; obj.right = function(){ obj.elem.removeClass(["text-left", "text-center"]).addClass("text-right"); return this; }
        obj.hide = function(ms) { if (isHidden()) return this; body.hide(ms || dura); return this; }; obj.show = function(ms) { if (!isHidden()) return this; if (!ms || !$.isNumeric(ms)) ms = dura; if (ms >= 0) body.css("display", "none").removeClass("hide").removeClass("hidden").show(ms); else obj.hide(Math.abs(ms)); return this; }
        obj.slideDown = function(ms){ if (isHidden()) { body.css("display", "none").removeClass("hide").removeClass("hidden").slideDown(ms || dura, arguments[1] || function(){}) } return this; }; obj.slideUp = function(ms){ if (!isHidden()) { body.slideUp(ms || dura, arguments[1] || function(){ }); } return this; }
        obj.progress = function(){ return { "elem": progress, "show": function(ms){ body.slideUp(ms || 10); progress.slideDown(); return this }, "hide": function(ms){ body.slideDown(ms || 10); progress.slideUp(); return this; }
            ,"success": function(){ return this.style("progress-bar-success") }, "info": function(){ return this.style("progress-bar-info") }, "warn": function(){ return this.style("progress-bar-warning") }, "error": function(){ return this.style("progress-bar-danger") }, "plain": function(){ return obj.style("") }, "style": function(clsName) { $(this.elem[0].childNodes[0]).removeClass(progressType).addClass(progressType = clsName); return this; }
            ,"percent": function(perc){ if (!$.isNumeric(perc) || (perc > 100 || perc < 0)) return this; this.elem[0].childNodes[0].style.width = perc +"%"; return this; }
            ,"timeCnt": function(seconds) { var o = this; this.percent(counter = 0); cntFunc = setInterval(function(){ o.percent(100/seconds * ++counter); if (seconds < counter) { clearInterval(cntFunc); o.hide(1000) } }, 1000); return this; }
        } }; function isHidden() { return body.css("display") === "none" || body.hasClass("hide") || body.hasClass("hidden") }; obj.elem.css("display", "").removeClass("hide").removeClass("hidden");
    }
    function BootstrapTable(el) { var obj = this, checkEvt = function(e){ console.log(e) }, uncheckEvt = checkEvt; obj.el = el; obj.elem = $(el);
	    obj.clear = function() { obj.elem.bootstrapTable("removeAll"); return obj; }
	    obj.showLoad = function() { obj.elem.bootstrapTable("showLoading"); return this }; obj.hideLoad = function() { obj.elem.bootstrapTable("hideLoading"); return this; };
	    obj.insert = function(row) { var data = obj.elem.bootstrapTable("getData"); obj.elem.bootstrapTable("insertRow", { "index": data.length, "row": row }); return this; }
	    //start add by kao 25600724 0953
	  
	    
	    //obj.rawData = function() { if (arguments.length == 1) { this.elem.bootstrapTable("load", rawData = arguments[0]); return this } return $.map(this.elem.bootstrapTable("getData"), function(row){ return row }) }; 
	    obj.getSelections = function(){ return obj.elem.bootstrapTable("getAllSelections") }
	    obj.rawData = function() { if (arguments.length == 1) { this.elem.bootstrapTable("load", rawData = arguments[0]); return this } return $.map(this.elem.bootstrapTable("getData"), function(row){ return row }) };
	    
	    
	    
	    obj.addRow = function() {
	        if (arguments.length == 1) {
	            for (var i = 0; i < arguments[0].length; i++) {
	                var dataObj = arguments[0][i];
	                obj.elem.bootstrapTable('insertRow', {
	                    index: i,
	                    row: {
	                    	id: (i+1),
                            groupKey : dataObj.groupKey,
                            key :      dataObj.key,
                            value :    dataObj.value,
                            property1 : dataObj.property1 == "null" ? "-" : dataObj.property1,
                            property2 : dataObj.property2 == "null" ? "-" : dataObj.property2,
                            property3 : dataObj.property3 == "null" ? "-" : dataObj.property3,
                            property4 : dataObj.property4 == "null" ? "-" : dataObj.property4,
                            property5 : dataObj.property5 == "null" ? "-" : dataObj.property5,
							edit: '<button type="button" class="btn-sm btn-success" onclick="editData(\'' + dataObj.id + '||' + dataObj.groupKey + '||' + dataObj.key + '||' + dataObj.value + '||' + dataObj.ordered + '||' + dataObj.property1 + '||' + dataObj.property2+ '||' + dataObj.property3+ '||' + dataObj.property4+ '||' + dataObj.property5 + '\');" > แก้ไข </button>',
                            //edit: '<button type="button" class="btn-sm btn-success" onclick="redirectPage(1,\'' + dataObj.id + '||' + dataObj.name + '||' + dataObj.desc + '||' + dataObj.parrentId + '||' + (dataObj.url == null ? '' : dataObj.url) + '||' + dataObj.ordering + '\');" > แก้ไข </button>',
                            del : '<button type="button" class="btn-sm btn-danger" onclick="deleteData(' + (i+1) + ','+dataObj.id+');" > ลบ </button>'  
                            
	                    }
	                });
	            }
	        }
	    },
	    
	    obj.removeAll = function(){ obj.elem.bootstrapTable('removeAll'); }
	  	//end add by kao 25600724 0953
	    obj.data = function() { if (arguments.length == 1) { obj.elem.bootstrapTable("load", arguments[0]); } return obj.elem.bootstrapTable("getData") };
	    obj.selected = function() { return obj.elem.bootstrapTable("getSelections") };
	    obj.sum = function(isAll, colName) { var sum = 0; $.each(obj.elem.bootstrapTable(isAll ? "getData" : "getSelections"), function(i,o){ sum += (o[colName] || 0) }); return sum };
	    obj.sumInput = function(index) { var sum = 0; obj.elem.find("tbody tr").each(function(i,o){ var val = o.children[index].children[0].value.replace(/[,]/g, ""); sum += (!$.isNumeric(val) ? 0 : parseFloat(val, 10)) }); return sum; }
	    obj.expand = function() { obj.elem.find(".detail-icon i.icon-plus").click(); return this };
	    obj.collapse = function() { obj.elem.find(".detail-icon i.icon-minus").click(); return this };
	    obj.checkboxEvent = function(func) { obj.elem.on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table", func); return this };
	    obj.checkEvent = function(func) { checkEvt = func; return this };
	    obj.uncheckEvent = function(func) { uncheckEvt = func; return this };
	    obj.checkboxEvent(function(e) { if (e.type === "check") checkEvt(e); if (e.type === "uncheck") uncheckEvt(e) });
	    obj.remove = function(ind){ obj.elem.bootstrapTable('remove', { field  : 'id', values : [ind] }); return this; }
	}
    function Modal(el) {
        this.el = el;
        this.elem = $(el);
        this.hide = function() { this.elem.modal("hide") };
        this.show = function() { this.elem.modal("show") };
    }
    function Tab(el) {
        var obj = this, index = 0, change = function(e){ console.log(e) }, initEvts = [];
        obj.el = el;
        obj.elem = $(el);
        obj.index = function() { if (arguments.length == 1) { index = arguments[0]; obj.elem.find("li").removeClass("active").find("a").eq(index).click(); return this; } return index; }
        obj.show = function(ind) { obj.elem.find("a").eq(ind).tab("show"); return this; }
        obj.change = function(func) { change = func; return this; }
        obj.init = function(ind, evt) { initEvts[ind] = evt; return obj; }
        obj.elem.find("a").each(function(i,o){ $(o).data("index", i) });
        obj.elem.find("li").each(function(i,o){ if ($(o).hasClass("active")) index = i; initEvts.push(function(){ console.log("index: "+ i) }) });
        obj.elem.on("show.bs.tab", function(e){ index = $(e.target).data("index"); change(e); initEvts[index]() });
    }
    (function(){ $(window["setup"]) })();
    // TO SUPPORT ECLIPSE OUTLINE.
 
	 //view.dto = parent.getMasterDataWithKey('000002');
   // alert(dto);
    self.searchPanel = new Panel("#searchPanel").show();
    self.searchResultPanel = new Panel("#searchResultPanel").hide();
    self.createPanel = new Panel("#createPanel").hide();

    // Search Criteria
//     self.groupKeyCrt = new Listbox("#groupKeyCrt").init("../MasterData/getGroupData?key=PAYTYPE");
	//self.groupKeyCrt = new Input("#groupKeyCrt");
	self.dropdownGroupKey = new DropDown("#dropdownGroupKey");
    self.keyCrt = new Input("#keyCrt");
    self.valueCrt = new Input("#valueCrt");
    self.searchCrtObjNameArr = ['dropdownGroupKey', 'keyCrt', 'valueCrt'];

    // Create Param
    self.inputAddId = new Input("#inputAddId");
    self.dropdownAddGroupKey = new DropDown("#dropdownAddGroupKey");
    self.inputAddOrdered = new Input("#inputAddOrdered");
    self.inputAddKey = new Input("#inputAddKey");
    self.inputAddValue = new Input("#inputAddValue");
    self.inputAddProperty1 = new Input("#inputAddProperty1");
    self.inputAddProperty2 = new Input("#inputAddProperty2");
    self.inputAddProperty3 = new Input("#inputAddProperty3");
    self.inputAddProperty4 = new Input("#inputAddProperty4");
    self.inputAddProperty5 = new Input("#inputAddProperty5");
    
    
    
    
    

    self.searchResultTable = new BootstrapTable("#searchResultTable");

    self.searchBtn = new Button("#searchBtn");
    self.resetSearchBtn = new Button("#resetSearchBtn");
    self.createBtn = new Button("#createBtn");
    self.saveBtn = new Button("#saveBtn");
    self.resetCreateBtn = new Button("#resetCreateBtn");
    self.alertDangerValidateMsg = new Message("#alertDangerValidateMsg");

    return this;
})(jQuery);
   

    var searchCriteriaId = [];

    $(function() {
    	
    	view.sessionUnset('epis-master-dataByRole');
    
		initData();
			 
	});
    function initData(){
    	
    	setupDropdownMainmenu();
    	
    }
    function setupDropdownMainmenu(){
		 $.ajax({
            type: "POST",
          //  async: false, 
            url: "../MasterData/searchGroupKeyByRole.json",
            success:function(resData){
           	 if(resData.statusCode==0) {
           		var dropdownData = {};
           		
    				$.each(resData.data,function(index,data){ dropdownData[data.key] = data.value; });
    				view.dropdownGroupKey.setupByObj(dropdownData);
    				view.dropdownAddGroupKey.setupByObj(dropdownData);
           	 }
                else{ 
           		 $('#alertSuccessAreaMsg').addClass('hide');
    			$('#alertDangerAreaMsg').removeClass('hide');
           	}
    			            	 
            },
            error: function(jqxhr) {
        		
           	$('#alertSuccessAreaMsg').addClass('hide');
				$('#alertDangerAreaMsg').removeClass('hide');
            }
        });
	}
   

    
    function searchBtnClickEvent(){	
        //if(isValidSearchCriteria()){
        $('#alertDangerAreaMsg').addClass('hide');
        $('#alertSuccessAreaMsg').addClass('hide');
        view.searchResultTable.removeAll();
		view.searchResultTable.showLoad();
            view.searchResultPanel.show();
            var dataSend =
            {
                "groupKey" : view.dropdownGroupKey.val()
                ,"key" : view.keyCrt.val()
                ,"value" : view.valueCrt.val()
            };

            $.ajax({
                type: "POST",
                url: "../MasterData/searchByGroupKeyAndValueByKey.json",
                data: JSON.stringify(dataSend),
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                success:function(data){
                	
                	view.searchResultTable.hideLoad();
                	if(data.statusCode==0){
                		view.searchResultTable.addRow(data.data);
                	}
                    	
// 					$('#alertSuccessAreaMsg').removeClass('hide');
// 					$('#alertDangerAreaMsg').addClass('hide');
                },
                error: function(jqxhr) {
 	        		$('#alertDangerAreaMsg').removeClass('hide');
 	        		$('#alertSuccessAreaMsg').addClass('hide');
                }
            });
        //}

    }

    function hideAllPanel(){
    	view.searchPanel.hide();
        view.searchResultPanel.hide();
        view.createPanel.hide();
    }
    function resetSearchBtnOnClick(){
        $('#searchForm')[0].reset();

// 	groupKeyCrt.error(false);
// 	keyCrt.error(false);
// 	valueCrt.error(false);
        clearErrorStyle(view.searchCrtObjNameArr);
    }

    function clearErrorStyle(objNameArray){
        $.each(objNameArray, function(index, objName) {
            eval(objName).error(false);
        });
    }

    function isValidSearchCriteria(){

        if(view.groupKeyCrt.error() && view.keyCrt.error() && view.valueCrt.error()){
            return false;
        }else{
            clearErrorStyle(view.searchCrtObjNameArr);
            return true;
        }
    }
    function deleteData(){
    	
    	if(arguments.length!=2){
    		
    		$('#alertDangerAreaMsg').removeClass('hide');
    		$('#alertSuccessAreaMsg').addClass('hide');
    		return false;
    	}
    	
    	var indexTable = arguments[0];
    	 var dataSend =
         {
       		"id" : arguments[1]
         };
         $.ajax({
             type: "POST",
             url: "../MasterData/deleteById",
             data: JSON.stringify(dataSend),
             dataType: "json",
             contentType: "application/json; charset=utf-8",
             success:function(data){
            	 
             	if(data.statusCode ==1){
             		view.searchResultTable.remove(parseInt(indexTable));
					$('#alertSuccessAreaMsg').removeClass('hide');
					$('#alertDangerAreaMsg').addClass('hide');
             	}else{
             		$('#alertDangerAreaMsg').removeClass('hide');
	        		$('#alertSuccessAreaMsg').addClass('hide');
             	}
            	 
             },
             error: function(jqxhr) {
	        		$('#alertDangerAreaMsg').removeClass('hide');
	        		$('#alertSuccessAreaMsg').addClass('hide');
             }
         });
    	
    }
    function editData(){
    	addOrEdit(arguments[0]);
    }
    function addOrEdit(){
    	
    	
    	
		if(arguments.length==1){
			var data =	arguments[0].split('||');
			if(data[0] == "" || data[1] =="" ){
				view.buttonSave.disable();
				
				$('#alertDangerAreaMsg').removeClass('hide');
        		$('#alertSuccessAreaMsg').addClass('hide');
			}
			view.inputAddId.val(data[0]);
			view.dropdownAddGroupKey.setSelectedByValue(data[1]);
			view.inputAddKey.val(data[2]);
			view.inputAddValue.val(data[3] == "null" ? "" : data[3]);
			view.inputAddOrdered.val(data[4] == "null" ? "" : data[4]);
			view.inputAddProperty1.val(data[5] == "null" ? "" : data[5]);
			view.inputAddProperty2.val(data[6] == "null" ? "" : data[6]);
			view.inputAddProperty3.val(data[7] == "null" ? "" : data[7]);
			view.inputAddProperty4.val(data[8] == "null" ? "" : data[8]);
			view.inputAddProperty5.val(data[9] == "null" ? "" : data[9]);
		}
		hideAllPanel();
        view.createPanel.show();
        
        

	}
    
	
    
    function resetCreateBtnClickEvent(){
    	
    	$('#createForm')[0].reset();
    	hideAllPanel();
    	view.searchPanel.show();
        view.searchResultPanel.show();
        //view.createPanel.hide();
    }

    function createBtnClickEvent(){
        /* view.searchPanel.hide();
        view.searchResultPanel.hide(); */
        $('#alertDangerAreaMsg').addClass('hide');
        $('#alertSuccessAreaMsg').addClass('hide');
        hideAllPanel();
        view.createPanel.show();
        $('#createForm')[0].reset();
        view.inputAddId.val(0);
    }

    function saveBtnClickEvent(){
    	$('#alertDangerAreaMsg').addClass('hide');
		$('#alertSuccessAreaMsg').addClass('hide');
		view.alertDangerValidateMsg.hide();
		var errMsg = '';
		
		
		if( view.dropdownAddGroupKey.val() == ''){
			errMsg = 'กรุณาเลือก Group Code';
		}
			 else if( view.inputAddKey.val() == ''){
			errMsg = 'กรุณากรอก key';
		}
		else if( view.inputAddValue.val() == ''){
			errMsg = 'กรุณากรอก value';
		}
			 
		
		if(errMsg != ''){
			view.alertDangerValidateMsg.error([errMsg]);
			view.alertDangerValidateMsg.show();
			return false;
		}
		
            var dataSend =
            {
          		"id" : view.inputAddId.val()
                  ,"groupKey" : view.dropdownAddGroupKey.val()
                  ,"ordered" : view.inputAddOrdered.val()
                  ,"key" : view.inputAddKey.val()
                  ,"value" : view.inputAddValue.val()
                  ,"property1" : view.inputAddProperty1.val()
                  ,"property2" : view.inputAddProperty2.val()
                  ,"property3" : view.inputAddProperty3.val()
                  ,"property4" : view.inputAddProperty4.val()
                  ,"property5" : view.inputAddProperty5.val()
            };
            $.ajax({
                type: "POST",
                url: "../MasterData/save",
                data: JSON.stringify(dataSend),
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                success:function(data){
                    $('#createForm')[0].reset();
 					$('#alertSuccessAreaMsg').removeClass('hide');
 					$('#alertDangerAreaMsg').addClass('hide');
                },
                error: function(jqxhr) {
 	        		$('#alertDangerAreaMsg').removeClass('hide');
 	        		$('#alertSuccessAreaMsg').addClass('hide');
                }
            });
        
    }
    
   
    function runningFormatter(value, row, index) {
	    return index;
	}

    function isValidCreateData(){
        var isKeyValid = view.key.error();
        var isValueValid = view.value.error();
        return !(isKeyValid || isValueValid);
    }

</script>
</html>

