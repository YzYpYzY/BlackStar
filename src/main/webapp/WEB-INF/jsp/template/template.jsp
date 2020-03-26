<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="../include/importTags.jsp" %>
<!DOCTYPE HTML>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300|Pacifico" rel="stylesheet">
    <link type="text/css" href="<spring:url value='/css/stylesheets/style.css'/>" rel="Stylesheet">
    <link rel="icon" href="<spring:url value="/images/favicon.ico"/>" />
    <title>${title}</title>
</head>
<body>
<div class="l-leftbar">
    <div class="l-brand">BlackStar<img src='<spring:url value="/images/coffee-logo.svg"/>' alt="logo" class="logo"></div>
        <sec:authorize access="!isAuthenticated()">
        <a class="l-signIn" href="<spring:url value='/user/login'/>"><i class="fa fa-user-o" aria-hidden="true"></i><spring:message code="login"/>
        </a>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <div class="l-user">
                <div class="l-user-welcome"><spring:message code="user-welcome"/> ${pageContext.request.userPrincipal.principal.firstname}</div>
                <a href="<spring:url value='/logout'/>" class="l-user-deco"><spring:message code="user-deco"/></a>

            </div>
        </sec:authorize>
    <div class="basket" id="basket-btn">
        <i class="fa fa-coffee" aria-hidden="true"><i class="nbProducts">${session.getBasket().count()}</i></i>
    </div>
    <div class="l-navigation">
        <a href="<spring:url value='/home'/>" ${session.getCurrentPage() == 'home' ? 'class="nav-link active" ' : 'class="nav-link"'}><spring:message code="menu-home"/></a>
        <a href="<spring:url value='/products'/>" ${session.getCurrentPage() == 'products' ? 'class="nav-link active" ' : 'class="nav-link"'}><spring:message code="menu-products"/></a>
    </div>
    <spring:url var="localeFr" value="">
        <spring:param name="locale" value="fr"/>
    </spring:url>
    <spring:url var="localeEn" value="">
        <spring:param name="locale" value="en"/>
    </spring:url>
    <c:set var="isFrLocale" value="${pageContext.response.locale == 'fr'}" />
    <div class="l-language-switch">
        <a href="${localeFr}" class="language-select <c:if test="${isFrLocale}">active</c:if>">Fr</a>
        |
        <a href="${localeEn}" class="language-select <c:if test="${!isFrLocale}">active</c:if>">En</a>
    </div>
</div>
<div class="l-basket" id="basket">
    <div class="l-basket-title"><spring:message code="basket"/></div>
    <div class="l-basket-products">
        <c:forEach items="${session.getBasket().getLines()}" var="basketLine">
        <div class="l-basket-product">
            <div class="l-basket-product-headLine">
                <div class="l-basket-product-name">${basketLine.value.getLabel()}</div>
                <div class="l-basket-product-nb">
                    <a class="product-action" href="<spring:url value='/products/removeOne/${basketLine.value.getProductId()}'/>"><i class="fa fa-minus" aria-hidden="true"></i></a>
                        ${basketLine.value.getQuantity()}
                    <a class="product-action" href="<spring:url value='/products/add/${basketLine.value.getProductId()}'/>"><i class="fa fa-plus" aria-hidden="true"></i></a>
                </div>
            </div>
            <div class="l-basket-product-secondLine">
                <a class="product-action" href="<spring:url value='/products/remove/${basketLine.value.getProductId()}'/>"><i class="fa fa-trash" aria-hidden="true"></i></a>
                <div class="l-basket-product-totalPrice">${basketLine.value.getPrice()}<i class="fa fa-eur" aria-hidden="true"></i></div>
            </div>
        </div>
        </c:forEach>
    </div>
    <c:if test="${ session.getBasket().getPromotionAmount() != 0}">
    <div class="promotion-details">
        <div>${String.format( "%.2f",session.getBasket().getTotalAmount())}</div>
        <div> <spring:message code="promotion"/> : ${String.format( "%.2f",session.getBasket().getPromotionAmount())}</div>
    </div>
</c:if>
    <div class="l-basket-totalPrice">${String.format( "%.2f",session.getBasket().getRealPrice())}
        <i class="fa fa-eur" aria-hidden="true"></i></div>
    <a class="l-basket-validate-btn" href="<spring:url value='/products/valid'/>"><spring:message code="basket-validate"/></a>
</div>
    <div class="l-content">
        <tiles:insertAttribute name = "main-content" />
    </div>
<script src="https://use.fontawesome.com/faa159ef04.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
    window.onload = function() {
        $("#basket-btn").on("click", function(){
            $("#basket").toggleClass("collapse");
        });
    };

</script>
</body>

</html>