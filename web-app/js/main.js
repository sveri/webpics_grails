if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
	})(jQuery);
}

jQuery(document).ready(function(){
	console.log(SPACER_GIF);
    $.fn.sessionTimeout({
        autoping: true,
        timeout : 30000,
        resource: SPACER_GIF
    }); 
});