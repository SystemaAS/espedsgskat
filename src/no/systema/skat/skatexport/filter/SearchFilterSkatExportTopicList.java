/**
 * 
 */
package no.systema.skat.skatexport.filter;

import java.lang.reflect.Field;
import java.util.*;

import org.slf4j.*;

import no.systema.main.util.DateTimeManager;


/**
 * This search class is used at the GUI search behavior
 * It is MANDATORY to have the same attribute name convention as the JSON-object fetched from the JSON-payload at the back-end.
 * The reason for this is the java-reflection mechanism used when searching (since no SQL or other mechanism is used)
 * By using java reflection to match the object fields, these 2 (the JSON object and its SearchFilter object) must have the same attribute name 
 * 
 * @author oscardelatorre
 * @date 	Feb 28, 2014
 * 
 */
public class SearchFilterSkatExportTopicList {
	private static final Logger logger = LoggerFactory.getLogger(SearchFilterSkatExportTopicList.class.getName());
	private static final DateTimeManager dateMgr = new DateTimeManager();
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String refnr = null;
	public void setRefnr(String value) {  this.refnr = value; }
	public String getRefnr() { return this.refnr;}
	
	private String xrefnr = null;
	public void setXrefnr(String value) {  this.xrefnr = value; }
	public String getXrefnr() { return this.xrefnr;}
	
	private String mrn = null;
	public void setMrn(String value) {  this.mrn = value; }
	public String getMrn() { return this.mrn;}
	
	private String sign = null;
	public void setSign(String value) { this.sign = value;}
	public String getSign() { return this.sign;}
	
	private String datum = this.dateMgr.getNewDateFromNow(-1);
	public void setDatum(String value) {  this.datum = value; }
	public String getDatum() { return this.datum;}
	
	private String datumt = null;
	public void setDatumt(String value) {  this.datumt = value; }
	public String getDatumt() { return this.datumt;}
	
	private String status = null;
	public void setStatus(String value) {  this.status = value; }
	public String getStatus() { return this.status;}
	
	private String avsNavn = null;
	public void setAvsNavn(String value) {  this.avsNavn = value; }
	public String getAvsNavn() { return this.avsNavn;}
	
	private String motNavn = null;
	public void setMotNavn(String value) {  this.motNavn = value; }
	public String getMotNavn() { return this.motNavn;}
	
	private String aart = null;
	public void setAart(String value) {  this.aart = value; }
	public String getAart() { return this.aart;}
	
	private String godsnr = null;
	public void setGodsnr(String value) {  this.godsnr = value; }
	public String getGodsnr() { return this.godsnr;}
	
	private String internFakturanr = null;
	public void setInternFakturanr(String value) {  this.internFakturanr = value; }
	public String getInternFakturanr() { return this.internFakturanr;}
	
	private String trid = null;
	public void setTrid(String value) {  this.trid = value; }
	public String getTrid() { return this.trid;}
	
	
	/**
	 * Gets the populated values by reflection
	 * @param searchFilter
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> getPopulatedFields() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		for(Field field : list){
			field.setAccessible(true);
			logger.info("FIELD NAME: " + field.getName() + "VALUE:" + (String)field.get(this));
			String value = (String)field.get(this);
			if(value!=null && !"".equals(value)){
				logger.info(field.getName() + " Value:" + value);
				map.put(field.getName(), value);
			}
		}
		
		return map;
	}
}
