<%@ include file="./include/importTags.jsp" %>
<div class="login-page">
<form:form id="login" name="login" class="login-form" method="POST"
        action="/user/login"
           modelAttribute="userEntity">
    <div class="form-group">
    <form:label path="username" for="username"><spring:message code="login-email"/></form:label>
    <form:input path="username" type="text"  name="username" id="username"/>
    </div>
        <form:errors path="username" />
    <div class="form-group">
        <form:label path="password" for="password"><spring:message code="login-password"/></form:label>
        <form:input path="password" type="password"  name="password" id="password"/>
    </div>
        <form:errors path="password" />
    <form:button class="form-btn"><spring:message code="login"/></form:button>
    <c:if test="${ generalErrors != null}">
        <div class="general-errors">${generalErrors}</div>
    </c:if>
</form:form>

<a href="<spring:url value='/user/register'/>" class="goToRegister">Register</a>
</div>