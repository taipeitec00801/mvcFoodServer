$(document).ready(function() {
	$.get("/mvcFoodServer/cartMain", function(data) {
		$(".cartMain").html(data);
	});

	$(".cartAdd").on("click", function() {
		$.UrlParam = function(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if (r != null)
				return unescape(r[2]);
			return null;
		}
		var param = $.UrlParam("giftId");

		$.ajax({
			url : "/mvcFoodServer/cartAddGift",
			type : "GET",
			data : {
				giftId : param,
				number : $("#buyCount").val()
			},
			success : function() {
				window.location.href = '/mvcFoodServer/shoppingMain';
			}
		});
	});

});

function testLogin() {
	if (typeof $.cookie('giftList') === 'undefined') {
		alert("購物車沒東西呦!!");		
	}else{
		$.ajax({
			url : "/mvcFoodServer/member9487/testLogin88",
			type : "POST",
			data : {},
			success : function() {
				alert("即將前往結帳");
			},
			error : function() {
				alert("請先登入");
			}
		});
	}
};
