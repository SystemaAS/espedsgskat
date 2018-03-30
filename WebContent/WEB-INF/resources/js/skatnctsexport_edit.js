	//===========================================
	//General functions for this JSP side - AJAX
	//===========================================
	
	//this variable is a global jQuery var instead of using "$" all the time. Very handy
	var jq = jQuery.noConflict();
	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
	  
	//Overlay on tab (to mark visually a delay...)
    jq(function() {
      jq('#alinkTopicList').click(function() { 
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
  	  
  	  jq( "#submit" ).click(function( event ) {
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
  	  });
  	  jq( "#submit2" ).click(function( event ) {
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT });
	  });
  	  
  	  
  	  	//----------------
	  	//CUSTOMER search
	  	//----------------
	    //SENDER
	    jq('#thnasIdLink').click(function() {
	    	jq('#thnasIdLink').attr('target','_blank');
	    	window.open('skat_childwindow_customer.do?action=doFind&sonavn=' + jq('#thnas').val() + '&ctype=thnas', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#thnasIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#thnasIdLink').click();
			}
	    });
	    //MOTTAKER
	    jq('#thnakIdLink').click(function() {
	    	jq('#thnakIdLink').attr('target','_blank');
	    	window.open('skat_childwindow_customer.do?action=doFind&sonavn=' + jq('#thnak').val() + '&ctype=thnak', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#thnakIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#thnakIdLink').click();
			}
	    });
	    //Ansvarige
	    jq('#thnaaIdLink').click(function() {
	    	jq('#thnaaIdLink').attr('target','_blank');
	    	window.open('skat_childwindow_customer.do?action=doFind&sonavn=' + jq('#thnaa').val() + '&ctype=thnaa', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#thnaaIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#thnaaIdLink').click();
			}
	    });
	    
	    //SIKKERHED
	    //SENDER - SIKK
	    jq('#thnassIdLink').click(function() {
	    	jq('#thnassIdLink').attr('target','_blank');
	    	window.open('skat_childwindow_customer.do?action=doFind&sonavn=' + jq('#thnass').val() + '&ctype=thnass', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#thnassIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#thnassIdLink').click();
			}
	    });
	    //MOTTAKER - SIKK
	    jq('#thnaksIdLink').click(function() {
	    	jq('#thnaksIdLink').attr('target','_blank');
	    	window.open('skat_childwindow_customer.do?action=doFind&sonavn=' + jq('#thnaks').val() + '&ctype=thnaks', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#thnaksIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#thnaksIdLink').click();
			}
	    });
	    //Carrier - SIKK
	    jq('#thnatIdLink').click(function() {
	    	jq('#thnatIdLink').attr('target','_blank');
	    	window.open('skat_childwindow_customer.do?action=doFind&sonavn=' + jq('#thnat').val() + '&ctype=thnat', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#thnatIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#thnatIdLink').click();
			}
	    });
	    //END CUSTOMER SEARCH
	    
	    
	  //Valuta
  	  jq('#thgvkIdLink').click(function() {
    	jq('#thgvkIdLink').attr('target','_blank');
    	window.open('skatnctsexport_edit_childwindow_generalcodes.do?action=doInit&type=107&ctype=thgvk', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
  	  });
  	  jq('#thgvkIdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#thgvkIdLink').click();
		}
  	  });
	    
    });
    
    
    //ChildWindow Country Codes
    function triggerChildWindowCountryCodes(record){
    	var idLink = record.id;
    	var id = idLink.replace("IdLink", "");
    	jq(idLink).attr('target','_blank');
    	window.open('skatnctsexport_edit_childwindow_generalcodes.do?action=doInit&type=008&ctype=' + id , "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    }
    //ChildWindow Language Codes
    function triggerChildWindowLanguageCodes(record){
    	var idLink = record.id;
    	var id = idLink.replace("IdLink", "");
    	jq(idLink).attr('target','_blank');
    	window.open('skatnctsexport_edit_childwindow_generalcodes.do?action=doInit&type=012&ctype=' + id , "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    }
    //ChildWindow Tullkontor Codes
    function triggerChildWindowTullkontorCodes(record){
    	var idLink = record.id;
    	var id = idLink.replace("IdLink", "");
    	//alert(idLink + "XX" + id);
    	//alert(jq("#"+id).val());
    	jq(idLink).attr('target','_blank');
    	window.open('skatnctsexport_edit_childwindow_tullkontor.do?action=doInit&tkkode=' + jq("#"+id).val()+ '&ctype=' + id, "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    }
	  
    
    
	jq(document).ready(function(){
  	    jq(this).scrollTop(0); //needed for Chrome (bug)
  	});
  	//------------
	//date fields
	//------------
    jq(function() {
  	  jq("#thtrdt").datepicker({ 
  		  dateFormat: 'yymmdd'
  	  });
  	  jq("#thddt").datepicker({ 
  		  dateFormat: 'yymmdd' 
  	  });
  	  jq("#thdta").datepicker({ 
		  dateFormat: 'yymmdd' 
	  });
  	  
    });
	//--------------------------
    //Events on specific fields
    //--------------------------
    jq(function() { 
	    jq('#thtsd1').blur(function() {
	    	  var transitOffice = jq('#thtsd1').val();
		  if(transitOffice!=null && transitOffice!=""){
    	  	  	jq('#thsik').val("1");
		  }else{
			jq('#thsik').val("");
			jq('#thdta').val("");
			jq('#thtma').val("");
		  }
		});
	    
	    //thtalk
	    jq('#thtalk').blur(function() {
	    	//Only with create new
	    	if(jq('#thtdn').val() == ''){
	    		jq('#thtgid').val(jq('#thtaid').val());
	    		jq('#thtglk').val(jq('#thtalk').val());
	    	}	
	    });
	});
	
    
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
				customerName : jq('#search_sveh_avna').val(),
				customerNumber : jq('#search_sveh_avkn').val(),
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
		  jq('#thnas').val("");
		  jq('#thtins').val("");
		  jq('#thads1').val("");
		  jq('#thpns').val("");
		  jq('#thpss').val("");
		  jq('#thlks').val("");
		  jq('#thsks').val("");
		  
		  //now populate (if applicable)
		  var key = jq('#senderList').val();
		  jq('#thkns').val(key);
		  customer = map[key];
		  jq('#thnas').val(customer.knavn);
		  jq('#thtins').val(customer.eori);
		  jq('#thads1').val(customer.adr1);
		  jq('#thpns').val(customer.postnr);
		  jq('#thpss').val(customer.adr3);
		  jq('#thlks').val(customer.syland);
		  jq('#thsks').val("");
	    });
	});
	
	//onClick for Sender dialog
	jq(function() { 
	    jq('#searchCustomerCloseCancel').click(function() {
	      //rescue the original fields
	      jq('#thkns').val(jq("#orig_thkns").val());	
		  jq('#thnas').val(jq("#orig_thnas").val());
		  jq('#thtins').val(jq("#orig_thtins").val());
		  jq('#thads1').val(jq("#orig_thads1").val());
		  jq('#thpns').val(jq("#orig_thpns").val());
		  jq('#thpss').val(jq("#orig_thpss").val());
		  jq('#thlks').val(jq("#orig_thlks").val());
		  jq('#thsks').val(jq("#orig_thsks").val());
	    });
	});
	
	//----------------------------------
	//Events Sender (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgCustomerSearch').click(function(){
    			jq("#search_sveh_avkn").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_avkn').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchSenderOwnWindow);
			}			
    		});
		jq('#search_sveh_avna').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchSenderOwnWindow);
			}			
    		});
	});
	//--------------------------------------------------------------------------------------
	//Extra behavior for Customer number ( without using (choose from list) extra roundtrip)
	//--------------------------------------------------------------------------------------
	jq(function() { 
	    jq('#thkns').blur(function() {
	    	
	    	if(jq('#thkns').val() != ''){
	    		jq.getJSON('searchCustomer_Skat.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : "",
				customerNumber : jq('#thkns').val(),
				ajax : 'true'
	    		}, function(data) {
				//alert("Hello");
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					//html += '<option value="' + data[i].kundnr + '">' + data[i].knavn + '</option>';
					customer = new Object();
					customer.kundnr = data[i].kundnr;
					customer.knavn = data[i].knavn;
					customer.eori = data[i].eori;
					customer.adr1 = data[i].adr1;
					customer.adr2 = data[i].adr2;
					customer.adr3 = data[i].adr3;
					customer.postnr = data[i].sypoge;//data[i].postnr; DK=sypoge
					if("" == customer.postnr){
						customer.postnr = data[i].postnr;
					}
					customer.kpers = data[i].kpers;
					customer.tlf = data[i].tlf;
					customer.syland = data[i].syland;
				  	//put the object in map now with customerNumber as key
					map[customer.kundnr] = customer;
				}
				if(len > 0){
					jq('#thkns').val(customer.kundnr);
					jq('#thtins').val(customer.eori);
					jq('#thnas').val(customer.knavn);
					jq('#thads1').val(customer.adr1);
					jq('#thpns').val(customer.postnr);
					jq('#thpss').val(customer.adr3);
					jq('#thlks').val(customer.syland);
				}else{
					//init fields
					jq('#thtins').val("");
					jq('#thnas').val("");
					jq('#thads1').val("");
					jq('#thpns').val("");
					jq('#thpss').val("");
					jq('#thlks').val("");
				}
	    		});
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
				customerName : jq('#search_sveh_mona').val(),
				customerNumber : jq('#search_sveh_mokn').val(),
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
		  jq('#thnak').val("");
		  jq('#thtink').val("");
		  jq('#thadk1').val("");
		  jq('#thpnk').val("");
		  jq('#thpsk').val("");
		  jq('#thlkk').val("");	
		  jq('#thskk').val("");	
		  
		  //now populate (if applicable)
		  var key = jq('#receiverList').val();
		  jq('#thknk').val(key);
		  customer = map[key];
		  jq('#thtink').val(customer.eori);
		  jq('#thnak').val(customer.knavn);
		  jq('#thadk1').val(customer.adr1);
		  jq('#thpnk').val(customer.postnr);
		  jq('#thpsk').val(customer.adr3);
		  jq('#thlkk').val(customer.syland);
		  jq('#thskk').val("");			  
		});
	});
	//onClick for Receiver(Mottagare) dialog
	jq(function() { 
	    jq('#searchCustomer10CloseCancel').click(function() {
	      //rescue the original fields
	      jq('#thknk').val(jq("#orig_thknk").val());	
		  jq('#thnak').val(jq("#orig_thnak").val());
		  jq('#thtink').val(jq("#orig_thtink").val());
		  jq('#thadk1').val(jq("#orig_thadk1").val());
		  jq('#thpnk').val(jq("#orig_thpnk").val());
		  jq('#thpsk').val(jq("#orig_thpsk").val());
		  jq('#thlkk').val(jq("#orig_thlkk").val());
		  jq('#thskk').val(jq("#orig_thskk").val());
		  
	    });
	});

	//----------------------------------
	//Events Receiver (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgReceiverSearch').click(function(){
    			jq("#search_sveh_mokn").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_mokn').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchReceiverOwnWindow);
			}			
    		});
		jq('#search_sveh_mona').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchReceiverOwnWindow);
			}			
    		});
	});
	//--------------------------------------------------------------------------------------
	//Extra behavior for Customer number ( without using (choose from list) extra roundtrip)
	//--------------------------------------------------------------------------------------
	jq(function() { 
	    jq('#thknk').blur(function() {
	    	if(jq('#thknk').val() != ''){
	    		jq.getJSON('searchCustomer_Skat.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : "",
				customerNumber : jq('#thknk').val(),
				ajax : 'true'
	    		}, function(data) {
				//alert("Hello");
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					//html += '<option value="' + data[i].kundnr + '">' + data[i].knavn + '</option>';
					customer = new Object();
					customer.kundnr = data[i].kundnr;
					customer.knavn = data[i].knavn;
					customer.eori = data[i].eori;
					customer.adr1 = data[i].adr1;
					customer.adr2 = data[i].adr2;
					customer.adr3 = data[i].adr3;
					customer.postnr = data[i].sypoge;//data[i].postnr; DK=sypoge
					if("" == customer.postnr){
						customer.postnr = data[i].postnr;
					}
					customer.kpers = data[i].kpers;
					customer.tlf = data[i].tlf;
					customer.syland = data[i].syland;
				  	//put the object in map now with customerNumber as key
					map[customer.kundnr] = customer;
				}
				if(len > 0){
					jq('#thknk').val(customer.kundnr);
					jq('#thtink').val(customer.eori);
					jq('#thnak').val(customer.knavn);
					jq('#thadk1').val(customer.adr1);
					jq('#thpnk').val(customer.postnr);
					jq('#thpsk').val(customer.adr3);
					jq('#thlkk').val(customer.syland);
						 
				}else{
					//init fields
					jq('#thtink').val("");
					jq('#thnak').val("");
					jq('#thadk1').val("");
					jq('#thpnk').val("");
					jq('#thpsk').val("");
					jq('#thlkk').val("");
				}
	    		});
	    	}
		});
	});

	
	
  	//-----------------------------------------
	//FETCH CUSTOMER from ANSVARIG html area
  	//-----------------------------------------
	function searchAnsvarigOwnWindow() {
		jq(function() {
			jq.getJSON('searchCustomer_Skat.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : jq('#search_sveh_dkna').val(),
				customerNumber : jq('#search_sveh_dkkn').val(),
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].kundnr + '">' + data[i].knavn + '</option>';
					customer = new Object();
					customer.kundnr = data[i].kundnr;
					customer.knavn = data[i].knavn;
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
				jq('#ansvarigList').html(html);
			});
		});
	}
	//Sets ansvarig values after user selection
	jq(function() { 
	    jq('#ansvarigList').change(function() {
	      //init fields
		  jq('#thnaa').val("");
		  jq('#thtina').val("");
		  jq('#thada1').val("");
		  jq('#thpna').val("");
		  jq('#thpsa').val("");
		  jq('#thlka').val("");
		  jq('#thska').val("");
		  
		  //now populate (if applicable)
		  var key = jq('#ansvarigList').val();
		  jq('#sveh_dkkn').val(key);
		  customer = map[key];
		  jq('#thnaa').val(customer.knavn);
		  jq('#thtina').val(customer.eori);
		  jq('#thada1').val(customer.adr1);
		  jq('#thpna').val(customer.postnr);
		  jq('#thpsa').val(customer.adr3);
		  jq('#thlka').val(customer.syland);
		  jq('#thska').val("");			  
		});
	});
	//onClick for Ansvarig dialog
	jq(function() { 
	    jq('#searchCustomer20CloseCancel').click(function() {
	      //rescue the original fields
	      jq('#sveh_dkkn').val(jq("#orig_sveh_dkkn").val());	
		  jq('#thnaa').val(jq("#orig_thnaa").val());
		  jq('#thtina').val(jq("#orig_thtina").val());
		  jq('#thada1').val(jq("#orig_thada1").val());
		  jq('#thpna').val(jq("#orig_thpna").val());
		  jq('#thpsa').val(jq("#orig_thpsa").val());
		  jq('#thlka').val(jq("#orig_thlka").val());
		  jq('#thska').val(jq("#orig_thska").val());
		  
	    });
	});
	
	//----------------------------------
	//Events Ansvarig (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgAnsvarigSearch').click(function(){
    			jq("#search_sveh_dkkn").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_dkkn').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchAnsvarigOwnWindow);
			}			
    		});
		jq('#search_sveh_dkna').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchAnsvarigOwnWindow);
			}			
    		});
	});
	
		
	//Currency AJAX fetch
	/* Not in use
	jq(function() { 
	    jq('#sveh_vakd').change(function() {
	    	//alert('Hej');
	    	//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('getCurrencyRate_Skat.do', {
				applicationUser : jq('#applicationUser').val(),
				currencyCode : jq('#sveh_vakd').val(),
				ajax : 'true'
			}, function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					//data[i].svvk_krs;
					//data[i].svvs_omr;
					jq('#sveh_vaku').val(data[i].svvk_krs);
					jq('#sveh_vaom').val(data[i].svvs_omr);
				}
				
			});
	    });
	});
	*/
	
	//------------------------------------------------------------------------------------------
	//Init Topic with CREATE NEW TOPIC since some values are fetched depending on the Avdelning
	//------------------------------------------------------------------------------------------	
	jq(function() { 
	    jq('#avd').change(function() {
	    		//alert('Hej');
	    		//this parameters must match the AJAX controller parameter names in Spring exactly...
	    		jq.getJSON('initCreateNewTopic_SkatNctsExport.do', {
	    			applicationUser : jq('#applicationUser').val(),
	    			avd : jq('#avd').val(),
	    			ajax : 'true'
			}, 
			function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					//Sender
					jq('#thkns').val(data[i].thkns);
					jq('#thnas').val(data[i].thnas);
					jq('#thtins').val(data[i].thtins);
					jq('#thads1').val(data[i].thads1);
					jq('#thpns').val(data[i].thpns);
					jq('#thpss').val(data[i].thpss);
					jq('#thlks').val(data[i].thlks);
					if('DK'== data[i].thlks){
						jq('#thsks').val('DA');
					}
					//Receiver
					jq('#thknk').val(data[i].thknk);
					jq('#thnak').val(data[i].thnak);
					jq('#thtink').val(data[i].thtink);
					jq('#thadk1').val(data[i].thadk1);
					jq('#thpnk').val(data[i].thpnk);
					jq('#thpsk').val(data[i].thpsk);
					jq('#thlkk').val(data[i].thlkk);
					if('NO'== data[i].thlkk){
						jq('#thskk').val('NO');
					}
					//Responsible
					jq('#thtina').val(data[i].thtina);
					jq('#thnaa').val(data[i].thnaa);
					jq('#thada1').val(data[i].thada1);
					jq('#thpna').val(data[i].thpna);
					jq('#thpsa').val(data[i].thpsa);
					jq('#thlka').val(data[i].thlka);
					if('DK'== data[i].thlka){
						jq('#thska').val('DA');
					}
					
					jq('#thdst').val(data[i].thdst);
					jq('#thdk').val(data[i].thdk);
					
					jq('#thlsd').val(data[i].thlsd);
					jq('#thlas2').val(data[i].thlas2);
					jq('#thalk').val(data[i].thalk);
					jq('#thtrmi').val(data[i].thtrmi);
					jq('#thskfd').val(data[i].thskfd);
					jq('#thcats').val(data[i].thcats);
					jq('#thtsd1').val(data[i].thtsd1);
					jq('#thtsb').val(data[i].thtsb);
					
					jq('#thddt').val(data[i].thddt);
					jq('#thdkr').val(data[i].thdkr);
					
					jq('#thgkd').val(data[i].thgkd);
					jq('#thgft1').val(data[i].thgft1);
					jq('#thgadk').val(data[i].thgadk);
					jq('#thgbl').val(data[i].thgbl);
					
					jq('#thgvk').val(data[i].thgvk);
					jq('#thgbgi').val(data[i].thgbgi);
					jq('#thdsk').val(data[i].thdsk);
					jq('#thgadk').val(data[i].thgadk);
					jq('#thgbl').val(data[i].thgbl);
				}
			});
	    });
	});
	
	
	//-------------------------------------------
	//Avgångs-Tullkontor AJAX [SKAT-NCTS Export]
	//-------------------------------------------
	//FETCH Tullkontor
	function searchAvgangTullkontorOwnWindow(){
		//init the tullkontor object in javascript (will be put into a map)
	  	var tullkontor = new Object();
	  	//fields
	  	tullkontor.tkkode = "";tullkontor.tktxtn = "";
	  	
		jq(function(){
			//this parameters must match the AJAX controller parameter names in Spring exactly...
			var KONTOR_TYPE_AVGANG = "avg"
			jq.getJSON('searchUtfartsTullkontor_Skat.do', {
				applicationUser : jq('#applicationUser').val(),
				tullkontorName : jq('#search_sveh_utfa').val(),
				tullkontorCode : jq('#search_sveh_utfa_Code').val(),
				kontorType: KONTOR_TYPE_AVGANG,
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].tkkode + '">' + data[i].tkkode + '&nbsp;&nbsp;' + data[i].tktxtn + '</option>';
					tullkontor = new Object();
					tullkontor.tkkode = data[i].tkkode;
					tullkontor.tktxtn = data[i].tktxtn;
					//put the object in map now with customerNumber as key
					map[tullkontor.tkkode] = tullkontor;
				}
				//now that we have our options, give them to our select
				jq('#tullkontorListAvgang').html(html);
			});
		})
		
	}
	//BestämmelseTullkontor list
	jq(function() { 
	    jq('#tullkontorListAvgang').change(function() {
	    	jq('#thcats').val(""); //ncts export
	    	
		//now populate (if applicable)
	    	var key = jq('#tullkontorListAvgang').val();
	    	jq('#thcats').val(key); //ncts export
	    	
	    });
	});
	
	//----------------------------------
	//Events Avgångstullkontor (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgUtfartstullKontor').click(function(){
    			jq("#search_sveh_utfa").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_utfa').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchAvgangTullkontorOwnWindow);
			}			
    		});
		jq('#search_sveh_utfa_Code').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchAvgangTullkontorOwnWindow);
			}			
    		});
	});

	
	//-------------------------------------------
	//Bestämmelses-Tullkontor AJAX [NCTS Export]
	//-------------------------------------------
	//FETCH Tullkontor
	function searchBestamTullkontorOwnWindow(){
		//init the tullkontor object in javascript (will be put into a map)
	  	var tullkontor = new Object();
	  	//fields
	  	tullkontor.tkkode = "";tullkontor.tktxtn = "";
	  	
		jq(function(){
			var KONTOR_TYPE_ANKOMST = "ank"
			//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('searchUtfartsTullkontor_Skat.do', {
				applicationUser : jq('#applicationUser').val(),
				tullkontorName : jq('#search_sveh_utfa_bestam').val(),
				tullkontorCode : jq('#search_sveh_utfa_Code_bestam').val(),
				kontorType : KONTOR_TYPE_ANKOMST,
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].tkkode + '">' + data[i].tkkode + '&nbsp;&nbsp;' + data[i].tktxtn + '</option>';
					tullkontor = new Object();
					tullkontor.tkkode = data[i].tkkode;
					tullkontor.tktxtn = data[i].tktxtn;
					//put the object in map now with customerNumber as key
					map[tullkontor.tkkode] = tullkontor;
				}
				//now that we have our options, give them to our select
				jq('#tullkontorListBestam').html(html);
			});
		})
		
	}
	//BestämmelseTullkontor list
	jq(function() { 
	    jq('#tullkontorListBestam').change(function() {
	    	jq('#thtsb').val(""); //ncts export
	    	
		//now populate (if applicable)
	    	var key = jq('#tullkontorListBestam').val();
	    	jq('#thtsb').val(key); //ncts export
	    	
	    });
	});
	
	
	//----------------------------------
	//Events TullkontorTransit (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgBestamstullKontor').click(function(){
    			jq("#search_sveh_utfa_bestam").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_utfa_bestam').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchBestamTullkontorOwnWindow);
			}			
    		});
		jq('#search_sveh_utfa_Code_bestam').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchBestamTullkontorOwnWindow);
			}			
    		});
	});
	


	//--------------------------------------------------------------------------
	//Transit-Tullkontor AJAX [NCTS Export] - Can be up to 8 transittullkontor
	//--------------------------------------------------------------------------
	var KONTOR_TYPE_TRANSIT = "trs";
	//FETCH Transittullkontor 01
	function searchTransitTullkontor01_OwnWindow(){
		//init the tullkontor object in javascript (will be put into a map)
	  	var tullkontor = new Object();
	  	//fields
	  	tullkontor.tkkode = "";tullkontor.tktxtn = "";
	  	
		jq(function(){
			//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('searchUtfartsTullkontor_Skat.do', {
				applicationUser : jq('#applicationUser').val(),
				tullkontorName : jq('#search_sveh_utfa_transit01').val(),
				tullkontorCode : jq('#search_sveh_utfa_Code_transit01').val(),
				kontorType : KONTOR_TYPE_TRANSIT,
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].tkkode + '">' + data[i].tkkode + '&nbsp;&nbsp;' + data[i].tktxtn + '</option>';
					tullkontor = new Object();
					tullkontor.tkkode = data[i].tkkode;
					tullkontor.tktxtn = data[i].tktxtn;
					//put the object in map now with customerNumber as key
					map[tullkontor.tkkode] = tullkontor;
				}
				//now that we have our options, give them to our select
				jq('#tullkontorListTransit01').html(html);
			});
		})
		
	}
	//Transittullkontor 01 list
	jq(function() { 
	    jq('#tullkontorListTransit01').change(function() {
	    	jq('#thtsd1').val(""); //ncts export
	    	
		//now populate (if applicable)
	    	var key = jq('#tullkontorListTransit01').val();
	    	jq('#thtsd1').val(key); //ncts export
	    	
	    });
	});
	
	//On Keypress (13)
	jq(function() { 
	    jq('#tullkontorListTransit01').keypress(function() {
		    	if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#thtsd1').val(""); //ncts export
				//now populate (if applicable)
			    	var key = jq('#tullkontorListTransit01').val();
			    	jq('#thtsd1').val(key); //ncts export
		    	}
	    });
	    
	});
	
	//----------------------------------
	//Events TullkontorTransit (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgTullkontorListTransit01').click(function(){
    			jq("#search_sveh_utfa_transit01").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_utfa_transit01').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor01_OwnWindow);
			}			
    		});
		jq('#search_sveh_utfa_Code_transit01').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor01_OwnWindow);
			}			
    		});
	});
	
	
	//--------------------------------------------------------------------------
	//Transit-Tullkontor AJAX [NCTS Export] - Can be up to 8 transittullkontor
	//--------------------------------------------------------------------------
	//FETCH Transittullkontor 02
	function searchTransitTullkontor02_OwnWindow(){
		//init the tullkontor object in javascript (will be put into a map)
	  	var tullkontor = new Object();
	  	//fields
	  	tullkontor.tkkode = "";tullkontor.tktxtn = "";
	  	
		jq(function(){
			//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('searchUtfartsTullkontor_Skat.do', {
				applicationUser : jq('#applicationUser').val(),
				tullkontorName : jq('#search_sveh_utfa_transit02').val(),
				tullkontorCode : jq('#search_sveh_utfa_Code_transit02').val(),
				kontorType : KONTOR_TYPE_TRANSIT,
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].tkkode + '">' + data[i].tkkode + '&nbsp;&nbsp;' + data[i].tktxtn + '</option>';
					tullkontor = new Object();
					tullkontor.tkkode = data[i].tkkode;
					tullkontor.tktxtn = data[i].tktxtn;
					//put the object in map now with customerNumber as key
					map[tullkontor.tkkode] = tullkontor;
				}
				//now that we have our options, give them to our select
				jq('#tullkontorListTransit02').html(html);
			});
		})
		
	}
	//Transittullkontor 02 list
	jq(function() { 
	    jq('#tullkontorListTransit02').change(function() {
	    	jq('#thtsd2').val(""); //ncts export
	    	
			//now populate (if applicable)
	    	var key = jq('#tullkontorListTransit02').val();
	    	jq('#thtsd2').val(key); //ncts export
	    	
	    });
	});
	
	//On Keypress (13)
	jq(function() { 
	    jq('#tullkontorListTransit02').keypress(function() {
		    	if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#thtsd2').val(""); //ncts export
				//now populate (if applicable)
			    	var key = jq('#tullkontorListTransit02').val();
			    	jq('#thtsd2').val(key); //ncts export
		    	}
	    });
	    
	});
	
	//----------------------------------
	//Events TullkontorTransit (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgTullkontorListTransit02').click(function(){
    			jq("#search_sveh_utfa_transit02").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_utfa_transit02').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor02_OwnWindow);
			}			
    		});
		jq('#search_sveh_utfa_Code_transit02').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor02_OwnWindow);
			}			
    		});
	});
	
	

	
	
	//--------------------------------------------------------------------------
	//Transit-Tullkontor AJAX [NCTS Export] - Can be up to 8 transittullkontor
	//--------------------------------------------------------------------------
	//FETCH Transittullkontor 03
	function searchTransitTullkontor03_OwnWindow(){
		//init the tullkontor object in javascript (will be put into a map)
	  	var tullkontor = new Object();
	  	//fields
	  	tullkontor.tkkode = "";tullkontor.tktxtn = "";
	  	
		jq(function(){
			//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('searchUtfartsTullkontor_Skat.do', {
				applicationUser : jq('#applicationUser').val(),
				tullkontorName : jq('#search_sveh_utfa_transit03').val(),
				tullkontorCode : jq('#search_sveh_utfa_Code_transit03').val(),
				kontorType : KONTOR_TYPE_TRANSIT,
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].tkkode + '">' + data[i].tkkode + '&nbsp;&nbsp;' + data[i].tktxtn + '</option>';
					tullkontor = new Object();
					tullkontor.tkkode = data[i].tkkode;
					tullkontor.tktxtn = data[i].tktxtn;
					//put the object in map now with customerNumber as key
					map[tullkontor.tkkode] = tullkontor;
				}
				//now that we have our options, give them to our select
				jq('#tullkontorListTransit03').html(html);
			});
		})
		
	}
	//Transittullkontor 03 list
	jq(function() { 
	    jq('#tullkontorListTransit03').change(function() {
	    	jq('#thtsd3').val(""); //ncts export
	    	
			//now populate (if applicable)
	    	var key = jq('#tullkontorListTransit03').val();
	    	jq('#thtsd3').val(key); //ncts export
	    	
	    });
	});
	
	//On Keypress (13)
	jq(function() { 
	    jq('#tullkontorListTransit03').keypress(function() {
		    	if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#thtsd3').val(""); //ncts export
				//now populate (if applicable)
			    	var key = jq('#tullkontorListTransit03').val();
			    	jq('#thtsd3').val(key); //ncts export
		    	}
	    });
	    
	});
	
	//----------------------------------
	//Events TullkontorTransit (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgTullkontorListTransit03').click(function(){
    			jq("#search_sveh_utfa_transit03").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_utfa_transit03').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor03_OwnWindow);
			}			
    		});
		jq('#search_sveh_utfa_Code_transit03').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor03_OwnWindow);
			}			
    		});
	});
	

	
	//--------------------------------------------------------------------------
	//Transit-Tullkontor AJAX [NCTS Export] - Can be up to 8 transittullkontor
	//--------------------------------------------------------------------------
	//FETCH Transittullkontor 04
	function searchTransitTullkontor04_OwnWindow(){
		//init the tullkontor object in javascript (will be put into a map)
	  	var tullkontor = new Object();
	  	//fields
	  	tullkontor.tkkode = "";tullkontor.tktxtn = "";
	  	
		jq(function(){
			//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('searchUtfartsTullkontor_Skat.do', {
				applicationUser : jq('#applicationUser').val(),
				tullkontorName : jq('#search_sveh_utfa_transit04').val(),
				tullkontorCode : jq('#search_sveh_utfa_Code_transit04').val(),
				kontorType : KONTOR_TYPE_TRANSIT,
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].tkkode + '">' + data[i].tkkode + '&nbsp;&nbsp;' + data[i].tktxtn + '</option>';
					tullkontor = new Object();
					tullkontor.tkkode = data[i].tkkode;
					tullkontor.tktxtn = data[i].tktxtn;
					//put the object in map now with customerNumber as key
					map[tullkontor.tkkode] = tullkontor;
				}
				//now that we have our options, give them to our select
				jq('#tullkontorListTransit04').html(html);
			});
		})
		
	}
	//Transittullkontor 04 list
	jq(function() { 
	    jq('#tullkontorListTransit04').change(function() {
	    	jq('#thtsd4').val(""); //ncts export
	    	
			//now populate (if applicable)
	    	var key = jq('#tullkontorListTransit04').val();
	    	jq('#thtsd4').val(key); //ncts export
	    	
	    });
	});
	
	
	//On Keypress (13)
	jq(function() { 
	    jq('#tullkontorListTransit04').keypress(function() {
		    	if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#thtsd4').val(""); //ncts export
				//now populate (if applicable)
			    	var key = jq('#tullkontorListTransit04').val();
			    	jq('#thtsd4').val(key); //ncts export
		    	}
	    });
	    
	});
	
	//----------------------------------
	//Events TullkontorTransit (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgTullkontorListTransit04').click(function(){
    			jq("#search_sveh_utfa_transit04").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_utfa_transit04').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor04_OwnWindow);
			}			
    		});
		jq('#search_sveh_utfa_Code_transit04').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor04_OwnWindow);
			}			
    		});
	});
	

	
	//--------------------------------------------------------------------------
	//Transit-Tullkontor AJAX [NCTS Export] - Can be up to 8 transittullkontor
	//--------------------------------------------------------------------------
	//FETCH Transittullkontor 05
	function searchTransitTullkontor05_OwnWindow(){
		//init the tullkontor object in javascript (will be put into a map)
	  	var tullkontor = new Object();
	  	//fields
	  	tullkontor.tkkode = "";tullkontor.tktxtn = "";
	  	
		jq(function(){
			//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('searchUtfartsTullkontor_Skat.do', {
				applicationUser : jq('#applicationUser').val(),
				tullkontorName : jq('#search_sveh_utfa_transit05').val(),
				tullkontorCode : jq('#search_sveh_utfa_Code_transit05').val(),
				kontorType : KONTOR_TYPE_TRANSIT,
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].tkkode + '">' + data[i].tkkode + '&nbsp;&nbsp;' + data[i].tktxtn + '</option>';
					tullkontor = new Object();
					tullkontor.tkkode = data[i].tkkode;
					tullkontor.tktxtn = data[i].tktxtn;
					//put the object in map now with customerNumber as key
					map[tullkontor.tkkode] = tullkontor;
				}
				//now that we have our options, give them to our select
				jq('#tullkontorListTransit05').html(html);
			});
		})
		
	}
	//Transittullkontor 05 list
	jq(function() { 
	    jq('#tullkontorListTransit05').change(function() {
	    	jq('#thtsd5').val(""); //ncts export
	    	
			//now populate (if applicable)
	    	var key = jq('#tullkontorListTransit05').val();
	    	jq('#thtsd5').val(key); //ncts export
	    	
	    });
	});
	
	
	//On Keypress (13)
	jq(function() { 
	    jq('#tullkontorListTransit05').keypress(function() {
		    	if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#thtsd5').val(""); //ncts export
				//now populate (if applicable)
			    	var key = jq('#tullkontorListTransit05').val();
			    	jq('#thtsd5').val(key); //ncts export
		    	}
	    });
	    
	});
	
	//----------------------------------
	//Events TullkontorTransit (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgTullkontorListTransit05').click(function(){
    			jq("#search_sveh_utfa_transit05").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_utfa_transit05').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor05_OwnWindow);
			}			
    		});
		jq('#search_sveh_utfa_Code_transit05').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor05_OwnWindow);
			}			
    		});
	});
	

	
	//--------------------------------------------------------------------------
	//Transit-Tullkontor AJAX [NCTS Export] - Can be up to 8 transittullkontor
	//--------------------------------------------------------------------------
	//FETCH Transittullkontor 06
	function searchTransitTullkontor06_OwnWindow(){
		//init the tullkontor object in javascript (will be put into a map)
	  	var tullkontor = new Object();
	  	//fields
	  	tullkontor.tkkode = "";tullkontor.tktxtn = "";
	  	
		jq(function(){
			//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('searchUtfartsTullkontor_Skat.do', {
				applicationUser : jq('#applicationUser').val(),
				tullkontorName : jq('#search_sveh_utfa_transit06').val(),
				tullkontorCode : jq('#search_sveh_utfa_Code_transit06').val(),
				kontorType : KONTOR_TYPE_TRANSIT,
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].tkkode + '">' + data[i].tkkode + '&nbsp;&nbsp;' + data[i].tktxtn + '</option>';
					tullkontor = new Object();
					tullkontor.tkkode = data[i].tkkode;
					tullkontor.tktxtn = data[i].tktxtn;
					//put the object in map now with customerNumber as key
					map[tullkontor.tkkode] = tullkontor;
				}
				//now that we have our options, give them to our select
				jq('#tullkontorListTransit06').html(html);
			});
		})
		
	}
	//Transittullkontor 06 list
	jq(function() { 
	    jq('#tullkontorListTransit06').change(function() {
	    	jq('#thtsd6').val(""); //ncts export
	    	
			//now populate (if applicable)
	    	var key = jq('#tullkontorListTransit06').val();
	    	jq('#thtsd6').val(key); //ncts export
	    	
	    });
	});
	
	
	//On Keypress (13)
	jq(function() { 
	    jq('#tullkontorListTransit06').keypress(function() {
		    	if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#thtsd6').val(""); //ncts export
				//now populate (if applicable)
			    	var key = jq('#tullkontorListTransit06').val();
			    	jq('#thtsd6').val(key); //ncts export
		    	}
	    });
	    
	});
	
	//----------------------------------
	//Events TullkontorTransit (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgTullkontorListTransit06').click(function(){
    			jq("#search_sveh_utfa_transit06").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_utfa_transit06').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor06_OwnWindow);
			}			
    		});
		jq('#search_sveh_utfa_Code_transit06').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor06_OwnWindow);
			}			
    		});
	});
	

	
	//--------------------------------------------------------------------------
	//Transit-Tullkontor AJAX [NCTS Export] - Can be up to 8 transittullkontor
	//--------------------------------------------------------------------------
	//FETCH Transittullkontor 07
	function searchTransitTullkontor07_OwnWindow(){
		//init the tullkontor object in javascript (will be put into a map)
	  	var tullkontor = new Object();
	  	//fields
	  	tullkontor.tkkode = "";tullkontor.tktxtn = "";
	  	
		jq(function(){
			//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('searchUtfartsTullkontor_Skat.do', {
				applicationUser : jq('#applicationUser').val(),
				tullkontorName : jq('#search_sveh_utfa_transit07').val(),
				tullkontorCode : jq('#search_sveh_utfa_Code_transit07').val(),
				kontorType : KONTOR_TYPE_TRANSIT,
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].tkkode + '">' + data[i].tkkode + '&nbsp;&nbsp;' + data[i].tktxtn + '</option>';
					tullkontor = new Object();
					tullkontor.tkkode = data[i].tkkode;
					tullkontor.tktxtn = data[i].tktxtn;
					//put the object in map now with customerNumber as key
					map[tullkontor.tkkode] = tullkontor;
				}
				//now that we have our options, give them to our select
				jq('#tullkontorListTransit07').html(html);
			});
		})
		
	}
	//Transittullkontor 07 list
	jq(function() { 
	    jq('#tullkontorListTransit07').change(function() {
	    	jq('#thtsd7').val(""); //ncts export
	    	
			//now populate (if applicable)
	    	var key = jq('#tullkontorListTransit07').val();
	    	jq('#thtsd7').val(key); //ncts export
	    	
	    });
	});
	
	
	//On Keypress (13)
	jq(function() { 
	    jq('#tullkontorListTransit07').keypress(function() {
		    	if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#thtsd7').val(""); //ncts export
				//now populate (if applicable)
			    	var key = jq('#tullkontorListTransit07').val();
			    	jq('#thtsd7').val(key); //ncts export
		    	}
	    });
	    
	});
	
	//----------------------------------
	//Events TullkontorTransit (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgTullkontorListTransit07').click(function(){
    			jq("#search_sveh_utfa_transit07").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_utfa_transit07').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor07_OwnWindow);
			}			
    		});
		jq('#search_sveh_utfa_Code_transit07').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor07_OwnWindow);
			}			
    		});
	});
	

	
	//--------------------------------------------------------------------------
	//Transit-Tullkontor AJAX [NCTS Export] - Can be up to 8 transittullkontor
	//--------------------------------------------------------------------------
	//FETCH Transittullkontor 08
	function searchTransitTullkontor08_OwnWindow(){
		//init the tullkontor object in javascript (will be put into a map)
	  	var tullkontor = new Object();
	  	//fields
	  	tullkontor.tkkode = "";tullkontor.tktxtn = "";
	  	
		jq(function(){
			//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('searchUtfartsTullkontor_Skat.do', {
				applicationUser : jq('#applicationUser').val(),
				tullkontorName : jq('#search_sveh_utfa_transit08').val(),
				tullkontorCode : jq('#search_sveh_utfa_Code_transit08').val(),
				kontorType : KONTOR_TYPE_TRANSIT,
				ajax : 'true'
			}, function(data) {
				var html = '<option selected value="">-Select-</option>';
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					html += '<option value="' + data[i].tkkode + '">' + data[i].tkkode + '&nbsp;&nbsp;' + data[i].tktxtn + '</option>';
					tullkontor = new Object();
					tullkontor.tkkode = data[i].tkkode;
					tullkontor.tktxtn = data[i].tktxtn;
					//put the object in map now with customerNumber as key
					map[tullkontor.tkkode] = tullkontor;
				}
				//now that we have our options, give them to our select
				jq('#tullkontorListTransit08').html(html);
			});
		})
		
	}
	//Transittullkontor 08 list
	jq(function() { 
	    jq('#tullkontorListTransit08').change(function() {
	    	jq('#thtsd8').val(""); //ncts export
	    	
			//now populate (if applicable)
	    	var key = jq('#tullkontorListTransit08').val();
	    	jq('#thtsd8').val(key); //ncts export
	    	
	    });
	});
	
	//On Keypress (13)
	jq(function() { 
	    jq('#tullkontorListTransit08').keypress(function() {
		    	if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#thtsd8').val(""); //ncts export
				//now populate (if applicable)
			    	var key = jq('#tullkontorListTransit08').val();
			    	jq('#thtsd8').val(key); //ncts export
		    	}
	    });
	    
	});
	
	//----------------------------------
	//Events TullkontorTransit (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgTullkontorListTransit08').click(function(){
    			jq("#search_sveh_utfa_transit08").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_sveh_utfa_transit08').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor08_OwnWindow);
			}			
    		});
		jq('#search_sveh_utfa_Code_transit08').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransitTullkontor08_OwnWindow);
			}			
    		});
	});
	

	  //-------------------------------------------
	  //START Model dialog ADMIN: "Update status"
	  //-------------------------------------------
	  //Initialize <div> here
	  jq(function() { 
		  jq("#dialogUpdateStatus").dialog({
			  autoOpen: false,
			  maxWidth:500,
	          maxHeight: 400,
	          width: 280,
	          height: 220,
			  modal: true
		  });
	  });
	  //Present dialog box onClick (href in parent JSP)
	  jq(function() {
		  jq("#updateStatusLink").click(function() {
			  //setters (add more if needed)
			  jq('#dialogUpdateStatus').dialog( "option", "title", "Update Status" );
			  
			  //deal with buttons for this modal window
			  jq('#dialogUpdateStatus').dialog({
				 buttons: [ 
		            {
					 id: "dialogSaveTU",	
					 text: "Ok",
					 click: function(){
						 		jq('#updateStatusForm').submit();
					 		}
				 	 },
		 	 		{
				 	 id: "dialogCancelTU",
				 	 text: "Cancel", 
					 click: function(){
						 		//back to initial state of form elements on modal dialog
						 		jq("#dialogSaveSU").button("option", "disabled", true);
						 		jq("#selectedStatus").val("");
						 		jq( this ).dialog( "close" ); 
					 		} 
		 	 		 } ] 
			  });
			  //init values
			  jq("#dialogSaveSU").button("option", "disabled", true);
			  //open now
			  jq('#dialogUpdateStatus').dialog('open');
		  });
	  });

	  //----------------------------------
	  //START Model dialog CANCELLATION
	  //----------------------------------
	  //Initialize <div> here
	  jq(function() { 
		  jq("#dialogCancellation").dialog({
			  autoOpen: false,
			  maxWidth:600,
	          maxHeight: 300,
	          width: 600,
	          height: 250,
			  modal: true
		  });
	  });
	  //Present dialog box onClick (href in parent JSP)
	  jq(function() {
		  jq("#cancellationButton").click(function() {
			  //setters (add more if needed)
			  jq('#dialogCancellation').dialog( "option", "title", "Annullering SKAT" );
			  
			  //deal with buttons for this modal window
			  jq('#dialogCancellation').dialog({
				 buttons: [ 
		            {
					 id: "dialogSaveTU",	
					 text: "Ok",
					 click: function(){
						 		jq('#cancellationForm').submit();
					 		}
				 	 },
		 	 		{
				 	 id: "dialogCancelTU",
				 	 text: "Cancel", 
					 click: function(){
						 		//back to initial state of form elements on modal dialog
						 		//jq("#dialogSaveSU").button("option", "disabled", true);
						 		//jq("#selectedStatus").val("");
						 		jq( this ).dialog( "close" ); 
					 		} 
		 	 		 } ] 
			  });
			  //init values
			  jq("#dialogSaveTU").button("option", "disabled", true);
			  //open now
			  jq('#dialogCancellation').dialog('open');
		  });
	  });
	  //Events for the dialog fields (some kind of "implicit validation" since all fields are mandatory)
	  jq(function() {
		  jq("#tkft1").blur(function() {
			  if(jq("#tkft1").val()!=''){
				  if(jq("#tksk").val()!=''){
					  jq("#dialogSaveTU").button("option", "disabled", false);
				  }else{
					  jq("#dialogSaveTU").button("option", "disabled", true);
				  }
			  }else{
				  jq("#dialogSaveTU").button("option", "disabled", true);
			  }
		  });
		  jq("#tksk").blur(function() {
			  if(jq("#tkft1").val()!=''){
				  if(jq("#tksk").val()!=''){
					  jq("#dialogSaveTU").button("option", "disabled", false);
				  }else{
					  jq("#dialogSaveTU").button("option", "disabled", true);
				  }
			  }else{
				  jq("#dialogSaveTU").button("option", "disabled", true);
			  }
			  
		  });
	  });
	  
	  
	//------------------------------------------------------------
  	//FETCH CUSTOMER from SENDER [AVSÄNDARE - SIKKERHED] html area
  	//------------------------------------------------------------
	function searchAfsenderSikkerhedOwnWindow() {
			jq(function() {
				jq.getJSON('searchCustomer_Skat.do', {
					applicationUser : jq('#applicationUser').val(),
					customerName : jq('#search_thnass').val(),
					customerNumber : jq('#search_thknss').val(),
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
					jq('#afsenderSikkerhedList').html(html);
				});
			});
		}
		//onChange sender list
		jq(function() { 
		    jq('#afsenderSikkerhedList').change(function() {
		      //init fields
			  jq('#thnass').val("");
			  jq('#thtinss').val("");
			  jq('#thadss1').val("");
			  jq('#thpnss').val("");
			  jq('#thpsss').val("");
			  jq('#thlkss').val("");
			  jq('#thskss').val("");
			  
			  //now populate (if applicable)
			  var key = jq('#afsenderSikkerhedList').val();
			  jq('#thknss').val(key);
			  customer = map[key];
			  jq('#thnass').val(customer.knavn);
			  jq('#thtinss').val(customer.eori);
			  jq('#thadss1').val(customer.adr1);
			  jq('#thpnss').val(customer.postnr);
			  jq('#thpsss').val(customer.adr3);
			  jq('#thlkss').val(customer.syland);
			  jq('#thskss').val("");
		    });
		});
		
		//onClick for Sender dialog
		jq(function() { 
		    jq('#searchCustomer30CloseCancel').click(function() {
		      //rescue the original fields
		      jq('#thknss').val(jq("#orig_thknss").val());	
			  jq('#thnass').val(jq("#orig_thnass").val());
			  jq('#thtinss').val(jq("#orig_thtinss").val());
			  jq('#thadss1').val(jq("#orig_thadss1").val());
			  jq('#thpnss').val(jq("#orig_thpnss").val());
			  jq('#thpsss').val(jq("#orig_thpsss").val());
			  jq('#thlkss').val(jq("#orig_thlkss").val());
			  jq('#thskss').val(jq("#orig_thskss").val());
		    });
		});
		
		//----------------------------------
		//Events Sender (SEARCH window)
		//----------------------------------
		//img click
		jq(function() {	    
			jq('#imgAfsenderSikkerhedSearch').click(function(){
	    			jq("#search_thknss").focus();
	    		});
		});
		
		jq(function() {	    
			jq('#search_thknss').keypress(function(e){
				if(e.which == 13) {
					e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
					jq(searchAfsenderSikkerhedOwnWindow);
				}			
	    		});
			jq('#search_thnass').keypress(function(e){
				if(e.which == 13) {
					e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
					jq(searchAfsenderSikkerhedOwnWindow);
				}			
	    		});
		});
		//--------------------------------------------------------------------------------------
		//Extra behavior for Customer number ( without using (choose from list) extra roundtrip)
		//--------------------------------------------------------------------------------------
		jq(function() { 
		    jq('#thknss').blur(function() {
		    	if(jq('#thknss').val() != ''){
		    		jq.getJSON('searchCustomer_Skat.do', {
					applicationUser : jq('#applicationUser').val(),
					customerName : "",
					customerNumber : jq('#thknss').val(),
					ajax : 'true'
		    		}, function(data) {
					//alert("Hello");
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						//html += '<option value="' + data[i].kundnr + '">' + data[i].knavn + '</option>';
						customer = new Object();
						customer.kundnr = data[i].kundnr;
						customer.knavn = data[i].knavn;
						customer.eori = data[i].eori;
						customer.adr1 = data[i].adr1;
						customer.adr2 = data[i].adr2;
						customer.adr3 = data[i].adr3;
						customer.postnr = data[i].sypoge;//data[i].postnr; DK=sypoge
						if("" == customer.postnr){
							customer.postnr = data[i].postnr;
						}
						customer.kpers = data[i].kpers;
						customer.tlf = data[i].tlf;
						customer.syland = data[i].syland;
					  	//put the object in map now with customerNumber as key
						map[customer.kundnr] = customer;
					}
					if(len > 0){
						jq('#thknss').val(customer.kundnr);
						jq('#thtinss').val(customer.eori);
						jq('#thnass').val(customer.knavn);
						jq('#thadss1').val(customer.adr1);
						jq('#thpnss').val(customer.postnr);
						jq('#thpsss').val(customer.adr3);
						jq('#thlkss').val(customer.syland);
					}else{
						//init fields
						jq('#thtinss').val("");
						jq('#thnass').val("");
						jq('#thadss1').val("");
						jq('#thpnss').val("");
						jq('#thpsss').val("");
						jq('#thlkss').val("");
					}
					});
		    	}
			});
		    
		});
		
	//------------------------------------------------------------
  	//FETCH CUSTOMER from RECEIVER [MODTAGER - SIKKERHED] html area
  	//------------------------------------------------------------
	function searchModtdagerSikkerhedOwnWindow() {
			jq(function() {
				
				jq.getJSON('searchCustomer_Skat.do', {
					applicationUser : jq('#applicationUser').val(),
					customerName : jq('#search_thnaks').val(),
					customerNumber : jq('#search_thknks').val(),
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
					jq('#modtagerSikkerhedList').html(html);
					});
				
			});
		}
		//onChange sender list
		jq(function() { 
		    jq('#modtagerSikkerhedList').change(function() {
		      //init fields
			  jq('#thnaks').val("");
			  jq('#thtinks').val("");
			  jq('#thadks1').val("");
			  jq('#thpnks').val("");
			  jq('#thpsks').val("");
			  jq('#thlkks').val("");
			  jq('#thskks').val("");
			  
			  //now populate (if applicable)
			  var key = jq('#modtagerSikkerhedList').val();
			  jq('#thknks').val(key);
			  customer = map[key];
			  jq('#thnaks').val(customer.knavn);
			  jq('#thtinks').val(customer.eori);
			  jq('#thadks1').val(customer.adr1);
			  jq('#thpnks').val(customer.postnr);
			  jq('#thpsks').val(customer.adr3);
			  jq('#thlkks').val(customer.syland);
			  jq('#thskks').val("");
		    });
		});
		
		//onClick for Sender dialog
		jq(function() { 
		    jq('#searchCustomer40CloseCancel').click(function() {
		      //rescue the original fields
		      jq('#thknks').val(jq("#orig_thknks").val());	
			  jq('#thnaks').val(jq("#orig_thnaks").val());
			  jq('#thtinks').val(jq("#orig_thtinks").val());
			  jq('#thadks1').val(jq("#orig_thadks1").val());
			  jq('#thpnks').val(jq("#orig_thpnks").val());
			  jq('#thpsks').val(jq("#orig_thpsks").val());
			  jq('#thlkks').val(jq("#orig_thlkks").val());
			  jq('#thskks').val(jq("#orig_thskks").val());
		    });
		});
		
		//----------------------------------
		//Events Sender (SEARCH window)
		//----------------------------------
		//img click
		jq(function() {	    
			jq('#imgModtagerSikkerhedSearch').click(function(){
	    			jq("#search_thknks").focus();
	    		});
		});
		
		jq(function() {	    
			jq('#search_thknks').keypress(function(e){
				if(e.which == 13) {
					e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
					jq(searchModtdagerSikkerhedOwnWindow);
				}			
	    		});
			jq('#search_thnaks').keypress(function(e){
				if(e.which == 13) {
					e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
					jq(searchModtdagerSikkerhedOwnWindow);
				}			
	    		});
		});
		//--------------------------------------------------------------------------------------
		//Extra behavior for Customer number ( without using (choose from list) extra roundtrip)
		//--------------------------------------------------------------------------------------
		jq(function() { 
		    jq('#thknks').blur(function() {
		    	if(jq('#thknks').val() != ''){
		    		jq.getJSON('searchCustomer_Skat.do', {
					applicationUser : jq('#applicationUser').val(),
					customerName : "",
					customerNumber : jq('#thknks').val(),
					ajax : 'true'
		    		}, function(data) {
					//alert("Hello");
					var len = data.length;
					for ( var i = 0; i < len; i++) {
						//html += '<option value="' + data[i].kundnr + '">' + data[i].knavn + '</option>';
						customer = new Object();
						customer.kundnr = data[i].kundnr;
						customer.knavn = data[i].knavn;
						customer.eori = data[i].eori;
						customer.adr1 = data[i].adr1;
						customer.adr2 = data[i].adr2;
						customer.adr3 = data[i].adr3;
						customer.postnr = data[i].sypoge;//data[i].postnr; DK=sypoge
						if("" == customer.postnr){
							customer.postnr = data[i].postnr;
						}
						customer.kpers = data[i].kpers;
						customer.tlf = data[i].tlf;
						customer.syland = data[i].syland;
					  	//put the object in map now with customerNumber as key
						map[customer.kundnr] = customer;
					}
					if(len > 0){
						jq('#thknks').val(customer.kundnr);
						jq('#thtinks').val(customer.eori);
						jq('#thnaks').val(customer.knavn);
						jq('#thadks1').val(customer.adr1);
						jq('#thpnks').val(customer.postnr);
						jq('#thpsks').val(customer.adr3);
						jq('#thlkks').val(customer.syland);
					}else{
						//init fields
						jq('#thtinks').val("");
						jq('#thnaks').val("");
						jq('#thadks1').val("");
						jq('#thpnks').val("");
						jq('#thpsks').val("");
						jq('#thlkks').val("");
					}
		    		});
		    	}
			});
		});

		//-----------------------------------------------------------------------
	  	//FETCH CUSTOMER from CARRIER-TRADER [TRANSPORTØR - SIKKERHED] html area
	  	//-----------------------------------------------------------------------
		function searchTransportorSikkerhedOwnWindow() {
				jq(function() {
					jq.getJSON('searchCustomer_Skat.do', {
						applicationUser : jq('#applicationUser').val(),
						customerName : jq('#search_thnat').val(),
						customerNumber : jq('#search_thknt').val(),
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
						jq('#transportorSikkerhedList').html(html);
					});
				});
			}
			//onChange sender list
			jq(function() { 
			    jq('#transportorSikkerhedList').change(function() {
			      //init fields
				  jq('#thnat').val("");
				  jq('#thtint').val("");
				  jq('#thadt1').val("");
				  jq('#thpnt').val("");
				  jq('#thpst').val("");
				  jq('#thlkt').val("");
				  jq('#thskt').val("");
				  
				  //now populate (if applicable)
				  var key = jq('#transportorSikkerhedList').val();
				  jq('#thknt').val(key);
				  customer = map[key];
				  jq('#thnat').val(customer.knavn);
				  jq('#thtint').val(customer.eori);
				  jq('#thadt1').val(customer.adr1);
				  jq('#thpnt').val(customer.postnr);
				  jq('#thpst').val(customer.adr3);
				  jq('#thlkt').val(customer.syland);
				  jq('#thskt').val("");
			    });
			});
			
			//onClick for Sender dialog
			jq(function() { 
			    jq('#searchCustomer50CloseCancel').click(function() {
			      //rescue the original fields
			      jq('#thknt').val(jq("#orig_thknt").val());	
				  jq('#thnat').val(jq("#orig_thnat").val());
				  jq('#thtint').val(jq("#orig_thtint").val());
				  jq('#thadt1').val(jq("#orig_thadt1").val());
				  jq('#thpnt').val(jq("#orig_thpnt").val());
				  jq('#thpst').val(jq("#orig_thpst").val());
				  jq('#thlkt').val(jq("#orig_thlkt").val());
				  jq('#thskt').val(jq("#orig_thskt").val());
			    });
			});
			
			//----------------------------------
			//Events Sender (SEARCH window)
			//----------------------------------
			//img click
			jq(function() {	    
				jq('#imgTransportorSikkerhedSearch').click(function(){
		    			jq("#search_thknt").focus();
		    		});
			});
			
			jq(function() {	    
				jq('#search_thknt').keypress(function(e){
					if(e.which == 13) {
						e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
						jq(searchTransportorSikkerhedOwnWindow);
					}			
		    		});
				jq('#search_thnat').keypress(function(e){
					if(e.which == 13) {
						e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
						jq(searchTransportorSikkerhedOwnWindow);
					}			
		    		});
			});
			//--------------------------------------------------------------------------------------
			//Extra behavior for Customer number ( without using (choose from list) extra roundtrip)
			//--------------------------------------------------------------------------------------
			jq(function() { 
			    jq('#thknt').blur(function() {
			    	if(jq('#thknt').val() != ''){
			    		jq.getJSON('searchCustomer_Skat.do', {
						applicationUser : jq('#applicationUser').val(),
						customerName : "",
						customerNumber : jq('#thknt').val(),
						ajax : 'true'
			    		}, function(data) {
						//alert("Hello");
						var len = data.length;
						for ( var i = 0; i < len; i++) {
							//html += '<option value="' + data[i].kundnr + '">' + data[i].knavn + '</option>';
							customer = new Object();
							customer.kundnr = data[i].kundnr;
							customer.knavn = data[i].knavn;
							customer.eori = data[i].eori;
							customer.adr1 = data[i].adr1;
							customer.adr2 = data[i].adr2;
							customer.adr3 = data[i].adr3;
							customer.postnr = data[i].sypoge;//data[i].postnr; DK=sypoge
							if("" == customer.postnr){
								customer.postnr = data[i].postnr;
							}
							customer.kpers = data[i].kpers;
							customer.tlf = data[i].tlf;
							customer.syland = data[i].syland;
						  	//put the object in map now with customerNumber as key
							map[customer.kundnr] = customer;
						}
						if(len > 0){
							jq('#thknt').val(customer.kundnr);
							jq('#thtint').val(customer.eori);
							jq('#thnat').val(customer.knavn);
							jq('#thadt1').val(customer.adr1);
							jq('#thpnt').val(customer.postnr);
							jq('#thpst').val(customer.adr3);
							jq('#thlkt').val(customer.syland);
						}else{
							//init fields
							jq('#thtint').val("");
							jq('#thnat').val("");
							jq('#thadt1').val("");
							jq('#thpnt').val("");
							jq('#thpst').val("");
							jq('#thlkt').val("");
						}
			    		});
			    	}	
				});
			});

	
	//--------------------------------------------------------
	//VALIDATION before submit
	//Some fields must be validated upon "change" on the spot
	//E.g. Guarantee 
	//--------------------------------------------------------
	/*
	//Guarantee nr and code validation
	jq(function() { 
	    jq('#thgft1').blur(function() {
	    		var original_guaranteeNumber = jq('#thgft1').val();
	    		var original_guaranteeCode = jq('#thgadk').val();
	    		
	    		//alert('Hej');
	    		//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('validateGuaranteeNrAndCode_SkatNctsExport.do', {
				applicationUser : jq('#applicationUser').val(),
				guaranteeNumber : jq('#thgft1').val(),
				guaranteeCode : jq('#thgadk').val(),
				ajax : 'true'
			}, 
			function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					var errMsg = data[i].errMsg;
					alert(errMsg);
					/*
					var errMsg = data[i].errMsg;
					if(errMsg!=null && !"".equals(errMsg)){
						alert('NOK');
						jq('#thgft1').val(errMsg);
						jq('#thgadk').val(original_guaranteeCode);
					}else{
						alert('No errors');
						jq('#thgft1').val(data[i].thgft1);
						jq('#thgadk').val(data[i].thgadk);
					}
					
				}
				
			});
		
	    });
	    
	});
	*/
	
  	//---------------------------
	//Tullkontor list - OBSOLETE
	//---------------------------
	//onChange
	/*
	jq(function() { 
	    jq('#tullkontorList').change(function() {
		    	jq('#thcats').val(""); //ncts export
		    	//now populate (if applicable)
		    	var key = jq('#tullkontorList').val();
		    	jq('#thcats').val(key); //ncts export
	    });
	});
	//On Keypress (13)
	jq(function() { 
	    jq('#tullkontorList').keypress(function() {
		    	if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
			    	jq('#thcats').val(""); //ncts export
				//now populate (if applicable)
			    	var key = jq('#tullkontorList').val();
			    	jq('#thcats').val(key); //ncts export
		    	}
	    });	    
	});
	 */
	
	
	
	
	
	
	

