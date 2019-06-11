<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>厚水智能-实时监测</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<link href="${ctx}/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${ctx}/css/font-awesome.min.css" />
        <link rel="stylesheet" href="${ctx}/bjjoy/css/font-awesome.min.css" />
		<!-- page specific plugin styles -->

		<!-- ace styles -->
		<link rel="stylesheet" href="${ctx}/css/ace.min.css" />
		<link rel="stylesheet" href="${ctx}/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${ctx}/css/ace-skins.min.css" />

		<script src="${ctx}/js/jquery-2.0.3.min.js"></script>
		<script src="${ctx}/js/ace-extra.min.js"></script>
		
		<link rel="stylesheet" href="${ctx}/css/mainpage.css">
		
	</head>

	<body>
		<div class="navbar navbar-default" id="navbar">
			<div class="msghead">
				<div class="headLeft"><i class="fa fa-backward" aria-hidden="true"></i>&nbsp;<a href="/appmainpage">主页</a></div>
				<div class="headCenter">
					<span>实时数据监测</span>
					<p class="msgHeadCount">设备总数:558，在线:172，离线:386，在线率:30.77%</p>
				</div>
				<div class="headRight"><a href="#" onclick="logout()">退出</a>&nbsp;<i class="fa fa-sign-out" aria-hidden="true"></i></div>
			</div>
		</div>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<div class="main-container-inner">
				<a class="menu-toggler1" id="menu-toggler" href="#">泵站选择</a>
				<#include "${ctx}/menu.ftl"/>			
				<div class="main-content">
					<div class="page-content" >
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								<div class="row">
                                    <div class="col-xs-12">
                                        <!-- <button class="btn btn-xs btn-danger" onclick="refreshAllStation();"><i class=""></i>&nbsp;加载所有泵站数据</button> -->
                                        <div class="form-group">
		                                    <div style="float:left">
		                                    	<div style="float:right; margin-left: 145px;">
		                                        	<input id="stationName" class="stationName" placeholder="请输入泵站名称" name="stationName" type="text"/>
			                                        <button class="btn btn-xs btn-primary" onclick="search();"><i class="fa fa-search"></i>&nbsp;查询</button>
			                                        <button class="btn btn-xs btn-warning" onclick="refreshAllStation();"><i class="fa fa-refresh"></i>&nbsp;刷新</button>
		                                        </div>
                                        	</div>
                                        </div>
                                    </div>
								</div>
                                <div class="space-6"></div>
                                <div class="table-responsive">
                                    <table id="equipdataListTable"></table>
                                </div>

							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->
			</div><!-- /.main-container-inner -->
		</div><!-- /.main-container -->

		<#include "${ctx}/common.ftl"/>

		<script type="text/javascript">
		
            $(document).ready(function () {
            
            	localStorage.setItem("parentId", ${parentId});
            	
                //初始化表格,动态从服务器加载数据
                $("#equipdataListTable").bootstrapTable({
                    //使用get请求到服务器获取数据
                    method: "GET",
                    //必须设置，不然request.getParameter获取不到请求参数
                    contentType: "application/x-www-form-urlencoded",
                    //获取数据的Servlet地址
                    url: "${ctx}/equipdata/newest",
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
                    datalocale: "zh-cn",
                    //是否启用详细信息视图
                    detailView: true,
                    detailFormatter: detailFormatter,
                    //表示服务端请求
                    sidePagination: "server",
                    //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
                    //设置为limit可以获取limit, offset, search, sort, order
                    queryParams: getQueryParams,
                    queryParamsType: "",
                    //json数据解析
                    striped: true,
                    responseHandler: function(res) {
                    	$("thead").hide();
                        return {
                            "rows": res.data.equipList,
                            "total": res.data.count,
                        };
                    },
                    //数据列
                    columns: [{
                    	title: "泵站名称",
                        field: "name",
                        width: 124
                    },{
                    	title: "正累积",
                        field: "netcumulative",
                        width: 90
                    },{
                    	title: "更新时间",
                        field: "add_time",
                        width: 138,
			            formatter: function (value, row, index) {
			                return value.substring(0,16)
			            }
                    }]
                });
            });

            function detailFormatter(index, row) {
                var html = [];
                html.push('<p><b>类型:</b> ' + row.bengxing + '</p>');
                html.push('<p><b>口径:</b> ' + row.koujing + '</p>');
                html.push('<p><b>功率:</b> ' + row.gonglv + '</p>');
                html.push('<p><b>操作:</b><button class="btn btn-primary btn-xs" type="button" onclick="refreshEquiptypeData(\''+row.addresscode+'\')"><i class="fa fa-edit"></i>&nbsp;刷新</button>&nbsp;&nbsp;<button class="btn btn-primary btn-xs" type="button" onclick="equiptypeDetail(\''+row.addresscode+'\')"><i class="fa fa-edit"></i>&nbsp;详情</button></p>');
                return html.join('');
            }

            function getQueryParams(params){
            	console.log("params:" + params);
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
            
            function refreshTable(){
                $('#equipdataListTable').bootstrapTable("refresh");
            }

			function equiptypeDetail(id){
				window.location.href="${ctx}/equiptype/detail/" + id;
			}
            
            function refreshEquiptypeData(id){
            	layer.confirm('发送成功，需要5秒钟返回数据。', {icon: 1, title:'提示'}, function(index){
					$.ajax({
	                    type: "GET",
	                    dataType: "json",
	                    url: '${ctx}/socket?param='  + id,
	                    success: function(msg){
		                    layer.close(index);
		                    refreshTable();
	                    }
	                });
	            });
            }
                        
            function refreshAllStation(){
                layer.confirm('确定刷新所有泵站数据吗?\n获取数据需要五秒钟', {icon: 3, title:'危险操作'}, function(index){
	                $.ajax({
	                    type: "GET",
	                    dataType: "json",
	                    url: '${ctx}/refreshAll' ,
	                    success: function(msg){
	                    	layer.close(index);
	                    	refreshTable();
	                    }
	                });
	            });
            }
            
            function editData(dataid , addressCode){
            	console.log('add data ' + dataid) ;
                layer.open({
                    type: 2,
                    title: '编辑数据',
                    shadeClose: true,
                    shade: false,
                    area: ['900px', '400px'],
                    content: '${ctx}/equipdata/edit/' + addressCode + '/' + dataid,
                    end: function(index){
                        layer.close(index);
                        refreshTable();
                    }
                });
            }
            
            function clickNode(event, data){
	        	localStorage.setItem("parentId",data['id']);
	        	refreshTable();
	        }
            
            function itemOnclick (){
            }
            
            function logout(){
            	localStorage.removeItem("username");
            	window.location.href="/logout";
            }
		</script>
</body>
</html>
