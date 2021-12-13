/**
 * 
 */
package no.systema.skat.z.maintenance.skatnctsexport.mapper.jsonjackson.dbtable;

//
import java.util.Collection;

//jackson library
import org.apache.logging.log4j.*;

import no.systema.main.mapper.jsonjackson.general.ObjectMapperAbstractGrandFather;
import no.systema.skat.z.maintenance.skatnctsexport.model.jsonjackson.dbtable.JsonMaintDkxhContainer;
//application library
import no.systema.skat.z.maintenance.skatnctsexport.model.jsonjackson.dbtable.JsonMaintDkxhRecord;


/**
 * @author oscardelatorre
 * @date Dec, 2021
 * 
 */
public class MaintDkxhMapper extends ObjectMapperAbstractGrandFather{
	private static final Logger logger = LogManager.getLogger(MaintDkxhMapper.class.getName());
	
	public JsonMaintDkxhContainer getContainer(String utfPayload) throws Exception{
		//At this point we now have an UTF-8 payload
		JsonMaintDkxhContainer container = super.getObjectMapper().readValue(utfPayload.getBytes(), JsonMaintDkxhContainer.class); 
		//logger.info("[JSON-String payload status=OK]  " + container.getUser());
		//DEBUG
		Collection<JsonMaintDkxhRecord> list = container.getList();
		for(JsonMaintDkxhRecord record : list){
			//logger.info(record.getKlikod());
		}
		return container;
	}
}
