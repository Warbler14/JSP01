package service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import service_interface.CommandLoader;

public class CommandLoader030 implements CommandLoader{
	static Logger logger = Logger.getLogger(CommandLoader030.class);
	
	private String move_page [][] ;
	private String parameters [] ;
	
	public CommandLoader030( String [][] move_page, String [] parameters ){
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
		HashMap<String, String> dataMap = loadParam( request, messagelist );
		if( dataMap != null && !dataMap.isEmpty() ){
			
			try{
				
				paintProcessJPG( request, response, BufferedImage.TYPE_INT_RGB , Color.orange, dataMap);
				
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
	
	private HashMap<String, String> loadParam( HttpServletRequest request, ArrayList<String> messagelist ){
		//---------------------------------------------------------------------------------
		HashMap<String, String> dataMap = new HashMap<String, String>();
		//---------------------------------------------------------------------------------
		
		try{
			for( int y = 0, yy = parameters.length ; y< yy ; y++ ){
				
				String param = URLDecoder.decode( request.getParameter( parameters[y] ),"UTF-8");
				logger.debug("param : " + parameters[y] + ", value : " + param);
				
				dataMap.put( parameters[y] , param );
				
				
				
				logger.debug( "[" + y + "] : " + dataMap.get(parameters[y]) );
				
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
	
	private static void paintProcessJPG( HttpServletRequest request, HttpServletResponse response
			, int imageType, Paint baseColor, HashMap<String, String> dataMap ) throws Exception{
		
		final int module [] = {500,200};
		final int textPos [] = {10,10};
		final int letterWidth = Integer.valueOf( dataMap.get( "letterWidth" ) );
		final int letterHight = Integer.valueOf( dataMap.get( "letterHight" ) );
		
		//----------------------------------------------
		response.setContentType("image/jpeg");
		BufferedImage bi = new BufferedImage(module[0], module[1], BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) bi.getGraphics();
		//----------------------------------------------
		
		g.setPaint(baseColor);
		
		//int style = Font.PLAIN | Font.ITALIC;
		int style = Font.PLAIN;
		g.setFont(new Font( "맑은고딕", style, 12 ));
		
		ArrayList<String> strArr = getStrArr( dataMap.get( "message" ), letterWidth );
		for( int i = 0, j = strArr.size() ; i<j ; i++ ){
			
			String [] strLines = strArr.get(i).split("%0A");
			logger.debug( "strLines size : " + strLines.length);
			
			for( int idx = 0, end = strLines.length ; idx<end ; idx++ ){
				g.drawString( strLines[idx], textPos[0], textPos[1] + letterHight*i + letterHight*idx );
			}
			
		}
		
		//----------------------------------------------
		OutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		out.close();
		//----------------------------------------------
		
	}
	
	private static ArrayList<String> getStrArr( final String strOrg, int length ){
		
		String str = strOrg;
		str = str.replaceAll("%0A", String.valueOf('\n'));
		
		logger.debug("str : " + str);
		
		ArrayList<String> rtnData = new ArrayList<String>();
		
		char [] charArr = str.toCharArray();
		int newLineCount = 0;
		
		if( charArr != null && charArr.length > 0 ){
			StringBuffer buff = new StringBuffer( length );
			for( int i = 0, j = charArr.length ; i<j ; i++, newLineCount++ ){
				
				if( '\n' == charArr[i] ){
					logger.debug(buff.toString());
					rtnData.add( buff.toString() );
					buff = new StringBuffer( length );
					newLineCount = 0;
				}
				
				if( newLineCount>0 && (newLineCount % (length-1)) == 0 ){
					logger.debug(buff.toString());
					rtnData.add( buff.toString() );
					buff = new StringBuffer( length );
				}
				
				buff.append( charArr[i] );
			}
			logger.debug(buff.toString());
			rtnData.add( buff.toString() );
			
		}
		
		
		return rtnData;
	}
	
}
