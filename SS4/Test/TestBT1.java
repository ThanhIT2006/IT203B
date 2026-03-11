import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestBT1 {

    @Test
    void TC01_validUsername() {
        BT1 validator = new BT1();
        String username = "user123";
        boolean result = validator.isValidUsername(username);
        assertTrue(result);
    }

    @Test
    void TC02_usernameTooShort() {
        BT1 validator = new BT1();
        String username = "abc";
        boolean result = validator.isValidUsername(username);
        assertFalse(result);
    }

    @Test
    void TC03_usernameContainsSpace() {
        BT1 validator = new BT1();
        String username = "user name";
        boolean result = validator.isValidUsername(username);
        assertFalse(result);
    }
}