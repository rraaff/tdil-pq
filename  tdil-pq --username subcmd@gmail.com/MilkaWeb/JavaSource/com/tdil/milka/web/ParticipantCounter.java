package com.tdil.milka.web;

import java.sql.SQLException;

import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.ClickCounter;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationException;

class ParticipantCounter implements TransactionalAction {
	
	private int result;
	
	public ParticipantCounter() {
		super();
	}

	public void executeInTransaction() throws SQLException, ValidationException {
		ClickCounter cartasDeHijosAPadres = DAOManager.getClickCounterDAO().selectClickCounterByPrimaryKey(MeltButton.CARTAS_DE_HIJOS_A_PADRES_RENDER);
		ClickCounter papapedia = DAOManager.getClickCounterDAO().selectClickCounterByPrimaryKey(MeltButton.PAPAPEDIA_RENDER);
		ClickCounter postIt = DAOManager.getClickCounterDAO().selectClickCounterByPrimaryKey(MeltButton.POSTIT_RENDER);
		ClickCounter finalesDeEmail = DAOManager.getClickCounterDAO().selectClickCounterByPrimaryKey(MeltButton.FINALES_DE_EMAIL_RENDER);
		ClickCounter apodosDeAmor = DAOManager.getClickCounterDAO().selectClickCounterByPrimaryKey(MeltButton.APODOS_DE_AMOR_RENDER);
		result = cartasDeHijosAPadres.getClicks() + papapedia.getClicks() + postIt.getClicks() + finalesDeEmail.getClicks() + apodosDeAmor.getClicks();
	}

	public int getResult() {
		return result;
	}
}