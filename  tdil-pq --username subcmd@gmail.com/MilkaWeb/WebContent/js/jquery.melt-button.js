/*
 * jbar (for jQuery)
 * version: 0.2.0 (07/02/2011)
 * @requires jQuery v1.4 or later
 * http://javan.github.com/jbar/
 * http://github.com/javan/jbar
 *
 * Licensed under the MIT:
 *   http://www.opensource.org/licenses/mit-license.php
 *
 * Copyright 2010+ Javan Makhmali :: javan@javan.us
 *
 * Usage:
 *  
 *  jQuery(function(){
 *    jQuery('.jbar').jbar();
 *  });
 *  // Where .jbar is the class belonging to your menus.
 *
 */

(function($) {
  $.fn.meltbutton = function(settings) {
	  var obj = $(this);
	  var buttonType = obj.attr("buttonType");
  	  var buttonId = obj.attr("buttonId");
  	  var actualQuantity = obj.attr("quantity");
  	  var cookieName = 'meltbutton-' + buttonType + '-' + buttonId;
  	  var cookie = $.cookie(cookieName);
  	  if (!cookie) {
  		this.each(function() {
            obj.html("Me derrite " + actualQuantity);
    		obj.clickenabled = true;
            obj.click(function() {
	  			if (obj.clickenabled) {
	  				var url='meltaction.do?buttonType=' + buttonType + '&buttonId=' + buttonId;
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