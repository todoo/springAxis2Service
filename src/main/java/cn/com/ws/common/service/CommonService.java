package cn.com.ws.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.ws.base.ResultInfo;
import cn.com.ws.base.XMLUtils;
import cn.com.ws.common.bean.AppToken;
import cn.com.ws.common.dao.CommonManager;

@Component
public class CommonService {
	@Autowired 
	private CommonManager commonManager;
	
	/**
	 * 获取授权token
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getAccessToken(String appID, String appSecret) {
		//校验用户名和密码是否正确
		ResultInfo result = commonManager.isValidApp(appID, appSecret);
		if (!result.isSuccess()) {
			//验证失败
			return XMLUtils.getErrorXml(result);	
		}
		
		//验证成功，生成token，token有效期为7200秒
		result = commonManager.createToken(appID, 7200);
		if (!result.isSuccess()) {
			//生成token失败
			return XMLUtils.getErrorXml(result);
		}
		//获取token成功，返回xml
		AppToken appToken = new AppToken();
		appToken.setToken((String)result.getData("token"));
		appToken.setExpires(7200);
		
		return XMLUtils.objectToXml(appToken, new Class[]{AppToken.class}, new String[]{"AppToken"});
	}
	
	public String checkToken(String checkToken) {
		ResultInfo result = commonManager.isValidToken(checkToken);
		
		return XMLUtils.getErrorXml(result);
	}
}
