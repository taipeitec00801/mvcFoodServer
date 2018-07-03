<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">


<!-- ============================ -->
<link rel="stylesheet" href="member/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="css/animate.css" type="text/css">
<link rel="stylesheet" href="member/css/facebox.css" type="text/css">
<!-- Navbar css -->
<link rel="stylesheet" type="text/css" href="css/myNavbar.css"
	charset="UTF-8">
<link rel="stylesheet" href="member/css/appStyle.2.css" type="text/css">


<!-- =============================================== -->
<script src="member/js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
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
					<a href="setting_memInfo" class="sidebarInfo">基本資料</a> <br> 
					<a href="foto" class="phtoManager">管理相片</a> <br> 
					<a href="password" class="sidebarCHPassword">更改密碼</a> <br> 
					<a href="history">歷史留言</a> <br>
				</div>

				<!-- infomation -->
				<div class="infomation test">
					<!-- Manage photos -->
				<div class="mgPhotos test">
					<div class="taitle test">
						<H2>管理相片</H2>
					</div>
				</div>
				<div class="imgForm test">

					<div class="col-xs-6 col-md-3 ">
						<a href="images/member/drew-coffman-94401-unsplash.jpg" class="thumbnail nino-prettyPhoto facebox">
							<img src="images/member/drew-coffman-94401-unsplash.jpg" class='imageCase' alt="...">
						</a>
					</div>


					<div class="col-xs-6 col-md-3 ">
						<a href="images/member/nick-hillier-254650-unsplash.jpg" class="thumbnail nino-prettyPhoto facebox">
							<img src="images/member/nick-hillier-254650-unsplash.jpg" class='imageCase' alt="...">
						</a>
					</div>

				</div> 

				<!-- infomation -->
			</div>

		</div>
	</div>
</div>
	<script>
		new WOW().init();
	</script>



</body>

</html>