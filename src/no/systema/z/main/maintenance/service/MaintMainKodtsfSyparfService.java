/**
 * 
 */
package no.systema.z.main.maintenance.service;

import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainKodtsfSyparfContainer;

/**
 * 
 * @author oscardelatorre
 * @date Mar 30, 2018
 * 
 *
 */
public interface MaintMainKodtsfSyparfService {
	public JsonMaintMainKodtsfSyparfContainer getList(String utfPayload);
	public JsonMaintMainKodtsfSyparfContainer doUpdate(String utfPayload);
	
}
