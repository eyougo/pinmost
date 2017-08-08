<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>Pin一下</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <form method="post" action="${rc.contextPath}/register">
                <h2>Pin一下</h2>
                <div class="form-group">
                    <label for="inputUrl" class="sr-only">网页URL</label>
                    <input type="input" id="inputUrl" name="url" class="form-control" placeholder="网页URL" autofocus>
                </div>
                <div class="form-group">
                    <label for="inputTitle" class="sr-only">网页标题</label>
                    <input type="input" id="inputTitle" name="title" class="form-control" placeholder="网页标题" required>
                </div>
                <div class="form-group">
                    <label for="inputSummary" class="sr-only">网页简介</label>
                </div>
                <button class="btn btn-lg btn-primary btn-block" id="pin-button" type="submit">Pin上去</button>
            </form>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
</body>
</html>