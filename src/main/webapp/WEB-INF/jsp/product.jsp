<%@ include file="./include/importTags.jsp" %>
    <div class="product-details">
        <div class="product-content">
        <div class="product-name"><div>${product.getLabel()}</div><div class="product-price"><div>${product.getPrice()}</div><i class="fa fa-euro" aria-hidden="true"></i></div></div>
        <img src="<spring:url value="/images/coffees/${product.getPhoto()}.jpg"/>" alt="" class="product-picture">
        <div class="l-basket-product-nb">
            <a class="product-action" href="<spring:url value='/products/removeOne/${product.getId()}'/>"><i class="fa fa-minus" aria-hidden="true"></i></a>
            ${quantity}
            <a class="product-action" href="<spring:url value='/products/add/${product.getId()}'/>"><i class="fa fa-plus" aria-hidden="true"></i></a>
        </div>
        </div>
        <div class="product-description">
            <div class="product-description-content">${product.getDescription()}</div>
                <a href="<spring:url value='/products'/>"class="form-btn"><spring:message code="back"/></a>
        </div>
    </div>