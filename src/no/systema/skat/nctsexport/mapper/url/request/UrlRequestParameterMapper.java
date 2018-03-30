/**
 * 
 */
package no.systema.skat.nctsexport.mapper.url.request;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.*;

import org.apache.log4j.Logger;

import no.systema.skat.nctsexport.model.jsonjackson.topic.JsonSkatNctsExportSpecificTopicRecord;
import no.systema.skat.nctsexport.model.jsonjackson.topic.items.JsonSkatNctsExportSpecificTopicItemRecord;
import no.systema.skat.util.SkatConstants;

/**
 * 
 * @author oscardelatorre
 * @param Apr 14, 2014
 * 
 */
public class UrlRequestParameterMapper {
	private static final Logger logger = Logger.getLogger(UrlRequestParameterMapper.class.getName());
	
	
	/**
	 * Builds the final url parameter list (to send with a GET or POST form method)
	 * @param object
	 * @return
	 * 
	 */
	public String getUrlParameterValidString(JsonSkatNctsExportSpecificTopicRecord object){
		StringBuffer sb = new StringBuffer();
		try{
			for(Field field: object.getFields()){
				try{
					field.setAccessible(true);//we must do this in order to access private fields
					String value = (String)field.get(object); 
					if(value==null){
						sb.append("");
					}else{
						//CRUCIAL! to encode the value in order to handle all special characters (%,&,",',()...) before JSON-call
						//& will be converted into "%26", %="%25", etc. 
						//Refer to URLEncode special characters for further info)
						value = URLEncoder.encode(value, "UTF-8");
						
						sb.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
						sb.append(value.trim());
					}
				}catch(Exception e){
					//Try Integer
					if(field.get(object) instanceof Integer){
						Integer value = (Integer)field.get(object); 
						sb.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
						sb.append(value);
					}else if(field.get(object) instanceof Double){
						Double value = (Double)field.get(object); 
						sb.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
						sb.append(value);
					}else{
						logger.info(" [INFO]data type not yet supported...");
					}
					//add more instances if you need...
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * Builds the final url parameter list (to send with a GET or POST form method)
	 * @param object
	 * @return
	 */
	public String getUrlParameterValidString(JsonSkatNctsExportSpecificTopicItemRecord object){
		StringBuffer sb = new StringBuffer();
		try{
			for(Field field: object.getFields()){
				try{
				field.setAccessible(true);//we must do this in order to access private fields
				String value = (String)field.get(object); 
				if(value==null){
					sb.append("");
				}else{
					//CRUCIAL! to encode the value in order to handle all special characters (%,&,",',()...) before JSON-call
					//& will be converted into "%26", %="%25", etc. 
					//Refer to URLEncode special characters for further info)
					value = URLEncoder.encode(value, "UTF-8");
					
					sb.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
					sb.append(value.trim());
				}
				}catch(Exception e){
					//Try Integer
					if(field.get(object) instanceof Integer){
						Integer value = (Integer)field.get(object); 
						sb.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
						sb.append(value);
					}else if(field.get(object) instanceof Double){
						Double value = (Double)field.get(object); 
						sb.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + field.getName() + "=");
						sb.append(value);
					}else{
						logger.info(" [INFO]data type not yet supported...");
					}
					//add more instances if you need...					
					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	
}
