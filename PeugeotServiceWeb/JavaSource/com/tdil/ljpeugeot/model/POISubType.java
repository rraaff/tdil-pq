package com.tdil.ljpeugeot.model;

public enum POISubType {

	Principal() {
		@Override
		public int getCode() {
			return 0;
		}
		@Override
		public String getDescription() {
			return "Principal";
		}
	},
	Secundaria() {
		@Override
		public int getCode() {
			return 1;
		}
		@Override
		public String getDescription() {
			return "Secundaria";
		}
	};
	
	public abstract int getCode();
	public abstract String getDescription();
	
	public static POISubType getForDescription(String desc) {
		for (POISubType en : POISubType.values()) {
			if (en.getDescription().equals(desc)) {
				return en;
			}
		}
		return null;
	}
}
