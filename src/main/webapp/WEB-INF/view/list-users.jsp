<%--@elvariable id="user" type=""--%>
<!DOCTYPE HTML>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<body>
<form action="/admin/add_user" method="get">
    <button type="sumbit" value="Submit">
    Add new user
    </button>s
</form>
<table>
    <tr>
        <th>User ID</th>
        <th>Username</th>
        <th>Points</th>
    </tr>
    <jsp:useBean id="users" scope="request" type="java.util.List"/>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.username}"/></td>
            <td><c:out value="${user.points}"/></td>
        </tr>
    </c:forEach>
</table>
</body>

</html>