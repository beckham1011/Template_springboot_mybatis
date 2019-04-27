<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>添加用户</title>
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
                                <label class="col-sm-3 control-label">用户名：</label>
                                <div class="col-sm-8">
                                    <input id="loginName" name="loginName" class="form-control" type="text" value="" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">密码：</label>
                                <div class="col-sm-8">
                                    <input id="password" name="password" class="form-control" type="password" value="" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">昵称：</label>
                                <div class="col-sm-8">
                                    <input id="name" name="name" class="form-control" type="text" value="">
                                </div>
                            </div>
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">所属组织：</label>
	                            <div class="col-sm-8">
	                            	<input id="parentId" name="parentId" class="form-control" type="text" value="" style="display:none;">
									<#include "${ctx}/selecttree.ftl"/>
	                            </div>
	                        </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">状态：</label>
                                <div class="col-sm-8">
                                	<select name="state" class="form-control">
                                		<option value="0">否</option>
                                		<option value="1" selected="selected">是</option>
                                	</select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">电话：</label>
                                <div class="col-sm-8">
                                    <input id="phone" name="phone" class="form-control" value="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">E-mail：</label>
                                <div class="col-sm-8">
                                    <input id="email" name="email" class="form-control" value="" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">描述：</label>
                                <div class="col-sm-8">
                                    <input id="remarks" name="remarks" class="form-control" value="">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit">提交</button>
                                    <button class="btn btn-secondary" onclick="userCancel()">取消</button>
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
	    $("#userForm").validate({
    	    rules: {
    	    	loginName: {
    	        required: true,
    	        minlength: 4,
    	    	maxlength: 10
    	      },
    	      	name: {
    	        required: true,
    	        minlength: 4,
    	    	maxlength: 10
    	      },
    	      	state: {
    	        required: true
    	      },
    	      	phone: {
    	        required: true
    	      },
    	      	email: {
    	      	email:true,
    	        required: true
    	      },
    	      	address: {
    	        required: true,
    	        maxlength: 40
    	      },
                remarks: {
    	        maxlength: 40
    	      }
    	    },
    	    messages: {},
    	    submitHandler:function(form){
    	    	console.log("submit");
    	    	$.ajax({
   	    		   type: "POST",
   	    		   dataType: "json",
   	    		   url: "${ctx}/user/insert",
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
    
    function userCancel(){
    	layer.close(index);
    }
    
    function typeSelect1Change(obj){
		var parentId = $('#typeSelect1').val().replace(/\$|\,/g, '');
	    $.ajax({
	        type: "GET",
	        dataType: "json",
	        url: "${ctx}/equiptype/subTypelist/?parentId=" + parentId,
	        success: function(msg){
	            var typeSelect2Html = [];
	            typeSelect2Html.push('<option value=-2>请选村级泵站</option>');
	            for(var i = 0; i < msg.data.subTypeList.length; i ++){
	                typeSelect2Html.push('<option value="' + msg.data.subTypeList[i].id  + '">' + msg.data.subTypeList[i].name + '</option>');
	            }
				$('#typeSelect2').html(typeSelect2Html);
	        }
	    });
    }
    
    function clickNode(event, data){
		$("#parentId").val(data['id'])
		console.log("clickNode=========" + data['id'])
    	$('#equipListHistoryTable').bootstrapTable("refresh");
    }
    
    function itemOnclick (){
    	console.log("itemOnclick=========")
    }
    </script>

</body>

</html>
