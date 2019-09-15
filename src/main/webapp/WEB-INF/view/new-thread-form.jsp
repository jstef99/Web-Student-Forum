<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<body>
<%--@elvariable id="thread" type=""--%>
<form:form action="/admin/new_thread" modelAttribute="thread" method="post">
    Name:<form:input path="name"/>
    <button type="submit">Add new thread</button>
</form:form>
</body>
</html>