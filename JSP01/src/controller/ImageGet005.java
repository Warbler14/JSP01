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
 * http://localhost:8081/JSP01/drawSpiral?width=500&height=500&x1=250&y1=250&degree=10&radius=50&count=50
 */
@WebServlet("/drawSpiral")
public class ImageGet005 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(ImageGet005.class);

	public final static String [] PARAMETERS = {"width", "height", "x1","y1","degree", "radius", "count"};
	
    public ImageGet005() {
        super();
        
    }
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//---------------------------------------------------------------------------------
		int dataArray [] = new int[PARAMETERS.length];
		
		ArrayList<String> messagelist = null;
		boolean isParamOK = false;
		//---------------------------------------------------------------------------------
		
		try{
			int i = 0;

			for( int j = PARAMETERS.length ; i< j ; i++ ){
				String param = request.getParameter( PARAMETERS[i] );
				
				if( ( param == null ) || param.length() <= 0 ){
					isParamOK = false;
					break;
					
				}else{
					
					dataArray[i] = Integer.valueOf( param );
				
				}
				
				logger.debug( "[" + i + "] " + PARAMETERS[i] + " : " + dataArray[i] );
			}//end for
			logger.debug( "----------------------------------------------------------" );
			
			if( i == PARAMETERS.length ){
				isParamOK = true;
			}
			
		}catch(NumberFormatException n){
			if(messagelist == null){
				messagelist = new ArrayList<String>();
			}
			
			messagelist.add( n.getMessage() );
			
			logger.error("NumberFormatException : " + n.getMessage() );
			
		}catch (Exception e) {
			if(messagelist == null){
				messagelist = new ArrayList<String>();
			}
			
			messagelist.add( e.getMessage() );
			
			logger.error("Exception : " + e.getMessage() );
		}
		
		
		//---------------------------------------------------------------------------------
		
		if( isParamOK ){
			
			
			
			
			try{
				
				paintProcessJPG( response, BufferedImage.TYPE_INT_RGB , Color.white , dataArray);
				
			}catch(Exception e){
				logger.error("Exception : " + e.getMessage() );
			}
			
			
			
			
		}else{
			response.getWriter().println("<h1> bad prameter </h1>");
			if((messagelist != null) && !messagelist.isEmpty() ){ 
				for(int i = 0 , j = messagelist.size() ; i<j ; i++ ){
					response.getWriter().println("<h1> "+ messagelist.get(i) +" </h1>");
				}
			}
		}
		
	}// end doGet
	
	
	private static void paintProcessJPG( HttpServletResponse response
									, int imageType, Paint baseColor
									, int dataArray []) throws Exception{
		
		//----------------------------------------------
		response.setContentType("image/jpeg");
		BufferedImage bi = new BufferedImage(dataArray[0], dataArray[1], imageType);
		Graphics2D g = (Graphics2D) bi.getGraphics();
		g.setPaint(baseColor);
		//----------------------------------------------
		
		//"width(0)", "height(1)", "x1(2)", "y1(3)", "degree(4)", "radius(5)", "count(6)"
		
		/*
		for(int i = 0 ; i < dataArray[6] ; i++ ){
			
			//get position
			int positions [] = getPoint( dataArray[2], dataArray[3], dataArray[4] , dataArray[5] );
			
			//draw Line
			g.drawLine(dataArray[2],dataArray[3], positions[0],  positions[1] );
			
			//reposition
			dataArray[2] = positions[0];
			dataArray[3] = positions[1];
		}
		*/
		/*
		Line2D line1 = new Line2D.Double(10.0, 10.0, 20.0, 20.0);
		Line2D line2 = new Line2D.Double(20.0, 20.0, 40.0, 10.0);
		Line2D line3 = new Line2D.Double(40.0, 10.0, 60.0, 20.0);
		g.draw(line1);
		g.draw(line2);
		g.draw(line3);
		*/
		g.drawLine(10, 10, 20, 20);
		g.drawLine(20 , 20, 40, 10);
		g.drawLine(20 , 20, 40, 90);
		
		//----------------------------------------------
		OutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		out.close();
		//----------------------------------------------
		
	}
	

	private static int [] getPoint( int x1, int y1, int degree, int radius ){
		int positions [] = {0,0};
		int x2 = (int)( Math.cos(Math.toRadians((double)degree)) * radius );
		int y2 = (int)( Math.sin(Math.toRadians((double)degree)) * radius );
		
		//TODO FIX CALCULATION
		if( 0 <= degree && degree <= 90 ){
			positions[0] = x1 + x2;
			positions[1] = y1 + y2;
		}else if( 90 <= degree && degree <= 180 ){
			
		}else if( 180 <= degree && degree <= 270 ){
			
		}else if( 270 <= degree && degree <= 360 ){
			
		}else{
			return positions;
		}
		positions[0] = x1 + (int)( Math.cos(Math.toRadians((double)degree)) * radius );
		positions[1] = y1 + (int)( Math.sin(Math.toRadians((double)degree)) * radius );
		
		logger.debug( "degree : " + degree + ", radius : " + radius);
		logger.debug( "x1 : " + x1 + ", y1 : " + y1);
		logger.debug( "x2 : " + positions[0] + ", y2 : " + positions[1]);
		
		return positions;
	}

}
