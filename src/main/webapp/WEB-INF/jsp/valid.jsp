<%@ include file="./include/importTags.jsp" %>
<div class="valid">
    ${ session.getBasket().getTotalAmount()}
    <form action="/products/pay" method="POST">
        <script
                src="https://checkout.stripe.com/checkout.js" class="stripe-button"
                data-key="pk_test_RbN2ErBxpnq9l5ZksyzSWETL"
                data-amount="${ Math.round(session.getBasket().getRealPrice() * 100)}"
                data-name="Stripe.com"
                data-description="Commande BlackStar"
                data-image="https://stripe.com/img/documentation/checkout/marketplace.png"
                data-locale="${ locale }"
                data-zip-code="true"
                data-currency="eur"
        >
        </script>
    </form>
</div>

