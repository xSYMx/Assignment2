package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartWatchTest {
    private  SmartWatch smartWatch;

    @BeforeEach
    void setUp() {
        Manufacturer manufacturer=new Manufacturer("TestManufacturer",1);
        smartWatch=new SmartWatch("TestSmartWatch",100,manufacturer,"123","steel","Large","OLED");

    }

    @AfterEach
    void tearDown() {
        smartWatch=null;
    }

    @Test
    void setDisplayType() {
        smartWatch.setDisplayType("OLED");
        assertEquals("OLED",smartWatch.getDisplayType());
        smartWatch.setDisplayType("LCD");
        assertEquals("LCD",smartWatch.getDisplayType());
        smartWatch.setDisplayType(null);
        assertNull(smartWatch.getDisplayType());
    }

    @Test
    void testToString() {
        smartWatch.setDisplayType("OLED");
        String expectedOutput=String.format("Model:TestSmartWatch,Price:100,Manufacturer: TestManufacturer, ID: 123, Material:  Steel, Size: Large, Display Type: OLED");
        assertEquals(expectedOutput,smartWatch.toString());
        smartWatch.setDisplayType(null);
        expectedOutput = String.format("Model: TestSmartWatch, Price: 100, Manufacturer: TestManufacturer, ID:123, Material:  Steel, Size: Large, Display Type: null");
        assertEquals(expectedOutput, smartWatch.toString());
    }
}