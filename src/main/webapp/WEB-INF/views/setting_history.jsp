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
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
<!-- Superfish -->
<link rel="stylesheet" href="css/superfish.css">
<link rel="stylesheet" href="css/style.css">

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
					<a href="memInfo" class="sidebarInfo">基本資料</a> <br> <a
						href="foto" class="phtoManager">管理相片</a> <br> <a
						href="password" class="sidebarCHPassword">更改密碼</a> <br> <a
						href="history">歷史留言</a> <br>
				</div>

				<!-- infomation -->
				<div class="infomation test">

					<div class="taitle test">
						<H2>歷史留言</H2>
					</div>

					<div id="fh5co-content-section test">

						<div class="container test">
							<div class="row">
								<div class="col-md-12">

									<div class="animate-box CommentMessage" style="width: 80%;">
										<div class="media">
											<div class="panel-group customPanel">
												<div class="panel panel-default">
													<div class="panel-heading">
														<h4 class="panel-title" style="height: 50px;">
															<a data-toggle="collapse" href="#collapse1"
																style="line-height: 50px;">點我看留言</a>
														</h4>
													</div>
													<div id="collapse1" class="panel-collapse collapse">
														<ul id='commMessage' class="list-group">
															<li class="list-group-item">
																<div class="animate-box CommentMessage">
																	<div class="media">
																		<div class="media-left">
																			<a href="#"> <figure class="customfigure">
																				<img class="fh5co-testimonial media-object"
																					src="images/man.png" alt="..."> </figure>
																			</a>
																		</div>
																		<div class="media-body">
																			<h4 class="media-heading customheading">User
																				Name</h4>
																			Lorem ipsum dolor sit amet consectetur adipisicing
																			elit. Voluptate minima, voluptatem libero
																			perspiciatis.
																		</div>
																	</div>
																</div>
															</li>
														</ul>
														<div class="panel-footer">
															<!-- User Icon -->
															<div class="media-left">
																<a href="#"> <figure class="customfigure">
																	<img id='userImg'
																		class="fh5co-testimonial media-object"
																		src="images/man.png" alt="..."> </figure>
																</a>
															</div>
															<!-- 留言送出 -->
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>

				</div>

			</div>


		</div>

	</div>
	<div></div>

	<div class="test" style="clear: both;">
		<%@ include file="footer.jsp"%>
	</div>
	<!-- =============================================== -->
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="member/js/wow.min.js"></script>
	<script src="member/js/facebox.js"></script>
	<script src="member/js/memberJS.js"></script>




</body>

</html>