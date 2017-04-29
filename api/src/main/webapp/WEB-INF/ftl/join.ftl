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
            <form method="post" action="${rc.contextPath}/register">
                <h2>请注册</h2>
                <div class="form-group">
                    <label for="inputUsername" class="sr-only">用户名</label>
                    <input type="input" id="inputUsername" name="username" class="form-control" oninvalid="this.setCustomValidity('请输入用户名')" oninput="setCustomValidity('')" placeholder="用户名" required autofocus>
                </div>
                <div class="form-group">
                    <label for="inputEmail" class="sr-only">Email</label>
                    <input type="input" id="inputEmail" name="email" class="form-control" oninvalid="this.setCustomValidity('请输入Email')" oninput="setCustomValidity('')" placeholder="Email" required>
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="sr-only">密码</label>
                    <input type="password" id="inputPassword" name="password" class="form-control" oninvalid="this.setCustomValidity('请输入密码')" oninput="setCustomValidity('')" placeholder="密码" required>
                </div>
                <button class="btn btn-lg btn-primary btn-block" id="join-button" type="submit">注册</button>
            </form>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
</body>
</html>