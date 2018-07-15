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
<link rel="stylesheet" href="/mvcFoodServer/css/bootstrap.css"
	type="text/css">
<!-- Superfish -->
<link rel="stylesheet" href="/mvcFoodServer/css/superfish.css"
	type="text/css">

<link rel="stylesheet" href="/mvcFoodServer/css/style.css"
	type="text/css">
<!-- Navbar css -->
<link rel="stylesheet" type="text/css"
	href="/mvcFoodServer/css/myNavbarFooter.css" charset="UTF-8">
<link rel="stylesheet" href="/mvcFoodServer/member/css/appStyle.1.css">

<link rel="stylesheet"
	href="/mvcFoodServer/shoppingCart/css/shopping.css">

<title>MemberInfo</title>

</head>

<body >
	<!-- header -->
	<!-- navbar -->

	<div id="fh5co-wrapper" style="margin-left: 15%; margin-right: 15%;">
		<div id="fh5co-page">

			<!-- navbar -->
			<div class="myNavbar">
				<%@ include file="navbar.jsp"%>
			</div>

			<span class="cartMain"></span>

			<form action="#">
				<div class="row" style="height: 625px;">
					<div class="col-md-6">
						<img src="<c:url value='/getGiftPicture/${gift.giftId}'/>"
							style="height: 350px; width: 100%; border: 1px solid black; border-radius: 5px;" />
					</div>
					<div class="col-md-6" style="padding-right: 0px; margin-top: 5%;">
						<h2 class="section-title"
							style="margin-left: 10%; margin-bottom: 5px; font-weight: bold;">${gift.giftName}</h2>
						<h5 style="margin-left: 10%;" class="giftId">
							<span>商品編號: </span>${gift.giftId}</h5>
						<div style="margin-left: 70%;">
							<span>NT$ </span>
							<h1 style="display: inline; color: red; font-weight: bold;">${gift.giftPrice}</h1>
							<span> 元</span>
						</div>
						<div style="margin-left: 10%;">
							<span>到期時間: </span>${gift.giftDeadline}</div>
						<hr style="margin-left: 10%; margin-top: 5px;">
						<ul class="contact-info"
							style="margin-left: 10%; list-style-type: none; padding: 0px;">
							<li><span>優惠說明: </span>${gift.giftContent}</li>
						</ul>
						<div style="margin-left: 10%; margin-top: 50px;">
							<span> <label class="v_middle">數量</label>&nbsp;&nbsp; <input
								id="buyCount" type="number" min="1" maxlength="2" class="number" 
								style="width: 55px; margin: 0px; padding-left: 6px;" value="1" />
							</span> <a class="btn btn-primary cartAdd" role="button" style="margin-left: 50%;">加入購物車</a>
						</div>

					</div>
				</div>
			</form>
		</div>
	</div>
			<%@ include file="footer.jsp"%>
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
	<script src="/mvcFoodServer/shoppingCart/js/shopping.js"></script>
</body>

</html>