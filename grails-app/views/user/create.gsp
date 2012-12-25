<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="pix.navigation.user.add" /></title>
	</head>
	<body>
		<tmpl:userLeftNav />
		
		<div id="create-user" class="content scaffold-create" role="main">
			<h1><g:message code="pix.navigation.user.add" /></h1>
			
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
			
			<g:form action="save"  enctype="multipart/form-data">
				<fieldset class="form" model="[create: 'asdfasdf']">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
