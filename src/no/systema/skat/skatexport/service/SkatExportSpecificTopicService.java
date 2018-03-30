/**
 * 
 */
package no.systema.skat.skatexport.service;


import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportSpecificTopicContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicCopiedContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.archive.JsonSkatExportSpecificTopicArchiveContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.logging.JsonSkatExportSpecificTopicLoggingContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.logging.JsonSkatExportSpecificTopicLoggingLargeTextContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicCopiedFromTransportUppdragContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportSpecificTopicOmbudContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicInvoiceContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicInvoiceExternalContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicInvoiceExternalForUpdateContainer;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportSpecificTopicFaktTotalContainer;


/**
 * @author oscardelatorre
 * @date Feb 26, 2014
 * 
 */
public interface SkatExportSpecificTopicService {
	public JsonSkatExportSpecificTopicContainer getSkatExportSpecificTopicContainer(String utfPayload);
	public JsonSkatExportTopicCopiedContainer getSkatExportTopicCopiedContainer(String utfPayload);
	public JsonSkatExportSpecificTopicArchiveContainer getSkatExportSpecificTopicArchiveContainer(String utfPayload);
	public JsonSkatExportSpecificTopicLoggingContainer getSkatExportSpecificTopicLoggingContainer(String utfPayload);
	public JsonSkatExportSpecificTopicLoggingLargeTextContainer getSkatExportSpecificTopicLoggingLargeTextContainer(String utfPayload);
	public JsonSkatExportTopicCopiedFromTransportUppdragContainer getSkatExportTopicCopiedFromTransportUppdragContainer(String utfPayload);
	public JsonSkatExportSpecificTopicOmbudContainer getSkatExportSpecificTopicOmbudContainer (String utfPayload);
	public JsonSkatExportSpecificTopicFaktTotalContainer getSkatExportSpecificTopicFaktTotalContainer (String utfPayload);
	
	//Invoices
	public JsonSkatExportTopicInvoiceContainer getSkatExportTopicInvoiceContainerContainer (String utfPayload);
	public JsonSkatExportTopicInvoiceContainer getSkatExportTopicInvoiceContainerOneInvoice (String utfPayload);
	//External invoices
	public JsonSkatExportTopicInvoiceExternalContainer getSkatExportTopicInvoiceContainerContainerExternal (String utfPayload);
	public JsonSkatExportTopicInvoiceExternalContainer getSkatExportTopicInvoiceContainerOneInvoiceExternal (String utfPayload);
	public JsonSkatExportTopicInvoiceExternalForUpdateContainer getSkatExportTopicInvoiceContainerOneInvoiceExternalForUpdate (String utfPayload);
	
	
}
