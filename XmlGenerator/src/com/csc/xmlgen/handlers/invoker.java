package com.csc.xmlgen.handlers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class invoker {

	/**
	 * @param args
	 */
	private static String destDirectory = "D://ExceedJ_Installs//project Data//BNRPerf//06172014//generated//NBS";
	private static String templateFilePath = "D://ExceedJ_Installs//project Data//BNRPerf//06172014//template//TEST2A-NBS.xml";
	private static String connectionURL = "http://20.15.80.57:9080/commfw_8086_nor151/servlet/CommFwServlet?genNewTranID=true&AdapterId=BillingNotifyRq";
	private static String contentType ="text/xml";
	private static String uuid =  "";
	private static String accountNbr = "";
	private static String policyNbr = "";
	private static String clientName = "";
	private static int xmlCount=300;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		uuid = UUID.randomUUID().toString().toUpperCase();
//		policyNbr = "MIX-TEST15";
//		accountNbr = "MIX-TEST15";
		policyNbr = uuid.substring(0,13);
		accountNbr = uuid.substring(0,13);
		clientName = uuid.substring(0,8);
		BillNotifyXMLGenerator bnr = new BillNotifyXMLGenerator(xmlCount, uuid, policyNbr, accountNbr, clientName, "");
		bnr.setDestFilePath(destDirectory);
		System.out.println("Generate XML Start:" + getDateTime());
		bnr.process(templateFilePath);
		System.out.println("Generate XML ends:" + getDateTime());
		BillingNotifyRequestDispatcher bnrd = new BillingNotifyRequestDispatcher(connectionURL,destDirectory, contentType );
		System.out.println("Http Post starts:" + getDateTime());
		bnrd.postData();
		System.out.println("Http Post ends:" + getDateTime());
	}
	
	public static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");
        Date date = new Date();
        return dateFormat.format(date);
    }

}
