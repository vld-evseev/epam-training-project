<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.root" var="root"/>
<fmt:setBundle basename="i18n.profile" var="profile"/>
<fmt:setBundle basename="i18n.user" var="userPage"/>
<fmt:message var="title" bundle="${profile}" key="profile.community"/>
<jsp:useBean id="user" type="com.epam.training.lawAndSocial.model.User" scope="session"/>
<jsp:useBean id="opponent" type="com.epam.training.lawAndSocial.model.User" scope="request"/>
<jsp:useBean id="messageHistory" type="java.util.List<com.epam.training.lawAndSocial.model.Message>" scope="request"/>
<jsp:useBean id="sessionId" type="java.lang.Long" scope="request"/>

<tags:main title="${title}">
    <tags:navigationBlock>

        <section class="chat-section">
            <ol class="chat-window">
                <c:forEach items="${messageHistory}" var="message" varStatus="count">
                    <li
                            <c:choose>
                                <c:when test="${message.fromUserId == user.id}">
                                    class="self"
                                    <c:set var="avatar" value="${user.avatar}"/>
                                    <c:set var="name" value="${user.firstName}"/>
                                </c:when>
                                <c:otherwise>
                                    class="other"
                                    <c:set var="avatar" value="${opponent.avatar}"/>
                                    <c:set var="name" value="${opponent.firstName}"/>
                                </c:otherwise>
                            </c:choose>
                    >
                        <div class="row message-row">
                            <div class="col-xs-1">
                                <img class="img-circle avatar-img" src="data:image/jpeg;base64,${avatar}"/>
                            </div>
                            <div>
                                <div>
                                        ${name}
                                </div>
                                <div class="message col-xs-8">
                                    <p>${message.text}</p>
                                    <time datetime="2009-11-13T20:00">${name} • ${message.date}</time>
                                </div>
                            </div>

                        </div>
                    </li>

                </c:forEach>
            </ol>
        </section>
        <div class="panel-body col-md-6">
            <div class="chat-input">
                <fmt:message var="typeMessagePlaceholder" bundle="${userPage}" key="user.type.message"/>
                        <textarea rows="5" id="messageBox" placeholder="${typeMessagePlaceholder}"
                                  style="width: 100%;"></textarea>
                <fmt:message var="sendMsgButton" bundle="${userPage}" key="user.send"/>
                <button class="btn btn-primary send-msg" style="width: 90px;">
                        ${sendMsgButton}
                </button>
            </div>
        </div>

    </tags:navigationBlock>
</tags:main>

<script>

    $(document).ready(function () {
        var connection = new WebSocket('ws://localhost:4445');

        var currentUserAvatar = "";
        var opponentAvatar = "";

        var sessionId = ${sessionId};

        <c:if test="${user.avatar != null}" >
        currentUserAvatar = "${user.avatar}";
        </c:if>
        <c:if test="${opponent.avatar != null}" >
        opponentAvatar = "${opponent.avatar}";
        </c:if>

        var currentUserId = ${user.id};
        var uniq = guidGenerator();

        connection.onopen = function () {
            console.log('Connected!');

            var enter = {
                sessionId: sessionId,
                fromUserId: ${user.id},
                type: 'enter'
            };
            connection.send(JSON.stringify(enter));
        };

        connection.onclose = function () {
            console.log('Closed!');
            var close = {
                sessionId: sessionId,
                fromUserId: ${user.id},
                type: 'close'
            };
            connection.send(JSON.stringify(close));
        };

        connection.onerror = function (error) {
            console.log('WebSocket Error ' + error);
            var close = {
                sessionId: sessionId,
                fromUserId: ${user.id},
                type: 'close'
            };
            connection.send(JSON.stringify(close));
        };

        connection.onmessage = function (e) {
            console.log('Server: ' + e.data);
            var fromId = JSON.parse(e.data)['fromUserId'];
            var toId = JSON.parse(e.data)['toUserId'];

            if (fromId + toId != ${opponent.id} + ${user.id}) {
                return;
            }

            var date = JSON.parse(e.data)['date'];
            var text = JSON.parse(e.data)['text'];
            var messageClass = 'self';
            var avatar = currentUserAvatar;
            var firstName = "${user.firstName}";
            if (fromId != currentUserId) {
                messageClass = 'other';
                avatar = opponentAvatar;
                firstName = "${opponent.firstName}";
            }

            $(".chat-window").append(
                    "<li class='" + messageClass + "'>" +
                    "<div class='row message-row'>" +
                    "<div class='col-xs-1'>" +
                    "<img class='img-circle avatar-img' src='data:image/jpeg;base64," + avatar + "'/>" +
                    "</div>" +
                    "<div class='message col-xs-8'>" +
                    "<p>" + text + "</p>" +
                    "<time datetime='2009-11-13T20:00'>" + firstName + " • " + date + "</time>" +
                    "</div>" +
                    "</div>" +
                    "</li>"
            );
            scrollDown();
        };

        function sendMessage() {
            var message = escapeHTML($('#messageBox').val(), true);
            if (!message.length) {
                return;
            }

            var jsonMsg = {
                sessionId: sessionId,
                fromUserId: ${user.id},
                toUserId:   ${opponent.id},
                text: message,
                type: 'message',
                date: Date.now()
            };

            console.log('JSON: ' + JSON.stringify(jsonMsg));
            connection.send(JSON.stringify(jsonMsg));
            $('#messageBox').val('');
        }

        $('.send-msg').click(sendMessage);

        var ESC_MAP = {
            '&': '&amp;',
            '<': '&lt;',
            '>': '&gt;',
            '"': '&quot;',
            "'": '&#39;'
        };

        function escapeHTML(s, forAttribute) {
            return s.replace(forAttribute ? /[&<>'"]/g : /[&<>]/g, function (c) {
                return ESC_MAP[c];
            });
        }

        scrollDown();
    });

    function scrollDown() {
        $('.chat-section').scrollTop($('.chat-section')[0].scrollHeight);
    }

    function guidGenerator() {
        var S4 = function () {
            return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
        };
        return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
    }


</script>