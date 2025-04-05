package main.najah.test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("🎯 Complete Test Suite for All Classes")
@SelectClasses({
    CalculatorTest.class,
    ProductTest.class,
    UserServiceSimpleTest.class,
    RecipeBookTest.class
})
public class AllTests {
}
