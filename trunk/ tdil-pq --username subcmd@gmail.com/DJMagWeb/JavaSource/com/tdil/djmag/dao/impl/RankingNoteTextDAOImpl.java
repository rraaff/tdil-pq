package com.tdil.djmag.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.djmag.dao.RankingNoteTextDAO;
import com.tdil.djmag.model.RankingNoteText;
import com.tdil.djmag.model.RankingNoteTextExample;
import java.sql.SQLException;
import java.util.List;

public class RankingNoteTextDAOImpl implements RankingNoteTextDAO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table RANKING_NOTE_TEXT
     *
     * @mbggenerated Thu May 17 08:30:59 ART 2012
     */
    private SqlMapClient sqlMapClient;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RANKING_NOTE_TEXT
     *
     * @mbggenerated Thu May 17 08:30:59 ART 2012
     */
    public RankingNoteTextDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RANKING_NOTE_TEXT
     *
     * @mbggenerated Thu May 17 08:30:59 ART 2012
     */
    public int countRankingNoteTextByExample(RankingNoteTextExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("RANKING_NOTE_TEXT.countByExample", example);
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RANKING_NOTE_TEXT
     *
     * @mbggenerated Thu May 17 08:30:59 ART 2012
     */
    public int deleteRankingNoteTextByExample(RankingNoteTextExample example) throws SQLException {
        int rows = sqlMapClient.delete("RANKING_NOTE_TEXT.deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RANKING_NOTE_TEXT
     *
     * @mbggenerated Thu May 17 08:30:59 ART 2012
     */
    public int deleteRankingNoteTextByPrimaryKey(Integer id) throws SQLException {
        RankingNoteText _key = new RankingNoteText();
        _key.setId(id);
        int rows = sqlMapClient.delete("RANKING_NOTE_TEXT.deleteByPrimaryKey", _key);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RANKING_NOTE_TEXT
     *
     * @mbggenerated Thu May 17 08:30:59 ART 2012
     */
    public Integer insertRankingNoteText(RankingNoteText record) throws SQLException {
        Object newKey = sqlMapClient.insert("RANKING_NOTE_TEXT.insert", record);
        return (Integer) newKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RANKING_NOTE_TEXT
     *
     * @mbggenerated Thu May 17 08:30:59 ART 2012
     */
    public Integer insertRankingNoteTextSelective(RankingNoteText record) throws SQLException {
        Object newKey = sqlMapClient.insert("RANKING_NOTE_TEXT.insertSelective", record);
        return (Integer) newKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RANKING_NOTE_TEXT
     *
     * @mbggenerated Thu May 17 08:30:59 ART 2012
     */
    @SuppressWarnings("unchecked")
    public List<RankingNoteText> selectRankingNoteTextByExampleWithBLOBs(RankingNoteTextExample example) throws SQLException {
        List<RankingNoteText> list = sqlMapClient.queryForList("RANKING_NOTE_TEXT.selectByExampleWithBLOBs", example);
        return list;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RANKING_NOTE_TEXT
     *
     * @mbggenerated Thu May 17 08:30:59 ART 2012
     */
    @SuppressWarnings("unchecked")
    public List<RankingNoteText> selectRankingNoteTextByExampleWithoutBLOBs(RankingNoteTextExample example) throws SQLException {
        List<RankingNoteText> list = sqlMapClient.queryForList("RANKING_NOTE_TEXT.selectByExample", example);
        return list;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RANKING_NOTE_TEXT
     *
     * @mbggenerated Thu May 17 08:30:59 ART 2012
     */
    public RankingNoteText selectRankingNoteTextByPrimaryKey(Integer id) throws SQLException {
        RankingNoteText _key = new RankingNoteText();
        _key.setId(id);
        RankingNoteText record = (RankingNoteText) sqlMapClient.queryForObject("RANKING_NOTE_TEXT.selectByPrimaryKey", _key);
        return record;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RANKING_NOTE_TEXT
     *
     * @mbggenerated Thu May 17 08:30:59 ART 2012
     */
    public int updateRankingNoteTextByExampleSelective(RankingNoteText record, RankingNoteTextExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("RANKING_NOTE_TEXT.updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RANKING_NOTE_TEXT
     *
     * @mbggenerated Thu May 17 08:30:59 ART 2012
     */
    public int updateRankingNoteTextByExampleWithBLOBs(RankingNoteText record, RankingNoteTextExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("RANKING_NOTE_TEXT.updateByExampleWithBLOBs", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RANKING_NOTE_TEXT
     *
     * @mbggenerated Thu May 17 08:30:59 ART 2012
     */
    public int updateRankingNoteTextByExampleWithoutBLOBs(RankingNoteText record, RankingNoteTextExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("RANKING_NOTE_TEXT.updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RANKING_NOTE_TEXT
     *
     * @mbggenerated Thu May 17 08:30:59 ART 2012
     */
    public int updateRankingNoteTextByPrimaryKeySelective(RankingNoteText record) throws SQLException {
        int rows = sqlMapClient.update("RANKING_NOTE_TEXT.updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RANKING_NOTE_TEXT
     *
     * @mbggenerated Thu May 17 08:30:59 ART 2012
     */
    public int updateRankingNoteTextByPrimaryKeyWithBLOBs(RankingNoteText record) throws SQLException {
        int rows = sqlMapClient.update("RANKING_NOTE_TEXT.updateByPrimaryKeyWithBLOBs", record);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RANKING_NOTE_TEXT
     *
     * @mbggenerated Thu May 17 08:30:59 ART 2012
     */
    public int updateRankingNoteTextByPrimaryKeyWithoutBLOBs(RankingNoteText record) throws SQLException {
        int rows = sqlMapClient.update("RANKING_NOTE_TEXT.updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table RANKING_NOTE_TEXT
     *
     * @mbggenerated Thu May 17 08:30:59 ART 2012
     */
    protected static class UpdateByExampleParms extends RankingNoteTextExample {
        private Object record;

        public UpdateByExampleParms(Object record, RankingNoteTextExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}