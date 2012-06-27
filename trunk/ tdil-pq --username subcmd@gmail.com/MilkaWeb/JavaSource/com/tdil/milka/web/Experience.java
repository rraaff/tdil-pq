package com.tdil.milka.web;

import com.tdil.milka.model.EmailEndings;
import com.tdil.milka.model.LoveNicknames;
import com.tdil.milka.model.MailToChild;
import com.tdil.milka.model.MailToParent;
import com.tdil.milka.model.PostIt;
import com.tdil.milka.model.WallWritting;

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
	},
	CARTAS_DE_PADRES_A_HIJOS() {
		@Override
		public String getLink() {
			return "cartasDePadresAHijos.jsp?lnk=";
		}
	};
	
	public abstract String getLink();
	
	public static String getLink(EmailEndings obj) {
		return FINALES_DE_EMAIL.getLink() + obj.getId();
	}
	
	public static String getLink(MailToParent obj) {
		return CARTAS_DE_HIJOS_A_PADRES.getLink() + obj.getId();
	}
	
	public static String getLink(PostIt obj) {
		return POST_ITS.getLink() + obj.getId();
	}
	
	public static String getLink(WallWritting obj) {
		return PAPAPEDIA.getLink() + obj.getId();
	}
	
	public static String getLink(LoveNicknames obj) {
		return APODOS_DE_AMOR.getLink() + obj.getId();
	}
	
	public static String getLink(MailToChild obj) {
		return CARTAS_DE_PADRES_A_HIJOS.getLink() + obj.getId();
	}
}
