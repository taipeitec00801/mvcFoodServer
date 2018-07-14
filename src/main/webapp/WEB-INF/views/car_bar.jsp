<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="display: inline-block; width: 100%;">
	<ul class="sf-menu" id="fh5co-primary-menu" style="float: none;">
		<li style="display: inline-block; width: 0%; margin-left: 85%;">
			<div class="shoppingImg">
				<div style="height: 20px;"></div>
				<img src="/mvcFoodServer/images/shopping-cart.png"
					style="display: inline;"> <a href="#" class="fh5co-sub-ddown"
					style="color: black; width: 10%; display: inline;">0個商品</a>
			</div>
			<ul class="fh5co-sub-menu"
				style="width: 350px; left: 0; margin-left: -180px;">
				<li><from style="color: black;"> <span style="margin-left: 5%;">商品名稱</span> <span
						style="margin-left: 20%;">數量</span> <span
						style="margin-left: 30%;">金額</span>
					<hr style="border: 0; border-top: 1px solid #ccc;">
					<c:forEach var="gift" begin="0" end="4" items="${giftList}">
						<div
							style="margin-left: 10px; width: 150px; display: inline-block;">漢堡王優惠券</div>
						<div style="width: 130px; display: inline-block;">1</div>
						<div style="width: 30px; display: inline-block;">50</div>
						<p></p>
					</c:forEach>
					<div class="captionBottom" style="width: 100px;margin-left: 200px;margin-top: 15px;">
						<a href="<spring:url value='gift_Info?giftId=${gift.giftId}' />"
							class="btn btn-primary" role="button" style="background-color: #ccc;line-height: 20px;">前往結帳</a>
					</div>
					</from></li>
			</ul>
		</li>
	</ul>

	<div>
		<hr
			style="margin-bottom: 30px; margin-top: 15px; width: 100%; left: 0; color: black; border: 0; border-top: 2px solid #ccc;">
	</div>
</div>