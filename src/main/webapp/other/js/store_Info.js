$(document).ready(function(){
	userNickName = "";
	userid="";
	comMid="";
		
	$.ajax({
		url : '/mvcFoodServer/member9487/testLogin88',
		type : 'POST',
		data : {},
		dataType : 'json', //text,json,xml
		success : function(date) {
			userNickName = date.nickname;
			userid = date.memberId;
		//	$(".nickName p").text(date.nickname);			
				$.ajax({
				url : '/mvcFoodServer/getMemberImg',
				type : 'POST',
				data : {
					userId : date.userAccount
				},
				dataType : 'json',
				success : function(date) {
					var a = 'data:image/jpeg;base64,' + date;
					$(".userImg").attr("src" , a);
	
				}
			});
		},
		error : function(data) {
			var ee = $(".LoginMessage");
			$(ee).css("display","none");
		}
		
	});
	
	
	
	$("a").click(function(){
		comMid = $(this).attr('id');
	 	//alert(comMid);
	});
	

         $("button").click(function(){
        	 var num = $(this).attr('id').substring(10);
        	 //alert(num);
        	 var commid="#commMessage"+num;
             mesid ="#userMessage"+num;
             message = $(mesid).val();
             var userImg = $('.userImg').attr('src');
             //alert(commid+"||"+mesid);
             
             $(commid).append('<li class="list-group-item">' +
                     '<div class="animate-box CommentMessage fadeInUp animated">'+
                         '<div class="media">' +
                             '<div class="media-left">' +
                                 '<a href="#">'+
                                     '<figure class="customfigure">'+
                                         '<img class="fh5co-testimonial media-object" src="'+userImg+'" alt="...">'+
                                     '</figure>'+
                                 '</a>'+
                             '</div>'+
                             '<div class="media-body">'+
                                 '<h4 class="media-heading customheading" id="userName"></h4>'+
                                         message+
                             '</div>'+
                         '</div>'+
                     '</div>'+
                 '</li>');
             $("#userName").text(userNickName);
             $("#userName").attr("id" , "#userName" + num);
         	$.ajax({
        		url : '/mvcFoodServer/member9487/sendMessage',
        		type : 'POST',
        		data : {commMsg : message,
        			commId : comMid,
        			commMid : userid
        			},
        		dataType : 'json', //text,json,xml
				success : function() {
					//alert(mesid);
					$(mesid).val("");
	
				},error : function() {
					$(mesid).val("");
				}
        		
        	});
             
             
         });
     
     

});