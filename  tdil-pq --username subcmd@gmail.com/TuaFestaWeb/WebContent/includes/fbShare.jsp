<%@page import="com.tdil.tuafesta.web.SystemPropertyUtils"%>
<%@page import="com.tdil.tuafesta.utils.SystemPropertiesKeys"%>
<script>
	window.fbAsyncInit = function() {
	  FB.init({
		appId      : '<%=SystemPropertyUtils.getSystemPropertValue(SystemPropertiesKeys.FB_API_KEY)%>',
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
	
	function facebookShare(text,description,caption,url,image) {
		var image = '<%=SystemPropertyUtils.getSystemPropertValue(SystemPropertiesKeys.SERVER_NAME)%>/images/logoFBsite.jpg';
		FB.ui({ method: 'feed',display: 'popup',
		link:url,picture:image,name:text,description:description,caption:caption});

	}

	function twitterShare(stat) {
		window.open('http://twitter.com/home?status=' + encodeURIComponent(stat) + ' ' + encodeURIComponent(location.href)); 
	}
	
  </script>