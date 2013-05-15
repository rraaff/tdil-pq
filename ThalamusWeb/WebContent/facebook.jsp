<% if (session != null) {
		session.invalidate();	
	}
	request.getSession();
	
	Cookie cookie1 = new Cookie("AWSELB", "asasasas");
	cookie1.setMaxAge(24*60*60);
	response.addCookie(cookie1);
%>
{"link":{"ref":"AuthRequest","href":"https://graph.facebook.com/oauth/authorize?client_id=520056664702384&response_type=code&scope=email&scope=user_birthday&redirect_uri=http%3A%2F%2Flocalhost%3A8180%2FThalamusJClientWeb%2Fsignin%2Ffacebook"},"reason":"ExternalProviderAutRequest","data":null}