public class PaymentGateway {

    public void charge(Card card, double amount) throws CannotChargeException {
        card.charge(amount);
    }
}
