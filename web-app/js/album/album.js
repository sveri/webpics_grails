function saveRotationState(photoId, rotVal) {

    $.ajax({
        type: "POST",
        url: SAVE_ROTATION_STATE,
        data: { photoId: photoId, rotVal: rotVal }
    });

}
$(document).ready(function() {

	Galleria.loadTheme(GALLERIA_CLASSIC_THEME_LINK);
	    var rotVal = 0;
		
		Galleria.ready(function() {
		    this.bind("image", function(e) {
                if(rotVal != 0 ) {
                    saveRotationState(e.galleriaData.photoId, rotVal);
                    rotVal = 0;
                }
		    });

            this.lazyLoadChunks( 3 );
			
	        this.attachKeyboard({
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
		
		Galleria.run('#galleria', {
            dataSource: dataGalleria,
            thumbnails: 'lazy'
        });

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