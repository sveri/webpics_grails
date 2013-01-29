<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="pix.navigation.albums"/></title>
    <r:require modules="album_index"/>
</head>

<body>
<content tag="navbar">
    <div class="well sidebar-nav left">
        <ul class="nav nav-list">
            <li class="nav-header"><g:message code="pix.navigation.albums"/></li>
            <g:each in="${albums}" var="album">
                <li><g:link action="album" id="${album.id}" elementId="nav_link_${album.id}">${album}</g:link></li>
            </g:each>
        </ul>
    </div>
</content>

<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
    <hr>
</g:if>

<shiro:hasPermission permission="album:album">
    <h3><g:message code="pix.albums.choose_album"/></h3>
</shiro:hasPermission>

<shiro:hasPermission permission="album:save">
    <fieldset>

        <legend><g:message code="pix.albums.new.album"/>:</legend>
        <g:renderErrors bean="${albumForm}"/>

        <g:form action="save" useToken="true">
            <f:field bean="albumForm" property="name"/>
            <g:submitButton name="submit" value="${message(code: 'pix.ok')}"/>
        </g:form>
    </fieldset>
</shiro:hasPermission>

<g:hasPermissions in="['album:renameAlbum', 'album:delete']">
    <tmpl:albumIndexList albums="${albums}"/>
</g:hasPermissions>

</body>
</html>