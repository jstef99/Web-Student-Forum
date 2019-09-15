<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<body>
    <%--@elvariable id="topic" type=""--%>
    <form:form action="/threads/${threadId}/${subthreadId}/submit_topic" modelAttribute="topic" method="post">
        Name:<form:input path="name"/>
        Description:<form:input path="description"/>
        <form:input type="hidden" path="subthread"/>
        <button type="submit">Add topic</button>
    </form:form>
</body>
</html>