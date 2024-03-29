/**
 * 
 */
package no.systema.skat.nctsimport.model.jsonjackson.topic;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author oscardelatorre
 * @date Apr 23, 2014
 * AS400 File: SVNH ; Format: SVNHR
 *
 */
public class JsonSkatNctsImportSpecificTopicRecord extends JsonAbstractGrandFatherRecord {
	//This record is used in special occasions when the session object is impossible to use
	//Typically in a Validator when we want to check further into RPG and we need the user name in a parameter (user=Oscar)
	private String applicationUser = null;
	public void setApplicationUser(String value) {  this.applicationUser = value; }
	public String getApplicationUser() {return this.applicationUser;}
	
	//test indicator
	private String dknh_0035 = null;
	public void setDknh_0035(String value) {  this.dknh_0035 = value; }
	public String getDknh_0035() {return this.dknh_0035;}
	
	//test avd flag
	private String testAvdFlag = null;
	public void setTestAvdFlag(String value) {  this.testAvdFlag = value; }
	public String getTestAvdFlag() {return this.testAvdFlag;}
	
	//thavd Avdeling 
	private String tiavd = null;
	public void setTiavd(String value) {  this.tiavd = value; }
	public String getTiavd() {return this.tiavd;}
	//thtdn Oppdragsnr
	private String titdn = null;
	public void setTitdn(String value) {  this.titdn = value; }
	public String getTitdn() {return this.titdn;}
	//status
	private String tist = null;
	public void setTist(String value) {  this.tist = value; }
	public String getTist() {return this.tist;}
	//signatur
	private String tisg = null;
	public void setTisg(String value) {  this.tisg = value; }
	public String getTisg() {return this.tisg;}
	//Date YMD
	private String tidt = null;
	public void setTidt(String value) {  this.tidt = value; }
	public String getTidt() {return this.tidt;}
	
	//Frigivningsdatum
	private String tidtf = null;
	public void setTidtf(String value) {  this.tidtf = value; }
	public String getTidtf() {return this.tidtf;}
	
	//MRN-nr.
	private String titrnr = null;
	public void setTitrnr(String value) {  this.titrnr = value; }
	public String getTitrnr() { return this.titrnr;}
	
	
	//Förenklad procedur
	private String tienkl = null;
	public void setTienkl(String value) {  this.tienkl = value; }
	public String getTienkl() {return this.tienkl;}
	
	private String tign = null;
	public void setTign(String value) {  this.tign = value; }
	public String getTign() {return this.tign;}
	
	private String tignsk = null;
	public void setTignsk(String value) {  this.tignsk = value; }
	public String getTignsk() {return this.tignsk;}
	
	private String tialsk = null;
	public void setTialsk(String value) {  this.tialsk = value; }
	public String getTialsk() {return this.tialsk;}
	
	private String tials = null;
	public void setTials(String value) {  this.tials = value; }
	public String getTials() {return this.tials;}
	
	private String tialss = null;
	public void setTialss(String value) {  this.tialss = value; }
	public String getTialss() {return this.tialss;}
	
	private String tiglsk = null;
	public void setTiglsk(String value) {  this.tiglsk = value; }
	public String getTiglsk() {return this.tiglsk;}
	
	private String tiacts = null;
	public void setTiacts(String value) {  this.tiacts = value; }
	public String getTiacts() {return this.tiacts;}
	
	private String tiskb = null;
	public void setTiskb(String value) {  this.tiskb = value; }
	public String getTiskb() {return this.tiskb;}
	
	private String titsb = null;
	public void setTitsb(String value) {  this.titsb = value; }
	public String getTitsb() {return this.titsb;}
	
	private String titarf = null;
	public void setTitarf(String value) {  this.titarf = value; }
	public String getTitarf() {return this.titarf;}
	
	private String tiws = null;
	public void setTiws(String value) {  this.tiws = value; }
	public String getTiws() {return this.tiws;}
	
	private String tikn = null;
	public void setTikn(String value) {  this.tikn = value; }
	public String getTikn() {return this.tikn;}
	
	private String tina = null;
	public void setTina(String value) {  this.tina = value; }
	public String getTina() {return this.tina;}
	
	private String tiad1 = null;
	public void setTiad1(String value) {  this.tiad1 = value; }
	public String getTiad1() {return this.tiad1;}
	
	private String tipn = null;
	public void setTipn(String value) {  this.tipn = value; }
	public String getTipn() {return this.tipn;}
	
	private String tips = null;
	public void setTips(String value) {  this.tips = value; }
	public String getTips() {return this.tips;}
	
	private String tilk = null;
	public void setTilk(String value) {  this.tilk = value; }
	public String getTilk() {return this.tilk;}
	
	private String tisk = null;
	public void setTisk(String value) {  this.tisk = value; }
	public String getTisk() {return this.tisk;}
	
	private String titin = null;
	public void setTitin(String value) {  this.titin = value; }
	public String getTitin() { return this.titin;}
	
	private String timf = null;
	public void setTimf(String value) {  this.timf = value; }
	public String getTimf() { return this.timf;}
	
	private String timp = null;
	public void setTimp(String value) {  this.timp = value; }
	public String getTimp() { return this.timp;}
	
	private String tikdv = null;
	public void setTikdv(String value) {  this.tikdv = value; }
	public String getTikdv() { return this.tikdv;}
	
	private String ti0035 = null;
	public void setTi0035(String value) {  this.ti0035 = value; }
	public String getTi0035() { return this.ti0035;}
	
	
	private String tialk = null;
	public void setTialk(String value) {  this.tialk = value; }
	public String getTialk() { return this.tialk;}
	
	private String tivers = null;
	public void setTivers(String value) {  this.tivers = value; }
	public String getTivers() { return this.tivers;}
	
	
	private String tiga1 = null;
	public void setTiga1(String value) {  this.tiga1 = value; }
	public String getTiga1() { return this.tiga1;}
	
	private String tiga2 = null;
	public void setTiga2(String value) {  this.tiga2 = value; }
	public String getTiga2() { return this.tiga2;}
	
	private String tiga3 = null;
	public void setTiga3(String value) {  this.tiga3 = value; }
	public String getTiga3() { return this.tiga3;}
	
	private String tiga4 = null;
	public void setTiga4(String value) {  this.tiga4 = value; }
	public String getTiga4() { return this.tiga4;}
	
	

	/**
	 * Used for java reflection in other classes
	 * @return
	 * @throws Exception
	 */
	
	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}

}
