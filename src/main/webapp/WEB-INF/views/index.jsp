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
<title>食在好評 &mdash; 100% Free Fully Responsive HTML5 Template by
	FREEHTML5.co</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Free HTML5 Template by FREEHTML5.CO" />
<meta name="keywords"
	content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
<meta name="author" content="FREEHTML5.CO" />

<!-- <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'> -->

<!-- Animate.css -->
<link rel="stylesheet" type="text/css" href="css/animate.css">
<!-- Icomoon Icon Fonts-->
<link rel="stylesheet" type="text/css" href="css/icomoon.css">
<!-- Bootstrap  -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<!-- Superfish -->
<link rel="stylesheet" type="text/css" href="css/superfish.css">

<link rel="stylesheet" type="text/css" href="css/style.css">
<!-- 自訂義 -->
<link rel="stylesheet" type="text/css" href="other/css/indexStyle.css"
	charset="UTF-8">
<!-- Navbar css -->
<link rel="stylesheet" type="text/css" href="css/myNavbarFooter.css"
	charset="UTF-8">

</head>
<body>
	<div id="fh5co-wrapper">
		<div id="fh5co-page">

			<!-- navbar -->
			<div class="myNavbar">
				<%@ include file="navbar.jsp"%>
			</div>

			<!-- Slider -->
			<!-- slider -->
			<div class="slider">
				<div id="carousel-example-generic" class="carousel slide"
					data-ride="carousel">
					<!-- Indicators -->
					<ol class="carousel-indicators">
						<li data-target="#carousel-example-generic" data-slide-to="0"
							class="active"></li>
						<li data-target="#carousel-example-generic" data-slide-to="1"></li>
						<li data-target="#carousel-example-generic" data-slide-to="2"></li>
						<li data-target="#carousel-example-generic" data-slide-to="3"></li>
						<li data-target="#carousel-example-generic" data-slide-to="4"></li>
					</ol>

					<!-- Wrapper for slides -->
					<div class="carousel-inner headSize" role="listbox">
						<div class="item active">
							<img src="images/indexPrettyPhoto01.jpg">

						</div>
						<div class="item">
							<img src="images/indexPrettyPhoto02.jpg">

						</div>
						<div class="item">
							<img src="images/indexPrettyPhoto03.jpg">

						</div>
						<div class="item">
							<img src="images/indexPrettyPhoto04.jpg">

						</div>
						<div class="item">
							<img src="images/indexPrettyPhoto05.jpg">

						</div>
					</div>

					<!-- Controls -->
					<a class="left carousel-control" href="#carousel-example-generic"
						role="button" data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a> <a class="right carousel-control" href="#carousel-example-generic"
						role="button" data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
			</div>

			<!-- 為您推薦 -->
			<div id="fh5co-content-section" class="section-gray">
				<div class="container storeContent">
					<div class="row">
						<div
							class="col-md-8 col-md-offset-2 text-center heading-section animate-box">
							<h3>為您推薦</h3>
							<p>立即找到最夯的打卡名店</p>
						</div>
					</div>
				</div>
				<div class="container storeContent">
					<div class="row">
						<div class="row color-3 animate-box">
							<c:forEach var="store" items="${stores}" varStatus="loop">
								<div class="col-sm-6 col-md-4 bottomPlace">
									<div class="thumbnail">
										
										<img src="<c:url value='/getOnePicture/${store.storeId}'/>"
											style="height: 350px; width: 100%" />
										<div class="caption">
											<h4>${store.storeName}</h4>
											<span>地址：</span><span>${store.storeAddress}</span><br> <span>電話：</span><span>${store.storePhone}</span><br>
											<div class="captionBottom">
												<a
													href="<spring:url value='store_Info?storeId=${store.storeId}' />"
													class="btn btn-primary" role="button">Read More 
												</a> 
												<span
													class="comment" id="${store.storeId}"> ${store.storeRecomCount} 
												</span>
												<i id="love${loop.index}" class="icon-heart4"></i>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>

			<!-- 美食分類 -->
			<div id="fh5co-portfolio">
				<div class="container">

					<div class="row">
						<div
							class="col-md-6 col-md-offset-3 text-center heading-section animate-box">
							<h3>美食分類</h3>
							<p>為您精挑細選的各種美食</p>
						</div>
					</div>

					<div class="row row-bottom-padded-md">
						<div class="col-md-12">
							<ul id="fh5co-portfolio-list" class="storeSortList">
								<li class="one-third animate-box" data-animate-effect="fadeIn"
									style="background-image: url(images/chineseStyle.jpg);"><a
									href="<spring:url value='sorts?sortNo=0&pages=1' />">
										<div class="case-studies-summary">
											<h2>中式</h2>
										</div>
								</a></li>
								<li class="one-third animate-box" data-animate-effect="fadeIn"
									style="background-image: url(images/westernStyle.jpg);"><a
									href="<spring:url value='sorts?sortNo=1&pages=1' />">
										<div class="case-studies-summary">
											<h2>西式</h2>
										</div>
								</a></li>
								<li class="one-third animate-box" data-animate-effect="fadeIn"
									style="background-image: url(images/koreanStyle.jpg);"><a
									href="<spring:url value='sorts?sortNo=3&pages=1' />">
										<div class="case-studies-summary">
											<h2>韓式</h2>
										</div>
								</a></li>
								<li class="two-third animate-box" data-animate-effect="fadeIn"
									style="background-image: url(images/japaneseStyle.jpg);">
									<a href="<spring:url value='sorts?sortNo=2&pages=1' />">
										<div class="case-studies-summary">
											<h2>日式</h2>
										</div>
								</a>
								</li>

								<li class="one-third animate-box" data-animate-effect="fadeIn"
									style="background-image: url(images/thaiStyle.jpg);"><a
									href="<spring:url value='sorts?sortNo=4&pages=1' />">
										<div class="case-studies-summary">
											<h2>泰式</h2>
										</div>
								</a></li>
								<li class="one-third animate-box" data-animate-effect="fadeIn"
									style="background-image: url(images/hongKongStyle.jpg);">
									<a href="<spring:url value='sorts?sortNo=5&pages=1' />">
										<div class="case-studies-summary">
											<h2>港式</h2>
										</div>
								</a>
								</li>
								<li class="one-third animate-box" data-animate-effect="fadeIn"
									style="background-image: url(images/snack.jpg);"><a
									href="<spring:url value='sorts?sortNo=6&pages=1' />">
										<div class="case-studies-summary">
											<h2>小吃</h2>
										</div>
								</a></li>
								<li class="one-third animate-box" data-animate-effect="fadeIn"
									style="background-image: url(images/iceProducts.jpg);"><a
									href="<spring:url value='sorts?sortNo=8&pages=1' />">
										<div class="case-studies-summary">
											<h2>冰品</h2>
										</div>
								</a></li>
								<li class="two-third animate-box" data-animate-effect="fadeIn"
									style="background-image: url(images//dessertDrink.jpg);">
									<a href="<spring:url value='sorts?sortNo=7&pages=1' />">
										<div class="case-studies-summary">
											<h2>
												甜點 <span>&</span> 飲品
											</h2>
										</div>
								</a>
								</li>
								<li class="one-third animate-box" data-animate-effect="fadeIn"
									style="background-image: url(images/otherStyle.jpg);"><a
									href="<spring:url value='sorts?sortNo=9&pages=1' />">
										<div class="case-studies-summary">
											<h2>其他</h2>
										</div>
								</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<!-- 熱門評論 -->
			<div id="fh5co-blog-section" class="section-gray">
				<div class="container">
					<div class="row">
						<div
							class="col-md-8 col-md-offset-2 text-center heading-section animate-box">
							<h3>熱門評論</h3>
							<p>來看看那些美食評論最受關注</p>
						</div>
					</div>
				</div>
				<div class="container">
					<div class="row row-bottom-padded-md">
						<c:forEach var="storeComment" begin="0" end="3"
							items="${storeComments}" varStatus="loop">
							<div class="col-lg-4 col-md-4">
								<div class="fh5co-blog animate-box">
									<a href="#"> 
									<img class="img-responsive" style="height:240px"
										src="<c:url value='/getOnePicture/${storeComment.commentSId.storeId}'/>">			
									</a>
									<div class="blog indexblog">
										<div class="prod-title">
											<h3>
												<a>${storeComment.commentMId.nickname}</a>
											</h3>
											<span class="posted_by">${storeComment.commentDate}</span> <span
												class="comment"> 
											<a> <i class="icon-heart3"></i>
												${storeComment.commentRecomCount}
											</a>
											</span>
											<c:forEach begin="${loop.index}" end="${loop.index}"
												items="${contentList}">
												<p class="content">${contentList[loop.index]}</p>
											</c:forEach>
											<a href="<spring:url value='store_Info?storeId=${storeComment.commentSId.storeId}' />" 
												class="btn btn-primary">Read More</a>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
			<!-- fh5co-blog-section -->
			<!-- footer -->
			<%@ include file="footer.jsp"%>


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
	<!-- Modernizr JS -->
	<script src="js/modernizr-2.6.2.min.js"></script>
	<!-- Main JS -->
	<script src="js/main.js"></script>
	<!-- cookie -->
	<script src="js/jquery.cookie.js"></script>
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<script src="js/mySearchBar.js"></script>
	
	   <!-- jQuery -->
    <script type="text/javascript">
    	$(document).ready(function(){
    		storeId = "";
    		userid="";
    		//check Login
    		$.ajax({
        			url : '/mvcFoodServer/member9487/testLogin88',
        			type : 'POST',
        			data : {},
        			dataType : 'json', //text,json,xml
        			success : function(date) {
        				userid = date.memberId;
        			},		
        		});
    		
    		var len = 80;
    		var datelen=10;
    		$(".content").each(function(i){
    			if($(this).text().length > len) {
    				var text = $(this).text().substring(0,len-1)+".....";
    				$(this).text(text);
            	}
    		}); 
    		
    		$(".posted_by").each(function(j){
    			if($(this).text().length > datelen) {
    				var text = $(this).text().substring(0,datelen);
    				$(this).text(text);
            	}
    		});
    		
    		//更新讚數
    		$(".icon-heart4").click(function(){
    			$(this).css("color","red");
    			var num = $(this).prev().text();
    		    iNum = parseInt(num)+1;
    			$(this).prev().text(iNum);
    			storeId = $(this).prev().attr("id");
    			//alert(storeId+"|"+iNum+"|"+userid);
    			//$.ajax({
        		//	url : '/mvcFoodServer/member9487/updateStoreRecom',
        		//	type : 'POST',
        		//	data : {stRecomMId : userid,
        		//			stRecomSId : storeId,
        		//			stRecomYN : iNum},
        		//	dataType : 'json', //text,json,xml
 		//
        	//	});
    		});
    		
    		
    				
    	});
    </script>

</body>

</html>