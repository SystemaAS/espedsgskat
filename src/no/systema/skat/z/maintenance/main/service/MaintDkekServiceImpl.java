/**
 * 
 */
package no.systema.skat.z.maintenance.main.service;

import no.systema.skat.z.maintenance.main.mapper.jsonjackson.dbtable.MaintDkekMapper;
import no.systema.skat.z.maintenance.main.mapper.jsonjackson.dbtable.MaintDktkdMapper;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDkekContainer;

/**
 * 
 * @author oscardelatorre
 * @date Jun 13, 2016
 * 
 * 
 */
public class MaintDkekServiceImpl implements MaintDkekService {
	/**
	 * 
	 */
	public JsonMaintDkekContainer getList(String utfPayload) {
		JsonMaintDkekContainer container = null;
		try{
			MaintDkekMapper mapper = new MaintDkekMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	/**
	 * 
	 * @param utfPayload
	 * @return
	 */
	public JsonMaintDkekContainer doUpdate(String utfPayload) {
		JsonMaintDkekContainer container = null;
		try{
			MaintDkekMapper mapper = new MaintDkekMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
