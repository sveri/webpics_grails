<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title><g:layoutTitle default="Grails"/></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
	<g:layoutHead/>
	
	<r:require modules="bootstrap"/>
	<r:layoutResources />

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="A tool to handle pictures.">
	<meta name="author" content="Sven Richter">




</head>

<body>

	<g:render template="/layouts/topTemplate" />

	<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>

	<div class="container-fluid">
	    
		<div class="row-fluid">

			<div class="content fixed-fixed">
				<div class="content-container">
					<g:layoutBody/>
				
				</div>
			</div>
			<!--/span-->
		</div>
		<!--/row-->

		<hr>

		<footer>
			<p>&copy; Sven Richter 2012</p>
		</footer>

	</div>
	<!--/.fluid-container-->
	
	<g:javascript library="application"/>
	<r:layoutResources />

</body>
</html>
