<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>故障申报</title>
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
                        	<input type="hidden" id="id" name="id" value="${declare.id}">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">单位名称：<label style="color:red">*</label></label>
                                <div class="col-sm-8">
                                    <input id="unitName" name="unitName" class="form-control" type="text" value="${declare.unitName}" />
                                </div>
                            </div>
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">测点名称：<label style="color:red">*</label></label>
	                            <div class="col-sm-8">
	                            	<input id="measuringPoint" name="measuringPoint" class="form-control" type="text" value="${declare.measuringPoint}" />
	                            </div>
	                        </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">故障类型：<label style="color:red">*</label></label>
                                <div class="col-sm-8">
                                    <input id="failure" name="failure" class="form-control" type="text" value="${declare.failure}" />
                                </div>
                            </div>
                            
                            <div class="form-group">
	                            <label class="col-sm-3 control-label">申报时间：<label style="color:red">*</label></label>
	                            <div class="col-sm-8">
                                    <input id="reportingTime" name="reportingTime" class="form-control" type="text" value="${declare.reportingTime}" />
                                </div>
	                        </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">申报人：<label style="color:red">*</label></label>
                                <div class="col-sm-8">
                                    <input id="reportingPer" name="reportingPer" class="form-control" type="text" value="${declare.reportingPer}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">申报人电话：<label style="color:red">*</label></label>
                                <div class="col-sm-8">
                                    <input id="reportingPhone" name="reportingPhone" class="form-control" type="text" value="${declare.reportingPhone}">
                                </div>
                            </div>
                            <div class="form-group">
	                            <label class="col-sm-3 control-label">受理时间：<label style="color:red">*</label></label>
	                            <div class="col-sm-8">
	                                <input id="acceptTime" name="acceptTime" class="form-control" type="text" value="${declare.acceptTime}">
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">受理人：<label style="color:red">*</label></label>
	                            <div class="col-sm-8">
	                        		<input id="acceptPer" name="acceptPer" class="form-control" type="text" value="${declare.acceptPer}">
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">受理人电话：<label style="color:red">*</label></label>
	                            <div class="col-sm-8">
	                        		<input id="acceptPhone" name="acceptPhone" class="form-control" type="text" value="${declare.acceptPhone}">
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">维修结果：<label style="color:red">*</label></label>
	                            <div class="col-sm-8">
	                        		<input id="results" name="results" class="form-control" type="text" value="${declare.results}">
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
    	    	unitName: {
    	        required: true
    	      },
    	      	measuringPoint: {
    	        required: true
    	      },
    	      	failure: {
    	        required: true
    	      },
    	      	reportingTime: {
    	        required: true
    	      },
    	      	reportingPer: {
    	        required: true
    	      },
    	      	reportingPhone: {
    	        required: true
    	      },
    	      	acceptPer: {
    	        required: true
    	      },
    	      	acceptPhone: {
    	        required: true
    	      }
    	    },
    	    messages: {},
    	    submitHandler:function(form){
    	    	$.ajax({
   	    		   type: "POST",
   	    		   dataType: "json",
   	    		   url: "${ctx}/declarationrecord/update",
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
