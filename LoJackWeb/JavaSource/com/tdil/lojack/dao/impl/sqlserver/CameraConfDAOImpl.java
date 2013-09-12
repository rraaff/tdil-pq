package com.tdil.lojack.dao.impl.sqlserver;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.lojack.dao.CameraConfDAO;
import com.tdil.lojack.model.CameraConf;
import com.tdil.lojack.model.CameraConfExample;
import java.sql.SQLException;
import java.util.List;

public class CameraConfDAOImpl implements CameraConfDAO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table dbo.CAMERA_CONF
     *
     * @mbggenerated Wed Sep 11 22:59:58 ART 2013
     */
    private SqlMapClient sqlMapClient;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.CAMERA_CONF
     *
     * @mbggenerated Wed Sep 11 22:59:58 ART 2013
     */
    public CameraConfDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.CAMERA_CONF
     *
     * @mbggenerated Wed Sep 11 22:59:58 ART 2013
     */
    public int countCameraConfByExample(CameraConfExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("dbo_CAMERA_CONF.countByExample", example);
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.CAMERA_CONF
     *
     * @mbggenerated Wed Sep 11 22:59:58 ART 2013
     */
    public int deleteCameraConfByExample(CameraConfExample example) throws SQLException {
        int rows = sqlMapClient.delete("dbo_CAMERA_CONF.deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.CAMERA_CONF
     *
     * @mbggenerated Wed Sep 11 22:59:58 ART 2013
     */
    public int deleteCameraConfByPrimaryKey(Integer id) throws SQLException {
        CameraConf _key = new CameraConf();
        _key.setId(id);
        int rows = sqlMapClient.delete("dbo_CAMERA_CONF.deleteByPrimaryKey", _key);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.CAMERA_CONF
     *
     * @mbggenerated Wed Sep 11 22:59:58 ART 2013
     */
    public Integer insertCameraConf(CameraConf record) throws SQLException {
        Object newKey = sqlMapClient.insert("dbo_CAMERA_CONF.insert", record);
        return (Integer) newKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.CAMERA_CONF
     *
     * @mbggenerated Wed Sep 11 22:59:58 ART 2013
     */
    public Integer insertCameraConfSelective(CameraConf record) throws SQLException {
        Object newKey = sqlMapClient.insert("dbo_CAMERA_CONF.insertSelective", record);
        return (Integer) newKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.CAMERA_CONF
     *
     * @mbggenerated Wed Sep 11 22:59:58 ART 2013
     */
    @SuppressWarnings("unchecked")
    public List<CameraConf> selectCameraConfByExample(CameraConfExample example) throws SQLException {
        List<CameraConf> list = sqlMapClient.queryForList("dbo_CAMERA_CONF.selectByExample", example);
        return list;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.CAMERA_CONF
     *
     * @mbggenerated Wed Sep 11 22:59:58 ART 2013
     */
    public CameraConf selectCameraConfByPrimaryKey(Integer id) throws SQLException {
        CameraConf _key = new CameraConf();
        _key.setId(id);
        CameraConf record = (CameraConf) sqlMapClient.queryForObject("dbo_CAMERA_CONF.selectByPrimaryKey", _key);
        return record;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.CAMERA_CONF
     *
     * @mbggenerated Wed Sep 11 22:59:58 ART 2013
     */
    public int updateCameraConfByExampleSelective(CameraConf record, CameraConfExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("dbo_CAMERA_CONF.updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.CAMERA_CONF
     *
     * @mbggenerated Wed Sep 11 22:59:58 ART 2013
     */
    public int updateCameraConfByExample(CameraConf record, CameraConfExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("dbo_CAMERA_CONF.updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.CAMERA_CONF
     *
     * @mbggenerated Wed Sep 11 22:59:58 ART 2013
     */
    public int updateCameraConfByPrimaryKeySelective(CameraConf record) throws SQLException {
        int rows = sqlMapClient.update("dbo_CAMERA_CONF.updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.CAMERA_CONF
     *
     * @mbggenerated Wed Sep 11 22:59:58 ART 2013
     */
    public int updateCameraConfByPrimaryKey(CameraConf record) throws SQLException {
        int rows = sqlMapClient.update("dbo_CAMERA_CONF.updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table dbo.CAMERA_CONF
     *
     * @mbggenerated Wed Sep 11 22:59:58 ART 2013
     */
    protected static class UpdateByExampleParms extends CameraConfExample {
        private Object record;

        public UpdateByExampleParms(Object record, CameraConfExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}