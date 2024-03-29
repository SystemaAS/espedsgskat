/**
 * 
 */
package no.systema.skat.z.maintenance.skatnctsexport.service;

import no.systema.skat.z.maintenance.skatnctsexport.model.jsonjackson.dbtable.JsonMaintDkxghContainer;
import no.systema.skat.z.maintenance.skatnctsexport.model.jsonjackson.dbtable.JsonMaintDkxhContainer;

/**
 * 
 * @author oscardelatorre
 * @date Sep 2, 2016
 * 
 *
 */
public interface MaintDkxghService {
	public JsonMaintDkxghContainer getList(String utfPayload);
	public JsonMaintDkxghContainer doUpdate(String utfPayload);
	//
	public JsonMaintDkxhContainer getListReservedGuaranty(String utfPayload);
	public JsonMaintDkxhContainer doReleaseGuarantee(String utfPayload);
	
}
