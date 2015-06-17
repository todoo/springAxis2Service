package cn.com.ws.common.dao;

import cn.com.ws.base.ResultInfo;

public interface CommonManager {
	/**
	 * 检测应用id和密码是否合法
	 * @param appID
	 * @param appSecret
	 * @return
	 */
	public ResultInfo isValidApp(String appID, String appSecret);
	
	/**
	 * 生成token
	 * @param appID
	 * @param expires 有效期，单位秒
	 * @return
	 */
	public ResultInfo createToken(String appID, int expires);
	
	/**
	 * 是否是合法token
	 * @param token
	 * @return
	 */
	public ResultInfo isValidToken(String token);
	
}
