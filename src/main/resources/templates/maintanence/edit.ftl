<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>添加泵站</title>
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
                        <form class="form-horizontal m-t" id="maintanenceForm">
	                    	<input type="hidden" id="id" name="id" value="${maintanence.id}">
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">单位名称：<label style="color:red">*</label></label>
	                            <div class="col-sm-8">
	                                <input id="name" name="name" class="form-control" type="text" value="${maintanence.name}" />
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">测点名称：<label style="color:red">*</label></label>
	                            <div class="col-sm-8">
	                                <input id="bengxing" name="bengxing" class="form-control" type="text" value="${maintanence.bengxing}" />
	                            </div>
	                        </div>
                            
                            <div class="form-group">
	                            <label class="col-sm-3 control-label">维修开始时间：</label>
								<div class="col-sm-8">
                                    <input id="startTime" name="startTime" class="form-control" type="text" value="${maintanence.startTime}" />
                                </div>
	                        </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">维修终止时间：<label style="color:red">*</label></label>
                                <div class="col-sm-8">
                                    <input id="endTime" name="endTime" class="form-control" type="text" value="${maintanence.endTime}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">维修方法：<label style="color:red">*</label></label>
                                <div class="col-sm-8">
                                    <input id="method" name="method" class="form-control" type="text" value="${maintanence.method}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">维修人：<label style="color:red">*</label></label>
                                <div class="col-sm-8">
                                    <input id="maintainPer" name="maintainPer" class="form-control" type="text" value="${maintanence.maintainPer}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">方法描述：</label>
                                <div class="col-sm-8">
                                    <input id="description" name="description" class="form-control" value="${maintanence.description}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">维修结果：</label>
                                <div class="col-sm-8">
                                    <input id="results" name="results" class="form-control" value="${maintanence.results}" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">金额：</label>
                                <div class="col-sm-8">
                                    <input id="money" name="money" class="form-control" value="${maintanence.money}">
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

	    $("#maintanenceForm").validate({
    	    rules: {
    	    	name: {
    	        required: true,
    	        minlength: 4,
    	    	maxlength: 10
    	      },
    	      	bengxing: {
    	        required: true
    	      },
    	      	startTime: {
    	        required: true,
    	        minlength: 4,
    	    	maxlength: 50
    	      },
    	      	endTime: {
    	        required: true
    	      },
    	      	method: {
    	        required: true
    	      },
    	      	maintainPer: {
    	        required: true
    	      },
    	      	description: {
    	        required: true
    	      },
    	      	results: {
    	        required: true
    	      },
    	      	money: {
    	        required: true
    	      }
    	    },
    	    messages: {},
    	    submitHandler:function(form){
    	    	$.ajax({
   	    		   type: "POST",
   	    		   dataType: "json",
   	    		   url: "${ctx}/maintainrecord/update",
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
