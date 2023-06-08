<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/6/2023
  Time: 3:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.2/css/bootstrap.min.css"
        integrity="sha512-rt/SrQ4UNIaGfDyEXZtNcyWvQeOq0QLygHluFQcSjaGB04IxWhal71tKuzP6K8eYXYB6vJV4pHkXcmFGGQ1/0w=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
        integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  <title>DANH SÁCH KHÁCH HÀNG</title>
  <style>
    html,
    body {
      height: 100%;
      margin: 15px;
    }
    tbody>tr>td {
      line-height: 30px;
    }
    a {
      margin: 10px;
    }
  </style>
</head>

<body class="">
<h3 class="w-50">DANH SÁCH KHÁCH HÀNG</h3>
<a class="btn btn-primary" href="/customers?action=create"><i class="fa-solid fa-user-plus"></i>ADD NEW CUSTOMER</a>

<form method="post" id="frmHiden" action="/customers?action=delete">
  <input type="hidden" id="txtIdEdit" name="idEdit"/>

</form>
<table class="table table-sm table-bordered w-50">
  <thead>
  <tr>
    <th>Tên</th>
    <th>Email</th>
    <th>Địa chỉ</th>
    <th>Action</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${requestScope.customers}" var="c">
    <tr>
      <td>${c.getName()}</td>
      <td>${c.getEmail()}</td>
      <td>${c.getAddress()}</td>
      <td>
        <a href="/customers?action=edit&id=${c.getId()}"><i class="fa-solid fa-pen-to-square"></i></a>
        <a href="javascript:void(0)" onclick="handleDelete(${c.getId()}, '${c.getName()}')"><i class="fa-solid fa-trash"></i></a>
      </td>
    </tr>
  </c:forEach>

  </tbody>
</table>
<script>
  function handleDelete(id, name){
    document.getElementById("txtIdEdit").value = id;
    let cf = confirm("Ban co muon xoa " + name);
    if(cf){
      document.getElementById("frmHiden").submit();
    }
  }
</script>

</body>

</html>
