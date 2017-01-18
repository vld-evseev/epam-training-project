<%@attribute name="tabId" rtexprvalue="true" required="true" %>
<%@ taglib prefix="plugins" tagdir="/WEB-INF/tags/plugins" %>
<%@ taglib prefix="profile" tagdir="/WEB-INF/tags/profile" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.user" var="userPage"/>
<jsp:useBean id="user" type="com.epam.training.lawAndSocial.model.User" scope="session"/>
<jsp:useBean id="schools" type="java.util.List<com.epam.training.lawAndSocial.model.education.EducationInfo>"
             scope="session"/>
<jsp:useBean id="universities" type="java.util.List<com.epam.training.lawAndSocial.model.education.EducationInfo>"
             scope="session"/>

<div id="${tabId}" class="tab-pane fade">
    <div class="content-box-large">
        <div class="panel-body">
            <c:url var="educationInfoEditUrl" value="/user/edit/education"/>
            <form id="educationForm" role="form" action="${educationInfoEditUrl}" method="post">
                <fieldset>
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="panel panel-primary">
                                <div class="panel-heading"><fmt:message bundle="${userPage}"
                                                                        key="user.education.school"/></div>
                                <div class="panel-body">
                                    <div id="dynamicSchoolInput">
                                        <c:choose>
                                            <c:when test="${not empty schools}">
                                                <c:forEach items="${schools}" var="school" varStatus="status">
                                                    <profile:editFormTemplate
                                                            subject="School"
                                                            count="${status.count}"
                                                            id="${school.id}"
                                                            userIdValue="${school.userId}"
                                                            nameValue="${school.name}"
                                                            countryValue="${school.country}"
                                                            cityValue="${school.city}"
                                                            yearFromValue="${school.yearFrom}"
                                                            yearToValue="${school.yearTo}"
                                                    />
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <profile:editFormTemplate
                                                        subject="School"
                                                        count="1"
                                                        userIdValue="${user.id}"
                                                />
                                            </c:otherwise>
                                        </c:choose>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-xs-6">
                                            <button class="btn btn-warning btn-block" id="addSchool">
                                                <fmt:message bundle="${userPage}" key="user.add"/>
                                            </button>
                                        </div>
                                        <div class="form-group col-xs-6">
                                            <button class="btn btn-danger btn-block" id="removeSchool">
                                                <fmt:message bundle="${userPage}" key="user.delete"/>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-6">
                            <div class="panel panel-primary">
                                <div class="panel-heading"><fmt:message bundle="${userPage}"
                                                                        key="user.education.university"/></div>
                                <div class="panel-body">
                                    <div id="dynamicUniversityInput">
                                        <c:choose>
                                            <c:when test="${not empty universities}">
                                                <c:forEach items="${universities}" var="university" varStatus="status">
                                                    <profile:editFormTemplate
                                                            subject="University"
                                                            count="${status.count}"
                                                            id="${university.id}"
                                                            userIdValue="${university.userId}"
                                                            nameValue="${university.name}"
                                                            countryValue="${university.country}"
                                                            cityValue="${university.city}"
                                                            yearFromValue="${university.yearFrom}"
                                                            yearToValue="${university.yearTo}"
                                                    />
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <profile:editFormTemplate
                                                        subject="University"
                                                        count="1"
                                                        userIdValue="${user.id}"
                                                />
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col-xs-6">
                                            <button class="btn btn-warning btn-block" id="addUniversity">
                                                <fmt:message bundle="${userPage}" key="user.add"/>
                                            </button>
                                        </div>
                                        <div class="form-group col-xs-6">
                                            <button class="btn btn-danger btn-block" id="removeUniversity">
                                                <fmt:message bundle="${userPage}" key="user.delete"/>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                    <input type="hidden" id="jsonSchoolData" name="jsonSchoolData"/>
                    <input type="hidden" id="jsonUniversityData" name="jsonUniversityData"/>

                </fieldset>
                <div>
                    <button type="submit" class="btn btn-primary" onclick="collectData()">
                        <i class="fa fa-save"></i>
                        <fmt:message bundle="${userPage}" key="user.save"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<plugins:dynamicFormConstructor subject="School"/>
<plugins:dynamicFormConstructor subject="University"/>

<script>
    function collectData() {
        var schools = getFormElements('school');
        var universities = getFormElements('university');
        $('#jsonSchoolData').attr('value', schools);
        $('#jsonUniversityData').attr('value', universities);
    }

    function getFormElements(subject) {
        var elements = document.forms['educationForm'].getElementsByTagName("input");

        var formLimit = 3;
        var data = [];

        for (var i = 1; i <= formLimit; i++) {
            var subjectElements = $(elements).filter("*[id*='" + subject + "']").filter("*[id*='" + i + "']");
            var id = $(subjectElements).filter('*[id*="Id"]');
            var userId = $(subjectElements).filter('*[id*="UserId"]');
            var name = $(subjectElements).filter('*[id*="Name"]');
            var country = $(subjectElements).filter('*[id*="Country"]');
            var city = $(subjectElements).filter('*[id*="City"]');
            var yearFrom = $(subjectElements).filter('*[id*="YearFrom"]');
            var yearTo = $(subjectElements).filter('*[id*="YearTo"]');

            if (name.val()) {
                var jsonMsg = {
                    id: !id.val() ? -1 : id.val(),
                    userId: userId.val(),
                    name: name.val(),
                    country: country.val(),
                    city: city.val(),
                    yearFrom: !yearFrom.val() ? 0 : yearFrom.val(),
                    yearTo: !yearTo.val() ? 0 : yearTo.val()
                };
                console.log("Data: " + JSON.stringify(jsonMsg));
                data.push(JSON.stringify(jsonMsg));
            }
        }

        return data;
    }
</script>