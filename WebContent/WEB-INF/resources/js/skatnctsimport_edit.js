	//===========================================
	//General functions for this JSP side - AJAX
	//===========================================
	
	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	var map = {};
  	
  	var BLOCKUI_OVERLAY_MESSAGE_DEFAULT = "Please wait...";
  	
	//Overlay on tab (to mark visually a delay...)
    jq(function() {
      jq('#alinkTopicList').click(function() { 
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  	  });	
  	  jq('#alinkItemLines').click(function() { 
  		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
  	  });
  	  jq('#alinkUnloading').click(function() { 
		  jq.blockUI({ message: BLOCKUI_OVERLAY_MESSAGE_DEFAULT});
	  });
  	  jq('#alinkUnloadingItemLines').click(function() { 
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
	    //ANSVARLIG
	    jq('#tinaIdLink').click(function() {
	    	jq('#tinaIdLink').attr('target','_blank');
	    	window.open('skat_childwindow_customer.do?action=doFind&sonavn=' + jq('#tina').val() + '&ctype=tina', "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
	    });
	    jq('#tinaIdLink').keypress(function(e){ //extra feature for the end user
			if(e.which == 13) {
				jq('#tinaIdLink').click();
			}
	    });
	    
    });  
  	
    //ChildWindow Country Codes
    function triggerChildWindowCountryCodes(record){
    	var idLink = record.id;
    	var id = idLink.replace("IdLink", "");
    	jq(idLink).attr('target','_blank');
    	window.open('skatnctsimport_edit_childwindow_generalcodes.do?action=doInit&type=008&ctype=' + id , "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    }
    //ChildWindow Language Codes
    function triggerChildWindowLanguageCodes(record){
    	var idLink = record.id;
    	var id = idLink.replace("IdLink", "");
    	jq(idLink).attr('target','_blank');
    	window.open('skatnctsimport_edit_childwindow_generalcodes.do?action=doInit&type=012&ctype=' + id , "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    }
    //ChildWindow Tullkontor Codes
    function triggerChildWindowTullkontorCodes(record){
    	var idLink = record.id;
    	var id = idLink.replace("IdLink", "");
    	//alert(idLink + "XX" + id);
    	//alert(jq("#"+id).val());
    	jq(idLink).attr('target','_blank');
    	window.open('skatnctsimport_edit_childwindow_tullkontor.do?action=doInit&tkkode=' + jq("#"+id).val()+ '&ctype=' + id, "codeWin", "top=300px,left=500px,height=600px,width=800px,scrollbars=no,status=no,location=no");
    }
	  
    
    
  	jq(document).ready(function(){
  	    jq(this).scrollTop(0); //needed for Chrome (bug)
  	});
  	
  	
  	//init the customer object in javascript (will be put into a map)
  	var customer = new Object();
  	//fields
  	customer.kundnr = "";customer.knavn = "";customer.eori = "";customer.adr1 = "";
  	customer.adr2 = "";customer.adr3 = "";customer.postnr = "";customer.syland = "";
  	customer.kpers = "";customer.tlf = "";

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
		  jq('#tikn').val("");
		  jq('#tina').val("");
		  jq('#titin').val("");
		  jq('#tiad1').val("");
		  jq('#tipn').val("");
		  jq('#tips').val("");
		  jq('#tilk').val("");
		  jq('#tisk').val("");
		  
		  //now populate (if applicable)
		  var key = jq('#ansvarigList').val();
		  jq('#tikn').val(key);
		  customer = map[key];
		  jq('#tina').val(customer.knavn);
		  jq('#titin').val(customer.eori);
		  jq('#tiad1').val(customer.adr1);
		  jq('#tipn').val(customer.postnr);
		  jq('#tips').val(customer.adr3);
		  jq('#tilk').val(customer.syland);
		  jq('#tisk').val("");			  
		});
	});
	//onClick for Ansvarig dialog
	jq(function() { 
	    jq('#searchCustomer10CloseCancel').click(function() {
	      //rescue the original fields
	      jq('#tikn').val(jq("#orig_tikn").val());	
		  jq('#tina').val(jq("#orig_tina").val());
		  jq('#titin').val(jq("#orig_titin").val());
		  jq('#tiad1').val(jq("#orig_tiad1").val());
		  jq('#tipn').val(jq("#orig_tipn").val());
		  jq('#tips').val(jq("#orig_tips").val());
		  jq('#tilk').val(jq("#orig_tilk").val());
		  jq('#tisk').val(jq("#orig_tisk").val());
		  
	    });
	});
	
	//----------------------------------
	//Events Ansvarig (SEARCH window)
	//----------------------------------
	//img click
	jq(function() {	    
		jq('#imgAnsvarigSearch').click(function(){
    			jq("#tikn").focus();
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
	//--------------------------------------------------------------------------------------
	//Extra behavior for Customer number ( without using (choose from list) extra roundtrip)
	//--------------------------------------------------------------------------------------
	jq(function() { 
	    jq('#tikn').blur(function() {
	    	var customerNumberVal = jq('#tikn').val();
    		if(customerNumberVal!=""){
		    		jq.getJSON('searchCustomer_Skat.do', {
					applicationUser : jq('#applicationUser').val(),
					customerName : "",
					customerNumber : jq('#tikn').val(),
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
						jq('#tikn').val(customer.kundnr);
						jq('#titin').val(customer.eori);
						jq('#tina').val(customer.knavn);
						jq('#tiad1').val(customer.adr1);
						jq('#tipn').val(customer.postnr);
						jq('#tips').val(customer.adr3);
						jq('#tilk').val(customer.syland);
						jq('#tisk').val("");	
					}else{
						//init fields
						jq('#titin').val("");
						jq('#tina').val("");
						jq('#tiad1').val("");
						jq('#tipn').val("");
						jq('#tips').val("");
						jq('#tilk').val("");
						jq('#tisk').val("");
					}
				});
			}
		});
	});
	
	
	
	
	
	//------------------------------------------------------------------------------------------
	//Init Topic with CREATE NEW TOPIC since some values are fetched depending on the Avdelning
	//------------------------------------------------------------------------------------------	
	jq(function() { 
	    jq('#avd').change(function() {
	    		//alert('Hej');
	    		//this parameters must match the AJAX controller parameter names in Spring exactly...
	    		jq.getJSON('initCreateNewTopic_SkatNctsImport.do', {
	    			applicationUser : jq('#applicationUser').val(),
	    			avd : jq('#avd').val(),
	    			ajax : 'true'
			}, 
			function(data) {
				var len = data.length;
				for ( var i = 0; i < len; i++) {
					jq('#tienkl').val(data[i].tienkl);
					//jq('#sign').val(data[i].tisg);
					//jq('#tisg').val(data[i].tisg);
					jq('#tist').val(data[i].tist);
					jq('#tidt').val(data[i].tidt);
					
					jq('#tikn').val(data[i].tikn);
					jq('#tina').val(data[i].tina);
					jq('#tiad1').val(data[i].tiad1);
					jq('#tips').val(data[i].tips);
					jq('#tipn').val(data[i].tipn);
					jq('#tilk').val(data[i].tilk);
					jq('#tisk').val(data[i].tisk);
					jq('#titin').val(data[i].titin);
					
					jq('#tign').val(data[i].tign);
					jq('#titrnr').val(data[i].titrnr);
					//jq('#tignsk').val(data[i].tignsk);
					//jq('#tialk').val(data[i].tialk);
					jq('#tignsk').val("DA");//default
					jq('#tialk').val("");//default
					jq('#tialsk').val(data[i].tialsk);
					
					jq('#tials').val(data[i].tials);
					jq('#tialss').val(data[i].tialss);
					
					jq('#tiglsk').val(data[i].tiglsk);
					jq('#tiacts').val(data[i].tiacts);
					jq('#tiskb').val(data[i].tiskb);
					jq('#titsb').val(data[i].titsb);
					jq('#tidtf').val(data[i].tidtf);
					
				}
				
			});
	    });
	});
	
	
	//-------------------------------------------
	//Mellanligande-Tullkontor AJAX [NCTS Import]
	//-------------------------------------------
	//FETCH Tullkontor
	function searchAvgangTullkontorOwnWindow(){
		//init the tullkontor object in javascript (will be put into a map)
	  	var tullkontor = new Object();
	  	//fields
	  	tullkontor.tkkode = "";tullkontor.tktxtn = "";
	  	var KONTOR_TYPE_ANKOMST = "ank";
		jq(function(){
			//this parameters must match the AJAX controller parameter names in Spring exactly...
			jq.getJSON('searchUtfartsTullkontor_Skat.do', {
				applicationUser : jq('#applicationUser').val(),
				tullkontorName : jq('#search_sveh_utfa').val(),
				tullkontorCode : jq('#search_sveh_utfa_Code').val(),
				kontorType: KONTOR_TYPE_ANKOMST,
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
				jq('#tullkontorList').html(html);
			});
		});
		
	}
	//BestämmelseTullkontor list
	jq(function() { 
	    jq('#tullkontorList').change(function() {
	    	jq('#titsb').val(""); //ncts import
	    	
		//now populate (if applicable)
	    	var key = jq('#tullkontorList').val();
	    	jq('#titsb').val(key); //ncts import
	    	
	    });
	});
	//----------------------------------------
	//Events Avgångstullkontor (SEARCH window)
	//----------------------------------------
	//img click
	jq(function() {	    
		jq('#imgTullkontor').click(function(){
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

	
	
	
	
	
	
	

