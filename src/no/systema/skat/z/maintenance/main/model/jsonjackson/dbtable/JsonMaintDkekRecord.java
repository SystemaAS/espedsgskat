package no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import lombok.Data;
import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;
/**
 * All variables must be initialized to empty strings and NOT to NULL values
 * This is because the db-inserts that will be done in all fields of the db-table
 * 
 * @author oscardelatorre
 * @date Jan, 2021
 * 
 * 
 */
@Data
public class JsonMaintDkekRecord extends JsonAbstractGrandFatherRecord{
	private String knavn = null;
		                       								//Start/end_pos Byte Dig Dec Type
	private String dkek_knr = null; //   Kundens kundenr          1     8      8   8   0 S  
	private String dkek_vnr = null; //   Kundens varenr           9      36    28           A (Artikkelnr)  
	private String dkek_311 = null; //   Kollimærke               37   52    16           A  
	private String dkek_314 = null; //   Kolliart                 53   54      2           A  
	private String dkek_315 = null; //   Varebeskrivelse          55   99    45           A  
	private String dkek_331 = null; //   Varekode                 100  107     8           A  
	private String dkek_332 = null; //   ym tillægskode           108  111     4           a  
	private String dkek_sikk = null;//   sikkerhedsstillesle      112  113     2           a  
	private String dkek_34a = null; //   oprindelsesland          114  115     2           a  
	private String dkek_37 = null;  //   procedure                116  122     7           a  
	private String dkek_401a = null;//   for.dok.kat              123  123     1           a  
	private String dkek_402a = null;//   for.dok.type             124  126     3           a  
	private String dkek_403a = null;//   for.dok.nr               127  146    20          a  
	private String dkek_411 = null; //   suppl.enhed              147  149     3           a  
	private String dkek_441 = null; //   bevillingsnr             150  162   13          a
	private String dkek_4421 = null;//   certifikatkode           163  166     4           a
	private String dkek_442a = null;//   certifikatnr             167  201    35          a
	private String dkek_443 = null; //   vab bestemmelse       	 202  202     1          a
	private String dkek_444 = null; //   fn farlig gods undg 	 203  206     4          a
	private String dkek_446a = null;//   suppl.vareopl.txt        207  276    70         a
	private String dkek_447  = null;//   delsendinger             277  281     5          a
	private String dkek_449a = null;//   oplysningstype kode      282  301   20         a
	private String dkek_49 = null;  //   id af oplag              302  318   17          a
	private String dkek_bem1 = null;//   bemærkninger             319  388   70         a

	public List<Field> getFields() throws Exception{
		Class cl = Class.forName(this.getClass().getCanonicalName());
		Field[] fields = cl.getDeclaredFields();
		List<Field> list = Arrays.asList(fields);
		
		return list;
	}
		
}
