package controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import service.CommandLoader022;
import service_interface.Page;

/**
 *  http://localhost:8081/JSP01/drawRotateSymbol?cmd=inputPage
 */



@WebServlet("/drawRotateSymbol")
public class ImageGet022 extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	public final static String COMPUTE01 = "drawImage";
	public final static String MOVE_PAGE [][] = {{"inputPage", "/WEB-INF/view/020/inputPage.jsp"}
												,{COMPUTE01, COMPUTE01}};
	public final static String [] PARAMETERS = {"box", "10", "10"};
	
	static Logger logger = Logger.getLogger(ImageGet007.class);

	CommandLoader022 service;
	
	public ImageGet022() {
		super();
	}
	
	@Override
	public void init() throws ServletException{
		logger.info("init");
		service = new CommandLoader022( MOVE_PAGE, PARAMETERS);
	
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