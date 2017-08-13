<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>PinMost.com -
        <sitemesh:write property="title"/>
    </title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
                  integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
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

        #backtop{ /* back to top button */
            position: fixed;
            bottom: 20px; /* 小按钮到浏览器底边的距离 */
            right: 150px; /* 小按钮到浏览器右边框的距离 */
            z-index: 1000;
        }
    </style>
    <sitemesh:write property="head"/>
</head>
<body style="padding-top:70px; position: relative;">
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
                        <li <#if rc.requestUri == rc.getContextUrl("/") || rc.requestUri == rc.getContextUrl("/index")>class="active"</#if>><a href="${rc.contextPath}">&nbsp;最新发布的Pin&nbsp;<span class="sr-only">(current)</span></a></li>
                        <li <#if rc.requestUri == rc.getContextUrl("/most_click")>class="active"</#if>><a href="${rc.contextPath}/most_click">&nbsp;点击最多的Pin&nbsp;</a></li>
                        <#--
                        <li><a href="#">&nbsp;最多收藏&nbsp;</a></li>-->
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                    <#if Session['account']>
                        <#--<li><a href="${rc.getContextUrl("/" + Session['account'].username)}">&nbsp;我的&nbsp;</a></li>-->
                        <li><a href="${rc.getContextUrl("/logout")}">&nbsp;退出&nbsp;</a></li>
                    <#else>
                        <li><a href="${rc.contextPath}/login">&nbsp;登录&nbsp;</a></li>
                        <li><a href="${rc.contextPath}/join">&nbsp;注册&nbsp;</a></li>
                    </#if>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="${rc.contextPath}/pin"><button type="button" class="btn btn-primary navbar-btn">Pin一下</button></a>
                    </ul>
                </div>
            </div>
            <div class="col-md-1"></div>
        </div>
    </div>
</nav>
<sitemesh:write property="body"/>
<div id="backtop">
    <button type="button" class="btn btn-info">
        <span class="glyphicon glyphicon-triangle-top" aria-hidden="true"></span>
    </button>
</div>
<script id='websiteTemplate' type='text/x-jquery-tmpl'>
        <div class="list-group-item">
            <h4 class="list-group-item-heading">
                <div class="row">
                    <div class="col-xs-10 col-md-9 text-hidden">
                        <a href="${rc.contextPath}/click?id=${r'${id}'}" target="_blank">${r'${title}'}</a>
                    </div>
                    <div class="col-xs-2 col-md-3 text-right text-muted">
                        <small class="hidden-xs"><span class="glyphicon glyphicon-hand-up" aria-hidden="true"></span> ${r'${clickCount}'}</small>
                        <#-- &nbsp;
                        <small class="hidden-xs"><span class="glyphicon glyphicon-star" aria-hidden="true"></span> ${r'${starCount}'}</small>
                        -->
                    </div>
                </div>
            </h4>
            <div class="list-group-item-text">
                <p>${r'${summary}'}
                </p>
                <div class="row">
                    <div class="col-xs-10 col-md-9 text-muted">
                        来自: ${r'${username}'} &nbsp;&nbsp;时间：${r'${createdAt}'}
                    </div>
                    <span class="col-xs-2 col-md-3 text-right">
                        <#--
                        <a>
                        <span class="glyphicon glyphicon-star" aria-hidden="true"></span> 收藏 &nbsp;&nbsp;
                        </a>
                        <a>
                        <span class="glyphicon glyphicon-share" aria-hidden="true"></span> 分享
                        </a>
                        -->
                    </span>
                </div>
            </div>
        </div>
</script>
<script type="text/javascript">
    $(document).ready(function () {
        if ($(window).scrollTop() < 300) {
            $("#backtop").hide();
        }

        $(window).scroll(function () {
            if ($(window).scrollTop() < 300) {
                $("#backtop").fadeOut();
            } else {
                $("#backtop").fadeIn();
            }
        });

        $("#backtop").click(function() {
            $(document).scrollTop(0);
        });
    });

    var nextOffset = 0;

    function loadWebsiteList(loadUrl, offset) {
        $.ajax({
            url: "${rc.contextPath}" + loadUrl,
            async: false,
            data: {
                offset: offset
            },
            success: function (response) {
                if (response.success){
                    var data = response.data;
                    $("#websiteTemplate").tmpl(data).appendTo('#website-list');
                    nextOffset = response.pager.nextOffset;
                    if (nextOffset > -1 && $(document).height() <= $(window).height()) {
                        loadWebsiteList(loadUrl, nextOffset);
                    }
                } else {
                    nextOffset = -1;
                }
            },
            error: function (jqXHR, status, errorThrown) {
                nextOffset = -1;
            },
            type:'post',
            dataType:'json'
        });
    }
</script>
</body>
</html>