package com.tdil.ljpeugeot.dao.impl.sqlserver;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.ljpeugeot.dao.KmDataDAO;
import com.tdil.ljpeugeot.model.KmData;
import com.tdil.ljpeugeot.model.KmDataExample;
import java.sql.SQLException;
import java.util.List;

public class KmDataDAOImpl implements KmDataDAO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table dbo.KM_DATA
     *
     * @mbggenerated Thu Feb 20 14:42:30 ART 2014
     */
    private SqlMapClient sqlMapClient;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.KM_DATA
     *
     * @mbggenerated Thu Feb 20 14:42:30 ART 2014
     */
    public KmDataDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.KM_DATA
     *
     * @mbggenerated Thu Feb 20 14:42:30 ART 2014
     */
    public int countKmDataByExample(KmDataExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("dbo_KM_DATA.countByExample", example);
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.KM_DATA
     *
     * @mbggenerated Thu Feb 20 14:42:30 ART 2014
     */
    public int deleteKmDataByExample(KmDataExample example) throws SQLException {
        int rows = sqlMapClient.delete("dbo_KM_DATA.deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.KM_DATA
     *
     * @mbggenerated Thu Feb 20 14:42:30 ART 2014
     */
    public int deleteKmDataByPrimaryKey(Integer id) throws SQLException {
        KmData _key = new KmData();
        _key.setId(id);
        int rows = sqlMapClient.delete("dbo_KM_DATA.deleteByPrimaryKey", _key);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.KM_DATA
     *
     * @mbggenerated Thu Feb 20 14:42:30 ART 2014
     */
    public Integer insertKmData(KmData record) throws SQLException {
        Object newKey = sqlMapClient.insert("dbo_KM_DATA.insert", record);
        return (Integer) newKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.KM_DATA
     *
     * @mbggenerated Thu Feb 20 14:42:30 ART 2014
     */
    public Integer insertKmDataSelective(KmData record) throws SQLException {
        Object newKey = sqlMapClient.insert("dbo_KM_DATA.insertSelective", record);
        return (Integer) newKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.KM_DATA
     *
     * @mbggenerated Thu Feb 20 14:42:30 ART 2014
     */
    @SuppressWarnings("unchecked")
    public List<KmData> selectKmDataByExample(KmDataExample example) throws SQLException {
        List<KmData> list = sqlMapClient.queryForList("dbo_KM_DATA.selectByExample", example);
        return list;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.KM_DATA
     *
     * @mbggenerated Thu Feb 20 14:42:30 ART 2014
     */
    public KmData selectKmDataByPrimaryKey(Integer id) throws SQLException {
        KmData _key = new KmData();
        _key.setId(id);
        KmData record = (KmData) sqlMapClient.queryForObject("dbo_KM_DATA.selectByPrimaryKey", _key);
        return record;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.KM_DATA
     *
     * @mbggenerated Thu Feb 20 14:42:30 ART 2014
     */
    public int updateKmDataByExampleSelective(KmData record, KmDataExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("dbo_KM_DATA.updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.KM_DATA
     *
     * @mbggenerated Thu Feb 20 14:42:30 ART 2014
     */
    public int updateKmDataByExample(KmData record, KmDataExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("dbo_KM_DATA.updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.KM_DATA
     *
     * @mbggenerated Thu Feb 20 14:42:30 ART 2014
     */
    public int updateKmDataByPrimaryKeySelective(KmData record) throws SQLException {
        int rows = sqlMapClient.update("dbo_KM_DATA.updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.KM_DATA
     *
     * @mbggenerated Thu Feb 20 14:42:30 ART 2014
     */
    public int updateKmDataByPrimaryKey(KmData record) throws SQLException {
        int rows = sqlMapClient.update("dbo_KM_DATA.updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table dbo.KM_DATA
     *
     * @mbggenerated Thu Feb 20 14:42:30 ART 2014
     */
    protected static class UpdateByExampleParms extends KmDataExample {
        private Object record;

        public UpdateByExampleParms(Object record, KmDataExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
    
    @Override
    public List<KmData> selectKmDataToProcess(int startId) throws SQLException {
    	return sqlMapClient.queryForList("dbo_KM_DATA.selectKmDataToProcess", startId);
    }
}