<%@ taglib prefix="profile" tagdir="/WEB-INF/tags/profile" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.root" var="root"/>
<fmt:message var="title" bundle="${root}" key="root.welcome"/>

<jsp:useBean id="contacts" type="com.epam.training.lawAndSocial.model.Contacts" scope="request"/>
<jsp:useBean id="user" type="com.epam.training.lawAndSocial.model.User" scope="session"/>
<jsp:useBean id="schools" type="java.util.List<com.epam.training.lawAndSocial.model.education.EducationInfo>"
             scope="session"/>
<jsp:useBean id="universities" type="java.util.List<com.epam.training.lawAndSocial.model.education.EducationInfo>"
             scope="session"/>
<jsp:useBean id="jobInfo" type="com.epam.training.lawAndSocial.model.Job" scope="session"/>

<jsp:useBean id="requestedUser" type="com.epam.training.lawAndSocial.model.User" scope="request"/>
<jsp:useBean id="requestedUserSchools"
             type="java.util.List<com.epam.training.lawAndSocial.model.education.EducationInfo>"
             scope="request"/>
<jsp:useBean id="requestedUserUniversities"
             type="java.util.List<com.epam.training.lawAndSocial.model.education.EducationInfo>"
             scope="request"/>
<jsp:useBean id="requestedJobInfo" type="com.epam.training.lawAndSocial.model.Job"
             scope="request"/>

<c:set var="userIsRequested" value="${requestedUser.id != 0}"/>

<tags:main title="${title}, ${user.userName}!">

    <tags:navigationBlock>
        <c:choose>
            <c:when test="${userIsRequested}">
                <profile:userBlock id="${requestedUser.id}"
                                   firstname="${requestedUser.firstName}"
                                   lastname="${requestedUser.lastName}"
                                   birthdate="${requestedUser.date}"
                                   email="${contacts.email}"
                                   phone="${contacts.phone}"
                                   website="${contacts.website}"
                                   schools="${requestedUserSchools}"
                                   universities="${requestedUserUniversities}"
                                   job="${requestedJobInfo}"
                />
            </c:when>
            <c:otherwise>
                <profile:userBlock id="${user.id}"
                                   firstname="${user.firstName}"
                                   lastname="${user.lastName}"
                                   birthdate="${user.date}"
                                   email="${contacts.email}"
                                   phone="${contacts.phone}"
                                   website="${contacts.website}"
                                   schools="${schools}"
                                   universities="${universities}"
                                   job="${jobInfo}"
                />
            </c:otherwise>
        </c:choose>
    </tags:navigationBlock>

</tags:main>