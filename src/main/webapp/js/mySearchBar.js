$(document).ready(function() {
	//--------login--------------------------------
	$.ajax({
		url : 'member9487/testLogin88',
		type : 'POST',
		data : {},
		dataType : 'json', //text,json,xml
		success : function(date) {
			$('.formOut').css("display", "none");
			$('.formIn').css("display", "block");
			$(".memberId").text(date.nickname);
			
			$.ajax({
				url : 'getMemberImg',
				type : 'POST',
				data : {
					userId : date.userAccount
				},
				dataType : 'json',
				success : function(date) {
					var a = 'data:image/jpeg;base64,' + date;
					$(".Img").attr('src', a)
				}
			});
		},
		error : function() {
			$.ajax({
				url : 'rememberMe',
				type : 'POST',
				success : function(date) {
					if (date) {
						window.location.href = 'memberLogin22';
					}
				}
			});
		}
	});

	$(".logOutBt").on("click", function() {
		$.ajax({
			url : 'member9487/logout',
			type : 'POST',
			data : {},
			dataType : 'json',
			error : function() {
				location.reload();
			}
		});
		var auth2 = gapi.auth2.getAuthInstance();
		auth2.signOut().then(function() {
			console.log('User signed out.');
		});
	});
	function onSignIn(googleUser) {
		// Useful data for your client-side scripts:
		alert("googleUser");
		var profile = googleUser.getBasicProfile();
		if(typeof $.cookie('user') === 'undefined'){
				$.ajax({
					url : 'googleAdd',
					type : 'POST',
					data : {
						userId : profile.getEmail()
					},
					dataType : 'json',
					success : function() {
//							alert("Google 回來了");
						window.location.href = 'memberLogin22';
					}
				});
		}
		// console.log("ID: " + profile.getId());
		// console.log('Full Name: ' + profile.getName());
		// console.log('Given Name: ' + profile.getGivenName());
		// console.log('Family Name: ' + profile.getFamilyName());
		// console.log("Image URL: " + profile.getImageUrl());
		// console.log("Email: " + profile.getEmail());
		// The ID token you need to pass to your backend:
		// var id_token = googleUser.getAuthResponse().id_token;
		// console.log("ID Token: " + id_token);
	};
	userAccount = "";
	userPassword = "";
	$("input[name=inputEmail]").on("focusout", function() {
		userAccount = $(this).val();
	});
	$("input[name=inputPassword]").on("focusout", function() {
		userPassword = $(this).val();
	});
	$("button[name=logInBt]")
			.on(
					"click",
					function(event) {

						var errorMessage = "";

						$(".submitError").html(errorMessage);

						function isValidEmailAddress(emailAddress) {
							var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
							return pattern.test(emailAddress);
						}
						;
						if (!isValidEmailAddress(userAccount)) {
							errorMessage = "請輸入正確的Email地址";
						}
						if (userPassword == "") {
							errorMessage = "請輸入密碼";
						}

						if (errorMessage == "") {

							$
									.ajax({
										url : 'login',
										type : 'POST',
										data : {
											userId : userAccount,
											userPassword : userPassword,
											rmMe : $(".rmMe").val()
										},
										dataType : 'json', //text,json,xml
										success : function(date) {
											if (!date) {
												//登入失敗
												$(".submitError").css(
														"visibility",
														"visible");
												$(".submitError").html(
														"帳號或密碼錯誤");
											} else {
												window.location.href = 'memberLogin22';
											}
										}
									});
						} else {
							$(".submitError").css("visibility", "visible");
							$(".submitError").html(errorMessage);
						}
					});

	$(".registerBt").on("click", function() {
		window.location.href = 'member9487';
	});
	
	
	
	//-----search--------------------------------
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

function onSignIn(googleUser) {
	// Useful data for your client-side scripts:
	var profile = googleUser.getBasicProfile();
	if(typeof $.cookie('user') === 'undefined'){
			$.ajax({
				url : 'googleAdd',
				type : 'POST',
				data : {
					userId : profile.getEmail()
				},
				dataType : 'json',
				success : function() {
//						alert("Google 回來了");
					window.location.href = 'memberLogin22';
				}
			});
	}
}

