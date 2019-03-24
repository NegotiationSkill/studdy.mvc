<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/11
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/testDelete" method="post">
        <input type="hidden" name="_method" value="DELETE">
        <input type="submit" value="删"/>
    </form>
    <form action="/testPut" method="post">
        <input type="hidden" name="_method" value="PUT">
        <input type="submit" value="改"/>
    </form>
    <form action="/testParam">
        <input type="text" name="userName" />
        <input type="submit" value="参数"/>
    </form>
    <form action="/testHeader">
        <input type="submit" value="Header"/>
    </form>
    <form action="/objectTest">
        id:<input type="text" name="id"/>
        name:<input type="text" name="name"/>
        age:<input type="text" name="age"/>
        home:<input type="text" name="Address.homeAddress"/>
        school:<input type="text" name="Address.schoolAddress"/>
        <input type="submit" value="object"/>
    </form>
    <a href="/modelAndViewTest">TestModelAndView</a>
    <br/>
    <br/>
    <br/>
    数据校验
    <form action="/TestValidator">
        name:<input type="text" name="name">
        birth:<input type="text" name="birth">
        age:<input type="text" name="age">
        <input type="submit" name="提交">
    </form>
    <br/>
    <br/>
    <br/>
    <form action="/fileUpdown" enctype="multipart/form-data" method="post">
        浏览<input type="file" name="file"/>
        desc:<input type="text" name="desc"/>
        <input type="submit" value="提交"/>
    </form>

</body>
</html>
