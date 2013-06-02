package com.tdil.lojack.struts.forms;

import java.sql.SQLException;
import java.util.List;

import com.tdil.lojack.daomanager.DAOManager;
import com.tdil.lojack.model.LightConf;
import com.tdil.lojack.model.LightConfExample;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;

public class RenameLightForm extends LightForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2556966768158490777L;
	
	private String description;
	
	@Override
	public void reset() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() throws SQLException {
		LightConfExample example = new LightConfExample();
		example.createCriteria().andIdentidadEqualTo(idEntidad).andIdluzEqualTo(idLuz).andIdwebsiteuserEqualTo(getSessionUser().getModelUser().getId());
		List<LightConf> result = DAOManager.getLightConfDAO().selectLightConfByExample(example);
		if (!result.isEmpty()) {
			setDescription(result.get(0).getDescription());
		} else {
			setDescription("");
		}
	}

	@Override
	public void initWith(int id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public ValidationError validate() {
		return new ValidationError();
	}

	@Override
	public void save() throws SQLException, ValidationException {
		LightConfExample example = new LightConfExample();
		example.createCriteria().andIdentidadEqualTo(idEntidad).andIdluzEqualTo(idLuz).andIdwebsiteuserEqualTo(getSessionUser().getModelUser().getId());
		List<LightConf> result = DAOManager.getLightConfDAO().selectLightConfByExample(example);
		if (result.isEmpty()) {
			// insert
			LightConf LightConf = new LightConf();
			LightConf.setIdentidad(idEntidad);
			LightConf.setIdluz(idLuz);
			LightConf.setIdwebsiteuser(getSessionUser().getModelUser().getId());
			LightConf.setDescription(description);
			LightConf.setEmailnotification(0);
			LightConf.setDeleted(0);
			DAOManager.getLightConfDAO().insertLightConf(LightConf);
		} else {
			// update
			LightConf LightConf = result.get(0);
			LightConf.setDescription(description);
			DAOManager.getLightConfDAO().updateLightConfByPrimaryKeySelective(LightConf);
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
