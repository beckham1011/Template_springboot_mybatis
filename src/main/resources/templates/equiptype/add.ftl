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
                        	<input type="hidden" id="id" name="id" value="">
                            <div class="form-group">
                                <label class="col-sm-3 control-label">泵站名称：<label style="color:red">*</label></label>
                                <div class="col-sm-8">
                                    <input id="name" name="name" class="form-control" type="text" value="" />
                                </div>
                            </div>
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">设备地址码：<label style="color:red">*</label></label>
	                            <div class="col-sm-8">
	                                <input id="addressCode" name="addressCode" class="form-control" type="text" value="" />
	                            </div>
	                        </div>
	                        
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">SystemId：</label>
	                            <div class="col-sm-8">
	                                <select name="systemId" id="systemId" class="form-control col-sm-7"  style="width: 480px; float:left; height: 33px;" >
	                                	<#list systemList as stm>
	                                		<option value="${stm.id}"> ${stm.system} </option>
	                                	</#list>
	                                </select>
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
                                <label class="col-sm-3 control-label">泵型：</label>
                                <div class="col-sm-8">
                                    <input id="bengxing" name="bengxing" class="form-control" type="text" value="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">口径：</label>
                                <div class="col-sm-8">
                                    <input id="koujing" name="koujing" class="form-control" type="text" value="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">功率：</label>
                                <div class="col-sm-8">
                                    <input id="gonglv" name="gonglv" class="form-control" type="text" value="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">纬度：</label>
                                <div class="col-sm-8">
                                    <input id="latitude" name="latitude" class="form-control" value="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">经度：</label>
                                <div class="col-sm-8">
                                    <input id="longitude" name="longitude" class="form-control" value="" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">IP：</label>
                                <div class="col-sm-8">
                                    <input id="iP" name="iP" class="form-control" value="">
                                </div>
                            </div>
                            
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">镇水利站管理人员：</label>
	                            <div class="col-sm-8">
	                                <input id="zguanliPer" name="zguanliPer" class="form-control" value="" />
	                            </div>
	                        </div>
	                        <div class="form-group">
	                            <label class="col-sm-3 control-label">镇管理人员电话：</label>
	                            <div class="col-sm-8">
	                                <input id="zguanliPhone" name="zguanliPhone" class="form-control" value="">
	                            </div>
	                        </div>
                                                        
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">村管理人员:</label>
                                <div class="col-sm-8">
                                    <input id="cguanliPer" name="cguanliPer" class="form-control" value="" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">村管理人员电话：</label>
                                <div class="col-sm-8">
                                    <input id="cguanliPhone" name="cguanliPhone" class="form-control" value="">
                                </div>
                            </div>
                            
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">具体管理人员：</label>
                                <div class="col-sm-8">
                                    <input id="jguanliPer" name="jguanliPer" class="form-control" value="" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">具体管理人员电话：</label>
                                <div class="col-sm-8">
                                    <input id="jguanliPhone" name="jguanliPhone" class="form-control" value="">
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
    	    	addressCode: {
    	        required: true,
    	        minlength: 4,
    	    	maxlength: 10
    	      },
    	      	typeSelect1: {
    	        required: true
    	      },
    	      	name: {
    	        required: true,
    	        minlength: 2,
    	    	maxlength: 50
    	      }
    	    },
    	    messages: {},
    	    submitHandler:function(form){
    	    	$.ajax({
   	    		   type: "POST",
   	    		   dataType: "json",
   	    		   url: "${ctx}/equiptype/save",
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
    	var parentId = $('#typeSelect1').val().replace(/\$|\,/g, '');
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
				$('#typeSelect2').html(typeSelect2Html);
            }
        });
    }
    
    
</script>

</body>

</html>
