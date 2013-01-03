<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta name="layout" content="main" />
  <title><g:message code="pix.navigation.login" /></title>
</head>
<body>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>

  <g:form action="signIn">
    <input type="hidden" name="targetUri" value="${targetUri}" />
    <table>
      <tbody>
        <tr>
          <td><g:message code="user.username.label" />:</td>
          <td><input type="text" name="username" value="${username}" /></td>
        </tr>
        <tr>
          <td><g:message code="user.password.label" />:</td>
          <td><input type="password" name="password" value="" /></td>
        </tr>
        <tr>
          <td><g:message code="user.remember_me" />:</td>
          <td><g:checkBox name="rememberMe" value="${rememberMe}" /></td>
        </tr>
        <tr>
          <td />
          <td><br />
              <input type="submit" value="${message(code: 'pix.navigation.login')}" class="btn btn-primary"/></td>
        </tr>
      </tbody>
    </table>
  </g:form>
</body>
</html>
