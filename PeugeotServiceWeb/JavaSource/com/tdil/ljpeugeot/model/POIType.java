package com.tdil.ljpeugeot.model;

public enum POIType {

	Excl_Post_Venta() {
		@Override
		public int getCode() {
			return 0;
		}
		@Override
		public String getDescription() {
			return "Excl. Post Venta";
		}
	},
	Excl_Ventas() {
		@Override
		public int getCode() {
			return 1;
		}
		@Override
		public String getDescription() {
			return "Excl. Ventas";
		}
	},
	Integral() {
		@Override
		public int getCode() {
			return 2;
		}
		@Override
		public String getDescription() {
			return "Integral";
		}
	},
	Peugeot_Rapide() {
		@Override
		public int getCode() {
			return 3;
		}
		@Override
		public String getDescription() {
			return "Peugeot Rapide";
		}
	},
	Seleccion_del_Leon() {
		@Override
		public int getCode() {
			return 4;
		}
		@Override
		public String getDescription() {
			return "Selección del León";
		}
	},
	Sitio_Premium_Ventas() {
		@Override
		public int getCode() {
			return 5;
		}
		@Override
		public String getDescription() {
			return "Sitio Premium Ventas";
		}
	},
	TACE_Peugeot_Rapide() {
		@Override
		public int getCode() {
			return 6;
		}
		@Override
		public String getDescription() {
			return "TACE / Peugeot Rapide";
		}
	},
	TADI_Excl_Post_Venta() {
		@Override
		public int getCode() {
			return 7;
		}
		@Override
		public String getDescription() {
			return "TADI Excl. Post Venta";
		}
	};
	
	public abstract int getCode();
	public abstract String getDescription();
	
	public static POIType getForDescription(String desc) {
		for (POIType en : POIType.values()) {
			if (en.getDescription().equals(desc)) {
				return en;
			}
		}
		return null;
	}
}
