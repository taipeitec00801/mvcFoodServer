<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="google-signin-scope" content="profile email">
<meta name="google-signin-client_id"
	content="891658422538-ccj5goer8ah8440aq7f0iq80p43e2l0j.apps.googleusercontent.com">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<!-- Animate.css -->
<link rel="stylesheet" href="/mvcFoodServer/css/animate.css"
	type="text/css">
<!-- Icomoon Icon Fonts-->
<link rel="stylesheet" href="/mvcFoodServer/css/icomoon.css"
	type="text/css">
<!-- Bootstrap  -->
<link rel="stylesheet" href="/mvcFoodServer/css/bootstrap.css" type="text/css">
<!-- Superfish -->
<link rel="stylesheet" href="/mvcFoodServer/css/superfish.css"
	type="text/css">

<link rel="stylesheet" href="/mvcFoodServer/css/style.css"
	type="text/css">
<!-- Navbar css -->
<link rel="stylesheet" type="text/css"
	href="/mvcFoodServer/css/myNavbarFooter.css" charset="UTF-8">
<link rel="stylesheet" href="/mvcFoodServer/member/css/appStyle.2.css">


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

			<!--fotoInfo -->

			<div class="fotoInfo test">

				<div class="fotoCase test wow bounceInDown"
					style="padding-top: 70px; margin: 0px 500px;">
					<a href="#"> <img style=" width: 200px; height: 200px;" class="memberImg">
					</a>
				</div>
				<!-- memberName -->
				<div class="meName test">
					<div class="nickName" style="margin-left: 750px;float: left;height: 80px;">
						<p style="font-size: 100px; font-family: tahoma;">memberName</p>
					</div>
					<div class="btSetting">
						<div class="btn-group" role="group" aria-label="...">
							<button type="button" class="btn btn-default"
								style="float: left; margin: 80px;"
								value="設定" onclick="location.href='memInfo'">設定</button>
						</div>
					</div>
				</div>

			</div>
			<div class="wdLike test" style="clear: both;">
				<h3 style="padding-left: 100px;">收藏區</h3>
				<hr>
			</div>
		
			<!-- like -->
			<div class="memberBackground">
                <div class="memberCard">
			<div class="container like test">
				<div class="row">
					<div class="row color-3 animate-box">
						<c:forEach var="store" begin="0" end="9" items="${stores}">
							<div class="col-sm-6 col-md-4 bottomPlace">
								<div class="thumbnail">
									<img src="<c:url value='/getOnePicture/${store.storeId}'/>"
										style="height: 350px; width: 100%" />
									<div class="caption">
										<h4>${store.storeName}</h4>
										<span>地址：</span><span>${store.storeAddress}</span><br> <span>電話：</span><span>${store.storePhone}</span><br>
										<div class="captionBottom">
											<a
												href="<spring:url value='/store_Info?storeId=${store.storeId}' />"
												class="btn btn-primary" role="button">Read More </a> <span
												class="comment"> ${store.storeRecomCount} <i
												class="icon-heart4" style="color: red;"></i>
											</span>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
</div></div>
		</div>
		<%@ include file="footer.jsp"%>
	</div>
	<script>
		new WOW().init();
	</script>

	<!-- jQuery -->
	<script src="/mvcFoodServer/js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="/mvcFoodServer/js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="/mvcFoodServer/js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="/mvcFoodServer/js/jquery.waypoints.min.js"></script>
	<!-- Stellar -->
	<script src="/mvcFoodServer/js/jquery.stellar.min.js"></script>

	<script src="/mvcFoodServer/member/js/wow.min.js"></script>
	<!-- Superfish -->
	<script src="/mvcFoodServer/js/hoverIntent.js"></script>
	<script src="/mvcFoodServer/js/superfish.js"></script>
	<!-- Modernizr JS -->
	<script src="/mvcFoodServer/js/modernizr-2.6.2.min.js"></script>
	<!-- Main JS (Do not remove) -->
	<script src="/mvcFoodServer/js/main.js"></script>
	<!-- cookie -->
	<script src="/mvcFoodServer/js/jquery.cookie.js"></script>
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<script src="/mvcFoodServer/member/js/memberMainPage.js"></script>
	<script src="/mvcFoodServer/js/mySearchBar.js"></script>
</body>

</html>