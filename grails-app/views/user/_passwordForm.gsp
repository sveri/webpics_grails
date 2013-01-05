<g:hiddenField name="id" value="${user?.id}" />
<g:hiddenField name="version" value="${user?.version}" />

<fieldset class="form">
	<div class="fieldcontain ${hasErrors(bean: passwordCommand, field: 'password', 'error')} ">
		<f:field bean="passwordCommand" property="password">
		    <g:passwordField name="password" required="" />
		</f:field>
	</div>
	
	<div class="fieldcontain ${hasErrors(bean: passwordCommand, field: 'passwordRepeat	', 'error')} ">
		<f:field bean="passwordCommand" property="passwordRepeat">
		    <g:passwordField name="passwordRepeat" required="" />
		</f:field>
	</div>
</fieldset>