<div class="navbar navbar-default" id="navbar">
    <script type="text/javascript">
        try{ace.settings.check('navbar' , 'fixed')}catch(e){}
    </script>

    <div class="navbar-container col-sm-12" id="navbar-container">
        <div class="navbar-header pull-left col-sm-4">
            <a href="#" class="navbar-brand">
                <img src="${ctx}/images/smartlogo.png" style="height: 25px;" class="img-rounded" /><small id="systemInfo">高港区农业综合水价改革远程监测系统</small>
            </a><!-- /.brand -->
        </div><!-- /.navbar-header -->
        <div class="navbar-header pull-left col-sm-4"></div>
        <div class="navbar-header pull-left col-sm-3" style="color:white;" id="msg">
        	<span id="currentDay"></span></br>
        	<span id="countmsg"></span>
       	</div>
		
        <div class="navbar-header pull-right col-sm-1" role="navigation">
            <ul class="nav ace-nav">
                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo" src="${ctx}/avatars/user.jpg" alt="Jason's Photo" />
                        <span class="user-info"><small>欢迎光临,</small><@shiro.principal property='name'/></span>
                        <i class="icon-caret-down"></i>
                    </a>
                    <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="#">
                                <i class="icon-cog"></i>设置</a>
                        </li>
                        <li>
                            <a href="#">
                                <i class="icon-user"></i>个人资料</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="${ctx}/admin/logout"><i class="icon-off"></i>退出</a>
                        </li>
                    </ul>
                </li>
            </ul><!-- /.ace-nav -->
        </div><!-- /.navbar-header -->
    </div><!-- /.container -->
</div>
<script src="${ctx}/js/jquery-2.0.3.min.js"></script>
<script>
	$.ajax({
        type: "GET",
        dataType: "json",
        url: "${ctx}/equipdata/coungMsg",
        success: function(msg){
        	var htmlCoungMsg = '设备总数:'+ msg.data.allNums + '，在线:' + msg.data.onLineNums +'，离线:' + msg.data.offLineNums + '，在线率:' + msg.data.onLineNumsRate
        	$("#countmsg").html(htmlCoungMsg);
        	var dayHtml = msg.data.currentDay ;
        	$("#currentDay").html(dayHtml);
        	var systemInfoHtml = msg.data.systemInfo ;
        	$("#systemInfo").html(systemInfoHtml);
        },
		error: function(){
			try{
				console.log('head get count msg error');
			}catch(e){
				
			}
        }
    });
</script>