/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.shop.service;

import java.util.List;

import org.egovframe.rte.ptl.reactive.validation.EgovNullCheck;

/**
 * @Class Name : ProductVO.java
 * @Description : ProductVO Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */
public class ProductVO extends ProductDefaultVO {

	private static final long serialVersionUID = 1L;

	private String kind; //1:신규, 2:수정, 3:구매, 4:장바구니
	private String productCode  ;
	private String productName  ;
	private String brand        ;
	private String category     ;
	private String price        ;
	private String description  ;
	private String shippingType ;
	private String shippingCost ;
	private String purchaseLimit    ;
	private String shippingInfo ;
	private String receiveMethod;
	private String image        ;
	private String freeLimit    ; 
	private List<CategoryVO> categoryList;
		
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getShippingType() {
		return shippingType;
	}
	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}
	public String getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(String shippingCost) {
		this.shippingCost = shippingCost;
	}
	
	public String getPurchaseLimit() {
		return purchaseLimit;
	}
	public void setPurchaseLimit(String purchaseLimit) {
		this.purchaseLimit = purchaseLimit;
	}
	public String getShippingInfo() {
		return shippingInfo;
	}
	public void setShippingInfo(String shippingInfo) {
		this.shippingInfo = shippingInfo;
	}
	public String getReceiveMethod() {
		return receiveMethod;
	}
	public void setReceiveMethod(String receiveMethod) {
		this.receiveMethod = receiveMethod;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getFreeLimit() {
		return freeLimit;
	}
	public void setFreeLimit(String freeLimit) {
		this.freeLimit = freeLimit;
	}
	public List<CategoryVO> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<CategoryVO> categoryList) {
		this.categoryList = categoryList;
	}

	
}
