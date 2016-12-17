<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="value" rtexprvalue="true" required="true" %>
<c:set var="validationFailed" value="${not empty value}"/>
<c:choose>
    <c:when test="${validationFailed}">
        class="has-error"
    </c:when>
    <c:otherwise>
        class=""
    </c:otherwise>
</c:choose>