<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid navbarSize">

		<div class="navbar-header">

			<a class="navbar-brand" href="/mvcFoodServer/home"> 
				<img alt="Brand" src="/mvcFoodServer/images/Logo.png"> 
				<span>食在好評</span>
			</a>

		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<Form action="<spring:url value='/search?pages=1' />" method="POST">
				<div class="col-lg-4 searchBar">				
					<div class="input-group">
						<input type="text" name="mySearchReq" class="form-control" placeholder="Search..."> <span class="input-group-btn">
								<button class="btn btn-default mySearchBot" type="submit">
									<img src="/mvcFoodServer/images/searchicon.png">
								</button>
						</span>
					</div>
				</div>
			</Form>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<spring:url value='/sorts?sortNo=-1&pages=1' />">分類</a></li>
				<li><a href="/mvcFoodServer/shoppingMain">商城</a></li>

				<li>
					<ul class="sf-menu" id="fh5co-primary-menu">
						<li>
						<a href="/mvcFoodServer/member9487/member_mainPage" class="fh5co-sub-ddown">會員</a>
						
							<ul class="fh5co-sub-menu" id="myMenu">
								<form class="myForm formOut">
									<div class="myLabel" id="rrrrr">
										<label for="inputEmail">Email address</label> <input
											type="email" class="form-control inputMargin inputEmail"
											name="inputEmail">
									</div>
									<div class="myLabel">
										<label for="inputPassword">Password</label> <input
											type="password"
											class="form-control inputMargin inputPassword"
											name="inputPassword">
									</div>
									<div class="submitError">error</div>
									<div class="submitDiv">
										<button type="button" class="btn btn-default myButton logInBt"
											name="logInBt">登入</button>
										<!-- 											<a href="#" id="forgetPass" -->
										<!-- 												style="font-size: 10px; float: right; margin: -5px -25px 0px -47px; height: 15px;">忘記密碼?</a> -->
									</div>
									<div class="reDiv">
										<input type="checkbox" class="rmMe" value="y" checked>
										<label for="rmMe">記住我</label>
									</div>
									<div class="clearFloat"></div>
									<hr class="btnLine">
									<div style="margin-top: 15px">
										<button type="button"
											class="btn btn-default myButton registerBt"
											style="width: 120px; height: 30px; float: left;">註冊</button>
										<div class="g-signin2 googleBtn" data-theme="dark"
											data-onsuccess="onSignIn"
											style="width: 120px; height: 30px; float: right;"></div>
									</div>
								</form>
								<form class="myForm formIn" style="display: none">
									<div class="form-group memberImg">
										<img src="" class="Img">
									</div>
									<div class="form-group memberId">Nick</div>
									<div class="GCdiv">
										<span>
											<button type="button" class="btn btn-default myButton giftBt"
												style="width: 120px;" onclick="goToOrder()">我的訂單</button>
										</span> <span>
											<button type="button"
												class="btn btn-default myButton collectionBt"
												style="width: 120px;" onclick="goToMyGift()">禮卷</button>
										</span>
									</div>
									<hr class="btnLine inBtnLine">
									<div class="submitDiv">
										<button type="button"
											class="btn btn-default myButton settingBt">會員主頁</button>
									</div>
									<div class="submitDiv">
										<button type="button"
											class="btn btn-default myButton logOutBt" name="logOutBt">登出</button>
									</div>
									<div class="clearFloat"></div>
								</form>
							</ul></li>
					</ul>
				</li>



			</ul>
		</div>
	</div>
</nav>
