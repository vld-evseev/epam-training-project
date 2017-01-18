<%@attribute name="path" rtexprvalue="true" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.profile" var="profile"/>

<jsp:useBean id="pagesCount" type="java.lang.Integer" scope="request"/>
<c:set var="pageIndex" value="${param.page}"/>

<div id="pagination" class="light-theme simple-pagination">
    <ul>
        <fmt:message var="backward" bundle="${profile}" key="profile.backward"/>
        <li>
            <c:choose>
                <c:when test="${pageIndex > 1}">
                    <c:set var="prevParam" value="${pageIndex - 1}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="prevParam" value="1"/>
                </c:otherwise>
            </c:choose>
            <c:url var="prevPageUrl" value="${path}">
                <c:param name="page" value="${prevParam}"/>
            </c:url>
            <a href="${prevPageUrl}" role="button" class="btn page-link
                        <c:if test="${pageIndex == 1}">
                                disabled
                        </c:if>
                    ">
                <i class="fa fa-arrow-left" aria-hidden="true"></i>
                ${backward}
            </a>
        </li>
        <c:set var="skippedMiddle" value="false"/>
        <c:set var="skippedBeginning" value="false"/>
        <c:forEach begin="${pageIndex}" end="${pagesCount}" varStatus="count">
            <c:url var="specificPageUrl" value="${path}">
                <c:param name="page" value="${count.index}"/>
            </c:url>

            <li>
            <c:choose>
                <c:when test="${count.index > pageIndex + 3
                            && count.index < pagesCount - 2
                            && skippedMiddle eq false}">
                    <li class=""><span class="ellipse clickable">...</span></li>
                    <c:set var="skippedMiddle" value="true"/>
                </c:when>
                <c:otherwise>
                    <c:if test="${pageIndex > 3
                                && skippedBeginning eq false}">
                        <c:url var="beginSpecificPageUrl" value="${path}">
                            <c:param name="page" value="1"/>
                        </c:url>
                        <a href="${beginSpecificPageUrl}" class="page-link">1</a>
                        <li class=""><span class="ellipse clickable">...</span></li>
                        <c:set var="skippedBeginning" value="true"/>
                    </c:if>

                    <c:if test="${count.index < pageIndex + 3 || count.index >= pagesCount - 2}">
                        <a href="${specificPageUrl}" class="page-link">${count.index}</a>
                    </c:if>
                </c:otherwise>
            </c:choose>

            </li>
        </c:forEach>
        <fmt:message var="forward" bundle="${profile}" key="profile.forward"/>
        <li>
            <c:choose>
                <c:when test="${pageIndex < pagesCount - 1}">
                    <c:set var="nextParam" value="${pageIndex + 1}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="nextParam" value="${pagesCount}"/>
                </c:otherwise>
            </c:choose>
            <c:url var="nextPageUrl" value="${path}">
                <c:param name="page" value="${nextParam}"/>
            </c:url>
            <a href="${nextPageUrl}" role="button" class="btn page-link
                        <c:if test="${pageIndex >= pagesCount}">
                                disabled
                        </c:if>
                    ">
                <i class="fa fa-arrow-right" aria-hidden="true"></i>
                ${forward}
            </a>
        </li>

    </ul>
</div>