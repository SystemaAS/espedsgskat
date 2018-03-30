package no.systema.skat.z.maintenance.skatnctsexport.controller.ajax;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.JsonDebugger;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktkdContainer;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDktkdRecord;
import no.systema.skat.z.maintenance.main.url.store.MaintenanceUrlDataStore;
import no.systema.skat.z.maintenance.skatncts.service.MaintDkxkodfService;
import no.systema.skat.z.maintenance.skatnctsexport.model.jsonjackson.dbtable.JsonMaintDkxghContainer;
import no.systema.skat.z.maintenance.skatnctsexport.model.jsonjackson.dbtable.JsonMaintDkxghRecord;
import no.systema.skat.z.maintenance.skatnctsexport.service.MaintDkxghService;
import no.systema.skat.z.maintenance.skatnctsexport.url.store.MaintenanceNctsExportUrlDataStore;
import no.systema.skat.z.maintenance.skatncts.model.jsonjackson.dbtable.JsonMaintDkxkodfContainer;
import no.systema.skat.z.maintenance.skatncts.model.jsonjackson.dbtable.JsonMaintDkxkodfRecord;


/**
 * Maintenance Ajax Controller 
 * 
 * @author oscardelatorre
 * @date Sep 05, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSkatNctsExportAjaxHandlerController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSkatNctsExportAjaxHandlerController.class.getName());
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_dkx030r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintDkxghRecord> getRecordDkx030
	  	(@RequestParam String applicationUser, @RequestParam String id) {
		final String METHOD = "[DEBUG] getRecordDkx030r";
		logger.info(METHOD + " Inside...");
		List<JsonMaintDkxghRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListDkx030(applicationUser, id);
    	
    	return result;
	
	}
	
	
	@RequestMapping(value="getSpecificRecord_dkx001r.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintDkxkodfRecord> getRecordDkx001r
	  	(@RequestParam String applicationUser, @RequestParam String tkunik, @RequestParam String tkkode ) {
		final String METHOD = "[DEBUG] getRecordDkx001r";
		logger.info(METHOD + " Inside...");
		List<JsonMaintDkxkodfRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListDkx001r(applicationUser, tkunik, tkkode);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param id
	 * @return
	 */
	private Collection<JsonMaintDkxghRecord> fetchListDkx030(String applicationUser, String id){
		
		String BASE_URL = MaintenanceNctsExportUrlDataStore.MAINTENANCE_BASE_DKX030R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&tggnr=" + id + "&om=1" ; //OneMatch ...
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintDkxghRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintDkxghContainer container = this.maintDkxghService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintDkxghRecord record: list){
	        		//logger.info(record.getTggnr());
	        	}
	        }
    	}
    	
    	return list;
    	
	}
	/**
	 * 
	 * @param applicationUser
	 * @param tkunik
	 * @param tkkode
	 * @return
	 */
	private Collection<JsonMaintDkxkodfRecord> fetchListDkx001r(String applicationUser, String tkunik, String tkkode){
		
		String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_DKX001R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&tkunik=" + tkunik + "&tkkode=" + tkkode ; 
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintDkxkodfRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintDkxkodfContainer container = this.maintDkxkodfService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintDkxkodfRecord record: list){
	        		//logger.info(record.getTggnr());
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
	
	
	
	@Qualifier ("maintDkxkodfService")
	private MaintDkxkodfService maintDkxkodfService;
	@Autowired
	@Required
	public void setMaintDkxkodfService (MaintDkxkodfService value){ this.maintDkxkodfService = value; }
	public MaintDkxkodfService getMaintDkxkodfService(){ return this.maintDkxkodfService; }
	
	
}

