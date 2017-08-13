<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>首页 - 点击最多的Pin</title>
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
                            <h4>点击最多的Pin</h4>
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
<script type="text/javascript">
    $(document).ready(function () {
        loadWebsiteList("/mostClick",0);
        $(window).scroll(function () {
            if ($(document).height() - $(window).height() - $(window).scrollTop() < 50 && nextOffset >= 0) {
                console.log("nextOffset:" + nextOffset);
                loadWebsiteList("/mostClick", nextOffset);
            }
        });
    });
</script>
</body>
</html>