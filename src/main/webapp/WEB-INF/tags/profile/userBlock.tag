<%@attribute name="firstname" rtexprvalue="true" required="true" %>
<%@attribute name="lastname" rtexprvalue="true" required="true" %>
<%@attribute name="birthdate" rtexprvalue="true" required="false" %>
<%@attribute name="country" rtexprvalue="true" required="false" %>
<%@attribute name="city" rtexprvalue="true" required="false" %>
<%@attribute name="email" rtexprvalue="true" required="false" %>
<%@attribute name="phone" rtexprvalue="true" required="false" %>
<%@attribute name="website" rtexprvalue="true" required="false" %>
<%@attribute name="schools" rtexprvalue="true" required="false"
             type="java.util.List<com.epam.training.lawAndSocial.model.education.School>" %>
<%@attribute name="universities" rtexprvalue="true" required="false"
             type="java.util.List<com.epam.training.lawAndSocial.model.education.University>" %>
<%@attribute name="jobs" rtexprvalue="true" required="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n.user" var="userPage"/>

<div class="text-left" style="margin-bottom:20px; margin-left:20px;">
    <p> <h4><strong>${lastname}</strong></h4></p>
    <p> <h4><strong>${firstname}</strong></h4></p>
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
                <td>${birthdate}</td>
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
            <c:if test="${not empty email}">
                <tr>
                    <td><fmt:message bundle="${userPage}" key="user.email"/></td>
                    <td>${email}</td>
                </tr>
            </c:if>

            <c:if test="${not empty phone}">
                <tr>
                    <td><fmt:message bundle="${userPage}" key="user.phone"/></td>
                    <td>${phone}</td>
                </tr>
            </c:if>

            <c:if test="${not empty website}">
                <tr>
                    <td><fmt:message bundle="${userPage}" key="user.website"/></td>
                    <td>${website}</td>
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
                                <c:out value="${school.yearsFrom}"/> - <c:out value="${school.yearsTo}"/>
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
                        <td>stub</td>
                    </tr>
                </c:if>

                </tbody>
            </table>
        </div>
    </div>
</c:if>


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