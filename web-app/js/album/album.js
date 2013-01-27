var rotVal = 0;

$(document).ready(function() {

	Galleria.loadTheme(GALLERIA_CLASSIC_THEME_LINK);

		Galleria.ready(function() {
            var gallery = Galleria.get(0);

            this.bind("loadfinish", function(e) {
                // first check if the rotVal of the last image changed
                var lastRotVal = getLastRotVal(gallery);
                if(lastRotVal != undefined && rotVal != lastRotVal){
                    saveRotationStateToDb(getLastImageId(), rotVal);
                    gallery.getData(getLastGalleriaId()).rotVal = rotVal;
                }
//
//                // now set rotVal to the rotation state of the new image
                var rotValPic = parseInt(e.galleriaData.rotVal);
                rotVal = rotValPic;
//
                if(rotValPic != 0){
                    $(e.imageTarget).rotate(rotValPic);
                }

                storeCurrentImageId(e.galleriaData.photoId, gallery.getIndex());
                storeCurrentImageRotVal(e.galleriaData.rotVal);
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
                    gallery.enterFullscreen(); // will go full screen when the #fullscreen element is clicked
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

function storeCurrentImageId(photoId, galleriaId) {
    $('body').data('album_lastImageId', photoId);
    $('body').data('album_lastGalleriaId', galleriaId);
}
function storeCurrentImageRotVal(photoId) {
    $('body').data('album_lastImageRotVal', photoId);
}

function getLastImageId() {
    return $('body').data('album_lastImageId');
}
function getLastRotVal(gallery) {
    return $('body').data('album_lastImageRotVal');
//    return gallery.getData(gallery.getPrev()).rotVal;
}

function getLastGalleriaId() {
    return $('body').data('album_lastGalleriaId');
}