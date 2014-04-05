package com.tdil.lojack.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.daomanager.DAOManager;
import com.tdil.lojack.model.NativeApp;
import com.tdil.lojack.model.NativeAppExample;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.struts.ValidationException;
import com.tdil.subsystem.generic.GenericTransactionExecutionService;

public class LoJackService {

	private static final class GetNativeApp implements TransactionalActionWithResult<NativeApp> {
		private String code;
		public GetNativeApp(String code) {
			super();
			this.code = code;
		}
		public NativeApp executeInTransaction() throws SQLException {
			NativeAppExample example = new NativeAppExample();
			example.createCriteria().andCodeEqualTo(this.code);
			List<NativeApp> nativeApps = DAOManager.getNativeAppDAO().selectNativeAppByExample(example);
			if (nativeApps.size() > 0) {
				return nativeApps.get(0);
			} else {
				return null;
			}
		}
	}
	
	private static final class UpdateNativeApp implements TransactionalAction {
		private String id;
		private String code;
		private String title;
		private String version;
		private String url;
		private String image;
		private String summary;

		public UpdateNativeApp(String id, String code, String title, String version, String url, String image, String summary) {
			super();
			this.id = id;
			this.code = code;
			this.title = title;
			this.version = version;
			this.url = url;
			this.summary = summary;
			this.image = image;
		}

		public void executeInTransaction() throws SQLException {
			int id = Integer.valueOf(this.id);
			if (id == 0) {
				NativeApp app = new NativeApp();
				app.setCode(this.code);
				app.setTitle(this.title);
				app.setVersion(this.version);
				app.setUrl(this.url);
				app.setImage(image);
				app.setSummary(this.summary);
				app.setDeleted(0);
				DAOManager.getNativeAppDAO().insertNativeApp(app);
			} else {
				NativeApp app = DAOManager.getNativeAppDAO().selectNativeAppByPrimaryKey(id);
				app.setTitle(this.title);
				app.setVersion(this.version);
				app.setUrl(this.url);
				app.setImage(image);
				app.setSummary(this.summary);
				DAOManager.getNativeAppDAO().updateNativeAppByPrimaryKey(app);
			}
		}
	}
	
	private static final class ToggleDeleteNativeApp implements TransactionalAction {
		private String id;

		public ToggleDeleteNativeApp(String id) {
			super();
			this.id = id;
		}

		public void executeInTransaction() throws SQLException {
			NativeApp app = DAOManager.getNativeAppDAO().selectNativeAppByPrimaryKey(Integer.valueOf(this.id));
			app.setDeleted(app.getDeleted() == 1 ? 0 : 1);
			DAOManager.getNativeAppDAO().updateNativeAppByPrimaryKey(app);
		}
	}
	
	private static final class GetNativeApps implements TransactionalActionWithResult<List<NativeApp>> {
		public GetNativeApps() {
			super();
		}
		public List<NativeApp> executeInTransaction() throws SQLException {
			return DAOManager.getNativeAppDAO().selectNativeAppByExample(new NativeAppExample());
		}
	}
	
	public static List<NativeApp> getNativeApps() {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetNativeApps());
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<NativeApp>();
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<NativeApp>();
		} 
	}
	
	public static NativeApp getNativeAppByCode(String code) {
		try {
			return GenericTransactionExecutionService.getInstance().execute(new GetNativeApp(code));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} 
	}
	
	public static void updateNativeApp(String idST, String code, String title, String version, String url, String image, String summary) {
		try {
			GenericTransactionExecutionService.getInstance().execute(new UpdateNativeApp(idST, code, title, version, url, image, summary));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
		} 
	}
	
	public static void toggleDeleteNativeApp(String idST) {
		try {
			GenericTransactionExecutionService.getInstance().execute(new ToggleDeleteNativeApp(idST));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
		} 
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(LoJackService.class);
	}
}
