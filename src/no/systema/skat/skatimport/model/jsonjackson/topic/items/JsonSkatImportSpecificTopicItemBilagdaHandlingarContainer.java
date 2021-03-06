/**
 * 
 */
package no.systema.skat.skatimport.model.jsonjackson.topic.items;

import java.util.Collection;



/**
 * @author oscardelatorre
 * @date Sep 10, 2103
 * 
 */
public class JsonSkatImportSpecificTopicItemBilagdaHandlingarContainer {
	
	private String user = null;
	public void setUser(String value) {  this.user = value; }
	public String getUser() { return this.user;}
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	
	private String errMsg = null;
	public void setErrMsg(String value) {  this.errMsg = value; }
	public String getErrMsg() { return this.errMsg;}
	
	
	private Collection<JsonSkatImportSpecificTopicItemBilagdaHandlingarRecord> bilhand;
	public void setBilhand(Collection<JsonSkatImportSpecificTopicItemBilagdaHandlingarRecord> value){ this.bilhand = value; }
	public Collection<JsonSkatImportSpecificTopicItemBilagdaHandlingarRecord> getBilhand(){ return bilhand; }
	
	
	
}
