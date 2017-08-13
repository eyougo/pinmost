<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>首页 - 最新加入</title>
    <script src="${rc.contextPath}/js/jquery.tmpl.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-10 col-md-9 panel-title">
                            <h4>最新加入</h4>
                        </div>
                    </div>
                </div>
                <div class="list-group" id="website-list">
                </div>
            </div>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>
<script id='websiteTemplate' type='text/x-jquery-tmpl'>
        <div class="list-group-item">
            <h4 class="list-group-item-heading">
                <div class="row">
                    <div class="col-xs-10 col-md-9 text-hidden">
                        <a href="" style="">${r'${title}'}</a>
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


    function loadWebsiteList(offset) {
        $.ajax({
            url: "${rc.contextPath}/website/mostNew",
            async: false,
            data: {
                offset: offset
            },
            success: function (response) {
                if (response.success){
                    var data = response.data;
                    $("#websiteTemplate").tmpl(data).appendTo('#website-list');
                    nextOffset = response.pager.nextOffset;

                    console.log("nextOffset1:" + nextOffset);
                    if (nextOffset > -1 && $(document).height() <= $(window).height()) {
                        loadWebsiteList(nextOffset);
                    }
                } else {
                    offset = -1;
                }
            },
            error: function (jqXHR, status, errorThrown) {
                offset = -1;
            },
            type:'post',
            dataType:'json'
        });
    }
    
    $(document).ready(function () {
        loadWebsiteList(nextOffset);
        $(window).scroll(function () {
            if ($(document).height() - $(window).height() - $(window).scrollTop() < 50 && nextOffset >= 0) {
                console.log("nextOffset:" + nextOffset);
                loadWebsiteList(nextOffset);
            }
        });
    });
</script>
</body>
</html>