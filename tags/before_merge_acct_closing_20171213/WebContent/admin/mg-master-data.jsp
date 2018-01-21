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
												<input class="form-control" id="groupKeyCrt" name="groupKeyCrt">
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
                    <table id="searchResultTable" data-toggle="table" data-toolbar="#officerToolbar" data-search="true" data-show-pagination-switch="true" data-show-refresh="true" data-show-toggle="true" data-show-columns="true" data-show-export="true" data-pagination="true" data-height="400" data-cache="false">
                        <thead>
                        <tr>
                            <th data-field="groupKey" data-align="center">Group Name</th>
                            <th data-field="key" data-align="left">Code</th>
                            <th data-field="value" data-align="left">Value</th>
                            <th data-field="property1" data-align="center">Property 1</th>
                            <th data-field="property2" data-align="center">Property 2</th>
                            <th data-field="property3" data-align="center">Property 3</th>
                            <th data-field="property4" data-align="center">Property 4</th>
                            <th data-field="property5" data-align="center">Property 5</th>
                            <th data-field="id" data-formatter="rowsFormatter" data-align="center"></th>
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
<!--                                                 <select class="form-control" id="groupKey" name="groupKey"></select> -->
                                                <input class="form-control" id="groupKey" name="groupKey">
                                            </div>
                                            <label class="control-label col-sm-2">Ordering :</label>
                                            <div class="col-sm-2"><input class="form-control" id="ordered" name="ordered"></div>
                                            <div class="col-sm-4"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-sm-2">Key :</label>
                                            <div class="col-sm-2"><input class="form-control" id="key" name="key"></div>
                                            <label class="control-label col-sm-2">Value :</label>
                                            <div class="col-sm-2"><input class="form-control" id="value" name="value"></div>
                                            <div class="col-sm-4"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">Property 1 :</label>
                                            <div class="col-sm-2"><input class="form-control" id="property1" name="property1"></div>
                                            <label class="control-label col-sm-2">Property 2 :</label>
                                            <div class="col-sm-2"><input class="form-control" id="property2" name="property2"></div>
                                            <div class="col-sm-4"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">Property 3 :</label>
                                            <div class="col-sm-2"><input class="form-control" id="property3" name="property3"></div>
                                            <label class="control-label col-sm-2">Property 4 :</label>
                                            <div class="col-sm-2"><input class="form-control" id="property4" name="property4"></div>
                                            <div class="col-sm-4"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">Property 5 :</label>
                                            <div class="col-sm-2"><input class="form-control" id="property5" name="property5"></div>
                                            <div class="col-sm-8">
                                                <a id="saveBtn" class="btn btn-primary"><span class="glyphicon glyphicon-plus-sign"></span> บันทึกข้อมูล</a>&nbsp;
                                                <a id="resetCreateBtn" class="btn btn-danger"><span class="glyphicon glyphicon-minus-sign"></span> ยกเลิก</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <a id="backToSearchBtn" class="btn btn-success"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูล</a>
                                            </div>
                                            <input type="hidden" id="id" name="id" value=""/>
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

//     var dto = parent.getMasterDataWithKey('000002');
//     alert(dto);
    var searchPanel = new Panel("#searchPanel").show();
    var searchResultPanel = new Panel("#searchResultPanel").hide();
    var createPanel = new Panel("#createPanel").hide();

    // Search Criteria
//     var groupKeyCrt = new Listbox("#groupKeyCrt").init("../MasterData/getGroupData?key=PAYTYPE");
	var groupKeyCrt = new Input("#groupKeyCrt");
    
    var keyCrt = new Input("#keyCrt");
    var valueCrt = new Input("#valueCrt");
    var searchCrtObjNameArr = ['groupKeyCrt', 'keyCrt', 'valueCrt'];

    // Create Param
    var id = new Input("#id");
    var groupKey = new Input("#groupKey");
    var ordered = new Input("#ordered");
    var key = new Input("#key");
    var value = new Input("#value");
    var property1 = new Input("#property1");
    var property2 = new Input("#property2");
    var property3 = new Input("#property3");
    var property4 = new Input("#property4");
    var property5 = new Input("#property5");

    var searchResultTable = new BootstrapTable("#searchResultTable");

    var searchBtn = new Button("#searchBtn");
    var resetSearchBtn = new Button("#resetSearchBtn");
    var createBtn = new Button("#createBtn");
    var saveBtn = new Button("#saveBtn");
    var resetCreateBtn = new Button("#resetCreateBtn");
    var backToSearchBtn = new Button("#backToSearchBtn");

    var searchCriteriaId = [];

    function resetCreateBtnOnClick(){
        $('#createForm')[0].reset();  
    }
    function backToSearchBtnOnClick(){
//     	$('#searchForm')[0].reset();
        searchPanel.show();
        searchResultPanel.show();
        createPanel.hide();
//         $('#createForm')[0].reset();  
    }

    function searchBtnOnClick(){	//$.get("../genBillingIdoc.json", {"date":"xxxx"}, function(res){}); return false;
// 	alert('selected : '+groupKeyCrt.selected());
// 	alert('index : '+groupKeyCrt.index());
// 	alert('key : '+groupKeyCrt.key());
// 	alert('val : '+groupKeyCrt.val());
        if(isValidSearchCriteria()){
            searchResultPanel.show();
            var dataSend =
            {
                "groupKey" : groupKeyCrt.val()
                ,"key" : keyCrt.val()
                ,"value" : valueCrt.val()
            };
// 		$.post("../MasterData/search", { groupKey: groupKeyCrt.val(), key: keyCrt.val(), value: valueCrt.val() }, function(res){searchResultTable.rawData(res);});
            $.ajax({
                type: "POST",
                url: "../MasterData/search",
                data: JSON.stringify(dataSend),
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                success:function(data){
                    searchResultTable.rawData(data);
// 					$('#alertSuccessAreaMsg').removeClass('hide');
// 					$('#alertDangerAreaMsg').addClass('hide');
                },
                error: function(jqxhr) {
// 	        		$('#alertDangerAreaMsg').removeClass('hide');
// 	        		$('#alertSuccessAreaMsg').addClass('hide');
                }
            });
        }

    }

    function resetSearchBtnOnClick(){
        $('#searchForm')[0].reset();

// 	groupKeyCrt.error(false);
// 	keyCrt.error(false);
// 	valueCrt.error(false);
        clearErrorStyle(searchCrtObjNameArr);
    }

    function clearErrorStyle(objNameArray){
        $.each(objNameArray, function(index, objName) {
            eval(objName).error(false);
        });
    }

    function isValidSearchCriteria(){
// 	var isGroupKeyValid = groupKeyCrt.error();
// 	var isKeyValid = keyCrt.error();
// 	var isValueValid = valueCrt.error();
// 	return !(isGroupKeyValid || isKeyValid || isValueValid);

        if(groupKeyCrt.error() && keyCrt.error() && valueCrt.error()){
            return false;
        }else{
            clearErrorStyle(searchCrtObjNameArr);
            return true;
        }
    }

    function createBtnOnClick(){
    	$('#createForm')[0].reset();
        searchPanel.hide();
        searchResultPanel.hide();
        createPanel.show();
//         $('#searchForm')[0].reset();        
    }

    function saveBtnOnClick(){
        if(isValidCreateData()){
            var dataSend =
            {
                "id" : id.val()
                ,"groupKey" : groupKey.val()
                ,"ordered" : ordered.val()
                ,"key" : key.val()
                ,"value" : value.val()
                ,"property1" : property1.val()
                ,"property2" : property2.val()
                ,"property3" : property3.val()
                ,"property4" : property4.val()
                ,"property5" : property5.val()
            };
            $.ajax({
                type: "POST",
                url: "../MasterData/save",
                data: JSON.stringify(dataSend),
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                success:function(data){
                    $('#createForm')[0].reset();
// 					$('#alertSuccessAreaMsg').removeClass('hide');
// 					$('#alertDangerAreaMsg').addClass('hide');
                },
                error: function(jqxhr) {
// 	        		$('#alertDangerAreaMsg').removeClass('hide');
// 	        		$('#alertSuccessAreaMsg').addClass('hide');
                }
            });
        }
    }

    function isValidCreateData(){
        var isKeyValid = key.error();
        var isValueValid = value.error();
        return !(isKeyValid || isValueValid);
    }

    function rowsFormatter(id) {return '<a id="masterDataEditLink" style="cursor:pointer" onClick="findMasterDataDetail(\''+id+'\')" class="btn-sm btn-info"><i class="glyphicon glyphicon-edit"></i> แก้ไข</a>'; }
    function findMasterDataDetail(idParam) {
    	var rowData = searchResultTable.getId(idParam);
    	if(rowData != null){ 
			$.get("../MasterData/getSpecificGroupData", { "group": rowData.groupKey, "keyGroup": rowData.key}, function(res) {
				if(res != null && res.length > 0){
					id.val(res[0].id);
				    groupKey.val(res[0].groupKey);
				    ordered.val(res[0].ordered);
				    key.val(res[0].key);
				    value.val(res[0].value);
				    property1.val(res[0].property1);
				    property2.val(res[0].property2);
				    property3.val(res[0].property3);
				    property4.val(res[0].property4);
				    property5.val(res[0].property5);
					
					searchPanel.hide();
			        searchResultPanel.hide();
			        createPanel.show();
				}
			});
    	}
	}
    
</script>
</html>

