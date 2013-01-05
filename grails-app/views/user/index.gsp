<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="pix.navigation.rights.nav_header" /></title>
	</head>
	<body>
		<tmpl:userLeftNav />
		
		<g:render template="/shared/message" />
		
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th><g:message code="pix.name" /></th>
					<th><g:message code="pix.user.roles" /></th>
					<th><g:message code="pix.delete" /></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${users}" var="user">
					<tr>
						<td><g:link action="edit" id="${user.id}">${user.username}</g:link></td>
						<td><g:each in="${user.roles }" var="role">${role.name}, </g:each></td>
						<td>
							<g:form method="POST">
								<g:hiddenField name="id" value="${user?.id}" />
								<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" 
									onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
							</g:form>
						</td>
					</tr>
				</g:each>
			</tbody>
		</table>
	</body>
</html>
