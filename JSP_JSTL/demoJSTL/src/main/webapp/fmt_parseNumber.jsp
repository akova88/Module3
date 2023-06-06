<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fmt:parseNumber example</title>
</head>
<body>
<h2>fmt:parseNumber example</h2>

<!-- Một chuỗi có định dạng tiền tệ -->
<c:set var="accountBalance" value="$12345.6789" />

<h4>Input <c:out value="${accountBalance}"/></h4>

<fmt:parseNumber var="parsedNumber" type="currency"
                 parseLocale="en_US"
                 value="${accountBalance}" />

<p>Account Balance: <c:out value="${parsedNumber}" /></p>


<!-- integerOnly="true" : Chỉ lấy phần nguyên -->

<fmt:parseNumber var="parsedNumber" type="currency"
                 integerOnly="true" parseLocale="en_US"
                 value="${accountBalance}" />

<p>Account Balance (without cents): <c:out value="${parsedNumber}" /></p>
</body>

</html>
