<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>修改密码</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico">
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${ctx}/css/font-awesome.min.css" />
    <link rel="stylesheet" href="${ctx}/bjjoy/css/font-awesome.min.css" />
    <link href="${ctx}/bjjoy/css/animate.css" rel="stylesheet">
    <link href="${ctx}/bjjoy/css/style.css" rel="stylesheet">

	<script src="${ctx}/js/jquery-2.0.3.min.js"></script>
	<script src="${ctx}/js/ace-extra.min.js"></script>

	<link rel="stylesheet" href="${ctx}/css/mainpage.css">

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <form class="form-horizontal m-t" id="changeUserPassword">
                        	<input type="hidden" id="id" name="id" value="">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">初始密码：<label style="color:red">*</label></label>
                                <div class="col-sm-8">
                                    <input id="oldPwd" name="oldPwd" class="form-control" type="password" value="" />
                                </div>
                            </div>
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">输入新密码：<label style="color:red">*</label></label>
	                            <div class="col-sm-8">
	                                <input id="newPwd1" name="newPwd1" class="form-control" type="password"" value="" />
	                            </div>
	                        </div>
	                        
                            <div class="form-group">
                                <label class="col-sm-3 control-label">再次输入新密码：<label style="color:red">*</label></label>
                                <div class="col-sm-8">
                                    <input id="newPwd2" name="newPwd2" type="password" class="form-control" value="">
                                </div>
                            </div>                                                        
                            
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" onclick="userPasswordFormSubmit()" type="submit">提交</button>
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
    <script type="text/javascript">
    $(document).ready(function () {
	    $("#changeUserPassword").validate({
    	    rules: {
    	    	oldPwd: {
    	        required: true,
    	       	rangelength:[6,15]
    	      },
    	      	newPwd1: {
    	        required: true,
    	        rangelength:[6,15]
    	      },
    	      	newPwd2: {
    	        equalTo:"#newPwd1"
    	      }
    	    },
    	    messages: {
    	    },
    	    submitHandler:function(form){
    	    	console.log("submit");
    	    	$.ajax({
   	    		   type: "POST",
   	    		   dataType: "json",
   	    		   url: "${ctx}/user/changePwd",
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
