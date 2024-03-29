	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  	
  	function reloadThis() {
  		jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  		window.location = 'skatnctsexport_edit_items.do?action=doFetch&avd='+ jq('#avd').val() + '&sign=' + jq('#sign').val() +'&opd=' + jq('#opd').val();
  	}
  	
  	//Overlay on tab (to mark visually a delay...)
    jq(function() {
      jq('#alinkTopicList').click(function() { 
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  	  });	
  	  jq('#alinkHeader').click(function() { 
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  	  });
  	  jq('#alinkLogging').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
  	  jq('#alinkArchive').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
  	  
  	  jq( "#submit" ).click(function( event ) {
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
  	  });
  	  
  	  //----------------
	  	//CUSTOMER search
	  	//----------------
	    //SENDER
	    jq('#tvnasIdLink').click(function() {
	    	jq('#tvnasIdLink').attr('target','_blank');
	    	window.open('skat_childwindow_customer.do?action=doFind&sonavn=' + jq('#tvnas').val() + '&ctype=tvnas', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#tvnasIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#tvnasIdLink').click();
			}
	    });
	    //MOTTAKER
	    jq('#tvnakIdLink').click(function() {
	    	jq('#tvnakIdLink').attr('target','_blank');
	    	window.open('skat_childwindow_customer.do?action=doFind&sonavn=' + jq('#tvnak').val() + '&ctype=tvnak', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#tvnakIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#tvnakIdLink').click();
			}
	    });
	    //SENDER - SIKKERHED
	    jq('#tvnassIdLink').click(function() {
	    	jq('#tvnassIdLink').attr('target','_blank');
	    	window.open('skat_childwindow_customer.do?action=doFind&sonavn=' + jq('#tvnass').val() + '&ctype=tvnass', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#tvnassIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#tvnassIdLink').click();
			}
	    });
	    //MOTTAKER - SIKKERHED
	    jq('#tvnaksIdLink').click(function() {
	    	jq('#tvnaksIdLink').attr('target','_blank');
	    	window.open('skat_childwindow_customer.do?action=doFind&sonavn=' + jq('#tvnaks').val() + '&ctype=tvnaks', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#tvnaksIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#tvnaksIdLink').click();
			}
	    });
	    //-----------
	    //Varukoder
	    //-----------
	    jq('#tvvntIdLink').click(function() {
	    	jq('#tvvntIdLink').attr('target','_blank');
	    	window.open('skatnctsexport_edit_items_childwindow_tolltariff.do?action=doInit&vkod=' + jq('#tvvnt').val(), "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#tvvntIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#tvvntIdLink').click();
			}
	    });
	    
	    //DocType
	    jq('#tvdtyIdLink').click(function() {
	    	jq('#tvdtyIdLink').attr('target','_blank');
	    	window.open('skatnctsexport_edit_items_childwindow_generalcodes.do?action=doInit&type=013&ctype=tvdty', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#tvdtyIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#tvdtyIdLink').click();
			}
	    });
	    
	    //Kollislag
	    jq('#tvehIdLink').click(function() {
	    	jq('#tvehIdLink').attr('target','_blank');
	    	window.open('skatnctsexport_edit_items_childwindow_generalcodes.do?action=doInit&type=017&ctype=tveh', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#tvehIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#tvehIdLink').click();
			}
	    });
	    
	  //Valuta
  	  jq('#dkxv_221IdLink').click(function() {
    	jq('#dkxv_221IdLink').attr('target','_blank');
    	window.open('skatnctsexport_edit_items_childwindow_generalcodes.do?action=doInit&type=107&ctype=dkxv_221', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
  	  });
  	  jq('#thgvkIdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#dkxv_221IdLink').click();
		}
  	  });
		   
    }); 
    
    //ChildWindow Language Codes
    function triggerChildWindowLanguageCodes(record){
    	var idLink = record.id;
    	var id = idLink.replace("IdLink", "");
    	jq(idLink).attr('target','_blank');
    	window.open('skatnctsexport_edit_items_childwindow_generalcodes.do?action=doInit&type=012&ctype=' + id , "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    }
    //ChildWindow Country Codes
    function triggerChildWindowCountryCodes(record){
    	var idLink = record.id;
    	var id = idLink.replace("IdLink", "");
    	jq(idLink).attr('target','_blank');
    	window.open('skatnctsexport_edit_items_childwindow_generalcodes.do?action=doInit&type=008&ctype=' + id , "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    }
    
  	jq(function() {
	  	jq('#tvtdn2IdLink').click(function() {
	    	jq('#tvtdn2IdLink').attr('target','_blank');
	    	window.open('skatnctsexport_edit_items_childwindow_angivelselist.do?action=doFind&avd=' + jq('#tvavd2').val() + '&opd=' + jq('#tvtdn2').val() + '&xref=' + jq('#xref').val(), "codeWin", "top=300px,left=500px,height=600px,width=900px,scrollbars=no,status=no,location=no");
	    });
	    jq('#tvtdn2IdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#tvtdn2IdLink').click();
			}
	    });
	    jq('#xrefIdLink').click(function() {
	    	jq('#xrefIdLink').attr('target','_blank');
	    	window.open('skatnctsexport_edit_items_childwindow_angivelselist.do?action=doFind&avd=' + jq('#tvavd2').val() + '&opd=' + jq('#tvtdn2').val() + '&xref=' + jq('#xref').val(), "codeWin", "top=300px,left=500px,height=600px,width=900px,scrollbars=no,status=no,location=no");
	    });
	    jq('#xrefIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#xrefIdLink').click();
			}
	    });
	    
	    jq('#itemLinesImportButton').click(function() {
	    	jq('#itemLinesImportButton').attr('target','_blank');
	    	window.open('skatnctsexport_edit_items_childwindow_angivelselist_gettoitemlines.do?action=doFind&avdNcts=' + jq('#avd').val() + '&opdNcts=' + jq('#opd').val(), "codeWinItemLinesImport", "top=300px,left=400px,height=650px,width=1400px,scrollbars=no,status=no,location=no");
	    });
	    jq('#itemLinesImportButton').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#itemLinesImportButton').click();
			}
	    });
	    
	    //delete all
  	  	jq('#itemListDeleteAllButton').click(function() {
  	  		doDeleteAll();
  	  	});
  	});
  	
  	jq(function() {
  		jq('#tvtdn2').blur(function() {
  		  if(jq('#tvtdn2').val()!='' ){
  			 //only with create new line 
  			 if(jq('#tvli').val()==''){
  				getDefaultValuesFromSkatExportHeader();
  			 }else if(jq('#tvnas').val()=='' && jq('#tvnak').val()==''){
  				getDefaultValuesFromSkatExportHeader(); 
  			 } 
  		  }			  
  		});
  		//same as above but with xref
  		jq('#xref').blur(function() {
		  if(jq('#xref').val()!='' ){
			 //only with create new line 
			 if(jq('#tvli').val()==''){
				getDefaultValuesFromSkatExportHeader();
			 }else if(jq('#tvnas').val()=='' && jq('#tvnak').val()==''){
				getDefaultValuesFromSkatExportHeader(); 
			 } 
		  }			  
		});
  	});
  	//populate GUI fields (when applicable)
  	function getDefaultValuesFromSkatExportHeader(){
  		jq.ajax({
  	  	  type: 'GET',
  	  	  url: 'getSpecificTopic_SkatExport.do',
  	  	  data: { applicationUser : jq('#applicationUser').val(), 
  	  		  	  avd : jq('#tvavd2').val(), 
  	  		  	  opd : jq('#tvtdn2').val(),
  	  		  	  xref: jq('#xref').val() },
  	  	  dataType: 'json',
  	  	  cache: false,
  	  	  contentType: 'application/json',
  	  	  success: function(data) {
  	  		var len = data.length;
  	  		//CLEAN all fields
  	  		//Avsender
			jq('#tvkns').val("");jq('#tvnas').val("");jq('#tvtins').val("");jq('#tvads1').val(""); 
			jq('#tvpns').val("");jq('#tvpss').val(""); jq('#tvlks').val(""); 
			//Mottaker
			jq('#tvknk').val("");jq('#tvnak').val("");jq('#tvtink').val("");jq('#tvadk1').val(""); 
			jq('#tvpnk').val("");jq('#tvpsk').val("");jq('#tvlkk').val("");
			
  			for ( var i = 0; i < len; i++) {
  				//This line counter (lastSelectedItemLineNumber) is used in order to have a serial counter for the row lines. It is the only serial counter...
  				//It is used ONLY for aspect/behavior purposes on GUI (scroll, bgColor, etc) in the specific row.
  				//Avsender
  				jq('#tvkns').val(data[i].dkeh_avkn);
  				jq('#tvnas').val(data[i].dkeh_02b);
  				jq('#tvtins').val(data[i].dkeh_02a);
  				jq('#tvads1').val(data[i].dkeh_02c);
  				jq('#tvpns').val(data[i].dkeh_02d);
  				jq('#tvpss').val(data[i].dkeh_02e);
  				jq('#tvlks').val(data[i].dkeh_02f);
  				//Mottaker
  				jq('#tvknk').val(data[i].dkeh_mokn);
  				jq('#tvnak').val(data[i].dkeh_08b);
  				jq('#tvtink').val(data[i].dkeh_08a);
  				jq('#tvadk1').val(data[i].dkeh_08c);
  				jq('#tvpnk').val(data[i].dkeh_08d);
  				jq('#tvpsk').val(data[i].dkeh_08e);
  				jq('#tvlkk').val(data[i].dkeh_08f);
  				//other header fields
  				if(jq('#tvalk').val()==''){
  					jq('#tvalk').val("DK");
  				}
  				if(jq('#tvdty').val()==''){
  					jq('#tvdty').val("830");
  				}
  				if(jq('#tvblk').val()==''){
  					jq('#tvblk').val(data[i].dkeh_17a);
  				}
  				//get some values from SKAT Eksport Item lines
  				getDefaultValuesFromSkatExportItemLines();
  			}
	  	  }
  		});		
  	}
  	//GET some line default values from Skat Eksport
  	function getDefaultValuesFromSkatExportItemLines(){
  		jq.ajax({
    	  	  type: 'GET',
    	  	  url: 'getFirstItemLineTopic_SkatExport.do',
    	  	  data: { applicationUser : jq('#applicationUser').val(), 
    	  		  	  avd : jq('#tvavd2').val(), 
    	  		  	  opd : jq('#tvtdn2').val() },
    	  	  dataType: 'json',
    	  	  cache: false,
    	  	  contentType: 'application/json',
    	  	  success: function(data) {
    	  		var len = data.length;
    	  		if (len>0){
	    	  		//only first line
	    			for ( var i = 0; i < 1; i++) {
	    				if(jq('#tvvnt').val()==''){
	    					var itemCode = data[i].dkev_331;
	    					/*if(itemCode!='' && itemCode.length>6){
	    						var tmp = itemCode.substring(0, 6);
	    						itemCode = tmp;
	    					}*/
	    					jq('#tvvnt').val(itemCode);
	    				}
	    				if(jq('#tvvktb').val()==''){
	    					jq('#tvvktb').val(data[i].dkev_35);
	    				}
	    				if(jq('#tvvktn').val()==''){
	    					jq('#tvvktn').val(data[i].dkev_38);
	    				}
	    				if(jq('#tvnt').val()==''){
	    					jq('#tvnt').val(data[i].dkev_313);
	    				}
	    		
	    			}
    	  		}
  		  	 }
  		});		
  	}
  	/**
  	 * gets a specific item line
  	 * 
  	 * @param record
  	 */
  	function getItemData(record) {
	  	var htmlValue = record.id;
	  	var rowCounter = jq("#"+record.id).attr("title");
	  	
	  	var applicationUserParam = jq('#applicationUser').val();
	  	var avdParam = jq('#avdItemList').val();
	  	var opdParam = jq('#opdItemList').val();
	  	//alert(htmlValue);
	  	jq.ajax({
	  	  type: 'GET',
	  	  url: 'getSpecificTopicItemChosenFromGuiElement_SkatNctsExport.do',
	  	  data: { applicationUser : applicationUserParam, 
	  		  	  elementValue : htmlValue, 
	  		  	  avd : avdParam, 
	  		  	  opd : opdParam },
	  	  dataType: 'json',
	  	  cache: false,
	  	  contentType: 'application/json',
	  	  success: function(data) {
	  		var len = data.length;
			for ( var i = 0; i < len; i++) {
				//This line counter (lastSelectedItemLineNumber) is used in order to have a serial counter for the row lines. It is the only serial counter...
				//It is used ONLY for aspect/behavior purposes on GUI (scroll, bgColor, etc) in the specific row.
				jq('#lastSelectedItemLineNumber').val(""); jq('#lastSelectedItemLineNumber').val(rowCounter);
				
				jq('#lineNr').val(data[i].tvli);
				jq('#tvli').val(data[i].tvli); 
				jq('#tvvnt').val(data[i].tvvnt);jq('#tvvt').val(data[i].tvvt);
				jq('#tvvt2').val(data[i].tvvt2);jq('#tvvt3').val(data[i].tvvt3);
				jq('#tvvt4').val(data[i].tvvt4);jq('#tvvt5').val(data[i].tvvt5);
				
				jq('#tvtdr').val(data[i].tvtdr);jq('#tvdk').val(data[i].tvdk); 
				jq('#tvvktb').val(data[i].tvvktb); jq('#tvvktn').val(data[i].tvvktn); 
				jq('#tvnt').val(data[i].tvnt);jq('#tvdref').val(data[i].tvdref);
				jq('#tvdsk').val(data[i].tvdsk);
				
				jq('#tvdty').val(data[i].tvdty); 
				jq('#tvalk').val(data[i].tvalk);
				jq('#tvblk').val(data[i].tvblk);
				jq('#tveh').val(data[i].tveh);
				
				//Extra section
				jq('#tvvtsk').val(data[i].tvvtsk);jq('#tvtdt').val(data[i].tvtdt);
				jq('#tvtdsk').val(data[i].tvtdsk);jq('#tvtdo').val(data[i].tvtdo);
				jq('#tvtdos').val(data[i].tvtdos);
				jq('#tvdosk').val(data[i].tvdosk);jq('#tvmtxt').val(data[i].tvmtxt);
				jq('#tvmsk').val(data[i].tvmsk);jq('#tvtlo').val(data[i].tvtlo);
				jq('#tvmsk').val(data[i].tvmsk);jq('#tvtlo').val(data[i].tvtlo);
				jq('#tvexkd').val(data[i].tvexkd);jq('#tvexlk').val(data[i].tvexlk);
				jq('#tvavd2').val(data[i].tvavd2);jq('#tvtdn2').val(data[i].tvtdn2);
				jq('#tvcnr').val(data[i].tvcnr);jq('#tvmn').val(data[i].tvmn);
				jq('#tvmnsk').val(data[i].tvmnsk);jq('#tvtdn2').val(data[i].tvtdn2);
				jq('#tvnteh').val(data[i].tvnteh);
				jq('#tvcnr2').val(data[i].tvcnr2);jq('#tvcnr3').val(data[i].tvcnr3);
				jq('#tvcnr4').val(data[i].tvcnr4);jq('#tvcnr5').val(data[i].tvcnr5);
				jq('#tvcnr6').val(data[i].tvcnr6);
				jq('#tvfv').val(data[i].tvfv);jq('#tvfvnt').val(data[i].tvfvnt);
				jq('#tvkns').val(data[i].tvkns);
				jq('#tvnas').val(data[i].tvnas);
				jq('#tvads1').val(data[i].tvads1);jq('#tvpns').val(data[i].tvpns);
				jq('#tvpss').val(data[i].tvpss);jq('#tvlks').val(data[i].tvlks);
				jq('#tvsks').val(data[i].tvsks);jq('#tvtins').val(data[i].tvtins);
				jq('#tvknk').val(data[i].tvknk);
				jq('#tvnak').val(data[i].tvnak);
				jq('#tvadk1').val(data[i].tvadk1);jq('#tvpnk').val(data[i].tvpnk);
				jq('#tvpsk').val(data[i].tvpsk);jq('#tvlkk').val(data[i].tvlkk);
				jq('#tvskk').val(data[i].tvskk);jq('#tvtink').val(data[i].tvtink);
				jq('#tvln').val(data[i].tvln);jq('#tvrefl').val(data[i].tvrefl);
				
				//rubrik 31 (extra Godsmärke)
				jq('#tvmn2').val(data[i].tvmn2);jq('#tveh2').val(data[i].tveh2);
				jq('#tvnt2').val(data[i].tvnt2);jq('#tvnteh2').val(data[i].tvnteh2);
				jq('#tvmn3').val(data[i].tvmn3);jq('#tveh3').val(data[i].tveh3);
				jq('#tvnt3').val(data[i].tvnt3);jq('#tvnteh3').val(data[i].tvnteh3);
				jq('#tvmn4').val(data[i].tvmn4);jq('#tveh4').val(data[i].tveh4);
				jq('#tvnt4').val(data[i].tvnt4);jq('#tvnteh4').val(data[i].tvnteh4);
				jq('#tvmn5').val(data[i].tvmn5);jq('#tveh5').val(data[i].tveh5);
				jq('#tvnt5').val(data[i].tvnt5);jq('#tvnteh5').val(data[i].tvnteh5);
				//end rubrik 31
				
				//rubrik 44 (Supplerende Oplysninger) - Multilines
				jq('#tvdty2').val(data[i].tvdty2);jq('#tvdref2').val(data[i].tvdref2);
				jq('#tvdsk2').val(data[i].tvdsk2);jq('#tvdo2').val(data[i].tvdo2);
				jq('#tvdosk2').val(data[i].tvdosk2);
				jq('#tvmtxt2').val(data[i].tvmtxt2);jq('#tvmsk2').val(data[i].tvmsk2);
				jq('#tvtlo2').val(data[i].tvtlo2);jq('#tvexkd2').val(data[i].tvexkd2);
				jq('#tvexlk2').val(data[i].tvexlk2);
				
				jq('#tvdty3').val(data[i].tvdty3);jq('#tvdref3').val(data[i].tvdref3);
				jq('#tvdsk3').val(data[i].tvdsk3);jq('#tvdo3').val(data[i].tvdo3);
				jq('#tvdosk3').val(data[i].tvdosk3);
				jq('#tvmtxt3').val(data[i].tvmtxt3);jq('#tvmsk3').val(data[i].tvmsk3);
				jq('#tvtlo3').val(data[i].tvtlo3);jq('#tvexkd3').val(data[i].tvexkd3);
				jq('#tvexlk3').val(data[i].tvexlk3);
				
				jq('#tvdty4').val(data[i].tvdty4);jq('#tvdref4').val(data[i].tvdref4);
				jq('#tvdsk4').val(data[i].tvdsk4);jq('#tvdo4').val(data[i].tvdo4);
				jq('#tvdosk4').val(data[i].tvdosk4);
				jq('#tvmtxt4').val(data[i].tvmtxt4);jq('#tvmsk4').val(data[i].tvmsk4);
				jq('#tvtlo4').val(data[i].tvtlo4);jq('#tvexkd4').val(data[i].tvexkd4);
				jq('#tvexlk4').val(data[i].tvexlk4);
				
				
				jq('#tvdty_readonly').val(data[i].tvdty);
				jq('#tvdref_readonly').val(data[i].tvdref);
				jq('#tvdsk_readonly').val(data[i].tvdsk);
				jq('#tvdo').val(data[i].tvdo);
				// End rubrik 44
				
				//Sikkerhet - Security section
				jq('#tvtkbm').val(data[i].tvtkbm);
				jq('#tvkref').val(data[i].tvkref);
				jq('#tvfgkd').val(data[i].tvfgkd);
				jq('#tvknss').val(data[i].tvknss);
				jq('#tvnass').val(data[i].tvnass);
				jq('#tvadss1').val(data[i].tvadss1);
				jq('#tvknss').val(data[i].tvknss);
				jq('#tvpnss').val(data[i].tvpnss);
				jq('#tvpsss').val(data[i].tvpsss);
				jq('#tvlkss').val(data[i].tvlkss);
				jq('#tvskss').val(data[i].tvskss);
				jq('#tvtinss').val(data[i].tvtinss);
				jq('#tvknks').val(data[i].tvknks);
				jq('#tvnaks').val(data[i].tvnaks);
				jq('#tvadks1').val(data[i].tvadks1);
				jq('#tvknks').val(data[i].tvknks);
				jq('#tvpnks').val(data[i].tvpnks);
				jq('#tvpsks').val(data[i].tvpsks);
				jq('#tvlkks').val(data[i].tvlkks);
				jq('#tvskks').val(data[i].tvskks);
				jq('#tvtinks').val(data[i].tvtinks);
				//Matrix dkxv
				jq('#dkxv_222').val(data[i].dkxv_222); //Fakt.b VAL
				jq('#dkxv_221').val(data[i].dkxv_221); //VALUTA
				jq('#dkxv_221b').val(data[i].dkxv_221b); //KURS
				jq('#dkxv_221c').val(data[i].dkxv_221c); //Faktor
				jq('#dkxv_222b').val(data[i].dkxv_222b); //Fakt.b DKK
				jq('#dkxv_46').val(data[i].dkxv_46);//Tollverdi
				jq('#dkxv_42b').val(data[i].dkxv_42b);//Moms
				jq('#dkxv_42c').val(data[i].dkxv_42c);//Grand total (ink moms)
				
				
				//debug information on Fetch item
				jq('#debugPrintlnAjaxItemFetchInfo').text(data[i].debugPrintlnAjax);
				
			}
	  	  },
	  	  error: function() {
	  	    alert('Error loading ...');
	  	  }
	  	});
  	}
  
  	
  	
  	jq(function() {
  		jq('#tvvnt').blur(function() {
  		  if(jq('#tvvnt').val()!='' && jq('#tvvnt').val().length==8 ){
  			jq.getJSON('searchTaricVarukod_SkatNctsExport.do', {
				applicationUser : jq('#applicationUser').val(),
				taricVarukod : jq('#tvvnt').val(),
				ajax : 'true'
			}, function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					if(jq('#tvvt').val()==''){ 
						jq('#tvvt').val(data[i].dktara64);
					}
					jq('#ownToldsats').val(data[i].dktara15);
				}					
			});
	  			
  			//STEP(2) get følsomme varer krav (mandatory or not)
  			if(jq('#tvvnt').val()!='' && jq('#tvvnt').val().length==8 ){  
	  			jq.getJSON('isFolsommeVare_SkatNctsExport.do', {
					applicationUser : jq('#applicationUser').val(),
					taricVarukod : jq('#tvvnt').val(),
					ajax : 'true'
				}, function(data) {
					var len = data.length;
					if(len>0){
						for ( var i = 0; i < len; i++) {
							//there has been a match. Proceed to populate hidden fields to force validation with folsomme vare
							jq('#tvfv').val(data[i].tfkode);
						}	
					}else{
						//jq('#tvvt').val("");
						jq('#tvfv').val("");
					}	
				});
  			}
  			
  		  }else{
			jq('#tvfvnt').val("");
			jq('#tvfv').val("");
			jq('#ownToldsats').val("");
  		  }			  
  		});
  	});
  	
  	
	//---------------------------------------------------------
  	//FETCH CUSTOMER from SENDER [AVSÄNDARE] html area
  	//---------------------------------------------------------
	var map = {};
  	
  	//init the customer object in javascript (will be put into a map)
  	var customer = new Object();
  	//fields
  	customer.kundnr = "";customer.knavn = "";customer.eori = "";customer.adr1 = "";
  	customer.adr2 = "";customer.adr3 = "";customer.postnr = "";customer.syland = "";
  	customer.kpers = "";customer.tlf = "";
  	//---------------------------------------------------------
  	//FETCH CUSTOMER from SENDER [AVSÄNDARE] html area
  	// [Same as TDS-EXPORT but with GUI fields for NCTS]
  	//---------------------------------------------------------
	function searchSenderOwnWindow() {
		jq(function() {
			jq.getJSON('searchCustomer_Skat.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : jq('#search_tvnas').val(),
				customerNumber : jq('#search_tvkns').val(),
				ajax : 'true'
			}, function(data) {
				//alert("Hello");
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].kundnr + '">' + data[i].knavn + '</option>';
					customer = new Object();
					customer.kundnr = data[i].kundnr;
					customer.knavn = data[i].knavn;
					customer.eori = data[i].eori;
					customer.adr1 = data[i].adr1;
					customer.adr2 = data[i].adr2;
					customer.adr3 = data[i].adr3;
					customer.postnr = data[i].postnr;
					customer.kpers = data[i].kpers;
					customer.tlf = data[i].tlf;
					customer.syland = data[i].syland;
				  	
					//put the object in map now with customerNumber as key
					map[customer.kundnr] = customer;
				}
				//now that we have our options, give them to our select
				jq('#senderList').html(html);
			});
		});
	}
	//onChange sender list
	jq(function() { 
	    jq('#senderList').change(function() {
	      //init fields
		  jq('#tvnas').val("");
		  jq('#tvtins').val("");
		  jq('#tvads1').val("");
		  jq('#tvpns').val("");
		  jq('#tvpss').val("");
		  jq('#tvlks').val("");
		  jq('#tvsks').val("");
		  
		  //now populate (if applicable)
		  var key = jq('#senderList').val();
		  jq('#tvkns').val(key);
		  customer = map[key];
		  jq('#tvnas').val(customer.knavn);
		  jq('#tvtins').val(customer.eori);
		  jq('#tvads1').val(customer.adr1);
		  jq('#tvpns').val(customer.postnr);
		  jq('#tvpss').val(customer.adr3);
		  jq('#tvlks').val(customer.syland);
		  jq('#tvsks').val("");
	    });
	});
	
	//onClick for Sender dialog
	jq(function() { 
	    jq('#searchCustomerCloseCancel').click(function() {
	      //rescue the original fields
	      jq('#tvkns').val(jq("#orig_tvkns").val());	
		  jq('#tvnas').val(jq("#orig_tvnas").val());
		  jq('#tvtins').val(jq("#orig_tvtins").val());
		  jq('#tvads1').val(jq("#orig_tvads1").val());
		  jq('#tvpns').val(jq("#orig_tvpns").val());
		  jq('#tvpss').val(jq("#orig_tvpss").val());
		  jq('#tvlks').val(jq("#orig_tvlks").val());
		  jq('#tvsks').val(jq("#orig_tvsks").val());
	    });
	});
	
	//---------------
	//Sender list
	//---------------
	//onChange
	jq(function() { 
	    jq('#senderList').change(function() {
		    	jq('#tvnas').val(""); 
		    	//now populate (if applicable)
		    	var key = jq('#senderList').val();
		    	jq('#tvnas').val(key); 
	    });
	});
	//On Keypress (13)
	jq(function() { 
	    jq('#senderList').keypress(function() {
		    	if(e.which == 13) {
				//alert("hej till publiken");
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#tvnas').val(""); 
				//now populate (if applicable)
			    	var key = jq('#senderList').val();
			    	jq('#tvnas').val(key); 
		    	}
	    });
	    
	});
	
	//----------------------------------
	//Events Sender (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgCustomerSearch').click(function(){
    			jq("#search_tvkns").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_tvkns').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchSenderOwnWindow);
			}			
    		});
		jq('#search_tvnas').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchSenderOwnWindow);
			}			
    		});
	});

	
  	//---------------------------------------------------------
	//FETCH CUSTOMER from RECEIVER [MOTTAGARE] html area
  	//---------------------------------------------------------
	function searchReceiverOwnWindow() {
		jq(function() {
			jq.getJSON('searchCustomer_Skat.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : jq('#search_tvnak').val(),
				customerNumber : jq('#search_tvknk').val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].kundnr + '">' + data[i].knavn + '</option>';
					customer = new Object();
					customer.kundnr = data[i].kundnr;
					customer.knavn = data[i].knavn;
					customer.eori = data[i].eori;
					customer.adr1 = data[i].adr1;
					customer.adr2 = data[i].adr2;
					customer.adr3 = data[i].adr3;
					customer.postnr = data[i].postnr;
					customer.kpers = data[i].kpers;
					customer.tlf = data[i].tlf;
					customer.syland = data[i].syland;
				  	
					//put the object in map now with customerNumber as key
					map[customer.kundnr] = customer;
				}
				//now that we have our options, give them to our select
				jq('#receiverList').html(html);
			});
		});
	}
	//Sets receiver values after user selection
	jq(function() { 
	    jq('#receiverList').change(function() {
		  //init all fields
		  jq('#tvnak').val("");
		  jq('#tvtink').val("");
		  jq('#tvadk1').val("");
		  jq('#tvpnk').val("");
		  jq('#tvpsk').val("");
		  jq('#tvlkk').val("");	
		  jq('#tvskk').val("");	
		  
		  //now populate (if applicable)
		  var key = jq('#receiverList').val();
		  jq('#tvknk').val(key);
		  customer = map[key];
		  jq('#tvtink').val(customer.eori);
		  jq('#tvnak').val(customer.knavn);
		  jq('#tvadk1').val(customer.adr1);
		  jq('#tvpnk').val(customer.postnr);
		  jq('#tvpsk').val(customer.adr3);
		  jq('#tvlkk').val(customer.syland);
		  jq('#tvskk').val("");			  
		});
	});
	//onClick for Receiver(Mottagare) dialog
	jq(function() { 
	    jq('#searchCustomer10CloseCancel').click(function() {
	      //rescue the original fields
	      jq('#tvknk').val(jq("#orig_tvknk").val());	
		  jq('#tvnak').val(jq("#orig_tvnak").val());
		  jq('#tvtink').val(jq("#orig_tvtink").val());
		  jq('#tvadk1').val(jq("#orig_tvadk1").val());
		  jq('#tvpnk').val(jq("#orig_tvpnk").val());
		  jq('#tvpsk').val(jq("#orig_tvpsk").val());
		  jq('#tvlkk').val(jq("#orig_tvlkk").val());
		  jq('#tvskk').val(jq("#orig_tvskk").val());
		  
	    });
	});

	//---------------
	//Receiver list
	//---------------
	//onChange
	jq(function() { 
	    jq('#receiverList').change(function() {
		    	jq('#tvnak').val(""); //tds export
		    	//now populate (if applicable)
		    	var key = jq('#receiverList').val();
		    	jq('#tvnak').val(key); //tds export
	    });
	});
	//On Keypress (13)
	jq(function() { 
	    jq('#receiverList').keypress(function() {
		    	if(e.which == 13) {
				//alert("hej till publiken");
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#tvnak').val(""); //tds export
				//now populate (if applicable)
			    	var key = jq('#receiverList').val();
			    	jq('#tvnak').val(key); //tds export
		    	}
	    });
	    
	});
	
	//----------------------------------
	//Events Receiver (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgReceiverSearch').click(function(){
    			jq("#search_tvnak").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_tvknk').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchReceiverOwnWindow);
			}			
    		});
		jq('#search_tvnak').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchReceiverOwnWindow);
			}			
    		});
	});
	
	//calculate a net weight from the gross weight
  	jq(function() { 
	    jq('#tvvktb').blur(function() {
		  //init field(s)
    	  var grossWeight = jq('#tvvktb').val().replace(".","");
    	  grossWeight = grossWeight.replace(",",".");
	    
    	  if(jq('#tvvktn').val()==''){
			  var netWeightRaw = Math.round(grossWeight * 0.8);
			  jq('#tvvktn').val(netWeightRaw);
		  }
		});
	});
  	
  	
  	//============================
	//START - Currency AJAX fetch
	//============================
  	jq(function() { 
	    jq('#dkxv_222').blur(function() {
	    	//only when currency and rate are already in place
	    	if(jq('#dkxv_221').val()!='' && jq('#dkxv_221b').val()!=''){
		    	//alert('Hej');
		    	//this parameters must match the AJAX controller parameter names in Spring exactly...
	    		var isoDate = "";
	    		var faktisktAnkDato = jq('#datum').val();
	    		var forventatAnkDato = jq('#datum').val();
	    		if(faktisktAnkDato!=""){
	    			isoDate =faktisktAnkDato; 
	    		}else{
	    			isoDate =forventatAnkDato;
	    		}
	    		getCurrencyData(isoDate);
	    	}
	    });
	});
	jq(function() { 
	    jq('#dkxv_221').blur(function() {
	    	//alert('Hej');
	    	//this parameters must match the AJAX controller parameter names in Spring exactly...
    		var isoDate = "";
    		var faktisktAnkDato = jq('#datum').val();
    		var forventatAnkDato = jq('#datum').val();
    		if(faktisktAnkDato!=""){
    			isoDate =faktisktAnkDato; 
    		}else{
    			isoDate =forventatAnkDato;
    		}
    		getCurrencyData(isoDate);
	    });
	});
	jq(function() { 
	    jq('#dkxv_221b').blur(function() {
    		//alert('Hej');
    		//this parameters must match the AJAX controller parameter names in Spring exactly...
    		var dkxv_221b = jq('#dkxv_221b').val();
    		var isoDate = "";
    		var faktisktAnkDato = jq('#datum').val();
    		var forventatAnkDato = jq('#datum').val();
    		if(faktisktAnkDato!=""){
    			isoDate =faktisktAnkDato; 
    		}else{
    			isoDate =forventatAnkDato;
    		}
    		if(dkxv_221b==null || dkxv_221b==""){
    			getCurrencyData(isoDate);
    		}	
	    });
	});
	jq(function() { 
	    jq('#dkxv_221c').blur(function() {
    		//alert('Hej');
    		//this parameters must match the AJAX controller parameter names in Spring exactly...
    		var dkxv_221c = jq('#dkxv_221c').val();
    		var isoDate = "";
    		var faktisktAnkDato = jq('#datum').val();
    		var forventatAnkDato = jq('#datum').val();
    		if(faktisktAnkDato!=""){
    			isoDate =faktisktAnkDato; 
    		}else{
    			isoDate =forventatAnkDato;
    		}
    		if(dkxv_221c==null || dkxv_221c==""){
    			getCurrencyData(isoDate);
    		}	
	    });
	});
	//private function
	function getCurrencyData(isoDate) {
		jq.ajax({
			type: 'GET',
			url: 'getCurrencyRate_SkatNctsExport.do',
			data: { applicationUser : jq('#applicationUser').val(),
					currencyCode : jq('#dkxv_221').val(),
					isoDate : isoDate,
					invoiceAmount : jq('#dkxv_222').val(),
					toldsats : jq('#ownToldsats').val()},
			dataType: 'json',
			success: function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					jq('#dkxv_221b').val(data[i].dkvk_krs);
					jq('#dkxv_221c').val(data[i].dkvs_omr);
					//own field NOT comming from JSON but from Ajax-method
					jq('#dkxv_222b').val(data[i].own_blpDKK);
					jq('#dkxv_46').val(data[i].own_tollvDKK);
					jq('#dkxv_42b').val(data[i].own_momsDKK);
					jq('#dkxv_42c').val(data[i].own_grandTotalDKK);

				}
			}
		});
	}
	//============================
	//END - Currency AJAX fetch
	//============================
	
  	
  	
  	
  	
  	
  	
  	jq(document).ready(function() {
	      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
	      jq('#tblItemLinesAll').dataTable( {
	    	  "dom": '<"top">t<"bottom"flip><"clear">',
	    	  "scrollY":    "800px",
	    	  "deferRender": true,
	  		  "scrollCollapse":  true,
	  		  "columnDefs": [{ "type": "num", "targets": 0 }],
	  		  "lengthMenu": [ 75, 100, 300, 400, 900]
	  	  });
	      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
	      jq('#tblItemLines').dataTable( {
	    	  "dom": '<"top">t<"bottom"flip><"clear">',
	    	  "scrollY":    "180px",
	    	  "deferRender": true, //to speed the table load
	    	  "scrollCollapse":  true,
	  		  "columnDefs": [{ "type": "num", "targets": 0 }],
	  		  "lengthMenu": [ 75, 100, 300, 400, 900]
	  	  });
	      
	      //event on input field for search
	      jq('input.tblItemLines_filter').on( 'keyup click', function () {
	      		filterGlobal();
	      });
	      //event on input field for search
	      jq('input.tblItemLinesAll_filter').on( 'keyup click', function () {
	      		filterGlobal();
	      });
	      
	      //
		  jq('#tvvnt').focus();

	});
  	
  //---------------------------------------
    //DELETE record
    //This is done in order to present a jquery
    //Alert modal pop-up
    //----------------------------------------
    function doDeleteAll(){
    	  //Localize
    	  //DA - std
    	  var dlgTitle = "Fjern alle vareposter "; var btnTextOk = "Fortsæt"; var btnTextCancel = "Annullér";
    	  var legend = "Er du sikker på at du vil slette alle?";
    	  //EN
    	  if(jq("#usrLang").val() == "EN"){
    		  dlgTitle = "Delete all item lines "; btnTextOk = "Ok"; btnTextCancel = "Cancel"; 
    		  legend = "Are you sure you want to delete all items?"
    	  }
    	  
      	//Start dialog
      	jq('<div></div>').dialog({
            modal: true,
            title: dlgTitle,
            
            buttons: [ 
                {
    			 id: "dialogSave",	
    			 text: btnTextOk,
    			 click: function(){
    				 	jq( this ).dialog( "close" );
    		            //do delete
    		            jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
    		          	window.location = 'skatnctsexport_edit_items_deleteAll.do?avd='+ jq('#avd').val() + '&opd=' + jq('#opd').val();
    			 	 }
    		 	 },
     	 		{
    		 	 id: "dialogCancel",
    		 	 text: btnTextCancel, 
    			 click: function(){
    				 	jq( this ).dialog( "close" );
    				} 
     	 		 } ], 
     	 		open: function() {
    		  		  var markup = legend;
    		          jq(this).html(markup);
    		          //make Cancel the default button
    		          jq(this).siblings('.ui-dialog-buttonpane').find('button:eq(1)').focus();
    		     }
    	});  //end dialog
    
    }
    