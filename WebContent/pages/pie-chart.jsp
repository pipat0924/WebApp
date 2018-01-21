<html>

<head>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="resources/js/canvasjs.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="resources/bootstrap-table/src/bootstrap-table.css"
	rel="stylesheet" type="text/css" />
<link href="resources/css/datepicker.min.css" rel="stylesheet"
	type="text/css" />
<link href="resources/custom.css" rel="stylesheet" type="text/css" />
<script src="resources/jquery.min.js" type="text/javascript"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"
	type="text/javascript"></script>
<script src="resources/bootstrap-table/src/bootstrap-table.js"></script>
<script src="resources/js/bootstrap-datepicker.min.js"></script>
<script src="resources/js/mustache.min.js" type="text/javascript"></script>
<script src="resources/custom.js" type="text/javascript"></script>

</head>
<body>
<div class="container">
<div style="position: fixed;right: 13%;z-index: 99;top: 10px;">
<button class="btn btn-default" id="printChart" value="print a div!"><span class="glyphicon glyphicon-print"></span> Print </button>
<button class="btn btn-default" id="exportChart" value="print a div!"><span class="glyphicon glyphicon-save"></span> Save </button>
</div>
<form id="chart">
	<div id="chartContainer" style="height: 600px; width: 100%;"></div> 
</form>
</div>
</body>
<script type="text/javascript">
var view = (function($){
	self.session = function(key, val) { if (!val) { var val = window.sessionStorage.getItem(key); return val && (val.indexOf("{") > -1 || val.indexOf("[") > -1) ? JSON.parse(val) : val } window.sessionStorage.setItem(key, ($.type(val) != "object" && $.type(val) != "array" ? val : JSON.stringify(val)))	};
	self.utils = {
		guid: function(){ function r() { return Math.floor((Math.random() + 1) * 0x10000).toString(16).substring(1) } return r() + r() +"-"+ r() +"-"+ r() +"-"+ r() +"-"+ r() + r() + r() },
		numberFormat: function(num, dec) { return $.type(num) !== "number" ? "0.00" : num.toFixed(dec || 2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); },
		dateFormat: function() { var obj = arguments[0]; if ($.type(obj) == "string" && /[0-9]{2}.[0-9]{2}.[0-9]{4}/g) { return obj } else if ($.isNumeric(obj)) { var dt = new Date(obj), dd = dt.getDate().toString(), mm = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(); return (dd[1]?"":"0") + dd +"/"+ (mm[1]?"":"0") + mm +"/"+ yyyy } else if ($.type == "date") { return "" } return "" },
		timeFormat: function() { var obj = arguments[0]; if ($.type(obj) == "string" && /[0-9]{2}.[0-9]{2}.[0-9]{4}/g) { return obj } else if ($.isNumeric(obj)) { var dt = new Date(obj), hh = dt.getHours().toString(), mm = dt.getMinutes().toString(), ss = dt.getSeconds().toString(); return (hh[1]?"":"0") + hh +":"+ (mm[1]?"":"0") + mm +":"+ (ss[1]?"":"0") + ss } else if ($.type == "date") { return "" } return "" },
		dateTimeFormat: function() { var obj = arguments[0], dt = $.type(obj) != "date" ? ($.isNumeric(obj) ? new Date(obj) : new Date()) : new Date(), dd = dt.getDate().toString(), MM = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(), hh = dt.getHours().toString(), mm = dt.getMinutes().toString(), ss = dt.getSeconds().toString(); return (dd[1]?"":"0") + dd +"/"+ (MM[1]?"":"0") + MM +"/"+ yyyy +" "+ (hh[1]?"":"0") + hh +":"+ (mm[1]?"":"0") + mm +":"+ (ss[1]?"":"0") + ss },
		queryString: function() { var pos = location.href.indexOf("?"), obj = {}; if (pos < 0) return obj; var params = location.href.slice(pos + 1).split("&"); for (var i = 0, m = params.length; i < m; i++) { pos = params[i].indexOf("="); if (pos < 0) { obj[params[i]] = true; continue; } obj[params[i].substring(0, pos)] = (params[i].substring(pos + 1) || true) } return obj },
		window: function(windowName, url, side) { window.windowOpened = (window.windowOpened || {}); var screenProp = ""; if (side == "left") { screenProp = "left=0,top=0,width="+ (screen.width/2) +",height="+ (screen.height-100) } else if (side == "right") { screenProp = "left="+ (screen.width/2-40) +",top=0,width="+ (screen.width/2+40) +",height="+ (screen.height-100) } window.windowOpened[windowName] = window.open(url, windowName, "menubar=no,scrollbars=no,resizable=0,"+ screenProp, false); return window.windowOpened }
	};
	//// AUTOMATIC REGISTER FORMATTER FUNCTION ////
	window.get = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "GET", "data": params, "error": msgDialog.valid, "success": function(res){ (preCheck || function(o){ console.log(o) })(res); if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
	window.post = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "POST", "data": params, "error": msgDialog.valid, "success": function(res){ (preCheck || function(o){ console.log(o) })(res); if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
	window.padLeft = function(obj, size, ch){ return padding(obj, size, ch, true) }; window.padRight = function(obj, size, ch){ return padding(obj, size, ch, false) }; function padding(obj, size, ch, isLeft) { var str = ""; if (!obj) str = ""; else if($.type(obj) == "string") str = obj; else if ($.type(obj) == "number") str = String(obj); if (str.length >= size) return str; var padded = Array(Math.max(size - str.length + 1, 0)).join(ch); return (isLeft ? padded : "") + str + (isLeft ? "" : padded) }
	window.stripToNumber = function(str) { return $.type(str) === "string" && $.isNumeric(str = str.replace(/[,A-Za-z\(\)\[\]\{\}]/g, "")) ? parseFloat(str, 10) : 0 }
	window.toDateString = function(ddMMyyyy){ return ddMMyyyy.replace(/(\d{2}).(\d{2}).(\d{4})/g, "$2/$1/$3") }
	window.runningFormatter = function(val, row, ind) { return ind + 1 }
	window.numberFormatter = function(val, row, ind) { return !$.isNumeric(stripToNumber(val)) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') }
	window.dateFormatter = function(val, row, ind){ if (!val) return ""; if ((/(\d{4}).(\d{2}).(\d{2}).*/g).test(val)) return val.replace(/(\d{4}).(\d{2}).(\d{2}).*/g, "$3/$2/$1"); return val }
	window.timeFormatter = function(val, row, ind){ if (!val) return ""; if ((/.*T(\d{2}).(\d{2}).(\d{2}).*/g).test(val)) return val.replace(/.*T(\d{2}).(\d{2}).(\d{2}).*/g, "$1:$2:$3"); return val }
	window.dateTimeFormatter = function(val, row, ind){ if (!val) return ""; if ((/(\d{4}).(\d{2}).(\d{2})T(\d{2}).(\d{2}).(\d{2}).*/g).test(val)) return val.replace(/(\d{4}).(\d{2}).(\d{2})T(\d{2}).(\d{2}).(\d{2}).*/g, "$3/$2/$1 $4:$5:$6"); return val }
	window.stringInputFormatter = function(val, row, ind) { return '<input value="'+ $.trim(val) +'" maxLength="40" class="form-control">' }
	window.numberInputFormatter = function(val, row, ind) { return '<input value="'+ self.utils.numberFormat(parseFloat(val || "0.00", 10)) +'" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">' }
	window.selectButtonFormatter = function(val, row, ind) { return '<a class="btn btn-primary btn-xs selList" onclick=\'(function(elem, row, ind){ var table = $("#"+ $(elem).closest("table")[0].id); window[table[0].id +"SelectEvent"](table, row, ind) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-check"></span> Select</a>' }
	window.modifyButtonFormatter = function(val, row, ind) { return '<a class="btn btn-primary btn-xs modList" onclick=\'(function(elem, row, ind){ var table = $("#"+ $(elem).closest("table")[0].id); window[table[0].id +"ModifyEvent"](table, row, ind) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-pencil"></span> Modify</a>' }
	window.removeButtonFormatter = function(val, row, ind) { return '<a class="btn btn-primary btn-xs delList" onclick=\'(function(elem, row, ind){ $("#"+ $(elem).closest("table")[0].id).bootstrapTable("remove", { field: "index", values: [ind] }) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-trash"></span> Remove</a>' }
	function Breadcrumb(el) { var obj = this; obj.el = el; obj.elem = $(el); var list = obj.elem.find("li").each(function(i,o){ $(o).data("index", i) }), index = list.filter(".active").data("index");
		obj.index = function(){ if(arguments.length == 1) { list.removeClass("active").eq(index = arguments[0]).addClass("active"); return obj } return index }
		obj.next = function(){ obj.index(++index); return this }; obj.prev = function(){ obj.index(--index); return this }
	}

	function Message(el) { var obj = this, timeCnt = 0, loadFunc; obj.el = el; obj.elem = $(el);
		obj.hide = function() { obj.elem.addClass("hide"); return obj };
		obj.show = function(flag) { if (flag == null || flag) obj.elem.removeClass("hide"); else obj.hide(); return obj };
		obj.clear = function() { obj.elem.find("*").remove(); obj.hide(); return obj };
		obj.message = function(arr, cls) { $.each(arr, function(i,o) { var m = o; if ($.type(o) === "object") { m = (o.desc || o.messageDesc) +" [code="+ (o.code || o.messageCode) +"]" }; obj.elem.append('<div class="'+ cls +'">'+ m +'<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button></div>') }); return obj };
		obj.error = function(arr) { return obj.message(arr, "alert alert-danger") };
		obj.warn = function(arr) { return obj.message(arr, "alert alert-warning") };
		obj.success = function(arr) { return obj.message(arr, "alert alert-success") };
		obj.valid = function(jqXHR, textStatus, errorThrow){ var res = jqXHR; obj.stopLoad(); if (jqXHR && jqXHR.responseJSON) res = jqXHR.responseJSON; if (res) { var isNoData = false; if (res.statusCode && res.statusCode != "0") { obj.warn(res && $.type(res.warningList) === 'array' ? res.warningList : []).error(res && $.type(res.errorList) === 'array' ? res.errorList : ["An error occurred on the server side but there is no error message returned."]).show(); return false } if (res.data && res.statusCode == '0' && res.data.length < 1) isNoData = true; if ($.type(res._embedded) === 'object' && $.map(res._embedded,function(v,k){return v}).length < 1) isNoData = true; if (isNoData) { obj.warn(["There is no records of data."]).show(); return false } return true } obj.error(["An error occurred on the server side but there is no error message returned."]).show(); return false }
		obj.hideLoad = function(){ obj.stopLoad().clear(); return this }; obj.stopLoad = function(){ clearInterval(loadFunc); return this }
		obj.showLoad = function(msg){ obj.elem.append('<div id="'+ obj.el +'-loading" class="alert alert-warning">'+ bind(msg, 0) +'</div>'); timeCnt = 0; var elem = document.getElementById(obj.el +"-loading"); loadFunc = setInterval(function(){ elem.innerHTML = bind(msg, ++timeCnt) }, 1000); obj.show(); return this }
		function bind(msg, timeCnt) { return msg.replace(/\{timeCnt\}/g, timeCnt) }
	}

	return this;
		})(jQuery);
		
			
	window.onload=function() { 
		var datechart = view.session("sessiondate").data;
		var datalist = [];
		var newdatalist =[];
		datalist = chartGroupPaymentType(datechart);
		if(datalist.length > 1){
			charts(datatochart(datalist),"chartContainer");
		}else{		
		newdatalist = chartGrouppayTypeName(datechart);
		charts(datatochart(newdatalist),"chartContainer");
		}
	}
	function datatochart(datalist){
		var listlegendText =[];							
			for(var i = 0; i < datalist.length ;i++){
				var objlegendText = {
						"y": datalist[i].cntReceipt, 
						"legendText":datalist[i].group, 
						"indexLabel": datalist[i].group +"  "+ view.numberFormatter(datalist[i].totalCharge) +" บาท",	

				 };
				listlegendText.push(objlegendText);
			}
		return listlegendText;
	}

	function chartGroupPaymentType(datechart){
		var groups = {};
		var totalchar = 0;
		var totalCnt = 0;
		for (var i = 0; i < datechart.length; i++) {
		  var groupName = datechart[i].paymentType;
	 	 if (!groups[groupName]) {
	 	   groups[groupName] = [];
	 	   totalchar = 0;
	 	   totalCnt = 0;
		  }
	 	  totalchar +=  parseFloat(datechart[i].totalCharge);
	 	  totalCnt  += parseFloat(datechart[i].cntReceipt);
		  groups[groupName].push(totalchar);
		  groups[groupName].push(totalCnt);
		}
		myArray = [];
		for (var groupName in groups) {
			var datagroups = groups[groupName]
			var obj ={
					group: groupName, 
				totalCharge: datagroups[datagroups.length -2],
				cntReceipt: datagroups[datagroups.length -1]
			}
	 		 myArray.push(obj);
		}
		return myArray;
	}
	function chartGrouppayTypeName(datechart){
		var groups = {};
		var totalchar = 0;
		var totalCnt = 0;
		for (var i = 0; i < datechart.length; i++) {
		  var groupName = datechart[i].payTypeName;
	 	 if (!groups[groupName]) {
	 	   groups[groupName] = [];
	 	   totalchar = 0;
	 	   totalCnt = 0;
		  }
	 	  totalchar +=  parseFloat(datechart[i].totalCharge);
	 	  totalCnt  += parseFloat(datechart[i].cntReceipt);
		  groups[groupName].push(totalchar);
		  groups[groupName].push(totalCnt);
		}
		myArray = [];
		for (var groupName in groups) {
			var datagroups = groups[groupName]
			var obj ={
				group: groupName, 
				totalCharge: datagroups[datagroups.length -2],
				cntReceipt: datagroups[datagroups.length -1]
			}
	 		 myArray.push(obj);
		}
		return myArray;
	}
	function charts(dataPoin,id){
		var chart = new CanvasJS.Chart(id,
				{
					title:{
						fontSize:18,
						text: "รายงานสถิติการรับชำระเงิน"
					},
			                animationEnabled: true,
			        	/* 	exportFileName: "Pie Chart",
			        		exportEnabled: true, */
			                
					legend:{
						verticalAlign: "bottom",
						horizontalAlign: "center"
					},
					data: [
					{        
						indexLabelFontSize: 20,
						indexLabelFontFamily: "Monospace",       
						indexLabelFontColor: "back", 
						indexLabelLineColor: "darkgrey",        
						indexLabelPlacement: "outside",
						type: "pie",       
						showInLegend: true,
						toolTipContent: "{y} รายการ - <strong>#percent%</strong>",
						dataPoints: dataPoin
					}
					]
				});
				chart.render();
				document.getElementById("printChart").addEventListener("click",function(){
			    	chart.print();
			    }); 
				document.getElementById("exportChart").addEventListener("click",function(){
			    	chart.exportChart({format: "jpg"});
			    }); 
			}
</script>
</html>