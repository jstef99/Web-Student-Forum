<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<body>
<table border="1">
    <thead>
    <tr>
        <th>Thread id</th>
        <th>Thread name</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${threads}" var="thread">
        <tr>
            <td><c:out value="${thread.id}"/></td>
            <td><c:out value="${thread.name}"/></td>
            <td>
                <form action="/admin/update_thread/${thread.id}"><button type="submit">Update</button></form>
            </td>
            <td>
                <form action="/admin/delete_thread/${thread.id}"><button type="submit">Delete</button></form>
            </td>
        </tr>
    </c:forEach>
    </tbody>

</table>
</body>
</html>