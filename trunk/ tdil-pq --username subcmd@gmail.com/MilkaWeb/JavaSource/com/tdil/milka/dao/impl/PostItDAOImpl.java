package com.tdil.milka.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.milka.dao.PostItDAO;
import com.tdil.milka.model.PostIt;
import com.tdil.milka.model.PostItExample;
import com.tdil.milka.model.valueobjects.MilkaPhotoValueObject;
import com.tdil.milka.model.valueobjects.PostItValueObject;

import java.sql.SQLException;
import java.util.List;

public class PostItDAOImpl implements PostItDAO {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table POST_IT
	 * @mbggenerated  Sat May 12 23:24:02 ART 2012
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_IT
	 * @mbggenerated  Sat May 12 23:24:02 ART 2012
	 */
	public PostItDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_IT
	 * @mbggenerated  Sat May 12 23:24:02 ART 2012
	 */
	public int countPostItByExample(PostItExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("POST_IT.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_IT
	 * @mbggenerated  Sat May 12 23:24:02 ART 2012
	 */
	public int deletePostItByExample(PostItExample example) throws SQLException {
		int rows = sqlMapClient.delete("POST_IT.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_IT
	 * @mbggenerated  Sat May 12 23:24:02 ART 2012
	 */
	public int deletePostItByPrimaryKey(Integer id) throws SQLException {
		PostIt _key = new PostIt();
		_key.setId(id);
		int rows = sqlMapClient.delete("POST_IT.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_IT
	 * @mbggenerated  Sat May 12 23:24:02 ART 2012
	 */
	public Integer insertPostIt(PostIt record) throws SQLException {
		Object newKey = sqlMapClient.insert("POST_IT.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_IT
	 * @mbggenerated  Sat May 12 23:24:02 ART 2012
	 */
	public Integer insertPostItSelective(PostIt record) throws SQLException {
		Object newKey = sqlMapClient.insert("POST_IT.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_IT
	 * @mbggenerated  Sat May 12 23:24:02 ART 2012
	 */
	@SuppressWarnings("unchecked")
	public List<PostIt> selectPostItByExample(PostItExample example) throws SQLException {
		List<PostIt> list = sqlMapClient.queryForList("POST_IT.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_IT
	 * @mbggenerated  Sat May 12 23:24:02 ART 2012
	 */
	public PostIt selectPostItByPrimaryKey(Integer id) throws SQLException {
		PostIt _key = new PostIt();
		_key.setId(id);
		PostIt record = (PostIt) sqlMapClient.queryForObject("POST_IT.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_IT
	 * @mbggenerated  Sat May 12 23:24:02 ART 2012
	 */
	public int updatePostItByExampleSelective(PostIt record, PostItExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("POST_IT.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_IT
	 * @mbggenerated  Sat May 12 23:24:02 ART 2012
	 */
	public int updatePostItByExample(PostIt record, PostItExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("POST_IT.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_IT
	 * @mbggenerated  Sat May 12 23:24:02 ART 2012
	 */
	public int updatePostItByPrimaryKeySelective(PostIt record) throws SQLException {
		int rows = sqlMapClient.update("POST_IT.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table POST_IT
	 * @mbggenerated  Sat May 12 23:24:02 ART 2012
	 */
	public int updatePostItByPrimaryKey(PostIt record) throws SQLException {
		int rows = sqlMapClient.update("POST_IT.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table POST_IT
	 * @mbggenerated  Sat May 12 23:24:02 ART 2012
	 */
	protected static class UpdateByExampleParms extends PostItExample {
		private Object record;

		public UpdateByExampleParms(Object record, PostItExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	public List<PostItValueObject> selectPostItsToApproveWithAuthor() throws SQLException {
    	List<PostItValueObject> list = sqlMapClient.queryForList("POST_IT.selectPostItsWithAuthorsToApprove");
		return list;
    }
	
	public List<PostItValueObject> selectPostItsToReviewWithAuthor() throws SQLException {
    	List<PostItValueObject> list = sqlMapClient.queryForList("POST_IT.selectPostItsWithAuthorsToReview");
		return list;
    }
}