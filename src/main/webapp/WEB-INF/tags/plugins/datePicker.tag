<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--datetimepicker--%>
<c:url var="bsDatepicker" value="/webjars/bootstrap-datepicker/1.6.1/js/bootstrap-datepicker.min.js"/>
<script src="${bsDatepicker}"></script>

<c:url var="bsDatepickerRu" value="/webjars/bootstrap-datepicker/1.6.1/locales/bootstrap-datepicker.ru.min.js"/>
<script src="${bsDatepickerRu}"></script>

<c:url var="bsValidator" value="/webjars/bootstrap-validator/0.11.5/js/validator.js"/>
<script src="${bsValidator}"></script>

<c:url var="bsDatepickerCss" value="/webjars/bootstrap-datepicker/1.6.1/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet" type="text/css" href="${bsDatepickerCss}"/>

<c:set var="localeCode" value="${pageContext.response.locale}"/>
<c:choose>
    <c:when test="${localeCode eq 'ru_RU'}">
        <c:set var="currentLocale" value="ru"/>
    </c:when>
    <c:otherwise>
        <c:set var="currentLocale" value="en"/>
    </c:otherwise>
</c:choose>

<script>
    $(document).ready(function () {
        var date_input = $('input[name="date"]'); //our date input has the name "date"
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
        var date_input_year = $('.dateYear'); //our date input has the name "date"
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