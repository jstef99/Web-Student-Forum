<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>

<html>
<body>
<%--@elvariable id="topic" type=""--%>
<form:form action="/admin/update_topic_submit" method="post" modelAttribute="topic">
    Topic name:<form:input path="name"/>
    Topic description:<form:input path="description"/>
    <form:input type="hidden" path="id"/>
    <button type="submit">Update topic</button>
</form:form>
</body>
</html>