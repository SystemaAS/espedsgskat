package no.systema.skat.z.maintenance.skatnctsexport.validator;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.*;
import org.springframework.validation.Validator;

import javawebparts.core.org.apache.commons.lang.StringUtils;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.skat.z.maintenance.skatnctsexport.model.jsonjackson.dbtable.JsonMaintDkxghRecord;

/**
 * 
 * @author oscardelatorre
 * @date Sep 2, 2016
 * 
 *
 */
public class MaintSkatExportDkx030rValidator implements Validator {
	private static final Logger logger = LoggerFactory.getLogger(MaintSkatExportDkx030rValidator.class.getName());
	private NumberFormatterLocaleAware numberFormatter = new NumberFormatterLocaleAware();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonMaintDkxghRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonMaintDkxghRecord record = (JsonMaintDkxghRecord)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tggnr", "", "Garantinr er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tgtina", "", "Foretagsnr er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tgnaa", "", "Navn er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tgpna", "", "Postnr. er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tgpsa", "", "Postadr. er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tglka", "", "Landkode er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tgtsd", "", "Garantitoldkont. er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tggty", "", "Garantityp er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tgakny", "", "Ny tillg.kode er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tgakgm", "", "Gml. tillg.kode er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tggbl", "", "Garantibeløb er obligatorisk"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tggvk", "", "Valuta er obligatorisk"); 
		
		
		//Logical (RULES) controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				if(StringUtils.isNotEmpty(record.getTgdt())){
					if(!this.isValidDate(record.getTgdt())){
						errors.rejectValue("tgdt", "", "Send dato er ugyldig ");
					}
				}
				if(StringUtils.isNotEmpty(record.getTgdtr())){
					if(!this.isValidDate(record.getTgdtr())){
						errors.rejectValue("tgdtr", "", "Reg.dato er ugyldig");
					}
				}
			}
		}
	}
	/**
	 * 
	 * @param obj
	 * @param errors
	 */
	
	public void validateDelete(Object obj, Errors errors) { 
		
		JsonMaintDkxghRecord record = (JsonMaintDkxghRecord)obj;
		//logger.info(record.getTariff());
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tggnr", "", "Garantinr er obligatorisk"); 
		
	}
	
	/**
	 * 
	 * @param rawValue
	 * @return
	 */
	private boolean isValidDate(String rawValue){
		boolean retval = false;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		formatter.setLenient(false); //in order to put logical control for month
		try{
			Date tmp = formatter.parse(rawValue);
			retval = true;
		}catch(Exception e){
			//nothing
		}
		return retval;
	}
	
	
}
