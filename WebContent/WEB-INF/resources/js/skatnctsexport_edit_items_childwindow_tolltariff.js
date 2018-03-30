	//this variable is a global jQuery var instead of using "$" all the time. Very handy
  	var jq = jQuery.noConflict();
  	//--------
  	//Koder
  	//--------
	jq(function() {
		jq('#tolltariffList').on('click', 'td', function(){
			  var id = this.id;
			  var record = id.split('@');
			  var vkod = record[0].replace("vkod", "");
			  var text = record[1].replace("text", "");
			  var callerType = record[2].replace("ctype", "");
			  var tollsats = record[3].replace("tollsats", "");
			  
			  //alert(vkod + " " + text + " " + callerType);
			  if(vkod!=null && vkod.length>6){
				  //vkod = vkod.substring(0, 6); change to 8-chars
			  }	  
			  opener.jq('#tvvnt').val(vkod);
			  opener.jq('#tvvnt').focus();
			  opener.jq('#tvvt').val(text);
			  //tollsats
			  opener.jq('#ownToldsats').val(tollsats);
			  
			  //close child window
			  window.close();
		  });
	});
	
	jq(function() {
		jq('#vkod').blur(function(){
			if(jq('#vkod').val() != ""){
				jq('#tekst').val("");
			}
		});
		jq('#tekst').blur(function(){
			if(jq('#tekst').val() != ""){
				jq('#vkod').val("");
			}
		});
	});
	
	//======================
    //Datatables jquery 
    //======================
    //private function [Filters]
    function filterGeneralCode () {
    		jq('#tolltariffList').DataTable().search(
      		jq('#tolltariffList_filter').val()
    		).draw();
    } 
	//Init datatables
    jq(document).ready(function() {
  	  //-----------------------
      //table [General Code List]
  	  //-----------------------
    	  jq('#tolltariffList').dataTable( {
    		  "dom": '<"top"fli>rt<"bottom"p><"clear">',
    		  "lengthMenu": [ 75, 100, 200, 500]
    	  });
      //event on input field for search
      jq('input.tolltariffList_filter').on( 'keyup click', function () {
      		filterGeneralCode();
      });
      
    });   
  	
  	
	