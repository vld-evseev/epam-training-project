<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.root" var="root"/>
<fmt:setBundle basename="i18n.profile" var="profile"/>
<fmt:message var="title" bundle="${profile}" key="profile.community"/>
<jsp:useBean id="userList" type="java.util.List<com.epam.training.lawAndSocial.model.User>"
             scope="request"/>
<jsp:useBean id="pagesCount" type="java.lang.Integer" scope="request"/>

<tags:main title="${title}">

    <tags:navigationBlock>

        <div class="panel-group">
            <c:forEach items="${userList}" var="user" varStatus="count">
                <c:url var="userUrl" value="/user">
                    <c:param name="id" value="${user.id}"/>
                </c:url>

                <div class="panel">
                    <div class="panel-body">
                        <a class="user-block" href="${userUrl}">
                                ${user.id} :
                                ${user.firstName}
                                ${user.lastName}
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>


        <%--<div class="row" style="padding-bottom: 20px;">
            <div class="col-md-12">
                <c:url var="communityUrl" value="/community"/>

                <form role="form" action="${communityUrl}" method="post">
                    <fmt:message var="backward" bundle="${profile}" key="profile.backward"/>
                    <button class="btn btn-default pull-left" role="link" type="submit" title="${backward}">
                        <i class="fa fa-backward"></i> ${backward}
                    </button>
                </form>

                <form role="form" action="${communityUrl}" method="post">
                    <fmt:message var="forward" bundle="${profile}" key="profile.forward"/>
                    <button class="btn btn-default pull-right" role="link" type="submit" title="${forward}">
                        <i class="fa fa-forward"></i> ${forward}
                    </button>
                </form>
            </div>
        </div>--%>

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
                    <c:url var="prevPageUrl" value="/community">
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
                    <c:url var="specificPageUrl" value="/community">
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
                                <c:url var="beginSpecificPageUrl" value="/community">
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
                    <c:url var="nextPageUrl" value="/community">
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
        <table id="content">
            <tbody>
            <tr>
                <td>Window</td>
                <td>John</td>
            </tr>
            <tr>
                <td>Door</td>
                <td>Chris</td>
            </tr>
            <tr>
                <td>Floor</td>
                <td>Michael</td>
            </tr>
            <tr>
                <td>Car</td>
                <td>James</td>
            </tr>
            <tr>
                <td>Bike</td>
                <td>Steven</td>
            </tr>
            </tbody>
        </table>


    </tags:navigationBlock>

</tags:main>

<script>

    $('a.current-page').click(function (ev) {
                ev.preventDefault();
                ev.stopPropagation();
                return false;
            }
    );


    jQuery(function ($) {
        var items = $("#content tbody tr");

        var numItems = items.length;
        var perPage = 2;

        // only show the first 2 (or "first per_page") items initially
        items.slice(perPage).hide();

        // now setup pagination
        $("#pagination").pagination({
            items: numItems,
            itemsOnPage: perPage,
            cssStyle: "light-theme",
            onPageClick: function (pageNumber) { // this is where the magic happens
                // someone changed page, lets hide/show trs appropriately
                var showFrom = perPage * (pageNumber - 1);
                var showTo = showFrom + perPage;

                items.hide() // first hide everything, then show for the new page
                        .slice(showFrom, showTo).show();
            }
        });
    });

</script>