package cn.com.ws.base;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XMLUtils {
	/**
	 * 获取错误信息的xml字符串
	 * @param result
	 * @return
	 */
	public static String getErrorXml(ResultInfo result) {
		XMLErrorInfo error = new XMLErrorInfo();
		error.setErrorCode(result.getErrorCode());
		error.setErrorMsg(result.getMessage());
		
		XStream xstream = new XStream(new DomDriver("UTF-8"));
		xstream.processAnnotations(XMLErrorInfo.class);
		xstream.registerConverter(new XMLNullConverter(new String[]{"XMLErrorInfo"}));
		return xstream.toXML(error);
	}
	
	/**
	 * 将对象转换为xml字符串
	 * @param object
	 * @param annotationClasses 对象中使用了xstream注解的类
	 * @param clazzNames 需要显示值为null的属性的类名
	 * @return
	 */
	public static String objectToXml(Object object, Class<Object>[] annotationClasses, String[] clazzNames) {
		XStream xstream = new XStream(new DomDriver("UTF-8"));
		for (int i = 0; i < annotationClasses.length; ++i) {
			xstream.processAnnotations(annotationClasses[i]);
		}
		xstream.registerConverter(new XMLNullConverter(clazzNames));
		String xml = xstream.toXML(object);
		
		return xml;
	}
	
	/**
	 * 返回未知的错误
	 * @return
	 */
	public static String getUnknowErrorXml() {
		//返回未知错误
		XMLErrorInfo error = new XMLErrorInfo();
		error.setErrorCode(ResultCodeConstant.ERROR_9999);
		error.setErrorMsg(ResultCodeConstant.ERROR_9999_DESC);
		
		XStream xstream = new XStream(new DomDriver("UTF-8"));
		xstream.processAnnotations(XMLErrorInfo.class);
		xstream.registerConverter(new XMLNullConverter(new String[]{"XMLErrorInfo"}));
		return xstream.toXML(error);
	}
	
	public static void main(String[] args) {

	}
}
