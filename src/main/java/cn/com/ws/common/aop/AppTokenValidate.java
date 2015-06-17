package cn.com.ws.common.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ws.base.ResultInfo;
import cn.com.ws.base.XMLUtils;
import cn.com.ws.common.dao.CommonManager;


/**
 * 接口token统一验证
 * @author 
 *
 */
@Service
@Aspect
public class AppTokenValidate {
	@Autowired
	private CommonManager commonManager;
	
	Log logger = LogFactory.getLog(AppTokenValidate.class);
	
	@SuppressWarnings("rawtypes")
	@Around("within(cn.com.service..service.*)")
    public Object appTokenValidate(ProceedingJoinPoint jp) {
		try {
			Object retVal = null;
			Object[] args = jp.getArgs();
			//检测方法第一个参数是否是字符串类型，且名字为token，如果是则该方法需要进行token验证
			MethodSignature signature = (MethodSignature)jp.getSignature();
			String[] paramNames = signature.getParameterNames();
			Class[] paramTypes = signature.getParameterTypes();
			if (paramNames == null || paramNames.length<1 || 
					!paramNames[0].equalsIgnoreCase("token") || 
					paramTypes.length<1 || !String.class.getName().equalsIgnoreCase(paramTypes[0].getName())) {
				//不需进行token验证
				retVal = jp.proceed();
				
				return retVal;
			}
			
			//检测token
			ResultInfo result = commonManager.isValidToken((String)args[0]);
			if (result.isSuccess()) {
				//token验证通过
				retVal = jp.proceed();
				
				return retVal;
			} else {
				//token验证失败
				return XMLUtils.getErrorXml(result);
			}
		} catch (Exception e) {
			logger.error("token 验证失败", e);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			logger.error("token 验证失败", e);
		}
		//返回未知的错误
		return XMLUtils.getUnknowErrorXml();
    }
}
