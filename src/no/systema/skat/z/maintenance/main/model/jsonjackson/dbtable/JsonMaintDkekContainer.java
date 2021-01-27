/**
 * 
 */
package no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable;

import java.util.Collection;

import lombok.Data;

/**
 * @author oscardelatorre
 * @date Jan, 2021
 *
 */
@Data
public class JsonMaintDkekContainer {
	private String user = null;
	private String errMsg = null;
	private Collection<JsonMaintDkekRecord> list;
	
}
