<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id"
	content="891658422538-ccj5goer8ah8440aq7f0iq80p43e2l0j.apps.googleusercontent.com">

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
<link rel="shortcut icon" href="favicon.ico">

<link
	href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700,300'
	rel='stylesheet' type='text/css'>

<!-- Animate.css -->
<link rel="stylesheet" href="css/animate.css" type="text/css">
<!-- Icomoon Icon Fonts-->
<link rel="stylesheet" href="css/icomoon.css" type="text/css">
<!-- Bootstrap  -->
<link rel="stylesheet" href="css/bootstrap.css" type="text/css">
<!-- Superfish -->
<link rel="stylesheet" href="css/superfish.css" type="text/css">

<link rel="stylesheet" href="css/style.css" type="text/css">

<link rel="stylesheet" href="member/css/myRegistered.css"
	type="text/css">
<!-- Navbar css -->
<link rel="stylesheet" href="css/myNavbarFooter.css" type="text/css">
</head>
<body>
	<div id="fh5co-wrapper">
		<div id="fh5co-page">
			<!-- navbar -->
			<div class="myNavbar">
				<%@ include file="navbar.jsp"%>
			</div>
			<div>
				<form id="validationForm">
					<label for="email">Email</label> <input type="email" name="email"
						id="email" class="inputError" placeholder="example@gmail.com">
					<label for="pass">密碼</label> <input name="pass" type="password"
						id="pass1" class="inputError" placeholder="至少6碼英文,數字"> <label
						for="pass1">確認密碼</label> <input name="pass1" type="password"
						id="pass2" style="margin: 0px 5px 5px 0px;" class="inputError">

					<div>
						<label for="brithDay" style="margin-left: 0px; display: inline;">生日</label>
						<label for="brithDay" style="margin-left: 150px; display: inline;">性別</label>
						<br> <input type="date" name="brithDay" id="brithDay"
							style="width: 220px; margin: 5px 5px 0px 0px;" class="inputError">
						<select name="" id="gender" class="inputError">
							<option value="x"></option>
							<option value="1">男</option>
							<option value="0">女</option>
						</select>
					</div>

					<div id="errorMessage" style="visibility: hidden;">error</div>

					<label for="submitButton"></label> <input type="button"
						id="submitButton" value="送出"
						style="width: 130px; border-radius: 5px; background: #fff;">
				</form>
			</div>
		</div>
	</div>
	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script>
	<!-- Stellar -->
	<script src="js/jquery.stellar.min.js"></script>
	<!-- Superfish -->
	<script src="js/hoverIntent.js"></script>
	<script src="js/superfish.js"></script>

	<!-- Main JS (Do not remove) -->
	<script src="js/main.js"></script>
	<!-- cookie -->
	<script src="js/jquery.cookie.js"></script>
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<script src="js/mySearchBar.js"></script>

	<script>
		var errorMessage = "";
		var successArray = [ false, false, false, false, false ];
		function errorMsg(error, input, bool, num) {

			errorMessage = error;
			successArray[num] = bool;

			if (!bool) {
				$(input).css({
					"border-color" : "red",
					"box-shadow" : "inset 0 0 10px red"
				});
				$("#errorMessage").css("visibility", "visible");
				$("#errorMessage").html(errorMessage);
			} else {
				$(input).css({
					"border-color" : "green",
					"box-shadow" : "inset 0 0 10px green"
				});
				$("#errorMessage").css("visibility", "hidden");
				$("#errorMessage").html(errorMessage);
			}
		}

		$("#email")
				.on(
						"focusout",
						function() {
							function isValidEmailAddress(emailAddress) {
								var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
								return pattern.test(emailAddress);
							}
							;
							if (!isValidEmailAddress($("#email").val())) {
								successArray[0] = false;
								errorMsg("請輸入正確的Email地址", "#email", false, 0);
							} else {

								$
										.ajax({
											url : 'checkEmail',
											type : 'POST',
											data : {
												userId : $("#email").val()
											},
											dataType : 'json', //text,json,xml
											success : function(data) {
												if (data) {
													//帳號符合格式且未重複
													errorMsg("success",
															"#email", true, 0);
												} else {
													errorMsg("帳號重複", "#email",
															false, 0);
												}
											}
										});
							}
						});
		$("#pass1").on("focusout", function() {
			var re = /(?!^[0-9]{6,}$)(?!^[a-zA-Z]{6,}$)^[0-9a-zA-Z]{6,}$/;
			var obj = document.all("pass");
			if (!re.test(obj.value)) {
				errorMsg("密碼格式錯誤", "#pass1", false, 1);
			} else {
				errorMsg("success", "#pass1", true, 1);
			}
		});
		$("#pass2")
				.on(
						"focusout",
						function() {
							if ($("#pass1").val() != $("#pass2").val()
									|| ($("#pass1").val() == "" && $("#pass2")
											.val() == "")) {

								errorMsg("密碼不一致", "#pass2", false, 2);
							} else {
								errorMsg("success", "#pass2", true, 2);
							}
						});
		$("#brithDay").on("focusout", function() {
			if ($("#brithDay").val() == "") {
				errorMsg("請輸入生日", "#brithDay", false, 3);
			} else {
				errorMsg("success", "#brithDay", true, 3);
			}
		});
		$("#gender").on("focusout", function() {
			if ($("#gender").val() == $("#gender option:first").val()) {
				errorMsg("請選擇性別", "#gender", false, 4);
			} else {
				errorMsg("success", "#gender", true, 4);
			}
		});

		$("#submitButton")
				.on(
						"click",
						function(event) {
							event.preventDefault();
							if (successArray[0] && successArray[1]
									&& successArray[2] && successArray[3]
									&& successArray[4]) {
								$("#errorMessage").css("visibility", "hidden");
								$("#errorMessage").html("Success");
								$.ajax({// 											contentType : "application/json",
											url : 'checkEmail',
											type : 'POST',
											data : {
												userId : $("#email").val()
											},
											dataType : 'json', //text,json,xml
											success : function(data) {
												function messageAnimation(
														inputEven, color) {
													// 			alert(color);
													$(inputEven).toggleClass(
															color);
												}
												;
												if (!data) {
													//				alert("失敗");
													messageAnimation("#email");
													$("#errorMessage").css(
															"visibility",
															"visible");
													$("#errorMessage").html(
															"帳號重複");
												} else{
													//				alert("成功");
													$
															.ajax({
																url : 'add',
																type : 'POST',
																data : {
																	userId : $(
																			"#email")
																			.val(),
																	userPassword : $(
																			"#pass1")
																			.val(),
																	nickName : $(
																			"#email")
																			.val()
																			.split(
																					"@")[0],
																	brithDay : $(
																			"#brithDay")
																			.val(),
																	gender : $(
																			"#gender")
																			.val()
																},
																dataType : 'json', //text,json,xml
																success : function() {
																	alert("成功");
																	window.location.href = 'home';
																}
															});
												}
											}
										});

							} else {
								if (!successArray[0]) {
									errorMsg("請輸入正確的Email地址", "#email", false,
											0);
								}
								if (!successArray[1]) {
									errorMsg("密碼格式錯誤", "#pass1", false, 1);
								}
								if (!successArray[2]) {
									errorMsg("密碼不一致", "#pass2", false, 2);
								}
								if (!successArray[3]) {
									errorMsg("請輸入生日", "#brithDay", false, 3);
								}
								if (!successArray[4]) {
									errorMsg("請選擇性別", "#gender", false, 4);
								}
								// && successArray[1] && successArray[2] && successArray[3] && successArray[4]
							}
						});
	</script>
</body>
</html>