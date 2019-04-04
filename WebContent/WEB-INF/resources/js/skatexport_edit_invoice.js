	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
    
  	//Overlay on tab (to mark visually a delay...)
    jq(function() {
	  jq('#alinkTopicList').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });	
  	  jq('#alinkHeader').click(function() { 
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  	  });
  	  jq('#alinkItemLines').click(function() { 
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  	  });
  	  jq('#alinkLogging').click(function() { 
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  	  });
  	  jq('#alinkArchive').click(function() { 
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  	  });
  	  
    });
    
  	jq(function() {
  	  	jq('#importInvoicesButton').click(function() {
  	  		window.open('skatexport_edit_childwindow_external_invoices.do?avd=' + jq("#avd").val() + "&opd=" + jq("#opd").val(), 'importInvoicesWin','top=120px,left=100px,height=650px,width=800px,scrollbars=no,status=no,location=no');
  	  	});
  	  	/*
  	  	//Typ ( same as Bilagda Handlingar on item level)
	    jq('#bilagdaHandIdLink').click(function() {
	    	jq('#bilagdaHandIdLink').attr('target','_blank');
	    	window.open('tdsexport_edit_invoice_childwindow_generalcodes.do?action=doInit&type=MCF&ctype=svef_faty', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#bilagdaHandIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#bilagdaHandIdLink').click();
			}
	    });
	    */
	    //Valuta
	    jq('#dkef_vakdIdLink').click(function() {
	    	jq('#dkef_vakdIdLink').attr('target','_blank');
	    	window.open('skatexport_edit_childwindow_generalcodes.do?action=doInit&type=107&ctype=dkef_vakd', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#dkef_vakdIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#dkef_vakdIdLink').click();
			}
	    });
  	});
  	
  
  	//Currency AJAX fetch
	jq(function() { 
	    jq('#dkef_vakd').change(function() {
	    	//alert('Hej');
	    	//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('getCurrencyRate_Skat.do', {
				applicationUser : jq('#applicationUser').val(),
				currencyCode : jq('#dkef_vakd').val(),
				ajax : 'true'
			}, function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					jq('#dkef_vaku').val(data[i].dkvk_krs);
					jq('#factor').val(data[i].dkvs_omr);
				}
				
			});
	    });
	});
	

  	//-----------------------------------------
  	//Get the item line for a potential Update
  	//-----------------------------------------
  	function getItemData(element) {
  	  var id = element.id;
  	  var record = id.split('_');
  	  var invoiceNr = record[1];
  	  
  	  jq.ajax({
  	  	  type: 'GET',
  	  	  url: 'getInvoiceLine_SkatExport.do',
  	  	  data: { applicationUser : jq('#applicationUser').val(),
  		  		  avd : jq('#avd').val(),
  		  		  opd : jq('#opd').val(),
  		  		  invoiceNr : invoiceNr },
  	  	  dataType: 'json',
  	  	  cache: false,
  	  	  contentType: 'application/json',
  	  	  success: function(data) {
  	  		var len = data.length;
  	  		for ( var i = 0; i < len; i++) {
  	  			jq("#isModeUpdate").val("true");
  	  			jq("#dkef_fatx").val(data[i].dkef_fatx);
  	  			jq("#dkef_fabl" ).val(data[i].dkef_fabl);
  	  			jq("#dkef_vakd").val(data[i].dkef_vakd);
  	  			jq("#dkef_vaku").val(data[i].dkef_vaku);
  	  			jq("#dkef_faty").val(data[i].dkef_faty);
  	  		}
  	  	  },
	  	  error: function() {
  	  	    alert('Error loading ...');
  	  	  }
  	  });
  	  
    }

  	
  	//-------------------
    //Datatables jquery
    //-------------------
    //private function
    function filterGlobal () {
      jq('#tblInvoices').DataTable().search(
      	jq('#tblInvoices_filter').val()
      ).draw();
    }

    jq(document).ready(function() {
      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
      jq('#tblInvoices').DataTable( {
    	  "dom": '<"top">t<"bottom"f><"clear">',
  		  "scrollY":    "110px",
  		  "scrollCollapse":  true,
  		  "lengthMenu": [ 25, 50]
  	  });
      //event on input field for search
      jq('input.tblInvoices_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
    });

    
    
  //---------------------------------------
  //DELETE record
  //This is done in order to present a jquery
  //Alert modal pop-up
  //----------------------------------------
  function doPermanentlyDelete(element){
  	  //start
  	  var record = element.id.split('@');
  	  var avd = record[0];
  	  var opd = record[1];
  	  var sign = record[2];
  	  var status = record[3];
  	  var fak = record[4];
	  
  	  avd= avd.replace("avd_","");
  	  opd= opd.replace("opd_","");
  	  sign= sign.replace("sign_","");
  	  status= status.replace("status_","");
  	  fak= fak.replace("fak_","");
  	  
  	  //Localize
  	  //DA - std
  	  var dlgTitle = "Fjern Faktura "; var btnTextOk = "Fortstæt"; var btnTextCancel = "Annullér";
  	  var legend = "Er du sikker på, at du vil slette dette?";
  	  //EN
  	  if(jq("#usrLang").val() == "EN"){
  		  dlgTitle = "Delete invoice "; btnTextOk = "Ok"; btnTextCancel = "Cancel"; 
  		  legend = "Are you sure you want to delete this record?"
  	  }
  	  
    	//Start dialog
    	jq('<div></div>').dialog({
          modal: true,
          title: dlgTitle + opd + "/" + fak,
          
          buttons: [ 
              {
  			 id: "dialogSave",	
  			 text: btnTextOk,
  			 click: function(){
  				 	jq( this ).dialog( "close" );
  		            //do delete
  		            jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  		            window.location = "skatexport_edit_invoice.do?action=doDelete" + "&avd=" + avd + "&opd=" + opd + "&sign=" + sign + "&status=" + status + "&fak=" + fak;
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
  	
  	
	