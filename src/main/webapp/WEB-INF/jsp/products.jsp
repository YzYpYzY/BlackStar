<%@ include file="./include/importTags.jsp" %>
<div class="products">
    <c:forEach items="${products}" var="product">
        <spring:url value='/products/details/${product.getId()}' var="url" htmlEscape="true"/>
    <a class="product" href="${url}">
        <img src="<spring:url value="/images/coffees/${product.getPhoto()}.jpg"/>" alt="" class="product-picture"/>
        <div class="product-name"><div>${product.getLabel()}</div><div class="product-action product-add" data="<spring:url value='/products/add/${product.getId()}'/>"><i class="fa fa-plus-circle" aria-hidden="true"></i></div></div>
        <div class="product-price"><div>${product.getPrice()}</div><i class="fa fa-euro" aria-hidden="true"></i></div>
    </a>
    </c:forEach>
    <script>
        window.onload = function() {
            $(".product-add").on("click",function(){
                var url = $(this).data;
                window.location = data;
            });
        };
    </script>
</div>