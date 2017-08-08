<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>PinMost.com -
        <sitemesh:write property="title"/>
    </title>
    <link rel="stylesheet" href="${rc.contextPath}/css/bootstrap.css">
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="${rc.contextPath}/js/bootstrap.js"></script>
    <style media="screen" type="text/css">
        .text-hidden {
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
        }
        a:hover {
            text-decoration:none;
            cursor: pointer;
        }
    </style>
    <sitemesh:write property="head"/>
</head>
<body style="padding-top:70px;">
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-10">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                            data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${rc.contextPath}">PinMost.com</a>
                </div>
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="${rc.contextPath}">&nbsp;最新加入&nbsp;<span class="sr-only">(current)</span></a></li>
                        <li><a href="#">&nbsp;最多点击&nbsp;</a></li>
                        <li><a href="#">&nbsp;最多收藏&nbsp;</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                    <#if Session['account']>
                        <li><a href="${rc.getContextUrl("/" + Session['account'].username)}">&nbsp;我的&nbsp;</a>
                        <li><a href="${rc.getContextUrl("/logout")}">&nbsp;退出&nbsp;</a>
                    <#else >
                        <li><a href="${rc.contextPath}/login">&nbsp;登录&nbsp;</a></li>
                        <li><a href="${rc.contextPath}/join">&nbsp;注册&nbsp;</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
            <div class="col-md-1"></div>
        </div>
    </div>
</nav>
<sitemesh:write property="body"/>
</body>
</html>