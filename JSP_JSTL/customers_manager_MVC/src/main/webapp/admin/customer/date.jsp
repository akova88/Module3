<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>fmt:parseDate example</title>
</head>
<body>
<h2>fmt:parseDate example</h2>

<!-- Một chuỗi có định dạng ngày tháng thời gian -->
<c:set var="dateTimeString" value="17-11-2015 11:49" />
<h4>
  dateTimeString:
  <c:out value="${dateTimeString}"/>
</h4>
<!-- Phân tích chuỗi mô tả ngày tháng thời gian lưu vào biến kiểu java.util.Date -->

<fmt:parseDate value="${dateTimeString}"
               type="both" var="parsedDatetime" pattern="dd-MM-yyyy HH:mm" />
<p>
  The date time after parsing:
  <c:out value="${parsedDatetime}" />
</p>
<br/>
<p>
  Date only (dd/MM/yyyy):
  <fmt:formatDate value="${parsedDatetime}" pattern="dd/MM/yyyy"/>
</body>

</html>