package com.tdil.cache.blob;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.users.User;
import com.tdil.utils.ImageUtils;

public class BlobLocalDiskCache {

	private static final char separator = '-';
	private static String diskBlobLocation;
	private static Map<String, BlobResolver> typesToResolvers = new ConcurrentHashMap<String, BlobResolver>(); 
	
	public static void addBlobResolver(String type, BlobResolver blobResolver) {
		typesToResolvers.put(type, blobResolver);
	}
	
	public static BlobLocalData getBlobThumbnail(String type, int id, String width, String height, String constrain, int version, String ext, User user) {
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
		String localDataLocation = makeFileName(type, id, width, height, constrain, version, ext, blobResolver);
		File local = new File(localDataLocation);
		if (!local.exists()) {
			try {
				BlobLocalData blobLocalData = getBlob(type, id, version, ext, user);
				ImageUtils.createThumbnail(blobLocalData, localDataLocation, width, height, "true".equals(constrain));
				local = new File(localDataLocation);
			} catch (Exception e) {
				getLog().error(e.getMessage(), e);
				return null;
			}
			local = new File(localDataLocation);
		}
		// TODO quizas deberia tener un margen de x bytes para mantenerlo en memoria
		try {
			return new BlobLocalData(ext, localDataLocation, new FileInputStream(local));
		} catch (FileNotFoundException e) {
			getLog().error(e.getMessage(), e);
			return null;
		}
	}
	
	public static BlobLocalData getBlob(String type, int id, int version, String ext, User user) {
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
		String localDataLocation = makeFileName(type, id, version, ext, blobResolver);
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
			return new BlobLocalData(ext, localDataLocation, new FileInputStream(local));
		} catch (FileNotFoundException e) {
			getLog().error(e.getMessage(), e);
			return null;
		}
	}
	
	private static String makeFileName(String type, int id, int version, String ext, BlobResolver blobResolver) {
		StringBuffer sb = new StringBuffer();
		sb.append(diskBlobLocation).append('/');
		sb.append(type).append(separator);
		sb.append(id).append('.').append(version).append('.').append(ext);
		return sb.toString();
	}
	
	private static String makeFileName(String type, int id, String width, String height, String max, int version, String ext, BlobResolver blobResolver) {
		StringBuffer sb = new StringBuffer();
		sb.append(diskBlobLocation).append('/');
		sb.append(type).append(separator);
		sb.append(id).append('.').append(version).append('-').append(width).append('x').append(height).append('.').append("true".equals(max) ? "1" : "0").append('.').append(ext);
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
