<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="pix.navigation.user.overview" /></title>
	</head>
	<body>
		<tmpl:userLeftNav />
		
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th><g:message code="pix.name" /></th>
					<th><g:message code="pix.user.roles" /></th>
				</tr>
			</thead>
			<tbody>
				<g:each in="${users}" var="user">
					<tr>
						<td><g:link action="edit" id="${user.id}">${user.username}</g:link></td>
						<td><g:each in="${user.roles }" var="role">${role.name}, </g:each></td>
					</tr>
				</g:each>
			</tbody>
		</table>
	</body>
</html>
