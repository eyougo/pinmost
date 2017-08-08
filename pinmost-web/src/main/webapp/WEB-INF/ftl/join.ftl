<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>注册新用户</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <h2>请注册</h2>
            <form method="post" action="${rc.contextPath}/register">
                <div class="form-group">
                    <label for="inputUsername" class="sr-only">用户名</label>
                    <input type="input" id="inputUsername" name="username" class="form-control" placeholder="用户名" required autofocus>
                </div>
                <div class="form-group">
                    <label for="inputEmail" class="sr-only">Email</label>
                    <input type="input" id="inputEmail" name="email" class="form-control" placeholder="Email" required>
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="sr-only">密码</label>
                    <input type="password" id="inputPassword" name="password" class="form-control" placeholder="密码" required>
                </div>
                <#if errorResult??>
                    <div class="alert alert-danger alert-dismissible" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        ${errorResult.message}
                    </div>
                </#if>
                <button class="btn btn-lg btn-primary btn-block" id="join-button" type="submit">注册</button>
            </form>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
</body>
</html>