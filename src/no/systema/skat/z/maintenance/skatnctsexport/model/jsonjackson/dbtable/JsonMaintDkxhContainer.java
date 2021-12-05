/**
 * 
 */
package no.systema.skat.z.maintenance.skatnctsexport.model.jsonjackson.dbtable;

import java.util.Collection;

import lombok.Data;

/**
 * @author oscardelatorre
 * @date Dec, 2021
 *
 */
@Data
public class JsonMaintDkxhContainer {
	private String user = null;
	private String errMsg = null;
	
	private Collection<JsonMaintDkxhRecord> list;
	public void setList(Collection<JsonMaintDkxhRecord> value){ this.list = value; }
	public Collection<JsonMaintDkxhRecord> getList(){ return list; }
	
}
