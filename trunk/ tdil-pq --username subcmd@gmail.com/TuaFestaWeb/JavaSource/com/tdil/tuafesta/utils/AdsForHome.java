package com.tdil.tuafesta.utils;

import java.util.ArrayList;
import java.util.List;

import com.tdil.tuafesta.model.valueobjects.AdvertisementValueObject;

public class AdsForHome {

	private List<AdvertisementValueObject> extraByCategory = new ArrayList<AdvertisementValueObject>();
	private List<AdvertisementValueObject> extraWithoutFilter = new ArrayList<AdvertisementValueObject>();
	private List<AdvertisementValueObject> normal = new ArrayList<AdvertisementValueObject>();
	
	public List<AdvertisementValueObject> getExtraByCategory() {
		return extraByCategory;
	}
	public void setExtraByCategory(List<AdvertisementValueObject> extraByCategory) {
		this.extraByCategory = extraByCategory;
	}
	public List<AdvertisementValueObject> getExtraWithoutFilter() {
		return extraWithoutFilter;
	}
	public void setExtraWithoutFilter(List<AdvertisementValueObject> extraWithoutFilter) {
		this.extraWithoutFilter = extraWithoutFilter;
	}
	public List<AdvertisementValueObject> getNormal() {
		return normal;
	}
	public void setNormal(List<AdvertisementValueObject> normal) {
		this.normal = normal;
	}

	
}
