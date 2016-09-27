$().ready(function () {
    ToggleActiveMailbox('Inbox');
    ToggleInactiveMailbox('Sent');
});

function ToggleAllMessages(selectAllCheckbox) {
    var chkSelectAll = $(selectAllCheckbox);
    var isChecked = chkSelectAll.prop('checked');

    chkSelectAll.closest('table').find('input:checkbox').prop('checked', isChecked);
}

function MailboxClicked(activeMailbox, inactiveMailbox) {
    ToggleActiveMailbox(activeMailbox);
    ToggleInactiveMailbox(inactiveMailbox);
}

function ToggleActiveMailbox(mailbox) {
    var activeDiv = $('#div' + mailbox);
    var activeTab = $('#btn' + mailbox);

    activeDiv.show();
    activeTab.addClass('active');
    activeTab.removeClass('btn-info');
}

function ToggleInactiveMailbox(mailbox) {
    var inactiveDiv = $('#div' + mailbox);
    var inactiveTab = $('#btn' + mailbox);

    inactiveDiv.hide();
    inactiveTab.addClass('btn-info');
    inactiveTab.removeClass('active');
}