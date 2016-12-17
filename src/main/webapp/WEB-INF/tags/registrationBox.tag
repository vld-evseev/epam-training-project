<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.root" var="root"/>
<fmt:setBundle basename="i18n.profile" var="profile"/>
<fmt:setBundle basename="i18n.error" var="error"/>
<jsp:useBean id="user" type="com.epam.training.lawAndSocial.model.User" scope="request"/>
<jsp:useBean id="credentials" type="com.epam.training.lawAndSocial.model.Credentials" scope="request"/>
<jsp:useBean id="validation" class="com.epam.training.lawAndSocial.web.servlet.model.FormValidation" scope="request"/>

<div id="signupbox" style="/*display:none; */margin-top:50px"
     class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="panel-title"><fmt:message bundle="${profile}" key="profile.sign.up"/></div>
            <div style="float:right; font-size: 85%; position: relative; top:-10px">
                <c:url var="loginUrl" value="/login"/>
                <a id="signinlink" href="${loginUrl}" <%--onclick="$('#signupbox').hide(); $('#loginbox').show()"--%>>
                    <fmt:message bundle="${profile}" key="profile.sign.in"/>
                </a></div>
        </div>
        <div class="panel-body">
            <c:url var="registrationUrl" value="/registration"/>
            <form id="signupform" data-toggle="validator" role="form" action="${registrationUrl}" method="post">

                <c:choose>
                    <c:when test="${validation.fields.email.incorrect
                            || validation.fields.username.incorrect
                            || validation.fields.firstname.incorrect
                            || validation.fields.lastname.incorrect}">
                        <fmt:message var="showRule" bundle="${profile}" key="profile.fields.rule"/>
                        <tags:alert value="${showRule}"/>
                    </c:when>
                    <c:when test="${validation.errors.INTERNAL_ERROR}">
                        <fmt:message var="showInternalErrorMessage" bundle="${error}" key="error.internal"/>
                        <tags:alert value="${showInternalErrorMessage}"/>
                    </c:when>
                </c:choose>

                <div
                        <tags:fieldValidation value="${validation.fields.email}"/>
                >
                    <div class="form-group has-feedback">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope fa-fw"
                                                               aria-hidden="true"></i></span>
                            <fmt:message var="email_address" bundle="${profile}" key="profile.email.address"/>
                            <fmt:message var="email_required" bundle="${profile}" key="profile.email.required"/>
                            <input type="text" class="form-control" name="email" placeholder="${email_address}"
                                   value="${user.email}"
                                   data-error="${email_required}" required>
                        </div>
                        <div class="help-block with-errors"></div>
                        <c:if test="${validation.fields.email.emptyField}">
                            <span class="help-block">
                                <fmt:message bundle="${profile}" key="profile.email.required"/>
                            </span>
                        </c:if>
                    </div>
                </div>


                <div
                        <tags:fieldValidation value="${validation.fields.username}"/>
                >
                    <div class="form-group has-feedback">
                        <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-users fa-fw"
                                                                   aria-hidden="true"></i></span>
                            <fmt:message var="username" bundle="${profile}" key="profile.username"/>
                            <fmt:message var="username_required" bundle="${profile}" key="profile.username.required"/>
                            <input type="text" class="form-control" name="username" placeholder="${username}"
                                   value="${user.userName}"
                                   data-error="${username_required}" required>
                        </div>
                        <div class="help-block with-errors"></div>
                        <c:if test="${validation.fields.username.emptyField}">
                            <span class="help-block">
                                <fmt:message bundle="${profile}" key="profile.username.required"/>
                            </span>
                        </c:if>
                    </div>
                </div>


                <div class="row">
                    <div
                            <tags:fieldValidation value="${validation.fields.firstname}"/>
                    >
                        <div class="form-group has-feedback col-sm-6">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user fa-fw"
                                                                   aria-hidden="true"></i></span>
                                <fmt:message var="first_name" bundle="${profile}" key="profile.first.name"/>
                                <fmt:message var="first_name_required" bundle="${profile}"
                                             key="profile.first.name.required"/>
                                <input type="text" class="form-control" name="firstname" placeholder="${first_name}"
                                       value="${user.firstName}"
                                       data-error="${first_name_required}" required>
                            </div>
                            <div class="help-block with-errors"></div>
                            <c:if test="${validation.fields.firstname.emptyField}">
                                <span class="help-block">
                                    <fmt:message bundle="${profile}" key="profile.first.name.required"/>
                                </span>
                            </c:if>
                        </div>


                    </div>


                    <div
                            <tags:fieldValidation value="${validation.fields.lastname}"/>
                    >
                        <div class="form-group has-feedback col-sm-6">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-user fa-fw"
                                                                   aria-hidden="true"></i></span>
                                <fmt:message var="last_name" bundle="${profile}" key="profile.last.name"/>
                                <fmt:message var="last_name_required" bundle="${profile}"
                                             key="profile.last.name.required"/>
                                <input type="text" class="form-control" name="lastname" placeholder="${last_name}"
                                       value="${user.lastName}"
                                       data-error="${last_name_required}" required/>
                            </div>
                            <div class="help-block with-errors"></div>
                            <c:if test="${validation.fields.lastname.emptyField}">
                                <span class="help-block">
                                    <fmt:message bundle="${profile}" key="profile.last.name.required"/>
                                </span>
                            </c:if>
                        </div>
                    </div>
                </div>

                <div
                        <tags:fieldValidation value="${validation.fields.date}"/>
                >
                    <div class="form-group has-feedback"> <!-- Date input -->
                        <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-id-card-o fa-fw"
                                                                   aria-hidden="true"></i></span>
                            <fmt:message var="birth_date" bundle="${profile}" key="profile.birth.date"/>
                            <fmt:message var="birth_date_required" bundle="${profile}"
                                         key="profile.birth.date.required"/>
                            <input class="form-control" id="date" name="date" placeholder="${birth_date}" type="text"
                                   value="${user.date}"
                                   data-error="${birth_date_required}" required/>
                        </div>
                        <div class="help-block with-errors"></div>
                        <c:if test="${validation.fields.date.emptyField
                            || validation.fields.date.incorrect}">
                            <span class="help-block">
                                <fmt:message bundle="${profile}" key="profile.birth.date.required"/>
                            </span>
                        </c:if>
                    </div>
                </div>

                <div class="row">
                    <div
                            <tags:fieldValidation value="${validation.fields.password}"/>
                    >
                        <div class="form-group has-feedback col-sm-6">
                            <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock fa-fw"
                                                               aria-hidden="true"></i></span>
                                <fmt:message var="password" bundle="${profile}"
                                             key="profile.password"/>
                                <input type="password"
                                       id="inputPassword"
                                       class="form-control"
                                       name="password"
                                       placeholder="${password}"
                                       data-minlength="6"
                                       value="${credentials.password}"
                                       required>
                            </div>
                            <div class="help-block with-errors">
                                <fmt:message bundle="${profile}" key="profile.chars.limitation"/>
                            </div>

                            <%--<c:if test="${validation.fields.password.emptyField}">
                                <span class="help-block">
                                    <fmt:message bundle="${profile}" key="profile.password.required"/>
                                </span>
                            </c:if>--%>
                        </div>
                    </div>


                    <div class="form-group has-feedback col-sm-6 pull-right">
                        <div
                                <tags:fieldValidation value="${validation.fields.confirm_password}"/>
                        >
                            <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock fa-fw"
                                                               aria-hidden="true"></i></span>
                                <fmt:message var="confirm_password" bundle="${profile}" key="profile.confirm.password"/>
                                <fmt:message var="password_doesnt_match" bundle="${profile}"
                                             key="profile.password.doesnt.match"/>
                                <fmt:message var="password_confirm_required" bundle="${profile}"
                                             key="profile.password.confirm.requlred"/>
                                <input type="password"
                                       id="inputPasswordConfirm"
                                       class="form-control"
                                       name="confirm_password"
                                       placeholder="${confirm_password}"
                                       data-error="${password_confirm_required}"
                                       data-match="#inputPassword"
                                       data-match-error="${password_doesnt_match}"
                                       required
                                >
                            </div>
                            <div class="help-block with-errors"></div>
                            <c:choose>
                                <c:when test="${validation.fields.confirm_password.emptyField}">
                                    <span class="help-block">
                                        <fmt:message bundle="${profile}" key="profile.password.confirm.requlred"/>
                                    </span>
                                </c:when>
                                <c:when test="${validation.fields.confirm_password.incorrect}">
                                    <span class="help-block">
                                        <fmt:message bundle="${profile}" key="profile.password.doesnt.match"/>
                                    </span>
                                </c:when>
                            </c:choose>

                            <%--<c:if test="${validation.fields.confirm_password.emptyField}">
                                <span class="help-block">
                                    <fmt:message bundle="${profile}" key="profile.password.confirm.requlred"/>
                                </span>
                            </c:if>--%>
                        </div>


                    </div>
                </div>

                <div class="form-group">
                    <!-- Button -->
                    <button id="btn-signup" type="submit" class="btn btn-info">
                        <fmt:message bundle="${profile}" key="profile.sign.up"/>
                    </button>
                    <%--<span style="margin-left:8px;">or</span>--%>
                </div>

            </form>
        </div>
    </div>

</div>

<%--datetimepicker--%>
<c:url var="bsDatepicker" value="/webjars/bootstrap-datepicker/1.6.1/js/bootstrap-datepicker.min.js"/>
<script src="${bsDatepicker}"></script>

<c:url var="bsDatepickerRu" value="/webjars/bootstrap-datepicker/1.6.1/locales/bootstrap-datepicker.ru.min.js"/>
<script src="${bsDatepickerRu}"></script>

<c:url var="bsValidator" value="/webjars/bootstrap-validator/0.11.5/js/validator.js"/>
<script src="${bsValidator}"></script>

<c:url var="bsDatepickerCss" value="/webjars/bootstrap-datepicker/1.6.1/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet" type="text/css" href="${bsDatepickerCss}"/>

<c:set var="localeCode" value="${pageContext.response.locale}"/>
<c:choose>
    <c:when test="${localeCode eq 'ru_RU'}">
        <c:set var="currentLocale" value="ru"/>
    </c:when>
    <c:otherwise>
        <c:set var="currentLocale" value="en"/>
    </c:otherwise>
</c:choose>

<script>
    $(document).ready(function () {
        var date_input = $('input[name="date"]'); //our date input has the name "date"
        var options = {
            format: 'dd.mm.yyyy',
            todayHighlight: true,
            autoclose: true,
            language: '${currentLocale}',
            startDate: new Date(1900, 1, 1),
            endDate: new Date(),
        };
        date_input.datepicker(options);
    })
</script>