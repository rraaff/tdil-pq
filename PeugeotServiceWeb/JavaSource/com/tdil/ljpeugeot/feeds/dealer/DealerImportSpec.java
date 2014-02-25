package com.tdil.ljpeugeot.feeds.dealer;

import java.sql.SQLException;
import java.util.List;

import org.supercsv.cellprocessor.ift.CellProcessor;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.feeds.ImportSpec;
import com.tdil.ljpeugeot.model.City;
import com.tdil.ljpeugeot.model.CityExample;
import com.tdil.ljpeugeot.model.DataImport;
import com.tdil.ljpeugeot.model.Dealer;
import com.tdil.ljpeugeot.model.DealerExample;
import com.tdil.ljpeugeot.model.State;
import com.tdil.ljpeugeot.model.StateExample;
import com.tdil.struts.TransactionalAction;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;

public class DealerImportSpec implements ImportSpec {
	
	public static final String TYPE = "DEALER";
	
	private CellProcessor[] processors;
	
	public DealerImportSpec() {
		processors = new CellProcessor[] { null, // state
					null, // city
					null, // name
					null,
					null,
					null,
					null,
					null,
					null,
					null,
					null
			};
	}
	
	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public Class getRecordClass() {
		return DealerImportRecord.class;
	}

	@Override
	public CellProcessor[] getCellProcessor() {
		return processors;
	}

	@Override
	public void processRow(Object importRecord, DataImport dataImport) throws Exception {
		GenericTransactionExecutionService.getInstance().execute(new ImportDealer((DealerImportRecord)importRecord, dataImport));
	}
	
    @Override
	public void importFinished(DataImport dataImport) throws Exception {
		System.out.println("TODO borrar los datos de importaciones viejas?");
	}
	
	static final class ImportDealer implements TransactionalAction {
		private DataImport dataImport;
		private DealerImportRecord importRecord;
		
		public ImportDealer(DealerImportRecord importRecord, DataImport dataImport) {
			super();
			this.importRecord = importRecord;
			this.dataImport = dataImport;
		}
		public void executeInTransaction() throws SQLException {
			DealerExample dealerExample = new DealerExample();
			dealerExample.createCriteria().andCodeEqualTo(importRecord.getCode());
			List<Dealer> dealers = DAOManager.getDealerDAO().selectDealerByExample(dealerExample);
			if (dealers.isEmpty()) {
				Dealer dealer = new Dealer();
				setDealerData(dealer);
				DAOManager.getDealerDAO().insertDealer(dealer);
			} else {
				Dealer dealer = dealers.get(0);
				setDealerData(dealer);
				DAOManager.getDealerDAO().updateDealerByPrimaryKey(dealer);
			}
		}
		public void setDealerData(Dealer dealer) throws SQLException {
			int id_state = getStateId(importRecord);
			int id_city = getCityId(importRecord, id_state);
			dealer.setIdCity(id_city);
			dealer.setCode(importRecord.getCode());
			dealer.setName(importRecord.getName());
			dealer.setAddress(importRecord.getAddress());
			dealer.setPostalcode(importRecord.getPostalCode());
			dealer.setEmail(importRecord.getEmail());
			dealer.setPhone(importRecord.getPhone());
			dealer.setFax(importRecord.getFax());
			dealer.setCategory(importRecord.getCategory());
			dealer.setLocationtype(importRecord.getLocationtype());
			dealer.setIdDataImport(dataImport.getId());
			dealer.setDeleted(0);
		}
		private int getStateId(DealerImportRecord importRecord2) throws SQLException {
			StateExample stateExample = new StateExample();
			stateExample.createCriteria().andNameEqualTo(importRecord2.getState());
			List<State> state = DAOManager.getStateDAO().selectStateByExample(stateExample);
			if (!state.isEmpty()) {
				return state.get(0).getId();
			}
			State newState = new State();
			newState.setDeleted(0);
			newState.setName(importRecord2.getState());
			return DAOManager.getStateDAO().insertState(newState);
		}
		private int getCityId(DealerImportRecord importRecord2, int id_state) throws SQLException {
			CityExample stateExample = new CityExample();
			stateExample.createCriteria().andNameEqualTo(importRecord2.getCity()).andIdStateEqualTo(id_state);
			List<City> state = DAOManager.getCityDAO().selectCityByExample(stateExample);
			if (!state.isEmpty()) {
				return state.get(0).getId();
			}
			City newState = new City();
			newState.setDeleted(0);
			newState.setName(importRecord2.getCity());
			newState.setIdState(id_state);
			return DAOManager.getCityDAO().insertCity(newState);
		}
		
	}

}
