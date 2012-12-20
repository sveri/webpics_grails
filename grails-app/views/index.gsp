<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="pix.index.title" /></title>
	</head>
	<body>
		<div class="hero-unit">
			<h1><g:message code="pix.index.intro" /></h1>
			<shiro:isNotLoggedIn>
				<p><g:message code="pix.index.intro_2" /></p>
			</shiro:isNotLoggedIn>
		</div>
	</body>
</html>