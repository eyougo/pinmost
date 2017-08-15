<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>注册新用户</title>
    <script src="https://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="${rc.contextPath}/js/jquery.validate.js"></script>
</head>
<body>
<form id="myform">
    <input name="test1" type="text" /><br />
    <input name="test2" type="text" /><br />
    <input name="test3" type="text" /><br />
    <input name="test4" type="text" /><br />
    <input name="test5" type="text" /><br />
    <input type="submit" />
</form>
<script type="text/javascript">
    $(document).ready(function() {

        $('#myform').validate({
            rules: {
                test1: {
                    required: true
                },
                test2: {
                    required: true
                }
            }
        });

    });
</script>
</body>
</html>