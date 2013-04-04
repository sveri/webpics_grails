<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="pix.navigation.role.edit" args="[role.name]" /></title>
	</head>
	<body>
		<g:render template="/user/userLeftNav" />
		
		<g:form method="post"  enctype="multipart/form-data">
            <div class="row">

			    <div class="span6">
                    <legend><g:message code="pix.navigation.role.edit" args="[role.name]" /></legend>

                    <g:render template="/shared/message" model="['message': flash.message]" />

                    <g:render template="/shared/error" model="['errorBean': role]" />

                    <tmpl:editForm bean="${role}" />

                    <fieldset class="buttons">
                        <g:actionSubmit class="btn btn-primary" action="update" value="${message(code: 'pix.ok', default: 'Ok')}" />
                    </fieldset>
                </div>

                <div class="span6">
                    <legend><g:message code="pix.navigation.role.albums_allowed" /></legend>
                    <tmpl:albumsForm bean="${albums}" />
                </div>

            </div>
		</g:form>
			
	</body>
</html>
