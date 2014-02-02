package com.tdil.ljpeugeot.feeds.km;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.supercsv.cellprocessor.ift.CellProcessor;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.feeds.ImportSpec;
import com.tdil.ljpeugeot.model.DataImport;
import com.tdil.ljpeugeot.model.Vehicle;
import com.tdil.ljpeugeot.model.VehicleExample;
import com.tdil.struts.TransactionalAction;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;

public class KMImportSpec implements ImportSpec {
	
	public static final String TYPE = "DEALER";
	
	private CellProcessor[] processors;
	
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
		GenericTransactionExecutionService.getInstance().execute(new ImportKM((KMImportRecord)importRecord, dataImport));
	}
	
    @Override
	public void importFinished(DataImport dataImport) throws Exception {
		System.out.println("TODO borrar los datos de importaciones viejas?");
	}
	
	public static final class ImportKM implements TransactionalAction {
		private DataImport dataImport;
		private KMImportRecord importRecord;
		
		public ImportKM() {
		}
		
		public ImportKM(KMImportRecord importRecord, DataImport dataImport) {
			super();
			this.importRecord = importRecord;
			this.dataImport = dataImport;
		}
		public void executeInTransaction() throws SQLException {
			VehicleExample vehicleExample = new VehicleExample();
			vehicleExample.createCriteria().andDomainEqualTo(importRecord.getDomain());
			List<Vehicle> vehicles = DAOManager.getVehicleDAO().selectVehicleByExample(vehicleExample);
			if (!vehicles.isEmpty()) {
				Vehicle vehicle = vehicles.get(0);
				if (needsFirstAdvice(vehicle, importRecord)) {
//					sendFirstAdvice(vehicle);
				} else {
					if (needsSecondAdvice(vehicle, importRecord)) {
//						sendSecondAdvice(vehicle);
					} else {
						if (needsThirdAdvice(vehicle, importRecord)) {
//							sendThirdAdvice(vehicle);
						} 
					}
				}
			}
		}
		public boolean needsFirstAdvice(Vehicle vehicle, KMImportRecord importRecord2) {
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
			if (limitFirst.after(today)) {
				if (limitSecond.after(today)) {
					if (vehicle.getAdvice1sent() == 0) {
						// no le mande el aviso
						if(vehicle.getNeedsadvice1() == 1 ) {
							return true;
						}
					}
				}
			}
			
			int kmFromLastServiceActual = vehicle.getKm() - vehicle.getLastservicekm();
			int kmFromLastServiceNew = Integer.parseInt(importRecord2.getKm()) - vehicle.getLastservicekm();
			if (kmFromLastServiceActual < 8000) {
				// Si los km actuales eran menores a 800
				if (kmFromLastServiceNew > 8000 && kmFromLastServiceNew < 10000) {
					// Y los km nuevos son menores a 10000
					if (vehicle.getAdvice1sent() == 0) {
						// no le mande el aviso
						if(vehicle.getNeedsadvice1() == 1 ) {
							return true;
						}
					}
				}
			}
			return false;
		}
		
		public boolean needsSecondAdvice(Vehicle vehicle, KMImportRecord importRecord2) {
			// si pasaron 11 meses, aviso
			Calendar today = Calendar.getInstance();
			Calendar lastService = Calendar.getInstance();
			lastService.setTime(vehicle.getLastservicedate());
			Calendar limitFirst = Calendar.getInstance();
			limitFirst.setTime(vehicle.getLastservicedate());
			limitFirst.add(Calendar.MONTH, 11);
			
			Calendar limitSecond = Calendar.getInstance();
			limitSecond.setTime(vehicle.getLastservicedate());
			limitSecond.add(Calendar.MONTH, 12);
			if (limitFirst.after(today)) {
				if (limitSecond.after(today)) {
					if (vehicle.getAdvice2sent() == 0) {
						// no le mande el aviso
						if(vehicle.getNeedsadvice2() == 1 ) {
							return true;
						}
					}
				}
			}
			
			int kmFromLastServiceActual = vehicle.getKm() - vehicle.getLastservicekm();
			int kmFromLastServiceNew = Integer.parseInt(importRecord2.getKm()) - vehicle.getLastservicekm();
			if (kmFromLastServiceActual < 10000) {
				// Si los km actuales eran menores a 800
				if (kmFromLastServiceNew > 10000 && kmFromLastServiceNew < 11000) {
					// Y los km nuevos son menores a 10000
					if (vehicle.getAdvice2sent() == 0) {
						// no le mande el aviso
						if(vehicle.getNeedsadvice2() == 1 ) {
							return true;
						}
					}
				}
			}
			return false;
		}

		public boolean needsThirdAdvice(Vehicle vehicle, KMImportRecord importRecord2) {
			// si pasaron 11 meses, aviso
			Calendar today = Calendar.getInstance();
			Calendar lastService = Calendar.getInstance();
			lastService.setTime(vehicle.getLastservicedate());
			Calendar limitFirst = Calendar.getInstance();
			limitFirst.setTime(vehicle.getLastservicedate());
			limitFirst.add(Calendar.MONTH, 11);
			
			Calendar limitSecond = Calendar.getInstance();
			limitSecond.setTime(vehicle.getLastservicedate());
			limitFirst.add(Calendar.MONTH, 11);
			limitSecond.add(Calendar.DAY_OF_MONTH, 15);
			if (limitFirst.after(today)) {
				if (limitSecond.after(today)) {
					if (vehicle.getAdvice3sent() == 0) {
						// no le mande el aviso
						if(vehicle.getNeedsadvice3() == 1 ) {
							return true;
						}
					}
				}
			}
			
			int kmFromLastServiceActual = vehicle.getKm() - vehicle.getLastservicekm();
			int kmFromLastServiceNew = Integer.parseInt(importRecord2.getKm()) - vehicle.getLastservicekm();
			if (kmFromLastServiceActual < 11000) {
				// Si los km actuales eran menores a 800
				if (kmFromLastServiceNew > 11000 && kmFromLastServiceNew < 11500) {
					// Y los km nuevos son menores a 10000
					if (vehicle.getAdvice3sent() == 0) {
						// no le mande el aviso
						if(vehicle.getNeedsadvice3() == 1 ) {
							return true;
						}
					}
				}
			}
			return false;
		}
		
	}

}
