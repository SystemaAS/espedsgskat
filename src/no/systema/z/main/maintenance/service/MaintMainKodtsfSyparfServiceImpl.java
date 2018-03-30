/**
 * 
 */
package no.systema.z.main.maintenance.service;

import no.systema.z.main.maintenance.model.JsonMaintMainKodtsfSyparfContainer;
import no.systema.z.main.maintenance.mapper.MaintMainKodtsfSyparfMapper;

/**
 * 
 * @author oscardelatorre
 * @date Mar 30, 2018
 * 
 * 
 */
public class MaintMainKodtsfSyparfServiceImpl implements MaintMainKodtsfSyparfService {
	/**
	 * 
	 */
	public JsonMaintMainKodtsfSyparfContainer getList(String utfPayload) {
		JsonMaintMainKodtsfSyparfContainer container = null;
		try{
			MaintMainKodtsfSyparfMapper mapper = new MaintMainKodtsfSyparfMapper();
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
	public JsonMaintMainKodtsfSyparfContainer doUpdate(String utfPayload) {
		JsonMaintMainKodtsfSyparfContainer container = null;
		try{
			MaintMainKodtsfSyparfMapper mapper = new MaintMainKodtsfSyparfMapper();
			container = mapper.getContainer(utfPayload);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return container;
		
	}

}