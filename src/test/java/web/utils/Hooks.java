package utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setup() {
        System.out.println("Starting test execution...");
    }

    @After
    public void teardown() {
        System.out.println("Test execution completed.");
    }
}
