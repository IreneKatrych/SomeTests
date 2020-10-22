// filename: tests/TestDynamicLoading.java

package tests;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import org.junit.experimental.categories.Category;
import pageobjects.DynamicLoading;
import tests.groups.Deep;

@Category(Deep.class)
public class TestDynamicLoading extends Base {

    private DynamicLoading dynamicLoading;

    // Loading page
    @Before
    public void setUp() {
        dynamicLoading = new DynamicLoading(driver);
    }

    // Check if invisible element will appear on page
    @Test
    public void dynamicElementLoads() {
        dynamicLoading.loadExample("1");
        assertTrue("finish text didn't display after loading", dynamicLoading.finishTextPresent());
    }

    // Check if new element will appear on page
    @Test
    public void elementAppears() {
        dynamicLoading.loadExample("2");
        assertTrue("finish text didn't display after loading", dynamicLoading.finishTextPresent());
    }
}