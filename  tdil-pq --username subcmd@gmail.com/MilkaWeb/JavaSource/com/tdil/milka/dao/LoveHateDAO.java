package com.tdil.milka.dao;

import com.tdil.milka.model.LoveHate;
import com.tdil.milka.model.LoveHateExample;
import java.sql.SQLException;
import java.util.List;

public interface LoveHateDAO {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
     */
    int countLoveHateByExample(LoveHateExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
     */
    int deleteLoveHateByExample(LoveHateExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
     */
    int deleteLoveHateByPrimaryKey(Integer id) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
     */
    Integer insertLoveHate(LoveHate record) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
     */
    Integer insertLoveHateSelective(LoveHate record) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
     */
    List<LoveHate> selectLoveHateByExample(LoveHateExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
     */
    LoveHate selectLoveHateByPrimaryKey(Integer id) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
     */
    int updateLoveHateByExampleSelective(LoveHate record, LoveHateExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
     */
    int updateLoveHateByExample(LoveHate record, LoveHateExample example) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
     */
    int updateLoveHateByPrimaryKeySelective(LoveHate record) throws SQLException;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table LOVE_HATE
     *
     * @mbggenerated Wed Jun 27 22:57:22 ART 2012
     */
    int updateLoveHateByPrimaryKey(LoveHate record) throws SQLException;

	List<LoveHate> selectLoveHateToApprove() throws SQLException;

	List<LoveHate> selectLoveHateToReview() throws SQLException;
}