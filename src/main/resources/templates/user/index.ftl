<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>厚水智能-用户管理</title>
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

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="${ctx}/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- ace settings handler -->

		<script src="${ctx}/js/ace-extra.min.js"></script>

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="${ctx}/js/html5shiv.js"></script>
		<script src="${ctx}/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body>
        <#include "${ctx}/head.ftl" />
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<#include "${ctx}/head_nav.ftl" />
			<div class="main-container-inner">
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
							<li><a href="#">用户中心</a></li>
							<li class="active">用户管理</li>
						</ul><!-- .breadcrumb -->
					</div>

					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<div class="row ">
                                    <div class="col-xs-12">
                                    	<h3 class="header smaller lighter blue">用户管理</h3>
                                    	<div class="form-group">
                                    		<input id="loginName" placeholder="请输入用户名" name="loginName" type="text"/>
                                        	<button class="btn btn-xs btn-primary" onclick="search();"><i class="fa fa-search"></i>&nbsp;查询</button>
                                            <button class="btn btn-xs btn-success " type="button" onclick="add();"><i class="fa fa-plus"></i>&nbsp;添加</button>
                                        </div>
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
                    url: "${ctx}/user/getList",
                    //表格显示条纹
                    striped: true,
                    //启动分页
                    pagination: false,
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
                    //json数据解析
                    responseHandler: function(res) {
                        return {
                            "rows": res.data.userList,
                            "total": res.data.count
                        };
                    },
                    //数据列
                    columns: [{
                        title: "序号",
                        field: "id",
                        sortable: true
                    },{
                        title: "用户名",
                        field: "name"
                    },{
                        title: "账号",
                        field: "loginName"
                    },{
                        title: "角色列表",
                        field: "roleList",
                        formatter: function(value, row, index) {
                            var r = "";
                            $(value).each(function (index,role){
                                r = r + "【" + role.name + "】";
                            });
                            return r;
                        }
                    },{
                        title: "电话",
                        field: "phone"
                    },{
                        title: "邮箱",
                        field: "email"
                    },{
                        title: "操作",
                        field: "empty",
                        formatter: function (value, row, index) {
                            var operateHtml = '<@shiro.hasPermission name="system:user:edit"><button class="btn btn-primary btn-xs" type="button" onclick="edit(\''+row.id+'\')"><i class="fa fa-edit"></i>&nbsp;编辑用户</button> &nbsp;</@shiro.hasPermission>';
                            operateHtml = operateHtml + '<button class="btn btn-info btn-xs" type="button" onclick="grant(\''+row.id+'\')"><i class="fa fa-arrows"></i>&nbsp;角色关联</button>';
                        	//operateHtml = operateHtml + '<button class="btn btn-info btn-xs" type="button" onclick="grant(\''+row.id+'\')"><i class="fa fa-arrows"></i>&nbsp;删除用户</button>';
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
                    "pageNumber":params.pageNumber,
                    "pageSize":params.pageSize,
                    "loginName":$("#loginName").val()
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
                    title: '用户添加',
                    shadeClose: true,
                    shade: false,
                    area: ['800px', '600px'],
                    content: '${ctx}/user/add',
                    end: function(index){
                        $('#userListTable').bootstrapTable("refresh");
                    }
                });
            }
            function grant(id){
                layer.open({
                    type: 2,
                    title: '关联角色',
                    shadeClose: true,
                    shade: false,
                    area: ['800px', '600px'],
                    content: '${ctx}/user/grant/'  + id,
                    end: function(index){
                        $('#userListTable').bootstrapTable("refresh");
                    }
                });
            }
            function edit(id){
                layer.open({
                    type: 2,
                    title: '编辑用户',
                    shadeClose: true,
                    shade: false,
                    area: ['800px', '600px'],
                    content: '${ctx}/user/edit/'  + id,
                    end: function(index){
                        $('#userListTable').bootstrapTable("refresh");
                    }
                });
            }
            function del(id){
                layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){
                    $.ajax({
                        type: "POST",
                        dataType: "json",
                        url: "${ctx}/user/delete/" + id,
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
