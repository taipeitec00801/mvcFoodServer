<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Free HTML5 Template by FREEHTML5.CO" />
<meta name="keywords"
	content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
<meta name="author" content="FREEHTML5.CO" />
<title>店家資訊</title>
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
<link rel="stylesheet" href="css/bootstrap.css">
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
<!-- FOR IE9 below -->
<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->
</head>

<body>
	<div id="fh5co-wrapper">
		<div id="fh5co-page">
			<!-- navbar -->
			<div class="myNavbar">
				<%@ include file="navbar.jsp"%>
			</div>
			<!-- StoreInfo -->

			<div id="fh5co-contact" class="animate-box">
				<div class="container">
					<div class="row">
						<div
							class="col-md-6 col-md-offset-3 text-center heading-section animate-box">
							<h3>店家資訊</h3>
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
								Velit est facilis maiores, perspiciatis accusamus asperiores
								sint consequuntur debitis.</p>
						</div>
					</div>
					<form action="#">
						<div class="row">
							<div class="col-md-6">
								<h3 class="section-title">${store.storeName}</h3>
								<p>Far far away, behind the word mountains, far from the
									countries Vokalia and Consonantia, there live the blind texts.</p>
								<ul class="contact-info">
									<li id="address"><i class="icon-location-pin"></i>${store.storeAddress}</li>
									<li><i class="icon-phone2"></i>${store.storePhone}</li>
									<li><i class="icon-back-in-time"></i>${store.serviceHours}</li>
									<li><i class="icon-heart3"></i>${store.storeRecomCount}</li>
								</ul>
							</div>
							<div class="col-md-6">
								<div class="row">
									<!-- StoreMap -->
									<div id="map" class="fh5co-map"></div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>

			<!-- StoreImg -->
			<div class="storeImg">
				<div class="container">
					<div class="row">
						<div
							class="col-md-6 col-md-offset-3 text-center heading-section animate-box">
							<h3>店家照片</h3>
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
								Velit est facilis maiores, perspiciatis accusamus asperiores
								sint consequuntur debitis.</p>
						</div>
					</div>

					<div class="row row-bottom-padded-md">
						<div class="row">
							<c:forEach var="i" begin="0" end="3">
								<div class="col-sm-6 col-md-6">
									<div class="thumbnail one-third animate-box"
										data-animate-effect="fadeIn">
										<img src="<c:url value='/getPicture/${store.storeId}/${i}'/>"
											style="height: 400px; width: 100%" />
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>

			<!-- Store Comment -->
			<div id="fh5co-content-section">
				<div class="container">
					<div class="row">
						<div
							class="col-md-8 col-md-offset-2 text-center heading-section animate-box">
							<h3>店家評論</h3>
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
								Velit est facilis maiores, perspiciatis accusamus asperiores
								sint consequuntur debitis.</p>
						</div>
					</div>
				</div>
				<div class="container">
					<c:forEach var="storeComment" begin="0" end="9"
						items="${storeComments}" varStatus="loop">
						<div class="row">
							<div class="col-md-12">
								<div class="fh5co-testimonial text-center animate-box Comment">
									<figure> <img src="<c:url value='/getMemberImg/${storeComment.commentMId.memberId}'/>" alt="user"> </figure>
									<blockquote>
										<c:forEach begin="${loop.index}" end="${loop.index}"
											items="${contentList}">
											<p class="content">${contentList[loop.index]}</p>
										</c:forEach>
									</blockquote>
									<div class="CommentImg animate-box"
										data-animate-effect="fadeIn">
										<img src="<c:url value='/getCommentImg/${storeComment.commentId}'/>"
										 style="height: 400px; width: 50%" alt="...">
									</div>
								</div>
								<div class="animate-box CommentMessage">
									<div class="media">
										<div class="panel-group customPanel">
											<div class="panel panel-default">
												<div class="panel-heading">
													<h4 class="panel-title">
														<a data-toggle="collapse" href="#collapse${loop.index}">點我看留言</a>
													</h4>
												</div>
												<div id="collapse${loop.index}"
													class="panel-collapse collapse">
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
																		<h4 class="media-heading customheading">User Name</h4>
																		Lorem ipsum dolor sit amet consectetur adipisicing
																		elit. Voluptate minima, voluptatem libero
																		perspiciatis.
																	</div>
																</div>
															</div>
														</li>
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
																		<h4 class="media-heading customheading">User Name</h4>
																		Lorem ipsum dolor sit amet consectetur adipisicing
																		elit. Voluptate minima, voluptatem libero
																		perspiciatis.
																	</div>
																</div>
															</div>
														</li>
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
																		<h4 class="media-heading customheading">User Name</h4>
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
															<a href="#"> <figure class="customfigure"> <img
																	id='userImg' class="fh5co-testimonial media-object"
																	src="images/man.png" alt="..."> </figure>
															</a>
														</div>
														<!-- 留言送出 -->
														<div class="input-group media-body">
															<input id="userMessage" type="text" class="form-control"
																placeholder="留言..."> <span
																class="input-group-btn">
																<button id='sendButton' class="btn btn-default"
																	type="button">送出</button>
															</span>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
					<!-- Button trigger modal -->
					<div class="col-md-4 col-md-offset-4 text-center animate-box readMore indexblog">
						<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#newComment" data-whatever="@getbootstrap">新增評論
						</button>
					</div>
					<!-- Add New Comment -->
					<div class="modal fade indexblog" id="newComment" tabindex="-1"
						role="dialog" aria-labelledby="exampleModalLabel"
						aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">新增評論</h5>
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									<form>
										<div class="form-group">
											<label for="recipient-name" class="col-form-label">Recipient:</label>
											<input type="text" class="form-control" id="recipient-name">
										</div>
										<div class="form-group">
											<label for="message-text" class="col-form-label">Message:</label>
											<textarea class="form-control" id="message-text" style="height: 400px;"></textarea>
										</div>
									</form>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Close</button>
									<button type="button" class="btn btn-primary">Send
										message</button>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>

			<!-- CopyRight -->
			<!-- fh5co-blog-section -->
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
	<!-- Google Map -->
	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js?region=cn&language=zh-TW&key=AIzaSyA9aukSBXl-4rQpunvDAmIO9l7b9porPVI">
		
	</script>
	<script src="js/google_map.js"></script>
	<!-- Main JS -->
	<script src="js/main.js"></script>
	<!-- includeHTML -->
	<script src="https://www.w3schools.com/lib/w3.js"></script>
	<script>
		w3.includeHTML();
	</script>
	<script src="other/js/store_Info.js"></script>

	<!-- jQuery -->
	<script type="text/javascript">
	$('#exampleModal').on('show.bs.modal', function(event) {
		var button = $(event.relatedTarget) // Button that triggered the modal
		var recipient = button.data('whatever') // Extract info from data-* attributes
		// If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		// Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		var modal = $(this)
		modal.find('.modal-title').text('New message to ' + recipient)
		modal.find('.modal-body input').val(recipient)
	})
	</script>

</body>

</html>