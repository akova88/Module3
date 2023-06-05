<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 5/6/2023
  Time: 10:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>display-discount</title>
</head>
<body>
    <table>
        <tr>
            <th>PRODUCT</th>
            <th></th>
        </tr>
        <tr>
            <td>Mô tả sản phẩm</td>
            <td>${des}</td>
        </tr>
        <tr>
            <td>Giá sản phẩm</td>
            <td>${price}</td>
        </tr>
        <tr>
            <td>Discount(%)</td>
            <td>${disc}</td>
        </tr>
        <tr>
            <td>Discount Amount</td>
            <td>${dcP}</td>
        </tr>
        <tr>
            <th>Total</th>
            <td>${price-dcP}</td>
        </tr>
    </table>
</body>
</html>
