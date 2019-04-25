<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0, user-scalable=no">
    <meta name="format-detection" content="telephone=no, email=no">
    <meta name="HandheldFriendly" content="true">
    <title>主页面</title>
    
    <link rel="stylesheet" href="${ctx}/css/frozenui.css">
    <link rel="stylesheet" href="${ctx}/css/mainpage.css">

</head>

<body ontouchstart>
    <section class="ui-container">
        
    <div class="index-wrap">
        <div class="header">
            <h1>主界面</h1>
        </div>
        <section id="list">
            <table class="demo-item ui-table ui-border">
                <tbody class="demo-block">
                    <tr class="">
                        <td class="" data-href="/app/equipdata/index">
                            <div>
                                <div class="icon-button icon"></div>
                                <div class="tit">实时检测</div>
                            </div>
                        </td>
                        <td class="" data-href="/app/equipdata/map">
                            <div>
                                <div class="icon-cell icon"></div>
                                <div class="tit">地图显示</div>
                            </div>
                        </td>
                    </tr>
                    <tr class="">
                        <td class="" data-href="/history/index">
                            <div>
                                <div class="icon-dialog icon"></div>
                                <div class="tit">记录查询</div>
                            </div>
                        </td>
                        <td class="" data-href="/analysis/index2">
                            <div>
                                <div class="icon-actionsheet icon"></div>
                                <div class="tit">汇总统计</div>
                            </div>
                        </td>
                    </tr>
                    <tr class="">
                        <td class="" data-href="/datacard/index">
                            <div>
                                <div class="icon-form icon"></div>
                                <div class="tit">系统设置</div>
                            </div>
                        </td>
                        <td class="" data-href="">
                            <div>
                                <div class="icon-grid icon"></div>
                                <div class="tit">功能待加</div>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </section>
    </div>

    </section>
    
    <script src="${ctx}/js/zepto.min.js"></script>
    <script src="${ctx}/js/mainpage.js"></script>
    
</body>
</html>
