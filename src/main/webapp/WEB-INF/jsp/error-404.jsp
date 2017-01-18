<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.root" var="root"/>
<!DOCTYPE html>
<html>
<head>
    <c:url var="bootstrapMain" value="/webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="${bootstrapMain}"/>
    <c:url var="customCss" value="/static/css/custom.css"/>
    <link rel="stylesheet" type="text/css" href="${customCss}"/>
    <c:url var="jqueryJs" value="/webjars/jquery/1.12.4/jquery.min.js"/>
    <script src="${jqueryJs}"></script>
    <c:url var="bootstrapJs" value="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"/>
    <script src="${bootstrapJs}"></script>
    <title><fmt:message bundle="${root}" key="root.page.not.found"/></title>
</head>
<body>
<div class="container">

    <div class="panel panel-default error-panel">
        <div class="panel-heading">
            <fmt:message bundle="${root}" key="root.page.not.found"/>
        </div>
        <div class="panel-body">
            <p><b><fmt:message bundle="${root}" key="root.error.code"/>:</b> ${pageContext.errorData.statusCode}</p>
            <p><b><fmt:message bundle="${root}"
                               key="root.requested.uri"/>:</b> ${pageContext.request.scheme}://${header.host}${pageContext.errorData.requestURI}
            </p><br/>
            <button class="btn btn-default" onclick="history.back()">
                <fmt:message bundle="${root}" key="root.back.to.previous.page"/>
            </button>
        </div>
    </div>
</div>

</body>
</html>