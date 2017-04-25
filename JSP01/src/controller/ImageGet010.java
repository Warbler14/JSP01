package controller;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
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
 * http://localhost:8081/JSP01/drawMiddleDot?width=500&height=500&x1=50&y1=450&x2=450&y2=50&radius=50
 */
@WebServlet("/drawMiddleDot")
public class ImageGet010 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(ImageGet010.class);

	public final static String [] PARAMETERS = {"width", "height", "x1","y1",   "x2","y2",  "radius"};
	
    public ImageGet010() {
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
				
				paintProcessJPG( response, BufferedImage.TYPE_INT_RGB , Color.white, Color.red, dataArray);
				
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
									, int imageType, Paint baseColor1,  Paint baseColor2
									, int dataArray []) throws Exception{
		
		//----------------------------------------------
		response.setContentType("image/jpeg");
		BufferedImage bi = new BufferedImage(dataArray[0], dataArray[1], imageType);
		Graphics2D g = (Graphics2D) bi.getGraphics();
		g.setPaint(baseColor1);
		//----------------------------------------------
		
		
		
		
		//"width", "height", "x1","y1","x2", "y2" 
		
		g.drawLine(dataArray[2],dataArray[3], dataArray[4], dataArray[5]);
		
		double [] centerPosition = calcPosition(dataArray[2],dataArray[3], dataArray[4], dataArray[5]);
		double radius = Double.valueOf( dataArray[6] );
		double radiusHalf = radius/2.0;
		
		g.setPaint(baseColor2);
		
		logger.info("x : " + centerPosition[0] + ", y : " + centerPosition[1] + ", radius : " + radius + ", radiusHalf : " + radiusHalf);
		
		Shape theCircle = new Ellipse2D.Double(centerPosition[0] - radiusHalf, centerPosition[1] - radiusHalf, radius, radius);
		g.draw(theCircle);
		
		
		//----------------------------------------------
		OutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		out.close();
		//----------------------------------------------
		
	}
	public static double[] calcPosition(int x1, int y1, int x2, int y2){
		double [] position = {getPosition(x1, x2),getPosition(y1, y2)};
		
		return position;
	}
	
	public static double getPosition( int a, int b){
		
		if( a > b ){
			return (( a - b ) / 2) + b ;
		}else if( a < b ){
			return (( b - a ) / 2) + a ;
		}else{
			return a;
		}
	}
	
	

}
