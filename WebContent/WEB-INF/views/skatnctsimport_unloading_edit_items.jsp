<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerSkat.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/skatglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>		
	<SCRIPT type="text/javascript" src="resources/js/skatnctsimport_unloading_edit_items.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		
		<tr height="2"><td></td></tr>
		<tr height="25"> 
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkTopicList" tabindex=-1 style="display:block;" href="skatnctsimport.do?action=doFind&sign=${recordTopicSkatNctsImport.tisg}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.ncts.import.list.tab"/></font>
					<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
					
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkHeader" tabindex=-1 style="display:block;" href="skatnctsimport_edit.do?action=doFetch&avd=${recordTopicSkatNctsImport.tiavd}&opd=${recordTopicSkatNctsImport.titdn}
						&sysg=${recordTopicSkatNctsImport.tisg}&syst=${recordTopicSkatNctsImport.tist}&sydt=${recordTopicSkatNctsImport.tidt}">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.ncts.import.created.mastertopic.tab"/></font>
					<font class="text12MediumBlue">[${recordTopicSkatNctsImport.titdn}]</font>
					<c:if test="${ recordTopicSkatNctsImport.tist == 'F' || recordTopicSkatNctsImport.tist == 'M' || empty recordTopicSkatNctsImport.tist}">
						<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
					</c:if>
				</a>
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkItemLines" tabindex=-1 style="display:block;" href="skatnctsimport_edit_items.do?action=doFetch&avd=${recordTopicSkatNctsImport.tiavd}&sign=${recordTopicSkatNctsImport.tisg}
											&opd=${recordTopicSkatNctsImport.titdn}&mrnNr=${recordTopicSkatNctsImport.titrnr}&godsNr=${recordTopicSkatNctsImport.tign}
											&status=${recordTopicSkatNctsImport.tist}&datum=${recordTopicSkatNctsImport.tidt}">
				<font class="tabDisabledLink">
					&nbsp;<spring:message code="systema.skat.ncts.import.item.createnew.tab"/>
				</font>
				<c:if test="${recordTopicSkatNctsImport.tist == 'F' || recordTopicSkatNctsImport.tist == 'M' || empty recordTopicSkatNctsImport.tist}">
					<img valign="bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="create new">
				</c:if>
				</a>
			</td>
			
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkUnloading" tabindex=-1 style="display:block;" href="skatnctsimport_unloading_edit.do?avd=${recordTopicSkatNctsImport.tiavd}&sign=${recordTopicSkatNctsImport.tisg}
											&opd=${recordTopicSkatNctsImport.titdn}&mrnNr=${recordTopicSkatNctsImport.titrnr}&godsNr=${recordTopicSkatNctsImport.tign}
											&status=${recordTopicSkatNctsImport.tist}&datum=${recordTopicSkatNctsImport.tidt}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.skat.ncts.import.unloading.createnew.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/unloading.png" width="16" hight="16" border="0" alt="show log">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">
					&nbsp;<spring:message code="systema.skat.ncts.import.unloading.item.createnew.tab"/>
				</font>
				<img style="vertical-align: bottom" src="resources/images/add.png" width="12" hight="12" border="0" alt="item lines">
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkLogging" tabindex=-1 style="display:block;" href="skatnctsimport_logging.do?avd=${recordTopicSkatNctsImport.tiavd}&sign=${recordTopicSkatNctsImport.tisg}
									&opd=${recordTopicSkatNctsImport.titdn}&mrnNr=${recordTopicSkatNctsImport.titrnr}&godsNr=${recordTopicSkatNctsImport.tign}
									&status=${recordTopicSkatNctsImport.tist}&datum=${recordTopicSkatNctsImport.tidt}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.skat.ncts.import.logging.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/log-icon.png" width="16" hight="16" border="0" alt="show log">
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkArchive" tabindex=-1 style="display:block;" href="skatnctsimport_archive.do?avd=${recordTopicSkatNctsImport.tiavd}&sign=${recordTopicSkatNctsImport.tisg}
									&opd=${recordTopicSkatNctsImport.titdn}&mrnNr=${recordTopicSkatNctsImport.titrnr}&godsNr=${recordTopicSkatNctsImport.tign}
									&status=${recordTopicSkatNctsImport.tist}&datum=${recordTopicSkatNctsImport.tidt}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.skat.ncts.import.archive.tab"/>
					</font>
					<img style="vertical-align: bottom" src="resources/images/archive.png" width="16" hight="16" border="0" alt="show archive">
				</a>
			</td>
			<td width="13%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					 	
		</tr>
	</table>
	<%-- -------------------------------- --%>	
 	<%-- tab area container MASTER TOPIC  --%>
	<%-- -------------------------------- --%>
 	<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
		<tr height="15"><td colspan="2">&nbsp;</td></tr>	
		
		<tr>
		<td >
		<table border="0" width="95%" align="center" border="0">
			<tr>
	 			<td >		
	 				<%-- MASTER Topic header --%>
	 				<table width="100%" align="center" class="formFrameHeaderTransparent" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text11MediumBlue">
				 				&nbsp;Afd&nbsp;<b>${recordTopicSkatNctsImport.tiavd}</b>
				 				&nbsp;Angivelse&nbsp;<b>${recordTopicSkatNctsImport.titdn}</b>
				 				&nbsp;Sign&nbsp;<b>${recordTopicSkatNctsImport.tisg}</b>
				 				
				 				&nbsp;
				 				<img onMouseOver="showPop('status_info');" onMouseOut="hidePop('status_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
           						Status:&nbsp;<b>${recordTopicSkatNctsImport.tist}</b>
				 				&nbsp;&nbsp;
								<span title="tienkl">Type procedure:</span>&nbsp;
								<c:if test="${recordTopicSkatNctsImport.tienkl == 'J'}"><b>Forenklet</b></c:if>
								<c:if test="${recordTopicSkatNctsImport.tienkl == 'N'}"><b>Normal</b></c:if>
				 				&nbsp;&nbsp;&nbsp;Mrn-nr:&nbsp;<b>${recordTopicSkatNctsImport.titrnr}</b>
				 				&nbsp;&nbsp;&nbsp;Gods-nr:&nbsp;<b>${recordTopicSkatNctsImport.tign}</b>
			 				
			 				<div class="text11" style="position: relative;" align="left">
							<span style="position:absolute;top:2px; width:250px;" id="status_info" class="popupWithInputText text11"  >
				           		Kun <b>M</b>, F eller <b>' '</b> kan redigeres.
				           			<ul>
										<c:forEach var="record" items="${model.statusCodeList}" >
						           			<li><b>${record.tkkode}&nbsp;</b>&nbsp;${record.tktxtn}</li>
					           			</c:forEach>
				           			</ul>
							</span>
							</div>
							</td>
		 				</tr>
		 				<tr height="20"><td></td></tr>
	 				</table>
	 				
					<%-- MASTER Topic information [it is passed through a session object: recordTopicSkatNctsImport] --%>
				 	<table height="40" width="100%" align="center" class="formFrameTitaniumWhite" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="2"><td class="text" align="left" colspan="2"></td></tr>
				 		<tr>
					 		<td width="50%">
						 		<table width="80%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text11Bold" align="left" >Ansvarlig&nbsp;</td>
							            <td class="text11" align="left" ></td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left" >Kundenr&nbsp;</td>
							            <td class="text11MediumBlue" align="left" >${recordTopicSkatNctsImport.tikn}</td>
							        </tr>
							        							        
							        <tr>
							            <td width="30%" class="text11" align="left">TIN-nr&nbsp;</td>
							           	<td class="text11MediumBlue" align="left"><b>${recordTopicSkatNctsImport.titin}</b></td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left">Navn&nbsp;</td>
							           	<td class="text11MediumBlue" align="left"><b>${recordTopicSkatNctsImport.tina}</b></td>
							        </tr>
								</table>
					        </td>
							<td width="50%">
						 		<table width="80%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td class="text11" align="left" >&nbsp;</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Adresse&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkatNctsImport.tiad1}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left">By&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkatNctsImport.tipn}&nbsp;${recordTopicSkatNctsImport.tips}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Landkode
							            </td>
							           	<td class="text11MediumBlue" align="left">${recordTopicSkatNctsImport.tilk}</td>
							        </tr>
							        
			        	        </table>
					        </td>					        
				        </tr>
				        
					</table>
            	</td>
           	</tr> 
           	<%-- ---------------------- --%>
           	<%-- LIST of existent ITEMs --%>
           	<%-- ---------------------- --%>
           	<tr>
				<td>
					<table width="100%" cellspacing="0" border="0" cellpadding="0">
	    				<%-- separator --%>
	        			<tr height="10"><td></td></tr> 
						<tr >
							<form id="createNewItemLine" action="skatnctsimport_unloading_edit_items.do">
								<input type="hidden" name="action" id="action" value='doFetch'>
				 				<input type="hidden" name="avd" id="avd" value='${model.avd	}'>
				 				<input type="hidden" name="renew" id="renew" value='1'>
				 				<input type="hidden" name="sign" id="sign" value='${model.sign}'>
								<input type="hidden" name="opd" id="opd" value='${model.opd}'>
				 				<input type="hidden" name="mrnNr" id="mrnNr" value='${model.mrnNr}'>
				 				<input type="hidden" name="status" id="status" value='${model.status}'>
				 				<input type="hidden" name="datum" id="datum" value='${model.datum}'>
				 					
							<td class="text12Bold">&nbsp;Vareposter&nbsp;&nbsp;
								<input tabindex=-1 class="inputFormSubmitStd" type="submit" name="submit" value='Skapa ny'>
								<button name="allItemsButton" class="inputFormSubmitStd" type="button" onClick="showPop('allItems');" >Vis alle</button> 
							        <span style="background-color:#EEEEEE; position:absolute; left:50px; top:200px; width:1100px; height:1000px;" id="allItems" class="popupWithInputTextThickBorder"  >
						           		<div class="ownScrollableSubWindow" style="width:1080px; height:900px; margin:10px;">
						           			<nav>
						           			<table width="100%" border="0" align="left" cellspacing="2">
						           			<tr>
							           			<td colspan="3" class="text14"><b>Varelinjer</b></td>
							           		</tr>
								           	<tr>	
												<td >
												<table width="100%" cellspacing="0" border="0" cellpadding="0">
													<tr class="tableHeaderField" height="20" valign="left">
													    <td class="tableHeaderFieldFirst">&nbsp;Linjenr.&nbsp;</td>   
													    <td class="tableHeaderField">&nbsp;Varekode&nbsp;</td>   
									                    <td class="tableHeaderField">&nbsp;Dok.type&nbsp;</td>
									                    <td class="tableHeaderField">&nbsp;Dok.ref&nbsp;</td>
									                    <td class="tableHeaderField">&nbsp;Bruttovægt&nbsp;</td>
									                    <td class="tableHeaderField">&nbsp;Nettovægt&nbsp;</td>
									                    <td class="tableHeaderField">&nbsp;Kollislag</td>
									                    <td class="tableHeaderField">&nbsp;Kolliantal</td>
									                    <td class="tableHeaderField">&nbsp;Varebeskrivelse&nbsp;</td>
									                    <td align="center" class="tableHeaderField" nowrap>Fjern</td>
									                    	
									               </tr>  
										           <c:forEach items="${model.list}" var="record" varStatus="counter">    
										               <c:choose>           
										                   <c:when test="${counter.count%2==0}">
										                       <tr class="tableRow" height="20" >
										                   </c:when>
										                   <c:otherwise>   
										                       <tr class="tableOddRow" height="20" >
										                   </c:otherwise>
										               </c:choose>
										               <%-- <td class="tableCellFirst" width="2%">&nbsp;${counter.count}</td> --%>
										               <td width="2%" class="tableCellFirst" align="center">&nbsp;${record.tvli}</td>
										               
										               <td class="tableCell" >&nbsp;${record.nvvnt}</td>
										               <td class="tableCell" >&nbsp;${record.nvdty}</td>
										               <td class="tableCell" >&nbsp;${record.nvdref}</td>
										               <td class="tableCell" align="right" >&nbsp;${record.nvvktb}&nbsp;</td>
										               <td class="tableCell" align="right" >&nbsp;${record.nvvktn}&nbsp;</td>
										               <td class="tableCell" >&nbsp;${record.nveh}</td>
										               <td class="tableCell" >&nbsp;${record.nvnt}</td>
										               <td width="40%" class="tableCell" width="100" >&nbsp;${record.nvvt}</td>
										               <td class="tableCell" align="center" nowrap>&nbsp;
										               	<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="skatnctsimport_unloading_edit_items.do?action=doDelete&avd=${record.tvavd}&opd=${record.tvtdn}&lin=${record.tvli}">
										               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
										               	</a>	
										               </td>	
											           </tr>
											        <%-- <c:set var="numberOfItemLinesInTopic" value="${counter.count}" scope="request" /> --%>
											        <c:set var="numberOfItemLinesInTopic" value="${record.tvli}" scope="request" />
											         
										            </c:forEach>
										        </table>
												</td>											           		
									         </tr>
									         <tr>
						           				<td>
						           				<table >
												<%-- OK BUTTON --%>
						           				<tr align="left" >
													<td class="text11"><button name="allItemsButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('allItems');">&nbsp;Ok</button> 
													</td>
												</tr>
												</table>
												</td>
											</tr>
									           		
							   			</table>
							   			</nav>
							   			</div>
					   				</span>	
							</td>
							</form>
						</tr> 
						<tr>
							<td class="ownScrollableSubWindow" style="width:1150px; height:10em;">
								<table width="100%" cellspacing="0" border="0" cellpadding="0">
									<tr class="tableHeaderField" height="20" valign="left">
									    <td class="tableHeaderFieldFirst">&nbsp;Linjenr.&nbsp;</td>
					                    <td class="tableHeaderField">&nbsp;Varekode&nbsp;</td>   
					                    <td class="tableHeaderField">&nbsp;Dok.type&nbsp;</td>
					                    <td class="tableHeaderField">&nbsp;Dok.ref&nbsp;</td>
					                    <td class="tableHeaderField">&nbsp;Bruttovægt&nbsp;</td>
					                    <td class="tableHeaderField">&nbsp;Nettovægt&nbsp;</td>
					                    <td class="tableHeaderField">&nbsp;Kollislag</td>
					                    <td class="tableHeaderField">&nbsp;Kolliantal</td>
					                    <td class="tableHeaderField">&nbsp;Varebeskrivelse&nbsp;</td>
					                    <td align="center" class="tableHeaderField" nowrap>Fjern</td>
					               </tr> 
					               
					               <form name="formItemList" id="formItemList" method="POST" >
					               		<input type="hidden" name="opdItemList" id="opdItemList" value='${model.opd}'>
				 						<input type="hidden" name="avdItemList" id="avdItemList" value='${model.avd}'> 
				 						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
				 						 
							           <c:forEach items="${model.list}" var="record" varStatus="counter">    
							               <c:choose>           
							                   <c:when test="${counter.count%2==0}">
							                       <tr class="tableRow" height="20" >
							                   </c:when>
							                   <c:otherwise>   
							                       <tr class="tableOddRow" height="20" >
							                   </c:otherwise>
							               </c:choose>
							               <%-- <td class="tableCellFirst" width="2%">&nbsp;${counter.count}</td> --%>
							               <td width="2%" class="tableCellFirst" align="center">&nbsp;
							               		<a tabindex=-1 id="recordUpdate_${counter.count}_${record.tvli}" href="#" onClick="getItemData(this);">${record.tvli}
							               			<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">&nbsp;
							               		</a>
								               	
						               	   </td>
							               	
							               <td class="tableCell" >&nbsp;${record.nvvnt}</td>
							               <td class="tableCell" >&nbsp;${record.nvdty}</td>
							               <td class="tableCell" >&nbsp;${record.nvdref}</td>
							               <td class="tableCell" align="right" >&nbsp;${record.nvvktb}&nbsp;</td>
							               <td class="tableCell" align="right" >&nbsp;${record.nvvktn}&nbsp;</td>
							               <td class="tableCell" >&nbsp;${record.nveh}</td>
							               <td class="tableCell" >&nbsp;${record.nvnt}</td>
							               
							               <td class="tableCell" ><div style="width:120px">&nbsp;${record.nvvt}</div></td>
							               <td class="tableCell" align="center" nowrap>&nbsp;
							               	<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="skatnctsimport_unloading_edit_items.do?action=doDelete&avd=${record.tvavd}&opd=${record.tvtdn}&lin=${record.tvli}">
							               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
							               	</a>	
							               </td>	
							               
							               
								           </tr>
								        <%-- <c:set var="numberOfItemLinesInTopic" value="${counter.count}" scope="request" /> --%>
								        <c:set var="numberOfItemLinesInTopic" value="${record.tvli}" scope="request" />
								         
							            </c:forEach>
						            </form>	
					            </table>
							</td>	
						</tr>
					</table>
				</td>
			</tr>
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
			<%-- ------------------------------------------------- --%>
           	<%-- DETAIL Section - Create Item line PRIMARY SECTION --%>
           	<%-- ------------------------------------------------- --%>
           	<tr>
	 			<td >
	 			<form name="nctsImportEditTopicItemForm" id="nctsImportEditTopicItemForm" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="opd" id="opd" value='${model.opd}'/>
				 	<input type="hidden" name="avd" id="avd" value='${model.avd}'/>
				 	<input type="hidden" name="sign" id="sign" value='${model.sign}'/>
				 	<input type="hidden" name="status" id="status" value='${model.status}'/>
				 	<input type="hidden" name="datum" id="datum" value='${model.datum}'/>
				 	<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
				 	<input type="hidden" name="numberOfItemLinesInTopic" id="numberOfItemLinesInTopic" value='${numberOfItemLinesInTopic}' />
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="100%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White">
				 				<b>&nbsp;&nbsp;Varelinje&nbsp;-&nbsp;Kontrolresultat&nbsp;&nbsp;</b>
				 				<img src="resources/images/update.gif" border="0" alt="edit">
				 				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 				<input tabindex=-1 align="center" class="text12BoldLightGreenForItemLinenr" readonly type="text" name="lineNr" id="lineNr" size="3" value="">
			 				</td>
			 				<td class="text12White" align="right">
				 				Konform&nbsp;&nbsp;<font class="text16RedBold">${ recordTopicSkatNctsImportUnloading.nikonf }</font>&nbsp;&nbsp;&nbsp;
				 			</td>
		 				</tr>
	 				</table>
	 				
					<table width="100%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="4"><td class="text" align="left"></td></tr>
				 		<tr>
					 		<td >
						 		<table width="100%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							 			<td class="text12" align="left" valign="bottom">
							 				<img onMouseOver="showPop('control_code_info');" onMouseOut="hidePop('control_code_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<c:if test="${recordTopicSkatNctsImportUnloading.nikonf != '1'}">
								 				<font class="text16RedBold" >*</font>
							 				</c:if>
							 				<span title="nvct" >Kode</span>
							 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="control_code_info" class="popupWithInputText text11"  >
							           			<b>Kode</b>
												<br/><br/>
												 (Hvis losningsresultat = 0)
												 <ul>
												 	<li><b>DI</b> = Afvigelse i værdi.</li>
												 	<li><b>NE</b> = New entry. I forbindelse med den nye linje</li>
												 	<li><b>OT</b> = Andre ting at rapportere</li>
												 	<li><b><font style="color: red;">OR</font></b> = Den oprindelige vareposten rapporteret. Not needed by the end-user.</li>
												 	<li><b><font style="color: red;">NP</font></b> = Dokumenter / Certifikater ikke forelagt. Not needed by the end-user. Not present. Is fixed automatically by the program</li>
												 </ul>
											</span>
											</div>
							 			</td>
							 			<td class="text12" align="left">&nbsp;&nbsp;
							            		<span title="nvctsk" >Sprogkode</span>
							            </td>
							            <td colspan="4" class="text12" align="left" valign="bottom">&nbsp;
											<span title="nvctb" >Bemærkning</span>
										</td>
										<%-- This pointer (Peker) is automatically fixed by the AS400 service
										  -- The final EDIFACT segment: FTX+ABV++NP+44# should be written without the need of this field
										  --
										<td class="text14" align="left">&nbsp;
											<img onMouseOver="showPop('pointer_info');" onMouseOut="hidePop('pointer_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<span title="nvctp" >Peker</span>
							 				<span style="position:absolute; left:900px; top:250px; width:250px; height:250px;" id="pointer_info" class="popupWithInputText"  >
							           		<div class="text11" align="left">
							           			<br/>
							           			<b>Peker</b>
												<p>
													TR0010<br/>													
													This data item is Required when the accompanying Control Indicator (<b>Kode</b>) is equal to <b>NP</b>.<br/>  
													In all other cases, this data item must not be used.
												</p>
						           			</div>
											</span>
							            </td>
							             --%>
							        </tr>
							        <tr>
							        		<td class="text12" align="left">
							        		<c:choose>
							        		<c:when test="${recordTopicSkatNctsImportUnloading.nikonf != '1'}">
												<select class="inputTextMediumBlueMandatoryField" name="nvct" id="nvct">
													<option value=""<c:if test="${model.record.nvct == ''}"> selected </c:if> >-vælg-</option>
												  	<option value="DI"<c:if test="${model.record.nvct == 'DI'}"> selected </c:if> >DI</option>
												  	<option value="NE"<c:if test="${model.record.nvct == 'NE'}"> selected </c:if> >NE</option>
												  	<option value="OT"<c:if test="${model.record.nvct == 'OT'}"> selected </c:if> >OT</option>
												</select>
											</c:when >
											<c:otherwise>
												<select class="inputTextMediumBlue" name="nvct" id="nvct">
													<option value=""<c:if test="${model.record.nvct == ''}"> selected </c:if> >-vælg-</option>
												  	<option value="DI"<c:if test="${model.record.nvct == 'DI'}"> selected </c:if> >DI</option>
												  	<option value="NE"<c:if test="${model.record.nvct == 'NE'}"> selected </c:if> >NE</option>
												  	<option value="OT"<c:if test="${model.record.nvct == 'OT'}"> selected </c:if> >OT</option>
												</select>
											</c:otherwise>
											</c:choose>
											
			 			            		</td>
							        		<td >
											<select name="nvctsk" id="nvctsk">
							            			<option value="">-vælg-</option>
						 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.nvctsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach>
											</select>
											<a tabindex="-1" id="nvctskIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>
										</td>
										<td colspan="4" class="text12" align="left" >
							        			<input type="text" class="inputText" name="nvctb" id="nvctb" size="71" maxlength="70" value="${model.record.nvctb}">
			 			            		</td>
			 			            		
			 			            		<%-- This pointer (Peker) is automatically fixed by the AS400 service
										  -- The final EDIFACT segment: FTX+ABV++NP+44# should be written without the need of this field
										  --
			 			            		<td colspan="2" class="text12" align="left" >
							        			<input type="text" class="inputText" name="nvctp" id="nvctp" size="25" maxlength="35" value="${model.record.nvctp}">
			 			            		</td>
			 			            		--%>
			 			            					 			            		
							        </tr>
							        <tr height="12"><td class="text" align="left"></td></tr>
							        
							 		<tr>
							 			<td class="text12" align="left" valign="bottom">
							 				&nbsp;&nbsp;<span title="tvli" >Linjenr.</span>
							 			</td>
							 			<td class="text12" align="left">
							            		&nbsp;&nbsp;<span title="nvvnt" >Varekode</span>
											<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" onClick="showPop('searchTaricCodesDialog');">
								            <%-- ======================================================== --%>
							            		<%-- Here we have the search Taric codes popup window --%>
							            		<%-- ======================================================== --%>
							            		<span style="position:absolute; left:300px; top:450px; width:500px; height:210px;" id="searchTaricCodesDialog" class="popupWithInputText"  >
								           		<div class="text10" align="left">
								           			<table>
									           			<tr>
									           			<td>
										           			<table>
										           				<tr>
																	<td class="text11">&nbsp;Varekode</td>
																	<td class="text11">&nbsp;<input type="text" class="inputText" name="search_svvs_vata" id="search_svvs_vata" size="10" maxlength="8" value=''></td>
																</tr>
											           			<tr>
											           				<td align="right">&nbsp;<button name="searchTaricCode" class="buttonGray" type="button" onClick="searchTaricVarukod();"><spring:message code="systema.skat.search"/></button></td>
											           				<td class="text11">&nbsp;</td>
												           			
												           		</tr>
												           		<tr height="4"><td ></td></tr>
											           		</table>
										           		</td>
										           		</tr>
														
														<tr>
									           			<td>
										           			<table>							           		
												           		<tr>
											           				<td class="text11">&nbsp;Liste</td>
												           			<td>&nbsp;</td>
												           		</tr>
												           		<tr>
																	<td colspan="2">&nbsp;
																		<select class="text11" id="taricVarukodList" name="taricVarukodList" size="5" onDblClick="hidePop('searchTaricCodesDialog');">
						 													<option selected value="">-vælg-</option>
						 							 					</select>
																	</td>
																</tr>
										           			</table>
									           			</td>
									           			</tr>
															
														<tr>
									           			<td>								           			
															<table width="100%" align="left" border="0">
																<tr align="left" >
																	<td >&nbsp;<button name="searchTaricCodesClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('searchTaricCodesDialog');"><spring:message code="systema.skat.ok"/></button></td>
																</tr>
															</table>
														</td>
														</tr>
													</table>
												</div>
											</span>							            		
							            </td>

							            <td nowrap class="text12" align="left" valign="bottom">
											&nbsp;<span title="nvvtsk" >Sprogkode</span>
										</td>
										<td class="text12" align="left">
							            		&nbsp;&nbsp;<span title="nvvktb" >Brut.vægt(kg)</span>
							            </td>
										<td class="text12" align="left">
							            		&nbsp;&nbsp;<span title="nvvktn" >Net.vægt(kg)</span>
							            </td>
							            <td colspan="3" class="text12" align="left">
							            		&nbsp;<font class="text16RedBold" >*</font><span title="nvvt" >Varebeskrivelse</span>
							            </td>
							        </tr>
							        <tr>
							        		<td class="text12" align="left">
							        			&nbsp;<input tabindex=-1 readonly type="text" class="inputTextReadOnly" name="tvli" id="tvli" size="4" maxlength="5" value="${model.record.tvli}">
			 			            		</td>
							        		<td class="text12" align="left" >
							        			<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="nvvnt" id="nvvnt" size="8" maxlength="6" value="${model.record.nvvnt}">
			 			            		</td>
			 			            		<td >
											<select name="nvvtsk" id="nvvtsk">
							            			<option value="">-vælg-</option>
						 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.nvvtsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach>
											</select>
											<a tabindex="-1" id="nvvtskIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>
										</td>
										<td class="text12" align="left" >
							        			<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="nvvktb" id="nvvktb" size="9" maxlength="9" value="${model.record.nvvktb}">
			 			            		</td>
			 			            		<td class="text12" align="left" >
							        			<input onKeyPress="return amountKey(event)" type="text" class="inputText" name="nvvktn" id="nvvktn" size="9" maxlength="9" value="${model.record.nvvktn}">
			 			            		</td>
			 			            		<td colspan="3" class="text12" align="left" >
							        			<input type="text" class="inputTextMediumBlueMandatoryField" name="nvvt" id="nvvt" size="60" maxlength="70" value="${model.record.nvvt}">
			 			            		</td>
			 			            					 			            		
							        </tr>
							        
							        
							        <tr><td class="text" colspan="9"><hr></td></tr>
							         
							        <tr>
							 			<td class="text12" align="left" valign="bottom">
							 				&nbsp;<b>44.</b>&nbsp;<span title="nvdty" >Dok.type</span>
							 			</td>
							 			<td colspan="2" class="text12" align="left">
							            		&nbsp;<b>44.</b>&nbsp;<span title="nvdref" >Dok.ref.</span>
							            </td>
										<td class="text12" align="left" valign="bottom"><b>44.</b>&nbsp;<span title="nvdsk" >Dok.sprog</span></td>
										
										<td class="text12" align="left">
							            		&nbsp;<b>44.</b>&nbsp;<span title="nvdo" >Met information</span>
							            </td>
							            
										<td class="text12" align="left">
							            		&nbsp;<b>44.</b>&nbsp;<span title="nvdosk" >Sprog</span>
							            </td>
							            
							        </tr>
							        <tr>
							        
										<%-- Doc.Type --%>
										<td class="text12" align="left">
											<select name="nvdty" id="nvdty">
							            		<option value="">-vælg-</option>
							 				  	<c:forEach var="code" items="${model.ncts013_DocType_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.nvdty == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
											</select>										           				
										</td>							        
							        		<td colspan="2" class="text12" align="left" >
							        			<input type="text" class="inputText" name="nvdref" id="nvdref" size="35" maxlength="35" value="${model.record.nvdref}">
			 			            		</td>
			 			            		<td >
											<select name="nvdsk" id="nvdsk">
							            			<option value="">-vælg-</option>
						 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.nvdsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach>
											</select>
											<a tabindex="-1" id="nvdskIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>
										</td>
										<td class="text12" align="left" >
							        			<input type="text" class="inputText" name="nvdo" id="nvdo" size="26" maxlength="26" value="${model.record.nvdo}">
			 			            		</td>
			 			            		
										<td >
											<select name="nvdosk" id="nvdosk">
							            			<option value="">-vælg-</option>
						 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.nvdosk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach>
											</select>
											<a tabindex="-1" id="nvdoskIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>
										</td>
							        </tr>
							        
							        <tr>
							 			<td colspan="3" class="text12" align="left" valign="bottom">
							 				&nbsp;<b>31.</b>&nbsp;<font class="text16RedBold" >*</font><span title="nvmn" >Mærke og nr.</span>
							 			</td>
							 			<td nowrap class="text12" align="left" valign="bottom"><b>31.</b>&nbsp;<span title="nvmnsk" >Godsm.sprog</span></td>
										<td class="text12" align="left">
							            		&nbsp;<b>31.</b>&nbsp;<span title="nvcnr" >Container</span>
							            </td>
							            <td class="text12" align="left">
							            		&nbsp;<b>31.</b>&nbsp;<font class="text16RedBold" >*</font><span title="nveh" >Kollislag</span>
							            </td>
							           <td class="text12" align="left">
							            		&nbsp;<b>31.</b>&nbsp;<span title="nvnt" >Kolliantal</span>
							            </td>
							           <td class="text12" align="left">
							            		&nbsp;<b>31.</b>&nbsp;<span title="nvnteh" >STK</span>
							            </td>
							        </tr>
							        <tr>
							        		<td colspan="3" class="text12" align="left">
							        			<input type="text" class="inputTextMediumBlueMandatoryField" name="nvmn" id="nvmn" size="35" maxlength="42" value="${model.record.nvmn}">
			 			            		</td>
							        		<td >
											<select name="nvmnsk" id="nvmnsk">
							            			<option value="">-vælg-</option>
						 					  	<c:forEach var="code" items="${model.ncts012_Sprak_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.nvmnsk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach>
											</select>
											<a tabindex="-1" id="nvmnskIdLink" OnClick="triggerChildWindowLanguageCodes(this)">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
											</a>
										</td>
										<td class="text12" align="left" >
							        			<input type="text" class="inputText" name="nvcnr" id="nvcnr" size="20" maxlength="17" value="${model.record.nvcnr}">
			 			            		</td>
			 			            		
			 			            		<td class="text12" align="left" >
							        			<select class="inputTextMediumBlueMandatoryField" name="nveh" id="nveh">
							            		<option value="">-vælg-</option>
							 				  	<c:forEach var="code" items="${model.ncts017_Kolli_CodeList}" >
			                                	 	<option value="${code.tkkode}"<c:if test="${model.record.nveh == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
												</c:forEach> 
											</select>
											<a tabindex=-1 href="renderLocalPdf.do?fn=SKAT_EDI_NCTS_emballagekoder_fn_rekomendationer_ncts.pdf" target="_blank">
		 										<img style="cursor:pointer;" src="resources/images/find.png" border="0" alt="search" >
		 									</a>		
			 			            		</td>
			 			            		<td class="text12" align="left" >
							        			<input onKeyPress="return numberKey(event)" align="right" type="text" class="inputText" name="nvnt" id="nvnt" size="6" maxlength="5" value="${model.record.nvnt}">
			 			            		</td>
			 			            		<td class="text12" align="left" >
							        			<input onKeyPress="return numberKey(event)" align="right" type="text" class="inputText" name="nvnteh" id="nvnteh" size="6" maxlength="5" value="${model.record.nvnteh}">
			 			            		</td>
							        </tr>
							        
   							        <tr><td class="text" colspan="9"><hr></td></tr>
   							        <tr>
							 			<td colspan="2" class="text12Bold" align="left" >
							 				&nbsp;&nbsp;Følsomme varer
							 			</td>
							 		</tr>
							        <tr>
							 			<td class="text12" align="left" >
							 				&nbsp;&nbsp;<span title="nvfv" >Kode</span>
							 			</td>
							 			<td colspan="2" class="text12" align="left">
							            		&nbsp;&nbsp;<span title="nvfvnt" >Antal</span>
							            </td>
							        </tr>
							        <tr>
							        		<td class="text12" align="left">
							        			<select name="nvfv" id="nvfv">
								        			<option value="">-vælg-</option>
								 				<option value="1"<c:if test="${model.record.nvfv == '1'}"> selected </c:if> >1</option>
											</select>
			 			            		</td>
							        		<td colspan="2" class="text12" align="left">
							        			<input onKeyPress="return numberKey(event)" align="right" type="text" class="inputText" name="nvfvnt" id="nvfvnt" size="12" maxlength="11" value="${model.record.nvfvnt}">
			 			            		</td>
							        		
							        
						            		<%-- only status = U,H are allowed  --%>
					 				    	<c:choose>
						 				    <c:when test="${ recordTopicSkatNctsImport.tist == 'U' ||  recordTopicSkatNctsImport.tist == 'H' }">
							 				    <td align="center" class="text9BlueGreen" valign="bottom"  >
								 				    &nbsp;&nbsp;&nbsp;<input tabindex=-1 class="inputFormSubmit" type="submit" name="submit" id="submit" onclick="javascript: form.action='skatnctsimport_unloading_edit_items.do';" value="<spring:message code="systema.skat.ncts.import.unloading.createnew.submit"/>"/>
								 				</td>    	
						 				    </c:when>
						 				    <c:otherwise>
							 				    <td  align="center" class="text9BlueGreen" valign="bottom"  >
						 				    		&nbsp;&nbsp;&nbsp;<input disabled class="inputFormSubmitGrayDisabled" type="submit" name="submit" value="<spring:message code="systema.skat.submit.not.editable"/>"/>
						 				    	</td>	
						 				    </c:otherwise>	
					 				    	</c:choose>
							        </form>
 							        <tr height="5"><td></td></tr>
				        	        </table>
					        </td>
				        </tr>
						
						<%-- ----------------- --%>						
						<%-- READ ONLY SECTION --%>
						<%-- ----------------- --%>					
						<tr height="22"><td>&nbsp;</td></tr>
				        <tr>
 					        <td colspan="3">
							<table width="90%" align="left" class="formFrameHeaderBlueWithBorder" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="15">
						 			<td class="text12White">
						 				&nbsp;&nbsp;<b>Varelinje&nbsp;-&nbsp;Original</b>&nbsp;[fra eksportøren]
					 				</td>
				 				</tr>
			 				</table>				        
					        </td>
				        </tr>
				        
				 		<tr>
					 		<td>
						 		<table align="left" width="90%" class="secondarySectionFrame" border="0" cellspacing="0" cellpadding="0">
						 			<tr>
							 			<td class="text12" align="left" valign="bottom">
							 				&nbsp;<span title="tvvnt" >Varekode</span>
							 			</td>
							 			<td class="text12" align="left" valign="bottom">
							            		&nbsp;&nbsp;<span title="tvvtsk" >Sprogkode</span>
							            </td>
										<td class="text12" align="left" valign="bottom">
											&nbsp;<span title="tvvktb" >Brut.vægt(kg)</span>
										</td>
							 			<td class="text12" align="left" valign="bottom">
											&nbsp;<span title="tvvktn" >Net.vægt(kg)</span>
										</td>
							 			<td class="text12" align="left" valign="bottom">
							            		&nbsp;&nbsp;<span title="tvvt" >Varebeskrivelse</span>
							            </td>
							            
							        </tr>
							        <tr>
							        		<td class="text12" align="left">
							        			&nbsp;<input readonly type="text" class="inputTextReadOnly" name="tvvnt" id="tvvnt" size="7" maxlength="6" value="${model.record.tvvnt}">
			 			            		</td>
			 			            		<td class="text12" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvvtsk" id="tvvtsk" size="3" maxlength="2" value="${model.record.tvvtsk}">
										</td>
							        		<td class="text12" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvvktb" id="tvvktb" size="10" maxlength="9" value="${model.record.tvvktb}">
										</td>
										<td class="text12" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvvktn" id="tvvktn" size="10" maxlength="9" value="${model.record.tvvktn}">
										</td>
										<td colspan="4" class="text12" align="left" >
							        			<input readonly type="text" class="inputTextReadOnly" name="tvvt" id="tvvt" size="35" maxlength="70" value="${model.record.tvvt}">
			 			            		</td>
			 			            		
							        </tr>
 							        <tr><td class="text" colspan="6"><hr></td></tr>
						 			<tr>
							 			<td class="text12" align="left" valign="bottom">
							 				&nbsp;<span title="tvdty" ><b>44.</b>&nbsp;Dok.type</span>
							 			</td>
										<td class="text12" align="left" valign="bottom">
											&nbsp;<span title="tvdref"><b>44.</b>&nbsp;Dok.ref.</span>
										</td>
							 			<td class="text12" align="left" valign="bottom">
											&nbsp;<span title="tvdsk"><b>44.</b>&nbsp;Dok.sprog</span>
										</td>
							 			<td nowrap class="text12" align="left" valign="bottom">
							            		&nbsp;<span title="tvdo"><b>44.</b>&nbsp;Met information</span>
							            </td>
							            <td nowrap class="text12" align="left" valign="bottom">
							            		&nbsp;<span title="tvdosk"><b>44.</b>&nbsp;Sprog</span>
							            </td>
							        </tr>
							        <tr>
							        		<td class="text12" align="left">
							        			&nbsp;<input readonly type="text" class="inputTextReadOnly" name="tvdty" id="tvdty" size="5" maxlength="4" value="${model.record.tvdty}">
			 			            		</td>
							        		<td class="text12" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvdref" id="tvdref" size="20" maxlength="35" value="${model.record.tvdref}">
										</td>
										<td class="text12" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvdsk" id="tvdsk" size="3" maxlength="2" value="${model.record.tvdsk}">
										</td>
										<td class="text12" align="left" >
							        			<input readonly type="text" class="inputTextReadOnly" name="tvdo" id="tvdo" size="20" maxlength="26" value="${model.record.tvdo}">
			 			            		</td>
			 			            		<td class="text12" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvdosk" id="tvdosk" size="3" maxlength="2" value="${model.record.tvdosk}">
										</td>
							        </tr>

 							        <tr><td class="text" colspan="6"><hr></td></tr>
						 			<tr>
							 			<td colspan="2" class="text12" align="left" valign="bottom">
							 				&nbsp;<span title="tvmn" ><b>31.</b>&nbsp;Kollimærkning</span>
							 			</td>
										<td class="text12" align="left" valign="bottom">
											&nbsp;<span title="tvmnsk"><b>31.</b>&nbsp;Godsm.sprog</span>
										</td>
							 			<td class="text12" align="left" valign="bottom">
											&nbsp;<span title="tvcnr"><b>31.</b>&nbsp;Container</span>
										</td>
							 			<td class="text12" align="left" valign="bottom">
							            		&nbsp;<span title="tveh"><b>31.</b>&nbsp;Kollislag</span>
							            </td>
							            <td class="text12" align="left" valign="bottom">
							            		&nbsp;<span title="tvnt"><b>31.</b>&nbsp;Kolliantal</span>
							            </td>
							            <td class="text12" align="left" valign="bottom">
							            		&nbsp;<span title="tvnteh"><b>31.</b>&nbsp;STK</span>
							            </td>
							        </tr>
							        <tr>
							        		<td colspan="2" class="text12" align="left">
							        			&nbsp;<input readonly type="text" class="inputTextReadOnly" name="tvmn" id="tvmn" size="20" maxlength="42" value="${model.record.tvmn}">
			 			            		</td>
							        		<td class="text12" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvmnsk" id="tvmnsk" size="3" maxlength="2" value="${model.record.tvmnsk}">
										</td>
										<td class="text12" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvcnr" id="tvcnr" size="20" maxlength="17" value="${model.record.tvcnr}">
										</td>
										
										<td class="text12" align="left" >
							        			<input readonly type="text" class="inputTextReadOnly" name="tveh" id="tveh" size="3" maxlength="2" value="${model.record.tveh}">
			 			            		</td>
			 			            		<td class="text12" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvnt" id="tvnt" size="6" maxlength="5" value="${model.record.tvnt}">
										</td>
										<td class="text12" align="left" >
											<input readonly type="text" class="inputTextReadOnly" name="tvnteh" id="tvnteh" size="6" maxlength="5" value="${model.record.tvnteh}">
										</td>
							        </tr>
							        <tr height="15"><td></td></tr>
			        	        		</table>
					        </td>
				        </tr>
				        <tr height="10"><td class="text" align="left"></td></tr>
				</table>
			</td>
		</tr>
		<tr height="30"><td>&nbsp;</td></tr>
	</table>    
	
	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

