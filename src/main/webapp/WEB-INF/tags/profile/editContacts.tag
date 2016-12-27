<%@attribute name="tabId" rtexprvalue="true" required="true" %>
<%@ taglib prefix="profile" tagdir="/WEB-INF/tags/profile" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.user" var="userPage"/>
<fmt:setBundle basename="i18n.profile" var="profile"/>
<%--<jsp:useBean id="user" type="com.epam.training.lawAndSocial.model.User" scope="session"/>--%>
<jsp:useBean id="contacts" type="com.epam.training.lawAndSocial.model.Contacts" scope="session"/>
<jsp:useBean id="validation" class="com.epam.training.lawAndSocial.web.servlet.model.FormValidation"
             scope="request"/>

<div id="${tabId}" class="tab-pane fade">
    <div class="content-box-large">
        <div class="panel-body">
            <c:url var="contactsInfoEditUrl" value="/user/edit/contacts"/>
            <form role="form" action="${contactsInfoEditUrl}" method="post">
                <fieldset>
                    <input type="hidden" name="redirect_to" value="${currentUrl}"/>

                    <div
                            <tags:fieldValidation value="${validation.fields.email}"/>
                    >
                        <div class="row">
                            <div class="form-group col-xs-4">
                                <fmt:message var="email" bundle="${userPage}" key="user.email"/>
                                <label>${email}</label>
                                <input class="form-control input-sm" placeholder="${email}" type="text" name="email"
                                       value="<c:out value="${contacts.email}"/>">
                            </div>
                        </div>
                        <c:if test="${validation.fields.email.emptyField}">
                            <span class="help-block">
                                <fmt:message bundle="${profile}" key="profile.email.required"/>
                            </span>
                        </c:if>
                    </div>


                    <div class="row">
                        <div class="form-group col-xs-4">
                            <fmt:message var="phone" bundle="${userPage}" key="user.phone"/>
                            <label>${phone}</label>
                            <input class="form-control input-sm" placeholder="${phone}" type="text" name="phone"
                                   value="<c:out value="${contacts.phone}" />">
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-xs-4">
                            <fmt:message var="website" bundle="${userPage}" key="user.website"/>
                            <label>${website}</label>
                            <input class="form-control input-sm" placeholder="${website}" type="text" name="website"
                                   value="<c:out value="${contacts.website}"/>">
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