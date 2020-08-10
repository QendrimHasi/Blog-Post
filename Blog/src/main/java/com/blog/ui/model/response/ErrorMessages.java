package com.blog.ui.model.response;

public enum ErrorMessages {
	
	MISSING_REQUIRED_FIELD("Missing required field. Please check dokumentation for required fields"),
	RECORD_ALREDY_EXISTS("Record alredy exists"),
	INERNAL_SERVER_ERROR("Internal server Error"),
	NO_RECORD_FOUND("Record with provided it nis not found"),
	AUTHENTICATION_FAILED("Authentication failed"),
	COULD_NOT_UPDATE_RECORD("Could not update Record"),
	COULD_NOT_DELETE_RECORD("Could not delete Record"),
	EMAIL_ADDRESS_NOT_VERIFIED("Email address could not be found"),
	FORMAT_NOT_SUPORTED("We dont suport this format"),
	USER_NOT_FOUND("User not found"),
	POST_NOT_FOUND("Post not found\""),
	COMMENT_NOT_FOUND("Comment not found\""),
	REPLIE_NOT_FOUND("Replie not found\""),
	EMAIL_EXIST("This email exists, Try agan.");
	
	
	private String errorMessage;

	private ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
