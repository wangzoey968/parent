<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../lib/bootstrap/css/bootstrap-table.min.css">
    <script type="text/javascript" src="../lib/jquery-3.2.1.min.js"></script>
    <title>Hello World!中国</title>
</head>

<body>
<div style="width: auto;height: 100px;border: dotted">
    <jsp:include page="jsp/user/userInfo.jsp"/>
</div>
<div style="width: auto;height: 200px;border: dashed">
    <c:import url="jsp/user/userInfo.jsp"/>
</div>
<script>
    //code
</script>
</body>
</html>
