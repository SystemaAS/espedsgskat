package no.systema.skat.z.maintenance.main.controller.ajax;

import java.util.*;

import org.slf4j.*;
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
import no.systema.skat.z.maintenance.main.service.MaintDktkdService;
import no.systema.skat.z.maintenance.main.url.store.MaintenanceUrlDataStore;



/**
 * Maintenance Ajax Controller 
 * 
 * @author oscardelatorre
 * @date Mar 2, 2017
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSkatMainAjaxHandlerController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LoggerFactory.getLogger(MaintSkatMainAjaxHandlerController.class.getName());
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_dkg210d.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintDktkdRecord> getRecordDkg210
	  	(@RequestParam String applicationUser, @RequestParam String dkkd_typ, @RequestParam String dkkd_kd) {
		final String METHOD = "[DEBUG] getSpecificRecord_dkg210d";
		logger.info(METHOD + " Inside...");
		List<JsonMaintDktkdRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListDkg210d(applicationUser, dkkd_typ, dkkd_kd);
    	
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param dkkd_typ
	 * @param dkkd_kd
	 * @return
	 */
	private Collection<JsonMaintDktkdRecord> fetchListDkg210d(String applicationUser, String dkkd_typ, String dkkd_kd){
		
		String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_DKG210R_GET_LIST_URL;
		String urlRequestParams = "user=" + applicationUser + "&dkkd_typ=" + dkkd_typ + "&dkkd_kd=" + dkkd_kd;
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + jsonDebugger.getBASE_URL_NoHostName(BASE_URL));
    	logger.info("URL PARAMS: " + urlRequestParams);
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);
    	//extract
    	List<JsonMaintDktkdRecord> list = new ArrayList();
    	if(jsonPayload!=null){
			//lists
    		JsonMaintDktkdContainer container = this.maintDktkdService.getList(jsonPayload);
	        if(container!=null){
	        	list = (List)container.getList();
	        	for(JsonMaintDktkdRecord record: list){
	        		//logger.info(record.getDkkd_kd());
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
	
	
	@Qualifier ("maintDktkdService")
	private MaintDktkdService maintDktkdService;
	@Autowired
	@Required
	public void setMaintDktkdService (MaintDktkdService value){ this.maintDktkdService = value; }
	public MaintDktkdService getMaintDktkdService(){ return this.maintDktkdService; }
	
	
}

