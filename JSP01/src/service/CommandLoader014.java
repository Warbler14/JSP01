package service;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import service_interface.CommandLoader;

public class CommandLoader014 implements CommandLoader{
	static Logger logger = Logger.getLogger(CommandLoader014.class);
	
	private String move_page [][] ;
	private String parameters [] ;
	
	public CommandLoader014( String [][] move_page, String [] parameters ){
		this.move_page = move_page;
		this.parameters = parameters;
	}
	@Override
	public String findPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String page = null;
		String cmd = request.getParameter( CommandLoader.CMD );
		
		if(cmd != null && cmd.length()>0){
			for( int i = 0, j = move_page.length ; i < j ; i++ ){
				if(move_page[i][0].equals(cmd)){
					
					page = move_page[i][1];
					
					logger.debug("page : " + page);
					
					break;
				}
			}
		}
		
		return page;
		
	}
	
	@Override
	public void pageMove(HttpServletRequest request, HttpServletResponse response, String page) throws IOException {
		
		try{
			
			RequestDispatcher rd = request.getRequestDispatcher( page );
			rd.forward(request,response);
					
		}catch( ServletException se ){
			logger.error( se.getMessage() );
		}
		
	}
	
	
	@Override
	public void drawImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		ArrayList<String> messagelist = new ArrayList<String>();
		HashMap<String,String> dataMap = loadParam( request, messagelist );
		if( dataMap != null ){
			
			try{
				
				paintProcessJPG( response, BufferedImage.TYPE_INT_RGB , Color.white , dataMap);
				
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
	}
	
	private HashMap<String,String> loadParam( HttpServletRequest request, ArrayList<String> messagelist ){
		//---------------------------------------------------------------------------------
		HashMap<String,String> dataMap = new HashMap<String,String>();
		//---------------------------------------------------------------------------------
		
		try{
			for( int i = 0, j = parameters.length ; i< j ; i++ ){
				String param = request.getParameter( parameters[i] );
				
				dataMap.put(parameters[i], param );
				
				logger.debug( "[" + i + "] " + parameters[i] + " : " + param );
			}//end for
			logger.debug( "----------------------------------------------------------" );
			
		}catch (Exception e) {
			if(messagelist == null){
				messagelist = new ArrayList<String>();
			}
			
			messagelist.add( e.getMessage() );
			
			logger.error("Exception : " + e.getMessage() );
		}
		
		return dataMap;
	}
	
	private static void paintProcessJPG( HttpServletResponse response
			, int imageType, Paint baseColor1 
			, HashMap<String,String> dataMap) throws Exception{
		
		final int dotSize = 50;
		final int module [] = {4,7};
		final Paint baseColor = Color.white;
		int number = Integer.valueOf( dataMap.get("number") );
		
		//----------------------------------------------
		response.setContentType("image/jpeg");
		BufferedImage bi = new BufferedImage(module[0] * dotSize, module[1] * dotSize ,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) bi.getGraphics();
		//----------------------------------------------
		
		g.setPaint(baseColor);
		
		graphicPainter( getMatrix(number) , dotSize, g );
		
		//----------------------------------------------
		OutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		out.close();
		//----------------------------------------------
		
	}
	
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
