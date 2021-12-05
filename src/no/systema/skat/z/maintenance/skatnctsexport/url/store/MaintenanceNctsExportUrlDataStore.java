/**
 * 
 */
package no.systema.skat.z.maintenance.skatnctsexport.url.store;
import no.systema.main.util.AppConstants;
/**
 * 
 * Static URLs
 * @author oscardelatorre
 * @date Jun 13, 2016
 * 
 * 
 */
public final class MaintenanceNctsExportUrlDataStore {
	//--------------------------------------------
	//[1] FETCH DB Table list or Specific record
	//--------------------------------------------
	//DKX030R
	//ALL --> http://gw.systema.no:8080/syjservicesst/syjsDKX030R.do?user=OSCAR
	static public String MAINTENANCE_BASE_DKX030R_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKX030R.do";
	//ALL --> http://gw.systema.no:8080/syjservices/syjsSVX030R_FBRUKT.do?user=OSCAR
	static public String MAINTENANCE_BASE_DKX030R_FBRUKT_GET_LIST_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKX030R_FBRUKT.do";
	
	//----------------------------------
	//[1] UPDATE DB record
	// mode = (U)pdate, (A)dd, (D)elete
	//----------------------------------
	//DKX030R
	//ALL --> http://gw.systema.no:8080/syjservicesst/syjsDKX030R_U.do?user=OSCAR&mode=U&<record>attributes...
	static public String MAINTENANCE_BASE_DKX030R_DML_UPDATE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKX030R_U.do";
	
	
	//ALL --> http://gw.systema.no:8080/syjservicesst/syjsDKX030R_U_releaseGuarantee.do?user=OSCAR&mode=U&<record>attributes...
	static public String MAINTENANCE_BASE_DKX030R_DML_RELEASE_GUARANTEE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKX030R_U_releaseGuarantee.do";
		
	//ALL --> http://gw.systema.no:8080/syjservicesst/syjsDKX030R_U_releaseGuarantee.do?user=OSCAR&mode=U&<record>attributes...
	static public String MAINTENANCE_BASE_DKX030R_DML_ADJUST_GUARANTEE_URL = AppConstants.HTTP_ROOT_SERVLET_JSERVICES + "/syjservicesst/syjsDKX030R_U_adjustGuarantee.do";
		
}
