import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestBT4 {

    BT4 service = new BT4();

    @Test
    void testStrongPassword() {
        String result = service.evaluatePasswordStrength("Abc123!@");
        assertEquals("Mạnh", result);
    }

    @Test
    void testMediumPasswords() {
        assertAll(
                () -> assertEquals("Trung bình",
                        service.evaluatePasswordStrength("abc123!@")),
                () -> assertEquals("Trung bình",
                        service.evaluatePasswordStrength("ABC123!@")),
                () -> assertEquals("Trung bình",
                        service.evaluatePasswordStrength("Abcdef!@")),
                () -> assertEquals("Trung bình",
                        service.evaluatePasswordStrength("Abc12345"))
        );
    }

    @Test
    void testWeakPasswords() {
        assertAll(
                () -> assertEquals("Yếu",
                        service.evaluatePasswordStrength("Ab1!")),
                () -> assertEquals("Yếu",
                        service.evaluatePasswordStrength("password")),
                () -> assertEquals("Yếu",
                        service.evaluatePasswordStrength("ABC12345"))
        );
    }
}