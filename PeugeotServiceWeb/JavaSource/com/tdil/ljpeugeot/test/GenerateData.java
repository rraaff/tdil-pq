package com.tdil.ljpeugeot.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.feeds.ImportRunnable;
import com.tdil.ljpeugeot.feeds.dealer.DealerImportSpec;
import com.tdil.ljpeugeot.feeds.model.ModelImportSpec;
import com.tdil.ljpeugeot.model.ContactData;
import com.tdil.ljpeugeot.model.DataImport;
import com.tdil.ljpeugeot.model.Dealer;
import com.tdil.ljpeugeot.model.DealerExample;
import com.tdil.ljpeugeot.model.Model;
import com.tdil.ljpeugeot.model.ModelExample;
import com.tdil.ljpeugeot.model.State;
import com.tdil.ljpeugeot.model.Vehicle;
import com.tdil.ljpeugeot.model.WebsiteUser;
import com.tdil.ljpeugeot.model.WebsiteUserExample;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationException;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;
import com.tdil.utils.SystemPropertyCache;

public class GenerateData extends TestCase {
	
	
	public void testGenerate() throws SQLException, ValidationException {
		GenericTransactionExecutionService.getInstance().execute(new TransactionalAction() {
			@Override
			public void executeInTransaction() throws SQLException, ValidationException {
				
				boolean create = false;
				
				WebsiteUserExample wuExample = new WebsiteUserExample();
				wuExample.createCriteria().andEmailEqualTo("mgodoy@mgodoy.com");
				List<WebsiteUser> wus = DAOManager.getWebsiteUserDAO().selectWebsiteUserByExample(wuExample);
				WebsiteUser wu = null;
				if (wus.size() > 0) {
					wu = wus.get(0);
				} else {
					wu = new WebsiteUser();
					wu.setEmail("mgodoy@mgodoy.com");
					wu.setDeleted(0);
					int id_wu = DAOManager.getWebsiteUserDAO().insertWebsiteUser(wu);
					wu.setId(id_wu);
					create = true;
					
					ContactData contactData = new ContactData();
					contactData.setIdWebsiteuser(wu.getId());
					contactData.setContact1healthi("OSDE");
					contactData.setContact1name("Marcos Godoy");
					contactData.setContact1phone("02216412772");
					contactData.setContact1relation("RELATIVE");
					contactData.setContact1secword("pajaroslocos");
					contactData.setContact2name("Marcela Cioma");
					contactData.setContact2phone("02215346997");
					contactData.setContact2relation("RELATIVE");
					contactData.setContact3name("Vicky");
					contactData.setContact3phone("34343434");
					contactData.setContact3relation("COWORKER");
					contactData.setDeleted(0);
					DAOManager.getContactDataDAO().insertContactData(contactData);
				}
				
				ModelExample modelExample = new ModelExample();
				modelExample.createCriteria().andNameEqualTo("24m");
				List<Model> models = DAOManager.getModelDAO().selectModelByExample(modelExample);
				Model model = null;
				if (models.size() > 0) {
					model = models.get(0);
				} else {
					model = new Model();
					model.setName("24m");
					model.setDescription("Modelo de auto con 24 meses de garantia");
					model.setMonthwarranty(24);
					model.setDeleted(0);
					int id = DAOManager.getModelDAO().insertModel(model);
					model.setId(id);
				}
				
				if (create) {
					String oldTemp = SystemPropertyCache.getTempPath();
					SystemPropertyCache.put(com.tdil.utils.SystemPropertyCache.TEMP_PATH , "/home/mgodoy/icarus/workspace/thalamus/PeugeotServiceWeb/JavaSource/com/tdil/ljpeugeot/feeds/model");
					DataImport dataImport = new DataImport();
					dataImport.setProcessed(0);
					dataImport.setErrors(0);
					dataImport.setStatus("PENDING");
					dataImport.setType(ModelImportSpec.TYPE);
					dataImport.setFilename("model.csv");
					int id = DAOManager.getDataImportDAO().insertDataImport(dataImport);
					dataImport.setId(id);
					try {
						new ImportRunnable(dataImport, new ModelImportSpec()).processImport();
					} catch (FileNotFoundException e) {
						new RuntimeException(e);
					} catch (IOException e) {
						new RuntimeException(e);
					}
					dataImport.setProcessed(0);
					dataImport.setErrors(0);
					dataImport.setStatus("PENDING");
					dataImport.setType(DealerImportSpec.TYPE);
					dataImport.setFilename("dealer.csv");
					id = DAOManager.getDataImportDAO().insertDataImport(dataImport);
					dataImport.setId(id);
					SystemPropertyCache.put(com.tdil.utils.SystemPropertyCache.TEMP_PATH , "/home/mgodoy/icarus/workspace/thalamus/PeugeotServiceWeb/JavaSource/com/tdil/ljpeugeot/feeds/dealer");
					try {
						new ImportRunnable(dataImport, new DealerImportSpec()).processImport();
					} catch (FileNotFoundException e) {
						new RuntimeException(e);
					} catch (IOException e) {
						new RuntimeException(e);
					}
					SystemPropertyCache.put(com.tdil.utils.SystemPropertyCache.TEMP_PATH , oldTemp);
					
					DealerExample dealerExample = new DealerExample();
					List<Dealer> dealers = DAOManager.getDealerDAO().selectDealerByExample(dealerExample);
					
					Vehicle v = new Vehicle();
					v.setDomain("CZP075");
					v.setIdWebsiteuser(wu.getId());
					v.setIdModel(model.getId());
					v.setIdDealer(dealers.get(0).getId());
					v.setPurchasedate(new Date());
					v.setKm(0);
					v.setLastservicekm(0);
					v.setLastservicedate(new Date());
					v.setNeedsadvice(0);
					v.setAdvice1sent(0);
					v.setNeedsadvice1(0);
					v.setNeedsadvice2(0);
					v.setAdvice2sent(0);
					v.setNeedsadvice3(0);
					v.setAdvice3sent(0);
					v.setWarrantyexpired(0);
					v.setDeleted(0);
					DAOManager.getVehicleDAO().insertVehicle(v);
				}
			}
		});
	}
	
}
