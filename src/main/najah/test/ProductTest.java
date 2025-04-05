package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.najah.code.Product;

@DisplayName("Product Tests")
public class ProductTest {

    Product p;

    @BeforeAll
    static void initAll() {
        System.out.println("🚀 Starting Product tests...");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("🏁 Finished Product tests.");
    }

    @BeforeEach
    void setUp() {
        p = new Product("Laptop", 1000.0);
        System.out.println("🔧 Product setup done.");
    }

    @AfterEach
    void afterEach() {
        System.out.println("✅ Test completed.");
    }

    @Test
    @DisplayName("Test valid discount applied")
    void testApplyDiscountValid() {
        p.applyDiscount(20);
        assertEquals(800.0, p.getFinalPrice(), 0.01);
    }

    @Test
    @DisplayName("Test invalid discount throws exception")
    void testApplyInvalidDiscount() {
        assertThrows(IllegalArgumentException.class, () -> p.applyDiscount(70));
    }

    @Test
    @DisplayName("Test negative discount throws exception")
    void testNegativeDiscount() {
        assertThrows(IllegalArgumentException.class, () -> p.applyDiscount(-5));
    }

    @ParameterizedTest
    @CsvSource({
        "0, 1000.0",
        "10, 900.0",
        "25, 750.0",
        "50, 500.0"
    })
    @DisplayName("Parameterized test for discount percentages")
    void testDiscountParameterized(double discount, double expectedPrice) {
        p.applyDiscount(discount);
        assertEquals(expectedPrice, p.getFinalPrice(), 0.01);
    }

    @Test
    @DisplayName("Test product getters")
    void testGetters() {
        assertAll("Product fields",
            () -> assertEquals("Laptop", p.getName()),
            () -> assertEquals(1000.0, p.getPrice(), 0.01),
            () -> assertEquals(0.0, p.getDiscount(), 0.01)
        );
    }

    @Test
    @DisplayName("Test timeout for heavy operation (simulated)")
    @Timeout(1)
    void testTimeout() {
        p.applyDiscount(30);
        assertEquals(700.0, p.getFinalPrice(), 0.01);
    }

    @Test
    @Disabled("Test for fixing in future: product with negative price")
    @DisplayName("Disabled test for negative price")
    void testNegativePrice() {
        new Product("Phone", -200.0);
    }
}
