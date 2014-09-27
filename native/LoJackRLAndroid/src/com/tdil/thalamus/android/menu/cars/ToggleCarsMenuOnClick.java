package com.tdil.thalamus.android.menu.cars;

import java.util.ArrayList;
import java.util.List;

import com.tdil.lojack.rl.R;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ToggleCarsMenuOnClick implements OnClickListener {

	private boolean open;
	private ImageView menuImage;
	private List<View> menuItems = new ArrayList<View>();
	
//	public ToggleCarsMenuOnClick(boolean open, ImageView menuImage, View carPositionsButtonMenu, View carSpeedButtonMenu, View carZoneButtonMenu, 
//			View carPhoneButtonMenu, View carPathButtonMenu, View carParkedModeButtonMenu) {
//		super();
//		this.open = open;
//		this.menuImage = menuImage;
//		this.menuItems = menuItems;
//	}
	
	public ToggleCarsMenuOnClick(boolean open, ImageView menuImage, View ...menuItems) {
		super();
		this.open = open;
		this.menuImage = menuImage;
		for (int i = 0; i < menuItems.length; i++) {
			this.menuItems.add(menuItems[i]);
		}
	}

	@Override
	public void onClick(View arg0) {
		if (open) {
			this.menuImage.setBackgroundResource(R.drawable.state_02);
			for (View v : this.menuItems) {
				v.setVisibility(View.GONE);
			}
			open = false;
		} else {
			this.menuImage.setBackgroundResource(R.drawable.state_01);
			for (View v : this.menuItems) {
				v.setVisibility(View.VISIBLE);
			}
			open = true;
		}
		
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public ImageView getMenuImage() {
		return menuImage;
	}

	public void setMenuImage(ImageView menuImage) {
		this.menuImage = menuImage;
	}

	public List<View> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<View> menuItems) {
		this.menuItems = menuItems;
	}

}
