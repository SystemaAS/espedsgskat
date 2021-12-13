/**
 * 
 */
package no.systema.skat.nctsexport.filter;

import java.lang.reflect.Field;
import java.util.*;

import no.systema.main.util.io.TextFileReaderService;

import org.apache.logging.log4j.*;

/**
 * This search class is used at the GUI search behavior
 * It is MANDATORY to have the same attribute name convention as the JSON-object fetched from the JSON-payload at the back-end.
 * The reason for this is the java-reflection mechanism used when searching (since no SQL or other mechanism is used)
 * By using java reflection to match the object fields, these 2 (the JSON object and its SearchFilter object) must have the same attribute name 
 * 
 * @author oscardelatorre
 * @date 	Apr 15, 2014
 */
public class SearchFilterSkatNctsExportTopicList {
	private static final Logger logger = LogManager.getLogger(SearchFilterSkatNctsExportTopicList.class.getName());
	
	private String avd = null;
	public void setAvd(String value) {  this.avd = value; }
	public String getAvd() { return this.avd;}
	
	private String opd = null;
	public void setOpd(String value) {  this.opd = value; }
	public String getOpd() { return this.opd;}
	
	private String sign = null;
	public void setSign(String value) {  this.sign = value; }
	public String getSign() { return this.sign;}

	private String datum = null;
	public void setDatum(String value) {  this.datum = value; }
	public String getDatum() { return this.datum;}

	private String datumt = null;
	public void setDatumt(String value) {  this.datumt = value; }
	public String getDatumt() { return this.datumt;}
	
	private String lrnNr = null;
	public void setLrnNr(String value) {  this.lrnNr = value; }
	public String getLrnNr() { return this.lrnNr;}
	
	private String mrnNr = null;
	public void setMrnNr(String value) {  this.mrnNr = value; }
	public String getMrnNr() { return this.mrnNr;}
	
	private String status = null;
	public void setStatus(String value) {  this.status = value; }
	public String getStatus() { return this.status;}

	private String kolli = null;
	public void setKolli(String value) {  this.kolli = value; }
	public String getKolli() { return this.kolli;}
	
	private String bruttoVikt = null;
	public void setBruttoVikt(String value) {  this.bruttoVikt = value; }
	public String getBruttoVikt() { return this.bruttoVikt;}

	private String forenklad = null;
	public void setForenklad(String value) {  this.forenklad = value; }
	public String getForenklad() { return this.forenklad;}

	private String elList = null;
	public void setElList(String value) {  this.elList = value; }
	public String getElList() { return this.elList;}

	private String eksternRef = null;
	public void setEksternRef(String value) {  this.eksternRef = value; }
	public String getEksternRef() { return this.eksternRef;}

	private String motNavn = null;
	public void setMotNavn(String value) {  this.motNavn = value; }
	public String getMotNavn() { return this.motNavn;}
	
	private String docRef = null;
	public void setDocRef(String value) {  this.docRef = value; }
	public String getDocRef() { return this.docRef;}
	
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
