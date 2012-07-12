<% String fbSharejspServerName = SystemPropertyUtils.getSystemPropertValue(SystemPropertiesKeys.SERVER_NAME); %>
<script>
	window.fbAsyncInit = function() {
	  FB.init({
		appId      : '<%=SystemPropertyUtils.getSystemPropertValue(SystemPropertiesKeys.APP_ID)%>',
		status     : true,
		cookie     : true
	  });
	};

	(function(d){
	   var js, id = 'facebook-jssdk'; if (d.getElementById(id)) {return;}
	   js = d.createElement('script'); js.id = id; js.async = true;
	   js.src = "//connect.facebook.net/en_US/all.js";
	   d.getElementsByTagName('head')[0].appendChild(js);
	 }(document));
	
	function facebookShare(text,description,caption,url, image) {
		if (!image) {
			image = '<%=fbSharejspServerName%>/images/logoFBsite.jpg';
		}
		FB.ui({ method: 'feed',display: 'popup',
		link:url,picture:image,name:text,description:description,caption:caption});

	}

	function facebookShareGoodMorning(text,description,caption, lnkid, image) {
		var img = "http://<%=fbSharejspServerName%>/" + image;
		var url = "http://<%=fbSharejspServerName%>/buenDia.jsp?lnk=" + lnkid;
		FB.ui({ method: 'feed',display: 'popup',
		link:url,picture:img,name:text,description:description,caption:caption});

	}
  </script>