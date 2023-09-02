package com.evantagesoft.vo.customer;
import com.evantagesoft.entities.customer.Customer;
import com.evantagesoft.entities.customer.CustomerDocument;

public class CustomerDocumentVo {

    private Long id;
    private Customer customer;
    private String type;
    private String identity;
    private String document;
    private int status;
    private String createdDate;
    private String updatedDate;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
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
    public static CustomerDocument getCustomerDoc(CustomerDocumentVo customerDocumentVo) {
        CustomerDocument customerDocument = null;
        if (customerDocumentVo != null) {
            customerDocument= new CustomerDocument();
            customerDocument.setId(customerDocumentVo.getId());
            customerDocument.setCustomer(customerDocumentVo.getCustomer());
            customerDocument.setType(customerDocumentVo.getType());
            customerDocument.setIdentity(customerDocumentVo.getIdentity());
            customerDocument.setStatus(customerDocumentVo.getStatus());
            customerDocument.setDocument(customerDocumentVo.getDocument());
            customerDocument.setStatus(customerDocumentVo.getStatus());
        }
        return customerDocument;
    }

    public static CustomerDocumentVo getCustomerDocVo(CustomerDocument customerDocument) {
        CustomerDocumentVo customerDocumentVo = null;
        if (customerDocument != null) {
            customerDocumentVo= new CustomerDocumentVo();
            customerDocumentVo.setId(customerDocument.getId());
            customerDocumentVo.setCustomer(customerDocument.getCustomer());
            customerDocumentVo.setType(customerDocument.getType());
            customerDocumentVo.setIdentity(customerDocument.getIdentity());
            customerDocumentVo.setStatus(customerDocument.getStatus());
            customerDocumentVo.setDocument(customerDocument.getDocument());
            customerDocumentVo.setStatus(customerDocument.getStatus());
        }
        return customerDocumentVo;
    }

}
