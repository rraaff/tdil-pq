package com.tdil.tuafesta.stats;

public enum StatisticType {

	GEO_LEVEL_SEARCH() {
		@Override
		public int getID() {
			return 0;
		}
	},
	PROD_CATEGORY_SEARCH() {
		@Override
		public int getID() {
			return 1;
		}
	},
	SERV_CATEGORY_SEARCH() {
		@Override
		public int getID() {
			return 2;
		}
	},
	
	PRODUCT_SEARCH() {
		@Override
		public int getID() {
			return 3;
		}
	},
	
	SERVICE_SEARCH() {
		@Override
		public int getID() {
			return 4;
		}
	},
	
	SIMPLE_SEARCH() {
		@Override
		public int getID() {
			return 5;
		}
	},
	
	PROFESIONAL_VIEW() {
		@Override
		public int getID() {
			return 6;
		}
	},
	
	PROFESIONAL_CONTACT() {
		@Override
		public int getID() {
			return 7;
		}
	},
	
	PROFESIONAL_REGISTRATION() {
		@Override
		public int getID() {
			return 8;
		}
	},
	
	CLIENT_REGISTRATION() {
		@Override
		public int getID() {
			return 9;
		}
	};
	
	
	public abstract int getID();
	
}
