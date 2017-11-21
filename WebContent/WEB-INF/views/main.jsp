<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main</title>
    <c:set var="ctx" value="${pageContext.request.contextPath}" scope="application"></c:set>
    <link rel="stylesheet" href="${ctx}/resources/module/bootstrap.min.css" >
	<script src="${ctx}/resources/module/jquery-2.1.4.min.js"></script>
	<script src="${ctx}/resources/module/bootstrap.min.js"></script>
	
    <link rel="stylesheet" href="${ctx}/resources/css/index.css" >
    <link href="${ctx}/resources/module/bootstrap-table.css" rel="stylesheet">

	<script src="${ctx}/resources/js/home.js"></script>
    <script src="${ctx}/resources/module/bootstrap-table.js"></script>
</head>
<body>
<body style="background-color: #bad1e3">
<div class="container">
    <nav class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
            <a class="navbar-brand">ScavTweets</a>
            <a class="right navbar-brand " href="/">Logout</a>
            <p class="navbar-brand welcome">Welcome!</p>
            <p class="navbar-brand time"></p>
        </div>
    </nav>
    <div class="mynav">
        <p class="myp"></p>
        <p class="mylisthead">Functionalities</p>
        <a href="#" id="search" onclick="navClick(this.id)" style="text-decoration: none">Search</a>
        <a href="#" id="result" onclick="navClick(this.id)" style="text-decoration: none">Result</a>
    </div>


    <iframe class="myframe" name="myf" id="myf" src="${ctx}/resources/init.html"></iframe>

</div>

</body>
</body>
</html>