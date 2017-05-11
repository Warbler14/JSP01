package controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import service.CommandLoader024;
import service_interface.Page;

/**
 *  http://localhost:8081/JSP01/drawMultiDropBox?cmd=inputPage
 */



@WebServlet("/drawMultiDropBox")
public class ImageGet024 extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	public final static String COMPUTE01 = "drawImage";
	public final static String MOVE_PAGE [][] = {{"inputPage", "/WEB-INF/view/024/inputPage.jsp"}
												,{COMPUTE01, COMPUTE01}};
	public final static String [] PARAMETERS = {"x_pos1", "y_pos1", "x_pos2", "y_pos2", "x_pos3", "y_pos3"};
	
	static Logger logger = Logger.getLogger(ImageGet007.class);

	CommandLoader024 service;
	
	public ImageGet024() {
		super();
	}
	
	@Override
	public void init() throws ServletException{
		logger.info("init");
		service = new CommandLoader024( MOVE_PAGE, PARAMETERS);
	
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
	
	
}