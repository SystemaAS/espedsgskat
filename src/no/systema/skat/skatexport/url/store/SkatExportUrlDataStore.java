/**
 * 
 */
package no.systema.skat.skatexport.url.store;
import no.systema.main.util.AppConstants;
import no.systema.main.model.UrlDataStoreAnnotationForField;
/**
 * Static URLs
 * 
 * @author oscardelatorre
 * @date Feb 26, 2014
 * 
 */
public final class SkatExportUrlDataStore {
	
	//----------------------------
	//[1] FETCH ARENDE LIST
	//----------------------------
	@UrlDataStoreAnnotationForField (name="@SkatExportController - doFind, @SkatNctsExportItemsControllerChildWindow - getAngivelseList() ", description=" --> SKAT_EXPORT_BASE_TOPICLIST_URL")
	static public String SKAT_EXPORT_BASE_TOPICLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE000R.pgm";
	//fetch external references
	
	//http://10.13.1.22/sycgip/tdke004r.pgm?user=A25DEMO
	@UrlDataStoreAnnotationForField (name="@SkatExportControllerChildWindow - skatexport_childwindow_external_references.do ", description=" --> SKAT_EXPORT_BASE_FETCH_TOPIC_LIST_EXTERNAL_REFERENCES_URL")
	static public String SKAT_EXPORT_BASE_FETCH_TOPIC_LIST_EXTERNAL_REFERENCES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE004R.pgm";
	
	//http://gw.systema.no/sycgip/tdke005r.pgm?user=OSCAR&avd=1&opd=91127
	@UrlDataStoreAnnotationForField (name="@SkatExportControllerChildWindow - skatexport_childwindow_external_references_delete.do ", description=" --> SKAT_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_EXTERNAL_REFERENCES_URL")
	static public String SKAT_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_EXTERNAL_REFERENCES_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE005R.pgm";	
		
	//----------------------------
	//[2] FETCH A SPECIFIC ARENDE
	//----------------------------
	//TEST static public String XX_EXPORT_BASE_SPECIFIC_TOPIC_URL = "http://gw.systema.no/sycgip/TDKI001R.pgm?user=OSCAR&avd=1&opd=139";
	@UrlDataStoreAnnotationForField (name="@SkatExportHeaderController - skatexport_edit.do, skatexport_copyTopic.do, skatexport_doFetchTopicFromTransportUppdrag.do, etc   ", description=" --> SKAT_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL")
	static public String SKAT_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE001R.pgm";
	//[2.1 FETCH Fakt.total from Invoices]
	@UrlDataStoreAnnotationForField (name="@SkatExportHeaderController - getInvoiceTotalFromInvoices()...  ", description=" --> SKAT_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_FAKT_TOTAL_URL")
	static public String SKAT_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_FAKT_TOTAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE033R.pgm";
	
	//------------------------------
	//[3] EDIT A SPECIFIC ARENDE
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)	
	//mode=C (Copy selected topic)... from Norskexport or fallback to the origin: transportuppdrag
	//mode=S (Send topic)
	//------------------------------		
	@UrlDataStoreAnnotationForField (name="@SkatExportHeaderController - many...  ", description=" --> SKAT_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL")
	static public String SKAT_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE002R.pgm";	
	
	//http://gw.systema.no/sycgip/tdke052r.pgm?user=CB&avd=2&opd=7000033&dtm2=201701172100
	@UrlDataStoreAnnotationForField (name="@SkatExportAjaxHandlerController - sendAllSkatExportStatus11_SkatExport.do   ", description=" --> SKAT_EXPORT_BASE_UPDATE_BULK_SEND_SPECIFIC_TOPICS_URL")
	static public String SKAT_EXPORT_BASE_UPDATE_BULK_SEND_SPECIFIC_TOPICS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE052R.pgm";	
	
	
	//-----------------------------
	//[5] FETCH ITEM RECORDS (LIST)
	//-----------------------------
	@UrlDataStoreAnnotationForField (name="@SkatExportItemsController - skatexport_edit_items.do, many more ...   ", description=" --> SKAT_EXPORT_BASE_FETCH_TOPIC_ITEMLIST_URL")
	static public String SKAT_EXPORT_BASE_FETCH_TOPIC_ITEMLIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE010R.pgm";
	
	//[5.1] FETCH SPECIFIC ITEM (for an update)
	@UrlDataStoreAnnotationForField (name="@SkatExportAjaxHandlerController - getSpecificTopicItemChosenFromGuiElement_SkatExport.do ", description=" --> SKAT_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_ITEM_URL")
	static public String SKAT_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE011R.pgm";
	
	//-----------------------------------
	//[6] EDIT A SPECIFIC ITEM RECORD
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)
	//-----------------------------------
	//http://gw.systema.no/sycgip/TDKE012R.pgm?user=OSCAR&avd=1&opd=80001&lin=2&mode=A
	@UrlDataStoreAnnotationForField (name="@SkatExportItemsController - skatexport_edit_items.do, etc ", description=" --> SKAT_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL")
	static public String SKAT_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE012R.pgm";
	
	//[6.1] DELETE ALL ITEM LINES
	//http://localhost:8080/syjservicesst/syjsDKEV.do?user=OSCAR&mode=D&dkev_syav=1&dkev_syop=9000049
	static public String SKAT_EXPORT_BASE_DELETE_ALL_ITEMS_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKEV_U.do";
	
	//--------------------------------------
	//[6.0.1] Update AutoControl error line  
	//--------------------------------------
	@UrlDataStoreAnnotationForField (name="@SkatExportItemsController - skatexport_edit_items_autocontrol.do, etc ", description=" --> SKAT_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_AUTOCONTROL_ERROR_URL")
	static public String SKAT_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_ITEM_AUTOCONTROL_ERROR_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE026R.pgm";
	//http://gw.systema.no/sycgip/tdke026r.pgm?user=OSCAR&avd=1&opd=900077&lin=1&dkev_err=X
	
	//--------------------------------------------------------------------
	//[6.0.2] EDIT/SAVE (Statistiskt værdi MÅSTE kalkyleras). Inga avgifter 
	//--------------------------------------------------------------------
	//http://gw.systema.no/sycgip/TDKE017R.pgm?user=OSCAR&dkeh_221=USD&dkeh_221b=6.80&dkeh_222=2000
	@UrlDataStoreAnnotationForField (name="@SkatExportAjaxHandlerController - calculateStatistisktVarde_SkatExport.do, etc ", description=" --> SKAT_EXPORT_BASE_AVGIFTS_CALCULATION_URL")
	static public String SKAT_EXPORT_BASE_AVGIFTS_CALCULATION_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE017R.pgm";	
	
	//-----------------------------------
	//[6.1] LIST/EDIT  INVOICE RECORD(s)
	//(mode=A (Add new), 
	//mode=U (Update existing), 
	//mode=D (Delete existing topic)
	//-----------------------------------
	@UrlDataStoreAnnotationForField (name="@SkatExportHeaderInvoiceController - skatexport_edit_invoice.do, etc ", description=" --> SKAT_EXPORT_BASE_FETCH_TOPIC_INVOICELIST_URL")
	static public String SKAT_EXPORT_BASE_FETCH_TOPIC_INVOICELIST_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE030R.pgm";
	//http://gw.systema.no/sycgip/tdke030r.pgm?user=OSCAR&avd=1&opd=52919
	
	
	@UrlDataStoreAnnotationForField (name="@SkatExportAjaxHandlerController - getInvoiceLine_SkatExport.do, etc ", description=" --> SKAT_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_INVOICE_URL")
	static public String SKAT_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_INVOICE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE031R.pgm";
	//http://gw.systema.no/sycgip/tdke031r.pgm?user=OSCAR&avd=1&opd=52919&fak=SE444197610900
	
	@UrlDataStoreAnnotationForField (name="@SkatExportHeaderInvoiceController - skatexport_edit_invoice.do, etc ", description=" --> SKAT_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_INVOICE_URL")
	static public String SKAT_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_INVOICE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE032R.pgm";	
	//http://gw.systema.no/sycgip/tdke032r.pgm?user=OSCAR&mode=U&avd=1&opd=52919&fak=SE444197610900..................................
	
	//------------------------------------------
	//[6.2] LIST/Get  External INVOICE RECORD(s)
	//------------------------------------------
	@UrlDataStoreAnnotationForField (name="@SkatExportControllerChildWindow - skatexport_edit_childwindow_external_invoices.do, etc ", description=" --> SKAT_EXPORT_BASE_FETCH_TOPIC_INVOICELIST_EXTERNAL_URL")
	static public String SKAT_EXPORT_BASE_FETCH_TOPIC_INVOICELIST_EXTERNAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE060R.pgm";
	//http://gw.systema.no/sycgip/tdke060r.pgm?user=OSCAR
	
	@UrlDataStoreAnnotationForField (name="@SkatExport... - ? ", description=" --> SKAT_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_INVOICE_EXTERNAL_URL")
	static public String SKAT_EXPORT_BASE_FETCH_SPECIFIC_TOPIC_INVOICE_EXTERNAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE061R.pgm";	
	//http://gw.systema.no/sycgip/tdke061r.pgm?user=OSCAR&reff=6441&unik=4009282
	
	@UrlDataStoreAnnotationForField (name="@SkatExportAjaxHandlerController ", description=" --> SKAT_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_INVOICE_EXTERNAL_URL")
	static public String SKAT_EXPORT_BASE_UPDATE_SPECIFIC_TOPIC_INVOICE_EXTERNAL_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE062R.pgm";	
	//http://gw.systema.no/sycgip/tdke062r.pgm?user=OSCAR&avd=1&opd=91127&mode=U&reff=6445&unik=4012087
	
	
	//------------------------------------------
	//[7] LOG and ARCHIVE for a SPECIFIC ARENDE
	//------------------------------------------
	//This section contains external functions such as LOGGING, ARCHIVE	
	@UrlDataStoreAnnotationForField (name="@SkatExportHeaderLoggingController - skatexport_logging.do ", description=" --> SKAT_EXPORT_BASE_LOG_LIST_FOR_SPECIFIC_TOPIC_URL")
	static public String SKAT_EXPORT_BASE_LOG_LIST_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE015R.pgm";	
	//http://gw.systema.no/sycgip/TDKE015R.pgm?user=OSCAR&avd=1&opd=218&typ=DKI 
	
	@UrlDataStoreAnnotationForField (name="@SkatExportHeaderLoggingController - skat_export_renderLargeText.do ", description=" --> SKAT_EXPORT_BASE_LOG_LARGE_TEXT_FOR_SPECIFIC_TOPIC_URL")
	static public String SKAT_EXPORT_BASE_LOG_LARGE_TEXT_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE016R.pgm";	
	//http://gw.systema.no/sycgip/TDKE016R.pgm?user=OSCAR&fmn=84278
	
	@UrlDataStoreAnnotationForField (name="@Controller not used... - skatexport_archive.do (uses SkatImport's entities...) ", description=" --> SKAT_EXPORT_BASE_ARCHIVE_FOR_SPECIFIC_TOPIC_URL")
	static public String SKAT_EXPORT_BASE_ARCHIVE_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TJGE001.pgm";	//this function is actually general for all modules byt
	//http://gw.systema.no/sycgip/TJGE001.pgm?user=JOVO&avd=1&opd=52919
		
	//------------------------------------------
	//[8] PRINT document for a SPECIFIC ARENDE
	//------------------------------------------
	@UrlDataStoreAnnotationForField (name="@SkatExportHeaderController - skatexport_edit_printTopic.do ", description=" --> SKAT_EXPORT_BASE_PRINT_FOR_SPECIFIC_TOPIC_URL")
	static public String SKAT_EXPORT_BASE_PRINT_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE014R.pgm";	//this function is actually general for all modules byt
	//http://gw.systema.no/sycgip/TDKE014R.pgm?user=OSCAR&avd=1&opd=218	
	
	
	@UrlDataStoreAnnotationForField (name="@SkatExportHeaderController - skatexport_edit_printSkilleArkTopic.do ", description=" --> SKAT_EXPORT_BASE_PRINT_SKILLEARK_FOR_SPECIFIC_TOPIC_URL")
	static public String SKAT_EXPORT_BASE_PRINT_SKILLEARK_FOR_SPECIFIC_TOPIC_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TARC000R.pgm";	
	//http://gw.systema.no/sycgip/TARC000R.pgm?user=OSCAR&avd=1&opd=218&type=Z

	//----------------------------------------------
	//[9] OMBUD default values for every new record
	//----------------------------------------------
	//http://gw.systema.no/sycgip/TDKE018R.pgm?user=OSCAR&avd=1
	@UrlDataStoreAnnotationForField (name="@SkatExportAjaxHandlerController - getSpecificTopicOmbud_SkatExport.do ", description=" --> SKAT_EXPORT_BASE_FETCH_OMBUD_DEFAULT_DATA_URL")
	static public String SKAT_EXPORT_BASE_FETCH_OMBUD_DEFAULT_DATA_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE018R.pgm";	
	
	//--------------------------------------------------
	//[10] Updates a message type (DNU, HNU) to another
	// Used in Use Case: Begäran om klarering...
	//--------------------------------------------------
	//http://gw.systema.no/sycgip/TSVI019R.pgm?user=OSCAR&avd=1&opd=156&svih_mtyp=HRT 
	//static public String SKAT_EXPORT_BASE_UPDATE_MESSAGETYPE_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI019R.pgm";	
		
	//--------------------------------------------------------
	//[11] Bilagda handlingar (item lines AJAX help function)
	//--------------------------------------------------------
	//http://gw.systema.no/sycgip/TSVI020R.pgm?user=CB&avd=1&opd=171  
	//static public String SKAT_EXPORT_BASE_GET_BILAGDA_HANDLIGAR_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TSVI020R.pgm";	
		
	
	//-----------------------------
	// Change status (Admin Role)
	//-----------------------------
	@UrlDataStoreAnnotationForField (name="@SkatExportHeaderController - skatexport_updateStatus.do ", description=" --> SKAT_EXPORT_BASE_UPDATE_STATUS_URL")
	static public String SKAT_EXPORT_BASE_UPDATE_STATUS_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE051R.pgm";
	
	//-----------------------------
	// Update proforma angivelse
	//-----------------------------
	@UrlDataStoreAnnotationForField (name="@SkatExportHeaderController - skatexport_updateProforma.do ", description=" --> SKAT_EXPORT_BASE_UPDATE_PROFORMA_URL")
	static public String SKAT_EXPORT_BASE_UPDATE_PROFORMA_URL = AppConstants.HTTP_ROOT_CGI + "/sycgip/TDKE053R.pgm";
		
	
}
