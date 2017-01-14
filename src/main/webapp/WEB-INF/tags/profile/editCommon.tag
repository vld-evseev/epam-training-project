<%@attribute name="tabId" rtexprvalue="true" required="true" %>
<%@ taglib prefix="profile" tagdir="/WEB-INF/tags/profile" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="plugins" tagdir="/WEB-INF/tags/plugins" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.user" var="userPage"/>
<fmt:setBundle basename="i18n.profile" var="profile"/>
<fmt:setBundle basename="i18n.root" var="root"/>
<jsp:useBean id="user" type="com.epam.training.lawAndSocial.model.User" scope="session"/>
<jsp:useBean id="validation" class="com.epam.training.lawAndSocial.web.servlet.model.FormValidation"
             scope="request"/>

<div id="${tabId}" class="tab-pane fade in active">
    <div class="content-box-large">
        <div class="panel-body">
            <c:url var="commonInfoEditUrl" value="/user/edit/common"/>

            <form role="form" action="${commonInfoEditUrl}" method="post">
                <fieldset>

                    <div class="form-group" id="uploadingBlock">

                        <div class="row">
                            <div class="form-group col-xs-4">
                                <label><fmt:message bundle="${userPage}" key="user.change.photo"/> </label>
                                <div style=" padding-top: 15px; padding-bottom: 15px; ">
                                    <img id='img-upload'/>
                                </div>
                                <input type="hidden" name="avatarSrc" id="avatarSrc" value=""/>
                                <div class="input-group">
                                    <span class="input-group-btn">
                                        <span class="btn btn-default btn-file">
                                            <fmt:message bundle="${root}" key="root.browse"/>...
                                            <input type="file" id="imgInp" onchange="validateAvatar(this);">
                                        </span>
                                    </span>
                                    <%--<input type="text" class="form-control" readonly>--%>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div
                            <tags:fieldValidation value="${validation.fields.firstname}"/>
                    >
                        <div class="row">
                            <div class="form-group col-xs-4">
                                <label for="firstName"><fmt:message bundle="${userPage}" key="user.first.name"/></label>
                                <input id="firstName" class="form-control input-sm" type="text" name="firstname"
                                       value="<c:out value="${user.firstName}"/>">
                            </div>
                        </div>
                        <c:if test="${validation.fields.firstname.emptyField}">
                                <span class="help-block">
                                    <fmt:message bundle="${profile}" key="profile.first.name.required"/>
                                </span>
                        </c:if>
                    </div>

                    <div
                            <tags:fieldValidation value="${validation.fields.lastname}"/>
                    >
                        <div class="row">
                            <div class="form-group col-xs-4">
                                <label for="lastName"><fmt:message bundle="${userPage}" key="user.last.name"/></label>
                                <input id="lastName" class="form-control input-sm" type="text" name="lastname"
                                       value="<c:out value="${user.lastName}"/>">
                            </div>
                        </div>
                        <c:if test="${validation.fields.lastname.emptyField}">
                                <span class="help-block">
                                    <fmt:message bundle="${profile}" key="profile.last.name.required"/>
                                </span>
                        </c:if>
                    </div>


                    <div class="row">
                        <div class="form-group col-xs-4">
                            <label for="patronymic"><fmt:message bundle="${userPage}" key="user.patronymic"/></label>
                            <input id="patronymic" class="form-control input-sm" type="text" name="patronymic"
                                   value="<c:out value="${user.patronymic}" />">
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-xs-4">
                            <label for="gender" class="control-label">
                                <fmt:message bundle="${userPage}" key="user.gender"/>
                            </label>
                            <select id="gender" class="form-control input-sm" name="gender">
                                <option value="UNKNOWN" <c:out
                                        value="${user.gender eq 'UNKNOWN' ? 'selected': ''}"/>>
                                    <fmt:message bundle="${userPage}"
                                                 key="user.gender.unknown"/>
                                </option>
                                <option value="MALE" <c:out value="${user.gender eq 'MALE' ? 'selected': ''}"/>>
                                    <fmt:message bundle="${userPage}"
                                                 key="user.gender.male"/>
                                </option>
                                <option value="FEMALE" <c:out
                                        value="${user.gender eq 'FEMALE' ? 'selected': ''}"/>>
                                    <fmt:message bundle="${userPage}"
                                                 key="user.gender.female"/>
                                </option>
                            </select>
                        </div>
                    </div>

                    <div
                            <tags:fieldValidation value="${validation.fields.date}"/>
                    >
                        <div class="row">
                            <div class="form-group col-xs-4">
                                <fmt:message var="birth_date" bundle="${userPage}" key="user.birth.date"/>
                                <label>${birth_date}</label>
                                <input class="form-control input-sm" id="date" name="date" placeholder="${birth_date}"
                                       type="text"
                                       value="<c:out value="${user.date}"/>"/>
                            </div>
                        </div>
                        <c:if test="${validation.fields.date.emptyField
                            || validation.fields.date.incorrect}">
                            <span class="help-block">
                                <fmt:message bundle="${profile}" key="profile.birth.date.required"/>
                            </span>
                        </c:if>
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

<plugins:datePicker/>

<script>
    $(document).ready(function () {
        $(document).on('change', '.btn-file :file', function () {
            var input = $(this),
                    label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
            input.trigger('fileselect', [label]);
        });

        $('.btn-file :file').on('fileselect', function (event, label) {
            var input = $(this).parents('.input-group').find(':text'),
                    log = label;
        });
        function readURL(input) {
            if (input.files && input.files[0]) {

                if (input.files[0].size > 2097152) {
                    <fmt:message var="invalidImageSizeMsg" bundle="${userPage}" key="user.message.invalid.size"/>
                    $("#uploadingBlock")
                            .after(
                                    "<div class='alert alert-danger' id='uploadAlert'>" + " ${invalidImageSizeMsg}" + " </div> "
                            );
                    input.value = "";
                    return false;
                }

                var reader = new FileReader();

                reader.onload = function (e) {
                    $('#img-upload').attr('src', e.target.result);
                    $('#avatarSrc').attr('value', e.target.result.split(',')[1]);
                };

                reader.readAsDataURL(input.files[0]);
            }
        }

        $("#imgInp").change(function () {
            readURL(this);
        });
    });

    var validFileExtensions = [".jpg", ".jpeg", ".gif", ".png"];
    function validateAvatar(input) {
        if (input.type == "file") {
            var fileName = input.value;
            if (fileName.length > 0) {
                var isValid = false;

                for (var j = 0; j < validFileExtensions.length; j++) {
                    var currentExtension = validFileExtensions[j];
                    if (fileName.substr(fileName.length - currentExtension.length, currentExtension.length).toLowerCase() == currentExtension.toLowerCase()) {
                        isValid = true;
                        $("#uploadAlert").remove();
                        break;
                    }
                }

                if (!isValid) {
                    <fmt:message var="invalidImageExtensionMsg" bundle="${userPage}" key="user.message.invalid.image"/>
                    var message = "${invalidImageExtensionMsg}" + ": " + validFileExtensions.join(", ");
                    $("#uploadingBlock").after("<div class='alert alert-danger' id='uploadAlert'>" + message + "</div>");
                    input.value = "";
                    return false;
                }
            }
        }
        return true;
    }
</script>