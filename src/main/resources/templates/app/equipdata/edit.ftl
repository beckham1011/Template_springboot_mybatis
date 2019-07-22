	<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>编辑实时数据</title>
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
                        	<input type="hidden" id="id" name="id" value="${data.dataId?c}">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">泵站名称：</label>
                                <div class="col-sm-8">
                                    <input id="name" name="name" class="form-control" type="text" value="${data.name}"  readonly="true" />
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">设备地址码：</label>
                                <div class="col-sm-8">
                                    <input id="addressCode" name="addressCode" class="form-control" type="text" readonly="true" value="${data.addresscode}" />
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">正累计流量：</label>
                                <div class="col-sm-8">
                                    <input id="netcumulative" name="netcumulative" class="form-control" type="text" value="${data.netcumulative?c}">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">在线状态：</label>
                                <div class="col-sm-8">
                                	<#if data.online == 0 >
                                    	<input type="checkbox" id="online" name="online">
                                    </#if>
                                	<#if data.online == 1 >
                                    	<input type="checkbox" id="online" name="online" checked="checked">
                                    </#if>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">更新时间：</label>
                                <div class="col-sm-8">
                                	<input id="addTime" name="addTime" type="date" class="laydate-icon form-control" value="${data.addTime}">
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
    	    	addressCode: {
    	        required: true,
    	        minlength: 4,
    	    	maxlength: 10
    	      },
    	      	name: {
    	        required: true,
    	        minlength: 2,
    	    	maxlength: 50
    	      }
    	    },
    	    messages: {},
    	    submitHandler:function(form){
    	    	$.ajax({
   	    		   type: "PUT",
   	    		   dataType: "json",
   	    		   url: "${ctx}/equipdata/update",
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
