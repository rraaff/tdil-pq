package com.tdil.lojack.vlu;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;

public class CVSExampleGeneration {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Random random = new Random();
		FileOutputStream fout= new FileOutputStream("/home/mgodoy/demo.csv");
		fout.write("dni,domain,message\n".getBytes());
		
		for (int i = 20000000; i < 21000000; i++) {
			boolean message = random.nextBoolean();
			fout.write((String.valueOf(i) + "," + RandomStringUtils.randomAlphanumeric(6) + "," + (message? RandomStringUtils.randomAlphanumeric(25) : "") + "\n").getBytes());
		}
		fout.close();
	}

}
