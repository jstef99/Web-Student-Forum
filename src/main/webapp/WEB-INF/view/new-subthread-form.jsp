<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
    <body>
        <%--@elvariable id="subthread" type=""--%>
        <form:form action="/admin/${threadNumber}/new_subthread" method="post" modelAttribute="subthread">
            <form:input path="name"/>
            <button type="submit">Add subthread</button>
        </form:form>
    </body>
</html>