<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>

<html>
<body>
<%--@elvariable id="thread" type=""--%>
<form:form action="/admin/update_thread_submit" method="post" modelAttribute="thread">
    Thread name:<form:input path="name"/>
    <form:input type="hidden" path="id"/>
    <button type="submit">Update</button>
</form:form>
</body>
</html>