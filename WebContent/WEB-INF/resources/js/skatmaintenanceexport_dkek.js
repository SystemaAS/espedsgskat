  //this variable is a global jQuery var instead of using "$" all the time. Very handy
  var jq = jQuery.noConflict();
  var counterIndex = 0;
  var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  
  function setBlockUI(element){
	  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  }
  
  jq(function() {
	  jq("#formRecord").submit(function() {
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT}); 
	  });
	  
	  //----------------
	  //CUSTOMER search
	  //----------------
	  //Kundnr - dkek_knr
	  jq('#dkek_knrIdLink').click(function() {
		  jq('#dkek_knrIdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_customer.do?action=doFind&firma=' + jq('#companyCode').val() + "&knr=" + jq('#dkek_knr').val() + '&ctype=dkek_knr', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#dkek_knrIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#dkek_knrIdLink').click();
  		  }
  	  });
	//Kundnr - search_dkek_knrIdLink
	  jq('#search_dkek_knrIdLink').click(function() {
		  jq('#search_dkek_knrIdLink').attr('target','_blank');
		  window.open('mainmaintenance_childwindow_customer.do?action=doFind&firma=' + jq('#companyCode').val() + "&knr=" + jq('#search_dkek_knr').val() + '&ctype=search_dkek_knr', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	  });
	  jq('#search_dkek_knrIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#search_dkek_knrIdLink').click();
  		  }
  	  });
	  
	  
  	  
  });
  
  
  jq(function() {
		//Clean values for creating new record
		jq('#newRecordButton').click(function() {
			jq('#dkek_knr').val("");
			jq("#dkek_knr").prop("readonly", false);
			jq("#dkek_knr").removeClass("inputTextReadOnly");
			jq("#dkek_knr").addClass("inputTextMediumBlueMandatoryField");
			//
			jq('#dkek_vnr').val("");
			jq("#dkek_vnr").prop("readonly", false);
			jq("#dkek_vnr").removeClass("inputTextReadOnly");
			jq("#dkek_vnr").addClass("inputTextMediumBlueMandatoryField");
			
			//rest of the gang
			jq('#dkek_331').val("");
			jq('#dkek_315').val("");
			jq('#dkek_34a').val("");
			jq('#dkek_37').val("");
  			jq('#dkek_311').val("");
  			jq('#dkek_314').val("");
  			jq('#dkek_402a').val("");
  			jq('#dkek_403a').val("");
  			jq('#dkek_411').val("");
  			jq('#dkek_441').val("");
  			jq('#dkek_442a').val("");
  			jq('#dkek_4421').val("");
  			jq('#dkek_443').val("");
  			jq('#dkek_444').val("");
			//
  			jq('#dkek_447').val("");
  			jq('#dkek_446a').val("");
  			//
  			jq('#dkek_332').val("");
  			jq('#dkek_sikk').val("");
  			jq('#dkek_449a').val("");
  			jq('#dkek_49').val("");
  			jq('#dkek_bem1').val("");
			//for update
			jq('#updateId').val("");
		});
  }); 
  
  //-----------------------
  //GET specific db-record
  //-----------------------
  function getRecord(record){
	
	var rawId = record.id;
  	var applicationUserParam = jq('#applicationUser').val();
  	//recordUpdate_${record.dkek_knr}_${record.dkek_vnr}
  	rawId = rawId.replace("recordUpdate_", "");
  	var record = rawId.split('_');
	var dkek_knr = record[0];
	var dkek_vnr= record[1];
	
	jq.ajax({
  	  type: 'GET',
  	  url: 'getSpecificRecord_dkek.do',
  	  data: { applicationUser : jq('#applicationUser').val(), 
  		  	  dkek_knr : dkek_knr,
  		  	  dkek_vnr : dkek_vnr},
  	  dataType: 'json',
  	  cache: false,
  	  contentType: 'application/json',
  	  success: function(data) {
	  	var len = data.length;
  		for ( var i = 0; i < len; i++) {
  			jq('#dkek_knr').val("");jq('#dkek_knr').val(data[i].dkek_knr);
  			jq("#dkek_knr").prop("readonly", true);
  			jq("#dkek_knr").removeClass("inputTextMediumBlueMandatoryField");
  			jq("#dkek_knr").addClass("inputTextReadOnly");
  			//
  			jq('#dkek_vnr').val("");jq('#dkek_vnr').val(data[i].dkek_vnr);
  			jq("#dkek_vnr").prop("readonly", true);
  			jq("#dkek_vnr").removeClass("inputTextMediumBlueMandatoryField");
  			jq("#dkek_vnr").addClass("inputTextReadOnly");
  			
  			//rest of the gang
  			jq('#dkek_331').val("");jq('#dkek_331').val(data[i].dkek_331);
  			jq('#dkek_315').val("");jq('#dkek_315').val(data[i].dkek_315);
  			jq('#dkek_34a').val("");jq('#dkek_34a').val(data[i].dkek_34a);
  			jq('#dkek_37').val("");jq('#dkek_37').val(data[i].dkek_37);
  			jq('#dkek_311').val("");jq('#dkek_311').val(data[i].dkek_311);
  			jq('#dkek_314').val("");jq('#dkek_314').val(data[i].dkek_314);
  			//
  			jq('#dkek_402a').val("");jq('#dkek_402a').val(data[i].dkek_401a + data[i].dkek_402a);
  			jq('#dkek_403a').val("");jq('#dkek_403a').val(data[i].dkek_403a);
  			jq('#dkek_411').val("");jq('#dkek_411').val(data[i].dkek_411);
  			//
  			jq('#dkek_441').val("");jq('#dkek_441').val(data[i].dkek_441);
  			jq('#dkek_442a').val("");jq('#dkek_442a').val(data[i].dkek_442a);
  			jq('#dkek_4421').val("");jq('#dkek_4421').val(data[i].dkek_4421);
  			//
  			jq('#dkek_443').val("");jq('#dkek_443').val(data[i].dkek_443);
  			jq('#dkek_444').val("");jq('#dkek_444').val(data[i].dkek_444);
  			jq('#dkek_447').val("");jq('#dkek_447').val(data[i].dkek_447);
  			jq('#dkek_446a').val("");jq('#dkek_446a').val(data[i].dkek_446a);
  			
  			jq('#dkek_332').val("");jq('#dkek_332').val(data[i].dkek_332);
  			jq('#dkek_sikk').val("");jq('#dkek_sikk').val(data[i].dkek_sikk);
  			jq('#dkek_449a').val("");jq('#dkek_449a').val(data[i].dkek_449a);
  			jq('#dkek_49').val("");jq('#dkek_49').val(data[i].dkek_49);
  			jq('#dkek_bem1').val("");jq('#dkek_bem1').val(data[i].dkek_bem1);
  			//for a future update
  			jq('#updateId').val("");jq('#updateId').val(data[i].dkek_knr);
  			
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
    jq('#mainList').dataTable().search(
    	jq('#mainList_filter').val()
    ).draw();
  }
  
  jq(document).ready(function() {
      //init table (no ajax, no columns since the payload is already there by means of HTML produced on the back-end)
      jq('#mainList').dataTable( {
    	  "dom": '<"top">t<"bottom"flip><"clear">',
    	  "scrollY": "250px",
    	  "scrollCollapse":  false,
    	  "lengthMenu": [ 75, 100]
  	  });
      
      //event on input field for search
      jq('input.mainList_filter').on( 'keyup click', function () {
      		filterGlobal();
      });
  	
  });
  