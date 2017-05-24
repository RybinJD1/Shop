<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
<div class="menu-container">

    <a href="${pageContext.request.contextPath}/"><spring:message code='menu.home'/></a>
    |
    <a href="${pageContext.request.contextPath}/productList">
        <spring:message code='page.allproducts'/>
    </a>
    |
    <a href="${pageContext.request.contextPath}/shoppingCart">
        <spring:message code='page.mycart'/>
    </a>
    |
    <security:authorize  access="hasAnyRole('ROLE_ADMIN','ROLE_USER')">
        <a href="${pageContext.request.contextPath}/orderList">
            <spring:message code='page.allorders'/>
        </a>
        |
    </security:authorize>

    <security:authorize  access="hasRole('ROLE_ADMIN')">
        <a href="${pageContext.request.contextPath}/product">
            <spring:message code='page.newproduct'/>
        </a>
        |
    </security:authorize>

</div>
