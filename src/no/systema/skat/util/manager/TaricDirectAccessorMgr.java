/**
 * 
 */
package no.systema.skat.util.manager;

import no.systema.skat.model.external.url.UrlTaricAskTullidObject;
import no.systema.skat.util.SkatConstants;

import java.util.*;

/**
 * The class has direct access to an external Taric url address
 * 
 * @author oscardelatorre
 * @date Nov 12, 2013
 * 
 *
 */
public class TaricDirectAccessorMgr {
	
	/**
	 * 
	 * @param model
	 */
	public void askTullid(Map model){
		model.put(SkatConstants.URL_EXTERNAL_FRAGA_TULLID_TARIC_CODE, new UrlTaricAskTullidObject());
	}
	
	
}
