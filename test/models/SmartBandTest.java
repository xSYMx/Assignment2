package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartBandTest {
    private  SmartBand smartBand;
    private  Manufacturer manufacturer;

    @BeforeEach
    void setUp() {
        manufacturer=new Manufacturer("TestManufacturer",1);
        smartBand=new SmartBand("TestSmartBand",100,manufacturer,"666","Silicone","Medium",true );
    }

    @AfterEach
    void tearDown() {
        smartBand=null;
        manufacturer=null;
    }

    @Test
    void setHeartRateMonitor() {
        assertEquals("TestSmartBand",smartBand.getModelName());
        assertEquals(100,smartBand.getPrice());
        assertEquals(manufacturer,smartBand.getManufacturer());
        assertEquals("666",smartBand.getId());
        assertEquals("Silicone",smartBand.getMaterial());
        assertEquals("Medium",smartBand.getSize());
        assertTrue(smartBand.isHeartRateMonitor());
        assertEquals("TestManufacturer",manufacturer.getManufacturerName());
        assertEquals(1,manufacturer.getNumEmployees());
    }
    @Test
    void testSetHeartRateMonitor() {
        smartBand.setHeartRateMonitor(true);
        assertTrue(smartBand.isHeartRateMonitor());

        smartBand.setHeartRateMonitor(false);
        assertFalse(smartBand.isHeartRateMonitor());
    }

    @Test
    void testToString()  {
        String expectedString = String.format("Model Name: TestSmartBand, Price: 100, Manufacturer: TestManufacturer, ID: 666, Material: Silicone, Size: Medium, Heart Rate Monitor: Yes");
     assertEquals(expectedString, smartBand.toString(), "toString output is incorrect");
     smartBand.setHeartRateMonitor(false);
    expectedString = String.format("Model Name: TestSmartBand, Price: 100, Manufacturer: TestManufacturer, ID: 666, Material: Silicone, Size: Medium, Heart Rate Monitor: No");
    assertEquals(expectedString, smartBand.toString(), "toString output should reflect heart rate monitor status change");
    }
    @Test
    void testGetInsurancePremium() {
        double expectedPremiumWithMonitor = 50 * 0.07;
        assertEquals(expectedPremiumWithMonitor, smartBand.getInsurancePremium(), 0.001);

        double expectedPremiumWithoutMonitor = 40 * 0.07;
        assertEquals(expectedPremiumWithoutMonitor, smartBand.getInsurancePremium(), 0.001);
    }
    @Test
    void testConnectToInternet() {
        assertEquals("Connects to the internet via Companion App", smartBand.connectToInternet());
        assertEquals("Connects to the internet via Companion App", smartBand.connectToInternet());
    }
}