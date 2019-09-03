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
                        <form class="form-horizontal m-t" id="userForm">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">旧密码：<label style="color:red">*</label></label>
                                <div class="col-sm-8">
                                    <input id="oldpwd" name="oldpwd" class="form-control" type="password" value="" />
                                </div>
                            </div>
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">输入新密码：<label style="color:red">*</label></label>
	                            <div class="col-sm-8">
	                                <input id="newPwd1" name="newPwd1" class="form-control" type="password" value="" />
	                            </div>
	                        </div>
	                        
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">再输入新密码：<label style="color:red">*</label></label>
	                            <div class="col-sm-8">
	                                <input id="newPwd2" name="newPwd2" class="form-control" type="password" value="" />
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
    	    	oldpwd: {
    	        required: true,
    	        minlength: 4,
    	    	maxlength: 20
    	      },
    	      	newPwd1: {
    	        required: true,
    	        minlength: 6,
    	    	maxlength: 20
    	      },
    	      	newPwd2: {
    	      	required: true,
    	        minlength: 6,
    	    	maxlength: 20
    	      }
    	    },
    	    messages: {},
    	    submitHandler:function(form){
    	    	$.ajax({
   	    		   type: "POST",
   	    		   dataType: "json",
   	    		   url: "${ctx}/user/changePwd",
   	    		   data: $(form).serialize(),
   	    		   success: function(msg){
   	    		   		if(200 == msg.code){
   	    		   			window.location.href="index";
   	    		   		}else{
   	    		   			alert("更改密码失败");
   	    		   		}
   	    		   }
   	    		});
            }
    	});
    });
    
</script>

</body>

</html>
