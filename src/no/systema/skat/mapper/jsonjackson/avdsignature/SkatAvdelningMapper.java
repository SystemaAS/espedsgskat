/**
 * 
 */
package no.systema.skat.mapper.jsonjackson.avdsignature;

import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.skat.model.jsonjackson.avdsignature.JsonSkatAvdelningContainer;
import no.systema.skat.model.jsonjackson.avdsignature.JsonSkatAvdelningRecord;

/**
 * @author oscardelatorre
 * @date Jan 27, 2014
 * 
 * 
 */
public class SkatAvdelningMapper {
	private static final Logger logger = Logger.getLogger(SkatAvdelningMapper.class.getName());
	
	/**
	 * 
	 * @param utfPayload
	 * @return
	 * @throws Exception
	 */
	public JsonSkatAvdelningContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		JsonSkatAvdelningContainer container = null;
		if(utfPayload!=null){
			//At this point we now have an UTF-8 payload
			container = mapper.readValue(utfPayload.getBytes(), JsonSkatAvdelningContainer.class); 
			//logger.info(mapper.writeValueAsString(container));
			//logger.info("Mapping Code object from JSON payload...");
			//logger.info("[JSON-String payload status=OK]  " + container.getUser());
			
			//DEBUG
			Collection<JsonSkatAvdelningRecord> fields = container.getAvdelningar();
			for(JsonSkatAvdelningRecord record : fields){
				//logger.info("Avd: " + record.getAvd());
				//logger.info("Namn: " + record.getNamn());
				//logger.info("Tst: " + record.getTst());
			}
		}
			
		return container;
	}
}
