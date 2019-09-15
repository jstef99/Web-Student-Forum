<!DOCTYPE HTML>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page import="java.util.List"%>
<body>
<table border="1">
    <tr>
        <th>Topic name</th>
        <th>Subthread name</th>
    </tr>
    <c:forEach items="${topics}" var="topic">
        <tr>
            <td><c:out value="${topic.name}"/></td>
            <td><c:out value="${topic.subthread.name}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>