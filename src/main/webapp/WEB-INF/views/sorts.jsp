<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge charset=tf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sort &mdash; 100% Free Fully Responsive HTML5 Template by
	FREEHTML5.co</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Free HTML5 Template by FREEHTML5.CO" />
<meta name="keywords"
	content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
<meta name="author" content="FREEHTML5.CO" />

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
<link rel="shortcut icon" href="favicon.ico">

<link
	href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700,300'
	rel='stylesheet' type='text/css'>

<!-- Animate.css -->
<link rel="stylesheet" href="css/animate.css" type="text/css">
<!-- Icomoon Icon Fonts-->
<link rel="stylesheet" href="css/icomoon.css" type="text/css">
<!-- Bootstrap  -->
<link rel="stylesheet" href="css/bootstrap.css" type="text/css">
<!-- Superfish -->
<link rel="stylesheet" href="css/superfish.css" type="text/css">

<link rel="stylesheet" href="css/style.css" type="text/css">
<!-- 自訂義 -->
<link rel="stylesheet" href="other/css/sortStyle.css" type="text/css">
<!-- Navbar css -->
<link rel="stylesheet" href="css/myNavbar.css" type="text/css">

<!-- Modernizr JS -->
<script src="js/modernizr-2.6.2.min.js"></script>


</head>

<body>
	<div id="fh5co-wrapper">
		<div id="fh5co-page">

			<!-- navbar -->
			<div class="myNavbar">
				<%@ include file="navbar.jsp"%>
			</div>

			<!-- <div class="searchBackground"> -->
			<div id="fh5co-blog-section" class="fh5co-section-gray">
				<!-- title -->
				<div class="sortTitle">
					<div class="container">
						<div
							class="col-md-8 col-md-offset-2 text-center heading-section animate-box">
							<h1>各種驚奇美食</h1>
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
								Velit est facilis maiores, perspiciatis accusamus asperiores
								sint consequuntur debitis.</p>
						</div>
					</div>
				</div>

				<!-- sort context -->
				<div class="sortBackground">
					<div class="sortCard">
						<!-- list -->
						<div class="myList">
							<div class="list-group">
								<a href="<spring:url value='sorts?sortNo=-1&pages=1' />" class="list-group-item">不限種類</a> 
								<a href="<spring:url value='sorts?sortNo=0&pages=1' />" class="list-group-item">中式美食</a> 
								<a href="<spring:url value='sorts?sortNo=1&pages=1' />" class="list-group-item">西式美食</a> 
								<a href="<spring:url value='sorts?sortNo=2&pages=1' />" class="list-group-item">日式美食</a> 
								<a href="<spring:url value='sorts?sortNo=3&pages=1' />" class="list-group-item">韓式美食</a> 
								<a href="<spring:url value='sorts?sortNo=4&pages=1' />" class="list-group-item">泰式美食</a> 
								<a href="<spring:url value='sorts?sortNo=5&pages=1' />" class="list-group-item">港式美食</a> 
								<a href="<spring:url value='sorts?sortNo=6&pages=1' />" class="list-group-item">路邊美食</a> 
								<a href="<spring:url value='sorts?sortNo=7&pages=1' />" class="list-group-item">甜點飲品</a> 
								<a href="<spring:url value='sorts?sortNo=8&pages=1' />" class="list-group-item">冰涼滋味</a> 
								<a href="<spring:url value='sorts?sortNo=9&pages=1' />" class="list-group-item">隱藏美食</a>
							</div>
						</div>
						<div class="sortContent">
							<div class="container">

								<c:forEach var="store" begin="0" end="8" items="${stores}">
									<div class="col-lg-4 col-md-6 .col-sm-8 bottomPlace">
										<div class="fh5co-blog animate-box">
											<a href="#"> <img class="img-responsive"
												src="images/036_02.jpg">
											</a>
											<div class="blog-text">
												<div class="prod-title">
													<div class="titleName">
														<h3>${store.storeName}</h3>
													</div>
													<span class="comment"> <span>${store.storeRecomCount}
															<i class="icon-heart3"></i>
													</span>
													</span><br> <span class="posted_by">營業時間 : <br>${store.serviceHours}</span>

													<p>
														地址：${store.storeAddress}<br> 電話：${store.storePhone}
													</p>
													<a href="#" class="btn btn-primary">Read More</a>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>


						<!-- pag -->
						<div class="pags">
							<nav aria-label="Page navigation">
							<ul class="pagination">

								<li><a href="<spring:url value='sorts?pages=1' />" aria-label="Previous"> <span
										aria-hidden="true">&laquo;</span>
								</a></li>
								<c:forEach var="pagesNo" begin="1" end="${totalPages}">
									<li><a href="<spring:url value='sorts?sortNo=${sortNumber}&pages=${pagesNo}' />">${pagesNo}</a></li>
										
								</c:forEach>
								<li><a href="<spring:url value='sorts?pages=${totalPages}' />" aria-label="Next"> <span
										aria-hidden="true">&raquo;</span>
								</a></li>
							</ul>
							</nav>
						</div>
					</div>
				</div>
			</div>

			<footer>
			<div id="footer">
				<div class="container">
					<div class="row">
						<div class="col-md-6 col-md-offset-3 text-center">
							<p class="fh5co-social-icons">
								<a href="#"> <i class="icon-twitter2"></i>
								</a> <a href="#"> <i class="icon-facebook2"></i>
								</a> <a href="#"> <i class="icon-instagram"></i>
								</a> <a href="#"> <i class="icon-dribbble2"></i>
								</a> <a href="#"> <i class="icon-youtube"></i>
								</a>
							</p>
							<p>
								Copyright 2016 Free Html5 <a href="#">Pentagon</a>. All Rights
								Reserved. <br>Made with <i class="icon-heart3"></i> by <a
									href="http://freehtml5.co/" target="_blank">Freehtml5.co</a> /
								Demo Images: <a href="https://unsplash.com/" target="_blank">Unsplash</a>
							</p>
						</div>
					</div>
				</div>
			</div>
			</footer>


		</div>
		<!-- END fh5co-page -->

	</div>
	<!-- END fh5co-wrapper -->

	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script>
	<!-- Stellar -->
	<script src="js/jquery.stellar.min.js"></script>
	<!-- Superfish -->
	<script src="js/hoverIntent.js"></script>
	<script src="js/superfish.js"></script>

	<!-- Main JS (Do not remove) -->
	<script src="js/main.js"></script>

</body>

</html>