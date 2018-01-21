<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css"/>
        <link href="resources/datepicker.min.css" rel="stylesheet" type="text/css"/>
        <link href="resources/custom.css" rel="stylesheet" type="text/css"/>
        <script src="resources/jquery.min.js" type="text/javascript"></script>
        <script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="resources/bootstrap-table/src/bootstrap-table.js"></script>
        <script src="resources/bootstrap-datepicker.min.js"></script>
        <script src="resources/custom.js" type="text/javascript"></script>
    </head>
    <body>
        <header class="header_page"></header>
        <section class="container-fluid menu">
            <!-- <button class="menu-toggle btn btn-sm btn-default" style="margin-bottom: 5px"><span class="glyphicon glyphicon-step-forward"></span></button> -->
                <%-- <%@include  file="menu.jsp" %> --%>
            <ol class="breadcrumb">
                <li><i>อนุมัติคำร้อง</i></li>
                <li class="active">รายการคำร้อง</li>
            </ol>
            <div class="row">
                <div class="col-md-12 tab-modefile">
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active" ><a href="#tab_1" aria-controls="tab_1" role="tab" data-toggle="tab">รายการคำร้อง</a></li>
                        <li class="list-inline pull-right list-menu-set">
                            <div class="row">
                                <div class="col-md-12">
                                    <button type="button" class="btn btn-primary pull-right" data-toggle="modal" data-target="#modal_authen"><span class="glyphicon glyphicon-search"></span> ดำเนินการตรวจสอบ</button> 
                                </div>
                            </div>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="tab_1">
                            <table id="table_1"
                                   data-row-style="rowStyle"
                                   data-toggle="table"
                                   data-classes="table table-hover table-striped"
                                   >
                                <thead>
                                    <tr>
                                    <th data-field="a1" data-align="center" class=""></th>
                                        <th data-field="a" data-align="center" class="">#</th>
                                        <th data-field="b" class="">เลขที่คำร้อง</th>
                                        <th data-field="c" class="">ประเภทคำร้อง</th>
                                        <th data-field="d" data-align="center" class="">วันที่คำร้อง</th>
                                        <th data-field="e" data-halign="center" data-align="left" class="">ผู้บันทึกคำร้อง</th>
                                        <th data-field="f" data-align="center" class="">สถานะคำร้อง</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td><input type="checkbox" class="checkbox" id="checkbox_37" onclick="show_price('37')"></td>
                                         <td>1</td>
                                        <td>EP1254512155</td>
                                        <td>ขอคืนเงิน</td>
                                        <td>15/01/2016</td>
                                        <td>นายเฉลิมพล เชื้อเย็น</td>
                                        <td>รออนุมัติ</td>
                                <span style="display: none;" id="desc0">
                                    <pre><strong class="bold">Description</strong></pre>
                                </span>
                                </tr>
                                <tr>
                                    <td><input type="checkbox" class="checkbox" id="checkbox_38" onclick="show_price('38')"></td>
                                    <td>2</td>
                                    <td>EP1254545875</td>
                                    <td>ขอปรับลดหนี้</td>
                                    <td>16/01/2016</td>
                                    <td>นายเฉลิมพล เชื้อเย็น</td>
                                    <td>รออนุมัติ</td>
                                <span style="display: none;" id="desc0">
                                    <pre><strong class="bold">Description</strong></pre>
                                </span>
                                </tr>

                                </tbody>
                            </table>




                        </div>

                    </div>
                </div>
            </div>
            <!--------------------------------->


        </section>

        <div class="modal fade" role="dialog" id="modal_authen">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">ตรวจสอบคำร้อง</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">


                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <label class="control-label col-sm-1" ></label>
                                        <label class="control-label col-sm-3" style="text-align: left">
                                            <input type="radio" name="RadioOptions1" id="RadioOptions1" value="" checked=""> อนุมัติ :
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-1" ></label>
                                        <label class="control-label col-sm-3" style="text-align: left">
                                            <input type="radio" name="RadioOptions1" id="RadioOptions2" value=""> ไม่อนุมัติ :
                                        </label>
                                        <label class="control-label col-sm-4" style="text-align: right">
                                            เหตุผลการไม่อนุมัติ :
                                        </label>
                                        <div class="col-sm-4">
                                            <select class="form-control">
                                                <option>xxxxxxxxxxxxx</option>
                                                <option>2</option>
                                                <option>3</option>
                                                <option>4</option>
                                                <option>5</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-4" ></label>
                                        <label class="col-sm-4 control-label">หมายเหตุ :</label>
                                        <div class="col-sm-4">
                                            <input type="text" class="form-control">
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" ><span class="glyphicon glyphicon-print"></span> บันทึก</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal --> 

    </body>
    <script>
        $("#btn_print_1").click(function () {
            $("#modal_print_1").modal("show");
        });
        $("#btn_print_2").click(function () {
            $("#modal_print_2").modal("show");
        });
    </script>
</html>
