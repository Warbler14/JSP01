package controller;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 
 */
@WebServlet("/ImageGet")
public class ImageGet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(ImageGet.class);

    public ImageGet() {
        super();
        
    }
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String pathToWeb = getServletContext().getRealPath(File.separator);
		
		logger.info("pathToWeb : " + pathToWeb );
		
		
		String fileName = request.getParameter("fileName");
		
		logger.info("fileName : " + fileName);
		
		String serverImagePath = getServletContext().getRealPath("/WEB-INF/image/" + fileName );
		
		logger.info("serverImagePath : " + serverImagePath);
		
		File f = new File( serverImagePath );
		
		if( f.exists() ){
			
			response.setContentType("image/jpeg");
			
			BufferedImage bi = ImageIO.read(f);
			OutputStream out = response.getOutputStream();
			ImageIO.write(bi, "jpg", out);
			out.close();
			
		}else{
			response.getWriter().println("<h1>There is no \"" + fileName + "\"</h1>");
		}
		
		
		

	}
    

}
