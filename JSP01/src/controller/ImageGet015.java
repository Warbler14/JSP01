package controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import service.CommandLoader015;
import service_interface.Page;

/**
 *  http://localhost:8081/JSP01/drawSymbol2?cmd=inputPage
 */



@WebServlet("/drawSymbol2")
public class ImageGet015 extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	public final static String COMPUTE01 = "drawImage";
	public final static String MOVE_PAGE [][] = {{"inputPage", "/WEB-INF/view/015/inputPage.jsp"}
												,{COMPUTE01, COMPUTE01}};
	public final static String [] PARAMETERS = {"box", "10", "10"};
	
	static Logger logger = Logger.getLogger(ImageGet007.class);

	CommandLoader015 service;
	
	public ImageGet015() {
		super();
	}
	
	@Override
	public void init() throws ServletException{
		logger.info("init");
		service = new CommandLoader015( MOVE_PAGE, PARAMETERS);
	
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		logger.info("doGet");
		String page = service.findPage(request, response);
		
		System.out.println( page );
		
		if( page != null ){
			if( COMPUTE01.equals(page) ){
				
				service.drawImage(request, response);
				
			}else{
				service.pageMove(request, response, page);
			}
		}else{
			service.pageMove(request, response, Page.ERROR_PAGE);
		}
		
	}// end doGet
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		logger.info("doPost");
		
		service.drawImage(request, response);
		
	}// end doGet
	
}