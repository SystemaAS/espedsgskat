package no.systema.skat.z.maintenance.skatnctsexport.controller;

import java.util.*;

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

import no.systema.skat.z.maintenance.main.model.MaintenanceMainListObject;
import no.systema.skat.z.maintenance.main.util.SkatMaintenanceConstants;

/**
 * SKAT Maintenance NCTS Import Topic Controller 
 * 
 * @author oscardelatorre
 * @date Sep 2, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class MaintSkatNctsExportController {
	private static final JsonDebugger jsonDebugger = new JsonDebugger();
	private static final Logger logger = LoggerFactory.getLogger(MaintSkatNctsExportController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatmaintenancenctsexport.do", method=RequestMethod.GET)
	public ModelAndView doSkatImportList(HttpSession session, HttpServletRequest request){
		ModelAndView successView = new ModelAndView("skatmaintenancenctsexport");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//SearchFilterSadExportTopicList searchFilter = new SearchFilterSadExportTopicList();
		
		Map model = new HashMap();
		if(appUser==null){
			return this.loginView;
		}else{
			appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SKAT_MAINTENANCE_NCTS_EXPORT);
			session.setAttribute(SkatMaintenanceConstants.ACTIVE_URL_RPG_SKAT_MAINTENANCE, SkatMaintenanceConstants.ACTIVE_URL_RPG_INITVALUE); 
		
			//lists
			List list = this.populateMaintenanceMainList();
			//init the rest
			model.put("list", list);
			successView.addObject(SkatMaintenanceConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	/**
	 * 
	 * @return
	 */
	private List<MaintenanceMainListObject> populateMaintenanceMainList(){
		List<MaintenanceMainListObject> listObject = new ArrayList<MaintenanceMainListObject>();
		MaintenanceMainListObject object = new  MaintenanceMainListObject();
	
		object.setId("1");
		object.setSubject("Vedligeholdelse af koder");
		object.setCode("SKAT_KODER");
		//object.setText("DKX001R");
		object.setDbTable("DKXKODF");
		object.setStatus("G");
		object.setPgm("kodergate");
		listObject.add(object);
		//
		object = new  MaintenanceMainListObject();
		object.setId("2");
		object.setSubject("Vedligeholdelse af garanti");
		object.setCode("SKAT_GARANTI");
		object.setText("DKXGH / ...");
		object.setDbTable("DKXGH");
		object.setStatus("G");
		object.setPgm("dkx030r");
		listObject.add(object);
		
		return listObject;
	}
	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	

}

