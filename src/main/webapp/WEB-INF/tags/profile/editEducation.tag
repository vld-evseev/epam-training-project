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
                        <div class="col-sm-6">
                            <div class="panel panel-primary">
                                <div class="panel-heading"><fmt:message bundle="${userPage}"
                                                                        key="user.education.school"/></div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="form-group col-md-12">
                                            <fmt:message var="name" bundle="${userPage}" key="user.education.name"/>
                                            <label for="schoolName"><fmt:message bundle="${userPage}"
                                                                                 key="user.education.school"/></label>
                                            <input id="schoolName" class="form-control input-sm" placeholder="${name}"
                                                   type="text">
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-md-12">
                                            <label for="schoolCountry"><fmt:message bundle="${userPage}"
                                                                                    key="user.country"/></label>
                                            <input id="schoolCountry" class="form-control input-sm" type="text">
                                        </div>
                                    </div>


                                    <div class="row">
                                        <div class="form-group col-md-12">
                                            <label for="schoolCity"><fmt:message bundle="${userPage}"
                                                                                 key="user.city"/></label>
                                            <input id="schoolCity" class="form-control input-sm" type="text">
                                        </div>
                                    </div>


                                    <div class="row">
                                        <div class="col-md-12">
                                            <fmt:message var="schoolYears" bundle="${userPage}"
                                                         key="user.education.years"/>
                                            <label>${schoolYears}</label>
                                            <div class="row">
                                                <div class="form-group col-xs-6">
                                                    <fmt:message var="schoolYearsFrom" bundle="${userPage}"
                                                                 key="user.education.years.from"/>
                                                    <input class="form-control input-sm" id="dateYear" name="dateYear"
                                                           placeholder="${schoolYearsFrom}"
                                                           type="text"/>
                                                </div>
                                                <div class="form-group col-xs-6">
                                                    <fmt:message var="schoolYearsTo" bundle="${userPage}"
                                                                 key="user.education.years.to"/>
                                                    <input class="form-control input-sm" id="dateYear" name="dateYear"
                                                           placeholder="${schoolYearsTo}"
                                                           type="text"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-sm-6">
                            <div class="panel panel-primary">
                                <div class="panel-heading"><fmt:message bundle="${userPage}"
                                                                        key="user.education.university"/></div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="form-group col-md-12">
                                            <fmt:message var="name" bundle="${userPage}" key="user.education.name"/>
                                            <label for="univName"><fmt:message bundle="${userPage}"
                                                                               key="user.education.university"/></label>
                                            <input id="univName" class="form-control input-sm" placeholder="${name}"
                                                   type="text">
                                        </div>
                                    </div>


                                    <div class="row">
                                        <div class="form-group col-md-12">
                                            <label for="univCountry"><fmt:message bundle="${userPage}"
                                                                                  key="user.country"/></label>
                                            <input id="univCountry" class="form-control input-sm" type="text">
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="form-group col-md-12">
                                            <label for="univCity"><fmt:message bundle="${userPage}"
                                                                               key="user.city"/></label>
                                            <input id="univCity" class="form-control input-sm" type="text">
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <fmt:message var="univYears" bundle="${userPage}"
                                                         key="user.education.years"/>
                                            <label>${univYears}</label>
                                            <div class="row">
                                                <div class="form-group col-xs-6">
                                                    <fmt:message var="univYearsFrom" bundle="${userPage}"
                                                                 key="user.education.years.from"/>
                                                    <input class="form-control input-sm" id="dateYear" name="dateYear"
                                                           placeholder="${univYearsFrom}"
                                                           type="text"/>
                                                </div>
                                                <div class="form-group col-xs-6">
                                                    <fmt:message var="univYearsTo" bundle="${userPage}"
                                                                 key="user.education.years.to"/>
                                                    <input class="form-control input-sm" id="dateYear" name="dateYear"
                                                           placeholder="${univYearsTo}"
                                                           type="text"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
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