<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge charset=tf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Search &mdash; 100% Free Fully Responsive HTML5 Template by FREEHTML5.co
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
    <link rel="stylesheet" href="other/css/searchStyle.css" type="text/css">
    <!-- Navbar css -->
    <link rel="stylesheet" href="css/myNavbarFooter.css" type="text/css">

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
    
    <script src="js/mySearchBar.js"></script>
    
    <!-- jQuery -->
    <script type="text/javascript">
        $(document).ready(function () {
            var strUrl = location.search;
            var getPara;
            var isPages;

            if (strUrl.indexOf("?") != -1) {
                var getSearch = strUrl.split("?");
                getPara = getSearch[1].split("&");
                /* ?pages=isPages */
                isPages = getPara[0].substring(getPara[1].indexOf("=") + 1);
            }
            var page = ".pagesNo_" + isPages;
            $(page).css({
                backgroundColor: '#203a43',
                color: '#fff'
            });

        });	
    </script>
</head>

<body>
    <div id="fh5co-wrapper">
        <div id="fh5co-page">
            <!-- navbar -->
            <div class="myNavbar">
                <%@ include file="navbar.jsp"%>
            </div>
            <!-- title -->
            <div class="searchTitle">
                <div class="container">
                    <div class="col-md-8 col-md-offset-2 text-center heading-section animate-box">
                        <h1>各種驚奇美食</h1>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit est facilis maiores, perspiciatis
                            accusamus asperiores sint consequuntur debitis.</p>
                    </div>
                </div>
            </div>
            <!-- sort context -->
            <div class="searchBackground">
                <div class="searchCard">                    
                    <!-- Content -->
                    <div class="searchContent">
                        <c:forEach var="store" begin="0" end="9" items="${stores}">
                            <div class="col-sm-6 col-md-4 bottomPlace">
                                <div class="thumbnail">
                                    <img src="images/036_02.jpg">
                                    <div class="caption">
                                        <h4>${store.storeName}</h4>
                                        <span>地址：</span><span>${store.storeAddress}</span><br>
                                        <span>電話：</span><span>${store.storePhone}</span><br>
                                        <div class="captionBottom">
                                        <a href="#" class="btn btn-primary" role="button">Read More</a>
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
                                    <a href="<spring:url value='search?pages=1' />" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <c:forEach var="pagesNo" begin="1" end="${totalPages}">
                                    <li>
                                        <a href="<spring:url value='search?pages=${pagesNo}' />" class="pagesNo_${pagesNo}">${pagesNo}</a>
                                    </li>
                                </c:forEach>
                                <li>
                                    <a href="<spring:url value='search?pages=${totalPages}' />" aria-label="Next">
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
</body>

</html>