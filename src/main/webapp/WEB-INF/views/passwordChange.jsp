<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id"
	content="891658422538-ccj5goer8ah8440aq7f0iq80p43e2l0j.apps.googleusercontent.com">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">


<!-- ============================ -->
<!-- Bootstrap  -->
<link rel="stylesheet" href="css/bootstrap.css" type="text/css">
<!-- Superfish -->
<link rel="stylesheet" href="css/superfish.css" type="text/css">

<link rel="stylesheet" href="member/css/facebox.css" type="text/css">
<link rel="stylesheet" href="member/css/appStyle.2.css" type="text/css">
<!-- Navbar css -->
<link rel="stylesheet" type="text/css" href="css/myNavbarFooter.css"
	charset="UTF-8">

<!-- =============================================== -->
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<!-- cookie -->
<script src="js/jquery.cookie.js"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script src="js/mySearchBar.js"></script>
<script src="member/js/wow.min.js"></script>
<script src="member/js/facebox.js"></script>
<script src="member/js/memberJS.js"></script>
<title>MemberInfo</title>


</head>

<body>
	<!-- header -->
	<!-- navbar -->

	<div id="fh5co-wrapper">
		<div id="fh5co-page">

			<!-- navbar -->
			<div class="myNavbar">
				<%@ include file="navbar.jsp"%>
			</div>
			<!-- body -->
			<div class="col-xs-6 col-md-3">

				<!-- sidebar -->
				<div class="sidebar test">
					<h2>帳號設定</h2>
					<a href="memInfo" class="sidebarInfo">基本資料</a> <br> <a
						href="foto" class="phtoManager">管理相片</a> <br> <a
						href="password" class="sidebarCHPassword">更改密碼</a> <br> <a
						href="history">歷史留言</a> <br>
				</div>

				<!-- infomation -->
				<div class="infomation test">
					<!-- change password -->
					<div class="password test">
						<div class="taitle test">
							<H2>更改密碼</H2>
						</div>
						<!--<form:form method='POST' modelAttribute="Member" class='form-horizontal'>-->
						<form action="password" method="POST"">
							<div class="oldPassword test">
								<div id="locaPassword test">
									<p>舊密碼</p>
									<input type="text" id="opInput" class="form-control"
										placeholder="Password" aria-describedby="sizing-addon1">

								</div>
								<div class="newPassword test">
									<p>新密碼</p>
									<input id="npInput" type="password" name="password"
										value="${param.password}" class='form-control'
										placeholder="Password" aria-describedby="sizing-addon1">
									<small><Font color='red' size="2">${ErrorMsgKey.PasswordEmptyError}</Font></small>

									<!-- 							<input type="text" id="npInput" class="form-control" placeholder="Password" aria-describedby="sizing-addon1"> -->
								</div>
								<div class="newPassword test">
									<p>確認密碼</p>
									<input id="NPagain" type="password" name="password2"
										value="${param.password2}" class="form-control"
										placeholder="Password" aria-describedby="sizing-addon1">
									<small><Font color='red' size="2">${ErrorMsgKey.PasswordEmptyError}</Font></small>
									<!--<input type="text" id="NPagain" class="form-control" placeholder="Password" aria-describedby="sizing-addon1">-->
								</div>
								<div class="btPasswordChange btn-group test" role="group">
									<button type='submit' id="btChangePassword"
										class="btn btn-default">Change Password</button>
								</div>
							</div>
							</form:form>
							<!-- infomation -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		new WOW().init();
	</script>



</body>

</html>