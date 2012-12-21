<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="pix.index.title" /></title>
	</head>
	<body>
	
		<div id="login" class="row">
		
			<g:if test="${flash.invalidToken}">
			  	Don't click the button twice!
			</g:if>
		
		    <div class="span3">
		    
				<h1><g:message code="playauthenticate.login.title" /></h1>
			      @* Display proprietary login form *@
			      	<g:form action="login" useToken="true">
    					<f:field bean="loginCommand" property="username" />
    					<f:field bean="loginCommand" property="password">
						    <g:passwordField name="password"/>
						</f:field>
			      		<g:submitButton name="submit" value="Continue"></g:submitButton>
			      	</g:form>
			    	
			        @if(loginForm.hasGlobalErrors) { 
				        <p class="error">
			    	        <span class="label label-important">@loginForm.globalError.message</span>
						</p>
			        }
			        
			        @_emailPartial(loginForm)
			          
			        @inputPassword(
			          loginForm("password"),
			          '_showConstraints -> false,
			          '_label -> Messages("playauthenticate.login.password.placeholder")
			        )
			          
			        <input type="submit" value="<g:message code="playauthenticate.login.now"/>" class="btn btn-primary"><br/>
			        <br/>
			        <a href="javascript:void(0);" onclick="window.location.href = jsRoutes.controllers.Signup.forgotPassword($('#email').val() || null).absoluteURL();">@Messages("playauthenticate.login.forgot.password")</a>
			
	    	</div>
	
	
	  	</div>
	  
	</body>
</html>