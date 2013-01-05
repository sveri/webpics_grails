<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="pix.navigation.albums" /></title>
	    <r:require modules="album_upload"/>
	</head>
	<body>
		<tmpl:albumLeftNav album="${album}" /> 

		<div id="uploadSection">
			<tmpl:albumUpload album="${album}" />
		</div>
	</body>
</html>