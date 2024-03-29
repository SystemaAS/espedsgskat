package no.systema.skat.nctsimport.controller;

import java.util.*;
import java.io.*;


import org.slf4j.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.WebDataBinder;


//application imports
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.util.AppConstants;
import no.systema.main.util.io.PayloadContentFlusher;
import no.systema.main.util.io.StackTraceUtil;
import no.systema.main.validator.IPAddressValidator;
import no.systema.main.model.SystemaWebUser;
import no.systema.skat.nctsimport.model.jsonjackson.topic.logging.JsonSkatNctsImportSpecificTopicLoggingContainer;
import no.systema.skat.nctsimport.model.jsonjackson.topic.logging.JsonSkatNctsImportSpecificTopicLoggingLargeTextContainer;
import no.systema.skat.nctsimport.model.jsonjackson.topic.logging.JsonSkatNctsImportSpecificTopicLoggingLargeTextRecord;
import no.systema.skat.nctsimport.model.jsonjackson.topic.logging.JsonSkatNctsImportSpecificTopicLoggingRecord;


import no.systema.skat.nctsimport.service.SkatNctsImportSpecificTopicService;
import no.systema.skat.nctsimport.url.store.SkatNctsImportUrlDataStore;
import no.systema.skat.nctsimport.util.RpgReturnResponseHandler;
import no.systema.skat.util.SkatConstants;
import no.systema.skat.util.manager.ArchiveGoogleCloudManager;


/**
 * SKAT-NCTS Import Topic Controller 
 * 
 * @author oscardelatorre
 * @date Apr 24, 2014
 * 
 */

@Controller
@Scope("session")
public class SkatNctsImportHeaderLoggingController {
	
	private static final Logger logger = LoggerFactory.getLogger(SkatNctsImportHeaderLoggingController.class.getName());
	private PayloadContentFlusher payloadContentFlusher = new PayloadContentFlusher();
	private StackTraceUtil stackTraceUtil = new StackTraceUtil();
	private ModelAndView loginView = new ModelAndView("redirect:logout.do");
	private ApplicationContext context;
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
		//binder.setValidator(new TdsExportValidator()); //it must have spring form tags in the html otherwise = meaningless
    }
	
	
	/**
	 * Renders the GUI view
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatnctsimport_logging.do",  method= RequestMethod.GET)
	public ModelAndView doShowList(HttpSession session, HttpServletRequest request){
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		//String messageFromContext = this.context.getMessage("user.label",new Object[0], request.getLocale());
		ModelAndView successView = new ModelAndView("skatnctsimport_logging");
		logger.info("Method: doShowList [RequestMapping-->skatnctsimport_logging.do]");
		
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		logger.info("Avd:" + avd);
		logger.info("Opd:" + opd);
		this.setDomainObjectsInView(request, model);
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL = SkatNctsImportUrlDataStore.NCTS_IMPORT_BASE_LOG_LIST_FOR_SPECIFIC_TOPIC_URL;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParameters(avd, opd, appUser);
			//for debug purposes in GUI
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
		    	//--------------------------------------
		    	//EXECUTE the FETCH (RPG program) here
		    	//--------------------------------------
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				//Debug --> 
		    	logger.info(" --> jsonPayload:" + jsonPayload);
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		JsonSkatNctsImportSpecificTopicLoggingContainer container = this.skatNctsImportSpecificTopicService.getNctsImportSpecificTopicLoggingContainer(jsonPayload);
		    		//adjust to google cloud if needed
		    		container = new ArchiveGoogleCloudManager().adjustUrl(appUser, container);
		    		
		    		//add domain objects here
		    		this.setDomainObjectsInView(model, container);
		    		this.setDomainObjectsInView(request, model);
		    		
		    		successView.addObject(SkatConstants.DOMAIN_MODEL, model);
				successView.addObject(SkatConstants.DOMAIN_LIST,container.getLogg());
		    		
		    	}else{
				logger.error("NO CONTENT on jsonPayload from URL... ??? <Null>");
				return loginView;
			}

	   		
		}
		
		return successView;
	}
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatncts_import_renderEdifact.do", method={ RequestMethod.GET })
	public ModelAndView doSkatNctsImportRenderEdifact(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		logger.info("Inside doSkatNctsImportRenderEdifact...");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SIGN_PKI);
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, SkatConstants.ACTIVE_URL_RPG_INITVALUE); 
			String filePath = request.getParameter("fp");
			
			if(filePath!=null && !"".equals(filePath)){
                String absoluteFilePath = filePath;
            		if(!filePath.startsWith("/")){
            			absoluteFilePath = "/" + absoluteFilePath;
            		}
            
        		if(!new IPAddressValidator().isValidAbsoluteFilePathFor_RenderFile(absoluteFilePath)){
                	return (null);
                }else{
	                response.setContentType(AppConstants.HTML_CONTENTTYPE_TEXTHTML);
	                //--> with browser dialogbox: response.setHeader ("Content-disposition", "attachment; filename=\"edifactPayload.txt\"");
	                response.setHeader ("Content-disposition", "filename=\"edifactPayload.txt\"");
	                
	                logger.info("Start flushing file payload...");
	                //send the file output to the ServletOutputStream
	                try{
	                		payloadContentFlusher.flushServletOutput(response, absoluteFilePath);
	                		
	                	
	                }catch (Exception e){
	                		e.printStackTrace();
	                		payloadContentFlusher.flushServletOutput(response, this.stackTraceUtil.printStackTrace(e).getBytes());
	                }
                }
            }
			//this to present the output in an independent window
            return(null);
			
		}
			
	}	
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatncts_import_renderXml.do", method={ RequestMethod.GET })
	public ModelAndView doSkatNctsImportRenderXml(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		logger.info("Inside doSkatNctsImportRenderXml...");
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		
		if(appUser==null){
			return this.loginView;
			
		}else{
			
			//appUser.setActiveMenu(SystemaWebUser.ACTIVE_MENU_SIGN_PKI);
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, SkatConstants.ACTIVE_URL_RPG_INITVALUE); 
			String filePath = request.getParameter("fp");
			
			if(filePath!=null && !"".equals(filePath)){
                String absoluteFilePath = filePath;
            		if(!filePath.startsWith("/")){
            			absoluteFilePath = "/" + absoluteFilePath;
            		}
            
        		if(!new IPAddressValidator().isValidAbsoluteFilePathFor_RenderFile(absoluteFilePath)){
                	return (null);
                }else{
	                response.setContentType(AppConstants.HTML_CONTENTTYPE_TEXTXML);
	                //--> with browser dialogbox: response.setHeader ("Content-disposition", "attachment; filename=\"edifactPayload.txt\"");
	                response.setHeader ("Content-disposition", "filename=\"payload.txt\"");
	                
	                logger.info("Start flushing file payload...");
	                //send the file output to the ServletOutputStream
	                try{
	                		payloadContentFlusher.flushServletOutput(response, absoluteFilePath);
	                		
	                	
	                }catch (Exception e){
	                		e.printStackTrace();
	                		payloadContentFlusher.flushServletOutput(response, this.stackTraceUtil.printStackTrace(e).getBytes());
	                }
                }
            }
			//this to present the output in an independent window
            return(null);
			
		}
			
	}	
	
	
	/**
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatncts_import_renderLargeText.do",  method= RequestMethod.GET)
	public ModelAndView doSkatNctsImportRenderLargeText(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		Map model = new HashMap();
		SystemaWebUser appUser = (SystemaWebUser)session.getAttribute(AppConstants.SYSTEMA_WEB_USER_KEY);
		logger.info("Inside doSkatNctsImportRenderLargeText...");
		
		//Message number
		String fmn = request.getParameter("fmn");
		//check user (should be in session already)
		if(appUser==null){
			return loginView;
			
		}else{
			//---------------------------
			//get BASE URL = RPG-PROGRAM
            //---------------------------
			String BASE_URL = SkatNctsImportUrlDataStore.NCTS_IMPORT_BASE_LOG_LARGE_TEXT_FOR_SPECIFIC_TOPIC_URL;
			//url params
			String urlRequestParamsKeys = this.getRequestUrlKeyParametersForLargeText(fmn, appUser);
			//for debug purposes in GUI
			session.setAttribute(SkatConstants.ACTIVE_URL_RPG_SKAT, BASE_URL  + "==>params: " + urlRequestParamsKeys.toString()); 
			
			logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
		    	logger.info("URL: " + BASE_URL);
		    	logger.info("URL PARAMS: " + urlRequestParamsKeys);
		    	//--------------------------------------
		    	//EXECUTE the FETCH (RPG program) here
		    	//--------------------------------------
		    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParamsKeys);
				//Debug --> 
		    	logger.info(" --> jsonPayload:" + jsonPayload);
		    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
		    	if(jsonPayload!=null){
		    		
		    		String largeText = this.setLargeTextInDomainObject(jsonPayload);
		    		if(largeText!=null && !"".equals(largeText)){
		                
		                response.setContentType(AppConstants.HTML_CONTENTTYPE_TEXTHTML);
		                //--> with browser dialogbox: response.setHeader ("Content-disposition", "attachment; filename=\"edifactPayload.txt\"");
		                response.setHeader ("Content-disposition", "filename=\"largeLogContent.txt\"");
		                
		                try{
		                		//send the output to the ServletOutputStream
			                payloadContentFlusher.flushServletOutput(response, largeText.getBytes());
			                
		                	}catch (Exception e){
		                		e.printStackTrace();
		                }
		            }
		    	}else{
				logger.error("NO CONTENT on jsonPayload from URL... ??? <Null>");
				return loginView;
			}

	   		
		}
		return(null);
	}

	/**
	 * 
	 * Gets the large text of a specific log record
	 * @jsonPayload
	 * @return
	 */
	private String setLargeTextInDomainObject(String jsonPayload){
		StringBuffer sb = new StringBuffer();
		JsonSkatNctsImportSpecificTopicLoggingLargeTextContainer container = this.skatNctsImportSpecificTopicService.getNctsImportSpecificTopicLoggingLargeTextContainer(jsonPayload);
		logger.info(container.getUser());
		//list of objects
		Collection<JsonSkatNctsImportSpecificTopicLoggingLargeTextRecord> list = container.getLoggtext();
		
		//Debug
		for(JsonSkatNctsImportSpecificTopicLoggingLargeTextRecord record : list){
			sb.append(record.getF0078a());
			sb.append("<br>"); //since the output will be in HTML
			sb.append(record.getF0078b());
			sb.append("<br>"); 
			sb.append(record.getF0078c());
			sb.append("<br>"); 
			sb.append(record.getF0078d());
			sb.append("<br>"); 
			sb.append(record.getF0078e());
			sb.append("<br><br>");
			
		}//add domain objects here
		
		return sb.toString();
		
	}
	
	/**
	 * 
	 * @param avd
	 * @param opd
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParameters(String avd, String opd, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		final String NCTS_IMPORT_TYPE = "6"; 
		//String action = request.getParameter("action");
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + avd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + opd);
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "typ=" + NCTS_IMPORT_TYPE);
		
		
		return urlRequestParamsKeys.toString();
	}
	/**
	 * 
	 * @param fmn
	 * @param appUser
	 * @return
	 */
	private String getRequestUrlKeyParametersForLargeText(String fmn, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fmn=" + fmn);
		
		return urlRequestParamsKeys.toString();
	}
	/**
	 * log Errors in Aspects and Domain objects in order to render on GUI
	 * @param model
	 * @param rpgReturnResponseHandler
	 * @param record
	 */
	private void setFatalError(Map model, RpgReturnResponseHandler rpgReturnResponseHandler, JsonSkatNctsImportSpecificTopicLoggingRecord record){
		logger.info(rpgReturnResponseHandler.getErrorMessage());
		this.setAspectsInView(model, rpgReturnResponseHandler);
		//No refresh on jsonRecord is done for the GUI (form fields). Must be implemented right here, if required. !!
        this.setDomainObjectsInView(model, record);
	}
	
	/**
	 * Populates the html object (model map for the JSTL)
	 * 
	 * @param session
	 * @param model
	 * @param container
	 * @return
	 */
	private void setDomainObjectsInView(Map model, JsonSkatNctsImportSpecificTopicLoggingContainer container){
		//SET HEADER RECORDS  (from RPG)
		for (JsonSkatNctsImportSpecificTopicLoggingRecord record : container.getLogg()){
			model.put(SkatConstants.DOMAIN_RECORD, record);
		}
		
	}
	
	/**
	 * In order to pass some crucial GET parameters comming from the specific topic selection in previous update-topic action
	 * 
	 * @param request
	 * @param model
	 */
	
	private void setDomainObjectsInView(HttpServletRequest request, Map model){
		//SET HEADER RECORDS  (from request)
		String opd = request.getParameter("opd");
		String avd = request.getParameter("avd");
		String sign = request.getParameter("sign");
		String status = request.getParameter("status");
		String godsNr = request.getParameter("godsNr");
		String mrnNr = request.getParameter("mrnNr");
		String datum = request.getParameter("datum");
		
		model.put("opd", opd);
		model.put("avd", avd);
		model.put("sign", sign);
		model.put("status", status);
		model.put("godsNr", godsNr);
		model.put("mrnNr", mrnNr);
		model.put("datum", datum);
		
		
	}
	
	
	/**
	 * 
	 * 
	 * @param model
	 * @param record
	 */
	private void setDomainObjectsInView(Map model, JsonSkatNctsImportSpecificTopicLoggingRecord record){
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
		errorMetaInformation.append(rpgReturnResponseHandler.getTitdn());
		model.put(SkatConstants.ASPECT_ERROR_META_INFO, errorMetaInformation);
		
	}
			
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	@Qualifier ("skatNctsImportSpecificTopicService")
	private SkatNctsImportSpecificTopicService skatNctsImportSpecificTopicService;
	@Autowired
	@Required
	public void setSkatNctsImportSpecificTopicService (SkatNctsImportSpecificTopicService value){ this.skatNctsImportSpecificTopicService = value; }
	public SkatNctsImportSpecificTopicService getSkatNctsImportSpecificTopicService(){ return this.skatNctsImportSpecificTopicService; }
	
	
	
}

