package service;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import service_interface.CommandLoader;

public class CommandLoader015 implements CommandLoader{
	static Logger logger = Logger.getLogger(CommandLoader015.class);
	
	private String move_page [][] ;
	private String parameters [] ;
	
	public CommandLoader015( String [][] move_page, String [] parameters ){
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
		int [][] dataArr = loadParam( request, messagelist );
		if( dataArr.length > 0 ){
			
			try{
				
				paintProcessJPG( request, response, BufferedImage.TYPE_INT_RGB , Color.white, dataArr);
				
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
	
	private int [][] loadParam( HttpServletRequest request, ArrayList<String> messagelist ){
		//---------------------------------------------------------------------------------
		int [][] dataArr = new int [Integer.valueOf( parameters[1])][Integer.valueOf( parameters[2])];
		
		//---------------------------------------------------------------------------------
		
		try{
			for( int y = 0, yy = Integer.valueOf( parameters[1]) ; y< yy ; y++ ){
				for( int x = 0, xx = Integer.valueOf( parameters[2]) ; x< xx ; x++ ){
					
					String param = request.getParameter( parameters[0] + "_" + y + "_"+ x );
					
					dataArr[y][x] = Integer.valueOf( param );
					
					logger.debug( "[" + y + "][" + x + "] : " + dataArr[y][x] );
					
				}
			}//end for
			logger.debug( "----------------------------------------------------------" );
			
		}catch (Exception e) {
			if(messagelist == null){
				messagelist = new ArrayList<String>();
			}
			
			messagelist.add( e.getMessage() );
			
			logger.error("Exception : " + e.getMessage() );
		}
		
		return dataArr;
	}
	
	private static void paintProcessJPG( HttpServletRequest request, HttpServletResponse response
			, int imageType, Paint baseColor, int [][] dataArr ) throws Exception{
		
		final int dotSize = 50;
		final int module [] = {10,10};
		
		//----------------------------------------------
		response.setContentType("image/jpeg");
		BufferedImage bi = new BufferedImage(module[0] * dotSize, module[1] * dotSize ,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) bi.getGraphics();
		//----------------------------------------------
		
		g.setPaint(baseColor);
		
		
		
		graphicPainter( dataArr , dotSize, g );
		
		
		
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
	
}
