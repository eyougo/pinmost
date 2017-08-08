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
                        <div class="col-xs-2 col-md-3 text-right">
                            <a href="${rc.getContextUrl("/website/pin")}" class="btn btn-primary">&nbsp;Pin一下&nbsp;</a>
                        </div>
                    </div>
                </div>
                <div class="list-group" id="website-list">
                    <div class="list-group-item">
                        <h4 class="list-group-item-heading">
                            <div class="row">
                                <div class="col-xs-10 col-md-9 text-hidden">
                                    <a href="" style="">这是一个牛逼的标题哈哈哈哈这是一个牛逼的标题哈哈哈哈牛逼的标题哈哈哈</a>
                                </div>
                                <div class="col-xs-2 col-md-3 text-right text-muted">
                                    <small class="hidden-xs"><span class="glyphicon glyphicon-hand-up" aria-hidden="true"></span> 10000</small>
                                    &nbsp;
                                    <small class="hidden-xs"><span class="glyphicon glyphicon-star" aria-hidden="true"></span> 10000</small>
                                </div>
                            </div>
                        </h4>
                        <div class="list-group-item-text">
                            <p class="text-muted">
                            Donec id elit non mi porta gravida at eget metus. Maecenas sed diam
                            eget risus varius blandit.Donec id elit non mi porta gravida at eget metus. Maecenas sed diam
                            eget risus varius blandit.Donec id elit non mi porta gravida at eget metus. Maecenas sed diam
                            eget risus varius blandit.
                            </p>
                            <div class="row">
                                <div class="col-xs-10 col-md-9 text-muted">
                                    来自：eyougo &nbsp;&nbsp;&nbsp; 时间：2017-03-19 22:22
                                </div>
                                <span class="col-xs-2 col-md-3 text-right">
                                    <a>
                                    <span class="glyphicon glyphicon-star" aria-hidden="true"></span> 收藏 &nbsp;&nbsp;
                                    </a>
                                    <a>
                                    <span class="glyphicon glyphicon-share" aria-hidden="true"></span> 分享
                                    </a>
                                </span>
                            </div>
                        </div>
                    </div>


                    <div class="list-group-item">
                        <h4 class="list-group-item-heading">
                            <div class="row">
                                <div class="col-xs-10 col-md-9 text-hidden">
                                    <a href="" style="">这是一个牛逼的标题哈哈哈哈这是一个牛逼的标题哈哈哈哈牛逼的标题哈哈哈</a>
                                </div>
                                <div class="col-xs-2 col-md-3 text-right text-muted">
                                    <small class="hidden-xs"><span class="glyphicon glyphicon-hand-up" aria-hidden="true"></span> 10000</small>
                                    &nbsp;
                                    <small class="hidden-xs"><span class="glyphicon glyphicon-star" aria-hidden="true"></span> 10000</small>
                                </div>
                            </div>
                        </h4>
                        <div class="list-group-item-text">
                            <p class="text-muted">
                            </p>
                            <div class="row">
                                <div class="col-xs-10 col-md-9 text-muted">
                                    来自：eyougo &nbsp;&nbsp;&nbsp; 时间：2017-03-19 22:22
                                </div>
                                <span class="col-xs-2 col-md-3 text-right">
                                    <a>
                                    <span class="glyphicon glyphicon-star" aria-hidden="true"></span> 收藏 &nbsp;&nbsp;
                                    </a>
                                    <a>
                                    <span class="glyphicon glyphicon-share" aria-hidden="true"></span> 分享
                                    </a>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <ul class="pager">
                <li><a href="#">Previous</a></li>
                <li><a href="#">Next</a></li>
            </ul>
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
                        &nbsp;
                        <small class="hidden-xs"><span class="glyphicon glyphicon-star" aria-hidden="true"></span> ${r'${starCount}'}</small>
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
                        <a>
                        <span class="glyphicon glyphicon-star" aria-hidden="true"></span> 收藏 &nbsp;&nbsp;
                        </a>
                        <a>
                        <span class="glyphicon glyphicon-share" aria-hidden="true"></span> 分享
                        </a>
                    </span>
                </div>
            </div>
        </div>
</script>
<script type="text/javascript">
    function loadWebsiteList(offset) {
        $.ajax({
            url: "${rc.contextPath}/website/mostNew",
            data: {
                offset: offset
            },
            success: function (response) {
                if (response.success){
                    var data = response.data;
                    $("#websiteTemplate").tmpl(data).appendTo('#website-list');
                } else {
                    alert(response.message);
                }
            },
            error: function (jqXHR, status, errorThrown) {
                alert(errorThrown + " : " + jqXHR.responseJSON.message);
            },
            type:'post',
            dataType:'json'
        });
    }
    
    $(document).ready(function () {
        console.log("start load");
        loadWebsiteList(0);
        console.log("end load");
    });
</script>
</body>
</html>