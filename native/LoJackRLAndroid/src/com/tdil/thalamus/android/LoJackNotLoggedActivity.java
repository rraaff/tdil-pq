package com.tdil.thalamus.android;

import com.tdil.lojack.rl.R;

public class LoJackNotLoggedActivity extends LoJackActivity {

	@Override
	public int menuResourceId() {
		return R.menu.menu_not_logged;
	}
	
	@Override
	protected boolean mustBeLogged() {
		return false;
	}
}
