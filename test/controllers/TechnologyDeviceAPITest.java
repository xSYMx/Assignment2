package controllers;

import models.Manufacturer;
import models.SmartWatch;
import models.Tablet;
import models.Technology;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class TechnologyDeviceAPITest {

    private Manufacturer apple = new Manufacturer("Apple", 1020);
    private Manufacturer samsung = new Manufacturer("Samsung", 1200);
    private Manufacturer hitachi = new Manufacturer("Hitachi", 1325);
    private Manufacturer tesla = new Manufacturer("Tesla", 3245);

    private TechnologyDeviceAPI populatedDevices = new TechnologyDeviceAPI(new File("technologyDevicesTest.xml"));
    private TechnologyDeviceAPI emptyDevices = new TechnologyDeviceAPI(new File("technologyDevicesemptyTest.xml"));

    @BeforeEach
    void setUp() {
        try {
            populatedDevices.load();
            emptyDevices.load();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Nested
    class GettersAndSetters {

    }

    @Nested
    class CRUDMethods {
        @Test
        void addNewTechnologyDevicetoEmpty() {
            assertEquals(0, emptyDevices.numberOfTechnologyDevices());
            Tablet newTab = new Tablet("Galaxy Tab S7", 799.99, tesla, "123456", "Snapdragon 865", 64, "Android");
            emptyDevices.addTechnologyDevice(newTab);
            assertEquals(1, emptyDevices.numberOfTechnologyDevices());

            Tablet newTab2 = new Tablet("Galaxy Tab S8", 799.99, samsung, "123457", "Snapdragon 865", 64, "Android");
            emptyDevices.addTechnologyDevice(newTab2);
            assertEquals(2, emptyDevices.numberOfTechnologyDevices());
        }

        @Test
        void addNewTechnologySameId() {
            assertEquals(0, emptyDevices.numberOfTechnologyDevices());
            Tablet newTab = new Tablet("Galaxy Tab S7", 799.99, tesla, "123456", "Snapdragon 865", 64, "Android");
            emptyDevices.addTechnologyDevice(newTab);
            assertEquals(1, emptyDevices.numberOfTechnologyDevices());

            Tablet newTab2 = new Tablet("Galaxy Tab S8", 799.99, samsung, "123456", "Snapdragon 865", 64, "Android");
            emptyDevices.addTechnologyDevice(newTab2);
            assertEquals(1, emptyDevices.numberOfTechnologyDevices());
        }

        @Test
        void updateTablet() {
            Tablet tablet = new Tablet("iPad Air", 799.99, apple, "A12345", "M1 Chip", 128, "iOS");
            emptyDevices.addTechnologyDevice(tablet);

            Tablet updatedTablet = new Tablet("iPad Air", 899.99, apple, "A12345", "M2 Chip", 256, "iOS");
            assertTrue(emptyDevices.updateTablet("iPad Air", updatedTablet));

            Technology retrievedTech = emptyDevices.getTechnologyDeviceById("iPad Air");
            assertNotNull(retrievedTech);
            assertEquals("iPad Air", retrievedTech.getModelName());
        }

        @Test
        void deleteTechnologyDeviceByIndex() {
            Tablet tablet = new Tablet("iPad Pro", 799.99, apple, "A12345", "M1 Chip", 128, "iOS");
            emptyDevices.addTechnologyDevice(tablet);

            Technology deletedTech = emptyDevices.deleteTechnologyDeviceByIndex(0);
            assertNotNull(deletedTech);
            assertEquals(0, emptyDevices.numberOfTechnologyDevices());
        }

        @Test
        void deleteTechnologyById() {
            Tablet tablet = new Tablet("iPad Pro", 799.99, apple, "A12345", "M1 Chip", 128, "iOS");
            emptyDevices.addTechnologyDevice(tablet);

            Technology deletedTech = emptyDevices.deleteTechnologyById("iPad Pro");
            assertNotNull(deletedTech);
            assertEquals(0, emptyDevices.numberOfTechnologyDevices());
        }
    }

    @Nested
    class ListingMethods {

        @Test
        void listAllReturnsNoTechnologyStoredWhenArrayListIsEmpty() {
            assertEquals(0, emptyDevices.numberOfTechnologyDevices());
            assertTrue(emptyDevices.listAllTechnologyDevices().toLowerCase().contains("no technology devices"));
        }

        @Test
        void listAllReturnsTechnologyDevicesStoredWhenArrayListHasTechnologyDevicesStored() {
            assertEquals(4, populatedDevices.numberOfTechnologyDevices());
            String populatedDeviceStr = populatedDevices.listAllTechnologyDevices();

            assertTrue(populatedDeviceStr.contains("ID: A123"));
            assertTrue(populatedDeviceStr.contains("ID: W1234"));
            assertTrue(populatedDeviceStr.contains("ID: T1223"));
            assertTrue(populatedDeviceStr.contains("ID: W3535"));
        }

        @Test
        void listBySelectedYearReturnsNoTechnologyDevicesWhenNoneExistForEnteredPrice() {
            assertEquals(4, populatedDevices.numberOfTechnologyDevices());
            String populatedDeviceStr = populatedDevices.listAllTechnologyAbovePrice(10000.99);
            assertTrue(populatedDeviceStr.contains("No technology more expensive than"));
        }
    }

    @Nested
    class ReportingMethods {

        @Test
        void numberOfTechnologyByChosenManufacturer() {
            Tablet tablet = new Tablet("iPad Pro", 799.99, apple, "A12345", "M1 Chip", 128, "iOS");
            SmartWatch smartWatch = new SmartWatch( "Apple Watch", 399.99, apple, "W12345","steel","Medium","OLED");
            emptyDevices.addTechnologyDevice(tablet);
            emptyDevices.addTechnologyDevice(smartWatch);

            assertEquals(2, emptyDevices.numberOfTechnologyByChosenManufacturer("Apple"));
        }
    }

    @Nested
    class SearchingMethods {

        @Test
        void isValidId() {
            Tablet tablet = new Tablet( "iPad Pro", 799.99, apple, "A12345", "M1 Chip", 128, "iOS");
            emptyDevices.addTechnologyDevice(tablet);

            assertFalse(emptyDevices.isValidId("Galaxy Tab S7"));
            assertTrue(emptyDevices.isValidId("iPad Pro"));
        }

        @Test
        void getTechnologyDeviceById() {
            Tablet tablet = new Tablet( "iPad Pro", 799.99, apple, "A12345", "M1 Chip", 128, "iOS");
            emptyDevices.addTechnologyDevice(tablet);

            Technology retrievedTech = emptyDevices.getTechnologyDeviceById("iPad Pro");
            assertNotNull(retrievedTech);
            assertEquals("iPad Pro", retrievedTech.getModelName());
        }
    }

    @Nested
    class SortingMethods {

        @Test
        void sortByCostDescendingReOrdersList() {
            assertEquals(4, populatedDevices.numberOfTechnologyDevices());

            assertEquals("smart watch1", populatedDevices.getTechnologyByIndex(0).getModelName());
            assertEquals("Smart Watch 12", populatedDevices.getTechnologyByIndex(1).getModelName());
            assertEquals("IPad 123", populatedDevices.getTechnologyByIndex(2).getModelName());
            assertEquals("HiTech Watch", populatedDevices.getTechnologyByIndex(3).getModelName());

            populatedDevices.sortByPriceDescending();

            assertEquals("IPad 123", populatedDevices.getTechnologyByIndex(0).getModelName());
            assertEquals("Smart Watch 12", populatedDevices.getTechnologyByIndex(1).getModelName());
            assertEquals("smart watch1", populatedDevices.getTechnologyByIndex(2).getModelName());
            assertEquals("HiTech Watch", populatedDevices.getTechnologyByIndex(3).getModelName());
        }

        @Test
        void sortByPriceDescendingDoesntCrashWhenListIsEmpty() {
            assertEquals(0, emptyDevices.numberOfTechnologyDevices());
            emptyDevices.sortByPriceDescending();
        }
    }
}