package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.UserService;

@DisplayName("UserService Tests")
@Execution(ExecutionMode.CONCURRENT)
class UserServiceSimpleTest {

    UserService service;

    @BeforeAll
    static void beforeAll() {
        System.out.println("🔧 Starting UserService Tests");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("✅ Finished UserService Tests");
    }

    @BeforeEach
    void setUp() {
        service = new UserService();
        System.out.println("⚙️ Test setup complete");
    }

    @AfterEach
    void tearDown() {
        System.out.println("🧹 Test cleanup done");
    }

    @Test
    @DisplayName("✅ Valid Email Test")
    void testValidEmail() {
        assertTrue(service.isValidEmail("test@example.com"));
    }

    @Test
    @DisplayName("❌ Invalid Email Test - missing @")
    void testInvalidEmailMissingAt() {
        assertFalse(service.isValidEmail("testexample.com"));
    }

    @ParameterizedTest
    @DisplayName("📧 Parameterized Valid Email Test")
    @ValueSource(strings = {"user@mail.com", "a@b.com", "x@y.org"})
    void parameterizedValidEmails(String email) {
        assertTrue(service.isValidEmail(email));
    }

    @Test
    @DisplayName("🔐 Valid Authentication")
    void testValidAuthentication() {
        assertAll(
            () -> assertTrue(service.authenticate("admin", "1234")),
            () -> assertFalse(service.authenticate("admin", "wrong")),
            () -> assertFalse(service.authenticate("user", "1234"))
        );
    }

    @Test
    @DisplayName("⏱️ Timeout Test")
    @Timeout(1)
    void testWithTimeout() {
        assertTrue(service.isValidEmail("test@test.com"));
    }

    @Test
    @Disabled("❌ This test fails due to incorrect expected output. To fix: change expected to false.")
    @DisplayName("🚫 Disabled Failing Test Example")
    void intentionallyFailingTest() {
    	assertFalse(service.isValidEmail("invalid-email")); // should be false
    }
}
