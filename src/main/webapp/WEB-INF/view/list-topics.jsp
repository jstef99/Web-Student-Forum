<!DOCTYPE HTML>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page import="java.util.List"%>
<body>
    <form action="/threads/${threadId}/${subthreadId}/add_topic">
        <button type="submit">Add new topic</button>
    </form>
<table border="1">
    <tr>
        <th>Topic name</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${topics}" var="topic">
        <tr>
            <td><c:out value="${topic.name}"/></td>
            <td>
            <form action="/threads/${threadId}/${subthreadId}/topics/${topic.id}/sign_up">
                <button type="submit">Sign up / Resign</button>
            </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>

</html>