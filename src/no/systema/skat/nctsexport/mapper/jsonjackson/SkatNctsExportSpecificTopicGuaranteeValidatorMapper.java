/**
 * 
 */
package no.systema.skat.nctsexport.mapper.jsonjackson;



import org.slf4j.*;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.skat.nctsexport.model.jsonjackson.topic.validation.JsonSkatNctsExportSpecificTopicGuaranteeValidatorContainer;


/**
 * 
 * @author oscardelatorre
 * @date Apr 14, 2014
 * 
 *
 */
public class SkatNctsExportSpecificTopicGuaranteeValidatorMapper {
	private static final Logger logger = LoggerFactory.getLogger(SkatNctsExportSpecificTopicGuaranteeValidatorMapper.class.getName());
		
	public JsonSkatNctsExportSpecificTopicGuaranteeValidatorContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonSkatNctsExportSpecificTopicGuaranteeValidatorContainer container = mapper.readValue(utfPayload.getBytes(), JsonSkatNctsExportSpecificTopicGuaranteeValidatorContainer.class); 
		//logger.info(mapper.writeValueAsString(topicListContainer));
		logger.info("Mapping Customer object from JSON payload...");
		logger.info("[JSON-String payload status=OK]  " + container.getUser());
			
		return container;
	}
}


