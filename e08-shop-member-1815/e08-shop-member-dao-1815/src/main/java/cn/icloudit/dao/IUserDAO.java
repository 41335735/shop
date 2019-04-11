package cn.icloudit.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.icloudit.api.entity.User;
import cn.icloudit.common.mybatis.IBaseDAO;

@Mapper
public interface IUserDAO extends IBaseDAO {

	@Select("select ID,USERNAME,PASSWORD,phone EMAIL,created,updated from shop_user Where PHONE=#{phone} and PASSWORD=#{password}")
	public User getUserPhoneAndPwd(@Param("phone") String phone,@Param("password") String password);

	@Select("SELECT ID , USERNAME , PASSWORD , PHONE EMAIL ,created updated from shop_user WHERE id=#{id}")
	public User getUserInfo(@Param("id") Long id);
}
