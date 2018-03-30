/**
 * 
 */
package no.systema.skat.skatimport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.skat.skatimport.model.jsonjackson.topic.logging.JsonSkatImportSpecificTopicLoggingLargeTextContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.logging.JsonSkatImportSpecificTopicLoggingLargeTextRecord;

/**
 * @author oscardelatorre
 * @date Feb 14, 2014
 * 
 */
public class SkatImportSpecificTopicLoggingLargeTextMapper {
	private static final Logger logger = Logger.getLogger(SkatImportSpecificTopicLoggingLargeTextMapper.class.getName());
	
	public JsonSkatImportSpecificTopicLoggingLargeTextContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatImportSpecificTopicLoggingLargeTextContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatImportSpecificTopicLoggingLargeTextContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
		
		//DEBUG
		Collection<JsonSkatImportSpecificTopicLoggingLargeTextRecord> list = container.getLoggtext();
		for(JsonSkatImportSpecificTopicLoggingLargeTextRecord record : list){
			//logger.info("Message nr (topic logging): " + record.getF0078a());
		}
		return container;
	}
}
