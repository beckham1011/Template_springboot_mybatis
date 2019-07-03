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
							<li><a href="#">实时监测</a></li>
							<li class="active">实时数据</li>
						</ul><!-- .breadcrumb -->

					</div>
					
					<div class="page-content" >
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								
								<div class="row ">
                                    <div class="col-xs-12">
                                        <h3 class="header smaller lighter blue">实时数据</h3>
                                        <div class="form-group">
											
		                                    <div style="float:left">
		                                    	<button class="btn btn-xs btn-danger" onclick="refreshAllStation();"><i class=""></i>&nbsp;即时刷新泵站</button>
	                                        	<input id="stationName" placeholder="请输入泵站名称" name="stationName" type="text"/>
	                                        	<aclass="glyphicon glyphicon-remove btn form-control-feedback"style="pointer-events:auto"></a>
		                                        <button class="btn btn-xs btn-primary" onclick="refreshTable()"><i class="fa fa-search"></i>&nbsp;查询</button>
		                                        <@shiro.hasPermission name="system:data:edit"><button class="btn btn-info btn-xs" type="button" onclick="exportData()"><i class="fa fa-edit"></i>&nbsp;导出数据</button></@shiro.hasPermission>
                                        	</div>
                                        	&nbsp;
                                        	<label>
												<input type="checkbox" id="online" name="online" onclick="refreshTable()"/>在线
											</label>
											<label>
												<input type="checkbox" id="offline" name="offline" onclick="refreshTable();"/>离线
											</label>
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
                    striped: false,
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
                    detailView:false,
                    detailFormatter:detailFormatter,
                    //表示服务端请求
                    sidePagination: "server",
                    //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
                    //设置为limit可以获取limit, offset, search, sort, order
                    queryParams: getQueryParams,
                    queryParamsType: "",
                    //json数据解析
                    striped: true,
                    responseHandler: function(res) {
                        return {
                            "rows": res.data.equipList,
                            "total": res.data.count,
                        };
                    },
                    //数据列
                    columns: [{
                        title: "序号",
                        field: "seq",
                        sortable: true
                    },{
                        title: "泵站名称",
                        field: "name"                   
                    },{
                        title: "泵型",
                        field: "bengxing"
                    },{
                        title: "口径",
                        field: "koujing"
                    },{
                        title: "功率",
                        field: "gonglv"

                    },{
                        title: "用水状态",
                        field: "waterstatus",
                        align: 'center',
	                    formatter: function(value,row,index){
	                    	var waterstatushtml = '' ;
                    		if(value == '1'){
                    			waterstatushtml = '<img src="${ctx}/images/big_online.gif" style="height: 25px;" class="img-rounded" />' ;
                    		}else{
                    			waterstatushtml = '<img src="${ctx}/images/big_red.png" style="height: 25px;" class="img-rounded" />' ;
                    		}
	                        return waterstatushtml;
	                    }
                    },{
                        title: "瞬时流量（M3/h）",
                        field: "flowrate"

                    },{
                        title: "正累积（M3）",
                        field: "netcumulative"
                    },{
                        title: "信号质量",
                        field: "signalquality"
                    },{
                        title: "通讯状态",
                        field: "communicationStatus",
                        align: 'center',
	                    formatter: function(value,row,index){
                    		var communicationStatushtml ;
                    		if(value == '2'){
	                    		communicationStatushtml = '<img src="${ctx}/images/wifi4.png" style="height: 25px;" class="img-rounded" />' ;
	                		}else{
	                    		communicationStatushtml = '<img src="${ctx}/images/wifi2.png" style="height: 25px;" class="img-rounded" />' ;
                    		}
	                        return communicationStatushtml;
	                    }
                    },{
                        title: "更新时间",
                        field: "add_time"
                    },{
                        title: "操作",
                        field: "empty",
                        formatter: function (value, row, index) {
                            var operateHtml = '<button class="btn btn-primary btn-xs" type="button" onclick="refreshEquiptypeData(\''+row.addresscode+'\')"><i class="fa fa-edit"></i>&nbsp;刷新</button>';
                        	operateHtml += '&nbsp;<@shiro.hasPermission name="system:data:edit"><button class="btn btn-info btn-xs" type="button" onclick="editData(\''+row.dataId+'\',\''+row.addresscode+'\')"><i class="fa fa-edit"></i>&nbsp;编辑</button></@shiro.hasPermission>';
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
                    "stationName": $("#stationName").val().trim(),
                    "parentId"   : localStorage.getItem("parentId")
                }
                if( !($("#online").is(':checked') == $("#offline").is(':checked'))){
                	params.waterStatus = $("#online").is(':checked') ? 1 : ( $("#offline").is(':checked') ? 0 : 1)
                }
                return params;
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
            
            function refreshTable(){
                $('#equipdataListTable').bootstrapTable("refresh");
            }
            
            function clickNode(event, data){
	        	localStorage.setItem("parentId",data['id']);
	        	refreshTable();
	        }
            
            function itemOnclick (){}

			function exportData(){
				window.location.href = "${ctx}/equipdata/export" ;
			}
            
		</script>
</body>
</html>
