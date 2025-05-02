package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartWatchTest {
    private  SmartWatch smartWatch;
    private Manufacturer manufacturer;

    @BeforeEach
    void setUp() {
        Manufacturer manufacturer=new Manufacturer("TestManufacturer",1);
        smartWatch=new SmartWatch("TestSmartWatch",100,manufacturer,"123","Steel","Large","OLED");

    }

    @AfterEach
    void tearDown() {
        smartWatch=null;
        manufacturer=null;
    }
    @Test
    void testInitialization() {
        assertEquals("TestSmartWatch", smartWatch.getModelName());
        assertEquals(100, smartWatch.getPrice());
        assertSame(manufacturer, smartWatch.getManufacturer());
        assertEquals("ID789", smartWatch.getId());
        assertEquals("Aluminum", smartWatch.getMaterial());
        assertEquals("Large", smartWatch.getSize());
        assertEquals("AMOLED", smartWatch.getDisplayType());
    }


    @Test
    void testsetDisplayType() {
        smartWatch.setDisplayType("OLED");
        assertEquals("OLED",smartWatch.getDisplayType());
        smartWatch.setDisplayType("LCD");
        assertEquals("LCD",smartWatch.getDisplayType());
        smartWatch.setDisplayType(null);
        assertNull(smartWatch.getDisplayType());
    }
    @Test
    void testGetInsurancePremium() {
        double expectedPremium = 200 * 0.06; // 12.0
        assertEquals(expectedPremium, smartWatch.getInsurancePremium(), 0.001);


        SmartWatch cheapWatch = new SmartWatch("Cheap", 50, manufacturer, "ID111", "Plastic", "Small", "TFT");
        assertEquals(3.0, cheapWatch.getInsurancePremium(), 0.001);
    }
    @Test
    void testConnectToInternet() {
        String expected = "Connects to the internet via Bluetooth";
        assertEquals(expected, smartWatch.connectToInternet());
    }

    @Test
    void testToString() {
        smartWatch.setDisplayType("OLED");
        String expectedOutput=String.format("Model:TestSmartWatch,Price:100.0,Manufacturer: TestManufacturer, numEmployees: 1,ID:123, Material: Steel, Size: Large, Display Type: OLED");
        assertEquals(expectedOutput,smartWatch.toString());
        smartWatch.setDisplayType(null);
        expectedOutput = String.format("Model:TestSmartWatch,Price:100.0,Manufacturer: TestManufacturer, numEmployees: 1,ID:123, Material: Steel, Size: Large, Display Type: null");
        assertEquals(expectedOutput, smartWatch.toString());
    }
}