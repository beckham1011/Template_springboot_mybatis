<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>厚水智能-通用分析</title>
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

		<script src="${ctx}/js/jquery-2.0.3.min.js"></script>
		<script src="${ctx}/js/bootstrap.min.js"></script>

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
				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>
						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="/equipdata/index">首页</a>
							</li>
							<li><a href="#">统计分析</a></li>
							<li class="active">通用报表</li>
						</ul><!-- .breadcrumb -->
					</div>

					<div class="page-content" >
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								
								<div class="row ">
                                    <div class="col-xs-12">
                                        <h3 class="header smaller lighter blue">通用报表</h3>
			                            <div class="form-group">
			                                <label class="col-sm-1 control-label">起止时间：</label>
			                                <div class="col-sm-1">
			                                    <input id="createDate" name="createDate"  class="laydate-icon form-control" value="">
			                                </div>
			                                <div class="col-sm-1">
			                                    <input id="enddate" name="enddate"  class="laydate-icon form-control" value="">
			                                </div>
			                            </div>                                    
	                                    <div class="form-group">
		                                    <div  style="float:right">
	                                        	<input id="stationName" placeholder="请输入泵站名称" name="stationName" type="text"/>
		                                        <button class="btn btn-xs btn-primary" onclick="search();"><i class="fa fa-search"></i>&nbsp;查询</button>
	                                    	</div>
	                                    </div>
                                    </div>
								</div>
                                
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
                    url: "${ctx}/analysis/list",
                    //表格显示条纹
                    striped: true,
                    //启动分页
                    pagination: false,
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
                            "rows": res.data.analysises,
                            "total": res.data.count
                        };
                    },
                    //数据列
                    columns: [{
                        title: "设备名称",
                        field: "name"                   
                    },{
                        title: "瞬时流量（M3/h）-最小值",
                        field: "mnflwrate"                        
                    },{
                        title: "瞬时流量（M3/h）-最大值",
                        field: "mxflwrate"
                    },{
                        title: "瞬时流量（M3/h）-平均值",
                        field: "avgflwrate"

                    },{
                        title: "正累积-最小值",
                        field: "mncumlate"

                    },{
                        title: "正累积-最大值",
                        field: "mxcumlate"

                    },{
                        title: "正累积-平均值",
                        field: "avgcumlate"

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
                    "stationName": $("#stationName").val().trim()
                }
                return params;
            }
            function search() {
                var params = { "stationName": $("#stationName").val().trim() };
                $('#userListTable').bootstrapTable("refresh");
            }
          
            
		</script>
</body>
</html>
