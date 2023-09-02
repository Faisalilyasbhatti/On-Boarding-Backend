package com.evantagesoft.util.response;

/**
 * @author Nand Khatri
 * Nov 05th 2020
 */
public enum EVSResponse {

	SUCCESS("DAO_SUCCESS_00", "Success"),
	INVALID_REQUEST_PARAMETER("DAO_ERROR_01", "Invalid request parameter"),
	INVALID_EMAIL("DAO_ERROR_01", "Invalid Email"),
	INVALID_ID("DAO_ERROR_01", "Invalid ID"),
	EXPIRED_OTP("DAO_ERROR_01", "Expired Otp"),
	INVALID_CREDENTIALS("DAO_ERROR_01", "Invalid login credentials"),
	USER_NOT_FOUND("DAO_ERROR_04", "User not found"),
	DATA_NOT_FOUND("DAO_ERROR_04", "Data not found"),
	CUSTOMER_ERROR("DAO_ERROR_99", "Customer Object is null"),
	BUSINESS_ERROR("DAO_ERROR_91", "Business Object is null"),
	CUSTOMER_ADDRESS_ERROR("DAO_ERROR_99", "Customer Address Object is null"),
	BUSINESS_ADDRESS_ERROR("DAO_ERROR_92", "Business Address Object is null"),
	CUSTOMER_DOCUMENT("DAO_ERROR_99", "Customer Document Object is null"),
	BUSINESS_DOCUMENT("DAO_ERROR_93", "Business Document Object is null"),
	EMAIL_IS_EMPTY("DAO_ERROR_05", "Email is empty"),
	CONTACT_IS_EMPTY("DAO_ERROR_05", "Contact is empty"),
	FIRST_NAME_EMPTY("DAO_ERROR_05", "First Name is empty"),
	USER_ROLE_VO_EMPTY("DAO_ERROR_05", "User Role Vo Empty is empty"),
	EMAIL_ALREADY_EXIST("DAO_ERROR_06", "Email already exist"),
	INVALID_STATUS("DAO_ERROR_07","Invalid status"),
	CUSTOMER_NOT_FOUND("DAO_ERROR_08", "Customer not found"),
	INVALID_BUSINESSID("DAO_ERROR_09", "Invalid business"),
	UNCOMPLETE_CUSTOMER_DATA("DAO_ERROR_06", "Customer data is not complete"),
	SYSTEM_ERROR("DAO_ERROR_99", "System error");


	
	private final String code;
	private final String message;
	
	EVSResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
}
