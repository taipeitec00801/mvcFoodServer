<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid navbarSize">

		<div class="navbar-header">

			<a class="navbar-brand" href="home"> 
			<img alt="Brand" src="images/Logo.png">
			<span>食在好評</span>
			</a>

		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<div class="col-lg-4 searchBar">
				<div class="input-group">
					<input type="search" class="form-control mySearchReq" placeholder="Search...">
					<span class="input-group-btn">
						<a href="search">
						<button class="btn btn-default mySearchBot" type="button" ><img src="images/searchicon.png"></button>
						</a>
					</span>
				</div>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<spring:url value='sorts?sortNo=-1&pages=1' />">分類</a></li>
				<li><a href="#">商城</a></li>
				<li><a href="member_mainPage">會員</a></li>
			</ul>
		</div>
	</div>
</nav>
