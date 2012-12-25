<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="pix.navigation.user.edit" /></title>
	</head>
	<body>
		<tmpl:userLeftNav />
		
		<div id="edit-user" class="content scaffold-edit" role="main">
			<h1><g:message code="pix.navigation.user.edit_certain" args="[user.username]" /></h1>
			
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${user}">
				<ul class="errors" role="alert">
					<g:eachError bean="${user}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
					</g:eachError>
				</ul>
			</g:hasErrors>
			
			<g:form method="post"  enctype="multipart/form-data">
				<g:hiddenField name="id" value="${user?.id}" />
				<g:hiddenField name="version" value="${user?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
			
		</div>
	</body>
</html>
