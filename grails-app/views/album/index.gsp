<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="pix.navigation.albums" /></title>
	</head>
	<body>
		<content tag="navbar">
			<div class="well sidebar-nav left">
				<ul class="nav nav-list">
					<li class="nav-header"><g:message code="pix.navigation.albums" /></li>
					<g:each in="${albums}" var="album">
						<li><g:link action="album" id="${album.id}">${album}</g:link></li>
					</g:each>
				</ul>
			</div>
		</content>

		<div class="hero-unit">
			<h1><g:message code="pix.index.intro" /></h1>
			<shiro:isNotLoggedIn>
				<p><g:message code="pix.index.intro_2" /></p>
			</shiro:isNotLoggedIn>
		</div>
	</body>
</html>