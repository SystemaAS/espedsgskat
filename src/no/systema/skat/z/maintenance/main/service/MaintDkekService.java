/**
 * 
 */
package no.systema.skat.z.maintenance.main.service;

import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDkekContainer;

/**
 * 
 * @author oscardelatorre
 * @date Jan, 2021
 * 
 *
 */
public interface MaintDkekService {
	public JsonMaintDkekContainer getList(String utfPayload);
	public JsonMaintDkekContainer doUpdate(String utfPayload);
	
}
