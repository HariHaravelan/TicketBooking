public class Card {
    private String provider;
    private double limit;

    public Card(String provider, double limit) {
        this.provider = provider;
        this.limit = limit;
    }

    public void charge(double amount) throws CannotChargeException {
        if (this.limit < amount) {
            throw new CannotChargeException("low balance");
        }
        this.limit -= amount;
    }

    public double getAvailableLimit() {
        return limit;
    }
}
