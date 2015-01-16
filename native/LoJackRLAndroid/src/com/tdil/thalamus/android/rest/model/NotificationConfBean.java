package com.tdil.thalamus.android.rest.model;

import java.io.Serializable;


public class NotificationConfBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6329689475865073122L;
	
	private int lojack;
	private int home;
	private int car;
	private int pet;
	
	public NotificationConfBean() {
	}
	
	public NotificationConfBean(int lojack, int home, int car, int pet) {
		super();
		this.lojack = lojack;
		this.home = home;
		this.car = car;
		this.pet = pet;
	}

	public int getLojack() {
		return lojack;
	}

	public void setLojack(int lojack) {
		this.lojack = lojack;
	}

	public int getHome() {
		return home;
	}

	public void setHome(int home) {
		this.home = home;
	}

	public int getCar() {
		return car;
	}

	public void setCar(int car) {
		this.car = car;
	}

	public int getPet() {
		return pet;
	}

	public void setPet(int pet) {
		this.pet = pet;
	}
	
	
}
