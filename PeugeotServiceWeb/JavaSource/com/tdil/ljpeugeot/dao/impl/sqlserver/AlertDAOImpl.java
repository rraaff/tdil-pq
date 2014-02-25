package com.tdil.ljpeugeot.dao.impl.sqlserver;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.ljpeugeot.dao.AlertDAO;
import com.tdil.ljpeugeot.model.Alert;
import com.tdil.ljpeugeot.model.AlertExample;
import java.sql.SQLException;
import java.util.List;

public class AlertDAOImpl implements AlertDAO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table dbo.ALERT
     *
     * @mbggenerated Mon Feb 24 23:21:35 ART 2014
     */
    private SqlMapClient sqlMapClient;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.ALERT
     *
     * @mbggenerated Mon Feb 24 23:21:35 ART 2014
     */
    public AlertDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.ALERT
     *
     * @mbggenerated Mon Feb 24 23:21:35 ART 2014
     */
    public int countAlertByExample(AlertExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("dbo_ALERT.countByExample", example);
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.ALERT
     *
     * @mbggenerated Mon Feb 24 23:21:35 ART 2014
     */
    public int deleteAlertByExample(AlertExample example) throws SQLException {
        int rows = sqlMapClient.delete("dbo_ALERT.deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.ALERT
     *
     * @mbggenerated Mon Feb 24 23:21:35 ART 2014
     */
    public int deleteAlertByPrimaryKey(Integer id) throws SQLException {
        Alert _key = new Alert();
        _key.setId(id);
        int rows = sqlMapClient.delete("dbo_ALERT.deleteByPrimaryKey", _key);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.ALERT
     *
     * @mbggenerated Mon Feb 24 23:21:35 ART 2014
     */
    public Integer insertAlert(Alert record) throws SQLException {
        Object newKey = sqlMapClient.insert("dbo_ALERT.insert", record);
        return (Integer) newKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.ALERT
     *
     * @mbggenerated Mon Feb 24 23:21:35 ART 2014
     */
    public Integer insertAlertSelective(Alert record) throws SQLException {
        Object newKey = sqlMapClient.insert("dbo_ALERT.insertSelective", record);
        return (Integer) newKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.ALERT
     *
     * @mbggenerated Mon Feb 24 23:21:35 ART 2014
     */
    @SuppressWarnings("unchecked")
    public List<Alert> selectAlertByExample(AlertExample example) throws SQLException {
        List<Alert> list = sqlMapClient.queryForList("dbo_ALERT.selectByExample", example);
        return list;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.ALERT
     *
     * @mbggenerated Mon Feb 24 23:21:35 ART 2014
     */
    public Alert selectAlertByPrimaryKey(Integer id) throws SQLException {
        Alert _key = new Alert();
        _key.setId(id);
        Alert record = (Alert) sqlMapClient.queryForObject("dbo_ALERT.selectByPrimaryKey", _key);
        return record;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.ALERT
     *
     * @mbggenerated Mon Feb 24 23:21:35 ART 2014
     */
    public int updateAlertByExampleSelective(Alert record, AlertExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("dbo_ALERT.updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.ALERT
     *
     * @mbggenerated Mon Feb 24 23:21:35 ART 2014
     */
    public int updateAlertByExample(Alert record, AlertExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("dbo_ALERT.updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.ALERT
     *
     * @mbggenerated Mon Feb 24 23:21:35 ART 2014
     */
    public int updateAlertByPrimaryKeySelective(Alert record) throws SQLException {
        int rows = sqlMapClient.update("dbo_ALERT.updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dbo.ALERT
     *
     * @mbggenerated Mon Feb 24 23:21:35 ART 2014
     */
    public int updateAlertByPrimaryKey(Alert record) throws SQLException {
        int rows = sqlMapClient.update("dbo_ALERT.updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table dbo.ALERT
     *
     * @mbggenerated Mon Feb 24 23:21:35 ART 2014
     */
    protected static class UpdateByExampleParms extends AlertExample {
        private Object record;

        public UpdateByExampleParms(Object record, AlertExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}