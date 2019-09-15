<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<body>
    <table border="1">
        <thead>
        <tr>
            <th>Topic id</th>
            <th>Topic name</th>
            <th>Thread id</th>
            <th>Subthread id</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${topics}" var="topic">
                <tr>
                    <td><c:out value="${topic.id}"/></td>
                    <td><c:out value="${topic.name}"/></td>
                    <td><c:out value="${topic.threadId}"/></td>
                    <td><c:out value="${topic.subthread.getId()}"/></td>
                    <td>
                    <form action="/admin/update_topic/${topic.id}"><button type="submit">Update</button></form>
                    </td>
                    <td>
                    <form action="/admin/delete_topic/${topic.id}"><button type="submit">Delete</button></form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>

    </table>
</body>
</html>