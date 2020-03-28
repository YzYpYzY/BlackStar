<%@ include file="./include/importTags.jsp" %>
<div class="valid">
    <spring:message code="valid-total"/> ${ Math.round(session.getBasket().getRealPrice() * 100) } &euro;
    <form action="/products/pay" method="POST">
        <script
                src="https://checkout.stripe.com/checkout.js" class="stripe-button"
                data-key="pk_test_RbN2ErBxpnq9l5ZksyzSWETL"
                data-amount="${ Math.round(session.getBasket().getRealPrice() * 100)}"
                data-name="Stripe.com"
                data-description="BlackStar"
                data-image="<spring:url value='/images/favicon.ico'/>"
                data-label="<spring:message code="valid-pay"/>"
                data-locale="${ locale }"
                data-zip-code="true"
                data-currency="eur"
        >
        </script>
    </form>
</div>

