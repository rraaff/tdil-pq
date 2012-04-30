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
  	  var buttonId = settings.buttonId;
  	  var actualQuantity = settings.quantity;
  	  var cookieName = 'meltbutton-' + buttonId;
  	  var cookie = $.cookie(cookieName);
  	  var obj = $(this);
  	  if (!cookie) {
  		this.each(function() {
            obj.prop("innerHTML", "Me derrite " + actualQuantity);
            obj.click(function() {
	 			$.cookie(cookieName, "set", { expires: 180, path: "/" });
                obj.prop("innerHTML", "Te derrite " + actualQuantity);
  				obj.click = null;
            });
  		  
        });
  	  } else {
  		 obj.prop("innerHTML", "Te derrite " + actualQuantity);
  	  }
  	  
	  
  }
})(jQuery);