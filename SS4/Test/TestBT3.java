import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestBT3 {

    private BT3 processor;

    @BeforeEach
    void setUp() {
        processor = new BT3();
    }

    @Test
    void testEmail1() {
        String email = "user@gmail.com";
        String result = processor.processEmail(email);
        assertEquals("user@gmail.com", result);
    }

    @Test
    void testEmail2() {
        String email = "usergmail.com";
        assertThrows(IllegalArgumentException.class, () -> {
            processor.processEmail(email);
        });
    }

    @Test
    void testEmail3() {
        String email = "user@";
        assertThrows(IllegalArgumentException.class, () -> {
            processor.processEmail(email);
        });
    }

    @Test
    void testEmail4() {
        String email = "Example@Gmail.com";
        String result = processor.processEmail(email);
        assertEquals("example@gmail.com", result);
    }
}
