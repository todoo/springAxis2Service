package cn.com.ws.base;

public class ResultCodeConstant {
	public static final int SUCCESS = 0;
	
	public static final int ERROR_9999 = -9999; 
	public static final String ERROR_9999_DESC = "未知错误";

	public static final int ERROR_1 = -1; 
	public static final String ERROR_1_DESC = "用户名或密码错误";
	public static final int ERROR_2 = -2; 
	public static final String ERROR_2_DESC = "无效token";
	public static final int ERROR_3 = -3; 
	public static final String ERROR_3_DESC = "token已过期";
}
