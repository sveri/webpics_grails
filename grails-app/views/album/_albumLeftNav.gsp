<content tag="navbar">
	<div class="well sidebar-nav left">
		<ul class="nav nav-list">
			<li class="nav-header">${album}</li>
			<li><g:link action="album" id="${album.id}"><g:message code="pix.navigation.albums.show_all" /></g:link></li>
	       	<shiro:hasAnyRole in="['Administrator', 'User']">
				<li><g:link action="upload" id="${album.id}"><g:message code="pix.navigation.albums.upload" /></g:link></li>
			</shiro:hasAnyRole>
		</ul>
	</div>
</content>