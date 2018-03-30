/**
 * 
 */
package no.systema.skat.z.maintenance.main.service;

import no.systema.skat.z.maintenance.main.mapper.jsonjackson.dbtable.MaintDktkdMapper;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktkdContainer;

/**
 * 
 * @author oscardelatorre
 * @date Jun 13, 2016
 * 
 * 
 */
public class MaintDktkdServiceImpl implements MaintDktkdService {
	/**
	 * 
	 */
	public JsonMaintDktkdContainer getList(String utfPayload) {
		JsonMaintDktkdContainer container = null;
		try{
			MaintDktkdMapper mapper = new MaintDktkdMapper();
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
	public JsonMaintDktkdContainer doUpdate(String utfPayload) {
		JsonMaintDktkdContainer container = null;
		try{
			MaintDktkdMapper mapper = new MaintDktkdMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}
