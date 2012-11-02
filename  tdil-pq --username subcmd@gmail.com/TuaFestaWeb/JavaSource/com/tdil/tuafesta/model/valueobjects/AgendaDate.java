package com.tdil.tuafesta.model.valueobjects;

public class AgendaDate {

	private String date;
	private boolean busy;
	private boolean currentMonth;
	
	public AgendaDate(String date, boolean currentMonth) {
		super();
		this.date = date;
		this.currentMonth = currentMonth;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean isBusy() {
		return busy;
	}
	public void setBusy(boolean busy) {
		this.busy = busy;
	}
	public boolean isCurrentMonth() {
		return currentMonth;
	}
	public void setCurrentMonth(boolean currentMonth) {
		this.currentMonth = currentMonth;
	}
}
