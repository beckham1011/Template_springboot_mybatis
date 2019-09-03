<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>厚水智能-通用分析app</title>
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
		<link rel="stylesheet" href="${ctx}/css/bootstrap-datetimepicker.min.css" />
		<script src="${ctx}/js/ace-extra.min.js"></script>

		<script src="${ctx}/js/jquery-2.0.3.min.js"></script>
		<script src="${ctx}/js/bootstrap.min.js"></script>
		<script src="${ctx}/js/bootstrap-datetimepicker.min.js"></script>

		<link rel="stylesheet" href="${ctx}/css/mainpage.css">
	</head>

	<body>
		<div class="navbar navbar-default" id="navbar">
			<div class="msghead">
				<div class="headLeft"><i class="fa fa-backward" aria-hidden="true"></i>&nbsp;<a href="/appmainpage">主页</a></div>
				<div class="headCenter">
					<span>统计分析</span>
					<p class="msgHeadCount">设备总数:558，在线:172，离线:386，在线率:30.77%</p>
				</div>
				<div class="headRight"><a href="#" onclick="logout()">退出</a>&nbsp;<i class="fa fa-sign-out" aria-hidden="true"></i></div>
			</div>
		</div>		
		<div class="main-container" id="main-container">
			<div class="main-container-inner">
                <#include "${ctx}/menu.ftl"/>
				<div class="main-content">

					<div class="page-content" >
						<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
								
								<div class="row ">
                                    <div class="col-xs-12">
                                        <h3 class="header smaller lighter blue">通用报表</h3>
			                            <div class="form-group">
			                                <label class="col-sm-1 control-label">起止时间：</label>
			                                <div class="col-sm-2">
			                                    <input id="createDate" name="createDate" type="date" class="laydate-icon form-control" value="2018-12-05">
			                                </div>
			                                <div class="col-sm-2">
			                                    <input id="enddate" name="enddate" type="date" class="laydate-icon form-control" value="2019-01-05">			                                
			                                </div>
			                               
			                            </div>
			                            <div class="form-group">
			                                	<label class="col-sm-1 control-label">类型:</label>
				                             	<div class="col-sm-2">
			                                	<select>
			                                		<option>天</option>
			                                		<option>月</option>
			                                		<option>季</option>
			                                		<option>年</option>
			                                	</select>
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
                                <div class="space-6"></div>
                                <div style="width:1350px;height:420px;border:#ccc solid 1px;" id="container">
	                               
                                </div>
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->
			</div><!-- /.main-container-inner -->

		</div><!-- /.main-container -->

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
   <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
   <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
   <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
   <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
   <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
   <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
   <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
   <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>
   <script type="text/javascript">
		
		//页面初始化
		loadChart(${parentId});
			
        function clickNode(event, data){
        	localStorage.setItem("parentId",data['id']);
        	loadChart(data['id']);
        }
        
        function itemOnclick (){}
        
        function loadChart(parentId){
        	$.ajax({
			    type: "GET",
			    dataType: "json",
			    url: '${ctx}/analysis/chart?parentId=' + parentId + '&startDate=' + $("#createDate").val() 
			    			+'&endDate=' + $("#enddate").val() ,
			    success: function(data){
			    	var typeListLength = data.data.typeList.length;
			    	console.log(data.data)
			    	var serialData = new Array(typeListLength);
			    	for(var index = 0 ; index < typeListLength ; index ++){
			    		console.log()
					}
					generateOption(data);
			    }
			});
        }
		
		
        function logout(){
        	localStorage.removeItem("username");
        	window.location.href="/logout";
        }
        
		
		function generateOption(data){
			var dom = document.getElementById("container");
			var myChart = echarts.init(dom);
			var app = {};
			option = null;
			app.title = '堆叠柱状图';
			
			var typeList = data.data.typeList ;
			var typeData = data.data.analysisChart ;
			var daysList  = typeData[0] ;
			var serialData = new Array(typeList.length) ;
			
			for(var index = 0 ; index < typeList.length ; index ++){
				var option = {} ;
				option.name = typeList[index];
				option.type = 'bar';
				option.stack = 'day';
				option.data = typeData[index + 1];
				serialData[index] = option ;
			}
			
			option = {
			    tooltip : {
			        trigger: 'axis',
			        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
			            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
			        }
			    },
			    legend: {
			        data: typeList
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis : [
			        {
			            type : 'category',
			            data : daysList
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series :serialData
			};
			if (option && typeof option === "object") {
		        // 使用刚指定的配置项和数据显示图表。
			    myChart.setOption(option, true);
			}
		}
		
	</script>
</body>
</html>
