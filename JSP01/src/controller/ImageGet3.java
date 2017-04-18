package controller;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * http://localhost:8080/JSP01/paintImage?number=1
 */
@WebServlet("/paintImage")
public class ImageGet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(ImageGet3.class);

    public ImageGet3() {
        super();
        
    }
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		//---------------------------------------------------------------------------------
		String number = request.getParameter("number");
		ArrayList<String> messagelist = null;
		
		//---------------------------------------------------------------------------------
		
		int chNum = -1;
		
		if( !( number == null ) && number.length() > 0 ){
			
			try{
				
				chNum = Integer.valueOf( number.substring(0,1) );
				
			}catch(NumberFormatException n){
				if(messagelist == null){
					messagelist = new ArrayList<String>();
				}
				
				messagelist.add( n.getMessage() );
				
				logger.error("NumberFormatException : " + n.getMessage() );
				
			}
		}
		
		logger.debug("number : " + number + ", chNum : " + chNum);
		
		//---------------------------------------------------------------------------------
		final int dotSize = 50;
		final int module [] = {4,7};
		final Paint baseColor = Color.white;
		
		if( chNum > -1 ){
			
			try {
				//----------------------------------------------
				response.setContentType("image/jpeg");
				BufferedImage bi = new BufferedImage(module[0] * dotSize, module[1] * dotSize ,BufferedImage.TYPE_INT_RGB);
				Graphics2D g = (Graphics2D) bi.getGraphics();
				g.setPaint(baseColor);
				//----------------------------------------------
				
				
				
				graphicPainter( getMatrix(chNum) , dotSize, g );
				
				
				
				//----------------------------------------------
				OutputStream out = response.getOutputStream();
				ImageIO.write(bi, "jpg", out);
				out.close();
				//----------------------------------------------
				
			} catch (Exception e) {
				logger.error("NumberFormatException : " + e.getMessage() );
			}
			
			
		}else{
			response.getWriter().println("<h1> bed prameter </h1>");
			if((messagelist != null) && !messagelist.isEmpty() ){ 
				for(int i = 0 , j = messagelist.size() ; i<j ; i++ ){
					response.getWriter().println("<h1> "+ messagelist.get(i) +" </h1>");
				}
			}
		}
		

	}// end doGet
	
	private static void graphicPainter( int [][] matrix, int dotSize, Graphics2D g ){
		
		//height
		for( int iy = 0, ly = matrix.length ; iy<ly ; iy++ ){
			//width
			for( int ix = 0, lx = matrix[0].length ; ix<lx ; ix++ ){
				logger.debug("iy : " + iy + ", ix : " + ix + ", matrix[iy][ix] " + matrix[iy][ix]);
				if( matrix[iy][ix] > 0 ){
					//void java.awt.Graphics.fillRect(int x, int y, int width, int height)
					g.fillRect(ix * dotSize, iy * dotSize, dotSize, dotSize);
				}
				
			}
			
		}
		
	}
	
	private static int [][] getMatrix( int dec ){
		
		switch( dec ){
		case 0 : 
			int [][] matrix0 = {{1,1,1,1}
							   ,{1,0,0,1}
							   ,{1,0,0,1}
							   ,{1,0,0,1}
							   ,{1,0,0,1}
							   ,{1,0,0,1}
							   ,{1,1,1,1}};
			return matrix0;
		case 1 : 
			int [][] matrix1 = {{0,0,1,0}
							   ,{0,0,1,0}
							   ,{0,0,1,0}
							   ,{0,0,1,0}
							   ,{0,0,1,0}
							   ,{0,0,1,0}
							   ,{0,0,1,0}};
			return matrix1;
		case 2 : 
			int [][] matrix2 = {{1,1,1,1}
							   ,{0,0,0,1}
							   ,{0,0,0,1}
							   ,{1,1,1,1}
							   ,{1,0,0,0}
							   ,{1,0,0,0}
							   ,{1,1,1,1}};
			return matrix2;
		case 3 : 
			int [][] matrix3 = {{1,1,1,1}
							   ,{0,0,0,1}
							   ,{0,0,0,1}
							   ,{1,1,1,1}
							   ,{0,0,0,1}
							   ,{0,0,0,1}
							   ,{1,1,1,1}};
			return matrix3;	
		case 4 : 
			int [][] matrix4 = {{1,0,1,0}
							   ,{1,0,1,0}
							   ,{1,0,1,0}
							   ,{1,0,1,0}
							   ,{1,1,1,1}
							   ,{0,0,1,0}
							   ,{0,0,1,0}};
			return matrix4;
		case 5 : 
			int [][] matrix5 = {{1,1,1,1}
							   ,{1,0,0,0}
							   ,{1,0,0,0}
							   ,{1,1,1,1}
							   ,{0,0,0,1}
							   ,{0,0,0,1}
							   ,{1,1,1,1}};
			return matrix5;
		case 6 : 
			int [][] matrix6 = {{1,1,1,1}
							   ,{1,0,0,0}
							   ,{1,0,0,0}
							   ,{1,1,1,1}
							   ,{1,0,0,1}
							   ,{1,0,0,1}
							   ,{1,1,1,1}};
			return matrix6;
		case 7 : 
			int [][] matrix7 = {{1,1,1,1}
							   ,{1,0,0,1}
							   ,{1,0,0,1}
							   ,{0,0,0,1}
							   ,{0,0,0,1}
							   ,{0,0,0,1}
							   ,{0,0,0,1}};
			return matrix7;
		case 8 : 
			int [][] matrix8 = {{1,1,1,1}
							   ,{1,0,0,1}
							   ,{1,0,0,1}
							   ,{1,1,1,1}
							   ,{1,0,0,1}
							   ,{1,0,0,1}
							   ,{1,1,1,1}};
			return matrix8;
		case 9: 
			int [][] matrix9 = {{1,1,1,1}
							   ,{1,0,0,1}
							   ,{1,0,0,1}
							   ,{1,1,1,1}
							   ,{0,0,0,1}
							   ,{0,0,0,1}
							   ,{0,0,0,1}};
			return matrix9;
			
		default : break;
		
		}//end switch
		
		
		int [][] matrix = {{1,0,1,0}
						  ,{0,1,0,1}
						  ,{1,0,1,0}
						  ,{0,1,0,1}
						  ,{1,0,1,0}
						  ,{0,1,0,1}
						  ,{1,0,1,0}};		
		return matrix;		
		
	}
    

}
