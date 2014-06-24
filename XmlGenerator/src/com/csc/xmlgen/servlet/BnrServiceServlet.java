package com.csc.xmlgen.servlet;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csc.xmlgen.handlers.BillNotifyXMLGenerator;
import com.csc.xmlgen.handlers.BillingNotifyRequestDispatcher;

/**
 * Servlet implementation class BnrServiceServlet
 */
@WebServlet("/BnrServiceServlet")
public class BnrServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String destDirectory = "";
	private static String templateFilePath = "";
	private static String connectionURL = "";
	private static String contentType = "text/xml";
	private static String uuid = "";
	private static String accountNbr = "";
	private static String policyNbr = "";
	private static String clientName = "";
	private static int xmlCount = 0;
	private static String alertMessage = "";
	private static boolean error = false;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BnrServiceServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		uuid = UUID.randomUUID().toString().toUpperCase();
		policyNbr = uuid.substring(0,13);
		accountNbr = uuid.substring(0,13);
		clientName = uuid.substring(0,8);
		destDirectory = request.getParameter("inputDestPath");
		checkIfDirExists(destDirectory);
		if(error){
			request.setAttribute("alertMessage", alertMessage);
			request.setAttribute("error", "true");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		templateFilePath = request.getParameter("inputXmlTemplatePath");
		connectionURL = request.getParameter("inputCommUrl");
		xmlCount = Integer.parseInt(request.getParameter("inputXmlCount"));
		if(xmlCount <=0 ){
			alertMessage = "XML Count is Zero or less" ;
			request.setAttribute("alertMessage", alertMessage);
			request.setAttribute("error", "true");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		try {
		BillNotifyXMLGenerator bnr = new BillNotifyXMLGenerator(xmlCount, uuid, policyNbr, accountNbr, clientName, "");
		bnr.setDestFilePath(destDirectory);
		request.setAttribute("genXMLStart", getDateTime());
		bnr.process(templateFilePath);
		request.setAttribute("genXMLEnd", getDateTime());
		BillingNotifyRequestDispatcher bnrd = new BillingNotifyRequestDispatcher(connectionURL,destDirectory, contentType );
		request.setAttribute("httpPostStart", getDateTime());
		bnrd.postData();
		request.setAttribute("httpPostEnd", getDateTime());
		alertMessage = "XML`s Posted to Communication Framework Successfully" ;
		request.setAttribute("alertMessage", alertMessage);
		request.setAttribute("uuid", uuid);
		request.setAttribute("error", "false");
		} catch (Exception e) {
			// TODO: handle exception
			alertMessage = "Error generating or Posting Data" ;
			request.setAttribute("alertMessage", alertMessage);
			request.setAttribute("error", "true");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/Result.jsp").forward(request, response);

	}
	
	private void checkIfDirExists(String destDirectory) {
		// TODO Auto-generated method stub
		File theDir = new File(destDirectory);

		  // if the directory does not exist, create it
		  if (!theDir.exists()) {
		    try{
		        theDir.mkdir();
		     } catch(SecurityException se){
		    	 alertMessage = "Error Creating Directory " + destDirectory;
		     }        
		     
		  }
	}

	public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS");
        Date date = new Date();
        return dateFormat.format(date);
    }

}
