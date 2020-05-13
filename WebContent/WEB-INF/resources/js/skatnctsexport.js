  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
  jq(function() {
	  jq("#opd").focus();
  });
  
  jq(function() {
	  jq("#datum").datepicker({ 
		  dateFormat: 'yymmdd'   
	  });
	  jq("#datumt").datepicker({ 
		  dateFormat: 'yymmdd'   
	  });
	  //Overlay
	  jq( "#submit" ).click(function( event ) {
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
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
	  
  
//-----------------------------------------------------------------------------
  //START Model dialog "Kopiera Ärende from template (norsk export/transport order)
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
  //-----------------------
  //END Transportuppdrag
  //-----------------------
  
  jq(function() {
	  jq('#extRefIdLink').click(function() {
	  	jq('#extRefIdLink').attr('target','_blank');
	  	window.open('skatnctsexport_edit_items_childwindow_angivelselist.do?action=doFind&avd=' + jq('#selectedAvd').val() + '&opd=' + jq('#selectedOpd').val() + '&xref=' + jq('#selectedExtRefNr').val(), "codeWin", "top=300px,left=500px,height=600px,width=1200px,scrollbars=no,status=no,location=no");
	  });
	  jq('#extRefIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#extRefIdLink').click();
			}
	  });
  });
 

