<g:if test="${role.id != null }" >
	<g:hiddenField name="id" value="${role.id}" />
	<g:hiddenField name="version" value="${role.version}" />
</g:if>

<fieldset class="form">
	<f:field bean="role" property="name"/>
<%--	<f:field bean="role" property="permissionsString"/>--%>
<div class="fieldcontain ${hasErrors(bean: role, field: 'permissions', 'error')} ">
	<label for="permissions">
		<g:message code="role.permissions.label" default="Permissions" />
		
	</label>
	
</div>
	<div class="fieldcontain ${hasErrors(bean: role, field: 'permissions', 'error')} ">
		<label for="permissions">
			<g:message code="user.roles.label" default="Permissions" />
		</label>
		<g:select name="permissions" from="${role.permissions}" multiple="multiple" optionKey="id" size="5" value="${user?.roles*.id}" class="many-to-many"/>
	</div>
</fieldset>