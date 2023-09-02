package com.evantagesoft.vo.business;
import com.evantagesoft.entities.business.Business;
import com.evantagesoft.entities.business.BusinessDocument;

public class BusinessDocumentVo {

    private Long id;
    private String type;
    private String identity;
    private String document;
    private int status;
    private BusinessVo business;
    private Business businessDocuments;
    private String createdDate;
    private String updatedDate;

    public String getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    public String getUpdatedDate() {
        return updatedDate;
    }
    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getIdentity() {
        return identity;
    }
    public void setIdentity(String identity) {
        this.identity = identity;
    }
    public String getDocument() {
        return document;
    }
    public void setDocument(String document) {
        this.document = document;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public BusinessVo getBusiness() {
        return business;
    }
   
    public Business getBusinessDocuments() {
		return businessDocuments;
	}
	public void setBusinessDocuments(Business businessDocuments) {
		this.businessDocuments = businessDocuments;
	}
	public void setBusiness(BusinessVo business) {
        this.business = business;
    }

    public static BusinessDocument getBusinessDocumnet(BusinessDocumentVo businessDocumentVo) {
        BusinessDocument businessDocument = null;
        if (businessDocumentVo != null) {
            businessDocument= new BusinessDocument();
            businessDocument.setId(businessDocumentVo.getId());
            businessDocument.setBusiness(businessDocumentVo.getBusinessDocuments());
            businessDocument.setType(businessDocumentVo.getType());
            businessDocument.setIdentity(businessDocumentVo.getIdentity());
            businessDocument.setStatus(businessDocumentVo.getStatus());
            businessDocument.setDocument(businessDocumentVo.getDocument());
            businessDocument.setStatus(businessDocumentVo.getStatus());
        }
        return businessDocument;
    }
    
    
    public static BusinessDocumentVo getBusinessDocumnetVo(BusinessDocument businessDocument) {
    	BusinessDocumentVo BusinessDocumentVo = null;
        if (businessDocument != null) {
        	BusinessDocumentVo= new BusinessDocumentVo();
        	BusinessDocumentVo.setId(businessDocument.getId());
//        	BusinessDocumentVo.setBusiness(businessDocument.getBusiness());
        	BusinessDocumentVo.setType(businessDocument.getType());
        	BusinessDocumentVo.setIdentity(businessDocument.getIdentity());
        	BusinessDocumentVo.setStatus(businessDocument.getStatus());
        	BusinessDocumentVo.setDocument(businessDocument.getDocument());
        	BusinessDocumentVo.setStatus(businessDocument.getStatus());
        }
        return BusinessDocumentVo;
    }

    
 

    
}
