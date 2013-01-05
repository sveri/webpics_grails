<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title><g:message code="pix.navigation.role.add" /></title>
	</head>
	<body>
		<g:render template="/user/userLeftNav" />
		
		<g:form method="post" action="save">
            <div class="row">

			    <div class="span6">
                    <legend><g:message code="pix.navigation.role.add" /></legend>

                    <g:render template="/shared/message" model="['message': flash.message]" />

                    <g:render template="/shared/error" model="['errorBean': role]" />

                    <tmpl:editForm bean="${role}" />

                    <fieldset class="buttons">
                        <g:submitButton class="btn btn-primary"  value="${message(code: 'pix.ok', default: 'Ok')}"  name="submit"/>
                    </fieldset>
                </div>

                <div class="span6">
                    <legend><g:message code="pix.navigation.role.albums_allowed" /></legend>
                    <tmpl:albumsForm albums="${albums}" role="${role}"/>
                </div>

            </div>
		</g:form>
			
	</body>
</html>
