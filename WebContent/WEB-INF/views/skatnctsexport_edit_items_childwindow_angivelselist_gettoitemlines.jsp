<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerSkatChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/skatnctsexport_edit_items_childwindow_angivelselist_gettoitemlines.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			<img title="select" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			Importere Eksport Angivelser
			</td>
		</tr>
		<tr>
		<td valign="top">
		
		  		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%" >
					<tr>
					<td>
						<table>
						<form name="skatNctsExportAngivelseForm" id="skatNctsExportAngivelseForm" action="skatnctsexport_edit_items_childwindow_angivelselist_gettoitemlines.do?action=doFind" method="post">
							<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
							<input type="hidden" name="avdNcts" id="avdNcts" value="${model.avdNcts}">
							<input type="hidden" name="opdNcts" id="opdNcts" value="${model.opdNcts}">
						<tr>
							<td class="text14">&nbsp;&nbsp;Afdelning</td>
							<td class="text14">&nbsp;&nbsp;Angivelse</td>
							<td class="text14">&nbsp;&nbsp;Mrn</td>
							<td class="text14">&nbsp;&nbsp;Ref.nr.</td>
							<td class="text14">&nbsp;&nbsp;Transp.id</td>
							<td class="text14">&nbsp;&nbsp;Ext.ref.nr</td>
							<td class="text14">&nbsp;&nbsp;Dato</td>
							<td class="text14">&nbsp;&nbsp;Afs.</td>
							<td class="text14">&nbsp;&nbsp;Modt.</td>
						 </tr>
						 <tr>	
							<td class="text14">&nbsp;<input type="text" class="inputText" name="avd" id="avd" size="5" maxlength="4" value="${searchFilter.avd}"></td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="opd" id="opd" size="8" maxlength="7" value="${searchFilter.opd}"></td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="mrn" id="mrn" size="10" maxlength="10" value="${searchFilter.mrn}"></td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="refnr" id="refnr" size="10" maxlength="10" value="${searchFilter.refnr}"></td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="trid" id="trid" size="10" maxlength="20" value="${searchFilter.trid}"></td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="xrefnr" id="xrefnr" size="10" maxlength="10" value="${searchFilter.xrefnr}"></td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="datum" id="datum" size="10" maxlength="8" value="${searchFilter.datum}"></td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="avsNavn" id="avsNavn" size="15" maxlength="25" value="${searchFilter.avsNavn}"></td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="motNavn" id="motNavn" size="15" maxlength="25" value="${searchFilter.motNavn}"></td>
							
							<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" onClick="setBlockUI(this);" value='<spring:message code="systema.skat.search"/>'>
		           		</tr>
		           		</form>
		           		</table>
					</td>
					</tr>
					 								           		
	           		<tr height="20"><td></td></tr>
					
					<tr class="text14" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:30em;">
					
					<%-- this is the datatables grid (content)--%>
					<form action="N/A_is_done_with_jquery....?action=doFind" name="searchForm" id="searchForm" method="post">
						
					<table id="angivelseList" class="display compact cell-border" width="100%" >
						<thead>
						<tr class="tableHeaderField">
							<th class="text12">&nbsp;Vælg&nbsp;</th>
							<th class="text14" title="avd">&nbsp;<spring:message code="systema.skat.export.list.search.label.avd"/>&nbsp;</th>
		                    <th class="text14" title="sign">&nbsp;<spring:message code="systema.skat.export.list.search.label.signatur"/>&nbsp;</th>
		                    <th class="text14" title="opd">&nbsp;<spring:message code="systema.skat.export.list.search.label.arende"/>&nbsp;</th>
		                    <th class="text14" title="mrn">&nbsp;Mrn&nbsp;</th>
		                    <th class="text14" title="refnr">&nbsp;<spring:message code="systema.skat.export.list.search.label.refnr"/>&nbsp;</th>
		                    <th class="text14" title="trid">&nbsp;Transp.id&nbsp;</th>
		                    <th class="text14" title="dkeh_xref">&nbsp;<spring:message code="systema.skat.export.list.search.label.xrefnr"/>&nbsp;</th>
		                    <th class="text14" title="aart">&nbsp;<spring:message code="systema.skat.export.list.search.label.aart"/>&nbsp;</th>
		                    <th class="text14" title="datum">&nbsp;<spring:message code="systema.skat.export.list.search.label.datum"/>&nbsp;</th>
		                    <th class="text14" title="status">&nbsp;<spring:message code="systema.skat.export.list.search.label.status"/>&nbsp;</th>
		                    <th class="text14" title="avsNavn">&nbsp;<spring:message code="systema.skat.export.list.search.label.avsandare"/>&nbsp;</th>
		                    <th class="text14" title="motNavn">&nbsp;<spring:message code="systema.skat.export.list.search.label.mottagare"/>&nbsp;</th>
		                    <th class="text14" title="dokref">&nbsp;Dok.ref.&nbsp;</th>
		                    
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.angivelseList}" varStatus="counter">    
			               <c:choose>
			                   <c:when test="${empty record.dokref}">    
				               		<tr style="color: #000000;">
				               </c:when>
				               <c:otherwise>
				               		<tr style="color: #4F8A10;background-color: #DFF2BF;">
				               </c:otherwise>
			               </c:choose>
			               <td align="center" class="text14" >
			               		<c:choose>
				               		<c:when test="${empty record.dokref}">
			               				<input class="clazzEksportAware" type="checkbox" value="J" id="syav${record.avd}_syop${record.opd}" name="syav${record.avd}_syop${record.opd}" >
			               			</c:when>
			               			<c:otherwise>
			               				<img title="already picked!" style="cursor:pointer;" src="resources/images/lock.gif" border="0" alt="edit">
			               			</c:otherwise>
		               			</c:choose>
			               </td>
			               <td width="2%" class="text14NoneColor">&nbsp;${record.avd}</td>
			               <td width="2%" class="text14NoneColor">&nbsp;${record.sign}</td>
			               <td width="2%" class="text14MediumBlue" id="avd${record.avd}@opd${record.opd}@xref${record.dkeh_xref}@refnr${record.refnr}@mrn${record.dkeh_mrn}@valuta${record.dkeh_221}@blp${record.dkeh_222}" >&nbsp;${record.opd}</td>
		               	   <td width="2%" class="text14NoneColor">&nbsp;${record.dkeh_mrn}</td>
		               	   <td width="2%" class="text14NoneColor">&nbsp;${record.refnr}</td>
		               	   <td width="2%" class="text14NoneColor">&nbsp;${record.dkeh_181}</td>
		               	   <td width="2%" class="text14NoneColor">&nbsp;${record.dkeh_xref}</td>
		               	   <td width="2%" class="text14NoneColor">&nbsp;${record.aart}</td>
		               	   <td width="2%" class="text14NoneColor">&nbsp;${record.datum}</td>
		               	   <td width="2%" class="text14NoneColor">&nbsp;${record.status}</td>
		               	   <td class="text14NoneColor">&nbsp;${record.avsNavn}</td>
		               	   <td class="text14NoneColor">&nbsp;${record.motNavn}</td>
		               	   <td width="2%" class="text14NoneColor">&nbsp;${record.dokref}</td>
			            </tr> 
			            </c:forEach>
			            </tbody>
		            </table>
		            </form>
	    	        </td>
	    	        
           		</tr>
           		<tr height="10"><td></td></tr>
           		<tr>
		          <td align="left">
		          		&nbsp;<input class="inputFormSubmit" type="button" name="buttonPick" id="buttonPick" value='Pluk'>
		          		&nbsp;&nbsp;&nbsp;<input class="inputFormSubmit" type="button" name="buttonCloseOk" id="buttonCloseOk" value='Importer'>
		          		&nbsp;<input class="inputFormSubmit" type="button" name="cancel" id="cancel" value='Annullér'>
		          </td>
		         </tr>
       			</table>
		</td>
		</tr>

	</table> 
