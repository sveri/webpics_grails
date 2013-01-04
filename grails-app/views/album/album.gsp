<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="pix.navigation.albums" /></title>
		
		<g:if test="${photos.size > 0}">
			<script type="text/javascript">
				var GALLERIA_CLASSIC_THEME_LINK = "${resource(dir: 'js/album/galleria/themes/classic/', file: 'galleria.classic.min.js')}";
			</script>
		    <r:require modules="album_view"/>
		</g:if>
		
	</head>
	<body>
		<tmpl:albumLeftNav album="${album}" /> 

		<g:if test="${photos.size==0}">
			<h4><g:message code="pix.albums.album.no_photos" /></h4>
		</g:if>
		<g:else>
			<tmpl:albumGalleria photos="${photos}" />
		</g:else>
	</body>
</html>