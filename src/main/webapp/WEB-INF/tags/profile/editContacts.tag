<%@attribute name="tabId" rtexprvalue="true" required="true" %>
<%@ taglib prefix="profile" tagdir="/WEB-INF/tags/profile" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.user" var="userPage"/>
<jsp:useBean id="user" type="com.epam.training.lawAndSocial.model.User" scope="session"/>
<jsp:useBean id="contacts" type="com.epam.training.lawAndSocial.model.Contacts" scope="request"/>

<div id="${tabId}" class="tab-pane fade">
    <div class="content-box-large">
        <div class="panel-body">
            <form action="">
                <fieldset>
                    <div class="row">
                        <div class="form-group col-xs-4">
                            <fmt:message var="email" bundle="${userPage}" key="user.email"/>
                            <label>${email}</label>
                            <input class="form-control input-sm" placeholder="${email}" type="text"
                                   value="${contacts.email}">
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-xs-4">
                            <fmt:message var="phone" bundle="${userPage}" key="user.phone"/>
                            <label>${phone}</label>
                            <input class="form-control input-sm" placeholder="${phone}" type="text"
                                   value="${contacts.phone}">
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-xs-4">
                            <fmt:message var="website" bundle="${userPage}" key="user.website"/>
                            <label>${website}</label>
                            <input class="form-control input-sm" placeholder="${website}" type="text"
                                   value="${contacts.website}">
                        </div>
                    </div>

                </fieldset>
                <div>
                    <div class="btn btn-primary">
                        <i class="fa fa-save"></i>
                        <fmt:message bundle="${userPage}" key="user.save"/>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>