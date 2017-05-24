<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
<div class="header-container">

    <div class="site-name"><spring:message code='page.home'/></div>

    <div class="header-bar">
        <c:if test="${pageContext.request.userPrincipal.name != null}">
            <spring:message code='page.hello'/>
            <a href="${pageContext.request.contextPath}/accountInfo">
                    ${pageContext.request.userPrincipal.name} </a>
            &nbsp;|&nbsp;
            <a href="${pageContext.request.contextPath}/logout"><spring:message code='page.logout'/></a>

        </c:if>
        <c:if test="${pageContext.request.userPrincipal.name == null}">
            <a href="${pageContext.request.contextPath}/login"><spring:message code='page.login'/></a>
        </c:if>
        <a href="<%=request.getContextPath()%>?lang=en"><spring:message code='language.english'/></a>
        <a href="<%=request.getContextPath()%>?lang=ru"><spring:message code='language.russian'/></a>


        <%--<li class="dropdown text-right" >--%>
            <%--<a href="#" class="dropdown-toggle text-right" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false" ><spring:message code='page.language'/><span class="caret text-right"></span></a>--%>
            <%--<ul class="dropdown-menu text-right">--%>
                <%--<li><a href="?lang=en"><spring:message code='language.english'/></a></li>--%>
                <%--<li><a href="?lang=ru"><spring:message code='language.russian'/></a></li>--%>
            <%--</ul>--%>
        <%--</li>--%>
    </div>
</div>