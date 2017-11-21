var $tweetsTable;
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
	},300);
	
})
	

function detailFormatter(value, row, index) {
    return [
        '<a class="detail" style="text-decoration:none" href=# title="Detail" data-dismiss="modal" data-toggle="modal" data-target="#detailModal">',
            '<i class="glyphicon glyphicon-info-sign"></i>',
        '</a>'
    ].join('');
}

window.detailEvents = {
		'click .detail':function (e,value,row,index){
			if($tweetsTable != null && typeof($tweetsTable) != "undefined")
				$tweetsTable.bootstrapTable('destroy');
			
			$tweetsTable = $("#detailTable").bootstrapTable({
		        data:row['Tweets'],
				striped: true,
				pagination: true,
				height: 600,
				pageSize: 12,
		    });
			
			setTimeout(function(){
				$("#detailTable").bootstrapTable('hideColumn', 'Id');
			},300);
		}

}
