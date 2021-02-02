package no.systema.skat.skatexport.model.jsonjackson.topic.items;
import java.lang.reflect.Field;

import no.systema.main.model.jsonjackson.general.JsonAbstractGrandFatherRecord;

import java.util.Arrays;
import java.util.List;

import lombok.Data;
/**
 * 
 * @author oscardelatorre
 * @date Feb, 2021
 * 
 * This dao has been created in order to be able to delete all item lines at once (SQL)
 * 
 */
@Data
public class JsonSkatExportDkevRecord extends JsonAbstractGrandFatherRecord {
		                       								
	private String dkev_syav = null; //   SONET        4  0      SYSPED Avdeling   
	private String dkev_syop = null; //   SONET        7  0      SYSPED Oppdragsnr  
	private String dkev_syli = null; //   SONET        2  0      SYSPED Linjenr   
	
	/**
	 * 
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
