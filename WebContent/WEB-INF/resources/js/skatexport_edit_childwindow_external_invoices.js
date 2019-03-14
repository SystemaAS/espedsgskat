	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
    
  	
  	jq(function() {
		jq('#buttonCloseOk').click(function(){
			jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
			
			var checkedCounterTillaggs = 0; //since the opener ONLY can receive max: 2 alternatives. This is the ultimate counter for knowing which receiver (1,2 or 3)
			jq( ".clazzInvoiceAware" ).each(function( i ) {
				  var id = this.id;
				  var record = id.split('_');
				  var id = record[0].replace("id", "");
				  var unik = record[1].replace("unik", "");
				  var counter = i + 1;
				  var requestParams = "&avd=" + jq('#avd').val() + "&opd=" + jq('#opd').val() + "&mode=U" + "&reff=" + id + "&unik=" + unik;
				  //alert(requestParams);
				  
				  if(jq('#id' + id + '_' + 'unik' + unik).prop('checked')){
					  //alert(id + '_' + unik + "-XX-" + jq('#id' + id + '_' + 'unik' + unik).prop('checked'));
					  
					  jq.ajax({
				  	  	  type: 'GET',
				  	  	  url: 'updateExternalInvoiceLine_SkatExport.do',
				  	  	  data: { applicationUser : jq('#applicationUser').val(),
						  			requestParams : requestParams },
				  	  	  dataType: 'json',
				  	  	  cache: false,
				  	  	  async: false,
				  	  	  contentType: 'application/json',
				  	  	  success: function(data) {
				  	  		var len = data.length;
				  	  		for ( var i = 0; i < len; i++) {
				  	  			//Update has been done successfully
				  	  		}
				  	  	  },
					  	  error: function() {
				  	  	    //alert('Error loading ...');
				  	  	  }
				  	  });
					  
			  	  }
			});
			//we must reload the parent window since the use case updates the invoice list (if the end-user has selected some invoices to import)
			window.opener.location.reload();
			window.close();
		});
		//abort
		jq('#cancel').click(function(){
			window.close();
		});
		
	});
  	
  	
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
      jq('#tblInvoices').dataTable( {
    	  "dom": '<"top"fli>rt<"bottom"p><"clear">',
  		  "scrollY":    "350px",
  		  "scrollCollapse":  true,
		  "lengthMenu": [ 75, 100]
  	  });
      //event on input field for search
      jq('input.tblInvoices_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
    });

	
  //---------------------------------------
    //DELETE Invoice
    //This is done in order to present a jquery
    //Alert modal pop-up
    //----------------------------------------
    function doPermanentlyDelete(element){
      //start
      var record = element.id.split('@');
  	  var id = record[0];
  	  var unik = record[1];
  	  id= id.replace("id","");
  	  unik= unik.replace("unik","");
  	  var requestParams = "&avd=" + jq('#avd').val() + "&opd=" + jq('#opd').val() + "&mode=D" + "&reff=" + id + "&unik=" + unik;
  	  	//Start dialog
  	  	jq('<div></div>').dialog({
  	        modal: true,
  	        title: "Fjern ekst.ref " ,
  	        buttons: {
  		        Fortsett: function() {

  		        	jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
  		            
		  	  		jq.ajax({
				  	  	  type: 'GET',
				  	  	  url: 'updateExternalInvoiceLine_SkatExport.do',
				  	  	  data: { applicationUser : jq('#applicationUser').val(),
						  			requestParams : requestParams },
				  	  	  dataType: 'json',
				  	  	  cache: false,
				  	  	  async: false,
				  	  	  contentType: 'application/json',
				  	  	  success: function(data) {
				  	  		  //reload
		  		        	  window.location = "skatexport_edit_childwindow_external_invoices.do?avd=" + jq("#avd").val() + "&opd=" + jq("#opd").val();
				  	  		  
				  	  	  },
					  	  error: function() {
				  	  	    //alert('Error loading ...');
				  	  	  }
				  	  });
  	        		
  		        },
  		        Avbryt: function() {
  		            jq( this ).dialog( "close" );
  		        }
  	        },
  	        open: function() {
  		  		  var markup = "Er du sikker på at du vil slette denne?";
  		          jq(this).html(markup);
  		          //make Cancel the default button
  		          jq(this).siblings('.ui-dialog-buttonpane').find('button:eq(1)').focus();
  		     }
  		     
  		});  //end dialog
    }	
    
  	
  	
	