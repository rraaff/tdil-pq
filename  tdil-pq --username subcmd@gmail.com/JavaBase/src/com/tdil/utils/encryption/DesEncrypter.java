package com.tdil.utils.encryption;

import com.tdil.utils.encryption.StringEncrypter.EncryptionException;



/**
 * Class <code>DesEncrypter</code> is the class that provides the
 * encrypting/decrypting service to the cea application.
 * This class acts as a Facade, because the real implementation is
 * performed by the StringEncrypter class.
 * This class provides the encripcion via a password, and the same
 * password must be use for the data decryption.
 * 
 * @author Marcos Godoy
 */
public final class DesEncrypter {

	private StringEncrypter stringEncrypter;

	/**
	 * Answers a new instance of the Facade. The password to use is
	 * the encryptionKey.  
	 */
	public DesEncrypter(String encryptionKey) throws Exception {
		stringEncrypter = new StringEncrypter(StringEncrypter.DESEDE_ENCRYPTION_SCHEME, encryptionKey);
	}

	/**
	 * Encrypts the string str with the configured password.
	 */
	public byte[] encrypt(byte[] str) throws EncryptionException {
		return stringEncrypter.encrypt(str);
	}
	/**
	 * Decrypts the string str with the configured password.
	 */
	public byte[] decrypt(byte[] str) throws EncryptionException {
		return stringEncrypter.decrypt(str);
	}
	
	public static void main(String[] args) throws Exception {
		DesEncrypter test = new DesEncrypter("palodu es una paloma con un nandu");
		byte[] enc = test.encrypt("esta es una prueba".getBytes());
		System.out.println(new String(enc));
		byte[] dec = test.decrypt(enc);
		System.out.println(new String(dec));
	}
}
