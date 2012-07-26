package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.ToggleDeletedFlagForm;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Geo2Example;
import com.tdil.tuafesta.model.valueobjects.GeoLevelValueObject;

public class GeoLevelForm extends TransactionalValidationForm implements ToggleDeletedFlagForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int level = 2;
	private int objectId;
	private String nombre;
	private int geo2Id;
	private int geo3Id;
	
	private String nombreSearch;
	private int levelSearch = 2;
	private int parentIdSearch;
	
	private List<GeoLevelValueObject> search = new ArrayList<GeoLevelValueObject>();
	
	private static final Logger LOG = LoggerProvider.getLogger(GeoLevelForm.class);
	
	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.nombre = null;
		this.geo2Id= 0;
		this.geo3Id= 0;
		this.level = 2;
		
		this.nombreSearch = null;
		this.levelSearch = 2;
		this.parentIdSearch = 0;
	}

	@Override
	public void init() throws SQLException {
	}

	/** Used for delete */
	public void resetAfterDelete() throws SQLException {
		this.reset();
	}
	public void initForDeleteWith(int userId) throws SQLException {
		this.objectId = userId;
	}
	public void validateForToggleDeletedFlag(ValidationError validationError) {
		// TODO Auto-generated method stub
	}
	public void toggleDeletedFlag() throws SQLException, ValidationException {
		
	}
	
	@Override
	public void initWith(int id) throws SQLException {
		
	}

	@Override
	public void basicValidate(ValidationError validationError) {
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}

	@Override
	public void save() throws SQLException, ValidationException {
		
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int id) {
		this.objectId = id;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String name) {
		this.nombre = name;
	}

	public String getNombreSearch() {
		return nombreSearch;
	}

	public void setNombreSearch(String nombreSearch) {
		this.nombreSearch = nombreSearch;
	}

	public int getLevelSearch() {
		return levelSearch;
	}

	public void setLevelSearch(int levelSearch) {
		this.levelSearch = levelSearch;
	}

	public int getParentIdSearch() {
		return parentIdSearch;
	}

	public void setParentIdSearch(int parentIdSearch) {
		this.parentIdSearch = parentIdSearch;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<GeoLevelValueObject> getSearch() {
		return search;
	}

	public void setSearch(List<GeoLevelValueObject> search) {
		this.search = search;
	}

	public int getGeo2Id() {
		return geo2Id;
	}

	public void setGeo2Id(int geo2Id) {
		this.geo2Id = geo2Id;
	}

	public int getGeo3Id() {
		return geo3Id;
	}

	public void setGeo3Id(int geo3Id) {
		this.geo3Id = geo3Id;
	}

	public void search() {
		try {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					if (GeoLevelForm.this.getLevelSearch() == 2) {
						GeoLevelForm.this.setSearch(DAOManager.getGeo2DAO().searchGeoLevelsByNombre("%" + GeoLevelForm.this.getNombreSearch().toUpperCase() + "%"));
					} else {
						if (GeoLevelForm.this.getLevelSearch() == 3) {
							GeoLevelForm.this.setSearch(DAOManager.getGeo3DAO().searchGeoLevelsByNombre("%" + GeoLevelForm.this.getNombreSearch().toUpperCase() + "%"));
						} else {
							GeoLevelForm.this.setSearch(DAOManager.getGeo4DAO().searchGeoLevelsByNombre("%" + GeoLevelForm.this.getNombreSearch().toUpperCase() + "%"));
						}	
					}
				}
			});
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		} 
	}

}
