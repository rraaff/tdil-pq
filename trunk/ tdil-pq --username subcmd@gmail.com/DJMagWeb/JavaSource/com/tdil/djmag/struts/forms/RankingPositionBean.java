package com.tdil.djmag.struts.forms;

import java.io.Serializable;

import com.tdil.djmag.model.BlobData;
import com.tdil.djmag.model.RankingPosition;
import com.tdil.struts.forms.UploadData;

public class RankingPositionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5436118842744032161L;

	private int blobId;
	private String position;
	private String description;
	private String content;
	private UploadData uploadData;
	
	public RankingPositionBean() {
		super();
	}
	
	public RankingPositionBean(RankingPosition position) {
		super();
		this.position = position.getPosition();
		this.description = position.getDescription();
		this.uploadData = null;
	}
	
	public RankingPositionBean(RankingPosition position, BlobData blob) {
		super();
		this.blobId = blob.getId();
		this.position = position.getPosition();
		this.description = position.getDescription();
		this.uploadData = new UploadData(blob.getFilename(), blob.getContent(), false);
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public boolean getHasUploadData() {
		return this.getUploadData() != null;
	}
	
	public UploadData getUploadData() {
		return uploadData;
	}

	public void setUploadData(UploadData uploadData) {
		this.uploadData = uploadData;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getBlobId() {
		return blobId;
	}

	public void setBlobId(int blobId) {
		this.blobId = blobId;
	}

	public RankingPosition getAsRankingPosition() {
		RankingPosition rankingPosition = new RankingPosition();
		rankingPosition.setPosition(this.getPosition());
		rankingPosition.setDescription(this.getDescription());
		if (this.getHasUploadData()) {
			rankingPosition.setImageid(String.valueOf(this.getBlobId()));
			rankingPosition.setImageext(this.getUploadData().getExtension());
		} else {
			rankingPosition.setImageid("0");
			rankingPosition.setImageext("");
		}
		return rankingPosition;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
