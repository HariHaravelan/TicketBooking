import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    public void shouldReturnAvailableLimitAfterDeductionOfCumulativeCharges() throws CannotChargeException {
        Card card = new Card("Amex", 1000);

        card.charge(100);
        card.charge(100);

        assertEquals(800, card.getAvailableLimit());
    }

    @Test
    public void shouldThrowExceptionWhenCardChargeExceedsLimit() {
        Card card = new Card("Amex", 1000);

        assertThrows(CannotChargeException.class, () -> {
            card.charge(1100);
        }, "low balance");
    }

}