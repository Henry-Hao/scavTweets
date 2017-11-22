var $tweetsTable;
var $resultId;
$(document).ready(function(){
	$("#resultTable").bootstrapTable({
        data:JSON.parse($("#dataField").html()),
		striped: true,
		pagination: true,
		height: 600,
		pageSize: 12,
    });
	
	setTimeout(function(){
		$("#resultTable").bootstrapTable('hideColumn', 'Tweets');
		$("#resultTable").bootstrapTable('hideColumn', 'Result_id');
	},300);
	
})
	

function detailFormatter(value, row, index) {
    return [
        '<a class="detail" style="text-decoration:none" href=# title="Detail" data-dismiss="modal" data-toggle="modal" data-target="#detailModal">',
            '<i class="glyphicon glyphicon-info-sign"></i>',
        '</a>'
    ].join('');
}

function contentFormatter(value, row, index) {
    return [
        '<a class="content" style="text-decoration:none" href=# title="content">',
            '<i class="glyphicon glyphicon-info-sign"></i>',
        '</a>'
    ].join('');
}

window.detailEvents = {
		'click .detail':function (e,value,row,index){
			if($tweetsTable != null && typeof($tweetsTable) != "undefined")
				$tweetsTable.bootstrapTable('destroy');
			$.ajax({
				url:"ResultDetail",
				data:{
					"Result_id":row['Result_id']
				},
				method:"POST",
				success:function(result){
					$resultId = row['Result_id'];
					$tweetsTable = $("#detailTable").bootstrapTable({
				        data:(JSON.parse(result)).result,
						striped: true,
						pagination: true,
						height: 400,
						pageSize: 12,
				    });
					
					setTimeout(function(){
						$("#detailTable").bootstrapTable('hideColumn', 'Id');
					},300);
				}
			})
		}
}

window.contentEvents = {
		'click .content':function (e,value,row,index){
			$.ajax({
				url:"ContentDetail",
				data:{
					"Result_id":$resultId,
					"Tweet_id":row["Id"]
				},
				method:"POST",
				success:function(result){
					alert(result);
				}
			})
		}
}
