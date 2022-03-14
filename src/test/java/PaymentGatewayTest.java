import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class PaymentGatewayTest {

    private PaymentGateway paymentGateway;
    private Card card;

    @BeforeEach
    public void setup() {
        card = mock(Card.class);
        paymentGateway = new PaymentGateway();
    }

    @Test
    public void shouldInvokeCardChargeMethodWhenPaymentGatewayChargeCalled() throws CannotChargeException {
        paymentGateway.charge(card, 100.00);

        verify(card, times(1)).charge(eq(100.00));
    }

    @Test
    public void shouldThrowExceptionWhenCardThrowException() throws CannotChargeException {
        doThrow(new CannotChargeException("low charge")).when(card).charge(eq(100.00));

        assertThrows(CannotChargeException.class, () -> {
            paymentGateway.charge(card, 100.00);
        });
    }


    @Test
    public void shouldReturnBySpyCardAvailableLimitAfterPaymentGatewayCharge() throws CannotChargeException {
        Card amexCard = new Card("Amex", 1000);
        Card spyCard = Mockito.spy(amexCard);

        paymentGateway.charge(spyCard,100);

        assertEquals(900, spyCard.getAvailableLimit());
    }
}