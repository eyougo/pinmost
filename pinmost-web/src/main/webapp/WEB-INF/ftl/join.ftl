<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>注册新用户</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-4">
            <h2>请注册</h2>
            <form id="join-form" method="post" action="${rc.contextPath}/register">
                <div class="form-group">
                    <label for="inputUsername">用户名</label>
                    <input type="text" id="inputUsername" name="username" class="form-control" placeholder="用户名">
                </div>
                <div class="form-group">
                    <label for="inputEmail">Email</label>
                    <input type="text" id="inputEmail" name="email" class="form-control" placeholder="Email">
                </div>
                <div class="form-group">
                    <label for="inputPassword">密码</label>
                    <input type="password" id="inputPassword" name="password" class="form-control" placeholder="密码">
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
<script type="text/javascript">

    $(document).ready(function () {
        $("#join-form").validate({
            ignore: [],
            rules: {
                username: {
                    required: true,
                    minlength: 3,
                    maxlength: 32,
                    remote: {
                        url: "${rc.contextPath}/check/username",
                        type: "post",
                        data: {
                            username: function () {
                                return $("#inputUsername").val();
                            }
                        },
                        dataFilter: function (response) {
                            var json = JSON.parse(response);
                            if (json.success === true) {
                                return true;
                            }
                            return "\"" + json.message + "\"";
                        }
                    }
                },
                email: {
                    required: true,
                    email: true
                },
                password: {
                    required: true,
                    maxlength: 32
                }
            },
            messages: {
                username: {
                    required: "用户名不能为空"
                },
                email: {
                    required: "Email电子邮箱不能为空",
                    email: "请输入正确的Email电子邮箱"
                },
                password: {
                    required: "密码不能为空",
                    maxlength: "密码不能超过32位"
                }
            },
            errorElement: "span",
            errorClass: "text-danger",
            onfocusout: function( element ) {
                if ( !this.checkable( element ) ) {
                    this.element( element );
                }
            }
        });
    });
</script>
</body>
</html>