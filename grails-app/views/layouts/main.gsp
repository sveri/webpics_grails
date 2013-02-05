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

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="A tool to handle pictures.">
	<meta name="author" content="Sven Richter">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <g:external dir="images" file="favicon.png" />

	<title>PIX - <g:layoutTitle default="PIX"/></title>

    <script type="text/javascript">
    	PROJECT_WEBROOT = "${createLink(uri: '/')}";
        RESET_SESSION_TIMEOUT_URL = "${createLink(controller: 'application', action: 'resetSessionTimeout')}";
        var piwik_url = "${grailsApplication.config.pix.piwik.url}";
        var piwik_site_id = "${grailsApplication.config.pix.piwik.site_id}";
    </script>

	<r:require modules="bootstrap, application"/>

	<g:layoutHead/>
	<r:layoutResources />

	<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
</head>

<body>

	<g:render template="/shared/topTemplate" />

	<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>

	<div class="container-fluid">

		<div class="row-fluid">

                <g:pageProperty name="page.navbar" />

			<div class="content fixed-fixed">
				<div class="content-container" id="mainContent">
					<g:layoutBody/>

				</div>
			</div>
			<!--/span-->
		</div>
		<!--/row-->

        <g:render template="/shared/footer" />

	</div>
	<!--/.fluid-container-->

	<r:layoutResources />


<script type="text/javascript">
    if(piwik_url != undefined && piwik_url != ''){
        var pkBaseURL = (("https:" == document.location.protocol) ? "https://" + piwik_url + "/" : "http://" + piwik_url + "/");
        document.write(unescape("%3Cscript src='" + pkBaseURL + "piwik.js' type='text/javascript'%3E%3C/script%3E"));
    }
</script>
<script type="text/javascript">
    if(piwik_url != undefined && piwik_url != ''){
        try {
            var piwikTracker = Piwik.getTracker(pkBaseURL + "piwik.php", parseInt(piwik_site_id));
            piwikTracker.trackPageView();
            piwikTracker.enableLinkTracking();
        } catch( err ) {}
    }
</script>

</body>
</html>
