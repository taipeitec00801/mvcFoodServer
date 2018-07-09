$(document).ready(function () {
	
//	var width = $(window).width();
//	alert("總寬: " + width);
//	var newWidth = (width-800)/2 +"px";
//	alert("邊界: " + newWidth);
//	$("html").css("margin-left", function(){return newWidth;});
	$('#opInput').on("focusout",function () {
		var op = $('#opInput').val();
		var opl = $('#opInput').val().length;
		alert("ajax + op : "+op);
		if (opl > 1) {
			$.ajax({
				url: '/mvcFoodServer/ajaxPassword.do',
				type: 'POST',
				data: {
					oldPassword: op,
				},
				dataType: 'text', //text,json,xml
				success: function (data) {
					alert(data);
					if(data == "true"){
						alert("Ajax執行結果正確"+data);
						$("#opInput").css("border-color", "green");
					}else{
						alert("Ajax執行結果錯誤"+data);
						$("#opInput").css("border-color", "red");
					}
					
				}

			});
		}
			alert("沒輸入舊密碼不動作");
			
		});
	 
});
	
	
		$('#btChangePassword').click(function () {
			var np = $('#npInput').val();
			var reNp = $('#NPagain').val();
			$("#locaPassword").find("input").each(function () {
				$(this).val(" ");
			});
			if (reNp == np) {
				
			} else {
				$("p").append("<span style='color:red; font-size: 12px'>密碼錯誤</span>");
				alert("密碼不一樣");
			}
	});


	// 相片管理
	
//		$(window).load(function() {
//		$.facebox.settings.loadingImage = 'images/member/loading.gif',
//			$.facebox.settings.closeImage =
//			'images/member/closelabel.png',
//
//			$('.facebox').facebox();
//		$('.thumbnail').on("click", function () {
//			var imgSrc = $(this).children().attr("src");
//			var thisImg = $(this).parent('div');
//			alert(imgSrc);
//			$('.popup').html(
//				'<img src="images/member/arrow_left.gif" id="leftDiv" alt="...">'
//				+ '<img src="images/member/arrow_right.gif" id="rightDiv" alt="...">'
//				+ '<div class="content">'
//				+ '</div>'
//			);
//			$(".content").on("click", function () {
//				if ($(thisImg).is("div")) {
//					// alert('true1');
//					nextImg = $(thisImg).next().find('img').attr("src");
//					// alert(nextImg);
//					thisImg = $(thisImg).next();
//					$(".content").find('img').attr("src", nextImg);
//				} else {
//					alert("END");
//				}
//
//			});
//			$("#rightDiv").click(function () {
//				// alert('true2');
//				if ($(thisImg).is("div")) {
//					// alert('true3');
//					nextImg = $(thisImg).next().find('img').attr("src");
//					if (nextImg != undefined) {
//						// alert(nextImg);
//						thisImg = $(thisImg).next();
//						$(".content").find('img').attr("src", nextImg);
//					} else {
//						// alert(nextImg);
//						$('.facebox').trigger('close.facebox');
//					}
//
//				}
//
//			});
//			$("#leftDiv").on("click", function () {
//				// alert('true4');
//				if ($(thisImg).is("div")) {
//					// alert('true5');
//					nextImg = $(thisImg).prev().find('img').attr("src");
//
//					if (nextImg != undefined) {
//						// alert(nextImg);
//						thisImg = $(thisImg).prev();
//						$(".content").find('img').attr("src", nextImg);
//					} else {
//						// alert(nextImg);
//						$('.facebox').trigger('close.facebox');
//					}
//
//				}
//
//
//			});
//		});
//
//	    });

	
