<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<body>
<%--@elvariable id="email" type=""--%>
<form:form action="/admin/add_allowed_email" modelAttribute="email" method="post">
    New allowed Email:<form:input path="name"/>
    <button type="submit">Add</button>
</form:form>

</body>
</html>