package com.tdil.ljpeugeot.feeds.model;

import java.sql.SQLException;

import org.supercsv.cellprocessor.ift.CellProcessor;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.feeds.ImportSpec;
import com.tdil.ljpeugeot.model.DataImport;
import com.tdil.ljpeugeot.model.Model;
import com.tdil.ljpeugeot.model.ModelExample;
import com.tdil.struts.TransactionalAction;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;

public class ModelImportSpec implements ImportSpec {
	
	public static final String TYPE = "MODEL";
	
	private CellProcessor[] processors;
	
	public ModelImportSpec() {
		processors = new CellProcessor[] { null, // state
					null, // city
					null // name
			};
	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	@Override
	public Class getRecordClass() {
		return ModelImportRecord.class;
	}

	@Override
	public CellProcessor[] getCellProcessor() {
		return processors;
	}

	@Override
	public void processRow(Object importRecord, DataImport dataImport) throws Exception {
		GenericTransactionExecutionService.getInstance().execute(new ImportModel((ModelImportRecord)importRecord, dataImport));
	}
	
    @Override
	public void importFinished(DataImport dataImport) throws Exception {
		System.out.println("TODO borrar los datos de importaciones viejas?");
	}
	
	static final class ImportModel implements TransactionalAction {
		private DataImport dataImport;
		private ModelImportRecord importRecord;
		
		public ImportModel(ModelImportRecord importRecord, DataImport dataImport) {
			super();
			this.importRecord = importRecord;
			this.dataImport = dataImport;
		}
		public void executeInTransaction() throws SQLException {
			ModelExample modelExample = new ModelExample(); 
			modelExample.createCriteria().andNameEqualTo(importRecord.getName());
			if(DAOManager.getModelDAO().selectModelByExample(modelExample).size() == 0) {
				Model model = new Model();
				model.setDeleted(0);
				model.setName(importRecord.getName());
				model.setDescription(importRecord.getDescription());
				model.setMonthwarranty(Integer.parseInt(importRecord.getMonthwarranty()));
				DAOManager.getModelDAO().insertModel(model);
			}
		}
	}

}
