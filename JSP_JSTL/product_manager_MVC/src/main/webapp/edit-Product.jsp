<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 8/6/2023
  Time: 1:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create Customer</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.2/css/bootstrap.min.css"
          integrity="sha512-rt/SrQ4UNIaGfDyEXZtNcyWvQeOq0QLygHluFQcSjaGB04IxWhal71tKuzP6K8eYXYB6vJV4pHkXcmFGGQ1/0w=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
          integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<c:if test="${requestScope.message != null}">
    <h6> Sửa thành công!</h6>
</c:if>

<form method="post">
    <fieldset style="border: 1px solid gray; width: 400px" class="m-5 table">
        <legend class="text-center">Product information</legend>
        <c:if test="${requestScope.errors != null}">
            <div class="alert alert-danger">
                <ul>
                    <c:forEach items="${requestScope.errors}" var="e">
                        <li>${e}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <table class="table-hover container">
            <tr>
                <td>Name: </td>
                <td><input type="text" name="name" id="name" value="${requestScope.product.getName()}"></td>
            </tr>
            <tr>
                <td>Price: </td>
                <td><input type="text" name="price" id="price" value="${requestScope.product.getPrice()}"></td>
            </tr>
            <tr>
                <td>Description: </td>
                <td><input type="text" name="desc" id="desc" value="${requestScope.product.getDescription()}"></td>
            </tr>
            <tr>
                <td>Company: </td>
                <td><input type="text" name="company" id="company" value="${requestScope.product.getCompany()}"></td>
            </tr>
            <td>Category:</td>

            <td>
                <select name="category" class="form-control">
                    <c:forEach items="${requestScope.categories}" var="ct">
                        <option
                                <c:if test="${ct.getIdCategory() == requestScope.product.getCategory().getIdCategory()}">selected</c:if>
                                value="${ct.getIdCategory()}">${ct.getNameCategory()}</option>
                    </c:forEach>
                </select>
            </td>
            <tr>
                <td></td>
                <td><input class="btn btn-dark" type="submit" value="Edit Product"></td>
            </tr>
        </table>
    </fieldset>
    <a class="btn btn-primary" href="/products"><i class="fa-solid fa-backward"></i>BACK SHOW CUSTOMER</a>
</form>
<body>

</body>
</html>