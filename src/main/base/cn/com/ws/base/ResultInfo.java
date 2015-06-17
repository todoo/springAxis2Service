package cn.com.ws.base;

import java.util.HashMap;
import java.util.Map;

/**
 * 结果集返回bean
 *
 */
public class ResultInfo {

	protected boolean success = false;
	protected String message = null;
	protected int errorCode = 0;
	protected Map<String, Object> data = new HashMap<String, Object>();

	public ResultInfo(boolean success) {
		this.success = success;
	}

	public ResultInfo(String key, Object object) {
		this.success = true;
		this.data.put(key, object);
	}

	public ResultInfo(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public ResultInfo(boolean success, String message, Map<String, Object> data) {
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public void setData(String key, Object data) {
		this.data.put(key, data);
	}

	public void addData(String key, Object data) {
		this.data.put(key, data);
	}

	public Object getData(String key) {
		return data.get(key);
	}
}