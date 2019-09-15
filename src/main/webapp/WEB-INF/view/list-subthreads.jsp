<!DOCTYPE HTML>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page import="java.util.List"%>
<body>
<security:authorize access="hasRole('ADMIN')">
<form action="/admin/${thread_number}/new_subthread">
    <button type="submit">Add new subthread</button>
</form>
</security:authorize>
<table border="1">
    <tr>
        <th>Subthread name</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${subthreads}" var="subthread">
        <tr>
            <td><c:out value="${subthread.id}"/></td>
            <td><a href="/threads/${thread_number}/${subthread.id}"><c:out value="${subthread.name}"/></a></td>
            <td><security:authorize access="hasRole('ADMIN')">
                <form action="/admin/${thread_number}/${subthread.id}/finalizeSubmits">
                    <button type="submit">Finalize submits</button>
                </form>
            </security:authorize>
            </td>
        </tr>
    </c:forEach>
</table>
</body>

</html>