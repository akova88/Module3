<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>PRODUCT DISCOUNT</title>
    <style>
        form input {
            width: 250px;
            padding: 5px;
            margin: 5px;
        }
        form label {
            font-size: 18px;
            font-weight: bold;
            margin: 5px;
        }
    </style>
</head>
<body>
    <h2>THÔNG TIN SẢN PHẨM</h2>
    <form action="/discount" method="post">
        <label>Mô tả sản phẩm:</label> <br/>
        <input type="text" name="description" placeholder="Enter product description: "> <br/>
        <label>Giá sản phẩm:</label> <br/>
        <input type="text" name="price" placeholder="Enter product price: "> <br/>
        <label>Giảm giá %:</label> <br/>
        <input type="text" name="discount" placeholder="Enter discount: "> <br/>
        <input type="submit" id="submit" value="Calculate Discount">
    </form>
</body>
</html>