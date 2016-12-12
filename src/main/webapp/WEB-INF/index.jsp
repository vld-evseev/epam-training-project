<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.root" var="root"/>
<fmt:setBundle basename="i18n.profile" var="profile"/>
<fmt:message var="title" bundle="${root}" key="root.title"/>

<!DOCTYPE html>
<tags:main title="${title}">

    <div class="container">
        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <div class="panel-title">
                        <fmt:message bundle="${profile}" key="profile.sign.in"/>
                    </div>
                </div>

                <div style="padding-top:30px" class="panel-body">

                    <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>

                    <form id="loginform" class="form-horizontal" role="form">

                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa-fw"
                                                               aria-hidden="true"></i></span>
                            <fmt:message var="username" bundle="${profile}" key="profile.user.name"/>
                            <input id="login-username" type="text" class="form-control" name="username" value=""
                                   placeholder="${username}">
                        </div>

                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock fa-fw"
                                                               aria-hidden="true"></i></span>
                            <fmt:message var="password" bundle="${profile}" key="profile.password"/>
                            <input id="login-password" type="password" class="form-control" name="password"
                                   placeholder="${password}">
                        </div>


                        <div style="margin-top:10px" class="form-group">
                            <!-- Button -->

                            <div class="col-sm-12 controls">
                                <a id="btn-login" href="#" class="btn btn-success">
                                    <fmt:message bundle="${profile}" key="profile.log.in"/>
                                </a>
                                    <%--<a id="btn-fblogin" href="#" class="btn btn-primary">
                                        Login with Facebook
                                    </a>--%>
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="col-md-12 control">
                                <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%">
                                    <fmt:message bundle="${profile}" key="profile.message.firsttime"/>
                                    <a href="#" onClick="$('#loginbox').hide(); $('#signupbox').show()">
                                        <fmt:message bundle="${profile}" key="profile.sign.up.here"/>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </form>


                </div>
            </div>
        </div>

        <div id="signupbox" style="display:none; margin-top:50px"
             class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <div class="panel-title"><fmt:message bundle="${profile}" key="profile.sign.up"/></div>
                    <div style="float:right; font-size: 85%; position: relative; top:-10px"><a id="signinlink" href="#"
                                                                                               onclick="$('#signupbox').hide(); $('#loginbox').show()">
                        <fmt:message bundle="${profile}" key="profile.sign.in"/>
                    </a></div>
                </div>
                <div class="panel-body">
                    <form id="signupform" <%--class="form-horizontal"--%> role="form">

                        <div id="signupalert" style="display:none" class="alert alert-danger">
                            <p>Error:</p>
                            <span></span>
                        </div>

                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-envelope fa-fw" aria-hidden="true"></i></span>
                                <fmt:message var="email_address" bundle="${profile}" key="profile.email.address"/>
                                <input type="text" class="form-control" name="email" placeholder="${email_address}">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-users fa-fw"
                                                                   aria-hidden="true"></i></span>
                                <fmt:message var="user_name" bundle="${profile}" key="profile.user.name"/>
                                <input type="text" class="form-control" name="firstname" placeholder="${username}">
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-sm-6">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user fa-fw" aria-hidden="true"></i></span>
                                    <fmt:message var="first_name" bundle="${profile}" key="profile.first.name"/>
                                    <input type="text" class="form-control" name="firstname"
                                           placeholder="${first_name}">
                                </div>
                            </div>

                            <div class="form-group col-sm-6">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="fa fa-user fa-fw" aria-hidden="true"></i></span>
                                    <fmt:message var="last_name" bundle="${profile}" key="profile.last.name"/>
                                    <input type="text" class="form-control" name="lastname" placeholder="${last_name}">
                                </div>
                            </div>
                        </div>

                        <div class="form-group"> <!-- Date input -->
                            <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-id-card-o fa-fw"
                                                                   aria-hidden="true"></i></span>
                                <fmt:message var="birth_date" bundle="${profile}" key="profile.birth.date"/>
                                <input class="form-control" id="date" name="date" placeholder="${birth_date}"
                                       type="text"/>
                            </div>
                        </div>


                        <div class="row">
                            <div class="form-group col-sm-6">
                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-lock fa-fw"
                                                                                       aria-hidden="true"></i></span>
                                    <fmt:message var="password" bundle="${profile}"
                                                 key="profile.password"/>
                                    <input type="password" class="form-control" name="password"
                                           placeholder="${password}">
                                </div>
                            </div>

                            <div class="form-group col-sm-6 pull-right">
                                <div class="input-group">
                                                    <span class="input-group-addon"><i class="fa fa-lock fa-fw"
                                                                                       aria-hidden="true"></i></span>
                                    <fmt:message var="confirm_password" bundle="${profile}"
                                                 key="profile.confirm.password"/>
                                    <input type="password" class="form-control" name="password"
                                           placeholder="${confirm_password}">
                                </div>
                            </div>
                        </div>


                        <div class="form-group">
                            <!-- Button -->
                            <button id="btn-signup" type="button" class="btn btn-info"><i
                                    class="icon-hand-right"></i>
                                &nbsp <fmt:message bundle="${profile}" key="profile.sign.up"/>
                            </button>
                                <%--<span style="margin-left:8px;">or</span>--%>
                        </div>

                    </form>
                </div>
            </div>

        </div>
    </div>

</tags:main>
