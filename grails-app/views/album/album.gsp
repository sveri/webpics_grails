<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="pix.navigation.albums" /></title>
	</head>
	<body>
		<tmpl:albumLeftNav album="${album}" /> 

		<div class="hero-unit">
			<h1><g:message code="pix.index.intro" /></h1>
			<shiro:isNotLoggedIn>
				<p><g:message code="pix.index.intro_2" /></p>
			</shiro:isNotLoggedIn>
		</div>
	</body>
</html>