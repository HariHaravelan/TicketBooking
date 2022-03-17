import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    private BookingService bookingService;
    private PaymentGateway paymentGateway;
    private TicketCenter ticketCenter;

    @BeforeEach
    public void setup() {
        paymentGateway = mock(PaymentGateway.class);
        ticketCenter = mock(TicketCenter.class);
        bookingService = new BookingService(paymentGateway, ticketCenter);
    }

    @Test
    public void shouldReturnBookingSuccessWhenTicketCenterAllowsBooking() throws CannotChargeException {
        String johnDoe = "John Doe";
        Card amex = new Card("AMEX", 1000);
        Ticket ticket = new Ticket(johnDoe);
        Record successRecord = new Record(BookingStatus.SUCCESS, Optional.of(ticket));

        when(ticketCenter.canBook()).thenReturn(true);
        doNothing().when(paymentGateway).charge(eq(amex), eq(675.00));
        when(ticketCenter.book(eq(johnDoe))).thenReturn(ticket);

        Record record = bookingService.bookTicket(johnDoe, amex, 675.00);

        assertEquals(successRecord, record);
    }

    @Test
    public void shouldReturnPaymentErrorWhenPaymentGatewayThrowsException() throws CannotChargeException {
        //Arrange or Given
        String johnDoe = "John Doe";
        Card visaCard = new Card("VISA", 1000);
        Record paymentErrorRecord = new Record(BookingStatus.PAYMENT_ERROR, Optional.empty());

        //Act or When
        when(ticketCenter.canBook()).thenReturn(true);
        doThrow(new CannotChargeException("low balance")).when(paymentGateway).charge(eq(visaCard), eq(500.00));

        Record actualRecord = bookingService.bookTicket(johnDoe, visaCard, 500.00);

        //Assert or Then
        assertEquals(paymentErrorRecord, actualRecord);
    }

    @Test
    public void shouldReturnSoldOutWhenTicketCenterCannotBook() throws CannotChargeException {
        String johnDoe = "John Doe";
        Card visaCard = new Card("VISA", 1000);
        Record soldOutRecord = new Record(BookingStatus.SOLD_OUT, Optional.empty());

        when(ticketCenter.canBook()).thenReturn(false);

        Record record = bookingService.bookTicket(johnDoe, visaCard, 500.00);

        assertEquals(soldOutRecord, record);
    }

}