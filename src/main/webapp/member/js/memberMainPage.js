$(document).ready(function() {
	$.ajax({
		url : '/mvcFoodServer/member9487/testLogin88',
		type : 'POST',
		data : {},
		dataType : 'json', //text,json,xml
		success : function(date) {
			$(".nickName p").text(date.nickname);			
				$.ajax({
				url : '/mvcFoodServer/getMemberImg',
				type : 'POST',
				data : {
					userId : date.userAccount
				},
				dataType : 'json',
				success : function(date) {
					var a = 'data:image/jpeg;base64,' + date;
					$(".memberImg").attr("src" , a);
				}
			});
		}
	});
});

