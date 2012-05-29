/*
 * meltbutton (for jQuery)
 */
(function($) {
  $.fn.meltbutton = function(settings) {
	  var obj = $(this);
  	  var buttonId = obj.attr("buttonId");
  	  var actualQuantity = obj.attr("quantity");
  	  var cookieName = 'meltbutton-' + buttonId;
  	  var cookie = $.cookie(cookieName);
  	  if (!cookie) {
  		this.each(function() {
            obj.html("<div id='bm_personas'>A <span id='" + buttonId + "-qty'>" + actualQuantity + " personas</span> les derrite esto.</div>");
    		obj.clickenabled = true;
    		var divBtn = $( "<div id='bm_me_derrite'><a href='#' title='me derrite'></a></div>" ).appendTo( obj );
            obj.click(function() {
	  			if (obj.clickenabled) {
	  				var url='meltaction.do?buttonId=' + buttonId;
				  $.getJSON(url,function(data){
					  	if (data.result == 'OK') {
					  		$('#' + buttonId + '-qty').html(data.quantity + " personas");
					  		$.cookie(cookieName, "set", { expires: 180, path: "/" });
					  		obj.clickenabled = false;
					  	}
				    });
	  			}
            });
  		  
        });
  	  } else {
  		obj.clickenabled = false;
  		obj.html("<div id='bm_personas'>A <span>" + actualQuantity + " personas</span> les derrite esto.</div>");
  		var divBtn = $( "<div id='bm_me_derrite'><a href='#' title='me derrite'></a></div>" ).appendTo( obj );
  	  }
  	  
	  
  }
})(jQuery);