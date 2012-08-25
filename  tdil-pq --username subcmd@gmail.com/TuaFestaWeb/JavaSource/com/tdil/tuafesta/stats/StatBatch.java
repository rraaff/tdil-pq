package com.tdil.tuafesta.stats;

import java.sql.SQLException;
import java.util.List;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationException;
import com.tdil.tuafesta.dao.StatisticDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Statistic;

public class StatBatch implements Runnable, TransactionalAction {

	private List<Statistic> batch;

	public StatBatch(List<Statistic> batch) {
		super();
		this.batch = batch;
	}
	
	public void run() {
		try {
			TransactionProvider.executeInTransaction(this);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void executeInTransaction() throws SQLException, ValidationException {
		StatisticDAO dao = DAOManager.getStatisticDAO();
		for (Statistic stat : batch) {
			dao.insertStatistic(stat);
		}
	}
}
