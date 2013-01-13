if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
	})(jQuery);
}

function reset_session_timeout(){
    var feedback = $.ajax({
        type: "GET",
        url: RESET_SESSION_TIMEOUT_URL,
        async: true
    }).complete(function(){
        setTimeout(function(){reset_session_timeout();}, 60000 * 60000 * 60000);
    });

}

jQuery(document).ready(function(){
	reset_session_timeout();
});