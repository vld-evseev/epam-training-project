<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="value" rtexprvalue="true" required="true" %>

<div id="signupalert" class="alert alert-danger">
    <span class="help-block">
        ${value}
    </span>
</div>