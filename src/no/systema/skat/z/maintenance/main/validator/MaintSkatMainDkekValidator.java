package no.systema.skat.z.maintenance.main.validator;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDkekContainer;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDkekRecord;


/**
 * 
 * @author oscardelatorre
 * @date Jan, 2021
 * 
 *
 */
public class MaintSkatMainDkekValidator implements Validator {
	private static final Logger logger = Logger.getLogger(MaintSkatMainDkekValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintDkekRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintDkekRecord record = (JsonMaintDkekRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkek_knr", "", "Kundenr er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkek_vnr", "", "Varenr er obligatorisk");  
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//N/A
			}
		}
	}
	/**
	 * 
	 * @param obj
	 * @param errors
	 */
	
	public void validateDelete(Object obj, Errors errors) { 
		
		JsonMaintDkekRecord record = (JsonMaintDkekRecord)obj;
		//logger.info(record.getDkek_knr());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkek_knr", "", "Kundenr er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dkek_vnr", "", "Varenr er obligatorisk");  
		
	}

}
