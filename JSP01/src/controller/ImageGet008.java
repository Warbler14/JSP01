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
 * http://localhost:8081/JSP01/drawXline?width=500&height=500&x1=250&y1=250&x2=50&y2=80
 */
@WebServlet("/drawXline")
public class ImageGet008 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(ImageGet008.class);

	public final static String [] PARAMETERS = {"width", "height", "x1","y1","x2", "y2"};
	
    public ImageGet008() {
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
		calcPosition(g, dataArray[2], dataArray[3], dataArray[4], dataArray[5]);
		
		
		
		//----------------------------------------------
		OutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		out.close();
		//----------------------------------------------
		
	}
	public static void calcPosition(Graphics2D g, int x1, int y1, int x2, int y2){
		int [][] vector = calcPosition(x1, y1, x2, y2);
		
		for(int i = 0, j=vector.length ; i<j ; i++ ){
			g.drawLine(x1, y1, vector[i][0], vector[i][1]);
		}
		
	}
	
	
	
	public static int [][] calcPosition(int x1, int y1, int x2, int y2){
		int [][] invertedVector = calcPosition(x2, y2);
		
		
		int [][] resultArray = {{x1 + invertedVector[0][0], y1 + invertedVector[0][1] }, {x1 + invertedVector[1][0], y1 + invertedVector[1][1]}
							  , {x1 + invertedVector[2][0], y1 + invertedVector[2][1] }, {x1 + invertedVector[3][0], y1 + invertedVector[3][1]}};
		
		return resultArray;
		
	}
	
	
	public static int [][] calcPosition(int x, int y){
		int [][] invertedVector = {{-1,-1}, { 1,-1}
								  ,{-1, 1}, { 1, 1}};
		
		
		int [][] resultArray = {{invertedVector[0][0]*x, invertedVector[0][1]*y}, {invertedVector[1][0]*x, invertedVector[1][1]*y}
							  , {invertedVector[2][0]*x, invertedVector[2][1]*y}, {invertedVector[3][0]*x, invertedVector[3][1]*y}};
		
		return resultArray;
		
	}
	

}
