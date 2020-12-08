jQuery(function($) {
  "use strict";
////////////////////////////////////////////////////////
///////////////preloader ///////////////////////////
////////////////////////////////////////////////////////

	jQuery(window).on('load',function(){ // makes sure the whole site is loaded
				jQuery('#status').fadeOut(); // will first fade out the loading animation
				jQuery('#preloader').delay(350).fadeOut('slow'); // will fade out the white DIV that covers the website.
				jQuery('body').delay(350).css({'overflow':'visible'});
	});
////////////////////////////////////////////////////////
///////////////// Off-screen Navigation ///////////////////////////
////////////////////////////////////////////////////////
if($('.navbar-toggle').length > 0){
		
	$('.navbar-toggle').on('click',function(event){
		$(this).toggleClass( "navbar-on" );
	});
	$('.navbar-toggle').on('click',function(event){
      	$(body).toggleClass( "mobi-menu" );
    });
		var menuLeft = document.getElementById( 'cbp-spmenu-s1' ),
		body = document.body;
			showLeft.onclick = function() {
				classie.toggle( this, 'active' );
				classie.toggle( menuLeft, 'cbp-spmenu-open' );
			};
}
////////////////////////////////////////////////////////
///////////////// background ///////////////////////////
////////////////////////////////////////////////////////
jQuery(window).on('load',function(){
	var theWindow        = $(window),
	    $bg              = $("#bg"),
	    aspectRatio      = $bg.width() / $bg.height();

	function resizeBg() {

		if ( (theWindow.width() / theWindow.height()) < aspectRatio ) {
		    $bg
		    	.removeClass()
		    	.addClass('bgheight');
		} else {
		    $bg
		    	.removeClass()
		    	.addClass('bgwidth');
		}

	}

	theWindow.resize(resizeBg).trigger("resize");

});
////////////////////////////////////////////////////////
/////////////// forms ///////////////////////////
////////////////////////////////////////////////////////
if($("#reply-form").length > 0){
jQuery("#reply-form").validate({
         ignore: ":hidden",
         rules: {
             name:{
                minlength: 2,
                maxlength: 30,
                required: true
            },
            email:{
                minlength: 2,
                required: true
            },

			message:{
                minlength: 3,
                maxlength: 300,
                required: true
            }
         },
	});
}
if($("#booking-form").length > 0){	
jQuery("#booking-form").validate({
         ignore: ":hidden",
         rules: {
             fname:{
                required: true
            },
			lname:{
                required: true
            },
            email:{
                minlength: 2,
                required: true
            },

			message:{
                minlength: 3,
                maxlength: 300,
                required: true
            }
         },
	});	
}

});
