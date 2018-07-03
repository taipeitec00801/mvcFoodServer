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
<link rel="stylesheet" href="member/css/appStyle.2.css" type="text/css">
<!-- Navbar css -->
<link rel="stylesheet" type="text/css" href="css/myNavbar.css"
	charset="UTF-8">


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
			<div id="allTX test">

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

					<div class="taitle test">
						<H2>基本資料</H2>
					</div>
					<!-- Account Setting -->
					<%-- 					<form:form method='POST' modelAttribute="MemberBean" class='form-horizontal'> --%>
					<div class="fotoSetting test">
						<div class="fotoCase test">
							<img src="../image/member/liam-stahnke-261528-unsplash.jpg"
								alt="...">
						</div>
						<div class="fsWord test">
							<a href="#">更改頭像</a>
						</div>
					</div>

					<div class="nameAndEmail test">
						<div class="memberId test">
							<p>帳號</p>
							<input type="text" class="form-control" placeholder="memberId"
								aria-describedby="sizing-addon1" value='${userInfo.userAccount}'
								readonly="readonly" />
						</div>

						<div class="nickName test">
							<p>姓名</p>


							<input type="text" class="form-control" placeholder="nickName"
								aria-describedby="sizing-addon1" value='${userInfo.nickname}'>
						</div>
						<div class="birthday test">
							<p>生日</p>


							<input type="text" class="form-control" placeholder="birthday"
								aria-describedby="sizing-addon1" value='${userInfo.birthday}'>
						</div>
						<div class="memIntroduce test">
							<p>關於我 :</p>

							<textarea name="talk" id="textTalk" cols="25" rows="3">
<%-- 									<form:input id="????" path="????" type='text' class='form:input-large' /> --%>
									<!-- path="" 透過 modelAttribute="" 對應到 "MemberBean" --> 
								</textarea>
							<hr>

							<div class="btChange btn-group test" role="group">
								<button type="button" class="btn btn-default">Update
									Account</button>
							</div>
						</div>


					</div>

					<%-- 					</form:form> --%>
				</div>


			</div>

		</div>
	</div>

	<script>
		new WOW().init();
	</script>



</body>

</html>