<%@ include file="./include/importTags.jsp" %>
<div class="register-page">
<form:form id="register" name="register" class="register-form" method="POST"
           action="/BlackStar/user/register"
           modelAttribute="registerModel">
    <div class="form-group">
        <form:label path="firstName" for="firstName"><spring:message code="register-firstname"/> *</form:label>
        <form:input path="firstName" type="text"  name="firstName" id="firstName"/>
    </div>
    <form:errors path="firstName" />
    <div class="form-group">
        <form:label path="lastName" for="lastName"><spring:message code="register-lastname"/> *</form:label>
        <form:input path="lastName" type="text"  name="lastName" id="lastName"/>
    </div>
    <form:errors path="lastName" />
    <div class="form-group">
    <form:label path="email" for="email"><spring:message code="register-email"/> *</form:label>
    <form:input path="email" type="text"  name="email" id="email"/>
    </div>
        <form:errors path="email" />
    <div class="form-group">
        <form:label path="deliveredAddress" for="deliveredAddress"><spring:message code="register-deliveredaddress"/> *</form:label>
        <form:input path="deliveredAddress" type="text"  name="deliveredAddress" id="deliveredAddress"/>
    </div>
        <form:errors path="deliveredAddress" />
    <div class="form-group">
        <form:label path="phone" for="phone"><spring:message code="register-phone"/> *</form:label>
        <form:input path="phone" type="text"  name="phone" id="phone"/>
    </div>
        <form:errors path="phone" />
    <div class="form-group">
        <form:label path="vatNum" for="vatNum"><spring:message code="register-vat"/></form:label>
        <form:input path="vatNum" type="text"  name="vatNum" id="vatNum"/>
    </div>
        <form:errors path="vatNum" />
    <div class="form-group">
        <form:label path="society" for="society"><spring:message code="register-society"/></form:label>
        <form:input path="society" type="text"  name="society" id="society"/>
    </div>
        <form:errors path="society" />
    <div class="form-group">
        <form:label path="password" for="password"><spring:message code="register-password"/> *</form:label>
        <form:input path="password" type="password"  name="password" id="password"/>
    </div>
        <form:errors path="password" />
    <div class="form-group">
        <form:label path="passwordConfirm" for="passwordConfirm"><spring:message code="register-passwordconfirm"/> *</form:label>
        <form:input path="passwordConfirm" type="password"  name="passwordConfirm" id="passwordConfirm"/>
    </div>
        <form:errors path="passwordConfirm" />
    <div class="form-group">
        <spring:message code="register-requiredfiels"/> *
    </div>
    <form:button class="form-btn"><spring:message code="register"/></form:button>
    <c:if test="${ generalErrors != null}">
        <div class="general-errors">${generalErrors}</div>
    </c:if>
</form:form>
</div>