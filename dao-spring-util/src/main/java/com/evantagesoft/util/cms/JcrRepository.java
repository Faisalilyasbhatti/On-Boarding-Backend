package com.evantagesoft.util.cms;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.Session;

import com.evantagesoft.util.AppContext;
import com.evantagesoft.util.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Nand Khatri
 * @version 1.0
 * @date 3/11/2021
 */
public class JcrRepository {

	static Session session = AppContext.jcrSession;
	
	public static Node getRootNode() throws Exception {
		try{
			Node rootNode = session.getRootNode();
			return rootNode;
		} catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}		
	}

	public static Node getCustomerRootNode() throws Exception {
		Node customerRootNode = null;
		try {
			customerRootNode = getRootNode().getNode(SystemConstant.CUSTOMER_DOCUMENTS);
			return customerRootNode;
		} catch(PathNotFoundException ex){
			ex.printStackTrace();
		}
		try {
			if (customerRootNode == null) {
				customerRootNode = addNode(getRootNode(), SystemConstant.CUSTOMER_DOCUMENTS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return customerRootNode;
	}
	
	public static Node addNode(Node node, String nodeName) throws Exception {
		try{
			Node newNode = node.addNode(nodeName);
			session.save();
			return newNode;
		} catch(Exception ex){
			throw ex;
		}
	}
	
	public static Node addNewNode(String property) throws Exception {
		try{
			Node rootNode = getRootNode();
			Node propertyNode = rootNode.addNode(property);
			session.save();
			return propertyNode;
		} catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public static Node getCustomerNode(String customerId) throws Exception {
		try{
			Node rootNode = getCustomerRootNode();
			Node customerNode = rootNode.getNode(customerId);
			return customerNode;
		} catch(Exception ex){
			ex.printStackTrace();
			return null;
		}		
	}

	/**
	 * Takes a Image Node and Image Data as Input stream and stores Into repository
	 * 
	 * **/
	public static String addImageNode(Node node, String newNodeName, InputStream iStream, String mimeType) throws Exception {
		String nodeName = null;
		try{
			UUID uuid = UUID.randomUUID();
			Node imageNode = node.addNode(newNodeName + "_" + uuid.toString());
			Node fileNode = imageNode.addNode("file", "nt:file");
			Node contentNode = fileNode.addNode("jcr:content", "nt:resource");

			contentNode.setProperty("jcr:data", iStream);
			contentNode.setProperty("jcr:mimeType", mimeType);
			
			session.save();
			nodeName = newNodeName + "_" + uuid.toString();
		} catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
		return nodeName;
	}
	
	/**
	 * This Method will return Image Node data as InputStream
	 * 
	 * **/
	public static Node getFileNode(Node accountNode, String imageNodeName) throws Exception {
		try{
			Node imageNode = accountNode.getNode(imageNodeName);
			Node fileDataNode = imageNode.getNode("file/jcr:content");
			return fileDataNode;
		} catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	public static Node getTempraryNode() throws  Exception {
		Node tempNode = null;
		try {
			tempNode = getRootNode().getNode(SystemConstant.TEMP_DOCUMENTS);
			return tempNode;
		} catch (PathNotFoundException e) {
			e.printStackTrace();
		}
		try {
			if (tempNode == null)
				tempNode = addNode(getRootNode(), SystemConstant.TEMP_DOCUMENTS);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return tempNode;
	}

	/**
	 * This Method will return Image Node data as InputStream
	 *
	 * **/
	public static InputStream getImageFromNode(Node accountNode, String imageNodeName) throws Exception {
		try{
			Node imageNode = accountNode.getNode(imageNodeName);
			Node fileDataNode = imageNode.getNode("file/jcr:content");
			return fileDataNode.getProperty("jcr:data").getStream();
		} catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	/**
	 * This Method will Delete an Image from given Node
	 * 
	 * **/
	public static void deleteImageFromNode(Node accountNode, String imageNodeName) throws Exception {
		
		try{
			Node imageNode = accountNode.getNode(imageNodeName);
			imageNode.remove();			
		} catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	/**
	 * This Method will return Image Node data as InputStream
	 * 
	 * **/
	public static List<String> getImageNodeNameList(Node node) throws Exception {
		List<String> nodeNameList = new ArrayList<String>();
		try{
			NodeIterator nodeIterator = node.getNodes();
			while(nodeIterator.hasNext()){
				Node nodeItem = nodeIterator.nextNode();
				nodeNameList.add(nodeItem.getName());
			}			
			
		} catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
		return nodeNameList;
	}

	public static void moveNode(Node srcNode, Node dstNode) throws Exception {
		String dstPath = dstNode.getPath() + "/"+srcNode.getName();
		session.move(srcNode.getPath(), dstPath);
		session.save();
	}
}
