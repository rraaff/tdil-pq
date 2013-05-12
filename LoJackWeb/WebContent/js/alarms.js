var sessionCheckIntervalID = window.setInterval(function () {
    $.ajax({
        type: "GET",
        url: "./getAlarmJobSates",
        dataType: "json",
        success: function (result) {
        	$.each(result, function(index, item) {
        		if ($('#alarm-status-' + item.idEntidad)) {
        			$('#alarm-status-' + item.idEntidad).prop('innerHTML', item.status);
        		}
        		if ($('#alarm-job-' + item.idEntidad)) {
        			$('#alarm-job-' + item.idEntidad).prop('innerHTML', '');
        		}
            });
        }
    });
}, 5000);