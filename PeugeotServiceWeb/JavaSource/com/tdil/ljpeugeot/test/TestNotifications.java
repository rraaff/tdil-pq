package com.tdil.ljpeugeot.test;

import java.util.Date;

import com.tdil.ljpeugeot.feeds.KMImportThread;
import com.tdil.ljpeugeot.feeds.km.KMImportRecord;
import com.tdil.ljpeugeot.feeds.km.KMImportSpec;
import com.tdil.ljpeugeot.model.Vehicle;

public class TestNotifications {
	
	public static void main(String[] args) {
		Vehicle v = new Vehicle();
		v.setDomain("PEPE");
		v.setKm(7500);
		v.setLastservicedate(new Date());
		v.setAdvice1sent(0);
		v.setNeedsadvice1(1);
		
		
		KMImportRecord rec = new KMImportRecord();
		rec.setKm("8100");
		
		System.out.println(new KMImportSpec.ImportKM().needsFirstAdvice(v, rec));
	}
}
