<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap-theme.css" />
 <script src="<%=request.getContextPath()%>/resources/js/bootstrap.js"></script>
<title></title>
</head>
<body>
	<c:if test="${!empty camSession}">
		<p class="text-right"><c:out value="${camSession}"/></p>
	</c:if>
	
	<div class="hero-unit" align="center">
		<form name="form" action="enableDisable.html" method="post">
			<label for="pin">PIN:</label>
			<input name='pin' type="password" /><br />
			<input type="submit" class="btn btn-large btn-success" value="Enable-Disable" />
		</form>
		<c:out value="${errorMessage}"/>
	</div>
</body>
</html>