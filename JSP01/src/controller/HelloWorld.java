package controller;


import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 
 */
@WebServlet("/HelloWorld")
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(HelloWorld.class);
       
  
    public HelloWorld() {
        super();
        
    }
    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		logger.info("HelloWorld call");
		
		Date date = new Date(System.currentTimeMillis());

		resp.getWriter().println("<h1>Hello World!!! "+ date.toString() +"</h1>");
	}

    

}
