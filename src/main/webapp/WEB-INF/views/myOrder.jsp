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
		<div id="fh5co-page"
			style="margin-left: 15%; margin-right: 15%; margin-bottom: 300px;">

			<!-- navbar -->
			<div class="myNavbar">
				<%@ include file="navbar.jsp"%>
			</div>
			<div style="height: 65px;"></div>
			<ul class="nav nav-tabs" role="tablist" id="myTabs">
				<li role="presentation" class="active"><a href="#home"
					aria-controls="home" role="tab" data-toggle="tab">我的訂單</a></li>
				<li role="presentation"><a href="#profile"
					aria-controls="profile" role="tab" data-toggle="tab">我的禮券</a></li>
			</ul>
			<div style="height: 65px;"></div>
			<div class="tab-content">
				<div role="tabpanel" class="tab-pane active" id="home">
					<table class="table table-bordered"
						style="text-align: center; font-size: 22px;">
						<tr style="background-color: #ffdcb9">
							<td>訂單編號</td>
							<td>訂購日期</td>
							<td>付款方式</td>
							<td>處理進度</td>
							<td>應付金額</td>
						</tr>
						<tr style="line-height: 40px;">
							<td><a class="btn btn-primary btn-lg" data-toggle="modal"
								data-target="#myModal"
								style="background-color: #fff; color: black">${orderMain.orderNo}</a></td>
							<td>${orderMain.orderDate}</td>
							<td>VISA</td>
							<td><a onclick="payToOrder()" class="waitPay">${orderStatus}</a></td>
							<td>${total}</td>
						</tr>
					</table>
					<div class="container">
						<ul class="progressbar">
							<li class="active">收到訂單</li>
							<li class="active">處理訂單</li>
							<li class="waitChangeClass">等待付款</li>
							<li class="waitChangeClass">訂單完成</li>
						</ul>
					</div>

					<div>
						<table class="table" style="text-align: center;">
							<tr style="background-color: #ffdcb9; height: 50px;">
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td
									style="margin: 0 auto; text-align: center; line-height: 50px;">共
									1 筆訂單</td>
							</tr>
						</table>
					</div>

					<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel">
						<div class="modal-dialog" role="document" style="width: 800px;">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">訂單編號 :
										${orderMain.orderNo}</h4>
								</div>
								<div class="modal-body">
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
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div role="tabpanel" class="tab-pane" id="profile">
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
								<td class="giftPrice" style="width: 175px">到期日 : ${gift.giftDeadline}</td>
							</tr>
						</table>
					</c:forEach>
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