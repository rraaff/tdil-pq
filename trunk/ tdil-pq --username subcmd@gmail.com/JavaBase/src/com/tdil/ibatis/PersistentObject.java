package com.tdil.ibatis;

import java.io.Serializable;

public abstract class PersistentObject implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8063622239352232658L;
	private Integer id;
    private Integer deleted;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
}
