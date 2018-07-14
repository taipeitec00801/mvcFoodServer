$(document).ready(function() {

		var allCount = $(".giftContent");
		var allPrice = $(".giftPrice");
		var total = 0;
		for(var i = 0 ; i < allCount.length ; i++){
			total += ($(allCount[i]).val() * parseInt($(allPrice[i]).text()));
		}
		$(".totalPrice").text(total);	
});

function backMain(){
	window.location.href = '/mvcFoodServer/shoppingMain';	
}

function goToMain2(){
	window.location.href = '/mvcFoodServer/member9487/goToMain2';	
}
