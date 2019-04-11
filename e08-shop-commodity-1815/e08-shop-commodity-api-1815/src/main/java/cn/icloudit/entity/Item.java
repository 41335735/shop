package cn.icloudit.entity;


import cn.icloudit.common.api.enity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Item extends BaseEntity{
	
	
	private String title;
	private String sellPoint;
	private Integer price;
	private Integer num;
	private String  barcode;
	  private String image;
	  private Integer  parentId;
	  private Integer  cid;
	  private Integer  status;
	/*
	 * private Date created; updated;
	 */
}
