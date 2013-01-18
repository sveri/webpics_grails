function rotateImage(photoId, rotVal) {

    $.ajax({
        type: "POST",
        url: ROTATE_IMAGE_LINK,
        data: { photoId: photoId, rotVal: rotVal }
    });

}
$(document).ready(function() {

	Galleria.loadTheme(GALLERIA_CLASSIC_THEME_LINK);
	    var rotVal = 0;
		
		Galleria.ready(function() {
		    var gallery = this;
		    
		    this.bind("image", function(e) {
                if(rotVal != 0 ) {
                    rotateImage($('body').data('lastImageId'), rotVal);

                    rotVal = 0;
                    setLastImageId(e);
                }
		    });
			
	        gallery.attachKeyboard({
	            left: function(){
	            	gallery.prev();
	            },
	            right: function() {
	            	gallery.next();
				},
	            up: function(){
    				rotVal += 90;
	            	$('#galleria').find('.galleria-stage').find('.galleria-images').find('.galleria-image').each(function(){
	    				$(this).find('img').rotate(rotVal);
	    			});
	            },
	        	down: function(){
    				rotVal -= 90;
	            	$('#galleria').find('.galleria-stage').find('.galleria-images').find('.galleria-image').each(function(){
	    				$(this).find('img').rotate(rotVal);
    				});
	        	}

	        });
	     });
		
		Galleria.run('#galleria');
		var gallery = Galleria.get(0);

		$('#rotate-right').click(function() {
			rotVal += 90;
			$('#galleria').find('.galleria-stage').find('.galleria-images').find('.galleria-image').each(function(){
				$(this).find('img').rotate(rotVal);
			});
		});
		$('#rotate-left').click(function() {
			rotVal -= 90;
			$('#galleria').find('.galleria-stage').find('.galleria-images').find('.galleria-image').each(function(){
				$(this).find('img').rotate(rotVal);
			});
		});
		
		$('#fullscreen').click(function() {
	        gallery.enterFullscreen(); // will go full screen when the #fullscreen element is clicked
	    });
	
});

function setLastImageId(e) {
    var reg = /\photoid=(.*)&size=/g;
    var res = reg.exec(e.galleriaData.image);
    $('body').data('lastImageId', res[1]);
}