<div class="fieldcontain ${hasErrors(bean: password, field: 'password', 'error')} ">

	<f:field bean="password" property="password">
	    <g:passwordField name="password"/>
	</f:field>
</div>

<div class="fieldcontain ${hasErrors(bean: password, field: 'passwordRepeat', 'error')} ">

	<f:field bean="password" property="passwordRepeat">
	    <g:passwordField name="passwordRepeat"/>
	</f:field>
</div>