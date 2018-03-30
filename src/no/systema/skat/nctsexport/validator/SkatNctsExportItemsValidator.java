package no.systema.skat.nctsexport.validator;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.skat.nctsexport.model.jsonjackson.topic.items.JsonSkatNctsExportSpecificTopicItemRecord;
import no.systema.skat.nctsexport.model.jsonjackson.topic.items.validation.JsonSkatNctsExportSpecificTopicItemSensitiveGoodsValidatorContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatNctsCodeContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatNctsCodeRecord;
import no.systema.skat.nctsexport.service.SkatNctsExportSpecificTopicItemService;
import no.systema.skat.nctsexport.service.SkatNctsExportSpecificTopicItemServiceImpl;
import no.systema.skat.nctsexport.url.store.SkatNctsExportUrlDataStore;
import no.systema.skat.service.html.dropdown.SkatDropDownListPopulationService;

import no.systema.skat.url.store.SkatUrlDataStore;
/**
 * 
 * @author oscardelatorre
 * @date Apr 15, 2014
 * 
 *
 */
public class SkatNctsExportItemsValidator implements Validator {
	private static final Logger logger = Logger.getLogger(SkatNctsExportItemsValidator.class.getName());
	//Intantiate services here since we are not capable to configure injection with Autowired. Check that further...
	private UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
	private SkatNctsExportSpecificTopicItemService nctsExportSpecificTopicItemService = new SkatNctsExportSpecificTopicItemServiceImpl();
	private SkatDropDownListPopulationService skatDropDownListPopulationService = new SkatDropDownListPopulationService();
	/**
	 * 
	 */
	public boolean supports(Class clazz) {
		return JsonSkatNctsExportSpecificTopicItemRecord.class.isAssignableFrom(clazz); 
	}
	
	/**
	 * @param obj
	 * @param errors
	 * 
	 */
	public void validate(Object obj, Errors errors) { 
		JsonSkatNctsExportSpecificTopicItemRecord record = (JsonSkatNctsExportSpecificTopicItemRecord)obj;

		//Check for Mandatory fields first
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tvvt", "systema.skat.ncts.export.header.error.null.item.itemdesc.tvvt"); 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tveh", "systema.skat.ncts.export.header.error.null.item.packageType.tveh");
				
		
		//Logical controls if we passed the NOT NULL errors
		if(!errors.hasFieldErrors()){
			if(record!=null){
				//Check if type header angiv.type is = T-
				if(record.isThdkTminusType()){
					if(record.getTvdk()!=null && !"".equals(record.getTvdk())){
						//OK = valid
					}else{
						errors.rejectValue("tvvnt", "systema.skat.ncts.export.header.error.rule.item.tvdk.mustExistWhenTminus");
					}
				}
				//Check varukod
				if(!record.isValidTolltariff()){
					//only check for mandatory tvvnt IF and only IF the dokument code != 380. Type 380 does not requires a varekode
					if( !"380".equals(record.getTvdty()) ){
						errors.rejectValue("tvvnt", "systema.skat.ncts.export.header.error.null.item.varenr.tvvnt");
					}
				}
				
				//-----------------------------------------------------
				//Oemballerade varor must always have "Styck" (delar)
				//tveh
				//-----------------------------------------------------
				if("NE".equals(record.getTveh()) || "NF".equals(record.getTveh())){
					if(record.getTvnteh()==null && "".equals(record.getTvnteh())){
						errors.rejectValue("tvnteh", "systema.skat.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
					}else{
						try{
							Double antal = Double.parseDouble(record.getTvnteh().replace(",", "."));
							if(antal==0.00D){
								errors.rejectValue("tvnteh", "systema.skat.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
							}
						}catch(Exception e){
							errors.rejectValue("tvnteh", "systema.skat.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
						}
					}
					
				}else if ("VQ".equals(record.getTveh()) || "VG".equals(record.getTveh()) ||
					"VL".equals(record.getTveh()) || "VY".equals(record.getTveh()) ||
					"VR".equals(record.getTveh()) || "VO".equals(record.getTveh())){
					if( record.getTvnt()!=null && !"".equals(record.getTvnt()) && record.getTvnteh()!=null && !"".equals(record.getTvnteh()) ){
						errors.rejectValue("tvnt", "systema.skat.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}else if( record.getTvnt()!=null && !"".equals(record.getTvnt()) ){
						errors.rejectValue("tvnt", "systema.skat.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}else if( record.getTvnteh()!=null && !"".equals(record.getTvnteh()) ){
						errors.rejectValue("tvnteh", "systema.skat.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}
					
				//These codes MUST have only the "Kolliantal" (tvnt) alternativ. Nothing else.	
				}else{
					if( record.getTvnt()!=null && !"".equals(record.getTvnt()) && record.getTvnteh()!=null && !"".equals(record.getTvnteh()) ){
						errors.rejectValue("tvnt", "systema.skat.ncts.export.header.error.rule.item.tvntOrtvnteh.mustExist");
					}else if ( record.getTvnt()==null || "".equals(record.getTvnt())){
						errors.rejectValue("tvnt", "systema.skat.ncts.export.header.error.rule.item.tvnt.mustExist");
					}
					//Merking
					if( record.getTvmn()==null || "".equals(record.getTvmn()) ){
						errors.rejectValue("tvmn", "systema.skat.ncts.export.header.error.null.item.merking.tvmn");
					}
				}
				
				
				
				/*
				//-----------------------------------------------------
				//Oemballerade varor must always have "Styck" (delar)
				//tveh2
				//-----------------------------------------------------
				if("NE".equals(record.getTveh2()) || "NF".equals(record.getTveh2())){
					if(record.getTvnteh2()==null && "".equals(record.getTvnteh2())){
						errors.rejectValue("tvnteh", "systema.skat.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
					}else{
						try{
							Double antal = Double.parseDouble(record.getTvnteh2().replace(",", "."));
							if(antal==0.00D){
								errors.rejectValue("tvnteh", "systema.skat.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
							}
						}catch(Exception e){
							errors.rejectValue("tvnteh", "systema.skat.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
						}
					}
					
				}else if ("VQ".equals(record.getTveh2()) || "VG".equals(record.getTveh2()) ||
					"VL".equals(record.getTveh2()) || "VY".equals(record.getTveh2()) ||
					"VR".equals(record.getTveh2()) || "VO".equals(record.getTveh2())){
					
					if( record.getTvnt2()!=null && !"".equals(record.getTvnt2()) && record.getTvnteh2()!=null && !"".equals(record.getTvnteh2()) ){
						errors.rejectValue("tvnt", "systema.skat.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}else if( record.getTvnt2()!=null && !"".equals(record.getTvnt2()) ){
						errors.rejectValue("tvnt", "systema.skat.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}else if( record.getTvnteh2()!=null && !"".equals(record.getTvnteh2()) ){
						errors.rejectValue("tvnteh", "systema.skat.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}
				//These codes MUST have only the "Kolliantal" (tvnt) alternativ. Nothing else.	
				}else{
					if(!"".equals(record.getTveh2())){
						if( record.getTvnt2()!=null && !"".equals(record.getTvnt2()) && record.getTvnteh2()!=null && !"".equals(record.getTvnteh2()) ){
							errors.rejectValue("tvnt", "systema.skat.ncts.export.header.error.rule.item.tvntOrtvnteh.mustExist");
						}else if ( record.getTvnt2()==null || "".equals(record.getTvnt2())){
							errors.rejectValue("tvnt", "systema.skat.ncts.export.header.error.rule.item.tvnt.mustExist");
						}
					}
				}
				
				//-----------------------------------------------------
				//Oemballerade varor must always have "Styck" (delar)
				//tveh3
				//-----------------------------------------------------
				if("NE".equals(record.getTveh3()) || "NF".equals(record.getTveh3())){
					if(record.getTvnteh3()==null && "".equals(record.getTvnteh3())){
						errors.rejectValue("tvnteh", "systema.skat.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
					}else{
						try{
							Double antal = Double.parseDouble(record.getTvnteh3().replace(",", "."));
							if(antal==0.00D){
								errors.rejectValue("tvnteh", "systema.skat.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
							}
						}catch(Exception e){
							errors.rejectValue("tvnteh", "systema.skat.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
						}
					}
					
				}else if ("VQ".equals(record.getTveh3()) || "VG".equals(record.getTveh3()) ||
					"VL".equals(record.getTveh3()) || "VY".equals(record.getTveh3()) ||
					"VR".equals(record.getTveh3()) || "VO".equals(record.getTveh3())){
					
					if( record.getTvnt3()!=null && !"".equals(record.getTvnt3()) && record.getTvnteh3()!=null && !"".equals(record.getTvnteh3()) ){
						errors.rejectValue("tvnt", "systema.skat.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}else if( record.getTvnt3()!=null && !"".equals(record.getTvnt3()) ){
						errors.rejectValue("tvnt", "systema.skat.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}else if( record.getTvnteh3()!=null && !"".equals(record.getTvnteh3()) ){
						errors.rejectValue("tvnteh", "systema.skat.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}
				//These codes MUST have only the "Kolliantal" (tvnt) alternativ. Nothing else.	
				}else{
					if(!"".equals(record.getTveh3())){
						if( record.getTvnt3()!=null && !"".equals(record.getTvnt3()) && record.getTvnteh3()!=null && !"".equals(record.getTvnteh3()) ){
							errors.rejectValue("tvnt", "systema.skat.ncts.export.header.error.rule.item.tvntOrtvnteh.mustExist");
						}else if ( record.getTvnt3()==null || "".equals(record.getTvnt3())){
							errors.rejectValue("tvnt", "systema.skat.ncts.export.header.error.rule.item.tvnt.mustExist");
						}
					}

				}
				
				//-----------------------------------------------------
				//Oemballerade varor must always have "Styck" (delar)
				//tveh4
				//-----------------------------------------------------
				if("NE".equals(record.getTveh4()) || "NF".equals(record.getTveh4())){
					if(record.getTvnteh4()==null && "".equals(record.getTvnteh4())){
						errors.rejectValue("tvnteh", "systema.skat.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
					}else{
						try{
							Double antal = Double.parseDouble(record.getTvnteh4().replace(",", "."));
							if(antal==0.00D){
								errors.rejectValue("tvnteh", "systema.skat.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
							}
						}catch(Exception e){
							errors.rejectValue("tvnteh", "systema.skat.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
						}
					}
					
				}else if ("VQ".equals(record.getTveh4()) || "VG".equals(record.getTveh4()) ||
					"VL".equals(record.getTveh4()) || "VY".equals(record.getTveh4()) ||
					"VR".equals(record.getTveh4()) || "VO".equals(record.getTveh4())){
					
					if( record.getTvnt4()!=null && !"".equals(record.getTvnt4()) && record.getTvnteh4()!=null && !"".equals(record.getTvnteh4()) ){
						errors.rejectValue("tvnt", "systema.skat.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}else if( record.getTvnt4()!=null && !"".equals(record.getTvnt4()) ){
						errors.rejectValue("tvnt", "systema.skat.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}else if( record.getTvnteh4()!=null && !"".equals(record.getTvnteh4()) ){
						errors.rejectValue("tvnteh", "systema.skat.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}
				//These codes MUST have only the "Kolliantal" (tvnt) alternativ. Nothing else.	
				}else{
					if(!"".equals(record.getTveh4())){
						if( record.getTvnt4()!=null && !"".equals(record.getTvnt4()) && record.getTvnteh4()!=null && !"".equals(record.getTvnteh4()) ){
							errors.rejectValue("tvnt", "systema.skat.ncts.export.header.error.rule.item.tvntOrtvnteh.mustExist");
						}else if ( record.getTvnt4()==null || "".equals(record.getTvnt4())){
							errors.rejectValue("tvnt", "systema.skat.ncts.export.header.error.rule.item.tvnt.mustExist");
						}
					}

				}
				
				//-----------------------------------------------------
				//Oemballerade varor must always have "Styck" (delar)
				//tveh5
				//-----------------------------------------------------
				if("NE".equals(record.getTveh5()) || "NF".equals(record.getTveh5())){
					if(record.getTvnteh5()==null && "".equals(record.getTvnteh5())){
						errors.rejectValue("tvnteh", "systema.skat.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
					}else{
						try{
							Double antal = Double.parseDouble(record.getTvnteh5().replace(",", "."));
							if(antal==0.00D){
								errors.rejectValue("tvnteh", "systema.skat.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
							}
						}catch(Exception e){
							errors.rejectValue("tvnteh", "systema.skat.ncts.export.header.error.rule.item.tvnteh.biggerThanZero");
						}
					}
					
				}else if ("VQ".equals(record.getTveh5()) || "VG".equals(record.getTveh5()) ||
					"VL".equals(record.getTveh5()) || "VY".equals(record.getTveh5()) ||
					"VR".equals(record.getTveh5()) || "VO".equals(record.getTveh5())){
					
					if( record.getTvnt5()!=null && !"".equals(record.getTvnt5()) && record.getTvnteh5()!=null && !"".equals(record.getTvnteh5()) ){
						errors.rejectValue("tvnt", "systema.skat.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}else if( record.getTvnt5()!=null && !"".equals(record.getTvnt5()) ){
						errors.rejectValue("tvnt", "systema.skat.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}else if( record.getTvnteh5()!=null && !"".equals(record.getTvnteh5()) ){
						errors.rejectValue("tvnteh", "systema.skat.ncts.export.header.error.rule.item.tvntAndtvnteh.mustBeNull");
					}
				//These codes MUST have only the "Kolliantal" (tvnt) alternativ. Nothing else.	
				}else{
					
					if(!"".equals(record.getTveh5())){
						if( record.getTvnt5()!=null && !"".equals(record.getTvnt5()) && record.getTvnteh5()!=null && !"".equals(record.getTvnteh5()) ){
							errors.rejectValue("tvnt", "systema.skat.ncts.export.header.error.rule.item.tvntOrtvnteh.mustExist");
						}else if ( record.getTvnt5()==null || "".equals(record.getTvnt5())){
							errors.rejectValue("tvnt", "systema.skat.ncts.export.header.error.rule.item.tvnt.mustExist");
						}
					}
					
				}
				*/
				
				//------------------------------------------------------------
				//Känsliga varor must always check for mandatory input or none
				//-------------------------------------------------------------
				
				if(record.getTvvnt()!=null && !"".equals(record.getTvvnt()) ){
					/* removed for DACHSER TEST
					if(this.isVarukodWithSensitiveGoodsFlag(record)){
						if(record.getTvfvnt()!=null && !"".equals(record.getTvfvnt())){
							//nothing since it has been filled in
						}else{
							errors.rejectValue("tvfvnt", "systema.skat.ncts.export.header.error.rule.item.tvfvnt.mustExist");
						}
					}else{
						//back to default values
						record.setTvfvnt(null);
					}
					*/
				}
				
				//-----------------------------------------------
				//Gross weight only mandatory on first item line
				//-----------------------------------------------
				/*
				if("0".equals(record.getNumberOfItemLinesInTopicStr()) ){
					if(record.getTvvktb()==null || "".equals(record.getTvvktb()) ){
						errors.rejectValue("tvfvnt", "systema.skat.ncts.export.header.error.null.item.grossweight.tvvktb");
					}
				}else if("-99".equals(record.getNumberOfItemLinesInTopicStr())){ //this is an update of the only item line in a topic
					if(record.getTvvktb()==null || "".equals(record.getTvvktb()) ){
						errors.rejectValue("tvfvnt", "systema.skat.ncts.export.header.error.null.item.grossweight.tvvktb");
					}
				}
				*/
				
				//---------------------
				//Gross and Net weight
				//---------------------
				if(record.getTvvktb()!=null && !"".equals(record.getTvvktb())){
					if(record.getTvvktn()!=null && !"".equals(record.getTvvktn())){
						try{
							String grossFormatTmp = record.getTvvktb().replace(".", "");
							double grossWeight = Double.parseDouble(grossFormatTmp.replace(",", "."));
							String netFormatTmp = record.getTvvktn().replace(".", "");
							double netWeight = Double.parseDouble(netFormatTmp.replace(",", "."));
							
							//Net can not be > than Gross
							if (netWeight>grossWeight){
								errors.rejectValue("tvvktb", "systema.skat.ncts.export.header.error.rule.item.netWeightTooBig");
							}
						}catch(Exception e){
							//just take a fantom hit here 
						}
					}
				}
				
				//Check valid Oppdrag reference
				if(!record.isValidOppdragRef()){
					errors.rejectValue("tvtdn2", "systema.skat.ncts.export.header.error.rule.item.tvtdn2.mustExist");
				}
				
				//--------------------------------
				//Dok.Type and Dok.ref (Rubrik 44)
				//--------------------------------
				if( (record.getTvdty()!=null && !"".equals(record.getTvdty())) || (record.getTvdref()!=null && !"".equals(record.getTvdref())) ){
					if(!isValidRubrik44(record)){
						errors.rejectValue("tvdty", "systema.skat.ncts.export.header.error.rule.item.docType.rubrik44");
					}
				}
				
			}
			
		}
			
	}
	//Either none or both values must exist
	private boolean isValidRubrik44(JsonSkatNctsExportSpecificTopicItemRecord record){
		boolean retval = true;
		//(a)
		if( record.getTvdty()!=null && !"".equals(record.getTvdty())){
			if( record.getTvdref()==null || "".equals(record.getTvdref())){
				retval = false;
			}
				
		}
		//(b)
		if( record.getTvdref()!=null && !"".equals(record.getTvdref())){
			if( record.getTvdty()==null || "".equals(record.getTvdty())){
				retval = false;
			}
				
		}
		
		return retval;
		
	}
	
	/**
	 * Validate towards sensitive goods register
	 * 
	 * @param record
	 * @return
	 */
	private boolean isVarukodWithSensitiveGoodsFlag(JsonSkatNctsExportSpecificTopicItemRecord record){
		String method = "isVarukodWithSensitiveGoodsFlag";
	 	logger.info("Inside " + method);
	 	boolean retval = true;
	 	/*removed for TEST DACHSER
	 	boolean retval = false;
	 	String FOLSOMMEVARE_CODE = "064";
	 	logger.info("VALIDATION of Sensitive Goods...");
		//---------------------------
		//get BASE URL = RPG-PROGRAM
		//---------------------------
		String BASE_URL = SkatUrlDataStore.SKAT_NCTS_CODES_URL;
		//url params
		String urlRequestParamsKeys = "user=" + record.getApplicationUser() + "&typ=" + FOLSOMMEVARE_CODE + "&tariff=" + record.getTvvnt();
		//String urlRequestParamsKeys = "user=" + record.getApplicationUser() + "&tftanr=" + record.getTvvnt();
		//for debug purposes in GUI
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("URL: " + BASE_URL);
		logger.info("URL PARAMS: " + urlRequestParamsKeys);
		//--------------------------------------
		//EXECUTE the FETCH (RPG program) here
		//--------------------------------------
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//Debug --> 
		logger.info(method + " --> jsonPayload:" + jsonPayload);
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	
		if(jsonPayload!=null){
			try{
			JsonSkatNctsCodeContainer container = this.skatDropDownListPopulationService.getNctsCodeContainer(jsonPayload);
				if(container!=null){
					for(JsonSkatNctsCodeRecord codeRecord : container.getKodlista()){
						logger.info("tkkode:" + codeRecord.getTkkode());
						record.setTvfv(codeRecord.getTfkode());
						retval = true;
					}	
					//if no match erase kode/antal
					if(!retval){
						
						record.setTvfv(null);
						record.setTvfvnt(null);
					}
				}
			}catch(Exception e){
				
			}
		}
		*/
		return retval;
	}
	
}
