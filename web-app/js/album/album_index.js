$(document).ready(function() {
    $.fn.editable.defaults.mode = 'inline';
    $('.editable').editable({
        success: function (data, config) {
            var a = data;
            $('#nav_link_' + data).html(config);
        }
    });
});