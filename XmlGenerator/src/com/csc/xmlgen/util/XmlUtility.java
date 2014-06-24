package com.csc.xmlgen.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public abstract class XmlUtility {
	protected org.w3c.dom.Document dom;

	public XmlUtility() {
		super();
	}

	public final static Document parseDoc(InputSource xmlSource) {
		Document sourceDom = null;
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			documentBuilderFactory.setNamespaceAware(true);
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			sourceDom = documentBuilder.parse(xmlSource);

		} catch (Exception ex) {
			System.out.println("Exception Captured in" + "XmlUtility.java "
					+ " Exception Trace ");
			System.out.println(ex.getMessage());
		}

		return sourceDom;
	}
	
	public final static Document readXMLfromFile(String filePath){
		Document sourceDom = null;
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			documentBuilderFactory.setNamespaceAware(true);
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			File file = new File(filePath);
			if(file.exists()){
				 sourceDom = documentBuilder.parse(file);
				 
	    	}
		} catch (Exception ex) {
			System.out.println("Exception Captured in" + "XmlUtility.java "
					+ " Exception Trace ");
			System.out.println(ex.getMessage());
		}
		return sourceDom;
		
	}

	public final static Element getFirstChildNode(Node node) {
		// TODO Auto-generated method stub
		Node tempNode = null;
		if (node != null) {
			tempNode = node.getFirstChild();
			while (tempNode != null
					&& tempNode.getNodeType() != Node.ELEMENT_NODE) {
				tempNode = tempNode.getNextSibling();
			}
		}
		return (Element) tempNode;
	}

	public static Element getNextChildNode(Node node) {
		// TODO Auto-generated method stub
		Node tempNode = null;
		if (node != null) {
			tempNode = node.getNextSibling();
			while (tempNode != null
					&& tempNode.getNodeType() != Node.ELEMENT_NODE) {
				tempNode = tempNode.getNextSibling();
			}
		}
		return (Element) tempNode;

	}

	public final static String getNodeValue(Node node) {
		// TODO Auto-generated method stub
		Node tempNode = null;
		StringBuffer buff = null;

		if (node != null) {
			tempNode = node.getFirstChild();
			while (tempNode != null
					&& (tempNode.getNodeType() == Node.TEXT_NODE || tempNode
							.getNodeType() == Node.CDATA_SECTION_NODE)) {
				if (buff == null) {
					buff = new StringBuffer();
				}
				String val = tempNode.getNodeValue();
				if (val != null) {
					buff.append(val);
				}
				tempNode = tempNode.getNextSibling();
			}
		}

		if (buff != null) {
			return buff.toString();
		} else {
			return null;
		}

	}

	public final static Document createXmlDoc() {
		Document sourceDom = null;
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			sourceDom = documentBuilder.newDocument();

		} catch (Exception ex) {
			System.out.println("Exception Captured in" + "XmlUtility.java "
					+ " Exception Trace ");
			System.out.println(ex.getMessage());
		
		}

		return sourceDom;
	}

	public final static Element addNodeToDocument(Document sourceDom,
			Element parentElement, String rootName) {

		Element newChildElement = sourceDom.createElement(rootName);
		parentElement.appendChild(newChildElement);
		return newChildElement;
	}

	public final static String getNodeName(Node sourceDom) {
		return sourceDom.getNodeName();
	}

	public final static Element addElementtoDocument(Document sourceDom,
			Element parentElement, String elementName, String elementValue) {

		try {
			Element element = sourceDom.createElement(elementName);
			element.appendChild(sourceDom.createTextNode(elementValue));
			parentElement.appendChild(element);
		} catch (Exception ex) {
			System.out.println("Exception Captured in" + "XmlUtility.java "
					+ " Exception Trace ");
			System.out.println(ex.getMessage());
		
		}

		return parentElement;
	}

	public final static Document addrootNodeToDocument(Document sourceDom,
			String rootName) {
		// TODO Auto-generated method stub
		Element newChildElement = sourceDom.createElement(rootName);
		sourceDom.appendChild(newChildElement);
		return sourceDom;
	}

	/**
	 * Transform an XML and XSL document as <code>File</code>s, placing the
	 * resulting transformed document in a <code>Writer</code>. The output
	 * document could easily be handled as a String (<code>StringWriter</code)>
	 * or as a <code>JSPWriter</code> in a JavaServer page.
	 */
	public final static void applyXsltTransform(File xmlFile, File xslFile)
			throws TransformerException {
		applyXsltTransform(new StreamSource(xmlFile), new StreamSource(xslFile));
	}

	/**
	 * Transform an XML <code>File</code> based on an XSL <code>File</code>,
	 * placing the resulting transformed document in a <code>OutputStream</code>
	 * . Convenient for handling the result as a <code>FileOutputStream</code>
	 * or <code>ByteArrayOutputStream</code>.
	 */

	public final static void applyXsltTransform(File xmlFile, File xslFile,
			OutputStream out) throws TransformerException {
		applyXsltTransform(new StreamSource(xmlFile), new StreamSource(xslFile));
	}

	public final static Document applyXsltTransform(Document sourceDom,
			String xslFilePath) throws TransformerException {
		DOMSource domSource = new DOMSource(sourceDom);
		StreamSource strXslSource;
		String resultXMLStr = null;
		Document resultDom = null;
		try {
			strXslSource = new StreamSource(new InputStreamReader(
					new FileInputStream(xslFilePath)));
			resultXMLStr = applyXsltTransform(domSource, strXslSource);
			InputSource xmlSource = new InputSource(new StringReader(
					resultXMLStr));
			resultDom = XmlUtility.parseDoc(xmlSource);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception Captured in" + "XmlUtility.java "
					+ " Exception Trace ");
			System.out.println(e.getMessage());
			}
		return resultDom;
	}

	/**
	 * Transform an XML source using XSLT based on a new template for the source
	 * XSL document. The resulting transformed document is placed in the passed
	 * in <code>Result</code> object.
	 * 
	 * @return
	 */
	public final static String applyXsltTransform(Source xmlSource, Source xsl)
			throws TransformerException {
		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Templates template = tFactory.newTemplates(xsl);
			Transformer transformer = template.newTransformer();
			StringWriter stringWriter = new StringWriter();
			StreamResult result = new StreamResult(stringWriter);
			transformer.transform(xmlSource, result);
			return stringWriter.toString();
		} catch (TransformerConfigurationException tce) {
			System.out.println("Exception Captured in" + "XmlUtility.java "
					+ " Exception Trace ");
			System.out.println(tce.getMessage());
			throw new TransformerException(tce.getMessageAndLocation());
		} catch (TransformerException te) {
			System.out.println("Exception Captured in" + "XmlUtility.java "
					+ " Exception Trace ");
			System.out.println(te.getMessage());
			throw new TransformerException(te.getMessageAndLocation());
		}
	}

	public final static void saveXMLtoFile(Document responseXmlDoc,
			String filePath) {
		// TODO Auto-generated method stub
		try {
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(responseXmlDoc);
			StreamResult result = new StreamResult(new File(filePath));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
			transformer.transform(source, result);
			//System.out.println("File saved!");
		} catch (Exception e) {
			System.out.println("Exception Captured in" + "XmlUtility.java "
					+ " Exception Trace ");
			System.out.println(e.getMessage());
		
		}

	}

	public static String convertDOMToString(Document sourceDom) {
		try {
			StringWriter stw = new StringWriter();
			Transformer serializer = TransformerFactory.newInstance()
					.newTransformer();
			serializer.transform(new DOMSource(sourceDom),
					new StreamResult(stw));
			return stw.toString();
		} catch (Exception e) {
			System.out.println("Exception Captured in" + "XmlUtility.java "
					+ " Exception Trace ");
			System.out.println(e.getMessage());
		
		}
		return null;
	}

	public static Document convertStringToDOM(String strXML) {
		Document sourceDom = null;
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			sourceDom = documentBuilder.parse(new InputSource(new StringReader(
					strXML)));

		} catch (Exception ex) {
			System.out.println("Exception Captured in" + "XmlUtility.java "
					+ " Exception Trace ");
			System.out.println(ex.getMessage());
		}

		return sourceDom;
	}

	// ****************************************************************************************************************//
	// ** ErrValue : Sets Error Severity of the Response Received from Exceed
	// CommFw **//
	// ** If 0 -> Success **//
	// ** 1 -> Failed with Business Error **//
	// ** 2 -> Failed with System Error **//
	// ** 3 -> Response Time out. **//
	// ** RespErrMsg String Representation of Error extracted from Response
	// Message **//
	// ****************************************************************************************************************//

	// ****************************************************************************************************************//
	// ** Method String getOuterXml(Node) **//
	// ** Returns String representation of OuterXML of a node in XML **//
	// ****************************************************************************************************************//

	private static String getOuterXml(Node node) {
		StringWriter writer;
		writer = new StringWriter();
		try {
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			transformer.setOutputProperty("omit-xml-declaration", "yes");
			transformer
					.transform(new DOMSource(node), new StreamResult(writer));

		} catch (Exception e) {
			System.out.println(e.getStackTrace().toString());
			System.out.println(e.getMessage());
		}
		return writer.toString();
	}

	// ****************************************************************************************************************//
	// ** Method String String checkResponse(String ResponseMsg) **//
	// ** Extracts Error Message from Response received from Exceed CommFW and
	// Returns String representation of it **// **//
	// ****************************************************************************************************************//
	public static String checkResponse(String ResponseMsg) {
		int ErrValue = 0;
		String RespErrMsg = "";
		String ResponseMessage = "";
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(
					ResponseMsg)));

			NodeList ErrList = document.getElementsByTagName("MsgStatusCd");
			NodeList ErrMsgList = document.getElementsByTagName("MsgStatus");

			for (int counter = 0; counter <= ErrMsgList.getLength() - 1; counter++) {
				Node ErrValueNode = ErrList.item(counter);
				if (ErrValue == 0) {
					if (ErrValueNode.getTextContent().equals("BusinessError")) {
						ErrValue = 1;
					} else if (ErrValueNode.getTextContent().equals(
							"SystemError")) {
						ErrValue = 2;
					} else {
						ErrValue = 0;
					}
				}

				Node ErrMsgNode = ErrMsgList.item(counter);
				RespErrMsg = getOuterXml(ErrMsgNode);
				if (ErrValue > 0)
					ResponseMessage = "N" + RespErrMsg.toString();
				else
					ResponseMessage = "Y";

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(RespErrMsg.toString());
		}
		return ResponseMessage;
	}
	
	public static Document updateXmlTagbyIndex(Document sourceDom ,String tagName, String tagData) {
		
		NodeList tagList = sourceDom.getElementsByTagName(tagName);
		if (tagList.getLength()>1){
			Node tagNode;
			for(int count =0;count<tagList.getLength();count++){
				tagNode = tagList.item(count);
				tagNode.setTextContent(tagData + "-" + count);
			}	 
		} else{
			Node tagNode = tagList.item(0);
			tagNode.setTextContent(tagData);
		}
		
		return sourceDom;
		
	}
	
public static Document updaterqUIDbyIndex(Document sourceDom ,String tagName, String tagData) {
		
		NodeList tagList = sourceDom.getElementsByTagName(tagName);
		if (tagList.getLength()>1){
			Node tagNode;
			for(int count =0;count<tagList.getLength();count++){
				tagNode = tagList.item(count);
				tagNode.setTextContent(tagData);
			}	 
		} else{
			Node tagNode = tagList.item(0);
			tagNode.setTextContent(tagData);
		}
		
		return sourceDom;
		
	}

}
