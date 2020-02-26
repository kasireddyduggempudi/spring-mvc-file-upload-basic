<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 26/02/20
  Time: 12:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upload file</title>
</head>
<body>
    <form action="upload" method="POST" enctype="multipart/form-data">  <!-- /upload doesnt work. ./upload works -->
        username: <input type="text" name="username" id="username" placeholder="username" /><br /><br />
        select file: <input type="file" name="file" id="file" /><br /><br />
        <input type="submit" name="submit" value="submit" />
    </form>
</body>
</html>
