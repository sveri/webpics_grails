<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="pix.navigation.albums" /></title>
	    <r:require modules="album_upload"/>
	</head>
	<body>
		<tmpl:albumLeftNav album="${album}" /> 

		<input type="hidden" value="${album.id}" id="albumId"/>

		<legend><g:message code="pix.album.album.upload.zip_imagefile_message" /></legend>
		
		<div id="file-uploader" ></div>
	    	
		<div>
			<button class="btn btn-primary" id="triggerUpload" >Upload Files</button>
		</div>
		
		<br />
		
		<p>
		    @helper.form(action = routes.Album.uploadLocalZipFile(albumId)) {
				<fieldset>
					<legend><g:message code="pix.album.album.upload.local_path_message" /></legend>
					<input type="text" placeholder="@Messages("pix.album.album.upload.textfield")" style="width:600px;" 
						name="file_path"/>
					<br />
					<button type="submit" class="btn">Submit</button>
				</fieldset>
			}
		</p>
	</body>
</html>