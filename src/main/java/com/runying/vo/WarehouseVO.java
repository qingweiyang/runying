package com.runying.vo;

/**
 * 仓库管理>仓库查看 的VO
 * 
 * @author I319213
 *
 */
public class WarehouseVO {
	private int warehouseID;
	
	private int productID;
	
	private String materialName;
	
	private String size1;
	
	private String size2;
	
	private String materialCode;
	
	private String material;
	
	/**
	 * 库存量
	 */
	private int warehouseCount;

	public int getWarehouseID() {
		return warehouseID;
	}

	public void setWarehouseID(int warehouseID) {
		this.warehouseID = warehouseID;
	}

	public String getSize1() {
		return size1;
	}

	public void setSize1(String size1) {
		this.size1 = size1;
	}

	public String getSize2() {
		return size2;
	}

	public void setSize2(String size2) {
		this.size2 = size2;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public int getWarehouseCount() {
		return warehouseCount;
	}

	public void setWarehouseCount(int warehouseCount) {
		this.warehouseCount = warehouseCount;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

}
