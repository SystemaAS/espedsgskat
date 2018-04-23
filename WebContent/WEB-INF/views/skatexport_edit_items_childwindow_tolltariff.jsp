<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerSkatChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/skatexport_edit_items_childwindow_tolltariff.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			<img title="select" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			Søg kode
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
						<form name="skatExportTolltariffForm" id="skatExportTolltariffForm" action="skatexport_edit_items_childwindow_tolltariff.do?action=doInit" method="post">
						<tr>
							<td class="text14">&nbsp;Varekod</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="vkod" id="vkod" size="10" maxlength="10" value="${model.vkod}"></td>
							<%--
							<td class="text14">&nbsp;Beskrivning</td>
							<td class="text14">&nbsp;<input type="text" class="inputText" name="tekst" id="tekst" size="30" maxlength="50" value="${model.tekst}"></td>
							 --%>
							<td class="text14">&nbsp;</td>
	           				<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="systema.skat.search"/>'>
		           		</tr>
		           		
		           		</table>
					</td>
					</tr>
					 
													           		
	           		<tr height="10"><td></td></tr>
					
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:30em;">
					<%-- this is the datatables grid (content)--%>
					<table id="tolltariffList" class="display compact cell-border" width="100%" >
						<thead>
						<tr class="tableHeaderField">
							<th class="text14" title="adunnr">&nbsp;Varekod&nbsp;</th>
		                    <th class="text14" title="adembg">&nbsp;Beskrivelse&nbsp;</th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.tolltariffList}" varStatus="counter">    
			               <c:choose>           
			                   <c:when test="${counter.count%2==0}">
			                       <tr class="text14">
			                   </c:when>
			                   <c:otherwise>   
			                       <tr class="text14">
			                   </c:otherwise>
			               </c:choose>
			               
		               	   <td nowrap style="cursor:pointer;" class="text14MediumBlue" id="vkod${record.dktara02}@text${record.dktara64}@ctype${model.callerType}" >
			               		<img title="select" style="vertical-align:top;" src="resources/images/bebullet.gif" border="0" alt="edit">&nbsp;${record.dktara02}
			               	</td>
		               	   <td class="text14">&nbsp;${record.dktara64}</td>
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
