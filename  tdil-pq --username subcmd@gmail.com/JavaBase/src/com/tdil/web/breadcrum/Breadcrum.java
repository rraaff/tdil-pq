package com.tdil.web.breadcrum;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;


public class Breadcrum {

	private String titles[];
	private String pages[];
	private String keys[];
	private String values[];
	
	public Breadcrum titles(String ...titles) {
		this.titles = titles;
		return this;
	}
	public Breadcrum pages(String ...pages) {
		this.pages = pages;
		if (pages.length != titles.length) {
			throw new RuntimeException("La cantidad de titulos es distinta de las paginas");
		}
		return this;
	}
	public Breadcrum keys(String ...keys) {
		this.keys = keys;
		return this;
	}
	public Breadcrum values(String ...values) {
		if (values.length != keys.length) {
			throw new RuntimeException("La cantidad de valores es distinta de las keys");
		}
		this.values = values;
		for (int i = 0; i < this.titles.length; i++) {
			for (int j = 0; j < this.keys.length; j++) {
				this.titles[i] = StringUtils.replace(this.titles[i], this.keys[j], this.values[j]);
				if (this.pages != null && this.pages[i] != null) {
					this.pages[i] = StringUtils.replace(this.pages[i], this.keys[j], this.values[j]);
				}
			}
		}
		return this;
	}
	
	public List<BreadcrumItem> finish() {
		List<BreadcrumItem> result = new ArrayList<BreadcrumItem>();
		for (int i = 0; i < this.titles.length; i++) {
			result.add(new BreadcrumItem(this.titles[i], this.pages[i]));
		}
		result.get(result.size() - 1).setLast(true);
		return result;
	}
}

