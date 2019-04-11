package cn.icloudit.common.constants;

public interface Constants {
	Long USER_TOKEN_TERMVALTIDITY = 60 * 60 * 24 * 90l;
	int WEBUSER_COOKIE_TOKEN_TERMVALIDITY = 1000*60*60*24*90;
	String USER_TOKEN = "token";
	/**
	 * 购物车有效期
	 */
	Long WEBUSER_SHOP_TERMVALIDITY = 1000 * 60 * 60 * 24 * 90l;
	
	String PAY_SUCCESS= "ok";
	String PAY_FAIL= "fail";
}
