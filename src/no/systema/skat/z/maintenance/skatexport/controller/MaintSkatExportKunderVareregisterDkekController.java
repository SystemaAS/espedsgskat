package no.systema.skat.z.maintenance.skatexport.controller;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
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
import no.systema.main.model.SystemaWebUser;
import no.systema.skat.service.html.dropdown.SkatDropDownListPopulationService;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportSpecificTopicRecord;
import no.systema.skat.skatexport.util.manager.CodeDropDownMgr;
import no.systema.skat.z.maintenance.main.mapper.url.request.UrlRequestParameterMapper;
import no.systema.skat.z.maintenance.main.model.MaintenanceMainListObject;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDkekContainer;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDkekRecord;

import no.systema.skat.z.maintenance.main.service.MaintDkekService;
import no.systema.skat.z.maintenance.main.url.store.MaintenanceUrlDataStore;
import no.systema.skat.z.maintenance.main.util.SkatMaintenanceConstants;
import no.systema.skat.z.maintenance.main.validator.MaintSkatMainDkekValidator;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfContainer;
import no.systema.z.main.maintenance.model.jsonjackson.dbtable.JsonMaintMainCundfRecord;
import no.systema.z.main.maintenance.service.MaintMainCundfService;
import no.systema.z.main.maintenance.url.store.MaintenanceMainUrlDataStore;
import no.systema.z.main.maintenance.util.MainMaintenanceConstants;


/**
 * SKAT Maintenance Export Kunder vareregister Controller 
 * 
 * @author oscardelatorre
 * @date Jan, 2021
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSkatExportKunderVareregisterDkekController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LoggerFactory.getLogger(MaintSkatExportKunderVareregisterDkekController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private UrlRequestParameterMapper urlRequestParameterMapper = new UrlRequestParameterMapper();
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatmaintenanceexport_dkek.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doSkatImportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("skatmaintenanceexport_dkek");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		String kundnr = request.getParameter("search_dkek_knr");
		
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SKAT_MAINTENANCE_EXPORT);
			session.setAttribute(SkatMaintenanceConstants.ACTIVE_URL_RPG_SKAT_MAINTENANCE, SkatMaintenanceConstants.ACTIVE_URL_RPG_INITVALUE); 
		
			//lists
			Collection list = this.fetchList(appUser.getUser(), kundnr);
			//drop downs populated from back-end
	    	this.setCodeDropDownMgr(appUser, model);

	    	//set domain objects
	    	if(StringUtils.isNotEmpty(kundnr)){
	    		JsonMaintMainCundfRecord cundf = this.getCundfRecord(appUser,kundnr);
	    		if(cundf!=null){
	    			model.put("search_knavn", cundf.getKnavn());
	    			model.put("search_dkek_knr", kundnr);
	    		}
			}
			model.put("list", list);
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
	@RequestMapping(value="skatmaintenanceexport_dkek_edit.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doSadMaintImportEdit(@ModelAttribute ("record") JsonMaintDkekRecord recordToValidate, BindingResult bindingResult, HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("skatmaintenanceexport_dkek");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		String updateId = request.getParameter("updateId");
		String action = request.getParameter("action");
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			//Move on
			MaintSkatMainDkekValidator validator = new MaintSkatMainDkekValidator();
			if(SkatMaintenanceConstants.ACTION_DELETE.equals(action)){
				validator.validateDelete(recordToValidate, bindingResult);
			}else{
				validator.validate(recordToValidate, bindingResult);
			}
			if(bindingResult.hasErrors()){
				//ERRORS
				logger.info("[ERROR Validation] Record does not validate)");
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
				int dmlRetval = 0;
				//UPDATE
				if (SkatMaintenanceConstants.ACTION_UPDATE.equals(action) ){
					//adjust for update
					if(StringUtils.isNotEmpty(recordToValidate.getDkek_402a())){
						recordToValidate.setDkek_401a(recordToValidate.getDkek_402a().substring(0,1));
						recordToValidate.setDkek_402a(recordToValidate.getDkek_402a().substring(1));
					}
					
					if(StringUtils.isNotEmpty(updateId)){
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
					model.put(SkatMaintenanceConstants.ASPECT_ERROR_MESSAGE, errMsg.toString());
					model.put(SkatMaintenanceConstants.DOMAIN_RECORD, recordToValidate);
				}
				
			}
			//------------
			//FETCH table
			//------------
			Collection<JsonMaintDkekRecord> list = this.fetchList(appUser.getUser(), recordToValidate.getDkek_knr());
			//drop downs populated from back-end
	    	this.setCodeDropDownMgr(appUser, model);
	    	//set domain objects
	    	if(recordToValidate!=null && StringUtils.isNotEmpty(recordToValidate.getDkek_knr())){
	    		JsonMaintMainCundfRecord cundf = this.getCundfRecord(appUser,recordToValidate.getDkek_knr());
		    	if(cundf!=null){
		    		model.put("search_knavn", cundf.getKnavn());
		    		model.put("search_dkek_knr", cundf.getKundnr());
		    	}else{
		    		model.put("search_knavn", "");
		    		model.put("search_dkek_knr", recordToValidate.getDkek_knr());
		    	}
	    	}
	    	model.put(SkatMaintenanceConstants.DOMAIN_LIST, list);
			successView.addObject(SkatMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	//get cundf for customer's data
	private JsonMaintMainCundfRecord getCundfRecord (SystemaWebUser appUser, String knr){
		JsonMaintMainCundfRecord result = null;
		
		//prepare the access CGI with RPG back-end
		if(StringUtils.isNotEmpty(knr)){
			
			String BASE_URL = MaintenanceMainUrlDataStore.MAINTENANCE_MAIN_BASE_SYCUNDFR_GET_LIST_URL;
			StringBuffer urlRequestParamsKeys = new StringBuffer();
			urlRequestParamsKeys.append("user=" + appUser.getUser() + "&kundnr=" + knr);
			if(StringUtils.isNotEmpty(appUser.getCompanyCode())){
				urlRequestParamsKeys.append( "&firma=" + appUser.getCompanyCode() );
			}
			logger.info("URL: " + BASE_URL);
			logger.info("PARAMS: " + urlRequestParamsKeys.toString());
			logger.info(Calendar.getInstance().getTime() +  " CGI-start timestamp");
			String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys.toString());
			//debugger
			logger.debug(jsonDebugger.debugJsonPayloadWithLog4J(jsonPayload));
			logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
	    	if(jsonPayload!=null){
	    		jsonPayload = jsonPayload.replaceFirst("Customerlist", "customerlist");
	    		JsonMaintMainCundfContainer container = this.maintMainCundfService.getList(jsonPayload);
	    		if(container!=null){
	    			Collection<JsonMaintMainCundfRecord> list = container.getList();
	    			for(JsonMaintMainCundfRecord  record : list){
	    				result = record;
	    			}
	    		}
	    	}
		}
		return result;
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	private Collection<JsonMaintDkekRecord> fetchList(String applicationUser, String kundnr){
		Collection<JsonMaintDkekRecord> list = new ArrayList();
		
    	if(StringUtils.isNotEmpty(kundnr)){
			String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_DKEK_GET_LIST_URL;
			StringBuffer urlRequestParams = new StringBuffer();
			//mandatory params
			urlRequestParams.append("user="+ applicationUser);
			urlRequestParams.append("&dkek_knr=" + kundnr);
			
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
	    	logger.warn("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
	    	logger.warn("URL PARAMS: " + urlRequestParams);
	    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
	    	//extract
	    	if(jsonPayload!=null){
				//lists
	    		JsonMaintDkekContainer container = this.maintDkekService.getList(jsonPayload);
		        if(container!=null){
		        	list = container.getList();
		        	for(JsonMaintDkekRecord record : list){
		        		//DEBUG logger.info("DKEK_VNR:" + record.getDkek_vnr());
		        	}
		        }
	    	}
    	}
    	return list;
    	
	}
	
	
	/**
	 * 
	 * @param applicationUser
	 * @param record
	 * @param mode
	 * @param errMsg
	 * @return
	 */
	private int updateRecord(String applicationUser, JsonMaintDkekRecord record, String mode, StringBuffer errMsg){
		int retval = 0;
		
		String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_DKEK_DML_UPDATE_URL;
		String urlRequestParamsKeys = "user=" + applicationUser + "&mode=" + mode;
		String urlRequestParams = this.urlRequestParameterMapper.getUrlParameterValidString((record));
		//put the final valid param. string
		urlRequestParams = urlRequestParamsKeys + urlRequestParams;
		
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.warn("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.warn("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	
    	//extract
    	if(jsonPayload!=null){
			//lists
    		JsonMaintDkekContainer container = this.maintDkekService.doUpdate(jsonPayload);
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
	
	private void setCodeDropDownMgr(SystemaWebUser appUser, Map model){
		final String TRANSPORT_VEJTRANSPORT_3 = "3";
    	//general code population
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_008_COUNTRY, null, null);
	
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_017_TRANSPORTDOK_SUMMARISKA_R40, null, TRANSPORT_VEJTRANSPORT_3);
	
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_022_SUPP_ENHEDER, null, null);
	
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_110_EMBALLAGE_R31, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_112_PROCEDURE_R37, null, null);
	
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_113_CERTIFIKAT_R44_2, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_114_VAB_CERTIFIKAT_R44_3, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_115_FN_FARLIG_GODS_R44_4, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_116_TRANSPORTDOK_TYPE_R44_5_1, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				model,appUser,CodeDropDownMgr.CODE_124_SUPPL_ENHEDER_YM, null, null);
		
		this.codeDropDownMgr.populateCodesHtmlDropDownsFromJsonString(this.urlCgiProxyService, this.skatDropDownListPopulationService,
				 model,appUser,CodeDropDownMgr.CODE_127_STATUS_KODER, null, null);
}

	
	//SERVICES
	@Autowired private UrlCgiProxyService urlCgiProxyService;
	
	@Autowired private MaintDkekService maintDkekService;
	
	@Autowired private SkatDropDownListPopulationService skatDropDownListPopulationService;
	
	@Autowired private MaintMainCundfService maintMainCundfService;
	
	

}

