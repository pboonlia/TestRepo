package com.csc.xmlgen.handlers;

import org.w3c.dom.Document;

import com.csc.xmlgen.util.XmlUtility;

public class BillNotifyXMLGenerator {
	String rqUID;
	String policyNbr;
	String accountNbr;
	String cltLastName;
	String zipCode;
	int xmlCount;
	Document sourceDom = null;
	String destFilePath = "";

	public BillNotifyXMLGenerator(int xmlCount, String rqUID, String policyNbr,
			String accountNbr, String cltLastName, String zipCode) {
		this.xmlCount = xmlCount;
		this.rqUID = rqUID;
		this.policyNbr = policyNbr;
		this.accountNbr = accountNbr;
		this.cltLastName = cltLastName;
		this.zipCode = zipCode;
	}

	public String getDestFilePath() {
		return destFilePath;
	}

	public void setDestFilePath(String destFilePath) {
		this.destFilePath = destFilePath;
	}

	public void process(String filePath) {

		sourceDom = XmlUtility.readXMLfromFile(filePath);
		for (int count = 0; count < xmlCount; count++) {
			Document destDom = null;
			String genRqUID = generateRqUID(rqUID, count);
			destDom = XmlUtility.updaterqUIDbyIndex(sourceDom, "RqUID",
					genRqUID);
			destDom = XmlUtility.updateXmlTagbyIndex(sourceDom, "PolicyNumber",
					policyNbr + count);
			destDom = XmlUtility.updateXmlTagbyIndex(sourceDom,
					"AccountNumberId", accountNbr + count);
			destDom = XmlUtility.updaterqUIDbyIndex(sourceDom, "Surname",
					cltLastName + count);
			XmlUtility.saveXMLtoFile(destDom, destFilePath + "//" + genRqUID
					+ ".xml");
		}
	}

	private String generateRqUID(String rqUID, int count) {
		// TODO Auto-generated method stub
		rqUID = rqUID.substring(0, 29) + String.format("%07d", count);
		return rqUID;
	}

}
