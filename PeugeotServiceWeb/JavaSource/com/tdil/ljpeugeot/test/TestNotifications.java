package com.tdil.ljpeugeot.test;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

import com.tdil.ljpeugeot.feeds.km.KMImportRecord;
import com.tdil.ljpeugeot.feeds.km.KMImportSpec;
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
	
	public void testAdvice3() {
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
		
	}
}
