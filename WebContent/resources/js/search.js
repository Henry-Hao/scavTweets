var start_time;
var end_time;
$(document).ready(function(){
	$table = $("#searchTable").bootstrapTable({
        data:JSON.parse($("#dataField").html()),
		striped: true,
		pagination: true,
		height: 600,
		pageSize: 12
    });
	
	setTimeout(function(){
		$("#resultTable").bootstrapTable('hideColumn', 'Id');
	},300);
	
	$('#start_time').datetimepicker({
		startDate: (new Date()).toLocaleString(),
        language:  'en',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1,
        minuteStep:5,
        pickerPosition:'top-left'
    });
	
	$('#end_time').datetimepicker({
		startDate: (new Date()).toLocaleString(),
        language:  'en',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1,
        minuteStep:5,
        pickerPosition:'top-left'
    });
	
	$("#starttime")
	.datetimepicker()
	.on('changeDate', function(ev){
		time = $("#st_input").val();
	});
	$('#starttime').datetimepicker('setEndDate', new Date());
	
	$("#endtime")
	.datetimepicker()
	.on('changeDate', function(ev){
		time = $("#et_input").val();
	});
	$('#endtime').datetimepicker('setEndDate', new Date());
	
	
	$("#addSearchConfirm").click(function(){
		var name = $("#add_name").val().trim();
		var location = $("#add_location").val().trim();
		var st = $("#st_input").val();
		var et = $("#et_input").val();
		var term = "";
		
		$(".add_term").each(function(index,value){
			term += value.value+";";
		});
		
		if(name == "" || location == ""){
			alert("All fields should be filled!");
			return;
		}
		
		if(term == ""){
			alert("At least one term should be provided!");
			return;
		}
		
		var d1 = new Date(st.replace(/\-/g, "\/"));    
		var d2 = new Date(et.replace(/\-/g, "\/"));    
		if(st!=""&&et!=""&&d1 >=d2){    
			alert("End_time should be greater than Start_time");    
			return;    
		 }  
		
		$.ajax({
			url:"newSearch",
			method:"POST",
			data:{
				"name":name,
				"location":location,
				"st":st,
				"et":et,
				"term":term
			},
			dataType:"json",
			success:function(result){
				switch(result.result){
				case "duplicate":
					alert("Duplicate Search!");
					break;
				case "fail":
					alert("Fail!");
					break;
				case "success":
					alert("Success!");
					//refresh the table
					parent.location.reload(true);
					break;
				}
			}
		})
		
	});
})

function addTerm(node){
	if($("#term_div .input-group").length == 6)
		alert("Maximum term reached!");
	else if($(node).parent().find(".add_term").val().trim() == "")
		alert("Filed should not be empty!");
	else{
		var str = `<div class="input-group">
            <span class="input-group-addon">term</span>
            <input type="text" class="form-control comp_sponsor add_term" style="width: 200px" maxlength="15" placeholder="term">
        	<div type="button" class="input-group-addon" style="border:solid 1px #ccc" onclick="addTerm(this)"><i class="glyphicon glyphicon-plus-sign" aria-hidden="true"></i></div>
        	<div type="button" class="input-group-addon" onclick="removeTerm(this)" style="border:solid 1px #ccc;display:none"><i class="glyphicon glyphicon-minus-sign"  aria-hidden="true"></i></div>
        </div>`;
		$(node).toggle();
		$(node).next("div").toggle();
		$("#term_div").append(str);
	}
}

function removeTerm(node){
	$(node).parent().remove();
}

function removeFormatter(value, row, index) {
    return [
        '<a class="remove" style="text-decoration:none" href=# title="Remove">',
            '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}
window.removeEvents = {
		'click .remove':function (e,value,row,index){
			var removeid = row['Id'];
			if(confirm("Are you sure to remove it??")){
				$.ajax({
					url:"removeSearch",
					type:"post",
					dataType:"json",
					data:{"removeId":removeid},
					success:function(result){
						switch (result.result) {
						case "success":
							alert("Success");
							parent.location.reload(true);
							break;
						case "fail":
							alert("Fail");
						default:
							break;
						}
					}
				})
			}
		}
}
