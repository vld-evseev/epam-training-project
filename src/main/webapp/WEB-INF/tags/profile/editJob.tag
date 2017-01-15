<%@attribute name="tabId" rtexprvalue="true" required="true" %>
<%@ taglib prefix="profile" tagdir="/WEB-INF/tags/profile" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.user" var="userPage"/>
<jsp:useBean id="jobInfo" type="com.epam.training.lawAndSocial.model.Job" scope="session"/>
<jsp:useBean id="validation" class="com.epam.training.lawAndSocial.web.servlet.model.FormValidation"
             scope="request"/>

<div id="${tabId}" class="tab-pane fade">
    <div class="content-box-large">
        <div class="panel-body">
            <c:url var="jobInfoEditUrl" value="/user/edit/job"/>
            <form id="jobInfoForm" role="form" action="${jobInfoEditUrl}" method="post">
                <fieldset>
                    <input type="hidden" id="id" name="id" value="${jobInfo.id}"/>
                    <input type="hidden" id="userId" name="userId" value="${jobInfo.userId}"/>

                    <div class="row">
                        <div class="form-group col-xs-4">
                            <fmt:message var="name" bundle="${userPage}" key="user.education.name"/>
                            <label for="organization"><fmt:message bundle="${userPage}"
                                                                   key="user.job.organization"/></label>
                            <input id="organization" class="form-control input-sm"
                                   placeholder="${name}"
                                   name="organization"
                                   type="text"
                                   value="<c:out value="${jobInfo.organization}"/>"
                            >
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-xs-4">
                            <label for="position"><fmt:message bundle="${userPage}" key="user.job.position"/></label>
                            <input id="position" class="form-control input-sm"
                                   type="text"
                                   name="position"
                                   value="<c:out value="${jobInfo.position}"/>"
                            >
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-xs-4">
                            <label for="industry"><fmt:message bundle="${userPage}" key="user.job.industry"/></label>
                            <input id="industry" class="form-control input-sm"
                                   type="text"
                                   name="industry"
                                   value="<c:out value="${jobInfo.industry}"/>"
                            >
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-xs-4">
                            <label for="website"><fmt:message bundle="${userPage}"
                                                              key="user.job.organization.website"/></label>
                            <input id="website" class="form-control input-sm"
                                   placeholder="http://"
                                   type="text"
                                   name="website"
                                   value="<c:out value="${jobInfo.website}"/>"
                            >
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xs-4">
                            <fmt:message var="jobYears" bundle="${userPage}" key="user.job.years"/>
                            <label>${jobYears}</label>
                            <div class="row">
                                <div class="form-group col-xs-6">
                                    <fmt:message var="jobYearFrom" bundle="${userPage}"
                                                 key="user.education.years.from"/>
                                    <input class="form-control input-sm dateYear" id="jobYearFrom" name="jobYearFrom"
                                           placeholder="${jobYearFrom}"
                                           type="text"
                                           value="<c:out value="${jobInfo.yearFrom}"/>"
                                    />
                                </div>
                                <div class="form-group col-xs-6">
                                    <fmt:message var="jobYearTo" bundle="${userPage}"
                                                 key="user.education.years.to"/>
                                    <input class="form-control input-sm dateYear" id="jobYearTo" name="jobYearTo"
                                           placeholder="${jobYearTo}"
                                           type="text"
                                           value="<c:out value="${jobInfo.yearTo}"/>"
                                    />
                                </div>
                            </div>
                        </div>
                    </div>

                    <input type="hidden" id="jsonJobData" name="jsonJobData"/>

                </fieldset>
                <div>
                    <button type="submit" class="btn btn-primary" onclick="collectJobInfoData()">
                        <i class="fa fa-save"></i>
                        <fmt:message bundle="${userPage}" key="user.save"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    function collectJobInfoData() {
        var elements = document.forms['jobInfoForm'].getElementsByTagName("input");

        console.log("Elements: " + elements);

        var id = $(elements).filter('#id');
        var userId = $(elements).filter('#userId');
        var organization = $(elements).filter('#organization');
        var position = $(elements).filter('#position');
        var industry = $(elements).filter('#industry');
        var website = $(elements).filter('#website');
        var yearFrom = $(elements).filter('#jobYearFrom');
        var yearTo = $(elements).filter('#jobYearTo');

        var jsonMsg = {
            id: !id.val() ? -1 : id.val(),
            userId: userId.val(),
            organization: organization.val(),
            position: position.val(),
            industry: industry.val(),
            website: website.val(),
            yearFrom: !yearFrom.val() ? 0 : yearFrom.val(),
            yearTo: !yearTo.val() ? 0 : yearTo.val()
        };
        console.log("Data: " + JSON.stringify(jsonMsg));

        $('#jsonJobData').attr('value', JSON.stringify(jsonMsg));
    }

</script>