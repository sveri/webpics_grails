<g:if test="${flash.message}">
  <div class="message">${flash.message}</div>
</g:if>

<g:form action="signIn">
  <input type="hidden" name="targetUri" value="${targetUri}" />
  <table>
    <tbody>
      <tr>
        <td><label for="username"><g:message code="user.username.label" />:</label></td>
        <td><input id="username" type="text" name="username" value="${username}" /></td>
      </tr>
      <tr>
        <td><label for="password"><g:message code="user.password.label" />:</label></td>
        <td><input id="password" type="password" name="password" value="" /></td>
      </tr>
      <tr>
        <td><g:message code="user.remember_me" />:</td>
        <td><g:checkBox name="rememberMe" value="${rememberMe}" /></td>
      </tr>
      <tr>
        <td></td>
        <td><br />
            <input type="submit" value="${message(code: 'pix.navigation.login')}" class="btn btn-primary"/></td>
      </tr>
    </tbody>
  </table>
</g:form>