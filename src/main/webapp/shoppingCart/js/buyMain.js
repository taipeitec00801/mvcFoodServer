$(document).ready(function() {
	totalPrice();

	$(".deleteBt").on("click", function() {

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
	changeClass();
});

function backMain() {
	window.location.href = '/mvcFoodServer/shoppingMain';
}

function goToMain2() {
	window.location.href = '/mvcFoodServer/member9487/buyMain2';
}

function goToMain() {
	window.location.href = '/mvcFoodServer/member9487/cartAddGift';
}

function totalPrice() {

	if (window.location.href.split("/")[5] !== "myOrder") {
		
		var allCount = $(".giftContent");
		var allPrice = $(".giftPrice");
		var total = 0;

		var a = $("img");
		var s = "";

		for (var i = 0; i < a.length - 3; i++) {
			total += ($(allCount[i]).val() * parseInt($(allPrice[i]).text()));

			s += $(a[i + 3]).attr("src").split("/")[3] + "-"
					+ $(allCount[i]).val();
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

}

function submitCart() {

	var r = confirm("確定要送出訂單嗎?");

	if (r == true) {
		// alert($.cookie('giftList'));
		// alert($.cookie('user'));
		$
				.ajax({
					url : '/mvcFoodServer/member9487/orderConfirm',
					type : 'POST',
					data : {
						orderList : $.cookie('giftList'),
						user : $.cookie('user')
					},
					success : function() {
						$.removeCookie('giftList', {
							path : '/mvcFoodServer'
						});
						// alert("準備前往/mvcFoodServer/member9487/buyEnd");
						window.location.href = '/mvcFoodServer/member9487/orderSuccess';
					},
					error : function() {
						alert("竟然失敗了??")
					}
				});
	}

}

function payToOrder() {

	//	
	var r = confirm("確任付款?");

	if (r == true) {
		window.location.href = '/mvcFoodServer/member9487/reflash8787';
		// window.location.href = '/mvcFoodServer/member9487/myOrder';
	} else {

	}
}

function changeClass() {
	if ($(".waitPay").text() == "訂單完成") {
		$(".waitChangeClass").attr("class", "active");
		$(".waitPay").attr("onclick", "");
	}
}

function goTomyOrder() {
	window.location.href = '/mvcFoodServer/member9487/myOrder';
}
