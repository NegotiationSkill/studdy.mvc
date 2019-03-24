<%@ page pageEncoding="UTF-8"  %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/11
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <fmt:message key="resource.exit"></fmt:message>
        <fmt:message key="resource.welcome"></fmt:message>
        hello world!
       ${requestScope.student.name}
        ----------------------------
        ${requestScope.student1.name}
        ${requestScope.student2.name}
        ${requestScope.studnet4.name}

        ${requestScope.ModelAttribute}
=========================================================
============================================================
        ${sessionScope.student.name}
        ----------------------------
        ${sessionScope.student1.name}
        ${sessionScope.student2.name}
        ${sessionScope.studnet4.name}
</body>
</html>
