package com.clv.parttimejobs.entity.my.specialattention;

public class MyGZbasis {

	private String imgUrl;
	private String shop_name;
	private String shop_context;
	private boolean isGZ;
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public String getShop_context() {
		return shop_context;
	}
	public void setShop_context(String shop_context) {
		this.shop_context = shop_context;
	}
	public boolean isGZ() {
		return isGZ;
	}
	public void setGZ(boolean isGZ) {
		this.isGZ = isGZ;
	}
	public MyGZbasis(String imgUrl, String shop_name, String shop_context,
			boolean isGZ) {
		super();
		this.imgUrl = imgUrl;
		this.shop_name = shop_name;
		this.shop_context = shop_context;
		this.isGZ= isGZ;
	}
	
}
