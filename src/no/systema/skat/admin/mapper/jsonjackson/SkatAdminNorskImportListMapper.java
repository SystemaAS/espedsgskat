/**
 * 
 */
package no.systema.skat.admin.mapper.jsonjackson;

//jackson library
import org.slf4j.*;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.skat.admin.model.jsonjackson.topic.JsonSkatAdminNorskImportListContainer;

/**
 * 
 * @author oscardelatorre
 * @date Feb 20, 2014
 * 
 */
public class SkatAdminNorskImportListMapper {
	private static final Logger logger = LoggerFactory.getLogger(SkatAdminNorskImportListMapper.class.getName());
	
	public JsonSkatAdminNorskImportListContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		//At this point we now have an UTF-8 payload
		JsonSkatAdminNorskImportListContainer topicListContainer = mapper.readValue(utfPayload.getBytes(), JsonSkatAdminNorskImportListContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("[JSON-String payload status=OK]  " + topicListContainer.getUser());
		
		//DEBUG 
		/*
		Collection<JsonTdsExportTopicListRecord> list = topicListContainer.getOrderList();
		for(JsonTdsExportTopicListRecord record : list){
			logger.info("TullId: " + record.getTullId());
			logger.info("Avs.: " + record.getAvsNavn());
			
		}*/
		
		
		return topicListContainer;
	}
}
