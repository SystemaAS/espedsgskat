<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerSkat.jsp" />
<!-- =====================end header ==========================-->
<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/skatglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/skatnctsexport.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="20%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;<spring:message code="systema.skat.ncts.export.list.tab"/></font>
				<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
				
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a style="display:block;" id="copyFromTransportUppdragLink" runat="server" href="#">
				<%-- <a style="display:block;" href="skatnctsexport_edit.do?action=doPrepareCreate&user=${user.user}"> --%>
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.ncts.export.createnew.tab"/></font>
					<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
					
				</a>
			</td>
			<td width="60%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
		</tr>
	</table>
	</td>
</tr>

<tr>
	<td>
	<%-- search filter component --%>
		
 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
 	        <tr height="3"><td></td></tr>
 	        <form name="skatNctsExportSearchForm" id="searchForm" action="skatnctsexport?action=doFind" method="post" >
 	        <tr>	
                <td class="text14" align="left" >&nbsp;&nbsp;&nbsp;<spring:message code="systema.skat.ncts.export.list.search.label.avd"/></td>
                <td class="text14" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.export.list.search.label.signatur"/></td>
                <td class="text14" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.export.list.search.label.arende"/></td>
                <td class="text14" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.export.list.search.label.lrnNr"/></td>
                <td class="text14" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.export.list.search.label.mrnNr"/></td>
                <td class="text14" align="left" >
				<img onMouseOver="showPop('datum_info');" onMouseOut="hidePop('datum_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
                <spring:message code="systema.skat.ncts.export.list.search.label.datum"/>
                <div class="text11" style="position: relative;" align="left">
				<span style="position:absolute;top:2px; width:250px;" id="datum_info" class="popupWithInputText text11"  >	
	           		Standardsøg (blank dato) gælder <b> 15 dage bagud </b> på det tidspunkt. 
	           		<br/><br/>
					Hvis du ønsker at se længere tilbage i tiden, type, skal du angive fra dato. <br/>
					For eksempel 20131001 leder efter en 1-Okt-2013 til i dag.
	           		
				</span>
				</div>	
                </td>
                <td class="text14" align="left" >Til dato</td>
                <td class="text14" align="left" >
				<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
                <spring:message code="systema.skat.ncts.export.list.search.label.status"/>
                <div class="text11" style="position: relative;" align="left">
				<span style="position:absolute;top:2px; width:250px;" id="status_info" class="popupWithInputText text11"  >	
	           		Kun status <b>M</b>, <b>F</b>, <b>G</b> eller <b>' '</b> kan redigeres.
	           			<ul>
							<c:forEach var="record" items="${model.statusCodeList}" >
			           			<li><b>${record.tkkode}&nbsp;</b>&nbsp;${record.tktxtn}</li>
		           			</c:forEach>
	           			</ul>						
					</span>	
					</div> 
				</td>               
                <td class="text14" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.export.list.search.label.mottagare"/></td>
                <td class="text14" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.export.list.search.label.bruttovikt"/></td>
                <td class="text14" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.export.list.search.label.docRef"/></td>
                <td>&nbsp;</td>
			</tr>
 	        <tr>
				<td align="left" >&nbsp;
           			<select class="selectMediumBlueE2" name="avd" id="avd">
	            		<option value="">-<spring:message code="systema.skat.html.dropdown.select"/>-</option>
	 				  	<c:forEach var="record" items="${model.avdList}" >
                            <option value="${record.avd}"<c:if test="${searchFilterSkatExportNcts.avd == record.avd}"> selected </c:if> >${record.avd}<c:if test="${record.tst == '1'}">&nbsp;(test)</c:if></option>                       	 	
						</c:forEach> 
					</select>
				</td>
				<td align="left" >
           			<select class="selectMediumBlueE2" name="sign" id="sign">
	            		<option value="">-<spring:message code="systema.skat.html.dropdown.select"/>-</option>
	 				  	<c:forEach var="record" items="${model.signList}" >
                             <option value="${record.sign}"<c:if test="${searchFilterSkatExportNcts.sign == record.sign}"> selected </c:if> >${record.sign}</option>
						</c:forEach> 
					</select>
				</td>
				<td align="left" ><input type="text" class="inputText" name="opd" id="opd" size="10" maxlength="10" value='${searchFilterSkatExportNcts.opd}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="lrnNr" id="lrnNr" size="14" maxlength="35" value='${searchFilterSkatExportNcts.lrnNr}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="mrnNr" id="mrnNr" size="14" maxlength="35" value='${searchFilterSkatExportNcts.mrnNr}'>&nbsp;</td>
				<td align="left" ><input autoComplete="nope" onKeyPress="return numberKey(event)" type="text" class="inputText" name="datum" id="datum" size="10" maxlength="8" value='${searchFilterSkatExportNcts.datum}'>&nbsp;</td>
				<td align="left" ><input autoComplete="nope" onKeyPress="return numberKey(event)" type="text" class="inputText" name="datumt" id="datumt" size="10" maxlength="8" value='${searchFilterSkatExportNcts.datumt}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="status" id="status" size="2" maxlength="1" value='${searchFilterSkatExportNcts.status}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="motNavn" id="motNavn" size="10" maxlength="50" value='${searchFilterSkatExportNcts.motNavn}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="bruttoVikt" id="bruttoVikt" size="10" maxlength="50" value='${searchFilterSkatExportNcts.bruttoVikt}'>&nbsp;</td>
				<td align="left" ><input type="text" class="inputText" name="docRef" id="docRef" size="10" maxlength="50" value='${searchFilterSkatExportNcts.docRef}'>&nbsp;</td>
				
				<td valign="top" align="left" >
                   &nbsp;<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.skat.search"/>'>
                   <img src="resources/images/find.png" border="0" alt="">
                </td>
			</tr>
			</form>
			<tr height="10"><td></td></tr>
		</table>
	</td>
	</tr>
	<tr height="3"><td></td></tr>
	<%-- Validation errors --%>
	<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
	<tr>
		<td>
           	<table width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
           	<tr>
			<td class="textError">					
	            <ul>
	            <c:forEach var="error" items="${errors.allErrors}">
	                <li >
	                	<spring:message code="${error.code}" text="${error.defaultMessage}"/>
	                </li>
	            </c:forEach>
	            </ul>
			</td>
			</tr>
			</table>
		</td>
	</tr>
	</spring:hasBindErrors>	
	<%-- list component --%>
	<c:if test="${not empty list}">
	<tr>
		<td width="100%" >
	     <table width="100%" >
	     	<tr >
			<td class="text14" align="right">
	        		<a href="skatNctsExportMainListExcelView.do" target="_blank">
               		<img id="itemListExcel" src="resources/images/excel.png" border="0" alt="excel">&nbsp;Excel
	        		</a>&nbsp;
	        	</td>	
		</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>		
		<table width="100%" cellspacing="0" border="0" cellpadding="0">
	    	<%-- separator --%>
	        <tr height="1"><td></td></tr> 
			<tr>
				<td>
				<table width="100%" cellspacing="0" border="0" cellpadding="0">
					<thead>
					<tr class="tableHeaderField" height="20" valign="left">
	                    <th class="tableHeaderFieldFirst" align="left" >&nbsp;&nbsp;&nbsp;<spring:message code="systema.skat.ncts.export.list.search.label.avd"/></th>
                		<th class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.export.list.search.label.signatur"/></th>
                		<th class="tableHeaderField" align="center" >&nbsp;<spring:message code="systema.skat.ncts.export.list.search.label.update"/></th>
                		<th class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.export.list.search.label.arende"/></th>
                		<th class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.export.list.search.label.lrnNr"/></th>
                		<th class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.export.list.search.label.mrnNr"/></th>
                		<th class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.export.list.search.label.datum"/></th>
                		<th class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.export.list.search.label.status"/></th>
                		<th class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.export.list.search.label.mottagare"/></th>
                		<th class="tableHeaderField" align="left" >&nbsp;&nbsp;<spring:message code="systema.skat.ncts.export.list.search.label.bruttovikt"/></th>
                		<th class="tableHeaderField">&nbsp;<spring:message code="systema.skat.copy"/>&nbsp;</th>
	                    
                	</tr> 
                	</thead>
                	<tbody>    
		           	<c:forEach items="${list}" var="topic" varStatus="counter">    
		               <c:choose>           
		                   <c:when test="${counter.count%2==0}">
		                       <tr class="tableRow" height="20" >
		                   </c:when>
		                   <c:otherwise>   
		                       <tr class="tableOddRow" height="20" >
		                   </c:otherwise>
		               </c:choose>
		               <td class="tableCellFirst" width="5%">&nbsp;${topic.avd}</td>
		               <td class="tableCell" >&nbsp;${topic.sign}</td>
		               <td class="tableCell" align="center" >
              	   	   		<c:if test="${empty topic.status || topic.status=='M' ||  topic.status=='G' ||  topic.status=='F'}">
              	   	   			<%-- only M, null or G (garantifel) are editable in NCTS --%>
              	   	   			<a id="alinkCurrentHeaderId_${counter.count}" onClick="setBlockUI(this);"  href="skatnctsexport_edit.do?action=doFetch&avd=${topic.avd}&opd=${topic.opd}&sysg=${topic.sign}&tuid=${topic.lrnNr}&syst=${topic.status}&sydt=${topic.datum}">
	               					<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
	               				</a>	
	               			</c:if>
	               	   </td>
               		   <td class="tableCell" width="10%" >&nbsp;
	               	   		<a id="alinkCurrentHeaderOpdId_${counter.count}" onClick="setBlockUI(this);"  href="skatnctsexport_edit.do?action=doFetch&avd=${topic.avd}&opd=${topic.opd}&sysg=${topic.sign}&tuid=${topic.lrnNr}&syst=${topic.status}&sydt=${topic.datum}">
	               	   			&nbsp;&nbsp;${topic.opd}
	               			</a>
               		   </td>
		               <td class="tableCell" >&nbsp;${topic.lrnNr}</td>
		               <td class="tableCell" >&nbsp;${topic.mrnNr}</td>
		               <td class="tableCell" >&nbsp;${topic.datum}</td>
		               <td class="tableCell" >&nbsp;<b>${topic.status}</b></td>
		               <td class="tableCell" >&nbsp;${topic.motNavn}</td>
		               <td class="tableCell" >&nbsp;${topic.bruttoVikt}</td>
    		               <td align="center" class="tableCell" width="10%">&nbsp;
		               		
		               		<%-- <button class="buttonGray" type="button" name="copyButton${counter.count}" id="copyButton${counter.count}" >Kopiera</button>	 --%>
							<a class="copyLink" id="copyLink${counter.count}" runat="server" href="#">
								<img src="resources/images/copy.png" border="0" alt="copy">
								&nbsp;<spring:message code="systema.skat.copy"/>
							</a>
							 
							<div style="display: none;" class="clazz_dialog" id="dialog${counter.count}" title="Dialog">
								<form  action="skatnctsexport_copyTopic.do" name="copyForm${counter.count}" id="copyForm${counter.count}" method="post">
								 	<input type="hidden" name="action${counter.count}" id="action${counter.count}" value='doFetch'/>
									<input type="hidden" name="originalAvd${counter.count}" id="originalAvd${counter.count}" value='${topic.avd}'/>
				 					<input type="hidden" name="originalOpd${counter.count}" id="originalOpd${counter.count}" value='${topic.opd}'/>
					 					
									<p class="text14" ><spring:message code="systema.skat.export.dialogCopy.p1"/></p>
									<p class="text14" ><spring:message code="systema.skat.export.dialogCopy.p2"/></p>
									
									
									<table>
										<tr>
											<td class="text14" align="left" >&nbsp;<spring:message code="systema.skat.export.dialogCopy.table.tr1.td1"/></td>
	                							<td class="text14" align="left" >&nbsp;<spring:message code="systema.skat.export.dialogCopy.table.tr1.td2"/></td>
	                						</tr>
	 									<tr>
											<td class="text14MediumBlue">
												<select class="newAvd" name="newAvd${counter.count}" id="newAvd${counter.count}">
								            		<option value="">-<spring:message code="systema.skat.html.dropdown.select"/>-</option>
								 				  	<c:forEach var="record" items="${model.avdList}" >
							                             	 	<option value="${record.avd}">${record.avd}</option>
													</c:forEach> 
												</select>
											</td>
											<td class="text14MediumBlue">
												<select class="newSign" name="newSign${counter.count}" id="newSign${counter.count}">
								            		<option value="">-<spring:message code="systema.skat.html.dropdown.select"/>-</option>
								 				  	<c:forEach var="record" items="${model.signList}" >
							                             	 	<option value="${record.sign}">${record.sign}</option>
													</c:forEach> 
												</select>
											</td>
										</tr>
									</table>
								</form>
							</div>
							
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
    </c:if> 
    
    <tr>
		<td>
			<div id="dialogCopyFromTransportUppdrag" title="Dialog">
				<form  action="skatnctsexport_doFetchTopicFromTransportUppdrag.do" name="copyFromTransportUppdragForm" id="copyFromTransportUppdragForm" method="post">
				 	<input type="hidden" name="actionGS" id="actionGS" value='doUpdate'/>
						
					<p class="text14" ><spring:message code="systema.skat.nctsexport.dialogCopyFromTransportUppdrag.p1"/></p>
					<p class="text14"><spring:message code="systema.skat.nctsexport.dialogCopyFromTransportUppdrag.p2"/></p>
					<ol class="text14" ><spring:message code="systema.skat.nctsexport.dialogCopyFromTransportUppdrag.ol"/></ol>
					<p class="text14" ><spring:message code="systema.skat.nctsexport.dialogCopyFromTransportUppdrag.p3"/></p>
					
					<table>
						<tr>
							<td class="text14" align="left" >&nbsp;<spring:message code="systema.skat.nctsexport.dialogCopyFromTransportUppdrag.table.tr1.td1"/></td>
   							<td class="text14" align="left" >&nbsp;<spring:message code="systema.skat.nctsexport.dialogCopyFromTransportUppdrag.table.tr1.td2"/></td>
   							<td class="text14" align="left" >&nbsp;<spring:message code="systema.skat.nctsexport.dialogCopyFromTransportUppdrag.table.tr1.td3"/>&nbsp;</td>
   						</tr>
						<tr>
							<td class="text14MediumBlue">
								<select class="selectMediumBlueE2" name="selectedAvd" id="selectedAvd">
				            		<option value="">-<spring:message code="systema.skat.html.dropdown.select"/>-</option>
				 				  	<c:forEach var="record" items="${model.avdList}" >
			                             	 	<option value="${record.avd}" <c:if test="${user.asavd == record.avd}"> selected </c:if> >${record.avd}</option>
									</c:forEach> 
								</select>
							</td>
							<td class="text14MediumBlue">
								<input type="text" class="inputText" id="selectedOpd" name="selectedOpd" size="10" maxlength="35" value=''>&nbsp;
							</td>
							<td class="text14MediumBlue">
								<input type="text" class="inputText" id="selectedExtRefNr" name="selectedExtRefNr" size="25" maxlength="35" value=''>&nbsp;
								<a tabindex="-1" id="extRefIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</td>
	</tr>
    
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

