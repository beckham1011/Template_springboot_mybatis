<link href="/css/bootstrap.min.css" rel="stylesheet">
<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="#"  class="dropdown-toggle" data-toggle="dropdown">
					实时监测
					<b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
						<li><a href="${ctx}/equipdata/index">实时数据</a></li>
						<li class="divider"></li>
						<li><a href="${ctx}/equipdata/map">电子地图</a></li>
					</ul>
				</li>
				<li><a href="#" class="dropdown-toggle" data-toggle="dropdown">
					历史查询
					<b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li><a href="${ctx}/history/index">历史查询</a></li>
						<li class="divider"></li>
						<li><a href="${ctx}/alarm/index">告警查询</a></li>
					</ul>			
				
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						统计分析
						<b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li><a href="${ctx}/analysis/index.ftl">通用报表</a></li>
						<li class="divider"></li>
						<li><a href="${ctx}/menu/index.ftl">曲线分析</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						信息管理
						<b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li><a href="${ctx}/datacard/index.ftl">数据卡维护</a></li>
						<li class="divider"></li>
						<li><a href="${ctx}/declarationrecord/index.ftl">申报记录</a></li>
						<li class="divider"></li>
						<li><a href="${ctx}/maintainrecord/index.ftl">维修记录</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						用户中心
						<b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li><a href="${ctx}/equiptype/index.ftl">泵站管理</a></li>
						<li class="divider"></li>
						<li><a href="${ctx}/user/index.ftl">用户管理</a></li>
						<li class="divider"></li>
						<li><a href="${ctx}/role/index.ftl">角色管理</a></li>
						<li class="divider"></li>
						<li><a href="${ctx}/role/index.ftl">系统管理</a></li>						
					</ul>
				</li>
			</ul>
		</div>
	</div>
</nav>