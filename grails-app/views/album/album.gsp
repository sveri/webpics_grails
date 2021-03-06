<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="pix.navigation.albums" /></title>
		
		<g:if test="${photos.size > 0}">
            <tmpl:galleriaJson photos="${photos}" />
		    <r:require modules="album_view"/>
		</g:if>
		
	</head>
	<body>
		<tmpl:albumLeftNav album="${album}" /> 

		<g:if test="${photos.size==0}">
			<h4><g:message code="pix.albums.album.no_photos" />
				<g:hasPermissions in="['album:upload','album:jsupload']"><g:message code="pix.albums.album.no_photos.upload" /></g:hasPermissions>  
			</h4>
		</g:if>
		<g:else>
			<tmpl:albumGalleria photos="${photos}" />
		</g:else>
	</body>
</html>