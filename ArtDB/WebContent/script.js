$(document).ready(function () {
	    $("#table").change(function () {
	        var val = $(this).val();
	        if (val == "Art_Object") {
	            $("#column").html("<option value='Title'>Title</option><option value='Description'>Description</option>" +
	            "<option value='Signature'>Signature</option><option value='Dated'>Dated</option>" +
	           "<option value='Markings'>Markings</option><option value='Style'>Style</option>");
	        } else if (val == "Artist") {
	            $("#column").html("<option value='Artist_name'>Name</option><option value='Life_date'>Life date</option>" +
	            		"<option value='Nationality'>Nationality</option>");
	        } else if (val == "Culture_info") {
	            $("#column").html("<option value='Continent'>Continent</option><option value='Country'>Country</option>");
	        } else if (val == "Department") {
	            $("#column").html("<option value='Department_name'>Name</option>");
	        } else if (val == "Exhibitions") {
	            $("#column").html("<option value='Exhibition_title'>Title</option><option value='Description'>Description</option>" +
	            		"<option value='Begin'>Begin</option><option value='End'>End</option><option value='Display_date'>Display date</option>");
	        } else if (val == "Room") {
	            $("#column").html("<option value='Room_name'>Name</option>");
	        } else if (val == "Specs") {
	            $("#column").html("<option value='Dimensions'>Dimensions</option>");
	        }
	    });
	});
