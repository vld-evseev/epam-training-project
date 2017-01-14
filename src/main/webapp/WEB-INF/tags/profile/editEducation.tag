<%@attribute name="tabId" rtexprvalue="true" required="true" %>
<%@ taglib prefix="plugins" tagdir="/WEB-INF/tags/plugins" %>
<%@ taglib prefix="profile" tagdir="/WEB-INF/tags/profile" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.user" var="userPage"/>
<jsp:useBean id="user" type="com.epam.training.lawAndSocial.model.User" scope="session"/>
<jsp:useBean id="schools" type="java.util.List<com.epam.training.lawAndSocial.model.education.School>"
             scope="session"/>
<jsp:useBean id="universities" type="java.util.List<com.epam.training.lawAndSocial.model.education.University>"
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
                                                            yearFromValue="${school.yearsFrom}"
                                                            yearToValue="${school.yearsTo}"
                                                    />
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <profile:editFormTemplate
                                                        subject="School"
                                                        count="1"
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
                                                            yearFromValue="${university.yearsFrom}"
                                                            yearToValue="${university.yearsTo}"
                                                    />
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <profile:editFormTemplate
                                                        subject="University"
                                                        count="1"
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

        <button class="btn btn-primary" onclick="collectData()">
            <i class="fa fa-save"></i>
            Test
        </button>

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
                    yearFrom: yearFrom.val(),
                    yearTo: yearTo.val()
                };
                console.log("Data: " + JSON.stringify(jsonMsg));
                data.push(JSON.stringify(jsonMsg));
            }
        }

        return data;
    }
</script>

<%--
<script>
    <fmt:message var="clearMsg" bundle="${userPage}" key="user.clear"/>
    <fmt:message var="deleteMsg" bundle="${userPage}" key="user.delete"/>
    $(document).ready(function () {

        var next = $("#dynamicUniversityInput > div").length;
        var limit = 3;

        if ($('.dateYear').val() == '1' || $('.dateYear').val() == '0') {
            $('.dateYear').val('');
        }

        if (!$('#universityName' + next).val()) {
            $('#universityCountry' + next).prop('disabled', true);
            $('#universityCity' + next).prop('disabled', true);
            $('#universityYearFrom' + next).prop('disabled', true);
            $('#universityYearTo' + next).prop('disabled', true);
        }

        $('#universityName').keyup(function () {
            $('#universityCountry').prop('disabled', this.value == "");
            $('#universityCity').prop('disabled', this.value == "");
            $('#universityYearFrom').prop('disabled', this.value == "");
            $('#universityYearTo').prop('disabled', this.value == "");
        });

        if (next == 1) {
            $('#removeUniversity').addClass("university-remove");
            $("#removeUniversity").html("${clearMsg}");
        }


        $("#addUniversity").click(function (e) {
            e.preventDefault();
            if (next < limit) {
                next = next + 1;
                $("#dynamicUniversityInput").append("<div id='universityForm" + next + "'><input type='hidden' name='universityCount' value=" + next + " />" +
                        "<input type='hidden' id='universityId" + next + "' name='universityId''/>" +
                        "<input type='hidden' id='universityUserId" + next + "' name='universityUserId' value='${user.id}'/>" +
                        "<div class='row'> <div class='form-group col-md-12'>" +
                        "<fmt:message var='name' bundle='${userPage}' key='user.education.name'/>" +
                        "<label for='universityName" + next + "'><fmt:message bundle='${userPage}' key='user.education.university'/></label>" +
                        "<input id='universityName" + next + "' class='form-control input-sm' placeholder='${name}' name='universityName' oninput='verifyUniversityFields(" + next + ")' type='text'>" +
                        "</div> </div>" +
                        "<div class='row'> <div class='form-group col-md-12'>" +
                        "<label for='universityCountry" + next + "'><fmt:message bundle='${userPage}' key='user.country'/></label>" +
                        "<input id='universityCountry" + next + "' class='form-control input-sm' name='universityCountry' type='text'>" +
                        "</div> </div>" +
                        "<div class='row'> <div class='form-group col-md-12'>" +
                        "<label for='universityCity" + next + "'><fmt:message bundle='${userPage}' key='user.city'/></label>" +
                        "<input id='universityCity" + next + "' class='form-control input-sm' name='universityCity' type='text'>" +
                        "</div> </div>" +
                        "<div class='row'> <div class='col-md-12'>" +
                        "<fmt:message var='universityYears' bundle='${userPage}' key='user.education.years'/>" +
                        "<label>${universityYears}</label> <div class='row'> <div class='form-group col-xs-6'>" +
                        "<fmt:message var='universityYearFrom' bundle='${userPage}' key='user.education.years.from'/>" +
                        "<input class='form-control input-sm dateYear' id='universityYearFrom" + next + "' name='universityYearFrom' placeholder='${universityYearFrom}' type='text'/>" +
                        "</div> <div class='form-group col-xs-6'>" +
                        "<fmt:message var='universityYearTo' bundle='${userPage}' key='user.education.years.to'/>" +
                        "<input class='form-control input-sm dateYear' id='universityYearTo" + next + "' name='universityYearTo' placeholder='${universityYearTo}' type='text'/>" +
                        "</div> </div> </div> </div> <hr> </div>"
                );

                $('#universityCountry' + next).prop('disabled', true);
                $('#universityCity' + next).prop('disabled', true);
                $('#universityYearFrom' + next).prop('disabled', true);
                $('#universityYearTo' + next).prop('disabled', true);

                changeUniversityButtonBehavior(next);
                window.scrollTo(0, document.body.scrollHeight);
            }

            if ($('.dateYear').val() == '1' || $('.dateYear').val() == '0') {
                $('.dateYear').val('');
            }
        });

        $("#removeUniversity").click(function (e) {
            e.preventDefault();
            if (next > 1) {
                var formID = '#universityForm' + next;
                $(formID).remove();
                next = next - 1;
            }
            changeUniversityButtonBehavior(next);
        });
    });

    function changeUniversityButtonBehavior(next) {
        if ($("#removeUniversity").hasClass("university-remove")) {
            if (next == 1) {
                $("#universityName" + next).val('');
                $("#universityCountry" + next).val('');
                $("#universityCity" + next).val('');
                $("#universityYearFrom" + next).val('');
                $("#universityYearTo" + next).val('');

                $('#universityCountry' + next).prop('disabled', true);
                $('#universityCity' + next).prop('disabled', true);
                $('#universityYearFrom' + next).prop('disabled', true);
                $('#universityYearTo' + next).prop('disabled', true);
            }
        }

        if (next == 1) {
            $('#removeUniversity').addClass("university-remove");
            $("#removeUniversity").html("${clearMsg}");
        } else {
            $('#removeUniversity').removeClass("university-remove");
            $("#removeUniversity").html("${deleteMsg}");
        }
    }

    function verifyUniversityFields(idx) {
        $('#universityName' + idx).keyup(function () {
            $('#universityCountry' + idx).prop('disabled', this.value == "");
            $('#universityCity' + idx).prop('disabled', this.value == "");
            $('#universityYearFrom' + idx).prop('disabled', this.value == "");
            $('#universityYearTo' + idx).prop('disabled', this.value == "");
        });
    }
</script>--%>
