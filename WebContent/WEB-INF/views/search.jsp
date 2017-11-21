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
<link href="${ctx}/resources/module/bootstrap-datetimepicker.min.css" rel="stylesheet">

<script src="${ctx}/resources/js/search.js"></script>
<script src="${ctx}/resources/module/bootstrap-table.js"></script>
<script src="${ctx}/resources/module/bootstrap-datetimepicker.js"></script>
</head>
<body>
	<div class="container">
	   <div id="toolbar" class="btn-group">
	   		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addModal" id='btn_add'>ADD</button>
	   </div>
        <textarea id="dataField" style="display: none">
            <%= request.getAttribute("search")%>
        </textarea>
	   <table id="searchTable" 
	          data-show-toggle="true"
	          data-search="true"           
	          data-show-filter="true"
	          data-striped="true"
	          data-sort="name"
	          data-toolbar="#toolbar"
	          data-sort-order="desc">
	     <thead>
	       <tr>
	       	 <th data-field="Id" data-width="30" data-align="left">Id</th>
	       	 <th data-field="Name" data-width="30" data-align="left">Name</th>
	         <th data-field="Region" data-width="30" data-align="left" data-sortable="true">Region</th>
	         <th data-field="Term" data-width="30" data-align="left" data-sortable="true">Term</th>
	         <th data-field="Start_time" data-width="30" data-align="left" data-sortable="true">Start_time</th>
	         <th data-field="End_time"data-width="80" data-align="left" data-sortable="true">End_time</th>
	         <th data-field="delete" data-width="20" data-formatter="removeFormatter" data-events="removeEvents">Remove</th>
	    	</tr>
	     </thead>
	   </table>
	</div>
	
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" 
	   aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" 
	               aria-hidden="true">x
	            </button>
	            <h4 class="modal-title" id="myModalLabel">
	              	Gnerate a new search
	            </h4>
	         </div>
	         <div class="modal-body">
                    <form class="bs-example bs-example-form" role="form" id="addNoteForm">
                        <div class="input-group">
                            <span class="input-group-addon">name</span>
                            <input type="text" class="form-control comp_sponsor" style="width: 200px" id="add_name"  maxlength="15" placeholder="name">
                        </div>
                        <br>
                        <div id="term_div">
	                        <div class="input-group">
	                            <span class="input-group-addon">term</span>
	                            <input type="text" class="form-control comp_sponsor add_term" style="width: 200px" maxlength="15" placeholder="term">
	                        	<div type="button" class="input-group-addon" onclick="addTerm(this)" style="border:solid 1px #ccc"><i class="glyphicon glyphicon-plus-sign" aria-hidden="true"></i></div>
	                       		<div type="button" class="input-group-addon" onclick="removeTerm(this)" style="display:none;border:solid 1px #ccc"><i class="glyphicon glyphicon-minus-sign" aria-hidden="true"></i></div>
	                        </div>
                        </div>
                        <br>
                        <br>
                        <div class="input-group">
                            <span class="input-group-addon">location</span>
                            <input type="text" class="form-control" id="add_location" style="width: 200px" maxlength="15" placeholder="location">
                        </div>
                        <br>
                        <br>
                        <div class="form-group">
			        		<div class="input-group date date col-md-3 " id="start_time" data-date="" data-date-format="yyyy-mm-dd hh:ii:ss" data-link-field="st_input" data-link-format="yyyy-mm-dd hh:ii:ss">
			                    <label for="st_input" class="input-group-addon control-label">Start_time</label>
			                    <input class="form-control" size="8" type="text" value="" readonly>
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                	</div>
		                	<input type="hidden" id="st_input" value="" /><br/>
		                	
		                	<div class="input-group date date col-md-3 " id="end_time" data-date="" data-date-format="yyyy-mm-dd hh:ii:ss" data-link-field="et_input" data-link-format="yyyy-mm-dd hh:ii:ss">
			                    <label for="et_input" class="input-group-addon control-label">End_time</label>
			                	<input class="form-control" size="8" type="text" value="" readonly>
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                	</div>
		                	<input type="hidden" id="et_input" value="" /><br/>
			        	</div>
                    </form>
                </div>
	         <div class="modal-footer">
	            <button type="button" class="btn btn-default" 
	               data-dismiss="modal">close
	            </button>
	            <button type="button" class="btn btn-primary" id='sendConfirm' data-dismiss="modal" data-toggle="modal" data-target="#confirmModal">
	              add
	            </button>
	         </div>
	      </div>
	   </div>
	</div>
	
	<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" 
	   aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content">
	         <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" 
	               aria-hidden="true">x
	            </button>
	            <h4 class="modal-title" id="myModalLabel">
	              	Confirmation
	            </h4>
	         </div>
	         <div class="modal-body">
	            No modifications are allowed after submitting!
	         </div>
	         <div class="modal-footer">
	            <button type="button" class="btn btn-default" 
	               data-dismiss="modal">Close
	            </button>
	            <button type="button" class="btn btn-primary" id='addSearchConfirm' data-dismiss="modal">
	               Confirm
	            </button>
	         </div>
	      </div><!-- /.modal-content -->
	   </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
</body>
</html>