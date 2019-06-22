<div class="navbar navbar-default" id="navbar">
    <script type="text/javascript">
        try{ace.settings.check('navbar' , 'fixed')}catch(e){}
    </script>

    <div class="navbar-container col-sm-12" id="navbar-container" style="font-size: 12px;height: 72px; background: white linear-gradient(to top, rgba(0,255,0,0), #0f7ce5);">
        <div class="navbar-header pull-left col-sm-4">
            <a href="#" class="navbar-brand" style="width: 490px;">
                <img src="${ctx}/images/smartlogo.png" style="height: 25px;" class="img-rounded" />
                <small id="systemInfo" style="color: #003366;font-size: 17px;">${systemInfo}</small>
            </a><!-- /.brand -->
        </div><!-- /.navbar-header -->
        <div class="navbar-header pull-left col-sm-5"></div>
        <div class="navbar-header pull-left col-sm-3" style="color:#003366;padding-top: 6px;" id="msg">
        	<span id="welcomeMsg"></span></br>
        	<span id="currentDay"></span></br>
        	<span id="countmsg"></span>
        	<input type="hidden" id="userId" name="userId" value="">
       	</div>
    </div><!-- /.container -->
</div>
<script src="${ctx}/js/jquery-2.0.3.min.js"></script>
<script>
	$.ajax({
        type: "GET",
        dataType: "json",
        url: "${ctx}/equipdata/countMsg",
        success: function(msg){
        	var htmlCoungMsg = "设备总数:" + msg.data.allNums + "，在线:"+msg.data.onLineNums + "，离线:" + msg.data.offLineNums + "，在线率:" + msg.data.onLineNumsRate
        	$("#countmsg").html(htmlCoungMsg);
        	var dayHtml = msg.data.currentDay ;
        	$("#currentDay").html(dayHtml);
        	var systemInfoHtml = "欢迎您：" + msg.data.username + "&nbsp;&nbsp;&nbsp;&nbsp;<a href='${ctx}/admin/logout' style='color:red;'>退出</a>&nbsp;&nbsp;<a href='${ctx}/user/changePwd' style='color:red;'>修改密码</a>";
        	$("#welcomeMsg").html(systemInfoHtml);
        	var systemInfo = msg.data.systemInfo;
        	$("#systemInfo").html(systemInfo);
        	$("#userId").val(msg.data.userId);
        },
		error: function(){
			try{
				console.log('head get count msg error');
			}catch(e){
				
			}
        }
    });
</script>

<script>

    function changePwd(){
        window.location.href = "http://${ctx}/user/changePwd";
    }

</script>