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
 *  http://localhost:8081/JSP01/drawOctagon?width=500&height=500&length=100
 */
@WebServlet("/drawOctagon")
public class ImageGet013 extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	static final int OCTAGON_DEGREE = 45;
	static Logger logger = Logger.getLogger(ImageGet007.class);

	public final static String [] PARAMETERS = {"width", "height", "length"};
	
    public ImageGet013() {
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
									, int imageType, Paint baseColor1 
									, int dataArray []) throws Exception{
		
		//----------------------------------------------
		response.setContentType("image/jpeg");
		BufferedImage bi = new BufferedImage(dataArray[0], dataArray[1], imageType);
		Graphics2D g = (Graphics2D) bi.getGraphics();
		//----------------------------------------------
		
		
		
		
		
		
		
		
		
		
		
		
		g.setPaint(baseColor1);
		//"width", "height", "x1","y1","x2","y2" (2,3,4,5)
		//x0 = width/2, y0 = height/2
		
		int[][] pos = getPosition( dataArray[0], dataArray[1], dataArray[2], OCTAGON_DEGREE );
		
		printPosition( pos );
		
		
		for( int i = 0 , j = pos.length-1 ; i<j ; i++ ){
			g.drawLine(pos[i][0], pos[i][1], pos[i+1][0], pos[i+1][1]);
		}
		g.drawLine(pos[7][0], pos[7][1], pos[0][0], pos[0][1]);
		
		
		
		//----------------------------------------------
		OutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		out.close();
		//----------------------------------------------
		
	}
	
	private static int[][] getPosition( int width, int height, int side, int degree ){
		
		double[] dataArr = get_XyR( (double) side/2, (double)degree/2 );
		
		double xm = width/2;
		double ym = height/2;
		double x = dataArr[0]; //Math.round(dataArr[0]*100)/100.0 ;
		double yh = side/2;
		
		System.out.println("xm : " + xm + ", ym : " + ym + ", x : " + x + ", yh : " + yh);
		
		int[][] octaPosition = new int [8][2];
		octaPosition[0][0] = (int)(xm - yh); 	//Ax = xm - yh
		octaPosition[0][1] = (int)(ym - x); 	//Ay = ym - x
		octaPosition[1][0] = (int)(xm + yh); 	//Bx = xm + yh
		octaPosition[1][1] = (int)(ym - x); 	//By = ym - x
		octaPosition[2][0] = (int)(xm + x); 	//Cx = xm + x
		octaPosition[2][1] = (int)(ym - yh); 	//Cy = ym - yh
		octaPosition[3][0] = (int)(xm + x); 	//Dx = xm + x
		octaPosition[3][1] = (int)(ym + yh); 	//Dy = ym + yh
		octaPosition[4][0] = (int)(xm + yh); 	//Ex = xm + yh
		octaPosition[4][1] = (int)(ym + x); 	//Ey = ym + x
		octaPosition[5][0] = (int)(xm - yh); 	//Fx = xm - yh
		octaPosition[5][1] = (int)(ym + x); 	//Fy = ym + x
		octaPosition[6][0] = (int)(xm - x); 	//Gx = xm - x
		octaPosition[6][1] = (int)(ym + yh); 	//Gy = ym + yh
		octaPosition[7][0] = (int)(xm - x); 	//Hx = xm - x
		octaPosition[7][1] = (int)(ym - yh); 	//Hy = ym - yh
				
		return octaPosition;
	}
	

	private static void printPosition( int [][] positions ){
		
		for( int i = 0, j = positions.length ; i<j ; i++ ){
			logger.info("[" + i + "] " + positions[i][0] + ", "+ positions[i][1] );
			
		}
	}
	
	// sinθ = y/r, cosθ = x/r, tanθ = y/x
		private static double[] get_xYR( double x, double rad ){
			double [] returnData = {0.0,0.0,0.0};
			
			returnData[0] = x;
			returnData[1] = x * Math.tan(rad);
			returnData[2] = x / Math.cos(rad);
			
			return returnData;
		}
		
		private static double[] get_XyR( double y, double rad ){
			double [] returnData = {0.0,0.0,0.0};
			
			returnData[0] = y / Math.tan(rad);
			returnData[1] = y;
			returnData[2] = y / Math.sin(rad);
			
			return returnData;
		}
		
		private static double[] get_XYr( double r, double rad ){
			double [] returnData = {0.0,0.0,0.0};
			
			returnData[0] = r * Math.cos(rad);
			returnData[1] = r * Math.sin(rad);
			returnData[2] = r;
			
			return returnData;
		}
		
		private static void print_XYR( double [] data , int digit ){
			System.out.printf("X : %."+ digit+ "f, Y : %."+ digit+ "f, R : %."+ digit+ "f \n", data[0], data[1], data[2]);
		}

}