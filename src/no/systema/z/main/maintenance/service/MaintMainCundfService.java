/**
 * 
 */
package no.systema.z.main.maintenance.service;

import no.systema.z.main.maintenance.model.JsonMaintMainCundfContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 30, 2018
 * 
 *
 */
public interface MaintMainCundfService {
	public JsonMaintMainCundfContainer getList(String utfPayload);
	public JsonMaintMainCundfContainer doUpdate(String utfPayload);
	
}
