package com.tdil.ljpeugeot.test;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.model.Model;
import com.tdil.ljpeugeot.model.ModelExample;
import com.tdil.ljpeugeot.model.Vehicle;
import com.tdil.ljpeugeot.model.WebsiteUser;
import com.tdil.ljpeugeot.model.WebsiteUserExample;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationException;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;

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
					Vehicle v = new Vehicle();
					v.setDomain("CZP075");
					v.setIdWebsiteuser(wu.getId());
					v.setIdModel(model.getId());
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
