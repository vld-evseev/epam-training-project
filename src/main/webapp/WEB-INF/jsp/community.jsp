<%@ taglib prefix="community" tagdir="/WEB-INF/tags/community" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.root" var="root"/>
<fmt:setBundle basename="i18n.profile" var="profile"/>
<fmt:message var="title" bundle="${profile}" key="profile.community"/>

<jsp:useBean id="userList" type="java.util.List<com.epam.training.lawAndSocial.model.User>"
             scope="request"/>
<jsp:useBean id="path" type="java.lang.String" scope="request"/>
<jsp:useBean id="followingIds" type="java.util.Set<java.lang.Long>" scope="request"/>
<jsp:useBean id="user" type="com.epam.training.lawAndSocial.model.User" scope="session"/>
<jsp:useBean id="pagesCount" type="java.lang.Integer" scope="request"/>

<tags:main title="${title}">

    <tags:navigationBlock>
        <div class="panel-group">
            <c:forEach items="${userList}" var="otherUser" varStatus="count">
                <c:if test="${user.id != otherUser.id}">
                    <c:url var="userUrl" value="/user">
                        <c:param name="id" value="${otherUser.id}"/>
                    </c:url>
                    <c:set var="avatar" value="${otherUser.avatar}"/>

                    <div class="panel">
                        <div class="panel-body">
                            <div class="row">
                                <c:choose>
                                    <c:when test="${not empty avatar}">
                                        <img alt="img" class="avatar-img"
                                             src="data:image/jpeg;base64,${otherUser.avatar}"/>
                                    </c:when>
                                    <c:otherwise>
                                        <i class="fa fa-user fa-2x avatar-icon"></i>
                                    </c:otherwise>
                                </c:choose>

                                <a href="${userUrl}">
                                        ${otherUser.id} :
                                        ${otherUser.firstName}
                                        ${otherUser.lastName}
                                </a>

                                <c:url var="returnPath" value="${path}"/>
                                <c:choose>
                                    <c:when test="${followingIds.contains(otherUser.id)}">
                                        <form class="pull-right" action="${returnPath}" method="post">
                                            <input type="hidden" name="action" value="unfollow">
                                            <input type="hidden" name="userId" value="${otherUser.id}">
                                            <button class="btn btn-warning btn-sm btn-follow pull-right" type="submit">
                                                <fmt:message bundle="${profile}" key="profile.unfollow"/>
                                            </button>
                                        </form>
                                    </c:when>

                                    <c:otherwise>
                                        <form class="pull-right" action="${returnPath}" method="post">
                                            <input type="hidden" name="action" value="follow">
                                            <input type="hidden" name="userId" value="${otherUser.id}">
                                            <button class="btn btn-primary btn-sm btn-follow pull-right" type="submit">
                                                <fmt:message bundle="${profile}" key="profile.follow"/>
                                            </button>
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>

        <c:if test="${pagesCount > 1}">
            <community:pagination path="${path}"/>
        </c:if>

    </tags:navigationBlock>
</tags:main>