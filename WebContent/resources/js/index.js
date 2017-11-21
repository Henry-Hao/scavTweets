$(document).ready(function(){
    $("#register").click(function(){
        $(".login-part").hide();
        $(".register-part").show();
    });

    $("#back").click(function(){
        $(".login-part").show();
        $(".register-part").hide();
    });
    
    $("#login").click(function(){
    	var username = $("#login_username").val().trim();
    	var password = $("#login_password").val().trim();
    	if(username == "" || password == ""){
    		alert("All fileds must be filled");
    		$("#login_username").val("");
    		$("#login_password").val("");
    	} else {
    		$.ajax({
        		url:"Login",
        		method:"POST",
        		data:{
        			"Username":username ,
        			"Password":password
        			},
    			dataType:"json",
        		success:function(result){
        			if(result.result == "fail")
        				alert("Fail");
        			else if(result.result == "success"){
        				location.href = "Main"
        			}
        			
        		}
        	});
    	}
    	
    });
    
    $("#register_submit").click(function(){
    	var username = $("#register_username").val().trim();
    	var password = $("#register_password").val().trim();
    	var password_2 = $("#register_password_confirmed").val().trim();
    	if(username == "" || password == "" || password_2 == ""){
    		alert("All fileds must be filled");
    		$("#register_username").val("");
    		$("#register_password").val("");
    		$("#register_password_confirmed").val("");
    	}else if(password != password_2){
    		alert("Passwords don't match");
    		$("#register_username").val("");
    		$("#register_password").val("");
    		$("#register_password_confirmed").val("");
    	} else{
    		$.ajax({
        		url:"Registeration",
        		method:"POST",
        		data:{
        			"Username":username ,
        			"Password":password
        			},
    			dataType:"json",
        		success:function(result){
        			if(result.result == "duplicated")
        				alert("Duplicated Username!");
        			else if(result.result == "fail")
        				alert("Fail!");
        			else{
        				location.href = "Main"
        			}
        		}
        	});
    	}
    	
    });

});