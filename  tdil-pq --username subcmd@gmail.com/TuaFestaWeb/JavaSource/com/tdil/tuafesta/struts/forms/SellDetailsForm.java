package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.dao.SellMediaDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Profesional;
import com.tdil.tuafesta.model.Sell;
import com.tdil.tuafesta.model.SellMedia;
import com.tdil.tuafesta.model.SellMediaExample;
import com.tdil.tuafesta.model.SellType;
import com.tdil.tuafesta.model.valueobjects.SellValueObject;
import com.tdil.tuafesta.stats.StatisticType;
import com.tdil.tuafesta.stats.StatsManager;
import com.tdil.tuafesta.struts.forms.beans.PublicImageBlobBean;

public class SellDetailsForm extends TransactionalValidationForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private SellValueObject sellValueObject;
	
	private SellMedia sellMedia;
	
	@Override
	public void reset() throws SQLException {
	}
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}

	@Override
	public void init() throws SQLException {
	}

	@Override
	public void initWith(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	public List<PublicImageBlobBean> getMedia() {
		List<PublicImageBlobBean> result = new ArrayList<PublicImageBlobBean>();
		if (sellMedia != null) {
			if (sellMedia.getIdBlobData1() != null && sellMedia.getIdBlobData1() != 0) {
				result.add(new PublicImageBlobBean(sellMedia.getIdBlobData1(), sellMedia.getExtBlobData1()));
			}
			if (sellMedia.getIdBlobData2() != null && sellMedia.getIdBlobData2() != 0) {
				result.add(new PublicImageBlobBean(sellMedia.getIdBlobData2(), sellMedia.getExtBlobData2()));
			}
			if (sellMedia.getIdBlobData3() != null && sellMedia.getIdBlobData3() != 0) {
				result.add(new PublicImageBlobBean(sellMedia.getIdBlobData3(), sellMedia.getExtBlobData3()));
			}
			if (sellMedia.getIdBlobData4() != null && sellMedia.getIdBlobData4() != 0) {
				result.add(new PublicImageBlobBean(sellMedia.getIdBlobData4(), sellMedia.getExtBlobData4()));
			}
			if (sellMedia.getIdBlobData5() != null && sellMedia.getIdBlobData5() != 0) {
				result.add(new PublicImageBlobBean(sellMedia.getIdBlobData5(), sellMedia.getExtBlobData5()));
			}
			
		}
		return result;
		
	}
	
	public boolean hasMedia() {
		return sellMedia != null;
	}
	
	public void initWith(int type, int id) throws SQLException {
		if (type == SellType.PRODUCT) {
			sellValueObject = DAOManager.getSellDAO().selectSellProductValueObject(id);
		} else {
			if (type == SellType.SERVICE) {
				sellValueObject = DAOManager.getSellDAO().selectSellServiceValueObject(id);
			} else {
				Sell sell = DAOManager.getSellDAO().selectSellByPrimaryKey(id);
				if (sell.getType().equals(SellType.PRODUCT)) {
					sellValueObject = DAOManager.getSellDAO().selectSellProductValueObject(id);
				} else {
					sellValueObject = DAOManager.getSellDAO().selectSellServiceValueObject(id);
				}
			}
		}
		SellMediaDAO sellMediaDAO = DAOManager.getSellMediaDAO();
		SellMediaExample sellMediaExample = new SellMediaExample();
		sellMediaExample.createCriteria().andIdSellEqualTo(id).andApprovedEqualTo(1);
		List<SellMedia> result = sellMediaDAO.selectSellMediaByExample(sellMediaExample);
		if (!result.isEmpty()) {
			sellMedia = result.get(0);
		} else {
			sellMedia = null;
		}
	}
	
	@Override
	public void basicValidate(ValidationError error) {
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}

	@Override
	public void save() throws SQLException, ValidationException {
		
	}	

	private static Logger getLog() {
		return LoggerProvider.getLogger(SellDetailsForm.class);
	}
	public SellValueObject getSellValueObject() {
		return sellValueObject;
	}
	public SellMedia getSellMedia() {
		return sellMedia;
	}
	public void setSellMedia(SellMedia sellMedia) {
		this.sellMedia = sellMedia;
	}

	
}
