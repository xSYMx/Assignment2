package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WearableDeviceTest {
    private SmartWatch smartWatch;

    @BeforeEach
    void setUp() {
    Manufacturer manufacturer=new Manufacturer("Testmanufacturer",1);
    smartWatch=new SmartWatch("TestSmartWatch",100,manufacturer,"123","steel","Large","OLED");



    }

    @AfterEach
    void tearDown() {
        smartWatch=null;
    }

    @Test
    void setMaterial() {
        smartWatch.setMaterial("Titanium");
        assertEquals("Titanium",smartWatch.getMaterial());
    }

    @Test
    void setSize() {
        smartWatch.setSize("Medium");
        assertEquals("Medium",smartWatch.getSize());
    }

    @Test
    void testToString() {
        String expected="Model: TestSmartWatch, Price: 100.0, Manufacturer: TestManufacturer (ID: 1), ID: 123, Material: Steel, Size: Large, Display: OLED;";
        assertEquals(expected,smartWatch.toString());
    }

    @Test
    void getInsurancePremium() {
        double expectedPremium=100*.01;
       assertEquals(expectedPremium,smartWatch.getInsurancePremium());
}
}