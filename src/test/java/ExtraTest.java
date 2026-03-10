import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ExtraTest extends AbstractParent {

    private static Calculator calculator = new Calculator();
    private final double DELTA = 0.001;

    @BeforeAll
    public static void testPowerOn() {
        System.out.println("@BeforeAll Power ON (before the first test)");
        calculator.powerOn();
    }

    @AfterAll
    public static void testPowerOff() {
        System.out.println("@AfterAll Power OFF (all tests executed).");
        calculator.powerOff();
        calculator = null;
    }

    @BeforeEach
    public void testReset() {
        System.out.println("  Reset calculator.");
        calculator.reset();
        assertEquals(0, calculator.getResult(), DELTA, "Reset failed");
    }

    @ParameterizedTest(name = "Square of {0} should be {1}")
    @CsvSource({ "2, 4", "4, 16", "5, 25", "7, 49", "10, 100" })
    public void testSquareParameterized(int input, int expected) {
        calculator.square(input);
        assertEquals(expected, calculator.getResult(), DELTA, "Squaring number " + input + " is incorrect");
    }

    @Test
    public void testSquareRoot2() {
        calculator.squareRoot(2);
        assertEquals(Math.sqrt(2), calculator.getResult(), DELTA, "Square root of 2 is incorrect");
    }

    @Test
    public void testSquareRoot4() {
        calculator.squareRoot(4);
        assertEquals(2.0, calculator.getResult(), DELTA, "Square root of 4 is incorrect");
    }

    @Test
    public void testSquareRootZero() {
        calculator.squareRoot(0);
        assertEquals(0.0, calculator.getResult(), DELTA, "Square root of 0 is incorrect");
    }

    @Test
    @DisplayName("Test negative square root - should throw exception")
    public void testSquareRootNegative() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, 
            () -> calculator.squareRoot(-5));
        assertEquals("Cannot calculate square root of negative number", exception.getMessage());
    }

    @ParameterizedTest(name = "Square root of {0} should be {1}")
    @CsvSource({ "4, 2.0", "9, 3.0", "16, 4.0", "25, 5.0", "2, 1.41421356" })
    public void testSquareRootParameterized(double input, double expected) {
        calculator.squareRoot(input);
        assertEquals(expected, calculator.getResult(), DELTA, "Square root of " + input + " is incorrect");
    }
}