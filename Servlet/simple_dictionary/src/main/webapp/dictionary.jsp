<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>SIMPLE DICTIONARY</title>
</head>
<body>
<h2>Vietnamese Dictionary</h2>
<form action="/dictionary" method="post">
  <input type="text" name="txtSearch" placeholder="Enter your word: "/>
  <input type = "submit" id = "submit" value = "Search"/>
</form>
<h1>${tuoi}</h1>
</body>
</html>