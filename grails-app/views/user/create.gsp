<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="pix.navigation.user.add" /></title>
	</head>
	<body>
		<tmpl:userLeftNav />
		
		<div id="create-user" class="content scaffold-create" role="main">
			<legend><g:message code="pix.navigation.user.add" /></legend>
			
			<g:render template="/shared/message" model="['message': flash.message]" />
			
			<g:render template="/shared/error" model="['errorBean': passwordCommand]" />
			<g:render template="/shared/error" model="['errorBean': user]" />
			
			<g:form method="post"  enctype="multipart/form-data">
			
				<tmpl:editForm />
				<tmpl:passwordForm />

				<fieldset class="buttons">
					<g:actionSubmit class="btn btn-primary" action="save" value="${message(code: 'pix.ok', default: 'Ok')}" />
				</fieldset>
			</g:form>
			
		</div>
	</body>
</html>
