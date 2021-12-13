package no.systema.skat.z.maintenance.felles.controller;

import java.util.*;

import org.apache.logging.log4j.*;
import org.springframework.validation.BindingResult;
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
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;

//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.main.util.StringManager;
import no.systema.main.model.SystemaWebUser;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainEdiiContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainEdiiRecord;
import no.systema.z.main.maintenance.service.MaintMainEdiiService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.skat.z.maintenance.main.mapper.url.request.UrlRequestParameterMapper;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktfiContainer;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktfiRecord;
import no.systema.skat.z.maintenance.main.service.MaintDktfiService;
import no.systema.skat.z.maintenance.main.service.html.dropdown.SkatMaintMainDropDownListPopulationService;
import no.systema.skat.z.maintenance.main.url.store.MaintenanceUrlDataStore;
import no.systema.skat.z.maintenance.main.util.SkatMaintenanceConstants;
import no.systema.skat.z.maintenance.felles.validator.MaintSkatFellesDkt055rValidator;


/**
 *  SKAT Maintenance Felles Dkt055 Controller 
 * 
 * @author oscardelatorre
 * @date Mar 14, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSkatFellesDkt055rController {
	
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LogManager.getLogger(MaintSkatFellesDkt055rController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private String SKAT_IMPORT_SUPPL_ENHETER_KODE = "022"; //We use the ones in Skat-Import
	private final String ID_INTERNAL = "I";
	private final String ID_EXTERNAL = "E";
	private final String ID_INFO_SENDER_TEST = "senderIdTest";
	private final String ID_INFO_SENDER_PROD = "senderIdProd";
	private final String ID_INFO_RECEIVER_TEST = "receiverIdTest";
	private final String ID_INFO_RECEIVER_PROD = "receiverIdProd";
	
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatmaintenancefelles_dkt055r.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doSadMaintImportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("skatmaintenancefelles_dkt055r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//SearchFilterSadExportTopicList searchFilter = new SearchFilterSadExportTopicList();
		String dbTable = request.getParameter("id");
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			//get table
	    	List<JsonMaintDktfiRecord> list = new ArrayList();
	    	list = this.fetchList(appUser.getUser(), model);
	    	//drop downs
	    	//List codeList022 = this.fetchListKoder(appUser.getUser(), this.SKAT_IMPORT_SUPPL_ENHETER_KODE);
	    	//List codeListToldsatstype = this.skatMaintMainDropDownListPopulationService.getToldsatstypeList();
	    	
	    	//set domain objets
	    	model.put("dbTable", dbTable);
	    	
	    	model.put(SkatMaintenanceConstants.DOMAIN_LIST, list);
	    	successView.addObject(SkatMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param bindingResult
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatmaintenancefelles_dkt055r_edit.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doSkatmaintenanceFellesEdit(@ModelAttribute ("record") JsonMaintDktfiRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("skatmaintenancefelles_dkt055r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		boolean isValidRecord = true;
		String dbTable = request.getParameter("id");
		String updateId = request.getParameter("updateId");
		String action = request.getParameter("action");
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			
			//Move on
			MaintSkatFellesDkt055rValidator validator = new MaintSkatFellesDkt055rValidator();
			if(SkatMaintenanceConstants.ACTION_DELETE.equals(action)){
				validator.validateDelete(recordToValidate, bindingResult);
			}else{
				this.validateEdiiChildren (recordToValidate, appUser.getUser());
				validator.validate(recordToValidate, bindingResult);
			}
			
			if(bindingResult.hasErrors()){
				//ERRORS
				logger.info("[ERROR Validation] Record does not validate)");
				model.put("dbTable", dbTable);
				isValidRecord = false;
				if(updateId!=null && !"".equals(updateId)){
					//meaning bounced in an Update and not a Create new
					model.put("updateId", updateId);
					
				}
				logger.info(recordToValidate.getDktf_0004t());
				model.put(SkatMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
			}else{
				
				//------------
				//UPDATE table
				//------------
				StringBuffer errMsg = new StringBuffer();
				int dmlRetval = 0;
				//UPDATE
				if (SkatMaintenanceConstants.ACTION_UPDATE.equals(action) ){
					if(updateId!=null && !"".equals(updateId)){
						//update
						logger.info(SkatMaintenanceConstants.ACTION_UPDATE);
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, SkatMaintenanceConstants.MODE_UPDATE, errMsg);
						
					//CREATE
					}else{
						//create new
						logger.info(SkatMaintenanceConstants.ACTION_CREATE);
						dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, SkatMaintenanceConstants.MODE_ADD, errMsg);
					}
				}else if(SkatMaintenanceConstants.ACTION_DELETE.equals(action) ){
					//delete
					logger.info(SkatMaintenanceConstants.ACTION_DELETE);
					dmlRetval = this.updateRecord(appUser.getUser(), recordToValidate, SkatMaintenanceConstants.MODE_DELETE, errMsg);
				}
				//check for Update errors
				if( dmlRetval < 0){
					logger.info("[ERROR Validation] Record does not validate)");
					model.put("dbTable", dbTable);
					model.put(SkatMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					model.put(SkatMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				}
				
			}
			//------------
			//FETCH table
			//------------
			if(SkatMaintenanceConstants.ACTION_DELETE.equals(action) || SkatMaintenanceConstants.ACTION_UPDATE.equals(action) ){
				//TODO
			}
			//fetch the newly updated record if valid
			List<JsonMaintDktfiRecord> list = null;
			if(isValidRecord){
				list = this.fetchList(appUser.getUser(), model);
			}
			//drop downs TODO
			logger.info(recordToValidate.getDktf_0004t());
	    	//set domain objets
	    	model.put("dbTable", dbTable);
	    	//model.put(SkatMaintenanceConstants.DOMAIN_LIST, list);
			successView.addObject(SkatMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @param startDato
	 * @return
	 */
	private List<JsonMaintDktfiRecord> fetchList(String applicationUser, Map model){
		
		String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_DKT055R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user="+ applicationUser);

		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintDktfiRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintDktfiContainer container = this.maintDktfiService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getDtoList();
	        	for(JsonMaintDktfiRecord record : list){
	        		//put record since this is the only one in whole table
	        		model.put(SkatMaintenanceConstants.DOMAIN_RECORD, record);
	        		model.put("updateId", record.getDktf_0004t());
	        	}
	        }
    	}
    	return list;
    	
	}
	
	
	
	
	/**
	 * UPDATE/CREATE/DELETE
	 * 
	 * @param applicationUser
	 * @param record
	 * @param mode
	 * @return
	 */
	private int updateRecord(String applicationUser, JsonMaintDktfiRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_DKT055R_DML_UPDATE_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&mode=" + mode;
		String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString((record));
		//put the final valid param. string
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	
    	//extract
    	if(jsonPayload!=null){
			//lists
    		JsonMaintDktfiContainer container = this.maintDktfiService.doUpdate(jsonPayload);
	        if(container!=null){
	        	if(container.getErrMsg()!=null && !"".equals(container.getErrMsg())){
	        		if(container.getErrMsg().toUpperCase().startsWith("ERROR")){
	        			errMsg.append(container.getErrMsg());
	        			retval = SkatMaintenanceConstants.ERROR_CODE;
	        		}
	        	}
	        }
    	}
    	return retval;
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param applicationUser
	 */
	private void validateEdiiChildren(JsonMaintDktfiRecord recordToValidate, String applicationUser){
		this.getEdiiList(recordToValidate, applicationUser, recordToValidate.getDktf_0004t(), this.ID_INTERNAL, ID_INFO_SENDER_TEST);
		this.getEdiiList(recordToValidate, applicationUser, recordToValidate.getDktf_0010t(), this.ID_EXTERNAL, ID_INFO_RECEIVER_TEST);
		this.getEdiiList(recordToValidate, applicationUser, recordToValidate.getDktf_0004p(), this.ID_INTERNAL, ID_INFO_SENDER_PROD);
		this.getEdiiList(recordToValidate, applicationUser, recordToValidate.getDktf_0010p(), this.ID_EXTERNAL, ID_INFO_RECEIVER_PROD);
	}
	
	/**
	 * 
	 * @param recordToValidate
	 * @param applicationUser
	 * @param id
	 * @param offset
	 * @param unbParty
	 */
	private void getEdiiList(JsonMaintDktfiRecord recordToValidate, String applicationUser, String id, String offset, String unbParty){
		
		Collection<JsonMaintMainEdiiRecord> list = new ArrayList<JsonMaintMainEdiiRecord>();
		//prepare the access CGI with RPG back-end
		
		String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYEDIIR_GET_LIST_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&inid=" + id;
		logger.info("URL: " + BASE_URL);
		logger.info("PARAMS: " + urlRequestParamsKeys);
		logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
		String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
		//debugger
		logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
		logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		if(jsonPayload!=null){
			JsonMaintMainEdiiContainer container = this.maintMainEdiiService.getList(jsonPayload);
			if(container!=null){
				list = container.getList();
				for(JsonMaintMainEdiiRecord  result : list){
					if(offset.equals(result.getInex()) ){
						if(ID_INFO_SENDER_TEST.equals(unbParty)){ recordToValidate.setValidSenderIdTest(true); }
						if(ID_INFO_SENDER_PROD.equals(unbParty)){ recordToValidate.setValidSenderIdProd(true); }
						if(ID_INFO_RECEIVER_TEST.equals(unbParty)){ recordToValidate.setValidReceiverIdTest(true); }
						if(ID_INFO_RECEIVER_PROD.equals(unbParty)){ recordToValidate.setValidReceiverIdProd(true); }
					}
				}
			}
		}
	}
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("maintDktfiService")
	private MaintDktfiService maintDktfiService;
	@Autowired
	@Required
	public void setMaintDktfiService (MaintDktfiService value){ this.maintDktfiService = value; }
	public MaintDktfiService getMaintDktfiService(){ return this.maintDktfiService; }
	
	
	@Qualifier ("skatMaintMainDropDownListPopulationService")
	private SkatMaintMainDropDownListPopulationService skatMaintMainDropDownListPopulationService;
	@Autowired
	@Required
	public void setSkatMaintMainDropDownListPopulationService (SkatMaintMainDropDownListPopulationService value){ this.skatMaintMainDropDownListPopulationService = value; }
	public SkatMaintMainDropDownListPopulationService getSkatMaintMainDropDownListPopulationService(){ return this.skatMaintMainDropDownListPopulationService; }
	
	
	@Qualifier 
	private MaintMainEdiiService maintMainEdiiService;
	@Autowired
	@Required	
	public void setMaintMainEdiiService(MaintMainEdiiService value){this.maintMainEdiiService = value;}
	public MaintMainEdiiService getMaintMainEdiiService(){ return this.maintMainEdiiService; }
	
	
	
	
}

