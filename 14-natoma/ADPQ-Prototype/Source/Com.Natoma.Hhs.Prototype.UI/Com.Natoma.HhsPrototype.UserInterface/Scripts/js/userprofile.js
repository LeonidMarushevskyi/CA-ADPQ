$().ready(function () {
    profileNaviationChange($('#liBasicTab'), 'divBasic');

    $('#txtPhone').mask('(999) 999-9999');
});

setTimeout(function () {
    $('.save-message').fadeOut('slow');
}, 3000)

function profileNaviationChange(li, divName) {
    $('.nav li').removeClass('active');
    $('.tabbed-div').hide();

    $(li).addClass('active');
    $('#' + divName).show();
}
