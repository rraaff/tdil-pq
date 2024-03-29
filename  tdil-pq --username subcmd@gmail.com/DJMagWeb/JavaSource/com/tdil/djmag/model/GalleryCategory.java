package com.tdil.djmag.model;

import com.tdil.ibatis.PersistentObject;

public class GalleryCategory extends PersistentObject {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column GALLERY_CATEGORY.title
     *
     * @mbggenerated Tue Jun 19 18:08:48 ART 2012
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column GALLERY_CATEGORY.description
     *
     * @mbggenerated Tue Jun 19 18:08:48 ART 2012
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column GALLERY_CATEGORY.imageExt
     *
     * @mbggenerated Tue Jun 19 18:08:48 ART 2012
     */
    private String imageext;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column GALLERY_CATEGORY.image_id
     *
     * @mbggenerated Tue Jun 19 18:08:48 ART 2012
     */
    private Integer imageId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column GALLERY_CATEGORY.title
     *
     * @return the value of GALLERY_CATEGORY.title
     *
     * @mbggenerated Tue Jun 19 18:08:48 ART 2012
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column GALLERY_CATEGORY.title
     *
     * @param title the value for GALLERY_CATEGORY.title
     *
     * @mbggenerated Tue Jun 19 18:08:48 ART 2012
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column GALLERY_CATEGORY.description
     *
     * @return the value of GALLERY_CATEGORY.description
     *
     * @mbggenerated Tue Jun 19 18:08:48 ART 2012
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column GALLERY_CATEGORY.description
     *
     * @param description the value for GALLERY_CATEGORY.description
     *
     * @mbggenerated Tue Jun 19 18:08:48 ART 2012
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column GALLERY_CATEGORY.imageExt
     *
     * @return the value of GALLERY_CATEGORY.imageExt
     *
     * @mbggenerated Tue Jun 19 18:08:48 ART 2012
     */
    public String getImageext() {
        return imageext;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column GALLERY_CATEGORY.imageExt
     *
     * @param imageext the value for GALLERY_CATEGORY.imageExt
     *
     * @mbggenerated Tue Jun 19 18:08:48 ART 2012
     */
    public void setImageext(String imageext) {
        this.imageext = imageext == null ? null : imageext.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column GALLERY_CATEGORY.image_id
     *
     * @return the value of GALLERY_CATEGORY.image_id
     *
     * @mbggenerated Tue Jun 19 18:08:48 ART 2012
     */
    public Integer getImageId() {
        return imageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column GALLERY_CATEGORY.image_id
     *
     * @param imageId the value for GALLERY_CATEGORY.image_id
     *
     * @mbggenerated Tue Jun 19 18:08:48 ART 2012
     */
    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }
}