package com.tdil.milka.struts.forms;

public interface LinkAnchorForm {

	public void setUrlLink(String link);
	
	public String getOriginType();
	
	public void setDestinationType(String type);
	public void setDestinationId(int id);

	public int getObjectId();
	
}
