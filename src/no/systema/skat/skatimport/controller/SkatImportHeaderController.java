package no.systema.skat.skatimport.controller;

import java.util.*;

 
import org.slf4j.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;


//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportSpecificTopicFaktTotalContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportSpecificTopicFaktTotalRecord;
import no.systema.skat.skatexport.url.store.SkatExportUrlDataStore;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportSpecificTopicFaktTotalContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportSpecificTopicFaktTotalRecord;

import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicCopiedContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicCopiedFromTransportUppdragContainer;
//
import no.systema.skat.model.jsonjackson.avdsignature.JsonSkatAvdelningContainer;
import no.systema.skat.model.jsonjackson.avdsignature.JsonSkatAvdelningRecord;
import no.systema.skat.model.jsonjackson.avdsignature.JsonSkatSignatureContainer;
import no.systema.skat.model.jsonjackson.avdsignature.JsonSkatSignatureRecord;
import no.systema.skat.skatimport.mapper.url.request.UrlRequestParameterMapper;

import no.systema.skat.skatimport.model.topic.SkatImportSpecificTopicTotalItemLinesObject;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportSpecificTopicRecalculationContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportSpecificTopicContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportSpecificTopicRecord;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemRecord;
import no.systema.skat.skatimport.url.store.SkatImportUrlDataStore;
import no.systema.skat.service.html.dropdown.SkatDropDownListPopulationService;
import no.systema.skat.skatimport.service.html.dropdown.SkatImportDropDownListPopulationService;
import no.systema.skat.skatimport.validator.SkatImportHeaderValidator;

import no.systema.skat.skatimport.service.SkatImportSpecificTopicService;
import no.systema.skat.skatimport.service.SkatImportSpecificTopicItemService;

import no.systema.skat.util.SkatConstants;
import no.systema.skat.url.store.SkatUrlDataStore;
import no.systema.skat.skatimport.util.RpgReturnResponseHandler;
import no.systema.skat.skatimport.util.manager.CodeDropDownMgr;


/**
 * SKAT Import Topic Controller 
 * 
 * @author oscardelatorre
 * @date Jan 24, 2014
 * 
 */

@Controller
@Scope("session")
public class SkatImportHeaderController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LoggerFactory.getLogger(SkatImportHeaderController.class.getName());
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
		//binder.setValidator(new TdsExportValidator()); //it must have spring form tags in the html otherwise = meaningless
    }
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			 
		}
	}	
	
	/**
	 * Renders the create GUI view (without any logic)
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatimport_edit.do",  params="action=doPrepareCreate", method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doPrepareCreate(HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		ModelAndView successView = new ModelAndView("skatimport_edit");
		logger.info("Method: doPrepareCreate");
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			
        //add gui lists here
		this.setCodeDropDownMgr(appUser, model);	
    		this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
    		this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
    		//domain
    		//logger.info("#######" + request.getAttribute("errorMessageOnCopyFromTransportOppdrag"));
    		successView.addObject("model", model);
    		successView.addObject(SkatConstants.EDIT_ACTION_ON_TOPIC, SkatConstants.ACTION_CREATE);

		}
		
		return successView;
	}
	
	
	
	/**
	 * Creates or Updates a new Topic (Arende)
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatimport_edit.do")
	public ModelAndView doSkatImportEdit(@ModelAttribute ("record") JsonSkatImportSpecificTopicRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("skatimport_edit");
		String method = "doSkatImportEdit";
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		logger.info("Method: " + method);
		
		SkatImportSpecificTopicTotalItemLinesObject totalItemLinesObject = new SkatImportSpecificTopicTotalItemLinesObject();
		//---------------------------------
		//Crucial request parameters (Keys)
		//---------------------------------
		String action = request.getParameter("action");
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		String sign = request.getParameter("sign");
		String dkih_0035 = request.getParameter("dkih_0035"); //test indicator
		String recalculationFlag = request.getParameter("recalculationFlag"); //test indicator
		
		logger.info("TEST flag:<" + dkih_0035 +">");
		
		//Action (doFetch, doUpdate, doCreate)
		logger.info("Action:" + action);
		logger.info("Opd:" + opd);
		logger.info("Avd:" + avd);
		logger.info("Sign:" + sign);
		logger.info("Fakturabelop (dkih_222):" + recordToValidate.getDkih_222());
		
		Map model = new HashMap();
		
		if(appUser==null){
			return this.loginView;
		}else{
			if(action!=null){
				//get some item lines total fields (∑)
				totalItemLinesObject = this.getRequiredSumsInItemLines(avd, opd, appUser);
				//get invoice totals from invoice list
				JsonSkatImportSpecificTopicFaktTotalRecord sumFaktTotalRecord = this.getInvoiceTotalFromInvoices(avd, opd, appUser);
				totalItemLinesObject.setFakturaListTotValidCurrency(sumFaktTotalRecord.getTot_vakd());
				totalItemLinesObject.setFakturaListTotSum(sumFaktTotalRecord.getTot_fabl());
				totalItemLinesObject.setFakturaListTotKurs(sumFaktTotalRecord.getTot_vaku());
				
				//-------------
				//FETCH RECORD
				//-------------
				if(SkatConstants.ACTION_FETCH.equals(action)){
						logger.info("FETCH record transaction...");
						//---------------------------
						//get BASE URL = RPG-PROGRAM
			            //---------------------------
						String BASE_URL = SkatImportUrlDataStore.SKAT_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
						//url params
						String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opd, sign, appUser);
						//for debug purposes in GUI
						session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
						
						logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
				    	logger.warn("URL: " + BASE_URL);
				    	logger.warn("URL PARAMS: " + urlRequestParamsKeys);
				    	//--------------------------------------
				    	//EXECUTE the FETCH (RPG program) here
				    	//--------------------------------------
				    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
						//Debug --> 
				    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
				    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
				    	if(jsonPayload!=null){
				    		JsonSkatImportSpecificTopicContainer jsonSkatImportSpecificTopicContainer = this.skatImportSpecificTopicService.getSkatImportSpecificTopicContainer(jsonPayload);
				    		//populate gui elements
				    		this.setCodeDropDownMgr(appUser, model);	
				    		this.setDomainObjectsInView(session, model, jsonSkatImportSpecificTopicContainer, totalItemLinesObject);
				    		successView.addObject(SkatConstants.DOMAIN_MODEL, model);
				    		//put the doUpdate action since we are preparing the record for an update (when saving)
				    		successView.addObject(SkatConstants.EDIT_ACTION_ON_TOPIC, SkatConstants.ACTION_UPDATE);
				    		
				    	}else{
				    		logger.error("NO CONTENT on jsonPayload from URL... ??? <Null>");
				    		return loginView;
				    	}
				    		logger.info(Calendar.getInstance().getTime() +  "END of FETCH");
					
			    	//----------------------------
				//CREATE and/or UPDATE RECORD
				//----------------------------	
				}else if(SkatConstants.ACTION_UPDATE.equals(action)){
					boolean isValidCreatedRecordTransactionOnRPG = true;
					//-----------
					//Validation
					//-----------
					logger.info("Host via HttpServletRequest.getHeader('Host'): " + request.getHeader("Host"));
					//this validation alternative is only used in a pure update.
					if(opd!=null && !"".equals(opd)){
						logger.info("PURE UPDATE validation...");
						totalItemLinesObject = this.getRequiredSumsInItemLines(avd, opd, appUser);
						//We do fetch this number here in order to update the recordToValidate (for validation purposes) 
						recordToValidate.setSumOfAntalKolliInItemLines(totalItemLinesObject.getSumOfAntalKolliInItemLines());
						recordToValidate.setSumOfAntalItemLines(totalItemLinesObject.getSumOfAntalItemLines());
						recordToValidate.setSumTotalAmountItemLines(totalItemLinesObject.getSumTotalAmountItemLines());
						this.adjustForPrivatePerson(request, recordToValidate);
						
					}else{
						recordToValidate.setDkih_syav(avd);
						recordToValidate.setDkih_sysg(sign);
					}
					
					SkatImportHeaderValidator validator = new SkatImportHeaderValidator();
					//required validation only for production avd
					if( !"1".equals(recordToValidate.getTestAvdFlag()) ){
						validator.validate(recordToValidate, bindingResult);
					}
					//test indicator in validation field
					recordToValidate.setDkih_0035(dkih_0035);
					
					//----------------------------
				    //check for validation ERRORS
					//----------------------------
					if(bindingResult.hasErrors()){
						
					    	logger.info("[ERROR Validation] Record does not validate)");
					    	//put domain objects and do go back to the original view...
					    	recordToValidate.setDkih_syav(avd);
					    	recordToValidate.setDkih_sysg(sign);
					    	this.setDomainObjectsInView(session, model, recordToValidate, totalItemLinesObject);
					    	isValidCreatedRecordTransactionOnRPG = false;
					    	if(opd==null || "".equals(opd)){
					    		action = SkatConstants.ACTION_CREATE;
					    	}

				    }else{
			    		JsonSkatImportSpecificTopicRecord jsonSkatImportSpecificTopicRecord = null;
						String tuidRefNr = null;
						
						if(opd!=null && !"".equals(opd)){
							logger.info("PURE UPDATE transaction..."); 
							//PURE UDPATE transaction
							//this means that the update is an update of an existing record
							jsonSkatImportSpecificTopicRecord = new JsonSkatImportSpecificTopicRecord();
							ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonSkatImportSpecificTopicRecord);
				            //binder.registerCustomEditor(...); // if needed
							logger.info("DKIH_02b (Afs.name) STEP 1: " + request.getParameter("dkih_02b"));
						    binder.bind(request);
				            this.adjustTollverdiFieldsAfterBind(request, jsonSkatImportSpecificTopicRecord);
				            this.adjustForPrivatePerson(request, jsonSkatImportSpecificTopicRecord);
				            //test indicator, and recalculation
				            jsonSkatImportSpecificTopicRecord.setDkih_0035(dkih_0035);
				            jsonSkatImportSpecificTopicRecord.setDkih_genb(recalculationFlag);
				            logger.info("recalculationFlag: " + jsonSkatImportSpecificTopicRecord.getDkih_genb());
						}else{
							logger.info("CREATE NEW follow by UDATE transaction...");
							//CREATE AND UPDATE transaction
							//This means that the update will be done AFTER a creation of an empty record. All this in the same transaction. 2 STEPS involved: (1)create and (2)update
							//---------------------------------------------------------------------------------------------
							//STEP[1] Generate new Topic key seeds (avd,opd,sign) by creating an empty new record. 
							//---------------------------------------------------------------------------------------------
							jsonSkatImportSpecificTopicRecord = this.createNewTopicHeaderKeySeeds(session, request, appUser, avd, sign);
							if(jsonSkatImportSpecificTopicRecord!=null){
								opd = jsonSkatImportSpecificTopicRecord.getDkih_syop();
								//
								jsonSkatImportSpecificTopicRecord.setDkih_syav(avd);
								jsonSkatImportSpecificTopicRecord.setDkih_sysg(sign);
								
								//take the rest from GUI.
								jsonSkatImportSpecificTopicRecord = new JsonSkatImportSpecificTopicRecord();
								ServletRequestDataBinder binder = new ServletRequestDataBinder(jsonSkatImportSpecificTopicRecord);
					            //binder.registerCustomEditor(...); // if needed
					            binder.bind(request);
					            //adjust fields in order to comply to the back-end requirements
					            this.adjustTollverdiFieldsAfterBind(request, jsonSkatImportSpecificTopicRecord);
					            this.adjustForPrivatePerson(request, jsonSkatImportSpecificTopicRecord);
					            
					            //Now set back with the generated values since the bind method above erases them...
					            jsonSkatImportSpecificTopicRecord.setDkih_syav(avd);
					            jsonSkatImportSpecificTopicRecord.setDkih_syop(opd);
					            jsonSkatImportSpecificTopicRecord.setDkih_sysg(sign);
					            //more completions
					            jsonSkatImportSpecificTopicRecord.setSumOfAntalKolliInItemLines(totalItemLinesObject.getSumOfAntalKolliInItemLines());
					            jsonSkatImportSpecificTopicRecord.setSumOfAntalItemLines(totalItemLinesObject.getSumOfAntalItemLines());
					            jsonSkatImportSpecificTopicRecord.setSumTotalAmountItemLines(totalItemLinesObject.getSumTotalAmountItemLines());
					            //test indicator
					            jsonSkatImportSpecificTopicRecord.setDkih_0035(dkih_0035);
								
							}else{
								//Some kind of error occurred. Set the transaction as invalid...
								isValidCreatedRecordTransactionOnRPG = false;
							}
						
						}
						//--------------------------------------------------
						//At this point we are ready to do an update
						//--------------------------------------------------
						if(isValidCreatedRecordTransactionOnRPG){
				            //---------------------------
							//get BASE URL = RPG-PROGRAM
				            //---------------------------
							String BASE_URL = SkatImportUrlDataStore.SKAT_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
							
							//-----------------------------------
							//add URL-parameter "mode=U" (Update)
							//-----------------------------------
							String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opd, sign, appUser);
							//Set fall-back fields if applicable. Usually deklarant-info. When deklarant = null then it will be = mottagare (receiver)
							//We check this right here though the jsonRecord (if applicable)
							/////this.setDeklarantFieldsIfApplicable(jsonSkatImportSpecificTopicRecord);
							
							//build the url parameters (from GUI-form) to send on a GET/POST method AFTER the keyParameters
							String urlRequestParamsTopic = this.urlRequestParameterMapper.getUrlParameterValidString((jsonSkatImportSpecificTopicRecord));
							//put the final valid param. string
							String urlRequestParams = urlRequestParamsKeys + urlRequestParamsTopic;
							//for debug purposes in GUI
							session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL); 
					    	
							logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
					    	logger.warn("URL: " + BASE_URL);
					    	logger.warn("URL PARAMS: " + urlRequestParams);
					    	//----------------------------------------------------------------------------
					    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
					    	//----------------------------------------------------------------------------
					    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
					    	//Debug --> 
					    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
					    	//we must evaluate a return RPG code in order to know if the Update was OK or not
					    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicUpdate(rpgReturnPayload);
					    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
					    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
					    		this.setFatalError(model, rpgReturnResponseHandler, jsonSkatImportSpecificTopicRecord);
					    		isValidCreatedRecordTransactionOnRPG = false;
					    	}else{
					    		//Update succefully done!
					    		logger.info("[INFO] Record successfully updated, OK ");
					    		//put domain objects
					    		this.setDomainObjectsInView(session, model, jsonSkatImportSpecificTopicRecord, totalItemLinesObject );
					    		if(totalItemLinesObject.getSumOfAntalItemLines()>0){
					    			this.adjustValidUpdateFlag(model, jsonSkatImportSpecificTopicRecord);
					    		}
					    		//now run the recalculation on item lines(stat.values) if applicable
					    		if("1".equals(jsonSkatImportSpecificTopicRecord.getDkih_genb())){
					    			logger.info("[INFO] Starting recalculation on items' stat.value... ");
					    			String RECALC_BASE_URL = SkatImportUrlDataStore.SKAT_IMPORT_BASE_RECALCULATE_SPECIFIC_TOPIC_ITEM_STATVALUE_URL;
					    			String urlRecalcRequestParamsKeys = "user=" + appUser.getUser() + "&avd=" + avd + "&opd=" + opd;
					    			logger.info("URL: " + RECALC_BASE_URL);
					    			logger.info("PARAMS: " + urlRecalcRequestParamsKeys);
					    			String jsonPayload = urlCgiProxyService.getJsonContent(RECALC_BASE_URL, urlRecalcRequestParamsKeys);
					    			JsonSkatImportSpecificTopicRecalculationContainer recalcContainer = this.skatImportSpecificTopicService.getSkatImportSpecificTopicRecalculationContainer(jsonPayload);
					    			logger.info("[DEBUG recalculation]errMsg:" + recalcContainer.getErrMsg());
					    			logger.info("[DEBUG recalculation] opd:" + recalcContainer.getOpd());
					    			logger.info("[DEBUG recalculation] OK and finished successfully");
					    		 }
					    		
					    	}
						}else{
							rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on CREATE, at tuid, syop generation : " + rpgReturnResponseHandler.getErrorMessage());
							this.setFatalError(model, rpgReturnResponseHandler, jsonSkatImportSpecificTopicRecord);
							isValidCreatedRecordTransactionOnRPG = false;
						}
				    }
		            //add gui lists here (valid only when the create mode is on (usually on validation errors)
					this.populateAvdelningHtmlDropDownsFromJsonString(model, appUser);
					this.populateSignatureHtmlDropDownsFromJsonString(model, appUser);
					this.setCodeDropDownMgr(appUser, model);	
		    			successView.addObject("model" , model);
			    		successView.addObject(SkatConstants.DOMAIN_MODEL, model);

			    		//Edit or Create offset
			    		if(isValidCreatedRecordTransactionOnRPG){
			    			successView.addObject(SkatConstants.EDIT_ACTION_ON_TOPIC, SkatConstants.ACTION_UPDATE);	
		            }else{
		            		//Validation errors have been generated and we must offset to some state (set or changed above in some flow)
		            		successView.addObject(SkatConstants.EDIT_ACTION_ON_TOPIC, action);
		            }
				//------------------------
				//CREATE-ADD [PURE] RECORD
				//-------------------------
				}else if(SkatConstants.ACTION_CREATE.equals(action)){
					//OBSOLETE
					//This action is not used as an isolated create. It is realized in the UPDATE above in 2 transactions
					//Could be needed in the future.
					
				//------------------
				//REMOVE RECORD
				//------------------	
				}else if(SkatConstants.ACTION_DELETE.equals(action)){
					//Remove Topic
					//Could be delete OR set a remove status...(no physical delete)
					//TODO
				}

			}
			
	    	return successView;
		}
	}

	/**
	 * 
	 * Aux method to prevent an end-user for sending the declaration without having saved it first.
	 * The end-user must save a topic before issuing a further "send". Sort of a validation routine to ensure validity of all fields.
	 * 
	 * @param model
	 * @param record
	 * 
	 */
	private void adjustValidUpdateFlag(Map model, JsonSkatImportSpecificTopicRecord record){
		record.setValidUpdate(true);
		model.put(SkatConstants.DOMAIN_RECORD, record);
	}
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="skatimport_send.do")
	public ModelAndView doSkatImportSend(HttpSession session, HttpServletRequest request){
		
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SKAT_IMPORT);
		
		ModelAndView successView = new ModelAndView("redirect:skatimport.do?action=doFind&sign=" + appUser.getSkatSign());
		
		//---------------------------------
		//Crucial request parameters (Keys
		//---------------------------------
		String action = SkatConstants.ACTION_SEND;
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		String sign = request.getParameter("sign");
		
		//Action (doFetch, doUpdate, doCreate)
		logger.info("Action:" + action);
		Map model = new HashMap();
		
		if(appUser==null){
			return this.loginView;
		}else{
			
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			//OLD--->String BASE_URL = SkatImportUrlDataStore.SKAT_IMPORT_BASE_SEND_SPECIFIC_TOPIC_TO_SKAT_URL;
			String BASE_URL = SkatImportUrlDataStore.SKAT_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
			
			//-------------------
			//add URL-parameter 
			//-------------------
			String urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, avd, opd, sign, appUser);
			//there are only key parameters in doSend. No other topic (record) specific parameters from GUI or such
			String urlRequestParams = urlRequestParamsKeys;
			//for debugging purposes
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParams);
		    	//----------------------------------------------------------------------------
		    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
		    	//----------------------------------------------------------------------------
		    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			//Debug --> 
		    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
		    	//we must evaluate a return RPG code in order to know if the Update was OK or not
		    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicUpdate(rpgReturnPayload);
		    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
		    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
		    		//TODO ERROR HANDLING HERE... stay in the same page ?
		    	}else{
		    		//Update succefully done!
		    		logger.info("[INFO] Record successfully sent [changed status], OK ");
		    		//put domain objects
		    		//this.setDomainObjectsInView(session, model, jsonTdsImportSpecificTopicRecord);
		    		//TODO SUCCESS should stay at the same side or not? Right now we go to the list of topics
		    	}
			
				
		}
		return successView;
	}
	
	
	/**
	 * Prints a specific topic
	 * @param session
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="skatimport_edit_printTopic.do",  method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doSkatImportEditPrintTopic(HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView successView = new ModelAndView("redirect:skatimport.do?action=doFind&sign=" + appUser.getSkatSign());
		
		String method = "doTdsImportEditPrintTopic [RequestMapping-->skatimport_edit_printTopic.do]";
		logger.info("Method: " + method);
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			//-------------------------------------
			//get BASE URL = RPG-PROGRAM for PRINT
            //-------------------------------------
			String BASE_URL = SkatImportUrlDataStore.SKAT_IMPORT_BASE_PRINT_FOR_SPECIFIC_TOPIC_URL;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForPrint( avd, opd, appUser);
			//for debug purposes in GUI
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the Print (RPG program) here
	    	//--------------------------------------
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug --> 
	    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    //END of PRINT here and now
		    		
		}
		
		return successView;
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatimport_edit_printSkilleArkTopic.do",  method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doSkatImportEditPrintSkilleArkTopic(HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView successView = new ModelAndView("redirect:skatimport.do?action=doFind&sign=" + appUser.getSkatSign());
		
		String method = "doSadImportEditPrintSkilleArkTopic [RequestMapping-->skatimport_edit_printSkilleArkTopic.do]";
		logger.info("Method: " + method);
		String opd = request.getParameter("currentOpd");
		String avd = request.getParameter("currentAvd");
		String archiveType = request.getParameter("selectedType");
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			//-------------------------------------
			//get BASE URL = RPG-PROGRAM for PRINT
            //-------------------------------------
			String BASE_URL = SkatImportUrlDataStore.SKAT_IMPORT_BASE_PRINT_SKILLEARK_FOR_SPECIFIC_TOPIC_URL;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForPrintSkilleArk( avd, opd, appUser, archiveType);
			//for debug purposes in GUI
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the Print (RPG program) here
	    	//--------------------------------------
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug --> 
	    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    //END of PRINT here and now
	    	logger.info("Method PRINT END - " + method);	
		}
		
		return successView;
	}


	/**
	 * Copies one topic(angivelse) to a new one (clones the source topic)
	 * STEP 1: Copy by getting JSON with the new record (new opd, new avd, new sign)
	 * STEP 2: Fetch the record as if it was a selection of a topic in a list
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	
	@RequestMapping(value="skatimport_copyTopic.do", method={RequestMethod.POST} )
	public ModelAndView doCopyTopic( HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("skatimport_edit");
		ModelAndView fallbackOnErrorView = new ModelAndView("skatimport");
		
		JsonSkatImportTopicCopiedContainer jsonSkatImportTopicCopiedContainer = null;
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		String method = "doCopyTopic [RequestMapping-->skatimport_copyTopic.do]";
		logger.info("Method: " + method);
		Map model = new HashMap();
		
		//We must get all parameters from the enumeration since all have sequence counter number
		String action=null;
		String avd=null;
		String opd=null;
		String newAvd=null;
		String newSign=null;
		
		Enumeration requestParameters = request.getParameterNames();
	    while (requestParameters.hasMoreElements()) {
	        String element = (String) requestParameters.nextElement();
	        String value = request.getParameter(element);

	        if (element != null && value != null) {
	        		logger.info("####################################################");
        			logger.info("param Name : " + element + " value: " + value);
        			if(element.startsWith("originalAvd")){
        				avd = value;
        			}else if(element.startsWith("originalOpd")){
        				opd = value;
        			}else if(element.startsWith("newAvd")){
        				newAvd = value;
        			}else if(element.startsWith("newSign")){
        				newSign = value;
        			}else if(element.startsWith("action")){
        				action = value;
        			}
        		}
	    	}
	    
	
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			//--------------------
			//STEP 1: COPY record
			//--------------------
			logger.info("starting COPY record transaction...");
			String BASE_URL = SkatImportUrlDataStore.SKAT_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForCopy(avd, newAvd, newSign, opd, appUser);
			//for debug purposes in GUI
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE (RPG program) here
	    	//--------------------------------------
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
	    	//Debug --> 
	    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		jsonSkatImportTopicCopiedContainer = this.skatImportSpecificTopicService.getSkatImportTopicCopiedContainer(jsonPayload);
	    		if(jsonSkatImportTopicCopiedContainer!=null){
	    			//Check for errors
	    			if(jsonSkatImportTopicCopiedContainer.getErrMsg()!=null && !"".equals(jsonSkatImportTopicCopiedContainer.getErrMsg())){
	    				logger.error("[ERROR FATAL] errMsg containing: " + jsonSkatImportTopicCopiedContainer.getErrMsg());
	    				return fallbackOnErrorView;
	    			}
	    		}
	    	}else{
				logger.error("NO CONTENT on jsonPayload from URL... ??? <Null>");
				return loginView;
			}
		    
			
		    	//At this point we do now have a cloned record with its own data. The only thing left is to present it in edit mode
		    	//--------------------
			//STEP 2: FETCH record
			//--------------------
			logger.info("starting FETCH record transaction...");
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			BASE_URL = SkatImportUrlDataStore.SKAT_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
			//url params
			urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, jsonSkatImportTopicCopiedContainer.getNewavd(), jsonSkatImportTopicCopiedContainer.getNewopd(), jsonSkatImportTopicCopiedContainer.getNewsign(), appUser);
			//for debug purposes in GUI
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.info("URL: " + BASE_URL);
	    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the FETCH (RPG program) here
	    	//--------------------------------------
	    	jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug --> 
	    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		JsonSkatImportSpecificTopicContainer jsonSkatImportSpecificTopicContainer = this.skatImportSpecificTopicService.getSkatImportSpecificTopicContainer(jsonPayload);
	    		//add gui lists here
	    		
	    		this.setCodeDropDownMgr(appUser, model);
	    		this.setDomainObjectsInView(session, model, jsonSkatImportSpecificTopicContainer);
	    		successView.addObject(SkatConstants.DOMAIN_MODEL, model);
			//put the doUpdate action since we are preparing the record for an update (when saving)
			successView.addObject(SkatConstants.EDIT_ACTION_ON_TOPIC, SkatConstants.ACTION_UPDATE);
	    		
	    	}else{
				logger.error("NO CONTENT on jsonPayload from URL... ??? <Null>");
				return loginView;
			}
			
			
			return successView;
		}
		
	}
	
	
	/**
	 * 
	 * Copies one topic(Angivelse) to a new one, from (1) a Norsk Export or (2) Transport Uppdrag (order)
	 * STEP 1: Copy
	 * STEP 2: Fetch the record as if it was a selection of a topic in a list (Update mode)
	 * 
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	
	@RequestMapping(value="skatimport_doFetchTopicFromTransportUppdrag.do", method={RequestMethod.POST} )
	public ModelAndView doFetchTopicFromTransportUppdrag( HttpSession session, HttpServletRequest request){
		JsonSkatImportTopicCopiedFromTransportUppdragContainer jsonContainer = null;
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView successView = new ModelAndView("skatimport_edit");
		//fallback view (usually on errors)
		ModelAndView fallbackView = new ModelAndView("skatimport_edit");
		fallbackView.addObject("action", "doPrepareCreate");
		//this view is when the end user choose not to copy at all. He/She will start from scratch (empty form (header))
		ModelAndView cleanNewView = new ModelAndView("redirect:skatimport_edit.do?action=doPrepareCreate");
		
		String method = "[RequestMapping-->skatimport_doFetchTopicFromTransportUppdrag.do]";
		logger.info("Method: " + method);
		Map model = new HashMap();
		
		//We must get all parameters from the enumeration since all have sequence counter number
		String action=request.getParameter("actionGS");;
		String avd=request.getParameter("selectedAvd");
		String opd=request.getParameter("selectedOpd");
		String extRefNr=request.getParameter("selectedExtRefNr"); //Domino ref in Dachser DK AS
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			
			if( (extRefNr!=null && !"".equals(extRefNr)) || ( (opd!=null && !"".equals(opd)) && (avd!=null && !"".equals(avd))) ){
				//--------------------
				//STEP 1: CLONE record
				//--------------------
				logger.info("starting PROCESS record transaction...");
				String BASE_URL = SkatImportUrlDataStore.SKAT_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
				String urlRequestParamsKeys = this.getRequestUrlKeyParametersForCopyTopicFromTransportUppdrag(avd, opd, extRefNr, appUser);
				//for debug purposes in GUI
				session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
				
				logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
		    	//--------------------------------------
		    	//EXECUTE (RPG program) here
		    	//--------------------------------------
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		    	//Debug --> 
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		jsonPayload = jsonPayload.replaceAll("DKIH", "dkih");//AS must change so this step is removed!
		    		jsonContainer = this.skatImportSpecificTopicService.getSkatImportTopicCopiedFromTransportUppdragContainer(jsonPayload);
		    		if(jsonContainer!=null){
		    			//Check for errors
		    			if(jsonContainer.getErrMsg()!=null && !"".equals(jsonContainer.getErrMsg())){
		    				logger.info("[WARN] errMsg containing: " + jsonContainer.getErrMsg());
		    				logger.info("[WARN] redirecting to doPrepareCreate");
		    				//Send the error message to the redirect view.
		    				//request.setAttribute("errorMessageOnCopyFromTransportOppdrag", jsonContainer.getErrMsg());
		    				model.put(SkatConstants.ASPECT_ERROR_MESSAGE, jsonContainer.getErrMsg());
		    				model.put(SkatConstants.ASPECT_ERROR_META_INFO, "Vid kopiering av TransportUppdrag...");
		    				fallbackView.addObject(SkatConstants.DOMAIN_MODEL, model);
		    				
		    				return fallbackView;
		    			}
		    		}
		    	}else{
		    		logger.error("NO CONTENT on jsonPayload from URL... ??? <Null>");
					return loginView;
				}    
				
		    	//At this point we do now have a cloned record with its own data. The only thing left is to present it in edit mode
		    	//--------------------
				//STEP 2: FETCH record
				//--------------------
				logger.info("starting FETCH record transaction...");
				//---------------------------
				//get BASE URL = RPG-PROGRAM
	            //---------------------------
				BASE_URL = SkatImportUrlDataStore.SKAT_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_URL;
				//url params
				urlRequestParamsKeys = this.getRequestUrlKeyParameters(action, jsonContainer.getDkih_syav(), jsonContainer.getDkih_syop(), jsonContainer.getSign(), appUser);
				//for debug purposes in GUI
				session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
				
				logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
		    	//--------------------------------------
		    	//EXECUTE the FETCH (RPG program) here
		    	//--------------------------------------
		    	jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				//Debug --> 
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		JsonSkatImportSpecificTopicContainer jsonSpecificTopicContainer = this.skatImportSpecificTopicService.getSkatImportSpecificTopicContainer(jsonPayload);
	    			//populate gui
		    		this.setCodeDropDownMgr(appUser, model);	
		    		this.setDomainObjectsInView(session, model, jsonSpecificTopicContainer);
		    		successView.addObject(SkatConstants.DOMAIN_MODEL, model);
		    		//put the doUpdate action since we are preparing the record for an update (when saving)
		    		successView.addObject(SkatConstants.EDIT_ACTION_ON_TOPIC, SkatConstants.ACTION_UPDATE);	
		    	}else{
		    		logger.error("[ERROR fatal] NO CONTENT on jsonPayload from URL... ??? <Null>");
		    		return loginView;
		    	}
			}else{
				logger.warn("[INFO] Opdnr is NULL. Redirecting to: skatimport_edit.do?action=doPrepareCreate... ");
				//return new ModelAndView("redirect:tdsimport_edit.do?action=doPrepareCreate");
				return cleanNewView;
			}			
			return successView;
		}
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatimport_edit_printToldvaerdiDeklaration.do",  method={RequestMethod.GET, RequestMethod.POST })
	public ModelAndView doSkatImportEditPrintToldvaerdiDeklaration(HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		ModelAndView successView = new ModelAndView("redirect:skatimport.do?action=doFind&sign=" + appUser.getSkatSign());
		
		String method = "doSkatImportEditPrintToldvaerdiDeklaration [RequestMapping-->skatimport_edit_printToldvaerdiDeklaration.do]";
		logger.info("Method: " + method);
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		
		
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
		}else{
			//-------------------------------------
			//get BASE URL = RPG-PROGRAM for PRINT
            //-------------------------------------
			String BASE_URL = SkatImportUrlDataStore.SKAT_IMPORT_BASE_PRINT_FOR_TOLDVAERDI_DEKLARATION_URL;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForPrint( avd, opd, appUser);
			//for debug purposes in GUI
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
		    	//--------------------------------------
		    	//EXECUTE the Print (RPG program) here
		    	//--------------------------------------
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
			//Debug --> 
		    	logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    //END of PRINT here and now
		    		
		}
		
		return successView;
	}
	
	
	/**
	 * 
	 * Admin purposes. Updates a status in order to enable the administrator with this task
	 * @param session
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="skatimport_updateStatus.do")
	public ModelAndView doUpdateStatus(HttpSession session, HttpServletRequest request){
		
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SKAT_IMPORT);
		
		ModelAndView successView = new ModelAndView("redirect:skatimport.do?action=doFind&sign=" + appUser.getSkatSign());
		
		
		//---------------------------------
		//Crucial request parameters (Keys
		//---------------------------------
		String opd = request.getParameter("currentOpd");
		String avd = request.getParameter("currentAvd");
		String newStatus = request.getParameter("selectedStatus");
		
		Map model = new HashMap();
		
		if(appUser==null){
			return this.loginView;
		}else{
			
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL = SkatImportUrlDataStore.SKAT_IMPORT_BASE_UPDATE_STATUS_URL;
			
			//-------------------
			//add URL-parameter 
			//-------------------
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForUpdateStatus(avd, opd, newStatus, appUser);
			//there are only key parameters in doSend. No other topic (record) specific parameters from GUI or such
			String urlRequestParams = urlRequestParamsKeys;
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParams);
		    	//----------------------------------------------------------------------------
		    	//EXECUTE the UPDATE (RPG program) here (STEP [2] when creating a new record)
		    	//----------------------------------------------------------------------------
		    	String rpgReturnPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
			//Debug --> 
		    	logger.info("Checking errMsg in rpgReturnPayload" + rpgReturnPayload);
		    	//we must evaluate a return RPG code in order to know if the Update was OK or not
		    	rpgReturnResponseHandler.evaluateRpgResponseOnTopicUpdate(rpgReturnPayload);
		    	if(rpgReturnResponseHandler.getErrorMessage()!=null && !"".equals(rpgReturnResponseHandler.getErrorMessage())){
		    		rpgReturnResponseHandler.setErrorMessage("[ERROR] FATAL on UPDATE: " + rpgReturnResponseHandler.getErrorMessage());
		    		//TODO ERROR HANDLING HERE... stay in the same page ?
		    	}else{
		    		//Update succefully done!
		    		logger.info("[INFO] Status successfully updated [changed status], OK ");
		    		//put domain objects
		    		//this.setDomainObjectsInView(session, model, jsonTdsImportSpecificTopicRecord);
		    		//TODO SUCCESS should stay at the same side or not? Right now we go to the list of topics
		    	}
			
				
		}
		return successView;
	}
	
	
	/**
	 * Population of codes (GUI drop-downs)
	 * 
	 * @param appUser
	 * @param model
	 */
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
					 model,appUser,CodeDropDownMgr.CODE_001_ANGIVELSESARTER, null, null,null);
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
					 model,appUser,CodeDropDownMgr.CODE_006_TOLDSTED, null, null,null);
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
					 model,appUser,CodeDropDownMgr.CODE_008_COUNTRY, null, null,null);
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				     model,appUser,CodeDropDownMgr.CODE_020_CURRENCY, null, null,null);
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
					 model,appUser,CodeDropDownMgr.CODE_015_STATUS_KODER, null, null,null);
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
					 model,appUser,CodeDropDownMgr.CODE_018_TRANSPORTKODER_R25R26, null, null,null);
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				     model,appUser,CodeDropDownMgr.CODE_109_BETALING_FOR_TRANSPORT_RS29, null, null,null);
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				     model,appUser,CodeDropDownMgr.CODE_106_INCOTERMS, null, null,null);
			
			//drop down to print skilleark (must be Z type)
			this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString2(urlCgiProxyService, skatDropDownListPopulationService, 
					model, appUser, "Z");
	}
	
	
	
	
	/**
	 * Sum of all item lines header-required fields (total nr. of item lines, total nr of kolli, total amount (∑) of all item lines' amount)
	 * 
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private SkatImportSpecificTopicTotalItemLinesObject getRequiredSumsInItemLines(String avd, String opd, SystemaWebUser appUser){
		SkatImportSpecificTopicTotalItemLinesObject totalItemLinesObject = new SkatImportSpecificTopicTotalItemLinesObject();
		//-----------------------------------------------------
		//FETCH the ITEM LIST of existent ITEMs for this TOPIC
		//-----------------------------------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL_FETCH = SkatImportUrlDataStore.SKAT_IMPORT_BASE_FETCH_TOPIC_ITEMLIST_URL;
		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&avd=" + avd + "&opd=" + opd;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.warn("FETCH av item list... ");
	    	logger.warn("URL: " + BASE_URL_FETCH);
	    	logger.warn("URL PARAMS: " + urlRequestParamsKeys);
	    	//--------------------------------------
	    	//EXECUTE the FETCH (RPG program) here
	    	//--------------------------------------
		String jsonPayloadFetch = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
		//Debug --> 
	    	//logger.info(jsonPayloadFetch);
	    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	JsonSkatImportSpecificTopicItemContainer jsonSkatImportSpecificTopicItemContainer = this.getSkatImportSpecificTopicItemService().getSkatImportSpecificTopicItemContainer(jsonPayloadFetch);
	    	//now to the real logic
	    	int antalKolli = 0;
	    	int numberOfItemLines = 0;
	    	double totalAmount = 0.00D;
	    	if(jsonSkatImportSpecificTopicItemContainer!=null){
		    	for(JsonSkatImportSpecificTopicItemRecord record : jsonSkatImportSpecificTopicItemContainer.getOrderList()){
		    		numberOfItemLines++;
		    		
		    		if(record.getDkiv_313a()!=null && !"".equals(record.getDkiv_313a())){
		    			try{
		    				antalKolli += Integer.parseInt(record.getDkiv_313a());
		    			}catch(Exception e){
		    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
		    			}
		    		}
		    		if(record.getDkiv_313b()!=null && !"".equals(record.getDkiv_313b())){
		    			try{
		    				antalKolli += Integer.parseInt(record.getDkiv_313b());
		    			}catch(Exception e){
		    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
		    			}
		    		}
		    		if(record.getDkiv_313c()!=null && !"".equals(record.getDkiv_313c())){
		    			try{
		    				antalKolli += Integer.parseInt(record.getDkiv_313c());
		    			}catch(Exception e){
		    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
		    			}
		    		}
		    		if(record.getDkiv_313d()!=null && !"".equals(record.getDkiv_313d())){
		    			try{
		    				antalKolli += Integer.parseInt(record.getDkiv_313d());
		    			}catch(Exception e){
		    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
		    			}
		    		}
		    		if(record.getDkiv_313e()!=null && !"".equals(record.getDkiv_313e())){
		    			try{
		    				antalKolli += Integer.parseInt(record.getDkiv_313e());
		    			}catch(Exception e){
		    				logger.info("[ERROR] on ANTAL KOLLI CATCH");
		    			}
		    		}
		    		//============
		    		//Varans pris
		    		//============
		    		if(record.getDkiv_42()!=null && !"".equals(record.getDkiv_42())){
		    			try{
		    				totalAmount += Double.parseDouble(record.getDkiv_42());
		    			}catch(Exception e){
		    				logger.info("[ERROR] on VARANS PRIS CATCH");
		    			}
		    		}
		    		
		    		
		    	}
	    	}
	    	//This is to flag the fact that no antal kolli exist DESPITE the fact that there is 1 or more item lines
	    	//to be used in validation...
	    	if(numberOfItemLines>0 && antalKolli==0){
	    		antalKolli = -1;
	    	}
	    	totalItemLinesObject.setSumOfAntalItemLines(numberOfItemLines);
	    	totalItemLinesObject.setSumOfAntalKolliInItemLines(antalKolli);
	    	totalItemLinesObject.setSumTotalAmountItemLines(totalAmount);
	    	//DEBUG
	    	logger.info("AntalKolli: " + totalItemLinesObject.getSumOfAntalKolliInItemLines());
	    	logger.info("AntalItems: " + totalItemLinesObject.getSumOfAntalItemLines());
	    	logger.info("TotalAmountItems: " + totalItemLinesObject.getSumTotalAmountItemLines());
	    
	    	return totalItemLinesObject;
	}
	
	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @param type
	 * @return
	 */
	private String getRequestUrlKeyParametersForPrintSkilleArk(String avd, String opd, SystemaWebUser appUser, String type){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "type=" + type);
		
		return urlRequestParamsKeys.toString();	
	}
	
	/**
	 * Generates key seeds for an upcoming update (the generation of this keys creates also a new record ready to be updated)
	 * The method must be seen as STEP ONE in an upcoming update [same transaction].
	 * 
	 * @param session
	 * @param request
	 * @param user
	 * @param avd
	 * @param sign
	 * 
	 * @return 
	 */
	
	private JsonSkatImportSpecificTopicRecord createNewTopicHeaderKeySeeds(HttpSession session, HttpServletRequest request, SystemaWebUser user,
																		 String avd, String sign){
		RpgReturnResponseHandler rpgReturnResponseHandler = new RpgReturnResponseHandler();
		JsonSkatImportSpecificTopicRecord record = new JsonSkatImportSpecificTopicRecord();
		//---------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		String BASE_URL = SkatImportUrlDataStore.SKAT_IMPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL;
		
		//----------------------------------------------------------------------------------------------------------
		// STEP[PREPARE CREATION] --> generate new opd and tuid (if applicable) in order to be able to Add (Create)
		//----------------------------------------------------------------------------------------------------------
		logger.info("STEP[1] GET SEEDS and CREATE RECORD...");
		StringBuffer urlRequestParamsForSeed = new StringBuffer();
		urlRequestParamsForSeed.append("user=" + user.getUser());
		//for debug purposes in GUI
		session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL);
				
		urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + sign);
		urlRequestParamsForSeed.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + SkatConstants.MODE_ADD);
		logger.info("URL for SEED: " + BASE_URL);
		logger.info("PARAMS for SEED: " + urlRequestParamsForSeed.toString());
		
		//Get the counter from RPG (new opd Id)
		String rpgSeedNumberPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsForSeed.toString());
		
		// Map the JSON response to the new seeds (syop,tuid and ombud fields)
		// We are not using std JSON conversion since the RPGs strings are not the same. Should be the same as
		// the header fields. The RPG output should be changed in order to comply to the field specification...
		logger.info("### rpgSeedNumberPayload: " + rpgSeedNumberPayload);
		
		rpgReturnResponseHandler.getNewSeedsOpdAndTuidRequiredForCreateNewTopic(rpgSeedNumberPayload);
		logger.info("### syop from RPG PROGRAM: " + rpgReturnResponseHandler.getDkih_syop());
		
		//we must complete the GUI-json sypo and tuid with the value from a seedTuid here
		if(rpgReturnResponseHandler.getDkih_syop()!=null){
			record.setDkih_syop(rpgReturnResponseHandler.getDkih_syop().trim());
		}else{
			logger.info("[ERROR] No mandatory seeds (opd) were generated correctly)! look at std output log. [errMsg]" + rpgReturnResponseHandler.getErrorMessage());
			record = null;
		}
        
		return record;
	}
	
	/**
	 * log Errors in Aspects and Domain objects in order to render on GUI
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param jsonTdsImportSpecificTopicRecord
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonSkatImportSpecificTopicRecord record){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, record);
	}
	
	/**
	 * Print parameters
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersForPrint(String avd, String opd, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		return urlRequestParamsKeys.toString();	
	}
	
	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param sign
	 * 
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersForCopyTopicFromTransportUppdrag(String avd, String opd, String extRefNr, SystemaWebUser appUser){
		//user=OSCAR&avd=1&opd=53452&sign=CB&mode=GS 
		final String MODE = "GS";
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		if(opd!=null && !"".equals(opd)){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		}else if (extRefNr!=null && !"".equals(extRefNr)){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "dkih_xref=" + extRefNr);
		}
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + appUser.getSkatSign());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + MODE);
		
		return urlRequestParamsKeys.toString();	
	}

	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersForCopy(String avd, String newAvd, String newSign, String opd, SystemaWebUser appUser){
		//user=OSCAR&avd=1&newavd=2&opd=218&mode=C&newsign=OT 
		final String MODE_COPY = "C";
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "newavd=" + newAvd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + MODE_COPY);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "newsign=" + newSign);
		
		
		return urlRequestParamsKeys.toString();	
	}
	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param mtyp
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersForCompletionEventsOnTopic(String avd, String opd, String mtyp,  SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "svih_mtyp=" + mtyp);
		
		return urlRequestParamsKeys.toString();	
	}
	
	/**
	 * Change the status in a specific topic
	 * 
	 * @param avd
	 * @param opd
	 * @param newStatus
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersForUpdateStatus(String avd, String opd, String newStatus, SystemaWebUser appUser){
		
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		if(newStatus != null & !"".equals(newStatus)){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "status=" + newStatus);
		}
		return urlRequestParamsKeys.toString();	
	}
	/**
	 * Gets the key parameter string (such as avd, opd, user, mode)
	 * @param action
	 * @param avd
	 * @param opd
	 * @param sign
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(String action, String avd, String opd, String sign, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		
		if(SkatConstants.ACTION_FETCH.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
			
		}else if(SkatConstants.ACTION_UPDATE.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + SkatConstants.MODE_UPDATE);
			
		}else if(SkatConstants.ACTION_CREATE.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + sign);
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + SkatConstants.MODE_ADD);
			
		}else if(SkatConstants.ACTION_SEND.equals(action)){
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "mode=" + SkatConstants.MODE_SEND);
		}
		return urlRequestParamsKeys.toString();
	}
	
	
	/**
	 * 
	 * @param session
	 * @param model
	 * @param container
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonSkatImportSpecificTopicContainer container){
		//SET HEADER RECORDS  (from RPG)
		for (JsonSkatImportSpecificTopicRecord record : container.getOneorder()){
			model.put(SkatConstants.DOMAIN_RECORD, record);
			//put the header topic in session for the coming item lines
			session.setAttribute(SkatConstants.DOMAIN_RECORD_TOPIC_SKAT_IMPORT, record);
		}

	}
	
	/**
	 * 
	 * @param session
	 * @param model
	 * @param container
	 * @param totalItemLinesObject
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonSkatImportSpecificTopicContainer container, SkatImportSpecificTopicTotalItemLinesObject totalItemLinesObject){
		//SET HEADER RECORDS  (from RPG)
		for (JsonSkatImportSpecificTopicRecord record : container.getOneorder()){
			if(totalItemLinesObject!=null){
				record.setSumOfAntalKolliInItemLines(totalItemLinesObject.getSumOfAntalKolliInItemLines());
				record.setSumOfAntalItemLines(totalItemLinesObject.getSumOfAntalItemLines());
				record.setSumTotalAmountItemLines(totalItemLinesObject.getSumTotalAmountItemLines());
				//Invoice list
				record.setFakturaListTotValidCurrency(totalItemLinesObject.getFakturaListTotValidCurrency());
				record.setFakturaListTotSum(totalItemLinesObject.getFakturaListTotSum());
				record.setFakturaListTotKurs(totalItemLinesObject.getFakturaListTotKurs());
				
			}
			model.put(SkatConstants.DOMAIN_RECORD, record);
			//put the header topic in session for the coming item lines
			session.setAttribute(SkatConstants.DOMAIN_RECORD_TOPIC_SKAT_IMPORT, record);
		}
		
	}
	/**
	 * 
	 * @param session
	 * @param model
	 * @param record
	 * @param totalItemLinesObject
	 */
	private void setDomainObjectsInView(HttpSession session, Map model, JsonSkatImportSpecificTopicRecord record, SkatImportSpecificTopicTotalItemLinesObject totalItemLinesObject){
		//SET HEADER RECORDS  (from RPG)
		if(totalItemLinesObject!=null){
			record.setSumOfAntalKolliInItemLines(totalItemLinesObject.getSumOfAntalKolliInItemLines());
			record.setSumOfAntalItemLines(totalItemLinesObject.getSumOfAntalItemLines());
			record.setSumTotalAmountItemLines(totalItemLinesObject.getSumTotalAmountItemLines());
		}
		model.put(SkatConstants.DOMAIN_RECORD, record);
		//put the header topic in session for the coming item lines
		session.setAttribute(SkatConstants.DOMAIN_RECORD_TOPIC_SKAT_IMPORT, record);
	}
	/**
	 * 
	 * 
	 * @param model
	 * @param jsonTdsImportSpecificTopicRecord
	 */
	private void setDomainObjectsInView(Map model, JsonSkatImportSpecificTopicRecord record){
		//SET HEADER RECORDS  (from RPG)
		model.put(SkatConstants.DOMAIN_RECORD, record);
	}
	/**
	 * Sets aspects 
	 * Usually error objects, log objects, other non-domain related objects
	 * @param model
	 */
	
	private void setAspectsInView (Map model, RpgReturnResponseHandler rpgReturnResponseHandler){
		model.put(SkatConstants.ASPECT_ERROR_MESSAGE, rpgReturnResponseHandler.getErrorMessage());
		//extra error information
		StringBuffer errorMetaInformation = new StringBuffer();
		errorMetaInformation.append(rpgReturnResponseHandler.getUser());
		errorMetaInformation.append(rpgReturnResponseHandler.getDkih_syop());
		model.put(SkatConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
			
	
	/**
	 * 
	 * @param model
	 * @param appUser
	 */
	private void populateAvdelningHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser){
		//fill in html lists here
		String TDS_IMPORT_IE = "I"; //Import
		try{
			String BASE_URL = SkatUrlDataStore.SKAT_FETCH_AVDELNINGAR_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + TDS_IMPORT_IE);
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("AVD BASE_URL:" + BASE_URL);
			logger.info("AVD BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonSkatAvdelningContainer container = this.skatDropDownListPopulationService.getAvdelningContainer(url);
			List<JsonSkatAvdelningRecord> list = new ArrayList();
			for(JsonSkatAvdelningRecord record: container.getAvdelningar()){
				list.add(record);
			}
			model.put(SkatConstants.RESOURCE_MODEL_KEY_AVD_LIST, list);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}	
	/**
	 * 
	 * @param model
	 * @param appUser
	 */
	private void populateSignatureHtmlDropDownsFromJsonString(Map model, SystemaWebUser appUser){
		//fill in html lists here
		String TDS_EXPORT_IMPORT_IE = "F"; //Fortullning. The other one is: NCTS = ie=N 
		
		try{
			String BASE_URL = SkatUrlDataStore.SKAT_FETCH_SIGNATURE_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser());
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "ie=" + TDS_EXPORT_IMPORT_IE);
			//Now build the URL and send to the back end via the drop down service
			String url = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			logger.info("SIGN BASE_URL:" + BASE_URL);
			logger.info("SIGN BASE_PARAMS:" + urlRequestParamsKeys.toString());
			
			JsonSkatSignatureContainer container = this.skatDropDownListPopulationService.getSignatureContainer(url);
			List<JsonSkatSignatureRecord> list = new ArrayList();
			for(JsonSkatSignatureRecord record: container.getSignaturer()){
				list.add(record);
			}
			model.put(SkatConstants.RESOURCE_MODEL_KEY_SIGN_LIST, list);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * Tollværdi fields must be adjusted (radio buttons)
	 * 
	 * @param jsonSkatImportSpecificTopicRecord
	 */
	private void adjustTollverdiFieldsAfterBind(HttpServletRequest request, JsonSkatImportSpecificTopicRecord jsonSkatImportSpecificTopicRecord){
		final String YES = "Y";
		String t07a = request.getParameter("groupTold_7a");
		String t07b = request.getParameter("groupTold_7b");
		String t07c = request.getParameter("groupTold_7c");
		String t08a = request.getParameter("groupTold_8a");
		String t08b = request.getParameter("groupTold_8b");
		String t09a = request.getParameter("groupTold_9a");
		String t09b = request.getParameter("groupTold_9b");
		
		if(YES.equals(t07a)){ jsonSkatImportSpecificTopicRecord.setDkih_t07a(t07a); }
		if(YES.equals(t07b)){ jsonSkatImportSpecificTopicRecord.setDkih_t07b(t07b); }
		if(YES.equals(t07c)){ jsonSkatImportSpecificTopicRecord.setDkih_t07c(t07c); }
		if(YES.equals(t08a)){ jsonSkatImportSpecificTopicRecord.setDkih_t08a(t08a); }
		if(YES.equals(t08b)){ jsonSkatImportSpecificTopicRecord.setDkih_t08b(t08b); }
		if(YES.equals(t09a)){ jsonSkatImportSpecificTopicRecord.setDkih_t09a(t09a); }
		if(YES.equals(t09b)){ jsonSkatImportSpecificTopicRecord.setDkih_t09b(t09b); }
		
	}
	/**
	 * 
	 * Private person
	 * @param request
	 * @param topicRecord
	 * 
	 */
	
	private void adjustForPrivatePerson(HttpServletRequest request, JsonSkatImportSpecificTopicRecord topicRecord){
		String PRIVATE_PERSON_CVR_CONSTANT = "DK09999981";
		
		if(PRIVATE_PERSON_CVR_CONSTANT.equals(topicRecord.getDkih_08a())){ 
			//clean up since this must be empty on EDIFACT
			topicRecord.setDkih_02f("");
			topicRecord.setDkih_08f("");
		}
		
	}

	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private JsonSkatImportSpecificTopicFaktTotalRecord getInvoiceTotalFromInvoices(String avd, String opd, SystemaWebUser appUser){
		//--------------------------
		//get BASE URL = RPG-PROGRAM
        //---------------------------
		JsonSkatImportSpecificTopicFaktTotalRecord returnRecord = null;
		
		String BASE_URL_FETCH = SkatImportUrlDataStore.SKAT_IMPORT_BASE_FETCH_SPECIFIC_TOPIC_FAKT_TOTAL_URL;
		String urlRequestParamsKeys = "user=" + appUser.getUser() + "&avd=" + avd + "&opd=" + opd;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		logger.info("FETCH av item list... ");
    	logger.info("URL: " + BASE_URL_FETCH);
    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
    	//--------------------------------------
    	//EXECUTE the FETCH (RPG program) here
    	//--------------------------------------
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL_FETCH, urlRequestParamsKeys);
		//Debug --> 
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	JsonSkatImportSpecificTopicFaktTotalContainer container = this.skatImportSpecificTopicService.getSkatImportSpecificTopicFaktTotalContainer(jsonPayload);
    	if(container!=null){
	    	for(JsonSkatImportSpecificTopicFaktTotalRecord record : container.getInvTot()){
				 returnRecord = record;
	    	}
    	}
		
		return returnRecord;
	}
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("skatImportSpecificTopicItemService")
	private SkatImportSpecificTopicItemService skatImportSpecificTopicItemService;
	@Autowired
	@Required
	public void setSkatImportSpecificTopicItemService (SkatImportSpecificTopicItemService value){ this.skatImportSpecificTopicItemService = value; }
	public SkatImportSpecificTopicItemService getSkatImportSpecificTopicItemService(){ return this.skatImportSpecificTopicItemService; }
	
	
	@Qualifier ("skatDropDownListPopulationService")
	private SkatDropDownListPopulationService skatDropDownListPopulationService;
	@Autowired
	public void setSkatDropDownListPopulationService (SkatDropDownListPopulationService value){ this.skatDropDownListPopulationService=value; }
	public SkatDropDownListPopulationService getSkatDropDownListPopulationService(){return this.skatDropDownListPopulationService;}
	
	
	@Qualifier ("skatImportDropDownListPopulationService")
	private SkatImportDropDownListPopulationService skatImportDropDownListPopulationService;
	@Autowired
	public void setSkatImportDropDownListPopulationService (SkatImportDropDownListPopulationService value){ this.skatImportDropDownListPopulationService=value; }
	public SkatImportDropDownListPopulationService getSkatImportDropDownListPopulationService(){return this.skatImportDropDownListPopulationService;}
	
	
	@Qualifier ("skatImportSpecificTopicService")
	private SkatImportSpecificTopicService skatImportSpecificTopicService;
	@Autowired
	@Required
	public void setSkatImportSpecificTopicService (SkatImportSpecificTopicService value){ this.skatImportSpecificTopicService = value; }
	public SkatImportSpecificTopicService getSkatImportSpecificTopicService(){ return this.skatImportSpecificTopicService; }
	
	
}

