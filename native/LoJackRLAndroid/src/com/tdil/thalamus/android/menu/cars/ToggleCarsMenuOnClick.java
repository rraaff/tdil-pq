package com.tdil.thalamus.android.menu.cars;

import java.util.ArrayList;
import java.util.List;

import com.tdil.lojack.rl.R;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ToggleCarsMenuOnClick implements OnClickListener {

	private boolean open;
	private CarsViewGroup carsMenu;
	private ImageView menuImage;
	private View menuOpen;
	private List<View> menuItems = new ArrayList<View>();
	
//	public ToggleCarsMenuOnClick(boolean open, ImageView menuImage, View carPositionsButtonMenu, View carSpeedButtonMenu, View carZoneButtonMenu, 
//			View carPhoneButtonMenu, View carPathButtonMenu, View carParkedModeButtonMenu) {
//		super();
//		this.open = open;
//		this.menuImage = menuImage;
//		this.menuItems = menuItems;
//	}
	
	public ToggleCarsMenuOnClick(boolean open, CarsViewGroup carsMenu, ImageView menuImage, View menuOpen, View ...menuItems) {
		super();
		this.carsMenu = carsMenu;
		this.open = open;
		this.menuImage = menuImage;
		this.menuOpen = menuOpen;
		for (int i = 0; i < menuItems.length; i++) {
			this.menuItems.add(menuItems[i]);
		}
	}

	@Override
	public void onClick(View arg0) {
		toggle();
	}

	public void toggle() {
		if (open) {
			this.menuImage.setVisibility(View.GONE);
			this.menuOpen.setVisibility(View.VISIBLE);
//			this.menuImage.setBackgroundResource(R.drawable.ic_cars_whm_menu_off);
			for (View v : this.menuItems) {
				v.setVisibility(View.GONE);
			}
			this.carsMenu.setBackgroundResource(0);
			open = false;
		} else {
			this.menuImage.setVisibility(View.VISIBLE);
			this.menuOpen.setVisibility(View.GONE);
//			this.menuImage.setBackgroundResource(R.drawable.ic_cars_whm_menu_on);
			for (View v : this.menuItems) {
				v.setVisibility(View.VISIBLE);
			}
			this.carsMenu.setBackgroundResource(R.drawable.ic_cars_whm_menu_connector);
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
