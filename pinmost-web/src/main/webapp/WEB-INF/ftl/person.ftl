<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title><#if sessionAccount?? && sessionAccount.id == account.id>我的<#else>${account.username}</#if></title>
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
                            <h4><#if sessionAccount?? && sessionAccount.id == account.id>我<#else>${account.username}</#if>发布的Pin</h4>
                        </div>
                    </div>
                </div>
                <div class="list-group" id="website-list">
                <#if dataResult?? && dataResult.getSuccess()>
                    <#list dataResult.data as website>
                        <div class="list-group-item">
                            <h4 class="list-group-item-heading">
                                <div class="row">
                                    <div class="col-xs-10 col-md-9 text-hidden">
                                        <a href="${rc.contextPath}/click?id=${website.id}" target="_blank">${website.title}</a>
                                    </div>
                                    <div class="col-xs-2 col-md-3 text-right text-muted">
                                        <small class="hidden-xs"><span class="glyphicon glyphicon-hand-up" aria-hidden="true"></span> ${website.clickCount}</small>
                                    <#-- &nbsp;
                                    <small class="hidden-xs"><span class="glyphicon glyphicon-star" aria-hidden="true"></span> ${r'${starCount}'}</small>
                                    -->
                                    </div>
                                </div>
                            </h4>
                            <div class="list-group-item-text">
                                <p>${website.summary}
                                </p>
                                <div class="row">
                                    <div class="col-xs-10 col-md-9 text-muted">
                                        时间：${website.createdAt}
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
                    </#list>
                <#elseif dataResult??>
                ${dataResult.message}
                </#if>
                </div>
                <nav aria-label="...">
                    <ul class="pager">
                    <#if dataResult??>
                        <#if dataResult.pager.previousOffset gte 0>
                            <li><a href="${rc.contextPath}/${account.username}/${dataResult.pager.previousOffset}" id="prePage">Previous</a></li>
                        </#if>
                        <#if dataResult.pager.nextOffset gte 0>
                            <li><a href="${rc.contextPath}/${account.username}/${dataResult.pager.nextOffset}" id="nextPage">Next</a></li>
                        </#if>
                    </#if>
                    </ul>
                </nav>
            </div>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        <#if sessionAccount?? && sessionAccount.id == account.id>
        setActiveNavbar("#nav-my");
        </#if>
        /*$(window).scroll(function () {
            if ($(document).height() - $(window).height() - $(window).scrollTop() < 100 && $("#nextPage").length > 0 && $("#nextPage").attr("href") != "") {
                var nextPageUrl = $("#nextPage").attr("href");
                loadWebsiteList(nextPageUrl, "#website-list", "#nextPage");
            }
        });*/
    });
</script>
</body>
</html>