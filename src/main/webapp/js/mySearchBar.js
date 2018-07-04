$(document).ready(function() {
	$(".mySearchBot").on("click", function() {
		var content = $(".mySearchReq").val();
	});
	$(".mySearchReq").focus(function(){
		$(this).attr('placeholder', '').css('borderColor','#203a43');
	})
	.blur(function(){
			$(this).css('borderColor','#aaa');
			if ($(this).val() == '') {
				$(this).attr('placeholder','Search...')
			}
	});
});