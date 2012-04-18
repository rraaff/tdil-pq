package com.tdil.djmag.dao;

import com.tdil.djmag.model.RankingNote;
import com.tdil.djmag.model.RankingNoteExample;
import java.sql.SQLException;
import java.util.List;

public interface RankingNoteDAO {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_NOTE
	 * @mbggenerated  Tue Apr 17 18:10:25 ART 2012
	 */
	int countRankingNoteByExample(RankingNoteExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_NOTE
	 * @mbggenerated  Tue Apr 17 18:10:25 ART 2012
	 */
	int deleteRankingNoteByExample(RankingNoteExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_NOTE
	 * @mbggenerated  Tue Apr 17 18:10:25 ART 2012
	 */
	int deleteRankingNoteByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_NOTE
	 * @mbggenerated  Tue Apr 17 18:10:25 ART 2012
	 */
	Integer insertRankingNote(RankingNote record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_NOTE
	 * @mbggenerated  Tue Apr 17 18:10:25 ART 2012
	 */
	Integer insertRankingNoteSelective(RankingNote record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_NOTE
	 * @mbggenerated  Tue Apr 17 18:10:25 ART 2012
	 */
	List<RankingNote> selectRankingNoteByExampleWithBLOBs(RankingNoteExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_NOTE
	 * @mbggenerated  Tue Apr 17 18:10:25 ART 2012
	 */
	List<RankingNote> selectRankingNoteByExampleWithoutBLOBs(RankingNoteExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_NOTE
	 * @mbggenerated  Tue Apr 17 18:10:25 ART 2012
	 */
	RankingNote selectRankingNoteByPrimaryKey(Integer id) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_NOTE
	 * @mbggenerated  Tue Apr 17 18:10:25 ART 2012
	 */
	int updateRankingNoteByExampleSelective(RankingNote record, RankingNoteExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_NOTE
	 * @mbggenerated  Tue Apr 17 18:10:25 ART 2012
	 */
	int updateRankingNoteByExampleWithBLOBs(RankingNote record, RankingNoteExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_NOTE
	 * @mbggenerated  Tue Apr 17 18:10:25 ART 2012
	 */
	int updateRankingNoteByExampleWithoutBLOBs(RankingNote record, RankingNoteExample example) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_NOTE
	 * @mbggenerated  Tue Apr 17 18:10:25 ART 2012
	 */
	int updateRankingNoteByPrimaryKeySelective(RankingNote record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_NOTE
	 * @mbggenerated  Tue Apr 17 18:10:25 ART 2012
	 */
	int updateRankingNoteByPrimaryKeyWithBLOBs(RankingNote record) throws SQLException;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table RANKING_NOTE
	 * @mbggenerated  Tue Apr 17 18:10:25 ART 2012
	 */
	int updateRankingNoteByPrimaryKeyWithoutBLOBs(RankingNote record) throws SQLException;
}