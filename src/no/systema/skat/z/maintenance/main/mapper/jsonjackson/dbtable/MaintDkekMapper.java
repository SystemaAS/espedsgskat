/**
 * 
 */
package no.systema.skat.z.maintenance.main.mapper.jsonjackson.dbtable;

//
import java.util.Collection;

//jackson library
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

//application library
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDkekContainer;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDkekRecord;

/**
 * @author oscardelatorre
 * @date Jan, 2021
 * 
 */
public class MaintDkekMapper {
	private static final Logger logger = Logger.getLogger(MaintDkekMapper.class.getName());
	
	public JsonMaintDkekContainer getContainer(String utfPayload) throws Exception{
		ObjectMapper mapper = new ObjectMapper();  
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		//At this point we now have an UTF-8 payload
		JsonMaintDkekContainer container = mapper.readValue(utfPayload.getBytes(), JsonMaintDkekContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintDkekRecord> list = container.getList();
		for(JsonMaintDkekRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
