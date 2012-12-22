<div class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container-fluid">
          	<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
          	</a> 
			
			<a class="brand" href="/"><g:message code="pix.index.title" /></a>
			  
			<div class="btn-group pull-right">
				<shiro:isLoggedIn>
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
		              		<i class="icon-user"></i> <shiro:user></shiro:user>
		              		<span class="caret"></span>
		            	</a>
		            	
		            	<ul class="dropdown-menu">
			              	<li class="divider"></li>
			              	<li><a href="@com.feth.play.module.pa.controllers.routes.Authenticate.logout()"><i class="icon-off"></i> @Messages("playauthenticate.navigation.logout")</a></li>
		            	</ul>
				</shiro:isLoggedIn>
				<shiro:isNotLoggedIn>
					<g:link controller="Auth" action="login" class="btn btn-primary btn-mini">
						<g:message code="pix.navigation.login" /></g:link>
				</shiro:isNotLoggedIn>
			</div>

          
			<div class="nav-collapse">
				<ul class="nav">
            	
            	</ul>
            </div><!--/.nav-collapse -->
            
		</div>
	</div>
</div>