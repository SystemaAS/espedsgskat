	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var counterIndex = 0;
 	 var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
 	 
  	//--------
  	//Koder
  	//--------
	jq(function() {
		jq('#tblList').on('click', 'td', function(){
			  var id = this.id;
			  var refnr = id.replace("ref", "");
			  //var callerType = record[1].replace("ctype", "");
			  //alert(refnr);
			  
			  opener.jq('#selectedExtRefNr').val(refnr);
			  opener.jq('#selectedExtRefNr').focus();
				  
			  //close child window
			  window.close();
		  });
	});
	
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filter () {
    		jq('#tblList').DataTable().search(
      		jq('#tblList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#tblList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 75, 100, 200, 500]
    	  });
      //event on input field for search
      jq('input.tblList_filter').on( 'keyup click', function () {
      		filter();
      });
      
    });
    
    //---------------------------------------
    //DELETE Order
    //This is done in order to present a jquery
    //Alert modal pop-up
    //----------------------------------------
    function doPermanentlyDeleteExternalRef(element){
      //start
  	  var record = element.id.split('@');
  	  var avd = record[0];
  	  var opd = record[1];
  	  avd= avd.replace("avd_","");
  	  opd= opd.replace("opd_","");
  	  alert("avd:" + avd + " opd:" + opd);
  	  	//Start dialog
  	  	jq('<div></div>').dialog({
  	        modal: true,
  	        title: "Fjern ekst.ref " ,
  	        buttons: {
  		        Fortsett: function() {
  	        		jq( this ).dialog( "close" );
  		            //do delete
  		            jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  		            window.location = "skatexport_edit_childwindow_external_references_delete.do?action=doDelete&parentAvd=" + jq("#parentAvd").val() + "&fsavd=" + avd + "&fsopd=" + opd;
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
  	  alert("Z");
    }	
    
    
    
  	
  	
  	
	