<%@attribute name="subject" rtexprvalue="true" required="true" %>
<%@attribute name="count" rtexprvalue="true" required="true" %>
<%@attribute name="id" rtexprvalue="true" required="false" %>
<%@attribute name="userIdValue" rtexprvalue="true" required="true" %>
<%@attribute name="nameValue" rtexprvalue="true" required="false" %>
<%@attribute name="countryValue" rtexprvalue="true" required="false" %>
<%@attribute name="cityValue" rtexprvalue="true" required="false" %>
<%@attribute name="yearFromValue" rtexprvalue="true" required="false" %>
<%@attribute name="yearToValue" rtexprvalue="true" required="false" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="i18n.user" var="userPage"/>
<jsp:useBean id="user" type="com.epam.training.lawAndSocial.model.User" scope="session"/>

<c:set var="subj" value="${subject}"/>
<c:set var="subjToLowerCase" value="${subject.toLowerCase()}"/>

<div id="${subjToLowerCase}Form${count}">
    <input type="hidden" id="${subjToLowerCase}Id${count}" name="${subjToLowerCase}Id" value="${id}"/>
    <input type="hidden" id="${subjToLowerCase}UserId${count}" name="${subjToLowerCase}UserId" value="${userIdValue}"/>
    <div class="row">
        <div class="form-group col-md-12">
            <fmt:message var="name" bundle="${userPage}"
                         key="user.education.name"/>
            <label for="${subjToLowerCase}Name${count}"><fmt:message bundle="${userPage}"
                                                                     key="user.education.${subjToLowerCase}"/></label>
            <input id="${subjToLowerCase}Name${count}" class="form-control input-sm"
                   placeholder="${name}"
                   name="${subjToLowerCase}Name"
                   value="<c:out value="${nameValue}"/>"
                   type="text"
                   oninput="verify${subj}Fields(${count})">
        </div>
    </div>

    <div class="row">
        <div class="form-group col-md-12">
            <label for="${subjToLowerCase}Country${count}"><fmt:message bundle="${userPage}"
                                                                        key="user.country"/></label>
            <input id="${subjToLowerCase}Country${count}" class="form-control input-sm"
                   name="${subjToLowerCase}Country"
                   value="<c:out value="${countryValue}"/>"
                   type="text">
        </div>
    </div>


    <div class="row">
        <div class="form-group col-md-12">
            <label for="${subjToLowerCase}City${count}"><fmt:message bundle="${userPage}"
                                                                     key="user.city"/></label>
            <input id="${subjToLowerCase}City${count}" class="form-control input-sm"
                   name="${subjToLowerCase}City"
                   value="<c:out value="${cityValue}"/>"
                   type="text">
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <fmt:message var="educationYears" bundle="${userPage}"
                         key="user.education.years"/>
            <label>${educationYears}</label>
            <div class="row">
                <div class="form-group col-xs-6">
                    <fmt:message var="educationYearFrom" bundle="${userPage}"
                                 key="user.education.years.from"/>
                    <input class="form-control input-sm dateYear" id="${subjToLowerCase}YearFrom${count}"
                           name="${subjToLowerCase}YearFrom"
                           placeholder="${educationYearFrom}"
                           value="<c:out value="${yearFromValue}"/>"
                           type="text"/>
                </div>
                <div class="form-group col-xs-6">
                    <fmt:message var="educationYearTo" bundle="${userPage}"
                                 key="user.education.years.to"/>
                    <input class="form-control input-sm dateYear" id="${subjToLowerCase}YearTo${count}"
                           name="${subjToLowerCase}YearTo"
                           placeholder="${educationYearTo}"
                           value="<c:out value="${yearToValue}"/>"
                           type="text"/>
                </div>
            </div>
        </div>
    </div>
    <hr>
</div>

