package cn.icloudit.adapter;

import com.alibaba.fastjson.JSONObject;

public interface MessageAdapeter {
	public void distribute(JSONObject jsonObject);
}
