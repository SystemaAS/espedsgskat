/**
 * 
 */
package no.systema.skat.z.maintenance.main.mapper.jsonjackson.dbtable;

//
import java.util.Collection;

//jackson library
import org.slf4j.*;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktkdContainer;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktkdRecord;

/**
 * @author oscardelatorre
 * @date Feb 28, 2017
 * 
 */
public class MaintDktkdMapper {
	private static final Logger logger = LoggerFactory.getLogger(MaintDktkdMapper.class.getName());
	
	public JsonMaintDktkdContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintDktkdContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintDktkdContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintDktkdRecord> list = container.getList();
		for(JsonMaintDktkdRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
