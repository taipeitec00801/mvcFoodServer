<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">


<!-- ============================ -->
<link href="<spring:url value="/member/css/bootstrap.min.css"/>"
	rel="stylesheet" type="text/css" />
<link href="<spring:url value="/css/animate.css"/>"
	rel="stylesheet" type="text/css" />
<link href="<spring:url value="/member/css/facebox.css"/>"
	rel="stylesheet" type="text/css" />
<link href="<spring:url value="/member/css/appStyle.2.css"/>"
	rel="stylesheet" type="text/css" />

<!-- =============================================== -->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js" type="text/javascript"></script> -->
<!-- <script src="../js/jquery-1.6.1.min.js"></script> -->
<script src="<spring:url value="/member/js/jquery-3.2.1.min.js"/>"></script>
<script src="<spring:url value="/js/bootstrap.min.js"/>"></script>
<script src="<spring:url value="/member/js/facebox.js"/>"></script>
<script src="<spring:url value="/member/js/wow.min.js"/>"></script>
<script src="<spring:url value="/member/js/memberJS.js" />" type="text/javascript"/></script>

<title>MemberInfo</title>


</head>

<body>
	<!-- header -->
	<!-- navbar -->

	<nav class="navbar navbar-inverse nav-full">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Brand</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Link <span class="sr-only">(current)</span>
				</a></li>
				<li><a href="#">Job</a></li>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"> <i class="icon1"></i>分類
				</a></li>
				<li><a href="#"> <i class="icon1"></i>商城
				</a></li>
				<li><a href="#"> <i class="icon-user"></i> Sing In
				</a></li>
				<li><a href="#">LogIn</a></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>
	<!-- body -->
	<div class="col-xs-6 col-md-3">

		<!-- sidebar -->
		<div class="sidebar test">
			<h2>帳號設定</h2>
			<a href="#" class="sidebarInfo">基本資料</a> <br> <a href="#"
				class="phtoManager">管理相片</a> <br> <a href="#"
				class="sidebarCHPassword">更改密碼</a> <br> <a href="#">歷史留言</a> <br>


		</div>

		<!-- infomation -->
		<div class="infomation test">

			<!-- <div class="taitle test">
					<H2>基本資料</H2>
				</div> -->
			<!-- Account Setting -->
			<!-- <div class="fotoSetting test">
					<div class="fotoCase test">
						<img src="../image/member/liam-stahnke-261528-unsplash.jpg" alt="...">
					</div>
					<div class="fsWord test">
						<a href="#">更改頭像</a>
					</div>
				</div>
				<div class="nameAndEmail test">
					<div class="memberId test">
						<p>帳號</p>


						<input type="text" class="form-control" placeholder="memberId" aria-describedby="sizing-addon1">
					</div>

					<div class="nickName test">
						<p>姓名</p>


						<input type="text" class="form-control" placeholder="nickName" aria-describedby="sizing-addon1">
					</div>
					<div class="birthday test">
						<p>生日</p>


						<input type="text" class="form-control" placeholder="birthday" aria-describedby="sizing-addon1">
					</div>
					<div class="memIntroduce test">
						<p>關於我 :</p>

						<textarea name="talk" cols="25" rows="3"></textarea>
						<hr>

					</div>

				</div>
				<div class="btChange btn-group test" role="group">
					<button type="button" class="btn btn-default">Update Account</button>
				</div> -->

			<!-- change password -->
			<!-- <div class="password test">
					<div class="taitle test">
						<H2>更改密碼</H2>
					</div>
					<div class="locaPassword test">
						<div class="oldPassword test">
							<p>新密碼</p>
							<input type="text" class="form-control" placeholder="Password" aria-describedby="sizing-addon1">
						</div>
						<div class="newPassword test">
							<p>確認密碼</p>
							<input type="text" class="form-control" placeholder="Password" aria-describedby="sizing-addon1">
						</div>
						<div class="btPasswordChange btn-group test" role="group">
							<button type="button" class="btn btn-default">Change Password</button>
						</div>
					</div> -->
			<!-- <div class="fotoSetting test">
						<div class="fotoCase test">
							<img src="../image/member/liam-stahnke-261528-unsplash.jpg" alt="...">
						</div>
						<div class="fsWord test">
							<a href="#">更改頭像</a>
						</div>
					</div> -->


			<!-- Manage photos -->
			<!-- <div class="mgPhotos test">
					<div class="taitle test">
						<H2>管理相片</H2>
					</div>
				</div>
				<div class="imgForm test">

					<div class="col-xs-6 col-md-3 ">
						<a href="../image/background/drew-coffman-94401-unsplash.jpg" class="thumbnail nino-prettyPhoto facebox">
							<img src="../image/background/drew-coffman-94401-unsplash.jpg" class='imageCase' alt="...">
						</a>
					</div>


					<div class="col-xs-6 col-md-3 ">
						<a href="../image/member/pablo-heimplatz-243278-unsplash2.jpg" class="thumbnail nino-prettyPhoto facebox">
							<img src="../image/member/pablo-heimplatz-243278-unsplash2.jpg" class='imageCase' alt="...">
						</a>
					</div>

				</div> -->
			<!-- message history -->
		</div>


	</div>

	</div>
	<!-- <script>
			$(document).ready(function () {
				alert('facebox');
				$.facebox.settings.loadingImage = '../image/loading.gif',
					$.facebox.settings.closeImage =
					'../image/closelabel.png',

					$('.facebox').facebox();

			});
		</script> -->
	<script>
		new WOW().init();
	</script>
	<script>
		
	</script>


</body>

</html>