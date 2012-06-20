package com.tdil.djmag.dao.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.tdil.djmag.dao.GalleryCategoryDAO;
import com.tdil.djmag.model.Country;
import com.tdil.djmag.model.GalleryCategory;
import com.tdil.djmag.model.GalleryCategoryExample;
import com.tdil.djmag.model.Section;

import java.sql.SQLException;
import java.util.List;

public class GalleryCategoryDAOImpl implements GalleryCategoryDAO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table GALLERY_CATEGORY
     *
     * @mbggenerated Tue Jun 19 18:08:48 ART 2012
     */
    private SqlMapClient sqlMapClient;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GALLERY_CATEGORY
     *
     * @mbggenerated Tue Jun 19 18:08:48 ART 2012
     */
    public GalleryCategoryDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GALLERY_CATEGORY
     *
     * @mbggenerated Tue Jun 19 18:08:48 ART 2012
     */
    public int countGalleryCategoryByExample(GalleryCategoryExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("GALLERY_CATEGORY.countByExample", example);
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GALLERY_CATEGORY
     *
     * @mbggenerated Tue Jun 19 18:08:48 ART 2012
     */
    public int deleteGalleryCategoryByExample(GalleryCategoryExample example) throws SQLException {
        int rows = sqlMapClient.delete("GALLERY_CATEGORY.deleteByExample", example);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GALLERY_CATEGORY
     *
     * @mbggenerated Tue Jun 19 18:08:48 ART 2012
     */
    public int deleteGalleryCategoryByPrimaryKey(Integer id) throws SQLException {
        GalleryCategory _key = new GalleryCategory();
        _key.setId(id);
        int rows = sqlMapClient.delete("GALLERY_CATEGORY.deleteByPrimaryKey", _key);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GALLERY_CATEGORY
     *
     * @mbggenerated Tue Jun 19 18:08:48 ART 2012
     */
    public Integer insertGalleryCategory(GalleryCategory record) throws SQLException {
        Object newKey = sqlMapClient.insert("GALLERY_CATEGORY.insert", record);
        return (Integer) newKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GALLERY_CATEGORY
     *
     * @mbggenerated Tue Jun 19 18:08:48 ART 2012
     */
    public Integer insertGalleryCategorySelective(GalleryCategory record) throws SQLException {
        Object newKey = sqlMapClient.insert("GALLERY_CATEGORY.insertSelective", record);
        return (Integer) newKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GALLERY_CATEGORY
     *
     * @mbggenerated Tue Jun 19 18:08:48 ART 2012
     */
    @SuppressWarnings("unchecked")
    public List<GalleryCategory> selectGalleryCategoryByExample(GalleryCategoryExample example) throws SQLException {
        List<GalleryCategory> list = sqlMapClient.queryForList("GALLERY_CATEGORY.selectByExample", example);
        return list;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GALLERY_CATEGORY
     *
     * @mbggenerated Tue Jun 19 18:08:48 ART 2012
     */
    public GalleryCategory selectGalleryCategoryByPrimaryKey(Integer id) throws SQLException {
        GalleryCategory _key = new GalleryCategory();
        _key.setId(id);
        GalleryCategory record = (GalleryCategory) sqlMapClient.queryForObject("GALLERY_CATEGORY.selectByPrimaryKey", _key);
        return record;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GALLERY_CATEGORY
     *
     * @mbggenerated Tue Jun 19 18:08:48 ART 2012
     */
    public int updateGalleryCategoryByExampleSelective(GalleryCategory record, GalleryCategoryExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("GALLERY_CATEGORY.updateByExampleSelective", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GALLERY_CATEGORY
     *
     * @mbggenerated Tue Jun 19 18:08:48 ART 2012
     */
    public int updateGalleryCategoryByExample(GalleryCategory record, GalleryCategoryExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("GALLERY_CATEGORY.updateByExample", parms);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GALLERY_CATEGORY
     *
     * @mbggenerated Tue Jun 19 18:08:48 ART 2012
     */
    public int updateGalleryCategoryByPrimaryKeySelective(GalleryCategory record) throws SQLException {
        int rows = sqlMapClient.update("GALLERY_CATEGORY.updateByPrimaryKeySelective", record);
        return rows;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table GALLERY_CATEGORY
     *
     * @mbggenerated Tue Jun 19 18:08:48 ART 2012
     */
    public int updateGalleryCategoryByPrimaryKey(GalleryCategory record) throws SQLException {
        int rows = sqlMapClient.update("GALLERY_CATEGORY.updateByPrimaryKey", record);
        return rows;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table GALLERY_CATEGORY
     *
     * @mbggenerated Tue Jun 19 18:08:48 ART 2012
     */
    protected static class UpdateByExampleParms extends GalleryCategoryExample {
        private Object record;

        public UpdateByExampleParms(Object record, GalleryCategoryExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
    
    public List<GalleryCategory> selectGalleryCategoryForCountry(Country country) throws SQLException {
    	List<GalleryCategory> list = sqlMapClient.queryForList("GALLERY_CATEGORY.selectGalleriesForCountry", country);
		return list;
    }
}