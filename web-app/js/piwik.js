if(PIWIK_URL != undefined && PIWIK_URL != ''){
    var pkBaseURL = (("https:" == document.location.protocol) ? "https://" + PIWIK_URL + "/" : "http://" + PIWIK_URL + "/");
    document.write(unescape("%3Cscript src='" + pkBaseURL + "piwik.js' type='text/javascript'%3E%3C/script%3E"));

    try {
        var piwikTracker = Piwik.getTracker(pkBaseURL + "piwik.php", parseInt(PIWIK_SITE_ID));
        piwikTracker.trackPageView();
        piwikTracker.enableLinkTracking();
    } catch( err ) {}
}
