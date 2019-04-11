package cn.icloudit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import cn.icloudit.entity.ItemCat;

@Mapper
public interface ItemCatDao{
	
	@Select("SELECT id as id ,parent_id as parentId , name as name , img as img , status as status ,is_parent as isParent , created as created , updated as updated FROM tb_item_cat")
	public List<ItemCat> allItemCat();
	
	
}
