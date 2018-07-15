$(document).ready(function() {
	totalPrice();
	
	$(".deleteBt").on("click" , function(){
		
		var r = confirm("確定要刪除此商品嗎?");
		
		if (r == true) {
			$(this).parent().parent().parent().remove();
			totalPrice();
		} else {

		}
		
	});
	
	$(".giftContent").on("change", function() {
		totalPrice();
	});
	
});

function backMain() {
	window.location.href = '/mvcFoodServer/shoppingMain';
}

function goToMain2() {
	window.location.href = '/mvcFoodServer/member9487/goToMain2';
}
function totalPrice() {
	var allCount = $(".giftContent");
	var allPrice = $(".giftPrice");
	var total = 0;

	var a = $("img");
	var s = "";
	
	for (var i = 0; i < a.length - 3; i++) {
		total += ($(allCount[i]).val() * parseInt($(allPrice[i]).text()));

		s += $(a[i + 3]).attr("src").split("/")[3] + "-" + $(allCount[i]).val();
		if (i != a.length - 4) {
			s += decodeURIComponent(",");
		}
	}
	$(".totalPrice").text(total);
	$.removeCookie('giftList', {
		path : '/mvcFoodServer'
	});
	$.cookie('giftList', s, {
		expires : 7,
		path : '/mvcFoodServer'
	});
}
