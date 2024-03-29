<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerSkat.jsp" />
<!-- =====================end header ==========================-->

<SCRIPT type="text/javascript" src="resources/js/skatexport_archive.js?ver=${user.versionEspedsg}"></SCRIPT>

<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr>
		<td>
		<%-- tab container component --%>
		<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
			<tr height="25"> 
				<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkTopicList" tabindex=-1 style="display:block;" href="skatexport.do?action=doFind&sign=${model.sign}">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.export.list.tab"/></font>
						<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					</a>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkHeader" tabindex=-1 style="display:block;" href="skatexport_edit.do?action=doFetch&avd=${model.avd}&opd=${model.opd}
							&sysg=${model.sign}&tuid=${model.tullId}&syst=${model.status}&sydt=${model.datum}">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.export.created.mastertopic.tab"/></font>
						<font class="text14MediumBlue">[${model.opd}]</font>
						<c:if test="${model.status == 'M' || empty model.status || model.status == '1' }">
							<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						</c:if>
					</a>
				</td>
				
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkInvoices" tabindex=-1 style="display:block;" href="skatexport_edit_invoice.do?action=doFetch&avd=${model.avd}&sign=${model.sign}
												&opd=${model.opd}&refnr=${model.refnr}
												&status=${model.status}&datum=${model.datum}">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.export.invoice.tab"/></font>
					</a>
				</td>				
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkItemLines" tabindex=-1 style="display:block;" href="skatexport_edit_items.do?action=doFetch&avd=${model.avd}&sign=${model.sign}
												&opd=${model.opd}&tullId=${model.tullId}
												&status=${model.status}&datum=${model.datum}&fabl=${recordTopicSkat.dkeh_222}">
						<font class="tabDisabledLink">
							&nbsp;<spring:message code="systema.skat.export.item.createnew.tab"/>
						</font>
						<c:if test="${model.status == 'M' || empty model.status || model.status == '1' }">
							<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
						</c:if>
						
					</a>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkLogging" tabindex=-1 style="display:block;" href="skatexport_logging.do?avd=${model.avd}&sign=${model.sign}
												&opd=${model.opd}&tullId=${model.tullId}
												&status=${model.status}&datum=${model.datum}">
						<font class="tabDisabledLink">
							&nbsp;<spring:message code="systema.skat.export.logging.tab"/>
						</font>
						<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
					</a>
				</td>
					
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="15%" valign="bottom" class="tab" align="center" nowrap>
					<font class="tabLink">&nbsp;<spring:message code="systema.skat.export.archive.tab"/></font>
					<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
				</td>
				
				<td width="10%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			</tr>
			<tr height="3"><td></td></tr>
		</table>
		</td>
	</tr>
	

	<%-- list component --%>
	<tr>
		<td>		
		<table width="100%" cellspacing="0" border="0" cellpadding="0">
	    	<%-- separator --%>
	        <tr height="2"><td></td></tr> 
			<tr>
				<td>
				<table width="100%" cellspacing="0" border="0" cellpadding="0">
					<thead>
					<tr class="tableHeaderField" height="20" valign="left">
					
	                    <th class="tableHeaderFieldFirst">&nbsp;<spring:message code="systema.skat.export.archive.list.label.topicNr"/>&nbsp;</th>
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.skat.export.archive.list.label.docType"/>&nbsp;</th> 
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.skat.export.archive.list.label.subject"/>&nbsp;</th> 
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.skat.export.archive.list.label.date"/>&nbsp;</th>
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.skat.export.archive.list.label.time"/>&nbsp;</th>
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.skat.export.archive.list.label.additionalInfo"/>&nbsp;</th>
	                    <th class="tableHeaderField">&nbsp;<spring:message code="systema.skat.document"/></th> 
	                    
	               </tr>  
	               </thead>
	               <tbody>   
		           	<c:forEach items="${list}" var="record" varStatus="counter">    
		               <c:choose>           
		                   <c:when test="${counter.count%2==0}">
		                       <tr class="tableRow" height="20" >
		                   </c:when>
		                   <c:otherwise>   
		                       <tr class="tableOddRow" height="20" >
		                   </c:otherwise>
		               </c:choose>
		               <td class="tableCellFirst" >&nbsp;${model.opd}&nbsp;&nbsp;<font class="text8">[${model.sign}]</font></td>
		               <td class="tableCell">&nbsp;${record.docType}</td>
		               <td class="tableCell" >&nbsp;${record.subject}</td>
		               <td class="tableCell" >&nbsp;${record.createDate}</td>
		               <td class="tableCell" >&nbsp;${record.createTime}</td>
		               <td class="tableCell" >&nbsp;${record.additionalInfo}</td>
		               <%-- <td class="tableCell" >&nbsp;${record.url}</td>  --%>
		               <td class="tableCell" >&nbsp;
		               		<c:choose>
		              		<c:when test="${fn:startsWith(record.url, 'http')}">
								<a href="${record.url}" target="_new" >
			               			<img src="resources/images/pdf.png" border="0" width="16px" height="16px" alt="Visa arkivdokument on cloud" >
			               			${record.documentName}
		               			</a>		              
		               		</c:when>
		               		<c:otherwise>
		               			<a href="skat_export_renderArchive.do?fp=${record.url}" target="_new" >
				               		<img src="resources/images/pdf.png" border="0" width="16px" height="16px" alt="Show archive document" >
				               		${record.documentName}
			               		</a>
		               		</c:otherwise>
		               		</c:choose>
		               		
		               </td>
		            </tr> 
		            </c:forEach>
		            </tbody>
	            </table>
			</td>	
			</tr>
		</table>
		</td>
	</tr>
    
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

