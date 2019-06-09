<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>厚水智能-泵站管理</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<!-- basic styles -->

		<link href="${ctx}/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${ctx}/css/font-awesome.min.css" />
        <link rel="stylesheet" href="${ctx}/bjjoy/css/font-awesome.min.css" />
		<!-- page specific plugin styles -->

		<!-- ace styles -->
		<link rel="stylesheet" href="${ctx}/css/ace.min.css" />
		<link rel="stylesheet" href="${ctx}/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${ctx}/css/ace-skins.min.css" />
		<link rel="stylesheet" href="${ctx}/css/equiptype.css" />

		<script src="${ctx}/js/ace-extra.min.js"></script>
	</head>

	<body>
        <#include "${ctx}/head.ftl" />
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<#include "${ctx}/head_nav.ftl" />
			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>
				<#include "${ctx}/menu.ftl"/>
				<div class="main-content maincontentwidth" >
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>
						<ul class="breadcrumb">
							<li><a href="/equipdata/index">首页</a></li>
							<li><a href="#">用户管理</a></li>
							<li class="active">泵站管理</li>
						</ul><!-- .breadcrumb -->

					</div>

					<div class="page-content" >
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="row ">
                                    <div class="col-xs-12">
                                        <h3 class="header smaller lighter blue">泵站管理</h3>
                                    	<input id="stationName" placeholder="请输入泵站名称" name="stationName" type="text"/>
                                        <button class="btn btn-xs btn-primary" onclick="search();"><i class="fa fa-search"></i>&nbsp;查询</button>
                                        <@shiro.hasPermission name="system:equip:add">
	                                        <button class="btn btn-xs btn-success " type="button" onclick="add();"><i class="fa fa-plus"></i>&nbsp;添加泵站</button>
											<button class="btn btn-xs btn-success " type="button" onclick="importFile();"><i class="fa fa-plus"></i>&nbsp;批量导入泵站</button>
										</@shiro.hasPermission>
                                    </div>
                                    
								</div>
                                <div class="space-6"></div>
	                            <div class="table-responsive table-bordered">
	                                <table id="equiptypelist"></table>
	                            </div>

							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

			</div><!-- /.main-container-inner -->

		</div><!-- /.main-container -->

		<#include "${ctx}/common.ftl"/>

		<!-- inline scripts related to this page -->

		<script type="text/javascript">
            $(document).ready(function () {
            	
            	localStorage.setItem("parentId", ${parentId});
            
                //初始化表格,动态从服务器加载数据
                $("#equiptypelist").bootstrapTable({
                    //使用get请求到服务器获取数据
                    method: "GET",
                    //必须设置，不然request.getParameter获取不到请求参数
                    contentType: "application/x-www-form-urlencoded",
                    //获取数据的Servlet地址
                    url: "${ctx}/equiptype/list",
                    //表格显示条纹
                    striped: true,
                    //启动分页
                    pagination: true,
                    //每页显示的记录数
                    pageSize: 20,
                    //当前第几页
                    pageNumber: 1,
                    //记录数可选列表
                    pageList: [5, 10, 15, 20, 25],
                    //是否启用查询
                    search: false,
                    //是否启用详细信息视图
                    detailView: false,
                    detailFormatter: detailFormatter,
                    //表示服务端请求
                    sidePagination: "server",
                    //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
                    //设置为limit可以获取limit, offset, search, sort, order
                    queryParams: getQueryParams,
                    queryParamsType: "",
                    //json数据解析
                    responseHandler: function(res) {
                        return {
                            "rows": res.data.typeList,
                            "total": res.data.count
                        };
                    },
                    //数据列
                    columns: [[{
                        title: "序号",
                        field: "id",
                        sortable: true,
                        align : 'center',
						valign : 'middle',                        
						colspan: 1,
						rowspan: 2
                    },{
                        title: "泵站名称",
                        field: "name",
                        align : 'center',
						valign : 'middle',                        
						colspan: 1,
						rowspan: 2
                    },{
                        title: "设备地址码",
                        field: "addressCode",
                        align : 'center',
						valign : 'middle',                        
						colspan: 1,
						rowspan: 2
                    },{
                        title: "泵站信息",
                        align : 'center',
						colspan: 3,
						rowspan: 1
                    },{
                        title: "纬度",
                        field: "latitude",
                        align : 'center',
						valign : 'middle',                        
						colspan: 1,
						rowspan: 2
                    },{
                        title: "经度",
                        field: "longitude",
                        align : 'center',
						valign : 'middle',                        
						colspan: 1,
						rowspan: 2
                    },{
                        title: "IP",
                        field: "iP",
                        align : 'center',
						valign : 'middle',
						colspan: 1,
						rowspan: 2
                    },{
                        title: "镇水利站管理人员",
                        field: "zguanliPer",
                        align : 'center',
						valign : 'middle',                        
						colspan: 1,
						rowspan: 2
                    },{
                        title: "镇管理人员电话",
                        field: "zguanliPhone",
                        align : 'center',
						valign : 'middle',                        
						colspan: 1,
						rowspan: 2
                    },{
                        title: "村管理人员",
                        field: "cguanliPer",
                        align : 'center',
						valign : 'middle',
						colspan: 1,
						rowspan: 2
                    },{
                        title: "村管理人员电话",
                        field: "cguanliPhone",
                        align : 'center',
						valign : 'middle',
						colspan: 1,
						rowspan: 2
                    },{
                        title: "具体管理人员",
                        field: "jguanliPer",
                        align : 'center',
						valign : 'middle',
						colspan: 1,
						rowspan: 2
                    },{
                        title: "具体管理人员电话",
                        field: "jguanliPhone",
                        align : 'center',
						valign : 'middle',
						colspan: 1,
						rowspan: 2
                    },{
                        title: "操作",
                        field: "empty",
                        align : 'center',
						valign : 'middle',
						colspan: 1,
						rowspan: 2,
                        formatter: function (value, row, index) {
                            var operateHtml = '<button class="btn btn-primary btn-xs" type="button" onclick="editEquiptype(\''+row.id+'\')"><i class="fa fa-edit"></i>&nbsp;编辑</button>&nbsp;';
							operateHtml += '<button class="btn btn-primary btn-xs" type="button" onclick="addCron(\''+row.id+'\')"><i class="fa fa-add"></i>&nbsp;添加刷新规则</button>&nbsp;';
                            operateHtml += '<button class="btn btn-danger btn-xs" type="button" onclick="deleteEquiptype(\''+row.id+'\')"><i class="fa fa-remove"></i>&nbsp;删除</button>';
                            return operateHtml;
                        }
                    }],[{
							title: "泵型",
							field: "bengxing"
						},{
							title: "口径",
							field: "koujing"
						},{
							title: "功率",
							field: "gonglv"
						}
                    ]]
                });
            });

            function detailFormatter(index, row) {
                var html = [];
                html.push('<p><b>描述:</b> ' + row.description + '</p>');
                return html.join('');
            }

            function getQueryParams(params){
                var params={
                    "page":		   params.pageNumber,
                    "rows":		   params.pageSize,
                    "stationName": $("#stationName").val().trim(),
                    "parentId"   : localStorage.getItem("parentId")
                }
                if( !($("#online").is(':checked') == $("#offline").is(':checked'))){
                	params.waterStatus = $("#online").is(':checked') ? 1 : ( $("#offline").is(':checked') ? 0 : 1)
                }
                return params;
            }
            
            function search() {
                $('#equiptypelist').bootstrapTable("refresh");
            }
            
            function add(){
                layer.open({
                    type: 2,
                    title: '添加泵站',
                    shadeClose: true,
                    shade: false,
                    area: ['900px', '600px'],
                    content: '${ctx}/equiptype/add',
                    end: function(index){
                        layer.close(index);
                        $('#equiptypelist').bootstrapTable("refresh");
                    }
                });
            }
            
            function importFile(){
                layer.open({
                    type: 2,
                    title: '批量导入泵站',
                    shadeClose: true,
                    shade: false,
                    area: ['900px', '260px'],
                    content: '${ctx}/equiptype/importFile'
                });
            }
            
            function editEquiptype(id){
                layer.open({
                    type: 2,
                    title: '编辑泵站',
                    shadeClose: true,
                    shade: false,
                    area: ['900px', '600px'],
                    content: '${ctx}/equiptype/edit?id=' + id,
                    end: function(index){
                    	layer.close(index);
                        $('#equiptypelist').bootstrapTable("refresh");
                    }
                });
            }


			function addCron(id){
				layer.open({
                    type: 2,
                    title: '添加定时刷新规则',
                    shadeClose: true,
                    shade: false,
                    area: ['900px', '600px'],
                    content: '${ctx}/equiptype/addCron?id=' + id,
                    end: function(index){
                    	layer.close(index);
                        $('#equiptypelist').bootstrapTable("refresh");
                    }
                });
			}

            function deleteEquiptype(id){
        		var subTypesHtml = '' ;
        		var subEquipList = [];
                $.ajax({
                    type: "get",
                    dataType: "json",
                    url: '${ctx}/equiptype/subTypelist?parentId=' + id,
                    success: function(msg){
                    	subEquipList = msg.data.subTypeList;
                    	
                    	var alertMsg = '确定删除吗?';
		            	if(subEquipList.length > 0)
		            		alertMsg = "有"+subEquipList.length+ "个下级泵站也将删除，" + alertMsg;
		                layer.confirm(alertMsg, {icon: 3, title:'提示'}, function(index){
		                    $.ajax({
		                        type: "get",
		                        dataType: "json",
		                        url: '${ctx}/equiptype/delete?id=' + id,
		                        success: function(msg){
		                            layer.msg(msg.msg, {time: 1500},function(){
		                                $('#equiptypelist').bootstrapTable("refresh");
		                                layer.close(index);
		                            });
		                        }
		                    });
		                });
                    	
                   	}
                });
            	
            	
            }
            
            function itemOnclick (){}
            
            function clickNode(event, data){
	        	console.log(  ) ;
	        	localStorage.setItem("parentId",data['id']);
	        	$('#equiptypelist').bootstrapTable("refresh");
	        }
            
		</script>
		<script src="${ctx}/js/md5.js"></script>
</body>
</html>
