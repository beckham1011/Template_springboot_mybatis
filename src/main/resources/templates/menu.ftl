<div class="sidebar" id="sidebar">
    <script type="text/javascript">
        try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
    </script>

    <div class="sidebar-shortcuts" id="sidebar-shortcuts">
        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
            <button class="btn btn-success">
                <i class="icon-signal"></i>
            </button>

            <button class="btn btn-info">
                <i class="icon-pencil"></i>
            </button>

            <button class="btn btn-warning">
                <i class="icon-group"></i>
            </button>

            <button class="btn btn-danger">
                <i class="icon-cogs"></i>
            </button>
        </div>

        <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
            <span class="btn btn-success"></span>

            <span class="btn btn-info"></span>

            <span class="btn btn-warning"></span>

            <span class="btn btn-danger"></span>
        </div>
    </div><!-- #sidebar-shortcuts -->

    <ul class="nav nav-list">
        <li class="active">
            <a href="/admin/index.ftl">
                <i class="icon-dashboard"></i>
                <span class="menu-text"> 控制台 </span>
            </a>
        </li>



        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-desktop"></i>
                <span class="menu-text"> 实时监测 </span>
                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li>
                    <a href="${ctx}/equipdata/index.ftl">
                        <i class="icon-double-angle-right"></i> 实时数据
                    </a>
                </li>
                <li>
                    <a href="${ctx}/equipdata/map.ftl">
                        <i class="icon-double-angle-right"></i> 电子地图
                    </a>
                </li>
            </ul>
        </li>


        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-tag"></i>
                <span class="menu-text"> 历史查询 </span>
                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li>
                    <a href="${ctx}/history/index.ftl">
                        <i class="icon-double-angle-right"></i> 历史查询
                    </a>
                </li>
                <li>
                    <a href="${ctx}/menu/index.ftl">
                        <i class="icon-double-angle-right"></i> 报警查询
                    </a>
                </li>
            </ul>
        </li>


        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-tag"></i>
                <span class="menu-text"> 统计分析</span>
                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li>
                    <a href="${ctx}/user/index.ftl">
                        <i class="icon-double-angle-right"></i> 通用报表
                    </a>
                </li>
                <li>
                    <a href="${ctx}/menu/index.ftl">
                        <i class="icon-double-angle-right"></i> 曲线分析
                    </a>
                </li>
            </ul>
        </li>


        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-tag"></i>
                <span class="menu-text"> 信息管理</span>
                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li>
                    <a href="${ctx}/user/index.ftl">
                        <i class="icon-double-angle-right"></i> 数据卡维护
                    </a>
                </li>
                <li>
                    <a href="${ctx}/role/index.ftl">
                        <i class="icon-double-angle-right"></i> 申报记录
                    </a>
                </li>
                <li>
                    <a href="${ctx}/menu/index.ftl">
                        <i class="icon-double-angle-right"></i> 维修记录
                    </a>
                </li>
            </ul>
        </li>




        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-list"></i>
                <span class="menu-text"> 用户中心 </span>
                <b class="arrow icon-angle-down"></b>
            </a>
        </li>

    </ul><!-- /.nav-list -->

    <div class="sidebar-collapse" id="sidebar-collapse">
        <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
    </div>

    <script type="text/javascript">
        try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
    </script>
</div>
