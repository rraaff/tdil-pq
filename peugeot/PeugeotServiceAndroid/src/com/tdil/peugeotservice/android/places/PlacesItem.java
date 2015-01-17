package com.tdil.peugeotservice.android.places;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class PlacesItem implements ClusterItem {
    private final LatLng mPosition;
    private String description;
    private String tel;
    private int type;

    public PlacesItem(double lat, double lng, int type, String description, String tel) {
        mPosition = new LatLng(lat, lng);
        this.type = type;
        this.description = description;
        this.tel = tel;
    }
    
    @Override
    public LatLng getPosition() {
        return mPosition;
    }

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
}