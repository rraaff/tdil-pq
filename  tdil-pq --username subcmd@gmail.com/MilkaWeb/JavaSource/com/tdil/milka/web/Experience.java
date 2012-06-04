package com.tdil.milka.web;

public enum Experience {

	FINALES_DE_EMAIL() {
		@Override
		public String getLink() {
			return "finalesDeEmail.jsp?lnk=";
		}
	},
	CARTAS_DE_HIJOS_A_PADRES() {
		@Override
		public String getLink() {
			return "cartasDeHijosAPadres.jsp?lnk=";
		}
	},
	POST_ITS() {
		@Override
		public String getLink() {
			return "postits.jsp?lnk=";
		}
	},
	PAPAPEDIA() {
		@Override
		public String getLink() {
			return "papapedia.jsp?lnk=";
		}
	},
	APODOS_DE_AMOR() {
		@Override
		public String getLink() {
			return "apodosDeAmor.jsp?lnk=";
		}
	};
	
	public abstract String getLink();
}
