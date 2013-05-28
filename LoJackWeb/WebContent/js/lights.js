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
        		if (item.unknowm) {
        			setLightStatusRanOff(item.idEntidad, item.idLuz);
        		} else {
        			if (item.ran) {
            			setLightStatusRanOn(item.idEntidad, item.idLuz);
            		} else {
            			if (item.on) {
                			setLightStatusOn(item.idEntidad, item.idLuz);
                		} else {
                   			setLightStatusOff(item.idEntidad, item.idLuz);
                		}
            		}
        		}
        		if ($('#light-job-' + item.idEntidad + '-' + item.idLuz)) {
        			$('#light-job-' + item.idEntidad + '-' + item.idLuz).prop('innerHTML', '');
        		}
            });
        }
    });
}, 5000);