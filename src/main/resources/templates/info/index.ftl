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
								<a href="/equipdata/index">首页</a>
							</li>
							<li><a href="#">实时监测</a></li>
							<li class="active">实时数据</li>
						</ul><!-- .breadcrumb -->

						<div class="nav-search" id="nav-search">
							<form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
									<i class="icon-search nav-search-icon"></i>
								</span>
							</form>
						</div><!-- #nav-search -->
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
	                                        	<option value="-1" >请选择---</option>
		                                        <#list subTypeList1 as type>
		                                            <option value="${type.id}" >
		                                                ${type.name}
		                                            </option>
		                                        </#list>
		                                    </select>
		                                    <select name="typeSelect2" id="typeSelect2" class="form-control"  style="width:180px; float:left;" onChange="typeSelect2Change(this)">
	                                    		<option value="-2" >请选择---</option>
		                                    </select>
		                                    <select name="typeSelect3" id="typeSelect3" class="form-control"  style="width:180px;  float:left;" onChange="typeSelect3Change()">>
	                                        	<option value="-3" >请选择---</option>
		                                    </select>
											<label>
												<input type="checkbox" id="online" name="online"/>在线
											</label>
											<label>
												<input type="checkbox" id="offline" name="offline"/>离线
											</label>
		                                    <div  style="float:right">
	                                        	<input id="stationName" placeholder="请输入泵站名称" name="stationName" type="text"/>
		                                        <button class="btn btn-xs btn-primary" onclick="search();"><i class="fa fa-search"></i>&nbsp;查询</button>
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

											<div class="modal-body no-padding">
												<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
													<thead>
														<tr>
															<th>Domain</th>
															<th>Price</th>
															<th>Clicks</th>

															<th>
																<i class="icon-time bigger-110"></i>
																Update
															</th>
														</tr>
													</thead>

													<tbody>
														<tr>
															<td>
																<a href="#">ace.com</a>
															</td>
															<td>$45</td>
															<td>3,330</td>
															<td>Feb 12</td>
														</tr>
													</tbody>
												</table>
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

				<div class="ace-settings-container" id="ace-settings-container">
					<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
						<i class="icon-cog bigger-150"></i>
					</div>

					<div class="ace-settings-box" id="ace-settings-box">
						<div>
							<div class="pull-left">
								<select id="skin-colorpicker" class="hide">
									<option data-skin="default" value="#438EB9">#438EB9</option>
									<option data-skin="skin-1" value="#222A2D">#222A2D</option>
									<option data-skin="skin-2" value="#C6487E">#C6487E</option>
									<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
								</select>
							</div>
							<span>&nbsp; Choose Skin</span>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar" />
							<label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar" />
							<label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs" />
							<label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" />
							<label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
						</div>

						<div>
							<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
							<label class="lbl" for="ace-settings-add-container">
								Inside
								<b>.container</b>
							</label>
						</div>
					</div>
				</div><!-- /#ace-settings-container -->
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
                        field: "waterstatus"

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
                        field: "communicationStatus"                              
                    },{
                        title: "更新时间",
                        field: "add_time"                  
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
                    "loginName":   $("#loginName").val(),
                    "parentId1" :  $("#typeSelect1").val().replace(/\$|\,/g, ''),
                    "parentId2" :  $("#typeSelect2").val().replace(/\$|\,/g, ''),
                    "parentId3" :  $("#typeSelect3").val().replace(/\$|\,/g, ''),
                    "stationName": $("#stationName").val().trim()
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
            function typeSelect1Change(obj){
            	var parentId = $('#typeSelect1').val().replace(/\$|\,/g, '');
                $('#userListTable').bootstrapTable("refresh");
                
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
            
            function typeSelect3Change(){
            	var parentId = $('#typeSelect3').val().replace(/\$|\,/g, '');
            	var params = {"parentId":parentId};
            	$('#userListTable').bootstrapTable("refresh");
            }
            
		</script>
</body>
</html>
