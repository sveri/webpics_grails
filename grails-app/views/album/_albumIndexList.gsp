<fieldset>
    <legend><g:message code="pix.overview" /></legend>
</fieldset>

<table class="table table-striped table-bordered">
    <thead>
    <tr>
        <th><g:message code="pix.albums.album.name" /></th>
        <th><g:message code="pix.delete" /></th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${albums}" var="album">
        <tr>
            <td><g:link id="${album.id}" data-type="text" data-pk="${album.id}" data-url="${createLink(action: 'renameAlbum')}"
                        class="editable">${album.name}</g:link></td>
            <td>
                <g:form method="POST">
                    <g:hiddenField name="id" value="${album?.id}" />
                    <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate=""
                                    onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </g:form>
            </td>
        </tr>
    </g:each>
    </tbody>
</table>