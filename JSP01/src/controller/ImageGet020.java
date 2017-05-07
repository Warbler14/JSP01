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
 * http://localhost:8081/JSP01/drawSpiral2?width=500&height=500&round=20
 */
@WebServlet("/drawSpiral2")
public class ImageGet020 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static Logger logger = Logger.getLogger(ImageGet020.class);

	public final static String [] PARAMETERS = {"width", "height", "round"};
	
	public final static Color[] COLORS= {Color.blue, Color.cyan, Color.darkGray, Color.green, Color.magenta
										,Color.orange, Color.pink, Color.red, Color.white, Color.yellow };
	
    public ImageGet020() {
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
				
				paintProcessJPG( response, BufferedImage.TYPE_INT_RGB , COLORS , dataArray);
				
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
									, int imageType, Paint[] baseColors
									, int dataArray []) throws Exception{
		
		//----------------------------------------------
		response.setContentType("image/jpeg");
		BufferedImage bi = new BufferedImage(dataArray[0], dataArray[1], imageType);
		Graphics2D g = (Graphics2D) bi.getGraphics();
		//----------------------------------------------
		
		
		int round = dataArray[2];
		
		int divX = dataArray[0] / round;
		int divY = dataArray[1] / round;
		
		int [][] drawArea = new int[][]{{0,0},{dataArray[0],dataArray[1]}};
		int colorIndex  = 0;
		for(int i = 0,  ii = round/2 ; i<ii  ; i++, colorIndex++ ){
			//logger.info( drawArea[0][0] + ", "+ drawArea[0][1] + ", "+ drawArea[1][0] + ", "+ drawArea[1][1]);
			
			if(colorIndex >= baseColors.length){
				colorIndex = 0;
			}
			g.setPaint(baseColors[ colorIndex ]);
			drawArea = drawLines( drawArea, divX, divY, g );
		}
		
		
		//----------------------------------------------
		OutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		out.close();
		//----------------------------------------------
		
	}
	

	private static int [][] drawLines( int [][] drawArea, int divX, int divY, Graphics2D g ){
		int [][] dataArea = new int[][]{  {drawArea[0][0] + divX, drawArea[0][1] + divY}
										, {drawArea[1][0] - divX, drawArea[1][1] - divY}};
		
		logger.info( drawArea[0][0] + ", "+ drawArea[0][1] + ", "+ drawArea[1][0] + ", "+ drawArea[1][1]);
										
		g.drawLine(dataArea[0][0] - divX,	dataArea[0][1], dataArea[1][0], dataArea[0][1]);
		g.drawLine(dataArea[1][0], 			dataArea[0][1], dataArea[1][0], dataArea[1][1]);
		g.drawLine(dataArea[1][0], 			dataArea[1][1], dataArea[0][0], dataArea[1][1]);
		g.drawLine(dataArea[0][0], 			dataArea[1][1], dataArea[0][0], dataArea[0][1] + divY);
		
		return dataArea;
	}
	

}
