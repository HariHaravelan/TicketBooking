import java.util.Optional;

public class BookingService {

    private PaymentGateway paymentGateway;
    private TicketCenter ticketCenter;

    public BookingService(PaymentGateway paymentGateway, TicketCenter ticketCenter) {
        this.paymentGateway = paymentGateway;
        this.ticketCenter = ticketCenter;
    }

    public Record bookTicket(String user, Card card, double amount) {
        if (ticketCenter.canBook()) {
            try {
                paymentGateway.charge(card, amount);
            } catch (CannotChargeException e) {
                return new Record(BookingStatus.PAYMENT_ERROR, Optional.empty());
            }
            return new Record(BookingStatus.SUCCESS, Optional.of(ticketCenter.book(user)));
        }
        return new Record(BookingStatus.SOLD_OUT, Optional.empty());
    }
}
