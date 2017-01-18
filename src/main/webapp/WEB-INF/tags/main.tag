<%@attribute name="title" rtexprvalue="true" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.locale" var="loc"/>
<fmt:setBundle basename="i18n.root" var="root"/>
<fmt:setBundle basename="i18n.profile" var="profile"/>
<fmt:setBundle basename="i18n.user" var="userPage"/>

<!DOCTYPE html>
<html>
<head>
    <c:url var="bootstrapMain" value="/webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${bootstrapMain}"/>
    <c:url var="flagsCss" value="/webjars/flag-icon-css/2.4.0/css/flag-icon.min.css"/>
    <link rel="stylesheet" type="text/css" href="${flagsCss}"/>
    <c:url var="customCss" value="/static/css/custom.css"/>
    <link rel="stylesheet" type="text/css" href="${customCss}"/>
    <c:url var="paginationCss" value="/static/css/simplePagination.css"/>
    <link rel="stylesheet" type="text/css" href="${paginationCss}"/>
    <c:url var="fontAwesome" value="/webjars/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="${fontAwesome}"/>

    <c:url var="bsDatepickerCss" value="/webjars/bootstrap-datepicker/1.6.1/css/bootstrap-datepicker3.min.css"/>
    <link rel="stylesheet" type="text/css" href="${bsDatepickerCss}"/>

    <%--JQuery--%>
    <c:url var="jqueryJs" value="/webjars/jquery/1.12.4/jquery.min.js"/>
    <script src="${jqueryJs}"></script>

    <%--Bootstrap--%>
    <c:url var="bootstrapJs" value="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"/>
    <script src="${bootstrapJs}"></script>

    <%--Validator--%>
    <c:url var="bsValidator" value="/webjars/bootstrap-validator/0.11.5/js/validator.js"/>
    <script src="${bsValidator}"></script>

    <%--Datepicker--%>
    <c:url var="bsDatepicker" value="/webjars/bootstrap-datepicker/1.6.1/js/bootstrap-datepicker.min.js"/>
    <script src="${bsDatepicker}"></script>

    <c:url var="bsDatepickerRu" value="/webjars/bootstrap-datepicker/1.6.1/locales/bootstrap-datepicker.ru.min.js"/>
    <script src="${bsDatepickerRu}"></script>


    <c:set var="localeCode" value="${pageContext.response.locale}"/>
    <c:choose>
        <c:when test="${localeCode eq 'ru_RU'}">
            <c:set var="currentLocale" value="ru"/>
        </c:when>
        <c:otherwise>
            <c:set var="currentLocale" value="en"/>
        </c:otherwise>
    </c:choose>

    <title>${title}</title>
</head>

<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">
                    <fmt:message bundle="${root}" key="root.toogle.navigation"/>
                </span>
            </button>
            <c:url var="rootUrl" value="/"/>
            <a class="navbar-brand" href="${rootUrl}"><i class="fa fa-balance-scale"></i>
                <fmt:message bundle="${root}" key="root.site.name"/>
            </a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <c:url var="loginUrl" value="/login"/>
                    <a href="${loginUrl}">
                        <fmt:message bundle="${profile}" key="profile.log.in"/>
                    </a>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <c:if test="${sessionScope.user != null}">
                    <li>
                        <c:url var="newsUrl" value="/news"/>
                        <a href="${newsUrl}">
                            <fmt:message bundle="${root}" key="root.news"/>
                        </a>
                    </li>

                    <li>
                        <c:url var="communityUrl" value="/community"/>
                        <a href="${communityUrl}">
                            <fmt:message bundle="${profile}" key="profile.community"/>
                        </a>
                    </li>

                    <jsp:useBean id="user" type="com.epam.training.lawAndSocial.model.User" scope="session"/>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false">
                            <i class="fa fa-user-circle"></i>
                                ${user.userName}
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <c:url var="userEditUrl" value="/user/edit"/>
                                <a href="${userEditUrl}">
                                    <i class="fa fa-cog"></i>
                                    <fmt:message bundle="${userPage}" key="user.edit"/>
                                </a>
                            </li>

                            <li>
                                <c:url var="signOutUrl" value="/signout"/>
                                <form class="navbar-form" action="${signOutUrl}" method="post">
                                    <fmt:message var="signout" bundle="${userPage}" key="user.sign.out"/>
                                    <button class="btn btn-sm btn-link" role="link" type="submit" title="${signout}">
                                        <i class="fa fa-sign-out"></i> ${signout}
                                    </button>
                                </form>
                            </li>
                        </ul>
                    </li>
                </c:if>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">
                        <i class="fa fa-globe"></i>
                        <fmt:message bundle="${loc}" key="locale.lang"/>
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <c:url var="localeUrl" value="/locale"/>
                        <c:set var="currentUrl"
                               value="${requestScope['javax.servlet.forward.request_uri']}?${pageContext.request.queryString}"/>
                        <li>
                            <form class="navbar-form" action="${localeUrl}" method="post">
                                <input type="hidden" name="redirect_to" value="${currentUrl}"/>
                                <input type="hidden" name="locale" value="en">
                                <fmt:message var="enTitle" bundle="${loc}" key="locale.select.lang.en"/>
                                <button class="btn btn-sm btn-link" role="link" type="submit" title="${enTitle}">
                                    <i class="flag-icon flag-icon-us"></i> ${enTitle}
                                </button>
                            </form>
                        </li>

                        <li>
                            <form class="navbar-form" action="${localeUrl}" method="post">
                                <input type="hidden" name="locale" value="ru">
                                <input type="hidden" name="redirect_to" value="${currentUrl}">
                                <fmt:message var="ruTitle" bundle="${loc}" key="locale.select.lang.ru"/>
                                <button class="btn btn-sm btn-link" role="link" type="submit" title="${ruTitle}">
                                    <i class="flag-icon flag-icon-ru"></i> ${ruTitle}
                                </button>
                            </form>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <jsp:doBody/>
</div>

<script>
    $(document).ready(function () {
        var date_input = $('input[name="date"]');
        var options = {
            format: 'dd.mm.yyyy',
            todayHighlight: true,
            autoclose: true,
            language: '${currentLocale}',
            startDate: new Date(1900, 1, 1),
            endDate: new Date()
        };
        date_input.datepicker(options);
    });

    $(document).ready(function () {
        yearPicker();
    });

    function yearPicker() {
        var date_input_year = $('.dateYear');
        var options = {
            format: "yyyy",
            todayHighlight: true,
            autoclose: true,
            language: '${currentLocale}',
            viewMode: "years",
            minViewMode: "years",
            startDate: new Date(1900, 1, 1),
            endDate: new Date()
        };
        date_input_year.datepicker(options);
    }

</script>

</body>
</html>