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

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/animate.css" type="text/css">
<!-- Superfish -->
<link rel="stylesheet" href="css/superfish.css">
<link rel="stylesheet" href="member/css/styles.css">
<link rel="stylesheet" href="css/bootstrap.css" type="text/css">
<!-- Navbar css -->
<link rel="stylesheet" type="text/css" href="css/myNavbarFooter.css"
	charset="UTF-8">
<link rel="stylesheet" href="member/css/appStyle.1.css">






<script src="js/jquery-3.2.1.min.js"></script>
<script src="member/js/wow.min.js"></script>
<script src="js/modernizr-2.6.2.min.js"></script>
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
			<div class="memberBar test">
				<div class="fotoInfo test">

					<div class="fotoCase test wow bounceInDown ">
						<a href="#"> <img src="images/memberFoto.jpg" alt="...">
						</a>

					</div>
					<!-- memberName -->
					<div class="name test  wow bounceInRight">
						<div class="nickName">
							<p>AlexHsu</p>
						</div>
						<div class="btSetting">
							<div class="btn-group" role="group" aria-label="...">
								<button type="button" class="btn btn-default">Setting</button>
							</div>
						</div>
					</div>
				</div>
				<div class="wdLike test">
					收藏區
					<hr>
				</div>

				<!-- like -->
				<div class="container like test">
					<div class="row">
						<div class="col-lg-4 col-md-4">
							<div class="fh5co-blog animate-box">
								<a href="#"> <img class="img-responsive"
									src="images/blog-1.jpg" alt="">
								</a>
								<div class="blog-text">
									<div class="prod-title">
										<h3>
											<a href=""#>45 Minimal Worksspace Rooms for Web Savvys</a>
										</h3>
										<span class="posted_by">Sep. 15th</span> <span class="comment">
											<a href="">21 <i class="icon-bubble2"></i>
										</a>
										</span>
										<p>Far far away, behind the word mountains, far from the
											countries Vokalia and Consonantia, there live the blind
											texts.</p>
										<a href="#" class="btn btn-primary">Read More</a>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4">
							<div class="fh5co-blog animate-box">
								<a href="#"> <img class="img-responsive"
									src="images/blog-2.jpg" alt="">
								</a>
								<div class="blog-text">
									<div class="prod-title">
										<h3>
											<a href=""#>45 Minimal Worksspace Rooms for Web Savvys</a>
										</h3>
										<span class="posted_by">Sep. 15th</span> <span class="comment">
											<a href="">21 <i class="icon-bubble2"></i>
										</a>
										</span>
										<p>Far far away, behind the word mountains, far from the
											countries Vokalia and Consonantia, there live the blind
											texts.</p>
										<a href="#" class="btn btn-primary">Read More</a>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4">
							<div class="fh5co-blog animate-box">
								<a href="#"> <img class="img-responsive"
									src="images/blog-3.jpg" alt="">
								</a>
								<div class="blog-text">
									<div class="prod-title">
										<h3>
											<a href=""#>45 Minimal Worksspace Rooms for Web Savvys</a>
										</h3>
										<span class="posted_by">Sep. 15th</span> <span class="comment">
											<a href="">21 <i class="icon-bubble2"></i>
										</a>
										</span>
										<p>Far far away, behind the word mountains, far from the
											countries Vokalia and Consonantia, there live the blind
											texts.</p>
										<a href="#" class="btn btn-primary">Read More</a>
									</div>
								</div>
							</div>
						</div>

						<div class="col-lg-4 col-md-4">
							<div class="fh5co-blog animate-box">
								<a href="#"> <img class="img-responsive"
									src="images/blog-1.jpg" alt="">
								</a>
								<div class="blog-text">
									<div class="prod-title">
										<h3>
											<a href=""#>45 Minimal Worksspace Rooms for Web Savvys</a>
										</h3>
										<span class="posted_by">Sep. 15th</span> <span class="comment">
											<a href="">21 <i class="icon-bubble2"></i>
										</a>
										</span>
										<p>Far far away, behind the word mountains, far from the
											countries Vokalia and Consonantia, there live the blind
											texts.</p>
										<a href="#" class="btn btn-primary">Read More</a>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4">
							<div class="fh5co-blog animate-box">
								<a href="#"> <img class="img-responsive"
									src="images/blog-2.jpg" alt="">
								</a>
								<div class="blog-text">
									<div class="prod-title">
										<h3>
											<a href=""#>45 Minimal Worksspace Rooms for Web Savvys</a>
										</h3>
										<span class="posted_by">Sep. 15th</span> <span class="comment">
											<a href="">21 <i class="icon-bubble2"></i>
										</a>
										</span>
										<p>Far far away, behind the word mountains, far from the
											countries Vokalia and Consonantia, there live the blind
											texts.</p>
										<a href="#" class="btn btn-primary">Read More</a>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-4 col-md-4">
							<div class="fh5co-blog animate-box">
								<a href="#"> <img class="img-responsive"
									src="images/blog-3.jpg" alt="">
								</a>
								<div class="blog-text">
									<div class="prod-title">
										<h3>
											<a href=""#>45 Minimal Worksspace Rooms for Web Savvys</a>
										</h3>
										<span class="posted_by">Sep. 15th</span> <span class="comment">
											<a href="">21 <i class="icon-bubble2"></i>
										</a>
										</span>
										<p>Far far away, behind the word mountains, far from the
											countries Vokalia and Consonantia, there live the blind
											texts.</p>
										<a href="#" class="btn btn-primary">Read More</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		new WOW().init();
	</script>
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
	<!-- cookie -->
	<script src="js/jquery.cookie.js"></script>
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<script src="js/mySearchBar.js"></script>
</body>

</html>