<link href="/css/bootstrap.min.css" rel="stylesheet">
<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<div>
			<ul class="nav navbar-nav">
				<@shiro.hasPermission name="realtime:manage">			
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
				</@shiro.hasPermission>
				<@shiro.hasPermission name="history:manage">
					<li><a href="#" class="dropdown-toggle" data-toggle="dropdown">
						历史查询
						<b class="caret"></b>
						</a>
						<ul class="dropdown-menu">
							<li><a href="${ctx}/history/index">历史查询</a></li>
							
							<!-- 
							<li class="divider"></li>
							<li><a href="${ctx}/alarm/index">告警查询</a></li>
							-->
						</ul>
					</li>				
				</@shiro.hasPermission>
				<@shiro.hasPermission name="count:manage">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							统计分析
							<b class="caret"></b>
						</a>
						<ul class="dropdown-menu">
							<li><a href="${ctx}/analysis/index2">通用报表</a></li>
							<!-- 
							<li class="divider"></li>
							<li><a href="${ctx}/menu/index">曲线分析</a></li>
							 -->
						</ul>
					</li>
				</@shiro.hasPermission>
				<@shiro.hasPermission name="info:manage">
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
							<@shiro.hasPermission name="system:equip:manage">
								<li class="divider"></li>
								<li><a href="${ctx}/equiptype/index.ftl">泵站管理</a></li>
							</@shiro.hasPermission>
						</ul>
					</li>
				</@shiro.hasPermission>
				<@shiro.hasPermission name="system:manage">
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							用户中心
							<b class="caret"></b>
						</a>
						<ul class="dropdown-menu">
							<@shiro.hasPermission name="system:platform:manage">
								<li><a href="${ctx}/system/index.ftl">租户管理</a></li>
								<li class="divider"></li>
							</@shiro.hasPermission>
							<@shiro.hasPermission name="system:menu:menu">
								<li><a href="${ctx}/menu/index.ftl">菜单/按钮权限管理</a></li>
								<li class="divider"></li>
							</@shiro.hasPermission>
							<@shiro.hasPermission name="system:role:manage">						
								<li><a href="${ctx}/role/index.ftl">角色管理</a></li>
								<li class="divider"></li>
							</@shiro.hasPermission>
							<@shiro.hasPermission name="system:user:manage">						
								<li><a href="${ctx}/user/index.ftl">用户管理</a></li>
							</@shiro.hasPermission>
						</ul>
					</li>
				</@shiro.hasPermission>

			</ul>
		</div>
	</div>
</nav>