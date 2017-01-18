<%@attribute name="tabId" rtexprvalue="true" required="true" %>
<%@ taglib prefix="profile" tagdir="/WEB-INF/tags/profile" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.user" var="userPage"/>
<fmt:setBundle basename="i18n.profile" var="profile"/>
<jsp:useBean id="contacts" type="com.epam.training.lawAndSocial.model.Contacts" scope="session"/>
<jsp:useBean id="validation" class="com.epam.training.lawAndSocial.web.servlet.model.FormValidation"
             scope="request"/>

<div id="${tabId}" class="tab-pane fade">
    <div class="content-box-large">
        <div class="panel-body">
            <c:url var="contactsInfoEditUrl" value="/user/edit/contacts"/>
            <form role="form" data-toggle="validator" action="${contactsInfoEditUrl}" method="post">
                <fieldset>
                    <div
                            <tags:fieldValidation value="${validation.fields.email}"/>
                    >
                        <%--Email--%>
                        <div class="row">
                            <div class="form-group col-xs-4">
                                <fmt:message var="email" bundle="${userPage}" key="user.email"/>
                                <fmt:message var="email_incorrect" bundle="${profile}" key="profile.email.incorrect"/>
                                <label><c:out value="${email}"/></label>
                                <input class="form-control input-sm"
                                       placeholder="${email}"
                                       type="email"
                                       name="email"
                                       value="<c:out value="${contacts.email}"/>"
                                       data-error="${email_incorrect}"
                                       required
                                />
                            </div>
                            <div class="help-block with-errors"></div>
                        </div>
                        <c:if test="${validation.fields.email.emptyField}">
                            <span class="help-block">
                                <fmt:message bundle="${profile}" key="profile.email.required"/>
                            </span>
                        </c:if>
                    </div>

                    <%--Pnone--%>
                    <div class="row">
                        <div class="form-group col-xs-4">
                            <fmt:message var="phone" bundle="${userPage}" key="user.phone"/>
                            <label>${phone}</label>
                            <input class="form-control input-sm"
                                   placeholder="${phone}"
                                   type="text"
                                   name="phone"
                                   value="<c:out value="${contacts.phone}" />"
                            />
                        </div>
                    </div>

                    <%--Website--%>
                    <div class="row">
                        <div class="form-group col-xs-4">
                            <fmt:message var="website" bundle="${userPage}" key="user.website"/>
                            <label>${website}</label>
                            <input class="form-control input-sm"
                                   placeholder="${website}"
                                   type="text" name="website"
                                   value="<c:out value="${contacts.website}"/>"
                            />
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