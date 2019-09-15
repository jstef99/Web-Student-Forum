<!DOCTYPE HTML>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List"%>
<html>
<body>
<form action="/admin/add_user" method="get">
    <button type="sumbit" value="Submit">
        Add new user
    </button>
</form>
<form action="/admin/add_allowed_email" method="get">
    <button type="submit">
        Allow new email
    </button>
</form>
    <table border="1">
        <thead>
            <th>User ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Active</th>
            <th>Action</th>

        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <form:form action="/admin/delete_user/${user.id}">
                    <td><button type="submit">Delete</button></td>
                    </form:form>
                    <form:form action="/admin/update_user/${user.id}" method="get">
                        <td><button type="submit">Update</button></td>
                    </form:form>
                    <form:form>
                        <td><button type="submit">Block/unblock</button></td>
                    </form:form>
                </tr>
            </c:forEach>
        </tbody>

    </table>
</body>
</html>