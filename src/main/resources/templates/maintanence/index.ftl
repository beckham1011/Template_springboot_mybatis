<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>厚水智能-维修记录</title>
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

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>
                <#include "${ctx}/menu.ftl"/>
				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>
						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">控制台</a>
							</li>
							<li><a href="#">信息管理</a></li>
							<li class="active">维修记录</li>
						</ul><!-- .breadcrumb -->

					</div>

					<div class="page-content" >
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								
								<div class="row ">
                                    <div class="col-xs-12">
                                        <h3 class="header smaller lighter blue">维修记录</h3>
                                    </div>
								</div>
                                <div class="space-6"></div>
                                <div class="table-responsive">
                                    <table id="userListTable"></table>
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

		<script type="text/javascript">
            $(document).ready(function () {
                //初始化表格,动态从服务器加载数据
                $("#userListTable").bootstrapTable({
                    //使用get请求到服务器获取数据
                    method: "GET",
                    //必须设置，不然request.getParameter获取不到请求参数
                    contentType: "application/x-www-form-urlencoded",
                    //获取数据的Servlet地址
                    url: "${ctx}/maintainrecord/list",
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
                    detailView:false,
                    detailFormatter:detailFormatter,
                    //表示服务端请求
                    sidePagination: "server",
                    //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
                    //设置为limit可以获取limit, offset, search, sort, order
                    queryParams: getQueryParams,
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
                    "loginName":   $("#loginName").val()
                }
                return params;
            }
            
            function search() {
                var params = {"loginName":$("#loginName").val()};
                $('#userListTable').bootstrapTable("refresh");
            }
            
            function add(){
                layer.open({
                    type: 2,
                    title: '维修记录添加',
                    shadeClose: true,
                    shade: false,
                    area: ['800px', '600px'],
                    content: '${ctx}/maintainrecord/add',
                    end: function(index){
                        $('#userListTable').bootstrapTable("refresh");
                    }
                });
            }
                        
            function edit(id){
                layer.open({
                    type: 2,
                    title: '编辑维修记录',
                    shadeClose: true,
                    shade: false,
                    area: ['800px', '600px'],
                    content: '${ctx}/maintainrecord/edit?id='  + id,
                    end: function(index){
                        $('#userListTable').bootstrapTable("refresh");
                    }
                });
            }
            
            function del(id){
                layer.confirm('确定删除维修记录吗?', {icon: 3, title:'提示'}, function(index){
                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        url: "${ctx}/maintainrecord/delete?id=" + id,
                        success: function(msg){
                            layer.msg(msg.msg, {time: 1500},function(){
                                $('#userListTable').bootstrapTable("refresh");
                                layer.close(index);
                            });
                        }
                    });
                });
            }
		</script>
</body>
</html>
