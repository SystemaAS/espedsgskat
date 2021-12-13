package no.systema.skat.nctsimport.controller;

import java.util.*;

 
import org.apache.logging.log4j.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.ServletRequestDataBinder;
//import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

//import no.systema.tds.service.MainHdTopicService;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;

import no.systema.skat.nctsimport.validator.SkatNctsImportUnloadingItemsValidator;


//application imports
import no.systema.skat.util.SkatConstants;
import no.systema.skat.nctsimport.util.manager.CodeDropDownMgr;
import no.systema.skat.service.html.dropdown.SkatDropDownListPopulationService;
import no.systema.skat.model.external.url.UrlISOLanguageObject;
import no.systema.skat.model.jsonjackson.codes.JsonSkatNctsCodeContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatNctsCodeRecord;

import no.systema.skat.url.store.SkatUrlDataStore;
//
import no.systema.skat.nctsimport.mapper.url.request.UrlRequestParameterMapper;
import no.systema.skat.nctsimport.service.html.dropdown.SkatNctsImportDropDownListPopulationService;
import no.systema.skat.nctsimport.url.store.SkatNctsImportUrlDataStore;
import no.systema.skat.nctsimport.util.RpgReturnResponseHandler;
import no.systema.skat.nctsimport.service.SkatNctsImportSpecificTopicUnloadingItemService;
import no.systema.skat.nctsimport.model.jsonjackson.topic.JsonSkatNctsImportSpecificTopicRecord;
import no.systema.skat.nctsimport.model.jsonjackson.topic.unloading.JsonSkatNctsImportSpecificTopicUnloadingRecord;
import no.systema.skat.nctsimport.model.jsonjackson.topic.unloading.items.JsonSkatNctsImportSpecificTopicUnloadingItemContainer;
import no.systema.skat.nctsimport.model.jsonjackson.topic.unloading.items.JsonSkatNctsImportSpecificTopicUnloadingItemRecord;




/**
 * SKAT-NCTS Import create items gateway
 * 
 * @author oscardelatorre
 * @date Apr 28, 2014
 * 
 * 
 * 	
 */

@Controller
//@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SkatNctsImportUnloadingItemsController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger(800);
	private static final Logger logger = LogManager.getLogger(SkatNctsImportUnloadingItemsController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();

	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        //binder.setValidator(new TdsExportItemsValidator());
    }
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			 
		}
	}
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatnctsimport_unloading_edit_items.do")
	public ModelAndView nctsImportEditItem(@ModelAttribute ("record") JsonSkatNctsImportSpecificTopicUnloadingItemRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		logger.info("Inside: skatnctsImportEditItemUnloading");
		ModelAndView successView = new ModelAndView("skatnctsimport_unloading_edit_items");
		
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		
		JsonSkatNctsImportSpecificTopicRecord recordTopicHeader = (JsonSkatNctsImportSpecificTopicRecord)session.getAttribute(SkatConstants.DOMAIN_RECORD_TOPIC_SKAT_NCTS_IMPORT);
		//Get the header record in order to use some values in the validator
		JsonSkatNctsImportSpecificTopicUnloadingRecord headerUnloadingRecord = (JsonSkatNctsImportSpecificTopicUnloadingRecord)session.getAttribute(SkatConstants.DOMAIN_RECORD_TOPIC_SKAT_UNLOADING);
		JsonSkatNctsImportSpecificTopicUnloadingItemRecord jsonNctsImportSpecificTopicUnloadingItemRecord = null;
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		Map model = new HashMap();
		String urlRequestParamsKeys = null;
		//Catch required action (doFetch or doUpdate)
		String action = request.getParameter("action");
		logger.info("ACTION: " + action);
		
		if(appUser==null){
			return this.loginView;
		}else{
		  
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SKAT_NCTS_IMPORT);
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, SkatConstants.ACTIVE_URL_RPG_INITVALUE); 
			
			boolean isValidCreatedRecordTransactionOnRPG = true;
			//Keys and header information
			String opd = request.getParameter("opd");
			String avd = request.getParameter("avd");
			String sign = request.getParameter("sign");
			String mrnNr = request.getParameter("mrnNr");
			
			String status = request.getParameter("status");
			String datum = request.getParameter("datum");
			//new line
			String renew = request.getParameter("renew");
			if(renew!=null && !"".equals(renew)){
				//clean
				session.removeAttribute("tvli_SESSION");
			}
			//this key is only used with a real Update. When empty it will be a signal for a CREATE NEW (Add)
			String lineNr = request.getParameter("tvli");
			if(lineNr!=null && !"".equals(lineNr)){
				//nothing
			}else{
				//this branch is necessary in order to get the line Nr after a validation error (ref. below att bindingResult.hasErrors in this same method)
				lineNr = (String)session.getAttribute("tvli_SESSION");
			}
			model.put("avd", avd);
			model.put("opd", opd);
			model.put("sign", sign);
			model.put("status", status);
			model.put("datum", datum);
			model.put("mrnNr", mrnNr);
			
			
			if(SkatConstants.ACTION_UPDATE.equals(action)){
				
				//-----------
				//Validation
				//-----------
				SkatNctsImportUnloadingItemsValidator validator = new SkatNctsImportUnloadingItemsValidator();
				logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
				//-------------------------------------------------------
				//this is only for validation of gross weight 
				//in first item line (manadatory), this is the only way
				//-------------------------------------------------------
				/*String numberOfItemLinesInTopicStr = request.getParameter("numberOfItemLinesInTopic");
				if(numberOfItemLinesInTopicStr==null || "".equals(numberOfItemLinesInTopicStr)){
					numberOfItemLinesInTopicStr = "0";
				}
				recordToValidate.setNumberOfItemLinesInTopicStr(numberOfItemLinesInTopicStr);
				//in order to flag an update one-line item
				if(lineNr!=null && !"".equals(lineNr)){
					if("1".equals(numberOfItemLinesInTopicStr)){
						recordToValidate.setNumberOfItemLinesInTopicStr("-99");
					}
				}
				*/
				//put some header records in aux.attributes (in order to send to validator)... Add more if applicable
				//setHeader_dkeh_aart(headerRecord.getDkeh_aart());
				recordToValidate.setHeader_nikonf(headerUnloadingRecord.getNikonf());
				
				//bind to validator
				validator.validate(recordToValidate, bindingResult);
				
				
			    //check for ERRORS
				if(bindingResult.hasErrors()){
					recordToValidate.setTvli(null);
				    	logger.info("[ERROR Validation] Item Record does not validate)");
				    	logger.info("[INFO lineNr] " + lineNr);
				    	
				    	model.put("record", recordToValidate);
				    	if(lineNr!=null && !"".equals(lineNr)){
				    		logger.info("[INFO lineNr] ... filling old value: lineNr:" + lineNr);
				    		session.setAttribute("tvli_SESSION", lineNr);
				    		recordToValidate.setTvli(lineNr);
					    	recordToValidate.setTvtdn(opd);
				    		recordToValidate.setTvavd(avd);
				    	}
			    }else{
					if(lineNr!=null && !"".equals(lineNr)){
						//clean
						session.removeAttribute("tvli_SESSION");
				    	
						//-------
						//UPDATE
						//-------
						logger.info("UPDATE(only) ITEM (existent item) in process...");
						//take the rest from GUI.
						jsonNctsImportSpecificTopicUnloadingItemRecord = new JsonSkatNctsImportSpecificTopicUnloadingItemRecord();
						ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonNctsImportSpecificTopicUnloadingItemRecord);
			            //binder.registerCustomEditor(...); // if needed
			            binder.bind(request);
			            //put back the generated seed and the header keys (avd,opd)
			            jsonNctsImportSpecificTopicUnloadingItemRecord.setTvavd(avd);
			            jsonNctsImportSpecificTopicUnloadingItemRecord.setTvtdn(opd);
			            //put back the values of sensitive goods
			            //jsonNctsImportSpecificTopicUnloadingItemRecord.setTvfv(recordToValidate.getTvfv());
			            //jsonNctsImportSpecificTopicUnloadingItemRecord.setTvfvnt(recordToValidate.getTvfvnt());
			            
			            logger.info("[DEBUG] UPDATE on Line nr: " + jsonNctsImportSpecificTopicUnloadingItemRecord.getTvli());
			            logger.info("[DEBUG] UPDATE on Opd: " + jsonNctsImportSpecificTopicUnloadingItemRecord.getTvtdn());
			            logger.info("[DEBUG] UPDATE on Avd: " + jsonNctsImportSpecificTopicUnloadingItemRecord.getTvavd());
			            //logger.info("[DEBUG] UPDATE on tvtdsk: " + jsonNctsImportSpecificTopicUnloadingItemRecord.getTvtdsk());
			            //logger.info("[DEBUG] UPDATE on tvtdo: " + jsonNctsImportSpecificTopicUnloadingItemRecord.getTvtdo());
						
					}else{
						//-------
						//CREATE
						//-------
						logger.info("CREATE and UPDATE on ITEM (new fresh item) on process...");
						//This means that the update will be done AFTER a creation of an empty record. All this in the same transaction. 2 STEPS involved: (1)create and (2)update
						//-----------------------------------------------------------------------------------------
						//STEP[1] Generate new Item line key seeds (avd,opd,syli) by creating an empty new record. 
						//		  This step is ONLY applicable for new item lines 
						//-------------------------------------------------------------------------------------------
						jsonNctsImportSpecificTopicUnloadingItemRecord  = this.createNewItemKeySeeds(session, request, appUser);
						if(jsonNctsImportSpecificTopicUnloadingItemRecord!=null){
							String newLineNr = jsonNctsImportSpecificTopicUnloadingItemRecord.getTvli();
							logger.info("[INFO] New LineNr:" + newLineNr);
							//take the rest from GUI.
							jsonNctsImportSpecificTopicUnloadingItemRecord = new JsonSkatNctsImportSpecificTopicUnloadingItemRecord();
							ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonNctsImportSpecificTopicUnloadingItemRecord);
				            //binder.registerCustomEditor(...); // if needed
				            binder.bind(request);
				            
				            jsonNctsImportSpecificTopicUnloadingItemRecord.setTvli(newLineNr);
				            //put back the generated seed and the header keys (avd,opd)
				            jsonNctsImportSpecificTopicUnloadingItemRecord.setTvtdn(opd);
				            jsonNctsImportSpecificTopicUnloadingItemRecord.setTvavd(avd);
				            logger.info("[INFO] Varubeskrivning (on item record):" + jsonNctsImportSpecificTopicUnloadingItemRecord.getTvvt());
				            logger.info("[INFO] New line number (on item record):" + jsonNctsImportSpecificTopicUnloadingItemRecord.getTvli());
				            //put back the values of sensitive goods
				            //jsonNctsImportSpecificTopicUnloadingItemRecord.setTvfv(recordToValidate.getTvfv());
				            //jsonNctsImportSpecificTopicUnloadingItemRecord.setTvfvnt(recordToValidate.getTvfvnt());
				            
						}else{
							isValidCreatedRecordTransactionOnRPG = false;
						}
					}
					//--------------------------------------------------
					//At this point we are ready to do an update
					//--------------------------------------------------
					if(isValidCreatedRecordTransactionOnRPG){
						
			            logger.info("[INFO] Valid previous step successfully processed, OK ");
			            logger.info("[INFO] Ready to move forward to do the UPDATE");
			            
						//---------------------------
						//get BASE URL = RPG-PROGRAM
			            //---------------------------
						String BASE_URL_UPDATE = SkatNctsImportUrlDataStore.NCTS_IMPORT_BASE_UPDATE_TOPIC_UNLOADING_ITEM_URL;
						urlRequestParamsKeys = this.getRequestUrlKeyParametersUpdate(jsonNctsImportSpecificTopicUnloadingItemRecord, appUser, SkatConstants.MODE_UPDATE);
						String urlRequestParamsTopicItem = this.urlRequestParameterMapper.getUrlParameterValidString((jsonNctsImportSpecificTopicUnloadingItemRecord));
						//put the final valid param. string
						String urlRequestParams = urlRequestParamsKeys + urlRequestParamsTopicItem;
						//for debug purposes in GUI
						session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL_UPDATE + "==>params: " + urlRequestParams.toString()); 
						
						logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
					    	logger.info("URL: " + BASE_URL_UPDATE);
					    	logger.info("URL PARAMS: " + urlRequestParams);
					    	
					    	//----------------------------------------------------------------------------
					    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
					    	//----------------------------------------------------------------------------
						String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_UPDATE, urlRequestParams);
						
						//Debug --> 
					    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
					    	//we must evaluate a return RPG code in order to know if the Update was OK or not
					    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicItemCreateOrUpdate(rpgReturnPayload);
					    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
					    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
					    		this.setFatalError(model, rpgReturnResponseHandler, jsonNctsImportSpecificTopicUnloadingItemRecord);
					    		
					    	}else{
					    		//Update succefully done!
					    		logger.info("[INFO] Valid STEP[2] Update -- Record successfully updated, OK ");
					    		//---------------------------
					    		//REFRESH ON SOME VARIABLES
					    		//---------------------------
					    		//Update some variables on header such as (1)Number of item lines, (2)Kolliantal and (3)Gross weight-Bruttovikt
					    		this.refreshHeaderVariables(appUser.getUser(), avd, opd);
					    	}
					    	
					}else{
						rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on CREATE, at tuid, syop generation : " + rpgReturnResponseHandler.getErrorMessage());
						this.setFatalError(model, rpgReturnResponseHandler, jsonNctsImportSpecificTopicUnloadingItemRecord);
					}
			    }
				
			}else if(SkatConstants.ACTION_DELETE.equals(action)){
				
				logger.info("[INFO] Delete record start process... ");
				String lineNrToDelete = request.getParameter("lin");
				
				//---------------------------
				//get BASE URL = RPG-PROGRAM
	            //---------------------------
				String BASE_URL_DELETE = SkatNctsImportUrlDataStore.NCTS_IMPORT_BASE_UPDATE_TOPIC_UNLOADING_ITEM_URL;
				jsonNctsImportSpecificTopicUnloadingItemRecord = new JsonSkatNctsImportSpecificTopicUnloadingItemRecord();
				jsonNctsImportSpecificTopicUnloadingItemRecord.setTvli(lineNrToDelete);
				jsonNctsImportSpecificTopicUnloadingItemRecord.setTvavd(avd);
				jsonNctsImportSpecificTopicUnloadingItemRecord.setTvtdn(opd);
				String urlRequestParams = this.getRequestUrlKeyParametersUpdate(jsonNctsImportSpecificTopicUnloadingItemRecord, appUser,SkatConstants.MODE_DELETE );
				
				logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			    	logger.info("URL: " + BASE_URL_DELETE);
			    	logger.info("URL PARAMS: " + urlRequestParams);
			    	//----------------------------------------------------------------------------
			    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
			    	//----------------------------------------------------------------------------
				String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_DELETE, urlRequestParams);
				//Debug --> 
			    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
			    	//we must evaluate a return RPG code in order to know if the Update was OK or not
			    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicItemCreateOrUpdate(rpgReturnPayload);
			    	
			    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
			    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
			    		this.setFatalError(model, rpgReturnResponseHandler, jsonNctsImportSpecificTopicUnloadingItemRecord);
			    		
			    	}else{
			    		//Delete successfully done!
			    		logger.info("[INFO] Valid Delete -- Record successfully deleted, OK ");
			    		//---------------------------
			    		//REFRESH ON SOME VARIABLES
			    		//---------------------------
			    		//Update some variables on header such as (1)Number of item lines, (2)Kolliantal and (3)Gross weight-Bruttovikt
			    		this.refreshHeaderVariables(appUser.getUser(), avd, opd);
			    	}
				
			}
			
	    	
			//FETCH the ITEM LIST of existent ITEMs for this TOPIC
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL_FETCH = SkatNctsImportUrlDataStore.NCTS_IMPORT_BASE_FETCH_TOPIC_UNLOADING_ITEMLIST_URL;
			
			urlRequestParamsKeys = this.getRequestUrlKeyParameters(request, avd, opd, appUser);
			//for debug purposes in GUI
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL_FETCH + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
			logger.info("FETCH av item list... ");
	     	logger.info("URL: " + BASE_URL_FETCH);
		    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
		    	//--------------------------------------
		    	//EXECUTE the FETCH (RPG program) here
		    	//--------------------------------------
			String jsonPayloadFetch = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
			//Debug --> 
			logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayloadFetch));
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	JsonSkatNctsImportSpecificTopicUnloadingItemContainer jsonNctsImportSpecificTopicUnloadingItemContainer = this.skatNctsImportSpecificTopicUnloadingItemService.getNctsImportSpecificTopicUnloadingItemContainer(jsonPayloadFetch);
		    	
		    	this.setCodeDropDownMgr(appUser, model);
		    	this.setDomainObjectsForListInView(model, jsonNctsImportSpecificTopicUnloadingItemContainer);
			
			successView.addObject("model",model);
			
			//successView.addObject(TdsConstants.EDIT_ACTION_ON_TOPIC, TdsConstants.ACTION_FETCH);//remove this line
			return successView;
		}
	}
	
	
	/**
	 * 
	 * @param appUser
	 * @param avd
	 * @param opd
	 */
	private void refreshHeaderVariables(String appUser, String avd, String opd){
		
		//---------------------------
		//REFRESH ON SOME VARIABLES
		//---------------------------
		//Update some variables on header such as (1)Number of item lines, (2)Kolliantal and (3)Gross weight-Bruttovikt
		logger.info("[INFO] REFRESH of topic ... starting now...");
		String BASE_URL_REFRESH = SkatNctsImportUrlDataStore.NCTS_IMPORT_BASE_REFRESH_SPECIFIC_TOPIC_URL;
		String urlRequestParams = "user=" + appUser + "&avd=" + avd + "&opd=" + opd; 
		
		logger.info("URL: " + BASE_URL_REFRESH);
		logger.info("URL PARAMS: " + urlRequestParams);
		String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_REFRESH, urlRequestParams);
		logger.info(rpgReturnPayload);	
		
	}
	/**
	 * Set aspects  objects
	 * @param model
	 * @param rpgReturnResponseHandler
	 */
	private void setAspectsInView (Map model, RpgReturnResponseHandler rpgReturnResponseHandler){
		model.put(SkatConstants.ASPECT_ERROR_MESSAGE, rpgReturnResponseHandler.getErrorMessage());
		//extra error information
		StringBuffer errorMetaInformation = new StringBuffer();
		errorMetaInformation.append(rpgReturnResponseHandler.getUser());
		errorMetaInformation.append(rpgReturnResponseHandler.getTitdn());
		errorMetaInformation.append(rpgReturnResponseHandler.getTvtdn());
		errorMetaInformation.append(rpgReturnResponseHandler.getTvli());
		
		model.put(SkatConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
	/**
	 * Sets domain objects
	 * @param model
	 * @param jsonNctsImportSpecificTopicUnloadingItemContainer
	 */
	private void setDomainObjectsForListInView(Map model, JsonSkatNctsImportSpecificTopicUnloadingItemContainer container){
		List list = new ArrayList();
		if(container!=null){
			for (JsonSkatNctsImportSpecificTopicUnloadingItemRecord record : container.getOrderList()){
				list.add(record);
				logger.info(record.getTvvt());
			}
		}
		model.put(SkatConstants.DOMAIN_LIST, list);
	}
	/**
	 * Sets domain objects
	 * @param model
	 * @param jsonNctsImportSpecificTopicUnloadingItemRecord
	 */
	private void setDomainObjectsInView(Map model, JsonSkatNctsImportSpecificTopicUnloadingItemRecord record){
		model.put(SkatConstants.DOMAIN_RECORD, record);
	}
	
	/**
	 * 
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param jsonNctsExportSpecificTopicItemRecord
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonSkatNctsImportSpecificTopicUnloadingItemRecord record){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, record);
	}
	
	/**
	 * 
	 * Creates the record (Add) for a later update in the same transaction
	 * @param session
	 * @param request
	 * @param appUser
	 * @return
	 */
	private JsonSkatNctsImportSpecificTopicUnloadingItemRecord createNewItemKeySeeds(HttpSession session, HttpServletRequest request, SystemaWebUser appUser){
		
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		
		//request variables
		String numberOfItemLinesInTopicStr = request.getParameter("numberOfItemLinesInTopic");
		if(numberOfItemLinesInTopicStr==null || "".equals(numberOfItemLinesInTopicStr)){
			numberOfItemLinesInTopicStr = "0";
		}else{
			numberOfItemLinesInTopicStr = numberOfItemLinesInTopicStr.trim();
		}
			
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		
		JsonSkatNctsImportSpecificTopicUnloadingItemRecord jsonNctsImportSpecificTopicUnloadingItemRecord = new JsonSkatNctsImportSpecificTopicUnloadingItemRecord();
		
		try{
			//---------------------------
			//get BASE URL = RPG-PROGRAM
	        //---------------------------
			String BASE_URL = SkatNctsImportUrlDataStore.NCTS_IMPORT_BASE_UPDATE_TOPIC_UNLOADING_ITEM_URL;
			
			//-------------------------------------------------------------------------------------------
			// STEP[PREPARE CREATION] --> generate new opd and tuid (if applicable) in order to be able to Add (Create)
			//-------------------------------------------------------------------------------------------
			logger.info("STEP[1] GET SEEDS and CREATE RECORD...");
			StringBuffer urlRequestParamsForSeed = new StringBuffer();
			urlRequestParamsForSeed.append("user=" + appUser.getUser());
			urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
			urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
			Integer numberOfItemLinesInTopic = -99;
			
			numberOfItemLinesInTopic = Integer.valueOf(numberOfItemLinesInTopicStr);
			//add one
			numberOfItemLinesInTopic++;
			logger.info("New item line nr: " + numberOfItemLinesInTopic);
			
			urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + numberOfItemLinesInTopic);
			urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + SkatConstants.MODE_ADD);
			logger.info("New SEEDs URL: " + BASE_URL);
			logger.info("PARAMS for SEED: " + urlRequestParamsForSeed.toString());
			//for debug purposes in GUI
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL + " ==>params: " + urlRequestParamsForSeed.toString() );
					
			//Get the counter from RPG (new opd Id)
			String rpgSeedNumberPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsForSeed.toString());
			// Map the JSON response to the new seeds (tvtdn,tvli)
			// We are not using std JSON conversion since the RPGs strings are not the same. Should be the same as
			// the header fields. The RPG output should be changed in order to comply to the field specification...
			rpgReturnResponseHandler.evaluateRpgResponseOnTopicItemCreateOrUpdate(rpgSeedNumberPayload);
			
			//we must complete the GUI-json with the value from a line nr seed here
			if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage()) ){
				logger.info("[ERROR] No mandatory seeds (tvli, opd) were generated correctly)! look at std output log. [errMsg]" + rpgReturnResponseHandler.getErrorMessage());
				jsonNctsImportSpecificTopicUnloadingItemRecord = null;
				
			}else{
				jsonNctsImportSpecificTopicUnloadingItemRecord.setTvtdn(rpgReturnResponseHandler.getTvtdn());
				jsonNctsImportSpecificTopicUnloadingItemRecord.setTvli(rpgReturnResponseHandler.getTvli());
			}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return jsonNctsImportSpecificTopicUnloadingItemRecord;
		
	}
	/**
	 * 
	 * @param request
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(HttpServletRequest request, String avd, String opd, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		
		
		return urlRequestParamsKeys.toString();
	}
	
	/**
	 * Update parameters
	 * 
	 * @param record
	 * @param appUser
	 * @param mode
	 * @return
	 */
	private String getRequestUrlKeyParametersUpdate(JsonSkatNctsImportSpecificTopicUnloadingItemRecord record, SystemaWebUser appUser, String mode){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + record.getTvavd());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + record.getTvtdn());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "lin=" + record.getTvli());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + mode);
		
		return urlRequestParamsKeys.toString();
	}
	
	
	/**
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_008_COUNTRY, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_012_SPRAK, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_013_DOKTYPE, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_017_KOLLI, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_064_KANSLIGVARA, null, null);
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_301_STATUS_KODER, null, null);
		
		
	}
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
		
	
	@Qualifier ("skatNctsImportSpecificTopicUnloadingItemService")
	private SkatNctsImportSpecificTopicUnloadingItemService skatNctsImportSpecificTopicUnloadingItemService;
	@Autowired
	@Required
	public void setSkatNctsImportSpecificTopicUnloadingItemService (SkatNctsImportSpecificTopicUnloadingItemService value){ this.skatNctsImportSpecificTopicUnloadingItemService = value; }
	public SkatNctsImportSpecificTopicUnloadingItemService getSkatNctsImportSpecificTopicUnloadingItemService(){ return this.skatNctsImportSpecificTopicUnloadingItemService; }
	 
	
	@Qualifier ("skatNctsImportDropDownListPopulationService")
	private SkatNctsImportDropDownListPopulationService skatNctsImportDropDownListPopulationService;
	@Autowired
	public void setSkatNctsImportDropDownListPopulationService (SkatNctsImportDropDownListPopulationService value){ this.skatNctsImportDropDownListPopulationService = value; }
	public SkatNctsImportDropDownListPopulationService getSkatNctsImportDropDownListPopulationService(){return this.skatNctsImportDropDownListPopulationService;}
	
	
	@Qualifier ("skatDropDownListPopulationService")
	private SkatDropDownListPopulationService skatDropDownListPopulationService;
	@Autowired
	public void setSkatDropDownListPopulationService (SkatDropDownListPopulationService value){ this.skatDropDownListPopulationService = value; }
	public SkatDropDownListPopulationService getSkatDropDownListPopulationService(){return this.skatDropDownListPopulationService;}
	 
	 
}

