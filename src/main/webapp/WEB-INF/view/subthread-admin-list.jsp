<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<body>
<table border="1">
    <thead>
    <tr>
        <th>Subhread id</th>
        <th>Subthread name</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${subthreads}" var="subthread">
        <tr>
            <td><c:out value="${subthread.id}"/></td>
            <td><c:out value="${subthread.name}"/></td>
            <td>
                <form action="/admin/update_subthread/${subthread.id}"><button type="submit">Update</button></form>
            </td>
            <td>
                <form action="/admin/delete_subthread/${subthread.id}"><button type="submit">Delete</button></form>
            </td>
        </tr>
    </c:forEach>
    </tbody>

</table>
</body>
</html>