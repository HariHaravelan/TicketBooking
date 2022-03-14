import java.util.Optional;

public class BookingResponse {
    private BookingStatus bookingStatus;
    private Optional<Ticket> ticket;

    public BookingResponse(BookingStatus bookingStatus, Optional<Ticket> ticket) {
        this.bookingStatus = bookingStatus;
        this.ticket = ticket;
    }

    @Override
    public boolean equals(Object obj) {
        BookingResponse otherBookingResponse = (BookingResponse) obj;
        return otherBookingResponse.bookingStatus.equals(this.bookingStatus) && otherBookingResponse.ticket.equals(this.ticket);
    }
}
