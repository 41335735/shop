package cn.icloudit.entity;




import cn.icloudit.common.api.enity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCat extends BaseEntity {

	private Integer parentId;
	private String name;
	private String  img;
	private String  status;
	private int sortOrder;
	private int isParent;
	
}
