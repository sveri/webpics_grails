<g:if test="${user.id != null }" >
	<g:hiddenField name="id" value="${user?.id}" />
	<g:hiddenField name="version" value="${user?.version}" />
</g:if>

<fieldset class="form">
	<div class="fieldcontain ${hasErrors(bean: user, field: 'username', 'error')} required">
		<label for="username">
			<g:message code="user.username.label" default="Username" />
			<span class="required-indicator">*</span>
		</label>
		<g:textField name="username" required="" value="${user?.username}"/>
	</div>
	
	<div class="fieldcontain ${hasErrors(bean: user, field: 'roles', 'error')} ">
		<label for="roles">
			<g:message code="user.roles.label" default="Roles" />
		</label>
		<g:select name="roles" from="${webpics_grails.auth.Role.list()}" multiple="multiple" optionKey="id" size="5" value="${user?.roles*.id}" class="many-to-many"/>
	</div>
</fieldset>

