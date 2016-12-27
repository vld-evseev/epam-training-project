<%@attribute name="tabId" rtexprvalue="true" required="true" %>
<%@ taglib prefix="profile" tagdir="/WEB-INF/tags/profile" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.user" var="userPage"/>
<jsp:useBean id="user" type="com.epam.training.lawAndSocial.model.User" scope="session"/>
<jsp:useBean id="schools" type="java.util.List<com.epam.training.lawAndSocial.model.education.School>"
             scope="session"/>


<div id="${tabId}" class="tab-pane fade">
    <div class="content-box-large">
        <div class="panel-body">
            <c:url var="educationInfoEditUrl" value="/user/edit/education"/>
            <form role="form" action="${educationInfoEditUrl}" method="post">
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
                                                    <profile:schoolEditForm
                                                            count="${status.count}"
                                                            schoolNameValue="${school.name}"
                                                            schoolCountryValue="${school.country}"
                                                            schoolCityValue="${school.city}"
                                                            schoolYearFromValue="${school.yearsFrom}"
                                                            schoolYearToValue="${school.yearsTo}"
                                                    />

                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <profile:schoolEditForm
                                                        count="1"
                                                        schoolNameValue=""
                                                        schoolCountryValue=""
                                                        schoolCityValue=""
                                                        schoolYearFromValue=""
                                                        schoolYearToValue=""
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
                                    <div class="row">
                                        <div class="form-group col-md-12">
                                            <fmt:message var="name" bundle="${userPage}" key="user.education.name"/>
                                            <label for="univName"><fmt:message bundle="${userPage}"
                                                                               key="user.education.university"/></label>
                                            <input id="univName" class="form-control input-sm" placeholder="${name}"
                                                   type="text">
                                        </div>
                                    </div>


                                    <div class="row">
                                        <div class="form-group col-md-12">
                                            <label for="univCountry"><fmt:message bundle="${userPage}"
                                                                                  key="user.country"/></label>
                                            <input id="univCountry" class="form-control input-sm" type="text">
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-md-12">
                                            <label for="univCity"><fmt:message bundle="${userPage}"
                                                                               key="user.city"/></label>
                                            <input id="univCity" class="form-control input-sm" type="text">
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <fmt:message var="univYears" bundle="${userPage}"
                                                         key="user.education.years"/>
                                            <label>${univYears}</label>
                                            <div class="row">
                                                <div class="form-group col-xs-6">
                                                    <fmt:message var="univYearsFrom" bundle="${userPage}"
                                                                 key="user.education.years.from"/>
                                                    <input class="form-control input-sm" id="dateYear" name="dateYear"
                                                           placeholder="${univYearsFrom}"
                                                           type="text"/>
                                                </div>
                                                <div class="form-group col-xs-6">
                                                    <fmt:message var="univYearsTo" bundle="${userPage}"
                                                                 key="user.education.years.to"/>
                                                    <input class="form-control input-sm" id="dateYear" name="dateYear"
                                                           placeholder="${univYearsTo}"
                                                           type="text"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                </fieldset>
                <div>
                    <button type="submit" class="btn btn-primary">
                        <i class="fa fa-save"></i>
                        <fmt:message bundle="${userPage}" key="user.save"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        $('#schoolCountry').prop('disabled', true);
        $('#schoolCity').prop('disabled', true);
        $('#schoolYearFrom').prop('disabled', true);
        $('#schoolYearTo').prop('disabled', true);
        $('#schoolName').keyup(function () {
            $('#schoolCountry').prop('disabled', this.value == "");
            $('#schoolCity').prop('disabled', this.value == "");
            $('#schoolYearFrom').prop('disabled', this.value == "");
            $('#schoolYearTo').prop('disabled', this.value == "");
        });


        var next = $("#dynamicSchoolInput > div").length;
        var limit = 3;

        if (next == 1) {
            $("#removeSchool").hide();
        }


        $("#addSchool").click(function (e) {
            e.preventDefault();
            if (next < limit) {
                next = next + 1;
                $("#dynamicSchoolInput").append("<div id='schoolForm" + next + "'><input type='hidden' name='schoolCount' value=" + next + " />" +
                        "<div class='row'> <div class='form-group col-md-12'>" +
                        "<fmt:message var='name' bundle='${userPage}' key='user.education.name'/>" +
                        "<label for='schoolName" + next + "'><fmt:message bundle='${userPage}' key='user.education.school'/></label>" +
                        "<input id='schoolName" + next + "' class='form-control input-sm' placeholder='${name}' name='schoolName' oninput='verify(" + next + ")' type='text'>" +
                        "</div> </div>" +
                        "<div class='row'> <div class='form-group col-md-12'>" +
                        "<label for='schoolCountry" + next + "'><fmt:message bundle='${userPage}' key='user.country'/></label>" +
                        "<input id='schoolCountry" + next + "' class='form-control input-sm' name='schoolCountry' type='text'>" +
                        "</div> </div>" +
                        "<div class='row'> <div class='form-group col-md-12'>" +
                        "<label for='schoolCity" + next + "'><fmt:message bundle='${userPage}' key='user.city'/></label>" +
                        "<input id='schoolCity" + next + "' class='form-control input-sm' name='schoolCity' type='text'>" +
                        "</div> </div>" +
                        "<div class='row'> <div class='col-md-12'>" +
                        "<fmt:message var='schoolYears' bundle='${userPage}' key='user.education.years'/>" +
                        "<label>${schoolYears}</label> <div class='row'> <div class='form-group col-xs-6'>" +
                        "<fmt:message var='schoolYearFrom' bundle='${userPage}' key='user.education.years.from'/>" +
                        "<input class='form-control input-sm' id='schoolYearFrom" + next + "' name='schoolYearFrom' placeholder='${schoolYearFrom}' type='text'/>" +
                        "</div> <div class='form-group col-xs-6'>" +
                        "<fmt:message var='schoolYearTo' bundle='${userPage}' key='user.education.years.to'/>" +
                        "<input class='form-control input-sm' id='schoolYearTo" + next + "' name='schoolYearTo' placeholder='${schoolYearTo}' type='text'/>" +
                        "</div> </div> </div> </div> <hr> </div>"
                );

                $('#schoolCountry' + next).prop('disabled', true);
                $('#schoolCity' + next).prop('disabled', true);
                $('#schoolYearFrom' + next).prop('disabled', true);
                $('#schoolYearTo' + next).prop('disabled', true);
                /*$('#schoolName' + next).keyup(function () {
                 $('#schoolCountry' + next).prop('disabled', this.value == "");
                 $('#schoolCity' + next).prop('disabled', this.value == "");
                 $('#schoolYearFrom' + next).prop('disabled', this.value == "");
                 $('#schoolYearTo' + next).prop('disabled', this.value == "");
                 });*/

                $("#removeSchool").show();
                window.scrollTo(0, document.body.scrollHeight);
            }
        });
        $("#removeSchool").click(function (e) {
            e.preventDefault();
            if (next > 1) {
                var formID = '#schoolForm' + next;
                $(formID).remove();
                next = next - 1;
            }
            if (next == 1) {
                $(this).hide();
            }
        });
    });

    function verify(idx) {
        $('#schoolName' + idx).keyup(function () {
            $('#schoolCountry' + idx).prop('disabled', this.value == "");
            $('#schoolCity' + idx).prop('disabled', this.value == "");
            $('#schoolYearFrom' + idx).prop('disabled', this.value == "");
            $('#schoolYearTo' + idx).prop('disabled', this.value == "");
        });
    }

</script>