package com.tdil.ibatis;

public abstract class PersistentObject {

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
