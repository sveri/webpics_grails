<g:if test="${role.id != null }" >
	<g:hiddenField name="id" value="${role.id}" />
	<g:hiddenField name="version" value="${role.version}" />
</g:if>

<fieldset class="form">
	<f:field bean="role" property="name"/>
	<g:permissionSelect name="permissions" size="20" value="${role?.permissions}"/>
</fieldset>
