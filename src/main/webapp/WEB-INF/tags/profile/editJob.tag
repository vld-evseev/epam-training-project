<%@attribute name="tabId" rtexprvalue="true" required="true" %>
<%@ taglib prefix="profile" tagdir="/WEB-INF/tags/profile" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.user" var="userPage"/>
<jsp:useBean id="user" type="com.epam.training.lawAndSocial.model.User" scope="session"/>

<div id="${tabId}" class="tab-pane fade">
    <div class="content-box-large">
        <div class="panel-body">
            <form action="">
                <fieldset>
                    <div class="row">
                        <div class="form-group col-xs-4">
                            <fmt:message var="name" bundle="${userPage}" key="user.education.name"/>
                            <label for="orgName"><fmt:message bundle="${userPage}" key="user.job.organization"/></label>
                            <input id="orgName" class="form-control input-sm" placeholder="${name}" type="text">
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-xs-4">
                            <label for="position"><fmt:message bundle="${userPage}" key="user.job.position"/></label>
                            <input id="position" class="form-control input-sm" type="text">
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-xs-4">
                            <label for="industry"><fmt:message bundle="${userPage}" key="user.job.industry"/></label>
                            <input id="industry" class="form-control input-sm" type="text">
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-xs-4">
                            <label for="orgWebsite"><fmt:message bundle="${userPage}"
                                                                 key="user.job.organization.website"/></label>
                            <input id="orgWebsite" class="form-control input-sm" placeholder="http://" type="text">
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xs-4">
                            <fmt:message var="jobYears" bundle="${userPage}" key="user.job.years"/>
                            <label>${jobYears}</label>
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <fmt:message var="jobYearsFrom" bundle="${userPage}"
                                                 key="user.education.years.from"/>
                                    <input class="form-control input-sm" id="dateYear" name="dateYear"
                                           placeholder="${jobYearsFrom}"
                                           type="text"/>
                                </div>
                                <div class="form-group col-xs-6">
                                    <fmt:message var="jobYearsTo" bundle="${userPage}"
                                                 key="user.education.years.to"/>
                                    <input class="form-control input-sm" id="dateYear" name="dateYear"
                                           placeholder="${jobYearsTo}"
                                           type="text"/>
                                </div>
                            </div>
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