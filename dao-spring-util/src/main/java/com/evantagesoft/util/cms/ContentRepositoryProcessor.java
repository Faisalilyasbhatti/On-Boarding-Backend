package com.evantagesoft.util.cms;

import org.springframework.stereotype.Component;

import javax.jcr.Node;
import javax.jcr.Property;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author Nand Khatri
 * @version 1.0
 * @date 3/11/2021
 */
@Component
public class ContentRepositoryProcessor {

    public String addFileNode(String customerId, InputStream stream, String fileName, String mimeType) throws Exception {
        String url = "";
        Node tempraryNode = JcrRepository.getTempraryNode();
        url = JcrRepository.addImageNode(tempraryNode, fileName, stream, mimeType);
        return url;
    }

    public InputStream getFileStream(String customerId, String fileName) throws Exception {
        InputStream is = null;
        String url = "";
        Node customerNode = JcrRepository.getCustomerNode(customerId);
        if (customerNode != null) {
            is = JcrRepository.getImageFromNode(customerNode, fileName);
        }
        return is;
    }

    public Node getFileNode(String customerId, String fileName) throws Exception {
        Node fileData = null;
        Node customerNode = JcrRepository.getCustomerNode(customerId);
        if (customerNode != null) {
            fileData = JcrRepository.getFileNode(customerNode, fileName);
        }
        return fileData;
    }

    public void moveNode(String customerId, String fileName) throws Exception {
        Node customerNode = JcrRepository.getCustomerNode(customerId);
        if (customerNode == null) {
            customerNode = JcrRepository.addNode(JcrRepository.getCustomerRootNode(), customerId);
        }
        Node tempraryNode = JcrRepository.getTempraryNode();
        JcrRepository.moveNode(tempraryNode.getNode(fileName), customerNode);
    }

    public InputStream getFileStream(Node node) throws Exception {
        return node.getProperty("jcr:data").getStream();
    }
    public String getMimeType(Node node) throws Exception {
        Property property = node.getProperty("jcr:mimeType");
        return property.getValue().getString();
    }
    public String getFileName(Node node) throws Exception {
        Node parentNode = node.getParent();
        String fileName = UUID.randomUUID().toString();
        String type = getMimeType(node);
        if (type.equals("image/jpeg")) {
            fileName = fileName+".jpeg";
        } else if (type.equals("image/png")) {
            fileName = fileName+".png";
        } else if (type.contains(".pdg")) {
            fileName = fileName + ".pdf";
        }
        return fileName;
    }
}
