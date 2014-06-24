package com.csc.xmlgen.handlers;

import java.io.File;
import java.util.ArrayList;

import org.w3c.dom.Document;

import com.csc.xmlgen.util.HttpPost;
import com.csc.xmlgen.util.XmlUtility;

public class BillingNotifyRequestDispatcher {
	HttpPost httppost;
	String directory;
	File[] files;
	ArrayList<String> filePath = new ArrayList<String>();
	Document sourceDom = null;
	String connectionUrl;
	String contentType;

	public BillingNotifyRequestDispatcher(String connectionUrl, String destDir,
			String contentType) {
		this.connectionUrl = connectionUrl;
		this.contentType = contentType;
		directory = destDir;
		files = new File(directory).listFiles();
		for (File file : files) {
			if (file.isFile()) {
				//System.out.println(file.getAbsolutePath());
				filePath.add(file.getAbsolutePath());
			}
		}
	}

	public void postData() {
		for (int count = 0; count < filePath.size(); count++) {
			sourceDom = XmlUtility.readXMLfromFile(filePath.get(count));
			httppost = new HttpPost(connectionUrl, 10000, contentType);
			httppost.PostDataviaHttp(XmlUtility.convertDOMToString(sourceDom));
		}
	}
}
