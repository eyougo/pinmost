<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>登录</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <form method="post" action="${rc.contextPath}/session">
                <#if redirect??>
                <input type="hidden" name="redirect" value="${redirect}">
                </#if>
                <h2>请登录</h2>
                <div class="form-group">
                    <label for="inputLogin" class="sr-only">Email</label>
                    <input type="input" id="inputLogin" name="login" class="form-control" placeholder="Email" required autofocus>
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="sr-only">密码</label>
                    <input type="password" id="inputPassword" name="password" class="form-control" placeholder="密码" required>
                </div>
                <button class="btn btn-lg btn-primary btn-block" id="login-button" type="submit">登录</button>
            </form>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
</body>
</html>