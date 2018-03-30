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
      jq('#alinkInvoices').click(function() { 
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
  	  
  	  //START get totals from Fakt.tab
  	  jq('#getFakturaListSumButton').click(function() { 
  		  getFakturaSum();
	  });
  	  /* TODO in JSP (as in export ...)
  	  jq('#getFakturaListSum2Button').click(function() { 
  		  getFakturaSum();
	  });*/
  	  function getFakturaSum(){
  		if(jq('#fakturaListTotSum').val()!='' && jq('#fakturaListTotValidCurrency').val()!='' ){  
	  		jq('#dkih_222').val(jq('#fakturaListTotSum').val());
	  		jq('#dkih_221').val(jq('#fakturaListTotValidCurrency').val());	
	  		//jq('#KURS???').val(jq('#finansOpplysningarTotKurs').val());
	  		
	  		//for backwards compatibility meaning: ref till fakturalista
  			jq('#dkih_28b').val("FLERE FAKTURAER");
  			
  		} 
  	  }
  	  //END get totals from Fakt.tab
  	  
  	  //----------------
  	  //CUSTOMER search
  	  //----------------
  	  //SENDER
  	  jq('#dkih_02bIdLink').click(function() {
  		  jq('#dkih_02bIdLink').attr('target','_blank');
  		  window.open('skat_childwindow_customer.do?action=doFind&sonavn=' + jq('#dkih_02b').val() + '&ctype=dkih_02b', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
  	  });
  	  jq('#dkih_02bIdLink').keypress(function(e){ //extra feature for the end user
  		  if(e.which == 13) {
  			  jq('#dkih_02bIdLink').click();
  		  }
  	  });
  	  //MODTAGER
  	  jq('#dkih_08bIdLink').click(function() {
    	jq('#dkih_08bIdLink').attr('target','_blank');
    	window.open('skat_childwindow_customer.do?action=doFind&sonavn=' + jq('#dkih_08b').val() + '&ctype=dkih_08b', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
  	  });
  	  jq('#dkih_08bIdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#dkih_08bIdLink').click();
		}
  	  });
  	  //CARRIER
  	  jq('#dkih_trnaIdLink').click(function() {
    	jq('#dkih_trnaIdLink').attr('target','_blank');
    	window.open('skat_childwindow_customer.do?action=doFind&sonavn=' + jq('#dkih_trna').val() + '&ctype=dkih_trna', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
  	  });
  	  jq('#dkih_trnaIdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#dkih_trnaIdLink').click();
		}
  	  });
  	  //UNDERETTES
  	  jq('#dkih_ninaIdLink').click(function() {
    	jq('#dkih_ninaIdLink').attr('target','_blank');
    	window.open('skat_childwindow_customer.do?action=doFind&sonavn=' + jq('#dkih_nina').val() + '&ctype=dkih_nina', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
  	  });
  	  jq('#dkih_ninaIdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#dkih_ninaIdLink').click();
		}
  	  });
  	  
  	  //Eksped.sted
  	  jq('#dkih_aIdLink').click(function() {
    	jq('#dkih_aIdLink').attr('target','_blank');
    	window.open('skatimport_edit_childwindow_generalcodes.do?action=doInit&type=006&ctype=dkih_a', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
  	  });
  	  jq('#dkih_aIdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#dkih_aIdLink').click();
		}
  	  });
  	  //Valuta
  	  jq('#dkih_221IdLink').click(function() {
    	jq('#dkih_221IdLink').attr('target','_blank');
    	window.open('skatimport_edit_childwindow_generalcodes.do?action=doInit&type=020&ctype=dkih_221', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
  	  });
  	  jq('#dkih_221IdLink').keypress(function(e){ //extra feature for the end user
		if(e.which == 13) {
			jq('#dkih_221IdLink').click();
		}
  	  });
  	
    });  
    
    //ChildWindow Country Codes
    function triggerChildWindowCountryCodes(record){
    	var idLink = record.id;
    	var id = idLink.replace("IdLink", "");
    	jq(idLink).attr('target','_blank');
    	window.open('skatimport_edit_childwindow_generalcodes.do?action=doInit&type=008&ctype=' + id , "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    }
  	
    
    
  	jq(document).ready(function(){
  	    jq(this).scrollTop(0); //needed for Chrome (bug)
  	});
  	
  	
  	jq(function() {
  		jq('#dkih_222').calculator({ showOn: 'button',  
  			buttonImageOnly: true, buttonImage: 'resources/images/calculator.png', decimalChar: ','});
  	});

  	jq(function() {
  	  jq("#dkih_dtm1").datetimepicker({ 
  		  dateFormat: 'yymmdd',
  		  controlType: 'select',
  		  timeFormat: 'HHmm'  
	  });
  	  //no space characters allowed...
	  jq('#dkih_dtm1').change(function() {
		  var val = jq("#dkih_dtm1").val();
		  jq("#dkih_dtm1").val(val.replace(' ', ''));
	  });
	  
	  
	  jq("#dkih_dtm2").datetimepicker({ 
		  dateFormat: 'yymmdd',
		  controlType: 'select',
		  timeFormat: 'HHmm' 
	  });
	  //no space characters allowed...
	  jq('#dkih_dtm2').change(function() {
		var val = jq("#dkih_dtm2").val();
		jq("#dkih_dtm2").val(val.replace(' ', ''));
		
	  });
	
	  
	  jq("#dkih_28c").datepicker({ 
		  dateFormat: 'yymmdd'  
	  });
	  
  	  jq("#dkih_t05b").datepicker({ 
		  dateFormat: 'yymmdd'  
	  });
  	  jq("#dkih_t06b").datepicker({ 
		  dateFormat: 'yymmdd'  
	  });
    });
  	
  	//onChange avd list
	jq(function() { 
	    jq('#avd').change(function() {
	    		
			jq.getJSON('getSpecificTopicOmbud_SkatImport.do', {
				applicationUser : jq('#applicationUser').val(),
				avd : jq('#avd').val(),
				ajax : 'true'
			}, function(data) {
				//alert("Hello");
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					jq('#dkih_14a').val(data[i].dkia_14a);
					jq('#dkih_14b').val(data[i].dkia_14b);
				}
			});
	    });
	});
  	  
	jq(function() { 
	    jq('#dkih_ajou').change(function() {
	    	var val = jq("#dkih_ajou").val();
			if(val=='8'){
				jq( "#dkih_bega" ).prop( "disabled", false );
			}else{
				jq( "#dkih_bega" ).prop( "disabled", true );
			}
	    });
	});
	/*
  	//onChange sign list
	jq(function() { 
	    jq('#sign').change(function() {
	    		
	    	jq.getJSON('getSignatureName_TdsImport.do', {
				applicationUser : jq('#applicationUser').val(),
				avd : jq('#avd').val(),
				sign : jq('#sign').val(),
				ajax : 'true'
			}, function(data) {
				//alert("Hello");
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					jq('#svih_omha').val(data[i].svth_namn);
				}
			});
	    });
	});
  	*/
	
	
	//-----------------------
  	//INIT CUSTOMER Object
  	//-----------------------
	var map = {};
  	//init the customer object in javascript (will be put into a map)
  	var customer = new Object();
  	//fields
  	customer.kundnr = "";customer.knavn = "";customer.eori = "";customer.adr1 = "";
  	customer.adr2 = "";customer.adr3 = "";customer.postnr = "";customer.syland = "";
  	customer.kpers = "";customer.tlf = "";
  	//---------------------------------------------------------
  	//FETCH CUSTOMER from SENDER  html area
  	//---------------------------------------------------------
	function searchSenderOwnWindow() {
		jq(function() {
			jq.getJSON('searchCustomer_Skat.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : jq('#search_dkih_02b').val(),
				customerNumber : jq('#search_dkih_avkn').val(),
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
		  jq('#dkih_02b').val("");
		  jq('#dkih_02c').val("");
		  jq('#dkih_02d').val("");
		  jq('#dkih_02e').val("");
		  jq('#dkih_02f').val("");
		  //now populate (if applicable)
		  var key = jq('#senderList').val();
		  jq('#dkih_avkn').val(key);
		  customer = map[key];
		  jq('#dkih_02b').val(customer.knavn);
		  jq('#dkih_02c').val(customer.adr1);
		  jq('#dkih_02d').val(customer.postnr);
		  jq('#dkih_02e').val(customer.adr3);
		  jq('#dkih_02f').val(customer.syland);
	    });
	});
	
	//onClick for Sender dialog
	jq(function() { 
	    jq('#searchCustomerCloseCancel').click(function() {
	      //rescue the original fields
	      jq('#dkih_avkn').val(jq("#orig_dkih_avkn").val());	
		  jq('#dkih_02b').val(jq("#orig_dkih_02b").val());
		  jq('#dkih_02c').val(jq("#orig_dkih_02c").val());
		  jq('#dkih_02d').val(jq("#orig_dkih_02d").val());
		  jq('#dkih_02e').val(jq("#orig_dkih_02e").val());
		  jq('#dkih_02f').val(jq("#orig_dkih_02f").val());
	    });
	});
	//----------------------------------
	//Events Sender (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgCustomerSearch').click(function(){
    			jq("#search_dkih_avkn").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_dkih_avkn').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchSenderOwnWindow);
			}			
    		});
		jq('#search_dkih_02b').keypress(function(e){
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
	    jq('#dkih_avkn').blur(function() {
	    		var avknValue = jq('#dkih_avkn').val();
	    		if(avknValue!=null && avknValue!=""){
		    		jq.getJSON('searchCustomer_Skat.do', {
					applicationUser : jq('#applicationUser').val(),
					customerName : "",
					customerNumber : jq('#dkih_avkn').val(),
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
						customer.kpers = data[i].kpers;
						customer.tlf = data[i].tlf;
						customer.syland = data[i].syland;
					  	//put the object in map now with customerNumber as key
						map[customer.kundnr] = customer;
					}
					if(len > 0){
						jq('#dkih_avkn').val(customer.kundnr);
						jq('#dkih_02a').val(customer.eori);
						jq('#dkih_02b').val(customer.knavn);
						jq('#dkih_02c').val(customer.adr1);
						jq('#dkih_02d').val(customer.postnr);
						jq('#dkih_02e').val(customer.adr3);
						jq('#dkih_02f').val(customer.syland);
					}else{
						//init fields
						jq('#dkih_02a').val("");
						jq('#dkih_02b').val("");
						jq('#dkih_02c').val("");
						jq('#dkih_02d').val("");
						jq('#dkih_02e').val("");
						jq('#dkih_02f').val("");
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
				customerName : jq('#search_dkih_08b').val(),
				customerNumber : jq('#search_dkih_mokn').val(),
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
		  jq('#dkih_08a').val("");
		  jq('#dkih_08b').val("");
		  jq('#dkih_08c').val("");
		  jq('#dkih_08d').val("");
		  jq('#dkih_08e').val("");
		  jq('#dkih_08f').val("");
		  //now populate (if applicable)
		  var key = jq('#receiverList').val();
		  jq('#dkih_mokn').val(key);
		  customer = map[key];
		  jq('#dkih_08a').val(customer.eori);
		  jq('#dkih_08b').val(customer.knavn);
		  jq('#dkih_08c').val(customer.adr1);
		  jq('#dkih_08d').val(customer.postnr);
		  jq('#dkih_08e').val(customer.adr3);
		  jq('#dkih_08f').val(customer.syland);
		});
	});
	//onClick for Receiver(Mottagare) dialog
	jq(function() { 
	    jq('#searchCustomer10CloseCancel').click(function() {
	      //rescue the original fields
		  jq('#dkih_avkn').val(jq("#orig_dkih_avkn").val());	
		  jq('#dkih_08a').val(jq("#orig_dkih_08a").val());
		  jq('#dkih_08b').val(jq("#orig_dkih_08b").val());
		  jq('#dkih_08c').val(jq("#orig_dkih_08c").val());
		  jq('#dkih_08d').val(jq("#orig_dkih_08d").val());
		  jq('#dkih_08e').val(jq("#orig_dkih_08e").val());
		  jq('#dkih_08f').val(jq("#orig_dkih_08f").val());		  
	    });
	});
	
	//----------------------------------
	//Events Receiver (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgReceiverSearch').click(function(){
    			jq("#search_dkih_mokn").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_dkih_mokn').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchReceiverOwnWindow);
			}			
    		});
		jq('#search_dkih_mona').keypress(function(e){
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
	    jq('#dkih_mokn').blur(function() {
	    		var moknValue = jq('#dkih_mokn').val();
	    		if(moknValue!=null && moknValue!=""){
	    	    		jq.getJSON('searchCustomer_Skat.do', {
					applicationUser : jq('#applicationUser').val(),
					customerName : "",
					customerNumber : jq('#dkih_mokn').val(),
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
						customer.kpers = data[i].kpers;
						customer.tlf = data[i].tlf;
						customer.syland = data[i].syland;
					  	//put the object in map now with customerNumber as key
						map[customer.kundnr] = customer;
					}
					if(len > 0){
						jq('#dkih_mokn').val(customer.kundnr);
						jq('#dkih_08a').val(customer.eori);
						jq('#dkih_08b').val(customer.knavn);
						jq('#dkih_08c').val(customer.adr1);
						jq('#dkih_08d').val(customer.postnr);
						jq('#dkih_08e').val(customer.adr3);
						jq('#dkih_08f').val(customer.syland);
					}else{
						//init fields
						jq('#dkih_08a').val("");
						jq('#dkih_08b').val("");
						jq('#dkih_08c').val("");
						jq('#dkih_08d').val("");
						jq('#dkih_08e').val("");
						jq('#dkih_08f').val("");
					}
				});
	    		}
		});
	});
		
	
  	//-----------------------------------------
	//FETCH CUSTOMER from TRANSPORTOR html area
  	//-----------------------------------------
	function searchTransportorOwnWindow() {
		jq(function() {
			jq.getJSON('searchCustomer_Skat.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : jq('#search_dkih_trna').val(),
				customerNumber : jq('#search_dkih_trkn').val(),
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
					
					//put the object in map now with customerNumber as key
					map[customer.kundnr] = customer;
				}
				//now that we have our options, give them to our select
				jq('#transportorList').html(html);
			});
		});
	}
	//Sets deklarant values after user selection
	jq(function() { 
	    jq('#transportorList').change(function() {
	      //init fields
		  jq('#dkih_trna').val("");
		  jq('#dkih_treo').val("");
		  jq('#dkih_trga').val("");
		  jq('#dkih_trpo').val("");
		  jq('#dkih_trby').val("");
		  jq('#dkih_trlk').val("");
		  
		  //now populate (if applicable)
		  var key = jq('#transportorList').val();
		  jq('#dkih_trkn').val(key);
		  customer = map[key];
		  jq('#dkih_trna').val(customer.knavn);
		  jq('#dkih_treo').val(customer.eori);
		  jq('#dkih_trga').val(customer.adr1);
		  jq('#dkih_trpo').val(customer.postnr);
		  jq('#dkih_trby').val(customer.adr3);
		  jq('#dkih_trlk').val(customer.syland);			  
		});
	});
	//onClick for Transport dialog
	jq(function() { 
	    jq('#searchCustomer20CloseCancel').click(function() {
	      //rescue the original fields
	      jq('#dkih_trkn').val(jq("#orig_dkih_trkn").val());	
		  jq('#dkih_trna').val(jq("#orig_dkih_trna").val());
		  jq('#dkih_treo').val(jq("#orig_dkih_treo").val());
		  jq('#dkih_trga').val(jq("#orig_dkih_trga").val());
		  jq('#dkih_trpo').val(jq("#orig_dkih_trpo").val());
		  jq('#dkih_trby').val(jq("#orig_dkih_trby").val());
		  jq('#dkih_trlk').val(jq("#orig_dkih_trlk").val());
		  
	    });
	});
	
	//----------------------------------
	//Events Transportor (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgTranportorSearch').click(function(){
    			jq("#search_dkih_trkn").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_dkih_trkn').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransportorOwnWindow);
			}			
    		});
		jq('#search_dkih_trna').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchTransportorOwnWindow);
			}			
    		});
	});
	//--------------------------------------------------------------------------------------
	//Extra behavior for Customer number ( without using (choose from list) extra roundtrip)
	//--------------------------------------------------------------------------------------
	jq(function() { 
	    jq('#dkih_trkn').blur(function() {
	    		var trknValue = jq('#dkih_trkn').val();
	    		if(trknValue!=null && trknValue!=""){
		    		jq.getJSON('searchCustomer_Skat.do', {
					applicationUser : jq('#applicationUser').val(),
					customerName : "",
					customerNumber : jq('#dkih_trkn').val(),
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
						customer.kpers = data[i].kpers;
						customer.tlf = data[i].tlf;
						customer.syland = data[i].syland;
					  	//put the object in map now with customerNumber as key
						map[customer.kundnr] = customer;
					}
					if(len > 0){
						jq('#dkih_trkn').val(customer.kundnr);
						jq('#dkih_trna').val(customer.knavn);
						jq('#dkih_treo').val(customer.eori);	
						jq('#dkih_trga').val(customer.adr1);
						jq('#dkih_trpo').val(customer.postnr);
						jq('#dkih_trby').val(customer.adr3);
						jq('#dkih_trlk').val(customer.syland);	
					}else{
						//init fields
						jq('#dkih_trna').val("");
						jq('#dkih_treo').val("");	
						jq('#dkih_trga').val("");
						jq('#dkih_trpo').val("");
						jq('#dkih_trby').val("");
						jq('#dkih_trlk').val("");
					}
				});
	    		}
		});
	});
	
	
	//-----------------------------------------
	//FETCH CUSTOMER from UNDERETTES html area
  	//-----------------------------------------
	function searchUnderettesOwnWindow() {
		jq(function() {
			jq.getJSON('searchCustomer_Skat.do', {
				applicationUser : jq('#applicationUser').val(),
				customerName : jq('#search_dkih_nina').val(),
				customerNumber : jq('#search_dkih_nikn').val(),
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
					
					//put the object in map now with customerNumber as key
					map[customer.kundnr] = customer;
				}
				//now that we have our options, give them to our select
				jq('#underettesList').html(html);
			});
		});
	}
	//Sets deklarant values after user selection
	jq(function() { 
	    jq('#underettesList').change(function() {
	      //init fields
		  jq('#dkih_nina').val("");
		  jq('#dkih_nieo').val("");
		  jq('#dkih_niga').val("");
		  jq('#dkih_nipo').val("");
		  jq('#dkih_niby').val("");
		  jq('#dkih_nilk').val("");
		  
		  //now populate (if applicable)
		  var key = jq('#underettesList').val();
		  jq('#dkih_nikn').val(key);
		  customer = map[key];
		  jq('#dkih_nina').val(customer.knavn);
		  jq('#dkih_nieo').val(customer.eori);
		  jq('#dkih_niga').val(customer.adr1);
		  jq('#dkih_nipo').val(customer.postnr);
		  jq('#dkih_niby').val(customer.adr3);
		  jq('#dkih_nilk').val(customer.syland);			  
		});
	});
	//onClick for Transport dialog
	jq(function() { 
	    jq('#searchCustomer30CloseCancel').click(function() {
	      //rescue the original fields
	      jq('#dkih_nikn').val(jq("#orig_dkih_nikn").val());	
		  jq('#dkih_nina').val(jq("#orig_dkih_nina").val());
		  jq('#dkih_nieo').val(jq("#orig_dkih_nieo").val());
		  jq('#dkih_niga').val(jq("#orig_dkih_niga").val());
		  jq('#dkih_nipo').val(jq("#orig_dkih_nipo").val());
		  jq('#dkih_niby').val(jq("#orig_dkih_niby").val());
		  jq('#dkih_nilk').val(jq("#orig_dkih_nilk").val());
		  
	    });
	});
	
	//----------------------------------
	//Events Transportor (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgUnderettesSearch').click(function(){
    			jq("#search_dkih_nikn").focus();
    		});
	});
	
	jq(function() {	    
		jq('#search_dkih_nikn').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchUnderettesOwnWindow);
			}			
    		});
		jq('#search_dkih_nina').keypress(function(e){
			if(e.which == 13) {
				e.preventDefault();//this is necessary in order to avoid form.action in form submit button (Save)
				jq(searchUnderettesOwnWindow);
			}			
    		});
	});
	//--------------------------------------------------------------------------------------
	//Extra behavior for Customer number ( without using (choose from list) extra roundtrip)
	//--------------------------------------------------------------------------------------
	jq(function() { 
	    jq('#dkih_nikn').blur(function() {
	    		var niknValue = jq('#dkih_nikn').val();
	    		if(niknValue!=null && niknValue!=""){
		    		jq.getJSON('searchCustomer_Skat.do', {
					applicationUser : jq('#applicationUser').val(),
					customerName : "",
					customerNumber : jq('#dkih_nikn').val(),
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
						customer.kpers = data[i].kpers;
						customer.tlf = data[i].tlf;
						customer.syland = data[i].syland;
					  	//put the object in map now with customerNumber as key
						map[customer.kundnr] = customer;
					}
					if(len > 0){
						jq('#dkih_nikn').val(customer.kundnr);
						jq('#dkih_nina').val(customer.knavn);
						jq('#dkih_nieo').val(customer.eori);
						jq('#dkih_niga').val(customer.adr1);
						jq('#dkih_nipo').val(customer.postnr);
						jq('#dkih_niby').val(customer.adr3);
						jq('#dkih_nilk').val(customer.syland);
					}else{
						//init fields
						jq('#dkih_nina').val("");
					    jq('#dkih_nieo').val("");
					    jq('#dkih_niga').val("");
					    jq('#dkih_nipo').val("");
					    jq('#dkih_niby').val("");
					    jq('#dkih_nilk').val("");
					}
				});
	    		}
		});
	});	
	
	//============================
	//START - Currency AJAX fetch
	//============================
	jq(function() { 
	    jq('#dkih_221').change(function() {
	    	//alert('Hej');
	    	//this parameters must match the AJAX controller parameter names in Spring exactly...
	    		var isoDate = "";
	    		var faktisktAnkDato = jq('#dkih_dtm2').val();
	    		var forventatAnkDato = jq('#dkih_dtm1').val();
	    		if(faktisktAnkDato!=""){
	    			isoDate =faktisktAnkDato; 
	    		}else{
	    			isoDate =forventatAnkDato;
	    		}
	    		getCurrencyData(isoDate);
	    });
	});
	jq(function() { 
	    jq('#dkih_221b').blur(function() {
	    		//alert('Hej');
	    		//this parameters must match the AJAX controller parameter names in Spring exactly...
	    		var dkih_221b = jq('#dkih_221b').val();
	    		var isoDate = "";
	    		var faktisktAnkDato = jq('#dkih_dtm2').val();
	    		var forventatAnkDato = jq('#dkih_dtm1').val();
	    		if(faktisktAnkDato!=""){
	    			isoDate =faktisktAnkDato; 
	    		}else{
	    			isoDate =forventatAnkDato;
	    		}
	    		if(dkih_221b==null || dkih_221b==""){
	    			getCurrencyData(isoDate);
	    		}	
	    });
	});
	jq(function() { 
	    jq('#dkih_221c').blur(function() {
	    		//alert('Hej');
	    		//this parameters must match the AJAX controller parameter names in Spring exactly...
	    		var dkih_221c = jq('#dkih_221c').val();
	    		var isoDate = "";
	    		var faktisktAnkDato = jq('#dkih_dtm2').val();
	    		var forventatAnkDato = jq('#dkih_dtm1').val();
	    		if(faktisktAnkDato!=""){
	    			isoDate =faktisktAnkDato; 
	    		}else{
	    			isoDate =forventatAnkDato;
	    		}
	    		if(dkih_221c==null || dkih_221c==""){
	    			getCurrencyData(isoDate);
	    		}	
	    });
	});
	//private function
	function getCurrencyData(isoDate) {
		jq.ajax({
			type: 'GET',
			url: 'getCurrencyRate_SkatImport.do',
			data: { 	applicationUser : jq('#applicationUser').val(),
					currencyCode : jq('#dkih_221').val(),
					isoDate : isoDate} ,
			dataType: 'json',
			success: function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					jq('#dkih_221b').val(data[i].dkvk_krs);
					jq('#dkih_221c').val(data[i].dkvs_omr);
				}
			}
		});
	}
	//============================
	//END - Currency AJAX fetch
	//============================
	
	
	
	
	//--------------------------------------------------------------------------
  	//onChange-VALIDATION on-demand event
	//This section should always be exactly the same as in the Spring Validator
	//(mandatory fields section of the Validator)
  	//--------------------------------------------------------------------------
	jq(function() { 
	    jq('#dkih_aart').change(function() {
	    		var aart = jq('#dkih_aart').val();
	    		
	    		if("" == aart){ 
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			
	    		}else if("01" == aart){
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_dkih_dtm1'));highlightElement(jq('#v_dkih_181'));
	    			
	    		}else if("02" == aart){
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_dkih_r012'));highlightElement(jq('#v_dkih_25'));
	    			highlightElement(jq('#v_dkih_dtm1'));
	    			
	    		}else if("03" == aart){
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_dkih_r012'));highlightElement(jq('#v_dkih_26'));
	    			highlightElement(jq('#v_dkih_221'));highlightElement(jq('#v_dkih_222'));
	    			highlightElement(jq('#v_dkih_02b'));highlightElement(jq('#v_dkih_02c'));
	    			highlightElement(jq('#v_dkih_02d'));highlightElement(jq('#v_dkih_02e'));
	    			highlightElement(jq('#v_dkih_02f'));
	    			
	    		}else if("04" == aart){
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_dkih_r012'));highlightElement(jq('#v_dkih_25'));
	    			highlightElement(jq('#v_dkih_26'));highlightElement(jq('#v_dkih_221'));
	    			highlightElement(jq('#v_dkih_222'));highlightElement(jq('#v_dkih_dtm1'));
	    			highlightElement(jq('#v_dkih_02b'));highlightElement(jq('#v_dkih_02c'));
	    			highlightElement(jq('#v_dkih_02d'));highlightElement(jq('#v_dkih_02e'));
	    			highlightElement(jq('#v_dkih_02f'));
	    			
	    		}else if("05" == aart){
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_dkih_r012'));highlightElement(jq('#v_dkih_dtm1'));
	    			
	    		}else if("07" == aart){
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_dkih_r012'));highlightElement(jq('#v_dkih_26'));
	    			highlightElement(jq('#v_dkih_221'));highlightElement(jq('#v_dkih_222'));
	    			highlightElement(jq('#v_dkih_02b'));highlightElement(jq('#v_dkih_02c'));
	    			highlightElement(jq('#v_dkih_02d'));highlightElement(jq('#v_dkih_02e'));
	    			highlightElement(jq('#v_dkih_02f'));
	    			
	    		}else if("08" == aart){ 
	    			//init all first
	    			initValidationClass(jq('.validation'));
	    			//change
	    			highlightElement(jq('#v_dkih_r012'));
	    			
	    		}
 
	    });
	    //private
		function highlightElement(element){
			element.css({ 'color': 'red', 'font-size': '112%' });
		}
		function initValidationClass(element){
			element.css({ 'color': 'black', 'font-size': '100%' });
		}
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

	  
	//----------------------------------------------
	  //START Model dialog: "Print delere (skilleark)"
	  //----------------------------------------------
	  //Initialize <div> here
	  jq(function() { 
		  jq("#dialogPrintSkilleArk").dialog({
			  autoOpen: false,
			  maxWidth:400,
	          maxHeight: 300,
	          width: 280,
	          height: 180,
			  modal: true
		  });
	  });
	  //----------------------------
	  //Present dialog box onClick 
	  //----------------------------
	  jq(function() {
		  jq("#printSkilleArkImg").click(function() {
			  presentPrintSkilleArkDialog();
		  });
		  
	  });
	  function presentPrintSkilleArkDialog(){
		//setters (add more if needed)
		  jq('#dialogPrintSkilleArk').dialog( "option", "title", "Print Delere" );
		  //deal with buttons for this modal window
		  jq('#dialogPrintSkilleArk').dialog({
			 buttons: [ 
	            {
				 id: "dialogSaveTU",	
				 text: "Fortsæt",
				 click: function(){
					 		jq('#skilleArkForm').submit();
				 		}
			 	 },
	 	 		{
			 	 id: "dialogCancelTU",
			 	 text: "Annullér", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		jq("#dialogSaveTU").button("option", "disabled", true);
					 		jq("#selectedType").val("");
					 		jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  jq("#dialogSaveTU").button("option", "disabled", true);
		  //open now
		  jq('#dialogPrintSkilleArk').dialog('open');
	  }
	  //Events for the drop downs (some kind of "implicit validation" since all drop downs are mandatory)
	  jq(function() {
		  jq("#selectedType").change(function() {
			  if(jq("#selectedType").val()!=''){
				  jq("#dialogSaveTU").button("option", "disabled", false);
				  
			  }else{
				  jq("#dialogSaveTU").button("option", "disabled", true);
			  }
		  });
		  
	  });
	  //-------------------------------------------
	  //END Model dialog: "Print skilleark"
	  //-------------------------------------------
	
	//-------------------------------------------
	  //START Model dialog: "File upload"
	  //-------------------------------------------
	  //Initialize <div> here
	  jq(function() { 
		  jq("#dialogUploadArchiveDocument").dialog({
			  autoOpen: false,
			  maxWidth:400,
	          maxHeight: 300,
	          width: 400,
	          height: 300,
			  modal: true
		  });
	  });
	  //----------------------------
	  //Present dialog box onClick 
	  //----------------------------
	  jq(function() {
		  jq("#uploadFileImg").click(function() {
			  presentUploadFileDialog();
		  });
		  
	  });
	  function presentUploadFileDialog(){
		//setters (add more if needed)
		  jq('#dialogUploadArchiveDocument').dialog( "option", "title", "Upload dokument" );
		  //deal with buttons for this modal window
		  jq('#dialogUploadArchiveDocument').dialog({
			 buttons: [ 
			     /* N/A (look at file-change event instead     
	            {
	             	
				 id: "dialogSaveTU",	
				 text: "Ok",
				 click: function(){
					 		jq('#uploadFileForm').submit();
				 		}
			 	 },*/
	 	 		{
			 	 id: "dialogCancelTU",
			 	 text: "Annullér", 
				 click: function(){
					 		//back to initial state of form elements on modal dialog
					 		//jq("#dialogSaveTU").button("option", "disabled", true);
					 		//jq("#wstype").val("");
					 		jq( this ).dialog( "close" ); 
				 		} 
	 	 		 } ] 
		  });
		  //init values
		  //jq("#dialogSaveTU").button("option", "disabled", false);
		  //open now
		  jq('#dialogUploadArchiveDocument').dialog('open');
	  }
	  
	  //Events for the drop downs (some kind of "implicit validation" since all drop downs are mandatory)
	  jq(function() {
		  jq("#fileUpload").change(function() {
			  uploadFile();
		  });
		  
	  });
	  //Upload file
	  function uploadFile(){
			//grab all form data  
			  var form = new FormData(document.getElementById('uploadFileForm'));
			  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
			  
			  jq.ajax({
			  	  type: 'POST',
			  	  url: 'uploadFileToArchive.do',
			  	  data: form,  
			  	  dataType: 'text',
			  	  cache: false,
			  	  processData: false,
			  	  contentType: false,
		  		  success: function(data) {
				  	  var len = data.length;
			  		  if(len>0){
			  			jq("#file").val("");
					  	//Check for errors or successfully processed
					  	var exists = data.indexOf("ERROR");
					  	if(exists>0){
					  		//ERROR on back-end
					  		jq("#file").addClass( "isa_error" );
					  		jq("#file").removeClass( "isa_success" );
					  	}else{
					  		//OK
					  		jq("#file").addClass( "isa_success" );
					  		jq("#file").removeClass( "isa_error" );
					  	}
					  	//response to end user 
					  	alert(data);
					  	if(data.indexOf('[OK') == 0) {
						  	var trip = '';
						  	var avd = jq("#wsavd").val();
						  	var opd = jq("#wsopd").val();
						  	var sign = jq("#sign").val();
						  	//reload
						  	window.location = "skatimport_edit.do?action=doFetch&avd=" + avd + "&opd=" + opd + "&sysg=" +  sign;
					  	}
					  	//unblock
					  	jq.unblockUI();
			  		  }
			  	  }, 
			  	  error: function() {
			  		  jq.unblockUI();
			  		  alert('Error loading ...');
			  		  jq("#file").val("");
			  		  //cosmetics
			  		  jq("#file").addClass( "isa_error" );
			  		  jq("#file").removeClass( "isa_success" );
				  }
			  });
			    
			  
		  }
	  
	  //-------------------------------------------
	  //END Model dialog: "File upload"
	  //-------------------------------------------
	

	  