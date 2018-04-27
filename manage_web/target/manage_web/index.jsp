<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="lib/bootstrap/css/bootstrap-table.min.css">
    <script type="text/javascript" src="lib/jquery-3.2.1.min.js"></script>
    <title>Hello提交</title>
</head>

<body>
<div style="width: auto;height: 100px;border: dotted">
    <%--<jsp:include page="/user/userInfo.do?id=1"/>
    ok--%>
</div>
<div>
    <form action="/user/uploadFile.do" enctype="multipart/form-data" method="post">
        <input name="file1" type="file"/><br><%--此处的name属性不能少--%>
        <input type="submit" value="提交">
        <input type="reset" value="重置">
    </form>
</div>
<script>
    //code
</script>
</body>
</html>
