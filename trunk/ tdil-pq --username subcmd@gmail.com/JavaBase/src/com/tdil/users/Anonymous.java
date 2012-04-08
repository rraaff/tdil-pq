package com.tdil.users;

public class Anonymous extends Role {
	
	public static final Anonymous INSTANCE = new Anonymous();

	private Anonymous() {
		
	}
	
	@Override
	public boolean isValid(SystemUser user) {
		return true;
	}

}
