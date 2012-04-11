package com.tdil.users;

public class None extends Role {
	
	public static final None INSTANCE = new None();

	private None() {
		
	}
	
	@Override
	public String getName() {
		return "None";
	}
	
	@Override
	public boolean isValid(User user) {
		return true;
	}

}
