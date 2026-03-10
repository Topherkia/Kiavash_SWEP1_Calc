import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private Calculator calculator = new Calculator();
    private final double DELTA = 0.001;

    @BeforeEach
    public void clearCalculator() {
        calculator.reset();
    }

    @Test
    public void testAdd() {
        calculator.add(1);
        calculator.add(2);
        assertEquals(3, calculator.getResult(), DELTA, "The sum of numbers 1 and 2 is incorrect");
    }

    @Test
    public void testAddMultiple() {
        calculator.add(1.5);
        calculator.add(2.3);
        assertEquals(3.8, calculator.getResult(), DELTA, "The sum of floating point numbers is incorrect");
    }

    @Test
    public void testSubtract() {
        calculator.add(10);
        calculator.subtract(2);
        assertEquals(8, calculator.getResult(), DELTA, "The difference between 10 and 2 is incorrect");
    }

    @Test
    public void testSubtractNegative() {
        calculator.add(5);
        calculator.subtract(10);
        assertEquals(-5, calculator.getResult(), DELTA, "Subtraction resulting in negative is incorrect");
    }

    @Test
    @DisplayName("Test division 8 / 2")
    public void testDivide() {
        calculator.add(8);
        calculator.divide(2);
        assertEquals(4, calculator.getResult(), DELTA, "Division 8/2 is incorrect");
    }

    @Test
    public void testDivideFloatingPoint() {
        calculator.add(10);
        calculator.divide(3);
        assertEquals(10.0/3.0, calculator.getResult(), DELTA, "Floating point division is incorrect");
    }

    @Test
    @DisplayName("Test division by zero")
    public void testDivideByZero() {
        calculator.add(10);
        ArithmeticException exception =
                assertThrows(ArithmeticException.class, () -> calculator.divide(0));
        assertEquals("Cannot divide by zero", exception.getMessage());
    }

    @Test
    public void testMultiply() {
        calculator.add(5);
        calculator.multiply(4);
        assertEquals(20, calculator.getResult(), DELTA, "Multiplication 5*4 is incorrect");
    }

    @Test
    public void testMultiplyByZero() {
        calculator.add(5);
        calculator.multiply(0);
        assertEquals(0, calculator.getResult(), DELTA, "Multiplication by zero should result in zero");
    }

    @Test
    public void testMultiplyByNegative() {
        calculator.add(5);
        calculator.multiply(-2);
        assertEquals(-10, calculator.getResult(), DELTA, "Multiplication by negative is incorrect");
    }

    @Test
    public void testSquare() {
        calculator.square(5);
        assertEquals(25, calculator.getResult(), DELTA, "Square of 5 is incorrect");
    }

    @Test
    public void testSquareZero() {
        calculator.square(0);
        assertEquals(0, calculator.getResult(), DELTA, "Square of 0 is incorrect");
    }

    @Test
    public void testSquareNegative() {
        calculator.square(-4);
        assertEquals(16, calculator.getResult(), DELTA, "Square of -4 should be 16");
    }

    @Test
    public void testChainedOperations() {
        calculator.add(10);
        calculator.subtract(3);
        calculator.multiply(2);
        calculator.divide(2);
        assertEquals(7, calculator.getResult(), DELTA, "Chained operations result is incorrect");
    }

    @Test
    public void testReset() {
        calculator.add(100);
        calculator.reset();
        assertEquals(0, calculator.getResult(), DELTA, "Reset should set result to zero");
    }
}
