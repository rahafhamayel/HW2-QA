package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.najah.code.Calculator;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Calculator Tests")
public class CalculatorTest {
	
    Calculator calc;

    @BeforeAll
    static void beforeAll() {
        System.out.println("📦 Starting Calculator Test Suite...");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("✅ Calculator Test Suite Complete.");
    }

    @BeforeEach
    void setUp() {
        calc = new Calculator();
        System.out.println("🔧 Setup complete.");
    }

    @AfterEach
    void tearDown() {
        System.out.println("🧹 Test finished.");
    }

    @Test
    @Order(1)
    @DisplayName("Test adding multiple numbers")
    void testAdd() {
        int result = calc.add(1, 2, 3);
        assertEquals(6, result);
    }

    @Test
    @Order(2)
    @DisplayName("Test divide valid case")
    void testDivideValid() {
        int result = calc.divide(10, 2);
        assertEquals(5, result);
    }

    @Test
    @Order(3)
    @DisplayName("Test divide by zero throws exception")
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calc.divide(10, 0));
    }

    @ParameterizedTest
    @CsvSource({
        "0, 1",
        "1, 1",
        "3, 6",
        "4, 24"
    })
    @Order(4)
    @DisplayName("Test factorial with parameterized inputs")
    void testFactorial(int input, int expected) {
        assertEquals(expected, calc.factorial(input));
    }

    @Test
    @Order(5)
    @DisplayName("Timeout test for addition")
    @Timeout(1)
    void testAddTimeout() {
        assertEquals(10, calc.add(4, 3, 2, 1));
    }

    @Test
    @Order(6)
    @Disabled("This test fails due to invalid negative input for factorial.")
    @DisplayName("Disabled failing test for factorial(-1)")
    void testFactorialNegative() {
        calc.factorial(-1); 
    }

    @Test
    @Order(7)
    @DisplayName("Multiple assertions example")
    void testMultipleAssertions() {
        int sum = calc.add(2, 2);
        int factorial = calc.factorial(3);
        assertAll("Check multiple methods",
            () -> assertEquals(4, sum),
            () -> assertEquals(6, factorial)
        );
    }
}
