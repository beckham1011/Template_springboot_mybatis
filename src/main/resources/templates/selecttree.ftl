<script src="${ctx}/js/bootstrap-treeview.js"></script>
<link href="${ctx}/css/bootstrap.min.css" rel="stylesheet">

<div class="sidebar" id="sidebar">
	<style  type="text/css">
		.node-treeview1 {
			overflow: hidden; text-overflow:ellipsis; white-space: nowrap;
		}
		.node-treeview1 a{
			width:40px;
		}
	</style>
	<div id="treeview1" class=""></div>
	<script type="text/javascript">
		$(function() {
			var treeData ;
	        $.ajax({
	            type: "GET",
	            dataType: "json",
	            url: "${ctx}/equiptype/tree",
	            success: function(result){
	                
	                treeData = result.data.typeTree;
	                
	                $('#treeview1').treeview({
			          	showTags: false,
			          	levels: 1,
			          	data: treeData,
			          	onNodeSelected: function(event, data){
			          		clickNode(event, data);
			          	}
			        });
	            }
	        });
	        
	    })
	    
        function itemOnclick (){}
	    
	</script>

</div>
