<%@ taglib prefix="profile" tagdir="/WEB-INF/tags/profile" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.user" var="userPage"/>
<fmt:message var="title" bundle="${userPage}" key="user.edit.profile"/>
<jsp:useBean id="user" type="com.epam.training.lawAndSocial.model.User" scope="session"/>
<jsp:useBean id="activeTab" type="java.util.Map" scope="request"/>

<tags:main title="${title}">

    <tags:navigationBlock>
        <ul class="nav nav-tabs" id="settingsTab" data-tabs="tabs">
            <li data-toggle="tab"
                    <c:if test="${activeTab.commonInfoTab}">
                        class="active"
                    </c:if>
            >
                <a data-toggle="tab" href="#common"><fmt:message bundle="${userPage}"
                                                                 key="user.common.information"/></a>
            </li>
            <li data-toggle="tab">
                <a data-toggle="tab" href="#contacts"><fmt:message bundle="${userPage}" key="user.contact.details"/></a>
            </li>
            <li><a data-toggle="tab" href="#education"><fmt:message bundle="${userPage}" key="user.education"/></a></li>
            <li><a data-toggle="tab" href="#job"><fmt:message bundle="${userPage}" key="user.job"/></a></li>
        </ul>

        <div class="tab-content">
            <profile:editCommon tabId="common"/>
            <profile:editContacts tabId="contacts"/>
            <profile:editEducation tabId="education"/>
            <profile:editJob tabId="job"/>
        </div>

    </tags:navigationBlock>

</tags:main>

<script>
    $(document).ready(function () {
        var contactsTabActive = false;
        <c:if test="${activeTab.contactsInfoTab}">
        contactsTabActive = true;
        </c:if>
        var educationTabActive = false;
        <c:if test="${activeTab.educationInfoTab}" >
        educationTabActive = true;
        </c:if>
        var jobTabActive = false;
        <c:if test="${activeTab.jobInfoTab}" >
        jobTabActive = true;
        </c:if>

        tabpage = "#common";
        if (contactsTabActive) {
            tabpage = "#contacts";
        } else if (educationTabActive) {
            tabpage = "#education";
        } else if (jobTabActive) {
            tabpage = "#job";
        }

        var selectedTab = $('#settingsTab a[href="' + tabpage + '"]');
        selectedTab.trigger('click', true);
    });

    $('#settingsTab a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });

    $("ul.nav-tabs > li > a").on("shown.bs.tab", function (e) {
        var id = $(e.target).attr("href").substr(1);
        window.location.hash = id;
    });

    var hash = window.location.hash;
    $('#settingsTab a[href="' + hash + '"]').tab('show');

</script>