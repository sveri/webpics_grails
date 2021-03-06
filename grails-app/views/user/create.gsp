<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="pix.navigation.user.add" /></title>
	</head>
	<body>
		<tmpl:userLeftNav />
		
		<g:form method="post"  enctype="multipart/form-data">
			<legend><g:message code="pix.navigation.user.add" /></legend>
			
			<g:render template="/shared/message" model="['message': flash.message]" />
			
			<g:render template="/shared/error" model="['errorBean': passwordCommand]" />
			<g:render template="/shared/error" model="['errorBean': user]" />
		
		
			<tmpl:editForm />
			<tmpl:passwordForm />

			<fieldset class="buttons">
				<g:actionSubmit class="btn btn-primary" action="save" value="${message(code: 'pix.ok', default: 'Ok')}" />
			</fieldset>
		</g:form>
		
	</body>
</html>
