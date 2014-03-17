package com.tdil.lojack.model;

public enum ImportType {

	VLU_COMPLETE() {
		@Override
		public int getType() {
			return 0;
		}
	},
	VLU_DELETE_REPAIRED() {
		@Override
		public int getType() {
			return 1;
		}
	};
	
	public abstract int getType();
}
