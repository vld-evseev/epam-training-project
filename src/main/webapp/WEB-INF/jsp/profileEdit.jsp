<%@ taglib prefix="profile" tagdir="/WEB-INF/tags/profile" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.root" var="root"/>
<fmt:setBundle basename="i18n.user" var="userPage"/>
<fmt:setBundle basename="i18n.profile" var="profile"/>
<fmt:message var="title" bundle="${userPage}" key="user.edit.profile"/>
<%--<jsp:useBean id="user" type="com.epam.training.lawAndSocial.model.User" scope="session"/>--%>

<tags:main title="${title}">

    <tags:leftSide>
        <ul class="nav nav-tabs" id="settingsTab">
            <li class="active">
                <a data-toggle="tab" href="#common"><fmt:message bundle="${userPage}"
                                                                 key="user.common.information"/></a>
            </li>
            <li><a data-toggle="tab" href="#contacts"><fmt:message bundle="${userPage}" key="user.contact.details"/></a>
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

    </tags:leftSide>

</tags:main>

<script>
    $('#settingsTab a').click(function (e) {
        e.preventDefault();
        $(this).tab('show');
    });

    // store the currently selected tab in the hash value
    $("ul.nav-tabs > li > a").on("shown.bs.tab", function (e) {
        var id = $(e.target).attr("href").substr(1);
        window.location.hash = id;
    });

    // on load of the page: switch to the currently selected tab
    var hash = window.location.hash;
    $('#settingsTab a[href="' + hash + '"]').tab('show');

</script>