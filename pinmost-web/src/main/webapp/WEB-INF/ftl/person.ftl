<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>我的</title>
    <script src="${rc.contextPath}/js/jquery.tmpl.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">我发布的Pin</h3>
                </div>
                <div class="list-group" id="pin-list">

                </div>
            </div>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>
<script id='pinTemplate' type='text/x-jquery-tmpl'>
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
    var nextOffset = 0;
    $(document).ready(function () {
        nextOffset = loadWebsiteList("/pinList",0, "#pin-list");
        $(window).scroll(function () {
            if ($(document).height() - $(window).height() - $(window).scrollTop() < 50 && nextOffset >= 0) {
                console.log("nextOffset:" + nextOffset);
                nextOffset = loadWebsiteList("/pinList", nextOffset, "#pin-list");
            }
        });
    });
</script>
</body>
</html>