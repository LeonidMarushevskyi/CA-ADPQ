$(function () {
  $('nav').on('click', '.nav-toggle', function (e) {
    e.preventDefault();
    $(e.delegateTarget).toggleClass('open');
  });
});
