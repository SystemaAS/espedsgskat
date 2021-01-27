package no.systema.skat.z.maintenance.skatexport.controller.ajax;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
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
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDkekContainer;
import no.systema.skat.z.maintenance.main.model.jsonjackson.dbtable.JsonMaintDkekRecord;
import no.systema.skat.z.maintenance.main.service.MaintDkekService;
import no.systema.skat.z.maintenance.main.url.store.MaintenanceUrlDataStore;



/**
 * Maintenance Ajax Controller 
 * 
 * @author oscardelatorre
 * @date Jan, 2021
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSkatExportAjaxHandlerController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = Logger.getLogger(MaintSkatExportAjaxHandlerController.class.getName());
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getSpecificRecord_dkek.do", method={RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody List<JsonMaintDkekRecord> getRecordDkek
	  	(@RequestParam String applicationUser, @RequestParam String dkek_knr, @RequestParam String dkek_vnr) {
		final String METHOD = "[DEBUG] getSpecificRecord_dkek";
		logger.info(METHOD + " Inside...");
		List<JsonMaintDkekRecord> result = new ArrayList();
	 	//get table
    	result = (List)this.fetchListDkek(applicationUser, dkek_knr, dkek_vnr);
    	logger.warn(result.toString());
    	return result;
	
	}
	
	/**
	 * 
	 * @param applicationUser
	 * @param kundnr
	 * @return
	 */
	private Collection<JsonMaintDkekRecord> fetchListDkek(String applicationUser, String kundnr, String varenr){
		Collection<JsonMaintDkekRecord> list = new ArrayList();
		
    	if(StringUtils.isNotEmpty(kundnr) && StringUtils.isNotEmpty(varenr)){
			String BASE_URL = MaintenanceUrlDataStore.MAINTENANCE_BASE_DKEK_GET_LIST_URL;
			StringBuffer urlRequestParams = new StringBuffer();
			//mandatory params
			urlRequestParams.append("user="+ applicationUser);
			urlRequestParams.append("&dkek_knr=" + kundnr);
			urlRequestParams.append("&dkek_vnr=" + varenr);
			
			
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
		        		logger.warn("DKEK_VNR:" + record.getDkek_vnr());
		        	}
		        }
	    	}
    	}
    	return list;
    	
	}
	
	

	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("maintDkekService")
	private MaintDkekService maintDkekService;
	@Autowired
	public void setMaintDkekService (MaintDkekService value){ this.maintDkekService = value; }
	public MaintDkekService getMaintDkekService(){ return this.maintDkekService; }
	
	
}

