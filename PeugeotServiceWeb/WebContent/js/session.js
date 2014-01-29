var sessionCheckIntervalID = window.setInterval(function () {
    $.ajax({
        type: "GET",
        url: "./checkSession.do",
        dataType: "json",
        success: function (result) {
            if (result.sessionExpired == "true") {
                window.clearInterval(sessionCheckIntervalID);
                top.location.href = "index.jsp";
            }
        }        
    });
}, 10000);