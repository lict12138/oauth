/*
 * Initial pages
 * */
$(function () {
    new CAS();
});

/**
 * displaytag paginated use it.
 * Don't change it
 *
 * @param formId
 * @param data
 */
function displaytagform(formId, data) {
    var $form = $("#" + formId);
    var action = $form.attr("action");
    var params = action.indexOf('?') == -1 ? '?' : '&';
    $.map(data, function (d) {
        params += (d.f + "=" + d.v + "&");
    });

    var url = action + params;
    var $targetDiv = $("div.displayTarget");
    if ($targetDiv.length > 0) {
        //if exist, load  the content to the div
        $targetDiv.load(url);
    } else {
        location.href = url;
    }
}


function CAS(){
    this.activeMenu();
    $(".children").css("padding-left", "50px");
}


CAS.prototype = {
    activeMenu: function () {
        //Active main menu
        var href = location.pathname;
        if (href.indexOf("/system/config/") != -1) {
            $("#mainMenu li#system_config").addClass("active");
        } else if (href.indexOf("/system/user/add") != -1) {
            $("#mainMenu li#user_add").addClass("active");
        }  else {
            $("#mainMenu li:eq(0)").addClass("active");
        }
    }
};
