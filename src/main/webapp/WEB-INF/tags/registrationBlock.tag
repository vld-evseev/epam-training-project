<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="plugins" tagdir="/WEB-INF/tags/plugins" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.profile" var="profile"/>
<fmt:setBundle basename="i18n.error" var="error"/>
<jsp:useBean id="newUser" type="com.epam.training.lawAndSocial.model.User" scope="request"/>
<jsp:useBean id="contacts" type="com.epam.training.lawAndSocial.model.Contacts" scope="request"/>
<jsp:useBean id="credentials" type="com.epam.training.lawAndSocial.model.Credentials" scope="request"/>
<jsp:useBean id="validation" class="com.epam.training.lawAndSocial.web.servlet.model.FormValidation"
             scope="request"/>

<div id="signupbox" style="margin-top:50px"
     class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
    <div class="panel panel-info">
        <div class="panel-heading">
            <div class="panel-title"><fmt:message bundle="${profile}" key="profile.sign.up"/></div>
            <div style="float:right; font-size: 85%; position: relative; top:-10px">
                <c:url var="loginUrl" value="/login"/>
                <a id="signinlink" href="${loginUrl}">
                    <fmt:message bundle="${profile}" key="profile.sign.in"/>
                </a>
            </div>
        </div>
        <div class="panel-body">

            <%--Form--%>
            <c:url var="registrationUrl" value="/registration"/>
            <form id="signupform" data-toggle="validator" role="form" action="${registrationUrl}" method="post">

                <%--Alert block--%>
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

                <%--Email--%>
                <div
                        <tags:fieldValidation value="${validation.fields.email}"/>
                >
                    <div class="form-group has-feedback">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope fa-fw"
                                                               aria-hidden="true"></i></span>
                            <fmt:message var="email_address" bundle="${profile}" key="profile.email.address"/>
                            <fmt:message var="email_required" bundle="${profile}" key="profile.email.required"/>
                            <fmt:message var="email_incorrect" bundle="${profile}" key="profile.email.incorrect"/>
                            <input type="email"
                                   class="form-control"
                                   name="email"
                                   placeholder="${email_address}"
                                   value="<c:out value="${contacts.email}"/>"
                                   data-error="${email_incorrect}"
                                   required>
                        </div>
                        <div class="help-block with-errors"></div>
                        <c:if test="${validation.fields.email.emptyField}">
                            <span class="help-block">
                                <fmt:message bundle="${profile}" key="profile.email.required"/>
                            </span>
                        </c:if>
                    </div>
                </div>

                <%--Username--%>
                <div
                        <c:choose>
                            <c:when test="${not empty validation.fields.username
                                || validation.errors.USERNAME_EXISTS}">
                                class="has-error"
                            </c:when>
                            <c:otherwise>
                                class=""
                            </c:otherwise>
                        </c:choose>
                >
                    <div class="form-group has-feedback">
                        <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-users fa-fw"
                                                                   aria-hidden="true"></i></span>
                            <fmt:message var="username" bundle="${profile}" key="profile.username"/>
                            <fmt:message var="username_required" bundle="${profile}" key="profile.username.required"/>
                            <input type="text" class="form-control" name="username" placeholder="${username}"
                                   value="<c:out value="${newUser.userName}"/>"
                                   data-error="${username_required}" required>
                        </div>
                        <div class="help-block with-errors"></div>
                        <c:choose>
                            <c:when test="${validation.fields.username.emptyField}">
                                <span class="help-block">
                                    <fmt:message bundle="${profile}" key="profile.username.required"/>
                                </span>
                            </c:when>
                            <c:when test="${validation.errors.USERNAME_EXISTS}">
                                <span class="help-block">
                                    <fmt:message bundle="${error}" key="error.username.exists"/>
                                </span>
                            </c:when>
                        </c:choose>
                    </div>
                </div>

                <%--Firstname--%>
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
                                       value="<c:out value="${newUser.firstName}"/>"
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

                    <%--Lastname--%>
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
                                       value="<c:out value="${newUser.lastName}"/>"
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

                <%--Birth date--%>
                <div
                        <tags:fieldValidation value="${validation.fields.date}"/>
                >
                    <div class="form-group has-feedback">
                        <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-id-card-o fa-fw"
                                                                   aria-hidden="true"></i></span>
                            <fmt:message var="birth_date" bundle="${profile}" key="profile.birth.date"/>
                            <fmt:message var="birth_date_required" bundle="${profile}"
                                         key="profile.birth.date.required"/>
                            <input class="form-control" id="date" name="date" placeholder="${birth_date}" type="text"
                                   value="<c:out value="${newUser.date}"/>"
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

                    <%--Password--%>
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
                        </div>
                    </div>

                    <%--Confirm password--%>
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
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <button id="btn-signup" type="submit" class="btn btn-info">
                        <fmt:message bundle="${profile}" key="profile.sign.up"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

