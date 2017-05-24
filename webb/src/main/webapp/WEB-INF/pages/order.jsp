<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">

</head>
<body>

<jsp:include page="_header.jsp" />
<jsp:include page="_menu.jsp" />

<fmt:setLocale value="en_US" scope="session"/>

<div class="page-title"><spring:message code='order.info'/></div>

<div class="customer-info-container">
    <h3>Customer Information:</h3>
    <ul>
        <li><spring:message code='buyer.name'/>: ${orderInfo.customerName}</li>
        <li><spring:message code='buyer.email'/>: ${orderInfo.customerEmail}</li>
        <li><spring:message code='buyer.phone'/>: ${orderInfo.customerPhone}</li>
        <li><spring:message code='buyer.address'/>: ${orderInfo.customerAddress}</li>
    </ul>
    <h3><spring:message code='order.summary'/>:</h3>
    <ul>
        <li><spring:message code='order.total'/>:
           <span class="total">
           <fmt:formatNumber value="${orderInfo.amount}" type="currency"/>
           </span></li>
    </ul>
</div>

<br/>

<table border="1" style="width:100%">
    <tr>
        <th><spring:message code='order.ProductCode'/></th>
        <th><spring:message code='order.product.name'/></th>
        <th><spring:message code='order.quantity'/></th>
        <th><spring:message code='order.price'/></th>
        <th><spring:message code='order.amount'/></th>
    </tr>
    <c:forEach items="${orderInfo.details}" var="orderDetailInfo">
        <tr>
            <td>${orderDetailInfo.productCode}</td>
            <td>${orderDetailInfo.productName}</td>
            <td>${orderDetailInfo.quantity}</td>
            <td>
                <fmt:formatNumber value="${orderDetailInfo.price}" type="currency"/>
            </td>
            <td>
                <fmt:formatNumber value="${orderDetailInfo.amount}" type="currency"/>
            </td>
        </tr>
    </c:forEach>
</table>
<c:if test="${paginationResult.totalPages > 1}">
    <div class="page-navigator">
        <c:forEach items="${paginationResult.navigationPages}" var = "page">
            <c:if test="${page != -1 }">
                <a href="orderList?page=${page}" class="nav-item">${page}</a>
            </c:if>
            <c:if test="${page == -1 }">
                <span class="nav-item"> ... </span>
            </c:if>
        </c:forEach>

    </div>
</c:if>

<jsp:include page="_footer.jsp" />

</body>
</html>
