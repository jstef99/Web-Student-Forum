<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>

<body>
                <%--@elvariable id="name" type=""--%>
                <form:form action="${pageContext.request.contextPath}/user/my_profile"
                           class="form-horizontal">
                    <button type="submit" class="btn btn-primary">My profile</button>
                </form:form>
                <form:form action="${pageContext.request.contextPath}/logout">
                    <button type="submit" class="btn btn-primary">Logout</button>
                </form:form>
            <security:authorize access="hasRole('ADMIN')">
            <div>
                <a href="/admin/students">Manage students</a>
            </div>
            </security:authorize>
            <security:authorize access="hasRole('ADMIN')">
                <div>
                    <a href="/admin/threads">Manage forum threads</a>
                </div>
            </security:authorize>
            <security:authorize access="hasRole('ADMIN')">
                <div>
                    <a href="/admin/subthreads">Manage forum subthreads</a>
                </div>
            </security:authorize>
            <security:authorize access="hasRole('ADMIN')">
                <div>
                    <a href="/admin/topics">Manage topics</a>
                </div>
            </security:authorize>
    <table border="1">
        <thead>
            <tr>
                <td>Thread name</td>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${threads}" var="thread">
            <tr>
                <td><a href="/threads/${thread.id}"><c:out value="${thread.name}"/></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <security:authorize access="hasRole('ADMIN')">
        <form action="/admin/new_thread" method="get">
        <button type="submit">New thread</button>
        </form>
    </security:authorize>
</body>
</html>