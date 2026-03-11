import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestBT2 {

    @Test
    void testAge18_isValid() {
        BT2 service = new BT2();
        boolean result = service.checkRegistrationAge(18);
        assertEquals(true, result);
    }

    @Test
    void testAge17_isInvalid() {
        BT2 service = new BT2();
        boolean result = service.checkRegistrationAge(17);
        assertEquals(false, result);
    }

    @Test
    void testNegativeAge_throwException() {
        BT2 service = new BT2();
        assertThrows(IllegalArgumentException.class, () -> {
            service.checkRegistrationAge(-1);
        });
    }
}
