package no.systema.skat.z.maintenance.skatnctsexport.controller;

import java.util.*;

import org.apache.logging.log4j.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import javawebparts.core.org.apache.commons.lang.StringUtils;

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
import no.systema.main.util.NumberFormatterLocaleAware;
import no.systema.main.model.SystemaWebUser;

import no.systema.skat.z.maintenance.main.mapper.url.request.UrlRequestParameterMapper;
import no.systema.skat.z.maintenance.skatnctsexport.model.jsonjackson.dbtable.JsonMaintDkxghContainer;
import no.systema.skat.z.maintenance.skatnctsexport.model.jsonjackson.dbtable.JsonMaintDkxghRecord;
import no.systema.skat.z.maintenance.skatnctsexport.model.jsonjackson.dbtable.JsonMaintDkxhContainer;
import no.systema.skat.z.maintenance.skatnctsexport.model.jsonjackson.dbtable.JsonMaintDkxhRecord;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktvkContainer;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktvkRecord;
import no.systema.skat.z.maintenance.skatnctsexport.service.MaintDkxghService;
import no.systema.skat.z.maintenance.skatnctsexport.validator.MaintSkatExportDkx030rValidator;
import no.systema.skat.z.maintenance.skatnctsexport.url.store.MaintenanceNctsExportUrlDataStore;
import no.systema.skat.z.maintenance.main.service.MaintDktvkService;
import no.systema.skat.z.maintenance.main.url.store.MaintenanceUrlDataStore;
import no.systema.skat.z.maintenance.main.util.SkatMaintenanceConstants;


/**
 *  SKAT Maintenance NCTS Export Dkx030r Controller 
 * 
 * @author oscardelatorre
 * @date Sep 2, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSkatNctsExportDkx030rController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LogManager.getLogger(MaintSkatNctsExportDkx030rController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	private NumberFormatterLocaleAware formatter = new NumberFormatterLocaleAware();
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatmaintenancenctsexport_dkx030r.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doSadMaintImportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("skatmaintenancenctsexport_dkx030r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//SearchFilterSadExportTopicList searchFilter = new SearchFilterSadExportTopicList();
		String dbTable = request.getParameter("id");
		String id = request.getParameter("searchGaranti");
		
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			//get table
	    	List<JsonMaintDkxghRecord> list = new ArrayList();
	    	list = this.fetchList(appUser.getUser(), id);
	    	//set domain objets
	    	model.put("currencyList", this.populateDropDownCurrency(appUser.getUser()) );
	    	model.put("dbTable", dbTable);
	    	model.put("searchGaranti", id);
	    	model.put(SkatMaintenanceConstants.DOMAIN_LIST, list);
	    	successView.addObject(SkatMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	@RequestMapping(value="skatmaintenancenctsexport_dkx030r_fbrukt.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doTdsMaintNctsExportGarantiListNotFriGaranti(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("skatmaintenancenctsexport_dkx030r_fbrukt");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//SearchFilterSadExportTopicList searchFilter = new SearchFilterSadExportTopicList();
		//String dbTable = request.getParameter("id");
		//String id = request.getParameter("searchGaranti");
		String idNr = request.getParameter("searchGaranti");
		Map model = new HashMap();
		model.put("searchGaranti", idNr);
		
		if(appUser==null){
			return this.loginView;
		}else{
			//get table
	    	List<JsonMaintDkxhRecord> list = new ArrayList();
	    	list = this.fetchListReservedGuaranties(appUser.getUser(), idNr );
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
	@RequestMapping(value="skatmaintenancenctsexport_dkx030r_edit.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doSadMaintImportEdit(@ModelAttribute ("record") JsonMaintDkxghRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("skatmaintenancenctsexport_dkx030r");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		String dbTable = request.getParameter("id");
		String updateId = request.getParameter("updateId");
		String action = request.getParameter("action");
		boolean validationError = false;
		int dmlRetval = 0;
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			//Move on
			MaintSkatExportDkx030rValidator validator = new MaintSkatExportDkx030rValidator();
			if(SkatMaintenanceConstants.ACTION_DELETE.equals(action)){
				validator.validateDelete(recordToValidate, bindingResult);
			}else{
				validator.validate(recordToValidate, bindingResult);
			}
			if(bindingResult.hasErrors()){
				//ERRORS
				validationError = true;
				logger.info("[ERROR Validation] Record does not validate)");
				model.put("dbTable", dbTable);
				if(updateId!=null && !"".equals(updateId)){
					//meaning bounced in an Update and not a Create new
					model.put("updateId", updateId);
				}
				model.put(SkatMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
			}else{
				
				//------------
				//UPDATE table
				//------------
				StringBuffer errMsg = new StringBuffer();
				dmlRetval = 0;
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
				if(!validationError && dmlRetval >= 0){
					//this in order to present the complete list to the end user after a DML-operation
					recordToValidate.setTggnr(null);
				}
			}
			List<JsonMaintDkxghRecord> list = this.fetchList(appUser.getUser(), recordToValidate.getTggnr());
	    	//set domain objets
			model.put("currencyList", this.populateDropDownCurrency(appUser.getUser()) );
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
	@RequestMapping(value="skatmaintenancenctsexport_dkx030r_fbrukt_edit.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doTdsMaintNctsExportGarantiRelaseEdit(@ModelAttribute ("record") JsonMaintDkxhRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("skatmaintenancenctsexport_dkx030r_fbrukt");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		int dmlRetval = 0;
		
		String idNr = request.getParameter("searchGaranti");
		Map model = new HashMap();
		model.put("searchGaranti", idNr);
		
		if(appUser==null){
			return this.loginView;
		}else{
			logger.warn("A");
			if(StringUtils.isNotEmpty(recordToValidate.getThavd()) && StringUtils.isNotEmpty(recordToValidate.getThtdn()) 
					&& StringUtils.isNotEmpty(recordToValidate.getThsg()) && StringUtils.isNotEmpty(recordToValidate.getThgbl()) ){
				logger.warn("B");
				//------------
				//UPDATE table
				//------------
				StringBuffer errMsg = new StringBuffer();
				dmlRetval = 0;
				//update
				logger.warn(SkatMaintenanceConstants.ACTION_UPDATE);
				dmlRetval = this.releaseGuarantee(appUser.getUser(), recordToValidate, errMsg);
				//check for Update errors
				if( dmlRetval < 0){
					logger.error("[ERROR Validation] Record does not validate)");
					//model.put("dbTable", dbTable);
					model.put(SkatMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					model.put(SkatMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
					
				}else {
					//at this point we have the guarantee released
					//now we go on with adjusting the mother guarantee by substracting the newly released amount
					double adjustedAmount = this.getCalculatedRest(appUser.getUser(), recordToValidate);
					logger.warn(adjustedAmount);
					if(adjustedAmount>=0) {
						JsonMaintDkxghRecord svxghDto = new JsonMaintDkxghRecord();
						svxghDto.setTggnr(recordToValidate.getThgft1());
						svxghDto.setTggblb(String.valueOf(adjustedAmount));
						//now update the brukt guarantee amount 
						dmlRetval = this.adjustGuarantee(appUser.getUser(), svxghDto, errMsg);
						if( dmlRetval < 0){
							logger.error("[ERROR Validation] Record does not validate)");
							//model.put("dbTable", dbTable);
							model.put(SkatMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
							model.put(SkatMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
						}
					}
				}
			}
				
		}
		//------------
		//FETCH table
		//------------
		//get table
    	List<JsonMaintDkxhRecord> list = new ArrayList();
    	list = this.fetchListReservedGuaranties(appUser.getUser(), idNr);
    	model.put(SkatMaintenanceConstants.DOMAIN_LIST, list);
    	successView.addObject(SkatMaintenanceConstants.DOMAIN_MODEL , model);
		
    	return successView;
	
	}
	
	/**
	 * 	
	 * @param applicationUser
	 * @param recordToValidate
	 * @return
	 */
    private double getCalculatedRest(String applicationUser, JsonMaintDkxhRecord recordToValidate) {
    
    	double result = -1.00D;
    	String BASE_URL = MaintenanceNctsExportUrlDataStore.MAINTENANCE_BASE_DKX030R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&tggnr=" + recordToValidate.getThgft1() + "&om=1" ; //OneMatch ...
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintDkxghRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintDkxghContainer container = this.maintDkxghService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	
	        	for(JsonMaintDkxghRecord record: list){
	        		logger.warn(record.getTggblb());
	        		logger.warn(recordToValidate.getThgbl());
	        		if(StringUtils.isNotEmpty(record.getTggblb()) && StringUtils.isNotEmpty(recordToValidate.getThgbl())) {
	        			String a = record.getTggblb().replace(",", ".");
	        			logger.warn("a:" + a);
        				if(Double.valueOf(a)>0) {
		        			String b = recordToValidate.getThgbl().replace(",", ".");
		        			logger.warn("b:" + b);
		        			
		        			double tmp = Double.valueOf(a) - Double.valueOf(b);
		        			if(tmp>=0) {
		        				result = tmp;
		        				break;
		        			}
        				}
	        			
	        		}
	        	}
	        	result = formatter.getDouble(result, 2);
	        	logger.warn("result:" + result);
	        }
    	}
    	
    	return result;
    }
	
	/**
	 * 
	 * @param applicationUser
	 * @param record
	 * @param errMsg
	 * @return
	 */
	private int releaseGuarantee(String applicationUser, JsonMaintDkxhRecord record, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceNctsExportUrlDataStore.MAINTENANCE_BASE_DKX030R_DML_RELEASE_GUARANTEE_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&thsg=" + record.getThsg() + "&thavd=" + record.getThavd() + "&thtdn=" + record.getThtdn();
		//String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString((record));
		//put the final valid param. string
		//urlRequestParams = urlRequestParamsKeys + urlRequestParams;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.warn("URL PARAMS: " + urlRequestParamsKeys);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
    	
    	//extract
    	if(jsonPayload!=null){
			//lists
    		JsonMaintDkxhContainer container = this.maintDkxghService.doReleaseGuarantee(jsonPayload);
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
	 * Adjust the amount
	 * @param applicationUser
	 * @param record
	 * @param errMsg
	 * @return
	 */
	private int adjustGuarantee(String applicationUser, JsonMaintDkxghRecord record, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceNctsExportUrlDataStore.MAINTENANCE_BASE_DKX030R_DML_ADJUST_GUARANTEE_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&tggnr=" + record.getTggnr() + "&tggblb=" + record.getTggblb();
		//String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString((record));
		//put the final valid param. string
		//urlRequestParams = urlRequestParamsKeys + urlRequestParams;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.warn("URL PARAMS: " + urlRequestParamsKeys);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
    	
    	//extract
    	if(jsonPayload!=null){
			//lists
    		JsonMaintDkxghContainer container = this.maintDkxghService.getList(jsonPayload);
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
	 * @param applicationUser
	 * @param idNr
	 * @return
	 */
	private List<JsonMaintDkxhRecord> fetchListReservedGuaranties(String applicationUser, String idNr){
		
		String BASE_URL = MaintenanceNctsExportUrlDataStore.MAINTENANCE_BASE_DKX030R_FBRUKT_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user="+ applicationUser);
		if(StringUtils.isNotEmpty(idNr)) {
			urlRequestParams.append("&id="+ idNr);
		}
		
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintDkxhRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintDkxhContainer container = this.maintDkxghService.getListReservedGuaranty(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintDkxhRecord record : list){
	        		//logger.info("THGBL:" + record.getThgbl());
	        	}
	        }
    	}
    	return list;
    	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @param levenr
	 * @return
	 */
	private List<JsonMaintDkxghRecord> fetchList(String applicationUser, String id){
		
		String BASE_URL = MaintenanceNctsExportUrlDataStore.MAINTENANCE_BASE_DKX030R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user="+ applicationUser);
		if(id!=null && !"".equals(id)){
			urlRequestParams.append("&tggnr=" + id);
		}else{
			//no further search. Just return an empty list
			//return new ArrayList();
		}
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintDkxghRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintDkxghContainer container = this.maintDkxghService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintDkxghRecord record : list){
	        		//logger.info("TGGNR:" + record.getTggnr());
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
	private int updateRecord(String applicationUser, JsonMaintDkxghRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceNctsExportUrlDataStore.MAINTENANCE_BASE_DKX030R_DML_UPDATE_URL;
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
    		JsonMaintDkxghContainer container = this.maintDkxghService.doUpdate(jsonPayload);
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
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	private List<JsonMaintDktvkRecord> populateDropDownCurrency(String applicationUser){
		
		String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_DKT057R_GET_LIST_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user="+ applicationUser);
		
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
    	//extract
    	List<JsonMaintDktvkRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintDktvkContainer container = this.maintDktvkService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintDktvkRecord record : list){
	        		//logger.info("your text");
	        	}
	        }
    	}
    	return list;
    	
	}
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("maintDkxghService")
	private MaintDkxghService maintDkxghService;
	@Autowired
	@Required
	public void setMaintDkxghService (MaintDkxghService value){ this.maintDkxghService = value; }
	public MaintDkxghService getMaintDkxghService(){ return this.maintDkxghService; }
	
	
	@Qualifier ("maintDktvkService")
	private MaintDktvkService maintDktvkService;
	@Autowired
	@Required
	public void setMaintDktvkService (MaintDktvkService value){ this.maintDktvkService = value; }
	public MaintDktvkService getMaintDktvkService(){ return this.maintDktvkService; }
}

