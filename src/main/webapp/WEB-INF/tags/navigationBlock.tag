<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.user" var="userPage"/>
<jsp:useBean id="user" type="com.epam.training.lawAndSocial.model.User" scope="session"/>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3">

            <div style="margin-bottom:20px; margin-top:20px;">
                <img alt="img" class="img-responsive"
                     style="margin: 0 auto; border-radius: 50%;
                            width: 100px;
                            height: 100px;"
                     src="data:image/jpeg;base64,${user.avatar}"/>
            </div>

            <ul class="nav nav-stacked collapse in" id="userMenu">
                <li class="active">
                    <c:url var="userUrl" value="/user"/>
                    <a href="${userUrl}"><i class="fa fa-home"></i>
                        <fmt:message bundle="${userPage}" key="user.home"/>
                    </a>
                </li>
                <li>
                    <c:url var="userMessagesUrl" value="/user/messages"/>
                    <a href="${userMessagesUrl}">
                        <i class="fa fa-envelope"> </i>
                        <fmt:message bundle="${userPage}" key="user.messages"/>
                    </a>
                </li>
                <li>
                    <c:url var="userFollowingUrl" value="/user/following"/>
                    <a href="${userFollowingUrl}">
                        <i class="fa fa-users"></i>
                        <fmt:message bundle="${userPage}" key="user.following"/>
                    </a>
                </li>
                <li>
                    <c:url var="userFollowersUrl" value="/user/followers"/>
                    <a href="${userFollowersUrl}">
                        <i class="fa fa-users"></i>
                        <fmt:message bundle="${userPage}" key="user.followers"/>
                    </a>
                </li>
                <li>
                    <c:url var="userEditUrl" value="/user/edit"/>
                    <a href="${userEditUrl}">
                        <i class="fa fa-cog"></i>
                        <fmt:message bundle="${userPage}" key="user.edit"/>
                    </a>
                </li>

            </ul>
        </div>

        <div class="col-sm-9">
            <jsp:doBody/>
        </div>
    </div>
</div>