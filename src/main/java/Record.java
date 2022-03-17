import java.util.Optional;

public class Record {
    private BookingStatus bookingStatus;
    private Optional<Ticket> ticket;

    public Record(BookingStatus bookingStatus, Optional<Ticket> ticket) {
        this.bookingStatus = bookingStatus;
        this.ticket = ticket;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Record)) {
            return false;
        }
        Record otherRecord = (Record) obj;
        return otherRecord.bookingStatus.equals(this.bookingStatus) && otherRecord.ticket.equals(this.ticket);
    }
}
