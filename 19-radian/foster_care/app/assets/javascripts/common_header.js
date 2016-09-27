header_ready = function() {
    $("#cssmenu").menumaker({
        title: "Menu",
        breakpoint: 768,
        format: "multitoggle"
    });
  };

$(document).ready(header_ready)
$(document).on('page:load', header_ready)