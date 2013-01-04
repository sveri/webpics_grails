<content tag="navbar">
	<div class="well sidebar-nav left">
		<ul class="nav nav-list">
			<li class="nav-header"><g:message code="pix.navigation.user.nav_header" /></li>
			<li class="divider"></li>
			<li class="${activeContActLink(controller: 'user', action:'index') }">
				<g:link action="index"><g:message code="pix.navigation.user.overview" /></g:link></li>
			<li class="${activeContActLink(controller: 'user', action:'create') }">
				<g:link action="create"><g:message code="pix.navigation.user.add" /></g:link></li>
		</ul>
	</div>
</content>