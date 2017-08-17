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
                            data-target="#bs-navbar-collapse" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${rc.contextPath}/">PinMost.com</a>
                </div>
                <div class="collapse navbar-collapse" id="bs-navbar-collapse">
                    <ul class="nav navbar-nav" id="navbar">
                        <li id="nav-most-new"><a href="${rc.contextPath}/">&nbsp;最新发布的Pin&nbsp;</a></li>
                        <li id="nav-most-click"><a href="${rc.contextPath}/most_click">&nbsp;点击最多的Pin&nbsp;</a></li>
                        <#--
                        <li><a href="#">&nbsp;最多收藏&nbsp;</a></li>-->
                    </ul>
                    <div class="navbar-right">
                        <ul class="nav navbar-nav">
                        <#if Session['account']>
                            <li id="nav-my"><a href="${rc.getContextUrl("/" + Session['account'].username)}">&nbsp;我的&nbsp;</a></li>
                            <li><a href="${rc.getContextUrl("/logout")}">&nbsp;退出&nbsp;</a></li>
                        <#else>
                            <li id="nav-login"><a href="${rc.contextPath}/login">&nbsp;登录&nbsp;</a></li>
                            <li id="nav-join"><a href="${rc.contextPath}/join">&nbsp;注册&nbsp;</a></li>
                        </#if>
                        </ul>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="${rc.contextPath}/pin"><button type="button" class="btn btn-primary navbar-btn">Pin一下</button></a>
                    </div>
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
<script type="text/javascript">
    $(document).ready(function () {
        if ($(window).scrollTop() < 500) {
            $("#backtop").hide();
        }

        $(window).scroll(function () {
            if ($(window).scrollTop() < 500) {
                $("#backtop").fadeOut();
            } else {
                $("#backtop").fadeIn();
            }
        });

        $("#backtop").click(function() {
            $(document).scrollTop(0);
        });
    });

    function setActiveNavbar(id) {
        $(".navbar-nav").children("li").each(function () {
            $(this).removeClass("active");
        });
        $(id).addClass("active");
    }

    function loadWebsiteList(loadUrl, listDiv, nextPage) {
        if (!loadUrl || loadUrl == "") {
            return;
        }
        $.ajax({
            url: loadUrl,
            async: false,
            success: function (response) {
                if ($(response).find("#website-list").length > 0) {
                    var list = $(response).find("#website-list").html();
                    $(list).appendTo(listDiv);
                }
                if ($(response).find("#nextPage").length > 0) {
                    var nextPageUrl = $(response).find("#nextPage").attr("href");
                    $(nextPage).attr("href", nextPageUrl);
                    $(nextPage).show();
                } else {
                    $(nextPage).attr("href", "");
                    $(nextPage).hide();
                }
            },
            error: function (jqXHR, status, errorThrown) {
                $(nextPage).attr("href", "");
                $(nextPage).hide();
            },
            type:'post',
            dataType:'html'
        });
    }
</script>
<script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
                (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
            m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-104688192-1', 'auto');
    ga('send', 'pageview');

</script>
</body>
</html>