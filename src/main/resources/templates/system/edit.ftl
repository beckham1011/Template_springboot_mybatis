<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>编辑平台</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico">
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${ctx}/css/font-awesome.min.css" />
    <link rel="stylesheet" href="${ctx}/bjjoy/css/font-awesome.min.css" />
    <link href="${ctx}/bjjoy/css/animate.css" rel="stylesheet">
    <link href="${ctx}/bjjoy/css/style.css" rel="stylesheet">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="userForm">
                        	<input type="hidden" id="id" name="id" value="${currentSystem.id?c}">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">平台名称：<label style="color:red">*</label></label>
                                <div class="col-sm-8">
                                    <input id="system" name="system" class="form-control" type="text" value="${currentSystem.system}" />
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit">提交</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!-- 全局js -->
<#include "${ctx}/common.ftl">
<script type="text/javascript">
    $(document).ready(function () {
	  	//外部js调用

	    $("#userForm").validate({
    	    rules: {
    	    	system: {
    	        required: true
    	      }
    	    },
    	    messages: {},
    	    submitHandler:function(form){
    	    	console.log("edit system submit") ;
    	    	$.ajax({
   	    		   type: "PUT",
   	    		   dataType: "json",
   	    		   url: "${ctx}/system/update",
   	    		   data: $(form).serialize(),
   	    		   success: function(msg){
	   	    			layer.msg(msg.msg, {time: 2000},function(){
	   						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	   						parent.layer.close(index);
	   					});
   	    		   }
   	    		});
            }
    	});
    });
    
    
</script>

</body>

</html>
