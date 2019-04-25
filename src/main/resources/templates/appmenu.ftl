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
	<script type="text/javascript">
	$(function() {
		var treeData ;
        $.ajax({
            type: "GET",
            dataType: "json",
            url: "${ctx}/equiptype/tree2",
            success: function(result){
                
                console.log("click tree")
                
                treeData = result.data.typeTree;
                
                $('#treeview1').treeview({
		          	showTags: true,
		          	levels: 2,
		          	data: treeData,
		          	onNodeSelected: function(event, data){
		          		clickNode(event, data);
		          	}
		        });
            }
        });
        
    })
	</script>

</div>
