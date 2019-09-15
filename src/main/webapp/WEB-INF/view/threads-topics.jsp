<!DOCTYPE HTML>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page import="java.util.List"%>
<body>
<table>
    <thead>
    <td>Thread name</td>
    </thead>
    <tbody>
    <c:forEach items="${threads}" var="thread">
        <tr>
            <td><a href="/user/topics/${thread.id}"><c:out value="${thread.name}"/></a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>

</html>