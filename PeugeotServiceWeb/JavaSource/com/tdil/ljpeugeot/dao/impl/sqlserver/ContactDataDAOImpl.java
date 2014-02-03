package com.tdil.ljpeugeot.dao.impl.sqlserver;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.ljpeugeot.dao.ContactDataDAO;
import com.tdil.ljpeugeot.model.ContactData;
import com.tdil.ljpeugeot.model.ContactDataExample;
import java.sql.SQLException;
import java.util.List;

public class ContactDataDAOImpl implements ContactDataDAO {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table dbo.CONTACTDATA
	 * @mbggenerated  Sun Feb 02 21:48:07 ART 2014
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CONTACTDATA
	 * @mbggenerated  Sun Feb 02 21:48:07 ART 2014
	 */
	public ContactDataDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CONTACTDATA
	 * @mbggenerated  Sun Feb 02 21:48:07 ART 2014
	 */
	public int countContactDataByExample(ContactDataExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("dbo_CONTACTDATA.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CONTACTDATA
	 * @mbggenerated  Sun Feb 02 21:48:07 ART 2014
	 */
	public int deleteContactDataByExample(ContactDataExample example) throws SQLException {
		int rows = sqlMapClient.delete("dbo_CONTACTDATA.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CONTACTDATA
	 * @mbggenerated  Sun Feb 02 21:48:07 ART 2014
	 */
	public int deleteContactDataByPrimaryKey(Integer id) throws SQLException {
		ContactData _key = new ContactData();
		_key.setId(id);
		int rows = sqlMapClient.delete("dbo_CONTACTDATA.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CONTACTDATA
	 * @mbggenerated  Sun Feb 02 21:48:07 ART 2014
	 */
	public Integer insertContactData(ContactData record) throws SQLException {
		Object newKey = sqlMapClient.insert("dbo_CONTACTDATA.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CONTACTDATA
	 * @mbggenerated  Sun Feb 02 21:48:07 ART 2014
	 */
	public Integer insertContactDataSelective(ContactData record) throws SQLException {
		Object newKey = sqlMapClient.insert("dbo_CONTACTDATA.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CONTACTDATA
	 * @mbggenerated  Sun Feb 02 21:48:07 ART 2014
	 */
	@SuppressWarnings("unchecked")
	public List<ContactData> selectContactDataByExample(ContactDataExample example) throws SQLException {
		List<ContactData> list = sqlMapClient.queryForList("dbo_CONTACTDATA.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CONTACTDATA
	 * @mbggenerated  Sun Feb 02 21:48:07 ART 2014
	 */
	public ContactData selectContactDataByPrimaryKey(Integer id) throws SQLException {
		ContactData _key = new ContactData();
		_key.setId(id);
		ContactData record = (ContactData) sqlMapClient.queryForObject("dbo_CONTACTDATA.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CONTACTDATA
	 * @mbggenerated  Sun Feb 02 21:48:07 ART 2014
	 */
	public int updateContactDataByExampleSelective(ContactData record, ContactDataExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("dbo_CONTACTDATA.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CONTACTDATA
	 * @mbggenerated  Sun Feb 02 21:48:07 ART 2014
	 */
	public int updateContactDataByExample(ContactData record, ContactDataExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("dbo_CONTACTDATA.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CONTACTDATA
	 * @mbggenerated  Sun Feb 02 21:48:07 ART 2014
	 */
	public int updateContactDataByPrimaryKeySelective(ContactData record) throws SQLException {
		int rows = sqlMapClient.update("dbo_CONTACTDATA.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.CONTACTDATA
	 * @mbggenerated  Sun Feb 02 21:48:07 ART 2014
	 */
	public int updateContactDataByPrimaryKey(ContactData record) throws SQLException {
		int rows = sqlMapClient.update("dbo_CONTACTDATA.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table dbo.CONTACTDATA
	 * @mbggenerated  Sun Feb 02 21:48:07 ART 2014
	 */
	protected static class UpdateByExampleParms extends ContactDataExample {
		private Object record;

		public UpdateByExampleParms(Object record, ContactDataExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}
}