<script src="${ctx}/js/bootstrap-treeview.js"></script>
<link href="/css/bootstrap.min.css" rel="stylesheet">

<div class="sidebar" id="sidebar">
	<style  type="text/css">
		li {
		    overflow: hidden;
		    text-overflow: ellipsis;
		    white-space: nowrap;
		}
	</style>
	<div id="treeview1" class=""></div>
    <script type="text/javascript">
        try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
    </script>
	<script>
	$(function() {
		var treeData ;
        $.ajax({
            type: "GET",
            dataType: "json",
            url: "${ctx}/equiptype/tree2",
            success: function(result){
                
                treeData = result.data.typeTree;
                var dataStr = JSON.stringify(treeData) ;
                
                $('#treeview1').treeview({
		          	showTags: false,
		          	levels: 1,
		          	enableLinks: true,
		          	data: JSON.parse(dataStr)
		        });
            }
        });
     
    })
	</script>

    <script type="text/javascript">
        try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
    </script>
</div>
