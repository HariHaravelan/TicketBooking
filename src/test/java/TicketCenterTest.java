import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TicketCenterTest {

    @Test
    public void shouldReturnFalseWhenTicketLimitReached() {
        String johnDoe = "John Doe";

        TicketCenter ticketCenter = new TicketCenter(1);
        ticketCenter.book(johnDoe);

        assertFalse(ticketCenter.canBook());
    }

    @Test
    public void shouldReturnTrueWhenTicketLimitNotReached() {
        String johnDoe = "John Doe";
        String joeDoe = "Joe Doe";

        TicketCenter ticketCenter = new TicketCenter(3);
        ticketCenter.book(johnDoe);
        ticketCenter.book(joeDoe);

        assertTrue(ticketCenter.canBook());
    }

}