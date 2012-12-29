<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="pix.navigation.user.edit" /></title>
	</head>
	<body>
		<tmpl:userLeftNav />
		
		<div class="row">
		
			<div class="span6">
				<div id="edit-user" class="content scaffold-edit" role="main">
					<legend><g:message code="pix.navigation.user.edit_certain" args="[user.username]" /></legend>
					
					<g:render template="/shared/message" model="['message': flash.message]" />
					<g:render template="/shared/error" model="['errorBean': user]" />
					
					<g:form method="post"  enctype="multipart/form-data">
						<g:render template="editForm"/>
	
						<fieldset class="buttons">
							<g:actionSubmit class="btn btn-primary" action="update" value="${message(code: 'pix.ok', default: 'Ok')}" />
						</fieldset>
					</g:form>
				</div>
			</div>
			
			<div class="span6">
				<fieldset>
					<legend><g:message code="pix.navigation.user.password_change" /></legend>
				
					<g:render template="/shared/message" model="['message': flash.message_password]" />
					<g:render template="/shared/error" model="['errorBean': passwordCommand]" />
					
					<g:form method="post"  action="changePassword">
						<g:render template="passwordForm"/>
	
						<fieldset class="buttons">
							<g:submitButton  class="btn btn-primary" name="${message(code: 'pix.navigation.user.password_change.save')}" />
						</fieldset>
					</g:form>
				</fieldset>
			</div>
		
		</div>
	</body>
</html>
