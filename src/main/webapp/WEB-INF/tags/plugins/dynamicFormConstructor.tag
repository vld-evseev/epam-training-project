<%@attribute name="subject" rtexprvalue="true" required="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="i18n.user" var="userPage"/>
<jsp:useBean id="user" type="com.epam.training.lawAndSocial.model.User" scope="session"/>

<script>
    <c:set var="subj" value="${subject}" />
    <c:set var="subjToLowerCase" value="${subject.toLowerCase()}" />

    <fmt:message var="clearMsg" bundle="${userPage}" key="user.clear"/>
    <fmt:message var="deleteMsg" bundle="${userPage}" key="user.delete"/>
    $(document).ready(function () {

        var next = $("#dynamic${subj}Input > div").length;
        var limit = 3;

        if ($('.dateYear').val() == '1' || $('.dateYear').val() == '0') {
            $('.dateYear').val('');
        }

        if (!$('#${subjToLowerCase}Name' + next).val()) {
            $('#${subjToLowerCase}Country' + next).prop('disabled', true);
            $('#${subjToLowerCase}City' + next).prop('disabled', true);
            $('#${subjToLowerCase}YearFrom' + next).prop('disabled', true);
            $('#${subjToLowerCase}YearTo' + next).prop('disabled', true);
        }

        $('#${subjToLowerCase}Name').keyup(function () {
            $('#${subjToLowerCase}Country').prop('disabled', this.value == "");
            $('#${subjToLowerCase}City').prop('disabled', this.value == "");
            $('#${subjToLowerCase}YearFrom').prop('disabled', this.value == "");
            $('#${subjToLowerCase}YearTo').prop('disabled', this.value == "");
        });

        if (next == 1) {
            $('#remove${subj}').addClass("${subjToLowerCase}-remove");
            $("#remove${subj}").html("${clearMsg}");
        }

        $("#add${subj}").click(function (e) {
            e.preventDefault();
            if (next < limit) {
                next = next + 1;
                $("#dynamic${subj}Input").append(
                        "<div id='${subjToLowerCase}Form" + next + "'><input type='hidden' name='${subjToLowerCase}Count' value=" + next + " />" +
                        "<input type='hidden' id='${subjToLowerCase}Id" + next + "' name='${subjToLowerCase}Id''/>" +
                        "<input type='hidden' id='${subjToLowerCase}UserId" + next + "' name='${subjToLowerCase}UserId' value='${user.id}'/>" +
                        "<div class='row'> <div class='form-group col-md-12'>" +
                        "<fmt:message var='name' bundle='${userPage}' key='user.education.name'/>" +
                        "<label for='${subjToLowerCase}Name" + next + "'><fmt:message bundle='${userPage}' key='user.education.${subjToLowerCase}'/></label>" +
                        "<input id='${subjToLowerCase}Name" + next + "' class='form-control input-sm' placeholder='${name}' name='${subjToLowerCase}Name' oninput='verify${subj}Fields(" + next + ")' type='text'>" +
                        "</div> </div>" +
                        "<div class='row'> <div class='form-group col-md-12'>" +
                        "<label for='${subjToLowerCase}Country" + next + "'><fmt:message bundle='${userPage}' key='user.country'/></label>" +
                        "<input id='${subjToLowerCase}Country" + next + "' class='form-control input-sm' name='${subjToLowerCase}Country' type='text'>" +
                        "</div> </div>" +
                        "<div class='row'> <div class='form-group col-md-12'>" +
                        "<label for='${subjToLowerCase}City" + next + "'><fmt:message bundle='${userPage}' key='user.city'/></label>" +
                        "<input id='${subjToLowerCase}City" + next + "' class='form-control input-sm' name='${subjToLowerCase}City' type='text'>" +
                        "</div> </div>" +
                        "<div class='row'> <div class='col-md-12'>" +
                        "<fmt:message var='educationYears' bundle='${userPage}' key='user.education.years'/>" +
                        "<label>${educationYears}</label> <div class='row'> <div class='form-group col-xs-6'>" +
                        "<fmt:message var='educationYearFrom' bundle='${userPage}' key='user.education.years.from'/>" +
                        "<input class='form-control input-sm dateYear'" +
                        " id='${subjToLowerCase}YearFrom" + next + "'" +
                        " name='${subjToLowerCase}YearFrom' placeholder='${educationYearFrom}' onclick='yearPicker()' type='text'/>" +
                        "</div> <div class='form-group col-xs-6'>" +
                        "<fmt:message var='educationYearTo' bundle='${userPage}' key='user.education.years.to'/>" +
                        "<input class='form-control input-sm dateYear'" +
                        " id='${subjToLowerCase}YearTo" + next + "'" +
                        " name='${subjToLowerCase}YearTo' placeholder='${educationYearTo}'  onclick='yearPicker() type='text'/>" +
                        "</div> </div> </div> </div> <hr> </div>"
                );

                $('#${subjToLowerCase}Country' + next).prop('disabled', true);
                $('#${subjToLowerCase}City' + next).prop('disabled', true);
                $('#${subjToLowerCase}YearFrom' + next).prop('disabled', true);
                $('#${subjToLowerCase}YearTo' + next).prop('disabled', true);

                change${subj}ButtonBehavior(next);
                window.scrollTo(0, document.body.scrollHeight);
            }

            if ($('.dateYear').val() == '1' || $('.dateYear').val() == '0') {
                $('.dateYear').val('');
            }
        });

        $("#remove${subj}").click(function (e) {
            e.preventDefault();
            if (next > 1) {
                var formID = '#${subjToLowerCase}Form' + next;
                $(formID).remove();
                next = next - 1;
            }
            change${subj}ButtonBehavior(next);
        });
    });

    function change${subj}ButtonBehavior(next) {
        if ($("#remove${subj}").hasClass("${subjToLowerCase}-remove")) {
            if (next == 1) {
                $("#${subjToLowerCase}Name" + next).val('');
                $("#${subjToLowerCase}Country" + next).val('');
                $("#${subjToLowerCase}City" + next).val('');
                $("#${subjToLowerCase}YearFrom" + next).val('');
                $("#${subjToLowerCase}YearTo" + next).val('');

                $('#${subjToLowerCase}Country' + next).prop('disabled', true);
                $('#${subjToLowerCase}City' + next).prop('disabled', true);
                $('#${subjToLowerCase}YearFrom' + next).prop('disabled', true);
                $('#${subjToLowerCase}YearTo' + next).prop('disabled', true);
            }
        }

        if (next == 1) {
            $('#remove${subj}').addClass("${subjToLowerCase}-remove");
            $("#remove${subj}").html("${clearMsg}");
        } else {
            $('#remove${subj}').removeClass("${subjToLowerCase}-remove");
            $("#remove${subj}").html("${deleteMsg}");
        }
    }

    function verify${subj}Fields(idx) {
        $('#${subjToLowerCase}Name' + idx).keyup(function () {
            $('#${subjToLowerCase}Country' + idx).prop('disabled', this.value == "");
            $('#${subjToLowerCase}City' + idx).prop('disabled', this.value == "");
            $('#${subjToLowerCase}YearFrom' + idx).prop('disabled', this.value == "");
            $('#${subjToLowerCase}YearTo' + idx).prop('disabled', this.value == "");
        });
    }
</script>