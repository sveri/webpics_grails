<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="pix.navigation.role.nav_left.overview" /></title>
	</head>
	<body>
		<g:render template="/user/userLeftNav" />
		
		<g:render template="/shared/message" />
		
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th><g:message code="pix.name" /></th>
					<th><g:message code="pix.delete" /></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${roles}" var="role">
					<tr>
						<td><g:link action="edit" id="${role.id}">${role.name}</g:link></td>
						<td>
							<g:form method="POST">
								<g:hiddenField name="id" value="${role.id}" />
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
