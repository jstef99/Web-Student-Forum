<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>

<html>
<body>
<%--@elvariable id="subthread" type=""--%>
<form:form action="/admin/update_subthread_submit" method="post" modelAttribute="subthread">
    Thread name:<form:input path="name"/>
    <form:input type="hidden" path="id"/>
    <button type="submit">Update</button>
</form:form>
</body>
</html>