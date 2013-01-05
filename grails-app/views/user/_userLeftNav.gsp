<content tag="navbar">
	<div class="well sidebar-nav left">
		<ul class="nav nav-list">
			<li class="nav-header"><g:message code="pix.navigation.rights.nav_header" /></li>
			<li class="divider"></li>
			
			<li class="${activeContActLink(controller: 'user', action:'index') }">
				<g:link controller="user" action="index"><g:message code="pix.navigation.user.nav_left.overview" /></g:link></li>
			<li class="${activeContActLink(controller: 'user', action:'create') }">
				<g:link controller="user" action="create"><g:message code="pix.navigation.user.add" /></g:link></li>
			<li class="divider"></li>
			
			<li class="${activeContActLink(controller: 'role', action:'index') }">
				<g:link controller="role" action="index"><g:message code="pix.navigation.role.nav_left.overview" /></g:link>
			</li>
			<li class="${activeContActLink(controller: 'role', action:'add') }">
				<g:link controller="role" action="add"><g:message code="pix.navigation.role.nav_left.add" /></g:link>
			</li>
		</ul>
	</div>
</content>