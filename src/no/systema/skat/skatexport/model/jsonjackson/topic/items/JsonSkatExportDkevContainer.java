/**
 * 
 */
package no.systema.skat.skatexport.model.jsonjackson.topic.items;

import java.util.Collection;

import lombok.Data;

/**
 * @author oscardelatorre
 * @date Feb, 2021
 *
 */
@Data
public class JsonSkatExportDkevContainer {
	private String user = null;
	private String errMsg = null;
	private Collection<JsonSkatExportDkevRecord> list;
	
}
