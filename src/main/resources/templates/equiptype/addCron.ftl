<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>添加定时刷新规则</title>
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
                        	<input type="hidden" id="id" name="id" value="${equiptype.id?c}">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">泵站名称：<label style="color:red">*</label></label>
                                <div class="col-sm-8">
                                    <input id="name" name="name" disable="disable" class="form-control" type="text" value="${equiptype.name}" />
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">设备地址码：<label style="color:red">*</label></label>
                                <div class="col-sm-8">
                                    <input id="addressCode" disable="disable" name="addressCode" class="form-control" type="text" disabled="disabled" value="${equiptype.addressCode}" />
                                </div>
                            </div>                            

                            <div class="form-group">
                                <label class="col-sm-3 control-label">表达式：</label>
                                <div class="col-sm-8">
                                    <input id="cron" name="cron" class="form-control" >
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
    	      	name: {
    	        required: true,
    	        minlength: 2,
    	    	maxlength: 50
    	      },
    	      	cron: {
    	        required: true,
    	        minlength: 12,
    	    	maxlength: 50
    	      }
    	    },
    	    messages: {},
    	    submitHandler:function(form){
    	    	$.ajax({
   	    		   type: "PUT",
   	    		   dataType: "json",
   	    		   url: "${ctx}/equiptype/update",
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
    
    function clickNode(event, data){
		$("#parentId").val(data['id'])
    }
    
    function typeSelect1Change(obj){
    	var parentId = $('#parentId2').val().replace(/\$|\,/g, '');
        
        $.ajax({
            type: "GET",
            dataType: "json",
            url: "${ctx}/equiptype/subTypelist/?parentId=" + parentId,
            success: function(msg){
                var typeSelect2Html = [];
                typeSelect2Html.push('<option value=-2>All--</option>');
                for(var i = 0; i < msg.data.subTypeList.length; i ++){
	                typeSelect2Html.push('<option value="' + msg.data.subTypeList[i].id  + '">' + msg.data.subTypeList[i].name + '</option>');
                }
				$('#parentId3').html(typeSelect2Html);
				$('#userListTable').bootstrapTable("refresh");
            }
        });
    }
 
    
</script>

</body>

</html>
