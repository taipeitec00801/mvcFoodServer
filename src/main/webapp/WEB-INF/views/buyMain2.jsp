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

<body>
	<!-- header -->
	<!-- navbar -->

	<div id="fh5co-wrapper">
		<div id="fh5co-page" style="margin-left: 15%; margin-right: 15%;">

			<!-- navbar -->
			<div class="myNavbar">
				<%@ include file="navbar.jsp"%>
			</div>
			<!-- 			<span class="cartMain"></span> -->
			<div style="height: 40px;"></div>
			<div>
				<span style="margin-left: 16%;">加入購物車</span> <span
					style="margin-left: 14%;">確認購物車</span> <span
					style="margin-left: 13%;">檢查訂單與送出</span> <span
					style="margin-left: 13%;">訂單完成</span>
				<div class="progress"
					style="width: 800px; margin: 15px auto; text-align: center;">
					<div
						class="progress-bar progress-bar-success progress-bar-striped active"
						aria-valuenow="45" aria-valuemin="0" aria-valuemax="100"
						style="width: 66.6%">
						<span class="sr-only">66.6% Complete (success)</span>
					</div>
				</div>
			</div>
			<div style="height: 50px;"></div>

			<div style="margin-left: 15%; margin-right: 15%">
				<div style="width: 100%; border: 2px solid #ccc;">
					<div style="height: 20px;"></div>
					<div style="font-size: 20px; color: #bb3d00; font-weight: bold;">
						<div
							style="margin-left: 100px; width: 280px; display: inline-block;">商品圖片</div>
						<div style="width: 200px; display: inline-block;">商品名稱</div>
						<div style="width: 150px; display: inline-block;">商品數量</div>
						<div style="display: inline-block;">商品價錢</div>
					</div>
					<hr>
					<c:forEach var="gift" begin="0" end="9" items="${orderList}">
						<table>
							<tr class="cartTr">
								<td><img width='200' height='120'
									src="<c:url value='/getGiftPicture/${gift.giftId}' />"
									style="margin: 30px; border: 1px solid black;" /></td>
								<td
									style="font-weight: bold; color: black; font-size: 25px; text-align: center; width: 350px;">${gift.giftName}</td>
								<td style="width: 150px;"><input type="number" min="1"
									maxlength="2" value="${gift.giftContent}"
									style="width: 50px; padding-left: 10px; margin-right: 10px; border: 0px; margin-left: 20px;"
									class="giftContent" readonly></td>
								<td class="giftPrice" style="width: 75px">${gift.giftPrice}.NT</td>
								</tr>
						</table>
					</c:forEach>
				</div>
				<div
					style="width: 100%; background-color: #ddd; height: 100px; margin-bottom: 50px;">
					<table
						style="display: inline; float: right; color: black; margin-right: 60px; margin-top: 30px;">
						<tr style="font-size: 20px; color: #bb3d00; font-weight: bold;">
							<td>共 <span>${cartCount}</span> 種優惠券
							</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td>總計</td>
							<td>&nbsp;&nbsp;</td>
							<td style="color: red;">$ <span
								style="font-size: 25px; color: red;" class="totalPrice">999</span></td>
						</tr>
					</table>
				</div>
				<div style="margin-bottom: 150px;">
					<button type="button" onclick="goToMain()"
						class="btn btn-default cartBuyyy"
						style="width: 150px; height: 50px; font-size: 18px;">上一步</button>
					<button type="button" onclick="submitCart()"
						class="btn btn-default cartBuyyy"
						style="width: 150px; height: 50px; font-size: 18px; margin-left: 66%; display: inline;">送出訂單</button>
				</div>
			</div>


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
	<script src="/mvcFoodServer/shoppingCart/js/shopping.js"></script>
	<script src="/mvcFoodServer/shoppingCart/js/buyMain.js"></script>
</body>

</html>