package com.tdil.ljpeugeot.model;

public enum AlertStatus {

	PENDING() {
		@Override
		public int code() {
			return 0;
		}
	},
	IN_PROGRESS{
		@Override
		public int code() {
			return 1;
		}
	},
	FINISHED{
		@Override
		public int code() {
			return 2;
		}
	};
	
	public abstract int code();
}
