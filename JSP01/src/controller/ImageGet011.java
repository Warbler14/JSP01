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
 *  http://localhost:8081/JSP01/drawBoxing01?width=500&height=500&x1=100&y1=400&x2=400&y2=400&x3=250&y3=120
 */
@WebServlet("/drawBoxing01")
public class ImageGet011 extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(ImageGet007.class);

	public final static String [] PARAMETERS = {"width", "height", "x1","y1","x2", "y2","x3", "y3"};
	
    public ImageGet011() {
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
				
				paintProcessJPG( response, BufferedImage.TYPE_INT_RGB , Color.white, Color.yellow , dataArray);
				
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
									, int imageType, Paint baseColor1 , Paint baseColor2
									, int dataArray []) throws Exception{
		
		//----------------------------------------------
		response.setContentType("image/jpeg");
		BufferedImage bi = new BufferedImage(dataArray[0], dataArray[1], imageType);
		Graphics2D g = (Graphics2D) bi.getGraphics();
		g.setPaint(baseColor1);
		//----------------------------------------------
		
		//"width", "height", "x1","y1","x2", "y2","x3", "y3"
		g.drawLine(dataArray[2], dataArray[3], dataArray[4], dataArray[5]);
		g.drawLine(dataArray[4], dataArray[5], dataArray[6], dataArray[7]);
		g.drawLine(dataArray[6], dataArray[7], dataArray[2], dataArray[3]);
		
		int [][] data = getPosition( new int[]{dataArray[2], dataArray[4], dataArray[6]}
									, new int[]{dataArray[3], dataArray[5], dataArray[7]} );
		
		printPosition(data);
		
		g.setPaint(baseColor2);
		g.drawLine(data[0][0], data[0][1], data[1][0], data[0][1]);
		g.drawLine(data[1][0], data[0][1], data[1][0], data[1][1]);
		g.drawLine(data[1][0], data[1][1], data[0][0], data[1][1]);
		g.drawLine(data[0][0], data[1][1], data[0][0], data[0][1]);
		
		
		//----------------------------------------------
		OutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		out.close();
		//----------------------------------------------
		
	}
	
	private static int [][] getPosition( int [] x, int [] y ){
		// {{small x, small y}, {big x, big y}}
		int [][] positions = {{0,0},{0,0}};
		
		if( x.length <= 0 || y.length <= 0 ) return positions;
		
		try{
			//small
			positions[0][0] = x[0];
			positions[0][1] = y[0];
			
			
			//big
			positions[1][0] = x[0];
			positions[1][1] = y[0];
			
			for( int i = 0, j = x.length ; i<j ; i++ ){
				if( positions[0][0] > x[i] && x[i] >= 0){
					positions[0][0] = x[i];
				}
				
				if( positions[1][0] < x[i] && x[i] >= 0){
					positions[1][0] = x[i];
				}
				
			}
			
			for( int i = 0, j = y.length ; i<j ; i++ ){
				if( positions[0][1] > y[i] && y[i] >= 0){
					positions[0][1] = y[i];
				}
				
				if( positions[1][1] < y[i] && y[i] >= 0){
					positions[1][1] = y[i];
				}
				
			}
		}catch(Exception e){
			
		}
		
		return positions;
	}
	

	private static void printPosition( int [][] positions ){
		
		for( int i = 0, j = positions.length ; i<j ; i++ ){
			logger.info("[" + i + "] " + positions[i][0] + ", "+ positions[i][1] );
			
		}
	}

}