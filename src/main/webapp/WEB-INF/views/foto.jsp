<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
			<div class="allTX test">
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
					<!-- Manage photos -->
					<div class="mgPhotos test">
						<div class="taitle test">
							<H2>管理相片</H2>
						</div>
					</div>
					<div class="imgForm test">
						<c:forEach var='product111' items='${Store}'>
							<c:forTokens items="${product111.storePicture}" delims=","
								var="Picture">
								<div class="col-xs-6 col-md-3 ">
									<a href="<c:url value='/pictures/${Picture}.jpg' />"
										class="thumbnail nino-prettyPhoto facebox"> <img
										src="<c:url value='/pictures/${Picture}.jpg' />"
										id='imageCase' alt="...">
									</a>
								</div>

							</c:forTokens>
						</c:forEach>
					</div>

					<!-- infomation -->
				</div>

			</div>
			</div>
		</div>
	</div>
	<script>
		new WOW().init();

		$.facebox.settings.loadingImage = 'images/member/loading.gif',
				$.facebox.settings.closeImage = 'images/member/closelabel.png',

				$('.facebox').facebox();
		$('.thumbnail')
				.on(
						"click",
						function() {
							var imgSrc = $(this).children().attr("src");
							var thisImg = $(this).parent('div');
							alert(imgSrc);
							$('.popup')
									.html(
											'<img src="images/arrow_left.gif" id="leftDiv" alt="...">'
													+ '<img src="images/arrow_right.gif" id="rightDiv" alt="...">'
													+ '<div class="content">'
													// 					+ '<img src="'+imgSrc+'" id="imgSrc" alt="...">'
													+ '</div>');
							$(".content").on(
									"click",
									function() {
										if ($(thisImg).is("div")) {
											alert('true1');
											nextImg = $(thisImg).next().find(
													'img').attr("src");
											alert(nextImg);
											thisImg = $(thisImg).next();
											$(".content").find('img').attr(
													"src", nextImg);
										} else {
											alert("END");
										}

									});
							$("#rightDiv").click(
									function() {
										// alert('true2');
										if ($(thisImg).is("div")) {
											// alert('true3');
											nextImg = $(thisImg).next().find(
													'img').attr("src");
											if (nextImg != undefined) {
												// alert(nextImg);
												thisImg = $(thisImg).next();
												$(".content").find('img').attr(
														"src", nextImg);
											} else {
												// alert(nextImg);
												$('.facebox').trigger(
														'close.facebox');
											}

										}

									});
							$("#leftDiv").on(
									"click",
									function() {
										// alert('true4');
										if ($(thisImg).is("div")) {
											// alert('true5');
											nextImg = $(thisImg).prev().find(
													'img').attr("src");

											if (nextImg != undefined) {
												// alert(nextImg);
												thisImg = $(thisImg).prev();
												$(".content").find('img').attr(
														"src", nextImg);
											} else {
												// alert(nextImg);
												$('.facebox').trigger(
														'close.facebox');
											}

										}

									});
						});
	</script>



</body>

</html>