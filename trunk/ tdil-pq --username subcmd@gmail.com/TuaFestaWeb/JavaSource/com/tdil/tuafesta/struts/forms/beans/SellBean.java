package com.tdil.tuafesta.struts.forms.beans;

import java.io.Serializable;
import java.sql.SQLException;

import com.tdil.struts.forms.UploadData;
import com.tdil.struts.resources.ApplicationResources;
import com.tdil.tuafesta.model.Sell;
import com.tdil.tuafesta.model.SellType;
import com.tdil.tuafesta.model.valueobjects.SellValueObject;
import com.tdil.tuafesta.struts.forms.EditProfesionalSellForm;
import com.tdil.tuafesta.struts.forms.EditProfesionalSellServiceForm;
import com.tdil.tuafesta.utils.ProductCategoryUtils;

public class SellBean implements Serializable {

	// TODO a esto le falta considerar que puede ser una edicion
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6929326102200140850L;

	private int index;
	
	private int type;
	private int id;
	private int productId;
	private String categoryText;
	private String name;
	private String referencePrice;
	
	private boolean dataLoaded;
	
	private UploadData image1;
	private UploadData image2;
	private UploadData image3;
	private UploadData image4;
	private UploadData image5;
	
	private String video1;
	private String video2;
	private String video3;
	private String video4;
	private String video5;
	
	
	public SellBean() {
	}
	
	public SellBean(Sell sell) {
		this.setId(sell.getId());
		this.setProductId(sell.getIdProdServ());
		this.setType(sell.getType());
		this.setName(sell.getItem());
		this.setReferencePrice(sell.getReferenceprice().toString());
	}

	public SellBean(SellValueObject sell) throws SQLException {
		this.setId(sell.getId());
		this.setProductId(sell.getIdProdServ());
		this.setType(sell.getType());
		this.setName(sell.getName());
		this.setReferencePrice(sell.getReferenceprice().toString());
		this.setCategoryText(ProductCategoryUtils.getCategoryPath(sell.getIdCategory()));
	}
	
	public String getSellTypeDescription() {
		return ApplicationResources.getMessage(this.type == SellType.PRODUCT ? "PRODUCT" : "SERVICE");
	}
	
	public String getReferencePrice() {
		return referencePrice;
	}
	public void setReferencePrice(String referencePrice) {
		this.referencePrice = referencePrice;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int id) {
		this.productId = id;
	}
	public String getCategoryText() {
		return categoryText;
	}
	public void setCategoryText(String categoryText) {
		this.categoryText = categoryText;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UploadData getImage1() {
		return image1;
	}

	public void setImage1(UploadData image1) {
		this.image1 = image1;
	}

	public UploadData getImage2() {
		return image2;
	}

	public void setImage2(UploadData image2) {
		this.image2 = image2;
	}

	public UploadData getImage3() {
		return image3;
	}

	public void setImage3(UploadData image3) {
		this.image3 = image3;
	}

	public UploadData getImage4() {
		return image4;
	}

	public void setImage4(UploadData image4) {
		this.image4 = image4;
	}

	public UploadData getImage5() {
		return image5;
	}

	public void setImage5(UploadData image5) {
		this.image5 = image5;
	}

	public String getVideo1() {
		return video1;
	}

	public void setVideo1(String video1) {
		this.video1 = video1;
	}

	public String getVideo2() {
		return video2;
	}

	public void setVideo2(String video2) {
		this.video2 = video2;
	}

	public String getVideo3() {
		return video3;
	}

	public void setVideo3(String video3) {
		this.video3 = video3;
	}

	public String getVideo4() {
		return video4;
	}

	public void setVideo4(String video4) {
		this.video4 = video4;
	}

	public String getVideo5() {
		return video5;
	}

	public void setVideo5(String video5) {
		this.video5 = video5;
	}

	public void setMedia(EditProfesionalSellForm editProfesionalSellServiceForm) {
		this.setImage1(editProfesionalSellServiceForm.getImage1());
		this.setImage2(editProfesionalSellServiceForm.getImage2());
		this.setImage3(editProfesionalSellServiceForm.getImage3());
		this.setImage4(editProfesionalSellServiceForm.getImage4());
		this.setImage5(editProfesionalSellServiceForm.getImage5());
		
		this.setVideo1(editProfesionalSellServiceForm.getVideo1());
		this.setVideo2(editProfesionalSellServiceForm.getVideo2());
		this.setVideo3(editProfesionalSellServiceForm.getVideo3());
		this.setVideo4(editProfesionalSellServiceForm.getVideo4());
		this.setVideo5(editProfesionalSellServiceForm.getVideo5());
		
	}

	public boolean isDataLoaded() {
		return dataLoaded;
	}

	public void setDataLoaded(boolean dataLoaded) {
		this.dataLoaded = dataLoaded;
	}
	
}
