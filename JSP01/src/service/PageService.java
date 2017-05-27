package service;


import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class PageService {
	public final static String PAGE_PROPERTIES = "conf/PAGE.properties";
	
	
    /**
	 * 프로퍼티 파일을 조회하여 리스트 정보를 배열로 변환하여 반환
	 * 
	 */
	public String[] getConfigurationArray( String properties, String key ){
		String[] array = null;
		
		// 참조
		// Apache Commons library : https://commons.apache.org/proper/commons-configuration/download_configuration.cgi
		// API document : https://commons.apache.org/proper/commons-configuration/javadocs/v1.10/apidocs/index.html
		
		try{
			System.out.println("CHECK PARAM");
			
			Configuration config = new PropertiesConfiguration( properties );
			array = config.getStringArray( key );
			System.out.println("CHECK PARAM2");
		}catch (ConfigurationException ce) {
			System.out.println(ce.getMessage());
	       
		}catch(Exception e){
			System.out.println("Exception : " + e.getMessage());
		}
		
		return array;
	}
	
	/**
	 * 프로퍼티 파일에서 메시지를 조회함
	 * 
	 */
	public String getConfigurationMessage( String key ){
		return getConfigurationMessage( PAGE_PROPERTIES, key );
	}
	
	public String getConfigurationMessage( String properties, String key ){
		String message = null;
		
		try{
			Configuration config = new PropertiesConfiguration( properties );
			message = config.getString( key );
		
		}catch (ConfigurationException ce) {
			System.out.println(ce.getMessage());
	       
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return message;
	}

}
