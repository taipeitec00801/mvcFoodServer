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

<title>MemberInfo</title>
<!-- Facebook and Twitter integration -->
<meta property="og:title" content="" />
<meta property="og:image" content="" />
<meta property="og:url" content="" />
<meta property="og:site_name" content="" />
<meta property="og:description" content="" />
<meta name="twitter:title" content="" />
<meta name="twitter:image" content="" />
<meta name="twitter:url" content="" />
<meta name="twitter:card" content="" />
<link
	href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700,300'
	rel='stylesheet' type='text/css'>
<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
<link rel="shortcut icon" href="favicon.ico">
<!-- <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'> -->
<!-- Animate.css -->
<link rel="stylesheet" href="css/animate.css">
<!-- Icomoon Icon Fonts-->
<link rel="stylesheet" href="css/icomoon.css">
<!-- Bootstrap  -->
<!-- <link rel="stylesheet" href="css/bootstrap.css"> -->
<!-- ============================ -->
<link rel="stylesheet" href="css/bootstrap.css"
	type="text/css">
<!-- Superfish -->
<link rel="stylesheet" href="css/superfish.css">
<link rel="stylesheet" href="css/style.css">
<!-- Modernizr JS -->
<script src="js/modernizr-2.6.2.min.js"></script>
<!-- 自訂義 -->
<link rel="stylesheet" href="other/css/indexStyle.css">
<link rel="stylesheet" href="other/css/storeInfo.css">
<!-- Navbar css -->
<link rel="stylesheet" href="css/myNavbarFooter.css" type="text/css">
<link rel="stylesheet" href="member/css/facebox.css" type="text/css">
<link rel="stylesheet" href="member/css/appStyle.2.css" type="text/css">

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
			<div class="allTX test">

				<!-- sidebar -->
				<div class="sidebar test">
					<h2>帳號設定</h2>
					<a href="memInfo" class="sidebarInfo">基本資料</a> <br> 
					<a href="foto" class="phtoManager">管理相片</a> <br> 
					<a href="password" class="sidebarCHPassword">更改密碼</a> <br> 
					<a href="history">歷史留言</a> <br>
				</div>

				<!-- infomation -->
				<div class="infomation test">

					<div class="taitle test">
						<H2>基本資料</H2>
					</div>

				<form:form method='POST' modelAttribute="member" 
				class='form-horizontal' enctype="multipart/form-data">
					<div class="fotoSetting test">
						<div class="fotoCase test">
							<img src="<c:url value='/mbPicture/mbfoto' />"
								alt="...">
						</div>
						<div class="fsWord test">
							<a href="#">更改頭像</a>
							<form:input id="mbImage" path="mbImage" type='file'
							class='form:input-large' />
						</div>
					</div>
					

					<div class="nameAndEmail test">
						<div class="memberId test">
							<p>帳號</p>
							<form:input id="userAccount" path="userAccount" type='text' 
							class="form-control" aria-describedby="sizing-addon1" readonly="readonly"/>
							
						</div>

						<div class="nickName test">
							<p>暱稱</p>
							<form:input id="nickname" path="nickname" type='text'
							class="form-control" aria-describedby="sizing-addon1" />

<!-- 							<input type="text" class="form-control" placeholder="nickName" -->
<!--  								aria-describedby="sizing-addon1" value='${Member.nickname}'> -->
						</div>
						<div class="birthday test">
							<p>生日</p>
							<form:input id="birthday" path="birthday" type='text'
							class="form-control" aria-describedby="sizing-addon1" />

<!-- 							<input type="text" class="form-control" placeholder="birthday" -->
<!-- 								aria-describedby="sizing-addon1" value='${Member.birthday}'> -->
						</div>
						<div class="memIntroduce test">
							<p>關於我 :</p>

							<textarea name="talk" id="textTalk" cols="25" rows="3">
 
								</textarea>
								
							<hr>

							<div class="btChange btn-group test" role="group">
								<button type='submit' class="btn btn-default">Update
									Account</button>
							</div>
						</div>
						</div>
					</form:form>

					</div>
					
				</div>

			
			</div>

		</div>
		<div class="test" style="clear: both;">
				<%@ include file="footer.jsp"%>
				</div>
	
<!-- =============================================== -->
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<!-- cookie -->
	<script src="js/jquery.cookie.js"></script>
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<script src="js/mySearchBar.js"></script>
	<script src="member/js/wow.min.js"></script>
	<script src="member/js/facebox.js"></script>
	 <script src="member/js/wow.min.js"></script>
	<script src="member/js/memberJS.js"></script>


</body>

</html>