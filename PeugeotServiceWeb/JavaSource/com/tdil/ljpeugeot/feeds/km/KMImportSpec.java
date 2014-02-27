package com.tdil.ljpeugeot.feeds.km;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.supercsv.cellprocessor.ift.CellProcessor;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.feeds.ImportSpec;
import com.tdil.ljpeugeot.feeds.KMImportThread;
import com.tdil.ljpeugeot.model.Advice;
import com.tdil.ljpeugeot.model.DataImport;
import com.tdil.ljpeugeot.model.KmData;
import com.tdil.ljpeugeot.model.Model;
import com.tdil.ljpeugeot.model.ModelExample;
import com.tdil.ljpeugeot.model.Vehicle;
import com.tdil.ljpeugeot.model.VehicleExample;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;

public class KMImportSpec implements ImportSpec {
	
	public static final String TYPE = "KM";
	
	private CellProcessor[] processors;
	
	private Map<String, Model> modelsTable = new HashMap<String, Model>();
	
	public KMImportSpec(CellProcessor[] processors) {
		super();
		this.processors = processors;
	}

	public KMImportSpec() {
		processors = new CellProcessor[] { null, // state
					null
			};
	}
	
	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public Class getRecordClass() {
		return KMImportRecord.class;
	}

	@Override
	public CellProcessor[] getCellProcessor() {
		return processors;
	}

	@Override
	public void processRow(Object importRecord, DataImport dataImport) throws Exception {
		GenericTransactionExecutionService.getInstance().execute(new ImportKM((KmData)importRecord, dataImport, this));
	}
	
    @Override
	public void importFinished(DataImport dataImport) throws Exception {
		System.out.println("TODO borrar los datos de importaciones viejas?");
	}
	
	public static final class ImportKM implements TransactionalAction {
		private DataImport dataImport;
		private KmData importRecord;
		private KMImportSpec importSpec;
		
		public ImportKM() {
		}
		
		public ImportKM(KmData importRecord, DataImport dataImport, KMImportSpec importSpec) {
			super();
			this.importRecord = importRecord;
			this.dataImport = dataImport;
			this.importSpec = importSpec;
		}
		public void executeInTransaction() throws SQLException {
			VehicleExample vehicleExample = new VehicleExample();
			vehicleExample.createCriteria().andDomainEqualTo(importRecord.getDominio());
			List<Vehicle> vehicles = DAOManager.getVehicleDAO().selectVehicleByExample(vehicleExample);
			for (Vehicle vehicle : vehicles) {
				boolean modified = completeVehicleData(vehicle, importRecord);
				AdviceEvaluationResult first = needsFirstAdvice(vehicle, importRecord);
				if (first.needsAdvice()) {
					sendFirstAdvice(vehicle, importRecord, first);
				} else {
					AdviceEvaluationResult second = needsSecondAdvice(vehicle, importRecord);
					if (second.needsAdvice()) {
						sendSecondAdvice(vehicle, importRecord, second);
					} else {
						AdviceEvaluationResult third = needsThirdAdvice(vehicle, importRecord);
						if (third.needsAdvice()) {
							sendThirdAdvice(vehicle, importRecord, third);
						} else {
							if (modified) {
								DAOManager.getVehicleDAO().updateVehicleByPrimaryKey(vehicle);
							}
						}
					}
				}
			}
		}
		public boolean completeVehicleData(Vehicle vehicle, KmData importRecord2) {
			boolean modified = false;
			if (vehicle.getIdModel() == null || vehicle.getIdModel() == 0) {
				String lookup = importRecord2.getModelo().toLowerCase().trim();
				if (importSpec.getModelsTable().containsKey(lookup)) {
					vehicle.setIdModel(importSpec.getModelsTable().get(lookup).getId());
					modified = true;
				} else {
					getLog().error("KMImport no se pudo encontrar el model para " + importRecord2.getModelo());
				}
			}
			if (vehicle.getPurchasedate() == null) {
				vehicle.setPurchasedate(importRecord2.getFechaalta());
				modified = true;
			}
			if (vehicle.getLastservicedate() == null) {
				vehicle.setLastservicedate(importRecord2.getFechaalta());
				modified = true;
			}
			if (vehicle.getKm() == null) {
				vehicle.setKm(0);
				modified = true;
			}
			if (vehicle.getLastservicekm() == null) {
				vehicle.setLastservicekm(0);
				modified = true;
			}
			return modified;
		}

		public AdviceEvaluationResult needsFirstAdvice(Vehicle vehicle, KmData importRecord2) {
			if(vehicle.getNeedsadvice1() == 1 ) {
				return new AdviceEvaluationResult(false, null, 0);
			}
			
			// si pasaron 10 meses, aviso
			Calendar today = Calendar.getInstance();
			Calendar lastService = Calendar.getInstance();
			lastService.setTime(vehicle.getLastservicedate());
			Calendar limitFirst = Calendar.getInstance();
			limitFirst.setTime(vehicle.getLastservicedate());
			limitFirst.add(Calendar.MONTH, 10);
			
			Calendar limitSecond = Calendar.getInstance();
			limitSecond.setTime(vehicle.getLastservicedate());
			limitSecond.add(Calendar.MONTH, 11);
			if (today.after(limitFirst)) {
				if (limitSecond.after(today)) {
					// dejo la fecha en 12 meses para avisar hasta que dia debe realizar el service
					limitSecond.add(Calendar.MONTH, 1); 
					return new AdviceEvaluationResult(true, limitFirst.getTime(), 0);
				}
			}
			
			int kmFromLastServiceActual = vehicle.getKm() - vehicle.getLastservicekm();
			int kmFromLastServiceNew = importRecord2.getKm() - vehicle.getLastservicekm();
			if (kmFromLastServiceActual < 8000) {
				// Si los km actuales eran menores a 8000
				if (kmFromLastServiceNew >= 8000 && kmFromLastServiceNew < 10000) {
					// Y los km nuevos son menores a 10000
					return new AdviceEvaluationResult(true, null, vehicle.getLastservicekm() + 12000);
				}
			}
			return new AdviceEvaluationResult(false, null, 0);
		}
		
		public AdviceEvaluationResult needsSecondAdvice(Vehicle vehicle, KmData importRecord2) {
			if(vehicle.getNeedsadvice2() == 1) {
				return new AdviceEvaluationResult(false, null, 0);
			}
			// si pasaron 11 meses a , aviso
			Calendar today = Calendar.getInstance();
			Calendar lastService = Calendar.getInstance();
			lastService.setTime(vehicle.getLastservicedate());
			Calendar limitFirst = Calendar.getInstance();
			limitFirst.setTime(vehicle.getLastservicedate());
			limitFirst.add(Calendar.MONTH, 11);
			
			Calendar limitSecond = Calendar.getInstance();
			limitSecond.setTime(vehicle.getLastservicedate());
			limitSecond.add(Calendar.MONTH, 11);
			limitSecond.add(Calendar.DATE, 15);
			if (today.after(limitFirst)) {
				if (limitSecond.after(today)) {
					// dejo la fecha en 12 meses para avisar hasta que dia debe realizar el service
					limitSecond.add(Calendar.DATE, -15);
					limitSecond.add(Calendar.MONTH, 1); 
					return new AdviceEvaluationResult(true, limitSecond.getTime(), 0);
				}
			}
			
			int kmFromLastServiceActual = vehicle.getKm() - vehicle.getLastservicekm();
			int kmFromLastServiceNew = importRecord2.getKm() - vehicle.getLastservicekm();
			if (kmFromLastServiceActual < 10000) {
				// Si los km actuales eran menores a 800
				if (kmFromLastServiceNew >= 10000 && kmFromLastServiceNew < 11500) {
					// Y los km nuevos son menores a 10000
					return new AdviceEvaluationResult(true, null, vehicle.getLastservicekm() + 12000);
				}
			}
			return new AdviceEvaluationResult(false, null, 0);
		}

		public AdviceEvaluationResult needsThirdAdvice(Vehicle vehicle, KmData importRecord2) throws SQLException {
			if(vehicle.getNeedsadvice3() == 1) {
				return new AdviceEvaluationResult(false, null, 0);
			}
			if (vehicle.getIdModel() != null) {
				// me fijo si la garantia expiro
				Model model = DAOManager.getModelDAO().selectModelByPrimaryKey(vehicle.getIdModel());
				Calendar expiration = Calendar.getInstance();
				expiration.setTime(vehicle.getPurchasedate());
				
				expiration.add(Calendar.MONTH, model.getMonthwarranty());
				if (Calendar.getInstance().after(expiration)) {
					vehicle.setWarrantyexpired(1);
					DAOManager.getVehicleDAO().updateVehicleByPrimaryKey(vehicle);
					return new AdviceEvaluationResult(false, null, 0);
				}
				// si expiro por km
				if (model.getKmwarranty()!= 0 && importRecord2.getKm() >= model.getKmwarranty()) {
					vehicle.setWarrantyexpired(1);
					DAOManager.getVehicleDAO().updateVehicleByPrimaryKey(vehicle);
					return new AdviceEvaluationResult(false, null, 0);
				}
			}
			
			// si pasaron 11 meses, aviso
			Calendar today = Calendar.getInstance();
			Calendar lastService = Calendar.getInstance();
			lastService.setTime(vehicle.getLastservicedate());
			Calendar limitFirst = Calendar.getInstance();
			limitFirst.setTime(vehicle.getLastservicedate());
			limitFirst.add(Calendar.MONTH, 11);
			limitFirst.add(Calendar.DATE, 15);
			
			Calendar limitSecond = Calendar.getInstance();
			limitSecond.setTime(vehicle.getLastservicedate());
			limitSecond.add(Calendar.MONTH, 12);
			if (today.after(limitFirst)) {
				if (limitSecond.after(today)) {
					return new AdviceEvaluationResult(true, limitSecond.getTime(), 0);
				}
			}
			
			int kmFromLastServiceActual = vehicle.getKm() - vehicle.getLastservicekm();
			int kmFromLastServiceNew = importRecord2.getKm() - vehicle.getLastservicekm();
			if (kmFromLastServiceActual < 11500) {
				// Si los km actuales eran menores a 800
				if (kmFromLastServiceNew >= 11500 && kmFromLastServiceNew < 12000) {
					// Y los km nuevos son menores a 10000
					return new AdviceEvaluationResult(true, null, vehicle.getLastservicekm() + 12000);
				}
			}
			return new AdviceEvaluationResult(false, null, 0);
		}
		
	}

	private static void sendFirstAdvice(Vehicle vehicle, KmData importRecord, AdviceEvaluationResult adviceEvaluationResult) throws SQLException {
		vehicle.setNeedsadvice(1);
		vehicle.setNeedsadvice1(1);
		vehicle.setNeedsadvice1date(adviceEvaluationResult.getDate());
		DAOManager.getVehicleDAO().updateVehicleByPrimaryKey(vehicle);
		int adviceNumber = 1;
		createAdvice(vehicle, importRecord, adviceNumber, adviceEvaluationResult);
	}
	
	private static void sendSecondAdvice(Vehicle vehicle, KmData importRecord, AdviceEvaluationResult adviceEvaluationResult) throws SQLException {
		vehicle.setNeedsadvice(1);
		vehicle.setNeedsadvice2(1);
		vehicle.setNeedsadvice2date(adviceEvaluationResult.getDate());
		DAOManager.getVehicleDAO().updateVehicleByPrimaryKey(vehicle);
		int adviceNumber = 2;
		createAdvice(vehicle, importRecord, adviceNumber, adviceEvaluationResult);
	}
	private static void sendThirdAdvice(Vehicle vehicle, KmData importRecord, AdviceEvaluationResult adviceEvaluationResult) throws SQLException {
		vehicle.setNeedsadvice(1);
		vehicle.setNeedsadvice3(1);
		vehicle.setNeedsadvice3date(adviceEvaluationResult.getDate());
		DAOManager.getVehicleDAO().updateVehicleByPrimaryKey(vehicle);
		int adviceNumber = 3;
		createAdvice(vehicle, importRecord, adviceNumber, adviceEvaluationResult);
	}

	private static void createAdvice(Vehicle vehicle, KmData importRecord, int adviceNumber, AdviceEvaluationResult adviceEvaluationResult) throws SQLException {
		Advice advice = new Advice();
		advice.setAdvisedate(new Date());
		advice.setAdvisenumber(adviceNumber);
		advice.setDeleted(0);
		advice.setIdVechicle(vehicle.getId());
		advice.setIsread(0);
		advice.setKm(adviceEvaluationResult.getKm());
		advice.setServicedate(adviceEvaluationResult.getDate());
		DAOManager.getAdviceDAO().insertAdvice(advice);
	}

	public Map<String, Model> getModelsTable() {
		return modelsTable;
	}

	public void setModelsTable(Map<String, Model> modelsTable) {
		this.modelsTable = modelsTable;
	}
	

	private static Logger getLog() {
		return LoggerProvider.getLogger(KMImportSpec.class);
	}
}
