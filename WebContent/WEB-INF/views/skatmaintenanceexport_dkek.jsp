<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerSkatMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/skatmaintenanceexport_dkek.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>


<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr height="15"><td>&nbsp;</td></tr>
	<tr>
		<td>
		<%-- tab container component --%>
		<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
				<tr height="25"> 
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkSadMaintExportGate" tabindex=-1 style="display:block;" href="skatmaintenanceexport.do">
						<font class="tabDisabledLink">&nbsp;SKAT - Vedligehold</font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tab" align="center">
						<font class="tabLink">Kunder vareregister</font>&nbsp;<font class="text14">DKEK</font>&nbsp;
						<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="skatmaintenance_dkek.do?">
							<img style="vertical-align: middle;"  src="resources/images/bulletGreen.png" border="0" width="8px" height="8px" alt="db table">
						</a>
					</td>
					<td width="70%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
				</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<%-- space separator --%>
	 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
	 	    <tr height="30"><td>&nbsp;</td></tr>
	 	    
	 	    <tr >
	 	    	<td width="5%">&nbsp;</td>
				<td width="100%" class="text14">
					<form action="skatmaintenanceexport_dkek.do" name="formRecordSearch" id="formRecordSearch" method="POST" >
						Kundenr&nbsp;
						<input type="text" class="inputTextMediumBlue" name="search_dkek_knr" id="search_dkek_knr" size="10" maxlength="8" value='${model.search_dkek_knr}'>
						<a tabindex="-1" id="search_dkek_knrIdLink">
							<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
						</a>
						&nbsp;&nbsp;<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submitSearch" id="submitSearch" value='<spring:message code="systema.skat.search"/>'/>
						&nbsp;&nbsp;<input type="text" class="inputTextReadOnly" name="search_knavn" id="search_knavn" size="50" maxlength="50" value='${model.search_knavn}'>
					</form>
				</td>
			</tr>
			
			<%-- list component --%>
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="100%">
				<table id="containerdatatableTable" width="98%" cellspacing="1" border="0" align="left">
			    	    <tr>
						<td class="text11">
						<table id="mainList" class="display compact cell-border" >
							<thead>
							<tr>
								<th align="center" width="2%" class="tableHeaderField" >&nbsp;Opdater&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Kundenr&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Kundens varenr&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Varekode&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Varebeskrivelse&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Opr.land&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Proc.&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;K.mærke&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;K.art&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Summ.angiv.&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Suppl.enhed&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Cert.kode&nbsp;</th>
			                    <th align="center" class="tableHeaderField">Fjern</th>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <td id="recordUpdate_${record.dkek_knr}_${record.dkek_vnr}" onClick="getRecord(this);" align="center" width="2%" class="tableCellFirst" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<img src="resources/images/update.gif" border="0" alt="update">
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text14">&nbsp;${record.dkek_knr}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.dkek_vnr}&nbsp;</font></td>
							   <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.dkek_331}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.dkek_315}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.dkek_34a}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.dkek_37}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.dkek_311}&nbsp;</font></td>
							   <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.dkek_314}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.dkek_401a}${record.dkek_402a}&nbsp;${record.dkek_403a}</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.dkek_411}&nbsp;</font></td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.dkek_4421}&nbsp;</font></td>
				               
				               <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<a onclick="javascript:return confirm('Er du sikker på at du vil fjerne denne?')" tabindex=-1 href="skatmaintenanceexport_dkek_edit.do?action=doDelete&dkek_knr=${record.dkek_knr}&dkek_vnr=${record.dkek_vnr}">
					               		<img valign="bottom" src="resources/images/delete.gif" border="0" width="15px" height="15px" alt="remove">
					               	</a>
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
		    
	 	    <tr height="25"><td>&nbsp;</td></tr>
	 	    
	 	    <%-- Validation errors --%>
			<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="100%">
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
	            	<tr >
					<td >					
			            <ul class="isa_error text14" >
			            <c:forEach var="error" items="${errors.allErrors}">
			                <li >
			                	<spring:message code="${error.code}" text="${error.defaultMessage}"/>&nbsp;&nbsp;
			                </li>
			            </c:forEach>
			            </ul>
					</td>
					</tr>
					</table>
				</td>
			</tr>
			</spring:hasBindErrors>
			
			<%-- Other errors (none validation errors) --%>
			<c:if test="${not empty model.errorMessage}">
			<tr>
				<td width="5">&nbsp;</td>
				<td >
	            	<table align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
				 			<td >
				 				<ul class="isa_error text14" >
                                    <li>[ERROR on Update] - Server return code: ${model.errorMessage}</li>                                    
                                </ul>
				 			</td>
						</tr>
					</table>
				</td>
			</tr>
			</c:if>
			<tr height="2"><td>&nbsp;</td>
			</tr>
			<tr >
				<td width="5%">&nbsp;</td>
				<td><button name="newRecordButton" id="newRecordButton" class="inputFormSubmitStd" type="button" >Opret&nbsp;ny</button></td>
			</tr>
	 	    <tr >
	 	    	<td width="5%">&nbsp;</td>
				<td width="100%">
				<form action="skatmaintenanceexport_dkek_edit.do" name="formRecord" id="formRecord" method="POST" >
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="updateId" id=updateId value="${model.updateId}"> 
					<input type="hidden" name="action" id=action value="doUpdate">
					<input type="hidden" name="companyCode" id="companyCode" value="${user.companyCode}">
					
					
					<table width="100%" cellspacing="1" border="0" align="left">
			    	    <tr>
							<td class="text14" title="dkek_knr">&nbsp;<font class="text14RedBold" >*</font>Kundenr</td>
							<td class="text14" title="dkek_vnr">&nbsp;<font class="text14RedBold" >*</font>Kundens varenr</td>
							<td class="text14" title="dkek_331">&nbsp;Varekode</td>
							<td class="text14" title="dkek_315">&nbsp;Varebeskrivelse</td>
							<td class="text14" title="dkek_34a">&nbsp;Opr.land</td>
							<td class="text14" title="dkek_37">&nbsp;Procedure</td>
							<td class="text14" title="dkek_311">&nbsp;Kollimærke</td>
							<td class="text14" title="dkek_314">&nbsp;Kolliart</td>
							<td colspan="2" class="text14" title="dkek_401a-402a/dkek_403a">&nbsp;Summarisk angivelse / Forudgående dokument</td>
							
							
						</tr>
						<tr>
							<td ><input onKeyPress="return numberKey(event)" type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="dkek_knr" id="dkek_knr" size="10" maxlength="8" value='${model.record.dkek_knr}'>
								<a tabindex="-1" id="dkek_knrIdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>
							
							</td>
							<td ><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="dkek_vnr" id="dkek_vnr" size="28" maxlength="28" value='${model.record.dkek_vnr}'></td>
							<td ><input type="text" class="inputTextMediumBlue" name="dkek_331" id="dkek_331" size="10" maxlength="8" value='${model.record.dkek_331}'>
								<a tabindex="-1" id="dkek_331IdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="dkek_315" id="dkek_315" size="25" maxlength="45" value='${model.record.dkek_315}'></td>
							<td >
								<input type="text" class="inputTextMediumBlue" list="dkek_34a_list" name="dkek_34a" id="dkek_34a" size="5" maxlength="2" value='${model.record.dkek_34a}'>
								<datalist id="dkek_34a_list">
								  <c:forEach var="code" items="${model.countryCodeList}" >
				 				  	<option value="${code.dkkd_kd}"<c:if test="${model.record.dkek_34a == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
			 				  	  </c:forEach>  
								</datalist>
								<a tabindex="-1" id="dkek_34aIdLink" OnClick="triggerChildWindowGeneralCodes(this, '008')">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>	 
							</td>
							<td >
								<input type="text" class="inputTextMediumBlue" list="dkek_37_list" name="dkek_37" id="dkek_37" size="10" maxlength="7" value='${model.record.dkek_37}'>
								<datalist id="dkek_37_list">
								  <c:forEach var="code" items="${model.procedureKoderR37CodeList}" >
				 				  	<option value="${code.dkkd_kd}"<c:if test="${model.record.dkek_37 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
			 				  	  </c:forEach>  
								</datalist>
								<a tabindex="-1" id="dkek_37IdLink" OnClick="triggerChildWindowGeneralCodes(this, '112')">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a> 
							</td>
							<td ><input type="text" class="inputTextMediumBlue" name="dkek_311" id="dkek_311" size="18" maxlength="16" value='${model.record.dkek_311}'></td>
							<td class="text14">
								<input type="text" class="inputTextMediumBlue" list="dkek_314_list" name="dkek_314" id="dkek_314" size="6" maxlength="2" value='${model.record.dkek_314}'>
								<datalist id="dkek_314_list">
								  <c:forEach var="code" items="${model.emballageCodeList}" >
				 				  	<option value="${code.dkkd_kd}"<c:if test="${model.record.dkek_314 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
			 				  	  </c:forEach>  
								</datalist>
								<a tabindex="-1" id="dkek_314IdLink" OnClick="triggerChildWindowGeneralCodes(this, '110')">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>	
							</td>
							<td colspan="2" class="text12">
								<span title="dkek_401a-402a">40.2&nbsp;<spring:message code="systema.skat.items.summarisk.ang.kattype"/></span>
								<input type="text" class="inputTextMediumBlue" list="dkek_402a_list" name="dkek_402a" id="dkek_402a" size="8" maxlength="4" value='${model.record.dkek_402a}'>
								<datalist id="dkek_402a_list">
								  <c:forEach var="code" items="${model.transportdocsSummariskadocsCodeList}" >
				 				  	<option value="${code.dkkd_kd}"<c:if test="${model.record.dkek_402a == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
			 				  	  </c:forEach>  
								</datalist>
								&nbsp;&nbsp;
								<span title="dkek_403a">&nbsp;40.3&nbsp;Id</span>
								<input type="text" class="inputTextMediumBlue" name="dkek_403a" id="dkek_403a" size="22" maxlength="20" value="${model.record.dkek_403a}">
							</td>
						</tr>
						<tr height="5"><td></td></tr>
						<tr>
							<td class="text14" title="dkek_411">&nbsp;Suppl.enhed</td>
							<td class="text14" title="dkek_441">&nbsp;Bevillingsnr</td>
							<td class="text14" title="dkek_442a">&nbsp;Certifikatnr</td>
							<td class="text14" title="dkek_4421">&nbsp;Certifikatkode</td>
							<td class="text14" title="dkek_443">&nbsp;Varebestemmelser</td>
							<td class="text14" title="dkek_444">&nbsp;FN Farlig gods</td>
							<td class="text14" title="dkek_447">&nbsp;Delsendinger</td>
							<td colspan="3" class="text14" title="dkek_446a">&nbsp;Supplerende oplysninger</td>
							
						</tr>
						<tr>
							<td class="text14" >
								<input type="text" class="inputTextMediumBlue" list="dkek_411_list" name="dkek_411" id="dkek_411" size="7" maxlength="3" value="${model.record.dkek_411}">
								<datalist id="dkek_411_list">
								  <c:forEach var="code" items="${model.uomKoderR411CodeList}" >
				 				  	<option value="${code.dkkd_kd}"<c:if test="${model.record.dkek_411 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
			 				  	  </c:forEach>  
								</datalist>
							</td>
							<td class="text14" ><input type="text" class="inputTextMediumBlue" name="dkek_442a" id="dkek_442a" size="25" maxlength="35" value="${model.record.dkek_442a}"></td>
							<td class="text14" ><input type="text" class="inputTextMediumBlue" name="dkek_441" id="dkek_441" size="15" maxlength="13" value="${model.record.dkek_441}"></td>
							
							<td class="text14" >
								<input type="text" class="inputTextMediumBlue" list="dkek_4421_list" name="dkek_4421" id="dkek_4421" size="7" maxlength="4" value="${model.record.dkek_4421}">
								<datalist id="dkek_4421_list">
								  <c:forEach var="code" items="${model.certifikatKoderR44_2CodeList}" >
				 				  	<option value="${code.dkkd_kd}"<c:if test="${model.record.dkek_4421 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
			 				  	  </c:forEach>  
								</datalist>
							</td>
							<td class="text14" >
								<input type="text" class="inputTextMediumBlue" list="dkek_443_list" name="dkek_443" id="dkek_443" size="3" maxlength="1" value="${model.record.dkek_443}">
								<datalist id="dkek_443_list">
								  <c:forEach var="code" items="${model.vabCertifikatKoderR44_3CodeList}" >
				 				  	<option value="${code.dkkd_kd}"<c:if test="${model.record.dkek_443 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
			 				  	  </c:forEach>  
								</datalist>
							</td>
							<td class="text14" >
								<input type="text" class="inputTextMediumBlue" list="dkek_444_list" name="dkek_444" id="dkek_444" size="7" maxlength="4" value="${model.record.dkek_444}">
								<datalist id="dkek_444_list">
								  <c:forEach var="code" items="${model.fnFarligGodsCodeList}" >
				 				  	<option value="${code.dkkd_kd}"<c:if test="${model.record.dkek_444 == code.dkkd_kd}"> selected </c:if> >${code.dkkd_kd}</option>
			 				  	  </c:forEach>  
								</datalist>
							</td>
							<td class="text14" ><input type="text" class="inputTextMediumBlue" name="dkek_447" id="dkek_447" size="7" maxlength="5" value="${model.record.dkek_447}"></td>
							<td class="text14" colspan="3"><input type="text" class="inputTextMediumBlue" name="dkek_446a" id="dkek_446a" size="45" maxlength="70" value="${model.record.dkek_446a}"></td>	
						</tr>
						<tr height="5"><td></td></tr>
						<tr>
							<td class="text14" title="dkek_332">&nbsp;Tillægskode</td>
							<td class="text14" title="dkek_sikk">&nbsp;Sikkerhedsstillesle</td>
							<td class="text14" title="dkek_449a">&nbsp;Oplysningstype kode</td>
							<td class="text14" title="dkek_49">&nbsp;Ident. af oplag</td>
							<td class="text14" title="dkek_bem1">&nbsp;Bemærkninger</td>
							
						</tr>
						<tr>
							<td class="text14" ><input type="text" class="inputTextMediumBlue" name="dkek_332" id="dkek_332" size="7" maxlength="4" value="${model.record.dkek_332}"></td>
							<td class="text14" ><input type="text" class="inputTextMediumBlue" name="dkek_sikk" id="dkek_sikk" size="5" maxlength="2" value="${model.record.dkek_sikk}"></td>
							<td class="text14" ><input type="text" class="inputTextMediumBlue" name="dkek_449a" id="dkek_449a" size="22" maxlength="20" value="${model.record.dkek_449a}"></td>
							<td class="text14" ><input type="text" class="inputTextMediumBlue" name="dkek_49" id="dkek_49" size="19" maxlength="17" value="${model.record.dkek_49}"></td>
							<td colspan="3" class="text14" ><input type="text" class="inputTextMediumBlue" name="dkek_bem1" id="dkek_bem1" size="45" maxlength="70" value="${model.record.dkek_bem1}"></td>
							
							<td>
								<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Spare'/>
							</td>
						</tr>
						<tr height="3"><td></td>
					</table>
	 	    	</form>
	 	    </tr>
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	     
	 		</table>
		</td>
	</tr>
</table>	

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

