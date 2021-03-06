$(document).ready(function() {
	$('#triggerUpload').click(function() {
		uploader.uploadStoredFiles();
	});

	var uploader = new qq.FileUploader({
		// pass the dom node (ex. $(selector)[0] for jQuery users.groovy)
		element : document.getElementById('file-uploader'),
		multiple : true,
		maxConnections : 3,
		autoUpload : false,
		forceMultipart : true,
		uploadButtonText : "Select Files",
		// path to server-side upload script
		action : PROJECT_WEBROOT + 'album/jsupload/',
		allowedExtensions : [ "jpg", "png", "gif", "zip" ],
		onComplete : function(id, fileName, responseJSON){},
		onSubmit: function() {
	        uploader.setParams({
	            albumid: $('#albumId').val()
	        });
	    }

	});
	
});
