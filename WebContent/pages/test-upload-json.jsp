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
				<div class="col-sm-12">
					<button onclick="TestManualBetch()">TestManualBetch</button>
				</div>

</body>
<script type="text/javascript">
function TestManualBetch(){
	   debugger;
    $.ajax({
        url: "../creatPaymentManualOffLine.json"
        , type: "GET"
        , async: false
        ,dataType: "json"
        , success: function (res) {
				debugger;
        }
    }
    ); 
};
</script>
</html>