package com.tdil.milka.web;

import java.sql.SQLException;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.ClickCounter;
import com.tdil.struts.TransactionalAction;

public class MeltButton implements TransactionalAction {

	// button
	public static final int PAPAPEDIA_COUNTER = 1;
	public static final int FINALES_DE_EMAIL_COUNTER = 2;
	public static final int CARTAS_DE_HIJOS_A_PADRES_COUNTER = 3;
	public static final int POSTIT_COUNTER = 4;
	public static final int BUEN_DIA_COUNTER = 5;
	// fase 2
	public static final int APODOS_DE_AMOR_COUNTER = 6;
	public static final int CARTAS_DE_PADRES_A_HIJOS_COUNTER = 7;
	public static final int QUE_AMAS_QUE_ODIAS_COUNTER = 8;
	
	// render
	public static final int PAPAPEDIA_RENDER = 10;
	public static final int FINALES_DE_EMAIL_RENDER = 11;
	public static final int CARTAS_DE_HIJOS_A_PADRES_RENDER = 12;
	public static final int POSTIT_RENDER = 13;
	public static final int BUEN_DIA_RENDER = 14;
	// fase 2
	public static final int APODOS_DE_AMOR_RENDER = 15;
	public static final int CARTAS_DE_PADRES_A_HIJOS_RENDER = 16;
	public static final int QUE_AMAS_QUE_ODIAS_RENDER = 17;
	
	private int id;
	private int quantity;
	
	public MeltButton(int id) {
		this.id = id;
	}
	
	//<div id="mb-2" buttonType="photomilka" buttonId="2" quantity="150"></div>
	public static String meltButton(ClickCounter c) {
		StringBuffer result = new StringBuffer();
		result.append("<div id=\"mb-").append(c.getId());
		result.append("\" buttonId=\"");
		result.append(c.getId()).append("\" quantity=\"").append(c.getClicks().toString()).append("\"></div>");
		return result.toString();
	}
	
	public static String meltButton(int id) {
		MeltButton meltButton = new MeltButton(id);
		try {
			TransactionProvider.executeInTransaction(meltButton);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer result = new StringBuffer();
		result.append("<div id=\"mb-").append(meltButton.id);
		result.append("\" buttonId=\"");
		result.append(meltButton.id).append("\" quantity=\"").append(meltButton.quantity).append("\"></div>");
		return result.toString();
	}
	
	public static int meltButtonCount(int id) {
		MeltButton meltButton = new MeltButton(id);
		try {
			TransactionProvider.executeInTransaction(meltButton);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return meltButton.quantity;
	}
	
	public static int countParticipants() {
		ParticipantCounter participantCounter = new ParticipantCounter();
		try {
			TransactionProvider.executeInTransaction(participantCounter);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return participantCounter.getResult();
	}
	
	
	public void executeInTransaction() throws SQLException {
		ClickCounter c = DAOManager.getClickCounterDAO().selectClickCounterByPrimaryKey(this.id);
		this.quantity = c.getClicks();
	}
	
	public static void incrementCounter(int id) {
		try {
			TransactionProvider.executeInTransaction(new IncrementCounter(id));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
