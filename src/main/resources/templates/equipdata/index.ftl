<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>厚水智能-实时监测</title>
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
		                                	<select id="typeSelect1" name="typeSelect1" class="form-control" style="width:180px;  float:left;" onChange="typeSelect1Change(this)">
	                                        	<option value="-1" >请选择镇泵站---</option>
		                                        <#list subTypeList1 as type>
		                                            <option value="${type.id}" >
		                                                ${type.name}
		                                            </option>
		                                        </#list>
		                                    </select>
		                                    <select name="typeSelect2" id="typeSelect2" class="form-control"  style="width:180px; float:left;" onChange="typeSelect2Change(this)">
	                                    		<option value="-2" >请选择村泵站---</option>
		                                    </select>
		                                    <select name="typeSelect3" id="typeSelect3" class="form-control"  style="width:180px;  float:left;" onChange="typeSelect3Change()">>
	                                        	<option value="-3" >请选择站点---</option>
		                                    </select>&nbsp;&nbsp;
											<label>
												<input type="checkbox" id="online" name="online" onclick="getOnlineOfflineData()"/>在线
											</label>
											<label>
												<input type="checkbox" id="offline" name="offline" onclick="getOnlineOfflineData()"/>离线
											</label>
		                                    <div style="float:right">
	                                        	<input id="stationName" placeholder="请输入泵站名称" name="stationName" type="text"/>
		                                        <button class="btn btn-xs btn-primary" onclick="search();"><i class="fa fa-search"></i>&nbsp;查询</button>
		                                        <button class="btn btn-xs btn-danger" onclick="refreshAllStation();"><i class=""></i>&nbsp;即时刷新泵站</button>
                                        	</div>
                                        </div>
                                    </div>
								</div>
                                <div class="space-6"></div>
                                <div class="table-responsive">
                                    <table id="userListTable"></table>
                                </div>

								<div id="modal-table" class="modal fade" tabindex="-1">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header no-padding">
												<div class="table-header">
													<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
														<span class="white">&times;</span>
													</button>
													Results for "Latest Registered Domains
												</div>
											</div>


											<div class="modal-footer no-margin-top">
												<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
													<i class="icon-remove"></i>
													Close
												</button>

												<ul class="pagination pull-right no-margin">
													<li class="prev disabled">
														<a href="#">
															<i class="icon-double-angle-left"></i>
														</a>
													</li>

													<li class="active">
														<a href="#">1</a>
													</li>

													<li>
														<a href="#">2</a>
													</li>

													<li>
														<a href="#">3</a>
													</li>

													<li class="next">
														<a href="#">
															<i class="icon-double-angle-right"></i>
														</a>
													</li>
												</ul>
											</div>
										</div><!-- /.modal-content -->
									</div><!-- /.modal-dialog -->
								</div><!-- PAGE CONTENT ENDS -->
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
                            "rows": res.data.equipList,
                            "total": res.data.count
                        };
                    },
                    //数据列
                    columns: [{
                        title: "序号",
                        field: "id",
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
                            var operateHtml = '<button class="btn btn-primary btn-xs" type="button" onclick="refreshEquiptypeData(\''+row.id+'\')"><i class="fa fa-edit"></i>&nbsp;刷新</button>';
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
                    "parentId1" :  $("#typeSelect1").val().replace(/\$|\,/g, ''),
                    "parentId2" :  $("#typeSelect2").val().replace(/\$|\,/g, ''),
                    "parentId3" :  $("#typeSelect3").val().replace(/\$|\,/g, ''),
                    "stationName": $("#stationName").val().trim()
                }
                if( !($("#online").is(':checked') == $("#offline").is(':checked'))){
                	params.waterStatus = $("#online").is(':checked') ? 1 : ( $("#offline").is(':checked') ? 0 : 1)
                }
                return params;
            }
            
            function search() {
                var params = {"loginName":$("#loginName").val()};
                $('#userListTable').bootstrapTable("refresh");
            }
            
            function getOnlineOfflineData(){
                $('#userListTable').bootstrapTable("refresh");
            }
            
            function refreshEquiptypeData(id){
				$.ajax({
                    type: "GET",
                    dataType: "json",
                    url: '${ctx}/socket?param='  + id,
                    success: function(msg){
					    $('#userListTable').bootstrapTable("refresh");
                    }
                });
            }
            
            function typeSelect1Change(obj){
            	var parentId = $('#typeSelect1').val().replace(/\$|\,/g, '');
                $('#userListTable').bootstrapTable("refresh");
				$('#stationName').val('');
				
                $.ajax({
                    type: "GET",
                    dataType: "json",
                    url: "${ctx}/equiptype/subTypelist/?parentId=" + parentId,
                    success: function(msg){
                        var typeSelect2Html = [];
                        typeSelect2Html.push('<option value=-2>All--</option>');
                        var typeSelect3Html = [];
                        typeSelect3Html.push('<option value=-3>All--</option>');
		                for(var i = 0; i < msg.data.subTypeList.length; i ++){
			                typeSelect2Html.push('<option value="' + msg.data.subTypeList[i].id  + '">' + msg.data.subTypeList[i].name + '</option>');
		                }
						$('#typeSelect2').html(typeSelect2Html);
						$('#typeSelect3').html(typeSelect3Html);
                    }
                });
            }
            
            function typeSelect2Change(obj){
            	var parentId = $('#typeSelect2').val().replace(/\$|\,/g, '');
            	$('#userListTable').bootstrapTable("refresh");
                
                $.ajax({
                    type: "GET",
                    dataType: "json",
                    url: "${ctx}/equiptype/subTypelist/?parentId=" + parentId,
                    success: function(msg){
                        var typeSelect3Html = [];
                        typeSelect3Html.push('<option value=-3>All--</option>');
		                for(var i = 0; i < msg.data.subTypeList.length; i ++){
			                typeSelect3Html.push('<option value="' + msg.data.subTypeList[i].id  + '">' + msg.data.subTypeList[i].name + '</option>');
		                }
						$('#typeSelect3').html(typeSelect3Html);
                    }
                });
            }
            
            function refreshAllStation(){
                layer.confirm('确定刷新所有泵站数据吗?', {icon: 3, title:'危险操作'}, function(index){
	                $.ajax({
	                    type: "GET",
	                    dataType: "json",
	                    url: '${ctx}/socket8082?param=all' ,
	                    success: function(msg){
	                    	layer.close(index);
						    $('#userListTable').bootstrapTable("refresh");
	                    }
	                });
	            });
            }
            
            function typeSelect3Change(){
            	var parentId = $('#typeSelect3').val().replace(/\$|\,/g, '');
            	var params = {"parentId":parentId};
            	$('#userListTable').bootstrapTable("refresh");
            }
            
		</script>
</body>
</html>
