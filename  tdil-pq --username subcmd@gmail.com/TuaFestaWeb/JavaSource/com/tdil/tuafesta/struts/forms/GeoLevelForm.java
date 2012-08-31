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
import com.tdil.tuafesta.dao.Geo2DAO;
import com.tdil.tuafesta.dao.Geo3DAO;
import com.tdil.tuafesta.dao.Geo4DAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Geo2;
import com.tdil.tuafesta.model.Geo2Example;
import com.tdil.tuafesta.model.Geo3;
import com.tdil.tuafesta.model.Geo3Example;
import com.tdil.tuafesta.model.Geo4;
import com.tdil.tuafesta.model.Geo4Example;
import com.tdil.tuafesta.model.valueobjects.GeoLevelValueObject;
import com.tdil.validations.FieldValidation;

public class GeoLevelForm extends TransactionalValidationForm implements ToggleDeletedFlagForm, GeoLevelSelectionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int level = 2;
	private int objectId;
	private String nombre;
	private int geo2Id;
	private String geo2Nombre;
	private int geo3Id;
	private String geo3Nombre;
	
	private String nombreSearch;
	private int levelSearch = 2;
	private int parentIdSearch;
	
	private List<Geo2> level2;
	private List<Geo3> level3;
	
	private List<GeoLevelValueObject> search = new ArrayList<GeoLevelValueObject>();
	
	private static String name_key = "GeoLevel.name";
	private static String name_duplicated_key = "DUPLICATED";
	
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
		Geo2DAO geo2dao = DAOManager.getGeo2DAO();
		Geo2Example geo2Example = new Geo2Example();
		geo2Example.setOrderByClause("nombre");
		setLevel2(geo2dao.selectGeo2ByExample(geo2Example));
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
	
	public void initWith(int level, int id) throws SQLException {
		setObjectId(id);
		this.level = level;
		if (level == 2) {
			Geo2 geo2 = DAOManager.getGeo2DAO().selectGeo2ByPrimaryKey(id);
			this.setNombre(geo2.getNombre());
			setGeo2Id(0);
			setGeo3Id(0);
		} else {
			if (level == 3) {
				Geo3 geo3 = DAOManager.getGeo3DAO().selectGeo3ByPrimaryKey(id);
				this.setNombre(geo3.getNombre());
				setGeo2Id(geo3.getGeo2Id());
				Geo2 geo2 = DAOManager.getGeo2DAO().selectGeo2ByPrimaryKey(geo3.getGeo2Id());
				setGeo2Nombre(geo2.getNombre());
				setGeo3Id(0);
			} else {
				Geo4 geo4 = DAOManager.getGeo4DAO().selectGeo4ByPrimaryKey(id);
				this.setNombre(geo4.getNombre());
				Geo3 geo3 = DAOManager.getGeo3DAO().selectGeo3ByPrimaryKey(geo4.getGeo3Id());
				Geo2 geo2 = DAOManager.getGeo2DAO().selectGeo2ByPrimaryKey(geo3.getGeo2Id());
				setGeo2Id(geo3.getGeo2Id());
				setGeo2Nombre(geo2.getNombre());
				setGeo3Id(geo3.getId());
				setGeo3Nombre(geo3.getNombre());
			}
		}
	}

	@Override
	public void basicValidate(ValidationError validationError) {
		FieldValidation.validateText(this.getNombre(), name_key, 100, validationError);
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		if (geo2Id == 0) {
			// busco por geo 2 repetido
			Geo2DAO geo2dao = DAOManager.getGeo2DAO();
			Geo2Example geo2Example = new Geo2Example();
			geo2Example.createCriteria().andNombreEqualTo(this.getNombre());
			List<Geo2> list = geo2dao.selectGeo2ByExample(geo2Example);
			if (!list.isEmpty()) {
				validationError.setFieldError(name_key, "2" + name_duplicated_key);
			}
		} else {
			if (geo3Id == 0) {
				// busco por geo 3 repetido
				Geo3DAO geo3dao = DAOManager.getGeo3DAO();
				Geo3Example geo3Example = new Geo3Example();
				geo3Example.createCriteria().andNombreEqualTo(this.getNombre()).andGeo2IdEqualTo(geo2Id);
				List<Geo3> list = geo3dao.selectGeo3ByExample(geo3Example);
				if (!list.isEmpty()) {
					validationError.setFieldError(name_key, "3" + name_duplicated_key);
				}
			} else {
				// busco por geo 4 repetido
				Geo4DAO geo4dao = DAOManager.getGeo4DAO();
				Geo4Example geo4Example = new Geo4Example();
				geo4Example.createCriteria().andNombreEqualTo(this.getNombre()).andGeo3IdEqualTo(geo3Id);
				List<Geo4> list = geo4dao.selectGeo4ByExample(geo4Example);
				if (!list.isEmpty()) {
					validationError.setFieldError(name_key, "4" + name_duplicated_key);
				}
			}
		}
	}

	@Override
	public void save() throws SQLException, ValidationException {
		if (geo2Id == 0) {
			// salvo geo 2
			if (this.getObjectId() == 0) {
				// inserto
				Geo2 geo2 = new Geo2();
				geo2.setNombre(this.getNombre());
				geo2.setDeleted(0);
				DAOManager.getGeo2DAO().insertGeo2(geo2);
			} else {
				// modifico
				Geo2 geo2 = DAOManager.getGeo2DAO().selectGeo2ByPrimaryKey(this.getObjectId());
				geo2.setNombre(this.getNombre());
				DAOManager.getGeo2DAO().updateGeo2ByPrimaryKey(geo2);
			}
		} else {
			if (geo3Id == 0) {
				// salvo geo3
				if (this.getObjectId() == 0) {
					// inserto
					Geo3 geo3 = new Geo3();
					geo3.setNombre(this.getNombre());
					geo3.setGeo2Id(this.getGeo2Id());
					geo3.setDeleted(0);
					DAOManager.getGeo3DAO().insertGeo3(geo3);
				} else {
					// modifico
					Geo3 geo3 = DAOManager.getGeo3DAO().selectGeo3ByPrimaryKey(this.getObjectId());
					geo3.setNombre(this.getNombre());
					geo3.setGeo2Id(this.getGeo2Id());
					DAOManager.getGeo3DAO().updateGeo3ByPrimaryKey(geo3);
				}
			} else {
				// salvo geo4
				if (this.getObjectId() == 0) {
					// inserto
					Geo4 geo4 = new Geo4();
					geo4.setNombre(this.getNombre());
					geo4.setGeo3Id(this.getGeo3Id());
					geo4.setDeleted(0);
					DAOManager.getGeo4DAO().insertGeo4(geo4);
				} else {
					// modifico
					Geo4 geo4 = DAOManager.getGeo4DAO().selectGeo4ByPrimaryKey(this.getObjectId());
					geo4.setNombre(this.getNombre());
					geo4.setGeo3Id(this.getGeo3Id());
					DAOManager.getGeo4DAO().updateGeo4ByPrimaryKey(geo4);
				}
			}
		}
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
		this.nombre = name != null ? name.toUpperCase() : null;
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

	public List<Geo2> getLevel2() {
		return level2;
	}

	public void setLevel2(List<Geo2> level2) {
		this.level2 = level2;
	}

	public List<Geo3> getLevel3() {
		if (geo2Id != 0) {
			try {
				TransactionProvider.executeInTransaction(new TransactionalAction() {
					public void executeInTransaction() throws SQLException, ValidationException {
						Geo3DAO geo3dao = DAOManager.getGeo3DAO();
						Geo3Example geo3Example = new Geo3Example();
						geo3Example.createCriteria().andGeo2IdEqualTo(GeoLevelForm.this.geo2Id);
						geo3Example.setOrderByClause("nombre");
						GeoLevelForm.this.setLevel3(geo3dao.selectGeo3ByExample(geo3Example));
					}
				});
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}
		return level3;
	}

	public void setLevel3(List<Geo3> level3) {
		this.level3 = level3;
	}

	public String getGeo2Nombre() {
		return geo2Nombre;
	}

	public void setGeo2Nombre(String geo2Nombre) {
		this.geo2Nombre = geo2Nombre;
	}

	public String getGeo3Nombre() {
		return geo3Nombre;
	}

	public void setGeo3Nombre(String geo3Nombre) {
		this.geo3Nombre = geo3Nombre;
	}

	public void setGeo4Id(int geo4Id) {
		// nothing to do
	}

	public void deleteGeoLevelFromDatabase(int levelId, int userId) {
		// TODO implementarlo, chequear todas las referencias
	}
}
