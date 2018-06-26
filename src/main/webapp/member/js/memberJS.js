$(document).ready(function () {
	// alert($('.infomation').children()!='div');
	// 載入會員資料
	if($('.infomation').children()!='div'){
		$('.infomation').html(
			'<div class="taitle test">' +
			'<H2>基本資料</H2>' +
			'</div>' +

			'<div class="fotoSetting test">' +
			'<div class="fotoCase test">' +
			'<img src="../image/member/liam-stahnke-261528-unsplash.jpg" alt="...">' +
			'</div>' +
			'<div class="fsWord test">' +
			'<a href="#">更改頭像</a>' +
			'</div>' +
			'</div>' +
			'<div class="nameAndEmail test">' +
			'<div class="memberId test">' +
			'<p>帳號</p>' +


			'<input type="text" class="form-control"' + 'placeholder="memberId"' + 'aria-describedby="sizing-addon1">' +
			'</div>' +

			'<div class="nickName test">' +
			'<p>姓名</p>' +


			'<input type="text" class="form-control"' + 'placeholder="nickName"' + 'aria-describedby="sizing-addon1">' +
			'</div>' +
			'<div class="birthday test">' +
			'<p>生日</p>' +


			'<input type="text" class="form-control"' + 'placeholder="birthday"' + 'aria-describedby="sizing-addon1">' +
			'</div>' +
			'<div class="memIntroduce test">' +
			'<p>關於我 :</p>' +

			'<textarea name="talk" id="textTalk" cols="25"' + 'rows="3"></textarea>' +
			'<hr>' +

			'</div>' +

			'</div>' +
			'<div class="btChange btn-group test"' + 'role="group">' +
			'<button type="button" class="btn' + 'btn-default">Update Account</button>' +
			'</div>');
	}
	$('.sidebarInfo').click(function () {
		$.ajax({
			url: '/foodGit/_02_login/login.do',
			type: 'POST',
			data: {
				userAccount: 'mark1234549@gmail.com'
			},
			dataType: 'json', //text,json,xml
			success: function (data) {
				alert(data.birthday);
				$('.infomation').html(
					'<div class="taitle test">' +
					'<H2>基本資料</H2>' +
					'</div>' +

					'<div class="fotoSetting test">' +
					'<div class="fotoCase test">' +
					'<img src="../image/member/liam-stahnke-261528-unsplash.jpg" alt="...">' +
					'</div>' +
					'<div class="fsWord test">' +
					'<a href="#">更改頭像</a>' +
					'</div>' +
					'</div>' +
					'<div class="nameAndEmail test">' +
					'<div class="memberId test">' +
					'<p>帳號</p>' +


					'<input type="text" class="form-control"' + 'placeholder="memberId"' + 'aria-describedby="sizing-addon1"  value=' + data.userAccount + '>' +
					'</div>' +

					'<div class="nickName test">' +
					'<p>暱稱</p>' +


					'<input type="text" id="nickName" class="form-control"' + 'placeholder="nickName"' + 'aria-describedby="sizing-addon1" value=' + data.nickName + '>' +
					'</div>' +
					'<div class="birthday test">' +
					'<p>生日</p>' +


					'<input type="text" id="birthday" class="form-control"' + 'placeholder="birthday"' + 'aria-describedby="sizing-addon1" value=' + data.birthday + '>' +
					'</div>' +
					'<div class="memIntroduce test">' +
					'<p>關於我 :</p>' +

					'<textarea id="textTalk" name="talk" cols="25"' + 'rows="3"></textarea>' +
					'<hr>' +

					'</div>' +

					'</div>' +
					'<div class="btChange btn-group test"' + 'role="group">' +
					'<button type="button" class="btn' + 'btn-default">Update Account</button>' +
					'</div>'
				);


				$('.btnbtn-default').click(function () {
					var nickName = $("#nickName").val();
					var birthday = $("#birthday").val();
					$.ajax({
						url: '/foodGit/_02_login/password.do',
						type: 'POST',
						data: {
							nickName: nickName,
							birthday: birthday
							//討論是否必要修改??
						},
						dataType: 'text', //text,json,xml
						success: function (data) {
							alert(data);
							//返回首頁OR顯示訊息??

						}

					});
	
				})

			}
		});
	});

	// 更改密碼
	$('.sidebarCHPassword').click(function () {
		// alert('123');
		// $.getJSON("/Food//MemberServlet", {
		//     userAccount: 'taipeitec00801@gmail.com'
		// }, function (data) {

		$('.infomation').html(
			'<div class="password test">' +
			'<div class="taitle test">' +
			'<H2>更改密碼</H2>' +
			'</div>' +
			'<div class="locaPassword test">' +
			'<div class="oldPassword test">' +
			'<p>舊密碼</p>' +
			'<input type="text" class="opInput form-control" placeholder="Password" aria-describedby="sizing-addon1">' +
			'</div>' +
			'<div class="newPassword test">' +
			'<p>新密碼</p>' +
			'<input type="text"' + 'class="npInput form-control"' + 'placeholder="Password"' + 'aria-describedby="sizing-addon1" >' +
			'</div>' +
			'<div class="newPassword test">' +
			'<p>確認密碼</p>' +
			'<input type="text"' + 'class="NPagain form-control"' + 'placeholder="Password"' + 'aria-describedby="sizing-addon1">' +
			'</div>' +
			'<div class="btPasswordChange btn-group' + 'test" role="group">' +
			'<button type="button" class="btn' + 'btn-default">Change' + 'Password</button>' +
			'</div>' +
			'</div>' +
			'</div>');


		$('.btnbtn-default').click(function () {
			var op = $('.opInput').val();
			var np = $('.npInput').val();
			var reNp = $('.NPagain').val();
			$(".locaPassword").find("input").each(function () {
				$(this).val("");
			});
			if (reNp == np) {
				alert(op + 'AND' + np);
				$.ajax({
					url: '/foodGit/_02_login/password.do',
					type: 'POST',
					data: {
						userAccount: 'hikarumiyasaki@gmail.com',
						oldPassword: op,
						newPassword: np
					},
					dataType: 'text', //text,json,xml
					success: function (data) {
						alert(data);
						//返回首頁OR顯示訊息??

					}

				});
			} else {
				$("p").append("<span style='color:red; font-size: 12px'>密碼錯誤</span>");
				alert("密碼不一樣");
			}
		});
	});


	// 相片管理
	$('body').on('click', '.phtoManager', function () {
		// $.getJSON("/Food//MemberServlet", {
		//     userAccount: 'taipeitec00801@gmail.com'
		// }, function (data) {
		// $('.thumbnail').on('click','.facebox', function () {
		// 	alert('Aa');

		// });
		// $("body").removeData("validator");

		$('.infomation').html(
			'<div class="mgPhotos test">' +
			'<div class="taitle test">' +
			'<H2>管理相片</H2>' +
			'</div>' +
			'</div>' +
			'<div class="imgForm test">' +

			'<div class="col-xs-6 col-md-3 ">' +
			'<a href="../image/background/drew-coffman-94401-unsplash.jpg" class="thumbnail nino-prettyPhoto facebox">' +
			'<img src="../image/background/drew-coffman-94401-unsplash.jpg" class="imageCas" alt="...">' +
			'</a>' +
			'</div>' +


			'<div class="col-xs-6 col-md-3 ">' +
			'<a href="../image/member/pablo-heimplatz-243278-unsplash2.jpg" class="thumbnail nino-prettyPhoto facebox">' +
			'<img src="../image/member/pablo-heimplatz-243278-unsplash2.jpg" class="imageCase" alt="...">' +
			'</a>' +
			'</div>' +

			'</div>'

		);
		alert('facebox');
		$.facebox.settings.loadingImage = '../image/loading.gif',
			$.facebox.settings.closeImage =
			'../image/closelabel.png',

			$('.facebox').facebox();
		$('.facebox').on("click", function () {

			// $.facebox.settings.loadingImage = '../image/loading.gif',
			// 			$.facebox.settings.closeImage =
			// 			'../image/closelabel.png',

			var imgSrc = $(this).children().attr("src");
			var thisImg = $(this).parent('div');
			alert(imgSrc);
			$('.popup').html(
				'<img src="../image/arrow_left.gif" id="leftDiv" alt="...">'
				+ '<img src="../image/arrow_right.gif" id="rightDiv" alt="...">'
				+ '<div class="content">'
				+ '</div>'
			);
			$(".content").on("click", function () {
				if ($(thisImg).is("div")) {
					// alert('true1');
					nextImg = $(thisImg).next().find('img').attr("src");
					// alert(nextImg);
					thisImg = $(thisImg).next();
					$(".content").find('img').attr("src", nextImg);
				} else {
					alert("END");
				}

			});
			$("#rightDiv").click(function () {
				// alert('true2');
				if ($(thisImg).is("div")) {
					// alert('true3');
					nextImg = $(thisImg).next().find('img').attr("src");
					if (nextImg != undefined) {
						// alert(nextImg);
						thisImg = $(thisImg).next();
						$(".content").find('img').attr("src", nextImg);
					} else {
						// alert(nextImg);
						$('.facebox').trigger('close.facebox');
					}

				}

			});
			$("#leftDiv").on("click", function () {
				// alert('true4');
				if ($(thisImg).is("div")) {
					// alert('true5');
					nextImg = $(thisImg).prev().find('img').attr("src");

					if (nextImg != undefined) {
						// alert(nextImg);
						thisImg = $(thisImg).prev();
						$(".content").find('img').attr("src", nextImg);
					} else {
						// alert(nextImg);
						$('.facebox').trigger('close.facebox');
					}

				}


			});
		});

	});
});