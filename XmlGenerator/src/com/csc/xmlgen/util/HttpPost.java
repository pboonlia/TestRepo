package com.csc.xmlgen.util;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

public class HttpPost {

	String result;
	public URLConnection con;
	public URL url;
	private String contentType;

	public HttpPost(String connectionURL, int timeOutInterval, String contType) {
		try {
			contentType = contType;
			url = new URL(connectionURL);
			con = url.openConnection();
			// specify that we will send output and accept input
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setDefaultUseCaches(false);
			con.setReadTimeout(timeOutInterval);
			// tell the web server what we are sending
			con.setRequestProperty("Content-Type", contentType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String PostDataviaHttp(String RequestMsg) {

		try {

			OutputStreamWriter writer = new OutputStreamWriter(
					con.getOutputStream());
			writer.write(RequestMsg);
			writer.flush();
			writer.close();

			// reading the response

			InputStreamReader reader = new InputStreamReader(
					con.getInputStream());
			StringBuilder buf = new StringBuilder();
			char[] cbuf = new char[2048];
			int num;
			while (-1 != (num = reader.read(cbuf))) {
				buf.append(cbuf, 0, num);
			}

			result = buf.toString();

		} catch (SocketTimeoutException e) {
			e.printStackTrace(System.out);
			if (this.contentType.equals("text/xml"))
				result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ACORD><Status><StatusCd>SystemError</StatusCd><StatusDesc>Connection Timed Out</StatusDesc></Status><AccountingSvcRs><PersAutoPolicyAddRq_ConversionFormatRs><MsgStatus><MsgStatusCd>SystemError</MsgStatusCd><MsgStatusDesc>Connection Timed Out.</MsgStatusDesc><ExtendedStatus><ExtendedStatusCd>SystemError</ExtendedStatusCd><ExtendedStatusDesc>Connection Timed Out</ExtendedStatusDesc></ExtendedStatus><ExtendedStatus><ExtendedStatusCd>UserError</ExtendedStatusCd><ExtendedStatusDesc>System error occurred.</ExtendedStatusDesc></ExtendedStatus></MsgStatus></PersAutoPolicyAddRq_ConversionFormatRs></AccountingSvcRs></ACORD>";
			if (this.contentType.equals("text/plain"))
				result = "NError Connection Timed Out";
		} catch (ConnectException e) {
			e.printStackTrace(System.out);
			if (this.contentType.equals("text/xml"))
				result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ACORD><Status><StatusCd>SystemError</StatusCd><StatusDesc>Connection Timed Out</StatusDesc></Status><AccountingSvcRs><PersAutoPolicyAddRq_ConversionFormatRs><MsgStatus><MsgStatusCd>SystemError</MsgStatusCd><MsgStatusDesc>Connection Timed Out.</MsgStatusDesc><ExtendedStatus><ExtendedStatusCd>SystemError</ExtendedStatusCd><ExtendedStatusDesc>Connection Timed Out</ExtendedStatusDesc></ExtendedStatus><ExtendedStatus><ExtendedStatusCd>UserError</ExtendedStatusCd><ExtendedStatusDesc>System error occurred.</ExtendedStatusDesc></ExtendedStatus></MsgStatus></PersAutoPolicyAddRq_ConversionFormatRs></AccountingSvcRs></ACORD>";
			if (this.contentType.equals("text/plain"))
				result = "NError Connection Timed Out";
		} catch (Exception e) {
			e.printStackTrace(System.out);
			if (this.contentType.equals("text/xml"))
				result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><ACORD><Status><StatusCd>SystemError</StatusCd><StatusDesc>Connection Timed Out</StatusDesc></Status><AccountingSvcRs><PersAutoPolicyAddRq_ConversionFormatRs><MsgStatus><MsgStatusCd>SystemError</MsgStatusCd><MsgStatusDesc>Connection Timed Out.</MsgStatusDesc><ExtendedStatus><ExtendedStatusCd>SystemError</ExtendedStatusCd><ExtendedStatusDesc>Connection Timed Out</ExtendedStatusDesc></ExtendedStatus><ExtendedStatus><ExtendedStatusCd>UserError</ExtendedStatusCd><ExtendedStatusDesc>System error occurred.</ExtendedStatusDesc></ExtendedStatus></MsgStatus></PersAutoPolicyAddRq_ConversionFormatRs></AccountingSvcRs></ACORD>";
			if (this.contentType.equals("text/plain"))
				result = "NError Connection Timed Out";
		}
		return result;
	}

}
