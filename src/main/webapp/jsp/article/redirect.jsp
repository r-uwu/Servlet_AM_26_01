<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <script>
        <% if(request.getAttribute("msg") != null){ %>
            alert("<%= request.getAttribute("msg") %>");
            location.href = "<%= request.getAttribute("redirectUrl") %>";
        <% } %>
    </script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>