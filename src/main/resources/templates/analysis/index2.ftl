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
		<link rel="stylesheet" href="${ctx}/css/bootstrap-datetimepicker.min.css" />
		<script src="${ctx}/js/ace-extra.min.js"></script>

		<script src="${ctx}/js/jquery-2.0.3.min.js"></script>
		<script src="${ctx}/js/bootstrap.min.js"></script>
		<script src="${ctx}/js/bootstrap-datetimepicker.min.js"></script>

	</head>

	<body>
        <#include "${ctx}/head.ftl" />
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<#include "${ctx}/head_nav.ftl" />
			<div class="main-container-inner">

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
	                                <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
							       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
							       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
							       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
							       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
							       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
							       <script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
							       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
							       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>
							       <script type="text/javascript">
									var dom = document.getElementById("container");
									var myChart = echarts.init(dom);
									var app = {};
									option = null;
									app.title = '堆叠柱状图';
									
									option = {
									    tooltip : {
									        trigger: 'axis',
									        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
									            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
									        }
									    },
									    legend: {
									        data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎','百度','谷歌','必应','其他']
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
									            data : ['周一','周二','周三','周四','周五','周六','周一','周二','周三','周四','周五','周六','周一','周二','周三','周四','周五','周六','周一','周二','周三','周四','周五','周六','周日']
									        }
									    ],
									    yAxis : [
									        {
									            type : 'value'
									        }
									    ],
									    series : [
									        
									        {
									            name:'邮件营销',
									            type:'bar',
									            barWidth : 15,
									            stack: '广告',
									            data:[120, 132, 101, 134, 90, 230,120, 132, 101, 134, 90, 230,120, 132, 101, 134, 90, 230,120, 132, 101, 134, 90, 230, 210]
									        },
									        {
									            name:'联盟广告',
									            type:'bar',
									            stack: '广告',
									            data:[220, 182, 191, 234, 290, 330, 220, 182, 191, 234, 290, 330, 220, 182, 191, 234, 290, 330, 220, 182, 191, 234, 290, 330, 310]
									        },
									        {
									            name:'视频广告',
									            type:'bar',
									            stack: '广告',
									            data:[150, 232, 201, 154, 190, 330,150, 232, 201, 154, 190, 330,150, 232, 201, 154, 190, 330,150, 232, 201, 154, 190, 330, 410]
									        },
									        {
									            name:'视频广告1',
									            type:'bar',
									            stack: '广告',
									            data:[150, 232, 201, 154, 190, 330,150, 232, 201, 154, 190, 330,150, 232, 201, 154, 190, 330,150, 232, 201, 154, 190, 330, 410]
									        },
									        {
									            name:'视频广告2',
									            type:'bar',
									            stack: '广告',
									            data:[150, 232, 201, 154, 190, 330,150, 232, 201, 154, 190, 330,150, 232, 201, 154, 190, 330,150, 232, 201, 154, 190, 330, 410]
									        },
									        {
									            name:'视频广告3',
									            type:'bar',
									            stack: '广告',
									            data:[150, 232, 201, 154, 190, 330,150, 232, 201, 154, 190, 330,150, 232, 201, 154, 190, 330,150, 232, 201, 154, 190, 330, 410]
									        },
									        {
									            name:'视频广告4',
									            type:'bar',
									            stack: '广告',
									            data:[150, 232, 201, 154, 190, 330,150, 232, 201, 154, 190, 330,150, 232, 201, 154, 190, 330,150, 232, 201, 154, 190, 330, 410]
									        },
									        {
									            name:'视频广告5',
									            type:'bar',
									            stack: '广告',
									            data:[150, 232, 201, 154, 190, 330,150, 232, 201, 154, 190, 330,150, 232, 201, 154, 190, 330,150, 232, 201, 154, 190, 330, 410]
									        },
									        {
									            name:'视频广告6',
									            type:'bar',
									            stack: '广告',
									            data:[150, 232, 201, 154, 190, 330,150, 232, 201, 154, 190, 330,150, 232, 201, 154, 190, 330,150, 232, 201, 154, 190, 330, 410]
									        },
									        {
									            name:'视频广告7',
									            type:'bar',
									            stack: '广告',
									            data:[150, 232, 201, 154, 190, 330,150, 232, 201, 154, 190, 330,150, 232, 201, 154, 190, 330,150, 232, 201, 154, 190, 330, 410]
									        },
					                        {
					                            name:'合计',
					                            type:'bar',
					                            stack:'xxx',
					                            label: {
					                                normal: {
					                                    show: true,
					                                    position: 'top',
					                                    textStyle: {
					                                        color: '#000'
					                                    },
					                                    formatter:''
					                                }
					                            },
					                            data:  [0,0]    //思路一：给series集合末尾多加一栏用于展示合计，但是值都是0；缺点：必须根据xAxis的data生成一组为空的数据，且tooltip不能加trigger: 'axis',这个条件，不然会展示合计：0
					                        }
									    ]
									};
									if (option && typeof option === "object") {
								        // 使用刚指定的配置项和数据显示图表。
									    myChart.setOption(option, true);
									}
                                	</script>
                                	<script>
                    		            $(document).ready(function () {
							            	localStorage.setItem("parentId", ${parentId});
							            	$("#createDate").val('2018-11-05');
							            	$("#enddate").val('2019-01-05');
						            	});
                                	</script>
                                </div>
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->
			</div><!-- /.main-container-inner -->

		</div><!-- /.main-container -->

</body>
</html>
