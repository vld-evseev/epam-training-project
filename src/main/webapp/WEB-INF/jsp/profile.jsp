<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.root" var="root"/>
<fmt:setBundle basename="i18n.profile" var="profile"/>
<fmt:message var="title" bundle="${profile}" key="profile.title"/>
<jsp:useBean id="user" type="com.epam.training.lawAndSocial.model.User" scope="session"/>

<tags:main title="${title}">

    ${user.userName}
    ${user.firstName}
    ${user.lastName}
    ${user.email}
    ${user.date.toString()}

</tags:main>