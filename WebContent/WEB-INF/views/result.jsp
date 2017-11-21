<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search</title>

<link rel="stylesheet" href="${ctx}/resources/module/bootstrap.min.css" >
<script src="${ctx}/resources/module/jquery-2.1.4.min.js"></script>
<script src="${ctx}/resources/module/bootstrap.min.js"></script>

<link rel="stylesheet" href="${ctx}/resources/css/index.css" >
<link href="${ctx}/resources/module/bootstrap-table.css" rel="stylesheet">

<script src="${ctx}/resources/js/result.js"></script>
<script src="${ctx}/resources/module/bootstrap-table.js"></script>
</head>
<body>
	<div class="container">
        <textarea id="dataField" style="display: none">
            <%= request.getAttribute("result")%>
        </textarea>
	   <table id="resultTable" 
	          data-show-toggle="true"
	          data-search="true"           
	          data-show-filter="true"
	          data-striped="true"
	          data-sort="Name"
	          data-toolbar="#toolbar"
	          data-sort-order="desc">
	     <thead>
	       <tr>
	       	 <th data-field="Name" data-width="30" data-align="left">Name</th>
	         <th data-field="Term" data-width="30" data-align="left" data-sortable="true">Term</th>
	         <th data-field="Tweets" data-width="30" data-align="left" data-sortable="true">Tweets</th>
	         <th data-field="Start_time" data-width="80" data-align="left" data-sortable="true">Start_time</th>
	         <th data-field="End_time"data-width="80" data-align="left" data-sortable="true">End_time</th>
	         <th data-field="detail" data-width="20" data-formatter="detailFormatter" data-events="detailEvents">Detail</th>
	    	</tr>
	     </thead>
	   </table>
	</div>
	
	<div class="modal fade" id="detailModal" tabindex="-1" role="dialog" 
	   aria-labelledby="myModalLabel" aria-hidden="true:>
	   <div class="modal-dialog">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" 
	               aria-hidden="true">x
	            </button>
	            <h4 class="modal-title" id="myModalLabel">
	              Detail
                </h4>
	         </div>
	         <div class="modal-body">
	            <table id="detailTable" 
			          data-show-toggle="true"
			          data-search="true"           
			          data-show-filter="true"
			          data-striped="true"
			          data-sort="Name"
			          data-toolbar="#toolbar"
			          data-sort-order="desc">
			     <thead>
			       <tr>
			       	 <th data-field="Id" data-width="30" data-align="left">Id</th>
			         <th data-field="Time" data-width="30" data-align="left" data-sortable="true">Time</th>
			         <th data-field="Keyword" data-width="30" data-align="left" data-sortable="true">Keyword</th>
			         <th data-field="Content" data-width="30" data-align="left" data-sortable="true">Content</th>
	             	</tr>
			     </thead>
			   </table>
	         </div>
	         <div class="modal-footer">
	            <button type="button" class="btn btn-default" 
	               data-dismiss="modal">Close
	            </button>
	            <button type="button" class="btn btn-primary" id='plot' data-dismiss="modal">
	               Plot
	            </button>
	         </div>
	      </div><!-- /.modal-content -->
	   </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
</body>
</html>