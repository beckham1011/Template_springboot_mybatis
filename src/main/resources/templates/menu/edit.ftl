<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>添加菜单/按钮</title>
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
                        <form class="form-horizontal m-t" id="menuForm">
                            <input type="hidden" id="id" name="id" value="${menu.id}">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">上级菜单/按钮：</label>
                                <div class="col-sm-8">
                                    <select name="parentId" class="form-control">
                                        <#list list as item>
                                            <option value="${item.id}" <#if item.id == menu.parentId>selected="selected"</#if>>
                                                <#if item.type == 0>&nbsp;&nbsp;|-<#elseif item.type == 1>&nbsp;&nbsp;&nbsp;&nbsp;|-<#else>|-</#if>${item.name}
                                            </option>
                                        </#list>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">名称：</label>
                                <div class="col-sm-8">
                                    <input id="name" name="name" class="form-control" type="text" value="${menu.name}" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">排序：</label>
                                <div class="col-sm-8">
                                    <input id="sort" name="sort" class="form-control" type="text" value="${menu.sort?c}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">菜单/按钮url：</label>
                                <div class="col-sm-8">
                                    <input id="href" name="href" class="form-control" type="text" value="${menu.href}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">类型：</label>
                                <div class="col-sm-8">
                                    <select id="type" name="type" class="form-control">
                                        <option value="0" <#if menu.type == 0>selected="selected"</#if>>目录</option>
                                        <option value="1" <#if menu.type == 1>selected="selected"</#if>>菜单</option>
                                        <option value="2" <#if menu.type == 2>selected="selected"</#if>>按钮</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">权限标识：</label>
                                <div class="col-sm-8">
                                    <input id="permission" name="permission" class="form-control" value="${menu.permission}">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">备注：</label>
                                <div class="col-sm-8">
                                    <input id="remarks" name="remarks" class="form-control" value="${menu.remarks}" />
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

	    $("#menuForm").validate({
    	    rules: {
    	    	parentId: {
    	        required: true
    	      },
    	      	name: {
    	        required: true
    	      },
                sort: {
    	        required: false
    	      },
                href: {
    	        required: false
    	      },
                type: {
    	        required: true
    	      },
                permission: {
    	        required: true
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
   	    		   url: "${ctx}/menu/update",
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
