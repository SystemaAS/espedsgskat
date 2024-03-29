  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
  jq(function() {
	  jq("#opd").focus();
	  
	  //Overlay
	  jq( "#submit" ).click(function( event ) {
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
  	  });
	  
	  //External references in model Dialog
	  	jq('#extRefIdLink').click(function() {
	    	jq('#extRefIdLink').attr('target','_blank');
	    	window.open('skatexport_childwindow_external_references.do?avd=' + jq("#selectedAvd").val(), "codeWin", "top=300px,left=750px,height=500px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#extRefIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#extRefIdLink').click();
			}
	    });
  });
  
  jq(function() {
	  jq("#datum").datepicker({ 
		  dateFormat: 'yymmdd'	  
	  });
	  jq("#datumt").datepicker({ 
		  dateFormat: 'yymmdd'	  
	  });
	  
	  jq("#sendAllDtm2").datetimepicker({ 
  		  dateFormat: 'yymmdd',
		  controlType: 'select',
		  timeFormat: 'HHmm' 
	  });
	  jq('#sendAllDtm2').change(function() {
	  		var val = jq("#sendAllDtm2").val();
	  		jq("#sendAllDtm2").val(val.replace(' ', ''));
	  	  });
	  
  });
  
  
  //-----------------------------------
  //START Model dialog "Kopiera Ärende
  //-----------------------------------
  //Initialize <div> here
  jq(function() { 
	  jq( ".clazz_dialog" ).each(function(){
		jq(this).dialog({
			autoOpen: false,
			modal: true
		});
	  });
  });
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq(".copyLink").click(function() {
		  //Localize
		  //DA - std
		  var dlgTitle = "Kopi Angivelse "; var btnTextOk = "Fortsæt"; var btnTextCancel = "Annullér";
		  //EN
		  if(jq("#usrLang").val() == "EN"){
			  dlgTitle = "Copy declaration "; btnTextOk = "Ok"; btnTextCancel = "Cancel"; 
		  }
		  
		  var id = this.id;
		  counterIndex = id.replace("copyLink","");
		  //setters (add more if needed)
		  jq('#dialog'+counterIndex).dialog( "option", "title", dlgTitle + jq('#originalOpd'+counterIndex).val() );
		  
		  //deal with buttons for this modal window
		  jq('#dialog'+counterIndex).dialog({
			 buttons: [ 
	            {
				 id: "dialogSave"+counterIndex,	
				 text: btnTextOk,
				 click: function(){
					 		jq('#copyForm'+counterIndex).submit();
							jq("#newAvd"+counterIndex).val("");
					 		jq("#newSign"+counterIndex).val("");
							jq( this ).dialog( "close" ); 
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancel"+counterIndex,
			 	 text: btnTextCancel, 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		jq("#dialogSave"+counterIndex).button("option", "disabled", true);
					 		jq("#newAvd"+counterIndex).val("");
					 		jq("#newSign"+counterIndex).val("");
							jq( this ).dialog( "close" ); 
					 		  
				 		} 
	 	 		 } ] 
			  
		  });
		  //init values
		  jq("#dialogSave"+counterIndex).button("option", "disabled", true);
		  
		  //open now
		  jq('#dialog'+counterIndex).dialog('open');
		 
	  });
  });
  //Events for the drop downs (some kind of "implicit validation" since all drop downs are mandatory)
  jq(function() {
	  jq(".newAvd").change(function() {
		  if(jq("#dialog"+counterIndex).find('.newAvd').val()!='' && jq("#dialog"+counterIndex).find('.newSign').val()!=''){
			  jq("#dialogSave"+counterIndex).button("option", "disabled", false);
			  
		  }else{
			  jq("#dialogSave"+counterIndex).button("option", "disabled", true);
		  }
	  });
	  jq(".newSign").change(function() {
		  if(jq("#dialog"+counterIndex).find('.newAvd').val()!='' && jq("#dialog"+counterIndex).find('.newSign').val()!=''){
			  jq("#dialogSave"+counterIndex).button("option", "disabled", false);
			  
		  }else{
			  jq("#dialogSave"+counterIndex).button("option", "disabled", true);
		  }
	  });
  });
  //---------------------------------
  //END Model dialog "Kopiera Ärende
  //---------------------------------
	
  //---------------------------------------
  //DELETE Angivelse
  //This is done in order to present a jquery
  //Alert modal pop-up
  //----------------------------------------
  function doPermanentlyDelete(element){
	  //start
	  var record = element.id.split('@');
	  var avd = record[0];
	  var opd = record[1];
	  avd= avd.replace("avd_","");
	  opd= opd.replace("opd_","");
	  
	  //Localize
	  //DA - std
	  var dlgTitle = "Fjern Angivelse "; var btnTextOk = "Fortsæt"; var btnTextCancel = "Annullér";
	  var legend = "Er du sikker på, at du vil slette dette?";
	  //EN
	  if(jq("#usrLang").val() == "EN"){
		  dlgTitle = "Delete declaration "; btnTextOk = "Ok"; btnTextCancel = "Cancel"; 
		  legend = "Are you sure you want to delete this record?"
	  }
	  
  	//Start dialog
  	jq('<div></div>').dialog({
        modal: true,
        title: dlgTitle + opd,
        
        buttons: [ 
            {
			 id: "dialogSave",	
			 text: btnTextOk,
			 click: function(){
				 	jq( this ).dialog( "close" );
		            //do delete
		            jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
		            window.location = "skatexport.do?action=doDelete" + "&avd=" + avd + "&opd=" + opd;
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
  
//-----------------------------------------------------------------------------
  //START Model dialog "Kopiera Ärende from template (norsk import/transport order)
  //---------------------------------------------------------------------------
  //Initialize <div> here
  jq(function() { 
	  jq("#dialogCopyFromTransportUppdrag").dialog({
		  autoOpen: false,
		  maxWidth:600,
          maxHeight: 600,
          width: 500,
          height: 500,
		  modal: true
	  });
  });
  //Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq("#copyFromTransportUppdragLink").click(function() {
		  //Localize
		  //DA - std
		  var dlgTitle = "Hent opdrag fra SYSPED"; var btnTextOk = "Fortsæt"; var btnTextCancel = "Annullér";
		  //EN
		  if(jq("#usrLang").val() == "EN"){
			  dlgTitle = "Get the order from SYSPED"; btnTextOk = "Ok"; btnTextCancel = "Cancel"; 
		  }
		  
		  //setters (add more if needed)
		  jq('#dialogCopyFromTransportUppdrag').dialog( "option", "title", dlgTitle );
		  
		  //deal with buttons for this modal window
		  jq('#dialogCopyFromTransportUppdrag').dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU",	
				 text: btnTextOk,
				 click: function(){
					 		jq('#copyFromTransportUppdragForm').submit();
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelTU",
			 	 text: btnTextCancel, 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		jq("#dialogSaveSU").button("option", "disabled", true);
					 		jq("#selectedAvd").val("");
					 		jq("#selectedOpd").val("");
					 		jq("#selectedExtRefNr").val("");
							jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  jq("#dialogSaveSU").button("option", "disabled", false);
		  //open now
		  jq('#dialogCopyFromTransportUppdrag').dialog('open');
	  });
  });
//Events for the drop downs (some kind of "implicit validation" since all drop downs are mandatory)
  jq(function() {
	  /*deprecated
	  jq("#selectedAvd").change(function() {
		  if(jq('#selectedAvd').val()!=''){
			  if(jq('#selectedOpd').val()!='' || jq('#selectedExtRefNr').val()!=''){
				  jq("#dialogSaveTU").button("option", "disabled", false);
			  }else{
				  jq("#dialogSaveTU").button("option", "disabled", true);
			  }
		  }else{
			  applyRuleOnDialogTranspUppdragNullAvd();
		  }
	  });
	  jq("#selectedOpd").blur(function() {
		  if(jq('#selectedAvd').val()!=''){
			  if(jq("#selectedOpd").val()!=''){
				  jq('#selectedExtRefNr').val("");
				  jq("#dialogSaveTU").button("option", "disabled", false);
			  }else{
				  if(jq("#selectedExtRefNr").val()==''){
					  jq("#dialogSaveTU").button("option", "disabled", true);
				  }
			  }
		  }else{
			  applyRuleOnDialogTranspUppdragNullAvd();
		  }
	  });
	  jq("#selectedExtRefNr").blur(function() {
		  if(jq('#selectedAvd').val()!=''){
			  if(jq("#selectedExtRefNr").val()!=''){
				  jq('#selectedOpd').val("");
				  jq("#dialogSaveTU").button("option", "disabled", false);
			  }else{
				  if(jq("#selectedOpd").val()==''){
					  jq("#dialogSaveTU").button("option", "disabled", true);
				  }
			  }
		  }else{
			  applyRuleOnDialogTranspUppdragNullAvd();
		  }
	  });
	  */
  });
  function applyRuleOnDialogTranspUppdragNullAvd(){
	  if(jq('#selectedOpd').val()=='' && jq('#selectedExtRefNr').val()==''){
		  jq("#dialogSaveTU").button("option", "disabled", false);
	  }else{
		  jq("#dialogSaveTU").button("option", "disabled", true);
	  }
  }
  function applyRuleOnDialogTranspUppdragExtRef(){
	  if(jq('#selectedExtRefNr').val()!=''){
		  jq("#dialogSaveTU").button("option", "disabled", false);
	  }else{
		  jq("#dialogSaveTU").button("option", "disabled", true);
	  }
  }
  
  //-----------------------------------------------
  //END Model dialog "Kopiera Ärende from template
  //-----------------------------------------------

//Initialize <div> here
  jq(function() { 
	  jq("#dialogSendAll").dialog({
		  autoOpen: false,
		  maxWidth:300,
          maxHeight: 350,
          width: 300,
          height: 350,
		  modal: true
	  });
  });
  
//Present dialog box onClick (href in parent JSP)
  jq(function() {
	  jq("#buttonSendAll").click(function() {
		  //Localize
		  //DA - std
		  var dlgTitle = "Sæt Faktisk ekspeditionstid"; var btnTextOk = "Fortsæt"; var btnTextCancel = "Annullér";
		  //EN
		  if(jq("#usrLang").val() == "EN"){
			  dlgTitle = "Set the Actual expedition time"; btnTextOk = "Ok"; btnTextCancel = "Cancel"; 
		  }
		  
		  
		  //setters (add more if needed)
		  jq('#dialogSendAll').dialog( "option", "title", dlgTitle );
		  
		  //deal with buttons for this modal window
		  jq('#dialogSendAll').dialog({
			 buttons: [ 
	            {
				 id: "buttonSaveOk",	
				 text: btnTextOk,
				 click: function(){
	            			sendAllWithStatus11();
				 		}
			 	 },
	 	 		{
			 	 id: "buttonCancel",
			 	 text: btnTextCancel, 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		//jq("#buttonSaveOk").button("option", "disabled", true);
					 		//jq("#selectedAvd").val("");
					 		//jq("#selectedOpd").val("");
					 		//jq("#selectedExtRefNr").val("");
							jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  jq("#buttonSaveOk").button("option", "disabled", false);
		  //open now
		  jq('#dialogSendAll').dialog('open');
	  });
  });
  
  //Send multiple selections to the controller here
  function sendAllWithStatus11() {
	  //init place holder
		var requestParams = "";
		var RECORD_SEPARATOR = ';';
		jq( ".clazzSendAware" ).each(function( i ) {
			  var id = this.id;
			  var record = id.split('_');
			  var syav = record[0].replace("syav", "");
			  var syop = record[1].replace("syop", "");
			  var counter = i + 1;
			  //
			  if(jq('#syav' + syav + '_' + 'syop' + syop ).prop('checked')){
				  var str = "&avd=" + syav + "&opd=" + syop + "&dtm2=" + jq('#sendAllDtm2').val();
				//start
				  requestParams += str + RECORD_SEPARATOR;
			  }  
		});	
		//DEBUG --> alert(requestParams);
		if(requestParams != '' && jq('#sendAllDtm2').val() != ''){
			//DEBUG-->alert("A");
			jq.ajax({
				type: 'GET',
				url: 'sendAllSkatExportStatus11_SkatExport.do',
				data: { applicationUser : jq('#applicationUser').val(),
						requestParams : requestParams },
				dataType: 'json',
				cache: false,
				contentType: 'application/json',
	  	  	  	success: function(data) {
					//Update has been done successfully
					jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
		  			window.location.reload();
		  			window.close();		
		  	  		/*
		  			var len = data.length;
		  	  		for ( var i = 0; i < len; i++) {
		  	  			//Update has been done successfully
		  	  			
		  	  		}*/
				},
		  	  	error: function() {
	  	  	    	alert('Error loading ...');
	  	  		}
			});
			//reload
			//jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
  			//window.location.reload();
  			//window.close();
		}
		
  	}

  
  
  
  