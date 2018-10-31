<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>添加数据卡</title>
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
                            <div class="form-group">
                                <label class="col-sm-3 control-label">单位名称：<label style="color:red">*</label></label>
                                <div class="col-sm-8">
                                    <input id="unitName" name="unitName" class="form-control" type="text" value="" />
                                </div>
                            </div>
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">资费标准：<label style="color:red">*</label></label>
	                            <div class="col-sm-8">
	                                <select name="costStandard" id="costStandard" class="form-control" style="width:160px;float:left;height:auto;">
	                                	<#list costStandards as item >
	                            			<option value="${item.id}" >${item.costStandard}</option>
	                                	</#list>
	                                </select>
	                            </div>
	                        </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">设备名称：<label style="color:red">*</label></label>
                                <div class="col-sm-8">
                                    <input id="equipName" name="equipName" class="form-control" type="text" value="" />
                                </div>
                            </div>
                            
                            <div class="form-group">
	                            <label class="col-sm-3 control-label">手机卡号：<label style="color:red">*</label></label>
	                            <div class="col-sm-8">
                                    <input id="mobilePhone" name="mobilePhone" class="form-control" type="text" value="" />
                                </div>
	                        </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">充值金额：<label style="color:red">*</label></label>
                                <div class="col-sm-8">
                                    <input id="amount" name="amount" class="form-control" type="text" value="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">充值时间：<label style="color:red">*</label></label>
                                <div class="col-sm-8">
                                    <input id="refillTime" name="refillTime" class="form-control" type="text" value="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">当前余额：<label style="color:red">*</label></label>
                                <div class="col-sm-8">
                                    <input id="balance" name="balance" class="form-control" type="text" value="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">当前状态：<label style="color:red">*</label></label>
                                 <div class="col-sm-8">
	                                <select name="currentState" id="currentState" class="form-control"  style="width:160px;float:left;height:auto;">
	                            		<option value="1" >正常</option>
	                            		<option value="2" >欠费</option>
	                                </select>
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
    	      	equipName: {
    	        required: true
    	      },
    	      	mobilePhone: {
    	        required: true
    	      },
    	      	costStandard: {
    	        required: true
    	      },
    	      	amount: {
    	        required: true
    	      },
    	      	refillTime: {
    	        required: true
    	      },
    	      	balance: {
    	        required: true
    	      },
    	      	currentState: {
    	        required: true
    	      }
    	    },
    	    messages: {},
    	    submitHandler:function(form){
    	    	$.ajax({
   	    		   type: "POST",
   	    		   dataType: "json",
   	    		   url: "${ctx}/datacard/save",
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
