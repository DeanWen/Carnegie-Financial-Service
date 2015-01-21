//test for update quality
$(document).ready(function(){
	//checkout0.html start.
	
	$('#sameaddr').click( function () {
    	// Copy the content of LHS to RHS.
        var output = document.getElementById('output');
	 	if ($(this).is(':checked')){
	    	$('#R0').val($('#L0').val());
	    	$('#R1').val($('#L1').val());
	    	$('#R2').val($('#L2').val());
	    	$('#R3').val($('#L3').val());
	    	$('#R4').val($('#L4').val());
	    	$('#R5').val($('#L5').val());
	    	$('#zipR').val($('#zipL').val());
	   
			var statelistR = document.getElementById('statelistR');
			while(statelistR.options.length){
				statelistR.remove(0);
			}
			statelistR.options[statelistR.options.length] = new Option( $('#statelist option:selected').text(), '0');

	    	$('#R6').val($('#L6').val());
	    	$('#R7').val($('#L7').val());
	 	}
	   	else{
	     	$("#output").val(212);
	    }

	});
	
	$( "#zipL" ).blur(function() {

		var statelist = document.getElementById('statelist');
		while(statelist.options.length){
			statelist.remove(0);
		}
  		//var zipnum = document.getElementById('#zipL').value;
  		var zipnum = $('#zipL').val().charAt(0);
  		if (zipnum==1) {
			statelist.options[statelist.options.length] = new Option( 'Delaware (DE)', 'DE');
			statelist.options[statelist.options.length] = new Option( 'New York (NY)', 'NY');
			statelist.options[statelist.options.length] = new Option( 'Pennsylvania (PA)', 'PA');
  		}
  		else if (zipnum==2) {
			statelist.options[statelist.options.length] = new Option( 'District of Columbia (DC)', 'DC');
			statelist.options[statelist.options.length] = new Option( 'Maryland (MD)', 'MD');
			statelist.options[statelist.options.length] = new Option( 'North Carolina (NC)', 'NC');
			statelist.options[statelist.options.length] = new Option( 'South Carolina (SC)', 'SC');
			statelist.options[statelist.options.length] = new Option( 'Virginia (VA)', 'VA');
			statelist.options[statelist.options.length] = new Option( 'West Virginia (WV)', 'WV');
  		}
  		else if (zipnum==3) {
			statelist.options[statelist.options.length] = new Option( 'Alabama (AL)', 'AL');
			statelist.options[statelist.options.length] = new Option( 'Florida (FL)', 'FL');
			statelist.options[statelist.options.length] = new Option( 'Georgia (GA)', 'GA');
			statelist.options[statelist.options.length] = new Option( 'Mississippi (MS)', 'MS');
			statelist.options[statelist.options.length] = new Option( 'Tennessee (TN)', 'TN');
  		}
  		else if (zipnum==4) {
			statelist.options[statelist.options.length] = new Option( 'Indiana (IN)', 'IN');
			statelist.options[statelist.options.length] = new Option( 'Kentucky (KY)', 'KY');
			statelist.options[statelist.options.length] = new Option( 'Michigan (MI)', 'MI');
			statelist.options[statelist.options.length] = new Option( 'Ohio (OH)', 'OH');
  		}
  		else if (zipnum==5) {
			statelist.options[statelist.options.length] = new Option( 'Iowa (IA)', 'IA');
			statelist.options[statelist.options.length] = new Option( 'Minnesota (MN)', 'MN');
			statelist.options[statelist.options.length] = new Option( 'Montana (MT)', 'MT');
			statelist.options[statelist.options.length] = new Option( 'North Dakota (ND)', 'ND');
			statelist.options[statelist.options.length] = new Option( 'South Dakota (SD)', 'SD');
			statelist.options[statelist.options.length] = new Option( 'Wisconsin (WI)', 'WI');
  		}
  		else if (zipnum==6) {
			statelist.options[statelist.options.length] = new Option( 'Illinois (IL)', 'IL');
			statelist.options[statelist.options.length] = new Option( 'Kansas (KS)', 'KS');
			statelist.options[statelist.options.length] = new Option( 'Missouri (MO)', 'MO');
			statelist.options[statelist.options.length] = new Option( 'Nebraska (NE)', 'NE');
  		}
  		else if (zipnum==7) {
			statelist.options[statelist.options.length] = new Option( 'Arkansas (AR)', 'AR');
			statelist.options[statelist.options.length] = new Option( 'Louisiana (LA)', 'LA');
			statelist.options[statelist.options.length] = new Option( 'Oklahoma (OK)', 'OK');
			statelist.options[statelist.options.length] = new Option( 'Texas (TX)', 'TX');
  		}
  		else if (zipnum==8) {
			statelist.options[statelist.options.length] = new Option( 'Arizona (AZ)', 'AZ');
			statelist.options[statelist.options.length] = new Option( 'Colorado (CO)', 'CO');
			statelist.options[statelist.options.length] = new Option( 'Idaho (ID)', 'ID');
			statelist.options[statelist.options.length] = new Option( 'New Mexico (NM)', 'NM');
			statelist.options[statelist.options.length] = new Option( 'Nevada (NV)', 'NV');
			statelist.options[statelist.options.length] = new Option( 'Utah (UT)', 'UT');
			statelist.options[statelist.options.length] = new Option( 'Wyoming (WY)', 'WY');
  		}
  		else if (zipnum==9) {
			statelist.options[statelist.options.length] = new Option( 'Alaska (AK)', 'AK');
			statelist.options[statelist.options.length] = new Option( 'American Samoa (AS)', 'AS');
			statelist.options[statelist.options.length] = new Option( 'California (CA)', 'CA');
			statelist.options[statelist.options.length] = new Option( 'Guam (GU)', 'GU');
			statelist.options[statelist.options.length] = new Option( 'Hawaii (HI)', 'HI');
			statelist.options[statelist.options.length] = new Option( 'Oregon (OR)', 'OR');
			statelist.options[statelist.options.length] = new Option( 'Washington (WA)', 'WA');
  		}
  		else if (zipnum==0) {
			statelist.options[statelist.options.length] = new Option( 'Connecticut (CT)', 'CT'); 
			statelist.options[statelist.options.length] = new Option( 'Massachusetts (MA)', 'MA');
			statelist.options[statelist.options.length] = new Option( 'Maine (ME)', 'ME');
			statelist.options[statelist.options.length] = new Option( 'New Hampshire (NH)', 'NH');
			statelist.options[statelist.options.length] = new Option( 'New Jersey (NJ)', 'NJ');
			statelist.options[statelist.options.length] = new Option( 'Puerto Rico (PR)', 'PR');
			statelist.options[statelist.options.length] = new Option( 'Rhode Island (RI)', 'RI');
			statelist.options[statelist.options.length] = new Option( 'Vermont (VT)', 'VT');
			statelist.options[statelist.options.length] = new Option( 'Virgin Islands (VI)', 'VI');
  		};
	});

	$( "#zipR" ).blur(function() {

		var statelist = document.getElementById('statelistR');
		while(statelist.options.length){
			statelist.remove(0);
		}
  		//var zipnum = document.getElementById('#zipL').value;
  		var zipnum = $('#zipR').val().charAt(0);
  		if (zipnum==1) {
			statelist.options[statelist.options.length] = new Option( 'Delaware (DE)', 'DE');
			statelist.options[statelist.options.length] = new Option( 'New York (NY)', 'NY');
			statelist.options[statelist.options.length] = new Option( 'Pennsylvania (PA)', '0');
  		}
  		else if (zipnum==2) {
			statelist.options[statelist.options.length] = new Option( 'District of Columbia (DC)', '0');
			statelist.options[statelist.options.length] = new Option( 'Maryland (MD)', '0');
			statelist.options[statelist.options.length] = new Option( 'North Carolina (NC)', '0');
			statelist.options[statelist.options.length] = new Option( 'South Carolina (SC)', '0');
			statelist.options[statelist.options.length] = new Option( 'Virginia (VA)', '0');
			statelist.options[statelist.options.length] = new Option( 'West Virginia (WV)', '0');
  		}
  		else if (zipnum==3) {
			statelist.options[statelist.options.length] = new Option( 'Alabama (AL)', '0');
			statelist.options[statelist.options.length] = new Option( 'Florida (FL)', '0');
			statelist.options[statelist.options.length] = new Option( 'Georgia (GA)', '0');
			statelist.options[statelist.options.length] = new Option( 'Mississippi (MS)', '0');
			statelist.options[statelist.options.length] = new Option( 'Tennessee (TN)', '0');
  		}
  		else if (zipnum==4) {
			statelist.options[statelist.options.length] = new Option( 'Indiana (IN)', '0');
			statelist.options[statelist.options.length] = new Option( 'Kentucky (KY)', '0');
			statelist.options[statelist.options.length] = new Option( 'Michigan (MI)', '0');
			statelist.options[statelist.options.length] = new Option( 'Ohio (OH)', '0');
  		}
  		else if (zipnum==5) {
			statelist.options[statelist.options.length] = new Option( 'Iowa (IA)', '0');
			statelist.options[statelist.options.length] = new Option( 'Minnesota (MN)', '0');
			statelist.options[statelist.options.length] = new Option( 'Montana (MT)', '0');
			statelist.options[statelist.options.length] = new Option( 'North Dakota (ND)', '0');
			statelist.options[statelist.options.length] = new Option( 'South Dakota (SD)', '0');
			statelist.options[statelist.options.length] = new Option( 'Wisconsin (WI)', '0');
  		}
  		else if (zipnum==6) {
			statelist.options[statelist.options.length] = new Option( 'Illinois (IL)', '0');
			statelist.options[statelist.options.length] = new Option( 'Kansas (KS)', '0');
			statelist.options[statelist.options.length] = new Option( 'Missouri (MO)', '0');
			statelist.options[statelist.options.length] = new Option( 'Nebraska (NE)', '0');
  		}
  		else if (zipnum==7) {
			statelist.options[statelist.options.length] = new Option( 'Arkansas (AR)', '0');
			statelist.options[statelist.options.length] = new Option( 'Louisiana (LA)', '0');
			statelist.options[statelist.options.length] = new Option( 'Oklahoma (OK)', '0');
			statelist.options[statelist.options.length] = new Option( 'Texas (TX)', '0');
  		}
  		else if (zipnum==8) {
			statelist.options[statelist.options.length] = new Option( 'Arizona (AZ)', '0');
			statelist.options[statelist.options.length] = new Option( 'Colorado (CO)', '0');
			statelist.options[statelist.options.length] = new Option( 'Idaho (ID)', '0');
			statelist.options[statelist.options.length] = new Option( 'New Mexico (NM)', '0');
			statelist.options[statelist.options.length] = new Option( 'Nevada (NV)', '0');
			statelist.options[statelist.options.length] = new Option( 'Utah (UT)', '0');
			statelist.options[statelist.options.length] = new Option( 'Wyoming (WY)', '0');
  		}
  		else if (zipnum==9) {
			statelist.options[statelist.options.length] = new Option( 'Alaska (AK)', '0');
			statelist.options[statelist.options.length] = new Option( 'American Samoa (AS)', '0');
			statelist.options[statelist.options.length] = new Option( 'California (CA)', '0');
			statelist.options[statelist.options.length] = new Option( 'Guam (GU)', '0');
			statelist.options[statelist.options.length] = new Option( 'Hawaii (HI)', '0');
			statelist.options[statelist.options.length] = new Option( 'Oregon (OR)', '0');
			statelist.options[statelist.options.length] = new Option( 'Washington (WA)', '0');
  		}
  		else if (zipnum==0) {
			statelist.options[statelist.options.length] = new Option( 'Connecticut (CT)', '0'); 
			statelist.options[statelist.options.length] = new Option( 'Massachusetts (MA)', '0');
			statelist.options[statelist.options.length] = new Option( 'Maine (ME)', '0');
			statelist.options[statelist.options.length] = new Option( 'New Hampshire (NH)', '0');
			statelist.options[statelist.options.length] = new Option( 'New Jersey (NJ)', '0');
			statelist.options[statelist.options.length] = new Option( 'Puerto Rico (PR)', '0');
			statelist.options[statelist.options.length] = new Option( 'Rhode Island (RI)', '0');
			statelist.options[statelist.options.length] = new Option( 'Vermont (VT)', '0');
			statelist.options[statelist.options.length] = new Option( 'Virgin Islands (VI)', '0');
  		};
	});


	//checkout0.html end.

});
