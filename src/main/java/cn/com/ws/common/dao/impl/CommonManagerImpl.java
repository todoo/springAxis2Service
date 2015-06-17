package cn.com.ws.common.dao.impl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import cn.com.ws.base.ResultCodeConstant;
import cn.com.ws.base.ResultInfo;
import cn.com.ws.common.dao.CommonManager;

@Service
public class CommonManagerImpl implements CommonManager {
	
	Log logger = LogFactory.getLog(CommonManagerImpl.class);
	
	public ResultInfo isValidApp(String appID, String appSecret) {
		ResultInfo result = new ResultInfo(true);
		String sql = "";
		try {
			
		} catch (Exception e) {
			logger.error("验证用户名和密码出错！", e);
			result.setSuccess(false);
			result.setErrorCode(ResultCodeConstant.ERROR_9999);
			result.setMessage(ResultCodeConstant.ERROR_9999_DESC);
		}
		return result;
	}

	public ResultInfo createToken(String appID, int expires) {
		ResultInfo result = new ResultInfo(true);
		String sql = "";
		try {
			result.addData("token", "aaaaaa");
		} catch (Exception e) {
			logger.error("生成token出错！", e);
			result.setSuccess(false);
			result.setErrorCode(ResultCodeConstant.ERROR_9999);
			result.setMessage(ResultCodeConstant.ERROR_9999_DESC);
		}
		return result;
	}

	public ResultInfo isValidToken(String token) {
		ResultInfo result = new ResultInfo(true);
		String sql = "";
		try {
			//检测token是否存在
			
			
			//检测token是否过期
			
		} catch (Exception e) {
			logger.error("检测token出错！", e);
			result.setSuccess(false);
			result.setErrorCode(ResultCodeConstant.ERROR_9999);
			result.setMessage(ResultCodeConstant.ERROR_9999_DESC);
		}
		return result;
	}

}
