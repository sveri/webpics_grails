<script type="text/javascript">
    var IMAGE_DOWNLOAD_LINK = "${createLink(action: 'downloadImage')}";
</script>

<content tag="navbar">
	<div class="well sidebar-nav left">
		<ul class="nav nav-list">
			<li class="nav-header">${album}</li>
			<li class="divider"></li>
			
			<li class="${activeContActLink(controller: 'album', action:'album') }">
				<g:link action="album" id="${album.id}"><g:message code="pix.navigation.albums.show_all" /></g:link></li>
			<li class="divider"></li>
			
	       	<shiro:hasPermission permission="album:upload">
				<li class="${activeContActLink(controller: 'album', action:'upload') }">
					<g:link action="upload" id="${album.id}"><g:message code="pix.navigation.albums.upload" /></g:link>
				</li>
			</shiro:hasPermission>
            <shiro:hasPermission permission="album:downloadAlbum">
                <li class="${activeContActLink(controller: 'album', action:'downloadAlbum') }">
                	<g:link action="downloadAlbum" id="${album.id}"><g:message code="pix.navigation.albums.download" /></g:link></li>
            </shiro:hasPermission>
            <shiro:hasPermission permission="album:downloadImage">
                <g:canDownloadSingleImage>
                    <li class="${activeContActLink(controller: 'album', action:'downloadAlbum') }">
                        <g:link action="downloadImage" elementId="downloadImage"><g:message code="pix.navigation.albums.download_image" /></g:link></li>
                </g:canDownloadSingleImage>
            </shiro:hasPermission>
		</ul>
	</div>
</content>