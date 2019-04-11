package cn.icloudit.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.icloudit.common.mybatis.IBaseDAO;
import cn.icloudit.entity.ItemDesc;

@Mapper
public interface ItemDescDao extends IBaseDAO {

	@Select("SELECT id as id , itemDesc as itemDesc , created as created , updated as updated FROM tb_item_desc WHERE id = #{id}")
	public ItemDesc getItemDesc(@Param("id") Long id);


}
