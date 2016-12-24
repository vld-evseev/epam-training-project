<%@attribute name="tabId" rtexprvalue="true" required="true" %>
<%@ taglib prefix="profile" tagdir="/WEB-INF/tags/profile" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="plugins" tagdir="/WEB-INF/tags/plugins" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.user" var="userPage"/>
<jsp:useBean id="user" type="com.epam.training.lawAndSocial.model.User" scope="session"/>

<div id="${tabId}" class="tab-pane fade in active">
    <div class="content-box-large">
        <div class="panel-body">
            <c:url var="loginUrl" value="/login"/>

            <form action="">
                <fieldset>
                    <div class="row">
                        <div class="form-group col-xs-4">
                            <label for="firstName"><fmt:message bundle="${userPage}" key="user.first.name"/></label>
                            <input id="firstName" class="form-control input-sm" type="text"
                                   value="${user.firstName}">
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-xs-4">
                            <label for="lastName"><fmt:message bundle="${userPage}" key="user.last.name"/></label>
                            <input id="lastName" class="form-control input-sm" type="text"
                                   value="${user.lastName}">
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-xs-4">
                            <label for="patronymic"><fmt:message bundle="${userPage}" key="user.patronymic"/></label>
                            <input id="patronymic" class="form-control input-sm" type="text">
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-xs-4">
                            <label for="gender" class="control-label">
                                <fmt:message bundle="${userPage}" key="user.gender"/>
                            </label>
                            <select id="gender" class="form-control input-sm">
                                <option><fmt:message bundle="${userPage}"
                                                     key="user.gender.male"/></option>
                                <option><fmt:message bundle="${userPage}"
                                                     key="user.gender.female"/></option>
                            </select>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-xs-4">
                            <fmt:message var="birth_date" bundle="${userPage}" key="user.birth.date"/>
                            <label>${birth_date}</label>
                            <input class="form-control input-sm" id="date" name="date" placeholder="${birth_date}"
                                   type="text"
                                   value="${user.date}"/>
                        </div>
                    </div>
                </fieldset>
                <div>
                    <button type="submit" class="btn btn-success">
                        <i class="fa fa-save"></i>
                        <fmt:message bundle="${userPage}" key="user.save"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<plugins:datePicker/>