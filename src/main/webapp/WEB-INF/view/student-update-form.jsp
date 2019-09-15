<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>

<html>
<body>
    <%--@elvariable id="user" type=""--%>
    <form:form action="/admin/update_user_submit" method="post" modelAttribute="user">
        Id:<form:input path="id"/>
        Username:<form:input path="username"/>
        Password:<form:input path="password"/>
        Email:<form:input path="email"/>
        <form:input type="hidden" path="roles"/>

        <button type="submit">Update</button>
    </form:form>
</body>
</html>