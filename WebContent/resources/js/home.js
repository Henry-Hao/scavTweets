$(document).ready(function(){
	
});

function navClick(id){
		switch(id){
			case "search":
				$(".myframe").attr("src","router?search");
				$(".myframe").css("height","630px");
				break;
			case "result":
				$(".myframe").attr("src","router?result");
				$(".myframe").css("height","630px");
				break;
			default:
				break;
		}
	}