<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib prefix="profile" tagdir="/WEB-INF/tags/profile" %>
<%@ taglib prefix="community" tagdir="/WEB-INF/tags/community" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.root" var="root"/>
<fmt:setBundle basename="i18n.profile" var="profile"/>
<fmt:message var="title" bundle="${root}" key="root.news"/>

<jsp:useBean id="path" type="java.lang.String" scope="request"/>
<jsp:useBean id="newsList" type="java.util.List<com.epam.training.lawAndSocial.model.News>" scope="request"/>
<jsp:useBean id="validation" class="com.epam.training.lawAndSocial.web.servlet.model.FormValidation"
             scope="request"/>
<jsp:useBean id="pagesCount" type="java.lang.Integer" scope="request"/>

<tags:main title="${title}">

    <tags:navigationBlock>
        <c:url var="postNewsUrl" value="/news"/>
        <div class="panel" style="border: 0;">
            <form role="form" action="${postNewsUrl}" method="post">
                <fieldset>
                    <div class="form-group">
                        <label for="heading"><fmt:message bundle="${profile}" key="profile.message.heading"/></label>
                        <input id="heading" class="form-control input-sm" type="text" name="newsHeading"/>
                    </div>
                    <div class="form-group">
                        <label for="newsBox"><fmt:message bundle="${profile}" key="profile.message.text"/></label>
                        <textarea id="newsBox" class="form-control" rows="5" name="newsContent"></textarea>
                    </div>
                </fieldset>

                <div>
                    <button type="submit" class="btn btn-primary">
                        <i class="fa fa-envelope"></i>
                        <fmt:message bundle="${profile}" key="profile.send"/>
                    </button>
                </div>
            </form>
        </div>

        <c:if test="${validation.fields.newsContent.emptyField}">
            <div class="alert alert-danger">
                <fmt:message bundle="${profile}" key="profile.news.content.required"/>
            </div>
        </c:if>

        <c:forEach items="${newsList}" var="news" varStatus="count">
            <div class="panel panel-default" id="news">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-4" style="border-right: 2px solid #9f9f9f;">
                            <img class="img-circle avatar-img" src="data:image/jpeg;base64,${news.user.avatar}"/>
                            <c:out value="${news.user.firstName}"/> <c:out value="${news.user.lastName}"/>
                        </div>
                        <div class="col-xs-5">
                            <c:out value="${news.heading}"/>
                        </div>
                    </div>
                </div>
                <div class="panel-body"><c:out value="${news.content}"/></div>
                <div class="panel-footer">
                    <input hidden class="newsDateHidden" value="<c:out value="${news.date}"/>"/>
                    <div id="newsDate"></div>
                </div>
            </div>
        </c:forEach>

        <c:if test="${pagesCount > 1}">
            <community:pagination path="${path}"/>
        </c:if>

    </tags:navigationBlock>
</tags:main>

<script>
    $(document).ready(function () {
        var elements = document.getElementsByClassName('newsDateHidden');

        for (var i = 0, len = elements.length; i < len; i++) {
            var element = elements[i];
            var dateInMills = Number(element.value);

            var formatted = formatDate(new Date(dateInMills));
            $(element).parent().append('<p>' + formatted + '</p>');

            console.log("Elements: " + formatted);
        }
    });

    function formatDate(date) {
        return date.getDate() + "-" + (date.getMonth() + 1) + "-" + date.getFullYear() + " " +
                date.getHours() + ":" + date.getMinutes();
    }
</script>