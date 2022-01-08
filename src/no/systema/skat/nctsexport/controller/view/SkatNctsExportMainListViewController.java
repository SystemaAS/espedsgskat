package no.systema.skat.nctsexport.controller.view;

import java.util.*;

import org.slf4j.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//application imports
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.AppConstants;
import no.systema.skat.nctsexport.model.jsonjackson.topic.JsonSkatNctsExportTopicListRecord;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicListRecord;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicListRecord;
import no.systema.skat.util.SkatConstants;


/**
 * Working controller for the Main list non JSP-pages
 * The controller will manage all export functionality to different view formats such as:
 * 
 * (1) Excel, PDF, other are implemented here
 * 
 * 
 * 
 * @author oscardelatorre
 * @date Apr, 2020
 * 
 */

@Controller
public class SkatNctsExportMainListViewController {
	private static final Logger logger = LoggerFactory.getLogger(SkatNctsExportMainListViewController.class.getName());
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	
	/**
	 * 
	 * @param user
	 * @param result
	 * @param request
	 * @return
	 * 
	 */
	@RequestMapping(value="skatNctsExportMainListExcelView.do", method={RequestMethod.GET})
	public ModelAndView getItemListExcelView(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		//this name is the one configured in /WEB-INF/views.xml
		final String EXCEL_VIEW = "skatNctsExportMainListExcelView";
		
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		List<JsonSkatNctsExportTopicListRecord> mainList = null;
		
        //--> with browser dialogbox: response.setHeader ("Content-disposition", "attachment; filename=\"edifactPayload.txt\"");
        response.setHeader ("Content-disposition", "filename=\"" + EXCEL_VIEW + ".xls\"");

		if(appUser==null){
			return this.loginView;
		}else{
			mainList = (List)session.getAttribute(session.getId() + SkatConstants.SESSION_LIST);
		}	
		
		return new ModelAndView(EXCEL_VIEW, SkatConstants.MAIN_TOPIC_LIST, mainList);
	}
}

