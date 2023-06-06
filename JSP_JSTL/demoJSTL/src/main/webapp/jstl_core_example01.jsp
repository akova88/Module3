<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 6/6/2023
  Time: 3:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Khai báo sử dụng JSTL Core Tags -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JSTL Core Tags Example 01</title>
</head>
<body>
    <h2>Departments and Employees</h2>
    <!-- Dùng for để duyệt trên các phòng ban (departments) -->
    <c:forEach items="${departments}" var="dept">
        <h3>${dept.deptName}</h3>
        <ul>
            <!-- Dùng for để duyệt trên các nhân viên
               thuộc phòng ban hiện tại -->
            <c:forEach items="${dept.employees}" var="emp">
                <li>
                    ${emp.empName} - (${emp.job})
                </li>
            </c:forEach>
        </ul>

    </c:forEach>

<%--    <h2>Departments and Employees</h2>--%>

<%--    <!-- Dùng for để duyệt trên các phòng ban (departments) -->--%>
<%--    <c:forEach items="${departments}" var="dept">--%>

<%--        <!-- Kiểm tra một tập hợp có phần tử không -->--%>
<%--        <c:if test="${not empty dept.employees}">--%>
<%--            <h3>${dept.deptName}</h3>--%>
<%--            <ul>--%>
<%--                <!-- Dùng for để duyệt trên các nhân viên--%>
<%--                            thuộc phòng ban hiện tại -->--%>
<%--                <c:forEach items="${dept.employees}" var="emp">--%>
<%--                    <li>--%>
<%--                            ${emp.empName} - (${emp.job})--%>
<%--                    </li>--%>
<%--                </c:forEach>--%>
<%--            </ul>--%>
<%--        </c:if>--%>

<%--    </c:forEach>--%>

    <h2>c:choose,c:when,c:otherwise example</h2>


    <c:choose>
        <%-- Khi tham số color == 'red' --%>
        <c:when test="${param.color=='red'}">
            <p style="color:red;">RED COLOR</p>
        </c:when>

        <%-- Khi tham số color == 'blue' --%>
        <c:when test="${param.color=='blue'}">
            <p style="color:blue;">BLUE COLOR</p>
        </c:when>

        <%-- Các trường hợp khác --%>
        <c:otherwise>
            <b>Other color</b>
        </c:otherwise>
    </c:choose>
</body>
</html>
