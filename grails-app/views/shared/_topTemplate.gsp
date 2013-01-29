<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container-fluid">
          	<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
          	</a> 
			
			<a class="brand" href="${createLink(uri: '/') }"><g:message code="pix.index.title" /></a>
			  
			<div class="btn-group pull-right">
				<shiro:isLoggedIn>
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
	              		<i class="icon-user"></i> <shiro:principal></shiro:principal>
	              		<span class="caret"></span>
	            	</a>
		            	
	            	<ul class="dropdown-menu">
		              	<li class="divider"></li>
		              	<li><g:link controller="auth" action="signOut"><i class="icon-off"></i> <g:message code="pix.signout" /></g:link></li>
	            	</ul>
				</shiro:isLoggedIn>
				<shiro:isNotLoggedIn>
					<g:link controller="Auth" action="login" class="btn btn-primary btn-mini">
						<g:message code="pix.navigation.login" /></g:link>
				</shiro:isNotLoggedIn>
			</div>

          
			<div class="nav-collapse">
				<ul class="nav">
					<shiro:hasPermission permission="album:index">
						<li class="${activeContLink(controller: 'album')}"><g:link controller="album" >
							<g:message code="pix.navigation.albums" /></g:link></li>
					</shiro:hasPermission>
					<shiro:hasPermission permission="user:index">
						<li class="${activeContLink(controller: 'user')} ${activeContLink(controller: 'role')}"><g:link
                                controller="user" >
							<g:message code="pix.navigation.user" /></g:link></li>
					</shiro:hasPermission>
            	</ul>
            </div><!--/.nav-collapse -->
            
		</div>
	</div>
</div>