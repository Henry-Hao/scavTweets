<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="${ctx}/resources/module/bootstrap.min.css" >
<script src="${ctx}/resources/module/jquery-2.1.4.min.js"></script>
<script src="${ctx}/resources/module/bootstrap.min.js"></script>

<link rel="stylesheet" href="${ctx}/resources/css/index.css" >
<script src="${ctx}/resources/js/index.js"></script>

<title>ScavTweets</title>
</head>

<%-- <c:if test="session.getAttribute('user') != null">
	<c:out session.getAttribute('user'); />
	<jsp:forward page="/WEB-INF/views/main.jsp" />
</c:if> --%>

<body>	
	 <div class="container" style="text-align: center">
        <div class="mycontainer">
            <div>
                <h1>ScavTweets</h1>
            </div>
            <div class="login-part">
                <div class="form-group" style="display: inline-block;">
                    <label class="col-sm-2 control-label">Username</label>
                    <div class="col-sm-8" style="margin-left: 30px;">
                        <input class="form-control" id="login_username" placeholder="Username">
                    </div>
                </div>
                <div class="form-group" style="display: inline-block;">
                    <label class="col-sm-2 control-label">Password</label>
                    <div class="col-sm-8" style="margin-left: 30px;">
                        <input type="password" class="form-control" id="login_password" placeholder="Password">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10" style="margin-left:30px">
                        <button type="submit" id="login" class="btn btn-default">Sign in</button>
                        <button type="submit" id="register" class="btn btn-default">Register</button>
                    </div>
                </div>
            </div>

            <div class="register-part" style="display: none">
                <div class="form-group" style="display: inline-block;">
                    <label class="col-sm-2 control-label">Username</label>
                    <div class="col-sm-8" style="margin-left: 30px;">
                        <input class="form-control" id="register_username" placeholder="Username">
                    </div>
                </div>
                <div class="form-group" style="display: inline-block;">
                    <label class="col-sm-2 control-label">Password</label>
                    <div class="col-sm-8" style="margin-left: 30px;">
                        <input type="password" class="form-control" id="register_password" placeholder="Password">
                    </div>
                </div>
                <div class="form-group" style="display: inline-block;">
                    <label class="col-sm-2 control-label">Confirm Password</label>
                    <div class="col-sm-8" style="margin-left: 30px;">
                        <input type="password" class="form-control" id="register_password_confirmed" placeholder="Password">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-10" style="margin-left:30px">
                        <button type="submit" id="register_submit" class="btn btn-default">Submit</button>
                        <button type="submit" id="back" class="btn btn-default">Back</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>