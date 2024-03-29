package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

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
import com.tdil.tuafesta.model.Category;
import com.tdil.tuafesta.model.Geo2;
import com.tdil.tuafesta.model.Geo2Example;
import com.tdil.tuafesta.model.Geo3;
import com.tdil.tuafesta.model.Geo3Example;
import com.tdil.tuafesta.model.Geo4;
import com.tdil.tuafesta.model.Geo4Example;
import com.tdil.tuafesta.model.valueobjects.GeoLevelValueObject;
import com.tdil.validations.FieldValidation;

public class GeoLevelForm extends TransactionalValidationForm implements GeoLevelSelectionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int level = 2;
	private int levelDelete = 2;
	private int objectId;
	private String nombre;
	private int geo2Id;
	private String geo2Nombre;
	private int geo3Id;
	private String geo3Nombre;
	
	private boolean availableforservice;
	private boolean showinhome;
	private String homeindex;
	
	private String nombreSearch;
	private int levelSearch = 2;
	private int parentIdSearch;
	
	private List<Geo2> level2;
	private List<Geo3> level3;
	
	private List<GeoLevelValueObject> search = new ArrayList<GeoLevelValueObject>();
	
	public static String homeindex_key = "GeoLevel.homeindex";
	public static String name_key = "GeoLevel.name";
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
		
		this.showinhome = false;
		this.homeindex = null;
		this.availableforservice = false;
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		this.showinhome = false;
		this.availableforservice = false;
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
		this.objectId = 0;
		this.search();
	}
	public void initForDeleteWith(int userId, int level) throws SQLException {
		this.objectId = userId;
		this.levelDelete = level;
	}
	public void validateForToggleDeletedFlag(ValidationError validationError) {
		// TODO Auto-generated method stub
	}
	public void toggleDeletedFlag() throws SQLException, ValidationException {
		if (this.levelDelete == 2) {
			Geo2 professionalCategory = DAOManager.getGeo2DAO().selectGeo2ByPrimaryKey(this.getObjectId());
			professionalCategory.setDeleted(professionalCategory.getDeleted().equals(1) ? 0 : 1);
			DAOManager.getGeo2DAO().updateGeo2ByPrimaryKeySelective(professionalCategory);
		} else {
			if (this.levelDelete == 3) {
				Geo3 professionalCategory = DAOManager.getGeo3DAO().selectGeo3ByPrimaryKey(this.getObjectId());
				professionalCategory.setDeleted(professionalCategory.getDeleted().equals(1) ? 0 : 1);
				DAOManager.getGeo3DAO().updateGeo3ByPrimaryKeySelective(professionalCategory);
			} else {
				Geo4 professionalCategory = DAOManager.getGeo4DAO().selectGeo4ByPrimaryKey(this.getObjectId());
				professionalCategory.setDeleted(professionalCategory.getDeleted().equals(1) ? 0 : 1);
				DAOManager.getGeo4DAO().updateGeo4ByPrimaryKeySelective(professionalCategory);
			}
		}
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
			this.homeindex = (geo2.getHomeindex() == null ? null : String.valueOf(geo2.getHomeindex()));
			this.showinhome = geo2.getShowinhome().equals(1);
			this.availableforservice = geo2.getAvailableforservice().equals(1);
		} else {
			if (level == 3) {
				Geo3 geo3 = DAOManager.getGeo3DAO().selectGeo3ByPrimaryKey(id);
				this.setNombre(geo3.getNombre());
				setGeo2Id(geo3.getGeo2Id());
				Geo2 geo2 = DAOManager.getGeo2DAO().selectGeo2ByPrimaryKey(geo3.getGeo2Id());
				setGeo2Nombre(geo2.getNombre());
				setGeo3Id(0);
				this.homeindex = (geo3.getHomeindex() == null ? null : String.valueOf(geo3.getHomeindex()));
				this.showinhome = geo3.getShowinhome().equals(1);
				this.availableforservice = geo3.getAvailableforservice().equals(1);
			} else {
				Geo4 geo4 = DAOManager.getGeo4DAO().selectGeo4ByPrimaryKey(id);
				this.setNombre(geo4.getNombre());
				Geo3 geo3 = DAOManager.getGeo3DAO().selectGeo3ByPrimaryKey(geo4.getGeo3Id());
				Geo2 geo2 = DAOManager.getGeo2DAO().selectGeo2ByPrimaryKey(geo3.getGeo2Id());
				setGeo2Id(geo3.getGeo2Id());
				setGeo2Nombre(geo2.getNombre());
				setGeo3Id(geo3.getId());
				setGeo3Nombre(geo3.getNombre());
				this.homeindex = (geo4.getHomeindex() == null ? null : String.valueOf(geo4.getHomeindex()));
				this.showinhome = geo4.getShowinhome().equals(1);
				this.availableforservice = geo4.getAvailableforservice().equals(1);
			}
		}
	}

	@Override
	public void basicValidate(ValidationError validationError) {
		FieldValidation.validateText(this.getNombre(), name_key, 100, validationError);
		FieldValidation.validateNumber(this.getHomeindex(), homeindex_key, 1, 1000, false, validationError);
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
				Geo2 geo = list.get(0);
				if (geo.getId() != this.getObjectId()) {
					validationError.setFieldError(name_key, "2" + name_duplicated_key);
				}
			}
		} else {
			if (geo3Id == 0) {
				// busco por geo 3 repetido
				Geo3DAO geo3dao = DAOManager.getGeo3DAO();
				Geo3Example geo3Example = new Geo3Example();
				geo3Example.createCriteria().andNombreEqualTo(this.getNombre()).andGeo2IdEqualTo(geo2Id);
				List<Geo3> list = geo3dao.selectGeo3ByExample(geo3Example);
				if (!list.isEmpty()) {
					Geo3 geo = list.get(0);
					if (geo.getId() != this.getObjectId()) {
						validationError.setFieldError(name_key, "3" + name_duplicated_key);
					}
				}
			} else {
				// busco por geo 4 repetido
				Geo4DAO geo4dao = DAOManager.getGeo4DAO();
				Geo4Example geo4Example = new Geo4Example();
				geo4Example.createCriteria().andNombreEqualTo(this.getNombre()).andGeo3IdEqualTo(geo3Id);
				List<Geo4> list = geo4dao.selectGeo4ByExample(geo4Example);
				if (!list.isEmpty()) {
					Geo4 geo = list.get(0);
					if (geo.getId() != this.getObjectId()) {
						validationError.setFieldError(name_key, "4" + name_duplicated_key);
					}
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
				if (StringUtils.isEmpty(this.getHomeindex())) {
					geo2.setHomeindex(null);
				} else {
					geo2.setHomeindex(Integer.valueOf(this.getHomeindex().trim()));
				}
				geo2.setShowinhome(this.isShowinhome() ? 1 : 0);
				geo2.setAvailableforservice(this.isAvailableforservice() ? 1 : 0);
				DAOManager.getGeo2DAO().insertGeo2(geo2);
			} else {
				// modifico
				Geo2 geo2 = DAOManager.getGeo2DAO().selectGeo2ByPrimaryKey(this.getObjectId());
				geo2.setNombre(this.getNombre());
				if (StringUtils.isEmpty(this.getHomeindex())) {
					geo2.setHomeindex(null);
				} else {
					geo2.setHomeindex(Integer.valueOf(this.getHomeindex().trim()));
				}
				geo2.setShowinhome(this.isShowinhome() ? 1 : 0);
				geo2.setAvailableforservice(this.isAvailableforservice() ? 1 : 0);
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
					if (StringUtils.isEmpty(this.getHomeindex())) {
						geo3.setHomeindex(null);
					} else {
						geo3.setHomeindex(Integer.valueOf(this.getHomeindex().trim()));
					}
					geo3.setShowinhome(this.isShowinhome() ? 1 : 0);
					geo3.setAvailableforservice(this.isAvailableforservice() ? 1 : 0);
					DAOManager.getGeo3DAO().insertGeo3(geo3);
				} else {
					// modifico
					Geo3 geo3 = DAOManager.getGeo3DAO().selectGeo3ByPrimaryKey(this.getObjectId());
					geo3.setNombre(this.getNombre());
					geo3.setGeo2Id(this.getGeo2Id());
					if (StringUtils.isEmpty(this.getHomeindex())) {
						geo3.setHomeindex(null);
					} else {
						geo3.setHomeindex(Integer.valueOf(this.getHomeindex().trim()));
					}
					geo3.setShowinhome(this.isShowinhome() ? 1 : 0);
					geo3.setAvailableforservice(this.isAvailableforservice() ? 1 : 0);
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
					if (StringUtils.isEmpty(this.getHomeindex())) {
						geo4.setHomeindex(null);
					} else {
						geo4.setHomeindex(Integer.valueOf(this.getHomeindex().trim()));
					}
					geo4.setShowinhome(this.isShowinhome() ? 1 : 0);
					geo4.setAvailableforservice(this.isAvailableforservice() ? 1 : 0);
					DAOManager.getGeo4DAO().insertGeo4(geo4);
				} else {
					// modifico
					Geo4 geo4 = DAOManager.getGeo4DAO().selectGeo4ByPrimaryKey(this.getObjectId());
					geo4.setNombre(this.getNombre());
					geo4.setGeo3Id(this.getGeo3Id());
					if (StringUtils.isEmpty(this.getHomeindex())) {
						geo4.setHomeindex(null);
					} else {
						geo4.setHomeindex(Integer.valueOf(this.getHomeindex().trim()));
					}
					geo4.setShowinhome(this.isShowinhome() ? 1 : 0);
					geo4.setAvailableforservice(this.isAvailableforservice() ? 1 : 0);
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
						GeoLevelForm.this.setSearch(DAOManager.getGeo2DAO().searchGeoLevelsByNombre("%" + GeoLevelForm.this.getNombreSearch().toUpperCase() + "%", false));
					} else {
						if (GeoLevelForm.this.getLevelSearch() == 3) {
							GeoLevelForm.this.setSearch(DAOManager.getGeo3DAO().searchGeoLevelsByNombre("%" + GeoLevelForm.this.getNombreSearch().toUpperCase() + "%", false));
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

	public boolean isAvailableforservice() {
		return availableforservice;
	}

	public void setAvailableforservice(boolean availableforservice) {
		this.availableforservice = availableforservice;
	}

	public boolean isShowinhome() {
		return showinhome;
	}

	public void setShowinhome(boolean showinhome) {
		this.showinhome = showinhome;
	}

	public String getHomeindex() {
		return homeindex;
	}

	public void setHomeindex(String homeindex) {
		this.homeindex = homeindex;
	}
}
