<legend><g:message code="pix.album.album.upload.zip_imagefile_message" /></legend>

<div id="file-uploader" ></div>

<div>
	<button class="btn btn-primary" id="triggerUpload" >Upload Files</button>
</div>

<br />

<p>
    <g:form action="zipupload">
   		<input type="hidden" value="${album.id}" id="albumId" name="albumid"/>
		<fieldset>
			<legend><g:message code="pix.album.album.upload.local_path_message" /></legend>
			<!--suppress HtmlFormInputWithoutLabel -->
                  <input type="text" placeholder="${message(code: 'pix.album.album.upload.textfield')}" style="width:600px;"
				name="file_path"/>
			<br />
			<button type="submit" class="btn">Submit</button>
		</fieldset>
	</g:form>
</p>