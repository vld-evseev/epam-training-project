<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n.user" var="userPage"/>
<jsp:useBean id="user" type="com.epam.training.lawAndSocial.model.User" scope="session"/>

<script>
    <fmt:message var="clearMsg" bundle="${userPage}" key="user.clear"/>
    <fmt:message var="deleteMsg" bundle="${userPage}" key="user.delete"/>
    $(document).ready(function () {

        var next = $("#dynamicSchoolInput > div").length;
        var limit = 3;

        if ($('.dateYear').val() == '1' || $('.dateYear').val() == '0') {
            $('.dateYear').val('');
        }

        if (!$('#schoolName' + next).val()) {
            $('#schoolCountry' + next).prop('disabled', true);
            $('#schoolCity' + next).prop('disabled', true);
            $('#schoolYearFrom' + next).prop('disabled', true);
            $('#schoolYearTo' + next).prop('disabled', true);
        }

        $('#schoolName').keyup(function () {
            $('#schoolCountry').prop('disabled', this.value == "");
            $('#schoolCity').prop('disabled', this.value == "");
            $('#schoolYearFrom').prop('disabled', this.value == "");
            $('#schoolYearTo').prop('disabled', this.value == "");
        });

        if (next == 1) {
            $('#removeSchool').addClass("school-remove");
            $("#removeSchool").html("${clearMsg}");
        }


        $("#addSchool").click(function (e) {
            e.preventDefault();
            if (next < limit) {
                next = next + 1;
                $("#dynamicSchoolInput").append("<div id='schoolForm" + next + "'><input type='hidden' name='schoolCount' value=" + next + " />" +
                        "<input type='hidden' id='schoolId" + next + "' name='schoolId''/>" +
                        "<input type='hidden' id='schoolUserId" + next + "' name='schoolUserId' value='${user.id}'/>" +
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
                        "<input class='form-control input-sm dateYear' id='schoolYearFrom" + next + "' name='schoolYearFrom' placeholder='${schoolYearFrom}' type='text'/>" +
                        "</div> <div class='form-group col-xs-6'>" +
                        "<fmt:message var='schoolYearTo' bundle='${userPage}' key='user.education.years.to'/>" +
                        "<input class='form-control input-sm dateYear' id='schoolYearTo" + next + "' name='schoolYearTo' placeholder='${schoolYearTo}' type='text'/>" +
                        "</div> </div> </div> </div> <hr> </div>"
                );

                $('#schoolCountry' + next).prop('disabled', true);
                $('#schoolCity' + next).prop('disabled', true);
                $('#schoolYearFrom' + next).prop('disabled', true);
                $('#schoolYearTo' + next).prop('disabled', true);

                changeSchoolButtonBehavior(next);
                window.scrollTo(0, document.body.scrollHeight);
            }

            if ($('.dateYear').val() == '1' || $('.dateYear').val() == '0') {
                $('.dateYear').val('');
            }
        });

        $("#removeSchool").click(function (e) {
            e.preventDefault();
            if (next > 1) {
                var formID = '#schoolForm' + next;
                $(formID).remove();
                next = next - 1;
            }
            changeSchoolButtonBehavior(next);
        });
    });

    function changeSchoolButtonBehavior(next) {
        if ($("#removeSchool").hasClass("school-remove")) {
            if (next == 1) {
                $("#schoolName" + next).val('');
                $("#schoolCountry" + next).val('');
                $("#schoolCity" + next).val('');
                $("#schoolYearFrom" + next).val('');
                $("#schoolYearTo" + next).val('');

                $('#schoolCountry' + next).prop('disabled', true);
                $('#schoolCity' + next).prop('disabled', true);
                $('#schoolYearFrom' + next).prop('disabled', true);
                $('#schoolYearTo' + next).prop('disabled', true);
            }
        }

        if (next == 1) {
            $('#removeSchool').addClass("school-remove");
            $("#removeSchool").html("${clearMsg}");
        } else {
            $('#removeSchool').removeClass("school-remove");
            $("#removeSchool").html("${deleteMsg}");
        }
    }

    function verifySchoolFields(idx) {
        $('#schoolName' + idx).keyup(function () {
            $('#schoolCountry' + idx).prop('disabled', this.value == "");
            $('#schoolCity' + idx).prop('disabled', this.value == "");
            $('#schoolYearFrom' + idx).prop('disabled', this.value == "");
            $('#schoolYearTo' + idx).prop('disabled', this.value == "");
        });
    }
</script>