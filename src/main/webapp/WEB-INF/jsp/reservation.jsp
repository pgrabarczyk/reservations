<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="reservation.title" /></title>
</head>
<body>
	<spring:message code="language" />
	:
	<a href="?language=en">English</a> |
	<a href="?language=pl">Polski</a>
	<h1>
		<spring:message code="rest_actions" />:
	</h1>


	<c:if test="${not empty methods}">
		<ul>
			<c:forEach var="method" items="${methods}">
				<li>
					<a href='<spring:url value="${method}"/>'>${method}</a>
				</li>
			</c:forEach>
		</ul>

	</c:if>
	

</body>
</html>