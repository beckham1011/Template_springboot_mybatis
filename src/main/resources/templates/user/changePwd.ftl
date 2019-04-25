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
                        <form class="form-horizontal m-t" id="userPasswordForm">
                        	<input type="hidden" id="id" name="id" value="">
                        	<input type="hidden" id="oldpwd" name="id" value="${password}">
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
<script src="${ctx}/js/md5.js" />
<script type="text/javascript">
	function userPasswordFormSubmit(){
		console.log('userPasswordFormSubmit');
		var oldCurrentPassword = '1231231' ;
		var oldInputPassword = $.md5(value);
		
		if(oldCurrentPassword != oldInputPassword){
			alert("请正确填写您的旧密码!");
			return ;
		}
		
		var newPwd1 = $("#newPwd1") ;
		var newPwd2 = $("#newPwd2") ;
		if(newPwd1.lengt < 6 || newPwd2 < 6){
			alert("新密码长度请大于6位!");
			return ;
		}
		if(newPwd1 != newPwd2){
			alert("请保持两次新密码一致!");
			return ;
		}
		var newPwdAfterMd5 = $.md5(newPwd1) ;
		if(newPwdAfterMd5 == oldCurrentPassword){
			alert("新密码不能和老密码一样!");
			return ;
		}
		
	}
</script>
</body>

</html>
