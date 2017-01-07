<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.root" var="root"/>
<fmt:setBundle basename="i18n.profile" var="profile"/>
<fmt:setBundle basename="i18n.error" var="error"/>
<jsp:useBean id="validation" class="com.epam.training.lawAndSocial.web.servlet.model.FormValidation"
             scope="request"/>
<jsp:useBean id="credentials" type="com.epam.training.lawAndSocial.model.Credentials" scope="request"/>

<div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="panel-title">
                <fmt:message bundle="${profile}" key="profile.sign.in"/>
            </div>
        </div>

        <div style="padding-top:30px; padding-left:40px; padding-right:40px" class="panel-body">
            <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>

            <c:url var="loginUrl" value="/login"/>
            <form id="loginform" data-toggle="validator" class="form-horizontal" role="form" action="${loginUrl}"
                  method="post">

                <div
                        <c:choose>
                            <c:when test="${not empty validation.fields.username}">
                                class="has-error"
                            </c:when>
                            <c:otherwise>
                                style="margin-bottom: 25px"
                            </c:otherwise>
                        </c:choose>
                >
                    <div class="form-group">
                        <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user fa-fw"
                                                           aria-hidden="true"></i></span>
                            <fmt:message var="username" bundle="${profile}" key="profile.username"/>
                            <fmt:message var="username_required" bundle="${profile}" key="profile.username.required"/>
                            <input id="login-username" type="text" class="form-control" name="username"
                                   value="${credentials.username}"
                                   placeholder="${username}"
                                   data-error="${username_required}" required>

                        </div>
                        <div class="help-block with-errors"></div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="input-group">

                            <span class="input-group-addon"><i class="fa fa-lock fa-fw"
                                                               aria-hidden="true"></i></span>
                        <fmt:message var="password" bundle="${profile}" key="profile.password"/>
                        <input id="login-password" type="password" class="form-control" name="password"
                               value="${credentials.password}"
                               placeholder="${password}"
                               data-minlength="6"
                               required>
                    </div>
                    <div class="help-block">
                        <fmt:message bundle="${profile}" key="profile.chars.limitation"/>
                    </div>
                </div>

                <div style="margin-top:10px" class="form-group">
                    <!-- Button -->

                    <div class="col-sm-12 controls">
                        <button type="submit" class="btn btn-success">
                            <fmt:message bundle="${profile}" key="profile.log.in"/>
                        </button>

                        <%--<a id="btn-login" href="#" class="btn btn-success">
                            <fmt:message bundle="${profile}" key="profile.log.in"/>
                        </a>--%>
                        <%--<a id="btn-fblogin" href="#" class="btn btn-primary">
                            Login with Facebook
                        </a>--%>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-md-12 control">
                        <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%">
                            <fmt:message bundle="${profile}" key="profile.message.firsttime"/>
                            <c:url var="registrationUrl" value="/registration"/>
                            <a href="${registrationUrl}" <%--onClick="$('#loginbox').hide(); $('#signupbox').show()"--%>>
                                <fmt:message bundle="${profile}" key="profile.sign.up.here"/>
                            </a>
                        </div>
                    </div>
                </div>
            </form>

            <c:choose>
                <c:when test="${validation.errors.INVALID_CREDENTIALS}">
                    <fmt:message var="showInvalidCredsMessage" bundle="${error}" key="error.credentials.invalid"/>
                    <tags:alert value="${showInvalidCredsMessage}"/>
                </c:when>
            </c:choose>

        </div>
    </div>
</div>

<c:url var="bsValidator" value="/webjars/bootstrap-validator/0.11.5/js/validator.js"/>
<script src="${bsValidator}"></script>