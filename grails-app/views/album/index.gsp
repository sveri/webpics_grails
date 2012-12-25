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
		
		<shiro:hasRole name="Viewer">
			<h3><g:message code="pix.albums.choose_album" /></h3>
		</shiro:hasRole>

		<shiro:hasAnyRole in="['Administrator', 'User']">
			<fieldset>
				
				<legend><g:message code="pix.albums.new.album" />:</legend>
				<g:renderErrors bean="${albumForm}" />
				
				<g:if test="${flash.message}">
					<div class="message" role="status">${flash.message}</div>
				</g:if>
			
		      	<g:form action="save" useToken="true">
  					<f:field bean="albumForm" property="name" />
		      		<g:submitButton name="submit" value="${message(code:'pix.ok')}" />
		      	</g:form>
			</fieldset>
		</shiro:hasAnyRole>
	</body>
</html>