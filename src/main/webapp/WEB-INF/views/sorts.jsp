<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge charset=tf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="google-signin-scope" content="profile email">
	<meta name="google-signin-client_id"
	content="891658422538-ccj5goer8ah8440aq7f0iq80p43e2l0j.apps.googleusercontent.com">
    <title>Sort &mdash; 100% Free Fully Responsive HTML5
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Free HTML5 Template by FREEHTML5.CO" />
    <meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
    <meta name="author" content="FREEHTML5.CO" />

    <link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,700,300' rel='stylesheet' type='text/css'>

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
    <link rel="stylesheet" href="css/myNavbarFooter.css" type="text/css">

</head>

<body>
    <div id="fh5co-wrapper">
        <div id="fh5co-page">
            <!-- navbar -->
            <div class="myNavbar">
                <%@ include file="navbar.jsp"%>
            </div>
            <!-- title -->
            <div class="sortTitle">
                <div class="container">
                    <div class="col-md-8 col-md-offset-2 text-center heading-section animate-box">
                        <h1>各種驚奇美食</h1>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit est facilis maiores, perspiciatis
                            accusamus asperiores sint consequuntur debitis.</p>
                    </div>
                </div>
            </div>
            <!-- sort context -->
            <div class="sortBackground">
                <div class="sortCard">
                    <!-- list -->
                    <div class="myList">
                        <div class="list-group">
                            <a href="<spring:url value='sorts?sortNo=-1&pages=1' />" class="list-group-item myItem_-1">不限種類</a>
                            <a href="<spring:url value='sorts?sortNo=0&pages=1' />" class="list-group-item myItem_0">中式美食</a>
                            <a href="<spring:url value='sorts?sortNo=1&pages=1' />" class="list-group-item myItem_1">西式美食</a>
                            <a href="<spring:url value='sorts?sortNo=2&pages=1' />" class="list-group-item myItem_2">日式美食</a>
                            <a href="<spring:url value='sorts?sortNo=3&pages=1' />" class="list-group-item myItem_3">韓式美食</a>
                            <a href="<spring:url value='sorts?sortNo=4&pages=1' />" class="list-group-item myItem_4">泰式美食</a>
                            <a href="<spring:url value='sorts?sortNo=5&pages=1' />" class="list-group-item myItem_5">港式美食</a>
                            <a href="<spring:url value='sorts?sortNo=6&pages=1' />" class="list-group-item myItem_6">路邊美食</a>
                            <a href="<spring:url value='sorts?sortNo=7&pages=1' />" class="list-group-item myItem_7">甜點飲品</a>
                            <a href="<spring:url value='sorts?sortNo=8&pages=1' />" class="list-group-item myItem_8">冰涼滋味</a>
                            <a href="<spring:url value='sorts?sortNo=9&pages=1' />" class="list-group-item myItem_9">其他美食</a>
                        </div>
                    </div>
                    <!-- Content -->
                    <div class="sortContent">
                        <c:forEach var="store" begin="0" end="9" items="${stores}">
                            <div class="col-sm-6 col-md-4 bottomPlace">
                                <div class="thumbnail">
                                    <img src="<c:url value='/getPicture/${store.storeId}/0'/>" style="height: 270px"/>
                                    <div class="caption">
                                        <h4>${store.storeName}</h4>
                                        <span>地址：</span><span>${store.storeAddress}</span><br>
                                        <span>電話：</span><span>${store.storePhone}</span><br>
                                        <div class="captionBottom">
                                        <a href="<spring:url value='store_Info?storeId=${store.storeId}' />" class="btn btn-primary" role="button">Read More</a>
                                        <span class="comment">
                                        ${store.storeRecomCount} <i class="icon-heart4" style="color:red;"></i>
                                        &nbsp;&nbsp;
                                        ${store.storeCommentCount} <i class="icon-bubble2" style="color:#203a43;"></i>
										</span>
                                    </div>
                                    </div>
                                </div>	
                            </div>
                        </c:forEach>
                    </div>
                    <!-- pag -->
                    <div class="pageBox">
                    <span class="pages">
                        <nav aria-label="Page navigation">
                            <ul class="pagination">
                                <li>
                                    <a href="<spring:url value='sorts?sortNo=${sortNumber}&pages=1' />" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <c:forEach var="pagesNo" begin="1" end="${totalPages}">
                                    <li>
                                        <a href="<spring:url value='sorts?sortNo=${sortNumber}&pages=${pagesNo}' />" class="pagesNo_${pagesNo}">${pagesNo}</a>
                                    </li>
                                </c:forEach>
                                <li>
                                    <a href="<spring:url value='sorts?sortNo=${sortNumber}&pages=${totalPages}' />" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </span>
                    </div>
                </div>
            </div>
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
    <!-- Main JS (Do not remove) -->
    <script src="js/main.js"></script>
    <!-- cookie -->
    <script src="js/jquery.cookie.js"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <script src="js/mySearchBar.js"></script>
    
    <!-- jQuery -->
    <script type="text/javascript">
        $(document).ready(function () {
            var strUrl = location.search;
            var getPara;
            var sortNum, isPages;
            if (strUrl.indexOf("?") != -1) {
                var getSearch = strUrl.split("?");
                getPara = getSearch[1].split("&");
                /* ?sortNo=sortNum&pages=isPages */
                sortNum = getPara[0].substring(getPara[0].indexOf("=") + 1);
                isPages = getPara[1].substring(getPara[1].indexOf("=") + 1);
            }
            var page = ".pagesNo_" + isPages;
            $(page).css({
                backgroundColor: '#203a43',
                color: '#fff'
            });

            var sortNumer = ".myItem_" + sortNum;
            $(sortNumer).css({
                backgroundColor: '#203a43',
                color: '#fff'
            });
            
        });	
    </script>
</body>

</html>