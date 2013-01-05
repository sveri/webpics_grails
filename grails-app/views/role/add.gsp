<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="pix.navigation.role.add" /></title>
	</head>
	<body>
		<g:render template="/user/userLeftNav" />
		
		<g:form method="post"  enctype="multipart/form-data">
			<legend><g:message code="pix.navigation.role.add" /></legend>
		
			<g:render template="/shared/message" model="['message': flash.message]" />
			
			<g:render template="/shared/error" model="['errorBean': role]" />
		
			<tmpl:editForm bean="${role}" />

			<fieldset class="buttons">
				<g:actionSubmit class="btn btn-primary" action="save" value="${message(code: 'pix.ok', default: 'Ok')}" />
			</fieldset>
		</g:form>
			
	</body>
</html>
