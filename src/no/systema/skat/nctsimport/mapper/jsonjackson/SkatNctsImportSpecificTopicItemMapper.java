/**
 * 
 */
package no.systema.skat.nctsimport.mapper.jsonjackson;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.skat.nctsimport.model.jsonjackson.topic.items.JsonSkatNctsImportSpecificTopicItemContainer;
import no.systema.skat.nctsimport.model.jsonjackson.topic.items.JsonSkatNctsImportSpecificTopicItemRecord;

/**
 * @author oscardelatorre
 * @date Apr 24, 2014
 * 
 * 
 */
public class SkatNctsImportSpecificTopicItemMapper {
	private static final Logger logger = Logger.getLogger(SkatNctsImportSpecificTopicItemMapper.class.getName());
	
	public JsonSkatNctsImportSpecificTopicItemContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatNctsImportSpecificTopicItemContainer topicItemContainer = mapper.readValue(utfPayload.getBytes(), JsonSkatNctsImportSpecificTopicItemContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicItemContainer.getUser());
		//DEBUG
		Collection<JsonSkatNctsImportSpecificTopicItemRecord> list = topicItemContainer.getOrderList();
		for(JsonSkatNctsImportSpecificTopicItemRecord record : list){
			/*logger.info("Item description (event desc): " + record.getTvinf1());
			logger.info("Place name: " + record.getTvst());
			logger.info("Transport Id: " + record.getTvtaid());
			*/
			
		}
		return topicItemContainer;
	}
}
