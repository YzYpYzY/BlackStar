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
    <c:if test="${ param.error != null}">
        <div class="general-errors"><spring:message code="login.error"/></div>
    </c:if>
    <form:button class="form-btn"><spring:message code="login"/></form:button>
</form:form>

<a href="<spring:url value='/user/register'/>" class="goToRegister">Register</a>
</div>