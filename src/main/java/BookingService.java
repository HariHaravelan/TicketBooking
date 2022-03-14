import java.util.Optional;

public class BookingService {

    private PaymentGateway paymentGateway;
    private TicketCenter ticketCenter;

    public BookingService(PaymentGateway paymentGateway, TicketCenter ticketCenter) {
        this.paymentGateway = paymentGateway;
        this.ticketCenter = ticketCenter;
    }

    public BookingResponse bookTicket(String user, Card card, double amount) {
        if (ticketCenter.canBook()) {
            try {
                paymentGateway.charge(card, amount);
            } catch (CannotChargeException e) {
                return new BookingResponse(BookingStatus.PAYMENT_ERROR, Optional.empty());
            }
            return new BookingResponse(BookingStatus.SUCCESS, Optional.of(ticketCenter.book(user)));
        }
        return new BookingResponse(BookingStatus.SOLD_OUT, Optional.empty());
    }
}
