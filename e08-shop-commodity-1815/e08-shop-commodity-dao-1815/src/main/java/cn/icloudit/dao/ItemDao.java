package cn.icloudit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.icloudit.common.mybatis.IBaseDAO;
import cn.icloudit.entity.Item;

@Mapper
public interface ItemDao extends IBaseDAO {
	@Select("SELECT a.id as id ,a.title as title , a.sell_point as sellpoint , a.price as price , a.num as num , a.barcode as barcode , a.image as image , a.parent_id as parentId , a.cid as cid ,a.status as status , a.created as created , a.updated as updated FROM tb_item as a inner join tb_item_cat as b on a.cid = b.id WHERE b.id =#{id} LIMIT 0,8")
	public List<Item> getIndexItem(@Param("id") Long id);
	
	@Select("SELECT a.id as id ,a.title as title , a.sell_point as sellpoint , a.price as price , a.num as num , a.barcode as barcode , a.image as image , a.parent_id as parentId , a.cid as cid ,a.status as status , a.created as created , a.updated as updated FROM tb_item as a WHERE a.id =#{id}")
	public Item getItem(@Param("id") Long id);
	
	
}
