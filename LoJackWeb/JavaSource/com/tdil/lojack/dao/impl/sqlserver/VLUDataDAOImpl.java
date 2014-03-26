package com.tdil.lojack.dao.impl.sqlserver;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.lojack.dao.VLUDataDAO;
import com.tdil.lojack.model.VLUData;
import com.tdil.lojack.model.VLUDataExample;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VLUDataDAOImpl implements VLUDataDAO {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table dbo.VLU_DATA
	 * @mbggenerated  Wed Mar 26 00:16:27 ART 2014
	 */
	private SqlMapClient sqlMapClient;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VLU_DATA
	 * @mbggenerated  Wed Mar 26 00:16:27 ART 2014
	 */
	public VLUDataDAOImpl(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VLU_DATA
	 * @mbggenerated  Wed Mar 26 00:16:27 ART 2014
	 */
	public int countVLUDataByExample(VLUDataExample example) throws SQLException {
		Integer count = (Integer) sqlMapClient.queryForObject("dbo_VLU_DATA.countByExample", example);
		return count;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VLU_DATA
	 * @mbggenerated  Wed Mar 26 00:16:27 ART 2014
	 */
	public int deleteVLUDataByExample(VLUDataExample example) throws SQLException {
		int rows = sqlMapClient.delete("dbo_VLU_DATA.deleteByExample", example);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VLU_DATA
	 * @mbggenerated  Wed Mar 26 00:16:27 ART 2014
	 */
	public int deleteVLUDataByPrimaryKey(Integer id) throws SQLException {
		VLUData _key = new VLUData();
		_key.setId(id);
		int rows = sqlMapClient.delete("dbo_VLU_DATA.deleteByPrimaryKey", _key);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VLU_DATA
	 * @mbggenerated  Wed Mar 26 00:16:27 ART 2014
	 */
	public Integer insertVLUData(VLUData record) throws SQLException {
		Object newKey = sqlMapClient.insert("dbo_VLU_DATA.insert", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VLU_DATA
	 * @mbggenerated  Wed Mar 26 00:16:27 ART 2014
	 */
	public Integer insertVLUDataSelective(VLUData record) throws SQLException {
		Object newKey = sqlMapClient.insert("dbo_VLU_DATA.insertSelective", record);
		return (Integer) newKey;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VLU_DATA
	 * @mbggenerated  Wed Mar 26 00:16:27 ART 2014
	 */
	@SuppressWarnings("unchecked")
	public List<VLUData> selectVLUDataByExample(VLUDataExample example) throws SQLException {
		List<VLUData> list = sqlMapClient.queryForList("dbo_VLU_DATA.selectByExample", example);
		return list;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VLU_DATA
	 * @mbggenerated  Wed Mar 26 00:16:27 ART 2014
	 */
	public VLUData selectVLUDataByPrimaryKey(Integer id) throws SQLException {
		VLUData _key = new VLUData();
		_key.setId(id);
		VLUData record = (VLUData) sqlMapClient.queryForObject("dbo_VLU_DATA.selectByPrimaryKey", _key);
		return record;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VLU_DATA
	 * @mbggenerated  Wed Mar 26 00:16:27 ART 2014
	 */
	public int updateVLUDataByExampleSelective(VLUData record, VLUDataExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("dbo_VLU_DATA.updateByExampleSelective", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VLU_DATA
	 * @mbggenerated  Wed Mar 26 00:16:27 ART 2014
	 */
	public int updateVLUDataByExample(VLUData record, VLUDataExample example) throws SQLException {
		UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
		int rows = sqlMapClient.update("dbo_VLU_DATA.updateByExample", parms);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VLU_DATA
	 * @mbggenerated  Wed Mar 26 00:16:27 ART 2014
	 */
	public int updateVLUDataByPrimaryKeySelective(VLUData record) throws SQLException {
		int rows = sqlMapClient.update("dbo_VLU_DATA.updateByPrimaryKeySelective", record);
		return rows;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table dbo.VLU_DATA
	 * @mbggenerated  Wed Mar 26 00:16:27 ART 2014
	 */
	public int updateVLUDataByPrimaryKey(VLUData record) throws SQLException {
		int rows = sqlMapClient.update("dbo_VLU_DATA.updateByPrimaryKey", record);
		return rows;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table dbo.VLU_DATA
	 * @mbggenerated  Wed Mar 26 00:16:27 ART 2014
	 */
	protected static class UpdateByExampleParms extends VLUDataExample {
		private Object record;

		public UpdateByExampleParms(Object record, VLUDataExample example) {
			super(example);
			this.record = record;
		}

		public Object getRecord() {
			return record;
		}
	}

	@Override
    public int deleteOLDVLUData(String dni, String domain, int importId) throws SQLException {
    	Map<String, Object> toDelete = new HashMap<String, Object>();
    	toDelete.put("dni", dni);
    	toDelete.put("domain", domain);
    	toDelete.put("importId", importId);
    	int rows = sqlMapClient.delete("dbo_VLU_DATA.deleteOLDVLUData", toDelete);
        return rows;
    }
}