<!doctype html>

<html>
<head>
    <title><g:message code="pix.error" /></title>
    <meta name="layout" content="bootstrap">
    <meta name="bootstrap" content="main">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'errors.css')}" type="text/css">
</head>

<body>
    <p><g:message code="pix.error.developer_message" /></p>
    <g:renderException exception="${exception}" />
</body>

</html>