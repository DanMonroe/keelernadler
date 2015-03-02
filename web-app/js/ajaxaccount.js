$(function() {

    $('#newaccountform').dialog({
        autoOpen: false,
        height: 500,
        width: 550,
        modal: true,
        buttons: {
            'Create Account': function() {
                var donkeyname = $("#donkeyname").val();
                var event = $("#id").val();
                var herderId = $("#herderId").val();
                $.post(contextPath + '/donkey/savedonkey', {'event.id':event, name:donkeyname, 'herder.id':herderId}, function(data) {
                    var item = $("<li>");
                    var link = $("<a>").attr("href", contextPath + "/donkey/show/" + data.id).html(data.name);
                    item.append(link);
                    $('#account_list').append(item);
                }, 'json');
                $(this).dialog('close');
            },
            Cancel: function() {
                $(this).dialog('close');
            }

        },
        close: function() {

        }
    });

    $('#add_account').click(function() {
        $('#newaccountform').dialog('open');
    });
});