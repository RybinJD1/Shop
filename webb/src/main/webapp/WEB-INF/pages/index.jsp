<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta charset="UTF-8">

    <title>Shop Online</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
</head>

<body>
<jsp:include page="/WEB-INF/pages/_header.jsp" />
<jsp:include page="/WEB-INF/pages/_menu.jsp" />

<div class="page-title"><spring:message code="page.home"/></div>

<div class="container">
    <div class="jumbotron text-center">
        <h1><spring:message code="page.welcome"/></h1>
    </div>
</div>

<jsp:include page="_footer.jsp" />

</body>
</html>
