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
            obj.html("Me derrite " + actualQuantity);
    		obj.clickenabled = true;
            obj.click(function() {
	  			if (obj.clickenabled) {
	  				var url='meltaction.do?buttonId=' + buttonId;
				  $.getJSON(url,function(data){
					  	if (data.result == 'OK') {
					  		obj.html("Te derrite " + data.quantity);
					  		$.cookie(cookieName, "set", { expires: 180, path: "/" });
					  		obj.clickenabled = false;
					  	}
				    });
	  			}
            });
  		  
        });
  	  } else {
  		obj.clickenabled = false;
  		 obj.html("Te derrite " + actualQuantity);
  	  }
  	  
	  
  }
})(jQuery);