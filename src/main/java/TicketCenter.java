import java.util.ArrayList;
import java.util.List;

public class TicketCenter {
    private List<Ticket> tickets;
    private int limit;

    public TicketCenter(int limit) {
        this.limit = limit;
        this.tickets = new ArrayList<>();
    }

    protected boolean canBook() {
        return this.tickets.size() < limit;
    }

    public Ticket book(String user) {
        Ticket ticket = new Ticket(user);
        tickets.add(ticket);
        return ticket;
    }

}
