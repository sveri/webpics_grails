var rotVal = 0;

$(document).ready(function() {

	Galleria.loadTheme(GALLERIA_CLASSIC_THEME_LINK);

		Galleria.ready(function() {
            var gallery = Galleria.get(0);

            this.bind("thumbnail", function(e) {
                $(e.thumbTarget).rotate(parseInt(e.galleriaData.rotVal));
            });

            this.bind("loadfinish", function(e) {
                // first check if the rotVal of the last image changed
                var lastGalleryData = getLastGalleriaData(gallery);
                var lastRotVal = lastGalleryData.rotVal;
                if(lastGalleryData.photoId != e.galleriaData.photoId && rotVal != lastRotVal){
                    saveRotationStateToDb(lastGalleryData.photoId, rotVal);
                    lastGalleryData.rotVal = rotVal;
                }

                // now set rotVal to the rotation state of the new image
                rotVal = parseInt(e.galleriaData.rotVal);

                if(rotVal != 0){
                    $(e.imageTarget).rotate(rotVal);
                    $(e.thumbTarget).rotate(rotVal);
                }

                storeCurrentImageId(gallery.getIndex());
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
                    $(gallery.getActiveImage()).rotate(rotVal);
	            },
	        	down: function(){
    				rotVal -= 90;
                    $(gallery.getActiveImage()).rotate(rotVal);
	        	}

	        });
	     });

		Galleria.run('#galleria', {
            dataSource: dataGalleria,
            thumbnails: 'lazy',
            extend: function() {
                var gallery = this;
                $('#rotate-right').click(function() {
                    rotVal += 90;
                    $(gallery.getActiveImage()).rotate(rotVal);
                });
                $('#rotate-left').click(function() {
                    rotVal -= 90;
                    $(gallery.getActiveImage()).rotate(rotVal);
                });
                $('#fullscreen').click(function() {
                    gallery.enterFullscreen();
                });
            }
        });



});

function saveRotationStateToDb(photoId, rotVal) {
    $.ajax({
        type: "POST",
        url: SAVE_ROTATION_STATE,
        data: { photoId: photoId, rotVal: rotVal }
    });
}

function storeCurrentImageId(galleriaId) {
    $('body').data('album_lastGalleriaId', galleriaId);
}
function getLastGalleriaData(gallery) {
    return gallery.getData($('body').data('album_lastGalleriaId'));
}