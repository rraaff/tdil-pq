package com.tdil.cache.blob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.users.User;

public class BlobLocalDiskCache {

	private static final char separator = '-';
	private static String diskBlobLocation;
	private static Map<String, BlobResolver> typesToResolvers = new ConcurrentHashMap<String, BlobResolver>(); 
	
	public static void addBlobResolver(String type, BlobResolver blobResolver) {
		typesToResolvers.put(type, blobResolver);
	}
	
	public static BlobLocalData getBlob(String type, int id, int version, String filename, User user) {
		BlobResolver blobResolver = typesToResolvers.get(type);
		if (blobResolver == null) {
			getLog().error("No se encontro un blob resolver para " + type);
			return null;
		}
		if (!blobResolver.canAccess(user)) {
			getLog().error("El usuario " + (user == null ? "null" : user.getId()) + "no tiene permisos acceder a los datos " + type);
			return null;
		}
		// TODO sincronizar el load...
		String localDataLocation = makeFileName(type, id, version, filename, blobResolver);
		File local = new File(localDataLocation);
		if (!local.exists()) {
			try {
				blobResolver.load(id, type, version, local);
			} catch (Exception e) {
				getLog().error(e.getMessage(), e);
				return null;
			}
			local = new File(localDataLocation);
		}
		// TODO quizas deberia tener un margen de x bytes para mantenerlo en memoria
		try {
			return new BlobLocalData(filename, localDataLocation, new FileInputStream(local));
		} catch (FileNotFoundException e) {
			getLog().error(e.getMessage(), e);
			return null;
		}
	}
	
	private static String makeFileName(String type, int id, int version, String filename, BlobResolver blobResolver) {
		StringBuffer sb = new StringBuffer();
		sb.append(diskBlobLocation).append("/");
		sb.append(type).append(separator);
		sb.append(version).append('.').append(FilenameUtils.getExtension(filename));
		return sb.toString();
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(BlobLocalDiskCache.class);
	}

	public static String getDiskBlobLocation() {
		return diskBlobLocation;
	}

	public static void setDiskBlobLocation(String localDataPath) {
		BlobLocalDiskCache.diskBlobLocation = localDataPath;
	}
}
