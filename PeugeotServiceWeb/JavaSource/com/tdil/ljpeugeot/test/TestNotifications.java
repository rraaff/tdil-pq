package com.tdil.ljpeugeot.test;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import com.tdil.ibatis.IBatisManager;
import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.feeds.km.KMImportRecord;
import com.tdil.ljpeugeot.feeds.km.KMImportSpec;
import com.tdil.ljpeugeot.model.Model;
import com.tdil.ljpeugeot.model.ModelExample;
import com.tdil.ljpeugeot.model.Vehicle;

public class TestNotifications extends TestCase {
	
	
	public void testAdvice1() {
		Vehicle v = new Vehicle();
		v.setDomain("PEPE");
		v.setKm(7500);
		v.setLastservicekm(0);
		v.setLastservicedate(new Date());
		v.setAdvice1sent(0);
		v.setNeedsadvice1(0);
		v.setNeedsadvice2(0);
		v.setNeedsadvice3(0);
		
		KMImportRecord rec = new KMImportRecord();
		
		// no paso de 8k
		rec.setKm("7900");
		assertFalse("No deberia necesitar el primer aviso", new KMImportSpec.ImportKM().needsFirstAdvice(v, rec));
		
		// paso de menos de 8k a mas
		rec.setKm("8000");
		assertTrue("Deberia necesitar el primer aviso", new KMImportSpec.ImportKM().needsFirstAdvice(v, rec));
		
		// si ya paso, no deberia necesitar mas el aviso
		v.setKm(8200);
		rec.setKm("8500");
		assertFalse("No deberia necesitar el primer aviso", new KMImportSpec.ImportKM().needsFirstAdvice(v, rec));
		
		// si no llego, deberia dar false
		v.setLastservicekm(8000);
		assertFalse("No deberia necesitar el primer aviso", new KMImportSpec.ImportKM().needsFirstAdvice(v, rec));
		
		rec.setKm("7900");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -10);
		cal.add(Calendar.DATE, -1);
		v.setLastservicedate(cal.getTime());
		// no tiene el km pero si paso el tiempo
		assertTrue("Deberia necesitar el primer aviso", new KMImportSpec.ImportKM().needsFirstAdvice(v, rec));
		
	}
	
	public void testAdvice1AlreadyProcessed() {
		Vehicle v = new Vehicle();
		v.setDomain("PEPE");
		v.setKm(7500);
		v.setLastservicekm(0);
		v.setLastservicedate(new Date());
		v.setAdvice1sent(0);
		v.setNeedsadvice1(1);
		v.setNeedsadvice2(0);
		v.setNeedsadvice3(0);
		
		KMImportRecord rec = new KMImportRecord();
		
		rec.setKm("7900");
		// no paso de 8k
		assertFalse("No deberia necesitar el primer aviso", new KMImportSpec.ImportKM().needsFirstAdvice(v, rec));
		
		rec.setKm("8000");
		// paso de menos de 8k a mas
		assertFalse("Deberia necesitar el primer aviso", new KMImportSpec.ImportKM().needsFirstAdvice(v, rec));

		// si ya paso, no deberia necesitar mas el aviso
		v.setKm(8200);
		rec.setKm("8500");
		assertFalse("No deberia necesitar el primer aviso", new KMImportSpec.ImportKM().needsFirstAdvice(v, rec));
		
		v.setKm(7500);
		rec.setKm("7900");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -10);
		cal.add(Calendar.DATE, -1);
		v.setLastservicedate(cal.getTime());
		// no tiene el km pero si paso el tiempo
		assertFalse("Deberia necesitar el primer aviso", new KMImportSpec.ImportKM().needsFirstAdvice(v, rec));
		
	}
	
	public void testAdvice2() {
		Vehicle v = new Vehicle();
		v.setDomain("PEPE");
		v.setKm(7500);
		v.setLastservicekm(0);
		v.setLastservicedate(new Date());
		v.setAdvice1sent(0);
		v.setNeedsadvice1(1);
		v.setNeedsadvice2(0);
		v.setNeedsadvice3(0);
		
		KMImportRecord rec = new KMImportRecord();
		
		rec.setKm("10900");
		// paso a 11, deberia tener aviso
		assertFalse("No deberia necesitar el primer aviso", new KMImportSpec.ImportKM().needsFirstAdvice(v, rec));
		assertTrue("Deberia necesitar el segundo aviso", new KMImportSpec.ImportKM().needsSecondAdvice(v, rec));
		
		rec.setKm("8200");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -11);
		cal.add(Calendar.DATE, -1);
		v.setLastservicedate(cal.getTime());
		// no tiene el km pero si paso el tiempo
		assertFalse("No deberia necesitar el primer aviso", new KMImportSpec.ImportKM().needsFirstAdvice(v, rec));
		assertTrue("Deberia necesitar el segundo aviso", new KMImportSpec.ImportKM().needsSecondAdvice(v, rec));
		
	}
	public void testAdvice2Processed() {
		Vehicle v = new Vehicle();
		v.setDomain("PEPE");
		v.setKm(7500);
		v.setLastservicekm(0);
		v.setLastservicedate(new Date());
		v.setAdvice1sent(0);
		v.setNeedsadvice1(1);
		v.setNeedsadvice2(1);
		v.setNeedsadvice3(0);
		
		KMImportRecord rec = new KMImportRecord();
		
		rec.setKm("10900");
		// paso a 11, deberia tener aviso
		assertFalse("No deberia necesitar el primer aviso", new KMImportSpec.ImportKM().needsFirstAdvice(v, rec));
		assertFalse("Deberia necesitar el segundo aviso", new KMImportSpec.ImportKM().needsSecondAdvice(v, rec));
	}
	
	
	public void testAdvice3() throws SQLException {
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
		
		Vehicle v = new Vehicle();
		v.setDomain("PEPE");
		v.setIdModel(model.getId());
		v.setPurchasedate(new Date());
		v.setKm(7500);
		v.setLastservicekm(0);
		v.setLastservicedate(new Date());
		v.setAdvice1sent(0);
		v.setNeedsadvice1(1);
		v.setNeedsadvice2(0);
		v.setNeedsadvice3(0);
		
		KMImportRecord rec = new KMImportRecord();
		
		rec.setKm("11500");
		// paso a 11, deberia tener aviso
		assertFalse("No deberia necesitar el primer aviso", new KMImportSpec.ImportKM().needsFirstAdvice(v, rec));
		assertFalse("no Deberia necesitar el segundo aviso", new KMImportSpec.ImportKM().needsSecondAdvice(v, rec));
		assertTrue("Deberia necesitar el tercer aviso", new KMImportSpec.ImportKM().needsThirdAdvice(v, rec));
		
		//si me fui
		rec.setKm("12000");
		assertFalse("No deberia necesitar el primer aviso", new KMImportSpec.ImportKM().needsFirstAdvice(v, rec));
		assertFalse("no Deberia necesitar el segundo aviso", new KMImportSpec.ImportKM().needsSecondAdvice(v, rec));
		assertFalse("no Deberia necesitar el tercer aviso", new KMImportSpec.ImportKM().needsThirdAdvice(v, rec));
		
		rec.setKm("8200");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -11);
		cal.add(Calendar.DATE, -17);
		v.setLastservicedate(cal.getTime());
		// no tiene el km pero si paso el tiempo
		assertFalse("No deberia necesitar el primer aviso", new KMImportSpec.ImportKM().needsFirstAdvice(v, rec));
		assertFalse("Deberia necesitar el segundo aviso", new KMImportSpec.ImportKM().needsSecondAdvice(v, rec));
		assertTrue("Deberia necesitar el tercer aviso", new KMImportSpec.ImportKM().needsThirdAdvice(v, rec));
		
		// si pasaron mas de 24 meses, no hay tercer aviso
		Calendar oldDate = Calendar.getInstance();
		oldDate.add(Calendar.MONTH, -25);
		v.setPurchasedate(oldDate.getTime());
		assertFalse("no Deberia necesitar el tercer aviso", new KMImportSpec.ImportKM().needsThirdAdvice(v, rec));
		
	}
	
	public void testAdvice3AlreadyProcessed() throws SQLException {
		Vehicle v = new Vehicle();
		v.setDomain("PEPE");
		v.setKm(7500);
		v.setLastservicekm(0);
		v.setLastservicedate(new Date());
		v.setAdvice1sent(0);
		v.setNeedsadvice1(1);
		v.setNeedsadvice2(0);
		v.setNeedsadvice3(1);
		
		KMImportRecord rec = new KMImportRecord();
		
		rec.setKm("11500");
		// paso a 11, deberia tener aviso
		assertFalse("No deberia necesitar el primer aviso", new KMImportSpec.ImportKM().needsFirstAdvice(v, rec));
		assertFalse("no Deberia necesitar el segundo aviso", new KMImportSpec.ImportKM().needsSecondAdvice(v, rec));
		assertFalse("Deberia necesitar el tercer aviso", new KMImportSpec.ImportKM().needsThirdAdvice(v, rec));
	}
}
