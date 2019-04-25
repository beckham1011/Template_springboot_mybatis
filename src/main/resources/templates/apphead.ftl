<div class="navbar navbar-default" id="navbar">
    <script type="text/javascript">
        try{ace.settings.check('navbar' , 'fixed')}catch(e){}
    </script>

    <div class="navbar-container col-sm-12" id="navbar-container" style="font-size: 12px;height: 72px; width: 100%; background: white linear-gradient(to top, rgba(0,255,0,0), #0f7ce5);">
        <div class="navbar-header pull-center col-sm-3">
            <a href="#" class="navbar-brand" style="width: 100%;text-align: center;">
                <small id="systemInfo" style="color: #003366;font-size: 17px;">实时数据监测</small>
		        <div class="navbar-header pull-left col-sm-3" style="color:#003366;padding-top: 6px;font-size: 11px;" id="msg">
		        	<span id="countmsg"></span>
		        	<input type="hidden" id="userId" name="userId" value="">
		       	</div>
            </a><!-- /.brand -->
        </div><!-- /.navbar-header -->
    </div><!-- /.container -->
</div>
<script src="${ctx}/js/jquery-2.0.3.min.js"></script>
<script>
	$.ajax({
        type: "GET",
        dataType: "json",
        url: "${ctx}/equipdata/countMsg",
        success: function(msg){
        	var htmlCoungMsg = '设备总数:558，在线:172，离线:386，在线率:30.77%'
        	$("#countmsg").html(htmlCoungMsg);
        	var dayHtml = msg.data.currentDay ;
        	$("#currentDay").html(dayHtml);
        	var systemInfoHtml = "欢迎您：" + msg.data.username + "&nbsp;&nbsp;&nbsp;&nbsp;<a href='${ctx}/admin/logout' style='color:red;'>退出</a>&nbsp;&nbsp;<a href='#' onclick='changePwd()' style='color:red;'>修改密码</a>";
        	$("#welcomeMsg").html(systemInfoHtml);
        	var systemInfo = msg.data.systemInfo;
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
        layer.open({
            type: 2,
            title: '修改密码',
            shadeClose: true,
            shade: false,
            area: ['900px', '350px'],
            content: '${ctx}/user/changePwd?id=' + $("#userId").val(),
            end: function(index){
                layer.close(index);
            }
        });
    }


</script>