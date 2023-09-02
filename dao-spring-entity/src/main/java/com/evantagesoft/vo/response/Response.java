package com.evantagesoft.vo.response;

import java.util.HashMap;
import java.util.Map;

import com.evantagesoft.util.response.EVSResponse;

/**
 * @author Nand Khatri
 * Nov 05th 2020
 */
public class Response {

	private String code;
	private String message;
	private Map<String, Object> responseData;	
	public Response() { }
	
	public Response(String code, String message) {
		this.code = code;
		this.message = message;
	}
	public Response(EVSResponse responseEnum, Map<String, Object> responseData) {
		this.code = responseEnum.getCode();
		this.message = responseEnum.getMessage();
		this.responseData = responseData;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, Object> getResponseData() {
		return responseData;
	}
	public void setResponseData(Map<String, Object> responseData) {
		this.responseData = responseData;
	}
	public void setResponse(EVSResponse response) {
		this.code = response.getCode();
		this.message = response.getMessage();
	}
	public void setData(String key, Object obj) {
		Map<String, Object> responseData = new HashMap<>();
		responseData.put(key, obj);
		this.responseData = responseData;
	}
}
