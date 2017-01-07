<%@attribute name="count" rtexprvalue="true" required="true" %>
<%@attribute name="universityId" rtexprvalue="true" required="false" %>
<%@attribute name="universityUserIdValue" rtexprvalue="true" required="false" %>
<%@attribute name="universityNameValue" rtexprvalue="true" required="false" %>
<%@attribute name="universityCountryValue" rtexprvalue="true" required="false" %>
<%@attribute name="universityCityValue" rtexprvalue="true" required="false" %>
<%@attribute name="universityYearFromValue" rtexprvalue="true" required="false" %>
<%@attribute name="universityYearToValue" rtexprvalue="true" required="false" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n.user" var="userPage"/>
<jsp:useBean id="user" type="com.epam.training.lawAndSocial.model.User" scope="session"/>

<div id="universityForm${count}">
    <input type="hidden" id="universityId${count}" name="universityId" value="${universityId}"/>
    <input type="hidden" id="universityUserId${count}" name="universityUserId" value="${universityUserIdValue}"/>
    <div class="row">
        <div class="form-group col-md-12">
            <fmt:message var="name" bundle="${userPage}" key="user.education.name"/>
            <label for="universityName${count}"><fmt:message bundle="${userPage}"
                                                             key="user.education.university"/></label>
            <input id="universityName${count}" class="form-control input-sm"
                   placeholder="${name}"
                   name="universityName"
                   value="${universityNameValue}"
                   type="text"
                   oninput="verifyUniversityFields(${count})">
        </div>
    </div>

    <div class="row">
        <div class="form-group col-md-12">
            <label for="universityCountry${count}"><fmt:message bundle="${userPage}"
                                                                key="user.country"/></label>
            <input id="universityCountry${count}" class="form-control input-sm"
                   value="${universityCountryValue}"
                   name="universityCountry"
                   type="text">
        </div>
    </div>

    <div class="row">
        <div class="form-group col-md-12">
            <label for="universityCity${count}"><fmt:message bundle="${userPage}"
                                                             key="user.city"/></label>
            <input id="universityCity${count}" class="form-control input-sm"
                   name="universityCity"
                   value="${universityCityValue}"
                   type="text">
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <fmt:message var="universityYears" bundle="${userPage}"
                         key="user.education.years"/>
            <label>${universityYears}</label>
            <div class="row">
                <div class="form-group col-xs-6">
                    <fmt:message var="univYearsFrom" bundle="${userPage}"
                                 key="user.education.years.from"/>
                    <input class="form-control input-sm dateYear" id="universityYearFrom${count}"
                           name="universityYearFrom"
                           placeholder="${univYearsFrom}"
                           value="${universityYearToValue}"
                           type="text"/>
                </div>
                <div class="form-group col-xs-6">
                    <fmt:message var="univYearsTo" bundle="${userPage}"
                                 key="user.education.years.to"/>
                    <input class="form-control input-sm dateYear" id="universityYearTo${count}"
                           name="universityYearTo"
                           placeholder="${univYearsTo}"
                           value="${universityYearToValue}"
                           type="text"/>
                </div>
            </div>
        </div>
    </div>
    <hr>
</div>
