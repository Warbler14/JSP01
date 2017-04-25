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
 * http://localhost:8081/JSP01/drawMirrorLine?width=500&height=500&x1=250&y1=250&x2=80&y2=300&x3=200&y3=50
 */
@WebServlet("/drawMirrorLine")
public class ImageGet009 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(ImageGet009.class);

	public final static String [] PARAMETERS = {"width", "height", "x1","y1",   "x2","y2",   "x3","y3"};
	
    public ImageGet009() {
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
		
		
		
		
		//"width", "height", "x1","y1","x2", "y2","x3", "y3"
		calcPosition(g, dataArray[2], dataArray[3], dataArray[4], dataArray[5], dataArray[6], dataArray[7]);
		
		
		
		//----------------------------------------------
		OutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		out.close();
		//----------------------------------------------
		
	}
	public static void calcPosition(Graphics2D g, int x1, int y1, int x2, int y2, int x3, int y3){
		int [][] array = calcPosition(x1, y1, x2, y2, x3, y3);
		
		g.drawLine(x2, y2, x3, y3);
		g.drawLine(array[0][0], array[0][1], array[1][0], array[1][1]);
		
	}
	
	
	
	
	public static int [][] calcPosition(int x1, int y1, int x2, int y2, int x3, int y3){
		
		int data [][] = {{getPosition( x1, x2 ), getPosition( y1, y2 )}
					 	,{getPosition( x1, x3 ), getPosition( y1, y3 )}};
		
		return data;
		
	}
	
	private static int getPosition(int a, int b){
		int c;
		
		if( a < b ){
			c = a - (b - a);
		}else if( a > b ){
			c = (a - b) + a;
		}else{
			c = a;
		}
		
		return c;
	}
	

}
