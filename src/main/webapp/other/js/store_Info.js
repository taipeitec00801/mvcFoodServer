$(document).ready(function(){
    $('#sendButton').click(function(){
        var message = $('#userMessage').val();
        var userImg = $('#userImg').attr('src');
      
        $('#commMessage').prepend('<li class="list-group-item">' +
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
                                                '<h4 class="media-heading customheading">User Name</h4>'+
                                                        message+
                                            '</div>'+
                                        '</div>'+
                                    '</div>'+
                                '</li>');
        
        
    });
});