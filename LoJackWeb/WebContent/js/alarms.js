var sessionCheckIntervalID = window.setInterval(function () {
    $.ajax({
        type: "GET",
        url: "./getAlarmJobSates",
        dataType: "json",
        success: function (result) {
        	$.each(result, function(index, item) {
        		if ($('#alarm-status-' + item.idEntidad)) {
        			$('#alarm-status-' + item.idEntidad).removeClass();
        			$('#alarm-status-' + item.idEntidad).addClass("portaStatus alarm-status-"+item.status);
        			$('#alarm-status-' + item.idEntidad).prop('innerHTML', item.status);
        		}
        		if (item.armada) {
        			checkbg(item.idEntidad);
        		} else {
        			uncheckbg(item.idEntidad);
        		}
        		if ($('#alarm-job-' + item.idEntidad)) {
        			$('#alarm-job-' + item.idEntidad).prop('innerHTML', '');
        		}
            });
        }
    });
}, 5000);