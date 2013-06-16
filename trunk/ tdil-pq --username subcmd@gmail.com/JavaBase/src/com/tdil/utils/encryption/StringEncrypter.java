package com.tdil.utils.encryption;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;


/**
 * Class <code>StringEncrypter</code> is a class that performs the
 * encryption/decryption of String with a configured password.
 *
 * @author Marcos Godoy
 */

public final class StringEncrypter {

	protected static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
	private static final String DES_ENCRYPTION_SCHEME = "DES";

	private KeySpec keySpec;
	private SecretKeyFactory keyFactory;
	private Cipher encrypter;
	private Cipher decrypter;
	private SecretKey key;

	private static final String UNICODE_FORMAT = "UTF8";

	public StringEncrypter(String encryptionScheme, String encryptionKey) throws EncryptionException {

		if (encryptionKey == null) {
			throw new IllegalArgumentException("encryption key was null");
		}
		if (encryptionKey.trim().length() < 24) {
			throw new IllegalArgumentException("encryption key was less than 24 characters");
		}

		try {
			byte[] keyAsBytes = encryptionKey.getBytes(UNICODE_FORMAT);
			if (encryptionScheme.equals(DESEDE_ENCRYPTION_SCHEME)) {
				keySpec = new DESedeKeySpec(keyAsBytes);
			} else if (encryptionScheme.equals(DES_ENCRYPTION_SCHEME)) {
				keySpec = new DESKeySpec(keyAsBytes);
			} else {
				throw new IllegalArgumentException("Encryption scheme not supported: " + encryptionScheme);
			}
			keyFactory = SecretKeyFactory.getInstance(encryptionScheme);
			key = keyFactory.generateSecret(keySpec);
			encrypter = Cipher.getInstance(encryptionScheme);
			encrypter.init(Cipher.ENCRYPT_MODE, key);
			decrypter = Cipher.getInstance(encryptionScheme);
			decrypter.init(Cipher.DECRYPT_MODE, key);
		} catch (InvalidKeyException e) {
			throw new EncryptionException(e);
		} catch (UnsupportedEncodingException e) {
			throw new EncryptionException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new EncryptionException(e);
		} catch (NoSuchPaddingException e) {
			throw new EncryptionException(e);
		} catch (InvalidKeySpecException e) {
			throw new EncryptionException(e);
		}

	}

	public byte[] encrypt(byte[] unencryptedString) throws EncryptionException {
		if (unencryptedString == null || unencryptedString.length == 0) {
			throw new IllegalArgumentException("unencrypted string was null or empty");
		}
		try {
			return encrypter.doFinal(unencryptedString);
		} catch (Exception e) {
			throw new EncryptionException(e);
		}
	}

	public byte[] decrypt(byte[] encryptedString) throws EncryptionException {
		if (encryptedString == null || encryptedString.length <= 0) {
			throw new IllegalArgumentException("encrypted string was null or empty");
		}
		try {
			return decrypter.doFinal(encryptedString);
		} catch (Exception e) {
			throw new EncryptionException(e);
		}
	}
}