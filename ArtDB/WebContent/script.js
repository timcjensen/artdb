$(document)
		.ready(
				function() {
					$("#table")
							.change(
									function() {
										var val = $(this).val();
										if (val == "Art_Object") {
											$("#column")
													.html(
															"<option value='Title'>Title</option><option value='Description'>Description</option>"
																	+ "<option value='Signature'>Signature</option><option value='Dated'>Dated</option>"
																	+ "<option value='Markings'>Markings</option><option value='Style'>Style</option>");
										} else if (val == "Artist") {
											$("#column")
													.html(
															"<option value='Artist_name'>Name</option><option value='Life_date'>Life date</option>"
																	+ "<option value='Nationality'>Nationality</option>");
										} else if (val == "Culture_info") {
											$("#column")
													.html(
															"<option value='Continent'>Continent</option><option value='Country'>Country</option>");
										} else if (val == "Department") {
											$("#column")
													.html(
															"<option value='Department_name'>Name</option>");
										} else if (val == "Exhibitions") {
											$("#column")
													.html(
															"<option value='Exhibition_title'>Title</option><option value='Description'>Description</option>"
																	+ "<option value='Begin'>Begin</option><option value='End'>End</option><option value='Display_date'>Display date</option>");
										} else if (val == "Room") {
											$("#column")
													.html(
															"<option value='Room_name'>Name</option>");
										} else if (val == "Specs") {
											$("#column")
													.html(
															"<option value='Dimensions'>Dimensions</option>");
										}
									});
				});

//$("#result td").click(function() {
//	$(this).addClass('selected').siblings().removeClass('selected');
//	var value = $(this).html();
//	alert(value);
//});

function sortTable(n) {
	var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
	table = document.getElementById("result");
	switching = true;
	// Set the sorting direction to ascending:	
	dir = "asc";
	/* Make a loop that will continue until
	no switching has been done: */
	while (switching) {
		// Start by saying: no switching is done:
		switching = false;
		rows = table.rows;
		/* Loop through all table rows (except the
		first, which contains table headers): */
		for (i = 1; i < (rows.length - 1); i++) {
			// Start by saying there should be no switching:
			shouldSwitch = false;
			/* Get the two elements you want to compare,
			one from current row and one from the next: */
			x = rows[i].getElementsByTagName("TD")[n];
			y = rows[i + 1].getElementsByTagName("TD")[n];
			/* Check if the two rows should switch place,
			based on the direction, asc or desc: */
			if (dir == "asc") {
				if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
					// If so, mark as a switch and break the loop:
					shouldSwitch = true;
					break;
				}
			} else if (dir == "desc") {
				if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
					// If so, mark as a switch and break the loop:
					shouldSwitch = true;
					break;
				}
			}
		}
		if (shouldSwitch) {
			/* If a switch has been marked, make the switch
			and mark that a switch has been done: */
			rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
			switching = true;
			// Each time a switch is done, increase this count by 1:
			switchcount++;
		} else {
			/* If no switching has been done AND the direction is "asc",
			set the direction to "desc" and run the while loop again. */
			if (switchcount == 0 && dir == "asc") {
				dir = "desc";
				switching = true;
			}
		}
	}
}
