<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.root" var="root"/>
<fmt:setBundle basename="i18n.user" var="userPage"/>
<fmt:setBundle basename="i18n.profile" var="profile"/>
<fmt:message var="title" bundle="${root}" key="root.welcome"/>
<jsp:useBean id="contacts" type="com.epam.training.lawAndSocial.model.Contacts" scope="request"/>
<jsp:useBean id="user" type="com.epam.training.lawAndSocial.model.User" scope="session"/>
<jsp:useBean id="school" type="com.epam.training.lawAndSocial.model.education.School" scope="session"/>

<tags:main title="${title}, ${user.userName}!">

    <tags:leftSide>
        <div class="text-left" style="margin-bottom:20px; margin-left:20px;">
            <p> <h4><strong>${user.lastName}</strong></h4></p>
            <p> <h4><strong>${user.firstName}</strong></h4></p>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading">
                <h4><fmt:message bundle="${userPage}" key="user.common.information"/></h4>
            </div>
            <div class="panel-body">
                <table class="table table-user-information">
                    <tbody>
                    <tr>
                        <td><fmt:message bundle="${userPage}" key="user.birth.date"/></td>
                        <td>${user.date}</td>
                    </tr>

                    <tr>
                        <td><fmt:message bundle="${userPage}" key="user.country"/></td>
                        <td>stub</td>
                    </tr>

                    <tr>
                        <td><fmt:message bundle="${userPage}" key="user.city"/></td>
                        <td>stub</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading">
                <h4><fmt:message bundle="${userPage}" key="user.contact.details"/></h4>
            </div>
            <div class="panel-body">
                <table class="table table-user-information">
                    <tbody>
                    <tr>
                        <td><fmt:message bundle="${userPage}" key="user.email"/></td>
                        <td>${contacts.email}</td>
                    </tr>

                    <tr>
                        <td><fmt:message bundle="${userPage}" key="user.phone"/></td>
                        <td>${contacts.phone}</td>
                    </tr>

                    <tr>
                        <td><fmt:message bundle="${userPage}" key="user.website"/></td>
                        <td>${contacts.website}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading">
                <h4><fmt:message bundle="${userPage}" key="user.education"/></h4>
            </div>
            <div class="panel-body">
                <table class="table table-user-information">
                    <tbody>
                    <tr>
                        <td><fmt:message bundle="${userPage}" key="user.education.school"/></td>
                        <td>${school.name}, ${school.city}, ${school.yearsFrom} - ${school.yearsTo}</td>
                    </tr>

                    <tr>
                        <td><fmt:message bundle="${userPage}" key="user.education.university"/></td>
                        <td>stub</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading">
                <h4><fmt:message bundle="${userPage}" key="user.job"/></h4>
            </div>
            <div class="panel-body">
                <table class="table table-user-information">
                    <tbody>
                    <tr>
                        <td><fmt:message bundle="${userPage}" key="user.job.organization"/></td>
                        <td>stub</td>
                    </tr>

                    <tr>
                        <td><fmt:message bundle="${userPage}" key="user.job.position"/></td>
                        <td>stub</td>
                    </tr>

                    <tr>
                        <td><fmt:message bundle="${userPage}" key="user.job.industry"/></td>
                        <td>stub</td>
                    </tr>

                    <tr>
                        <td><fmt:message bundle="${userPage}" key="user.job.organization.website"/></td>
                        <td>stub</td>
                    </tr>

                    <tr>
                        <td><fmt:message bundle="${userPage}" key="user.job.years"/></td>
                        <td>stub</td>
                    </tr>

                    <tr>
                        <td><fmt:message bundle="${userPage}" key="user.job.description"/></td>
                        <td>stub</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

    </tags:leftSide>

</tags:main>