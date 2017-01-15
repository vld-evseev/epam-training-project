<%@attribute name="id" rtexprvalue="true" required="true" %>
<%@attribute name="firstname" rtexprvalue="true" required="true" %>
<%@attribute name="lastname" rtexprvalue="true" required="true" %>
<%@attribute name="birthdate" rtexprvalue="true" required="false" %>
<%@attribute name="country" rtexprvalue="true" required="false" %>
<%@attribute name="city" rtexprvalue="true" required="false" %>
<%@attribute name="email" rtexprvalue="true" required="false" %>
<%@attribute name="phone" rtexprvalue="true" required="false" %>
<%@attribute name="website" rtexprvalue="true" required="false" %>
<%@attribute name="schools" rtexprvalue="true" required="false"
             type="java.util.List<com.epam.training.lawAndSocial.model.education.EducationInfo>" %>
<%@attribute name="universities" rtexprvalue="true" required="false"
             type="java.util.List<com.epam.training.lawAndSocial.model.education.EducationInfo>" %>
<%@attribute name="job" type="com.epam.training.lawAndSocial.model.Job"
             rtexprvalue="true" required="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.user" var="userPage"/>

<jsp:useBean id="user" type="com.epam.training.lawAndSocial.model.User" scope="session"/>

<div class="text-left" style="margin-bottom:20px; margin-left:20px;">
    <div class="row">
        <div class="col-sm-4">
            <h4><strong>${lastname}</strong></h4>
            <h4><strong>${firstname}</strong></h4>
        </div>
        <c:if test="${id != user.id}">
            <div class="col-sm-4 pull-right">
                <c:url var="sendMsgUrl" value="/user/message">
                    <c:param name="id" value="${id}"/>
                </c:url>
                <a href="${sendMsgUrl}" class="btn btn-info">
                    <i class="fa fa-envelope"></i>
                    <fmt:message bundle="${userPage}" key="user.send.message"/>
                </a>
            </div>
        </c:if>
    </div>
</div>

<div class="panel panel-default">
    <div class="panel-heading">
        <h4><fmt:message bundle="${userPage}" key="user.common.information"/></h4>
    </div>
    <div class="panel-body">
        <table class="table table-user-information">
            <tbody>
            <tr>
                <fmt:message var="dateMsg" bundle="${userPage}" key="user.birth.date"/>
                <td>${dateMsg}</td>
                <td>${birthdate}</td>
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
            <c:if test="${not empty email}">
                <tr>
                    <td><fmt:message bundle="${userPage}" key="user.email"/></td>
                    <td><c:out value="${email}"/></td>
                </tr>
            </c:if>

            <c:if test="${not empty phone}">
                <tr>
                    <td><fmt:message bundle="${userPage}" key="user.phone"/></td>
                    <td><c:out value="${phone}"/></td>
                </tr>
            </c:if>

            <c:if test="${not empty website}">
                <tr>
                    <td><fmt:message bundle="${userPage}" key="user.website"/></td>
                    <td><c:out value="${website}"/></td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>

<c:if test="${not empty schools || not empty universities}">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4><fmt:message bundle="${userPage}" key="user.education"/></h4>
        </div>
        <div class="panel-body">
            <table class="table table-user-information">
                <tbody>

                <c:if test="${not empty schools}">
                    <tr>
                        <td><fmt:message bundle="${userPage}" key="user.education.school"/></td>
                        <td>
                            <c:forEach items="${schools}" var="school" varStatus="count">
                                <c:out value="${school.name}"/>
                                <c:out value="${school.country}"/>
                                <c:out value="${school.city}"/>
                                <c:out value="${school.yearFrom}"/> - <c:out value="${school.yearTo}"/>
                                <c:if test="${not count.last}">
                                    <hr>
                                </c:if>
                            </c:forEach>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${not empty universities}">
                    <tr>
                        <td><fmt:message bundle="${userPage}" key="user.education.university"/></td>
                        <td>
                            <c:forEach items="${universities}" var="univer" varStatus="count">
                                <c:out value="${univer.name}"/>
                                <c:out value="${univer.country}"/>
                                <c:out value="${univer.city}"/>
                                <c:out value="${univer.yearFrom}"/> - <c:out value="${univer.yearTo}"/>
                                <c:if test="${not count.last}">
                                    <hr>
                                </c:if>
                            </c:forEach>
                        </td>
                    </tr>
                </c:if>

                </tbody>
            </table>
        </div>
    </div>
</c:if>

<c:if test="${not empty job.organization}">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4><fmt:message bundle="${userPage}" key="user.job"/></h4>
        </div>
        <div class="panel-body">
            <table class="table table-user-information">
                <tbody>
                <tr>
                    <td><fmt:message bundle="${userPage}" key="user.job.organization"/></td>
                    <td><c:out value="${job.organization}"/></td>
                </tr>

                <tr>
                    <td><fmt:message bundle="${userPage}" key="user.job.position"/></td>
                    <td><c:out value="${job.position}"/></td>
                </tr>

                <tr>
                    <td><fmt:message bundle="${userPage}" key="user.job.industry"/></td>
                    <td><c:out value="${job.industry}"/></td>
                </tr>

                <tr>
                    <td><fmt:message bundle="${userPage}" key="user.job.organization.website"/></td>
                    <td><c:out value="${job.yearFrom}"/></td>
                </tr>

                <tr>
                    <td><fmt:message bundle="${userPage}" key="user.job.years"/></td>
                    <td><c:out value="${job.yearTo}"/></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</c:if>