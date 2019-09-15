<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<body>
    <p>New user form</p>
    <%--@elvariable id="user" type=""--%>
    <form:form action="/admin/add_user" method="post" modelAttribute="user">
        <form:input path="username" placeholder="username..."/>
        <form:input path="password" placeholder="password..."/>
        <form:input path="email" placeholder="email..."/>
        <button type="submit">Add</button>
    </form:form>
</body>
</html>