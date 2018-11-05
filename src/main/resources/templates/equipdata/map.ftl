<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta charset="utf-8" />
		<title>厚水智能-实时监测</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<!-- basic styles -->

		<link href="${ctx}/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${ctx}/css/font-awesome.min.css" />
        <link rel="stylesheet" href="${ctx}/bjjoy/css/font-awesome.min.css" />
		<!-- page specific plugin styles -->

		<!-- ace styles -->
		<link rel="stylesheet" href="${ctx}/css/ace.min.css" />
		<link rel="stylesheet" href="${ctx}/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${ctx}/css/ace-skins.min.css" />
		
		<style type="text/css">
		    html,body{margin:0;padding:0;}
		    .iw_poi_title {color:#CC5522;font-size:14px;font-weight:bold;overflow:hidden;padding-right:13px;white-space:nowrap}
		    .iw_poi_content {font:12px arial,sans-serif;overflow:visible;padding-top:4px;white-space:-moz-pre-wrap;word-wrap:break-word}
		</style>
		<script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.1&services=true"></script>

		<script src="${ctx}/js/ace-extra.min.js"></script>
		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	</head>

	<body >
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
					<div class="main-content">
						<div class="breadcrumbs" id="breadcrumbs">
							<script type="text/javascript">
								try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
							</script>
							<ul class="breadcrumb">
								<li>
									<i class="icon-home home-icon"></i>
								</li>
								<li><a href="#">实时监测</a></li>
								<li class="active">实时电子地图</li>
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
	                                        <h3 class="header smaller lighter blue">泵站地图</h3>
	                                    </div>
									</div>
	                                <div class="space-6"></div>
	                                <div class="table-responsive">
										  <!--百度地图容器-->
										  <div style="width:1650px;height:750px;border:#ccc solid 1px;" id="dituContent"></div>
	                                </div>
								</div><!-- /.col -->
							</div><!-- /.row -->
						</div><!-- /.page-content -->
					</div><!-- /.main-content -->
	
				</div><!-- /.main-container-inner -->
	
			</div><!-- /.main-container -->
	
			<#include "${ctx}/common.ftl"/>

</body>

<script type="text/javascript">
	//创建和初始化地图函数：
	function initMap(){
		createMap();//创建地图
		setMapEvent();//设置地图事件
		addMapControl();//向地图添加控件
		addRemark();//向地图中添加文字标注
	}

	//创建地图函数：
	function createMap(){
		var map = new BMap.Map("dituContent");//在百度地图容器中创建一个地图
		var point = new BMap.Point(120.0158,32.3272);//定义一个中心点坐标
		map.centerAndZoom(point,14);//设定地图的中心点和坐标并将地图显示在地图容器中
		window.map = map;//将map变量存储在全局
	}

	//地图事件设置函数：
	function setMapEvent(){
		map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
		map.enableScrollWheelZoom();//启用地图滚轮放大缩小
		map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
		map.enableKeyboard();//启用键盘上下左右键移动地图
	}

	//地图控件添加函数：
	function addMapControl(){
	
		//向地图中添加缩放控件
		var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
		map.addControl(ctrl_nav);
		
		//向地图中添加缩略图控件
		var ctrl_ove = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:1});
		map.addControl(ctrl_ove);
		
		//向地图中添加比例尺控件
		var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
		map.addControl(ctrl_sca);
	}

	//文字标注数组
	//var lbPoints = [{point:"120.0158|32.3272",content:"3、高丰河翻水站"}];
	
	
	//向地图中添加文字标注函数
	var stationList = ${equipDataList} ;
	function addRemark(){
		for(var i=0;i < stationList.length ; i++){
			var label ;
			if(stationList[i].waterstatus == '0'){
				label = new BMap.Label("<div style='padding:2px;'><img src='${ctx}/images/big_red.png' style='height: 15px;'/>"+stationList[i].name+"</div>",{point:new BMap.Point(stationList[i].longitude,stationList[i].latitude),offset:new BMap.Size(3,-6)});
			}else{
				label = new BMap.Label("<div style='padding:2px;'><img src='${ctx}/images/big_online.gif' style='height: 15px;'/>"+stationList[i].name+"</div>",{point:new BMap.Point(stationList[i].longitude,stationList[i].latitude),offset:new BMap.Size(3,-6)});
			}
			map.addOverlay(label);
			label.setStyle({borderColor:"#999"});
		}
	}

	initMap();//创建和初始化地图
</script>

</html>
