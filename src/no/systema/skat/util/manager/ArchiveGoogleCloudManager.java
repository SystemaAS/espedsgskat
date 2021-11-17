package no.systema.skat.util.manager;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javawebparts.core.org.apache.commons.lang.StringUtils;
import no.systema.jservices.common.dao.ArkivpDao;
import no.systema.main.model.SystemaWebUser;
import no.systema.main.util.ApplicationPropertiesUtil;
import no.systema.skat.nctsexport.model.jsonjackson.topic.archive.JsonSkatNctsExportSpecificTopicArchiveContainer;
import no.systema.skat.nctsexport.model.jsonjackson.topic.archive.JsonSkatNctsExportSpecificTopicArchiveRecord;
import no.systema.skat.nctsimport.model.jsonjackson.topic.archive.JsonSkatNctsImportSpecificTopicArchiveContainer;
import no.systema.skat.nctsimport.model.jsonjackson.topic.archive.JsonSkatNctsImportSpecificTopicArchiveRecord;
import no.systema.skat.skatexport.model.jsonjackson.topic.archive.JsonSkatExportSpecificTopicArchiveContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.archive.JsonSkatExportSpecificTopicArchiveRecord;
import no.systema.skat.skatimport.model.jsonjackson.topic.archive.JsonSkatImportSpecificTopicArchiveContainer;
import no.systema.skat.skatimport.model.jsonjackson.topic.archive.JsonSkatImportSpecificTopicArchiveRecord;


/**
 * 
 * @author oscardelatorre
 * Aug 2021
 * 
 */
@Service
public class ArchiveGoogleCloudManager {
	private static final Logger logger = Logger.getLogger(ArchiveGoogleCloudManager.class.getName());
	//private final String GOOGLE_BUCKET_PREFIX_URL = "https://storage.googleapis.com/gc_"; //complete should be e.g--> "https://storage.googleapis.com/gc_a12/si20210003100088296FVQzjftv.pdf"
	
	//API-SYSTEMA -->File passthrough, kan brukes for å vise fil til bruker. Returnere faktisk fil (pdf) :
	//GET http://10.13.3.22:9886/api/files?companyid=a12&filename=si201200060073243gS3b26AwqC.pdf
	//private final String GOOGLE_BUCKET_PREFIX_URL = "http://10.13.3.22:9886/api/files?";
		
	private final String GOOGLE_BUCKET_PREFIX_URL = ApplicationPropertiesUtil.getProperty("archive.cloud.endpoint.prefix");
	
	private final String SAAS_ROOT_PATH_ON_FILE_SYSTEM = "/asp/";
	private final String SAAS_ROOT_PATH_ON_FILE_SYSTEM_UPPERCASE = "/ASP/";
	private final String LOCALHOST_ROOT_PATH_ON_FILE_SYSTEM = "/pdf/";
	private final String GOOGLE = "google";
	private final String SYSTEMA_HOST_IP = "10.13.3.22";
	private final String SYSTEMA_HOST_DNS = "gw.systema.no";
	/**
	 * 
	 * @param appUser
	 * @param container
	 * @return
	 */
	public JsonSkatExportSpecificTopicArchiveContainer adjustUrl(SystemaWebUser appUser, JsonSkatExportSpecificTopicArchiveContainer container ) {
		JsonSkatExportSpecificTopicArchiveContainer result = container;
		List newList = new ArrayList();
		
		//change the url to the external google cloud url
		for(JsonSkatExportSpecificTopicArchiveRecord record : container.getArchiveElements()) {
			String originalUrl = record.getUrl();
			record.setUrl(this.setAdjustedUrl(appUser, originalUrl));
			newList.add(record);
		}
		if(newList.size()>0) {
			result.setArchiveElements(newList);
		}
		
		return result;
	}
	/**
	 * 
	 * @param appUser
	 * @param container
	 * @return
	 */
	public JsonSkatImportSpecificTopicArchiveContainer adjustUrl(SystemaWebUser appUser, JsonSkatImportSpecificTopicArchiveContainer container ) {
		JsonSkatImportSpecificTopicArchiveContainer result = container;
		List newList = new ArrayList();
		
		//change the url to the external google cloud url
		for(JsonSkatImportSpecificTopicArchiveRecord record : container.getArchiveElements()) {
			String originalUrl = record.getUrl();
			record.setUrl(this.setAdjustedUrl(appUser, originalUrl));
			newList.add(record);
		}
		if(newList.size()>0) {
			result.setArchiveElements(newList);
		}
				
		return result;
	}
	
	/**
	 * 
	 * @param appUser
	 * @param container
	 * @return
	 */
	public JsonSkatNctsExportSpecificTopicArchiveContainer adjustUrl(SystemaWebUser appUser, JsonSkatNctsExportSpecificTopicArchiveContainer container ) {
		JsonSkatNctsExportSpecificTopicArchiveContainer result = container;
		List newList = new ArrayList();
		
		//change the url to the external google cloud url
		for(JsonSkatNctsExportSpecificTopicArchiveRecord record : container.getArchiveElements()) {
			String originalUrl = record.getUrl();
			record.setUrl(this.setAdjustedUrl(appUser, originalUrl));
			newList.add(record);
		}
		if(newList.size()>0) {
			result.setArchiveElements(newList);
		}
				
		return result;
	}
	
	/**
	 * 
	 * @param appUser
	 * @param container
	 * @return
	 */
	public JsonSkatNctsImportSpecificTopicArchiveContainer adjustUrl(SystemaWebUser appUser, JsonSkatNctsImportSpecificTopicArchiveContainer container ) {
		JsonSkatNctsImportSpecificTopicArchiveContainer result = container;
		List newList = new ArrayList();
		
		//change the url to the external google cloud url
		for(JsonSkatNctsImportSpecificTopicArchiveRecord record : container.getArchiveElements()) {
			String originalUrl = record.getUrl();
			record.setUrl(this.setAdjustedUrl(appUser, originalUrl));
			newList.add(record);
		}
		if(newList.size()>0) {
			result.setArchiveElements(newList);
		}
				
		return result;
	}
	
	
	
	/**
	 * Special case for ZEM since this is retrieve with JAVA services (through db-table:ARKIVP) and not with RPG-service pgm
	 * 
	 * @param appUser
	 * @param originalPdfDir
	 * @param list
	 * @return
	 */
	public Collection<ArkivpDao> adjustUrl(SystemaWebUser appUser, String originalPdfDir, Collection<ArkivpDao> list) {
		Collection<ArkivpDao> newList = new ArrayList<ArkivpDao>();
		
		//change the url to the external google cloud url
		for(ArkivpDao record : list) {
			String originalUrl = originalPdfDir + record.getArlink();
			record.setOwn_url(this.setAdjustedUrl(appUser, originalUrl));
			newList.add(record);
		}
		return newList;
		
	}

	/**
	 * 
	 * @param suffix
	 * @param strToReplace
	 * @return
	 */
	private String adjustPdfSuffix(String suffix, String strToReplace) {
		String retval = suffix.replace(strToReplace, "");
		
		//Exception for A25 and some others. We must convert all directories to lower case (google cloud will have only lower case)
		if(strToReplace.equals(SAAS_ROOT_PATH_ON_FILE_SYSTEM_UPPERCASE)) {
			int index = suffix.lastIndexOf("/");
			String dirs = suffix.substring(0, index);
			String file = suffix.substring(index);
			//change all directories to lower case
			dirs = dirs.toLowerCase();
			dirs = dirs.replace(SAAS_ROOT_PATH_ON_FILE_SYSTEM, "");
			//concat. Will end up with this result string: "a25/xxxxx.pdf"
			retval = dirs + file;
			
		}
		
		return retval;
		
	}
	/**
	 * Special case in case we are using SYSTEMAs API and not Google directly 
	 * @param suffix
	 * @param strToReplace
	 * @return
	 */
	private String adjustPdfApiSuffix(String suffix, String strToReplace) {
		String result = "";
		String tmp = this.adjustPdfSuffix(suffix, strToReplace);
		logger.warn(tmp);
		//We now have a string in this format: a12/xxxx.pdf
		//We aim to have this suffix: "companyid=a12&filename=si201200060073243gS3b26AwqC.pdf
		int index = tmp.lastIndexOf("/");
		String companyid = tmp.substring(0, index);
		String filename = tmp.substring(index + 1);
		
		result = "companyid=" + companyid + "&filename=" + filename;
		
		return result;
		
	}
	/**
	 * 
	 * @param appUser
	 * @param url
	 * @return
	 */
	private String setAdjustedUrl(SystemaWebUser appUser, String url) {
		String retval = url;
		logger.info("original url:" + url);
		
		if(url.startsWith(SAAS_ROOT_PATH_ON_FILE_SYSTEM) || url.startsWith(SAAS_ROOT_PATH_ON_FILE_SYSTEM_UPPERCASE) ) {
			logger.warn("Saas!");
			logger.warn("local url:" + url);
			if(!new File(url).exists()){
				logger.info("File does not exists locally!...going to google cloud ...");
				String strToReplace = SAAS_ROOT_PATH_ON_FILE_SYSTEM;
				if(url.startsWith(SAAS_ROOT_PATH_ON_FILE_SYSTEM_UPPERCASE)) { strToReplace = SAAS_ROOT_PATH_ON_FILE_SYSTEM_UPPERCASE; }
				
				if(GOOGLE_BUCKET_PREFIX_URL.toLowerCase().contains(this.GOOGLE)) {
					//do it
					retval = GOOGLE_BUCKET_PREFIX_URL + this.adjustPdfSuffix(url, strToReplace);
					logger.info("cloud url:" + retval);
					
				}else if(GOOGLE_BUCKET_PREFIX_URL.toLowerCase().contains(this.SYSTEMA_HOST_IP) || GOOGLE_BUCKET_PREFIX_URL.toLowerCase().contains(this.SYSTEMA_HOST_DNS)) {
					//implement other API in case the direct google API is not used. Probably an inhouse API (Vidars)
					retval = GOOGLE_BUCKET_PREFIX_URL + this.adjustPdfApiSuffix(url, strToReplace);
					logger.info("api url:" + retval);
					
				}
			}
			
		}else if(url.toLowerCase().startsWith(LOCALHOST_ROOT_PATH_ON_FILE_SYSTEM) && (appUser.getServletHostWithoutHttpPrefix().equals("localhost") || appUser.getServletHostWithoutHttpPrefix().equals("gw.systema.no"))) {
			logger.info("localhost! or gw.systema test");
			logger.info("local url:" + url);
			if(!new File(url).exists()){
				logger.info("File does not exists locally!...going to google cloud ...");
				String strToReplace = LOCALHOST_ROOT_PATH_ON_FILE_SYSTEM;
				
				if(GOOGLE_BUCKET_PREFIX_URL.toLowerCase().contains(this.GOOGLE)) {
					//do it
					retval = GOOGLE_BUCKET_PREFIX_URL + this.adjustPdfSuffix(url, strToReplace);
					// TEST record.setUrl(googleBucketPrefix + "a12/si20210003100088296FVQzjftv.pdf");
					logger.warn("cloud url:" + retval);
					
				}else if(GOOGLE_BUCKET_PREFIX_URL.toLowerCase().contains(this.SYSTEMA_HOST_IP) || GOOGLE_BUCKET_PREFIX_URL.toLowerCase().contains(this.SYSTEMA_HOST_DNS)) {
					//implement other API in case the direct google API is not used. Probably an inhouse API (Vidars)
					retval = GOOGLE_BUCKET_PREFIX_URL + this.adjustPdfApiSuffix(url, strToReplace);
					logger.warn("api url:" + retval);
					
				}
			}
		}
		
		logger.warn("adjusted url:" + retval);
		
		return retval;
	}
	
	
	
	
}
