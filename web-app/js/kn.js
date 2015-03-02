var dlgBox = null;

function showMessage(title, msg, dlgHeight, hideok) {
    if(! hideok) {
        dlgBox.dialog('option', 'buttons', { "Ok": function() {
            $(this).dialog("close");
            return false;
        }
        });
    }
    dlgBox.dialog('option', 'title', title);
    if (dlgHeight) {
        dlgBox.dialog('option', 'height', dlgHeight);
    } else {
        dlgBox.dialog('option', 'height', 200);
    }

    $('#jqMsg').html(msg);
    dlgBox.dialog('open');
    dlgBox.closest('.ui-dialog').find('.ui-dialog-buttonpane button:eq(0)').focus();
}

$(document).ready(function() {
    $("#jqDialog").dialog({
        autoOpen: false,
        bgiframe: true,
        height: 140,
        modal: true,
        zIndex: 100101,
        resizable: false,
        closeOnEscape: false

    });

    dlgBox = $('#jqDialog');

});