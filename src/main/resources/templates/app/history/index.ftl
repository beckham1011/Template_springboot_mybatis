<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>厚水智能-app历史查询</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<link href="${ctx}/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${ctx}/css/font-awesome.min.css" />
        <link rel="stylesheet" href="${ctx}/bjjoy/css/font-awesome.min.css" />
		<!-- page specific plugin styles -->

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
					<span>记录查询</span>
					<p class="msgHeadCount">设备总数:558，在线:172，离线:386，在线率:30.77%</p>
				</div>
				<div class="headRight"><a href="#" onclick="logout()">退出</a>&nbsp;<i class="fa fa-sign-out" aria-hidden="true"></i></div>
			</div>
		</div>	
		<div class="main-container" id="main-container">
			<div class="main-container-inner">
				
				<div class="main-content">
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->

								<div class="row ">
                                    <div class="col-xs-12">
										<div class="row ">
		                                    <div class="col-xs-12">
					                            <div class="form-group">
					                                <label class="col-sm-1 control-label">起止时间：</label>
					                                <div class="col-sm-2">
					                                    <input id="createDate" name="createDate" type="date" class="laydate-icon form-control" value="2018-12-05">
					                                </div>
					                                <div class="col-sm-2">
					                                    <input id="enddate" name="enddate" type="date" class="laydate-icon form-control" value="2019-01-05">			                                
					                                </div>
				                                    <div style="float:right">
					                                    &nbsp;&nbsp;<a class="menu-toggler1" id="menu-toggler" href="#">泵站选择</a>
														&nbsp;&nbsp;<#include "${ctx}/menu.ftl"/>
			                                        	<input id="stationName" placeholder="请输入泵站名称" name="stationName" type="text"/>
				                                        <button class="btn btn-xs btn-primary" onclick="search();"><i class="fa fa-search"></i>&nbsp;查询</button>
			                                    	</div>
			                                    </div>
		                                    </div>
										</div>
                                    </div>
								</div>
                            	<div class="space-6"></div>
                            	<div class="table-responsive">
                                <table id="equipListHistoryTable"></table>
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

			</div><!-- /.main-container-inner -->
		</div><!-- /.main-container -->

		<#include "${ctx}/common.ftl"/>

		<!-- inline scripts related to this page -->


<script>
	
	Date.prototype.Format = function(fmt)   
	{ //author: meizz   
	  var o = {   
	    "M+" : this.getMonth()+1,                 //月份   
	    "d+" : this.getDate(),                    //日   
	    "h+" : this.getHours(),                   //小时   
	    "m+" : this.getMinutes(),                 //分   
	    "s+" : this.getSeconds(),                 //秒   
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
	    "S"  : this.getMilliseconds()             //毫秒   
	  };   
	  if(/(y+)/.test(fmt))   
	    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	  for(var k in o)   
	    if(new RegExp("("+ k +")").test(fmt))   
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	  return fmt;
	}
	
	$(document).ready(function () {
		localStorage.setItem("parentId", ${parentId});
		
		localStorage.setItem("createDate", new Date(new Date().getTime() - (31 * 24 * 3600 * 1000)).Format("yyyy-MM-dd"));
		localStorage.setItem("enddate", new Date(new Date().getTime() - (24 * 3600 * 1000)).Format("yyyy-MM-dd"));
		
		$("#createDate").val(new Date(new Date().getTime() - (31 * 24 * 3600 * 1000)).Format("yyyy-MM-dd"));
		$("#enddate").val(new Date(new Date().getTime() - (24 * 3600 * 1000)).Format("yyyy-MM-dd"));
	});
	
</script>


		<script type="text/javascript">
            $(document).ready(function () {
        		
        		Date.prototype.Format = function(fmt)   
				{ //author: meizz   
				  var o = {   
				    "M+" : this.getMonth()+1,                 //月份   
				    "d+" : this.getDate(),                    //日   
				    "h+" : this.getHours(),                   //小时   
				    "m+" : this.getMinutes(),                 //分   
				    "s+" : this.getSeconds(),                 //秒   
				    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
				    "S"  : this.getMilliseconds()             //毫秒   
				  };   
				  if(/(y+)/.test(fmt))   
				    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
				  for(var k in o)   
				    if(new RegExp("("+ k +")").test(fmt))   
				  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
				  return fmt;
				}
            	localStorage.setItem("parentId", ${parentId});
				$("#createDate").val(new Date(new Date().getTime() - (31 * 24 * 3600 * 1000)).Format("yyyy-MM-dd"));
				$("#enddate").val(new Date(new Date().getTime() - (24 * 3600 * 1000)).Format("yyyy-MM-dd"));
				
				
                //初始化表格,动态从服务器加载数据
                $("#equipListHistoryTable").bootstrapTable({
                    //使用get请求到服务器获取数据
                    method: "GET",
                    //必须设置，不然request.getParameter获取不到请求参数
                    contentType: "application/x-www-form-urlencoded",
                    //获取数据的Servlet地址
                    url: "${ctx}/history/newest",
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
                    queryParams : getQueryParams,
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
                        title: "泵站名称",
                        field: "name"                   
                    },{
                        title: "当天正累积（M3）",
                        field: "areCumulativeHis"
                    },{
                        title: "日期",
                        field: "addTime"                  
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
                    "startDate":   $("#createDate").val(),
                    "endDate":     $("#enddate").val(),                    
                    "stationName": $("#stationName").val().trim(),
                    "parentId"   : localStorage.getItem("parentId")
                }
                return params;
            }
            
            function search() {
                $('#equipListHistoryTable').bootstrapTable("refresh");
            }
            
            function exportHistory(){
            	window.location.href = "${ctx}/history/export" ;
            }
            
            function clickNode(event, data){
	        	localStorage.setItem("parentId",data['id']);
	        	$('#equipListHistoryTable').bootstrapTable("refresh");
	        }
            
            function itemOnclick (){}
            
            function logout(){
            	localStorage.removeItem("username");
            	window.location.href="/logout";
            }
            
			function treeOnClick() {
				var treeData ;
		        $.ajax({
		            type: "GET",
		            dataType: "json",
		            url: "${ctx}/equiptype/tree2",
		            success: function(result){
		                
		                treeData = result.data.typeTree;
		                
		                console.log("tree on click");
		                
		                $('#treeview1').treeview({
				          	showTags: true,
				          	levels: 2,
				          	data: treeData,
				          	onNodeSelected: function(event, data){
				          		console.log("onNodeSelected");
				          		clickNode(event, data);
				          	}
				        });
		            }
		        });
		    }            
            
            
		</script>
</body>
</html>
