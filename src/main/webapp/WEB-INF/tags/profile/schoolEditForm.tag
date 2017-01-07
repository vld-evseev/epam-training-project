<%@attribute name="count" rtexprvalue="true" required="true" %>
<%@attribute name="schoolId" rtexprvalue="true" required="true" %>
<%@attribute name="schoolUserIdValue" rtexprvalue="true" required="true" %>
<%@attribute name="schoolNameValue" rtexprvalue="true" required="true" %>
<%@attribute name="schoolCountryValue" rtexprvalue="true" required="true" %>
<%@attribute name="schoolCityValue" rtexprvalue="true" required="true" %>
<%@attribute name="schoolYearFromValue" rtexprvalue="true" required="true" %>
<%@attribute name="schoolYearToValue" rtexprvalue="true" required="true" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n.user" var="userPage"/>
<jsp:useBean id="user" type="com.epam.training.lawAndSocial.model.User" scope="session"/>

<div id="schoolForm${count}">
    <input type="hidden" id="schoolId${count}" name="schoolId" value="${schoolId}"/>
    <input type="hidden" id="schoolUserId${count}" name="schoolUserId" value="${schoolUserIdValue}"/>
    <div class="row">
        <div class="form-group col-md-12">
            <fmt:message var="name" bundle="${userPage}"
                         key="user.education.name"/>
            <label for="schoolName${count}"><fmt:message bundle="${userPage}"
                                                         key="user.education.school"/></label>
            <input id="schoolName${count}" class="form-control input-sm"
                   placeholder="${name}"
                   name="schoolName"
                   value="${schoolNameValue}"
                   type="text"
                   oninput="verifySchoolFields(${count})">
        </div>
    </div>

    <div class="row">
        <div class="form-group col-md-12">
            <label for="schoolCountry${count}"><fmt:message bundle="${userPage}"
                                                            key="user.country"/></label>
            <input id="schoolCountry${count}" class="form-control input-sm"
                   name="schoolCountry"
                   value="${schoolCountryValue}"
                   type="text">
        </div>
    </div>


    <div class="row">
        <div class="form-group col-md-12">
            <label for="schoolCity${count}"><fmt:message bundle="${userPage}"
                                                         key="user.city"/></label>
            <input id="schoolCity${count}" class="form-control input-sm"
                   name="schoolCity"
                   value="${schoolCityValue}"
                   type="text">
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <fmt:message var="schoolYears" bundle="${userPage}"
                         key="user.education.years"/>
            <label>${schoolYears}</label>
            <div class="row">
                <div class="form-group col-xs-6">
                    <fmt:message var="schoolYearFrom" bundle="${userPage}"
                                 key="user.education.years.from"/>
                    <input class="form-control input-sm dateYear" id="schoolYearFrom${count}"
                           name="schoolYearFrom"
                           placeholder="${schoolYearFrom}"
                           value="${schoolYearFromValue}"
                           type="text"/>
                </div>
                <div class="form-group col-xs-6">
                    <fmt:message var="schoolYearTo" bundle="${userPage}"
                                 key="user.education.years.to"/>
                    <input class="form-control input-sm dateYear" id="schoolYearTo${count}"
                           name="schoolYearTo"
                           placeholder="${schoolYearTo}"
                           value="${schoolYearToValue}"
                           type="text"/>
                </div>
            </div>
        </div>
    </div>
    <hr>
</div>

