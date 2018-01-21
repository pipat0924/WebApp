jQuery(function() {
//	$('.header_page').load("header_page");
	$('.header_page_1').load("header_page_1.jsp");
	$('.footer_page').load("footer_page.jsp");
//	$('.menu-side').load("menu.jsp");
});

$(document).ready(function() {
	$("#billing_account").focus();
	var body = $('body');
	
//	body.toggleClass('menu-open');
//	body.css("overflow-y", "hidden");

	//$('.menu-toggle').on('click', function(event) {
	$(".menu-toggle.btn.btn-sm.btn-default").on('click', function(event) {

		event.preventDefault();
		body.toggleClass('menu-open');
		body.css("overflow-y", "hidden");

	});
	
	$("a.list-group-item.small").click(function(e){
	     e.preventDefault();
	     document.getElementById("page").src = this.href;
	     $(".menu-toggle.btn.btn-sm.btn-default").click();
	});
	$("#list_payment").collapse('show');
	$("#list_2").collapse('show');
	$("#list_3").collapse('show');
	$("#list_4").collapse('show');
	$("#list_5").collapse('show');
	$("#list_6").collapse('show');
	$("#list_7").collapse('show');
	$("#list_8").collapse('show');
	$(".menu-close").on('click', function() {
		//body.css("overflow-y", "show");
		$(".menu-toggle.btn.btn-sm.btn-default").click();
	});
});
