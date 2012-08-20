package com.tdil.tuafesta.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.tuafesta.dao.PromotionVideoDAO;
import com.tdil.tuafesta.model.PromotionVideo;
import com.tdil.tuafesta.model.PromotionVideoExample;
import java.sql.SQLException;
import java.util.List;

public class PromotionVideoDAOImpl implements PromotionVideoDAO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table PROMOTION_VIDEO
     *
     * @mbggenerated Mon Aug 20 20:10:42 ART 2012
     */
    private SqlMapClient sqlMapClient;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PROMOTION_VIDEO
     *
     * @mbggenerated Mon Aug 20 20:10:42 ART 2012
     */
    public PromotionVideoDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PROMOTION_VIDEO
     *
     * @mbggenerated Mon Aug 20 20:10:42 ART 2012
     */
    public int countPromotionVideoByExample(PromotionVideoExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("PROMOTION_VIDEO.countByExample", example);
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PROMOTION_VIDEO
     *
     * @mbggenerated Mon Aug 20 20:10:42 ART 2012
     */
    public int deletePromotionVideoByExample(PromotionVideoExample example) throws SQLException {
        int rows = sqlMapClient.delete("PROMOTION_VIDEO.deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PROMOTION_VIDEO
     *
     * @mbggenerated Mon Aug 20 20:10:42 ART 2012
     */
    public int deletePromotionVideoByPrimaryKey(Integer id) throws SQLException {
        PromotionVideo _key = new PromotionVideo();
        _key.setId(id);
        int rows = sqlMapClient.delete("PROMOTION_VIDEO.deleteByPrimaryKey", _key);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PROMOTION_VIDEO
     *
     * @mbggenerated Mon Aug 20 20:10:42 ART 2012
     */
    public Integer insertPromotionVideo(PromotionVideo record) throws SQLException {
        Object newKey = sqlMapClient.insert("PROMOTION_VIDEO.insert", record);
        return (Integer) newKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PROMOTION_VIDEO
     *
     * @mbggenerated Mon Aug 20 20:10:42 ART 2012
     */
    public Integer insertPromotionVideoSelective(PromotionVideo record) throws SQLException {
        Object newKey = sqlMapClient.insert("PROMOTION_VIDEO.insertSelective", record);
        return (Integer) newKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PROMOTION_VIDEO
     *
     * @mbggenerated Mon Aug 20 20:10:42 ART 2012
     */
    @SuppressWarnings("unchecked")
    public List<PromotionVideo> selectPromotionVideoByExample(PromotionVideoExample example) throws SQLException {
        List<PromotionVideo> list = sqlMapClient.queryForList("PROMOTION_VIDEO.selectByExample", example);
        return list;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PROMOTION_VIDEO
     *
     * @mbggenerated Mon Aug 20 20:10:42 ART 2012
     */
    public PromotionVideo selectPromotionVideoByPrimaryKey(Integer id) throws SQLException {
        PromotionVideo _key = new PromotionVideo();
        _key.setId(id);
        PromotionVideo record = (PromotionVideo) sqlMapClient.queryForObject("PROMOTION_VIDEO.selectByPrimaryKey", _key);
        return record;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PROMOTION_VIDEO
     *
     * @mbggenerated Mon Aug 20 20:10:42 ART 2012
     */
    public int updatePromotionVideoByExampleSelective(PromotionVideo record, PromotionVideoExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("PROMOTION_VIDEO.updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PROMOTION_VIDEO
     *
     * @mbggenerated Mon Aug 20 20:10:42 ART 2012
     */
    public int updatePromotionVideoByExample(PromotionVideo record, PromotionVideoExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("PROMOTION_VIDEO.updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PROMOTION_VIDEO
     *
     * @mbggenerated Mon Aug 20 20:10:42 ART 2012
     */
    public int updatePromotionVideoByPrimaryKeySelective(PromotionVideo record) throws SQLException {
        int rows = sqlMapClient.update("PROMOTION_VIDEO.updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PROMOTION_VIDEO
     *
     * @mbggenerated Mon Aug 20 20:10:42 ART 2012
     */
    public int updatePromotionVideoByPrimaryKey(PromotionVideo record) throws SQLException {
        int rows = sqlMapClient.update("PROMOTION_VIDEO.updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table PROMOTION_VIDEO
     *
     * @mbggenerated Mon Aug 20 20:10:42 ART 2012
     */
    protected static class UpdateByExampleParms extends PromotionVideoExample {
        private Object record;

        public UpdateByExampleParms(Object record, PromotionVideoExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}