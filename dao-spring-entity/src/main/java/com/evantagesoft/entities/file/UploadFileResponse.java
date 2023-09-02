package com.evantagesoft.entities.file;

public class UploadFileResponse {
	
	private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private String createdfileName;
    

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getCreatedfileName() {
		return createdfileName;
	}

	public void setCreatedfileName(String createdfileName) {
		this.createdfileName = createdfileName;
	}

    
    public UploadFileResponse(String fileName,String createdfileName,String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.createdfileName=createdfileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }
    
    
}
