package no.systema.skat.skatexport.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
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

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.ServletRequestDataBinder;

//application imports
import no.systema.main.context.TdsAppContext;
import no.systema.main.service.UrlCgiProxyService;
import no.systema.main.service.UrlCgiProxyServiceImpl;
import no.systema.main.validator.LoginValidator;
import no.systema.main.util.AppConstants;
import no.systema.main.util.DateTimeManager;
import no.systema.main.util.EncodingTransformer;
import no.systema.main.util.JsonDebugger;
import no.systema.main.model.SystemaWebUser;


import no.systema.skat.model.jsonjackson.codes.JsonSkatCodeContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatCodeRecord;
import no.systema.skat.skatexport.service.SkatExportGeneralCodesChildWindowService;
import no.systema.skat.service.SkatTaricVarukodService;
import no.systema.skat.skatexport.filter.SearchFilterSkatExportTopicList;
import no.systema.skat.skatexport.service.SkatExportTopicListService;
import no.systema.skat.url.store.SkatUrlDataStore;
import no.systema.skat.util.SkatConstants;
import no.systema.skat.model.jsonjackson.codes.JsonSkatTaricVarukodContainer;
import no.systema.skat.model.jsonjackson.codes.JsonSkatTaricVarukodRecord;


/**
 * SKAT Export Item Controller - child windows popup
 * 
 * @author oscardelatorre
 * @date Apr 21, 2016
 * 
 */

@Controller
@SessionAttributes(AppConstants.SYSTEMA_WEB_USER_KEY)
@Scope("session")
public class SkatExportItemsControllerChildWindow {
	
	private static final Logger logger = Logger.getLogger(SkatExportItemsControllerChildWindow.class.getName());
	private static final JsonDebugger jsonDebugger = new JsonDebugger(2000);
	
	private ModelAndView loginView = new ModelAndView("login");
	private ApplicationContext context;
	private LoginValidator loginValidator = new LoginValidator();
	//private CodeDropDownMgr codeDropDownMgr = new CodeDropDownMgr();
	private DateTimeManager dateTimeMgr = new DateTimeManager();
	
	
	@PostConstruct
	public void initIt() throws Exception {
		if("DEBUG".equals(AppConstants.LOG4J_LOGGER_LEVEL)){
			logger.setLevel(Level.DEBUG);
		}
	}
	
	
	/**
	 * 
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatexport_edit_items_childwindow_generalcodes.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitGeneralCodes(@ModelAttribute ("record") JsonSkatCodeRecord recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitGeneralCodes");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		String typeCode = request.getParameter("type");
		
		ModelAndView successView = new ModelAndView("skatexport_edit_items_childwindow_generalcodes");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			  
			List list = this.getCodeList(appUser, typeCode);
			model.put("generalCodeList", list);
			model.put("callerType", callerType);
			
			successView.addObject(SkatConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	/**
	 * 
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatexport_edit_items_childwindow_generalcodesR40.do", params="action=doInit",  method={RequestMethod.GET} )
	public ModelAndView doInitGeneralCodesR40(@ModelAttribute ("record") JsonSkatCodeRecord recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitGeneralCodes");
		Map model = new HashMap();
		String callerType = request.getParameter("ctype");
		String typeCode = request.getParameter("type");
		//
		String dkeh_26 = request.getParameter("dkeh_26");
		
		
		ModelAndView successView = new ModelAndView("skatexport_edit_items_childwindow_generalcodes");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			  
			List list = this.getCodeListR40(appUser, typeCode, dkeh_26);
			model.put("generalCodeList", list);
			model.put("callerType", callerType);
			
			successView.addObject(SkatConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	/**
	 * 
	 * @param recordToValidate
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="skatexport_edit_items_childwindow_tolltariff.do", params="action=doInit",  method={RequestMethod.GET, RequestMethod.POST } )
	public ModelAndView doInitTolltariff(@ModelAttribute ("record") JsonSkatTaricVarukodContainer recordToValidate, HttpSession session, HttpServletRequest request){
		this.context = TdsAppContext.getApplicationContext();
		logger.info("Inside: doInitTolltariff");
		Map model = new HashMap();
		String varuKod = request.getParameter("vkod");
		String text = request.getParameter("tekst");
		String ieMode = "E";
		
		ModelAndView successView = new ModelAndView("skatexport_edit_items_childwindow_tolltariff");
		SystemaWebUser appUser = this.loginValidator.getValidUser(session);
		//check user (should be in session already)
		if(appUser==null){
			return this.loginView;
			
		}else{
			  
			List<JsonSkatTaricVarukodRecord> list = new ArrayList();
			if(text!=null && !"".equals(text)){
				//TODO (CB) list = this.getTulltaxaListFromDesc(appUser, text, ieMode);
				model.put("tekst", text);
			}else{
				list = this.getTolltariffList(appUser, varuKod, ieMode);
				model.put("vkod", varuKod);
			}
			model.put("tolltariffList", list);
			successView.addObject(SkatConstants.DOMAIN_MODEL , model);
			
	    	return successView;
		}
	}
	
	/**
	 * 
	 * @param appUser
	 * @param tolltariffVarekod
	 * @param ieMode
	 * @return
	 */
	private List<JsonSkatTaricVarukodRecord> getTolltariffList(SystemaWebUser appUser, String tolltariffVarekod, String ieMode){
		List<JsonSkatTaricVarukodRecord> list = new ArrayList<JsonSkatTaricVarukodRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = SkatUrlDataStore.SKAT_FETCH_TARIC_VARUKODER_ITEMS_URL;
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser() + "&ie=" + ieMode);
		urlRequestParams.append("&kod=" + tolltariffVarekod);
		
		logger.info(BASE_URL);
		logger.info(urlRequestParams);
		
		UrlCgiProxyService urlCgiProxyService = new UrlCgiProxyServiceImpl();
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//logger.info(jsonPayload);
		JsonSkatTaricVarukodContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.skatTaricVarukodService.getContainer(jsonPayload);
				if(container!=null){
					for(JsonSkatTaricVarukodRecord  record : container.getTariclist()){
						list.add(record);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 public static final String CODE_001_ANGIVELSESARTER = "001";
	public static final String CODE_005_CERTIFIKAT_R44_2 = "005";
	public static final String CODE_006_TOLDSTED = "006";
	public static final String CODE_007_EMBALLAGE_R31 = "007";
	public static final String CODE_008_COUNTRY = "008";
	public static final String CODE_009_OPLYSNINGSTYPE_R44_9 = "009";
	public static final String CODE_011_PROCEDURE_R37 = "011";
	public static final String CODE_012_PDOKUMENTATIONSKODER_R44_4 = "012";
	public static final String CODE_013_PREFERENCE_R36 = "013";
	public static final String CODE_015_STATUS_KODER = "015";
	public static final String CODE_017_TRANSPORTDOK_SUMMARISKA_R40 = "017";
	public static final String CODE_018_TRANSPORTKODER_R25R26 = "018";
	public static final String CODE_019_VAB_CERTIFIKAT_R44_3 = "019";
	public static final String CODE_020_CURRENCY = "020";
	public static final String CODE_021_SUPP_VAREOPL_R44_6 = "021";
	public static final String CODE_022_SUPP_ENHEDER_R41_1 = "022";
	public static final String CODE_109_BETALING_FOR_TRANSPORT_RS29 = "109";
	//Borrowed from Export
	public static final String CODE_106_INCOTERMS = "106";
	
	 * 
	 * @param appUser
	 * @param typeCode
	 * @return
	 */
	private List<JsonSkatCodeRecord> getCodeList(SystemaWebUser appUser, String typeCode){
		List<JsonSkatCodeRecord> list = new ArrayList<JsonSkatCodeRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = SkatUrlDataStore.SKAT_CODES_URL;
		
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&typ=" + typeCode);
		
		logger.info(BASE_URL);
		logger.info(urlRequestParams);
		
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		
		JsonSkatCodeContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.skatExportGeneralCodesChildWindowService.getCodeContainer(jsonPayload);
				if(container!=null){
					for(JsonSkatCodeRecord  record : container.getKodlista()){
						list.add(record);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 
	 * @param appUser
	 * @param typeCode
	 * @param dkeh_26
	 * @return
	 */
	private List<JsonSkatCodeRecord> getCodeListR40(SystemaWebUser appUser, String typeCode, String dkeh_26){
		//It must be a Set in order to eliminate duplicates
		Set<JsonSkatCodeRecord> list = new HashSet<JsonSkatCodeRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		String BASE_URL = SkatUrlDataStore.SKAT_CODES_URL;
		
		StringBuffer urlRequestParams = new StringBuffer();
		urlRequestParams.append("user=" + appUser.getUser());
		urlRequestParams.append("&typ=" + typeCode);
		if(dkeh_26!=null && !"".equals(dkeh_26)){
			urlRequestParams.append("&kode3=" + dkeh_26);
		}
		logger.info(BASE_URL);
		logger.info(urlRequestParams);
		
		String jsonPayload = urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams.toString());
		//logger.info(jsonPayload);
		JsonSkatCodeContainer container = null;
		try{
			if(jsonPayload!=null){
				container = this.skatExportGeneralCodesChildWindowService.getCodeContainer(jsonPayload);
				if(container!=null){
					for(JsonSkatCodeRecord  record : container.getKodlista()){
						list.add(record);
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ArrayList(list);
	}
	/**
	 * 
	 * @param appUser
	 * @param searchFilter
	 * @return
	 */
	/*
	private Collection<JsonSkatExportTopicListRecord> getAngivelseList(SystemaWebUser appUser, SearchFilterSkatExportTopicList searchFilter){
		Collection<JsonSkatExportTopicListRecord> list = new ArrayList<JsonSkatExportTopicListRecord>();
		
		logger.info(Calendar.getInstance().getTime() + " CONTROLLER start - timestamp");
		
		//get BASE URL
		final String BASE_URL = SkatExportUrlDataStore.SKAT_EXPORT_BASE_TOPICLIST_URL;
		//add URL-parameters
		String urlRequestParams = this.getRequestUrlKeyParameters(searchFilter, appUser);
		logger.info(Calendar.getInstance().getTime() + " CGI-start timestamp");
    	logger.info("URL: " + BASE_URL);
    	logger.info("URL PARAMS: " + urlRequestParams);
    	
    	String jsonPayload = this.urlCgiProxyService.getJsonContent(BASE_URL, urlRequestParams);

    	//Debug --> 
    	logger.info(jsonPayload);
    	logger.info(Calendar.getInstance().getTime() +  " CGI-end timestamp");
    	if(jsonPayload!=null){
    		JsonSkatExportTopicListContainer jsonSkatExportTopicListContainer = this.skatExportTopicListService.getSkatExportTopicListContainer(jsonPayload);
			//-----------------------------------------------------------
			//now filter the topic list with the search filter (if applicable)
			//-----------------------------------------------------------
			list = jsonSkatExportTopicListContainer.getOrderList();	
			//Remove all "D" status rows. These are to be shown if-and-only-if the user demand it explicitly in the search filter
			if(!"D".equals(searchFilter.getStatus())){
				//To avoid the ...ConcurrentModificationException we take a copy of the existing list and iterate over new copy
				for (JsonSkatExportTopicListRecord record : new ArrayList<JsonSkatExportTopicListRecord>(list)){
					if("D".equals(record.getStatus())){
						list.remove(record);
					}
				}
			}
    	}
		return list;
	}
    	*/
    /**
     * 	
     * @param searchFilter
     * @param appUser
     * @return
     */
	private String getRequestUrlKeyParameters(SearchFilterSkatExportTopicList searchFilter, SystemaWebUser appUser){
		StringBuffer urlRequestParamsKeys = new StringBuffer();
		//String action = request.getParameter("action");
		
		urlRequestParamsKeys.append("user=" + appUser.getUser());
		if(searchFilter.getAvd()!=null && !"".equals(searchFilter.getAvd())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avd=" + searchFilter.getAvd());
		}
		if(searchFilter.getOpd()!=null && !"".equals(searchFilter.getOpd())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "opd=" + searchFilter.getOpd());
		}
		if(searchFilter.getSign()!=null && !"".equals(searchFilter.getSign())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "sign=" + searchFilter.getSign());
		}
		if(searchFilter.getRefnr()!=null && !"".equals(searchFilter.getRefnr())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "refnr=" + searchFilter.getRefnr());
		}
		if(searchFilter.getXrefnr()!=null && !"".equals(searchFilter.getXrefnr())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "xref=" + searchFilter.getXrefnr());
		}
		if(searchFilter.getDatum()!=null && !"".equals(searchFilter.getDatum())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "datum=" + searchFilter.getDatum());
		}
		if(searchFilter.getStatus()!=null && !"".equals(searchFilter.getStatus())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "status=" + searchFilter.getStatus());
		}
		if(searchFilter.getAvsNavn()!=null && !"".equals(searchFilter.getAvsNavn())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "avsNavn=" + searchFilter.getAvsNavn());
		}
		if(searchFilter.getMotNavn()!=null && !"".equals(searchFilter.getMotNavn())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "motNavn=" + searchFilter.getMotNavn());
		}
		if(searchFilter.getInternFakturanr()!=null && !"".equals(searchFilter.getInternFakturanr())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "fakt=" + searchFilter.getInternFakturanr());
		}
		if(searchFilter.getAart()!=null && !"".equals(searchFilter.getAart())){
			urlRequestParamsKeys.append(SkatConstants.URL_CHAR_DELIMETER_FOR_PARAMS_WITH_HTML_REQUEST + "aart=" + searchFilter.getAart());
		}
		return urlRequestParamsKeys.toString();
	}	
	
	//SERVICES
	@Qualifier ("urlCgiProxyService")
	private UrlCgiProxyService urlCgiProxyService;
	@Autowired
	@Required
	public void setUrlCgiProxyService (UrlCgiProxyService value){ this.urlCgiProxyService = value; }
	public UrlCgiProxyService getUrlCgiProxyService(){ return this.urlCgiProxyService; }
	
	
	@Qualifier ("skatExportTopicListService")
	private SkatExportTopicListService skatExportTopicListService;
	@Autowired
	@Required
	public void setSkatExportTopicListService (SkatExportTopicListService value){ this.skatExportTopicListService = value; }
	public SkatExportTopicListService getSkatExportTopicListService(){ return this.skatExportTopicListService; }
	
	
	@Qualifier 
	private SkatExportGeneralCodesChildWindowService skatExportGeneralCodesChildWindowService;
	@Autowired
	@Required	
	public void setSkatExportGeneralCodesChildWindowService(SkatExportGeneralCodesChildWindowService value){this.skatExportGeneralCodesChildWindowService = value;}
	public SkatExportGeneralCodesChildWindowService getSkatExportGeneralCodesChildWindowService(){ return this.skatExportGeneralCodesChildWindowService; }
	
	
	@Qualifier 
	private SkatTaricVarukodService skatTaricVarukodService;
	@Autowired
	@Required	
	public void setSkatTaricVarukodService(SkatTaricVarukodService value){this.skatTaricVarukodService = value;}
	public SkatTaricVarukodService getSkatTaricVarukodService(){ return this.skatTaricVarukodService; }
	
}

