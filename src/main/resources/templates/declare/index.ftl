<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>厚水智能-申报记录</title>
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
				<div class="main-content" style="margin-left: 0px!important;">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>
						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="/equipdata/index">首页</a>
							</li>
							<li><a href="#">信息管理</a></li>
							<li class="active">申报记录</li>
						</ul><!-- .breadcrumb -->

					</div>

					<div class="page-content" >
						<div class="row">
							<div class="col-xs-12">
								<div class="row ">
                                    <div class="col-xs-12">
                                        <h3 class="header smaller lighter blue">申报记录</h3>
                                        <button class="btn btn-xs btn-success " type="button" onclick="add();"><i class="fa fa-plus"></i>&nbsp;添加申报记录</button><span style="color:#357ebd;font-size:9px;">双击显示维修记录列表</span>
                                        <button class="btn btn-xs btn-success " type="button" onclick="exportExcel();"><i class="fa "></i>&nbsp;导出</button>
                                    </div>
								</div>
                                <div class="space-6"></div>
                                <div class="table-responsive">
                                    <table id="declarerecode"></table>
                                </div>
                                <div class="table-responsive" id="declarerecode2response">
	                                <table id="declarerecode2"></table>
	                            </div>
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

			</div><!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

		<#include "${ctx}/common.ftl"/>

		<!-- inline scripts related to this page -->
		
		<div id="DeclarationRecordId" style="display:none;"></div>
		
		<script type="text/javascript">
            $(document).ready(function () {
                //初始化表格,动态从服务器加载数据
                $("#declarerecode").bootstrapTable({
                    //使用get请求到服务器获取数据
                    method: "GET",
                    //必须设置，不然request.getParameter获取不到请求参数
                    contentType: "application/x-www-form-urlencoded",
                    //获取数据的Servlet地址
                    url: "${ctx}/declarationrecord/list",
                    //表格显示条纹
                    striped: true,
                    //启动分页
                    pagination: true,
                    //每页显示的记录数
                    pageSize: 10,
                    //当前第几页
                    pageNumber: 1,
                    //记录数可选列表
                    pageList: [5, 10, 15, 20, 25],
                    //是否启用查询
                    search: false,
                    //是否启用详细信息视图
                    detailView:false,
                    detailFormatter:detailFormatter,
                    //表示服务端请求
                    sidePagination: "server",
                    //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
                    //设置为limit可以获取limit, offset, search, sort, order
                    queryParams: getQueryParams,
                    queryParamsType: "",
                    //双击显示维修详情
                    onDblClickRow : function (row){
		            	showMaintanenceDetails(row);
		            },
                    //json数据解析
                    responseHandler: function(res) {
                        return {
                            "rows": res.data.declares,
                            "total": res.data.count
                        };
                    },
                    //数据列
                    columns: [{
                        title: "序号",
                        field: "id",
                        sortable: true
                    },{
                        title: "单位名称",
                        field: "unitName"                   
                    },{
                        title: "测点名称",
                        field: "measuringPoint"                        
                    },{
                        title: "故障类型",
                        field: "failure"
                    },{
                        title: "申报时间",
                        field: "reportingTime"
                    },{
                        title: "申报人",
                        field: "reportingPer"
                    },{
                        title: "申报人电话",
                        field: "reportingPhone"
                    },{
                        title: "受理时间",
                        field: "acceptTime"
                    },{
                        title: "受理人",
                        field: "acceptPer"
                    },{
                        title: "受理人电话",
                        field: "acceptPhone"
                    },{
                        title: "维修结果",
                        field: "results"
                    },{
                        title: "操作",
                        field: "empty",
                        align : 'center',
						valign : 'middle',
                        formatter: function (value, row, index) {
                            var operateHtml = '<button class="btn btn-primary btn-xs" type="button" onclick="edit(\''+row.id+'\')"><i class="fa fa-edit"></i>&nbsp;编辑</button> &nbsp;';
                            operateHtml += '<button class="btn btn-danger btn-xs" type="button" onclick="del(\''+row.id+'\')"><i class="fa fa-remove"></i>&nbsp;删除</button>';
                            return operateHtml;
                        }
                    }]
                });
                $("#declarerecode2response").hide();
            });


			function showMaintanenceDetails(row){
				$("#declarerecode2response").show();
				console.log('unitName:' + row.id);
				var maintanenceListURL = "${ctx}/maintainrecord/list" ;
				$("#DeclarationRecordId").val(row.id);
				getQueryMaintanenceRecordParams.DeclarationRecordId = row.id ;
				$("#declarerecode2").bootstrapTable({
                    //使用get请求到服务器获取数据
                    method: "GET",
                    //必须设置，不然request.getParameter获取不到请求参数
                    contentType: "application/x-www-form-urlencoded",
                    //获取数据的Servlet地址
                    url: maintanenceListURL,
                    //url: "${ctx}/maintainrecord/list" ,
                    //表格显示条纹
                    striped: true,
                    //启动分页
                    pagination: true,
                    //每页显示的记录数
                    pageSize: 5,
                    //当前第几页
                    pageNumber: 1,
                    //记录数可选列表
                    pageList: [5, 10, 15, 20, 25],
                    //是否启用查询
                    search: false,
                    //是否启用详细信息视图
                    detailView:false,
                    detailFormatter:detailFormatter,
                    //表示服务端请求
                    sidePagination: "server",
                    //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
                    //设置为limit可以获取limit, offset, search, sort, order
                    queryParams: getQueryMaintanenceRecordParams,
                    queryParamsType: "",
                    //json数据解析
                    responseHandler: function(res) {
                        return {
                            "rows": res.data.records,
                            "total": res.data.count
                        };
                    },
                    //数据列
                    columns: [{
                        title: "序号",
                        field: "id",
                        sortable: true
                    },{
                        title: "维修开始时间",
                        field: "startTime"
                    },{
                        title: "维修终止时间",
                        field: "endTime"
                    },{
                        title: "维修方法",
                        field: "method"
                    },{
                        title: "维修人",
                        field: "maintainPer"
                    },{
                        title: "方法描述",
                        field: "description"
                    },{
                        title: "维修结果",
                        field: "results"
                    },{
                        title: "金额",
                        field: "money"                              
                    }]
                });
                $('#declarerecode2').bootstrapTable("refresh");
			}

            function detailFormatter(index, row) {
                var html = [];
                html.push('<p><b>描述:</b> ' + row.description + '</p>');
                return html.join('');
            }

            function getQueryMaintanenceRecordParams(params){
                var params={
                    "page":	params.pageNumber,
                    "rows":	params.pageSize,
                    "DeclarationRecordId" :$("#DeclarationRecordId").val()
                }
                return params;
            }

            function getQueryParams(params){
                var params={
                    "page": params.pageNumber,
                    "rows":	params.pageSize
                }
                return params;
            }
            function search() {
                var params = {"loginName":$("#loginName").val()};
                $('#declarerecode').bootstrapTable("refresh");
            }
            
            function add(){
                layer.open({
                    type: 2,
                    title: '故障申报',
                    shadeClose: true,
                    shade: false,
                    area: ['900px', '700px'],
                    content: '${ctx}/declarationrecord/add',
                    end: function(index){
                        $('#declarerecode').bootstrapTable("refresh");
                    }
                });
            }
            

            function edit(id){
                layer.open({
                    type: 2,
                    title: '故障申报变更',
                    shadeClose: true,
                    shade: false,
                    area: ['800px', '600px'],
                    content: '${ctx}/declarationrecord/edit?declareId='  + id,
                    end: function(index){
                        $('#declarerecode').bootstrapTable("refresh");
                        layer.close(index);
                    }
                });
            }
            
            
            function del(id){
                layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){
                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        url: '${ctx}/declarationrecord/delete?id='  + id,
                        success: function(msg){
                            layer.msg(msg.msg, {time: 1500},function(){
                                $('#declarerecode').bootstrapTable("refresh");
                                layer.close(index);
                            });
                        }
                    });
                });
            }

            function exportExcel(){
            	window.location.href = "${ctx}/declarationrecord/export" ;
            }
            
		</script>
</body>
</html>
