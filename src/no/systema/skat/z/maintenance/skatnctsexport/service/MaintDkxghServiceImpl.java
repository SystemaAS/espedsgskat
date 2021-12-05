/**
 * 
 */
package no.systema.skat.z.maintenance.skatnctsexport.service;

import no.systema.skat.z.maintenance.skatnctsexport.mapper.jsonjackson.dbtable.MaintDkxghMapper;
import no.systema.skat.z.maintenance.skatnctsexport.mapper.jsonjackson.dbtable.MaintDkxhMapper;
import no.systema.skat.z.maintenance.skatnctsexport.model.jsonjackson.dbtable.JsonMaintDkxghContainer;
import no.systema.skat.z.maintenance.skatnctsexport.model.jsonjackson.dbtable.JsonMaintDkxhContainer;

/**
 * 
 * @author oscardelatorre
 * @date Jun 13, 2016
 * 
 * 
 */
public class MaintDkxghServiceImpl implements MaintDkxghService {
	/**
	 * 
	 */
	public JsonMaintDkxghContainer getList(String utfPayload) {
		JsonMaintDkxghContainer container = null;
		try{
			MaintDkxghMapper mapper = new MaintDkxghMapper();
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
	public JsonMaintDkxghContainer doUpdate(String utfPayload) {
		JsonMaintDkxghContainer container = null;
		try{
			MaintDkxghMapper mapper = new MaintDkxghMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}
	public JsonMaintDkxhContainer getListReservedGuaranty(String utfPayload) {
		JsonMaintDkxhContainer container = null;
		try{
			MaintDkxhMapper mapper = new MaintDkxhMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	
	public JsonMaintDkxhContainer doReleaseGuarantee(String utfPayload) {
		JsonMaintDkxhContainer container = null;
		try{
			MaintDkxhMapper mapper = new MaintDkxhMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
	}
	

}
