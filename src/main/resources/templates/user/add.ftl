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
                                    <input id="password" name="password" class="form-control" type="text" value="" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">昵称：</label>
                                <div class="col-sm-8">
                                    <input id="name" name="name" class="form-control" type="text" value="">
                                </div>
                            </div>
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">管辖泵站区域：</label>
	                            <div class="col-sm-8">
	                                <select name="typeSelect0" id="typeSelect0" class="form-control"  style="width:160px;float:left;height:auto;">
	                            		<option value="${currentOrg.id}" >${currentOrg.name}</option>
	                                </select>
	                                <#if currentOrg.typeLayer lt 3>
		                                <select id="typeSelect1" name="typeSelect1" class="form-control" style="width:140px;float:left;height:auto;" onChange="typeSelect1Change(this)">
		                                	<option value="-1" >请选镇级泵站</option>
		                                    <#list subTypeList1 as type>
		                                        <option value="${type.id}" >${type.name}</option>
		                                    </#list>
		                                </select>
		                            </#if>
	                                <#if currentOrg.typeLayer lt 2>
		                                <select name="typeSelect2" id="typeSelect2" class="form-control"  style="width:140px;float:left;height:auto;" >
		                            		<option value="-2" >请选择---</option>
		                                </select>
	                                </#if>
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
                                <label class="col-sm-3 control-label">创建日期：</label>
                                <div class="col-sm-8">
                                    <input id="createDate" name="createDate" readonly="readonly"
                                           class="laydate-icon form-control"
                                           value="">
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
	    laydate({
	        elem: '#createDate', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
	        event: 'focus', //响应事件。如果没有传入event，则按照默认的click
            format: 'YYYY-MM-DD hh:mm:ss',
            istime: true
	    });

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
    	      	createDate: {
    	      	date:true,
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
    	        required: false,
    	        maxlength: 40
    	      }
    	    },
    	    messages: {},
    	    submitHandler:function(form){
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
    
    
    </script>

</body>

</html>
