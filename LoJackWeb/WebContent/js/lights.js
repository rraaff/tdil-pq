var sessionCheckIntervalID = window.setInterval(function () {
    $.ajax({
        type: "GET",
        url: "./getLightJobSates",
        dataType: "json",
        success: function (result) {
        	$.each(result, function(index, item) {
        		if ($('#light-status-' + item.idEntidad + '-' + item.idLuz)) {
        			$('#light-status-' + item.idEntidad + '-' + item.idLuz).prop('innerHTML', item.status);
        		}
        		if ($('#light-job-' + item.idEntidad + '-' + item.idLuz)) {
        			$('#light-job-' + item.idEntidad + '-' + item.idLuz).prop('innerHTML', '');
        		}
            });
        }
    });
}, 5000);