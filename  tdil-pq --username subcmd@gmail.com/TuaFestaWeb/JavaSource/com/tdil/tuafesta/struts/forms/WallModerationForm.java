package com.tdil.tuafesta.struts.forms;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.ValidationError;
import com.tdil.struts.ValidationException;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.dao.WallWrittingDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Client;
import com.tdil.tuafesta.model.Profesional;
import com.tdil.tuafesta.model.WallWritting;
import com.tdil.tuafesta.model.valueobjects.WallWrittingValueObject;

public class WallModerationForm extends TransactionalValidationForm implements EditProfesionalDataForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6752258803637709971L;
	
	private int id;
	
	private int objectId;
	private int wallId;
	
	private String post;
	
	private WallWritting postToAnswer;
	private WallWritting postToEdit;
	private Client postClient;
	
	private boolean ajaxLoaded;
	
	private boolean hasMore;
	
	private boolean responsePending;
	private List<WallWrittingValueObject> wallWritting;
	
	private Profesional profesional;
	
	public static final String post_key = "WallModerationForm.post";
	
	@Override
	public void reset() throws SQLException {
		this.objectId = 0;
		this.post = null;
		this.postToAnswer = null;
		this.postToEdit = null;
	}
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
	}

	@Override
	public void init() throws SQLException {
	}
	
	public boolean isResponding() {
		return this.getPostToAnswer() != null;
	}
	
	public boolean isEditingOrPosting() {
		return this.getPostToEdit() != null || this.getPostToAnswer() == null;
	}
	
	public boolean isShowAll() {
		return !this.responsePending;
	}

	@Override
	public void initWith(int id) throws SQLException {
		profesional = DAOManager.getProfesionalDAO().selectProfesionalByPrimaryKey(id);
		wallId = profesional.getIdWall();
		
		wallWritting = DAOManager.getWallWrittingDAO().selectWallWrittingPending(wallId);
		responsePending = true;
	}
	
	public void viewPending() throws SQLException {
		wallWritting = DAOManager.getWallWrittingDAO().selectWallWrittingPending(wallId);
		responsePending = true;
	}
	
	public void viewAll() throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idWall", this.getProfesional().getIdWall());
		params.put("start", 0);
		params.put("limit", 11);
		wallWritting = DAOManager.getWallWrittingDAO().selectWallWindowAll(params);
		
		if (wallWritting.size() > 10) {
			setHasMore(true);
			setWallWritting(this.getWallWritting().subList(0, 10));
		}
		responsePending = false;
	}
	
	public void loadWrittingToAnswer(int id) throws SQLException {
		this.reset();
		setPostToEdit(null);
		setPostToAnswer(DAOManager.getWallWrittingDAO().selectWallWrittingByPrimaryKey(id));
		if (this.getPostToAnswer().getIdAuthor() == null || this.getPostToAnswer().getIdAuthor().equals(0)) {
			setPostToAnswer(null);
			setPostClient(null);
		} else {
			setPostClient(DAOManager.getClientDAO().selectClientByPrimaryKey(this.getPostToAnswer().getIdAuthor()));
		}
	}
	
	public void loadWrittingToEdit(int id) throws SQLException {
		this.reset();
		setPostToAnswer(null);
		setPostClient(null);
		setPostToEdit(DAOManager.getWallWrittingDAO().selectWallWrittingByPrimaryKey(id));
		if (this.getPostToEdit().getIdAuthor() != null && !this.getPostToEdit().getIdAuthor().equals(0)) {
			setPostToEdit(null);
		} else {
			this.objectId = id;
			this.setPost(getPostToEdit().getOriginaltext());
		}
	}
	
	@Override
	public void basicValidate(ValidationError validationError) {
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}

	@Override
	public void save() throws SQLException, ValidationException {
		WallWrittingDAO wallWrittingDAO = DAOManager.getWallWrittingDAO();
		WallWritting post = null;
		if (this.getObjectId() == 0) {
			post = new WallWritting();
		} else {
			post = wallWrittingDAO.selectWallWrittingByPrimaryKey(this.getObjectId());
		}
		post.setApproved(1);
		post.setDeleted(0);
		post.setCreationdate(new Date());
		post.setIdWall(this.getProfesional().getIdWall());
		post.setPublishdate(new Date());
		post.setOriginaltext(this.getPost());
		post.setResponsePending(0);
		if (this.isResponding()) {
			WallWritting question = this.getPostToAnswer();
			question.setApproved(1);
			question.setResponsePending(0);
			wallWrittingDAO.updateWallWrittingByPrimaryKey(question);
			post.setIdResponseTo(question.getId());
		}
		if (this.getObjectId() == 0) {
			wallWrittingDAO.insertWallWritting(post);
		} else {
			wallWrittingDAO.updateWallWrittingByPrimaryKey(post);
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

	private static Logger getLog() {
		return LoggerProvider.getLogger(WallModerationForm.class);
	}
	public WallWritting getPostToAnswer() {
		return postToAnswer;
	}
	public void setPostToAnswer(WallWritting toAnswer) {
		this.postToAnswer = toAnswer;
	}
	public Client getPostClient() {
		return postClient;
	}
	public void setPostClient(Client postClient) {
		this.postClient = postClient;
	}
	public List<WallWrittingValueObject> getWallWritting() {
		return wallWritting;
	}
	public void setWallWritting(List<WallWrittingValueObject> wallWritting) {
		this.wallWritting = wallWritting;
	}
	public Profesional getProfesional() {
		return profesional;
	}
	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}
	public WallWritting getPostToEdit() {
		return postToEdit;
	}
	public void setPostToEdit(WallWritting postToEdit) {
		this.postToEdit = postToEdit;
	}
	public void delete(int id) throws SQLException {
		WallWritting wallWritting = DAOManager.getWallWrittingDAO().selectWallWrittingByPrimaryKey(id);
		if (wallWritting.getIdWall().equals(this.getProfesional().getIdWall())) {
			DAOManager.getWallWrittingDAO().deleteWallWrittingByPrimaryKey(id);
		}
		WallWritting deleted = null;
		for (WallWritting all : getWallWritting()) {
			if (all.getId().equals(id)) {
				deleted = all;
			}
		}
		getWallWritting().remove(deleted);
	}
	public void markAsResponded(int id) throws SQLException {
		WallWritting wallWritting = DAOManager.getWallWrittingDAO().selectWallWrittingByPrimaryKey(id);
		if (wallWritting.getIdWall().equals(this.getProfesional().getIdWall())) {
			wallWritting.setResponsePending(0);
			wallWritting.setApproved(1);
			DAOManager.getWallWrittingDAO().updateWallWrittingByPrimaryKey(wallWritting);
		}
		WallWritting deleted = null;
		for (WallWritting all : getWallWritting()) {
			if (all.getId().equals(id)) {
				deleted = all;
			}
		}
		deleted.setResponsePending(0);
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public int getWallId() {
		return wallId;
	}
	public void setWallId(int wallId) {
		this.wallId = wallId;
	}
	public boolean isAjaxLoaded() {
		return ajaxLoaded;
	}
	public void setAjaxLoaded(boolean ajaxLoaded) {
		this.ajaxLoaded = ajaxLoaded;
	}
	public boolean hasMore() {
		return hasMore;
	}
	public void setHasMore(boolean hasMore) {
		this.hasMore = hasMore;
	}
	protected boolean isAutoApprove() {
		return false;
	}

}
