/**
 * 
 */
package no.systema.skat.skatexport.view;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;

import no.systema.main.context.TdsAppContext;
import no.systema.skat.skatexport.model.jsonjackson.topic.JsonSkatExportTopicListRecord;
import no.systema.skat.skatimport.model.jsonjackson.topic.JsonSkatImportTopicListRecord;
import no.systema.skat.util.SkatConstants;
/**
 * 
 * @author oscardelatorre
 * @date Apr 2020
 * 
 */
public class MainListExcelBuilder extends AbstractXlsView {
	private ApplicationContext context;
	
	public MainListExcelBuilder(){
		this.context = TdsAppContext.getApplicationContext();
	}
	
	protected void buildExcelDocument(Map<String, Object> model,
        Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get data model which is passed by the Spring Container via our own Controller implementation
        List<JsonSkatExportTopicListRecord> itemList = (List<JsonSkatExportTopicListRecord>) model.get(SkatConstants.MAIN_TOPIC_LIST);
         
        // create a new Excel sheet
        Sheet sheet = workbook.createSheet("Skat-Export Main list");
        sheet.setDefaultColumnWidth(30);
         
        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        font.setColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        style.setFont(font);
         
        // create header row
        Row header = sheet.createRow(0);

        header.createCell(0).setCellValue(this.context.getMessage("systema.skat.export.list.search.label.avd", new Object[0], request.getLocale()));
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue(this.context.getMessage("systema.skat.export.list.search.label.signatur", new Object[0], request.getLocale()));
        header.getCell(1).setCellStyle(style);
        
        header.createCell(2).setCellValue(this.context.getMessage("systema.skat.export.list.search.label.arende", new Object[0], request.getLocale()));
        header.getCell(2).setCellStyle(style);
        
        
        header.createCell(3).setCellValue(this.context.getMessage("systema.skat.export.list.search.label.mrn", new Object[0], request.getLocale()));
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue(this.context.getMessage("systema.skat.export.list.search.label.refnr", new Object[0], request.getLocale()));
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue(this.context.getMessage("systema.skat.export.list.search.label.xrefnr", new Object[0], request.getLocale()));
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue(this.context.getMessage("systema.skat.export.list.search.label.aart", new Object[0], request.getLocale()));
        header.getCell(6).setCellStyle(style);
        
        header.createCell(7).setCellValue(this.context.getMessage("systema.skat.export.list.search.label.datum", new Object[0], request.getLocale()));
        header.getCell(7).setCellStyle(style);
        
        header.createCell(8).setCellValue(this.context.getMessage("systema.skat.export.list.search.label.status", new Object[0], request.getLocale()));
        header.getCell(8).setCellStyle(style);
        
        header.createCell(9).setCellValue(this.context.getMessage("systema.skat.export.list.search.label.proformaang", new Object[0], request.getLocale()));
        header.getCell(9).setCellStyle(style);
        
        header.createCell(10).setCellValue(this.context.getMessage("systema.skat.export.list.search.label.avsandare", new Object[0], request.getLocale()));
        header.getCell(10).setCellStyle(style);
        
        header.createCell(11).setCellValue(this.context.getMessage("systema.skat.export.list.search.label.mottagare", new Object[0], request.getLocale()));
        header.getCell(11).setCellStyle(style);
        
          // create data rows
        int rowCount = 1;
         
        for (JsonSkatExportTopicListRecord record : itemList) {
            Row aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(record.getAvd());
            aRow.createCell(1).setCellValue(record.getSign());
            aRow.createCell(2).setCellValue(record.getOpd());
            aRow.createCell(3).setCellValue(record.getDkeh_mrn());
            
            aRow.createCell(4).setCellValue(record.getRefnr());
            aRow.createCell(5).setCellValue(record.getDkeh_xref());
            aRow.createCell(6).setCellValue(record.getAart());
            aRow.createCell(7).setCellValue(record.getDatum());
            aRow.createCell(8).setCellValue(record.getStatus());
            
            aRow.createCell(9).setCellValue(record.getDkeh_prof());
            aRow.createCell(10).setCellValue(record.getAvsNavn());
            aRow.createCell(11).setCellValue(record.getMotNavn());
            
        }
    }
	
}
