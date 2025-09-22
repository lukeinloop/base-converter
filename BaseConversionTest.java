import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BaseConversionTest {

    @Test
    public void testBinaryToDecimal() {
        String result = BaseConversion.convertBase("1010", 2, 10);
        assertEquals("10", result);
    }

    @Test
    public void testDecimalToHex() {
        String result = BaseConversion.convertBase("255", 10, 16);
        assertEquals("FF", result);
    }

    @Test
    public void testHexToBinary() {
        String result = BaseConversion.convertBase("A", 16, 2);
        assertEquals("1010", result);
    }

    @Test
    public void testInvalidValue() {
        assertFalse(BaseConversion.isValidInteger("GHI", 16));
    }

    @Test
    public void testValidValue() {
        assertTrue(BaseConversion.isValidInteger("1A3F", 16));
    }
}
