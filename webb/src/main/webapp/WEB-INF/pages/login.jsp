<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <title>Login</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">

</head>
<body>

<jsp:include page="_header.jsp" />
<jsp:include page="_menu.jsp" />

<div class="page-title"><spring:message code='page.login'/></div>

<div class="login-container">

    <h3><spring:message code='login.name.password'/></h3>
    <br>
    <c:if test="${param.error == 'true'}">
        <div style="color: red; margin: 10px 0px;">

            <spring:message code='login.failed'/><br /><spring:message code='login.reason'/>:
                ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}

        </div>
    </c:if>

    <form method="POST"
          action="${pageContext.request.contextPath}/j_spring_security_check">
        <table>
            <tr>
                <td><spring:message code='buyer.name'/> *</td>
                <td><input name="userName" placeholder="Name"/></td>
            </tr>

            <tr>
                <td><spring:message code='buyer.password'/> *</td>
                <td><input type="password" name="password" placeholder="password"/></td>
            </tr>

            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" value="Login" /> <input type="reset" value="Reset" /></td>
            </tr>
        </table>
    </form>
    <span class="error-message">${error }</span>
</div>

<jsp:include page="_footer.jsp" />
</body>
</html>
