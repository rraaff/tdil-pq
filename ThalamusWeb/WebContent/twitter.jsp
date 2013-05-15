<% if (session != null) {
		session.invalidate();	
	}
	request.getSession();
	
	Cookie cookie1 = new Cookie("AWSELB", "asasasas");
	cookie1.setMaxAge(24*60*60);
	response.addCookie(cookie1);
%>
{"link":{"ref":"AuthRequest","href":"https://api.twitter.com/oauth/authenticate?oauth_token=c2pwlxt1I6Pj1AYCU4RR5yTiTVqTrXo28jWygIVaabU"},"reason":"ExternalProviderAutRequest","data":null}