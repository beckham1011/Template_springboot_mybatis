    function clickNode(event, data){
		$("#parentId").val(data['id'])
		console.log("clickNode=========" + data['id'])
    	$('#equipListHistoryTable').bootstrapTable("refresh");
    }
    
    function itemOnclick (){
    	console.log("itemOnclick=========")
    }
    