<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>Pin一下</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <form method="post" action="${rc.contextPath}/website/pinSubmit">
                <h2>Pin一下</h2>
                <div class="form-group">
                    <label for="inputUrl">网页的URL地址</label>
                    <input type="input" id="inputUrl" name="url" class="form-control" placeholder="请输入网页的URL地址">
                </div>
                <div class="form-group">
                    <label for="inputTitle">网页的标题</label>
                    <input type="input" id="inputTitle" name="title" class="form-control" placeholder="请输入网页的标题">
                </div>
                <div class="form-group">
                    <label for="inputSummary">网页的简介</label>
                    <textarea id="inputSummary" name="summary" class="form-control"  placeholder="请输入网页的简介"></textarea>
                </div>
                <div id="error" class="alert alert-danger alert-dismissible <#if errorResult??>show<#else>hide</#if>" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <#if errorResult??>
                        ${errorResult.message}
                    </#if>
                </div>
                <button class="btn btn-lg btn-primary btn-block" id="pin-button" type="submit">Pin上去</button>
            </form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $("#inputUrl").change(function () {
            var url = $(this).val();
            if (url.trim() == "") {
                return;
            }
            $("#error").addClass("hide");
            $.ajax({
                url: "${rc.contextPath}/website/get",
                data: {
                    url: url
                },
                success: function (response) {
                    if (response.success){
                        var data = response.data;
                        $("#inputTitle").val(data.title);
                        $("#inputSummary").val(data.summary);
                    } else {
                        $("#error").text(response.message);
                        $("#error").removeClass("hide");
                    }
                },
                error: function (jqXHR, status, errorThrown) {
                    $("#error").text(errorThrown + " : " + jqXHR.responseJSON.message);
                    $("#error").removeClass("hide");
                },
                type:'post',
                dataType:'json'
            });
        });
    });
</script>
</body>
</html>