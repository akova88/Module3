<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.2/css/bootstrap.min.css"
          integrity="sha512-rt/SrQ4UNIaGfDyEXZtNcyWvQeOq0QLygHluFQcSjaGB04IxWhal71tKuzP6K8eYXYB6vJV4pHkXcmFGGQ1/0w=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>DANH SÁCH KHÁCH HÀNG</title>
    <style>
        html,
        body {
            height: 100%;
            box-sizing: border-box;
        }
        fieldset{
            border: 1px groove gray;
            padding: 6px;
        }
    </style>
</head>

<body class="ml-5">
<h1>Simple Calculator</h1>
<fieldset class="col-4">
    <legend class="text-center w-auto px-3">Calculator</legend>
    <form action="calculator" method="post">
        <label for="" class="col-4 pr-0">First operand</label>
        <input type="text" name="first-operand" class="col-7">

        <label for="" class="col-4">Operator</label>
        <select name="operator" id="" class="col-4">
            <option value="+">Addition</option>
            <option value="-">Subtraction</option>
            <option value="*">Multiplication</option>
            <option value="/">Division</option>
        </select>

        <label for="" class="col-4 pr-0">Second operand</label>
        <input type="text" name="second-operand" class="col-7">
        <input type="submit" value="Calculator">
    </form>
</fieldset>

<h2>Result: ${result}</h2>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0"
        crossorigin="anonymous"></script>
</body>

</html>